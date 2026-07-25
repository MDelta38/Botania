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
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.network.IGuiHandler
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  net.minecraft.block.Block
 *  net.minecraft.command.ServerCommandManager
 *  net.minecraft.creativetab.CreativeTabs
 *  net.minecraft.item.Item
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.StatCollector
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.common.config.Configuration
 *  org.apache.logging.log4j.Logger
 */
package drunkmafia.thaumicinfusion.common;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import drunkmafia.thaumicinfusion.common.CommonProxy;
import drunkmafia.thaumicinfusion.common.asm.BlockTransformer;
import drunkmafia.thaumicinfusion.common.aspect.AspectEffect;
import drunkmafia.thaumicinfusion.common.aspect.AspectHandler;
import drunkmafia.thaumicinfusion.common.block.TIBlocks;
import drunkmafia.thaumicinfusion.common.command.TICommand;
import drunkmafia.thaumicinfusion.common.event.CommonEventContainer;
import drunkmafia.thaumicinfusion.common.intergration.ThaumcraftIntergration;
import drunkmafia.thaumicinfusion.common.item.TIItems;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import net.minecraft.block.Block;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Logger;

@Mod(modid="thaumicinfusion", name="Thaumic Infusion", version="4.32", dependencies="required-after:Forge@[10.13.2,);required-after:Thaumcraft@[4.2.3.5,)")
public class ThaumicInfusion {
    @Mod.Instance(value="thaumicinfusion")
    public static ThaumicInfusion instance;
    @SidedProxy(clientSide="drunkmafia.thaumicinfusion.client.ClientProxy", serverSide="drunkmafia.thaumicinfusion.common.CommonProxy")
    public static CommonProxy proxy;
    private static Logger logger;
    public Configuration config;
    public CreativeTabs tab = new CreativeTabs("thaumicinfusion"){

        public Item func_78016_d() {
            return TIItems.focusInfusing;
        }
    };

    public static String translate(String key, Object ... params) {
        return StatCollector.func_74837_a((String)key, (Object[])params);
    }

    public static Logger getLogger() {
        return logger;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        this.config = new Configuration(event.getSuggestedConfigurationFile());
        this.config.load();
        TIItems.init();
        TIBlocks.initBlocks();
        AspectEffect.init();
        FMLInterModComms.sendRuntimeMessage((Object)"thaumicinfusion", (String)"VersionChecker", (String)"addVersionCheck", (String)"https://raw.githubusercontent.com/TheDrunkMafia/ThaumicInfusion/master/version.json");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register((Object)new CommonEventContainer());
        FMLCommonHandler.instance().bus().register((Object)new CommonEventContainer());
        NetworkRegistry.INSTANCE.registerGuiHandler((Object)instance, (IGuiHandler)proxy);
        proxy.initRenderers();
        ChannelHandler.registerPackets();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        AspectHandler.postInit();
        ThaumcraftIntergration.init();
        BlockTransformer.blockCheck(Block.field_149771_c.iterator());
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        MinecraftServer server = event.getServer();
        TICommand.init((ServerCommandManager)server.func_71187_D());
    }
}

