/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.world.dim;

import java.util.ArrayList;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.tiles.TileCrystal;
import thaumcraft.common.tiles.TileEldritchCrabSpawner;

public class GenCommon {
    static ArrayList<ChunkCoordinates> decoCommon = new ArrayList();
    static ArrayList<ChunkCoordinates> crabSpawner = new ArrayList();
    static ArrayList<ChunkCoordinates> decoUrn = new ArrayList();
    static final int BEDROCK = 1;
    static final int BEDROCK_REPL = 99;
    static final int STONE = 2;
    static final int VOID = 8;
    static final int AIR_REPL = 9;
    static final int STAIR_DIRECTIONAL = 10;
    static final int STAIR_DIRECTIONAL_INV = 11;
    static final int SLAB = 12;
    static final int DOOR_BLOCK = 15;
    static final int DOOR_LOCK = 16;
    static final int VOID_DOOR = 17;
    static final int ROCK = 18;
    static final int STONE_NOSPAWN = 19;
    static final int STONE_TRAPPED = 20;
    static final int CRUST = 21;
    static final int[][] PAT_CONNECT = new int[][]{{0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}, {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1}, {1, 8, 8, 2, 2, 2, 2, 2, 8, 8, 1}, {1, 8, 2, 5, 9, 9, 9, 6, 2, 8, 1}, {1, 8, 2, 9, 9, 9, 9, 9, 2, 8, 1}, {1, 8, 2, 9, 9, 9, 9, 9, 2, 8, 1}, {1, 8, 2, 9, 9, 9, 9, 9, 2, 8, 1}, {1, 8, 2, 3, 9, 9, 9, 4, 2, 8, 1}, {1, 8, 8, 2, 2, 2, 2, 2, 8, 8, 1}, {1, 8, 8, 8, 8, 8, 8, 8, 8, 8, 1}, {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0}};

    static void placeBlock(World world, int i, int j, int k, int l, Cell cell) {
        GenCommon.placeBlock(world, i, j, k, l, ForgeDirection.UNKNOWN, cell);
    }

