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
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.container.InventoryInjector;
import com.kentington.thaumichorizons.common.container.SlotRestricted;
import com.kentington.thaumichorizons.common.items.ItemInjector;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class ContainerInjector
extends Container {
    EntityPlayer player;
    ItemStack[] ammo = new ItemStack[7];
    public IInventory ammoInv = new InventoryInjector(this);
    ItemStack injector = null;
    int blockSlot;

    public ContainerInjector(EntityPlayer p) {
        int i;
        this.player = p;
        this.blockSlot = this.player.field_71071_by.field_70461_c + 34;
        this.injector = this.player.field_71071_by.func_70448_g();
        for (i = 0; i < 7; ++i) {
            this.ammoInv.func_70299_a(i, ((ItemInjector)ThaumicHorizons.itemInjector).getAmmo(this.injector, i));
        }
        this.func_75146_a(new SlotRestricted(this.ammoInv, 0, 73, 10, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.func_75146_a(new SlotRestricted(this.ammoInv, 1, 99, 20, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.func_75146_a(new SlotRestricted(this.ammoInv, 2, 107, 47, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.func_75146_a(new SlotRestricted(this.ammoInv, 3, 92, 70, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.func_75146_a(new SlotRestricted(this.ammoInv, 4, 64, 72, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.func_75146_a(new SlotRestricted(this.ammoInv, 5, 45, 51, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        this.func_75146_a(new SlotRestricted(this.ammoInv, 6, 49, 24, new ItemStack(ThaumicHorizons.itemSyringeInjection)));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)p.field_71071_by, j + i * 9 + 9, 8 + j * 18, 108 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)p.field_71071_by, i, 8 + i * 18, 166));
        }
        if (!this.player.field_70170_p.field_72995_K) {
            // empty if block
        }
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return true;
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (p_82846_2_ < 7) {
                if (!this.func_75135_a(itemstack1, 7, 43, true)) {
                    return null;
                }
                slot.func_75220_a(itemstack1, itemstack);
                if (itemstack1.field_77994_a == 0) {
                    slot.func_75215_d((ItemStack)null);
                }
                if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                    return null;
                }
                slot.func_82870_a(p_82846_1_, itemstack1);
            } else {
                if (itemstack1.func_77973_b() != ThaumicHorizons.itemSyringeInjection || !this.func_75135_a(itemstack1, 0, 7, false)) {
                    return null;
                }
                slot.func_75220_a(itemstack1, itemstack);
                if (itemstack1.field_77994_a == 0) {
                    slot.func_75215_d((ItemStack)null);
                }
                if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                    return null;
                }
            }
        }
        return itemstack;
    }

    public ItemStack func_75144_a(int par1, int par2, int par3, EntityPlayer par4EntityPlayer) {
        if (par1 == this.blockSlot) {
            return null;
        }
        InventoryPlayer inventoryplayer = par4EntityPlayer.field_71071_by;
        if (par1 != 0 || this.ammoInv.func_94041_b(par1, inventoryplayer.func_70445_o()) || par1 == 0 && inventoryplayer.func_70445_o() == null) {
            return super.func_75144_a(par1, par2, par3, par4EntityPlayer);
        }
        return null;
    }

    public void func_75134_a(EntityPlayer par1EntityPlayer) {
        if (!this.player.field_70170_p.field_72995_K) {
            NBTTagList ammo = new NBTTagList();
            for (int i = 0; i < 7; ++i) {
                ItemStack var3 = this.ammoInv.func_70304_b(i);
                if (var3 != null) {
                    NBTTagCompound var4 = new NBTTagCompound();
                    var3.func_77955_b(var4);
                    ammo.func_74742_a((NBTBase)var4);
                    continue;
                }
                ammo.func_74742_a((NBTBase)new NBTTagCompound());
            }
            NBTTagCompound newTag = new NBTTagCompound();
            newTag.func_74782_a("ammo", (NBTBase)ammo);
            this.injector.func_77982_d(newTag);
            if (this.player == null) {
                return;
            }
            if (this.player.func_70694_bm() != null && this.player.func_70694_bm().func_77969_a(this.injector)) {
                this.player.func_70062_b(0, this.injector);
            }
            this.player.field_71071_by.func_70296_d();
        }
    }

    public void func_75141_a(int par1, ItemStack par2ItemStack) {
        if (this.ammoInv.func_94041_b(par1, par2ItemStack)) {
            super.func_75141_a(par1, par2ItemStack);
        }
    }
}

