/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package witchinggadgets.common.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryBag
implements IInventory {
    private Container container;
    public ItemStack[] stackList;

    public InventoryBag(Container par1Container) {
        this.container = par1Container;
        this.stackList = new ItemStack[18];
    }

    public int func_70302_i_() {
        return this.stackList.length;
    }

    public ItemStack func_70301_a(int i) {
        if (i >= this.func_70302_i_()) {
            return null;
        }
        return this.stackList[i];
    }

    public ItemStack func_70304_b(int i) {
        if (this.stackList[i] != null) {
            ItemStack itemstack = this.stackList[i];
            this.stackList[i] = null;
            return itemstack;
        }
        return null;
    }

    public ItemStack func_70298_a(int i, int j) {
        if (this.stackList[i] != null) {
            if (this.stackList[i].field_77994_a <= j) {
                ItemStack itemstack = this.stackList[i];
                this.stackList[i] = null;
                this.func_70296_d();
                return itemstack;
            }
            ItemStack itemstack = this.stackList[i].func_77979_a(j);
            if (this.stackList[i].field_77994_a == 0) {
                this.stackList[i] = null;
            }
            this.container.func_75130_a((IInventory)this);
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int i, ItemStack stack) {
        this.stackList[i] = stack;
        if (stack != null && stack.field_77994_a > this.func_70297_j_()) {
            stack.field_77994_a = this.func_70297_j_();
        }
        this.container.func_75130_a((IInventory)this);
    }

    public String func_145825_b() {
        return "container.WGPouch";
    }

    public boolean func_145818_k_() {
        return true;
    }

    public int func_70297_j_() {
        return 64;
    }

    public void func_70296_d() {
    }

    public boolean func_70300_a(EntityPlayer entityplayer) {
        return true;
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }
}

