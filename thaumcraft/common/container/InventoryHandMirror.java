/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryHandMirror
implements IInventory {
    private ItemStack[] stackList = new ItemStack[1];
    private Container eventHandler;

    public InventoryHandMirror(Container par1Container) {
        this.eventHandler = par1Container;
    }

    public int func_70302_i_() {
        return this.stackList.length;
    }

    public ItemStack func_70301_a(int par1) {
        return par1 >= this.func_70302_i_() ? null : this.stackList[par1];
    }

    public ItemStack func_70304_b(int par1) {
        if (this.stackList[par1] != null) {
            ItemStack var2 = this.stackList[par1];
            this.stackList[par1] = null;
            return var2;
        }
        return null;
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

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.stackList[par1] = par2ItemStack;
        this.eventHandler.func_75130_a((IInventory)this);
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return true;
    }

    public boolean func_94041_b(int i, ItemStack itemstack) {
        return true;
    }

    public String func_145825_b() {
        return "container.handmirror";
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

