/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.tiles.TileMagicBox;

public class ContainerMagicBox
extends Container {
    private TileMagicBox box;
    public IInventory playerInv;
    private int numRows;

    public ContainerMagicBox(IInventory par1IInventory, TileMagicBox par2IInventory) {
        this.box = par2IInventory;
        this.numRows = par2IInventory.func_70302_i_() / 9;
        this.playerInv = par1IInventory;
        par2IInventory.func_70295_k_();
        this.bindBoxInventory(0);
        this.bindPlayerInventory();
        if (this.box.func_145831_w() != null && this.box.func_145831_w().field_72995_K) {
            TileMagicBox.tc = this;
        }
    }

    void bindBoxInventory(int row) {
        for (int j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.func_75146_a(new Slot((IInventory)this.box, k + (j + row) * 9, 8 + k * 18, 18 + j * 18));
            }
        }
    }

    void bindPlayerInventory() {
        int j;
        for (j = 0; j < 3; ++j) {
            for (int k = 0; k < 9; ++k) {
                this.func_75146_a(new Slot(this.playerInv, k + j * 9 + 9, 8 + k * 18, 103 + j * 18));
            }
        }
        for (j = 0; j < 9; ++j) {
            this.func_75146_a(new Slot(this.playerInv, j, 8 + j * 18, 161));
        }
    }

    public void refreshInventory() {
        this.field_75153_a.clear();
        this.field_75151_b.clear();
        this.bindBoxInventory(0);
        this.bindPlayerInventory();
    }

    public boolean func_75145_c(EntityPlayer par1EntityPlayer) {
        return this.box.func_70300_a(par1EntityPlayer);
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int par2) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(par2);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (par2 < this.numRows * 9 ? !this.func_75135_a(itemstack1, this.numRows * 9, this.field_75151_b.size(), true) : !this.func_75135_a(itemstack1, 0, this.numRows * 9, false)) {
                return null;
            }
            if (itemstack1.field_77994_a == 0) {
                slot.func_75215_d((ItemStack)null);
            } else {
                slot.func_75218_e();
            }
        }
        return itemstack;
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        this.box.func_70305_f();
    }
}

