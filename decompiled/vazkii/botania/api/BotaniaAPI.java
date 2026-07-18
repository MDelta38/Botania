/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.EnumRarity
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.EnumChatFormatting
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.api;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import cpw.mods.fml.common.Loader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.internal.DummyMethodHandler;
import vazkii.botania.api.internal.DummySubTile;
import vazkii.botania.api.internal.IInternalMethodHandler;
import vazkii.botania.api.lexicon.KnowledgeType;
import vazkii.botania.api.lexicon.LexiconCategory;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.recipe.RecipeBrew;
import vazkii.botania.api.recipe.RecipeElvenTrade;
import vazkii.botania.api.recipe.RecipeManaInfusion;
import vazkii.botania.api.recipe.RecipeMiniFlower;
import vazkii.botania.api.recipe.RecipePetals;
import vazkii.botania.api.recipe.RecipePureDaisy;
import vazkii.botania.api.recipe.RecipeRuneAltar;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.signature.BasicSignature;
import vazkii.botania.api.subtile.signature.SubTileSignature;
import vazkii.botania.api.wiki.IWikiProvider;
import vazkii.botania.api.wiki.SimpleWikiProvider;
import vazkii.botania.api.wiki.WikiHooks;

public final class BotaniaAPI {
    private static List<LexiconCategory> categories = new ArrayList<LexiconCategory>();
    private static List<LexiconEntry> allEntries = new ArrayList<LexiconEntry>();
    public static Map<String, KnowledgeType> knowledgeTypes = new HashMap<String, KnowledgeType>();
    public static Map<String, Brew> brewMap = new LinkedHashMap<String, Brew>();
    public static List<String> disposableBlocks = new ArrayList<String>();
    public static List<String> semiDisposableBlocks = new ArrayList<String>();
    public static List<RecipePetals> petalRecipes = new ArrayList<RecipePetals>();
    public static List<RecipePureDaisy> pureDaisyRecipes = new ArrayList<RecipePureDaisy>();
    public static List<RecipeManaInfusion> manaInfusionRecipes = new ArrayList<RecipeManaInfusion>();
    public static List<RecipeRuneAltar> runeAltarRecipes = new ArrayList<RecipeRuneAltar>();
    public static List<RecipeElvenTrade> elvenTradeRecipes = new ArrayList<RecipeElvenTrade>();
    public static List<RecipeBrew> brewRecipes = new ArrayList<RecipeBrew>();
    public static List<RecipeManaInfusion> miniFlowerRecipes = new ArrayList<RecipeManaInfusion>();
    private static BiMap<String, Class<? extends SubTileEntity>> subTiles = HashBiMap.create();
    private static Map<Class<? extends SubTileEntity>, SubTileSignature> subTileSignatures = new HashMap<Class<? extends SubTileEntity>, SubTileSignature>();
    public static Set<String> subtilesForCreativeMenu = new LinkedHashSet<String>();
    public static Map<String, String> subTileMods = new HashMap<String, String>();
    public static BiMap<String, String> miniFlowers = HashBiMap.create();
    public static Map<String, Integer> oreWeights = new HashMap<String, Integer>();
    public static Map<String, Integer> oreWeightsNether = new HashMap<String, Integer>();
    public static Map<Item, Block> seeds = new HashMap<Item, Block>();
    public static Set<Item> looniumBlacklist = new LinkedHashSet<Item>();
    public static Set<Block> paintableBlocks = new LinkedHashSet<Block>();
    public static Set<String> magnetBlacklist = new LinkedHashSet<String>();
    public static Set<Class<? extends Entity>> gravityRodBlacklist = new LinkedHashSet<Class<? extends Entity>>();
    public static ItemArmor.ArmorMaterial manasteelArmorMaterial = EnumHelper.addArmorMaterial((String)"MANASTEEL", (int)16, (int[])new int[]{2, 6, 5, 2}, (int)18);
    public static Item.ToolMaterial manasteelToolMaterial = EnumHelper.addToolMaterial((String)"MANASTEEL", (int)3, (int)300, (float)6.2f, (float)2.0f, (int)20);
    public static ItemArmor.ArmorMaterial elementiumArmorMaterial = EnumHelper.addArmorMaterial((String)"B_ELEMENTIUM", (int)18, (int[])new int[]{2, 6, 5, 2}, (int)18);
    public static Item.ToolMaterial elementiumToolMaterial = EnumHelper.addToolMaterial((String)"B_ELEMENTIUM", (int)3, (int)720, (float)6.2f, (float)2.0f, (int)20);
    public static ItemArmor.ArmorMaterial terrasteelArmorMaterial = EnumHelper.addArmorMaterial((String)"TERRASTEEL", (int)34, (int[])new int[]{3, 8, 6, 3}, (int)26);
    public static Item.ToolMaterial terrasteelToolMaterial = EnumHelper.addToolMaterial((String)"TERRASTEEL", (int)4, (int)2300, (float)9.0f, (float)3.0f, (int)26);
    public static ItemArmor.ArmorMaterial manaweaveArmorMaterial = EnumHelper.addArmorMaterial((String)"MANAWEAVE", (int)5, (int[])new int[]{1, 2, 2, 1}, (int)18);
    public static EnumRarity rarityRelic = EnumHelper.addRarity((String)"RELIC", (EnumChatFormatting)EnumChatFormatting.GOLD, (String)"Relic");
    public static KnowledgeType basicKnowledge;
    public static KnowledgeType elvenKnowledge;
    public static KnowledgeType relicKnowledge;
    public static LexiconCategory categoryBasics;
    public static LexiconCategory categoryMana;
    public static LexiconCategory categoryFunctionalFlowers;
    public static LexiconCategory categoryGenerationFlowers;
    public static LexiconCategory categoryDevices;
    public static LexiconCategory categoryTools;
    public static LexiconCategory categoryBaubles;
    public static LexiconCategory categoryEnder;
    public static LexiconCategory categoryAlfhomancy;
    public static LexiconCategory categoryMisc;
    public static Brew fallbackBrew;
    public static IInternalMethodHandler internalHandler;

