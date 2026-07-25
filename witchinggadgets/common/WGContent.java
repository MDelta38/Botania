/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.init.Blocks
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.Item$ToolMaterial
 *  net.minecraft.item.ItemArmor$ArmorMaterial
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.crafting.IRecipe
 *  net.minecraft.potion.Potion
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraftforge.common.ChestGenHooks
 *  net.minecraftforge.common.util.EnumHelper
 *  net.minecraftforge.oredict.OreDictionary
 *  net.minecraftforge.oredict.RecipeSorter
 *  net.minecraftforge.oredict.RecipeSorter$Category
 *  net.minecraftforge.oredict.ShapedOreRecipe
 *  net.minecraftforge.oredict.ShapelessOreRecipe
 *  thaumcraft.api.ItemApi
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.ConfigItems
 */
package witchinggadgets.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import java.util.Arrays;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigItems;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.WGResearch;
import witchinggadgets.common.blocks.BlockModifiedAiry;
import witchinggadgets.common.blocks.BlockRoseVines;
import witchinggadgets.common.blocks.BlockVoidWalkway;
import witchinggadgets.common.blocks.BlockWGMetalDevice;
import witchinggadgets.common.blocks.BlockWGStoneDevice;
import witchinggadgets.common.blocks.BlockWGWoodenDevice;
import witchinggadgets.common.blocks.BlockWallMirror;
import witchinggadgets.common.blocks.ItemBlockMetalDevice;
import witchinggadgets.common.blocks.ItemBlockStoneDevice;
import witchinggadgets.common.blocks.ItemBlockWoodenDevice;
import witchinggadgets.common.blocks.tiles.TileEntityAgeingStone;
import witchinggadgets.common.blocks.tiles.TileEntityBlastfurnace;
import witchinggadgets.common.blocks.tiles.TileEntityCobbleGen;
import witchinggadgets.common.blocks.tiles.TileEntityCuttingTable;
import witchinggadgets.common.blocks.tiles.TileEntityEssentiaPump;
import witchinggadgets.common.blocks.tiles.TileEntityEtherealWall;
import witchinggadgets.common.blocks.tiles.TileEntityLabelLibrary;
import witchinggadgets.common.blocks.tiles.TileEntityMagicalTileLock;
import witchinggadgets.common.blocks.tiles.TileEntitySarcophagus;
import witchinggadgets.common.blocks.tiles.TileEntitySaunaStove;
import witchinggadgets.common.blocks.tiles.TileEntitySnowGen;
import witchinggadgets.common.blocks.tiles.TileEntitySpinningWheel;
import witchinggadgets.common.blocks.tiles.TileEntityTempLight;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformFocus;
import witchinggadgets.common.blocks.tiles.TileEntityTerraformer;
import witchinggadgets.common.blocks.tiles.TileEntityVoidWalkway;
import witchinggadgets.common.blocks.tiles.TileEntityWallMirror;
import witchinggadgets.common.items.EntityItemReforming;
import witchinggadgets.common.items.ItemClusters;
import witchinggadgets.common.items.ItemCrystalCapsule;
import witchinggadgets.common.items.ItemInfusedGem;
import witchinggadgets.common.items.ItemMagicFood;
import witchinggadgets.common.items.ItemMaterials;
import witchinggadgets.common.items.ItemThaumiumShears;
import witchinggadgets.common.items.armor.ItemAdvancedRobes;
import witchinggadgets.common.items.armor.ItemPrimordialArmor;
import witchinggadgets.common.items.baubles.ItemCloak;
import witchinggadgets.common.items.baubles.ItemKama;
import witchinggadgets.common.items.baubles.ItemMagicalBaubles;
import witchinggadgets.common.items.tools.ItemBag;
import witchinggadgets.common.items.tools.ItemPrimordialAxe;
import witchinggadgets.common.items.tools.ItemPrimordialGlove;
import witchinggadgets.common.items.tools.ItemPrimordialHammer;
import witchinggadgets.common.items.tools.ItemPrimordialSword;
import witchinggadgets.common.items.tools.ItemScanCamera;
import witchinggadgets.common.magic.WGEnchantBackstab;
import witchinggadgets.common.magic.WGEnchantGemBrittle;
import witchinggadgets.common.magic.WGEnchantGemPotency;
import witchinggadgets.common.magic.WGEnchantInvisibleGear;
import witchinggadgets.common.magic.WGEnchantRideProtect;
import witchinggadgets.common.magic.WGEnchantStealth;
import witchinggadgets.common.magic.WGEnchantUnveiling;
import witchinggadgets.common.magic.WGPotion;
import witchinggadgets.common.util.Utilities;
import witchinggadgets.common.util.handler.WGMultiPartHandler;
import witchinggadgets.common.util.recipe.BagColourizationRecipe;
import witchinggadgets.common.util.recipe.CloakColourizationRecipe;
import witchinggadgets.common.util.recipe.InfernalBlastfurnaceRecipe;
import witchinggadgets.common.util.recipe.RobeColourizationRecipe;

