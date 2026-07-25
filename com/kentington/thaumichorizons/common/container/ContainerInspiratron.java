/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Items
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.config.ConfigItems
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.container.SlotRestricted;
import com.kentington.thaumichorizons.common.tiles.TileInspiratron;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.config.ConfigItems;

public class ContainerInspiratron
extends Container {
    TileInspiratron tile;
    int progress;

    public ContainerInspiratron(InventoryPlayer p_i1812_1_, TileInspiratron p_i1812_2_) {
        int i;
        this.tile = p_i1812_2_;
        this.func_75146_a(new SlotRestricted((IInventory)p_i1812_2_, 0, 15, 42, new ItemStack(Items.field_151121_aF)));
        this.func_75146_a(new SlotRestricted((IInventory)p_i1812_2_, 1, 146, 42, new ItemStack(ConfigItems.itemResource, 1, 9)));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 137 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)p_i1812_1_, i, 8 + i * 18, 195));
        }
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a((Container)this, 0, this.tile.progress);
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return this.tile.func_70300_a(p_75145_1_);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
            if (this.progress == this.tile.progress) continue;
            icrafting.func_71112_a((Container)this, 0, this.tile.progress);
        }
        this.progress = this.tile.progress;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.tile.progress = par2;
        }
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (p_82846_2_ == 0) {
                if (!this.func_75135_a(itemstack1, 2, 38, true)) {
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
            } else if (p_82846_2_ == 1) {
                if (!this.func_75135_a(itemstack1, 2, 38, true)) {
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
                if (!this.func_75135_a(itemstack1, 0, 1, false)) {
                    return null;
                }
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
}

