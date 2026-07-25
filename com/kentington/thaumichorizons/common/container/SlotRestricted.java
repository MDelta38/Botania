/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.item.ItemStack
 */
package com.kentington.thaumichorizons.common.container;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotRestricted
extends Slot {
    ItemStack what;

    public SlotRestricted(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, ItemStack restriction) {
        super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
        this.what = restriction;
    }

    public boolean func_75214_a(ItemStack p_75214_1_) {
        return this.what.func_77973_b() == p_75214_1_.func_77973_b();
    }
}

