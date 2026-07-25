/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  cpw.mods.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IFuelHandler
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCMessage
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageCreationHandler
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageTradeHandler
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.command.ICommand
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.gen.structure.MapGenStructureIO
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package thaumcraft.common;

import com.google.common.collect.ImmutableList;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import java.io.File;
import net.minecraft.block.BlockDispenser;
import net.minecraft.command.ICommand;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.client.lib.RenderEventHandler;
import thaumcraft.common.CommonProxy;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigAspects;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigEntities;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.config.ConfigRecipes;
import thaumcraft.common.config.ConfigResearch;
import thaumcraft.common.items.BehaviorDispenseAlumetum;
import thaumcraft.common.lib.CreativeTabThaumcraft;
import thaumcraft.common.lib.InternalMethodHandler;
import thaumcraft.common.lib.events.CommandThaumcraft;
import thaumcraft.common.lib.events.EventHandlerEntity;
import thaumcraft.common.lib.events.EventHandlerRunic;
import thaumcraft.common.lib.events.EventHandlerWorld;
import thaumcraft.common.lib.events.ServerTickEventsFML;
import thaumcraft.common.lib.network.EventHandlerNetwork;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketSyncWarp;
import thaumcraft.common.lib.network.playerdata.PacketWarpMessage;
import thaumcraft.common.lib.research.PlayerKnowledge;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.CropUtils;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ComponentBankerHome;
import thaumcraft.common.lib.world.ComponentWizardTower;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.lib.world.VillageBankerManager;
import thaumcraft.common.lib.world.VillageWizardManager;
import thaumcraft.common.lib.world.dim.WorldProviderOuter;