    static void placeBlock(World world, int x, int y, int z, int b, ForgeDirection dir, Cell cell) {
        Block block = null;
        int meta = 0;
        block0 : switch (b) {
            case 1: {
                if (!world.func_147437_c(x, y, z)) break;
                block = Blocks.field_150357_h;
                break;
            }
            case 15: {
                block = ConfigBlocks.blockEldritch;
                meta = 7;
                decoCommon.remove(new ChunkCoordinates(x, y, z));
                crabSpawner.remove(new ChunkCoordinates(x, y, z));
                decoUrn.remove(new ChunkCoordinates(x, y, z));
                break;
            }
            case 16: {
                block = ConfigBlocks.blockEldritch;
                meta = 8;
                decoCommon.remove(new ChunkCoordinates(x, y, z));
                crabSpawner.remove(new ChunkCoordinates(x, y, z));
                decoUrn.remove(new ChunkCoordinates(x, y, z));
                break;
            }
            case 99: {
                block = Blocks.field_150357_h;
                break;
            }
            case 8: {
                block = ConfigBlocks.blockEldritchNothing;
                break;
            }
            case 17: {
                block = ConfigBlocks.blockAiry;
                meta = 12;
                break;
            }
            case 9: {
                block = Blocks.field_150350_a;
                decoCommon.remove(new ChunkCoordinates(x, y, z));
                crabSpawner.remove(new ChunkCoordinates(x, y, z));
                decoUrn.remove(new ChunkCoordinates(x, y, z));
                break;
            }
            case 2: {
                if (cell.feature != 7 || world.field_73012_v.nextInt(3) != 0) {
                    if (world.func_147439_a(x, y, z) == ConfigBlocks.blockEldritchNothing) break;
                    if (world.field_73012_v.nextInt(25) == 0) {
                        boolean crab;
                        boolean bl = cell.feature == 7 ? true : (crab = world.field_73012_v.nextInt(50) == 0);
                        if (crab && cell.feature == 0 || crab && cell.feature == 7) {
                            crabSpawner.add(new ChunkCoordinates(x, y, z));
                        } else {
                            decoCommon.add(new ChunkCoordinates(x, y, z));
                        }
                    }
                    block = ConfigBlocks.blockCosmeticSolid;
                    meta = 11;
                    break;
                }
            }
            case 21: {
                boolean crab;
                if (world.func_147439_a(x, y, z) == ConfigBlocks.blockEldritchNothing) break;
                block = ConfigBlocks.blockCosmeticSolid;
                meta = 14;
                if (world.field_73012_v.nextInt(25) == 0) {
                    block = ConfigBlocks.blockEldritch;
                    meta = 4;
                    break;
                }
                if (world.field_73012_v.nextInt(25) != 0) break;
                boolean bl = cell.feature == 7 ? true : (cell.feature == 12 && world.field_73012_v.nextBoolean() ? true : (crab = world.field_73012_v.nextInt(25) == 0));
                if (!(crab && cell.feature == 0 || crab && cell.feature == 7) && (!crab || cell.feature != 12)) break;
                crabSpawner.add(new ChunkCoordinates(x, y, z));
                break;
            }
            case 18: {
                if (world.func_147439_a(x, y, z) == ConfigBlocks.blockEldritchNothing) break;
                block = ConfigBlocks.blockCosmeticSolid;
                meta = 12;
                break;
            }
            case 19: {
                if (world.func_147439_a(x, y, z) == ConfigBlocks.blockEldritchNothing) break;
                block = ConfigBlocks.blockCosmeticSolid;
                meta = 13;
                break;
            }
            case 20: {
                if (world.func_147439_a(x, y, z) == ConfigBlocks.blockEldritchNothing) break;
                block = ConfigBlocks.blockEldritch;
                meta = 10;
                break;
            }
            case 10: {
                block = ConfigBlocks.blockStairsEldritch;
                switch (dir) {
                    case NORTH: {
                        meta = 3;
                        break block0;
                    }
                    case SOUTH: {
                        meta = 2;
                        break block0;
                    }
                    case EAST: {
                        meta = 0;
                        break block0;
                    }
                    case WEST: {
                        meta = 1;
                        break block0;
                    }
                }
                break;
            }
            case 11: {
                block = ConfigBlocks.blockStairsEldritch;
                switch (dir) {
                    case NORTH: {
                        meta = 7;
                        break block0;
                    }
                    case SOUTH: {
                        meta = 6;
                        break block0;
                    }
                    case EAST: {
                        meta = 4;
                        break block0;
                    }
                    case WEST: {
                        meta = 5;
                        break block0;
                    }
                }
                break;
            }
            case 3: {
                if ((double)world.field_73012_v.nextFloat() < 0.005) {
                    decoUrn.add(new ChunkCoordinates(x, y, z));
                }
                block = ConfigBlocks.blockStairsEldritch;
                switch (dir.ordinal()) {
                    case 2: 
                    case 3: {
                        meta = 1;
                        break;
                    }
                    case 4: 
                    case 5: {
                        meta = 3;
                    }
                }
                break;
            }
            case 4: {
                if ((double)world.field_73012_v.nextFloat() < 0.005) {
                    decoUrn.add(new ChunkCoordinates(x, y, z));
                }
                block = ConfigBlocks.blockStairsEldritch;
                switch (dir.ordinal()) {
                    case 2: 
                    case 3: {
                        meta = 0;
                        break;
                    }
                    case 4: 
                    case 5: {
                        meta = 2;
                    }
                }
                break;
            }
            case 5: {
                block = ConfigBlocks.blockStairsEldritch;
                switch (dir.ordinal()) {
                    case 2: 
                    case 3: {
                        meta = 5;
                        break;
                    }
                    case 4: 
                    case 5: {
                        meta = 7;
                    }
                }
                break;
            }
            case 6: {
                block = ConfigBlocks.blockStairsEldritch;
                switch (dir.ordinal()) {
                    case 2: 
                    case 3: {
                        meta = 4;
                        break;
                    }
                    case 4: 
                    case 5: {
                        meta = 6;
                    }
                }
                break;
            }
            case 7: {
                block = ConfigBlocks.blockEldritch;
                meta = 4;
            }
        }
        if (block != null) {
            world.func_147465_d(x, y, z, block, meta, block == ConfigBlocks.blockEldritchNothing || block == Blocks.field_150357_h || block == Blocks.field_150350_a ? 0 : 3);
        }
    }

