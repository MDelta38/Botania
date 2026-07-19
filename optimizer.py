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
SIM_MAX_GEN = 500      # Gen>60 rule kills all old cells; useful patterns fire within ~200 ticks
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

    Rules (all sourced from SubTileDandelifeon.java):
      - Standard Conway's GoL survival/birth on the full 25x25 grid.
      - Generation tracking: survivors age by +1 each tick; newborns inherit
        (max neighbour generation + 1).
      - Gen > 60 death: any cell outside the center 3x3 whose generation would
        exceed 60 is forcibly destroyed (setBlockForGeneration, line 145).
        This caps useful cell age at ~61 ticks — patterns relying on gen 183+
        are impossible in-game.
      - Absorption: cells that enter the center 3x3 (rows/cols 11-13) are wiped.
        If generation > 1: mana += generation * 150.
        If generation == 1: silent death, no mana (gen==1 ? -1 : -2 branch).
      - Total mana capped at 50,000.

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

        # --- Gen > 60 death rule (SubTileDandelifeon.setBlockForGeneration line 145) ---
        # Any cell outside the center 3x3 that has aged past generation 60 is forcibly
        # removed.  Our simulator was missing this entirely, which let cells reach gen 183+
        # — impossible in-game.  Cap is 60 outside the kill zone; center cells are handled
        # separately below (they get absorbed before this rule would kill them).
        for i in range(25):
            for j in range(25):
                if new_grid[i, j] == 1 and new_age[i, j] > 60:
                    if not (11 <= i <= 13 and 11 <= j <= 13):
                        new_grid[i, j] = 0
                        new_age[i, j]  = 0

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

        # Count live cells in new_grid for early exit checks
        total = 0
        for i in range(25):
            for j in range(25):
                total += new_grid[i, j]

        # Exit immediately if the grid is completely dead — no point simulating empty space
        if total == 0:
            return 0, gen

        # Exit early if pattern is tiny and stuck (still life / oscillator, no path to center)
        if gen > 150 and total < 4:
            return 0, gen

        grid = new_grid
        age  = new_age

    return 0, SIM_MAX_GEN


# -------------------------------------------------------------------
# LAYER 2: GENETIC OPERATORS
# -------------------------------------------------------------------
def create_random():
    """
    Cluster-based seeding biased toward the outer ring of the grid.

    Under the gen>60 death rule, cells need DISTANCE from the center to accumulate
    generation count before they arrive.  A glider from corner (0,0) takes ~48 ticks
    to reach center (12,12) — well within the 60-gen budget.  Anchors placed near the
    center have too little time to age and arrive with gen 1-2, earning nearly nothing.

    Strategy: 70% chance each anchor is placed in the outer 6-cell border; 30% anywhere.
    Isolated single cells still die on tick 1, so clusters of 2-6 are enforced.
    """
    bits = np.zeros((25, 25), dtype=np.int8)
    num_clusters = random.randint(2, 8)

    for _ in range(num_clusters):
        for _attempt in range(20):
            if random.random() < 0.7:
                # Outer border: rows/cols 0-5 or 19-24
                if random.random() < 0.5:
                    ar = random.choice(list(range(0, 6)) + list(range(19, 25)))
                    ac = random.randint(0, 24)
                else:
                    ar = random.randint(0, 24)
                    ac = random.choice(list(range(0, 6)) + list(range(19, 25)))
            else:
                ar = random.randint(0, 24)
                ac = random.randint(0, 24)
            if not (11 <= ar <= 13 and 11 <= ac <= 13):
                break

        # Place 2–6 cells in a tight radius around the anchor
        cluster_size = random.randint(2, 6)
        for _ in range(cluster_size):
            for _attempt in range(10):
                r = ar + random.randint(-2, 2)
                c = ac + random.randint(-2, 2)
                if 0 <= r < 25 and 0 <= c < 25 and not (11 <= r <= 13 and 11 <= c <= 13):
                    bits[r, c] = 1
                    break

    return bits.flatten()


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
    """
    Three mutation types:
    1. Bit-flips  — low rate random toggles (explore new positions).
    2. Nudge      — move an existing live cell to an adjacent slot (preserves local
                    density so cells keep their neighbours instead of scattering).
    3. Block rotation — rearranges a rectangular patch without changing cell count.
    """
    g = genome.copy().reshape((25, 25))

    # 1. Bit-flips (low rate to avoid scattering clusters into singletons)
    for i in range(25):
        for j in range(25):
            if random.random() < 0.003:
                if not (11 <= i <= 13 and 11 <= j <= 13):
                    g[i, j] = 1 - g[i, j]

    # 2. Nudge: pick a live cell and slide it one step in a random direction
    if random.random() < 0.4:
        live_cells = [(i, j) for i in range(25) for j in range(25) if g[i, j] == 1]
        if live_cells:
            r, c = random.choice(live_cells)
            dr, dc = random.choice([(-1,0),(1,0),(0,-1),(0,1),(-1,-1),(-1,1),(1,-1),(1,1)])
            nr, nc = r + dr, c + dc
            if 0 <= nr < 25 and 0 <= nc < 25 and not (11 <= nr <= 13 and 11 <= nc <= 13):
                g[r, c] = 0
                g[nr, nc] = 1

    # 3. Block rotation
    if random.random() < 0.05:
        size = random.randint(3, 7)
        x = random.randint(0, 25 - size)
        y = random.randint(0, 25 - size)
        g[x:x + size, y:y + size] = np.rot90(
            g[x:x + size, y:y + size], k=random.randint(1, 3)
        )

    # Always clear the center kill zone
    g[11:14, 11:14] = 0
    return g.flatten()


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
    """Print the full 25x25 grid with the center 3x3 marked as [F] for the flower."""
    g = genome.reshape((25, 25))
    for i, row in enumerate(g):
        line = ""
        for j, c in enumerate(row):
            if 11 <= i <= 13 and 11 <= j <= 13:
                line += "F"   # flower / kill zone
            else:
                line += "█" if c else "·"
        print(line)


