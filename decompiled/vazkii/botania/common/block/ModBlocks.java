/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.oredict.OreDictionary
 */
package vazkii.botania.common.block;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.oredict.OreDictionary;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.block.BlockAlfPortal;
import vazkii.botania.common.block.BlockAltGrass;
import vazkii.botania.common.block.BlockAltar;
import vazkii.botania.common.block.BlockAvatar;
import vazkii.botania.common.block.BlockBifrost;
import vazkii.botania.common.block.BlockBifrostPerm;
import vazkii.botania.common.block.BlockCacophonium;
import vazkii.botania.common.block.BlockCell;
import vazkii.botania.common.block.BlockCocoon;
import vazkii.botania.common.block.BlockDreamwood;
import vazkii.botania.common.block.BlockEnchantedSoil;
import vazkii.botania.common.block.BlockEnderEye;
import vazkii.botania.common.block.BlockFakeAir;
import vazkii.botania.common.block.BlockFelPumpkin;
import vazkii.botania.common.block.BlockFloatingSpecialFlower;
import vazkii.botania.common.block.BlockForestEye;
import vazkii.botania.common.block.BlockGaiaHead;
import vazkii.botania.common.block.BlockGhostRail;
import vazkii.botania.common.block.BlockHourglass;
import vazkii.botania.common.block.BlockIncensePlate;
import vazkii.botania.common.block.BlockLightLauncher;
import vazkii.botania.common.block.BlockLightRelay;
import vazkii.botania.common.block.BlockLivingrock;
import vazkii.botania.common.block.BlockLivingwood;
import vazkii.botania.common.block.BlockManaBomb;
import vazkii.botania.common.block.BlockModDoubleFlower;
import vazkii.botania.common.block.BlockModFlower;
import vazkii.botania.common.block.BlockOpenCrate;
import vazkii.botania.common.block.BlockPistonRelay;
import vazkii.botania.common.block.BlockPlatform;
import vazkii.botania.common.block.BlockPylon;
import vazkii.botania.common.block.BlockRoot;
import vazkii.botania.common.block.BlockSolidVines;
import vazkii.botania.common.block.BlockSparkChanger;
import vazkii.botania.common.block.BlockSpecialFlower;
import vazkii.botania.common.block.BlockStorage;
import vazkii.botania.common.block.BlockTeruTeruBozu;
import vazkii.botania.common.block.BlockTinyPlanet;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.corporea.BlockCorporeaCrystalCube;
import vazkii.botania.common.block.corporea.BlockCorporeaFunnel;
import vazkii.botania.common.block.corporea.BlockCorporeaIndex;
import vazkii.botania.common.block.corporea.BlockCorporeaInterceptor;
import vazkii.botania.common.block.corporea.BlockCorporeaRetainer;
import vazkii.botania.common.block.decor.BlockBlaze;
import vazkii.botania.common.block.decor.BlockBuriedPetals;
import vazkii.botania.common.block.decor.BlockCustomBrick;
import vazkii.botania.common.block.decor.BlockDirtPath;
import vazkii.botania.common.block.decor.BlockElfGlass;
import vazkii.botania.common.block.decor.BlockEndStoneBrick;
import vazkii.botania.common.block.decor.BlockFloatingFlower;
import vazkii.botania.common.block.decor.BlockManaBeacon;
import vazkii.botania.common.block.decor.BlockManaFlame;
import vazkii.botania.common.block.decor.BlockManaGlass;
import vazkii.botania.common.block.decor.BlockModMushroom;
import vazkii.botania.common.block.decor.BlockPetalBlock;
import vazkii.botania.common.block.decor.BlockPrismarine;
import vazkii.botania.common.block.decor.BlockReeds;
import vazkii.botania.common.block.decor.BlockSeaLamp;
import vazkii.botania.common.block.decor.BlockShimmerrock;
import vazkii.botania.common.block.decor.BlockShimmerwoodPlanks;
import vazkii.botania.common.block.decor.BlockShinyFlower;
import vazkii.botania.common.block.decor.BlockStarfield;
import vazkii.botania.common.block.decor.BlockThatch;
import vazkii.botania.common.block.decor.BlockTinyPotato;
import vazkii.botania.common.block.decor.BlockUnstable;
import vazkii.botania.common.block.dispenser.BehaviourPoolMinecart;
import vazkii.botania.common.block.dispenser.BehaviourSeeds;
import vazkii.botania.common.block.dispenser.BehaviourWand;
import vazkii.botania.common.block.mana.BlockAlchemyCatalyst;
import vazkii.botania.common.block.mana.BlockBellows;
import vazkii.botania.common.block.mana.BlockBrewery;
import vazkii.botania.common.block.mana.BlockConjurationCatalyst;
import vazkii.botania.common.block.mana.BlockDistributor;
import vazkii.botania.common.block.mana.BlockEnchanter;
import vazkii.botania.common.block.mana.BlockForestDrum;
import vazkii.botania.common.block.mana.BlockManaDetector;
import vazkii.botania.common.block.mana.BlockManaVoid;
import vazkii.botania.common.block.mana.BlockPool;
import vazkii.botania.common.block.mana.BlockPrism;
import vazkii.botania.common.block.mana.BlockPump;
import vazkii.botania.common.block.mana.BlockRFGenerator;
import vazkii.botania.common.block.mana.BlockRuneAltar;
import vazkii.botania.common.block.mana.BlockSpawnerClaw;
import vazkii.botania.common.block.mana.BlockSpreader;
import vazkii.botania.common.block.mana.BlockTerraPlate;
import vazkii.botania.common.block.mana.BlockTurntable;
import vazkii.botania.common.block.string.BlockRedStringComparator;
import vazkii.botania.common.block.string.BlockRedStringContainer;
import vazkii.botania.common.block.string.BlockRedStringDispenser;
import vazkii.botania.common.block.string.BlockRedStringFertilizer;
import vazkii.botania.common.block.string.BlockRedStringInterceptor;
import vazkii.botania.common.block.string.BlockRedStringRelay;
import vazkii.botania.common.block.subtile.SubTileDecor;
import vazkii.botania.common.block.subtile.SubTileManastar;
import vazkii.botania.common.block.subtile.SubTilePureDaisy;
import vazkii.botania.common.block.subtile.functional.SubTileAgricarnation;
import vazkii.botania.common.block.subtile.functional.SubTileBellethorn;
import vazkii.botania.common.block.subtile.functional.SubTileBubbell;
import vazkii.botania.common.block.subtile.functional.SubTileClayconia;
import vazkii.botania.common.block.subtile.functional.SubTileDaffomill;
import vazkii.botania.common.block.subtile.functional.SubTileDreadthorn;
import vazkii.botania.common.block.subtile.functional.SubTileExoflame;
import vazkii.botania.common.block.subtile.functional.SubTileFallenKanade;
import vazkii.botania.common.block.subtile.functional.SubTileHeiseiDream;
import vazkii.botania.common.block.subtile.functional.SubTileHopperhock;
import vazkii.botania.common.block.subtile.functional.SubTileHyacidus;
import vazkii.botania.common.block.subtile.functional.SubTileJadedAmaranthus;
import vazkii.botania.common.block.subtile.functional.SubTileJiyuulia;
import vazkii.botania.common.block.subtile.functional.SubTileLoonuim;
import vazkii.botania.common.block.subtile.functional.SubTileMarimorphosis;
import vazkii.botania.common.block.subtile.functional.SubTileMedumone;
import vazkii.botania.common.block.subtile.functional.SubTileOrechid;
import vazkii.botania.common.block.subtile.functional.SubTileOrechidIgnem;
import vazkii.botania.common.block.subtile.functional.SubTilePollidisiac;
import vazkii.botania.common.block.subtile.functional.SubTileRannuncarpus;
import vazkii.botania.common.block.subtile.functional.SubTileSolegnolia;
import vazkii.botania.common.block.subtile.functional.SubTileSpectranthemum;
import vazkii.botania.common.block.subtile.functional.SubTileTangleberrie;
import vazkii.botania.common.block.subtile.functional.SubTileTigerseye;
import vazkii.botania.common.block.subtile.functional.SubTileVinculotus;
import vazkii.botania.common.block.subtile.generating.SubTileArcaneRose;
import vazkii.botania.common.block.subtile.generating.SubTileDandelifeon;
import vazkii.botania.common.block.subtile.generating.SubTileDaybloom;
import vazkii.botania.common.block.subtile.generating.SubTileEndoflame;
import vazkii.botania.common.block.subtile.generating.SubTileEntropinnyum;
import vazkii.botania.common.block.subtile.generating.SubTileGourmaryllis;
import vazkii.botania.common.block.subtile.generating.SubTileHydroangeas;
import vazkii.botania.common.block.subtile.generating.SubTileKekimurus;
import vazkii.botania.common.block.subtile.generating.SubTileMunchdew;
import vazkii.botania.common.block.subtile.generating.SubTileNarslimmus;
import vazkii.botania.common.block.subtile.generating.SubTileNightshade;
import vazkii.botania.common.block.subtile.generating.SubTileRafflowsia;
import vazkii.botania.common.block.subtile.generating.SubTileSpectrolus;
import vazkii.botania.common.block.subtile.generating.SubTileThermalily;
import vazkii.botania.common.block.tile.TileAlfPortal;
import vazkii.botania.common.block.tile.TileAltar;
import vazkii.botania.common.block.tile.TileAvatar;
import vazkii.botania.common.block.tile.TileBifrost;
import vazkii.botania.common.block.tile.TileBrewery;
import vazkii.botania.common.block.tile.TileCacophonium;
import vazkii.botania.common.block.tile.TileCell;
import vazkii.botania.common.block.tile.TileCocoon;
import vazkii.botania.common.block.tile.TileCraftCrate;
import vazkii.botania.common.block.tile.TileEnchanter;
import vazkii.botania.common.block.tile.TileEnderEye;
import vazkii.botania.common.block.tile.TileFakeAir;
import vazkii.botania.common.block.tile.TileFloatingFlower;
import vazkii.botania.common.block.tile.TileFloatingSpecialFlower;
import vazkii.botania.common.block.tile.TileForestEye;
import vazkii.botania.common.block.tile.TileGaiaHead;
import vazkii.botania.common.block.tile.TileHourglass;
import vazkii.botania.common.block.tile.TileIncensePlate;
import vazkii.botania.common.block.tile.TileLightRelay;
import vazkii.botania.common.block.tile.TileManaBeacon;
import vazkii.botania.common.block.tile.TileManaFlame;
import vazkii.botania.common.block.tile.TileOpenCrate;
import vazkii.botania.common.block.tile.TilePlatform;
import vazkii.botania.common.block.tile.TilePylon;
import vazkii.botania.common.block.tile.TileRuneAltar;
import vazkii.botania.common.block.tile.TileSparkChanger;
import vazkii.botania.common.block.tile.TileSpawnerClaw;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.block.tile.TileStarfield;
import vazkii.botania.common.block.tile.TileTerraPlate;
import vazkii.botania.common.block.tile.TileTeruTeruBozu;
import vazkii.botania.common.block.tile.TileTinyPlanet;
import vazkii.botania.common.block.tile.TileTinyPotato;
import vazkii.botania.common.block.tile.corporea.TileCorporeaCrystalCube;
import vazkii.botania.common.block.tile.corporea.TileCorporeaFunnel;
import vazkii.botania.common.block.tile.corporea.TileCorporeaIndex;
import vazkii.botania.common.block.tile.corporea.TileCorporeaInterceptor;
import vazkii.botania.common.block.tile.corporea.TileCorporeaRetainer;
import vazkii.botania.common.block.tile.mana.TileBellows;
import vazkii.botania.common.block.tile.mana.TileDistributor;
import vazkii.botania.common.block.tile.mana.TileManaDetector;
import vazkii.botania.common.block.tile.mana.TileManaVoid;
import vazkii.botania.common.block.tile.mana.TilePool;
import vazkii.botania.common.block.tile.mana.TilePrism;
import vazkii.botania.common.block.tile.mana.TilePump;
import vazkii.botania.common.block.tile.mana.TileRFGenerator;
import vazkii.botania.common.block.tile.mana.TileSpreader;
import vazkii.botania.common.block.tile.mana.TileTurntable;
import vazkii.botania.common.block.tile.string.TileRedStringComparator;
import vazkii.botania.common.block.tile.string.TileRedStringContainer;
import vazkii.botania.common.block.tile.string.TileRedStringDispenser;
import vazkii.botania.common.block.tile.string.TileRedStringFertilizer;
import vazkii.botania.common.block.tile.string.TileRedStringInterceptor;
import vazkii.botania.common.block.tile.string.TileRedStringRelay;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.lib.LibOreDict;

