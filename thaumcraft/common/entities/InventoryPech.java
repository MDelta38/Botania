/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.entities;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import thaumcraft.common.entities.monster.EntityPech;

public class InventoryPech
implements IInventory {
    private final EntityPech theMerchant;
    private ItemStack[] theInventory = new ItemStack[5];
    private final EntityPlayer thePlayer;
    private Container eventHandler;

    public InventoryPech(EntityPlayer par1EntityPlayer, EntityPech par2IMerchant, Container par1Container) {
        this.thePlayer = par1EntityPlayer;
        this.theMerchant = par2IMerchant;
        this.eventHandler = par1Container;
    }

    public int func_70302_i_() {
        return this.theInventory.length;
    }

    public ItemStack func_70301_a(int par1) {
        return this.theInventory[par1];
    }

    public ItemStack func_70298_a(int par1, int par2) {
        if (this.theInventory[par1] != null) {
            if (this.theInventory[par1].field_77994_a <= par2) {
                ItemStack var3 = this.theInventory[par1];
                this.theInventory[par1] = null;
                this.eventHandler.func_75130_a((IInventory)this);
                return var3;
            }
            ItemStack var3 = this.theInventory[par1].func_77979_a(par2);
            if (this.theInventory[par1].field_77994_a == 0) {
                this.theInventory[par1] = null;
            }
            this.eventHandler.func_75130_a((IInventory)this);
            return var3;
        }
        return null;
    }

    public ItemStack func_70304_b(int par1) {
        if (this.theInventory[par1] != null) {
            ItemStack itemstack = this.theInventory[par1];
            this.theInventory[par1] = null;
            return itemstack;
        }
        return null;
    }

    public void func_70299_a(int par1, ItemStack par2ItemStack) {
        this.theInventory[par1] = par2ItemStack;
        if (par2ItemStack != null && par2ItemStack.field_77994_a > this.func_70297_j_()) {
            par2ItemStack.field_77994_a = this.func_70297_j_();
        }
        this.eventHandler.func_75130_a((IInventory)this);
    }

    public int func_70297_j_() {
        return 64;
    }

    public boolean func_70300_a(EntityPlayer par1EntityPlayer) {
        return this.theMerchant.isTamed();
    }

    public boolean func_94041_b(int par1, ItemStack par2ItemStack) {
        return par1 == 0;
    }

    public String func_145825_b() {
        return "entity.Pech.name";
    }

    public boolean func_145818_k_() {
        return false;
    }

    public void func_70296_d() {
        this.eventHandler.func_75130_a((IInventory)this);
    }

    public void func_70295_k_() {
    }

    public void func_70305_f() {
    }
}