    public static KnowledgeType registerKnowledgeType(String id, EnumChatFormatting color, boolean autoUnlock) {
        KnowledgeType type = new KnowledgeType(id, color, autoUnlock);
        knowledgeTypes.put(id, type);
        return type;
    }

    public static Brew registerBrew(Brew brew) {
        brewMap.put(brew.getKey(), brew);
        return brew;
    }

    public static Brew getBrewFromKey(String key) {
        if (brewMap.containsKey(key)) {
            return brewMap.get(key);
        }
        return fallbackBrew;
    }

    public static void registerDisposableBlock(String oreDictName) {
        disposableBlocks.add(oreDictName);
    }

    public static void registerSemiDisposableBlock(String oreDictName) {
        semiDisposableBlocks.add(oreDictName);
    }

    public static Block registerPaintableBlock(Block paintable) {
        paintableBlocks.add(paintable);
        return paintable;
    }

    public static void blacklistEntityFromGravityRod(Class entity) {
        gravityRodBlacklist.add(entity);
    }

    public static boolean isEntityBlacklistedFromGravityRod(Class entity) {
        return gravityRodBlacklist.contains(entity);
    }

    public static void blacklistItemFromMagnet(ItemStack stack) {
        String key = BotaniaAPI.getMagnetKey(stack);
        magnetBlacklist.add(key);
    }

    public static void blacklistBlockFromMagnet(Block block, int meta) {
        String key = BotaniaAPI.getMagnetKey(block, meta);
        magnetBlacklist.add(key);
    }

    public static boolean isItemBlacklistedFromMagnet(ItemStack stack) {
        return BotaniaAPI.isItemBlacklistedFromMagnet(stack, 0);
    }

    public static boolean isItemBlacklistedFromMagnet(ItemStack stack, int recursion) {
        ItemStack copy;
        boolean general;
        if (recursion > 5) {
            return false;
        }
        if (stack.func_77960_j() != Short.MAX_VALUE && (general = BotaniaAPI.isItemBlacklistedFromMagnet(copy = new ItemStack(stack.func_77973_b(), 0, Short.MAX_VALUE), recursion + 1))) {
            return true;
        }
        String key = BotaniaAPI.getMagnetKey(stack);
        return magnetBlacklist.contains(key);
    }

    public static boolean isBlockBlacklistedFromMagnet(Block block, int meta) {
        return BotaniaAPI.isBlockBlacklistedFromMagnet(block, meta, 0);
    }

