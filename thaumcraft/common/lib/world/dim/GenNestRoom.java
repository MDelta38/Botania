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
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.GenCommon;
import thaumcraft.common.tiles.TileCrystal;

public class GenNestRoom
extends GenCommon {
    static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell) {
        int c;
        int b;
        int a;
        int x = cx * 16;
        int z = cz * 16;
        for (a = 1; a <= 15; ++a) {
            for (b = 1; b <= 15; ++b) {
                for (c = 0; c < 11; ++c) {
                    if (a != 1 && a != 15 && b != 1 && b != 15) continue;
                    GenNestRoom.placeBlock(world, x + a, y + c, z + b, 1, cell);
                }
            }
        }
        for (a = 2; a <= 14; ++a) {
            for (b = 2; b <= 14; ++b) {
                for (c = 1; c < 10; ++c) {
                    if (a != 2 && a != 14 && b != 2 && b != 14 || a == 2 && b > 3 && b < 12 && cell.west && c < 10 || a == 14 && b > 3 && b < 12 && cell.east && c < 10 || b == 2 && a > 3 && a < 12 && cell.north && c < 10 || b == 14 && a > 3 && a < 12 && cell.south && c < 10) continue;
                    GenNestRoom.placeBlock(world, x + a, y + c, z + b, 8, cell);
                }
            }
        }
        for (a = 3; a <= 13; ++a) {
            for (b = 3; b <= 13; ++b) {
                for (c = 2; c < 9; ++c) {
                    if (a == 3 || a == 13 || b == 3 || b == 13) {
                        GenNestRoom.placeBlock(world, x + a, y + c, z + b, 21, cell);
                    }
                    if (!(a == 4 && !cell.west || a == 12 && !cell.east || b == 4 && !cell.north) && (b != 12 || cell.south) || !random.nextBoolean()) continue;
                    GenNestRoom.placeBlock(world, x + a, y + c, z + b, 21, cell);
                }
            }
        }
        for (a = 2; a <= 14; ++a) {
            for (b = 2; b <= 14; ++b) {
                GenNestRoom.placeBlock(world, x + a, y - 1, z + b, 1, cell);
                GenNestRoom.placeBlock(world, x + a, y, z + b, 8, cell);
                GenNestRoom.placeBlock(world, x + a, y + 1, z + b, 21, cell);
                GenNestRoom.placeBlock(world, x + a, y + 11, z + b, 1, cell);
                GenNestRoom.placeBlock(world, x + a, y + 10, z + b, 8, cell);
                GenNestRoom.placeBlock(world, x + a, y + 9, z + b, 21, cell);
                if (random.nextBoolean()) {
                    GenNestRoom.placeBlock(world, x + a, y + 8, z + b, 21, cell);
                    continue;
                }
                if (!random.nextBoolean() || !world.func_147437_c(x + a, y + 8, z + b)) continue;
                world.func_147465_d(x + a, y + 8, z + b, ConfigBlocks.blockCrystal, 7, 3);
                TileCrystal te = (TileCrystal)world.func_147438_o(x + a, y + 8, z + b);
                te.orientation = (short)ForgeDirection.DOWN.ordinal();
            }
        }
        GenNestRoom.placeBlock(world, x + 8, y + 2, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 8, y + 3, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 8, y + 4, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 7, y + 2, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 8, y + 2, z + 7, 21, cell);
        GenNestRoom.placeBlock(world, x + 9, y + 2, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 8, y + 2, z + 9, 21, cell);
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 7, y + 3, z + 8, 21, cell);
        }
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 8, y + 3, z + 7, 21, cell);
        }
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 9, y + 3, z + 8, 21, cell);
        }
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 8, y + 3, z + 9, 21, cell);
        }
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 8, y + 5, z + 8, 7, cell);
        }
        GenNestRoom.placeBlock(world, x + 8, y + 8, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 8, y + 7, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 8, y + 6, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 7, y + 8, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 8, y + 8, z + 7, 21, cell);
        GenNestRoom.placeBlock(world, x + 9, y + 8, z + 8, 21, cell);
        GenNestRoom.placeBlock(world, x + 8, y + 8, z + 9, 21, cell);
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 7, y + 7, z + 8, 21, cell);
        }
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 8, y + 7, z + 7, 21, cell);
        }
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 9, y + 7, z + 8, 21, cell);
        }
        if (random.nextBoolean()) {
            GenNestRoom.placeBlock(world, x + 8, y + 7, z + 9, 21, cell);
        }
        GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
        for (a = -5; a <= 5; ++a) {
            for (b = -5; b <= 5; ++b) {
                if (!(random.nextFloat() < 0.15f) || !world.func_147437_c(x + 8 + a, y + 2, z + 8 + b)) continue;
                float rr = random.nextFloat();
                int md = rr < 0.15f ? 2 : (rr < 0.4f ? 1 : 0);
                world.func_147465_d(x + 8 + a, y + 2, z + 8 + b, random.nextFloat() < 0.2f ? ConfigBlocks.blockLootCrate : ConfigBlocks.blockLootUrn, md, 3);
            }
        }
    }
}