public final class ModBlocks {
    public static Block flower;
    public static Block altar;
    public static Block livingrock;
    public static Block livingwood;
    public static Block specialFlower;
    public static Block spreader;
    public static Block pool;
    public static Block runeAltar;
    public static Block unstableBlock;
    public static Block pylon;
    public static Block pistonRelay;
    public static Block distributor;
    public static Block manaBeacon;
    public static Block manaVoid;
    public static Block manaDetector;
    public static Block enchanter;
    public static Block turntable;
    public static Block tinyPlanet;
    public static Block alchemyCatalyst;
    public static Block openCrate;
    public static Block forestEye;
    public static Block storage;
    public static Block forestDrum;
    public static Block shinyFlower;
    public static Block platform;
    public static Block alfPortal;
    public static Block dreamwood;
    public static Block conjurationCatalyst;
    public static Block bifrost;
    public static Block solidVines;
    public static Block buriedPetals;
    public static Block prismarine;
    public static Block seaLamp;
    public static Block floatingFlower;
    public static Block tinyPotato;
    public static Block spawnerClaw;
    public static Block reedBlock;
    public static Block thatch;
    public static Block customBrick;
    public static Block enderEye;
    public static Block starfield;
    public static Block rfGenerator;
    public static Block elfGlass;
    public static Block brewery;
    public static Block manaGlass;
    public static Block terraPlate;
    public static Block redStringContainer;
    public static Block redStringDispenser;
    public static Block redStringFertilizer;
    public static Block redStringComparator;
    public static Block redStringRelay;
    public static Block floatingSpecialFlower;
    public static Block manaFlame;
    public static Block prism;
    public static Block dirtPath;
    public static Block enchantedSoil;
    public static Block petalBlock;
    public static Block corporeaIndex;
    public static Block corporeaFunnel;
    public static Block endStoneBrick;
    public static Block mushroom;
    public static Block pump;
    public static Block doubleFlower1;
    public static Block doubleFlower2;
    public static Block fakeAir;
    public static Block blazeBlock;
    public static Block corporeaInterceptor;
    public static Block corporeaCrystalCube;
    public static Block incensePlate;
    public static Block hourglass;
    public static Block ghostRail;
    public static Block sparkChanger;
    public static Block root;
    public static Block felPumpkin;
    public static Block cocoon;
    public static Block lightRelay;
    public static Block lightLauncher;
    public static Block manaBomb;
    public static Block cacophonium;
    public static Block bellows;
    public static Block bifrostPerm;
    public static Block cellBlock;
    public static Block redStringInterceptor;
    public static Block gaiaHead;
    public static Block corporeaRetainer;
    public static Block teruTeruBozu;
    public static Block shimmerrock;
    public static Block shimmerwoodPlanks;
    public static Block avatar;
    public static Block altGrass;

