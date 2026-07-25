/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.crafting;

import com.emoniph.witchery.Witchery;
import java.util.ArrayList;
import java.util.Arrays;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeShapelessPoppet
implements IRecipe {
    final ItemStack prototype;
    final ItemStack[] pattern;

    public RecipeShapelessPoppet(ItemStack resultPoppet, ItemStack ... pattern) {
        this.prototype = resultPoppet;
        this.pattern = pattern;
    }

    public ItemStack func_77571_b() {
        return null;
    }

    public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World par2World) {
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>(Arrays.asList(this.pattern));
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                ItemStack itemstack = par1InventoryCrafting.func_70463_b(j, i);
                if (itemstack == null) continue;
                boolean flag = false;
                for (ItemStack itemstack1 : arraylist) {
                    if (itemstack.func_77973_b() != itemstack1.func_77973_b() || itemstack1.func_77960_j() != Short.MAX_VALUE && itemstack.func_77960_j() != itemstack1.func_77960_j()) continue;
                    flag = true;
                    arraylist.remove(itemstack1);
                    break;
                }
                if (flag) continue;
                return false;
            }
        }
        return arraylist.isEmpty();
    }

    public ItemStack func_77572_b(InventoryCrafting inv) {
        ItemStack newPoppet = this.prototype.func_77946_l();
        int index = 1;
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stack = inv.func_70301_a(i);
            if (stack == null || stack.func_77973_b() != Witchery.Items.TAGLOCK_KIT) continue;
            Witchery.Items.TAGLOCK_KIT.addTagLockToPoppet(stack, newPoppet, index++);
        }
        ItemStack recipePoppet = this.findRecipeItemStack(inv, Witchery.Items.POPPET);
        if (recipePoppet != null) {
            Witchery.Items.POPPET.addDamageToPoppet(recipePoppet, newPoppet);
        }
        return newPoppet;
    }

    private ItemStack findRecipeItemStack(InventoryCrafting inv, Item itemToFind) {
        for (int i = 0; i < inv.func_70302_i_(); ++i) {
            ItemStack stack = inv.func_70301_a(i);
            if (stack == null || stack.func_77973_b() != itemToFind) continue;
            return stack;
        }
        return null;
    }

    public int func_77570_a() {
        return this.pattern.length;
    }
}