def run():
    warmup()
    islands = [Island(POP_PER_ISLAND) for _ in range(NUM_ISLANDS)]
    global_best_score  = -float('inf')
    global_best_genome = None

    # Also track the fewest-cell pattern that still hits max mana separately.
    # Score keeps climbing as cells drop while mana stays at cap, so we surface this explicitly.
    best_maxmana_cells  = float('inf')
    best_maxmana_genome = None

    print(f"🚀 Island GA | {NUM_ISLANDS} islands × {POP_PER_ISLAND} pop | Penalty: -{CELL_PENALTY}/cell | Max mana: {MAX_MANA}", flush=True)
    print(f"   Mana formula: generation × {MANA_PER_GEN} (matches SubTileDandelifeon.java)", flush=True)
    print(f"   Gen>60 death rule active — cells outside center 3×3 are wiped at gen 61+", flush=True)
    print(f"   Max single-cell mana: 61 × {MANA_PER_GEN} = {61*MANA_PER_GEN}  → need ~{-(-MAX_MANA//(61*MANA_PER_GEN))} cells at max gen to hit {MAX_MANA}", flush=True)
    print(f"   Goal: find the FEWEST starting cells that still produce {MAX_MANA} mana\n", flush=True)

    with mp.Pool(processes=PROCESSES, maxtasksperchild=100) as pool:
        for epoch in range(EPOCHS):
            epoch_start = time.perf_counter()

            for island in islands:
                island.evolve_epoch(pool)
                if island.best_score > global_best_score:
                    global_best_score  = island.best_score
                    global_best_genome = island.best_genome.copy()
                    mana, gen = simulate(global_best_genome)
                    cells = int(np.sum(global_best_genome))
                    print(f"\n🏆 NEW BEST SCORE: Mana={mana}, Cells={cells}, Gen={gen} (Score={global_best_score:.0f})", flush=True)

                    if mana >= MAX_MANA and cells < best_maxmana_cells:
                        best_maxmana_cells  = cells
                        best_maxmana_genome = global_best_genome.copy()
                        print(f"   ✨ New fewest-cell max-mana record: {cells} cells!", flush=True)

            # Ring migration: best of island i → worst slot of island i+1
            for i in range(NUM_ISLANDS):
                neighbor = (i + 1) % NUM_ISLANDS
                sorted_i = sorted(range(POP_PER_ISLAND), key=lambda idx: islands[i].fitness_values[idx], reverse=True)
                sorted_n = sorted(range(POP_PER_ISLAND), key=lambda idx: islands[neighbor].fitness_values[idx])
                islands[neighbor].population[sorted_n[0]] = islands[i].population[sorted_i[0]].copy()

            if epoch % 10 == 0:
                if global_best_genome is not None:
                    mana, gen = simulate(global_best_genome)
                    cells = int(np.sum(global_best_genome))
                else:
                    mana, gen, cells = 0, 0, 0
                elapsed = time.perf_counter() - epoch_start
                rec = f" | ✨ MaxMana record: {best_maxmana_cells} cells" if best_maxmana_genome is not None else ""
                print(f"Epoch {epoch:>4}: Best Mana={mana:>6}, Cells={cells:>3}, Gen={gen:>6} ({elapsed:.1f}s/epoch){rec}", flush=True)

    # Return the most cell-efficient max-mana pattern if found, else highest-scoring overall
    return best_maxmana_genome if best_maxmana_genome is not None else global_best_genome


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
        print("\nFull 25×25 grid (F = flower kill zone, █ = starting cell, · = empty):")
        print_grid(best)
    print(f"\nRuntime: {(time.perf_counter() - start) / 60:.2f} minutes")
