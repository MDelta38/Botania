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
 *  thaumcraft.api.research.ResearchCategoryList
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.common.config.ConfigResearch
 */
package flaxbeard.thaumicexploration.research;

import cpw.mods.fml.common.Loader;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.research.FauxResearchItem;
import flaxbeard.thaumicexploration.research.TXResearchItem;
import java.util.ArrayList;
import java.util.List;
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
import thaumcraft.api.research.ResearchCategoryList;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.common.config.ConfigResearch;

public final class ModResearch {
    public static ItemStack getOriginalItem(String name, String category) {
        return ((ResearchItem)((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)category)).research.get((Object)name)).icon_item;
    }

    public static ResourceLocation getOriginalGraphic(String name, String category) {
        return ((ResearchItem)((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)category)).research.get((Object)name)).icon_resource;
    }

    public static void initResearch() {
        ModResearch.registerResearchPages();
        ResearchItem research = new FauxResearchItem("TXBASICARTIFACE", "TX", "BASICARTIFACE", "ARTIFICE", 3, 1, ModResearch.getOriginalItem("BASICARTIFACE", "ARTIFICE")).registerResearchItem();
        research = new TXResearchItem("DISCOUNTRINGS", "TX", new AspectList().add(Aspect.MAGIC, 5).add(Aspect.CRYSTAL, 2), 1, 3, 1, new ItemStack(ThaumicExploration.discountRing, 1, 2)).setParents(new String[]{"BASICARTIFACE", "TXBASICARTIFACE"}).setConcealed().setSecondary().registerResearchItem();
        ArrayList<IArcaneRecipe> scer = new ArrayList<IArcaneRecipe>();
        for (int i = 0; i < 6; ++i) {
            scer.add((IArcaneRecipe)ConfigResearch.recipes.get("DISCOUNTRINGS" + i));
        }
        research.setPages(new ResearchPage[]{new ResearchPage("1"), new ResearchPage(scer.toArray(new IArcaneRecipe[0]))});
        research = new FauxResearchItem("TXTALLOW", "TX", "TALLOW", "ALCHEMY", -6, -11, ModResearch.getOriginalItem("TALLOW", "ALCHEMY")).registerResearchItem();
        research = new FauxResearchItem("TXINFUSION", "TX", "INFUSION", "ARTIFICE", 2, -7, ModResearch.getOriginalItem("INFUSION", "ARTIFICE")).registerResearchItem();
        research = new TXResearchItem("FLESHCURE", "TX", new AspectList().add(Aspect.ARMOR, 1).add(Aspect.FLESH, 3).add(Aspect.EXCHANGE, 2), -4, -10, 1, new ItemStack(Items.field_151078_bh)).setParents(new String[]{"TXTALLOW"}).setParentsHidden(new String[]{"TALLOW"}).setHidden().setSecondary().setItemTriggers(new ItemStack[]{new ItemStack(Items.field_151078_bh, 1, Short.MAX_VALUE)}).registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.cruciblePage("FLESHCURE")});
        research = new TXResearchItem("SOULBRAZIER", "TX", new AspectList().add(Aspect.AURA, 3).add(Aspect.ELDRITCH, 3), -6, -10, 0, new ItemStack(ThaumicExploration.soulBrazier)).registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("SOULBRAZIER")});
        research = new TXResearchItem("FLOATCANDLE", "TX", new AspectList().add(Aspect.MAGIC, 1).add(Aspect.FLESH, 2).add(Aspect.AIR, 3), -7, -9, 1, new ItemStack(ThaumicExploration.floatCandle)).setParents(new String[]{"TXTALLOW", "TALLOW"}).setConcealed().setSecondary().registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.arcaneRecipePage("FLOATCANDLE")});
        research = new TXResearchItem("BRAINCURE", "TX", new AspectList().add(Aspect.MIND, 4).add(Aspect.ORDER, 5).add(Aspect.MAN, 3).add(Aspect.UNDEAD, 3), -2, -8, 1, new ItemStack(ThaumicExploration.pureZombieBrain)).setParents(new String[]{"JARBRAIN", "FLESHCURE", "TXINFUSION"}).setParentsHidden(new String[]{"INFUSION"}).setConcealed().registerResearchItem().setSecondary();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("BRAINCURE")});
        if (ThaumicExploration.allowThinkTank) {
            research = new TXResearchItem("THINKTANK", "TX", new AspectList().add(Aspect.UNDEAD, 4).add(Aspect.MIND, 8).add(Aspect.SENSES, 6).add(Aspect.GREED, 2), -1, -5, 3, new ItemStack(ThaumicExploration.thinkTankJar)).setParents(new String[]{"BRAINCURE", "RESEARCHER2"}).setParentsHidden(new String[]{"INFUSION"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("THINKTANK"), new ResearchPage("2"), ModResearch.constructPage("BUILDTHINKTANK")});
        }
        if (ThaumicExploration.allowUrn) {
            research = new TXResearchItem("URN", "TX", new AspectList().add(Aspect.MAGIC, 4).add(Aspect.VOID, 2).add(Aspect.WATER, 5), 3, -10, 1, new ItemStack(ThaumicExploration.everfullUrn)).setParents(new String[]{"INFUSION", "TXINFUSION"}).setParentsHidden(new String[]{"ARCANEEAR"}).setConcealed().registerResearchItem();
            if (Loader.isModLoaded((String)"Botania")) {
                research.setPages(new ResearchPage[]{new ResearchPage("1B"), ModResearch.infusionPage("URN")});
            } else {
                research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("URN")});
            }
            research = new TXResearchItem("BURN", "TX", new AspectList().add(Aspect.MAGIC, 4).add(Aspect.VOID, 2).add(Aspect.FIRE, 5), 4, -11, 1, new ItemStack(ThaumicExploration.everburnUrn)).setParents(new String[]{"URN"}).setParentsHidden(new String[]{"ARCANEEAR"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("BURN")});
        }
        research = new FauxResearchItem("TXHOVERHARNESS", "TX", "HOVERHARNESS", "ARTIFICE", 2, -12, ModResearch.getOriginalItem("HOVERHARNESS", "ARTIFICE")).registerResearchItem();
        research = new TXResearchItem("STABILIZERBELT", "TX", new AspectList().add(Aspect.ORDER, 4).add(Aspect.EARTH, 4).add(Aspect.TRAVEL, 2), 1, -10, 1, new ItemStack(ThaumicExploration.stabilizerBelt)).setParents(new String[]{"INFUSION", "TXINFUSION", "HOVERHARNESS", "TXHOVERHARNESS"}).setConcealed().registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("STABILIZERBELT")});
        research = new FauxResearchItem("TXDISTILESSENTIA", "TX", "DISTILESSENTIA", "ALCHEMY", 7, -6, ModResearch.getOriginalItem("DISTILESSENTIA", "ALCHEMY")).registerResearchItem();
        if (ThaumicExploration.allowReplication) {
            research = new TXResearchItem("REPLICATOR", "TX", new AspectList().add(Aspect.CRAFT, 10).add(Aspect.MECHANISM, 10).add(Aspect.ORDER, 6), 4, -5, 3, new ItemStack(ThaumicExploration.replicator)).setParents(new String[]{"DISTILESSENTIA", "TXINFUSION", "TXDISTILESSENTIA"}).setParentsHidden(new String[]{"INFUSION"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), new ResearchPage("2"), ModResearch.infusionPage("REPLICATOR")});
        }
        if (ThaumicExploration.allowCrucSouls) {
            research = new TXResearchItem("CRUCSOULS", "TX", new AspectList().add(Aspect.DEATH, 7).add(Aspect.HUNGER, 7).add(Aspect.SOUL, 8), 5, -7, 3, new ItemStack(ThaumicExploration.crucibleSouls)).setParents(new String[]{"DISTILESSENTIA", "TXINFUSION", "TXDISTILESSENTIA"}).setParentsHidden(new String[]{"BRAINCURE", "INFUSION"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), new ResearchPage("2"), ModResearch.infusionPage("CRUCSOULS")});
        }
        if (ThaumicExploration.allowFood) {
            research = new TXResearchItem("TALISMANFOOD", "TX", new AspectList().add(Aspect.HUNGER, 5).add(Aspect.FLESH, 4).add(Aspect.CROP, 4), -1, -11, 2, new ItemStack(ThaumicExploration.talismanFood)).setParents(new String[]{"TXINFUSION", "FLESHCURE"}).setParentsHidden(new String[]{"INFUSION"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("TALISMANFOOD")});
        }
        if (ThaumicExploration.allowBoots) {
            research = new FauxResearchItem("TXBOOTSTRAVELLER", "TX", "BOOTSTRAVELLER", "ARTIFICE", 1, -4, ModResearch.getOriginalItem("BOOTSTRAVELLER", "ARTIFICE")).setParents(new String[]{"TXINFUSION"}).registerResearchItem();
            research = new TXResearchItem("METEORBOOTS", "TX", new AspectList().add(Aspect.FIRE, 5).add(Aspect.ENERGY, 5).add(Aspect.TRAVEL, 10).add(Aspect.FLIGHT, 5), 2, -1, 2, new ItemStack(ThaumicExploration.bootsMeteor)).setParents(new String[]{"TXBOOTSTRAVELLER", "BOOTSTRAVELLER", "FOCUSFIRE"}).setParentsHidden(new String[]{"INFUSION"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("METEORBOOTS")});
            research = new TXResearchItem("COMETBOOTS", "TX", new AspectList().add(Aspect.WATER, 5).add(Aspect.COLD, 5).add(Aspect.TRAVEL, 10).add(Aspect.MOTION, 5), 5, -3, 2, new ItemStack(ThaumicExploration.bootsComet)).setParents(new String[]{"TXBOOTSTRAVELLER", "BOOTSTRAVELLER", "FOCUSFROST"}).setParentsHidden(new String[]{"INFUSION"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("COMETBOOTS")});
        }
        if (ThaumicExploration.allowSojourner || ThaumicExploration.allowMechanist) {
            research = new FauxResearchItem("TXCAP_thaumium", "TX", "CAP_thaumium", "THAUMATURGY", -7, -7, ModResearch.getOriginalItem("CAP_thaumium", "THAUMATURGY")).registerResearchItem();
        }
        if (ThaumicExploration.allowSojourner) {
            research = new TXResearchItem("CAP_SOJOURNER", "TX", new AspectList().add(Aspect.EXCHANGE, 5).add(Aspect.ENERGY, 5).add(Aspect.AURA, 8).add(Aspect.GREED, 5).add(Aspect.TOOL, 3), -5, -8, 2, new ItemStack(ThaumicExploration.sojournerCap)).setParents(new String[]{"TXCAP_thaumium", "CAP_thaumium"}).setParentsHidden(new String[]{"WANDPED"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.arcaneRecipePage("UNCHARGEDSOJOURNER"), ModResearch.infusionPage("CAP_SOJOURNER")});
        }
        if (ThaumicExploration.allowMechanist) {
            research = new TXResearchItem("CAP_MECHANIST", "TX", new AspectList().add(Aspect.MECHANISM, 5).add(Aspect.ENERGY, 5).add(Aspect.AURA, 8).add(Aspect.GREED, 5).add(Aspect.TOOL, 3), -9, -6, 2, new ItemStack(ThaumicExploration.mechanistCap)).setParents(new String[]{"TXCAP_thaumium", "CAP_thaumium"}).setParentsHidden(new String[]{"NODETAPPER2"}).setConcealed().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.arcaneRecipePage("UNCHARGEDMECHANIST"), ModResearch.infusionPage("CAP_MECHANIST")});
        }
        ResourceLocation taint = new ResourceLocation("thaumicexploration:textures/tabs/taintResearch2.png");
        research = new TXResearchItem("TAINTBASICS", "TX", new AspectList(), -9, 0, 0, taint).setAutoUnlock().setStub().setRound().registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), new ResearchPage("2")});
        if (ThaumicExploration.allowTainturgy) {
            research = new TXResearchItem("DREAMCATCHER", "TX", new AspectList().add(Aspect.MIND, 5).add(Aspect.TOOL, 5).add(Aspect.TAINT, 6), -7, 2, 2, new ItemStack(ThaumicExploration.charmNoTaint)).setParents(new String[]{"TAINTBASICS"}).setHidden().setAspectTriggers(new Aspect[]{Aspect.TAINT}).registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.arcaneRecipePage("DREAMCATCHER")});
        }
        research = new TXResearchItem("TENTACLERING", "TX", new AspectList().add(Aspect.TAINT, 8).add(Aspect.WEAPON, 4).add(Aspect.ARMOR, 4), -11, 2, 2, new ItemStack(ThaumicExploration.tentacleRing)).setParents(new String[]{"TAINTBASICS"}).setHidden().setEntityTriggers(new String[]{"Thaumcraft.Taintacle"}).registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("TENTACLERING")});
        if (ThaumicExploration.allowEnchants) {
            research = new FauxResearchItem("TXINFUSIONENCHANTMENT", "TX", "INFUSIONENCHANTMENT", "ARTIFICE", -5, -6, ModResearch.getOriginalGraphic("INFUSIONENCHANTMENT", "ARTIFICE")).registerResearchItem();
            research = new TXResearchItem("ENCHBINDING", "TX", new AspectList().add(Aspect.TRAP, 6).add(Aspect.ENTROPY, 5).add(Aspect.TRAVEL, 3), -7, -4, 1, new ResourceLocation("thaumicexploration:textures/tabs/binding.png")).setParents(new String[]{"TXINFUSIONENCHANTMENT", "INFUSIONENCHANTMENT"}).setConcealed().setSecondary().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionEnchantPage("ENCHBINDING")});
            research = new TXResearchItem("ENCHNIGHTVISION", "TX", new AspectList().add(Aspect.SENSES, 6).add(Aspect.DARKNESS, 2).add(Aspect.LIGHT, 6), -5, -4, 1, new ResourceLocation("thaumicexploration:textures/tabs/nightVision.png")).setParents(new String[]{"TXINFUSIONENCHANTMENT", "INFUSIONENCHANTMENT"}).setConcealed().setSecondary().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionEnchantPage("ENCHNIGHTVISION")});
            research = new TXResearchItem("ENCHDISARM", "TX", new AspectList().add(Aspect.SLIME, 6).add(Aspect.TRAP, 4).add(Aspect.WEAPON, 4), -3, -4, 1, new ResourceLocation("thaumicexploration:textures/tabs/disarm.png")).setParents(new String[]{"TXINFUSIONENCHANTMENT", "INFUSIONENCHANTMENT"}).setConcealed().setSecondary().registerResearchItem();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionEnchantPage("ENCHDISARM")});
        }
        research = new FauxResearchItem("TXROD_greatwood", "TX", "ROD_greatwood", "THAUMATURGY", -3, -2, ModResearch.getOriginalItem("ROD_greatwood", "THAUMATURGY")).registerResearchItem();
        research = new TXResearchItem("ROD_AMBER", "TX", new AspectList().add(Aspect.TOOL, 5).add(Aspect.AURA, 8).add(Aspect.TRAP, 5).add(Aspect.MAGIC, 8), -5, -1, 2, new ItemStack(ThaumicExploration.amberCore)).setParents(new String[]{"TXROD_greatwood", "ROD_obsidian", "ROD_reed", "ROD_blaze", "ROD_ice", "ROD_quartz", "ROD_bone"}).setConcealed().registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("ROD_AMBER")});
        research = new TXResearchItem("ROD_TRANSMUTATION", "TX", new AspectList().add(Aspect.TOOL, 8).add(Aspect.MAGIC, 8).add(Aspect.EXCHANGE, 12), -1, -1, 2, new ItemStack(ThaumicExploration.transmutationCore)).setParents(new String[]{"TXROD_greatwood", "ROD_greatwood"}).setConcealed().registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("ROD_TRANSMUTATION")});
        research = new TXResearchItem("ROD_AMBER_staff", "TX", new AspectList().add(Aspect.TOOL, 3).add(Aspect.AURA, 4).add(Aspect.TRAP, 3).add(Aspect.MAGIC, 4), -6, 0, 2, new ItemStack(ThaumicExploration.amberStaffCore)).setParents(new String[]{"TXROD_greatwood_staff", "ROD_AMBER"}).setParentsHidden(new String[]{"ROD_greatwood_staff"}).setConcealed().setSecondary().registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.arcaneRecipePage("ROD_AMBER_staff")});
        research = new TXResearchItem("ROD_TRANSMUTATION_staff", "TX", new AspectList().add(Aspect.TOOL, 4).add(Aspect.MAGIC, 4).add(Aspect.EXCHANGE, 6), 0, 0, 2, new ItemStack(ThaumicExploration.transmutationStaffCore)).setParents(new String[]{"TXROD_greatwood_staff", "ROD_TRANSMUTATION"}).setParentsHidden(new String[]{"ROD_greatwood_staff"}).setConcealed().setSecondary().registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.arcaneRecipePage("ROD_TRANSMUTATION_staff")});
        research = new FauxResearchItem("TXROD_greatwood_staff", "TX", "ROD_greatwood_staff", "THAUMATURGY", -3, 3, ModResearch.getOriginalItem("ROD_greatwood_staff", "THAUMATURGY")).registerResearchItem();
        research = new TXResearchItem("ROD_NECROMANCER_staff", "TX", new AspectList().add(Aspect.TOOL, 5).add(Aspect.ENTROPY, 5).add(Aspect.DEATH, 8).add(Aspect.AURA, 4).add(Aspect.SOUL, 5), -3, 1, 3, new ItemStack(ThaumicExploration.necroStaffCore)).setParents(new String[]{"ROD_greatwood_staff", "TXROD_greatwood_staff"}).setConcealed().registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.infusionPage("ROD_NECROMANCER_staff")});
        if (ThaumicExploration.breadWand) {
            // empty if block
        }
        ResourceLocation chestSeal = new ResourceLocation("thaumicexploration:textures/tabs/chestSeals.png");
        ResourceLocation jarSeal = new ResourceLocation("thaumicexploration:textures/tabs/jarSeals.png");
        if (ThaumicExploration.allowBoundInventories) {
            research = new TXResearchItem("JARSEAL", "TX", new AspectList().add(Aspect.ELDRITCH, 5).add(Aspect.TRAP, 8).add(Aspect.MAGIC, 5).add(Aspect.TRAVEL, 5), -7, -2, 1, jarSeal).setConcealed().registerResearchItem().setSecondary();
            research.setPages(new ResearchPage[]{new ResearchPage("1"), ModResearch.cruciblePage("JARSEAL")});
        }
        research = new FauxResearchItem("TXJARVOID", "TX", "JARVOID", "ALCHEMY", 7, -10, ModResearch.getOriginalItem("JARVOID", "ALCHEMY")).registerResearchItem();
        research = new TXResearchItem("TRASHJAR", "TX", new AspectList().add(Aspect.ENTROPY, 8).add(Aspect.VOID, 8).add(Aspect.HUNGER, 5).add(Aspect.ELDRITCH, 3), 5, -9, 2, new ItemStack(ThaumicExploration.trashJar)).setConcealed().setParents(new String[]{"TXJARVOID", "JARVOID", "INFUSION", "TXINFUSION"}).registerResearchItem();
        research.setPages(new ResearchPage[]{new ResearchPage("1"), new ResearchPage("2"), ModResearch.infusionPage("TRASHJAR")});
    }

    private static void registerResearchPages() {
        ResourceLocation background2 = new ResourceLocation("thaumcraft", "textures/gui/gui_researchback.png");
        ResearchCategories.registerCategory((String)"TX", (ResourceLocation)new ResourceLocation("thaumicexploration:textures/tabs/txTab.png"), (ResourceLocation)background2);
    }

    private static ResearchPage recipePage(String name) {
        return new ResearchPage((IRecipe)ConfigResearch.recipes.get(name));
    }

    private static ResearchPage cruciblePage(String name) {
        return new ResearchPage((CrucibleRecipe)ConfigResearch.recipes.get(name));
    }

    private static ResearchPage infusionPage(String name) {
        return new ResearchPage((InfusionRecipe)ConfigResearch.recipes.get(name));
    }

    private static ResearchPage infusionEnchantPage(String name) {
        return new ResearchPage((InfusionEnchantmentRecipe)ConfigResearch.recipes.get(name));
    }

    private static ResearchPage constructPage(String name) {
        return new ResearchPage((List)ConfigResearch.recipes.get(name));
    }

    private static ResearchPage arcaneRecipePage(String name) {
        return new ResearchPage((IArcaneRecipe)ConfigResearch.recipes.get(name));
    }
}

