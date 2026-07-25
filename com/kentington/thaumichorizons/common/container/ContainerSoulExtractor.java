/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.InventoryPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.container.SlotRestricted;
import com.kentington.thaumichorizons.common.tiles.TileSoulExtractor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerSoulExtractor
extends Container {
    TileSoulExtractor tile;
    private int ticksLeft;

    public ContainerSoulExtractor(InventoryPlayer p_i1812_1_, TileSoulExtractor p_i1812_2_) {
        int i;
        this.tile = p_i1812_2_;
        this.func_75146_a(new SlotRestricted((IInventory)p_i1812_2_, 0, 64, 30, new ItemStack(Blocks.field_150425_aM)));
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)p_i1812_1_, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)p_i1812_1_, i, 8 + i * 18, 142));
        }
    }

    public void func_75132_a(ICrafting par1ICrafting) {
        super.func_75132_a(par1ICrafting);
        par1ICrafting.func_71112_a((Container)this, 0, this.tile.ticksLeft);
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return this.tile.func_70300_a(p_75145_1_);
    }

    public void func_75142_b() {
        super.func_75142_b();
        for (int i = 0; i < this.field_75149_d.size(); ++i) {
            ICrafting icrafting = (ICrafting)this.field_75149_d.get(i);
            if (this.ticksLeft == this.tile.ticksLeft) continue;
            icrafting.func_71112_a((Container)this, 0, this.tile.ticksLeft);
        }
        this.ticksLeft = this.tile.ticksLeft;
    }

    @SideOnly(value=Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
        if (par1 == 0) {
            this.tile.ticksLeft = par2;
        }
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (p_82846_2_ == 0) {
                if (!this.func_75135_a(itemstack1, 1, 37, true)) {
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
                if (itemstack.func_77973_b() != Item.func_150898_a((Block)Blocks.field_150425_aM) || !this.func_75135_a(itemstack1, 0, 1, false)) {
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
}