public class WGContent {
    public static Block BlockWallMirror;
    public static Block BlockVoidWalkway;
    public static Block BlockPortal;
    public static Block BlockStoneDevice;
    public static Block BlockWoodenDevice;
    public static Block BlockMetalDevice;
    public static Block BlockMagicBed;
    public static Block BlockRoseVine;
    public static Block BlockCustomAiry;
    public static Item ItemMaterial;
    public static Item ItemCluster;
    public static Item ItemCapsule;
    public static Item ItemBag;
    public static Item ItemCloak;
    public static Item ItemKama;
    public static Item ItemThaumiumShears;
    public static Item ItemAdvancedRobeChest;
    public static Item ItemAdvancedRobeLegs;
    public static Item ItemMagicFoodstuffs;
    public static Item ItemMagicBed;
    public static Item ItemPrimordialGlove;
    public static Item ItemPrimordialHammer;
    public static Item ItemPrimordialAxe;
    public static Item ItemPrimordialSword;
    public static Item ItemPrimordialHelm;
    public static Item ItemPrimordialChest;
    public static Item ItemPrimordialLegs;
    public static Item ItemPrimordialBoots;
    public static Item ItemInfusedGem;
    public static Item ItemMagicalBaubles;
    public static Item ItemScanCamera;
    public static Item ItemRelic;
    public static Potion pot_knockbackRes;
    public static Potion pot_dissolve;
    public static Potion pot_cinderCoat;
    public static Enchantment enc_gemstonePotency;
    public static Enchantment enc_gemstoneBrittle;
    public static Enchantment enc_invisibleGear;
    public static Enchantment enc_unveiling;
    public static Enchantment enc_stealth;
    public static Enchantment enc_backstab;
    public static Enchantment enc_rideProtect;
    public static ItemArmor.ArmorMaterial armorMatSpecialRobe;
    public static Item.ToolMaterial primordialTool;
    public static ItemArmor.ArmorMaterial primordialArmor;
    static final String UUIDBASE = "424C5553-5747-1694-4452-";

    public static void preInit() {
        WGContent.preInitItems();
        WGContent.preInitBlocks();
    }

    public static void init() {
        int enchId;
        WGContent.initializeItems();
        WGContent.initializeBlocks();
        int k = Potion.field_76425_a.length;
        int l = 3;
        if (k < 128 - l) {
            Utilities.extendPotionArray(l);
        }
        String s = new UUID(109406002307L, 1L).toString();
        int potionId = WGConfig.getPotionID(32, "Knockback Resistance");
        if (potionId > 0) {
            pot_knockbackRes = new WGPotion(potionId, false, 0x6E6E6E, 0, false, 1).func_76390_b("wg.potionKnockbackRes").func_111184_a(SharedMonsterAttributes.field_111266_c, s, 0.34, 0);
        }
        if ((potionId = WGConfig.getPotionID(potionId, "Dissolve")) > 0) {
            pot_dissolve = new WGPotion(potionId, true, 4524869, 40, true, 2).func_76390_b("wg.potionDissolve");
        }
        if ((potionId = WGConfig.getPotionID(potionId, "Cinder Coat")) > 0) {
            pot_cinderCoat = new WGPotion(potionId, true, 9387807, 0, false, 3).func_76390_b("wg.potionCinderCoat");
        }
        if ((enchId = WGConfig.getEnchantmentID(64, "Gemstone Potency")) > 0) {
            enc_gemstonePotency = new WGEnchantGemPotency(enchId, 4);
        }
        if ((enchId = WGConfig.getEnchantmentID(enchId, "Gemstone Brittle")) > 0) {
            enc_gemstoneBrittle = new WGEnchantGemBrittle(enchId, 1);
        }
        if ((enchId = WGConfig.getEnchantmentID(enchId, "Gemstone Invisible Gear")) > 0) {
            enc_invisibleGear = new WGEnchantInvisibleGear(enchId);
        }
        if ((enchId = WGConfig.getEnchantmentID(enchId, "Gemstone Unveiling")) > 0) {
            enc_unveiling = new WGEnchantUnveiling(enchId);
        }
        if ((enchId = WGConfig.getEnchantmentID(enchId, "Gemstone Stealth")) > 0) {
            enc_stealth = new WGEnchantStealth(enchId);
        }
        if ((enchId = WGConfig.getEnchantmentID(enchId, "Gemstone Backstab")) > 0) {
            enc_backstab = new WGEnchantBackstab(enchId);
        }
        if ((enchId = WGConfig.getEnchantmentID(enchId, "Gemstone Ride Protection")) > 0) {
            enc_rideProtect = new WGEnchantRideProtect(enchId);
        }
    }

