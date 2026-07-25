/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockCrops
 *  net.minecraft.block.BlockStem
 *  net.minecraft.block.IGrowable
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package thaumcraft.common.lib.utils;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockStem;
import net.minecraft.block.IGrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CropUtils {
    public static ArrayList<String> clickableCrops = new ArrayList();
    public static ArrayList<String> standardCrops = new ArrayList();
    public static ArrayList<String> stackedCrops = new ArrayList();
    public static ArrayList<String> lampBlacklist = new ArrayList();

    public static void addStandardCrop(ItemStack stack, int grownMeta) {
        if (Block.func_149634_a((Item)stack.func_77973_b()) == null) {
            return;
        }
        if (grownMeta == Short.MAX_VALUE) {
            for (int a = 0; a < 16; ++a) {
                standardCrops.add(Block.func_149634_a((Item)stack.func_77973_b()).func_149739_a() + a);
            }
        } else {
            standardCrops.add(Block.func_149634_a((Item)stack.func_77973_b()).func_149739_a() + grownMeta);
        }
        if (Block.func_149634_a((Item)stack.func_77973_b()) instanceof BlockCrops && grownMeta != 7) {
            standardCrops.add(Block.func_149634_a((Item)stack.func_77973_b()).func_149739_a() + "7");
        }
    }

    public static void addClickableCrop(ItemStack stack, int grownMeta) {
        if (Block.func_149634_a((Item)stack.func_77973_b()) == null) {
            return;
        }
        if (grownMeta == Short.MAX_VALUE) {
            for (int a = 0; a < 16; ++a) {
                clickableCrops.add(Block.func_149634_a((Item)stack.func_77973_b()).func_149739_a() + a);
            }
        } else {
            clickableCrops.add(Block.func_149634_a((Item)stack.func_77973_b()).func_149739_a() + grownMeta);
        }
        if (Block.func_149634_a((Item)stack.func_77973_b()) instanceof BlockCrops && grownMeta != 7) {
            clickableCrops.add(Block.func_149634_a((Item)stack.func_77973_b()).func_149739_a() + "7");
        }
    }

    public static void addStackedCrop(ItemStack stack, int grownMeta) {
        if (Block.func_149634_a((Item)stack.func_77973_b()) == null) {
            return;
        }
        CropUtils.addStackedCrop(Block.func_149634_a((Item)stack.func_77973_b()), grownMeta);
    }

    public static void addStackedCrop(Block block, int grownMeta) {
        if (grownMeta == Short.MAX_VALUE) {
            for (int a = 0; a < 16; ++a) {
                stackedCrops.add(block.func_149739_a() + a);
            }
        } else {
            stackedCrops.add(block.func_149739_a() + grownMeta);
        }
        if (block instanceof BlockCrops && grownMeta != 7) {
            stackedCrops.add(block.func_149739_a() + "7");
        }
    }

    public static boolean isGrownCrop(World world, int x, int y, int z) {
        if (world.func_147437_c(x, y, z)) {
            return false;
        }
        boolean found = false;
        Block bi = world.func_147439_a(x, y, z);
        for (int a = 0; a < 16; ++a) {
            if (!standardCrops.contains(bi.func_149739_a() + a) && !clickableCrops.contains(bi.func_149739_a() + a) && !stackedCrops.contains(bi.func_149739_a() + a)) continue;
            found = true;
            break;
        }
        Block biA = world.func_147439_a(x, y + 1, z);
        Block biB = world.func_147439_a(x, y - 1, z);
        int md = world.func_72805_g(x, y, z);
        return bi instanceof IGrowable && !((IGrowable)bi).func_149851_a(world, x, y, z, world.field_72995_K) && !(bi instanceof BlockStem) || bi instanceof BlockCrops && md == 7 && !found || bi == Blocks.field_150388_bm && md >= 3 || bi == Blocks.field_150375_by && (md & 0xC) >> 2 >= 2 || standardCrops.contains(bi.func_149739_a() + md) || clickableCrops.contains(bi.func_149739_a() + md) || stackedCrops.contains(bi.func_149739_a() + md) && biB == bi;
    }

    public static void blacklistLamp(ItemStack stack, int meta) {
        if (Block.func_149634_a((Item)stack.func_77973_b()) == null) {
            return;
        }
        if (meta == Short.MAX_VALUE) {
            for (int a = 0; a < 16; ++a) {
                lampBlacklist.add(Block.func_149634_a((Item)stack.func_77973_b()).func_149739_a() + a);
            }
        } else {
            lampBlacklist.add(Block.func_149634_a((Item)stack.func_77973_b()).func_149739_a() + meta);
        }
    }

    public static boolean doesLampGrow(World world, int x, int y, int z) {
        Block bi = world.func_147439_a(x, y, z);
        int md = world.func_72805_g(x, y, z);
        return !lampBlacklist.contains(bi.func_149739_a() + md);
    }
}

