/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.biome.BiomeGenBase
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.item.ItemBase;
import com.emoniph.witchery.item.ItemBook;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemBiomeNote
extends ItemBase {
    public ItemBiomeNote() {
        this.func_77627_a(true);
        this.func_77656_e(0);
    }

    public String func_77653_i(ItemStack stack) {
        String name = super.func_77653_i(stack);
        BiomeGenBase biome = ItemBook.getSelectedBiome(stack.func_77960_j());
        if (biome != null) {
            return String.format(name, biome.field_76791_y);
        }
        return String.format(name, "").trim();
    }
}

