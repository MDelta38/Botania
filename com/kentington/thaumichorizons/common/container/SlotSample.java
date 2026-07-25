/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.tiles.TileVat;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotSample
extends Slot {
    public SlotSample(TileVat p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
        super((IInventory)p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
    }

    public boolean func_75214_a(ItemStack what) {
        return what.func_77973_b() == ThaumicHorizons.itemSyringeBloodSample || what.func_77973_b() == ThaumicHorizons.itemCorpseEffigy || ThaumicHorizons.incarnationItems.containsKey(what.func_77973_b());
    }
}

