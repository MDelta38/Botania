/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemSlab
 *  net.minecraft.item.ItemStack
 */
package com.emoniph.witchery.item;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.ItemUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;

public class ItemWitchSlab
extends ItemSlab {
    public ItemWitchSlab(BlockSlab slab, BlockSlab singleSlab, BlockSlab doubleSlab) {
        super((Block)slab, singleSlab, doubleSlab, slab == doubleSlab);
        this.func_77637_a(WitcheryCreativeTab.INSTANCE);
    }

    public ItemBlock func_77655_b(String itemName) {
        ItemUtil.registerItem((Item)this, itemName);
        return super.func_77655_b(itemName);
    }

    public String func_77667_c(ItemStack itemStack) {
        return super.func_77667_c(itemStack);
    }
}