    public static void genObelisk(World world, int x, int y, int z) {
        world.func_147465_d(x, y, z, ConfigBlocks.blockEldritch, 1, 3);
        world.func_147465_d(x, y + 1, z, ConfigBlocks.blockEldritch, 2, 3);
        world.func_147465_d(x, y + 2, z, ConfigBlocks.blockEldritch, 2, 3);
        world.func_147465_d(x, y + 3, z, ConfigBlocks.blockEldritch, 2, 3);
        world.func_147465_d(x, y + 4, z, ConfigBlocks.blockEldritch, 2, 3);
    }

    static void processDecorations(World world) {
        int meta;
        for (ChunkCoordinates cc : decoUrn) {
            if (!world.func_147437_c(cc.field_71574_a, cc.field_71572_b + 1, cc.field_71573_c)) continue;
            world.func_147465_d(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockCosmeticSolid, 15, 3);
            float rr = world.field_73012_v.nextFloat();
            meta = rr < 0.025f ? 2 : (rr < 0.1f ? 1 : 0);
            world.func_147465_d(cc.field_71574_a, cc.field_71572_b + 1, cc.field_71573_c, ConfigBlocks.blockLootUrn, meta, 3);
        }
        block1: for (ChunkCoordinates cc : decoCommon) {
            int exp = BlockUtils.countExposedSides(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
            if (exp <= 0 || exp != 1 && GenCommon.isBedrockShowing(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c) || BlockUtils.isBlockAdjacentToAtleast((IBlockAccess)world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockEldritch, Short.MAX_VALUE, 1)) continue;
            meta = world.field_73012_v.nextInt(3) != 0 ? 4 : (world.field_73012_v.nextInt(8) != 0 ? 5 : 10);
            world.func_147465_d(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockEldritch, meta, 3);
            if (meta != 4 || world.field_73012_v.nextInt(12) != 0) continue;
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                if (!world.func_147437_c(cc.field_71574_a + dir.offsetX, cc.field_71572_b + dir.offsetY, cc.field_71573_c + dir.offsetZ)) continue;
                world.func_147465_d(cc.field_71574_a + dir.offsetX, cc.field_71572_b + dir.offsetY, cc.field_71573_c + dir.offsetZ, ConfigBlocks.blockCrystal, 7, 3);
                TileCrystal te = (TileCrystal)world.func_147438_o(cc.field_71574_a + dir.offsetX, cc.field_71572_b + dir.offsetY, cc.field_71573_c + dir.offsetZ);
                te.orientation = (short)dir.ordinal();
                continue block1;
            }
        }
        block3: for (ChunkCoordinates cc : crabSpawner) {
            int exp = BlockUtils.countExposedSides(world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
            if (exp != 1 || BlockUtils.isBlockAdjacentToAtleast((IBlockAccess)world, cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockEldritch, Short.MAX_VALUE, 1)) continue;
            world.func_147465_d(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c, ConfigBlocks.blockEldritch, 9, 3);
            TileEntity te = world.func_147438_o(cc.field_71574_a, cc.field_71572_b, cc.field_71573_c);
            if (te == null || !(te instanceof TileEldritchCrabSpawner)) continue;
            for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
                if (!world.func_147437_c(cc.field_71574_a + dir.offsetX, cc.field_71572_b + dir.offsetY, cc.field_71573_c + dir.offsetZ)) continue;
                ((TileEldritchCrabSpawner)te).setFacing((byte)dir.ordinal());
                continue block3;
            }
        }
        decoCommon.clear();
        crabSpawner.clear();
        decoUrn.clear();
    }

