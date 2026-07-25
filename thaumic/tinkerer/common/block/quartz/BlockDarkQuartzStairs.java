/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.item.ItemBlock
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 */
package thaumic.tinkerer.common.block.quartz;

import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.quartz.BlockDarkQuartz;
import thaumic.tinkerer.common.core.handler.ModCreativeTab;
import thaumic.tinkerer.common.registry.ITTinkererBlock;
import thaumic.tinkerer.common.registry.ThaumicTinkererCraftingBenchRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;

public class BlockDarkQuartzStairs
extends BlockStairs
implements ITTinkererBlock {
    public BlockDarkQuartzStairs() {
        super(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class), 0);
        this.func_149647_a(ModCreativeTab.INSTANCE);
    }

    @Override
    public ArrayList<Object> getSpecialParameters() {
        return null;
    }

    @Override
    public String getBlockName() {
        return "darkQuartzStairs";
    }

    @Override
    public boolean shouldRegister() {
        return true;
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public Class<? extends ItemBlock> getItemBlock() {
        return null;
    }

    @Override
    public Class<? extends TileEntity> getTileEntity() {
        return null;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        return null;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererRecipeMulti(new ThaumicTinkererCraftingBenchRecipe("DARK_QUARTZ5", new ItemStack((Block)this, 4), "  Q", " QQ", "QQQ", Character.valueOf('Q'), ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class)), new ThaumicTinkererCraftingBenchRecipe("", new ItemStack((Block)this, 4), "Q  ", "QQ ", "QQQ", Character.valueOf('Q'), ThaumicTinkerer.registry.getFirstBlockFromClass(BlockDarkQuartz.class)));
    }
}

