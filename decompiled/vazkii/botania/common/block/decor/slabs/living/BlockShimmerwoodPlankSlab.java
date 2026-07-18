/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSlab
 */
package vazkii.botania.common.block.decor.slabs.living;

import net.minecraft.block.BlockSlab;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;

public class BlockShimmerwoodPlankSlab
extends BlockLivingSlab {
    public BlockShimmerwoodPlankSlab(boolean full) {
        super(full, ModBlocks.shimmerwoodPlanks, 0);
        this.func_149711_c(2.0f);
        this.func_149752_b(10.0f);
        this.func_149672_a(field_149766_f);
    }

    @Override
    public BlockSlab getFullBlock() {
        return (BlockSlab)ModFluffBlocks.shimmerwoodPlankSlabFull;
    }

    @Override
    public BlockSlab getSingleBlock() {
        return (BlockSlab)ModFluffBlocks.shimmerwoodPlankSlab;
    }
}

