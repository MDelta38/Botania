/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.FurnaceRecipes
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fluids.FluidRegistry
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.crafting.CrucibleRecipe
 *  thaumcraft.api.crafting.IArcaneRecipe
 *  thaumcraft.api.crafting.InfusionEnchantmentRecipe
 *  thaumcraft.api.crafting.InfusionRecipe
 *  thaumcraft.api.crafting.ShapedArcaneRecipe
 *  thaumcraft.api.crafting.ShapelessArcaneRecipe
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchItem
 *  thaumcraft.api.research.ResearchPage
 *  thaumcraft.api.wands.IWandTriggerManager
 *  thaumcraft.api.wands.WandTriggerRegistry
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.config.ConfigItems
 *  thaumcraft.common.lib.utils.Utils
 */
package witchinggadgets.common;

import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.CrucibleRecipe;
import thaumcraft.api.crafting.IArcaneRecipe;
import thaumcraft.api.crafting.InfusionEnchantmentRecipe;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import thaumcraft.api.crafting.ShapelessArcaneRecipe;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchItem;
import thaumcraft.api.research.ResearchPage;
import thaumcraft.api.wands.IWandTriggerManager;
import thaumcraft.api.wands.WandTriggerRegistry;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.lib.utils.Utils;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.blocks.tiles.TileEntityBlastfurnace;
import witchinggadgets.common.items.ItemClusters;
import witchinggadgets.common.items.baubles.ItemCloak;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;
import witchinggadgets.common.util.recipe.PhotoDevelopingRecipe;
import witchinggadgets.common.util.recipe.SpinningRecipe;
import witchinggadgets.common.util.registry.MetalFluidData;
import witchinggadgets.common.util.research.WGFakeResearchItem;
import witchinggadgets.common.util.research.WGResearchItem;

public class WGResearch {
    public static HashMap<String, Object> recipeList = new HashMap();
    public static final ResourceLocation[] wgbackgrounds = new ResourceLocation[]{new ResourceLocation("witchinggadgets:textures/gui/research/WGResearchBack.png"), new ResourceLocation("witchinggadgets:textures/gui/research/WGResearchBackAwoken.png")};

    public static void setupResearchPages() {
        ResearchCategories.registerCategory((String)"WITCHGADG", (ResourceLocation)new ResourceLocation("witchinggadgets:textures/gui/research/WGIcon.png"), (ResourceLocation)wgbackgrounds[0]);
    }

