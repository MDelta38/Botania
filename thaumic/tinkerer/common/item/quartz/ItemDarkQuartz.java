/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 */
package thaumic.tinkerer.common.item.quartz;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumic.tinkerer.common.registry.ItemBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererCraftingBenchRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;

public class ItemDarkQuartz
extends ItemBase {
    @Override
    public String getItemName() {
        return "darkQuartzItem";
    }

    @Override
    public boolean shouldDisplayInTab() {
        return true;
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        IRegisterableResearch researchItem = (IRegisterableResearch)new TTResearchItem("DARK_QUARTZ", new AspectList(), -2, 2, 0, new ItemStack((Item)this), new ResearchPage("0"), ResearchHelper.recipePage("DARK_QUARTZ0"), ResearchHelper.recipePage("DARK_QUARTZ1"), ResearchHelper.recipePage("DARK_QUARTZ2"), ResearchHelper.recipePage("DARK_QUARTZ3"), ResearchHelper.recipePage("DARK_QUARTZ4"), ResearchHelper.recipePage("DARK_QUARTZ5")).setStub().setAutoUnlock().setRound().registerResearchItem();
        return researchItem;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        return new ThaumicTinkererRecipeMulti(new ThaumicTinkererCraftingBenchRecipe("DARK_QUARTZ0", new ItemStack((Item)this, 4), "Q Q", " C ", "Q Q", Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), Items.field_151044_h), new ThaumicTinkererCraftingBenchRecipe("DARK_QUARTZ0", new ItemStack((Item)this, 4), "Q Q", " C ", "Q Q", Character.valueOf('Q'), Items.field_151128_bU, Character.valueOf('C'), new ItemStack(Items.field_151044_h, 1, 1)));
    }
}