    static boolean isBedrockShowing(World world, int x, int y, int z) {
        for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            if (world.func_147439_a(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ).func_149662_c() || world.func_147439_a(x + dir.getOpposite().offsetX, y + dir.getOpposite().offsetY, z + dir.getOpposite().offsetZ) != Blocks.field_150357_h && world.func_147439_a(x + dir.getOpposite().offsetX, y + dir.getOpposite().offsetY, z + dir.getOpposite().offsetZ) != ConfigBlocks.blockEldritchNothing) continue;
            return true;
        }
        return false;
    }

    static void generateConnections(World world, Random random, int cx, int cz, int y, Cell cell, int depth, boolean justthetip) {
        int h;
        int w;
        int d;
        int x = cx * 16;
        int z = cz * 16;
        if (cell.north) {
            for (d = 0; d <= depth; ++d) {
                int n = d == depth && justthetip ? 2 : (w = d == depth - 1 && justthetip ? 1 : 0);
                while (w < (d == depth && justthetip ? 9 : (d == depth - 1 && justthetip ? 10 : 11))) {
                    int n2 = d == depth && justthetip ? 2 : (h = d == depth - 1 && justthetip ? 1 : 0);
                    while (h < (d == depth && justthetip ? 9 : (d == depth - 1 && justthetip ? 10 : 11))) {
                        if (d != depth || !justthetip || PAT_CONNECT[h][w] != 8) {
                            GenCommon.placeBlock(world, x + 3 + w, y + 10 - h, z + d, PAT_CONNECT[h][w], ForgeDirection.NORTH, cell);
                        }
                        ++h;
                    }
                    ++w;
                }
            }
        }
        if (cell.south) {
            for (d = 0; d <= depth; ++d) {
                int n = d == depth && justthetip ? 2 : (w = d == depth - 1 && justthetip ? 1 : 0);
                while (w < (d == depth && justthetip ? 9 : (d == depth - 1 && justthetip ? 10 : 11))) {
                    int n3 = d == depth && justthetip ? 2 : (h = d == depth - 1 && justthetip ? 1 : 0);
                    while (h < (d == depth && justthetip ? 9 : (d == depth - 1 && justthetip ? 10 : 11))) {
                        if (d != depth || !justthetip || PAT_CONNECT[h][w] != 8) {
                            GenCommon.placeBlock(world, x + 3 + w, y + 10 - h, z + 16 - d, PAT_CONNECT[h][w], ForgeDirection.SOUTH, cell);
                        }
                        ++h;
                    }
                    ++w;
                }
            }
        }
        if (cell.east) {
            for (d = 0; d <= depth; ++d) {
                int n = d == depth && justthetip ? 2 : (w = d == depth - 1 && justthetip ? 1 : 0);
                while (w < (d == depth && justthetip ? 9 : (d == depth - 1 && justthetip ? 10 : 11))) {
                    int n4 = d == depth && justthetip ? 2 : (h = d == depth - 1 && justthetip ? 1 : 0);
                    while (h < (d == depth && justthetip ? 9 : (d == depth - 1 && justthetip ? 10 : 11))) {
                        if (d != depth || !justthetip || PAT_CONNECT[h][w] != 8) {
                            GenCommon.placeBlock(world, x + 16 - d, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], ForgeDirection.EAST, cell);
                        }
                        ++h;
                    }
                    ++w;
                }
            }
        }
        if (cell.west) {
            for (d = 0; d <= depth; ++d) {
                int n = d == depth && justthetip ? 2 : (w = d == depth - 1 && justthetip ? 1 : 0);
                while (w < (d == depth && justthetip ? 9 : (d == depth - 1 && justthetip ? 10 : 11))) {
                    int n5 = d == depth && justthetip ? 2 : (h = d == depth - 1 && justthetip ? 1 : 0);
                    while (h < (d == depth && justthetip ? 9 : (d == depth - 1 && justthetip ? 10 : 11))) {
                        if (d != depth || !justthetip || PAT_CONNECT[h][w] != 8) {
                            GenCommon.placeBlock(world, x + d, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], ForgeDirection.WEST, cell);
                        }
                        ++h;
                    }
                    ++w;
                }
            }
        }
    }
}