    public static void postInit() {
        WGContent.postInitItems();
        WGContent.postInitBlocks();
        WGContent.postInitThaumcraft();
    }

    private static void preInitBlocks() {
        BlockWallMirror = new BlockWallMirror().func_149663_c("WG_WallMirror");
        GameRegistry.registerBlock((Block)BlockWallMirror, (String)BlockWallMirror.func_149739_a().substring("tile.".length()));
        BlockVoidWalkway = new BlockVoidWalkway().func_149663_c("WG_VoidWalkway");
        GameRegistry.registerBlock((Block)BlockVoidWalkway, (String)BlockVoidWalkway.func_149739_a().substring("tile.".length()));
        BlockStoneDevice = new BlockWGStoneDevice().func_149663_c("WG_StoneDevice");
        GameRegistry.registerBlock((Block)BlockStoneDevice, ItemBlockStoneDevice.class, (String)BlockStoneDevice.func_149739_a().substring("tile.".length()));
        BlockWoodenDevice = new BlockWGWoodenDevice().func_149663_c("WG_WoodenDevice");
        GameRegistry.registerBlock((Block)BlockWoodenDevice, ItemBlockWoodenDevice.class, (String)BlockWoodenDevice.func_149739_a().substring("tile.".length()));
        BlockMetalDevice = new BlockWGMetalDevice().func_149663_c("WG_MetalDevice");
        GameRegistry.registerBlock((Block)BlockMetalDevice, ItemBlockMetalDevice.class, (String)BlockMetalDevice.func_149739_a().substring("tile.".length()));
        BlockRoseVine = new BlockRoseVines().func_149663_c("WG_RoseVine");
        GameRegistry.registerBlock((Block)BlockRoseVine, (String)BlockRoseVine.func_149739_a().substring("tile.".length()));
        BlockCustomAiry = new BlockModifiedAiry().func_149663_c("WG_CustomAir");
        GameRegistry.registerBlock((Block)BlockCustomAiry, (String)BlockCustomAiry.func_149739_a().substring("tile.".length()));
        OreDictionary.registerOre((String)"blockVoid", (ItemStack)new ItemStack(BlockMetalDevice, 1, 7));
    }

    private static void initializeBlocks() {
        if (Loader.isModLoaded((String)"ForgeMultipart")) {
            WGMultiPartHandler.instance.init();
        }
        WGContent.registerTile(TileEntityWallMirror.class);
        WGContent.registerTile(TileEntityVoidWalkway.class);
        WGContent.registerTile(TileEntityTempLight.class);
        WGContent.registerTile(TileEntityEtherealWall.class);
        WGContent.registerTile(TileEntityMagicalTileLock.class);
        WGContent.registerTile(TileEntitySarcophagus.class);
        WGContent.registerTile(TileEntityAgeingStone.class);
        WGContent.registerTile(TileEntityBlastfurnace.class);
        WGContent.registerTile(TileEntitySpinningWheel.class);
        WGContent.registerTile(TileEntitySnowGen.class);
        WGContent.registerTile(TileEntityCobbleGen.class);
        WGContent.registerTile(TileEntityCuttingTable.class);
        WGContent.registerTile(TileEntitySaunaStove.class);
        WGContent.registerTile(TileEntityLabelLibrary.class);
        WGContent.registerTile(TileEntityEssentiaPump.class);
        WGContent.registerTile(TileEntityTerraformer.class);
        WGContent.registerTile(TileEntityTerraformFocus.class);
    }

