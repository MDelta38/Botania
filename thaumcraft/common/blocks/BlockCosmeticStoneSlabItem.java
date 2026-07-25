/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.item.ItemSlab
 */
package thaumcraft.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import thaumcraft.common.config.ConfigBlocks;

public class BlockCosmeticStoneSlabItem
extends ItemSlab {
    public BlockCosmeticStoneSlabItem(Block par1) {
        super(par1, (BlockSlab)ConfigBlocks.blockSlabStone, (BlockSlab)ConfigBlocks.blockDoubleSlabStone, false);
    }
}