    public static boolean isBlockBlacklistedFromMagnet(Block block, int meta, int recursion) {
        boolean general;
        if (recursion >= 5) {
            return false;
        }
        if (meta != Short.MAX_VALUE && (general = BotaniaAPI.isBlockBlacklistedFromMagnet(block, Short.MAX_VALUE, recursion + 1))) {
            return true;
        }
        String key = BotaniaAPI.getMagnetKey(block, meta);
        return magnetBlacklist.contains(key);
    }

    public static RecipePetals registerPetalRecipe(ItemStack output, Object ... inputs) {
        RecipePetals recipe = new RecipePetals(output, inputs);
        petalRecipes.add(recipe);
        return recipe;
    }

    public static RecipePureDaisy registerPureDaisyRecipe(Object input, Block output, int outputMeta) {
        RecipePureDaisy recipe = new RecipePureDaisy(input, output, outputMeta);
        pureDaisyRecipes.add(recipe);
        return recipe;
    }

    public static RecipeRuneAltar registerRuneAltarRecipe(ItemStack output, int mana, Object ... inputs) {
        RecipeRuneAltar recipe = new RecipeRuneAltar(output, mana, inputs);
        runeAltarRecipes.add(recipe);
        return recipe;
    }

    public static RecipeManaInfusion registerManaInfusionRecipe(ItemStack output, Object input, int mana) {
        RecipeManaInfusion recipe = new RecipeManaInfusion(output, input, mana);
        manaInfusionRecipes.add(recipe);
        return recipe;
    }

    public static RecipeManaInfusion registerManaAlchemyRecipe(ItemStack output, Object input, int mana) {
        RecipeManaInfusion recipe = BotaniaAPI.registerManaInfusionRecipe(output, input, mana);
        recipe.setAlchemy(true);
        return recipe;
    }

    public static RecipeManaInfusion registerManaConjurationRecipe(ItemStack output, Object input, int mana) {
        RecipeManaInfusion recipe = BotaniaAPI.registerManaInfusionRecipe(output, input, mana);
        recipe.setConjuration(true);
        return recipe;
    }

    public static RecipeElvenTrade registerElvenTradeRecipe(ItemStack output, Object ... inputs) {
        RecipeElvenTrade recipe = new RecipeElvenTrade(output, inputs);
        elvenTradeRecipes.add(recipe);
        return recipe;
    }

    public static RecipeBrew registerBrewRecipe(Brew brew, Object ... inputs) {
        RecipeBrew recipe = new RecipeBrew(brew, inputs);
        brewRecipes.add(recipe);
        return recipe;
    }

    public static void registerSubTile(String key, Class<? extends SubTileEntity> subtileClass) {
        subTiles.put((Object)key, subtileClass);
        subTileMods.put(key, Loader.instance().activeModContainer().getModId());
    }

    public static RecipeManaInfusion registerMiniSubTile(String key, Class<? extends SubTileEntity> subtileClass, String original) {
        BotaniaAPI.registerSubTile(key, subtileClass);
        miniFlowers.put((Object)original, (Object)key);
        RecipeMiniFlower recipe = new RecipeMiniFlower(key, original, 2500);
        manaInfusionRecipes.add(recipe);
        miniFlowerRecipes.add(recipe);
        return recipe;
    }

    public static void registerSubTileSignature(Class<? extends SubTileEntity> subtileClass, SubTileSignature signature) {
        subTileSignatures.put(subtileClass, signature);
    }

    public static SubTileSignature getSignatureForClass(Class<? extends SubTileEntity> subtileClass) {
        if (!subTileSignatures.containsKey(subtileClass)) {
            BotaniaAPI.registerSubTileSignature(subtileClass, new BasicSignature((String)subTiles.inverse().get(subtileClass)));
        }
        return subTileSignatures.get(subtileClass);
    }

    public static SubTileSignature getSignatureForName(String name) {
        Class subtileClass = (Class)subTiles.get((Object)name);
        return BotaniaAPI.getSignatureForClass(subtileClass);
    }

    public static void addSubTileToCreativeMenu(String key) {
        subtilesForCreativeMenu.add(key);
    }

    public static void addCategory(LexiconCategory category) {
        categories.add(category);
    }

    public static List<LexiconCategory> getAllCategories() {
        return categories;
    }

    public static List<LexiconEntry> getAllEntries() {
        return allEntries;
    }

    public static void addEntry(LexiconEntry entry, LexiconCategory category) {
        allEntries.add(entry);
        category.entries.add(entry);
    }

