/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockBush
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.ItemBlock
 */
package com.emoniph.witchery.blocks;

import com.emoniph.witchery.WitcheryCreativeTab;
import com.emoniph.witchery.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

public abstract class BlockBaseBush
extends BlockBush {
    protected boolean registerBlockName = true;
    protected boolean registerWithCreateTab = true;
    protected final Class<? extends ItemBlock> clazzItem;

    public BlockBaseBush(Material material) {
        this(material, null);
    }

    public BlockBaseBush(Material material, Class<? extends ItemBlock> clazzItem) {
        super(material);
        this.clazzItem = clazzItem;
    }

    public Block func_149663_c(String blockName) {
        if (this.registerWithCreateTab) {
            this.func_149647_a(WitcheryCreativeTab.INSTANCE);
        } else {
            this.func_149647_a(null);
        }
        if (this.registerBlockName) {
            if (this.clazzItem == null) {
                BlockUtil.registerBlock((Block)this, blockName);
            } else {
                BlockUtil.registerBlock((Block)this, this.clazzItem, blockName);
            }
        }
        return super.func_149663_c(blockName);
    }
}