    private static void registerTile(Class<? extends TileEntity> c) {
        GameRegistry.registerTileEntity(c, (String)("WitchingGadgets_" + c.getCanonicalName().substring(c.getCanonicalName().lastIndexOf("."))));
    }

    private static void postInitBlocks() {
        boolean rc = WGModCompat.railcraftAllowBlastFurnace();
        for (int yy = 0; yy <= 1; ++yy) {
            for (int zz = 0; zz <= 2; ++zz) {
                for (int xx = 0; xx <= 2; ++xx) {
                    int pos = yy * 9 + zz * 3 + xx;
                    TileEntityBlastfurnace.brickBlock[pos] = rc ? GameRegistry.findBlock((String)"Railcraft", (String)"brick.infernal") : (pos < 9 && pos != 4 ? Blocks.field_150385_bj : (pos == 10 || pos == 12 || pos == 13 || pos == 14 || pos == 16 ? Blocks.field_150425_aM : Blocks.field_150343_Z));
                }
            }
        }
        TileEntityBlastfurnace.stairBlock = rc ? GameRegistry.findBlock((String)"Railcraft", (String)"stair") : Blocks.field_150387_bl;
    }

    private static void preInitItems() {
        ItemMaterial = new ItemMaterials().func_77655_b("WG_Material");
        GameRegistry.registerItem((Item)ItemMaterial, (String)ItemMaterial.func_77658_a());
        ItemBag = new ItemBag().func_77655_b("WG_Bag");
        GameRegistry.registerItem((Item)ItemBag, (String)ItemBag.func_77658_a());
        ItemThaumiumShears = new ItemThaumiumShears().func_77655_b("WG_ThaumiumShears");
        GameRegistry.registerItem((Item)ItemThaumiumShears, (String)ItemThaumiumShears.func_77658_a());
        ItemAdvancedRobeChest = new ItemAdvancedRobes(armorMatSpecialRobe, 2, 1).func_77655_b("WG_AdvancedRobeChest");
        GameRegistry.registerItem((Item)ItemAdvancedRobeChest, (String)ItemAdvancedRobeChest.func_77658_a());
        ItemAdvancedRobeLegs = new ItemAdvancedRobes(armorMatSpecialRobe, 2, 2).func_77655_b("WG_AdvancedRobeLegs");
        GameRegistry.registerItem((Item)ItemAdvancedRobeLegs, (String)ItemAdvancedRobeLegs.func_77658_a());
        ItemMagicFoodstuffs = new ItemMagicFood().func_77655_b("WG_MagicFood");
        GameRegistry.registerItem((Item)ItemMagicFoodstuffs, (String)ItemMagicFoodstuffs.func_77658_a());
        ItemCloak = (ItemCloak)new ItemCloak().func_77655_b("WG_Cloak");
        GameRegistry.registerItem((Item)ItemCloak, (String)ItemCloak.func_77658_a());
        ItemKama = (ItemKama)new ItemKama().func_77655_b("WG_Kama");
        GameRegistry.registerItem((Item)ItemKama, (String)ItemKama.func_77658_a());
        ItemInfusedGem = new ItemInfusedGem().func_77655_b("WG_InfusedGem");
        GameRegistry.registerItem((Item)ItemInfusedGem, (String)ItemInfusedGem.func_77658_a());
        ItemMagicalBaubles = new ItemMagicalBaubles().func_77655_b("WG_Baubles");
        GameRegistry.registerItem((Item)ItemMagicalBaubles, (String)ItemMagicalBaubles.func_77658_a());
        ItemScanCamera = new ItemScanCamera().func_77655_b("WG_ScanCamera");
        GameRegistry.registerItem((Item)ItemScanCamera, (String)ItemScanCamera.func_77658_a());
        ItemPrimordialGlove = new ItemPrimordialGlove().func_77655_b("WG_PrimordialGlove");
        GameRegistry.registerItem((Item)ItemPrimordialGlove, (String)ItemPrimordialGlove.func_77658_a());
        ItemPrimordialHammer = new ItemPrimordialHammer(primordialTool).func_77655_b("WG_PrimordialHammer");
        GameRegistry.registerItem((Item)ItemPrimordialHammer, (String)ItemPrimordialHammer.func_77658_a());
        ItemPrimordialAxe = new ItemPrimordialAxe(primordialTool).func_77655_b("WG_PrimordialAxe");
        GameRegistry.registerItem((Item)ItemPrimordialAxe, (String)ItemPrimordialAxe.func_77658_a());
        ItemPrimordialSword = new ItemPrimordialSword(primordialTool).func_77655_b("WG_PrimordialSword");
        GameRegistry.registerItem((Item)ItemPrimordialSword, (String)ItemPrimordialSword.func_77658_a());
        ItemPrimordialHelm = new ItemPrimordialArmor(primordialArmor, 4, 0).func_77655_b("WG_PrimordialHelm");
        GameRegistry.registerItem((Item)ItemPrimordialHelm, (String)ItemPrimordialHelm.func_77658_a());
        ItemPrimordialChest = new ItemPrimordialArmor(primordialArmor, 4, 1).func_77655_b("WG_PrimordialChest");
        GameRegistry.registerItem((Item)ItemPrimordialChest, (String)ItemPrimordialChest.func_77658_a());
        ItemPrimordialLegs = new ItemPrimordialArmor(primordialArmor, 4, 2).func_77655_b("WG_PrimordialLegs");
        GameRegistry.registerItem((Item)ItemPrimordialLegs, (String)ItemPrimordialLegs.func_77658_a());
        ItemPrimordialBoots = new ItemPrimordialArmor(primordialArmor, 4, 3).func_77655_b("WG_PrimordialBoots");
        GameRegistry.registerItem((Item)ItemPrimordialBoots, (String)ItemPrimordialBoots.func_77658_a());
        ItemCapsule = new ItemCrystalCapsule().func_77655_b("WG_CrystalFlask");
        GameRegistry.registerItem((Item)ItemCapsule, (String)ItemCapsule.func_77658_a());
        if (WGConfig.allowClusters) {
            ItemCluster = new ItemClusters().func_77655_b("WG_Cluster");
            GameRegistry.registerItem((Item)ItemCluster, (String)ItemCluster.func_77658_a());
        }
        OreDictionary.registerOre((String)"blockVoid", (ItemStack)new ItemStack(BlockMetalDevice, 1, 7));
        OreDictionary.registerOre((String)"crystalNetherQuartz", (ItemStack)new ItemStack(Items.field_151128_bU));
        OreDictionary.registerOre((String)"scribingTools", (ItemStack)new ItemStack(ConfigItems.itemInkwell, 1, Short.MAX_VALUE));
    }

