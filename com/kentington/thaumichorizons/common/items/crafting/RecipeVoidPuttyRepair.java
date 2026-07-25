/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items.crafting;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import java.util.ArrayList;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipeVoidPuttyRepair
implements IRecipe {
    public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting) {
        ItemStack itemstack = null;
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        for (int i = 0; i < par1InventoryCrafting.func_70302_i_(); ++i) {
            ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(i);
            if (itemstack1 == null) continue;
            if (itemstack1.func_77973_b().func_77645_m() && itemstack1.func_77973_b().isDamaged(itemstack1)) {
                if (itemstack != null) {
                    return null;
                }
                itemstack = itemstack1;
                continue;
            }
            if (itemstack1.func_77973_b() != ThaumicHorizons.itemVoidPutty) {
                return null;
            }
            arraylist.add(itemstack1);
        }
        ItemStack output = itemstack.func_77946_l();
        output.func_77964_b(0);
        return output;
    }

    public ItemStack func_77571_b() {
        return null;
    }

    public int func_77570_a() {
        return 10;
    }

    public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World arg1) {
        ItemStack itemstack = null;
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        for (int i = 0; i < par1InventoryCrafting.func_70302_i_(); ++i) {
            ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(i);
            if (itemstack1 == null) continue;
            if (itemstack1.func_77973_b().func_77645_m() && itemstack1.func_77973_b().isDamaged(itemstack1)) {
                if (itemstack != null) {
                    return false;
                }
                itemstack = itemstack1;
                continue;
            }
            if (itemstack1.func_77973_b() != ThaumicHorizons.itemVoidPutty) {
                return false;
            }
            arraylist.add(itemstack1);
        }
        return itemstack != null && !arraylist.isEmpty();
    }
}