    public static void registerRecipes() {
        AspectList infusionAspects;
        ItemStack standardCloak = new ItemStack(WGContent.ItemCloak, 1, 0);
        WGResearch.registerShapedOreRecipe("GEMCUTTING", "_TOOLS", new ItemStack(WGContent.ItemMaterial, 1, 8), "qfi", "sss", Character.valueOf('q'), "gemQuartz", Character.valueOf('f'), Items.field_151145_ak, Character.valueOf('i'), "ingotIron", Character.valueOf('s'), "stickWood");
        if (Config.allowMirrors) {
            infusionAspects = new AspectList().add(Aspect.VOID, 20).add(Aspect.TRAVEL, 20).add(Aspect.ELDRITCH, 20).add(Aspect.CRYSTAL, 20);
            WGResearch.registerInfusionRecipe("WALLMIRROR", "", new ItemStack(WGContent.BlockWallMirror), 8, infusionAspects, new ItemStack(ConfigBlocks.blockMirror), new ItemStack[]{new ItemStack(ConfigItems.itemFocusPortableHole), new ItemStack(ConfigItems.itemShard, 1, 5), new ItemStack(Items.field_151079_bi), new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151043_k), new ItemStack(Blocks.field_150371_ca, 1, 1)});
        }
        AspectList craftingAspects = new AspectList().add(Aspect.AIR, 20).add(Aspect.EARTH, 20).add(Aspect.ORDER, 10);
        WGResearch.registerArcaneRecipe("SCANCAMERA", "", new ItemStack(WGContent.ItemScanCamera), craftingAspects, "wl ", "pmt", "wl ", Character.valueOf('t'), ConfigItems.itemThaumometer, Character.valueOf('m'), new ItemStack(ConfigItems.itemResource, 1, 10), Character.valueOf('p'), Blocks.field_150410_aZ, Character.valueOf('w'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('l'), Items.field_151116_aA);
        PhotoDevelopingRecipe developingRecipe = new PhotoDevelopingRecipe();
        ThaumcraftApi.getCraftingRecipes().add(developingRecipe);
        recipeList.put("SCANCAMERA_DEVELOP", (Object)developingRecipe);
        WGResearch.registerShapelessOreRecipe("SCANCAMERA", "_CLEARPLATE", new ItemStack(ConfigItems.itemResource, 1, 10), new ItemStack(WGContent.ItemMaterial, 1, 9));
        craftingAspects = new AspectList().add(Aspect.ORDER, 10);
        WGResearch.registerArcaneRecipe("CALCULATOR", "", new ItemStack(WGContent.ItemMaterial, 1, 7), craftingAspects, "srs", "sbs", "sgs", Character.valueOf('s'), "stickWood", Character.valueOf('r'), "dyeRed", Character.valueOf('b'), "dyeBlue", Character.valueOf('g'), "dyeGreen");
        craftingAspects = new AspectList().add(Aspect.AIR, 20).add(Aspect.ENTROPY, 15).add(Aspect.ORDER, 10);
        WGResearch.registerArcaneRecipe("CLOAK_STORAGE", "", new ItemStack(WGContent.ItemCloak, 1, 2), craftingAspects, "SCS", " B ", Character.valueOf('C'), "travelgearCloakBase", Character.valueOf('S'), new ItemStack(WGContent.ItemMaterial, 1, 3), Character.valueOf('B'), new ItemStack(WGContent.ItemBag));
        craftingAspects = new AspectList().add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 20).add(Aspect.EARTH, 15);
        WGResearch.registerArcaneRecipe("CLOAK_WOLF", "", new ItemStack(WGContent.ItemCloak, 1, 3), craftingAspects, " W ", "WCW", Character.valueOf('C'), "travelgearCloakBase", Character.valueOf('W'), new ItemStack(WGContent.ItemMaterial, 1, 6));
        if (WGModCompat.tfRavensFeather != null) {
            craftingAspects = new AspectList().add(Aspect.AIR, 15).add(Aspect.ORDER, 15);
            WGResearch.registerArcaneRecipe("CLOAK_RAVEN", "", new ItemStack(WGContent.ItemCloak, 1, 4), craftingAspects, " F ", "FCF", "FSF", Character.valueOf('C'), "travelgearCloakBase", Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 0), Character.valueOf('F'), new ItemStack(WGModCompat.tfRavensFeather));
        }
        craftingAspects = new AspectList().add(Aspect.ORDER, 5).add(Aspect.EARTH, 3);
        WGResearch.registerArcaneRecipe("ETHEREALWALL", "", new ItemStack(WGContent.BlockStoneDevice, 6, 0), craftingAspects, "SsS", "STS", "S S", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), Character.valueOf('s'), new ItemStack(ConfigItems.itemShard, 1, Short.MAX_VALUE), Character.valueOf('T'), new ItemStack(Blocks.field_150429_aA));
        craftingAspects = new AspectList().add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10);
        WGResearch.registerArcaneRecipe("AGEINGSTONE", "", new ItemStack(WGContent.BlockStoneDevice, 1, 7), craftingAspects, " s ", "SCS", " s ", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), Character.valueOf('s'), new ItemStack(ConfigItems.itemShard, 1, Short.MAX_VALUE), Character.valueOf('C'), new ItemStack(Items.field_151113_aN));
        craftingAspects = new AspectList().add(Aspect.ENTROPY, 4).add(Aspect.EARTH, 8);
        WGResearch.registerArcaneRecipe("STONEEXTRUDER", "", new ItemStack(WGContent.BlockWoodenDevice, 1, 2), craftingAspects, " P ", "WSL", "wSw", Character.valueOf('S'), new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6), Character.valueOf('w'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('W'), new ItemStack(Items.field_151131_as), Character.valueOf('L'), new ItemStack(Items.field_151129_at), Character.valueOf('P'), new ItemStack(ConfigItems.itemPickThaumium));
        craftingAspects = new AspectList().add(Aspect.ORDER, 5).add(Aspect.AIR, 5);
        WGResearch.registerArcaneRecipe("SPINNINGWHEEL", "", new ItemStack(WGContent.BlockWoodenDevice), craftingAspects, "I W", " T ", Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTable), Character.valueOf('I'), new ItemStack(Items.field_151042_j), Character.valueOf('W'), "plankWood");
        craftingAspects = new AspectList().add(Aspect.FIRE, 20).add(Aspect.WATER, 10).add(Aspect.ORDER, 10);
        WGResearch.registerArcaneRecipe("SAUNASTOVE", "", new ItemStack(WGContent.BlockWoodenDevice, 1, 4), craftingAspects, "SCS", "WBW", "WWW", Character.valueOf('S'), new ItemStack((Block)Blocks.field_150333_U), Character.valueOf('C'), "blockCoal", Character.valueOf('W'), new ItemStack(ConfigBlocks.blockWoodenDevice, 1, 6), Character.valueOf('B'), new ItemStack(Items.field_151133_ar));
        craftingAspects = new AspectList().add(Aspect.ORDER, 30);
        WGResearch.registerArcaneRecipe("LABELLIB", "", new ItemStack(WGContent.BlockWoodenDevice, 1, 5), craftingAspects, "BLW", " T ", Character.valueOf('B'), new ItemStack(Items.field_151122_aG), Character.valueOf('L'), new ItemStack(ConfigItems.itemResource, 1, 13), Character.valueOf('W'), new ItemStack(ConfigItems.itemInkwell), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTable));
        if (Config.allowMirrors) {
            craftingAspects = new AspectList().add(Aspect.AIR, 10).add(Aspect.WATER, 10).add(Aspect.ORDER, 10);
            WGResearch.registerArcaneRecipe("MIRRORPUMP", "", new ItemStack(WGContent.BlockMetalDevice, 1, 0), craftingAspects, " B ", "HCT", " B ", Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube), Character.valueOf('B'), new ItemStack(ConfigBlocks.blockWoodenDevice), Character.valueOf('C'), new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), Character.valueOf('H'), new ItemStack((Block)Blocks.field_150438_bZ));
        }
        craftingAspects = new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.AIR, 5);
        WGResearch.registerShapelessArcaneRecipe("BAGOFTRICKS", "_CLOTH", new ItemStack(WGContent.ItemMaterial, 2, 3), craftingAspects, new ItemStack(WGContent.ItemMaterial, 1, 0), new ItemStack(WGContent.ItemMaterial, 1, 0), new ItemStack(WGContent.ItemMaterial, 1, 0), new ItemStack(WGContent.ItemMaterial, 1, 2));
        craftingAspects = new AspectList().add(Aspect.ORDER, 20).add(Aspect.AIR, 20);
        WGResearch.registerArcaneRecipe("BAGOFTRICKS", "_BAG", new ItemStack(WGContent.ItemBag), craftingAspects, "C C", "C C", "CCC", Character.valueOf('C'), new ItemStack(WGContent.ItemMaterial, 1, 3));
        craftingAspects = new AspectList().add(Aspect.ORDER, 10).add(Aspect.AIR, 10);
        WGResearch.registerArcaneRecipe("HUNGERBAG", "", new ItemStack(WGContent.ItemBag, 1, 3), craftingAspects, " H ", "CBC", Character.valueOf('C'), new ItemStack(WGContent.ItemMaterial, 1, 3), Character.valueOf('H'), new ItemStack(ConfigBlocks.blockChestHungry), Character.valueOf('B'), new ItemStack(WGContent.ItemBag));
        craftingAspects = new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.ORDER, 5);
        WGResearch.registerShapelessArcaneRecipe("ADVANCEDROBES", "_CLOTH", new ItemStack(WGContent.ItemMaterial, 1, 5), craftingAspects, new ItemStack(WGContent.ItemMaterial, 1, 0), new ItemStack(WGContent.ItemMaterial, 1, 2), new ItemStack(WGContent.ItemMaterial, 1, 2), new ItemStack(WGContent.ItemMaterial, 1, 1));
        craftingAspects = new AspectList().add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10);
        WGResearch.registerArcaneRecipe("ADVANCEDROBES", "_CHEST", new ItemStack(WGContent.ItemAdvancedRobeChest), craftingAspects, " C ", "CRC", Character.valueOf('C'), new ItemStack(WGContent.ItemMaterial, 1, 5), Character.valueOf('R'), new ItemStack(ConfigItems.itemChestRobe));
        craftingAspects = new AspectList().add(Aspect.ORDER, 10).add(Aspect.ENTROPY, 10);
        WGResearch.registerArcaneRecipe("ADVANCEDROBES", "_LEGS", new ItemStack(WGContent.ItemAdvancedRobeLegs), craftingAspects, " C ", "CRC", Character.valueOf('C'), new ItemStack(WGContent.ItemMaterial, 1, 5), Character.valueOf('R'), new ItemStack(ConfigItems.itemLegsRobe));
        craftingAspects = new AspectList().add(Aspect.AIR, 7);
        WGResearch.registerArcaneRecipe("CLOAK", "", standardCloak, craftingAspects, " F ", "FFF", "FFF", Character.valueOf('F'), new ItemStack(ConfigItems.itemResource, 1, 7));
        craftingAspects = new AspectList().add(Aspect.ENTROPY, 10).add(Aspect.FIRE, 10);
        WGResearch.registerArcaneRecipe("WGBAUBLES", "_WOLFVAMBRACES", new ItemStack(WGContent.ItemMagicalBaubles, 1, 2), craftingAspects, " P ", "PVP", Character.valueOf('P'), new ItemStack(WGContent.ItemMaterial, 1, 6), Character.valueOf('V'), "travelgearVambraceBase");
        craftingAspects = new AspectList().add(Aspect.ORDER, 10).add(Aspect.EARTH, 10);
        WGResearch.registerArcaneRecipe("WGBAUBLES", "_KNOCKBACKSHOULDERS", new ItemStack(WGContent.ItemMagicalBaubles, 1, 1), craftingAspects, " S ", "ETE", Character.valueOf('E'), new ItemStack(ConfigItems.itemShard, 1, 3), Character.valueOf('S'), "travelgearShoulderBase", Character.valueOf('T'), "ingotThaumium");
        ItemStack luckyCoin = new ItemStack(ConfigItems.itemResource, 1, 18);
        luckyCoin.func_77966_a(Enchantment.field_77346_s, 1);
        luckyCoin.func_77966_a(Enchantment.field_77335_o, 1);
        craftingAspects = new AspectList().add(Aspect.ORDER, 30);
        WGResearch.registerArcaneRecipe("WGBAUBLES", "_COIN", luckyCoin, craftingAspects, "BCB", "CCC", "BCB", Character.valueOf('C'), new ItemStack(ConfigItems.itemResource, 1, 18), Character.valueOf('B'), Items.field_151134_bR);
        craftingAspects = new AspectList().add(Aspect.AIR, 5).add(Aspect.ORDER, 5);
        for (int cm = 0; cm < ItemCloak.subNames.length; ++cm) {
            WGResearch.registerArcaneRecipe("CLOAKKAMA", "_" + cm, new ItemStack(WGContent.ItemKama, 1, cm), craftingAspects, "B", "C", Character.valueOf('B'), "baubleBeltBase", Character.valueOf('C'), new ItemStack(WGContent.ItemCloak, 1, cm));
        }
        craftingAspects = new AspectList().add(Aspect.EARTH, 10);
        WGResearch.registerArcaneRecipe("TERRAFORMER", "_PLAINS", new ItemStack(WGContent.BlockMetalDevice, 1, 2), craftingAspects, " S ", "IBI", "ITI", Character.valueOf('B'), new ItemStack((Block)Blocks.field_150349_c), Character.valueOf('I'), "ingotIron", Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube));
        craftingAspects = new AspectList().add(Aspect.WATER, 10).add(Aspect.ORDER, 10);
        WGResearch.registerArcaneRecipe("TERRAFORMFOCUS_COLDTAIGA", "", new ItemStack(WGContent.BlockMetalDevice, 1, 3), craftingAspects, " S ", "IBI", "ITI", Character.valueOf('B'), new ItemStack(Blocks.field_150432_aD), Character.valueOf('I'), "ingotIron", Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube));
        craftingAspects = new AspectList().add(Aspect.FIRE, 10).add(Aspect.EARTH, 10);
        WGResearch.registerArcaneRecipe("TERRAFORMFOCUS_DESERT", "", new ItemStack(WGContent.BlockMetalDevice, 1, 4), craftingAspects, " S ", "IBI", "ITI", Character.valueOf('B'), new ItemStack((Block)Blocks.field_150354_m), Character.valueOf('I'), "ingotIron", Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube));
        craftingAspects = new AspectList().add(Aspect.WATER, 10).add(Aspect.EARTH, 10);
        WGResearch.registerArcaneRecipe("TERRAFORMFOCUS_JUNGLE", "", new ItemStack(WGContent.BlockMetalDevice, 1, 5), craftingAspects, " S ", "IBI", "ITI", Character.valueOf('B'), new ItemStack(Blocks.field_150364_r, 1, 3), Character.valueOf('I'), "ingotIron", Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube));
        craftingAspects = new AspectList().add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 10);
        WGResearch.registerArcaneRecipe("TERRAFORMFOCUS_HELL", "", new ItemStack(WGContent.BlockMetalDevice, 1, 6), craftingAspects, " S ", "IBI", "ITI", Character.valueOf('B'), new ItemStack(Blocks.field_150385_bj), Character.valueOf('I'), "ingotIron", Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube));
        ThaumcraftApi.addWarpToItem((ItemStack)new ItemStack(WGContent.BlockMetalDevice, 1, 6), (int)1);
        craftingAspects = new AspectList().add(Aspect.EARTH, 10).add(Aspect.WATER, 10);
        WGResearch.registerArcaneRecipe("TERRAFORMFOCUS_MUSHROOM", "", new ItemStack(WGContent.BlockMetalDevice, 1, 9), craftingAspects, " S ", "IBI", "ITI", Character.valueOf('B'), new ItemStack((Block)Blocks.field_150391_bh), Character.valueOf('I'), "ingotIron", Character.valueOf('S'), new ItemStack(ConfigItems.itemShard, 1, 6), Character.valueOf('T'), new ItemStack(ConfigBlocks.blockTube));
        infusionAspects = new AspectList().add(Aspect.SOUL, 8).add(Aspect.TRAVEL, 8).add(Aspect.ELDRITCH, 4).add(Aspect.SENSES, 4);
        WGResearch.registerInfusionRecipe("CLOAK_SPECTRAL", "", new ItemStack(WGContent.ItemCloak, 1, 1), 3, infusionAspects, new ItemStack(WGContent.ItemCloak), new ItemStack[]{new ItemStack((Item)Items.field_151068_bn, 1, 8270), new ItemStack(WGContent.ItemMaterial, 1, 5), new ItemStack(Items.field_151079_bi), new ItemStack(WGContent.ItemMaterial, 1, 5)});
        infusionAspects = new AspectList().add(Aspect.MINE, 8).add(Aspect.TOOL, 4).add(Aspect.MOTION, 4).add(Aspect.AIR, 8);
        WGResearch.registerInfusionRecipe("WGBAUBLES", "_HASTEVAMBRACES", new ItemStack(WGContent.ItemMagicalBaubles, 1, 3), 2, infusionAspects, (ItemStack)OreDictionary.getOres((String)"travelgearVambraceBase").get(0), new ItemStack[]{new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151102_aT), new ItemStack((Item)Items.field_151068_bn, 1, 8194), new ItemStack(Items.field_151102_aT)});
        ItemStack stack_ingot = !OreDictionary.getOres((String)"ingotSilver").isEmpty() ? (ItemStack)OreDictionary.getOres((String)"ingotSilver").get(0) : new ItemStack(Items.field_151042_j);
        infusionAspects = new AspectList().add(Aspect.FLIGHT, 16).add(Aspect.MOTION, 8).add(Aspect.AIR, 16);
        WGResearch.registerInfusionRecipe("WGBAUBLES", "_DOUBLEJUMPSHOULDERS", new ItemStack(WGContent.ItemMagicalBaubles, 1, 0), 2, infusionAspects, (ItemStack)OreDictionary.getOres((String)"travelgearShoulderBase").get(0), new ItemStack[]{new ItemStack(Items.field_151008_G), stack_ingot, new ItemStack(Items.field_151008_G), new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(Items.field_151008_G), stack_ingot});
        infusionAspects = new AspectList().add(Aspect.AIR, 16).add(Aspect.WEAPON, 8).add(Aspect.ORDER, 8);
        WGResearch.registerInfusionRecipe("WGBAUBLES", "_SNIPERRING", new ItemStack(WGContent.ItemMagicalBaubles, 1, 6), 2, infusionAspects, new ItemStack(ConfigItems.itemBaubleBlanks, 1, 1), new ItemStack[]{new ItemStack(ConfigItems.itemPrimalArrow, 1, 0), new ItemStack(ConfigItems.itemPrimalArrow, 1, 1), new ItemStack(ConfigItems.itemPrimalArrow, 1, 2), new ItemStack(ConfigItems.itemPrimalArrow, 1, 3), new ItemStack(ConfigItems.itemPrimalArrow, 1, 4), new ItemStack(ConfigItems.itemPrimalArrow, 1, 5)});
        infusionAspects = new AspectList().add(Aspect.GREED, 32).add(Aspect.TOOL, 16);
        WGResearch.registerInfusionRecipe("WGBAUBLES", "_LUCKRING", new ItemStack(WGContent.ItemMagicalBaubles, 1, 5), 3, infusionAspects, luckyCoin, new ItemStack[]{new ItemStack(Items.field_151043_k), new ItemStack(Items.field_151100_aR, 1, 4), stack_ingot, new ItemStack(Items.field_151100_aR, 1, 4), stack_ingot, new ItemStack(Items.field_151100_aR, 1, 4), stack_ingot, new ItemStack(Items.field_151100_aR, 1, 4)});
        infusionAspects = new AspectList().add(Aspect.TRAVEL, 4).add(Aspect.MIND, 6).add(Aspect.TOOL, 2);
        WGResearch.registerInfusionRecipe("LABYRINTHSTRING", "", new ItemStack(WGContent.ItemMaterial, 1, 11), 2, infusionAspects, new ItemStack(ConfigBlocks.blockMagicalLog, 1, 0), new ItemStack[]{new ItemStack(Items.field_151079_bi), new ItemStack(WGContent.ItemMaterial, 1, 0), new ItemStack(WGContent.ItemMaterial, 1, 0), new ItemStack(WGContent.ItemMaterial, 1, 0)});
        infusionAspects = new AspectList().add(Aspect.ORDER, 16).add(Aspect.EXCHANGE, 8).add(Aspect.EARTH, 16);
        WGResearch.registerInfusionRecipe("TERRAFORMER", "", new ItemStack(WGContent.BlockMetalDevice, 1, 1), 3, infusionAspects, new ItemStack(ConfigBlocks.blockMetalDevice, 1, 9), new ItemStack[]{new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(Items.field_151042_j), new ItemStack(ConfigBlocks.blockTube), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 1), new ItemStack(ConfigBlocks.blockTube), new ItemStack(Items.field_151042_j)});
        infusionAspects = new AspectList().add(Aspect.TAINT, 32).add(Aspect.EXCHANGE, 8);
        WGResearch.registerInfusionRecipe("TERRAFORMFOCUS_TAINT", "", new ItemStack(WGContent.BlockMetalDevice, 1, 8), 3, infusionAspects, new ItemStack(ConfigBlocks.blockTaint, 1, 0), new ItemStack[]{new ItemStack(ConfigItems.itemShard, 1, 6), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151042_j), new ItemStack(ConfigBlocks.blockTube, 1, 0), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151042_j)});
        ThaumcraftApi.addWarpToItem((ItemStack)new ItemStack(WGContent.BlockMetalDevice, 1, 8), (int)2);
        infusionAspects = new AspectList().add(Aspect.VOID, 8).add(Aspect.ELDRITCH, 4).add(Aspect.MAGIC, 4);
        WGResearch.registerInfusionRecipe("ENDERBAG", "", new ItemStack(WGContent.ItemBag, 1, 2), 3, infusionAspects, new ItemStack(WGContent.ItemBag, 1, 0), new ItemStack[]{new ItemStack(Blocks.field_150477_bB), new ItemStack(WGContent.ItemMaterial, 1, 5), new ItemStack(Items.field_151061_bv), new ItemStack(WGContent.ItemMaterial, 1, 5)});
        infusionAspects = new AspectList().add(Aspect.VOID, 16).add(Aspect.ELDRITCH, 16).add(Aspect.ENTROPY, 32);
        WGResearch.registerInfusionRecipe("VOIDBAG", "", new ItemStack(WGContent.ItemBag, 1, 1), 4, infusionAspects, new ItemStack(WGContent.ItemBag, 1, 0), new ItemStack[]{new ItemStack(ConfigItems.itemResource, 1, 17), new ItemStack(WGContent.ItemMaterial, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 17), new ItemStack(WGContent.ItemMaterial, 1, 3)});
        infusionAspects = new AspectList().add(Aspect.AIR, 16).add(Aspect.FIRE, 16).add(Aspect.EARTH, 16).add(Aspect.WATER, 16).add(Aspect.ORDER, 16).add(Aspect.ENTROPY, 16);
        WGResearch.registerInfusionRecipe("EMPOWERPEARL", "", new ItemStack(ConfigItems.itemEldritchObject, 1, 3), 3, infusionAspects, new ItemStack(WGContent.ItemMaterial, 1, 12), new ItemStack[]{new ItemStack(ConfigItems.itemShard, 1, 0), new ItemStack(ConfigItems.itemShard, 1, 1), new ItemStack(ConfigItems.itemShard, 1, 2), new ItemStack(ConfigItems.itemShard, 1, 3), new ItemStack(ConfigItems.itemShard, 1, 4), new ItemStack(ConfigItems.itemShard, 1, 5)});
        infusionAspects = new AspectList().add(Aspect.MAGIC, 32).add(Aspect.CRYSTAL, 16).add(Aspect.TOOL, 8).add(Aspect.AIR, 8).add(Aspect.FIRE, 8).add(Aspect.WATER, 8).add(Aspect.EARTH, 8).add(Aspect.ORDER, 8).add(Aspect.ENTROPY, 8);
        WGResearch.registerInfusionRecipe("PRIMORDIALGLOVE", "", new ItemStack(WGContent.ItemPrimordialGlove), 6, infusionAspects, new ItemStack(ConfigBlocks.blockStoneDevice, 1, 11), new ItemStack[]{new ItemStack(WGContent.ItemMaterial, 1, 5), new ItemStack(ConfigItems.itemResource, 1, 17), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemResource, 1, 17)});
        infusionAspects = new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.WEAPON, 64).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32);
        WGResearch.registerInfusionRecipe("PRIMORDIALWEAPONRY", "_CLAYMORE", new ItemStack(WGContent.ItemPrimordialSword), 10, infusionAspects, new ItemStack(WGContent.BlockMetalDevice, 1, 7), new ItemStack[]{new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWandRod, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid)});
        infusionAspects = new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.TOOL, 32).add(Aspect.WEAPON, 32).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32);
        WGResearch.registerInfusionRecipe("PRIMORDIALWEAPONRY", "_HAMMER", new ItemStack(WGContent.ItemPrimordialHammer), 10, infusionAspects, new ItemStack(WGContent.BlockMetalDevice, 1, 7), new ItemStack[]{new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemPickVoid), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWandRod, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemSwordVoid)});
        infusionAspects = new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.TOOL, 32).add(Aspect.WEAPON, 32).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32);
        WGResearch.registerInfusionRecipe("PRIMORDIALWEAPONRY", "_GREATAXE", new ItemStack(WGContent.ItemPrimordialAxe), 10, infusionAspects, new ItemStack(WGContent.BlockMetalDevice, 1, 7), new ItemStack[]{new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemAxeVoid), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWandRod, 1, 0), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemAxeVoid)});
        infusionAspects = new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.ARMOR, 32).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32);
        WGResearch.registerInfusionRecipe("PRIMORDIALARMOR", "_HELMET", new ItemStack(WGContent.ItemPrimordialHelm), 10, infusionAspects, new ItemStack(WGContent.BlockMetalDevice, 1, 7), new ItemStack[]{new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemHelmetFortress), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence)});
        infusionAspects = new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.ARMOR, 32).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32);
        WGResearch.registerInfusionRecipe("PRIMORDIALARMOR", "_CUIRASS", new ItemStack(WGContent.ItemPrimordialChest), 10, infusionAspects, new ItemStack(WGContent.BlockMetalDevice, 1, 7), new ItemStack[]{new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemChestFortress), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence)});
        infusionAspects = new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.ARMOR, 32).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32);
        WGResearch.registerInfusionRecipe("PRIMORDIALARMOR", "_GREAVES", new ItemStack(WGContent.ItemPrimordialLegs), 10, infusionAspects, new ItemStack(WGContent.BlockMetalDevice, 1, 7), new ItemStack[]{new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemLegsFortress), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence)});
        infusionAspects = new AspectList().add(Aspect.MAGIC, 64).add(Aspect.METAL, 128).add(Aspect.ARMOR, 32).add(Aspect.AIR, 32).add(Aspect.FIRE, 32).add(Aspect.WATER, 32).add(Aspect.EARTH, 32).add(Aspect.ORDER, 32).add(Aspect.ENTROPY, 32);
        WGResearch.registerInfusionRecipe("PRIMORDIALARMOR", "_BOOTS", new ItemStack(WGContent.ItemPrimordialBoots), 10, infusionAspects, new ItemStack(WGContent.BlockMetalDevice, 1, 7), new ItemStack[]{new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemBootsTraveller), new ItemStack(ConfigItems.itemResource, 1, 16), new ItemStack(ConfigItems.itemEldritchObject, 1, 3), new ItemStack(ConfigItems.itemWispEssence), new ItemStack(ConfigItems.itemResource, 1, 15), new ItemStack(ConfigItems.itemWispEssence)});
        infusionAspects = new AspectList().add(Aspect.SENSES, 32).add(Aspect.AURA, 16).add(Aspect.ARMOR, 16);
        WGResearch.registerInfusionRecipe("HELMGOGGLES", "_PRIMORDIAL", new Object[]{"goggles", new NBTTagByte(1)}, 5, infusionAspects, new ItemStack(WGContent.ItemPrimordialHelm, 1, Short.MAX_VALUE), new ItemStack[]{new ItemStack(Items.field_151123_aH), new ItemStack(ConfigItems.itemGoggles, 1, Short.MAX_VALUE)});
        infusionAspects = new AspectList().add(Aspect.MIND, 64).add(Aspect.HEAL, 64).add(Aspect.ARMOR, 16);
        WGResearch.registerInfusionRecipe("MASKGRINNINGDEVIL", "_PRIMORDIAL", new Object[]{"mask", new NBTTagByte(0)}, 8, infusionAspects, new ItemStack(WGContent.ItemPrimordialHelm, 1, Short.MAX_VALUE), new ItemStack[]{new ItemStack(Items.field_151100_aR, 1, 0), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151116_aA), new ItemStack(ConfigBlocks.blockCustomPlant, 1, 2), new ItemStack(ConfigItems.itemZombieBrain), new ItemStack(Items.field_151042_j)});
        infusionAspects = new AspectList().add(Aspect.ENTROPY, 64).add(Aspect.DEATH, 64).add(Aspect.ARMOR, 16);
        WGResearch.registerInfusionRecipe("MASKANGRYGHOST", "_PRIMORDIAL", new Object[]{"mask", new NBTTagByte(1)}, 8, infusionAspects, new ItemStack(WGContent.ItemPrimordialHelm, 1, Short.MAX_VALUE), new ItemStack[]{new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151170_bI), new ItemStack(Items.field_151144_bL, 1, 1), new ItemStack(Items.field_151042_j)});
        infusionAspects = new AspectList().add(Aspect.UNDEAD, 64).add(Aspect.LIFE, 64).add(Aspect.ARMOR, 16);
        WGResearch.registerInfusionRecipe("MASKSIPPINGFIEND", "_PRIMORDIAL", new Object[]{"mask", new NBTTagByte(2)}, 8, infusionAspects, new ItemStack(WGContent.ItemPrimordialHelm, 1, Short.MAX_VALUE), new ItemStack[]{new ItemStack(Items.field_151100_aR, 1, 1), new ItemStack(Items.field_151042_j), new ItemStack(Items.field_151116_aA), new ItemStack(Items.field_151073_bk), new ItemStack(Items.field_151117_aB), new ItemStack(Items.field_151042_j)});
        infusionAspects = new AspectList().add(Aspect.DARKNESS, 4).add(Aspect.CRYSTAL, 8).add(Aspect.MAGIC, 8);
        WGResearch.registerInfusionEnchantmentRecipe("ENCH_INVISIBLEGEAR", "", WGContent.enc_invisibleGear, 2, infusionAspects, new ItemStack[]{new ItemStack(Items.field_151128_bU), new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack(WGContent.ItemMaterial, 1, 13)});
        WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_invisibleGear, "witchinggadgets:textures/gui/research/icon_ench_invisGear.png", new AspectList().add(Aspect.AIR, 25).add(Aspect.ORDER, 20).add(Aspect.ENTROPY, 15), "ENCH_INVISIBLEGEAR");
        infusionAspects = new AspectList().add(Aspect.LIGHT, 4).add(Aspect.SENSES, 8).add(Aspect.MAGIC, 8);
        WGResearch.registerInfusionEnchantmentRecipe("ENCH_UNVEILING", "", WGContent.enc_unveiling, 2, infusionAspects, new ItemStack[]{new ItemStack(Items.field_151150_bK), new ItemStack(ConfigItems.itemResource, 1, 14)});
        WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_unveiling, "witchinggadgets:textures/gui/research/icon_ench_unveiling.png", new AspectList().add(Aspect.AIR, 25).add(Aspect.ORDER, 20).add(Aspect.WATER, 10), "ENCH_UNVEILING");
        infusionAspects = new AspectList().add(Aspect.MOTION, 6).add(Aspect.DARKNESS, 8).add(Aspect.MAGIC, 8);
        WGResearch.registerInfusionEnchantmentRecipe("ENCH_STEALTH", "", WGContent.enc_stealth, 2, infusionAspects, new ItemStack[]{new ItemStack((Item)Items.field_151068_bn, 1, 8206), new ItemStack(ConfigItems.itemResource, 1, 14)});
        WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_stealth, "witchinggadgets:textures/gui/research/icon_ench_stealth.png", new AspectList().add(Aspect.AIR, 10).add(Aspect.ORDER, 20).add(Aspect.EARTH, 10), "ENCH_STEALTH");
        infusionAspects = new AspectList().add(Aspect.WEAPON, 12).add(Aspect.DARKNESS, 8).add(Aspect.MAGIC, 4);
        WGResearch.registerInfusionEnchantmentRecipe("ENCH_BACKSTAB", "", WGContent.enc_backstab, 3, infusionAspects, new ItemStack[]{new ItemStack(Items.field_151040_l), new ItemStack((Item)Items.field_151068_bn, 1, 8206), new ItemStack(ConfigItems.itemResource, 1, 14)});
        WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_backstab, "witchinggadgets:textures/gui/research/icon_ench_backstab.png", new AspectList().add(Aspect.AIR, 20).add(Aspect.ENTROPY, 20).add(Aspect.FIRE, 20), "ENCH_BACKSTAB");
        infusionAspects = new AspectList().add(Aspect.ARMOR, 12).add(Aspect.TRAP, 8).add(Aspect.MAGIC, 4);
        WGResearch.registerInfusionEnchantmentRecipe("ENCH_RIDEPROTECT", "", WGContent.enc_rideProtect, 3, infusionAspects, new ItemStack[]{new ItemStack(ConfigItems.itemResource, 1, 14), new ItemStack((Block)Blocks.field_150331_J), new ItemStack((Block)Blocks.field_150331_J)});
        WGModCompat.thaumicTinkererRegisterEnchantment(WGContent.enc_rideProtect, "witchinggadgets:textures/gui/research/icon_ench_rideProtect.png", new AspectList().add(Aspect.AIR, 20).add(Aspect.ENTROPY, 20).add(Aspect.ORDER, 20), "ENCH_RIDEPROTECT");
        AspectList alchemyAspects = new AspectList().add(Aspect.PLANT, 4).add(Aspect.ENTROPY, 4).add(Aspect.MAGIC, 4);
        WGResearch.registerAlchemyRecipe("ROSEVINE", "", new ItemStack(WGContent.BlockRoseVine), new ItemStack((Block)Blocks.field_150398_cm, 1, 4), alchemyAspects);
        alchemyAspects = new AspectList().add(Aspect.PLANT, 2).add(Aspect.LIFE, 1);
        WGResearch.registerAlchemyRecipe("ALCHEMICALTRANSMOGRIFY", "_GRASS", new ItemStack((Block)Blocks.field_150349_c), new ItemStack(Blocks.field_150346_d), alchemyAspects);
        alchemyAspects = new AspectList().add(Aspect.PLANT, 2).add(Aspect.DARKNESS, 1);
        WGResearch.registerAlchemyRecipe("ALCHEMICALTRANSMOGRIFY", "_MYCEL", new ItemStack((Block)Blocks.field_150391_bh), new ItemStack(Blocks.field_150346_d), alchemyAspects);
        alchemyAspects = new AspectList().add(Aspect.ENTROPY, 3);
        WGResearch.registerAlchemyRecipe("ALCHEMICALTRANSMOGRIFY", "_SAND", new ItemStack((Block)Blocks.field_150354_m), new ItemStack(Blocks.field_150347_e), alchemyAspects);
        alchemyAspects = new AspectList().add(Aspect.CRYSTAL, 3);
        WGResearch.registerAlchemyRecipe("ALCHEMICALTRANSMOGRIFY", "_FLINT", new ItemStack(Items.field_151145_ak), new ItemStack(Blocks.field_150351_n), alchemyAspects);
        alchemyAspects = new AspectList().add(Aspect.METAL, 1).add(Aspect.ORDER, 1);
        WGResearch.registerAlchemyRecipe("PURECINNABAR", "", new ItemStack(ConfigItems.itemNugget, 1, 21), "oreCinnabar", alchemyAspects);
        WGResearch.addBlastTrippling("Cinnabar");
        alchemyAspects = new AspectList().add(Aspect.VOID, 2).add(Aspect.CRYSTAL, 4);
        WGResearch.registerAlchemyRecipe("CRYSTALCAPSULE", "", new ItemStack(WGContent.ItemCapsule), new ItemStack(Items.field_151133_ar), alchemyAspects);
        for (int iOre = 0; iOre < ItemClusters.subNames.length; ++iOre) {
            boolean bb;
            if (WGConfig.allowClusters) {
                alchemyAspects = new AspectList().add(Aspect.METAL, 1).add(Aspect.ORDER, 1);
                if (!OreDictionary.getOres((String)("ore" + ItemClusters.subNames[iOre])).isEmpty() && !OreDictionary.getOres((String)("ingot" + ItemClusters.subNames[iOre])).isEmpty()) {
                    WGResearch.registerAlchemyRecipe("METALLURGICPERFECTION_CLUSTERS", "_" + ItemClusters.subNames[iOre], new ItemStack(WGContent.ItemCluster, 1, iOre), "ore" + ItemClusters.subNames[iOre], alchemyAspects);
                    WGResearch.setupCluster(ItemClusters.subNames[iOre]);
                }
            }
            if (!WGConfig.allowTransmutations) continue;
            boolean bl = bb = !OreDictionary.getOres((String)("nugget" + ItemClusters.subNames[iOre])).isEmpty() && !OreDictionary.getOres((String)("ingot" + ItemClusters.subNames[iOre])).isEmpty();
            if (!bb) continue;
            ItemStack ingot = (ItemStack)OreDictionary.getOres((String)("ingot" + ItemClusters.subNames[iOre])).get(0);
            alchemyAspects = (AspectList)ThaumcraftApi.objectTags.get(Arrays.asList(ingot.func_77973_b(), ingot.func_77960_j()));
            if (alchemyAspects == null) {
                alchemyAspects = new AspectList();
            }
            alchemyAspects.remove(Aspect.METAL);
            alchemyAspects.add(Aspect.METAL, 2);
            Iterator it = alchemyAspects.aspects.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = it.next();
                if (e.getKey() != null && e.getValue() != null) continue;
                it.remove();
            }
            ItemStack nuggets = Utilities.copyStackWithSize((ItemStack)OreDictionary.getOres((String)("nugget" + ItemClusters.subNames[iOre])).get(0), 3);
            WGResearch.registerAlchemyRecipe("METALLURGICPERFECTION_TRANSMUTATION", "_" + ItemClusters.subNames[iOre], nuggets, "nugget" + ItemClusters.subNames[iOre], alchemyAspects);
        }
        SpinningRecipe spin_Thread = new SpinningRecipe(new ItemStack(WGContent.ItemMaterial, 2, 0), Items.field_151007_F, Items.field_151007_F, Items.field_151007_F, Items.field_151007_F);
        SpinningRecipe.addRecipe(spin_Thread);
        SpinningRecipe spin_goldThread = new SpinningRecipe(new ItemStack(WGContent.ItemMaterial, 2, 1), Items.field_151007_F, Items.field_151007_F, Items.field_151074_bl, Items.field_151074_bl);
        SpinningRecipe.addRecipe(spin_goldThread);
        SpinningRecipe spin_thaumiumThread = new SpinningRecipe(new ItemStack(WGContent.ItemMaterial, 2, 2), Items.field_151007_F, Items.field_151007_F, "nuggetThaumium", "nuggetThaumium");
        SpinningRecipe.addRecipe(spin_thaumiumThread);
        WGResearch.registerCompoundRecipe("GEMCUTTING", "", new AspectList(), 1, 2, 1, new ItemStack(WGContent.ItemMaterial, 1, 8), new ItemStack(ConfigBlocks.blockTable));
        craftingAspects = new AspectList().add(Aspect.AIR, 15).add(Aspect.ORDER, 15);
        WGResearch.registerCompoundRecipe("LOOM", "", craftingAspects, 2, 2, 3, new ItemStack(Blocks.field_150422_aJ), null, new ItemStack(Blocks.field_150411_aY), null, new ItemStack(Blocks.field_150422_aJ), null, "plankWood", "plankWood", "slabWood", "slabWood", "plankWood", "plankWood");
        ItemStack ifBlFrStair = new ItemStack(TileEntityBlastfurnace.stairBlock, 1, TileEntityBlastfurnace.stairBlock != Blocks.field_150390_bg ? 1 : 0);
        craftingAspects = new AspectList().add(Aspect.FIRE, 50).add(Aspect.EARTH, 50).add(Aspect.ENTROPY, 50);
        WGResearch.registerCompoundRecipe("INFERNALBLASTFURNACE", "", craftingAspects, 3, 3, 3, ifBlFrStair, ifBlFrStair, ifBlFrStair, ifBlFrStair, new ItemStack(Blocks.field_150353_l), ifBlFrStair, ifBlFrStair, ifBlFrStair, ifBlFrStair, new ItemStack(TileEntityBlastfurnace.brickBlock[9]), new ItemStack(TileEntityBlastfurnace.brickBlock[10]), new ItemStack(TileEntityBlastfurnace.brickBlock[11]), new ItemStack(TileEntityBlastfurnace.brickBlock[12]), new ItemStack(TileEntityBlastfurnace.brickBlock[13]), new ItemStack(TileEntityBlastfurnace.brickBlock[14]), new ItemStack(TileEntityBlastfurnace.brickBlock[15]), new ItemStack(TileEntityBlastfurnace.brickBlock[16]), new ItemStack(TileEntityBlastfurnace.brickBlock[17]), new ItemStack(TileEntityBlastfurnace.brickBlock[0]), new ItemStack(TileEntityBlastfurnace.brickBlock[1]), new ItemStack(TileEntityBlastfurnace.brickBlock[2]), new ItemStack(TileEntityBlastfurnace.brickBlock[3]), new ItemStack(TileEntityBlastfurnace.brickBlock[4]), new ItemStack(TileEntityBlastfurnace.brickBlock[5]), new ItemStack(TileEntityBlastfurnace.brickBlock[6]), new ItemStack(TileEntityBlastfurnace.brickBlock[7]), new ItemStack(TileEntityBlastfurnace.brickBlock[8]));
        WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)WitchingGadgets.instance.wgWandManager, (int)0, (Block)Blocks.field_150422_aJ, (int)-1);
        WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)WitchingGadgets.instance.wgWandManager, (int)0, (Block)Blocks.field_150411_aY, (int)-1);
        if (WGModCompat.railcraftAllowBlastFurnace()) {
            WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)WitchingGadgets.instance.wgWandManager, (int)1, (Block)TileEntityBlastfurnace.brickBlock[0], (int)-1);
        } else {
            WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)WitchingGadgets.instance.wgWandManager, (int)1, (Block)TileEntityBlastfurnace.brickBlock[0], (int)-1);
            WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)WitchingGadgets.instance.wgWandManager, (int)1, (Block)TileEntityBlastfurnace.brickBlock[4], (int)-1);
            WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)WitchingGadgets.instance.wgWandManager, (int)1, (Block)TileEntityBlastfurnace.brickBlock[10], (int)-1);
        }
        WandTriggerRegistry.registerWandBlockTrigger((IWandTriggerManager)WitchingGadgets.instance.wgWandManager, (int)1, (Block)TileEntityBlastfurnace.stairBlock, (int)-1);
        WGResearch.addBlastTrippling("Iron");
        WGResearch.addBlastTrippling("Gold");
        WGResearch.addBlastTrippling("Copper");
        WGResearch.addBlastTrippling("Tin");
        WGResearch.addBlastTrippling("Silver");
        WGResearch.addBlastTrippling("Lead");
        InfernalBlastfurnaceRecipe.addRecipe(new ItemStack(ConfigItems.itemResource, 3, 3), "clusterCinnabar", 1, 440, false).addBonus(new ItemStack(ConfigItems.itemNugget, 1, 5));
        for (String name : OreDictionary.getOreNames()) {
            if (!name.startsWith("cluster")) continue;
            WGResearch.addBlastTrippling(name.substring("cluster".length()));
        }
        if (WGModCompat.loaded_TCon) {
            if (WGConfig.smelteryResultForClusters > 0) {
                WGModCompat.addTConSmelteryRecipe("clusterIron", "blockIron", 600, "iron.molten", WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe("clusterGold", "blockGold", 400, "gold.molten", WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe("clusterCopper", "blockCopper", 350, "copper.molten", WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe("clusterTin", "blockTin", 400, "tin.molten", WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe("clusterSilver", "blockSilver", 550, "silver.molten", WGConfig.smelteryResultForClusters);
                WGModCompat.addTConSmelteryRecipe("clusterLead", "blockLead", 400, "lead.molten", WGConfig.smelteryResultForClusters);
            }
            WGModCompat.addTConDryingRecipe(new ItemStack(ConfigItems.itemZombieBrain), 600, new ItemStack(WGContent.ItemMagicFoodstuffs, 1, 2));
        }
    }

    public static void registerResearch() {
        AspectList researchAspects = new AspectList();
        ResearchPage[] pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.WGPOTIONS.1"), new ResearchPage("witchinggadgets_research_page.WGPOTIONS.2")};
        WGResearch.getResearchItem("WGPOTIONS", "WITCHGADG", researchAspects, 8, 0, 0, new ResourceLocation("witchinggadgets:textures/gui/research/icon_potioneffects.png")).setRound().setAutoUnlock().setPages(pages).registerResearchItem();
        researchAspects = new AspectList();
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.SPINNINGWHEEL.1"), new ResearchPage("witchinggadgets_research_page.SPINNINGWHEEL.2"), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("SPINNINGWHEEL"))), new ResearchPage("witchinggadgets_research_page.SPINNINGWHEEL.r1"), new ResearchPage("witchinggadgets_research_page.SPINNINGWHEEL.r2"), new ResearchPage("witchinggadgets_research_page.SPINNINGWHEEL.r3")};
        WGResearch.getResearchItem("SPINNINGWHEEL", "WITCHGADG", researchAspects, 8, 4, 0, new ItemStack(WGContent.BlockWoodenDevice, 1, 0)).setRound().setAutoUnlock().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.VOID, 2).add(Aspect.HUNGER, 2);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.BAGOFTRICKS.1"), new ResearchPage((IArcaneRecipe)((ShapelessArcaneRecipe)recipeList.get("BAGOFTRICKS_CLOTH"))), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("BAGOFTRICKS_BAG")))};
        WGResearch.getResearchItem("BAGOFTRICKS", "WITCHGADG", researchAspects, 7, 2, 1, new ItemStack(WGContent.ItemBag, 1, 0)).setParents("SPINNINGWHEEL").setConcealed().setPages(pages).registerResearchItem();
        WGResearch.getFakeResearchItem("ENCHFABRIC", "ARTIFICE", 10, 1, new ItemStack(ConfigItems.itemResource, 1, 7)).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 3).add(Aspect.MAGIC, 4).add(Aspect.TAINT, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ADVANCEDROBES.1"), new ResearchPage((IArcaneRecipe)((ShapelessArcaneRecipe)recipeList.get("ADVANCEDROBES_CLOTH"))), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("ADVANCEDROBES_CHEST"))), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("ADVANCEDROBES_LEGS")))};
        WGResearch.getResearchItem("ADVANCEDROBES", "WITCHGADG", researchAspects, 9, 2, 3, new ItemStack(WGContent.ItemMaterial, 1, 5)).setParents("WGFAKEENCHFABRIC", "SPINNINGWHEEL").setPages(pages).setConcealed().setSecondary().registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.HUNGER, 3).add(Aspect.VOID, 3);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.HUNGERBAG.1"), new ResearchPage((IArcaneRecipe)recipeList.get("HUNGERBAG"))};
        WGResearch.getResearchItem("HUNGERBAG", "WITCHGADG", researchAspects, 6, 1, 1, new ItemStack(WGContent.ItemBag, 1, 3)).setPages(pages).setParents(new String[]{"BAGOFTRICKS", "HUNGRYCHEST"}).setConcealed().setSecondary().registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.ELDRITCH, 3).add(Aspect.VOID, 3);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ENDERBAG.1"), new ResearchPage((InfusionRecipe)recipeList.get("ENDERBAG"))};
        WGResearch.getResearchItem("ENDERBAG", "WITCHGADG", researchAspects, 7, 4, 1, new ItemStack(WGContent.ItemBag, 1, 2)).setPages(pages).setParents(new String[]{"BAGOFTRICKS", "INFUSION"}).setHidden().setSecondary().setItemTriggers(new ItemStack[]{new ItemStack(Blocks.field_150477_bB, 1, Short.MAX_VALUE)}).setAspectTriggers(new Aspect[]{Aspect.ELDRITCH}).registerResearchItem();
        ItemStack standardCloak = new ItemStack(WGContent.ItemCloak, 1, 0);
        researchAspects = new AspectList().add(Aspect.CLOTH, 1).add(Aspect.AIR, 1).add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CLOAK.1"), new ResearchPage((IArcaneRecipe)recipeList.get("CLOAK"))};
        WGResearch.getResearchItem("CLOAK", "WITCHGADG", researchAspects, 8, -2, 2, standardCloak).setParentsHidden("ENCHFABRIC").setConcealed().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 4).add(Aspect.TRAVEL, 2).add(Aspect.SOUL, 2).add(Aspect.DARKNESS, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CLOAK_SPECTRAL.1"), new ResearchPage((InfusionRecipe)recipeList.get("CLOAK_SPECTRAL")), new ResearchPage("witchinggadgets_research_page.CLOAK_SPECTRAL.2")};
        WGResearch.getResearchItem("CLOAK_SPECTRAL", "WITCHGADG", researchAspects, 10, -1, 3, new ItemStack(WGContent.ItemCloak, 1, 1)).setParents("CLOAK").setParentsHidden(new String[]{"INFUSION"}).setPages(pages).setConcealed().setSecondary().registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 4).add(Aspect.VOID, 6).add(Aspect.HUNGER, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CLOAK_STORAGE.1"), new ResearchPage((IArcaneRecipe)recipeList.get("CLOAK_STORAGE"))};
        WGResearch.getResearchItem("CLOAK_STORAGE", "WITCHGADG", researchAspects, 10, -3, 3, new ItemStack(WGContent.ItemCloak, 1, 2)).setParents("CLOAK").setParentsHidden(new String[]{"BAGOFTRICKS"}).setPages(pages).setConcealed().setSecondary().registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 4).add(Aspect.BEAST, 4).add(Aspect.HUNGER, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CLOAK_WOLF.1"), new ResearchPage((IArcaneRecipe)recipeList.get("CLOAK_WOLF"))};
        WGResearch.getResearchItem("CLOAK_WOLF", "WITCHGADG", researchAspects, 6, -1, 3, new ItemStack(WGContent.ItemCloak, 1, 3)).setParents("CLOAK").setPages(pages).setConcealed().setSecondary().registerResearchItem();
        if (WGModCompat.tfRavensFeather != null) {
            researchAspects = new AspectList().add(Aspect.CLOTH, 4).add(Aspect.AIR, 4).add(Aspect.FLIGHT, 4).add(Aspect.TRAVEL, 2);
            pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CLOAK_RAVEN.1"), new ResearchPage((IArcaneRecipe)recipeList.get("CLOAK_RAVEN"))};
            WGResearch.getResearchItem("CLOAK_RAVEN", "WITCHGADG", researchAspects, 6, -3, 3, new ItemStack(WGContent.ItemCloak, 1, 4)).setParents("CLOAK").setPages(pages).setConcealed().setSecondary().registerResearchItem();
        }
        researchAspects = new AspectList().add(Aspect.CLOTH, 1).add(Aspect.MAGIC, 1).add(Aspect.ARMOR, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.WGBAUBLES.1"), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("WGBAUBLES_KNOCKBACKSHOULDERS"))), new ResearchPage("witchinggadgets_research_page.WGBAUBLES.2"), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("WGBAUBLES_WOLFVAMBRACES"))), new ResearchPage("witchinggadgets_research_page.WGBAUBLES.3"), new ResearchPage((InfusionRecipe)recipeList.get("WGBAUBLES_HASTEVAMBRACES")), new ResearchPage("witchinggadgets_research_page.WGBAUBLES.4"), new ResearchPage((InfusionRecipe)recipeList.get("WGBAUBLES_DOUBLEJUMPSHOULDERS")), new ResearchPage("witchinggadgets_research_page.WGBAUBLES.5"), new ResearchPage((InfusionRecipe)recipeList.get("WGBAUBLES_SNIPERRING")), new ResearchPage("witchinggadgets_research_page.WGBAUBLES.6"), new ResearchPage((InfusionRecipe)recipeList.get("WGBAUBLES_LUCKRING")), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("WGBAUBLES_COIN")))};
        WGResearch.getResearchItem("WGBAUBLES", "WITCHGADG", researchAspects, 7, -5, 1, new ItemStack(WGContent.ItemMagicalBaubles, 1, 2)).setParents("THAUMIUM").setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.ARMOR, 2);
        ArrayList<ShapedArcaneRecipe> recList = new ArrayList<ShapedArcaneRecipe>();
        for (int cm = 0; cm < ItemCloak.subNames.length; ++cm) {
            recList.add((ShapedArcaneRecipe)recipeList.get("CLOAKKAMA_" + cm));
        }
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CLOAKKAMA.1"), new ResearchPage((IArcaneRecipe[])recList.toArray(new ShapedArcaneRecipe[0]))};
        WGResearch.getResearchItem("CLOAKKAMA", "WITCHGADG", researchAspects, 9, -5, 1, new ItemStack(WGContent.ItemKama, 1, 0)).setParents("WGBAUBLES", "CLOAK").setConcealed().setSecondary().setPages(pages).registerResearchItem();
        WGResearch.getFakeResearchItem("ARCANESTONE", "ARTIFICE", -3, -5, new ItemStack(ConfigBlocks.blockCosmeticSolid, 1, 6)).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.EARTH, 1).add(Aspect.MECHANISM, 1).add(Aspect.TOOL, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.STONEEXTRUDER.1"), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("STONEEXTRUDER")))};
        WGResearch.getResearchItem("STONEEXTRUDER", "WITCHGADG", researchAspects, -1, -5, 1, new ItemStack(WGContent.BlockWoodenDevice, 1, 2)).setParents("WGFAKEARCANESTONE", "THAUMIUM").setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.LIFE, 3).add(Aspect.MECHANISM, 3);
        if (Aspect.getAspect((String)"tempus") != null) {
            researchAspects.add(Aspect.getAspect((String)"tempus"), 2);
        }
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.AGEINGSTONE.1"), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("AGEINGSTONE")))};
        WGResearch.getResearchItem("AGEINGSTONE", "WITCHGADG", researchAspects, -1, -7, 2, new ItemStack(WGContent.BlockStoneDevice, 1, 7)).addWarp(1).setParents(new String[]{"WGFAKEARCANESTONE"}).setPages(pages).setSecondary().registerResearchItem();
        researchAspects = new AspectList().add(Aspect.ELDRITCH, 2).add(Aspect.MECHANISM, 2).add(Aspect.EARTH, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ETHEREALWALL.1"), new ResearchPage("witchinggadgets_research_page.ETHEREALWALL.2"), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("ETHEREALWALL")))};
        WGResearch.getResearchItem("ETHEREALWALL", "WITCHGADG", researchAspects, -1, -6, 2, new ItemStack(WGContent.BlockStoneDevice, 1, 0)).setParents("WGFAKEARCANESTONE").setPages(pages).setSecondary().registerResearchItem();
        WGResearch.getFakeResearchItem("BATHSALTS", "ALCHEMY", -1, -3, new ItemStack(ConfigItems.itemBathSalts)).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.WATER, 3).add(Aspect.FIRE, 3).add(Aspect.MECHANISM, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.SAUNASTOVE.1"), new ResearchPage("witchinggadgets_research_page.SAUNASTOVE.2"), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("SAUNASTOVE")))};
        WGResearch.getResearchItem("SAUNASTOVE", "WITCHGADG", researchAspects, -1, -1, 1, new ItemStack(WGContent.BlockWoodenDevice, 1, 4)).setParents("WGFAKEBATHSALTS").setPages(pages).setSecondary().setConcealed().registerResearchItem();
        WGResearch.getFakeResearchItem("JARLABEL", "ALCHEMY", 4, -1, new ItemStack(ConfigBlocks.blockJar)).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.SENSES, 4).add(Aspect.MIND, 4).add(Aspect.TOOL, 2);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.LABELLIB.1"), new ResearchPage((IArcaneRecipe)((ShapedArcaneRecipe)recipeList.get("LABELLIB")))};
        WGResearch.getResearchItem("LABELLIB", "WITCHGADG", researchAspects, 4, -3, 2, new ItemStack(WGContent.BlockWoodenDevice, 1, 5)).setParents("WGFAKEJARLABEL").setPages(pages).setSecondary().registerResearchItem();
        if (Config.allowMirrors) {
            WGResearch.getFakeResearchItem("MIRRORESSENTIA", "ARTIFICE", 1, -7, new ItemStack(ConfigBlocks.blockMirror, 1, 6)).registerResearchItem();
            researchAspects = new AspectList().add(Aspect.TOOL, 1).add(Aspect.WATER, 1).add(Aspect.TRAVEL, 1).add(Aspect.SLIME, 1);
            pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.MIRRORPUMP.1"), new ResearchPage((IArcaneRecipe)recipeList.get("MIRRORPUMP"))};
            WGResearch.getResearchItem("MIRRORPUMP", "WITCHGADG", researchAspects, 2, -8, 2, new ItemStack(WGContent.BlockMetalDevice, 1, 0)).setPages(pages).setParents(new String[]{"WGFAKEMIRRORESSENTIA"}).registerResearchItem();
        }
        researchAspects = new AspectList().add(Aspect.SENSES, 1).add(Aspect.MIND, 1).add(Aspect.SOUL, 1).add(Aspect.CRYSTAL, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.SCANCAMERA.1"), new ResearchPage((IArcaneRecipe)recipeList.get("SCANCAMERA")), new ResearchPage("witchinggadgets_research_page.SCANCAMERA.2"), new ResearchPage((IArcaneRecipe)recipeList.get("SCANCAMERA_DEVELOP")), new ResearchPage((IRecipe)recipeList.get("SCANCAMERA_CLEARPLATE"))};
        WGResearch.getResearchItem("SCANCAMERA", "WITCHGADG", researchAspects, 2, -4, 2, new ItemStack(WGContent.ItemScanCamera)).setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.TOOL, 1).add(Aspect.MIND, 1).add(Aspect.MECHANISM, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CALCULATOR.1"), new ResearchPage((IArcaneRecipe)recipeList.get("CALCULATOR"))};
        WGResearch.getResearchItem("CALCULATOR", "WITCHGADG", researchAspects, 1, -5, 1, new ItemStack(WGContent.ItemMaterial, 1, 7)).setPages(pages).setParents(new String[]{"INFUSION"}).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.TOOL, 1).add(Aspect.MIND, 1).add(Aspect.TRAVEL, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.LABYRINTHSTRING.1"), new ResearchPage((InfusionRecipe)recipeList.get("LABYRINTHSTRING"))};
        WGResearch.getResearchItem("LABYRINTHSTRING", "WITCHGADG", researchAspects, 2, -6, 1, new ItemStack(WGContent.ItemMaterial, 1, 11)).setPages(pages).setParents(new String[]{"INFUSION"}).registerResearchItem();
        WGResearch.getFakeResearchItem("ALCHEMICALMANUFACTURE", "ALCHEMY", -4, -4, new ResourceLocation("thaumcraft", "textures/misc/r_alchman.png")).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.PLANT, 4).add(Aspect.LIFE, 2).add(Aspect.WATER, 2);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ALCHEMICALTRANSMOGRIFY.1"), new ResearchPage((CrucibleRecipe)recipeList.get("ALCHEMICALTRANSMOGRIFY_GRASS")), new ResearchPage((CrucibleRecipe)recipeList.get("ALCHEMICALTRANSMOGRIFY_MYCEL")), new ResearchPage((CrucibleRecipe)recipeList.get("ALCHEMICALTRANSMOGRIFY_SAND")), new ResearchPage((CrucibleRecipe)recipeList.get("ALCHEMICALTRANSMOGRIFY_FLINT"))};
        WGResearch.getResearchItem("ALCHEMICALTRANSMOGRIFY", "WITCHGADG", researchAspects, -6, -4, 1, new ItemStack((Block)Blocks.field_150349_c)).setSecondary().setParents(new String[]{"WGFAKEALCHEMICALMANUFACTURE"}).setConcealed().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.PLANT, 2).add(Aspect.AIR, 3).add(Aspect.ENTROPY, 2);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ROSEVINE.1"), new ResearchPage((CrucibleRecipe)recipeList.get("ROSEVINE"))};
        WGResearch.getResearchItem("ROSEVINE", "WITCHGADG", researchAspects, -6, -5, 1, new ItemStack(WGContent.BlockRoseVine)).setSecondary().setParents(new String[]{"WGFAKEALCHEMICALMANUFACTURE"}).setConcealed().setPages(pages).registerResearchItem();
        WGResearch.getFakeResearchItem("PUREIRON", "ALCHEMY", -5, -2, new ItemStack(ConfigItems.itemNugget, 1, 16)).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.METAL, 5).add(Aspect.ORDER, 1).add(Aspect.POISON, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.PURECINNABAR.1"), new ResearchPage((CrucibleRecipe)recipeList.get("PURECINNABAR"))};
        WGResearch.getResearchItem("PURECINNABAR", "WITCHGADG", researchAspects, -6, -3, 1, new ItemStack(ConfigItems.itemNugget, 1, 21)).setConcealed().setSecondary().setParents(new String[]{"WGFAKEPUREIRON"}).setPages(pages).registerResearchItem();
        if (WGConfig.allowClusters) {
            ArrayList<ResearchPage> clusterPages = new ArrayList<ResearchPage>();
            clusterPages.add(new ResearchPage("witchinggadgets_research_page.METALLURGICPERFECTION_CLUSTERS.1"));
            for (String ore : ItemClusters.subNames) {
                if (!recipeList.containsKey("METALLURGICPERFECTION_CLUSTERS_" + ore)) continue;
                clusterPages.add(new ResearchPage((CrucibleRecipe)recipeList.get("METALLURGICPERFECTION_CLUSTERS_" + ore)));
            }
            pages = clusterPages.toArray(new ResearchPage[0]);
            researchAspects = new AspectList().add(Aspect.METAL, 20).add(Aspect.ORDER, 10).add(Aspect.CRYSTAL, 10);
            ArrayList<String> clusterParents = new ArrayList<String>();
            clusterParents.add("WGFAKEPUREIRON");
            clusterParents.add("PUREGOLD");
            if (Utilities.researchExists("ALCHEMY", "PURECOPPER")) {
                clusterParents.add("PURECOPPER");
            }
            if (Utilities.researchExists("ALCHEMY", "PURETIN")) {
                clusterParents.add("PURETIN");
            }
            if (Utilities.researchExists("ALCHEMY", "PURESILVER")) {
                clusterParents.add("PURESILVER");
            }
            if (Utilities.researchExists("ALCHEMY", "PURELEAD")) {
                clusterParents.add("PURELEAD");
            }
            clusterParents.add("PURECINNABAR");
            WGResearch.getResearchItem("METALLURGICPERFECTION_CLUSTERS", "WITCHGADG", researchAspects, -6, -1, 1, new ResourceLocation("witchinggadgets:textures/gui/research/icon_mp_cluster.png")).setConcealed().setSecondary().setSpecial().setParents(clusterParents.toArray(new String[0])).setPages(pages).registerResearchItem();
        }
        if (WGConfig.allowTransmutations) {
            WGResearch.getFakeResearchItem("TRANSIRON", "ALCHEMY", -4, -2, new ItemStack(ConfigItems.itemNugget, 1, 0)).registerResearchItem();
            ArrayList<ResearchPage> transmutePages = new ArrayList<ResearchPage>();
            transmutePages.add(new ResearchPage("witchinggadgets_research_page.METALLURGICPERFECTION_TRANSMUTATION.1"));
            for (String ore : ItemClusters.subNames) {
                if (!recipeList.containsKey("METALLURGICPERFECTION_TRANSMUTATION_" + ore)) continue;
                transmutePages.add(new ResearchPage((CrucibleRecipe)recipeList.get("METALLURGICPERFECTION_TRANSMUTATION_" + ore)));
            }
            pages = transmutePages.toArray(new ResearchPage[0]);
            researchAspects = new AspectList().add(Aspect.METAL, 20).add(Aspect.ORDER, 10).add(Aspect.EXCHANGE, 10);
            ArrayList<String> transmuteParents = new ArrayList<String>();
            transmuteParents.add("WGFAKETRANSIRON");
            transmuteParents.add("TRANSGOLD");
            if (Utilities.researchExists("ALCHEMY", "TRANSCOPPER")) {
                transmuteParents.add("TRANSCOPPER");
            }
            if (Utilities.researchExists("ALCHEMY", "TRANSTIN")) {
                transmuteParents.add("TRANSTIN");
            }
            if (Utilities.researchExists("ALCHEMY", "TRANSSILVER")) {
                transmuteParents.add("TRANSSILVER");
            }
            if (Utilities.researchExists("ALCHEMY", "TRANSLEAD")) {
                transmuteParents.add("TRANSLEAD");
            }
            WGResearch.getResearchItem("METALLURGICPERFECTION_TRANSMUTATION", "WITCHGADG", researchAspects, -3, -1, 1, new ResourceLocation("witchinggadgets:textures/gui/research/icon_mp_trans.png")).setConcealed().setSecondary().setSpecial().setParents(transmuteParents.toArray(new String[0])).setPages(pages).registerResearchItem();
        }
        WGResearch.getFakeResearchItem("INFERNALFURNACE", "ARTIFICE", 3, -5, new ResourceLocation("thaumcraft", "textures/misc/r_infernalfurnace.png")).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.FIRE, 2).add(Aspect.METAL, 1).add(Aspect.CRAFT, 1).add(Aspect.DARKNESS, 1).add(Aspect.TAINT, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.INFERNALBLASTFURNACE.1"), new ResearchPage("witchinggadgets_research_page.INFERNALBLASTFURNACE.2"), new ResearchPage((List)recipeList.get("INFERNALBLASTFURNACE")), new ResearchPage("witchinggadgets_research_page.INFERNALBLASTFURNACE.3")};
        WGResearch.getResearchItem("INFERNALBLASTFURNACE", "WITCHGADG", researchAspects, 4, -7, 2, new ResourceLocation("witchinggadgets:textures/gui/research/icon_blastfurnace.png")).addWarp(3).setConcealed().setPages(pages).setParents(new String[]{"WGFAKEINFERNALFURNACE"}).registerResearchItem();
        WGResearch.getFakeResearchItem("CENTRIFUGE", "ALCHEMY", 5, -5, new ItemStack(ConfigBlocks.blockTube, 1, 2)).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.EARTH, 2).add(Aspect.EXCHANGE, 1).add(Aspect.ENERGY, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMER.1"), new ResearchPage((InfusionRecipe)recipeList.get("TERRAFORMER")), new ResearchPage("witchinggadgets_research_page.TERRAFORMER.2"), new ResearchPage((IArcaneRecipe)recipeList.get("TERRAFORMER_PLAINS"))};
        WGResearch.getResearchItem("TERRAFORMER", "WITCHGADG", researchAspects, 6, -7, 2, new ItemStack(WGContent.BlockMetalDevice, 1, 1)).setPages(pages).setParents(new String[]{"WGFAKECENTRIFUGE"}).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.COLD, 8).add(Aspect.ORDER, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_COLDTAIGA.1"), new ResearchPage((IArcaneRecipe)recipeList.get("TERRAFORMFOCUS_COLDTAIGA"))};
        WGResearch.getResearchItem("TERRAFORMFOCUS_COLDTAIGA", "WITCHGADG", researchAspects, 6, -9, 2, new ItemStack(WGContent.BlockMetalDevice, 1, 3)).setSecondary().setPages(pages).setParents(new String[]{"TERRAFORMER"}).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.FIRE, 4).add(Aspect.ENTROPY, 8).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_DESERT.1"), new ResearchPage((IArcaneRecipe)recipeList.get("TERRAFORMFOCUS_DESERT"))};
        WGResearch.getResearchItem("TERRAFORMFOCUS_DESERT", "WITCHGADG", researchAspects, 5, -8, 2, new ItemStack(WGContent.BlockMetalDevice, 1, 4)).setSecondary().setPages(pages).setParents(new String[]{"TERRAFORMER"}).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.TREE, 8).add(Aspect.PLANT, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_JUNGLE.1"), new ResearchPage((IArcaneRecipe)recipeList.get("TERRAFORMFOCUS_JUNGLE"))};
        WGResearch.getResearchItem("TERRAFORMFOCUS_JUNGLE", "WITCHGADG", researchAspects, 7, -9, 2, new ItemStack(WGContent.BlockMetalDevice, 1, 5)).setSecondary().setPages(pages).setParents(new String[]{"TERRAFORMER"}).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.DARKNESS, 8).add(Aspect.FIRE, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_HELL.1"), new ResearchPage((IArcaneRecipe)recipeList.get("TERRAFORMFOCUS_HELL"))};
        WGResearch.getResearchItem("TERRAFORMFOCUS_HELL", "WITCHGADG", researchAspects, 8, -9, 2, new ItemStack(WGContent.BlockMetalDevice, 1, 6)).addWarp(2).setSecondary().setPages(pages).setParents(new String[]{"TERRAFORMER"}).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.PLANT, 8).add(Aspect.EARTH, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_MUSHROOM.1"), new ResearchPage((IArcaneRecipe)recipeList.get("TERRAFORMFOCUS_MUSHROOM"))};
        WGResearch.getResearchItem("TERRAFORMFOCUS_MUSHROOM", "WITCHGADG", researchAspects, 9, -8, 2, new ItemStack(WGContent.BlockMetalDevice, 1, 9)).setSecondary().setPages(pages).setParents(new String[]{"TERRAFORMER"}).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.TAINT, 8).add(Aspect.MAGIC, 4).add(Aspect.EXCHANGE, 2).add(Aspect.ENERGY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.TERRAFORMFOCUS_TAINT.1"), new ResearchPage((InfusionRecipe)recipeList.get("TERRAFORMFOCUS_TAINT"))};
        WGResearch.getResearchItem("TERRAFORMFOCUS_TAINT", "WITCHGADG", researchAspects, 9, -7, 2, new ItemStack(WGContent.BlockMetalDevice, 1, 8)).addWarp(3).setSecondary().setPages(pages).setParents(new String[]{"TERRAFORMER", "BOTTLETAINT"}).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.ORDER, 1).add(Aspect.MAGIC, 1).add(Aspect.CRAFT, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.GEMCUTTING.1"), new ResearchPage((IRecipe)((ShapedOreRecipe)recipeList.get("GEMCUTTING_TOOLS"))), new ResearchPage((List)recipeList.get("GEMCUTTING")), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.2"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.3"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.4"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.5"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.6"), new ResearchPage("witchinggadgets_research_page.GEMCUTTING.7" + (!Config.allowMirrors ? ".noMirrors" : ""))};
        WGResearch.getResearchItem("GEMCUTTING", "WITCHGADG", researchAspects, 1, -2, 2, new ItemStack(WGContent.BlockWoodenDevice, 1, 3)).setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CRYSTAL, 3).add(Aspect.ORDER, 2).add(Aspect.VOID, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.CRYSTALCAPSULE.1"), new ResearchPage((CrucibleRecipe)recipeList.get("CRYSTALCAPSULE"))};
        WGResearch.getResearchItem("CRYSTALCAPSULE", "WITCHGADG", researchAspects, 2, -2, 2, new ItemStack(WGContent.ItemCapsule)).setPages(pages).setSecondary().setParents(new String[]{"GEMCUTTING"}).registerResearchItem();
        WGResearch.getFakeResearchItem("INFUSIONENCHANTMENT", "ARTIFICE", -8, 1, new ResourceLocation("thaumcraft:textures/misc/r_enchant.png")).setSiblings(new String[0]).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.CRYSTAL, 4).add(Aspect.DARKNESS, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ENCH_INVISIBLEGEAR.1"), new ResearchPage((InfusionEnchantmentRecipe)recipeList.get("ENCH_INVISIBLEGEAR"))};
        WGResearch.getResearchItem("ENCH_INVISIBLEGEAR", "WITCHGADG", researchAspects, -10, 1, 2, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_invisGear.png")).setParents("WGFAKEINFUSIONENCHANTMENT").setConcealed().setSecondary().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.SENSES, 4).add(Aspect.LIGHT, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ENCH_UNVEILING.1"), new ResearchPage((InfusionEnchantmentRecipe)recipeList.get("ENCH_UNVEILING"))};
        WGResearch.getResearchItem("ENCH_UNVEILING", "WITCHGADG", researchAspects, -10, 2, 2, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_unveiling.png")).setParents("WGFAKEINFUSIONENCHANTMENT").setConcealed().setSecondary().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.MOTION, 4).add(Aspect.DARKNESS, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ENCH_STEALTH.1"), new ResearchPage((InfusionEnchantmentRecipe)recipeList.get("ENCH_STEALTH"))};
        WGResearch.getResearchItem("ENCH_STEALTH", "WITCHGADG", researchAspects, -9, 3, 2, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_stealth.png")).setParents("WGFAKEINFUSIONENCHANTMENT").setConcealed().setSecondary().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.WEAPON, 4).add(Aspect.DARKNESS, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ENCH_BACKSTAB.1"), new ResearchPage((InfusionEnchantmentRecipe)recipeList.get("ENCH_BACKSTAB"))};
        WGResearch.getResearchItem("ENCH_BACKSTAB", "WITCHGADG", researchAspects, -11, 4, 2, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_backstab.png")).setParents("ENCH_STEALTH").setConcealed().setSecondary().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.MAGIC, 2).add(Aspect.TRAP, 4).add(Aspect.ARMOR, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.ENCH_RIDEPROTECT.1"), new ResearchPage((InfusionEnchantmentRecipe)recipeList.get("ENCH_RIDEPROTECT"))};
        WGResearch.getResearchItem("ENCH_RIDEPROTECT", "WITCHGADG", researchAspects, -11, 0, 2, new ResourceLocation("witchinggadgets:textures/gui/research/icon_ench_rideProtect.png")).setParents("WGFAKEINFUSIONENCHANTMENT").setConcealed().setSecondary().setPages(pages).registerResearchItem();
        WGResearch.getFakeResearchItem("ELDRITCHMINOR", "ELDRITCH", 1, 3, new ResourceLocation("thaumcraft", "textures/misc/r_eldritchminor.png")).setSpecial().registerResearchItem();
        WGResearch.getFakeResearchItem("PRIMPEARL", "ELDRITCH", 0, 1, new ItemStack(ConfigItems.itemEldritchObject, 1, 3)).setSpecial().registerResearchItem();
        researchAspects = new AspectList().add(Aspect.MAGIC, 1).add(Aspect.ENERGY, 1).add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.PRIMORDIALGEARSET.1"), new ResearchPage("witchinggadgets_research_page.PRIMORDIALGEARSET.2")};
        WGResearch.getResearchItem("PRIMORDIALGEARSET", "WITCHGADG", researchAspects, -2, 3, 3, new ResourceLocation("witchinggadgets:textures/gui/research/icon_primordialGear.png")).setParents("VOIDMETAL", "WGFAKEPRIMPEARL").setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.MAGIC, 8).add(Aspect.CRYSTAL, 4).add(Aspect.VOID, 4).add(Aspect.ENERGY, 4);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.EMPOWERPEARL.1"), new ResearchPage((InfusionRecipe)recipeList.get("EMPOWERPEARL"))};
        WGResearch.getResearchItem("EMPOWERPEARL", "WITCHGADG", researchAspects, -3, 5, 1, new ItemStack(WGContent.ItemMaterial, 1, 12)).setSecondary().setParents(new String[]{"WGFAKEPRIMPEARL"}).setItemTriggers(new ItemStack[]{new ItemStack(WGContent.ItemMaterial, 1, 12)}).setHidden().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CRYSTAL, 1).add(Aspect.MAGIC, 1).add(Aspect.TOOL, 1).add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.PRIMORDIALGLOVE.1"), new ResearchPage((InfusionRecipe)recipeList.get("PRIMORDIALGLOVE")), new ResearchPage("witchinggadgets_research_page.PRIMORDIALGLOVE.2")};
        WGResearch.getResearchItem("PRIMORDIALGLOVE", "WITCHGADG", researchAspects, 2, 0, 2, new ItemStack(WGContent.ItemPrimordialGlove)).setParents("VOIDMETAL", "PRIMORDIALGEARSET", "WGFAKEPRIMPEARL").setConcealed().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.ENTROPY, 1).add(Aspect.WEAPON, 1).add(Aspect.TOOL, 1).add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.MAGIC, 2).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.PRIMORDIALWEAPONRY.1"), new ResearchPage("witchinggadgets_research_page.PRIMORDIALWEAPONRY.2"), new ResearchPage("witchinggadgets_research_page.PRIMORDIALWEAPONRY.3"), new ResearchPage((InfusionRecipe)recipeList.get("PRIMORDIALWEAPONRY_CLAYMORE")), new ResearchPage((InfusionRecipe)recipeList.get("PRIMORDIALWEAPONRY_HAMMER")), new ResearchPage((InfusionRecipe)recipeList.get("PRIMORDIALWEAPONRY_GREATAXE"))};
        WGResearch.getResearchItem("PRIMORDIALWEAPONRY", "WITCHGADG", researchAspects, -4, 2, 3, new ResourceLocation("witchinggadgets:textures/gui/research/icon_primordialWeaponry.png")).setParents("PRIMORDIALGEARSET").setConcealed().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.ARMOR, 1).add(Aspect.MAGIC, 1).add(Aspect.AIR, 1).add(Aspect.FIRE, 1).add(Aspect.WATER, 1).add(Aspect.EARTH, 1).add(Aspect.ORDER, 1).add(Aspect.ENTROPY, 1);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.PRIMORDIALARMOR.1"), new ResearchPage("witchinggadgets_research_page.PRIMORDIALARMOR.2"), new ResearchPage((InfusionRecipe)recipeList.get("PRIMORDIALARMOR_HELMET")), new ResearchPage((InfusionRecipe)recipeList.get("PRIMORDIALARMOR_CUIRASS")), new ResearchPage((InfusionRecipe)recipeList.get("PRIMORDIALARMOR_GREAVES")), new ResearchPage((InfusionRecipe)recipeList.get("PRIMORDIALARMOR_BOOTS"))};
        WGResearch.getResearchItem("PRIMORDIALARMOR", "WITCHGADG", researchAspects, -1, 5, 3, new ResourceLocation("witchinggadgets:textures/gui/research/icon_primordialArmor.png")).setParents("PRIMORDIALGEARSET", "ARMORFORTRESS").setConcealed().setPages(pages).registerResearchItem();
        researchAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.VOID, 3).add(Aspect.ENTROPY, 5).add(Aspect.ELDRITCH, 3);
        pages = new ResearchPage[]{new ResearchPage("witchinggadgets_research_page.VOIDBAG.1"), new ResearchPage((InfusionRecipe)recipeList.get("VOIDBAG"))};
        WGResearch.getResearchItem("VOIDBAG", "WITCHGADG", researchAspects, 2, 2, 1, new ItemStack(WGContent.ItemBag, 1, 1)).setParents("BAGOFTRICKS", "WGFAKEELDRITCHMINOR").setConcealed().setSecondary().setPages(pages).registerResearchItem();
    }

    public static void modifyStandardThaumcraftResearch() {
        int i;
        ResearchItem thaumium = ResearchCategories.getResearch((String)"THAUMIUM");
        ResearchPage[] pages = thaumium.getPages();
        ResearchPage[] newPages = new ResearchPage[pages.length + 1];
        for (i = 0; i < 7; ++i) {
            newPages[i] = pages[i];
        }
        newPages[7] = new ResearchPage((IRecipe)recipeList.get("THAUMIUMSHEARS"));
        for (i = 8; i < newPages.length; ++i) {
            newPages[i] = pages[i - 1];
        }
        thaumium.setPages(newPages);
    }

    private static void registerArcaneRecipe(String tag, String tagAddon, ItemStack result, AspectList craftingAspects, Object ... recipe) {
        ShapedArcaneRecipe arcaneRecipe = ThaumcraftApi.addArcaneCraftingRecipe((String)tag, (ItemStack)result, (AspectList)craftingAspects, (Object[])recipe);
        recipeList.put(tag + tagAddon, arcaneRecipe);
    }

    private static void registerShapelessArcaneRecipe(String tag, String tagAddon, ItemStack result, AspectList craftingAspects, Object ... recipe) {
        ShapelessArcaneRecipe arcaneRecipe = ThaumcraftApi.addShapelessArcaneCraftingRecipe((String)tag, (ItemStack)result, (AspectList)craftingAspects, (Object[])recipe);
        recipeList.put(tag + tagAddon, arcaneRecipe);
    }

    private static void registerAlchemyRecipe(String tag, String tagAddon, ItemStack result, Object catalyst, AspectList alchemyAspects) {
        CrucibleRecipe crucibleRecipe = ThaumcraftApi.addCrucibleRecipe((String)tag, (ItemStack)result, (Object)catalyst, (AspectList)alchemyAspects);
        recipeList.put(tag + tagAddon, crucibleRecipe);
    }

    private static void registerInfusionRecipe(String tag, String tagAddon, Object result, int difficulty, AspectList infusionAspects, ItemStack centralIngredient, ItemStack[] otherIngredients) {
        InfusionRecipe infusionRecipe = ThaumcraftApi.addInfusionCraftingRecipe((String)tag, (Object)result, (int)difficulty, (AspectList)infusionAspects, (ItemStack)centralIngredient, (ItemStack[])otherIngredients);
        recipeList.put(tag + tagAddon, infusionRecipe);
    }

    private static void registerInfusionEnchantmentRecipe(String tag, String tagAddon, Enchantment enchantment, int difficulty, AspectList infusionAspects, ItemStack[] otherIngredients) {
        InfusionEnchantmentRecipe infusionRecipe = ThaumcraftApi.addInfusionEnchantmentRecipe((String)tag, (Enchantment)enchantment, (int)difficulty, (AspectList)infusionAspects, (ItemStack[])otherIngredients);
        recipeList.put(tag + tagAddon, infusionRecipe);
    }

    private static void registerCompoundRecipe(String tag, String tagAddon, AspectList creationAspects, int sizeX, int sizeY, int sizeZ, Object ... recipe) {
        List<Object> compoundRecipe = Arrays.asList(creationAspects, sizeX, sizeY, sizeZ, Arrays.asList(recipe));
        recipeList.put(tag + tagAddon, compoundRecipe);
    }

    private static void registerShapelessOreRecipe(String tag, String tagAddon, ItemStack result, Object ... recipe) {
        ShapelessOreRecipe oreRecipe = new ShapelessOreRecipe(result, recipe);
        GameRegistry.addRecipe((IRecipe)oreRecipe);
        recipeList.put(tag + tagAddon, oreRecipe);
    }

    private static void registerShapedOreRecipe(String tag, String tagAddon, ItemStack result, Object ... recipe) {
        ShapedOreRecipe oreRecipe = new ShapedOreRecipe(result, recipe);
        GameRegistry.addRecipe((IRecipe)oreRecipe);
        recipeList.put(tag + tagAddon, oreRecipe);
    }

    private static WGResearchItem getResearchItem(String tag, String category, AspectList researchAspects, int xPos, int yPos, int complexity, Object icon) {
        WGResearchItem item = null;
        if (icon instanceof ItemStack) {
            item = new WGResearchItem(tag, category, researchAspects, xPos, yPos, complexity, (ItemStack)icon);
        }
        if (icon instanceof ResourceLocation) {
            item = new WGResearchItem(tag, category, researchAspects, xPos, yPos, complexity, (ResourceLocation)icon);
        }
        return item;
    }

    private static WGFakeResearchItem getFakeResearchItem(String original, String originalCat, int xPos, int yPos, Object icon) {
        WGFakeResearchItem item = null;
        if (icon instanceof ItemStack) {
            item = new WGFakeResearchItem("WGFAKE" + original, "WITCHGADG", original, originalCat, xPos, yPos, (ItemStack)icon);
        }
        if (icon instanceof ResourceLocation) {
            item = new WGFakeResearchItem("WGFAKE" + original, "WITCHGADG", original, originalCat, xPos, yPos, (ResourceLocation)icon);
        }
        return item;
    }

    private static void setupCluster(String name) {
        String fluid = MetalFluidData.getOreFluidName(name);
        int fluidTemp = MetalFluidData.getOreFluidTemp(name);
        fluidTemp = fluidTemp > 0 ? fluidTemp : 550;
        String ore = "ore" + name;
        String cluster = "cluster" + name;
        String ingot = "ingot" + name;
        String nugget = "nugget" + name;
        ItemStack clusterStack = ItemClusters.getCluster(name);
        if (!OreDictionary.getOres((String)nugget).isEmpty()) {
            if (!OreDictionary.getOres((String)ore).isEmpty()) {
                ThaumcraftApi.addSmeltingBonus((String)ore, (ItemStack)((ItemStack)OreDictionary.getOres((String)nugget).get(0)));
            }
            if (!OreDictionary.getOres((String)cluster).isEmpty()) {
                ThaumcraftApi.addSmeltingBonus((String)cluster, (ItemStack)((ItemStack)OreDictionary.getOres((String)nugget).get(0)));
            }
        }
        if (!OreDictionary.getOres((String)cluster).isEmpty()) {
            if (!OreDictionary.getOres((String)ingot).isEmpty()) {
                ItemStack ingots = (ItemStack)OreDictionary.getOres((String)ingot).get(0);
                if (clusterStack != null) {
                    FurnaceRecipes.func_77602_a().func_151394_a(clusterStack, Utilities.copyStackWithSize(ingots, 2), 1.0f);
                    if (!OreDictionary.getOres((String)ore).isEmpty()) {
                        Utils.addSpecialMiningResult((ItemStack)((ItemStack)OreDictionary.getOres((String)ore).get(0)), (ItemStack)clusterStack, (float)1.0f);
                    }
                }
            }
            if (WGModCompat.loaded_TCon && WGConfig.smelteryResultForClusters > 0 && FluidRegistry.getFluid((String)fluid) != null) {
                WGModCompat.addTConSmelteryRecipe(cluster, "block" + name, fluidTemp, fluid, WGConfig.smelteryResultForClusters);
            }
        }
    }

    static void addBlastTrippling(String name) {
        InfernalBlastfurnaceRecipe r;
        if (!OreDictionary.getOres((String)("ingot" + name)).isEmpty() && (r = InfernalBlastfurnaceRecipe.addRecipe(Utilities.copyStackWithSize((ItemStack)OreDictionary.getOres((String)("ingot" + name)).get(0), 3), "cluster" + name, 1, 440, false)) != null && !OreDictionary.getOres((String)("nugget" + name)).isEmpty()) {
            r.addBonus((ItemStack)OreDictionary.getOres((String)("nugget" + name)).get(0));
        }
    }
}

