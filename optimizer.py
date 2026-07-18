import numpy as np
import random
from numba import njit
import multiprocessing as mp
import time
import gc

# -------------------------------------------------------------------
# CONFIGURATION
# -------------------------------------------------------------------
PROCESSES = 32          # Ryzen 9 7950X — 16 cores / 32 threads
NUM_ISLANDS = 8         # More islands = more diversity; one per 4 threads is a good rule
POP_PER_ISLAND = 200    # Larger population per island to fill those cores
GENERATIONS_PER_EPOCH = 25
EPOCHS = 500
SIM_MAX_GEN = 20000
CELL_PENALTY = 100      # Each starting cell costs 100 mana worth of fitness.

# Mana constants from actual Botania source (SubTileDandelifeon.java):
#   mana += prevGen * 150   (for each cell absorbed by the center 3x3, gen > 1)
#   max mana = 50,000
MANA_PER_GEN = 150
MAX_MANA = 50000

# -------------------------------------------------------------------
# LAYER 1: SIMULATOR (Game-accurate rules based on SubTileDandelifeon)
# -------------------------------------------------------------------
@njit
def simulate(grid_flat):
    """
    Runs the Dandelifeon simulation on a 25x25 grid.

    Mana is earned when a live cell enters the center 3x3 (rows 11-13, cols 11-13):
      - If the cell's generation > 1: mana += generation * 150
      - If generation == 1: cell dies silently (no mana), matching the game's
        `gen == 1 ? -1 : -2` branch in setBlockForGeneration().
    Total mana is capped at 50,000 (the flower's max pool).

    Returns (mana_earned, game_tick_when_absorbed).
    Returns (0, SIM_MAX_GEN) if nothing is ever absorbed.
    """
    grid = grid_flat.reshape((25, 25)).astype(np.int8)
    # age[i,j] tracks each cell's generation count (0 = just born, increments each tick)
    age = np.zeros((25, 25), dtype=np.int16)
    offsets = ((-1, -1), (-1, 0), (-1, 1), (0, -1), (0, 1), (1, -1), (1, 0), (1, 1))

    for gen in range(SIM_MAX_GEN):
        new_grid = np.zeros((25, 25), dtype=np.int8)
        new_age  = np.zeros((25, 25), dtype=np.int16)

        for i in range(25):
            for j in range(25):
                live    = 0
                max_age = -1
                for di, dj in offsets:
                    ni, nj = i + di, j + dj
                    if 0 <= ni < 25 and 0 <= nj < 25:
                        if grid[ni, nj] == 1:
                            live += 1
                            if age[ni, nj] > max_age:
                                max_age = age[ni, nj]

                if grid[i, j] == 1:
                    if live == 2 or live == 3:
                        new_grid[i, j] = 1
                        new_age[i, j]  = age[i, j] + 1
                else:
                    if live == 3:
                        new_grid[i, j] = 1
                        new_age[i, j]  = max_age + 1  # inherits highest neighbour gen

        # --- Absorption: cells that landed in the center 3x3 ---
        # Matches game logic: gen==1 → silent death (-1), gen>1 → absorbed (-2, earns mana)
        mana = 0
        for i in range(11, 14):
            for j in range(11, 14):
                if new_grid[i, j] == 1:
                    cell_gen = new_age[i, j]
                    if cell_gen > 1:                       # gen > 1 → earns mana
                        mana += cell_gen * MANA_PER_GEN
                    # Either way, wipe the cell (center is always cleared)
                    new_grid[i, j] = 0
                    new_age[i, j]  = 0

        if mana > 0:
            return min(mana, MAX_MANA), gen + 1

        # Natural early exit (pattern dead or stuck)
        total = 0
        for i in range(25):
            for j in range(25):
                total += new_grid[i, j]
        if gen > 2000 and total == 0:
            return 0, gen
        if gen > 5000 and total < 4:
            return 0, gen

        grid = new_grid
        age  = new_age

    return 0, SIM_MAX_GEN


# -------------------------------------------------------------------
# LAYER 2: GENETIC OPERATORS
# -------------------------------------------------------------------
def create_random():
    """Random genome biased toward low density (fewer starting cells = lower penalty)."""
    bits = np.zeros(625, dtype=np.int8)
    num_cells = random.randint(5, 80)
    positions = random.sample(range(625), num_cells)
    for pos in positions:
        row = pos // 25
        col = pos % 25
        if not (11 <= row <= 13 and 11 <= col <= 13):  # keep center clear
            bits[pos] = 1
    return bits


def crossover_2d(p1, p2):
    """Rectangular crossover on the 2D grid."""
    p1_2d = p1.copy().reshape((25, 25))
    p2_2d = p2.copy().reshape((25, 25))
    x1, x2 = sorted(random.sample(range(25), 2))
    y1, y2 = sorted(random.sample(range(25), 2))
    p1_2d[x1:x2, y1:y2], p2_2d[x1:x2, y1:y2] = p2_2d[x1:x2, y1:y2].copy(), p1_2d[x1:x2, y1:y2].copy()
    c1, c2 = p1_2d.flatten(), p2_2d.flatten()
    for i in range(11, 14):
        for j in range(11, 14):
            c1[i * 25 + j] = 0
            c2[i * 25 + j] = 0
    return c1, c2


def mutate(genome):
    """Bit-flips + block rotations."""
    g = genome.copy()
    for idx in range(625):
        if random.random() < 0.005:
            g[idx] = 1 - g[idx]
    if random.random() < 0.05:
        g_2d = g.reshape((25, 25))
        size = random.randint(3, 7)
        x = random.randint(0, 25 - size)
        y = random.randint(0, 25 - size)
        g_2d[x:x + size, y:y + size] = np.rot90(
            g_2d[x:x + size, y:y + size], k=random.randint(1, 3)
        )
        g = g_2d.flatten()
    for i in range(11, 14):
        for j in range(11, 14):
            g[i * 25 + j] = 0
    return g


