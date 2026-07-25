/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package flaxbeard.thaumicexploration.misc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SortingInventory
implements IInventory {
    private ItemStack[] inventory = new ItemStack[18];
    public int type = 1;

    public int func_70302_i_() {
        return this.inventory.length;
    }

    public ItemStack func_70301_a(int i) {
        return this.inventory[i];
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.inventory[i] != null) {
            if (this.inventory[i].field_77994_a <= j) {
                ItemStack itemstack = this.inventory[i];
                this.inventory[i] = null;
                return itemstack;
            }
            ItemStack itemstack = this.inventory[i].func_77979_a(j);
            if (this.inventory[i].field_77994_a == 0) {
                this.inventory[i] = null;
            }
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70304_b(int i) {
        return this.inventory[i];
    }

    public void func_70299_a(int i, ItemStack itemstack) {
        this.inventory[i] = itemstack;
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return false;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public String func_145825_b() {
        return null;
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_70296_d() {
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }
}

