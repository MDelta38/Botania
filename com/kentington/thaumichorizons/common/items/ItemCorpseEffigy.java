/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package com.kentington.thaumichorizons.common.items;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemCorpseEffigy
extends Item {
    public ItemCorpseEffigy() {
        this.func_77625_d(1);
        this.func_77637_a(ThaumicHorizons.tabTH);
        this.func_111206_d("thaumcraft:brain");
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return "item.corpseEffigy";
    }
}

