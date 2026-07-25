/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.BaubleType
 *  cpw.mods.fml.client.registry.RenderingRegistry
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.network.FMLEventChannel
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.EntityRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.LanguageRegistry
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.ICommand
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityList$EntityEggInfo
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EnumCreatureType
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.Potion
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.ForgeChunkManager
 *  net.minecraftforge.common.ForgeChunkManager$LoadingCallback
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 *  net.minecraftforge.oredict.OreDictionary
 *  org.apache.commons.lang3.tuple.MutablePair
 *  thaumcraft.api.ThaumcraftApi
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.api.wands.IWandRodOnUpdate
 *  thaumcraft.api.wands.StaffRod
 *  thaumcraft.api.wands.WandCap
 *  thaumcraft.api.wands.WandRod
 *  thaumcraft.common.blocks.BlockCandleItem
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.lib.crafting.ThaumcraftCraftingManager
 */
package flaxbeard.thaumicexploration;

import baubles.api.BaubleType;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import flaxbeard.thaumicexploration.block.BlockAutoSorter;
import flaxbeard.thaumicexploration.block.BlockBootsIce;
import flaxbeard.thaumicexploration.block.BlockBoundChest;
import flaxbeard.thaumicexploration.block.BlockBoundJar;
import flaxbeard.thaumicexploration.block.BlockCrucibleSouls;
import flaxbeard.thaumicexploration.block.BlockEverburnUrn;
import flaxbeard.thaumicexploration.block.BlockEverfullUrn;
import flaxbeard.thaumicexploration.block.BlockFloatyCandle;
import flaxbeard.thaumicexploration.block.BlockReplicator;
import flaxbeard.thaumicexploration.block.BlockSoulBrazier;
import flaxbeard.thaumicexploration.block.BlockThinkTank;
import flaxbeard.thaumicexploration.block.BlockTrashJar;
import flaxbeard.thaumicexploration.chunkLoader.ChunkLoaderCallback;
import flaxbeard.thaumicexploration.commands.CommandAlterRate;
import flaxbeard.thaumicexploration.commands.CommandCheckWarp;
import flaxbeard.thaumicexploration.common.CommonProxy;
import flaxbeard.thaumicexploration.enchantment.EnchantmentBinding;
import flaxbeard.thaumicexploration.enchantment.EnchantmentDisarm;
import flaxbeard.thaumicexploration.enchantment.EnchantmentNightVision;
import flaxbeard.thaumicexploration.entity.EntityTaintacleMinion;
import flaxbeard.thaumicexploration.event.TXBootsEventHandler;
import flaxbeard.thaumicexploration.event.TXEventHandler;
import flaxbeard.thaumicexploration.event.TXTickHandler;
import flaxbeard.thaumicexploration.gui.TXGuiHandler;
import flaxbeard.thaumicexploration.integration.TTIntegration;
import flaxbeard.thaumicexploration.item.ItemBauble;
import flaxbeard.thaumicexploration.item.ItemBaubleDiscountRing;
import flaxbeard.thaumicexploration.item.ItemBlankSeal;
import flaxbeard.thaumicexploration.item.ItemBrain;
import flaxbeard.thaumicexploration.item.ItemChestSeal;
import flaxbeard.thaumicexploration.item.ItemChestSealLinked;
import flaxbeard.thaumicexploration.item.ItemFoodTalisman;
import flaxbeard.thaumicexploration.item.ItemJarSeal;
import flaxbeard.thaumicexploration.item.ItemStabilizerBelt;
import flaxbeard.thaumicexploration.item.ItemTXArmorSpecial;
import flaxbeard.thaumicexploration.item.ItemTaintSeedFood;
import flaxbeard.thaumicexploration.misc.TXPotion;
import flaxbeard.thaumicexploration.misc.TXTaintPotion;
import flaxbeard.thaumicexploration.research.ModRecipes;
import flaxbeard.thaumicexploration.research.ModResearch;
import flaxbeard.thaumicexploration.tile.TileEntityAutoSorter;
import flaxbeard.thaumicexploration.tile.TileEntityBoundChest;
import flaxbeard.thaumicexploration.tile.TileEntityBoundJar;
import flaxbeard.thaumicexploration.tile.TileEntityCrucibleSouls;
import flaxbeard.thaumicexploration.tile.TileEntityEverburnUrn;
import flaxbeard.thaumicexploration.tile.TileEntityEverfullUrn;
import flaxbeard.thaumicexploration.tile.TileEntityFloatyCandle;
import flaxbeard.thaumicexploration.tile.TileEntityReplicator;
import flaxbeard.thaumicexploration.tile.TileEntitySoulBrazier;
import flaxbeard.thaumicexploration.tile.TileEntityThinkTank;
import flaxbeard.thaumicexploration.tile.TileEntityTrashJar;
import flaxbeard.thaumicexploration.wand.StaffRodTransmutative;
import flaxbeard.thaumicexploration.wand.WandRodAmberOnUpdate;
import flaxbeard.thaumicexploration.wand.WandRodBreadOnUpdate;
import flaxbeard.thaumicexploration.wand.WandRodNecromancerOnUpdate;
import flaxbeard.thaumicexploration.wand.WandRodTransmutationOnUpdate;
import flaxbeard.thaumicexploration.wand.WandRodTransmutative;
import java.lang.reflect.Field;
import java.util.ArrayList;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.tuple.MutablePair;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.wands.IWandRodOnUpdate;
import thaumcraft.api.wands.StaffRod;
import thaumcraft.api.wands.WandCap;
import thaumcraft.api.wands.WandRod;
import thaumcraft.common.blocks.BlockCandleItem;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.crafting.ThaumcraftCraftingManager;

