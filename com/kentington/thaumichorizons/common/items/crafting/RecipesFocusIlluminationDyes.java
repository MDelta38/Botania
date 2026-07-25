/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.items.crafting;

import com.kentington.thaumichorizons.common.items.ItemFocusIllumination;
import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;

public class RecipesFocusIlluminationDyes
implements IRecipe {
    public boolean func_77569_a(InventoryCrafting par1InventoryCrafting, World p_77569_2_) {
        ItemStack itemstack = null;
        ArrayList<ItemStack> arraylist = new ArrayList<ItemStack>();
        for (int i = 0; i < par1InventoryCrafting.func_70302_i_(); ++i) {
            ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(i);
            if (itemstack1 == null) continue;
            if (itemstack1.func_77973_b() instanceof ItemFocusIllumination) {
                ItemFocusIllumination itemarmor = (ItemFocusIllumination)itemstack1.func_77973_b();
                if (itemstack != null) {
                    return false;
                }
                itemstack = itemstack1;
                continue;
            }
            if (itemstack1.func_77973_b() != Items.field_151100_aR) {
                return false;
            }
            arraylist.add(itemstack1);
        }
        return itemstack != null && !arraylist.isEmpty();
    }

    public ItemStack func_77572_b(InventoryCrafting par1InventoryCrafting) {
        ItemStack itemstack = null;
        ItemFocusIllumination itemarmor = null;
        int color = 0;
        for (int k = 0; k < par1InventoryCrafting.func_70302_i_(); ++k) {
            ItemStack itemstack1 = par1InventoryCrafting.func_70301_a(k);
            if (itemstack1 == null) continue;
            if (itemstack1.func_77973_b() instanceof ItemFocusIllumination) {
                itemarmor = (ItemFocusIllumination)itemstack1.func_77973_b();
                if (itemstack != null) {
                    return null;
                }
                itemstack = itemstack1.func_77946_l();
                itemstack.field_77994_a = 1;
                continue;
            }
            if (itemstack1.func_77973_b() != Items.field_151100_aR) {
                return null;
            }
            color = itemstack1.func_77960_j();
        }
        if (itemarmor == null) {
            return null;
        }
        itemstack.func_77964_b(color);
        return itemstack;
    }

    public int func_77570_a() {
        return 10;
    }

    public ItemStack func_77571_b() {
        return null;
    }
}