# -------------------------------------------------------------------
# LAYER 3: ISLAND (island-model GA)
# -------------------------------------------------------------------
class Island:
    def __init__(self, pop_size):
        self.pop_size = pop_size
        self.population = [create_random() for _ in range(pop_size)]
        self.fitness_values = []
        self.best_score = -float('inf')
        self.best_genome = None

    def get_score(self, mana, initial_cells):
        return mana - (CELL_PENALTY * initial_cells)

    def evaluate(self, pool):
        initial_counts = [int(np.sum(ind)) for ind in self.population]
        results = pool.map(simulate, self.population)

        self.fitness_values = []
        for idx, (mana, gen) in enumerate(results):
            score = self.get_score(mana, initial_counts[idx])
            self.fitness_values.append(score)

            if score > self.best_score:
                self.best_score = score
                self.best_genome = self.population[idx].copy()
                if mana > 0 and initial_counts[idx] < 50:
                    print(f"  🧬 Efficient Hit: Mana={mana}, Cells={initial_counts[idx]}, Gen={gen} (Score={score})", flush=True)

    def evolve_epoch(self, pool):
        self.evaluate(pool)

        for _ in range(GENERATIONS_PER_EPOCH):
            new_pop = []
            sorted_idx = sorted(range(self.pop_size), key=lambda i: self.fitness_values[i], reverse=True)
            new_pop.append(self.population[sorted_idx[0]].copy())
            new_pop.append(self.population[sorted_idx[1]].copy())

            while len(new_pop) < self.pop_size:
                t1 = random.sample(range(self.pop_size), 3)
                p1 = max(t1, key=lambda i: self.fitness_values[i])
                t2 = random.sample(range(self.pop_size), 3)
                p2 = max(t2, key=lambda i: self.fitness_values[i])
                c1, c2 = crossover_2d(self.population[p1], self.population[p2])
                new_pop.append(mutate(c1))
                new_pop.append(mutate(c2))

            self.population = new_pop[:self.pop_size]
            self.evaluate(pool)
            gc.collect()


# -------------------------------------------------------------------
# LAYER 4: MAIN
# -------------------------------------------------------------------
def warmup():
    print("🔥 Warming up Numba JIT...", flush=True)
    dummy = np.zeros(625, dtype=np.int8)
    simulate(dummy)
    print("✅ Ready.\n", flush=True)


def print_grid(genome):
    """Print the central 15x15 crop of the best genome."""
    g = genome.reshape((25, 25))[5:20, 5:20]
    for row in g:
        print("".join("█" if c else "·" for c in row))


def run():
    warmup()
    islands = [Island(POP_PER_ISLAND) for _ in range(NUM_ISLANDS)]
    global_best_score  = -float('inf')
    global_best_genome = None

    print(f"🚀 Island GA | {NUM_ISLANDS} islands × {POP_PER_ISLAND} pop | Penalty: -{CELL_PENALTY}/cell | Max mana: {MAX_MANA}", flush=True)
    print(f"   Mana formula: generation × {MANA_PER_GEN} (matches SubTileDandelifeon.java)\n", flush=True)

    with mp.Pool(processes=PROCESSES, maxtasksperchild=100) as pool:
        for epoch in range(EPOCHS):
            start = time.perf_counter()

            for island in islands:
                island.evolve_epoch(pool)
                if island.best_score > global_best_score:
                    global_best_score  = island.best_score
                    global_best_genome = island.best_genome.copy()
                    mana, gen = simulate(global_best_genome)
                    cells = int(np.sum(global_best_genome))
                    print(f"\n🏆 NEW BEST: Mana={mana}, Cells={cells}, Gen={gen} (Score={global_best_score:.0f})", flush=True)
                    if mana >= MAX_MANA:
                        print("🎉 MAX MANA (50,000) ACHIEVED!")
                        pool.terminate()
                        return global_best_genome

            # Ring migration: best of island i → worst slot of island i+1
            for i in range(NUM_ISLANDS):
                neighbor  = (i + 1) % NUM_ISLANDS
                sorted_i  = sorted(range(POP_PER_ISLAND), key=lambda idx: islands[i].fitness_values[idx], reverse=True)
                sorted_n  = sorted(range(POP_PER_ISLAND), key=lambda idx: islands[neighbor].fitness_values[idx])
                islands[neighbor].population[sorted_n[0]] = islands[i].population[sorted_i[0]].copy()

            if epoch % 10 == 0:
                if global_best_genome is not None:
                    mana, gen = simulate(global_best_genome)
                    cells = int(np.sum(global_best_genome))
                else:
                    mana, gen, cells = 0, 0, 0
                elapsed = time.perf_counter() - start
                print(f"Epoch {epoch:>4}: Best Mana={mana:>6}, Cells={cells:>3}, Gen={gen:>6} ({elapsed:.1f}s/epoch)", flush=True)

    return global_best_genome


if __name__ == "__main__":
    start = time.perf_counter()
    best  = run()
    if best is not None:
        mana, gen = simulate(best)
        cells = int(np.sum(best))
        print("\n" + "=" * 50)
        print("🏅 OPTIMAL PATTERN:")
        print(f"   Mana       : {mana}")
        print(f"   Cells      : {cells}")
        print(f"   Generations: {gen}")
        print(f"   Efficiency : {mana / cells:.1f} mana/cell")
        print("\nGrid (central 15×15):")
        print_grid(best)
    print(f"\nRuntime: {(time.perf_counter() - start) / 60:.2f} minutes")
