/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.world.dim;

import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.GenCommon;

public class GenPortal
extends GenCommon {
    static void generatePortal(World world, Random random, int cx, int cz, int y, Cell cell) {
        int c;
        int b;
        int a;
        int x = cx * 16;
        int z = cz * 16;
        for (a = 1; a <= 15; ++a) {
            for (b = 1; b <= 15; ++b) {
                for (c = 0; c < 13; ++c) {
                    if (a != 1 && a != 15 && b != 1 && b != 15) continue;
                    GenPortal.placeBlock(world, x + a, y + c, z + b, 1, cell);
                }
            }
        }
        for (a = 2; a <= 14; ++a) {
            for (b = 2; b <= 14; ++b) {
                for (c = 1; c < 12; ++c) {
                    if (a != 2 && a != 14 && b != 2 && b != 14 || a == 2 && b > 3 && b < 12 && cell.west && c < 10 || a == 14 && b > 3 && b < 12 && cell.east && c < 10 || b == 2 && a > 3 && a < 12 && cell.north && c < 10 || b == 14 && a > 3 && a < 12 && cell.south && c < 10) continue;
                    GenPortal.placeBlock(world, x + a, y + c, z + b, 8, cell);
                }
            }
        }
        for (a = 3; a <= 13; ++a) {
            for (b = 3; b <= 13; ++b) {
                for (c = 2; c < 11; ++c) {
                    if (a != 3 && a != 13 && b != 3 && b != 13 || a <= 4 && b <= 4 || a <= 4 && b >= 12 || a >= 12 && b <= 4 || a >= 12 && b >= 12) continue;
                    GenPortal.placeBlock(world, x + a, y + c, z + b, 2, cell);
                }
            }
        }
        for (a = 2; a <= 14; ++a) {
            for (b = 2; b <= 14; ++b) {
                int g;
                int q;
                GenPortal.placeBlock(world, x + a, y - 1, z + b, 1, cell);
                GenPortal.placeBlock(world, x + a, y, z + b, 8, cell);
                GenPortal.placeBlock(world, x + a, y + 1, z + b, 19, cell);
                GenPortal.placeBlock(world, x + a, y + 13, z + b, 1, cell);
                GenPortal.placeBlock(world, x + a, y + 12, z + b, 8, cell);
                GenPortal.placeBlock(world, x + a, y + 11, z + b, 2, cell);
                if (a > 1 && a < 15 && b > 1 && b < 15) {
                    q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
                    for (g = 0; g < q - 1; ++g) {
                        GenPortal.placeBlock(world, x + a, y + 1 + g, z + b, 19, cell);
                    }
                }
                if (a <= 3 || a >= 13 || b <= 3 || b >= 13) continue;
                q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
                for (g = 0; g < q; ++g) {
                    GenPortal.placeBlock(world, x + a, y + 11 - g, z + b, 19, cell);
                }
            }
        }
        for (int g = 0; g < 5; ++g) {
            GenPortal.placeBlock(world, x + 6 + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
            GenPortal.placeBlock(world, x + 6 + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
            GenPortal.placeBlock(world, x + 12, y + 2, z + 6 + g, 10, ForgeDirection.EAST, cell);
            GenPortal.placeBlock(world, x + 4, y + 2, z + 6 + g, 10, ForgeDirection.WEST, cell);
        }
        GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
        for (a = 3; a <= 13; ++a) {
            for (b = 3; b <= 13; ++b) {
                for (c = 1; c < 12; ++c) {
                    if (!(a <= 4 && b <= 4 || a <= 4 && b >= 12 || a >= 12 && b <= 4) && (a < 12 || b < 12)) continue;
                    GenPortal.placeBlock(world, x + a, y + c, z + b, 9, cell);
                    world.func_147444_c(x + a, y + c, z + b, Blocks.field_150350_a);
                }
            }
        }
        GenPortal.placeBlock(world, x + 5, y + 3, z + 5, 10, ForgeDirection.NORTH, cell);
        GenPortal.placeBlock(world, x + 4, y + 3, z + 5, 10, ForgeDirection.NORTH, cell);
        GenPortal.placeBlock(world, x + 5, y + 3, z + 4, 10, ForgeDirection.WEST, cell);
        GenPortal.placeBlock(world, x + 5, y + 8, z + 5, 11, ForgeDirection.NORTH, cell);
        GenPortal.placeBlock(world, x + 4, y + 8, z + 5, 11, ForgeDirection.NORTH, cell);
        GenPortal.placeBlock(world, x + 5, y + 8, z + 4, 11, ForgeDirection.WEST, cell);
        GenPortal.placeBlock(world, x + 12, y + 3, z + 5, 10, ForgeDirection.NORTH, cell);
        GenPortal.placeBlock(world, x + 11, y + 3, z + 5, 10, ForgeDirection.NORTH, cell);
        GenPortal.placeBlock(world, x + 11, y + 3, z + 4, 10, ForgeDirection.EAST, cell);
        GenPortal.placeBlock(world, x + 12, y + 8, z + 5, 11, ForgeDirection.NORTH, cell);
        GenPortal.placeBlock(world, x + 11, y + 8, z + 5, 11, ForgeDirection.NORTH, cell);
        GenPortal.placeBlock(world, x + 11, y + 8, z + 4, 11, ForgeDirection.EAST, cell);
        GenPortal.placeBlock(world, x + 5, y + 3, z + 11, 10, ForgeDirection.SOUTH, cell);
        GenPortal.placeBlock(world, x + 4, y + 3, z + 11, 10, ForgeDirection.SOUTH, cell);
        GenPortal.placeBlock(world, x + 5, y + 3, z + 12, 10, ForgeDirection.WEST, cell);
        GenPortal.placeBlock(world, x + 5, y + 8, z + 11, 11, ForgeDirection.SOUTH, cell);
        GenPortal.placeBlock(world, x + 4, y + 8, z + 11, 11, ForgeDirection.SOUTH, cell);
        GenPortal.placeBlock(world, x + 5, y + 8, z + 12, 11, ForgeDirection.WEST, cell);
        GenPortal.placeBlock(world, x + 12, y + 3, z + 11, 10, ForgeDirection.SOUTH, cell);
        GenPortal.placeBlock(world, x + 11, y + 3, z + 11, 10, ForgeDirection.SOUTH, cell);
        GenPortal.placeBlock(world, x + 11, y + 3, z + 12, 10, ForgeDirection.EAST, cell);
        GenPortal.placeBlock(world, x + 12, y + 8, z + 11, 11, ForgeDirection.SOUTH, cell);
        GenPortal.placeBlock(world, x + 11, y + 8, z + 11, 11, ForgeDirection.SOUTH, cell);
        GenPortal.placeBlock(world, x + 11, y + 8, z + 12, 11, ForgeDirection.EAST, cell);
        world.func_147465_d(x + 8, y + 2, z + 8, ConfigBlocks.blockEldritch, 3, 3);
        world.func_147449_b(x + 8, y + 3, z + 8, ConfigBlocks.blockEldritchPortal);
        GenPortal.genObelisk(world, x + 8, y + 4, z + 8);
    }
}

