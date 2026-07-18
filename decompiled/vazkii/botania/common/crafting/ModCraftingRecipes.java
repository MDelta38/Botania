/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLLog
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.CraftingManager
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 *  org.apache.logging.log4j.Level
 */
package vazkii.botania.common.crafting;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import org.apache.logging.log4j.Level;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.common.Botania;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.tile.TileCraftCrate;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.item.ItemSignalFlare;
import vazkii.botania.common.item.ItemTwigWand;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

public final class ModCraftingRecipes {
    public static IRecipe recipeLexicon;
    public static List<IRecipe> recipesPetals;
    public static List<IRecipe> recipesDyes;
    public static List<IRecipe> recipesPetalBlocks;
    public static IRecipe recipePestleAndMortar;
    public static List<IRecipe> recipesTwigWand;
    public static List<IRecipe> recipesApothecary;
    public static List<IRecipe> recipesSpreader;
    public static List<IRecipe> recipesManaLens;
    public static IRecipe recipePool;
    public static IRecipe recipePoolDiluted;
    public static IRecipe recipePoolFabulous;
    public static List<IRecipe> recipesRuneAltar;
    public static IRecipe recipeLensVelocity;
    public static IRecipe recipeLensPotency;
    public static IRecipe recipeLensResistance;
    public static IRecipe recipeLensEfficiency;
    public static IRecipe recipeLensBounce;
    public static IRecipe recipeLensGravity;
    public static IRecipe recipeLensBore;
    public static IRecipe recipeLensDamaging;
    public static IRecipe recipeLensPhantom;
    public static IRecipe recipeLensMagnet;
    public static IRecipe recipeLensExplosive;
    public static List<IRecipe> recipesUnstableBlocks;
    public static IRecipe recipePylon;
    public static IRecipe recipeDistributor;
    public static IRecipe recipeLivingrockDecor1;
    public static IRecipe recipeLivingrockDecor2;
    public static IRecipe recipeLivingrockDecor3;
    public static IRecipe recipeLivingrockDecor4;
    public static IRecipe recipeLivingwoodDecor1;
    public static IRecipe recipeLivingwoodDecor2;
    public static IRecipe recipeLivingwoodDecor3;
    public static IRecipe recipeLivingwoodDecor4;
    public static IRecipe recipeLivingwoodDecor5;
    public static List<IRecipe> recipesManaBeacons;
    public static List<IRecipe> recipesSignalFlares;
    public static IRecipe recipeManaVoid;
    public static List<IRecipe> recipesManaTablet;
    public static IRecipe recipeManaDetector;
    public static IRecipe recipeManaBlaster;
    public static IRecipe recipeTurntable;
    public static IRecipe recipeFertilizerPowder;
    public static IRecipe recipeFerilizerDye;
    public static IRecipe recipeLivingwoodTwig;
    public static IRecipe recipeDirtRod;
    public static IRecipe recipeTerraformRod;
    public static IRecipe recipeRedstoneSpreader;
    public static IRecipe recipeManaMirror;
    public static IRecipe recipeManasteelHelm;
    public static IRecipe recipeManasteelChest;
    public static IRecipe recipeManasteelLegs;
    public static IRecipe recipeManasteelBoots;
    public static IRecipe recipeManasteelPick;
    public static IRecipe recipeManasteelShovel;
    public static IRecipe recipeManasteelAxe;
    public static IRecipe recipeManasteelShears;
    public static IRecipe recipeManasteelSword;
    public static IRecipe recipeGrassHorn;
    public static IRecipe recipeTerrasteelHelm;
    public static IRecipe recipeTerrasteelChest;
    public static IRecipe recipeTerrasteelLegs;
    public static IRecipe recipeTerrasteelBoots;
    public static IRecipe recipeTerraSword;
    public static IRecipe recipeTinyPlanet;
    public static IRecipe recipeManaRing;
    public static IRecipe recipeAuraRing;
    public static IRecipe recipeGreaterManaRing;
    public static IRecipe recipeGreaterAuraRing;
    public static IRecipe recipeTravelBelt;
    public static IRecipe recipeKnocbackBelt;
    public static IRecipe recipeIcePendant;
    public static IRecipe recipeFirePendant;
    public static IRecipe recipeGoldenLaurel;
    public static IRecipe recipeTinyPlanetBlock;
    public static IRecipe recipeAlchemyCatalyst;
    public static IRecipe recipeOpenCrate;
    public static IRecipe recipeForestEye;
    public static IRecipe recipeRedstoneRoot;
    public static IRecipe recipeForestDrum;
    public static IRecipe recipeWaterRing;
    public static IRecipe recipeMiningRing;
    public static IRecipe recipeMagnetRing;
    public static IRecipe recipeTerraPick;
    public static IRecipe recipeDivaCharm;
    public static IRecipe recipeFlightTiara;
    public static List<IRecipe> recipesShinyFlowers;
    public static IRecipe recipePlatform;
    public static IRecipe recipeEnderDagger;
    public static IRecipe recipeDarkQuartz;
    public static IRecipe recipeBlazeQuartz;
    public static List<IRecipe> recipesLavenderQuartz;
    public static IRecipe recipeRedQuartz;
    public static IRecipe recipeSunnyQuartz;
    public static IRecipe recipeAlfPortal;
    public static IRecipe recipeNaturaPylon;
    public static IRecipe recipeWaterRod;
    public static IRecipe recipeElementiumHelm;
    public static IRecipe recipeElementiumChest;
    public static IRecipe recipeElementiumLegs;
    public static IRecipe recipeElementiumBoots;
    public static IRecipe recipeElementiumPick;
    public static IRecipe recipeElementiumShovel;
    public static IRecipe recipeElementiumAxe;
    public static IRecipe recipeElementiumShears;
    public static IRecipe recipeElementiumSword;
    public static IRecipe recipeOpenBucket;
    public static IRecipe recipeConjurationCatalyst;
    public static IRecipe recipeSpawnerMover;
    public static IRecipe recipePixieRing;
    public static IRecipe recipeSuperTravelBelt;
    public static IRecipe recipeRainbowRod;
    public static IRecipe recipeSpectralPlatform;
    public static List<IRecipe> recipesDreamwoodSpreader;
    public static IRecipe recipeTornadoRod;
    public static IRecipe recipeFireRod;
    public static IRecipe recipeVineBall;
    public static IRecipe recipeSlingshot;
    public static IRecipe recipeMossStone;
    public static IRecipe recipePrismarine;
    public static IRecipe recipePrismarineBrick;
    public static IRecipe recipeDarkPrismarine;
    public static IRecipe recipeSeaLamp;
    public static IRecipe recipeLensInfluence;
    public static IRecipe recipeLensWeight;
    public static IRecipe recipeLensPaint;
    public static IRecipe recipeLensWarp;
    public static IRecipe recipeLensRedirect;
    public static IRecipe recipeLensFirework;
    public static IRecipe recipeLensFlare;
    public static List<IRecipe> recipesMiniIsland;
    public static IRecipe recipeGaiaPylon;
    public static IRecipe recipeGatherDrum;
    public static IRecipe recipeLensFire;
    public static IRecipe recipeLensPiston;
    public static List<IRecipe> recipesLaputaShard;
    public static List<IRecipe> recipesLaputaShardUpgrade;
    public static IRecipe recipeVirusZombie;
    public static IRecipe recipeVirusSkeleton;
    public static IRecipe recipeReachRing;
    public static IRecipe recipeSkyDirtRod;
    public static IRecipe recipeSpawnerClaw;
    public static IRecipe recipeCraftCrate;
    public static IRecipe recipePlaceholder;
    public static IRecipe recipeReedBlock;
    public static IRecipe recipeThatch;
    public static IRecipe recipeNetherBrick;
    public static IRecipe recipeSoulBrick;
    public static IRecipe recipeSnowBrick;
    public static IRecipe recipeRoofTile;
    public static IRecipe recipeAzulejo;
    public static List<IRecipe> recipesAzulejoCycling;
    public static IRecipe recipeEnderEyeBlock;
    public static IRecipe recipeItemFinder;
    public static IRecipe recipeSuperLavaPendant;
    public static IRecipe recipeEnderHand;
    public static IRecipe recipeGlassPick;
    public static IRecipe recipeStarfield;
    public static List<IRecipe> recipesSpark;
    public static List<IRecipe> recipesSparkUpgrades;
    public static IRecipe recipeLeafHorn;
    public static IRecipe recipeDiviningRod;
    public static List<IRecipe> recipesWings;
    public static IRecipe recipeRFGenerator;
    public static IRecipe recipeGravityRod;
    public static IRecipe recipeRegenIvy;
    public static IRecipe recipeUltraSpreader;
    public static IRecipe recipeHelmetOfRevealing;
    public static IRecipe recipeVial;
    public static IRecipe recipeFlask;
    public static IRecipe recipeBrewery;
    public static IRecipe recipeBloodPendant;
    public static IRecipe recipeTerraPlate;
    public static IRecipe recipeRedString;
    public static IRecipe recipeRedStringContainer;
    public static IRecipe recipeRedStringDispenser;
    public static IRecipe recipeRedStringFertilizer;
    public static IRecipe recipeRedStringComparator;
    public static IRecipe recipeRedStringRelay;
    public static IRecipe recipeRedStringInterceptor;
    public static IRecipe recipeMissileRod;
    public static IRecipe recipeHolyCloak;
    public static IRecipe recipeUnholyCloak;
    public static IRecipe recipeCraftingHalo;
    public static List<IRecipe> recipesLensFlash;
    public static IRecipe recipePrism;
    public static IRecipe recipeDirtPath;
    public static IRecipe recipeDreamwoodTwig;
    public static IRecipe recipeMonocle;
    public static IRecipe recipeClip;
    public static IRecipe recipeCobbleRod;
    public static IRecipe recipeSmeltRod;
    public static IRecipe recipeWorldSeed;
    public static IRecipe recipeSpellCloth;
    public static IRecipe recipeThornChakram;
    public static IRecipe recipeDirtPathSlab;
    public static List<IRecipe> recipesPatterns;
    public static IRecipe recipeGaiaIngot;
    public static IRecipe recipeCorporeaSpark;
    public static IRecipe recipeMasterCorporeaSpark;
    public static IRecipe recipeCorporeaIndex;
    public static IRecipe recipeCorporeaFunnel;
    public static IRecipe recipeCorporeaInterceptor;
    public static IRecipe recipeEndStoneBricks;
    public static IRecipe recipeEndStoneChiseledBricks;
    public static IRecipe recipeEnderBricks;
    public static IRecipe recipePillarEnderBricks;
    public static IRecipe recipeLivingwoodBow;
    public static IRecipe recipeCrystalBow;
    public static List<IRecipe> recipesCosmeticItems;
    public static List<IRecipe> recipesMushrooms;
    public static IRecipe recipeSwapRing;
    public static IRecipe recipeSnowHorn;
    public static IRecipe recipeFlowerBag;
    public static IRecipe recipePhantomInk;
    public static IRecipe recipePoolCart;
    public static IRecipe recipePump;
    public static List<IRecipe> recipesPetalsDouble;
    public static IRecipe recipeKeepIvy;
    public static IRecipe recipeBlackHoleTalisman;
    public static List<IRecipe> recipe18StonePolish;
    public static List<IRecipe> recipe18StoneBrick;
    public static List<IRecipe> recipe18StoneChisel;
    public static IRecipe recipeBlazeBlock;
    public static List<IRecipe> recipesAltarMeta;
    public static IRecipe recipeCorporeaCrystalCube;
    public static IRecipe recipeTemperanceStone;
    public static IRecipe recipeIncenseStick;
    public static IRecipe recipeIncensePlate;
    public static IRecipe recipeTerraAxe;
    public static IRecipe recipeHourglass;
    public static IRecipe recipeGhostRail;
    public static IRecipe recipeCanopyDrum;
    public static IRecipe recipeSparkChanger;
    public static IRecipe recipeCocoon;
    public static IRecipe recipeLuminizer;
    public static IRecipe recipeDetectorLuminizer;
    public static IRecipe recipeLuminizerLauncher;
    public static IRecipe recipeObedienceStick;
    public static IRecipe recipeCacophonium;
    public static IRecipe recipeManaBomb;
    public static IRecipe recipeCobweb;
    public static IRecipe recipeSlimeBottle;
    public static IRecipe recipeStarSword;
    public static IRecipe recipeExchangeRod;
    public static IRecipe recipeGreaterMagnetRing;
    public static IRecipe recipeFireChakram;
    public static IRecipe recipeThunderSword;
    public static IRecipe recipeBellows;
    public static IRecipe recipeManaweaveCloth;
    public static IRecipe recipeManaweaveHelm;
    public static IRecipe recipeManaweaveChest;
    public static IRecipe recipeManaweaveLegs;
    public static IRecipe recipeManaweaveBoots;
    public static IRecipe recipeBifrost;
    public static IRecipe recipeShimmerrock;
    public static IRecipe recipeShimmerwoodPlanks;
    public static IRecipe recipeAutocraftingHalo;
    public static List<IRecipe> recipesPavement;
    public static IRecipe recipeCellBlock;
    public static IRecipe recipeCorporeaRetainer;
    public static IRecipe recipeTeruTeruBozu;
    public static IRecipe recipeAvatar;
    public static IRecipe recipeSextant;
    public static List<IRecipe> recipesAltGrassSeeds;
    public static IRecipe recipeSpeedUpBelt;
    public static IRecipe recipeBaubleCase;
    public static IRecipe recipeRootToSapling;
    public static IRecipe recipeRootToFertilizer;
    public static IRecipe recipePebbleCobblestone;
    public static IRecipe recipeMagmaToSlimeball;
    public static IRecipe recipeFelPumpkin;
    public static IRecipe recipeEndPortal;

