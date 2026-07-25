/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.world.dim;

import java.util.Random;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.GenCommon;

public class Gen2x2
extends GenCommon {
    static void generateUpperLeft(World world, Random random, int cx, int cz, int y, Cell cell) {
        int g;
        int c;
        int b;
        int a;
        int x = cx * 16;
        int z = cz * 16;
        for (a = 1; a <= 15; ++a) {
            for (b = 1; b <= 15; ++b) {
                for (c = 0; c < 13; ++c) {
                    if (a != 1 && b != 1) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 1, cell);
                }
            }
        }
        for (a = 2; a <= 15; ++a) {
            for (b = 2; b <= 15; ++b) {
                for (c = 1; c < 12; ++c) {
                    if (a != 2 && b != 2 || a == 2 && b > 4 && b < 12 && cell.west && c < 10 || b == 2 && a > 4 && a < 12 && cell.north && c < 10) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 8, cell);
                }
            }
        }
        for (a = 3; a <= 15; ++a) {
            for (b = 3; b <= 15; ++b) {
                for (c = 2; c < 11; ++c) {
                    if (a != 3 && b != 3) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 18, cell);
                }
            }
        }
        for (a = 2; a <= 15; ++a) {
            for (b = 2; b <= 15; ++b) {
                Gen2x2.placeBlock(world, x + a, y - 1, z + b, 1, cell);
                Gen2x2.placeBlock(world, x + a, y, z + b, 8, cell);
                Gen2x2.placeBlock(world, x + a, y + 1, z + b, 19, cell);
                Gen2x2.placeBlock(world, x + a, y + 13, z + b, 1, cell);
                Gen2x2.placeBlock(world, x + a, y + 12, z + b, 8, cell);
                Gen2x2.placeBlock(world, x + a, y + 11, z + b, 2, cell);
            }
        }
        for (g = 4; g <= 15; ++g) {
            Gen2x2.placeBlock(world, x + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
            Gen2x2.placeBlock(world, x + g, y + 10, z + 4, 11, ForgeDirection.NORTH, cell);
        }
        for (g = 4; g <= 15; ++g) {
            Gen2x2.placeBlock(world, x + 4, y + 2, z + g, 10, ForgeDirection.WEST, cell);
            Gen2x2.placeBlock(world, x + 4, y + 10, z + g, 11, ForgeDirection.WEST, cell);
        }
        GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
    }

    static void generateUpperRight(World world, Random random, int cx, int cz, int y, Cell cell) {
        int g;
        int c;
        int b;
        int a;
        int x = cx * 16;
        int z = cz * 16;
        for (a = 0; a <= 15; ++a) {
            for (b = 1; b <= 15; ++b) {
                for (c = 0; c < 13; ++c) {
                    if (a != 15 && b != 1) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 1, cell);
                }
            }
        }
        for (a = 0; a <= 14; ++a) {
            for (b = 2; b <= 15; ++b) {
                for (c = 1; c < 12; ++c) {
                    if (a != 14 && b != 2 || a == 14 && b > 4 && b < 12 && cell.east && c < 10 || b == 2 && a > 4 && a < 12 && cell.north && c < 10) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 8, cell);
                }
            }
        }
        for (a = 0; a <= 13; ++a) {
            for (b = 3; b <= 15; ++b) {
                for (c = 2; c < 11; ++c) {
                    if (a != 13 && b != 3) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 18, cell);
                }
            }
        }
        for (a = 0; a <= 14; ++a) {
            for (b = 2; b <= 15; ++b) {
                Gen2x2.placeBlock(world, x + a, y - 1, z + b, 1, cell);
                Gen2x2.placeBlock(world, x + a, y, z + b, 8, cell);
                Gen2x2.placeBlock(world, x + a, y + 1, z + b, 19, cell);
                Gen2x2.placeBlock(world, x + a, y + 13, z + b, 1, cell);
                Gen2x2.placeBlock(world, x + a, y + 12, z + b, 8, cell);
                Gen2x2.placeBlock(world, x + a, y + 11, z + b, 2, cell);
            }
        }
        for (g = 0; g <= 11; ++g) {
            Gen2x2.placeBlock(world, x + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
            Gen2x2.placeBlock(world, x + g, y + 10, z + 4, 11, ForgeDirection.NORTH, cell);
        }
        for (g = 4; g <= 15; ++g) {
            Gen2x2.placeBlock(world, x + 12, y + 2, z + g, 10, ForgeDirection.EAST, cell);
            Gen2x2.placeBlock(world, x + 12, y + 10, z + g, 11, ForgeDirection.EAST, cell);
        }
        GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
    }

    static void generateLowerLeft(World world, Random random, int cx, int cz, int y, Cell cell) {
        int g;
        int c;
        int b;
        int a;
        int x = cx * 16;
        int z = cz * 16;
        for (a = 1; a <= 15; ++a) {
            for (b = 0; b <= 15; ++b) {
                for (c = 0; c < 13; ++c) {
                    if (a != 1 && b != 15) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 1, cell);
                }
            }
        }
        for (a = 2; a <= 15; ++a) {
            for (b = 0; b <= 14; ++b) {
                for (c = 1; c < 12; ++c) {
                    if (a != 2 && b != 14 || a == 2 && b > 4 && b < 12 && cell.west && c < 10 || b == 14 && a > 4 && a < 12 && cell.south && c < 10) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 8, cell);
                }
            }
        }
        for (a = 3; a <= 15; ++a) {
            for (b = 0; b <= 13; ++b) {
                for (c = 2; c < 11; ++c) {
                    if (a != 3 && b != 13) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 18, cell);
                }
            }
        }
        for (a = 2; a <= 15; ++a) {
            for (b = 0; b <= 14; ++b) {
                Gen2x2.placeBlock(world, x + a, y - 1, z + b, 1, cell);
                Gen2x2.placeBlock(world, x + a, y, z + b, 8, cell);
                Gen2x2.placeBlock(world, x + a, y + 1, z + b, 19, cell);
                Gen2x2.placeBlock(world, x + a, y + 13, z + b, 1, cell);
                Gen2x2.placeBlock(world, x + a, y + 12, z + b, 8, cell);
                Gen2x2.placeBlock(world, x + a, y + 11, z + b, 2, cell);
            }
        }
        for (g = 4; g <= 15; ++g) {
            Gen2x2.placeBlock(world, x + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
            Gen2x2.placeBlock(world, x + g, y + 10, z + 12, 11, ForgeDirection.SOUTH, cell);
        }
        for (g = 0; g <= 11; ++g) {
            Gen2x2.placeBlock(world, x + 4, y + 2, z + g, 10, ForgeDirection.WEST, cell);
            Gen2x2.placeBlock(world, x + 4, y + 10, z + g, 11, ForgeDirection.WEST, cell);
        }
        GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
    }

    static void generateLowerRight(World world, Random random, int cx, int cz, int y, Cell cell) {
        int g;
        int c;
        int b;
        int a;
        int x = cx * 16;
        int z = cz * 16;
        for (a = 0; a <= 15; ++a) {
            for (b = 0; b <= 15; ++b) {
                for (c = 0; c < 13; ++c) {
                    if (a != 15 && b != 15) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 1, cell);
                }
            }
        }
        for (a = 0; a <= 14; ++a) {
            for (b = 0; b <= 14; ++b) {
                for (c = 1; c < 12; ++c) {
                    if (a != 14 && b != 14 || a == 14 && b > 4 && b < 12 && cell.east && c < 10 || b == 14 && a > 4 && a < 12 && cell.south && c < 10) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 8, cell);
                }
            }
        }
        for (a = 0; a <= 13; ++a) {
            for (b = 0; b <= 13; ++b) {
                for (c = 2; c < 11; ++c) {
                    if (a != 13 && b != 13) continue;
                    Gen2x2.placeBlock(world, x + a, y + c, z + b, 18, cell);
                }
            }
        }
        for (a = 0; a <= 14; ++a) {
            for (b = 0; b <= 14; ++b) {
                Gen2x2.placeBlock(world, x + a, y - 1, z + b, 1, cell);
                Gen2x2.placeBlock(world, x + a, y, z + b, 8, cell);
                Gen2x2.placeBlock(world, x + a, y + 1, z + b, 19, cell);
                Gen2x2.placeBlock(world, x + a, y + 13, z + b, 1, cell);
                Gen2x2.placeBlock(world, x + a, y + 12, z + b, 8, cell);
                Gen2x2.placeBlock(world, x + a, y + 11, z + b, 2, cell);
            }
        }
        for (g = 0; g <= 11; ++g) {
            Gen2x2.placeBlock(world, x + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
            Gen2x2.placeBlock(world, x + g, y + 10, z + 12, 11, ForgeDirection.SOUTH, cell);
        }
        for (g = 0; g <= 12; ++g) {
            Gen2x2.placeBlock(world, x + 12, y + 2, z + g, 10, ForgeDirection.EAST, cell);
            Gen2x2.placeBlock(world, x + 12, y + 10, z + g, 11, ForgeDirection.EAST, cell);
        }
        GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
    }
}

