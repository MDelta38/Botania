/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSlab
 */
package vazkii.botania.common.block.decor.slabs.prismarine;

import net.minecraft.block.BlockSlab;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.prismarine.BlockPrismarineSlab;

public class BlockDarkPrismarineSlab
extends BlockPrismarineSlab {
    public BlockDarkPrismarineSlab(boolean full) {
        super(full, 2);
    }

    @Override
    public BlockSlab getFullBlock() {
        return (BlockSlab)ModFluffBlocks.darkPrismarineSlabFull;
    }

    @Override
    public BlockSlab getSingleBlock() {
        return (BlockSlab)ModFluffBlocks.darkPrismarineSlab;
    }
}