    public static void init() {
        int i;
        flower = new BlockModFlower();
        altar = new BlockAltar();
        livingrock = new BlockLivingrock();
        livingwood = new BlockLivingwood();
        specialFlower = new BlockSpecialFlower();
        spreader = new BlockSpreader();
        pool = new BlockPool();
        runeAltar = new BlockRuneAltar();
        unstableBlock = new BlockUnstable();
        pylon = new BlockPylon();
        pistonRelay = new BlockPistonRelay();
        distributor = new BlockDistributor();
        manaBeacon = new BlockManaBeacon();
        manaVoid = new BlockManaVoid();
        manaDetector = new BlockManaDetector();
        enchanter = new BlockEnchanter();
        turntable = new BlockTurntable();
        tinyPlanet = new BlockTinyPlanet();
        alchemyCatalyst = new BlockAlchemyCatalyst();
        openCrate = new BlockOpenCrate();
        forestEye = new BlockForestEye();
        storage = new BlockStorage();
        forestDrum = new BlockForestDrum();
        shinyFlower = new BlockShinyFlower();
        platform = new BlockPlatform();
        alfPortal = new BlockAlfPortal();
        dreamwood = new BlockDreamwood();
        conjurationCatalyst = new BlockConjurationCatalyst();
        bifrost = new BlockBifrost();
        solidVines = new BlockSolidVines();
        buriedPetals = new BlockBuriedPetals();
        prismarine = new BlockPrismarine();
        seaLamp = new BlockSeaLamp();
        floatingFlower = new BlockFloatingFlower();
        tinyPotato = new BlockTinyPotato();
        spawnerClaw = new BlockSpawnerClaw();
        reedBlock = new BlockReeds();
        thatch = new BlockThatch();
        customBrick = new BlockCustomBrick();
        enderEye = new BlockEnderEye();
        starfield = new BlockStarfield();
        rfGenerator = new BlockRFGenerator();
        elfGlass = new BlockElfGlass();
        brewery = new BlockBrewery();
        manaGlass = new BlockManaGlass();
        terraPlate = new BlockTerraPlate();
        redStringContainer = new BlockRedStringContainer();
        redStringDispenser = new BlockRedStringDispenser();
        redStringFertilizer = new BlockRedStringFertilizer();
        redStringComparator = new BlockRedStringComparator();
        redStringRelay = new BlockRedStringRelay();
        floatingSpecialFlower = new BlockFloatingSpecialFlower();
        manaFlame = new BlockManaFlame();
        prism = new BlockPrism();
        dirtPath = new BlockDirtPath();
        enchantedSoil = new BlockEnchantedSoil();
        petalBlock = new BlockPetalBlock();
        corporeaIndex = new BlockCorporeaIndex();
        corporeaFunnel = new BlockCorporeaFunnel();
        endStoneBrick = new BlockEndStoneBrick();
        mushroom = new BlockModMushroom();
        pump = new BlockPump();
        doubleFlower1 = new BlockModDoubleFlower(false);
        doubleFlower2 = new BlockModDoubleFlower(true);
        fakeAir = new BlockFakeAir();
        blazeBlock = new BlockBlaze();
        corporeaInterceptor = new BlockCorporeaInterceptor();
        corporeaCrystalCube = new BlockCorporeaCrystalCube();
        incensePlate = new BlockIncensePlate();
        hourglass = new BlockHourglass();
        ghostRail = new BlockGhostRail();
        sparkChanger = new BlockSparkChanger();
        root = new BlockRoot();
        felPumpkin = new BlockFelPumpkin();
        cocoon = new BlockCocoon();
        lightRelay = new BlockLightRelay();
        lightLauncher = new BlockLightLauncher();
        manaBomb = new BlockManaBomb();
        cacophonium = new BlockCacophonium();
        bellows = new BlockBellows();
        bifrostPerm = new BlockBifrostPerm();
        cellBlock = new BlockCell();
        redStringInterceptor = new BlockRedStringInterceptor();
        gaiaHead = new BlockGaiaHead();
        corporeaRetainer = new BlockCorporeaRetainer();
        teruTeruBozu = new BlockTeruTeruBozu();
        shimmerrock = new BlockShimmerrock();
        shimmerwoodPlanks = new BlockShimmerwoodPlanks();
        avatar = new BlockAvatar();
        altGrass = new BlockAltGrass();
        ModFluffBlocks.init();
        for (i = 0; i < 16; ++i) {
            OreDictionary.registerOre((String)LibOreDict.FLOWER[i], (ItemStack)new ItemStack(flower, 1, i));
        }
        OreDictionary.registerOre((String)"livingrock", (Block)livingrock);
        OreDictionary.registerOre((String)"livingwood", (Block)livingwood);
        OreDictionary.registerOre((String)"dreamwood", (Block)dreamwood);
        for (i = 0; i < 8; ++i) {
            OreDictionary.registerOre((String)LibOreDict.DOUBLE_FLOWER[i], (ItemStack)new ItemStack(doubleFlower1, 1, i));
            OreDictionary.registerOre((String)LibOreDict.DOUBLE_FLOWER[i + 8], (ItemStack)new ItemStack(doubleFlower2, 1, i));
        }
        OreDictionary.registerOre((String)"blockPrismarine", (ItemStack)new ItemStack(prismarine, 1, Short.MAX_VALUE));
        OreDictionary.registerOre((String)"blockBlaze", (Block)blazeBlock);
        for (i = 0; i < 16; ++i) {
            OreDictionary.registerOre((String)LibOreDict.STONE_18_VARIANTS[i], (ItemStack)new ItemStack(ModFluffBlocks.stone, 1, i));
        }
        OreDictionary.registerOre((String)"dirt", (Block)Blocks.field_150346_d);
        OreDictionary.registerOre((String)"grass", (Block)Blocks.field_150349_c);
        OreDictionary.registerOre((String)"sand", (Block)Block.func_149684_b((String)"sand"));
        OreDictionary.registerOre((String)"gravel", (Block)Block.func_149684_b((String)"gravel"));
        OreDictionary.registerOre((String)"hardenedClay", (ItemStack)new ItemStack(Blocks.field_150405_ch, 1, Short.MAX_VALUE));
        OreDictionary.registerOre((String)"snowLayer", (Block)Blocks.field_150431_aC);
        OreDictionary.registerOre((String)"mycelium", (Block)Blocks.field_150391_bh);
        OreDictionary.registerOre((String)"podzol", (ItemStack)new ItemStack(Blocks.field_150346_d, 1, 2));
        OreDictionary.registerOre((String)"netherrack", (Block)Blocks.field_150424_aL);
        OreDictionary.registerOre((String)"soulSand", (Block)Blocks.field_150425_aM);
        OreDictionary.registerOre((String)"ice", (Block)Blocks.field_150432_aD);
        OreDictionary.registerOre((String)"slabCobblestone", (ItemStack)new ItemStack((Block)Blocks.field_150333_U, 1, 3));
        OreDictionary.registerOre((String)"chestWood", (Block)Blocks.field_150486_ae);
        OreDictionary.registerOre((String)"craftingTableWood", (Block)Blocks.field_150462_ai);
        BotaniaAPI.registerPaintableBlock(unstableBlock);
        BotaniaAPI.registerPaintableBlock(manaBeacon);
        ModBlocks.initTileEntities();
    }