    /*
     * Opcode count of 13402 triggered aggressive code reduction.  Override with --aggressivesizethreshold.
     */
    public static void init() {
        int i;
        int i2;
        int recipeListSize = CraftingManager.func_77594_a().func_77592_b().size();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lexicon), "treeSapling", Items.field_151122_aG);
        recipeLexicon = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.petal, 2, i2), LibOreDict.FLOWER[i2]);
        }
        recipesPetals = BotaniaAPI.getLatestAddedRecipes(16);
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.dye, 1, i2), LibOreDict.PETAL[i2], "pestleAndMortar");
        }
        recipesDyes = BotaniaAPI.getLatestAddedRecipes(16);
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.petalBlock, 1, i2), "PPP", "PPP", "PPP", Character.valueOf('P'), LibOreDict.PETAL[i2]);
        }
        recipesPetalBlocks = BotaniaAPI.getLatestAddedRecipes(16);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.pestleAndMortar), " S", "W ", "B ", Character.valueOf('S'), "stickWood", Character.valueOf('W'), "plankWood", Character.valueOf('B'), Items.field_151054_z);
        recipePestleAndMortar = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            for (int j = 0; j < 16; ++j) {
                ModCraftingRecipes.addOreDictRecipe(ItemTwigWand.forColors(i2, j), " AS", " SB", "S  ", Character.valueOf('A'), LibOreDict.PETAL[i2], Character.valueOf('B'), LibOreDict.PETAL[j], Character.valueOf('S'), "livingwoodTwig");
            }
        }
        recipesTwigWand = BotaniaAPI.getLatestAddedRecipes(256);
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.altar), "SPS", " C ", "CCC", Character.valueOf('S'), "slabCobblestone", Character.valueOf('P'), LibOreDict.PETAL[i2], Character.valueOf('C'), "cobblestone");
        }
        recipesApothecary = BotaniaAPI.getLatestAddedRecipes(16);
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.spreader), "WWW", "GP ", "WWW", Character.valueOf('W'), "livingwood", Character.valueOf('P'), LibOreDict.PETAL[i2], Character.valueOf('G'), Botania.gardenOfGlassLoaded ? "livingwood" : "ingotGold");
        }
        recipesSpreader = BotaniaAPI.getLatestAddedRecipes(16);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.lens), " S ", "SGS", " S ", Character.valueOf('S'), "ingotManasteel", Character.valueOf('G'), "paneGlassColorless");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.lens), " S ", "SGS", " S ", Character.valueOf('S'), "ingotManasteel", Character.valueOf('G'), "blockGlassColorless");
        recipesManaLens = BotaniaAPI.getLatestAddedRecipes(2);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.pool), "R R", "RRR", Character.valueOf('R'), "livingrock");
        recipePool = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.pool, 1, 2), "R R", "RRR", Character.valueOf('R'), new ItemStack(ModFluffBlocks.livingrockSlab));
        recipePoolDiluted = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.pool, 1, 3), "R R", "RRR", Character.valueOf('R'), new ItemStack(ModBlocks.shimmerrock));
        recipePoolFabulous = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.runeAltar), "SSS", "SPS", Character.valueOf('S'), "livingrock", Character.valueOf('P'), "manaPearl");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.runeAltar), "SSS", "SDS", Character.valueOf('S'), "livingrock", Character.valueOf('D'), "manaDiamond");
        recipesRuneAltar = BotaniaAPI.getLatestAddedRecipes(2);
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 1), new ItemStack(ModItems.lens), LibOreDict.RUNE[3]);
        recipeLensVelocity = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 2), new ItemStack(ModItems.lens), LibOreDict.RUNE[1]);
        recipeLensPotency = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 3), new ItemStack(ModItems.lens), LibOreDict.RUNE[2]);
        recipeLensResistance = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 4), new ItemStack(ModItems.lens), LibOreDict.RUNE[0]);
        recipeLensEfficiency = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 5), new ItemStack(ModItems.lens), LibOreDict.RUNE[5]);
        recipeLensBounce = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 6), new ItemStack(ModItems.lens), LibOreDict.RUNE[7]);
        recipeLensGravity = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.lens, 1, 7), " P ", "ALA", " R ", Character.valueOf('P'), new ItemStack((Block)Blocks.field_150331_J), Character.valueOf('R'), "dustRedstone", Character.valueOf('A'), "gemLapis", Character.valueOf('L'), new ItemStack(ModItems.lens));
        recipeLensBore = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 8), new ItemStack(ModItems.lens), LibOreDict.RUNE[13]);
        recipeLensDamaging = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 9), new ItemStack(ModItems.lens), new ItemStack(ModBlocks.platform));
        recipeLensPhantom = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 10), new ItemStack(ModItems.lens), "ingotIron", "ingotGold");
        recipeLensMagnet = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 11), new ItemStack(ModItems.lens), LibOreDict.RUNE[14]);
        recipeLensExplosive = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.unstableBlock, 2, i2), "OPO", "PMP", "OPO", Character.valueOf('O'), new ItemStack(Blocks.field_150343_Z), Character.valueOf('P'), LibOreDict.PETAL[i2], Character.valueOf('M'), new ItemStack(Items.field_151079_bi));
        }
        recipesUnstableBlocks = BotaniaAPI.getLatestAddedRecipes(16);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.pylon), " G ", "MDM", " G ", Character.valueOf('G'), "ingotGold", Character.valueOf('M'), "ingotManasteel", Character.valueOf('D'), "manaDiamond");
        recipePylon = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.distributor), "RRR", "S S", "RRR", Character.valueOf('R'), "livingrock", Character.valueOf('S'), "ingotManasteel");
        recipeDistributor = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.livingrock, 4, 1), "RR", "RR", Character.valueOf('R'), "livingrock");
        recipeLivingrockDecor1 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.livingrock, 1, 2), new ItemStack(ModBlocks.livingrock, 1, 1), new ItemStack(Items.field_151014_N));
        recipeLivingrockDecor2 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.livingrock, 2, 3), new ItemStack(ModBlocks.livingrock, 1, 1), "cobblestone");
        recipeLivingrockDecor3 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.livingrock, 4, 4), "RR", "RR", Character.valueOf('R'), new ItemStack(ModBlocks.livingrock, 1, 1));
        recipeLivingrockDecor4 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.livingwood, 4, 1), "livingwood");
        recipeLivingwoodDecor1 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.livingwood, 1, 2), new ItemStack(ModBlocks.livingwood, 1, 1), new ItemStack(Items.field_151014_N));
        recipeLivingwoodDecor2 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.livingwood, 4, 3), "WW", "WW", Character.valueOf('W'), new ItemStack(ModBlocks.livingwood, 1, 1));
        recipeLivingwoodDecor3 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.livingwood, 4, 4), " W ", "W W", " W ", Character.valueOf('W'), new ItemStack(ModBlocks.livingwood, 1, 1));
        recipeLivingwoodDecor4 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.livingwood, 1, 5), "livingwood", "dustGlowstone");
        recipeLivingwoodDecor5 = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.dreamwood, 4, 1), "dreamwood");
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.dreamwood, 1, 2), new ItemStack(ModBlocks.dreamwood, 1, 1), new ItemStack(Items.field_151014_N));
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.dreamwood, 4, 3), "WW", "WW", Character.valueOf('W'), new ItemStack(ModBlocks.dreamwood, 1, 1));
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.dreamwood, 4, 4), " W ", "W W", " W ", Character.valueOf('W'), new ItemStack(ModBlocks.dreamwood, 1, 1));
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.dreamwood, 1, 5), "dreamwood", "dustGlowstone");
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.manaBeacon, 1, i2), " B ", "BPB", " B ", Character.valueOf('B'), new ItemStack(ModBlocks.unstableBlock, 1, i2), Character.valueOf('P'), "manaPearl");
        }
        recipesManaBeacons = BotaniaAPI.getLatestAddedRecipes(16);
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(ItemSignalFlare.forColor(i2), "I ", " B", "W ", Character.valueOf('B'), new ItemStack(ModBlocks.manaBeacon, 1, i2), Character.valueOf('I'), "ingotIron", Character.valueOf('W'), "livingwood");
        }
        recipesSignalFlares = BotaniaAPI.getLatestAddedRecipes(16);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.manaVoid), "SSS", "O O", "SSS", Character.valueOf('S'), "livingrock", Character.valueOf('O'), new ItemStack(Blocks.field_150343_Z));
        recipeManaVoid = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaTablet, 1, 10000), "SSS", "SPS", "SSS", Character.valueOf('S'), "livingrock", Character.valueOf('P'), "manaPearl");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaTablet, 1, 10000), "SSS", "SDS", "SSS", Character.valueOf('S'), "livingrock", Character.valueOf('D'), "manaDiamond");
        recipesManaTablet = BotaniaAPI.getLatestAddedRecipes(2);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.manaDetector), "RSR", "SCS", "RSR", Character.valueOf('R'), "dustRedstone", Character.valueOf('C'), new ItemStack(Items.field_151132_bS), Character.valueOf('S'), "livingrock");
        recipeManaDetector = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaGun), "SMD", " WT", "  W", Character.valueOf('S'), new ItemStack(ModBlocks.spreader, 1, 1), Character.valueOf('M'), LibOreDict.RUNE[8], Character.valueOf('D'), "manaDiamond", Character.valueOf('T'), new ItemStack(Blocks.field_150335_W), Character.valueOf('W'), "livingwood");
        recipeManaBlaster = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.turntable), "WWW", "WPW", "WWW", Character.valueOf('W'), "livingwood", Character.valueOf('P'), Blocks.field_150320_F);
        recipeTurntable = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.fertilizer, Botania.gardenOfGlassLoaded ? 3 : 1), (Object[])new Object[]{new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(ModItems.dye, 1, Short.MAX_VALUE), new ItemStack(ModItems.dye, 1, Short.MAX_VALUE), new ItemStack(ModItems.dye, 1, Short.MAX_VALUE), new ItemStack(ModItems.dye, 1, Short.MAX_VALUE)});
        recipeFertilizerPowder = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.fertilizer), (Object[])new Object[]{new ItemStack(Items.field_151100_aR, 1, 15), new ItemStack(Items.field_151100_aR, 1, 11), new ItemStack(Items.field_151100_aR, 1, 11), new ItemStack(Items.field_151100_aR, 1, 1), new ItemStack(Items.field_151100_aR, 1, 1)});
        recipeFerilizerDye = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 3), "W", "W", Character.valueOf('W'), "livingwood");
        recipeLivingwoodTwig = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.dirtRod), "  D", " T ", "E  ", Character.valueOf('D'), new ItemStack(Blocks.field_150346_d), Character.valueOf('T'), "livingwoodTwig", Character.valueOf('E'), LibOreDict.RUNE[2]);
        recipeDirtRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terraformRod), " WT", "ARS", "GM ", Character.valueOf('T'), "ingotTerrasteel", Character.valueOf('R'), new ItemStack(ModItems.dirtRod), Character.valueOf('G'), new ItemStack(ModItems.grassSeeds), Character.valueOf('W'), LibOreDict.RUNE[7], Character.valueOf('S'), LibOreDict.RUNE[4], Character.valueOf('M'), LibOreDict.RUNE[5], Character.valueOf('A'), LibOreDict.RUNE[6]);
        recipeTerraformRod = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(ModBlocks.spreader, 1, 1), new Object[]{new ItemStack(ModBlocks.spreader), "dustRedstone"}));
        recipeRedstoneSpreader = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaMirror), " PR", " SI", "T  ", Character.valueOf('P'), "manaPearl", Character.valueOf('R'), "livingrock", Character.valueOf('S'), "livingwoodTwig", Character.valueOf('I'), "ingotTerrasteel", Character.valueOf('T'), new ItemStack(ModItems.manaTablet, 1, Short.MAX_VALUE));
        recipeManaMirror = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelHelm), "SSS", "S S", Character.valueOf('S'), "ingotManasteel");
        recipeManasteelHelm = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelChest), "S S", "SSS", "SSS", Character.valueOf('S'), "ingotManasteel");
        recipeManasteelChest = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelLegs), "SSS", "S S", "S S", Character.valueOf('S'), "ingotManasteel");
        recipeManasteelLegs = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelBoots), "S S", "S S", Character.valueOf('S'), "ingotManasteel");
        recipeManasteelBoots = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelPick), "SSS", " T ", " T ", Character.valueOf('S'), "ingotManasteel", Character.valueOf('T'), "livingwoodTwig");
        recipeManasteelPick = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelShovel), "S", "T", "T", Character.valueOf('S'), "ingotManasteel", Character.valueOf('T'), "livingwoodTwig");
        recipeManasteelShovel = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelAxe), "SS", "TS", "T ", Character.valueOf('S'), "ingotManasteel", Character.valueOf('T'), "livingwoodTwig");
        recipeManasteelAxe = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelSword), "S", "S", "T", Character.valueOf('S'), "ingotManasteel", Character.valueOf('T'), "livingwoodTwig");
        recipeManasteelSword = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manasteelShears), "S ", " S", Character.valueOf('S'), "ingotManasteel");
        recipeManasteelShears = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.grassHorn), " W ", "WSW", "WW ", Character.valueOf('W'), "livingwood", Character.valueOf('S'), new ItemStack(ModItems.grassSeeds));
        recipeGrassHorn = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terrasteelHelmRevealing), "TRT", "SAS", " S ", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('S'), "ingotTerrasteel", Character.valueOf('R'), LibOreDict.RUNE[4], Character.valueOf('A'), new ItemStack(ModItems.manasteelHelmRevealing));
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terrasteelHelm), "TRT", "SAS", " S ", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('S'), "ingotTerrasteel", Character.valueOf('R'), LibOreDict.RUNE[4], Character.valueOf('A'), new ItemStack(ModItems.manasteelHelm));
        recipeTerrasteelHelm = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terrasteelChest), "TRT", "SAS", " S ", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('S'), "ingotTerrasteel", Character.valueOf('R'), LibOreDict.RUNE[5], Character.valueOf('A'), new ItemStack(ModItems.manasteelChest));
        recipeTerrasteelChest = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terrasteelLegs), "TRT", "SAS", " S ", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('S'), "ingotTerrasteel", Character.valueOf('R'), LibOreDict.RUNE[6], Character.valueOf('A'), new ItemStack(ModItems.manasteelLegs));
        recipeTerrasteelLegs = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terrasteelBoots), "TRT", "SAS", " S ", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('S'), "ingotTerrasteel", Character.valueOf('R'), LibOreDict.RUNE[7], Character.valueOf('A'), new ItemStack(ModItems.manasteelBoots));
        recipeTerrasteelBoots = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terraSword), "I", "I", "S", Character.valueOf('I'), "ingotTerrasteel", Character.valueOf('S'), "livingwoodTwig");
        recipeTerraSword = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.tinyPlanet), "LSL", "SPS", "LSL", Character.valueOf('S'), "stone", Character.valueOf('L'), "livingrock", Character.valueOf('P'), "manaPearl");
        recipeTinyPlanet = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaRing), "TI ", "I I", " I ", Character.valueOf('T'), new ItemStack(ModItems.manaTablet, 1, Short.MAX_VALUE), Character.valueOf('I'), "ingotManasteel");
        recipeManaRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.auraRing), "RI ", "I I", " I ", Character.valueOf('R'), LibOreDict.RUNE[8], Character.valueOf('I'), "ingotManasteel");
        recipeAuraRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.manaRingGreater), "ingotTerrasteel", new ItemStack(ModItems.manaRing));
        recipeGreaterManaRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.auraRingGreater), "ingotTerrasteel", new ItemStack(ModItems.auraRing));
        recipeGreaterAuraRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.travelBelt), "EL ", "L L", "SLA", Character.valueOf('E'), LibOreDict.RUNE[2], Character.valueOf('A'), LibOreDict.RUNE[3], Character.valueOf('S'), "ingotManasteel", Character.valueOf('L'), new ItemStack(Items.field_151116_aA));
        recipeTravelBelt = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.knockbackBelt), "AL ", "L L", "SLE", Character.valueOf('E'), LibOreDict.RUNE[2], Character.valueOf('A'), LibOreDict.RUNE[1], Character.valueOf('S'), "ingotManasteel", Character.valueOf('L'), new ItemStack(Items.field_151116_aA));
        recipeKnocbackBelt = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.icePendant), "WS ", "S S", "MSR", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('M'), "ingotManasteel", Character.valueOf('R'), LibOreDict.RUNE[0], Character.valueOf('W'), LibOreDict.RUNE[7]);
        recipeIcePendant = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.lavaPendant), "MS ", "S S", "DSF", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('D'), "ingotManasteel", Character.valueOf('M'), LibOreDict.RUNE[5], Character.valueOf('F'), LibOreDict.RUNE[1]);
        recipeFirePendant = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.goldLaurel), "G G", "LEL", "LLL", Character.valueOf('G'), "ingotGold", Character.valueOf('L'), "treeLeaves", Character.valueOf('E'), "eternalLifeEssence");
        recipeGoldenLaurel = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.tinyPlanet), "SSS", "SPS", "SSS", Character.valueOf('S'), "stone", Character.valueOf('P'), ModItems.tinyPlanet);
        recipeTinyPlanetBlock = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.alchemyCatalyst), "SGS", "BPB", "SGS", Character.valueOf('S'), "livingrock", Character.valueOf('G'), "ingotGold", Character.valueOf('B'), new ItemStack(Items.field_151067_bt), Character.valueOf('P'), "manaPearl");
        recipeAlchemyCatalyst = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.openCrate), (Object[])new Object[]{"WWW", "W W", "W W", Character.valueOf('W'), new ItemStack(ModBlocks.livingwood, 1, 1)});
        recipeOpenCrate = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.forestEye), "MSM", "SES", "MSM", Character.valueOf('M'), "ingotManasteel", Character.valueOf('S'), "livingrock", Character.valueOf('E'), new ItemStack(Items.field_151061_bv));
        recipeForestEye = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(ModItems.manaResource, 1, 6), new Object[]{"dustRedstone", new ItemStack((Block)Blocks.field_150329_H, 1, 1)}));
        recipeRedstoneRoot = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.forestDrum), "WLW", "WHW", "WLW", Character.valueOf('W'), "livingwood", Character.valueOf('L'), new ItemStack(Items.field_151116_aA), Character.valueOf('H'), new ItemStack(ModItems.grassHorn));
        recipeForestDrum = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.waterRing), "WMP", "M M", "SM ", Character.valueOf('W'), LibOreDict.RUNE[0], Character.valueOf('M'), "ingotManasteel", Character.valueOf('P'), new ItemStack(Items.field_151115_aP, 1, 3), Character.valueOf('S'), new ItemStack(Items.field_151115_aP, 1, 1));
        recipeWaterRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.miningRing), "EMP", "M M", " M ", Character.valueOf('E'), LibOreDict.RUNE[2], Character.valueOf('M'), "ingotManasteel", Character.valueOf('P'), new ItemStack(Items.field_151005_D));
        recipeMiningRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.magnetRing), "LM ", "M M", " M ", Character.valueOf('L'), new ItemStack(ModItems.lens, 1, 10), Character.valueOf('M'), "ingotManasteel");
        recipeMagnetRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terraPick), "ITI", "ILI", " L ", Character.valueOf('T'), new ItemStack(ModItems.manaTablet, 1, Short.MAX_VALUE), Character.valueOf('I'), "ingotTerrasteel", Character.valueOf('L'), "livingwoodTwig");
        recipeTerraPick = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.divaCharm), "LGP", " HG", " GL", Character.valueOf('L'), "eternalLifeEssence", Character.valueOf('G'), "ingotGold", Character.valueOf('H'), LibOreDict.RUNE[15], Character.valueOf('P'), new ItemStack(ModItems.tinyPlanet));
        recipeDivaCharm = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.flightTiara), "LLL", "ILI", "FEF", Character.valueOf('L'), "eternalLifeEssence", Character.valueOf('I'), "ingotElvenElementium", Character.valueOf('F'), new ItemStack(Items.field_151008_G), Character.valueOf('E'), "bEnderAirBottle");
        recipeFlightTiara = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.shinyFlower, 1, i2), "dustGlowstone", "dustGlowstone", LibOreDict.FLOWER[i2]);
        }
        recipesShinyFlowers = BotaniaAPI.getLatestAddedRecipes(16);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.platform, 2), "343", "0P0", Character.valueOf('0'), new ItemStack(ModBlocks.livingwood, 1, 0), Character.valueOf('3'), new ItemStack(ModBlocks.livingwood, 1, 3), Character.valueOf('4'), new ItemStack(ModBlocks.livingwood, 1, 4), Character.valueOf('P'), "manaPearl");
        recipePlatform = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.enderDagger), "P", "S", "T", Character.valueOf('P'), "manaPearl", Character.valueOf('S'), "ingotManasteel", Character.valueOf('T'), "livingwoodTwig");
        recipeEnderDagger = BotaniaAPI.getLatestAddedRecipe();
        if (ConfigHandler.darkQuartzEnabled) {
            recipeDarkQuartz = ModCraftingRecipes.addQuartzRecipes(0, Items.field_151044_h, ModFluffBlocks.darkQuartz, ModFluffBlocks.darkQuartzStairs, ModFluffBlocks.darkQuartzSlab);
        }
        ModCraftingRecipes.addQuartzRecipes(1, null, ModFluffBlocks.manaQuartz, ModFluffBlocks.manaQuartzStairs, ModFluffBlocks.manaQuartzSlab);
        recipeBlazeQuartz = ModCraftingRecipes.addQuartzRecipes(2, Items.field_151065_br, ModFluffBlocks.blazeQuartz, ModFluffBlocks.blazeQuartzStairs, ModFluffBlocks.blazeQuartzSlab);
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModItems.quartz, 8, 3), new Object[]{"QQQ", "QCQ", "QQQ", Character.valueOf('Q'), "gemQuartz", Character.valueOf('C'), new ItemStack((Block)Blocks.field_150328_O, 1, 2)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModItems.quartz, 8, 3), new Object[]{"QQQ", "QCQ", "QQQ", Character.valueOf('Q'), "gemQuartz", Character.valueOf('C'), new ItemStack((Block)Blocks.field_150328_O, 1, 7)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModItems.quartz, 8, 3), new Object[]{"QQQ", "QCQ", "QQQ", Character.valueOf('Q'), "gemQuartz", Character.valueOf('C'), new ItemStack((Block)Blocks.field_150398_cm, 1, 1)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModItems.quartz, 8, 3), new Object[]{"QQQ", "QCQ", "QQQ", Character.valueOf('Q'), "gemQuartz", Character.valueOf('C'), new ItemStack((Block)Blocks.field_150398_cm, 1, 5)}));
        recipesLavenderQuartz = BotaniaAPI.getLatestAddedRecipes(4);
        ModCraftingRecipes.addQuartzRecipes(3, null, ModFluffBlocks.lavenderQuartz, ModFluffBlocks.lavenderQuartzStairs, ModFluffBlocks.lavenderQuartzSlab);
        recipeRedQuartz = ModCraftingRecipes.addQuartzRecipes(4, Items.field_151137_ax, ModFluffBlocks.redQuartz, ModFluffBlocks.redQuartzStairs, ModFluffBlocks.redQuartzSlab);
        ModCraftingRecipes.addQuartzRecipes(5, null, ModFluffBlocks.elfQuartz, ModFluffBlocks.elfQuartzStairs, ModFluffBlocks.elfQuartzSlab);
        recipeSunnyQuartz = ModCraftingRecipes.addQuartzRecipes(6, Item.func_150898_a((Block)Blocks.field_150398_cm), ModFluffBlocks.sunnyQuartz, ModFluffBlocks.sunnyQuartzStairs, ModFluffBlocks.sunnyQuartzSlab);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.alfPortal), "WTW", "WTW", "WTW", Character.valueOf('W'), "livingwood", Character.valueOf('T'), "nuggetTerrasteel");
        recipeAlfPortal = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.pylon, 1, 1), " T ", "TPT", " E ", Character.valueOf('T'), "nuggetTerrasteel", Character.valueOf('P'), new ItemStack(ModBlocks.pylon), Character.valueOf('E'), new ItemStack(Items.field_151061_bv));
        recipeNaturaPylon = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.waterRod), "  B", " T ", "R  ", Character.valueOf('B'), new ItemStack((Item)Items.field_151068_bn), Character.valueOf('T'), "livingwoodTwig", Character.valueOf('R'), LibOreDict.RUNE[0]);
        recipeWaterRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumHelm), "SSS", "S S", Character.valueOf('S'), "ingotElvenElementium");
        recipeElementiumHelm = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumChest), "S S", "SSS", "SSS", Character.valueOf('S'), "ingotElvenElementium");
        recipeElementiumChest = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumLegs), "SSS", "S S", "S S", Character.valueOf('S'), "ingotElvenElementium");
        recipeElementiumLegs = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumBoots), "S S", "S S", Character.valueOf('S'), "ingotElvenElementium");
        recipeElementiumBoots = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumPick), "SSS", " T ", " T ", Character.valueOf('S'), "ingotElvenElementium", Character.valueOf('T'), "dreamwoodTwig");
        recipeElementiumPick = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumShovel), "S", "T", "T", Character.valueOf('S'), "ingotElvenElementium", Character.valueOf('T'), "dreamwoodTwig");
        recipeElementiumShovel = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumAxe), "SS", "TS", "T ", Character.valueOf('S'), "ingotElvenElementium", Character.valueOf('T'), "dreamwoodTwig");
        recipeElementiumAxe = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumSword), "S", "S", "T", Character.valueOf('S'), "ingotElvenElementium", Character.valueOf('T'), "dreamwoodTwig");
        recipeElementiumSword = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.elementiumShears), "S ", " S", Character.valueOf('S'), "ingotElvenElementium");
        recipeElementiumShears = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.openBucket), "E E", " E ", Character.valueOf('E'), "ingotElvenElementium");
        recipeOpenBucket = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.conjurationCatalyst), "SBS", "GPG", "SGS", Character.valueOf('S'), "livingrock", Character.valueOf('G'), "ingotElvenElementium", Character.valueOf('B'), "elvenPixieDust", Character.valueOf('P'), new ItemStack(ModBlocks.alchemyCatalyst));
        recipeConjurationCatalyst = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.spawnerMover), "EIE", "ADA", "EIE", Character.valueOf('E'), "eternalLifeEssence", Character.valueOf('I'), "ingotElvenElementium", Character.valueOf('A'), "bEnderAirBottle", Character.valueOf('D'), "elvenDragonstone");
        recipeSpawnerMover = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.pixieRing), "DE ", "E E", " E ", Character.valueOf('D'), "elvenPixieDust", Character.valueOf('E'), "ingotElvenElementium");
        recipePixieRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.superTravelBelt), "E  ", " S ", "L E", Character.valueOf('E'), "ingotElvenElementium", Character.valueOf('L'), "eternalLifeEssence", Character.valueOf('S'), new ItemStack(ModItems.travelBelt));
        recipeSuperTravelBelt = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.rainbowRod), " PD", " EP", "E  ", Character.valueOf('P'), "elvenPixieDust", Character.valueOf('E'), "ingotElvenElementium", Character.valueOf('D'), "elvenDragonstone");
        recipeRainbowRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.platform, 2, 1), "343", "0D0", Character.valueOf('0'), new ItemStack(ModBlocks.dreamwood, 1, 0), Character.valueOf('3'), new ItemStack(ModBlocks.dreamwood, 1, 3), Character.valueOf('4'), new ItemStack(ModBlocks.dreamwood, 1, 4), Character.valueOf('D'), "elvenPixieDust");
        recipeSpectralPlatform = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.spreader, 1, 2), "WWW", "EP ", "WWW", Character.valueOf('W'), "dreamwood", Character.valueOf('P'), LibOreDict.PETAL[i2], Character.valueOf('E'), "ingotElvenElementium");
        }
        recipesDreamwoodSpreader = BotaniaAPI.getLatestAddedRecipes(16);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.tornadoRod), "  F", " T ", "R  ", Character.valueOf('F'), new ItemStack(Items.field_151008_G), Character.valueOf('T'), "livingwoodTwig", Character.valueOf('R'), LibOreDict.RUNE[3]);
        recipeTornadoRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.fireRod), "  F", " T ", "R  ", Character.valueOf('F'), new ItemStack(Items.field_151065_br), Character.valueOf('T'), "livingwoodTwig", Character.valueOf('R'), LibOreDict.RUNE[1]);
        recipeFireRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.vineBall), "VVV", "VVV", "VVV", Character.valueOf('V'), new ItemStack(Blocks.field_150395_bd));
        recipeVineBall = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.slingshot), " TA", " TT", "T  ", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('A'), LibOreDict.RUNE[3]);
        recipeSlingshot = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(Blocks.field_150341_Y), "cobblestone", new ItemStack(ModItems.vineBall));
        recipeMossStone = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.prismarine, 1, 0), " S ", "SBS", " S ", Character.valueOf('S'), "shardPrismarine", Character.valueOf('B'), "cobblestone");
        recipePrismarine = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.prismarine, 1, 1), " S ", "SBS", " S ", Character.valueOf('S'), "shardPrismarine", Character.valueOf('B'), new ItemStack(Blocks.field_150417_aV));
        recipePrismarineBrick = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.prismarine, 1, 2), " S ", "SBS", " S ", Character.valueOf('S'), "shardPrismarine", Character.valueOf('B'), new ItemStack(Blocks.field_150385_bj));
        recipeDarkPrismarine = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.seaLamp), " S ", "SBS", " S ", Character.valueOf('S'), "shardPrismarine", Character.valueOf('B'), "glowstone");
        recipeSeaLamp = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.lens, 1, 12), "PRP", "PLP", "PPP", Character.valueOf('P'), "shardPrismarine", Character.valueOf('R'), LibOreDict.RUNE[3], Character.valueOf('L'), new ItemStack(ModItems.lens));
        recipeLensInfluence = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.lens, 1, 13), "PPP", "PLP", "PRP", Character.valueOf('P'), "shardPrismarine", Character.valueOf('R'), LibOreDict.RUNE[0], Character.valueOf('L'), new ItemStack(ModItems.lens));
        recipeLensWeight = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.lens, 1, 14), " E ", "WLW", " E ", Character.valueOf('E'), "ingotElvenElementium", Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, Short.MAX_VALUE), Character.valueOf('L'), new ItemStack(ModItems.lens));
        recipeLensPaint = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 18), new ItemStack(ModItems.lens), "elvenPixieDust");
        recipeLensWarp = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 19), new ItemStack(ModItems.lens), "livingwood", "ingotElvenElementium");
        recipeLensRedirect = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 20), new ItemStack(ModItems.lens), new ItemStack(Items.field_151152_bP), "ingotElvenElementium");
        recipeLensFirework = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 21), new ItemStack(ModItems.lens), new ItemStack(ModBlocks.elfGlass), "ingotElvenElementium");
        recipeLensFlare = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.floatingFlower, 1, i2), (Object[])new Object[]{"F", "S", "D", Character.valueOf('F'), new ItemStack(ModBlocks.shinyFlower, 1, i2), Character.valueOf('S'), new ItemStack(ModItems.grassSeeds), Character.valueOf('D'), new ItemStack(Blocks.field_150346_d)});
        }
        recipesMiniIsland = BotaniaAPI.getLatestAddedRecipes(16);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.pylon, 1, 2), " D ", "EPE", " D ", Character.valueOf('D'), "elvenPixieDust", Character.valueOf('E'), "ingotElvenElementium", Character.valueOf('P'), new ItemStack(ModBlocks.pylon));
        recipeGaiaPylon = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.forestDrum, 1, 1), "WLW", "WEW", "WLW", Character.valueOf('W'), "dreamwood", Character.valueOf('L'), new ItemStack(Items.field_151116_aA), Character.valueOf('E'), "ingotElvenElementium");
        recipeGatherDrum = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 15), new ItemStack(ModItems.lens), new ItemStack(Items.field_151059_bz));
        recipeLensFire = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.lens, 1, 16), new ItemStack(ModItems.lens), new ItemStack(ModBlocks.pistonRelay));
        recipeLensPiston = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.laputaShard), "SFS", "PDP", "ASE", Character.valueOf('S'), "eternalLifeEssence", Character.valueOf('D'), "elvenDragonstone", Character.valueOf('F'), new ItemStack(ModBlocks.floatingFlower, 1, i2), Character.valueOf('P'), "shardPrismarine", Character.valueOf('A'), LibOreDict.RUNE[3], Character.valueOf('E'), LibOreDict.RUNE[2]);
        }
        recipesLaputaShard = BotaniaAPI.getLatestAddedRecipes(16);
        for (i2 = 1; i2 < 20; ++i2) {
            ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.laputaShard, 1, i2), "eternalLifeEssence", new ItemStack(ModItems.laputaShard, 1, i2 - 1));
        }
        recipesLaputaShardUpgrade = BotaniaAPI.getLatestAddedRecipes(19);
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.virus), "elvenPixieDust", new ItemStack(ModItems.vineBall), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151061_bv), new ItemStack(Items.field_151144_bL, 1, 2));
        recipeVirusZombie = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.virus, 1, 1), "elvenPixieDust", new ItemStack(ModItems.vineBall), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151071_bq), new ItemStack(Items.field_151061_bv), new ItemStack(Items.field_151144_bL));
        recipeVirusSkeleton = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.reachRing), "RE ", "E E", " E ", Character.valueOf('R'), LibOreDict.RUNE[15], Character.valueOf('E'), "ingotElvenElementium");
        recipeReachRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.skyDirtRod), new ItemStack(ModItems.dirtRod), "elvenPixieDust", LibOreDict.RUNE[3]);
        recipeSkyDirtRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.spawnerClaw), "BSB", "PMP", "PEP", Character.valueOf('B'), new ItemStack(Items.field_151072_bj), Character.valueOf('S'), "ingotElvenElementium", Character.valueOf('P'), new ItemStack(ModBlocks.prismarine, 1, 2), Character.valueOf('M'), new ItemStack(ModBlocks.storage), Character.valueOf('E'), "bEnderAirBottle");
        recipeSpawnerClaw = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModBlocks.openCrate, 1, 1), new Object[]{"WCW", "W W", "W W", Character.valueOf('C'), "craftingTableWood", Character.valueOf('W'), new ItemStack(ModBlocks.dreamwood, 1, 1)}));
        recipeCraftCrate = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.manaResource, 32, 11), "craftingTableWood", "livingrock");
        recipePlaceholder = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.reedBlock), (Object[])new Object[]{"rrr", "rrr", "rrr", Character.valueOf('r'), new ItemStack(Items.field_151120_aE)});
        recipeReedBlock = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModBlocks.thatch), new Object[]{"ww", "ww", Character.valueOf('w'), "cropWheat"}));
        recipeThatch = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.customBrick, 4, 0), (Object[])new Object[]{" B ", "BSB", " B ", Character.valueOf('B'), new ItemStack(Blocks.field_150424_aL), Character.valueOf('S'), new ItemStack(Blocks.field_150417_aV)});
        recipeNetherBrick = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.customBrick, 4, 1), (Object[])new Object[]{" B ", "BSB", " B ", Character.valueOf('B'), new ItemStack(Blocks.field_150425_aM), Character.valueOf('S'), new ItemStack(Blocks.field_150417_aV)});
        recipeSoulBrick = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.customBrick, 4, 2), (Object[])new Object[]{" B ", "BSB", " B ", Character.valueOf('B'), new ItemStack(Blocks.field_150433_aE), Character.valueOf('S'), new ItemStack(Blocks.field_150417_aV)});
        recipeSnowBrick = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModBlocks.customBrick, 4, 3), new Object[]{"BB", "BB", "BB", Character.valueOf('B'), "ingotBrick"}));
        recipeRoofTile = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(ModBlocks.customBrick, 1, 4), new Object[]{"gemLapis", "blockQuartz"}));
        recipeAzulejo = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 12; ++i2) {
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModBlocks.customBrick, 1, 4 + (i2 == 11 ? 0 : i2 + 1)), (Object[])new Object[]{new ItemStack(ModBlocks.customBrick, 1, 4 + i2)});
        }
        recipesAzulejoCycling = BotaniaAPI.getLatestAddedRecipes(12);
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModBlocks.enderEye), new Object[]{"RER", "EOE", "RER", Character.valueOf('R'), "dustRedstone", Character.valueOf('E'), new ItemStack(Items.field_151061_bv), Character.valueOf('O'), new ItemStack(Blocks.field_150343_Z)}));
        recipeEnderEyeBlock = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.itemFinder), " I ", "IYI", "IEI", Character.valueOf('I'), "ingotIron", Character.valueOf('Y'), new ItemStack(Items.field_151061_bv), Character.valueOf('E'), "gemEmerald");
        recipeItemFinder = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.superLavaPendant), "BBB", "BPB", "NGN", Character.valueOf('B'), new ItemStack(Items.field_151072_bj), Character.valueOf('P'), new ItemStack(ModItems.lavaPendant), Character.valueOf('N'), new ItemStack(Blocks.field_150385_bj), Character.valueOf('G'), "eternalLifeEssence");
        recipeSuperLavaPendant = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.enderHand), "PLO", "LEL", "OL ", Character.valueOf('P'), "manaPearl", Character.valueOf('L'), new ItemStack(Items.field_151116_aA), Character.valueOf('E'), new ItemStack(Blocks.field_150477_bB), Character.valueOf('O'), new ItemStack(Blocks.field_150343_Z));
        recipeEnderHand = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.glassPick), "GIG", " T ", " T ", Character.valueOf('G'), "blockGlassColorless", Character.valueOf('I'), "ingotManasteel", Character.valueOf('T'), "livingwoodTwig");
        recipeGlassPick = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.starfield), "EPE", "EOE", Character.valueOf('E'), "ingotElvenElementium", Character.valueOf('P'), "elvenPixieDust", Character.valueOf('O'), new ItemStack(Blocks.field_150343_Z));
        recipeStarfield = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.spark), " P ", "BNB", " P ", Character.valueOf('B'), new ItemStack(Items.field_151065_br), Character.valueOf('P'), LibOreDict.PETAL[i2], Character.valueOf('N'), "nuggetGold");
        }
        recipesSpark = BotaniaAPI.getLatestAddedRecipes(16);
        for (i2 = 0; i2 < 4; ++i2) {
            ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.sparkUpgrade, 1, i2), "elvenPixieDust", "ingotManasteel", LibOreDict.RUNE[i2]);
        }
        recipesSparkUpgrades = BotaniaAPI.getLatestAddedRecipes(4);
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.grassHorn, 1, 1), new ItemStack(ModItems.grassHorn), "treeLeaves");
        recipeLeafHorn = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.diviningRod), " TD", " TT", "T  ", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('D'), "manaDiamond");
        recipeDiviningRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.gravityRod), " TD", " WT", "T  ", Character.valueOf('T'), "dreamwoodTwig", Character.valueOf('W'), "cropWheat", Character.valueOf('D'), "elvenDragonstone");
        recipeGravityRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.regenIvy), new ItemStack(Blocks.field_150395_bd), "eternalLifeEssence", "ingotElvenElementium");
        recipeRegenIvy = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.spreader, 1, 3), "ESD", Character.valueOf('E'), "eternalLifeEssence", Character.valueOf('S'), new ItemStack(ModBlocks.spreader, 1, 2), Character.valueOf('D'), "elvenDragonstone");
        recipeUltraSpreader = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(ModItems.flightTiara, 1, 1), new Object[]{new ItemStack(ModItems.flightTiara, 1, Short.MAX_VALUE), "gemQuartz"}));
        for (i2 = 0; i2 < 7; ++i2) {
            GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(ModItems.flightTiara, 1, 2 + i2), new Object[]{new ItemStack(ModItems.flightTiara, 1, Short.MAX_VALUE), LibOreDict.QUARTZ[i2]}));
        }
        recipesWings = BotaniaAPI.getLatestAddedRecipes(8);
        if (ConfigHandler.fluxfieldEnabled) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.rfGenerator), "SRS", "RMR", "SRS", Character.valueOf('S'), "livingrock", Character.valueOf('M'), "ingotManasteel", Character.valueOf('R'), "blockRedstone");
            recipeRFGenerator = BotaniaAPI.getLatestAddedRecipe();
        }
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModItems.vial, 3, 0), (Object[])new Object[]{"G G", " G ", Character.valueOf('G'), new ItemStack(ModBlocks.manaGlass)});
        recipeVial = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModItems.vial, 3, 1), (Object[])new Object[]{"G G", " G ", Character.valueOf('G'), new ItemStack(ModBlocks.elfGlass)});
        recipeFlask = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.brewery), "RSR", "RAR", "RMR", Character.valueOf('R'), "livingrock", Character.valueOf('S'), new ItemStack(Items.field_151067_bt), Character.valueOf('A'), LibOreDict.RUNE[8], Character.valueOf('M'), new ItemStack(ModBlocks.storage));
        recipeBrewery = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.bloodPendant), " P ", "PGP", "DP ", Character.valueOf('P'), "shardPrismarine", Character.valueOf('G'), new ItemStack(Items.field_151073_bk), Character.valueOf('D'), "manaDiamond");
        recipeBloodPendant = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.terraPlate), "LLL", "0M1", "283", Character.valueOf('L'), "blockLapis", Character.valueOf('M'), new ItemStack(ModBlocks.storage), Character.valueOf('0'), LibOreDict.RUNE[0], Character.valueOf('1'), LibOreDict.RUNE[1], Character.valueOf('2'), LibOreDict.RUNE[2], Character.valueOf('3'), LibOreDict.RUNE[3], Character.valueOf('8'), LibOreDict.RUNE[8]);
        recipeTerraPlate = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 12), new ItemStack(Items.field_151007_F), "blockRedstone", "elvenPixieDust", "bEnderAirBottle");
        recipeRedString = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 12), new ItemStack(Items.field_151007_F), "blockRedstone", "elvenPixieDust", "bEnderAirBottle", new ItemStack(Blocks.field_150423_aK));
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.redStringContainer), "RRR", "RCS", "RRR", Character.valueOf('R'), "livingrock", Character.valueOf('S'), "bRedString", Character.valueOf('C'), "chestWood");
        recipeRedStringContainer = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.redStringDispenser), "RRR", "RDS", "RRR", Character.valueOf('R'), "livingrock", Character.valueOf('S'), "bRedString", Character.valueOf('D'), new ItemStack(Blocks.field_150367_z));
        recipeRedStringDispenser = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.redStringFertilizer), "RRR", "RBS", "RRR", Character.valueOf('R'), "livingrock", Character.valueOf('S'), "bRedString", Character.valueOf('B'), new ItemStack(ModItems.fertilizer));
        recipeRedStringFertilizer = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.redStringComparator), "RRR", "RCS", "RRR", Character.valueOf('R'), "livingrock", Character.valueOf('S'), "bRedString", Character.valueOf('C'), new ItemStack(Items.field_151132_bS));
        recipeRedStringComparator = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.redStringRelay), "RRR", "RMS", "RRR", Character.valueOf('R'), "livingrock", Character.valueOf('S'), "bRedString", Character.valueOf('M'), new ItemStack(ModBlocks.spreader));
        recipeRedStringRelay = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.redStringInterceptor), "RRR", "RMS", "RRR", Character.valueOf('R'), "livingrock", Character.valueOf('S'), "bRedString", Character.valueOf('M'), new ItemStack(Blocks.field_150430_aB));
        recipeRedStringInterceptor = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.missileRod), "GDD", " TD", "T G", Character.valueOf('G'), "eternalLifeEssence", Character.valueOf('D'), "elvenDragonstone", Character.valueOf('T'), "dreamwoodTwig");
        recipeMissileRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.holyCloak), "WWW", "GWG", "GSG", Character.valueOf('W'), new ItemStack(Blocks.field_150325_L), Character.valueOf('G'), "dustGlowstone", Character.valueOf('S'), "eternalLifeEssence");
        recipeHolyCloak = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.unholyCloak), "WWW", "RWR", "RSR", Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, 15), Character.valueOf('R'), "dustRedstone", Character.valueOf('S'), "eternalLifeEssence");
        recipeUnholyCloak = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.craftingHalo), " P ", "ICI", " I ", Character.valueOf('P'), "manaPearl", Character.valueOf('I'), "ingotManasteel", Character.valueOf('C'), "craftingTableWood");
        recipeCraftingHalo = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModItems.lens, 1, 17), new Object[]{"GFG", "FLF", "GFG", Character.valueOf('G'), "glowstone", Character.valueOf('F'), new ItemStack(Items.field_151059_bz), Character.valueOf('L'), new ItemStack(ModItems.lens)}));
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModItems.lens, 1, 17), new Object[]{"FGF", "GLG", "FGF", Character.valueOf('G'), "glowstone", Character.valueOf('F'), new ItemStack(Items.field_151059_bz), Character.valueOf('L'), new ItemStack(ModItems.lens)}));
        recipesLensFlash = BotaniaAPI.getLatestAddedRecipes(2);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.prism), "GPG", "GSG", "GPG", Character.valueOf('G'), "blockGlassColorless", Character.valueOf('P'), "shardPrismarine", Character.valueOf('S'), new ItemStack(ModBlocks.platform, 1, 1));
        recipePrism = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(ModBlocks.dirtPath, 4), new Object[]{new ItemStack(Blocks.field_150346_d, 1, 1), new ItemStack(Blocks.field_150346_d, 1, 1), new ItemStack(Blocks.field_150346_d, 1, 1), "sand"}));
        recipeDirtPath = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 13), "W", "W", Character.valueOf('W'), "dreamwood");
        recipeDreamwoodTwig = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.monocle), "GN", "IN", " N", Character.valueOf('G'), new ItemStack(ModBlocks.manaGlass), Character.valueOf('I'), "ingotManasteel", Character.valueOf('N'), new ItemStack(Items.field_151074_bl));
        recipeMonocle = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.clip), " D ", "D D", "DD ", Character.valueOf('D'), "dreamwood");
        recipeClip = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.cobbleRod), " FC", " TW", "T  ", Character.valueOf('F'), LibOreDict.RUNE[1], Character.valueOf('W'), LibOreDict.RUNE[0], Character.valueOf('T'), "livingwoodTwig", Character.valueOf('C'), "cobblestone");
        recipeCobbleRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.smeltRod), " BF", " TB", "T  ", Character.valueOf('B'), new ItemStack(Items.field_151072_bj), Character.valueOf('F'), LibOreDict.RUNE[1], Character.valueOf('T'), "livingwoodTwig");
        recipeSmeltRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.worldSeed, 4), "G", "S", "D", Character.valueOf('G'), new ItemStack((Block)Blocks.field_150349_c), Character.valueOf('S'), new ItemStack(Items.field_151014_N), Character.valueOf('D'), "elvenDragonstone");
        recipeWorldSeed = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.spellCloth), " C ", "CPC", " C ", Character.valueOf('C'), "clothManaweave", Character.valueOf('P'), "manaPearl");
        recipeSpellCloth = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.thornChakram, 2), "VVV", "VTV", "VVV", Character.valueOf('V'), new ItemStack(Blocks.field_150395_bd), Character.valueOf('T'), "ingotTerrasteel");
        recipeThornChakram = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModFluffBlocks.dirtPathSlab, 6), (Object[])new Object[]{"DDD", Character.valueOf('D'), new ItemStack(ModBlocks.dirtPath)});
        recipeDirtPathSlab = BotaniaAPI.getLatestAddedRecipe();
        int count = TileCraftCrate.PATTERNS.length;
        List<Object> recipeObjects = Arrays.asList(Character.valueOf('R'), "dustRedstone", Character.valueOf('P'), "bPlaceholder");
        for (int i3 = 0; i3 < count; ++i3) {
            ArrayList<Object> recipe = new ArrayList<Object>();
            for (int j = 0; j < 3; ++j) {
                String s = "";
                for (int k = 0; k < 3; ++k) {
                    s = s + (TileCraftCrate.PATTERNS[i3][j * 3 + k] ? "R" : "P");
                }
                recipe.add(s);
            }
            recipe.addAll(recipeObjects);
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.craftPattern, 1, i3), recipe.toArray(new Object[recipe.size()]));
        }
        recipesPatterns = BotaniaAPI.getLatestAddedRecipes(count);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 14), " S ", "SIS", " S ", Character.valueOf('S'), "eternalLifeEssence", Character.valueOf('I'), "ingotTerrasteel");
        recipeGaiaIngot = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.corporeaSpark), new ItemStack(ModItems.spark), "elvenPixieDust", "bEnderAirBottle");
        recipeCorporeaSpark = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.corporeaSpark, 1, 1), new ItemStack(ModItems.corporeaSpark), "elvenDragonstone");
        recipeMasterCorporeaSpark = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.corporeaIndex), "AOA", "OSO", "DOD", Character.valueOf('A'), "bEnderAirBottle", Character.valueOf('O'), new ItemStack(Blocks.field_150343_Z), Character.valueOf('S'), new ItemStack(ModItems.corporeaSpark), Character.valueOf('D'), "elvenDragonstone");
        recipeCorporeaIndex = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.corporeaFunnel), new ItemStack(Blocks.field_150409_cd), new ItemStack(ModItems.corporeaSpark));
        recipeCorporeaFunnel = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.corporeaInterceptor), "blockRedstone", new ItemStack(ModItems.corporeaSpark));
        recipeCorporeaInterceptor = BotaniaAPI.getLatestAddedRecipe();
        if (ConfigHandler.enderStuff19Enabled) {
            GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.endStoneBrick, 4), (Object[])new Object[]{"SS", "SS", Character.valueOf('S'), new ItemStack(Blocks.field_150377_bs)});
            recipeEndStoneBricks = BotaniaAPI.getLatestAddedRecipe();
            GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.endStoneBrick, 1, 1), (Object[])new Object[]{"S", "S", Character.valueOf('S'), new ItemStack(ModFluffBlocks.endStoneSlab)});
            recipeEndStoneChiseledBricks = BotaniaAPI.getLatestAddedRecipe();
            GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.endStoneBrick, 4, 2), (Object[])new Object[]{" B ", "BPB", " B ", Character.valueOf('B'), new ItemStack(ModBlocks.endStoneBrick), Character.valueOf('P'), new ItemStack(Items.field_151079_bi)});
            recipeEnderBricks = BotaniaAPI.getLatestAddedRecipe();
            GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.endStoneBrick, 2, 3), (Object[])new Object[]{"B", "B", Character.valueOf('B'), new ItemStack(ModBlocks.endStoneBrick, 1, 2)});
            recipePillarEnderBricks = BotaniaAPI.getLatestAddedRecipe();
        }
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.livingwoodBow), " TS", "T S", " TS", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('S'), "manaString");
        recipeLivingwoodBow = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.crystalBow), " DS", "T S", " DS", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('D'), "elvenDragonstone", Character.valueOf('S'), "manaString");
        recipeCrystalBow = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 32; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.cosmetic, 1, i2), "PPP", "PSP", "PPP", Character.valueOf('P'), new ItemStack(i2 < 16 ? ModItems.petal : ModItems.dye, 1, i2 % 16), Character.valueOf('S'), "manaString");
        }
        recipesCosmeticItems = BotaniaAPI.getLatestAddedRecipes(32);
        for (i2 = 0; i2 < 16; ++i2) {
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModBlocks.mushroom, 1, i2), (Object[])new Object[]{new ItemStack((Block)Blocks.field_150337_Q), new ItemStack(ModItems.dye, 1, i2)});
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModBlocks.mushroom, 1, i2), (Object[])new Object[]{new ItemStack((Block)Blocks.field_150338_P), new ItemStack(ModItems.dye, 1, i2)});
        }
        recipesMushrooms = BotaniaAPI.getLatestAddedRecipes(32);
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(Items.field_151009_A), (Object[])new Object[]{new ItemStack(ModBlocks.mushroom, 1, Short.MAX_VALUE), new ItemStack(ModBlocks.mushroom, 1, Short.MAX_VALUE), new ItemStack(Items.field_151054_z)});
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.swapRing), "CM ", "M M", " M ", Character.valueOf('C'), new ItemStack(Blocks.field_150435_aG), Character.valueOf('M'), "ingotManasteel");
        recipeSwapRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.grassHorn, 1, 2), new ItemStack(ModItems.grassHorn), new ItemStack(Items.field_151126_ay));
        recipeSnowHorn = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addShapedRecipe((ItemStack)new ItemStack(ModItems.flowerBag), (Object[])new Object[]{"WPW", "W W", " W ", Character.valueOf('P'), new ItemStack(ModItems.petal, 1, Short.MAX_VALUE), Character.valueOf('W'), new ItemStack(Blocks.field_150325_L, 1, Short.MAX_VALUE)});
        recipeFlowerBag = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.phantomInk, 4), "manaPearl", "dye", "blockGlass", new ItemStack(Items.field_151069_bo), new ItemStack(Items.field_151069_bo), new ItemStack(Items.field_151069_bo), new ItemStack(Items.field_151069_bo));
        recipePhantomInk = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.poolMinecart), (Object[])new Object[]{new ItemStack(Items.field_151143_au), new ItemStack(ModBlocks.pool)});
        recipePoolCart = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.pump), "SSS", "IBI", "SSS", Character.valueOf('S'), "livingrock", Character.valueOf('I'), "ingotManasteel", Character.valueOf('B'), new ItemStack(Items.field_151133_ar));
        recipePump = BotaniaAPI.getLatestAddedRecipe();
        for (i2 = 0; i2 < 16; ++i2) {
            ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.petal, 4, i2), LibOreDict.DOUBLE_FLOWER[i2]);
        }
        recipesPetalsDouble = BotaniaAPI.getLatestAddedRecipes(16);
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.keepIvy), "elvenPixieDust", new ItemStack(Blocks.field_150395_bd), "bEnderAirBottle");
        recipeKeepIvy = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.blackHoleTalisman), " G ", "EAE", " E ", Character.valueOf('G'), "eternalLifeEssence", Character.valueOf('E'), "ingotElvenElementium", Character.valueOf('A'), "bEnderAirBottle");
        recipeBlackHoleTalisman = BotaniaAPI.getLatestAddedRecipe();
        recipe18StonePolish = new ArrayList<IRecipe>();
        recipe18StoneBrick = new ArrayList<IRecipe>();
        recipe18StoneChisel = new ArrayList<IRecipe>();
        for (i2 = 0; i2 < 4; ++i2) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stone, 8, i2 + 4), "SSS", "S S", "SSS", Character.valueOf('S'), LibOreDict.STONE_18_VARIANTS[i2]);
            recipe18StonePolish.add(BotaniaAPI.getLatestAddedRecipe());
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stone, 4, i2 + 8), "SS", "SS", Character.valueOf('S'), LibOreDict.STONE_18_VARIANTS[i2]);
            recipe18StoneBrick.add(BotaniaAPI.getLatestAddedRecipe());
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stone, 1, i2 + 12), "S", "S", Character.valueOf('S'), new ItemStack(ModFluffBlocks.stoneSlabs[i2 + 4], 1, 0));
            recipe18StoneChisel.add(BotaniaAPI.getLatestAddedRecipe());
        }
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.blazeBlock), "BBB", "BBB", "BBB", Character.valueOf('B'), Botania.gardenOfGlassLoaded ? "powderBlaze" : "rodBlaze");
        recipeBlazeBlock = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(Botania.gardenOfGlassLoaded ? Items.field_151065_br : Items.field_151072_bj, 9), "blockBlaze");
        for (i2 = 0; i2 < 8; ++i2) {
            GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.altar, 1, i2 + 1), (Object[])new Object[]{"SSS", "SAS", "SSS", Character.valueOf('S'), new ItemStack(ModFluffBlocks.biomeStoneA, 1, i2 + 8), Character.valueOf('A'), new ItemStack(ModBlocks.altar)});
        }
        recipesAltarMeta = BotaniaAPI.getLatestAddedRecipes(8);
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.corporeaCrystalCube), "C", "G", "W", Character.valueOf('C'), new ItemStack(ModItems.corporeaSpark), Character.valueOf('G'), new ItemStack(ModBlocks.elfGlass), Character.valueOf('W'), "dreamwood");
        recipeCorporeaCrystalCube = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.temperanceStone), " S ", "SRS", " S ", Character.valueOf('S'), "stone", Character.valueOf('R'), LibOreDict.RUNE[2]);
        recipeTemperanceStone = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.incenseStick), "  G", " B ", "T  ", Character.valueOf('G'), new ItemStack(Items.field_151073_bk), Character.valueOf('B'), new ItemStack(Items.field_151065_br), Character.valueOf('T'), "livingwoodTwig");
        recipeIncenseStick = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.incensePlate), "WSS", Character.valueOf('W'), "livingwood", Character.valueOf('S'), new ItemStack(ModFluffBlocks.livingwoodSlab));
        recipeIncensePlate = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.terraAxe), "TTG", "TST", " S ", Character.valueOf('T'), "ingotTerrasteel", Character.valueOf('G'), "glowstone", Character.valueOf('S'), "livingwoodTwig");
        recipeTerraAxe = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.hourglass), "GMG", "RSR", "GMG", Character.valueOf('G'), "ingotGold", Character.valueOf('M'), new ItemStack(ModBlocks.manaGlass), Character.valueOf('R'), "dustRedstone", Character.valueOf('S'), "ingotManasteel");
        recipeHourglass = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModBlocks.ghostRail), (Object[])new Object[]{new ItemStack(Blocks.field_150448_aq), new ItemStack(ModBlocks.platform, 1, 1)});
        recipeGhostRail = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.forestDrum, 1, 2), "WLW", "WHW", "WLW", Character.valueOf('W'), "livingwood", Character.valueOf('L'), new ItemStack(Items.field_151116_aA), Character.valueOf('H'), new ItemStack(ModItems.grassHorn, 1, 1));
        recipeCanopyDrum = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.sparkChanger), "ESE", "SRS", Character.valueOf('S'), "livingrock", Character.valueOf('E'), "ingotElvenElementium", Character.valueOf('R'), "dustRedstone");
        recipeSparkChanger = BotaniaAPI.getLatestAddedRecipe();
        if (Botania.gardenOfGlassLoaded) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.cocoon), "SSS", "SFS", "SIS", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('F'), new ItemStack(ModBlocks.felPumpkin), Character.valueOf('I'), "ingotManasteel");
        } else {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.cocoon), "SSS", "SPS", "SDS", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('P'), "elvenPixieDust", Character.valueOf('D'), "elvenDragonstone");
        }
        recipeCocoon = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModBlocks.felPumpkin), (Object[])new Object[]{" S ", "BPF", " G ", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('B'), new ItemStack(Items.field_151103_aS), Character.valueOf('P'), new ItemStack(Blocks.field_150423_aK), Character.valueOf('F'), new ItemStack(Items.field_151078_bh), Character.valueOf('G'), new ItemStack(Items.field_151016_H)});
        recipeFelPumpkin = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.lightRelay), "bRedString", "elvenDragonstone", "dustGlowstone", "dustGlowstone");
        recipeLuminizer = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.lightRelay, 1, 1), new ItemStack(ModBlocks.lightRelay), "dustRedstone");
        recipeDetectorLuminizer = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.lightLauncher), "DDD", "DLD", Character.valueOf('D'), "dreamwood", Character.valueOf('L'), new ItemStack(ModBlocks.lightRelay));
        recipeLuminizerLauncher = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.obedienceStick), "  M", " T ", "T  ", Character.valueOf('M'), "ingotManasteel", Character.valueOf('T'), "livingwoodTwig");
        recipeObedienceStick = BotaniaAPI.getLatestAddedRecipe();
        if (OreDictionary.getOres((String)"ingotBrass").isEmpty()) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.cacophonium), " G ", "GNG", "GG ", Character.valueOf('G'), "ingotGold", Character.valueOf('N'), new ItemStack(Blocks.field_150323_B));
        } else {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.cacophonium), " G ", "GNG", "GG ", Character.valueOf('G'), "ingotBrass", Character.valueOf('N'), new ItemStack(Blocks.field_150323_B));
        }
        recipeCacophonium = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.manaBomb), "LTL", "TGT", "LTL", Character.valueOf('L'), "livingwood", Character.valueOf('T'), new ItemStack(Blocks.field_150335_W), Character.valueOf('G'), "eternalLifeEssence");
        recipeManaBomb = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(Blocks.field_150321_G), "S S", " M ", "S S", Character.valueOf('S'), new ItemStack(Items.field_151007_F), Character.valueOf('M'), "manaString");
        recipeCobweb = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.slimeBottle), "EGE", "ESE", " E ", Character.valueOf('E'), "ingotElvenElementium", Character.valueOf('G'), new ItemStack(ModBlocks.elfGlass), Character.valueOf('S'), "slimeball");
        recipeSlimeBottle = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.starSword), "  I", "AD ", "TA ", Character.valueOf('I'), "ingotElvenElementium", Character.valueOf('D'), "elvenDragonstone", Character.valueOf('A'), "bEnderAirBottle", Character.valueOf('T'), new ItemStack(ModItems.terraSword));
        recipeStarSword = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.exchangeRod), " SR", " TS", "T  ", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('S'), "stone", Character.valueOf('R'), LibOreDict.RUNE[12]);
        recipeExchangeRod = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.magnetRingGreater), "ingotTerrasteel", new ItemStack(ModItems.magnetRing));
        recipeGreaterMagnetRing = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.thornChakram, 2, 1), "BBB", "CPC", "BBB", Character.valueOf('B'), new ItemStack(Items.field_151065_br), Character.valueOf('P'), "elvenPixieDust", Character.valueOf('C'), new ItemStack(ModItems.thornChakram));
        recipeFireChakram = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.thunderSword), "  I", "AD ", "TA ", Character.valueOf('I'), "ingotElvenElementium", Character.valueOf('D'), "manaDiamond", Character.valueOf('A'), "bEnderAirBottle", Character.valueOf('T'), new ItemStack(ModItems.terraSword));
        recipeThunderSword = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.bellows), "SSS", "RL ", "SSS", Character.valueOf('S'), new ItemStack(ModFluffBlocks.livingwoodSlab), Character.valueOf('R'), LibOreDict.RUNE[3], Character.valueOf('L'), new ItemStack(Items.field_151116_aA));
        recipeBellows = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 22), "SS", "SS", Character.valueOf('S'), "manaString");
        recipeManaweaveCloth = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaweaveHelm), "SSS", "S S", Character.valueOf('S'), "clothManaweave");
        recipeManaweaveHelm = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaweaveChest), "S S", "SSS", "SSS", Character.valueOf('S'), "clothManaweave");
        recipeManaweaveChest = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaweaveLegs), "SSS", "S S", "S S", Character.valueOf('S'), "clothManaweave");
        recipeManaweaveLegs = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaweaveBoots), "S S", "S S", Character.valueOf('S'), "clothManaweave");
        recipeManaweaveBoots = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.bifrostPerm), new ItemStack(ModItems.rainbowRod), new ItemStack(ModBlocks.elfGlass));
        recipeBifrost = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.shimmerrock), "livingrock", new ItemStack(ModBlocks.bifrostPerm));
        recipeShimmerrock = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.shimmerwoodPlanks), new ItemStack(ModBlocks.dreamwood, 1, 1), new ItemStack(ModBlocks.bifrostPerm));
        recipeShimmerwoodPlanks = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.autocraftingHalo), new ItemStack(ModItems.craftingHalo), "manaDiamond");
        recipeAutocraftingHalo = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModFluffBlocks.pavement, 3, 0), "livingrock", "cobblestone", "gravel");
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModFluffBlocks.pavement, 3, 1), "livingrock", "cobblestone", "gravel", new ItemStack(Items.field_151044_h));
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModFluffBlocks.pavement, 3, 2), "livingrock", "cobblestone", "gravel", new ItemStack(Items.field_151100_aR, 1, 4));
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModFluffBlocks.pavement, 3, 3), "livingrock", "cobblestone", "gravel", new ItemStack(Items.field_151137_ax));
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModFluffBlocks.pavement, 3, 4), "livingrock", "cobblestone", "gravel", new ItemStack(Items.field_151015_O));
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModFluffBlocks.pavement, 3, 5), "livingrock", "cobblestone", "gravel", new ItemStack(Items.field_151123_aH));
        recipesPavement = BotaniaAPI.getLatestAddedRecipes(6);
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModBlocks.cellBlock, 3), (Object[])new Object[]{new ItemStack(Blocks.field_150434_aF), new ItemStack(Blocks.field_150434_aF), new ItemStack(Blocks.field_150434_aF), new ItemStack(Blocks.field_150434_aF), new ItemStack(Items.field_151172_bF), new ItemStack(Items.field_151174_bG)});
        recipeCellBlock = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModBlocks.corporeaRetainer), new ItemStack((Block)Blocks.field_150486_ae), new ItemStack(ModItems.corporeaSpark));
        recipeCorporeaRetainer = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.teruTeruBozu), "C", "C", "S", Character.valueOf('C'), "clothManaweave", Character.valueOf('S'), new ItemStack((Block)Blocks.field_150398_cm));
        recipeTeruTeruBozu = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.avatar), " W ", "WDW", "W W", Character.valueOf('W'), "livingwood", Character.valueOf('D'), "manaDiamond");
        recipeAvatar = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.sextant), " TI", " TT", "III", Character.valueOf('T'), "livingwoodTwig", Character.valueOf('I'), "ingotManasteel");
        recipeSextant = BotaniaAPI.getLatestAddedRecipe();
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.grassSeeds, 1, 3), (Object[])new Object[]{new ItemStack(ModItems.grassSeeds), new ItemStack((Block)Blocks.field_150330_I)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.grassSeeds, 1, 4), (Object[])new Object[]{new ItemStack(ModItems.grassSeeds), new ItemStack(Items.field_151015_O)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.grassSeeds, 1, 5), (Object[])new Object[]{new ItemStack(ModItems.grassSeeds), new ItemStack(Items.field_151100_aR, 1, 2)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.grassSeeds, 1, 6), (Object[])new Object[]{new ItemStack(ModItems.grassSeeds), new ItemStack(Items.field_151065_br)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.grassSeeds, 1, 7), (Object[])new Object[]{new ItemStack(ModItems.grassSeeds), new ItemStack(ModItems.manaResource, 1, 10)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.grassSeeds, 1, 8), (Object[])new Object[]{new ItemStack(ModItems.grassSeeds), new ItemStack(Items.field_151070_bp)});
        recipesAltGrassSeeds = BotaniaAPI.getLatestAddedRecipes(6);
        GameRegistry.addRecipe((ItemStack)new ItemStack(ModItems.speedUpBelt), (Object[])new Object[]{" M ", "PBP", " S ", Character.valueOf('M'), new ItemStack((Item)Items.field_151098_aY, 1, Short.MAX_VALUE), Character.valueOf('P'), new ItemStack(ModItems.grassSeeds), Character.valueOf('B'), new ItemStack(ModItems.travelBelt), Character.valueOf('S'), new ItemStack(Items.field_151102_aT)});
        recipeSpeedUpBelt = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.baubleBox), " M ", "MCG", " M ", Character.valueOf('M'), "ingotManasteel", Character.valueOf('C'), new ItemStack((Block)Blocks.field_150486_ae), Character.valueOf('G'), "ingotGold");
        recipeBaubleCase = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.storage, 1, 0), "III", "III", "III", Character.valueOf('I'), "ingotManasteel");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.storage, 1, 1), "III", "III", "III", Character.valueOf('I'), "ingotTerrasteel");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.storage, 1, 2), "III", "III", "III", Character.valueOf('I'), "ingotElvenElementium");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.storage, 1, 3), "III", "III", "III", Character.valueOf('I'), "manaDiamond");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModBlocks.storage, 1, 4), "III", "III", "III", Character.valueOf('I'), "elvenDragonstone");
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.manaResource, 9, 0), (Object[])new Object[]{new ItemStack(ModBlocks.storage, 1, 0)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.manaResource, 9, 4), (Object[])new Object[]{new ItemStack(ModBlocks.storage, 1, 1)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.manaResource, 9, 7), (Object[])new Object[]{new ItemStack(ModBlocks.storage, 1, 2)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.manaResource, 9, 2), (Object[])new Object[]{new ItemStack(ModBlocks.storage, 1, 3)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.manaResource, 9, 9), (Object[])new Object[]{new ItemStack(ModBlocks.storage, 1, 4)});
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 0), "III", "III", "III", Character.valueOf('I'), "nuggetManasteel");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 4), "III", "III", "III", Character.valueOf('I'), "nuggetTerrasteel");
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModItems.manaResource, 1, 7), "III", "III", "III", Character.valueOf('I'), "nuggetElvenElementium");
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.manaResource, 9, 17), "ingotManasteel");
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.manaResource, 9, 18), "ingotTerrasteel");
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.manaResource, 9, 19), "ingotElvenElementium");
        if (Botania.thaumcraftLoaded) {
            Item goggles = (Item)Item.field_150901_e.func_82594_a("Thaumcraft:ItemGoggles");
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.manasteelHelmRevealing), (Object[])new Object[]{new ItemStack(ModItems.manasteelHelm), goggles});
            recipeHelmetOfRevealing = BotaniaAPI.getLatestAddedRecipe();
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.terrasteelHelmRevealing), (Object[])new Object[]{new ItemStack(ModItems.terrasteelHelm), goggles});
            GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ModItems.elementiumHelmRevealing), (Object[])new Object[]{new ItemStack(ModItems.elementiumHelm), goggles});
        }
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.livingwood, 0, ModFluffBlocks.livingwoodStairs, ModFluffBlocks.livingwoodSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.livingwood, 1, ModFluffBlocks.livingwoodPlankStairs, ModFluffBlocks.livingwoodPlankSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.livingrock, 0, ModFluffBlocks.livingrockStairs, ModFluffBlocks.livingrockSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.livingrock, 1, ModFluffBlocks.livingrockBrickStairs, ModFluffBlocks.livingrockBrickSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.dreamwood, 0, ModFluffBlocks.dreamwoodStairs, ModFluffBlocks.dreamwoodSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.dreamwood, 1, ModFluffBlocks.dreamwoodPlankStairs, ModFluffBlocks.dreamwoodPlankSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.prismarine, 0, ModFluffBlocks.prismarineStairs, ModFluffBlocks.prismarineSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.prismarine, 1, ModFluffBlocks.prismarineBrickStairs, ModFluffBlocks.prismarineBrickSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.prismarine, 2, ModFluffBlocks.darkPrismarineStairs, ModFluffBlocks.darkPrismarineSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.reedBlock, 0, ModFluffBlocks.reedStairs, ModFluffBlocks.reedSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.thatch, 0, ModFluffBlocks.thatchStairs, ModFluffBlocks.thatchSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.customBrick, 0, ModFluffBlocks.netherBrickStairs, ModFluffBlocks.netherBrickSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.customBrick, 1, ModFluffBlocks.soulBrickStairs, ModFluffBlocks.soulBrickSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.customBrick, 2, ModFluffBlocks.snowBrickStairs, ModFluffBlocks.snowBrickSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.customBrick, 3, ModFluffBlocks.tileStairs, ModFluffBlocks.tileSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.endStoneBrick, 0, ModFluffBlocks.endStoneStairs, ModFluffBlocks.endStoneSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.shimmerrock, 0, ModFluffBlocks.shimmerrockStairs, ModFluffBlocks.shimmerrockSlab);
        ModCraftingRecipes.addStairsAndSlabs(ModBlocks.shimmerwoodPlanks, 0, ModFluffBlocks.shimmerwoodPlankStairs, ModFluffBlocks.shimmerwoodPlankSlab);
        ModCraftingRecipes.addWall(ModBlocks.livingrock, 0, ModFluffBlocks.livingrockWall, 0);
        ModCraftingRecipes.addWall(ModBlocks.livingwood, 0, ModFluffBlocks.livingwoodWall, 0);
        ModCraftingRecipes.addWall(ModBlocks.dreamwood, 0, ModFluffBlocks.dreamwoodWall, 0);
        ModCraftingRecipes.addWall(ModBlocks.prismarine, 0, ModFluffBlocks.prismarineWall, 0);
        ModCraftingRecipes.addWall(ModBlocks.reedBlock, 0, ModFluffBlocks.reedWall, 0);
        for (i = 0; i < 8; ++i) {
            ModCraftingRecipes.addWall(ModFluffBlocks.biomeStoneA, i + 8, ModFluffBlocks.biomeStoneWall, i);
        }
        for (i = 0; i < 4; ++i) {
            ModCraftingRecipes.addWall(ModFluffBlocks.stone, i, ModFluffBlocks.stoneWall, i);
        }
        ModCraftingRecipes.addPane(ModBlocks.manaGlass, ModFluffBlocks.managlassPane);
        ModCraftingRecipes.addPane(ModBlocks.elfGlass, ModFluffBlocks.alfglassPane);
        ModCraftingRecipes.addPane(ModBlocks.bifrostPerm, ModFluffBlocks.bifrostPane);
        for (i = 0; i < 8; ++i) {
            GameRegistry.addSmelting((ItemStack)new ItemStack(ModFluffBlocks.biomeStoneA, 1, i + 8), (ItemStack)new ItemStack(ModFluffBlocks.biomeStoneA, 1, i), (float)0.1f);
            GameRegistry.addRecipe((ItemStack)new ItemStack(ModFluffBlocks.biomeStoneB, 4, i), (Object[])new Object[]{"SS", "SS", Character.valueOf('S'), new ItemStack(ModFluffBlocks.biomeStoneA, 1, i)});
            GameRegistry.addRecipe((ItemStack)new ItemStack(ModFluffBlocks.biomeStoneB, 1, i + 8), (Object[])new Object[]{"S", "S", Character.valueOf('S'), new ItemStack(ModFluffBlocks.biomeStoneSlabs[i + 16])});
            ModCraftingRecipes.addStairsAndSlabs(ModFluffBlocks.biomeStoneA, i, ModFluffBlocks.biomeStoneStairs[i], ModFluffBlocks.biomeStoneSlabs[i]);
            ModCraftingRecipes.addStairsAndSlabs(ModFluffBlocks.biomeStoneA, i + 8, ModFluffBlocks.biomeStoneStairs[i + 8], ModFluffBlocks.biomeStoneSlabs[i + 8]);
            ModCraftingRecipes.addStairsAndSlabs(ModFluffBlocks.biomeStoneB, i, ModFluffBlocks.biomeStoneStairs[i + 16], ModFluffBlocks.biomeStoneSlabs[i + 16]);
        }
        for (i = 0; i < 4; ++i) {
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stoneSlabs[i], 6), "QQQ", Character.valueOf('Q'), LibOreDict.STONE_18_VARIANTS[i]);
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stoneStairs[i], 4), "  Q", " QQ", "QQQ", Character.valueOf('Q'), LibOreDict.STONE_18_VARIANTS[i]);
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stoneStairs[i], 4), "Q  ", "QQ ", "QQQ", Character.valueOf('Q'), LibOreDict.STONE_18_VARIANTS[i]);
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stone, 1, i), "Q", "Q", Character.valueOf('Q'), new ItemStack(ModFluffBlocks.stoneSlabs[i]));
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stoneSlabs[i + 4], 6), "QQQ", Character.valueOf('Q'), LibOreDict.STONE_18_VARIANTS[i + 8]);
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stoneStairs[i + 4], 4), "  Q", " QQ", "QQQ", Character.valueOf('Q'), LibOreDict.STONE_18_VARIANTS[i + 8]);
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stoneStairs[i + 4], 4), "Q  ", "QQ ", "QQQ", Character.valueOf('Q'), LibOreDict.STONE_18_VARIANTS[i + 8]);
            ModCraftingRecipes.addOreDictRecipe(new ItemStack(ModFluffBlocks.stone, 1, i + 8), "Q", "Q", Character.valueOf('Q'), new ItemStack(ModFluffBlocks.stoneSlabs[i + 4]));
        }
        for (i = 0; i < ModFluffBlocks.pavementStairs.length; ++i) {
            ModCraftingRecipes.addStairsAndSlabs(ModFluffBlocks.pavement, i, ModFluffBlocks.pavementStairs[i], ModFluffBlocks.pavementSlabs[i]);
        }
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(Items.field_151120_aE, 9, 0), (Object[])new Object[]{new ItemStack(ModBlocks.reedBlock)});
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(Items.field_151015_O, 4, 0), (Object[])new Object[]{new ItemStack(ModBlocks.thatch)});
        if (Botania.gardenOfGlassLoaded) {
            ModCraftingRecipes.initGardenOfGlass();
        }
        int newRecipeListSize = CraftingManager.func_77594_a().func_77592_b().size();
        FMLLog.log((Level)Level.INFO, (String)"[Botania] Registered %d recipes.", (Object[])new Object[]{newRecipeListSize - recipeListSize});
    }

    private static void initGardenOfGlass() {
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(Blocks.field_150345_g), "livingRoot", "livingRoot", "livingRoot", "livingRoot");
        recipeRootToSapling = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(ModItems.fertilizer), "livingRoot");
        recipeRootToFertilizer = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(Blocks.field_150347_e), "pebble", "pebble", "pebble", "pebble");
        recipePebbleCobblestone = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addShapelessOreDictRecipe(new ItemStack(Items.field_151123_aH), new ItemStack(Items.field_151064_bs), new ItemStack(Items.field_151131_as));
        recipeMagmaToSlimeball = BotaniaAPI.getLatestAddedRecipe();
        ModCraftingRecipes.addOreDictRecipe(new ItemStack(Blocks.field_150378_br), "OGO", Character.valueOf('O'), new ItemStack(Blocks.field_150343_Z), Character.valueOf('G'), "eternalLifeEssence");
        recipeEndPortal = BotaniaAPI.getLatestAddedRecipe();
    }

    private static void addStairsAndSlabs(Block block, int meta, Block stairs, Block slab) {
        GameRegistry.addRecipe((ItemStack)new ItemStack(slab, 6), (Object[])new Object[]{"QQQ", Character.valueOf('Q'), new ItemStack(block, 1, meta)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(stairs, 4), (Object[])new Object[]{"  Q", " QQ", "QQQ", Character.valueOf('Q'), new ItemStack(block, 1, meta)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(stairs, 4), (Object[])new Object[]{"Q  ", "QQ ", "QQQ", Character.valueOf('Q'), new ItemStack(block, 1, meta)});
        GameRegistry.addRecipe((ItemStack)new ItemStack(block, 1, meta), (Object[])new Object[]{"Q", "Q", Character.valueOf('Q'), new ItemStack(slab)});
    }

    private static void addWall(Block block, int blockMeta, Block wall, int wallMeta) {
        GameRegistry.addRecipe((ItemStack)new ItemStack(wall, 6, wallMeta), (Object[])new Object[]{"BBB", "BBB", Character.valueOf('B'), new ItemStack(block, 1, blockMeta)});
    }

    private static void addPane(Block block, Block pane) {
        GameRegistry.addRecipe((ItemStack)new ItemStack(pane, 16), (Object[])new Object[]{"BBB", "BBB", Character.valueOf('B'), new ItemStack(block, 1)});
    }

    private static IRecipe addQuartzRecipes(int meta, Item req, Block block, Block stairs, Block slab) {
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(block), new Object[]{"QQ", "QQ", Character.valueOf('Q'), LibOreDict.QUARTZ[meta]}));
        GameRegistry.addRecipe((ItemStack)new ItemStack(block, 2, 2), (Object[])new Object[]{"Q", "Q", Character.valueOf('Q'), block});
        GameRegistry.addRecipe((ItemStack)new ItemStack(block, 1, 1), (Object[])new Object[]{"Q", "Q", Character.valueOf('Q'), slab});
        ModCraftingRecipes.addStairsAndSlabs(block, 0, stairs, slab);
        if (req != null) {
            if (req == Items.field_151044_h) {
                GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModItems.quartz, 8, meta), new Object[]{"QQQ", "QCQ", "QQQ", Character.valueOf('Q'), "gemQuartz", Character.valueOf('C'), new ItemStack(req, 1, 1)}));
            }
            GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(ModItems.quartz, 8, meta), new Object[]{"QQQ", "QCQ", "QQQ", Character.valueOf('Q'), "gemQuartz", Character.valueOf('C'), req}));
            return BotaniaAPI.getLatestAddedRecipe();
        }
        return null;
    }

    private static void addOreDictRecipe(ItemStack output, Object ... recipe) {
        CraftingManager.func_77594_a().func_77592_b().add(new ShapedOreRecipe(output, recipe));
    }

    private static void addShapelessOreDictRecipe(ItemStack output, Object ... recipe) {
        CraftingManager.func_77594_a().func_77592_b().add(new ShapelessOreRecipe(output, recipe));
    }
}

