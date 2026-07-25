/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.init.Items
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.CrucibleRecipe
 *  thaumcraft.api.crafting.IArcaneRecipe
 *  thaumcraft.api.crafting.InfusionEnchantmentRecipe
 *  thaumcraft.api.crafting.InfusionRecipe
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigResearch
 */
package thaumic.tinkerer.common.research;

import cpw.mods.fml.common.Loader;
import java.util.Arrays;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigResearch;
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.block.mobilizer.BlockMobilizer;
import thaumic.tinkerer.common.block.mobilizer.BlockMobilizerRelay;
import thaumic.tinkerer.common.research.TTResearchItem;

public final class ResearchHelper {
    public static ResearchItem kamiResearch;

    public static void initResearch() {
        ResearchHelper.registerResearchPages();
        ResearchItem research = new TTResearchItem("TTENCH_ASCENT_BOOST", new AspectList().add(Aspect.AIR, 1).add(Aspect.MOTION, 1).add(Aspect.MAGIC, 2), 6, 2, 2, new ResourceLocation("ttinkerer:textures/enchants/ascentBoost.png"), new ResearchPage[0]).setParents(new String[]{"ENCHANTER"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_SLOW_FALL", new AspectList().add(Aspect.AIR, 1).add(Aspect.MOTION, 1).add(Aspect.MAGIC, 2), 7, 3, 2, new ResourceLocation("ttinkerer:textures/enchants/slowFall.png"), new ResearchPage[0]).setParents(new String[]{"ENCHANTER"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_AUTO_SMELT", new AspectList().add(Aspect.FIRE, 1).add(Aspect.ENTROPY, 1).add(Aspect.MAGIC, 2), 7, 5, 2, new ResourceLocation("ttinkerer:textures/enchants/autoSmelt.png"), new ResearchPage[0]).setParents(new String[]{"ENCHANTER"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_DESINTEGRATE", new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.VOID, 1).add(Aspect.MAGIC, 2), 6, 6, 2, new ResourceLocation("ttinkerer:textures/enchants/desintegrate.png"), new ResearchPage[0]).setParents(new String[]{"ENCHANTER"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_QUICK_DRAW", new AspectList().add(Aspect.SENSES, 1).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 2), 4, 6, 2, new ResourceLocation("ttinkerer:textures/enchants/quickDraw.png"), new ResearchPage[0]).setParents(new String[]{"ENCHANTER"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_VAMPIRISM", new AspectList().add(Aspect.HUNGER, 1).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 2), 3, 5, 2, new ResourceLocation("ttinkerer:textures/enchants/vamprisim.png"), new ResearchPage[0]).setParents(new String[]{"ENCHANTER"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_FOCUSED", new AspectList().add(Aspect.ORDER, 1).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 2), 2, 7, 2, new ResourceLocation("ttinkerer:textures/enchants/focusedStrikes.png"), new ResearchPage[0]).setParents(new String[]{"TTENCH_VAMPIRISM"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_DISPERSED", new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.WEAPON, 1).add(Aspect.MAGIC, 2), 1, 6, 2, new ResourceLocation("ttinkerer:textures/enchants/dispersedStrikes.png"), new ResearchPage[0]).setParents(new String[]{"TTENCH_VAMPIRISM"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_FINAL", new AspectList().add(Aspect.ENTROPY, 3).add(Aspect.ORDER, 3).add(Aspect.WEAPON, 3).add(Aspect.MAGIC, 2), 0, 8, 2, new ResourceLocation("ttinkerer:textures/enchants/finalStrike.png"), new ResearchPage[0]).setParents(new String[]{"TTENCH_FOCUSED", "TTENCH_DISPERSED"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_POUNCE", new AspectList().add(Aspect.AIR, 3).add(Aspect.ORDER, 3).add(Aspect.ARMOR, 3).add(Aspect.MAGIC, 2), 7, 0, 2, new ResourceLocation("ttinkerer:textures/enchants/pounce.png"), new ResearchPage[0]).setParents(new String[]{"TTENCH_ASCENT_BOOST"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_SHATTER", new AspectList().add(Aspect.EARTH, 3).add(Aspect.ENTROPY, 3).add(Aspect.TOOL, 1).add(Aspect.MAGIC, 2), 5, 8, 2, new ResourceLocation("ttinkerer:textures/enchants/shatter.png"), new ResearchPage[0]).setParents(new String[]{"TTENCH_DESINTEGRATE"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_SHOCKWAVE", new AspectList().add(Aspect.AIR, 3).add(Aspect.ENTROPY, 3).add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 2), 9, 2, 2, new ResourceLocation("ttinkerer:textures/enchants/shockwave.png"), new ResearchPage[0]).setParents(new String[]{"TTENCH_SLOW_FALL"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_TUNNEL", new AspectList().add(Aspect.EARTH, 3).add(Aspect.ORDER, 3).add(Aspect.TOOL, 1).add(Aspect.MAGIC, 2), 9, 6, 2, new ResourceLocation("ttinkerer:textures/enchants/tunnel.png"), new ResearchPage[0]).setParents(new String[]{"TTENCH_AUTO_SMELT"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        research = new TTResearchItem("TTENCH_VALIANCE", new AspectList().add(Aspect.WEAPON, 3).add(Aspect.HEAL, 3).add(Aspect.MAGIC, 2), 1, 4, 2, new ResourceLocation("ttinkerer:textures/enchants/valiance.png"), new ResearchPage[0]).setParents(new String[]{"TTENCH_VAMPIRISM"});
        research.setPages(new ResearchPage[]{new ResearchPage("0")}).setSecondary().registerResearchItem();
        if (Loader.isModLoaded((String)"ComputerCraft")) {
            research = new TTResearchItem("PERIPHERALS", new AspectList(), -1, 0, 0, new ItemStack(Items.field_151137_ax), new ResearchPage[0]).setAutoUnlock().setRound();
            research.setPages(new ResearchPage[]{new ResearchPage("0")}).registerResearchItem();
        }
    }

    private static void registerResearchPages() {
        ResourceLocation background = new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png");
        ResearchCategories.registerCategory((String)"TT_CATEGORY", (ResourceLocation)new ResourceLocation("ttinkerer:textures/misc/r_enchanting.png"), (ResourceLocation)background);
    }

    public static ResearchPage recipePage(String name) {
        return new ResearchPage((IRecipe)ConfigResearch.recipes.get(name));
    }

    public static ResearchPage arcaneRecipePage(String name) {
        return new ResearchPage((IArcaneRecipe)ConfigResearch.recipes.get(name));
    }

    public static ResearchPage infusionPage(String name) {
        return new ResearchPage((InfusionRecipe)ConfigResearch.recipes.get(name));
    }

    public static ResearchPage infusionPage(String name, int count) {
        InfusionRecipe[] recipes = new InfusionRecipe[count];
        for (int i = 0; i < count; ++i) {
            recipes[i] = (InfusionRecipe)ConfigResearch.recipes.get(name + i);
        }
        return new ResearchPage(recipes);
    }

    public static ResearchPage enchantPage(String name) {
        return new ResearchPage((InfusionEnchantmentRecipe)ConfigResearch.recipes.get(name));
    }

    public static ResearchPage crucibleRecipePage(String name) {
        return new ResearchPage((CrucibleRecipe)ConfigResearch.recipes.get(name));
    }

    public static ResearchPage LeviationaryHelp() {
        return new ResearchPage(Arrays.asList(new AspectList(), 5, 1, 1, Arrays.asList(new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockMobilizerRelay.class)), new ItemStack(ConfigBlocks.blockHole, 1, 15), new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockMobilizer.class)), new ItemStack(ConfigBlocks.blockHole, 1, 15), new ItemStack(ThaumicTinkerer.registry.getFirstBlockFromClass(BlockMobilizerRelay.class)))));
    }
}