    public static void addOreWeight(String ore, int weight) {
        oreWeights.put(ore, weight);
    }

    public static void addOreWeightNether(String ore, int weight) {
        if (ore.contains("Nether") && OreDictionary.getOres((String)ore.replace("Nether", "")).size() == 0) {
            return;
        }
        oreWeightsNether.put(ore, weight);
    }

    public static int getOreWeight(String ore) {
        return oreWeights.get(ore);
    }

    public static int getOreWeightNether(String ore) {
        return oreWeightsNether.get(ore);
    }

    public static void addSeed(Item item, Block block) {
        seeds.put(item, block);
    }

    public static void blackListItemFromLoonium(Item item) {
        looniumBlacklist.add(item);
    }

    public static IRecipe getLatestAddedRecipe() {
        List list = CraftingManager.func_77594_a().func_77592_b();
        return (IRecipe)list.get(list.size() - 1);
    }

    public static List<IRecipe> getLatestAddedRecipes(int x) {
        List list = CraftingManager.func_77594_a().func_77592_b();
        ArrayList<IRecipe> newList = new ArrayList<IRecipe>();
        for (int i = x - 1; i >= 0; --i) {
            newList.add((IRecipe)list.get(list.size() - 1 - i));
        }
        return newList;
    }

    public static void registerModWiki(String mod, IWikiProvider provider) {
        WikiHooks.registerModWiki(mod, provider);
    }

    public static Class<? extends SubTileEntity> getSubTileMapping(String key) {
        if (!subTiles.containsKey((Object)key)) {
            key = "";
        }
        return (Class)subTiles.get((Object)key);
    }

    public static String getSubTileStringMapping(Class<? extends SubTileEntity> clazz) {
        return (String)subTiles.inverse().get(clazz);
    }

    public static Set<String> getAllSubTiles() {
        return subTiles.keySet();
    }

    private static String getMagnetKey(ItemStack stack) {
        if (stack == null) {
            return "";
        }
        return "i_" + stack.func_77973_b().func_77658_a() + "@" + stack.func_77960_j();
    }

    private static String getMagnetKey(Block block, int meta) {
        return "bm_" + block.func_149739_a() + "@" + meta;
    }

