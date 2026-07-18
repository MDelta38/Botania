/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor.slabs;

import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.slabs.BlockLivingSlab;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockReedSlab
extends BlockLivingSlab {
    public BlockReedSlab(boolean full) {
        super(full, ModBlocks.reedBlock, 0);
        this.func_149711_c(1.0f);
        this.func_149672_a(field_149766_f);
    }

    @Override
    public BlockSlab getFullBlock() {
        return (BlockSlab)ModFluffBlocks.reedSlabFull;
    }

    @Override
    public BlockSlab getSingleBlock() {
        return (BlockSlab)ModFluffBlocks.reedSlab;
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.decorativeBlocks;
    }
}

