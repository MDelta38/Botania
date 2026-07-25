/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.world.dim;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.GenCommon;

public class GenPassage
extends GenCommon {
    static void generateDefaultPassage(World world, Random random, int cx, int cz, int y, Cell cell) {
        int j;
        int h;
        int w;
        int mod;
        int z;
        int x;
        block55: {
            block54: {
                block53: {
                    block52: {
                        block51: {
                            block50: {
                                block49: {
                                    block48: {
                                        x = cx * 16;
                                        z = cz * 16;
                                        GenPassage.generateConnections(world, random, cx, cz, y, cell, 4, false);
                                        mod = 0;
                                        if (cell.north && cell.south && cell.west && cell.east && random.nextBoolean()) {
                                            mod = 1;
                                        }
                                        for (w = 1; w < 8; ++w) {
                                            for (h = 1; h < 8; ++h) {
                                                if (w == 4 && h == 4 && mod == 1) {
                                                    GenPassage.placeBlock(world, x + 4 + w, y + 2, z + 4 + h, 7, cell);
                                                    GenPassage.placeBlock(world, x + 4 + w, y + 8, z + 4 + h, 7, cell);
                                                } else {
                                                    GenPassage.placeBlock(world, x + 4 + w, y + 2, z + 4 + h, cell.feature == 11 && random.nextInt(3) == 0 ? 20 : 2, cell);
                                                    GenPassage.placeBlock(world, x + 4 + w, y + 8, z + 4 + h, cell.feature == 11 && random.nextInt(3) == 0 ? 20 : 2, cell);
                                                }
                                                GenPassage.placeBlock(world, x + 4 + w, y, z + 4 + h, 1, cell);
                                                GenPassage.placeBlock(world, x + 4 + w, y + 10, z + 4 + h, 1, cell);
                                                GenPassage.placeBlock(world, x + 4 + w, y + 1, z + 4 + h, 8, cell);
                                                GenPassage.placeBlock(world, x + 4 + w, y + 9, z + 4 + h, 8, cell);
                                            }
                                        }
                                        if (!cell.north) break block48;
                                        for (w = 2 + mod; w < 9 - mod; ++w) {
                                            for (h = 2 + mod; h < 9 - mod; ++h) {
                                                GenPassage.placeBlock(world, x + 3 + w, y + 10 - h, z + 5, PAT_CONNECT[h][w], ForgeDirection.NORTH, cell);
                                            }
                                        }
                                        if (mod != 0) break block49;
                                        if (cell.west) {
                                            GenPassage.placeBlock(world, x + 6, y + 3, z + 6, 3, ForgeDirection.EAST, cell);
                                            GenPassage.placeBlock(world, x + 6, y + 7, z + 6, 5, ForgeDirection.EAST, cell);
                                        }
                                        if (!cell.east) break block49;
                                        GenPassage.placeBlock(world, x + 10, y + 3, z + 6, 3, ForgeDirection.EAST, cell);
                                        GenPassage.placeBlock(world, x + 10, y + 7, z + 6, 5, ForgeDirection.EAST, cell);
                                        break block49;
                                    }
                                    for (w = 1; w < 8; ++w) {
                                        for (h = 1; h < 8; ++h) {
                                            GenPassage.placeBlock(world, x + 4 + w, y + 9 - h, z + 5, cell.feature == 11 && random.nextInt(3) == 0 ? 20 : 2, cell);
                                            GenPassage.placeBlock(world, x + 4 + w, y + 9 - h, z + 4, 8, cell);
                                            GenPassage.placeBlock(world, x + 4 + w, y + 9 - h, z + 3, 1, cell);
                                            if (h == 7) {
                                                GenPassage.placeBlock(world, x + 4 + w, y + 1, z + 4, 1, cell);
                                                GenPassage.placeBlock(world, x + 4 + w, y + 9, z + 4, 1, cell);
                                            }
                                            if (w != 7) continue;
                                            GenPassage.placeBlock(world, x + 4, y + 9 - h, z + 4, 1, cell);
                                            GenPassage.placeBlock(world, x + 12, y + 9 - h, z + 4, 1, cell);
                                        }
                                    }
                                    for (w = 2; w < 7; ++w) {
                                        GenPassage.placeBlock(world, x + 4 + w, y + 3, z + 6, 3, ForgeDirection.EAST, cell);
                                        GenPassage.placeBlock(world, x + 4 + w, y + 7, z + 6, 5, ForgeDirection.EAST, cell);
                                    }
                                }
                                if (!cell.south) break block50;
                                for (w = 2 + mod; w < 9 - mod; ++w) {
                                    for (h = 2 + mod; h < 9 - mod; ++h) {
                                        GenPassage.placeBlock(world, x + 3 + w, y + 10 - h, z + 11, PAT_CONNECT[h][w], ForgeDirection.SOUTH, cell);
                                    }
                                }
                                if (mod != 0) break block51;
                                if (cell.west) {
                                    GenPassage.placeBlock(world, x + 6, y + 3, z + 10, 4, ForgeDirection.EAST, cell);
                                    GenPassage.placeBlock(world, x + 6, y + 7, z + 10, 6, ForgeDirection.EAST, cell);
                                }
                                if (!cell.east) break block51;
                                GenPassage.placeBlock(world, x + 10, y + 3, z + 10, 4, ForgeDirection.EAST, cell);
                                GenPassage.placeBlock(world, x + 10, y + 7, z + 10, 6, ForgeDirection.EAST, cell);
                                break block51;
                            }
                            for (w = 1; w < 8; ++w) {
                                for (h = 1; h < 8; ++h) {
                                    GenPassage.placeBlock(world, x + 4 + w, y + 9 - h, z + 11, cell.feature == 11 && random.nextInt(3) == 0 ? 20 : 2, cell);
                                    GenPassage.placeBlock(world, x + 4 + w, y + 9 - h, z + 12, 8, cell);
                                    GenPassage.placeBlock(world, x + 4 + w, y + 9 - h, z + 13, 1, cell);
                                    if (h == 7) {
                                        GenPassage.placeBlock(world, x + 4 + w, y + 1, z + 12, 1, cell);
                                        GenPassage.placeBlock(world, x + 4 + w, y + 9, z + 12, 1, cell);
                                    }
                                    if (w != 7) continue;
                                    GenPassage.placeBlock(world, x + 4, y + 9 - h, z + 12, 1, cell);
                                    GenPassage.placeBlock(world, x + 12, y + 9 - h, z + 12, 1, cell);
                                }
                            }
                            for (w = 2; w < 7; ++w) {
                                GenPassage.placeBlock(world, x + 4 + w, y + 3, z + 10, 4, ForgeDirection.EAST, cell);
                                GenPassage.placeBlock(world, x + 4 + w, y + 7, z + 10, 6, ForgeDirection.EAST, cell);
                            }
                        }
                        if (!cell.east) break block52;
                        for (w = 2 + mod; w < 9 - mod; ++w) {
                            for (h = 2 + mod; h < 9 - mod; ++h) {
                                GenPassage.placeBlock(world, x + 11, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], ForgeDirection.EAST, cell);
                            }
                        }
                        if (mod != 0) break block53;
                        if (cell.north) {
                            GenPassage.placeBlock(world, x + 10, y + 3, z + 6, 4, ForgeDirection.NORTH, cell);
                            GenPassage.placeBlock(world, x + 10, y + 7, z + 6, 6, ForgeDirection.NORTH, cell);
                        }
                        if (!cell.south) break block53;
                        GenPassage.placeBlock(world, x + 10, y + 3, z + 10, 4, ForgeDirection.NORTH, cell);
                        GenPassage.placeBlock(world, x + 10, y + 7, z + 10, 6, ForgeDirection.NORTH, cell);
                        break block53;
                    }
                    for (w = 1; w < 8; ++w) {
                        for (h = 1; h < 8; ++h) {
                            GenPassage.placeBlock(world, x + 11, y + 9 - h, z + 4 + w, cell.feature == 11 && random.nextInt(3) == 0 ? 20 : 2, cell);
                            GenPassage.placeBlock(world, x + 12, y + 9 - h, z + 4 + w, 8, cell);
                            GenPassage.placeBlock(world, x + 13, y + 9 - h, z + 4 + w, 1, cell);
                            if (h == 7) {
                                GenPassage.placeBlock(world, x + 12, y + 1, z + 4 + w, 1, cell);
                                GenPassage.placeBlock(world, x + 12, y + 9, z + 4 + w, 1, cell);
                            }
                            if (w != 7) continue;
                            GenPassage.placeBlock(world, x + 12, y + 9 - h, z + 4, 1, cell);
                            GenPassage.placeBlock(world, x + 12, y + 9 - h, z + 12, 1, cell);
                        }
                    }
                    for (w = 2; w < 7; ++w) {
                        GenPassage.placeBlock(world, x + 10, y + 3, z + 4 + w, 4, ForgeDirection.NORTH, cell);
                        GenPassage.placeBlock(world, x + 10, y + 7, z + 4 + w, 6, ForgeDirection.NORTH, cell);
                    }
                }
                if (!cell.west) break block54;
                for (w = 2 + mod; w < 9 - mod; ++w) {
                    for (h = 2 + mod; h < 9 - mod; ++h) {
                        GenPassage.placeBlock(world, x + 5, y + 10 - h, z + 3 + w, PAT_CONNECT[h][w], ForgeDirection.WEST, cell);
                    }
                }
                if (mod != 0) break block55;
                if (cell.north) {
                    GenPassage.placeBlock(world, x + 6, y + 3, z + 6, 3, ForgeDirection.NORTH, cell);
                    GenPassage.placeBlock(world, x + 6, y + 7, z + 6, 5, ForgeDirection.NORTH, cell);
                }
                if (!cell.south) break block55;
                GenPassage.placeBlock(world, x + 6, y + 3, z + 10, 3, ForgeDirection.NORTH, cell);
                GenPassage.placeBlock(world, x + 6, y + 7, z + 10, 5, ForgeDirection.NORTH, cell);
                break block55;
            }
            for (w = 1; w < 8; ++w) {
                for (h = 1; h < 8; ++h) {
                    GenPassage.placeBlock(world, x + 5, y + 9 - h, z + 4 + w, cell.feature == 11 && random.nextInt(3) == 0 ? 20 : 2, cell);
                    GenPassage.placeBlock(world, x + 4, y + 9 - h, z + 4 + w, 8, cell);
                    GenPassage.placeBlock(world, x + 3, y + 9 - h, z + 4 + w, 1, cell);
                    if (h == 7) {
                        GenPassage.placeBlock(world, x + 4, y + 1, z + 4 + w, 1, cell);
                        GenPassage.placeBlock(world, x + 4, y + 9, z + 4 + w, 1, cell);
                    }
                    if (w != 7) continue;
                    GenPassage.placeBlock(world, x + 4, y + 9 - h, z + 4, 1, cell);
                    GenPassage.placeBlock(world, x + 4, y + 9 - h, z + 12, 1, cell);
                }
            }
            for (w = 2; w < 7; ++w) {
                GenPassage.placeBlock(world, x + 6, y + 3, z + 4 + w, 3, ForgeDirection.NORTH, cell);
                GenPassage.placeBlock(world, x + 6, y + 7, z + 4 + w, 5, ForgeDirection.NORTH, cell);
            }
        }
        if (mod == 1) {
            GenPassage.placeBlock(world, x + 5, y + 3, z + 5, 3, ForgeDirection.EAST, cell);
            GenPassage.placeBlock(world, x + 5, y + 7, z + 5, 5, ForgeDirection.EAST, cell);
            GenPassage.placeBlock(world, x + 5, y + 3, z + 6, 3, ForgeDirection.NORTH, cell);
            GenPassage.placeBlock(world, x + 5, y + 7, z + 6, 5, ForgeDirection.NORTH, cell);
            GenPassage.placeBlock(world, x + 11, y + 3, z + 5, 3, ForgeDirection.EAST, cell);
            GenPassage.placeBlock(world, x + 11, y + 7, z + 5, 5, ForgeDirection.EAST, cell);
            GenPassage.placeBlock(world, x + 11, y + 3, z + 6, 4, ForgeDirection.NORTH, cell);
            GenPassage.placeBlock(world, x + 11, y + 7, z + 6, 6, ForgeDirection.NORTH, cell);
            GenPassage.placeBlock(world, x + 5, y + 3, z + 11, 3, ForgeDirection.NORTH, cell);
            GenPassage.placeBlock(world, x + 5, y + 7, z + 11, 5, ForgeDirection.NORTH, cell);
            GenPassage.placeBlock(world, x + 6, y + 3, z + 11, 4, ForgeDirection.EAST, cell);
            GenPassage.placeBlock(world, x + 6, y + 7, z + 11, 6, ForgeDirection.EAST, cell);
            GenPassage.placeBlock(world, x + 11, y + 3, z + 11, 4, ForgeDirection.NORTH, cell);
            GenPassage.placeBlock(world, x + 11, y + 7, z + 11, 6, ForgeDirection.NORTH, cell);
            GenPassage.placeBlock(world, x + 10, y + 3, z + 11, 4, ForgeDirection.EAST, cell);
            GenPassage.placeBlock(world, x + 10, y + 7, z + 11, 6, ForgeDirection.EAST, cell);
        }
        if (cell.feature == 12) {
            for (w = -4; w <= 4; ++w) {
                for (h = -4; h < 5; ++h) {
                    for (j = -4; j <= 4; ++j) {
                        if (!world.func_147437_c(x + 8 + w, y + 4 + h, z + 8 + j) && world.func_147439_a(x + 8 + w, y + 4 + h, z + 8 + j) != ConfigBlocks.blockCosmeticSolid && world.func_147439_a(x + 8 + w, y + 4 + h, z + 8 + j) != ConfigBlocks.blockStairsEldritch || !random.nextBoolean()) continue;
                        GenPassage.placeBlock(world, x + 8 + w, y + 4 + h, z + 8 + j, 21, cell);
                    }
                }
            }
        }
        if (cell.feature == 13) {
            for (w = -4; w <= 4; ++w) {
                for (h = -3; h <= 3; ++h) {
                    for (j = -4; j <= 4; ++j) {
                        if (!world.func_147437_c(x + 8 + w, y + 4 + h, z + 8 + j) || !BlockUtils.isAdjacentToSolidBlock(world, x + 8 + w, y + 4 + h, z + 8 + j)) continue;
                        if (random.nextInt(3) != 0) {
                            world.func_147465_d(x + 8 + w, y + 4 + h, z + 8 + j, ConfigBlocks.blockTaintFibres, random.nextInt(4) == 0 ? 1 : 0, 3);
                        }
                        Utils.setBiomeAt(world, x + 8 + w, z + 8 + j, ThaumcraftWorldGenerator.biomeTaint);
                    }
                }
            }
        }
        if (cell.feature == 14) {
            for (w = -3; w <= 3; ++w) {
                for (h = -3; h <= 3; ++h) {
                    for (j = -3; j <= 3; ++j) {
                        if (world.func_147437_c(x + 8 + w, y + 4 + h, z + 8 + j) && random.nextFloat() < 0.35f) {
                            world.func_147449_b(x + 8 + w, y + 4 + h, z + 8 + j, Blocks.field_150321_G);
                        }
                        world.func_147449_b(x + 8, y + 4, z + 8, Blocks.field_150474_ac);
                        TileEntityMobSpawner var12 = (TileEntityMobSpawner)world.func_147438_o(x + 8, y + 4, z + 8);
                        if (var12 == null) continue;
                        var12.func_145881_a().func_98272_a("Thaumcraft.MindSpider");
                    }
                }
            }
        }
    }
}

