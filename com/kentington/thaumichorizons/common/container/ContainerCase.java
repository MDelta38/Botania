/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  thaumcraft.common.container.SlotLimitedByClass
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.container.InventoryCase;
import com.kentington.thaumichorizons.common.items.lenses.ILens;
import com.kentington.thaumichorizons.common.items.lenses.ItemLensCase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import thaumcraft.common.container.SlotLimitedByClass;

public class ContainerCase
extends Container {
    private World worldObj;
    private int posX;
    private int posY;
    private int posZ;
    private int blockSlot;
    public IInventory input = new InventoryCase(this);
    ItemStack pouch = null;
    EntityPlayer player = null;

    public ContainerCase(InventoryPlayer iinventory, World par2World, int par3, int par4, int par5) {
        this.worldObj = par2World;
        this.posX = par3;
        this.posY = par4;
        this.posZ = par5;
        this.player = iinventory.field_70458_d;
        this.pouch = iinventory.func_70448_g();
        this.blockSlot = iinventory.field_70461_c + 45;
        for (int a = 0; a < 18; ++a) {
            this.func_75146_a((Slot)new SlotLimitedByClass(ILens.class, this.input, a, 37 + a % 6 * 18, 51 + a / 6 * 18));
        }
        this.bindPlayerInventory(iinventory);
        if (!par2World.field_72995_K) {
            try {
                ((InventoryCase)this.input).stackList = ((ItemLensCase)this.pouch.func_77973_b()).getInventory(this.pouch);
            }
            catch (Exception e) {
                // empty catch block
            }
        }
        this.func_75130_a(this.input);
    }

    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 151 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)inventoryPlayer, i, 8 + i * 18, 209));
        }
    }

    public ItemStack func_82846_b(EntityPlayer par1EntityPlayer, int slot) {
        if (slot == this.blockSlot) {
            return null;
        }
        ItemStack stack = null;
        Slot slotObject = (Slot)this.field_75151_b.get(slot);
        if (slotObject != null && slotObject.func_75216_d()) {
            ItemStack stackInSlot = slotObject.func_75211_c();
            stack = stackInSlot.func_77946_l();
            if (slot < 18 ? !this.input.func_94041_b(slot, stackInSlot) || !this.func_75135_a(stackInSlot, 18, this.field_75151_b.size(), true) : !this.input.func_94041_b(slot, stackInSlot) || !this.func_75135_a(stackInSlot, 0, 18, false)) {
                return null;
            }
            if (stackInSlot.field_77994_a == 0) {
                slotObject.func_75215_d(null);
            } else {
                slotObject.func_75218_e();
            }
        }
        return stack;
    }

    public boolean func_75145_c(EntityPlayer var1) {
        return true;
    }

    public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 == this.blockSlot) {
            return null;
        }
        return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        super.func_75134_a(par1EntityPlayer);
        if (!this.worldObj.field_72995_K) {
            ((ItemLensCase)this.pouch.func_77973_b()).setInventory(this.pouch, ((InventoryCase)this.input).stackList);
            if (this.player == null) {
                return;
            }
            if (this.player.func_70694_bm() != null && this.player.func_70694_bm().func_77969_a(this.pouch)) {
                this.player.func_70062_b(0, this.pouch);
            }
            this.player.field_71071_by.func_70296_d();
        }
    }
}

