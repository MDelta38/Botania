/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSlab
 */
package vazkii.botania.common.block.decor.slabs.bricks;

import net.minecraft.block.BlockSlab;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.bricks.BlockCustomBrickSlab;

public class BlockSoulBrickSlab
extends BlockCustomBrickSlab {
    public BlockSoulBrickSlab(boolean full) {
        super(full, 1);
    }

    @Override
    public BlockSlab getFullBlock() {
        return (BlockSlab)ModFluffBlocks.soulBrickSlabFull;
    }

    @Override
    public BlockSlab getSingleBlock() {
        return (BlockSlab)ModFluffBlocks.soulBrickSlab;
    }
}

