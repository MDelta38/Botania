/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.FMLCommonHandler
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLMissingMappingsEvent
 *  cpw.mods.fml.common.event.FMLMissingMappingsEvent$MissingMapping
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.common.registry.GameRegistry$Type
 *  cpw.mods.fml.common.registry.VillagerRegistry
 *  cpw.mods.fml.common.registry.VillagerRegistry$IVillageCreationHandler
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.block.Block
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.world.gen.structure.MapGenStructureIO
 *  net.minecraftforge.common.MinecraftForge
 *  org.apache.logging.log4j.Level
 *  org.apache.logging.log4j.LogManager
 *  org.apache.logging.log4j.Logger
 */
package witchinggadgets;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import witchinggadgets.common.CommonProxy;
import witchinggadgets.common.WGConfig;
import witchinggadgets.common.WGContent;
import witchinggadgets.common.WGModCompat;
import witchinggadgets.common.util.WGCreativeTab;
import witchinggadgets.common.util.handler.EventHandler;
import witchinggadgets.common.util.handler.PlayerTickHandler;
import witchinggadgets.common.util.handler.WGWandManager;
import witchinggadgets.common.util.network.message.MessageClientNotifier;
import witchinggadgets.common.util.network.message.MessagePlaySound;
import witchinggadgets.common.util.network.message.MessagePrimordialGlove;
import witchinggadgets.common.util.network.message.MessageTileUpdate;
import witchinggadgets.common.world.VillageComponentPhotoshop;

@Mod(modid="WitchingGadgets", name="Witching Gadgets", version="1.1.10", dependencies="required-after:Thaumcraft;required-after:TravellersGear@[1.16.4,);after:TwilightForest;after:Mystcraft;after:TConstruct;after:MagicBees;after:ForgeMultipart")
public class WitchingGadgets {
    public static final String MODID = "WitchingGadgets";
    public static final String MODNAME = "Witching Gadgets";
    public static final String VERSION = "1.1.10";
    public PlayerTickHandler playerTickHandler;
    public WGWandManager wgWandManager = new WGWandManager();
    public static CreativeTabs tabWG = new WGCreativeTab(CreativeTabs.getNextID(), "witchinggadgets");
    public static final Logger logger = LogManager.getLogger((String)"WitchingGadgets");
    public EventHandler eventHandler;
    @Mod.Instance(value="WitchingGadgets")
    public static WitchingGadgets instance = new WitchingGadgets();
    @SidedProxy(clientSide="witchinggadgets.client.ClientProxy", serverSide="witchinggadgets.common.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper packetHandler;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger.log(Level.INFO, "Setting up 'WitchingGadgets'");
        WGConfig.loadConfig(event);
        WGContent.preInit();
        packetHandler = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
        this.eventHandler = new EventHandler();
        MinecraftForge.EVENT_BUS.register((Object)this.eventHandler);
        this.playerTickHandler = new PlayerTickHandler();
        FMLCommonHandler.instance().bus().register((Object)this.eventHandler);
        FMLCommonHandler.instance().bus().register((Object)this.playerTickHandler);
        VillagerRegistry.instance().registerVillageCreationHandler((VillagerRegistry.IVillageCreationHandler)new VillageComponentPhotoshop.VillageManager());
        try {
            MapGenStructureIO.func_143031_a(VillageComponentPhotoshop.class, (String)"WGVillagePhotoWorkshop");
        }
        catch (Exception e) {
            logger.log(Level.ERROR, "Photographer's Workshop not added to Villages");
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.registerRenders();
        WGContent.init();
        proxy.registerHandlers();
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)instance, (IGuiHandler)proxy);
        packetHandler.registerMessage(MessageClientNotifier.HandlerClient.class, MessageClientNotifier.class, 0, Side.CLIENT);
        packetHandler.registerMessage(MessagePlaySound.HandlerClient.class, MessagePlaySound.class, 1, Side.CLIENT);
        packetHandler.registerMessage(MessagePrimordialGlove.HandlerServer.class, MessagePrimordialGlove.class, 2, Side.SERVER);
        packetHandler.registerMessage(MessageTileUpdate.HandlerClient.class, MessageTileUpdate.class, 3, Side.CLIENT);
        packetHandler.registerMessage(MessageTileUpdate.HandlerServer.class, MessageTileUpdate.class, 4, Side.SERVER);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        WGModCompat.init();
        WGContent.postInit();
    }

    @Mod.EventHandler
    public void missingMappings(FMLMissingMappingsEvent event) {
        Block[] wgBlocks = new Block[]{WGContent.BlockWallMirror, WGContent.BlockVoidWalkway, WGContent.BlockPortal, WGContent.BlockStoneDevice, WGContent.BlockWoodenDevice, WGContent.BlockMetalDevice, WGContent.BlockMagicBed, WGContent.BlockRoseVine, WGContent.BlockCustomAiry};
        for (FMLMissingMappingsEvent.MissingMapping mapping : event.get()) {
            if (!mapping.name.startsWith("WitchingGadgets:")) continue;
            try {
                String s = mapping.name.substring("WitchingGadgets:".length());
                for (Block b : wgBlocks) {
                    if (b == null || !s.equalsIgnoreCase(b.func_149732_F())) continue;
                    if (mapping.type == GameRegistry.Type.BLOCK) {
                        mapping.remap(b);
                    } else {
                        mapping.remap(Item.func_150898_a((Block)b));
                    }
                    logger.warn("Remapping " + mapping.name + " to " + b.func_149739_a());
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

