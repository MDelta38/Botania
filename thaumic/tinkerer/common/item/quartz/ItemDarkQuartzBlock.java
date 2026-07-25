/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemMultiTexture
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.item.quartz;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.quartz.BlockDarkQuartz;
import thaumic.tinkerer.common.lib.LibBlockNames;
import thaumic.tinkerer.common.registry.ITTinkererItem;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class ItemDarkQuartzBlock
extends ItemMultiTexture
implements ITTinkererItem {
    public ItemDarkQuartzBlock(Block par1) {
        super(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class), ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class), new String[]{""});
    }

    public String func_77667_c(ItemStack par1ItemStack) {
        return par1ItemStack.func_77960_j() >= 3 ? "" : LibBlockNames.DARK_QUARTZ_BLOCK_NAMES[par1ItemStack.func_77960_j()];
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getItemName() {
        return "darkQuartz";
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