@Mod(modid="Thaumcraft", name="Thaumcraft", version="4.2.3.5", guiFactory="thaumcraft.client.ThaumcraftGuiFactory", dependencies="required-after:Forge@[10.13.2,);required-after:Baubles@[1.0.1.10,)")
public class Thaumcraft {
    public static final String MODID = "Thaumcraft";
    public static final String MODNAME = "Thaumcraft";
    public static final String VERSION = "4.2.3.5";
    @SidedProxy(clientSide="thaumcraft.client.ClientProxy", serverSide="thaumcraft.common.CommonProxy")
    public static CommonProxy proxy;
    @Mod.Instance(value="Thaumcraft")
    public static Thaumcraft instance;
    ResearchManager researchManager;
    public ThaumcraftWorldGenerator worldGen;
    public EventHandlerWorld worldEventHandler;
    public EventHandlerNetwork networkEventHandler;
    public ServerTickEventsFML serverTickEvents;
    public EventHandlerEntity entityEventHandler;
    public EventHandlerRunic runicEventHandler;
    public RenderEventHandler renderEventHandler;
    public File modDir;
    public static final Logger log;
    public static boolean isHalloween;
    public static CreativeTabs tabTC;
    public boolean aspectShift = false;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        event.getModMetadata().version = VERSION;
        this.modDir = event.getModConfigurationDirectory();
        try {
            Config.initialize(event.getSuggestedConfigurationFile());
        }
        catch (Exception e) {
            log.error("Thaumcraft has a problem loading it's configuration");
        }
        finally {
            if (Config.config != null) {
                Config.save();
            }
        }
        ThaumcraftApi.internalMethods = new InternalMethodHandler();
        Thaumcraft.proxy.playerKnowledge = new PlayerKnowledge();
        Thaumcraft.proxy.researchManager = new ResearchManager();
        this.worldEventHandler = new EventHandlerWorld();
        this.serverTickEvents = new ServerTickEventsFML();
        this.entityEventHandler = new EventHandlerEntity();
        this.runicEventHandler = new EventHandlerRunic();
        this.renderEventHandler = new RenderEventHandler();
        this.networkEventHandler = new EventHandlerNetwork();
        PacketHandler.init();
        MinecraftForge.TERRAIN_GEN_BUS.register((Object)this.worldEventHandler);
        MinecraftForge.EVENT_BUS.register((Object)this.worldEventHandler);
        FMLCommonHandler.instance().bus().register((Object)this.worldEventHandler);
        MinecraftForge.EVENT_BUS.register((Object)this.entityEventHandler);
        MinecraftForge.EVENT_BUS.register((Object)this.runicEventHandler);
        FMLCommonHandler.instance().bus().register((Object)this.networkEventHandler);
        GameRegistry.registerFuelHandler((IFuelHandler)this.worldEventHandler);
        FMLCommonHandler.instance().bus().register((Object)this.serverTickEvents);
        this.worldGen = new ThaumcraftWorldGenerator();
        GameRegistry.registerWorldGenerator((IWorldGenerator)this.worldGen, (int)0);
        ThaumcraftApi.registerScanEventhandler(new ScanManager());
        Config.save();
        ConfigBlocks.init();
        ConfigItems.init();
        try {
            MapGenStructureIO.func_143031_a(ComponentWizardTower.class, (String)"TCVillageTower");
            MapGenStructureIO.func_143031_a(ComponentBankerHome.class, (String)"TCVillageBanker");
        }
        catch (Throwable e) {
            log.error("[Thaumcraft] Village tower could not be registered.");
        }
        proxy.registerHandlers();
        this.worldGen.initialize();
        FMLCommonHandler.instance().bus().register((Object)instance);
        Config.registerBiomes();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent evt) {
        proxy.registerDisplayInformation();
        ConfigEntities.init();
        BlockDispenser.field_149943_a.func_82595_a((Object)ConfigItems.itemResource, (Object)new BehaviorDispenseAlumetum());
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)instance, (IGuiHandler)proxy);
        VillageWizardManager villageManagerWizard = new VillageWizardManager();
        VillageBankerManager villageManagerBanker = new VillageBankerManager();
        VillagerRegistry.instance().registerVillagerId(ConfigEntities.entWizardId);
        VillagerRegistry.instance().registerVillagerId(ConfigEntities.entBankerId);
        VillagerRegistry.instance().registerVillageCreationHandler((VillagerRegistry.IVillageCreationHandler)villageManagerWizard);
        VillagerRegistry.instance().registerVillageCreationHandler((VillagerRegistry.IVillageCreationHandler)villageManagerBanker);
        VillagerRegistry.instance().registerVillageTradeHandler(ConfigEntities.entWizardId, (VillagerRegistry.IVillageTradeHandler)villageManagerWizard);
        VillagerRegistry.instance().registerVillageTradeHandler(ConfigEntities.entBankerId, (VillagerRegistry.IVillageTradeHandler)villageManagerBanker);
        proxy.registerKeyBindings();
        DimensionManager.registerProviderType((int)Config.dimensionOuterId, WorldProviderOuter.class, (boolean)false);
        DimensionManager.registerDimension((int)Config.dimensionOuterId, (int)Config.dimensionOuterId);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent evt) {
        Config.initPotions();
        ConfigEntities.initEntitySpawns();
        Config.initModCompatibility();
        ConfigItems.postInit();
        ConfigRecipes.init();
        ConfigAspects.init();
        ConfigResearch.init();
        Config.initLoot();
        Config.initMisc();
        ImmutableList messages = FMLInterModComms.fetchRuntimeMessages((Object)this);
        for (FMLInterModComms.IMCMessage message : messages) {
            String[] t;
            ItemStack crop;
            if (message.key.equals("harvestStandardCrop") && message.isItemStackMessage()) {
                crop = message.getItemStackValue();
                CropUtils.addStandardCrop(crop, crop.func_77960_j());
                log.warn("Adding standard crop support for [" + crop.func_82833_r() + "]");
            }
            if (message.key.equals("harvestClickableCrop") && message.isItemStackMessage()) {
                crop = message.getItemStackValue();
                CropUtils.addClickableCrop(crop, crop.func_77960_j());
                log.warn("Adding clickable crop support for [" + crop.func_82833_r() + "]");
            }
            if (message.key.equals("harvestStackedCrop") && message.isItemStackMessage()) {
                crop = message.getItemStackValue();
                CropUtils.addStackedCrop(crop, crop.func_77960_j());
                log.warn("Adding stacked crop support for [" + crop.func_82833_r() + "]");
            }
            if (message.key.equals("nativeCluster") && message.isStringMessage() && (t = message.getStringValue().split(",")) != null && t.length == 5) {
                try {
                    ItemStack ore = new ItemStack(Item.func_150899_d((int)Integer.parseInt(t[0])), 1, Integer.parseInt(t[1]));
                    ItemStack cluster = new ItemStack(Item.func_150899_d((int)Integer.parseInt(t[2])), 1, Integer.parseInt(t[3]));
                    Utils.addSpecialMiningResult(ore, cluster, Float.parseFloat(t[4]));
                    log.warn("Adding [" + cluster.func_82833_r() + "] to special result list for [" + ore.func_82833_r() + "]");
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
            if (message.key.equals("lampBlacklist") && message.isItemStackMessage()) {
                crop = message.getItemStackValue();
                CropUtils.blacklistLamp(crop, crop.func_77960_j());
                log.warn("[Thaumcraft] Blacklisting [" + crop.func_82833_r() + "] for lamp of growth");
            }
            if (message.key.equals("dimensionBlacklist") && message.isStringMessage() && (t = message.getStringValue().split(":")) != null && t.length == 2) {
                try {
                    ThaumcraftWorldGenerator.addDimBlacklist(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
                    log.warn("Blacklisting dimension [" + Integer.parseInt(t[0]) + "] to only spawn TC content at level [" + Integer.parseInt(t[1]) + "]");
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
            if (message.key.equals("biomeBlacklist") && message.isStringMessage() && (t = message.getStringValue().split(":")) != null && t.length == 2 && BiomeGenBase.func_150568_d((int)Integer.parseInt(t[0])) != null) {
                try {
                    ThaumcraftWorldGenerator.addBiomeBlacklist(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
                    log.warn("Blacklisting [" + BiomeGenBase.func_150568_d((int)Integer.parseInt((String)t[0])).field_76791_y + "] to only spawn TC content at level [" + Integer.parseInt(t[1]) + "]");
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
            if (!message.key.equals("championWhiteList") || !message.isStringMessage()) continue;
            try {
                t = message.getStringValue().split(":");
                Class oclass = (Class)EntityList.field_75625_b.get(t[0]);
                if (oclass == null) continue;
                ConfigEntities.championModWhitelist.put(oclass, Integer.parseInt(t[1]));
                log.warn("Whitelisting [" + t[0] + "] to spawn champion mobs at level [" + Integer.parseInt(t[1]) + "]");
            }
            catch (Exception e) {
                log.error("Failed to Whitelist [" + message.getStringValue() + "] with [ championWhiteList ] message.");
            }
        }
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new CommandThaumcraft());
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
        if (eventArgs.modID.equals("Thaumcraft")) {
            Config.syncConfigurable();
            if (Config.config != null && Config.config.hasChanged()) {
                Config.save();
            }
        }
    }

    public static void addWarpToPlayer(EntityPlayer player, int amount, boolean temporary) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        if (proxy.getPlayerKnowledge() == null) {
            return;
        }
        if (!temporary && amount < 0) {
            return;
        }
        if (amount == 0) {
            return;
        }
        if (temporary) {
            if (amount < 0 && proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_()) <= 0) {
                return;
            }
            proxy.getPlayerKnowledge().addWarpTemp(player.func_70005_c_(), amount);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp(player, 2), (EntityPlayerMP)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketWarpMessage(player, 2, amount), (EntityPlayerMP)player);
        } else {
            proxy.getPlayerKnowledge().addWarpPerm(player.func_70005_c_(), amount);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp(player, 0), (EntityPlayerMP)player);
            PacketHandler.INSTANCE.sendTo((IMessage)new PacketWarpMessage(player, 0, amount), (EntityPlayerMP)player);
        }
        proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_()));
    }

    public static void addStickyWarpToPlayer(EntityPlayer player, int amount) {
        if (player.field_70170_p.field_72995_K) {
            return;
        }
        if (proxy.getPlayerKnowledge() == null) {
            return;
        }
        if (amount == 0) {
            return;
        }
        if (amount < 0 && proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_()) <= 0) {
            return;
        }
        proxy.getPlayerKnowledge().addWarpSticky(player.func_70005_c_(), amount);
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketSyncWarp(player, 1), (EntityPlayerMP)player);
        PacketHandler.INSTANCE.sendTo((IMessage)new PacketWarpMessage(player, 1, amount), (EntityPlayerMP)player);
        proxy.getPlayerKnowledge().setWarpCounter(player.func_70005_c_(), proxy.getPlayerKnowledge().getWarpTotal(player.func_70005_c_()));
    }

    static {
        log = LogManager.getLogger((String)"THAUMCRAFT");
        isHalloween = false;
        tabTC = new CreativeTabThaumcraft(CreativeTabs.getNextID(), "thaumcraft");
    }
}