@Mod(modid="ThaumicExploration", name="Thaumic Exploration", version="0.6.0", dependencies="required-after:Thaumcraft;after:ThaumicTinkerer")
public class ThaumicExploration {
    @Mod.Instance(value="ThaumicExploration")
    public static ThaumicExploration instance;
    public static FMLEventChannel channel;
    public static ArrayList<MutablePair<Item, Integer>> allowedItems;
    public static Item pureZombieBrain;
    public static int pureZombieBrainID;
    public static Item blankSeal;
    public static int blankSealID;
    public static Item chestSeal;
    public static int chestSealID;
    public static Item chestSealLinked;
    public static int chestSealLinkedID;
    public static Item jarSeal;
    public static int jarSealID;
    public static Item jarSealLinked;
    public static int jarSealLinkedID;
    public static Item transmutationCore;
    public static int transmutationCoreID;
    public static Item transmutationStaffCore;
    public static Item amberCore;
    public static int amberCoreID;
    public static Item amberStaffCore;
    public static int amberStaffCoreID;
    public static Item necroStaffCore;
    public static Item breadCore;
    public static int breadCoreID;
    public static Item sojournerCap;
    public static int sojournerCapID;
    public static Item sojournerCapUncharged;
    public static int sojournerCapUnchargedID;
    public static Item mechanistCap;
    public static int mechanistCapID;
    public static Item mechanistCapUncharged;
    public static int mechanistCapUnchargedID;
    public static Item theCandle;
    public static int theCandleID;
    public static Item maskEvil;
    public static int maskEvilID;
    public static Item focusNecromancy;
    public static int focusNecromancyID;
    public static Item bootsMeteor;
    public static int bootsMeteorID;
    public static Item bootsComet;
    public static int bootsCometID;
    public static Item charmNoTaint;
    public static int charmNoTaintID;
    public static Item charmTaint;
    public static int charmTaintID;
    public static Item talismanFood;
    public static int talismanFoodID;
    public static Item tentacleRing;
    public static Item stabilizerBelt;
    public static Item discountRing;
    public static Item enhancedHelmetRunic;
    public static Item enhancedChestRunic;
    public static Item enhancedLegsRunic;
    public static Item enhancedBootsRunic;
    public static Item enhancedHelmetRunic2;
    public static Item enhancedChestRunic2;
    public static Item enhancedLegsRunic2;
    public static Item enhancedBootsRunic2;
    public static int enhancedHelmetRunicID;
    public static int enhancedChestRunicID;
    public static int enhancedLegsRunicID;
    public static int enhancedBootsRunicID;
    public static int enhancedHelmetRunic2ID;
    public static int enhancedChestRunic2ID;
    public static int enhancedLegsRunic2ID;
    public static int enhancedBootsRunic2ID;
    public static Item taintBerry;
    public static int taintBerryID;
    public static Item itemAltar;
    public static int itemAltarID;
    public static Block boundChest;
    public static int boundChestID;
    public static Block boundJar;
    public static int boundJarID;
    public static Block thinkTankJar;
    public static int thinkTankJarID;
    public static Block everfullUrn;
    public static int everfullUrnID;
    public static Block everburnUrn;
    public static int everburnUrnID;
    public static Block soulBrazier;
    public static int soulBrazierID;
    public static Block autoSorter;
    public static int autoSorterID;
    public static Block trashJar;
    public static Block necroPedestal;
    public static int necroPedestalID;
    public static Block necroFire;
    public static int necroFireID;
    public static Block crucibleSouls;
    public static int crucibleSoulsID;
    public static Block taintBerryCrop;
    public static int taintBerryCropID;
    public static Block meltyIce;
    public static int meltyIceID;
    public static Block replicator;
    public static int replicatorID;
    public static Block skullCandle;
    public static int skullCandleID;
    public static Block floatCandle;
    public static int floatCandleID;
    public static WandRod WAND_ROD_CRYSTAL;
    public static WandRod STAFF_ROD_CRYSTAL;
    public static WandRod WAND_ROD_AMBER;
    public static WandRod WAND_ROD_NECRO;
    public static WandRod WAND_ROD_BREAD;
    public static StaffRod STAFF_ROD_AMBER;
    public static WandCap WAND_CAP_SOJOURNER;
    public static WandCap WAND_CAP_MECHANIST;
    public static StaffRod STAFF_ROD_NECRO;
    public static int everfullUrnRenderID;
    public static int soulBrazierRenderID;
    public static int crucibleSoulsRenderID;
    public static int replicatorRenderID;
    public static int candleSkullRenderID;
    public static int necroPedestalRenderID;
    public static int floatCandleRenderID;
    public static int trashJarRenderID;
    public static CreativeTabs tab;
    public static boolean allowBoundInventories;
    public static boolean allowReplication;
    public static boolean allowMagicPlankReplication;
    public static boolean allowModWoodReplication;
    public static boolean allowModStoneReplication;
    public static boolean allowCrucSouls;
    public static boolean allowThinkTank;
    public static boolean allowFood;
    public static boolean allowUrn;
    public static boolean allowbUrn;
    public static boolean allowBoots;
    public static boolean allowSojourner;
    public static boolean allowMechanist;
    public static boolean allowEnchants;
    public static boolean allowTainturgy;
    public static Aspect fakeAspectNecro;
    public static boolean allowOsmotic;
    public static boolean prefix;
    public static boolean brainsGolem;
    public static boolean taintBloom;
    public static boolean breadWand;
    public static int potionBindingID;
    public static int potionTaintWithdrawlID;
    public static Enchantment enchantmentBinding;
    public static Enchantment enchantmentNightVision;
    public static Enchantment enchantmentDisarm;
    public static int enchantmentBindingID;
    public static int enchantmentNightVisionID;
    public static int enchantmentDisarmID;
    public static Potion potionBinding;
    public static Potion potionTaintWithdrawl;
    @SidedProxy(clientSide="flaxbeard.thaumicexploration.client.ClientProxy", serverSide="flaxbeard.thaumicexploration.common.CommonProxy")
    public static CommonProxy proxy;
    private TXTickHandler tickHandler;
    private TXBootsEventHandler entityEventHandler;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Potion[] potionTypes = null;
        for (Field f : Potion.class.getDeclaredFields()) {
            f.setAccessible(true);
            try {
                if (!f.getName().equals("potionTypes") && !f.getName().equals("field_76425_a")) continue;
                Field modfield = Field.class.getDeclaredField("modifiers");
                modfield.setAccessible(true);
                modfield.setInt(f, f.getModifiers() & 0xFFFFFFEF);
                potionTypes = (Potion[])f.get(null);
                Potion[] newPotionTypes = new Potion[256];
                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
                f.set(null, newPotionTypes);
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        Configuration config = new Configuration(event.getSuggestedConfigurationFile());
        config.load();
        potionTaintWithdrawlID = config.get("Potion", "Taint Withdrawl", 32).getInt();
        potionBindingID = config.get("Potion", "Binding", 31).getInt();
        enchantmentBindingID = config.get("Enchantment", "Binding", 77).getInt();
        enchantmentNightVisionID = config.get("Enchantment", "Night Vision", 78).getInt();
        enchantmentDisarmID = config.get("Enchantment", "Disarming", 79).getInt();
        prefix = config.get("Miscellaneous", "Display [TX] prefix before Thaumic Exploration research", true).getBoolean(true);
        breadWand = config.get("Easter Eggs", "Enable Thaumic Frenchurgy", false).getBoolean(true);
        brainsGolem = config.get("Miscellaneous", "Use Purified Brains in advanced golems", true).getBoolean(true);
        allowBoundInventories = config.get("Miscellaneous", "Enable bound inventories", true).getBoolean(true);
        allowReplication = config.get("Miscellaneous", "Enable Thaumic Replicator", true).getBoolean(true);
        allowCrucSouls = config.get("Miscellaneous", "Enable Crucible of Souls", true).getBoolean(true);
        allowThinkTank = config.get("Miscellaneous", "Enable Think Tank", true).getBoolean(true);
        allowFood = config.get("Miscellaneous", "Enable Talisman of Nourishment", true).getBoolean(true);
        allowUrn = config.get("Miscellaneous", "Enable Everfull Urn", true).getBoolean(true);
        allowbUrn = config.get("Miscellaneous", "Enable Everburn Urn", true).getBoolean(true);
        allowBoots = config.get("Miscellaneous", "Enable Boots of the Meteor/Comet", true).getBoolean(true);
        allowSojourner = config.get("Miscellaneous", "Enable Sojourner's Wand Caps", true).getBoolean(true);
        allowMechanist = config.get("Miscellaneous", "Enable Mechanist's Wand Caps", true).getBoolean(true);
        allowEnchants = config.get("Miscellaneous", "Enable TX Enchantments", true).getBoolean(true);
        allowTainturgy = config.get("Miscellaneous", "Enable Wispy Dreamcatcher", true).getBoolean(true);
        allowMagicPlankReplication = config.get("Replicator", "Allow replication of Greatwood/Silverwood planks", true).getBoolean(true);
        allowModWoodReplication = config.get("Replicator", "Allow replication of other mods' logs and planks", true).getBoolean(true);
        allowModStoneReplication = config.get("Replicator", "Allow replication of other mods' stone blocks", true).getBoolean(true);
        config.save();
        tab = new TXTab(CreativeTabs.getNextID(), "thaumicExploration");
        thinkTankJar = new BlockThinkTank(thinkTankJarID, false).func_149663_c("thaumicexploration:thinkTankJar").func_149647_a(tab).func_149658_d("thaumicExploration:blankTexture");
        everfullUrn = new BlockEverfullUrn(everfullUrnID).func_149711_c(2.0f).func_149663_c("thaumicexploration:everfullUrn").func_149647_a(tab).func_149658_d("thaumicExploration:everfullUrn");
        everburnUrn = new BlockEverburnUrn(everburnUrnID).func_149711_c(2.0f).func_149663_c("thaumicexploration:everburnUrn").func_149647_a(tab).func_149658_d("thaumicExploration:everfullUrn");
        soulBrazier = new BlockSoulBrazier().func_149711_c(2.0f).func_149663_c("thaumicexploration:soulBrazier").func_149647_a(tab).func_149658_d("thaumicExploration:soulBrazier");
        crucibleSouls = new BlockCrucibleSouls(crucibleSoulsID).func_149711_c(2.0f).func_149663_c("thaumicexploration:crucibleSouls").func_149647_a(tab).func_149658_d("thaumicExploration:crucible3");
        replicator = new BlockReplicator(replicatorID).func_149711_c(4.0f).func_149663_c("thaumicexploration:replicator").func_149647_a(tab).func_149658_d("thaumicexploration:replicatorBottom");
        meltyIce = new BlockBootsIce(meltyIceID).func_149663_c("thaumicexploration:meltyIce").func_149711_c(0.5f).func_149713_g(3).func_149672_a(Block.field_149778_k).func_149663_c("ice").func_149658_d("ice");
        boundChest = new BlockBoundChest(boundChestID, 0).func_149711_c(2.5f).func_149672_a(Block.field_149766_f).func_149663_c("boundChest");
        boundJar = new BlockBoundJar(boundJarID).func_149663_c("boundJar");
        autoSorter = new BlockAutoSorter(autoSorterID, Material.field_151592_s).func_149711_c(4.0f).func_149663_c("thaumicexploration:autoSorter").func_149647_a(tab).func_149658_d("thaumicexploration:replicatorBottom");
        floatCandle = new BlockFloatyCandle(floatCandleID).func_149663_c("thaumicexploration:floatCandle").func_149647_a(tab);
        trashJar = new BlockTrashJar().func_149663_c("thaumicexploration:trashJar");
        GameRegistry.registerBlock((Block)autoSorter, (String)"autoSorter");
        GameRegistry.registerBlock((Block)trashJar, (String)"trashJar");
        GameRegistry.registerBlock((Block)boundChest, (String)"boundChest");
        GameRegistry.registerBlock((Block)floatCandle, BlockCandleItem.class, (String)"floatCandle");
        GameRegistry.registerBlock((Block)meltyIce, (String)"meltyIce");
        GameRegistry.registerBlock((Block)boundJar, (String)"boundJar");
        GameRegistry.registerBlock((Block)thinkTankJar, (String)"thinkTankJar");
        GameRegistry.registerBlock((Block)everfullUrn, (String)"everfullUrn");
        GameRegistry.registerBlock((Block)everburnUrn, (String)"everburnUrn");
        GameRegistry.registerBlock((Block)soulBrazier, (String)"soulBrazier");
        GameRegistry.registerBlock((Block)crucibleSouls, (String)"crucibleSouls");
        GameRegistry.registerBlock((Block)replicator, (String)"replicator");
        transmutationCore = new Item().func_77655_b("thaumicexploration:transmutationCore").func_77637_a(tab).func_111206_d("thaumicexploration:rodTransmutation");
        GameRegistry.registerItem((Item)transmutationCore, (String)"transmutationCore");
        transmutationStaffCore = new Item().func_77655_b("thaumicexploration:transmutationStaffCore").func_77637_a(tab).func_111206_d("thaumicexploration:rodTransmutation_staff");
        GameRegistry.registerItem((Item)transmutationStaffCore, (String)"transmutationStaffCore");
        talismanFood = new ItemFoodTalisman(talismanFoodID).func_77655_b("thaumicexploration:talismanFood").func_77637_a(tab).func_111206_d("thaumicexploration:talismanFood");
        GameRegistry.registerItem((Item)talismanFood, (String)"talismanFood");
        amberCore = new Item().func_77655_b("thaumicexploration:amberCore").func_77637_a(tab).func_111206_d("thaumicexploration:rodAmber");
        GameRegistry.registerItem((Item)amberCore, (String)"amberCore");
        amberStaffCore = new Item().func_77655_b("thaumicexploration:amberStaffCore").func_77637_a(tab).func_111206_d("thaumicexploration:rodAmber_staff");
        GameRegistry.registerItem((Item)amberStaffCore, (String)"amberStaffCore");
        necroStaffCore = new Item().func_77655_b("thaumicexploration:necroStaffCore").func_77637_a(tab).func_111206_d("thaumicexploration:rodNecro_staff");
        GameRegistry.registerItem((Item)necroStaffCore, (String)"necroStaffCore");
        if (breadWand) {
            breadCore = new Item().func_77655_b("thaumicexploration:breadCore").func_77637_a(tab).func_111206_d("thaumicexploration:rodBread");
            GameRegistry.registerItem((Item)breadCore, (String)"breadCore");
        }
        sojournerCap = new Item().func_77655_b("thaumicexploration:capSojourner").func_77637_a(tab).func_111206_d("thaumicexploration:capSojournerCharged");
        GameRegistry.registerItem((Item)sojournerCap, (String)"sojournerCap");
        sojournerCapUncharged = new Item().func_77655_b("thaumicexploration:capSojournerInert").func_77637_a(tab).func_111206_d("thaumicexploration:capSojourner");
        GameRegistry.registerItem((Item)sojournerCapUncharged, (String)"sojournerCapUncharged");
        mechanistCap = new Item().func_77655_b("thaumicexploration:capMechanist").func_77637_a(tab).func_111206_d("thaumicexploration:capMechanistCharged");
        GameRegistry.registerItem((Item)mechanistCap, (String)"mechanistCap");
        mechanistCapUncharged = new Item().func_77655_b("thaumicexploration:capMechanistInert").func_77637_a(tab).func_111206_d("thaumicexploration:capMechanist");
        GameRegistry.registerItem((Item)mechanistCapUncharged, (String)"mechanistCapUncharged");
        pureZombieBrain = new ItemBrain(pureZombieBrainID).func_77655_b("thaumicexploration:pureZombieBrain").func_77637_a(tab).func_111206_d("thaumicexploration:pureZombieBrain");
        GameRegistry.registerItem((Item)pureZombieBrain, (String)"pureZombieBrain");
        blankSeal = new ItemBlankSeal(blankSealID).func_77637_a(tab).func_111206_d("thaumicexploration:sealBlank");
        GameRegistry.registerItem((Item)blankSeal, (String)"blankSeal");
        chestSeal = new ItemChestSeal(chestSealID).func_77637_a(tab).func_111206_d("thaumicexploration:sealChest").func_77655_b("thaumicexploration:chestSeal");
        GameRegistry.registerItem((Item)chestSeal, (String)"chestSeal");
        chestSealLinked = new ItemChestSealLinked(chestSealLinkedID).func_111206_d("thaumicexploration:sealChest").func_77655_b("thaumicexploration:chestSeal");
        GameRegistry.registerItem((Item)chestSealLinked, (String)"chestSealLinked");
        jarSeal = new ItemJarSeal().func_77637_a(tab).func_111206_d("thaumicexploration:sealJar").func_77655_b("thaumicexploration:jarSeal");
        GameRegistry.registerItem((Item)jarSeal, (String)"jarSeal");
        charmNoTaint = new Item().func_77655_b("thaumicexploration:dreamcatcher").func_77637_a(tab).func_111206_d("thaumicexploration:dreamcatcher");
        GameRegistry.registerItem((Item)charmNoTaint, (String)"charmNoTaint");
        charmTaint = new Item().func_77655_b("thaumicexploration:ringTaint").func_77637_a(tab).func_111206_d("thaumicexploration:taintRing");
        GameRegistry.registerItem((Item)charmTaint, (String)"charmTaint");
        bootsMeteor = new ItemTXArmorSpecial(bootsMeteorID, ThaumcraftApi.armorMatSpecial, 4, 3).func_77655_b("thaumicexploration:bootsMeteor").func_77637_a(tab).func_111206_d("thaumicexploration:bootsMeteor");
        GameRegistry.registerItem((Item)bootsMeteor, (String)"bootsMeteor");
        bootsComet = new ItemTXArmorSpecial(bootsCometID, ThaumcraftApi.armorMatSpecial, 4, 3).func_77655_b("thaumicexploration:bootsComet").func_77637_a(tab).func_111206_d("thaumicexploration:bootsComet");
        GameRegistry.registerItem((Item)bootsComet, (String)"bootsComet");
        taintBerry = new ItemTaintSeedFood(taintBerryID, 1, 0.3f, Blocks.field_150335_W, ConfigBlocks.blockTaint).func_77637_a(tab).func_77655_b("thaumicexploration:taintBerry").func_111206_d("thaumicExploration:taintBerry");
        GameRegistry.registerItem((Item)taintBerry, (String)"taintBerry");
        tentacleRing = new ItemBauble(BaubleType.RING).func_77637_a(tab).func_77655_b("thaumicexploration:tentacleRing").func_111206_d("thaumicExploration:taintaclering");
        GameRegistry.registerItem((Item)tentacleRing, (String)"tentacleRing");
        stabilizerBelt = new ItemStabilizerBelt().func_77637_a(tab).func_77655_b("thaumicexploration:stabilizerBelt").func_111206_d("thaumicExploration:stabilizerBelt");
        GameRegistry.registerItem((Item)stabilizerBelt, (String)"stabilizerBelt");
        discountRing = new ItemBaubleDiscountRing().func_77637_a(tab).func_77655_b("thaumicexploration:discountRing").func_111206_d("thaumicExploration:discountRing");
        GameRegistry.registerItem((Item)discountRing, (String)"discountRing");
        ForgeChunkManager.setForcedChunkLoadingCallback((Object)instance, (ForgeChunkManager.LoadingCallback)new ChunkLoaderCallback());
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new CommandAlterRate());
        event.registerServerCommand((ICommand)new CommandCheckWarp());
    }

