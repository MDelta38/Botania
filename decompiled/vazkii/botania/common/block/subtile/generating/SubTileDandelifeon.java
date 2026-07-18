/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.subtile.generating;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.TileCell;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileDandelifeon
extends SubTileGenerating {
    private static final int RANGE = 12;
    private static final int SPEED = 10;
    private static final int MAX_GENERATIONS = 60;
    private static final int MANA_PER_GEN = 150;
    private static final int[][] ADJACENT_BLOCKS = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}};

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (!this.supertile.func_145831_w().field_72995_K && this.redstoneSignal > 0 && this.ticksExisted % 10 == 0) {
            this.runSimulation();
        }
    }

    void runSimulation() {
        int[][] table = this.getCellTable();
        ArrayList<int[]> changes = new ArrayList<int[]>();
        new ArrayList();
        boolean wipe = false;
        for (int i = 0; i < table.length; ++i) {
            for (int j = 0; j < table[0].length; ++j) {
                int gen = table[i][j];
                int adj = this.getAdjCells(table, i, j);
                int newVal = gen;
                if (adj < 2 || adj > 3) {
                    newVal = -1;
                } else if (adj == 3 && gen == -1) {
                    newVal = this.getSpawnCellGeneration(table, i, j);
                } else if (gen > -1) {
                    newVal = gen + 1;
                }
                int xdist = Math.abs(i - 12);
                int zdist = Math.abs(j - 12);
                int allowDist = 1;
                if (xdist <= allowDist && zdist <= allowDist && newVal > -1) {
                    gen = newVal;
                    int n = newVal = gen == 1 ? -1 : -2;
                }
                if (newVal == gen) continue;
                changes.add(new int[]{i, j, newVal, gen});
                if (newVal != -2) continue;
                wipe = true;
            }
        }
        int x = this.supertile.field_145851_c;
        int y = this.supertile.field_145848_d;
        int z = this.supertile.field_145849_e;
        for (int[] change : changes) {
            int px = x - 12 + change[0];
            int pz = z - 12 + change[1];
            int val = change[2];
            if (val != -2 && wipe) {
                val = -1;
            }
            int old = change[3];
            this.setBlockForGeneration(px, y, pz, val, old);
        }
    }

    int[][] getCellTable() {
        int diam = 25;
        int[][] table = new int[diam][diam];
        int x = this.supertile.field_145851_c;
        int y = this.supertile.field_145848_d;
        int z = this.supertile.field_145849_e;
        for (int i = 0; i < diam; ++i) {
            for (int j = 0; j < diam; ++j) {
                int px = x - 12 + i;
                int pz = z - 12 + j;
                table[i][j] = this.getCellGeneration(px, y, pz);
            }
        }
        return table;
    }

    int getCellGeneration(int x, int y, int z) {
        TileEntity tile = this.supertile.func_145831_w().func_147438_o(x, y, z);
        if (tile instanceof TileCell) {
            return ((TileCell)tile).isSameFlower(this.supertile) ? ((TileCell)tile).getGeneration() : 0;
        }
        return -1;
    }

    int getAdjCells(int[][] table, int x, int z) {
        int count = 0;
        for (int[] shift : ADJACENT_BLOCKS) {
            int gen;
            int xp = x + shift[0];
            int zp = z + shift[1];
            if (this.isOffBounds(table, xp, zp) || (gen = table[xp][zp]) < 0) continue;
            ++count;
        }
        return count;
    }

    int getSpawnCellGeneration(int[][] table, int x, int z) {
        int max = -1;
        for (int[] shift : ADJACENT_BLOCKS) {
            int gen;
            int xp = x + shift[0];
            int zp = z + shift[1];
            if (this.isOffBounds(table, xp, zp) || (gen = table[xp][zp]) <= max) continue;
            max = gen;
        }
        return max == -1 ? -1 : max + 1;
    }

    boolean isOffBounds(int[][] table, int x, int z) {
        return x < 0 || z < 0 || x >= table.length || z >= table[0].length;
    }

    void setBlockForGeneration(int x, int y, int z, int gen, int prevGen) {
        World world = this.supertile.func_145831_w();
        Block blockAt = world.func_147439_a(x, y, z);
        TileEntity tile = world.func_147438_o(x, y, z);
        if (gen == -2) {
            int val = prevGen * 150;
            this.mana = Math.min(this.getMaxMana(), this.mana + val);
        } else if (blockAt == ModBlocks.cellBlock) {
            if (gen < 0 || gen > 60) {
                world.func_147468_f(x, y, z);
            } else {
                ((TileCell)tile).setGeneration(this.supertile, gen);
            }
        } else if (gen >= 0 && blockAt.isAir((IBlockAccess)this.supertile.func_145831_w(), x, y, z)) {
            world.func_147449_b(x, y, z, ModBlocks.cellBlock);
            tile = world.func_147438_o(x, y, z);
            ((TileCell)tile).setGeneration(this.supertile, gen);
        }
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), 12);
    }

    @Override
    public int getMaxMana() {
        return 50000;
    }

    @Override
    public int getColor() {
        return 10226302;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.dandelifeon;
    }
}

