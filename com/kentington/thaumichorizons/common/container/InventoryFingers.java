/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.items.wands.ItemWandCasting
 *  thaumcraft.common.tiles.TileMagicWorkbench
 */
package com.kentington.thaumichorizons.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.tiles.TileMagicWorkbench;

public class InventoryFingers
extends TileMagicWorkbench
implements IInventory {
    public int func_70302_i_() {
        return this.stackList.length;
    }

    public ItemStack func_70301_a(int par1) {
        return par1 >= this.func_70302_i_() ? null : this.stackList[par1];
    }

    public ItemStack getStackInRowAndColumn(int par1, int par2) {
        if (par1 >= 0 && par1 < 3) {
            int var3 = par1 + par2 * 3;
            return this.func_70301_a(var3);
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.stackList[par1] != null) {
            ItemStack var2 = this.stackList[par1];
            this.stackList[par1] = null;
            this.func_70296_d();
            return var2;
        }
        return null;
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.stackList[par1] != null) {
            if (this.stackList[par1].field_77994_a <= par2) {
                ItemStack var3 = this.stackList[par1];
                this.stackList[par1] = null;
                if (this.eventHandler != null) {
                    this.eventHandler.func_75130_a((IInventory)this);
                }
                this.func_70296_d();
                return var3;
            }
            ItemStack var3 = this.stackList[par1].func_77979_a(par2);
            if (this.stackList[par1].field_77994_a == 0) {
                this.stackList[par1] = null;
            }
            if (this.eventHandler != null) {
                this.eventHandler.func_75130_a((IInventory)this);
            }
            this.func_70296_d();
            return var3;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
        this.func_70296_d();
        if (this.eventHandler != null) {
            this.eventHandler.func_75130_a((IInventory)this);
        }
    }

    public void setInventorySlotContentsSoftly(int par1, ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
    }

    public int func_70297_j_() {
        return 64;
    }

    public String func_145825_b() {
        return null;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_145818_k_() {
        return false;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        if (i == 10 && itemstack != null) {
            if (!(itemstack.func_77973_b() instanceof ItemWandCasting)) {
                return false;
            }
            ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
            return !wand.isStaff(itemstack);
        }
        return true;
    }

    public int[] func_94128_d(int var1) {
        return new int[]{10};
    }

    public boolean func_102007_a(int i, ItemStack itemstack, int j) {
        if (i != 10 || itemstack == null || !(itemstack.func_77973_b() instanceof ItemWandCasting)) {
            return false;
        }
        ItemWandCasting wand = (ItemWandCasting)itemstack.func_77973_b();
        return !wand.isStaff(itemstack);
    }

    public boolean func_102008_b(int i, ItemStack itemstack, int j) {
        return i == 10;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return true;
    }
}