    @Mod.EventHandler
    public void load(FMLInitializationEvent event) {
        channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("tExploration");
        this.tickHandler = new TXTickHandler();
        FMLCommonHandler.instance().bus().register((Object)this.tickHandler);
        this.entityEventHandler = new TXBootsEventHandler();
        MinecraftForge.EVENT_BUS.register((Object)this.entityEventHandler);
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)instance, (IGuiHandler)new TXGuiHandler());
        everfullUrnRenderID = RenderingRegistry.getNextAvailableRenderId();
        crucibleSoulsRenderID = RenderingRegistry.getNextAvailableRenderId();
        replicatorRenderID = RenderingRegistry.getNextAvailableRenderId();
        candleSkullRenderID = RenderingRegistry.getNextAvailableRenderId();
        necroPedestalRenderID = RenderingRegistry.getNextAvailableRenderId();
        floatCandleRenderID = RenderingRegistry.getNextAvailableRenderId();
        trashJarRenderID = RenderingRegistry.getNextAvailableRenderId();
        soulBrazierRenderID = RenderingRegistry.getNextAvailableRenderId();
        MinecraftForge.EVENT_BUS.register((Object)new TXEventHandler());
        FMLCommonHandler.instance().bus().register((Object)new TXEventHandler());
        GameRegistry.registerTileEntity(TileEntityFloatyCandle.class, (String)"tileEntityFloatyCandle");
        GameRegistry.registerTileEntity(TileEntityAutoSorter.class, (String)"tileEntityAutoSorter");
        GameRegistry.registerTileEntity(TileEntityBoundChest.class, (String)"tileEntityBoundChest");
        GameRegistry.registerTileEntity(TileEntityBoundJar.class, (String)"tileEntityBoundJar");
        GameRegistry.registerTileEntity(TileEntityThinkTank.class, (String)"tileEntityThinkTank");
        GameRegistry.registerTileEntity(TileEntityEverfullUrn.class, (String)"tileEntityEverfullUrn");
        GameRegistry.registerTileEntity(TileEntityEverburnUrn.class, (String)"tileEntityEverburnUrn");
        GameRegistry.registerTileEntity(TileEntitySoulBrazier.class, (String)"tileEntitySoulBrazier");
        GameRegistry.registerTileEntity(TileEntityCrucibleSouls.class, (String)"tileEntityCrucibleSouls");
        GameRegistry.registerTileEntity(TileEntityReplicator.class, (String)"tileEntityReplicator");
        GameRegistry.registerTileEntity(TileEntityTrashJar.class, (String)"tileEntityTrashJar");
        STAFF_ROD_AMBER = new StaffRod("AMBER", 25, new ItemStack(amberStaffCore), 18, (IWandRodOnUpdate)new WandRodAmberOnUpdate(), new ResourceLocation("thaumicexploration:textures/models/rodAmber.png"));
        WAND_ROD_AMBER = new WandRod("AMBER", 10, new ItemStack(amberCore), 8, (IWandRodOnUpdate)new WandRodAmberOnUpdate(), new ResourceLocation("thaumicexploration:textures/models/rodAmber.png"));
        WAND_ROD_CRYSTAL = new WandRodTransmutative("TRANSMUTATION", 75, new ItemStack(transmutationCore), 6, new WandRodTransmutationOnUpdate(), new ResourceLocation("thaumicexploration:textures/models/0.png"));
        STAFF_ROD_CRYSTAL = new StaffRodTransmutative("TRANSMUTATION", 175, new ItemStack(transmutationStaffCore), 14, new WandRodTransmutationOnUpdate(), new ResourceLocation("thaumicexploration:textures/models/0.png"));
        STAFF_ROD_NECRO = new StaffRod("NECROMANCER", 200, new ItemStack(necroStaffCore), 15, (IWandRodOnUpdate)new WandRodNecromancerOnUpdate(), new ResourceLocation("thaumicexploration:textures/models/rodNecro.png"));
        if (breadWand) {
            WAND_ROD_BREAD = new WandRod("BREAD", 39, new ItemStack(breadCore), 8, (IWandRodOnUpdate)new WandRodBreadOnUpdate(), new ResourceLocation("thaumicexploration:textures/models/rodBread.png"));
        }
        WAND_CAP_SOJOURNER = new WandCap("SOJOURNER", 0.95f, new ItemStack(sojournerCap), 6);
        WAND_CAP_SOJOURNER.setTexture(new ResourceLocation("thaumicexploration:textures/models/capSojourner.png"));
        WAND_CAP_MECHANIST = new WandCap("MECHANIST", 0.95f, new ItemStack(mechanistCap), 6);
        WAND_CAP_MECHANIST.setTexture(new ResourceLocation("thaumicexploration:textures/models/capMechanist.png"));
        enchantmentBinding = new EnchantmentBinding(enchantmentBindingID, 1);
        enchantmentNightVision = new EnchantmentNightVision(enchantmentNightVisionID, 1);
        enchantmentDisarm = new EnchantmentDisarm(enchantmentDisarmID, 1);
        if (Loader.isModLoaded((String)"ThaumicTinkerer")) {
            TTIntegration.registerEnchants();
        }
        if (Loader.isModLoaded((String)"Waila")) {
            FMLInterModComms.sendMessage((String)"Waila", (String)"register", (String)"flaxbeard.thaumicexploration.interop.WailaConfig.callbackRegister");
        }
        EntityRegistry.registerModEntity(EntityTaintacleMinion.class, (String)"TaintacleMinion", (int)0, (Object)instance, (int)64, (int)3, (boolean)false);
        potionBinding = new TXPotion(potionBindingID, false, 0).func_76399_b(0, 0).func_76390_b("potion.binding");
        potionTaintWithdrawl = new TXTaintPotion(potionTaintWithdrawlID, true, 0).func_76390_b("potion.taintWithdrawl");
        proxy.registerRenderers();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        String[] ores;
        ModRecipes.initRecipes();
        ModResearch.initResearch();
        proxy.setUnicode();
        allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150348_b), (Object)0));
        for (String ore : ores = OreDictionary.getOreNames()) {
            AspectList ot;
            if (ore == null) continue;
            if (ore.equals("logWood")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)is);
                    ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)is, (AspectList)ot);
                    if (is.func_77973_b() == Item.func_150898_a((Block)ConfigBlocks.blockMagicalLog) || ot.getAspects().length <= 0) continue;
                    allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)is.func_77973_b(), (Object)is.func_77960_j()));
                }
            }
            if (ore.equals("treeLeaves")) {
                for (ItemStack is : OreDictionary.getOres((String)ore)) {
                    ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)is);
                    ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)is, (AspectList)ot);
                    if (is.func_77973_b() == Item.func_150898_a((Block)ConfigBlocks.blockMagicalLeaves) || ot.getAspects().length <= 0) continue;
                    allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)is.func_77973_b(), (Object)is.func_77960_j()));
                }
            }
            if (allowModWoodReplication) {
                if (allowMagicPlankReplication) {
                    if (ore.equals("plankWood")) {
                        for (ItemStack is : OreDictionary.getOres((String)ore)) {
                            ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)is);
                            if ((ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)is, (AspectList)ot)).getAspects().length <= 0) continue;
                            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)is.func_77973_b(), (Object)is.func_77960_j()));
                        }
                    }
                } else if (ore.equals("plankWood")) {
                    for (ItemStack is : OreDictionary.getOres((String)ore)) {
                        ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)is);
                        ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)is, (AspectList)ot);
                        if (is.func_77973_b() == Item.func_150898_a((Block)ConfigBlocks.blockWoodenDevice) || ot.getAspects().length <= 0) continue;
                        allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)is.func_77973_b(), (Object)is.func_77960_j()));
                    }
                }
                if (ore.equals("slabWood")) {
                    for (ItemStack is : OreDictionary.getOres((String)ore)) {
                        ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)is);
                        if ((ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)is, (AspectList)ot)).getAspects().length <= 0) continue;
                        allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)is.func_77973_b(), (Object)is.func_77960_j()));
                    }
                }
                if (ore.equals("stairWood")) {
                    for (ItemStack is : OreDictionary.getOres((String)ore)) {
                        ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)is);
                        if ((ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)is, (AspectList)ot)).getAspects().length <= 0) continue;
                        allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)is.func_77973_b(), (Object)is.func_77960_j()));
                    }
                }
            } else {
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150376_bx), (Object)Short.MAX_VALUE));
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150487_bG), (Object)Short.MAX_VALUE));
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150476_ad), (Object)Short.MAX_VALUE));
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150481_bH), (Object)Short.MAX_VALUE));
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150485_bF), (Object)Short.MAX_VALUE));
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150364_r), (Object)Short.MAX_VALUE));
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150363_s), (Object)Short.MAX_VALUE));
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150344_f), (Object)Short.MAX_VALUE));
                if (allowMagicPlankReplication) {
                    allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)ConfigBlocks.blockWoodenDevice), (Object)6));
                    allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)ConfigBlocks.blockWoodenDevice), (Object)7));
                }
            }
            if (allowModStoneReplication) {
                if (ore.equals("stone")) {
                    for (ItemStack is : OreDictionary.getOres((String)ore)) {
                        ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)is);
                        if ((ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)is, (AspectList)ot)).getAspects().length <= 0) continue;
                        allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)is.func_77973_b(), (Object)is.func_77960_j()));
                    }
                }
                if (ore.equals("cobblestone")) {
                    for (ItemStack is : OreDictionary.getOres((String)ore)) {
                        ot = ThaumcraftCraftingManager.getObjectTags((ItemStack)is);
                        if ((ot = ThaumcraftCraftingManager.getBonusTags((ItemStack)is, (AspectList)ot)).getAspects().length <= 0) continue;
                        allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)is.func_77973_b(), (Object)is.func_77960_j()));
                    }
                }
            } else {
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150348_b), (Object)Short.MAX_VALUE));
                allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150347_e), (Object)Short.MAX_VALUE));
            }
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150341_Y), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150333_U), (Object)0));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150333_U), (Object)3));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150446_ar), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150354_m), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150322_A), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150372_bz), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150333_U), (Object)1));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150336_V), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150389_bf), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150333_U), (Object)5));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150417_aV), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150390_bg), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150333_U), (Object)4));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150385_bj), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150387_bl), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150333_U), (Object)6));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150425_aM), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150351_n), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150359_w), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150349_c), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150346_d), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150433_aE), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150435_aG), (Object)Short.MAX_VALUE));
            allowedItems.add((MutablePair<Item, Integer>)MutablePair.of((Object)Item.func_150898_a((Block)Blocks.field_150405_ch), (Object)Short.MAX_VALUE));
        }
    }

    public void addRecipes() {
    }

    public void registerEntity(Class<? extends Entity> entityClass, String entityName, int bkEggColor, int fgEggColor) {
        int id = EntityRegistry.findGlobalUniqueEntityId();
        EntityRegistry.registerGlobalEntityID(entityClass, (String)entityName, (int)id);
        EntityList.field_75627_a.put(id, new EntityList.EntityEggInfo(id, bkEggColor, fgEggColor));
    }

    public void addSpawn(Class<? extends EntityLiving> entityClass, int spawnProb, int min, int max, BiomeGenBase[] biomes) {
        if (spawnProb > 0) {
            EntityRegistry.addSpawn(entityClass, (int)spawnProb, (int)min, (int)max, (EnumCreatureType)EnumCreatureType.monster, (BiomeGenBase[])biomes);
        }
    }

    private void addAchievementName(String ach, String name) {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
    }

    private void addAchievementDesc(String ach, String desc) {
        LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
    }

    static {
        allowedItems = new ArrayList();
    }

    private class TXTab
    extends CreativeTabs {
        public TXTab(int par1, String par2Str) {
            super(par1, par2Str);
        }

        @SideOnly(value=Side.CLIENT)
        public Item func_78016_d() {
            return Item.func_150898_a((Block)thinkTankJar);
        }
    }
}

