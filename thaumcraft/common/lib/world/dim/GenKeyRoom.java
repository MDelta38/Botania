/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.world.dim;

import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.entities.EntityPermanentItem;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.lib.utils.EntityUtils;
import thaumcraft.common.lib.world.dim.Cell;
import thaumcraft.common.lib.world.dim.GenCommon;

public class GenKeyRoom
extends GenCommon {
    static void generateRoom(World world, Random random, int cx, int cz, int y, Cell cell) {
        int c;
        int b;
        int a;
        int x = cx * 16;
        int z = cz * 16;
        for (a = 1; a <= 15; ++a) {
            for (b = 1; b <= 15; ++b) {
                for (c = 0; c < 13; ++c) {
                    if (a != 1 && a != 15 && b != 1 && b != 15) continue;
                    GenKeyRoom.placeBlock(world, x + a, y + c, z + b, 1, cell);
                }
            }
        }
        for (a = 2; a <= 14; ++a) {
            for (b = 2; b <= 14; ++b) {
                for (c = 1; c < 12; ++c) {
                    if (a != 2 && a != 14 && b != 2 && b != 14 || a == 2 && b > 3 && b < 12 && cell.west && c < 10 || a == 14 && b > 3 && b < 12 && cell.east && c < 10 || b == 2 && a > 3 && a < 12 && cell.north && c < 10 || b == 14 && a > 3 && a < 12 && cell.south && c < 10) continue;
                    GenKeyRoom.placeBlock(world, x + a, y + c, z + b, 8, cell);
                }
            }
        }
        for (a = 3; a <= 13; ++a) {
            for (b = 3; b <= 13; ++b) {
                for (c = 2; c < 11; ++c) {
                    if (a != 3 && a != 13 && b != 3 && b != 13) continue;
                    if (c > 3 && c < 9 && (a == 8 || b == 8) || c > 4 && c < 8 && (a == 7 || b == 7 || a == 9 || b == 9)) {
                        if ((a == 8 || b == 8) && c == 6) continue;
                        GenKeyRoom.placeBlock(world, x + a, y + c, z + b, 19, cell);
                        continue;
                    }
                    GenKeyRoom.placeBlock(world, x + a, y + c, z + b, 18, cell);
                }
            }
        }
        for (a = 2; a <= 14; ++a) {
            for (b = 2; b <= 14; ++b) {
                int g;
                int q;
                GenKeyRoom.placeBlock(world, x + a, y - 1, z + b, 1, cell);
                GenKeyRoom.placeBlock(world, x + a, y, z + b, 8, cell);
                GenKeyRoom.placeBlock(world, x + a, y + 1, z + b, 2, cell);
                GenKeyRoom.placeBlock(world, x + a, y + 13, z + b, 1, cell);
                GenKeyRoom.placeBlock(world, x + a, y + 12, z + b, 8, cell);
                GenKeyRoom.placeBlock(world, x + a, y + 11, z + b, 2, cell);
                if (a > 1 && a < 15 && b > 1 && b < 15) {
                    q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
                    for (g = 0; g < q - 1; ++g) {
                        GenKeyRoom.placeBlock(world, x + a, y + 1 + g, z + b, 2, cell);
                    }
                }
                if (a <= 3 || a >= 13 || b <= 3 || b >= 13) continue;
                q = Math.min(Math.abs(8 - a), Math.abs(8 - b));
                for (g = 0; g < q; ++g) {
                    GenKeyRoom.placeBlock(world, x + a, y + 11 - g, z + b, 2, cell);
                }
            }
        }
        for (int g = 0; g < 5; ++g) {
            GenKeyRoom.placeBlock(world, x + 6 + g, y + 2, z + 4, 10, ForgeDirection.NORTH, cell);
            GenKeyRoom.placeBlock(world, x + 6 + g, y + 2, z + 12, 10, ForgeDirection.SOUTH, cell);
            GenKeyRoom.placeBlock(world, x + 12, y + 2, z + 6 + g, 10, ForgeDirection.EAST, cell);
            GenKeyRoom.placeBlock(world, x + 4, y + 2, z + 6 + g, 10, ForgeDirection.WEST, cell);
        }
        GenCommon.generateConnections(world, random, cx, cz, y, cell, 3, true);
        world.func_147465_d(x + 8, y + 2, z + 8, ConfigBlocks.blockEldritch, 3, 3);
        EntityPermanentItem entityitem = new EntityPermanentItem(world, (double)x + 8.5, (double)y + 3.5, (double)z + 8.5, new ItemStack(ConfigItems.itemEldritchObject, 1, 2));
        entityitem.field_70181_x = 0.0;
        entityitem.field_70159_w = 0.0;
        entityitem.field_70179_y = 0.0;
        world.func_72838_d((Entity)entityitem);
        int zz = 2 + (world.field_73013_u == EnumDifficulty.HARD ? 2 : (world.field_73013_u == EnumDifficulty.NORMAL ? 1 : 0));
        for (int qq = 0; qq < zz; ++qq) {
            EntityEldritchGuardian eg = new EntityEldritchGuardian(world);
            double i1 = (double)x + 8.5 + (double)(MathHelper.func_76136_a((Random)world.field_73012_v, (int)1, (int)3) * MathHelper.func_76136_a((Random)world.field_73012_v, (int)-1, (int)1));
            double j1 = y + 2;
            double k1 = (double)z + 8.5 + (double)(MathHelper.func_76136_a((Random)world.field_73012_v, (int)1, (int)3) * MathHelper.func_76136_a((Random)world.field_73012_v, (int)-1, (int)1));
            eg.func_70107_b(i1, j1, k1);
            eg.func_110161_a(null);
            eg.func_110171_b(x + 8, y + 2, z + 8, 16);
            if (qq == 0 && zz >= 4) {
                EntityUtils.makeChampion(eg, true);
            }
            world.func_72838_d((Entity)eg);
        }
    }
}

