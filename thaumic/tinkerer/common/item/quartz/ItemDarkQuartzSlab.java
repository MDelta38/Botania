/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSlab
 *  net.minecraft.item.ItemSlab
 */
package thaumic.tinkerer.common.item.quartz;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.quartz.BlockDarkQuartzSlab;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemDarkQuartzSlab
extends ItemSlab
implements ITTinkererItem {
    public ItemDarkQuartzSlab(Block par1) {
        super(par1, (BlockSlab)ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartzSlab.class), (BlockSlab)ThaumicTinkerer.registry.getBlockFromClass(BlockDarkQuartzSlab.class).get(1), false);
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "darkQuartzSlab";
    }

    @Override
    public boolean shouldRegister() {
        return false;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return null;
    }
}

