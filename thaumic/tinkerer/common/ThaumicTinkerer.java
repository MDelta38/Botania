/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLInterModComms
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCEvent
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCMessage
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  net.minecraft.command.ICommand
 *  net.minecraft.command.ICommandManager
 *  net.minecraft.command.ServerCommandManager
 *  net.minecraft.server.MinecraftServer
 *  net.minecraftforge.common.DimensionManager
 *  org.apache.logging.log4j.Logger
 *  thaumcraft.common.CommonProxy
 *  thaumcraft.common.Thaumcraft
 */
package thaumic.tinkerer.common;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.util.Arrays;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandManager;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.DimensionManager;
import org.apache.logging.log4j.Logger;
import thaumcraft.common.CommonProxy;
import thaumcraft.common.Thaumcraft;
import thaumic.tinkerer.common.core.commands.KamiUnlockedCommand;
import thaumic.tinkerer.common.core.commands.MaxResearchCommand;
import thaumic.tinkerer.common.core.commands.SetTendencyCommand;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.core.proxy.TTCommonProxy;
import thaumic.tinkerer.common.dim.WorldProviderBedrock;
import thaumic.tinkerer.common.registry.TTRegistry;
import thaumic.tinkerer.common.research.KamiResearchItem;

@Mod(modid="ThaumicTinkerer", name="Thaumic Tinkerer", version="unspecified", dependencies="required-after:Forge@[10.12.1.1112,);required-after:Thaumcraft@[4.2.2.0,];before:MagicBees;before:advthaum;after:IC2;after:ThaumicTinkererKami;after:Waila;after:ForgeMultipart;after:ComputerCraft")
public class ThaumicTinkerer {
    @Mod.Instance(value="ThaumicTinkerer")
    public static ThaumicTinkerer instance;
    @SidedProxy(clientSide="thaumic.tinkerer.client.core.proxy.TTClientProxy", serverSide="thaumic.tinkerer.common.core.proxy.TTCommonProxy")
    public static TTCommonProxy proxy;
    public static CommonProxy tcProxy;
    public static SimpleNetworkWrapper netHandler;
    public static TTRegistry registry;
    public static Logger log;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        log = event.getModLog();
        tcProxy = Thaumcraft.proxy;
        proxy.preInit(event);
        if (Loader.isModLoaded((String)"Waila")) {
            FMLInterModComms.sendMessage((String)"Waila", (String)"register", (String)"thaumic.tinkerer.common.compat.TTinkererProvider.callbackRegister");
        }
    }

    @Mod.EventHandler
    public void serverStart(FMLServerStartingEvent event) {
        MinecraftServer server = MinecraftServer.func_71276_C();
        ICommandManager command = server.func_71187_D();
        ServerCommandManager manager = (ServerCommandManager)command;
        manager.func_71560_a((ICommand)new SetTendencyCommand());
        manager.func_71560_a((ICommand)new MaxResearchCommand());
        manager.func_71560_a((ICommand)new KamiUnlockedCommand());
    }

    @Mod.EventHandler
    public void HandleIMCMessages(FMLInterModComms.IMCEvent messages) {
        for (FMLInterModComms.IMCMessage message : messages.getMessages()) {
            if (!message.key.equalsIgnoreCase("AddResearchBlacklist")) continue;
            String[] values = message.getStringValue().split(",");
            KamiResearchItem.Blacklist.addAll(Arrays.asList(values));
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
        if (ConfigHandler.enableKami && ConfigHandler.bedrockDimensionID != 0) {
            DimensionManager.registerProviderType((int)ConfigHandler.bedrockDimensionID, WorldProviderBedrock.class, (boolean)false);
            DimensionManager.registerDimension((int)ConfigHandler.bedrockDimensionID, (int)ConfigHandler.bedrockDimensionID);
        }
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    static {
        netHandler = NetworkRegistry.INSTANCE.newSimpleChannel("ThaumicTinkerer|B");
        registry = new TTRegistry();
    }
}

