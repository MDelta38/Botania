/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package vazkii.botania.common.crafting.recipe;

import net.minecraft.block.Block;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class SpecialFloatingFlowerRecipe
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting var1, World var2) {
        boolean foundFloatingFlower = false;
        boolean foundSpecialFlower = false;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null) continue;
            if (stack.func_77973_b() == Item.func_150898_a((Block)ModBlocks.floatingFlower)) {
                foundFloatingFlower = true;
                continue;
            }
            if (stack.func_77973_b() == Item.func_150898_a((Block)ModBlocks.specialFlower)) {
                foundSpecialFlower = true;
                continue;
            }
            return false;
        }
        return foundFloatingFlower && foundSpecialFlower;
    }

    public ItemStack func_77572_b(InventoryCrafting var1) {
        ItemStack specialFlower = null;
        for (int i = 0; i < var1.func_70302_i_(); ++i) {
            ItemStack stack = var1.func_70301_a(i);
            if (stack == null || stack.func_77973_b() != Item.func_150898_a((Block)ModBlocks.specialFlower)) continue;
            specialFlower = stack;
        }
        if (specialFlower == null) {
            return null;
        }
        return ItemBlockSpecialFlower.ofType(new ItemStack(ModBlocks.floatingSpecialFlower), ItemBlockSpecialFlower.getType(specialFlower));
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

