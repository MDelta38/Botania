/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  cpw.mods.fml.client.event.ConfigChangedEvent$OnConfigChangedEvent
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.IWorldGenerator
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLMissingMappingsEvent
 *  cpw.mods.fml.common.event.FMLMissingMappingsEvent$MissingMapping
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.registry.GameRegistry
 *  cpw.mods.fml.common.registry.GameRegistry$Type
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageCreationHandler
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageTradeHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDispenser
 *  net.minecraft.command.ICommand
 *  net.minecraft.init.Items
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.structure.MapGenStructureIO
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.common.ForgeChunkManager
 *  net.minecraftforge.common.ForgeChunkManager$LoadingCallback
 *  net.minecraftforge.common.ForgeChunkManager$OrderedLoadingCallback
 *  net.minecraftforge.common.ForgeChunkManager$Ticket
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 */
package com.emoniph.witchery;

import com.emoniph.witchery.WitcheryBlocks;
import com.emoniph.witchery.WitcheryEntities;
import com.emoniph.witchery.WitcheryFluids;
import com.emoniph.witchery.WitcheryItems;
import com.emoniph.witchery.WitcheryRecipes;
import com.emoniph.witchery.blocks.BlockAreaMarker;
import com.emoniph.witchery.blocks.BlockPoppetShelf;
import com.emoniph.witchery.brewing.WitcheryBrewRegistry;
import com.emoniph.witchery.brewing.potions.WitcheryPotions;
import com.emoniph.witchery.client.KeyboardHandler;
import com.emoniph.witchery.client.PlayerRender;
import com.emoniph.witchery.common.ChantCommand;
import com.emoniph.witchery.common.CommonProxy;
import com.emoniph.witchery.common.PowerSources;
import com.emoniph.witchery.common.ServerTickEvents;
import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.dimension.WorldProviderMirror;
import com.emoniph.witchery.dimension.WorldProviderTorment;
import com.emoniph.witchery.integration.ModHookArsMagica2;
import com.emoniph.witchery.integration.ModHookBaubles;
import com.emoniph.witchery.integration.ModHookBloodMagic;
import com.emoniph.witchery.integration.ModHookForestry;
import com.emoniph.witchery.integration.ModHookManager;
import com.emoniph.witchery.integration.ModHookMineFactoryReloaded;
import com.emoniph.witchery.integration.ModHookMorph;
import com.emoniph.witchery.integration.ModHookMystCraft;
import com.emoniph.witchery.integration.ModHookThaumcraft4;
import com.emoniph.witchery.integration.ModHookTinkersConstruct;
import com.emoniph.witchery.integration.ModHookTreecapitator;
import com.emoniph.witchery.integration.ModHookWaila;
import com.emoniph.witchery.item.DispenseBehaviourItemBrew;
import com.emoniph.witchery.item.DispenseBehaviourItemGeneral;
import com.emoniph.witchery.network.PacketPipeline;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.worldgen.BiomeManager;
import com.emoniph.witchery.worldgen.ComponentVillageApothecary;
import com.emoniph.witchery.worldgen.ComponentVillageBookShop;
import com.emoniph.witchery.worldgen.ComponentVillageWitchHut;
import com.emoniph.witchery.worldgen.WitcheryWorldGenerator;
import com.emoniph.witchery.worldgen.WorldHandlerVillageApothecary;
import com.emoniph.witchery.worldgen.WorldHandlerVillageBookShop;
import com.emoniph.witchery.worldgen.WorldHandlerVillageDistrict;
import com.emoniph.witchery.worldgen.WorldHandlerVillageWitchHut;
import com.google.common.collect.Lists;
import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDispenser;
import net.minecraft.command.ICommand;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