    static {
        fallbackBrew = new Brew("fallback", "botania.brew.fallback", 0, 0, new PotionEffect[0]);
        BotaniaAPI.registerSubTile("", DummySubTile.class);
        basicKnowledge = BotaniaAPI.registerKnowledgeType("minecraft", EnumChatFormatting.RESET, true);
        elvenKnowledge = BotaniaAPI.registerKnowledgeType("alfheim", EnumChatFormatting.DARK_GREEN, false);
        relicKnowledge = BotaniaAPI.registerKnowledgeType("relic", EnumChatFormatting.DARK_PURPLE, false);
        BotaniaAPI.addOreWeight("oreAluminum", 3940);
        BotaniaAPI.addOreWeight("oreAmber", 2075);
        BotaniaAPI.addOreWeight("oreApatite", 1595);
        BotaniaAPI.addOreWeight("oreBlueTopaz", 3195);
        BotaniaAPI.addOreWeight("oreCertusQuartz", 3975);
        BotaniaAPI.addOreWeight("oreChimerite", 3970);
        BotaniaAPI.addOreWeight("oreCinnabar", 2585);
        BotaniaAPI.addOreWeight("oreCoal", 46525);
        BotaniaAPI.addOreWeight("oreCopper", 8325);
        BotaniaAPI.addOreWeight("oreDark", 1350);
        BotaniaAPI.addOreWeight("oreDarkIron", 1700);
        BotaniaAPI.addOreWeight("oreFzDarkIron", 1700);
        BotaniaAPI.addOreWeight("oreDiamond", 1265);
        BotaniaAPI.addOreWeight("oreEmerald", 780);
        BotaniaAPI.addOreWeight("oreGalena", 1000);
        BotaniaAPI.addOreWeight("oreGold", 2970);
        BotaniaAPI.addOreWeight("oreInfusedAir", 925);
        BotaniaAPI.addOreWeight("oreInfusedEarth", 925);
        BotaniaAPI.addOreWeight("oreInfusedEntropy", 925);
        BotaniaAPI.addOreWeight("oreInfusedFire", 925);
        BotaniaAPI.addOreWeight("oreInfusedOrder", 925);
        BotaniaAPI.addOreWeight("oreInfusedWater", 925);
        BotaniaAPI.addOreWeight("oreIron", 20665);
        BotaniaAPI.addOreWeight("oreLapis", 1285);
        BotaniaAPI.addOreWeight("oreLead", 7985);
        BotaniaAPI.addOreWeight("oreMCropsEssence", 3085);
        BotaniaAPI.addOreWeight("oreMithril", 8);
        BotaniaAPI.addOreWeight("oreNickel", 2275);
        BotaniaAPI.addOreWeight("oreOlivine", 1100);
        BotaniaAPI.addOreWeight("orePlatinum", 365);
        BotaniaAPI.addOreWeight("oreRedstone", 6885);
        BotaniaAPI.addOreWeight("oreRuby", 1100);
        BotaniaAPI.addOreWeight("oreSapphire", 1100);
        BotaniaAPI.addOreWeight("oreSilver", 6300);
        BotaniaAPI.addOreWeight("oreSulfur", 1105);
        BotaniaAPI.addOreWeight("oreTin", 9450);
        BotaniaAPI.addOreWeight("oreUranium", 1337);
        BotaniaAPI.addOreWeight("oreVinteum", 5925);
        BotaniaAPI.addOreWeight("oreYellorite", 3520);
        BotaniaAPI.addOreWeight("oreZinc", 6485);
        BotaniaAPI.addOreWeight("oreMythril", 6485);
        BotaniaAPI.addOreWeight("oreAdamantium", 2275);
        BotaniaAPI.addOreWeight("oreTungsten", 3520);
        BotaniaAPI.addOreWeightNether("oreQuartz", 19600);
        BotaniaAPI.addOreWeightNether("oreCobalt", 500);
        BotaniaAPI.addOreWeightNether("oreArdite", 500);
        BotaniaAPI.addOreWeightNether("oreFirestone", 5);
        BotaniaAPI.addOreWeightNether("oreNetherCoal", 17000);
        BotaniaAPI.addOreWeightNether("oreNetherCopper", 4700);
        BotaniaAPI.addOreWeightNether("oreNetherDiamond", 175);
        BotaniaAPI.addOreWeightNether("oreNetherEssence", 2460);
        BotaniaAPI.addOreWeightNether("oreNetherGold", 3635);
        BotaniaAPI.addOreWeightNether("oreNetherIron", 5790);
        BotaniaAPI.addOreWeightNether("oreNetherLapis", 3250);
        BotaniaAPI.addOreWeightNether("oreNetherLead", 2790);
        BotaniaAPI.addOreWeightNether("oreNetherNickel", 1790);
        BotaniaAPI.addOreWeightNether("oreNetherPlatinum", 170);
        BotaniaAPI.addOreWeightNether("oreNetherRedstone", 5600);
        BotaniaAPI.addOreWeightNether("oreNetherSilver", 1550);
        BotaniaAPI.addOreWeightNether("oreNetherSteel", 1690);
        BotaniaAPI.addOreWeightNether("oreNetherTin", 3750);
        BotaniaAPI.addOreWeightNether("oreFyrite", 1000);
        BotaniaAPI.addOreWeightNether("oreAshstone", 1000);
        BotaniaAPI.addOreWeightNether("oreDragonstone", 175);
        BotaniaAPI.addOreWeightNether("oreArgonite", 1000);
        BotaniaAPI.addOreWeightNether("oreOnyx", 500);
        BotaniaAPI.addOreWeightNether("oreHaditeCoal", 500);
        BotaniaAPI.addSeed(Items.field_151014_N, Blocks.field_150464_aj);
        BotaniaAPI.addSeed(Items.field_151174_bG, Blocks.field_150469_bN);
        BotaniaAPI.addSeed(Items.field_151172_bF, Blocks.field_150459_bM);
        BotaniaAPI.addSeed(Items.field_151075_bm, Blocks.field_150388_bm);
        BotaniaAPI.addSeed(Items.field_151080_bb, Blocks.field_150393_bb);
        BotaniaAPI.addSeed(Items.field_151081_bc, Blocks.field_150394_bc);
        BotaniaAPI.registerModWiki("Minecraft", new SimpleWikiProvider("Minecraft Wiki", "http://minecraft.gamepedia.com/%s"));
        SimpleWikiProvider technicWiki = new SimpleWikiProvider("Technic Wiki", "http://wiki.technicpack.net/%s");
        SimpleWikiProvider mekanismWiki = new SimpleWikiProvider("Mekanism Wiki", "http://wiki.aidancbrady.com/wiki/%s");
        SimpleWikiProvider buildcraftWiki = new SimpleWikiProvider("BuildCraft Wiki", "http://www.mod-buildcraft.com/wiki/doku.php?id=%s");
        BotaniaAPI.registerModWiki("Mekanism", mekanismWiki);
        BotaniaAPI.registerModWiki("MekanismGenerators", mekanismWiki);
        BotaniaAPI.registerModWiki("MekanismTools", mekanismWiki);
        BotaniaAPI.registerModWiki("EnderIO", new SimpleWikiProvider("EnderIO Wiki", "http://wiki.enderio.com/%s"));
        BotaniaAPI.registerModWiki("TropiCraft", new SimpleWikiProvider("Tropicraft Wiki", "http://wiki.tropicraft.net/wiki/%s"));
        BotaniaAPI.registerModWiki("RandomThings", new SimpleWikiProvider("Random Things Wiki", "http://randomthingsminecraftmod.wikispaces.com/%s"));
        BotaniaAPI.registerModWiki("Witchery", new SimpleWikiProvider("Witchery Wiki", "https://sites.google.com/site/witcherymod/%s", "-", true));
        BotaniaAPI.registerModWiki("AppliedEnergistics2", new SimpleWikiProvider("AE2 Wiki", "http://ae-mod.info/%s"));
        BotaniaAPI.registerModWiki("BigReactors", technicWiki);
        BotaniaAPI.registerModWiki("BuildCraft|Core", buildcraftWiki);
        BotaniaAPI.registerModWiki("BuildCraft|Builders", buildcraftWiki);
        BotaniaAPI.registerModWiki("BuildCraft|Energy", buildcraftWiki);
        BotaniaAPI.registerModWiki("BuildCraft|Factory", buildcraftWiki);
        BotaniaAPI.registerModWiki("BuildCraft|Silicon", buildcraftWiki);
        BotaniaAPI.registerModWiki("BuildCraft|Transport", buildcraftWiki);
        BotaniaAPI.registerModWiki("ArsMagica2", new SimpleWikiProvider("ArsMagica2 Wiki", "http://wiki.arsmagicamod.com/wiki/%s"));
        BotaniaAPI.registerModWiki("PneumaticCraft", new SimpleWikiProvider("PneumaticCraft Wiki", "http://www.minemaarten.com/wikis/pneumaticcraft-wiki/pneumaticcraft-wiki-%s"));
        BotaniaAPI.registerModWiki("StevesCarts2", new SimpleWikiProvider("Steve's Carts Wiki", "http://stevescarts2.wikispaces.com/%s"));
        BotaniaAPI.registerModWiki("GanysSurface", new SimpleWikiProvider("Gany's Surface Wiki", "http://ganys-surface.wikia.com/wiki/%s"));
        BotaniaAPI.registerModWiki("GanysNether", new SimpleWikiProvider("Gany's Nether Wiki", "http://ganys-nether.wikia.com/wiki/%s"));
        BotaniaAPI.registerModWiki("GanysEnd", new SimpleWikiProvider("Gany's End Wiki", "http://ganys-end.wikia.com/wiki/%s"));
        BotaniaAPI.registerPaintableBlock((Block)Blocks.field_150399_cn);
        BotaniaAPI.registerPaintableBlock((Block)Blocks.field_150397_co);
        BotaniaAPI.registerPaintableBlock(Blocks.field_150406_ce);
        BotaniaAPI.registerPaintableBlock(Blocks.field_150325_L);
        BotaniaAPI.registerPaintableBlock(Blocks.field_150404_cg);
        BotaniaAPI.registerDisposableBlock("dirt");
        BotaniaAPI.registerDisposableBlock("sand");
        BotaniaAPI.registerDisposableBlock("gravel");
        BotaniaAPI.registerDisposableBlock("cobblestone");
        BotaniaAPI.registerDisposableBlock("netherrack");
        BotaniaAPI.registerSemiDisposableBlock("stoneAndesite");
        BotaniaAPI.registerSemiDisposableBlock("stoneBasalt");
        BotaniaAPI.registerSemiDisposableBlock("stoneDiorite");
        BotaniaAPI.registerSemiDisposableBlock("stoneGranite");
        internalHandler = new DummyMethodHandler();
    }
}

