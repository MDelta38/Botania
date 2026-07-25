/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.ICrafting
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.lib.research.ResearchManager
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.container.SlotRestricted;
import com.kentington.thaumichorizons.common.container.SlotSample;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import thaumcraft.common.lib.research.ResearchManager;

public class ContainerVat
extends Container {
    TileVat tile;
    EntityPlayer player;
    SlotSample slotSample;

    public ContainerVat(EntityPlayer p, TileVat t) {
        int i;
        this.tile = t;
        this.player = p;
        if (ResearchManager.isResearchComplete((String)this.player.func_70005_c_(), (String)"incarnationVat")) {
            this.slotSample = new SlotSample(this.tile, 0, 63, 32);
            this.func_75146_a(this.slotSample);
            this.func_75146_a(new SlotRestricted((IInventory)this.tile, 1, 96, 32, new ItemStack(ThaumicHorizons.itemNutrients)));
        }
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.func_75146_a(new Slot((IInventory)p.field_71071_by, j + i * 9 + 9, 8 + j * 18, 127 + i * 18));
            }
        }
        for (i = 0; i < 9; ++i) {
            this.func_75146_a(new Slot((IInventory)p.field_71071_by, i, 8 + i * 18, 185));
        }
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return this.tile.func_70300_a(p_75145_1_);
    }

    public ItemStack func_82846_b(EntityPlayer p_82846_1_, int p_82846_2_) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.field_75151_b.get(p_82846_2_);
        if (slot != null && slot.func_75216_d()) {
            ItemStack itemstack1 = slot.func_75211_c();
            itemstack = itemstack1.func_77946_l();
            if (this.slotSample != null && p_82846_2_ == 0) {
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
            } else if (this.slotSample != null && p_82846_2_ == 1) {
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
            } else if (this.slotSample != null) {
                if (!(itemstack.func_77973_b() == ThaumicHorizons.itemNutrients && this.func_75135_a(itemstack1, 1, 2, false) || this.slotSample.func_75214_a(itemstack1) && this.func_75135_a(itemstack1, 0, 1, false))) {
                    return null;
                }
                if (itemstack1.field_77994_a == 0) {
                    slot.func_75215_d((ItemStack)null);
                }
                if (itemstack1.field_77994_a == itemstack.field_77994_a) {
                    return null;
                }
            } else {
                return null;
            }
        }
        return itemstack;
    }

    public void func_75142_b() {
    }

    @SideOnly(value=Side.CLIENT)
    public void func_75137_b(int par1, int par2) {
    }

    public void func_75132_a(ICrafting par1ICrafting) {
    }
}