@Mod(modid="witchery", name="Witchery", version="0.24.1", guiFactory="com.emoniph.witchery.util.WitcheryGuiFactory", dependencies="required-after:Forge@[10.13.2.1277,);after:MineFactoryReloaded;after:NotEnoughItems;after:Waila;after:ForgeMultipart;after:AWWayofTime")
public class Witchery {
    public static final String MOD_ID = "witchery";
    public static final String MOD_PREFIX = "WITC";
    @SidedProxy(clientSide="com.emoniph.witchery.client.ClientProxy", serverSide="com.emoniph.witchery.common.CommonProxy")
    public static CommonProxy proxy;
    @Mod.Instance(value="witchery")
    public static Witchery instance;
    public static final PacketPipeline packetPipeline;
    public static WitcheryRecipes Recipes;
    public static final ModHookManager modHooks;
    public static WitcheryPotions Potions;
    public static WitcheryFluids Fluids;
    public static WitcheryBlocks Blocks;
    public static WitcheryItems Items;
    public static WitcheryEntities Entities;
    private static WitcheryWorldGenerator worldGenerator;
    public static File configDirectoryPath;
    public static Configuration config;
    public static Configuration config_debug;
    public static boolean isDeathChestModInstalled;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        if (instance != this) {
            Log.instance().warning("instance static not set");
        }
        configDirectoryPath = event.getModConfigurationDirectory();
        config = new Configuration(new File(String.format("%s/%s.cfg", configDirectoryPath, MOD_ID)));
        config_debug = new Configuration(new File(String.format("%s/%s_debug.cfg", configDirectoryPath, MOD_ID)));
        Config.instance().init(config, config_debug);
        worldGenerator = new WitcheryWorldGenerator();
        GameRegistry.registerWorldGenerator((IWorldGenerator)worldGenerator, (int)0);
        if (Config.instance().generateApothecaries) {
            WorldHandlerVillageApothecary villageApothecaryHandler = new WorldHandlerVillageApothecary();
            VillagerRegistry.instance().registerVillagerId(Config.instance().apothecaryID);
            proxy.registerVillagers();
            VillagerRegistry.instance().registerVillageCreationHandler((VillagerRegistry.IVillageCreationHandler)villageApothecaryHandler);
            VillagerRegistry.instance().registerVillageTradeHandler(Config.instance().apothecaryID, (VillagerRegistry.IVillageTradeHandler)villageApothecaryHandler);
            try {
                MapGenStructureIO.func_143031_a(ComponentVillageApothecary.class, (String)"witchery:Apothecary");
            }
            catch (Throwable e) {
                // empty catch block
            }
        }
        if (Config.instance().generateWitchHuts) {
            WorldHandlerVillageWitchHut villageWitchHutHandler = new WorldHandlerVillageWitchHut();
            VillagerRegistry.instance().registerVillageCreationHandler((VillagerRegistry.IVillageCreationHandler)villageWitchHutHandler);
            try {
                MapGenStructureIO.func_143031_a(ComponentVillageWitchHut.class, (String)"witchery:witchhut");
            }
            catch (Throwable e) {
                // empty catch block
            }
        }
        if (Config.instance().generateBookShops) {
            WorldHandlerVillageBookShop villageBookShopHandler = new WorldHandlerVillageBookShop();
            VillagerRegistry.instance().registerVillageCreationHandler((VillagerRegistry.IVillageCreationHandler)villageBookShopHandler);
            try {
                MapGenStructureIO.func_143031_a(ComponentVillageBookShop.class, (String)"witchery:bookshop");
            }
            catch (Throwable throwable) {
                // empty catch block
            }
        }
        WorldHandlerVillageDistrict.preInit();
        proxy.preInit();
        packetPipeline.preInit();
        Potions = new WitcheryPotions();
        Fluids = new WitcheryFluids();
        Blocks = new WitcheryBlocks();
        Items = new WitcheryItems();
        Entities = new WitcheryEntities();
        Recipes.preInit();
        Potions.preInit();
        FMLCommonHandler.instance().bus().register((Object)new ServerTickEvents());
        if (event.getSide() == Side.CLIENT) {
            FMLCommonHandler.instance().bus().register((Object)new PlayerRender());
            FMLCommonHandler.instance().bus().register((Object)new KeyboardHandler());
        }
    }

    @Mod.EventHandler
    public void onMissingMappings(FMLMissingMappingsEvent event) {
        for (FMLMissingMappingsEvent.MissingMapping missing : event.get()) {
            Block replacement;
            String strippedName;
            if (missing.name == null) continue;
            int index = missing.name.lastIndexOf(58);
            String string = strippedName = index != -1 && missing.name.length() > index ? missing.name.substring(index + 1) : missing.name;
            if (missing.type == GameRegistry.Type.BLOCK) {
                replacement = GameRegistry.findBlock((String)MOD_ID, (String)strippedName);
                if (replacement == null) continue;
                missing.remap(replacement);
                continue;
            }
            if (missing.type != GameRegistry.Type.ITEM || (replacement = GameRegistry.findItem((String)MOD_ID, (String)strippedName)) == null) continue;
            missing.remap((Item)replacement);
        }
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(MOD_ID)) {
            Config.instance().sync();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        FMLCommonHandler.instance().bus().register((Object)instance);
        WorldHandlerVillageDistrict.init();
        packetPipeline.init();
        Entities.init();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)this, (IGuiHandler)proxy);
        DimensionManager.registerProviderType((int)Config.instance().dimensionDreamID, WorldProviderDreamWorld.class, (boolean)false);
        DimensionManager.registerDimension((int)Config.instance().dimensionDreamID, (int)Config.instance().dimensionDreamID);
        DimensionManager.registerProviderType((int)Config.instance().dimensionTormentID, WorldProviderTorment.class, (boolean)false);
        DimensionManager.registerDimension((int)Config.instance().dimensionTormentID, (int)Config.instance().dimensionTormentID);
        DimensionManager.registerProviderType((int)Config.instance().dimensionMirrorID, WorldProviderMirror.class, (boolean)false);
        DimensionManager.registerDimension((int)Config.instance().dimensionMirrorID, (int)Config.instance().dimensionMirrorID);
        MinecraftForge.addGrassSeed((ItemStack)new ItemStack(Witchery.Items.SEEDS_ARTICHOKE), (int)3);
        MinecraftForge.addGrassSeed((ItemStack)new ItemStack(Witchery.Items.SEEDS_BELLADONNA), (int)4);
        MinecraftForge.addGrassSeed((ItemStack)new ItemStack(Witchery.Items.SEEDS_MANDRAKE), (int)5);
        MinecraftForge.addGrassSeed((ItemStack)new ItemStack(Witchery.Items.SEEDS_SNOWBELL), (int)2);
        MinecraftForge.addGrassSeed((ItemStack)new ItemStack(Witchery.Items.SEEDS_WOLFSBANE), (int)1);
        MinecraftForge.addGrassSeed((ItemStack)new ItemStack(Witchery.Items.SEEDS_GARLIC), (int)1);
        proxy.registerHandlers();
        proxy.registerServerHandlers();
        proxy.registerRenderers();
        isDeathChestModInstalled = Config.instance().respectOtherDeathChestMods && (Loader.isModLoaded((String)"tombstone") || Loader.isModLoaded((String)"OpenBlocks") || Loader.isModLoaded((String)"Taigore_InventorySaver") || Loader.isModLoaded((String)"KeepItemsOnDeath"));
        modHooks.register(ModHookArsMagica2.class);
        modHooks.register(ModHookBloodMagic.class);
        modHooks.register(ModHookForestry.class);
        modHooks.register(ModHookMineFactoryReloaded.class);
        modHooks.register(ModHookMystCraft.class);
        modHooks.register(ModHookThaumcraft4.class);
        modHooks.register(ModHookTinkersConstruct.class);
        modHooks.register(ModHookTreecapitator.class);
        modHooks.register(ModHookWaila.class);
        modHooks.register(ModHookMorph.class);
        modHooks.register(ModHookBaubles.class);
        modHooks.init();
        Potions.init();
        Recipes.init();
        WitcheryBrewRegistry.INSTANCE.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        Recipes.postInit();
        modHooks.postInit();
        try {
            BiomeManager.addModBiomes();
        }
        catch (Exception e) {
            Log.instance().warning(e, "Failed to add external mod biome postInit compatability");
        }
        proxy.postInit();
        proxy.registerEvents();
        BlockDispenser.field_149943_a.func_82595_a((Object)Witchery.Items.GENERIC, (Object)new DispenseBehaviourItemGeneral());
        BlockDispenser.field_149943_a.func_82595_a((Object)Witchery.Items.BREW, (Object)new DispenseBehaviourItemBrew());
        BlockDispenser.field_149943_a.func_82595_a((Object)Witchery.Items.BREW_ENDLESS_WATER, (Object)new DispenseBehaviourItemBrew());
        BlockDispenser.field_149943_a.func_82595_a((Object)net.minecraft.init.Items.field_151069_bo, (Object)new DispenseBehaviourItemBrew());
        BlockDispenser.field_149943_a.func_82595_a((Object)Witchery.Items.SUN_GRENADE, (Object)new DispenseBehaviourItemBrew());
        BlockDispenser.field_149943_a.func_82595_a((Object)Witchery.Items.DUP_GRENADE, (Object)new DispenseBehaviourItemBrew());
        ForgeChunkManager.setForcedChunkLoadingCallback((Object)instance, (ForgeChunkManager.LoadingCallback)new ChunkloadCallback());
    }

    @Mod.EventHandler
    public void serverLoad(FMLServerStartingEvent event) {
        event.registerServerCommand((ICommand)new ChantCommand());
        PowerSources.initiate();
        BlockAreaMarker.AreaMarkerRegistry.serverStart();
        worldGenerator.initiate();
    }

    public static String resource(String id) {
        String s = StatCollector.func_74838_a((String)id);
        return s.replace("|", "\n").replace("{", "\u00a7");
    }

    static {
        packetPipeline = new PacketPipeline();
        Recipes = new WitcheryRecipes();
        modHooks = new ModHookManager();
    }

    public static class ChunkloadCallback
    implements ForgeChunkManager.OrderedLoadingCallback {
        public void ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world) {
            for (ForgeChunkManager.Ticket ticket : tickets) {
                int posX = ticket.getModData().func_74762_e("poppetX");
                int posY = ticket.getModData().func_74762_e("poppetY");
                int posZ = ticket.getModData().func_74762_e("poppetZ");
                BlockPoppetShelf.TileEntityPoppetShelf tileEntity = (BlockPoppetShelf.TileEntityPoppetShelf)world.func_147438_o(posX, posY, posZ);
                tileEntity.forceChunkLoading(ticket);
            }
        }

        public List<ForgeChunkManager.Ticket> ticketsLoaded(List<ForgeChunkManager.Ticket> tickets, World world, int maxTicketCount) {
            ArrayList validTickets = Lists.newArrayList();
            for (ForgeChunkManager.Ticket ticket : tickets) {
                int posZ;
                int posY;
                int posX = ticket.getModData().func_74762_e("poppetX");
                Block block = world.func_147439_a(posX, posY = ticket.getModData().func_74762_e("poppetY"), posZ = ticket.getModData().func_74762_e("poppetZ"));
                if (block != Witchery.Blocks.POPPET_SHELF) continue;
                validTickets.add(ticket);
            }
            return validTickets;
        }
    }
}

