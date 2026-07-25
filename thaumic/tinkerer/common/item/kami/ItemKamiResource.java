/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.client.renderer.texture.IIconRegister
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.IIcon
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.item.kami;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.List;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.client.core.helper.IconHelper;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.lib.LibItemNames;
import thaumic.tinkerer.common.registry.ItemKamiBase;
import thaumic.tinkerer.common.registry.ThaumicTinkererArcaneRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererInfusionRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipe;
import thaumic.tinkerer.common.registry.ThaumicTinkererRecipeMulti;
import thaumic.tinkerer.common.research.IRegisterableResearch;
import thaumic.tinkerer.common.research.KamiResearchItem;
import thaumic.tinkerer.common.research.ResearchHelper;
import thaumic.tinkerer.common.research.TTResearchItem;
import thaumic.tinkerer.common.research.TTResearchItemMulti;

public class ItemKamiResource
extends ItemKamiBase {
    final int subtypes = 8;
    IIcon[] icons;

    public ItemKamiResource() {
        this.func_77627_a(true);
    }

    public void func_150895_a(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < 8; ++i) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public void func_94581_a(IIconRegister par1IconRegister) {
        this.icons = new IIcon[8];
        for (int i = 0; i < this.icons.length; ++i) {
            this.icons[i] = IconHelper.forName(par1IconRegister, LibItemNames.KAMI_RESOURCE_NAMES[i]);
        }
    }

    public IIcon func_77617_a(int par1) {
        return this.icons[Math.min(7, par1)];
    }

    public String func_77657_g(ItemStack par1ItemStack) {
        return par1ItemStack.func_77960_j() >= 8 ? super.func_77667_c(par1ItemStack) : "item." + LibItemNames.KAMI_RESOURCE_NAMES[par1ItemStack.func_77960_j()];
    }

    public EnumRarity func_77613_e(ItemStack par1ItemStack) {
        return par1ItemStack.func_77960_j() != 7 && par1ItemStack.func_77960_j() != 6 ? TTCommonProxy.kamiRarity : super.func_77613_e(par1ItemStack);
    }

    @Override
    public String getItemName() {
        return "kamiResource";
    }

    @Override
    public IRegisterableResearch getResearchItem() {
        TTResearchItemMulti researchItemMulti = new TTResearchItemMulti();
        TTResearchItem research = (TTResearchItem)new KamiResearchItem("DIMENSION_SHARDS", new AspectList(), 7, 8, 0, new ItemStack(ThaumicTinkerer.registry.getFirstItemFromClass(ItemKamiResource.class), 1, 7)).setStub().setAutoUnlock().setRound();
        research.setPages(new ResearchPage("0"));
        researchItemMulti.addResearch(research);
        research = new KamiResearchItem("ICHOR", new AspectList().add(Aspect.MAN, 1).add(Aspect.LIGHT, 2).add(Aspect.SOUL, 1).add(Aspect.TAINT, 1), 9, 8, 5, new ItemStack((Item)this, 1, 0)).setWarp(5);
        research.setPages(new ResearchPage("0"), ResearchHelper.infusionPage("ICHOR"));
        ResearchHelper.kamiResearch = research;
        researchItemMulti.addResearch(research);
        research = (TTResearchItem)new KamiResearchItem("ICHOR_CLOTH", new AspectList().add(Aspect.CLOTH, 2).add(Aspect.LIGHT, 1).add(Aspect.CRAFT, 1).add(Aspect.SENSES, 1), 11, 7, 5, new ItemStack((Item)this, 1, 1)).setConcealed().setParents(new String[]{"ICHOR"});
        research.setPages(new ResearchPage("0"), ResearchHelper.arcaneRecipePage("ICHOR_CLOTH"));
        researchItemMulti.addResearch(research);
        research = (TTResearchItem)new KamiResearchItem("ICHORIUM", new AspectList().add(Aspect.METAL, 2).add(Aspect.LIGHT, 1).add(Aspect.CRAFT, 1).add(Aspect.TOOL, 1), 11, 9, 5, new ItemStack((Item)this, 1, 2)).setConcealed().setParents(new String[]{"ICHOR"}).setParentsHidden(new String[]{"ICHOR_CLOTH"});
        research.setPages(new ResearchPage("0"), ResearchHelper.arcaneRecipePage("ICHORIUM"));
        researchItemMulti.addResearch(research);
        research = (TTResearchItem)new KamiResearchItem("CAP_ICHOR", new AspectList().add(Aspect.TOOL, 2).add(Aspect.METAL, 1).add(Aspect.LIGHT, 1).add(Aspect.MAGIC, 1), 11, 11, 5, new ItemStack((Item)this, 1, 4)).setWarp(2).setConcealed().setParents(new String[]{"ICHORIUM"});
        research.setPages(new ResearchPage("0"), ResearchHelper.arcaneRecipePage("CAP_ICHOR"));
        researchItemMulti.addResearch(research);
        research = (TTResearchItem)new KamiResearchItem("ROD_ICHORCLOTH", new AspectList().add(Aspect.TOOL, 2).add(Aspect.CLOTH, 1).add(Aspect.LIGHT, 1).add(Aspect.MAGIC, 1), 14, 2, 5, new ItemStack((Item)this, 1, 5)).setWarp(1).setConcealed().setParents(new String[]{"ICHOR_CLOTH"}).setParentsHidden(new String[]{"CAP_ICHOR"});
        research.setPages(new ResearchPage("0"), ResearchHelper.infusionPage("ROD_ICHORCLOTH"));
        researchItemMulti.addResearch(research);
        return researchItemMulti;
    }

    @Override
    public ThaumicTinkererRecipe getRecipeItem() {
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack((Item)this, 9, 3), (Object[])new Object[]{new ItemStack((Item)this, 1, 2)});
        return new ThaumicTinkererRecipeMulti(new ThaumicTinkererArcaneRecipe("ICHOR_CLOTH", "ICHOR_CLOTH", new ItemStack((Item)this, 3, 1), new AspectList().add(Aspect.FIRE, 125).add(Aspect.EARTH, 125).add(Aspect.WATER, 125).add(Aspect.AIR, 125).add(Aspect.ORDER, 125).add(Aspect.ENTROPY, 125), "CCC", "III", "DDD", Character.valueOf('C'), new ItemStack(ConfigItems.itemResource, 1, 7), Character.valueOf('I'), new ItemStack((Item)this, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151045_i)), new ThaumicTinkererArcaneRecipe("ICHORIUM", "ICHORIUM", new ItemStack((Item)this, 1, 2), new AspectList().add(Aspect.FIRE, 100).add(Aspect.EARTH, 100).add(Aspect.WATER, 100).add(Aspect.AIR, 100).add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100), " T ", "IDI", " I ", Character.valueOf('T'), new ItemStack(ConfigItems.itemResource, 1, 2), Character.valueOf('I'), new ItemStack((Item)this, 1, 0), Character.valueOf('D'), new ItemStack(Items.field_151045_i)), new ThaumicTinkererArcaneRecipe("CAP_ICHOR", "CAP_ICHOR", new ItemStack((Item)this, 2, 4), new AspectList().add(Aspect.FIRE, 100).add(Aspect.EARTH, 100).add(Aspect.WATER, 100).add(Aspect.AIR, 100).add(Aspect.ORDER, 100).add(Aspect.ENTROPY, 100), "ICI", " M ", "ICI", Character.valueOf('M'), new ItemStack((Item)this, 1, 2), Character.valueOf('I'), new ItemStack((Item)this, 1, 0), Character.valueOf('C'), new ItemStack(ConfigItems.itemWandCap, 1, 2)), new ThaumicTinkererInfusionRecipe("ICHOR", new ItemStack((Item)this, 8, 0), 7, new AspectList().add(Aspect.MAN, 32).add(Aspect.LIGHT, 32).add(Aspect.SOUL, 64), new ItemStack(Items.field_151156_bN), new ItemStack(Items.field_151045_i), new ItemStack((Item)this, 8, 7), new ItemStack(Items.field_151061_bv), new ItemStack((Item)this, 8, 6)), new ThaumicTinkererInfusionRecipe("ROD_ICHORCLOTH", new ItemStack((Item)this, 1, 5), 9, new AspectList().add(Aspect.MAGIC, 100).add(Aspect.LIGHT, 32).add(Aspect.TOOL, 32), new ItemStack(ConfigItems.itemWandRod, 1, 2), new ItemStack((Item)this), new ItemStack((Item)this, 1, 1), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(Items.field_151073_bk), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack((Item)this, 1, 1)));
    }
}