    private static void initializeItems() {
        WGResearch.recipeList.put("THAUMIUMSHEARS", GameRegistry.addShapedRecipe((ItemStack)new ItemStack(ItemThaumiumShears), (Object[])new Object[]{" t", "t ", Character.valueOf('t'), ItemApi.getItem((String)"itemResource", (int)2)}));
        BlockDispenser.field_149943_a.func_82595_a((Object)ItemCapsule, (Object)new ItemCrystalCapsule.CapsuleDispenserBehaviour());
        GameRegistry.addRecipe((IRecipe)new RobeColourizationRecipe());
        GameRegistry.addRecipe((IRecipe)new CloakColourizationRecipe());
        GameRegistry.addRecipe((IRecipe)new BagColourizationRecipe());
        RecipeSorter.register((String)"WitchingGadgets:advrobedye", RobeColourizationRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"after:forge:shapelessore");
        RecipeSorter.register((String)"WitchingGadgets:cloakdye", CloakColourizationRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"after:forge:shapelessore");
        RecipeSorter.register((String)"WitchingGadgets:bagdye", BagColourizationRecipe.class, (RecipeSorter.Category)RecipeSorter.Category.SHAPELESS, (String)"after:forge:shapelessore");
        GameRegistry.addRecipe((IRecipe)new ShapedOreRecipe(new ItemStack(BlockMetalDevice, 1, 7), new Object[]{"vvv", "vvv", "vvv", Character.valueOf('v'), "ingotVoid"}));
        ItemStack voidIngot = (ItemStack)OreDictionary.getOres((String)"ingotVoid").get(0);
        GameRegistry.addRecipe((IRecipe)new ShapelessOreRecipe(new ItemStack(voidIngot.func_77973_b(), 9, voidIngot.func_77960_j()), new Object[]{"blockVoid"}));
        GameRegistry.addShapelessRecipe((ItemStack)new ItemStack(ItemMagicFoodstuffs, 1, 0), (Object[])new Object[]{Items.field_151075_bm, Items.field_151102_aT});
        GameRegistry.addShapedRecipe((ItemStack)new ItemStack(ItemMagicFoodstuffs, 1, 1), (Object[])new Object[]{"nnn", "www", Character.valueOf('n'), new ItemStack(ItemMagicFoodstuffs, 1, 0), Character.valueOf('w'), Items.field_151015_O});
        EntityRegistry.registerModEntity(EntityItemReforming.class, (String)"reformingItem", (int)0, (Object)WitchingGadgets.instance, (int)64, (int)1, (boolean)true);
        if (WGConfig.allowClusters) {
            for (int iOre = 0; iOre < ItemClusters.subNames.length; ++iOre) {
                OreDictionary.registerOre((String)("cluster" + ItemClusters.subNames[iOre]), (ItemStack)new ItemStack(ItemCluster, 1, iOre));
            }
        }
    }