    public static void addDispenserBehaviours() {
        for (Item seed : BotaniaAPI.seeds.keySet()) {
            BlockDispenser.field_149943_a.func_82595_a((Object)seed, (Object)new BehaviourSeeds(BotaniaAPI.seeds.get(seed)));
        }
        BlockDispenser.field_149943_a.func_82595_a((Object)ModItems.twigWand, (Object)new BehaviourWand());
        BlockDispenser.field_149943_a.func_82595_a((Object)ModItems.poolMinecart, (Object)new BehaviourPoolMinecart());
    }

    private static void initTileEntities() {
        ModBlocks.registerTile(TileAltar.class, "altar");
        ModBlocks.registerTile(TileSpecialFlower.class, "specialFlower");
        ModBlocks.registerTile(TileSpreader.class, "spreader");
        ModBlocks.registerTile(TilePool.class, "pool");
        ModBlocks.registerTile(TileRuneAltar.class, "runeAltar");
        ModBlocks.registerTile(TilePylon.class, "pylon");
        ModBlocks.registerTile(TileDistributor.class, "distributor");
        ModBlocks.registerTile(TileManaBeacon.class, "manaBeacon");
        ModBlocks.registerTile(TileManaVoid.class, "manaVoid");
        ModBlocks.registerTile(TileManaDetector.class, "manaDetector");
        ModBlocks.registerTile(TileEnchanter.class, "enchanter");
        ModBlocks.registerTile(TileTurntable.class, "turntable");
        ModBlocks.registerTile(TileTinyPlanet.class, "tinyPlanetBlock");
        ModBlocks.registerTile(TileOpenCrate.class, "openCrate");
        ModBlocks.registerTile(TileCraftCrate.class, "craftCrate");
        ModBlocks.registerTile(TileForestEye.class, "forestEye");
        ModBlocks.registerTile(TilePlatform.class, "platform");
        ModBlocks.registerTile(TileAlfPortal.class, "alfheimPortal");
        ModBlocks.registerTile(TileBifrost.class, "bifrost");
        ModBlocks.registerTile(TileFloatingFlower.class, "miniIsland");
        ModBlocks.registerTile(TileTinyPotato.class, "tinyPotato");
        ModBlocks.registerTile(TileSpawnerClaw.class, "spawnerClaw");
        ModBlocks.registerTile(TileEnderEye.class, "enderEyeBlock");
        ModBlocks.registerTile(TileStarfield.class, "starfield");
        ModBlocks.registerTile(TileRFGenerator.class, "rfGenerator");
        ModBlocks.registerTile(TileBrewery.class, "brewery");
        ModBlocks.registerTile(TileTerraPlate.class, "terraPlate");
        ModBlocks.registerTile(TileRedStringContainer.class, "redStringContainer");
        ModBlocks.registerTile(TileRedStringDispenser.class, "redStringDispenser");
        ModBlocks.registerTile(TileRedStringFertilizer.class, "redStringFertilizer");
        ModBlocks.registerTile(TileRedStringComparator.class, "redStringComparator");
        ModBlocks.registerTile(TileRedStringRelay.class, "redStringRelay");
        ModBlocks.registerTile(TileFloatingSpecialFlower.class, "floatingSpecialFlower");
        ModBlocks.registerTile(TileManaFlame.class, "manaFlame");
        ModBlocks.registerTile(TilePrism.class, "prism");
        ModBlocks.registerTile(TileCorporeaIndex.class, "corporeaIndex");
        ModBlocks.registerTile(TileCorporeaFunnel.class, "corporeaFunnel");
        ModBlocks.registerTile(TilePump.class, "pump");
        ModBlocks.registerTile(TileFakeAir.class, "fakeAir");
        ModBlocks.registerTile(TileCorporeaInterceptor.class, "corporeaInterceptor");
        ModBlocks.registerTile(TileCorporeaCrystalCube.class, "corporeaCrystalCube");
        ModBlocks.registerTile(TileIncensePlate.class, "incensePlate");
        ModBlocks.registerTile(TileHourglass.class, "hourglass");
        ModBlocks.registerTile(TileSparkChanger.class, "sparkChanger");
        ModBlocks.registerTile(TileCocoon.class, "cocoon");
        ModBlocks.registerTile(TileLightRelay.class, "lightRelay");
        ModBlocks.registerTile(TileCacophonium.class, "cacophoniumBlock");
        ModBlocks.registerTile(TileBellows.class, "bellows");
        ModBlocks.registerTile(TileCell.class, "cellBlock");
        ModBlocks.registerTile(TileRedStringInterceptor.class, "redStringInterceptor");
        ModBlocks.registerTile(TileGaiaHead.class, "gaiaHeadBlock");
        ModBlocks.registerTile(TileCorporeaRetainer.class, "corporeaRetainer");
        ModBlocks.registerTile(TileTeruTeruBozu.class, "teruTeruBozu");
        ModBlocks.registerTile(TileAvatar.class, "avatar");
        BotaniaAPI.registerSubTile("puredaisy", SubTilePureDaisy.class);
        BotaniaAPI.registerSubTile("manastar", SubTileManastar.class);
        ModBlocks.registerSubTileWithDecor("daybloom", SubTileDaybloom.class, SubTileDecor.Daybloom.class);
        BotaniaAPI.registerSubTile("daybloomPrime", SubTileDaybloom.Prime.class);
        BotaniaAPI.registerSubTile("endoflame", SubTileEndoflame.class);
        ModBlocks.registerSubTileWithDecor("hydroangeas", SubTileHydroangeas.class, SubTileDecor.Hydroangeas.class);
        BotaniaAPI.registerSubTile("thermalily", SubTileThermalily.class);
        ModBlocks.registerSubTileWithDecor("nightshade", SubTileNightshade.class, SubTileDecor.Nightshade.class);
        BotaniaAPI.registerSubTile("nightshadePrime", SubTileNightshade.Prime.class);
        BotaniaAPI.registerSubTile("arcanerose", SubTileArcaneRose.class);
        BotaniaAPI.registerSubTile("munchdew", SubTileMunchdew.class);
        BotaniaAPI.registerSubTile("entropinnyum", SubTileEntropinnyum.class);
        BotaniaAPI.registerSubTile("kekimurus", SubTileKekimurus.class);
        BotaniaAPI.registerSubTile("gourmaryllis", SubTileGourmaryllis.class);
        BotaniaAPI.registerSubTile("narslimmus", SubTileNarslimmus.class);
        BotaniaAPI.registerSubTile("spectrolus", SubTileSpectrolus.class);
        BotaniaAPI.registerSubTile("dandelifeon", SubTileDandelifeon.class);
        BotaniaAPI.registerSubTile("rafflowsia", SubTileRafflowsia.class);
        ModBlocks.registerSubTileWithMini("bellethorn", SubTileBellethorn.class);
        BotaniaAPI.registerSubTile("dreadthorn", SubTileDreadthorn.class);
        BotaniaAPI.registerSubTile("heiseiDream", SubTileHeiseiDream.class);
        BotaniaAPI.registerSubTile("tigerseye", SubTileTigerseye.class);
        BotaniaAPI.registerSubTile("jadedAmaranthus", SubTileJadedAmaranthus.class);
        BotaniaAPI.registerSubTile("orechid", SubTileOrechid.class);
        BotaniaAPI.registerSubTile("orechidIgnem", SubTileOrechidIgnem.class);
        BotaniaAPI.registerSubTile("fallenKanade", SubTileFallenKanade.class);
        BotaniaAPI.registerSubTile("exoflame", SubTileExoflame.class);
        ModBlocks.registerSubTileWithMini("agricarnation", SubTileAgricarnation.class);
        ModBlocks.registerSubTileWithMini("hopperhock", SubTileHopperhock.class);
        BotaniaAPI.registerSubTile("tangleberrie", SubTileTangleberrie.class);
        BotaniaAPI.registerSubTile("jiyuulia", SubTileJiyuulia.class);
        ModBlocks.registerSubTileWithMini("rannuncarpus", SubTileRannuncarpus.class);
        BotaniaAPI.registerSubTile("hyacidus", SubTileHyacidus.class);
        BotaniaAPI.registerSubTile("pollidisiac", SubTilePollidisiac.class);
        ModBlocks.registerSubTileWithMini("clayconia", SubTileClayconia.class);
        BotaniaAPI.registerSubTile("loonium", SubTileLoonuim.class);
        BotaniaAPI.registerSubTile("daffomill", SubTileDaffomill.class);
        BotaniaAPI.registerSubTile("vinculotus", SubTileVinculotus.class);
        BotaniaAPI.registerSubTile("spectranthemum", SubTileSpectranthemum.class);
        BotaniaAPI.registerSubTile("medumone", SubTileMedumone.class);
        ModBlocks.registerSubTileWithMini("marimorphosis", SubTileMarimorphosis.class);
        ModBlocks.registerSubTileWithMini("bubbell", SubTileBubbell.class);
        ModBlocks.registerSubTileWithMini("solegnolia", SubTileSolegnolia.class);
    }

    public static void registerMultiparts() {
        if (Loader.isModLoaded((String)"ForgeMultipart")) {
            try {
                Class<?> clazz = Class.forName("vazkii.botania.common.integration.multipart.MultipartHandler");
                clazz.newInstance();
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
    }

    private static void registerSubTileWithMini(String key, Class<? extends SubTileEntity> clazz) {
        BotaniaAPI.registerSubTile(key, clazz);
        for (Class<?> innerClazz : clazz.getDeclaredClasses()) {
            if (!innerClazz.getSimpleName().equals("Mini")) continue;
            BotaniaAPI.registerMiniSubTile(key + "Chibi", innerClazz, key);
        }
    }

    private static void registerSubTileWithDecor(String key, Class<? extends SubTileEntity> clazz, Class<? extends SubTileEntity> decor) {
        BotaniaAPI.registerSubTile(key, clazz);
        BotaniaAPI.registerMiniSubTile(key + "Decor", decor, key);
    }

    private static void registerTile(Class<? extends TileEntity> clazz, String key) {
        GameRegistry.registerTileEntity(clazz, (String)("botania:" + key));
    }
}

