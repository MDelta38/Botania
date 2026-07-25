/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.ItemApi
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.aspects.IEssentiaContainerItem
 *  thaumcraft.api.crafting.IArcaneRecipe
 *  thaumcraft.api.crafting.InfusionRecipe
 *  thaumcraft.api.crafting.ShapedArcaneRecipe
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 */
package drunkmafia.thaumicinfusion.common.intergration;

import drunkmafia.thaumicinfusion.common.block.EssentiaBlock;
import drunkmafia.thaumicinfusion.common.item.TIItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IEssentiaContainerItem;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;

public class ThaumcraftIntergration {
    public static void init() {
        ShapedArcaneRecipe essentiaRecipe = null;
        ItemStack essentiaBlock = null;
        for (Aspect aspect : Aspect.aspects.values()) {
            for (int i = 0; i <= 2; ++i) {
                ItemStack item;
                ItemStack stack = EssentiaBlock.getEssentiaBlock(aspect, i);
                if (i == 0) {
                    item = ItemApi.getItem((String)"itemEssence", (int)0);
                    ((IEssentiaContainerItem)item.func_77973_b()).setAspects(item, new AspectList().add(aspect, 8));
                } else if (i == 1) {
                    item = EssentiaBlock.getEssentiaBlock(aspect, 0);
                } else {
                    if (i != 2) continue;
                    item = EssentiaBlock.getEssentiaBlock(aspect, 1);
                }
                ShapedArcaneRecipe recipe = ThaumcraftApi.addArcaneCraftingRecipe((String)"ESSENTIABLOCKS", (ItemStack)stack, (AspectList)new AspectList().add(Aspect.ENTROPY, 4), (Object[])new Object[]{"PP", "PP", Character.valueOf('P'), item});
                if (essentiaRecipe == null) {
                    essentiaRecipe = recipe;
                }
                if (essentiaBlock != null) continue;
                essentiaBlock = stack;
            }
        }
        ResearchCategories.registerCategory((String)"THAUMICINFUSION", (ResourceLocation)new ResourceLocation("thaumicinfusion", "textures/research/r_ti.png"), (ResourceLocation)new ResourceLocation("thaumicinfusion", "textures/research/r_tibg.png"));
        InfusionRecipe infusionRecipe = ThaumcraftApi.addInfusionCraftingRecipe((String)"FOCUSINFUSION", (Object)new ItemStack(TIItems.focusInfusing), (int)4, (AspectList)new AspectList().add(Aspect.EARTH, 25).add(Aspect.ARMOR, 25).add(Aspect.ORDER, 25).add(Aspect.MIND, 10), (ItemStack)ItemApi.getItem((String)"itemFocusWarding", (int)0), (ItemStack[])new ItemStack[]{ItemApi.getItem((String)"itemResource", (int)3), ItemApi.getItem((String)"itemShard", (int)3), new ItemStack(Items.field_151128_bU), ItemApi.getItem((String)"itemShard", (int)4), ItemApi.getItem((String)"itemResource", (int)3), ItemApi.getItem((String)"itemShard", (int)3), new ItemStack(Items.field_151128_bU), ItemApi.getItem((String)"itemShard", (int)4)});
        new ResearchItem("FOCUSINFUSION", "THAUMICINFUSION", new AspectList().add(Aspect.EARTH, 6).add(Aspect.ARMOR, 3).add(Aspect.ORDER, 3).add(Aspect.MIND, 3), -2, 0, 2, new ItemStack(TIItems.focusInfusing)).setPages(new ResearchPage[]{new ResearchPage("tc.research_page.FOCUSINFUSION.1"), new ResearchPage(infusionRecipe), new ResearchPage("tc.research_page.FOCUSINFUSION.2"), new ResearchPage("tc.research_page.FOCUSINFUSION.3")}).registerResearchItem();
        new ResearchItem("ESSENTIABLOCKS", "THAUMICINFUSION", new AspectList().add(Aspect.ORDER, 3).add(Aspect.MAGIC, 3), 2, 0, 2, essentiaBlock).setPages(new ResearchPage[]{new ResearchPage("tc.research_page.ESSENTIABLOCKS.1"), new ResearchPage((IArcaneRecipe)essentiaRecipe)}).registerResearchItem();
    }
}