    private static void postInitItems() {
        ChestGenHooks.getInfo((String)"towerChestContents").addItem(new WeightedRandomChestContent(new ItemStack(ItemMaterial, 1, 8), 1, 1, 8));
        ChestGenHooks.getInfo((String)"villageBlacksmith").addItem(new WeightedRandomChestContent(new ItemStack(ItemMaterial, 1, 8), 1, 1, 8));
        InfernalBlastfurnaceRecipe.tryAddIngotImprovement("Iron", "Steel", false);
        InfernalBlastfurnaceRecipe.tryAddSpecialOreMelting("Tungsten", "Tungsten", true);
        InfernalBlastfurnaceRecipe.tryAddSpecialOreMelting("Rutile", "Titanium", true);
    }

    private static void postInitThaumcraft() {
        AspectList addAspects = new AspectList().add(Aspect.TREE, 4).add(Aspect.CLOTH, 2).add(Aspect.MECHANISM, 2).add(Aspect.AIR, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockWoodenDevice, 1, 1), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.TREE, 2).add(Aspect.CRYSTAL, 2).add(Aspect.CRAFT, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockWoodenDevice, 1, 3), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.MECHANISM, 1).add(Aspect.EARTH, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockStoneDevice, 1, 0), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.MECHANISM, 3).add(Aspect.ELDRITCH, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockStoneDevice, 1, 1), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.EARTH, 1).add(Aspect.ELDRITCH, 2).add(Aspect.DARKNESS, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockStoneDevice, 1, 2), (AspectList)addAspects);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockStoneDevice, 1, 3), (AspectList)addAspects);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockStoneDevice, 1, 4), (AspectList)addAspects);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockStoneDevice, 1, 5), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.VOID, 2).add(Aspect.ELDRITCH, 1).add(Aspect.DARKNESS, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockStoneDevice, 1, 6), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.PLANT, 6).add(Aspect.ENTROPY, 4).add(Aspect.MAGIC, 4).add(Aspect.LIFE, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(BlockRoseVine, 1, Short.MAX_VALUE), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CLOTH, 1);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 0), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CLOTH, 1).add(Aspect.GREED, 1);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 1), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CLOTH, 1).add(Aspect.METAL, 1).add(Aspect.MAGIC, 1);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 2), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CLOTH, 3).add(Aspect.VOID, 3);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 3), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CLOTH, 3).add(Aspect.GREED, 3);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 4), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CLOTH, 3).add(Aspect.MAGIC, 2).add(Aspect.TAINT, 1);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 5), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CLOTH, 2).add(Aspect.BEAST, 3);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 6), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CRYSTAL, 5).add(Aspect.SENSES, 3).add(Aspect.EXCHANGE, 2).add(Aspect.POISON, 1);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 9), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.AIR, 2).add(Aspect.WATER, 2).add(Aspect.ORDER, 2).add(Aspect.SENSES, 2).add(Aspect.MIND, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 10), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.CRYSTAL, 4).add(Aspect.VOID, 4);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMaterial, 1, 12), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.FIRE, 1).add(Aspect.HUNGER, 1);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMagicFoodstuffs, 1, 0), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.MIND, 3).add(Aspect.HUNGER, 2).add(Aspect.FLESH, 2);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMagicFoodstuffs, 1, 2), (AspectList)addAspects);
        addAspects = new AspectList().add(Aspect.SENSES, 2).add(Aspect.MAN, 1);
        ThaumcraftApi.registerObjectTag((ItemStack)new ItemStack(ItemMagicalBaubles, 1, 4), (AspectList)addAspects);
        WGResearch.setupResearchPages();
        WGResearch.registerRecipes();
        WGResearch.registerResearch();
        WGResearch.modifyStandardThaumcraftResearch();
    }

    static void addOreAspects(String ore, AspectList aspects, boolean isRareOre) {
        AspectList al;
        if (!OreDictionary.getOres((String)("ore" + ore)).isEmpty() && !WGContent.oreHasAspects("ore" + ore)) {
            al = new AspectList().add(Aspect.METAL, Math.max(isRareOre ? 3 : 2, (isRareOre ? 4 : 3) - aspects.visSize())).add(Aspect.EARTH, 1);
            for (Aspect aa : aspects.getAspects()) {
                al.merge(aa, 1);
            }
            ThaumcraftApi.registerObjectTag((String)("ore" + ore), (AspectList)al);
        }
        if (!OreDictionary.getOres((String)("ingot" + ore)).isEmpty() && !WGContent.oreHasAspects("ingot" + ore)) {
            al = new AspectList().add(Aspect.METAL, Math.max(isRareOre ? 3 : 2, (isRareOre ? 5 : 4) - aspects.visSize()));
            for (Aspect aa : aspects.getAspects()) {
                al.merge(aa, aspects.getAmount(aa));
            }
            ThaumcraftApi.registerObjectTag((String)("ingot" + ore), (AspectList)al);
        }
        if (!OreDictionary.getOres((String)("nugget" + ore)).isEmpty() && !WGContent.oreHasAspects("nugget" + ore)) {
            ThaumcraftApi.registerObjectTag((String)("nugget" + ore), (AspectList)new AspectList().add(Aspect.METAL, 1));
        }
        if (!OreDictionary.getOres((String)("dust" + ore)).isEmpty() && !WGContent.oreHasAspects("dust" + ore)) {
            al = new AspectList().add(Aspect.METAL, Math.max(isRareOre ? 3 : 2, (isRareOre ? 4 : 3) - aspects.visSize())).add(Aspect.ENTROPY, 1);
            for (Aspect aa : aspects.getAspects()) {
                al.merge(aa, 1);
            }
            ThaumcraftApi.registerObjectTag((String)("dust" + ore), (AspectList)al);
        }
        if (!OreDictionary.getOres((String)("block" + ore)).isEmpty() && !WGContent.oreHasAspects("block" + ore)) {
            al = new AspectList().add(Aspect.METAL, Math.max(isRareOre ? 6 : 5, (isRareOre ? 7 : 6) - aspects.visSize())).add(Aspect.ENTROPY, 1);
            for (Aspect aa : aspects.getAspects()) {
                al.merge(aa, 1);
            }
            ThaumcraftApi.registerObjectTag((String)("block" + ore), (AspectList)al);
        }
    }

    static boolean oreHasAspects(String ore) {
        for (ItemStack stack : OreDictionary.getOres((String)ore)) {
            if (stack == null) continue;
            return ThaumcraftApi.objectTags.get(Arrays.asList(stack.func_77973_b(), stack.func_77960_j())) != null;
        }
        return false;
    }

    static {
        armorMatSpecialRobe = EnumHelper.addArmorMaterial((String)"WG:ADVANCEDCLOTH", (int)25, (int[])new int[]{2, 4, 3, 2}, (int)25);
        primordialTool = EnumHelper.addToolMaterial((String)"WG:PRIMORDIALTOOL", (int)4, (int)1500, (float)8.0f, (float)6.0f, (int)25);
        primordialArmor = EnumHelper.addArmorMaterial((String)"WG:PRIMORDIALARMOR", (int)40, (int[])new int[]{3, 7, 6, 3}, (int)30);
    }
}

