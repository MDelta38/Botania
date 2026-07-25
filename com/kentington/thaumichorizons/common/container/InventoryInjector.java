/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryInjector
implements IInventory {
    private ItemStack[] stackList;
    private Container eventHandler;

    public InventoryInjector(Container cont) {
        this.eventHandler = cont;
        this.stackList = new ItemStack[7];
    }

    public int func_70302_i_() {
        return 7;
    }

    public ItemStack func_70301_a(int par1) {
        return par1 >= this.func_70302_i_() ? null : this.stackList[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.stackList[par1] != null) {
            if (this.stackList[par1].field_77994_a <= par2) {
                ItemStack var3 = this.stackList[par1];
                this.stackList[par1] = null;
                this.eventHandler.func_75130_a((IInventory)this);
                return var3;
            }
            ItemStack var3 = this.stackList[par1].func_77979_a(par2);
            if (this.stackList[par1].field_77994_a == 0) {
                this.stackList[par1] = null;
            }
            this.eventHandler.func_75130_a((IInventory)this);
            return var3;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.stackList[par1] != null) {
            ItemStack var2 = this.stackList[par1];
            this.stackList[par1] = null;
            return var2;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
        this.eventHandler.func_75130_a((IInventory)this);
    }

    public String func_145825_b() {
        return "container.injector";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public int func_70297_j_() {
        return 1;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer p_70300_1_) {
        return true;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int p_94041_1_, ItemStack p_94041_2_) {
        return p_94041_2_ != null && p_94041_2_.func_77973_b() == ThaumicHorizons.itemSyringeInjection;
    }
}

