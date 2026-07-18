/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  cpw.mods.fml.common.Loader
 *  cpw.mods.fml.common.Mod
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.Mod$Instance
 *  cpw.mods.fml.common.ModAPIManager
 *  cpw.mods.fml.common.SidedProxy
 *  cpw.mods.fml.common.event.FMLInitializationEvent
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCEvent
 *  cpw.mods.fml.common.event.FMLInterModComms$IMCMessage
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 *  cpw.mods.fml.common.event.FMLServerAboutToStartEvent
 *  cpw.mods.fml.common.event.FMLServerStartingEvent
 *  cpw.mods.fml.common.event.FMLServerStoppingEvent
 */
package vazkii.botania.common;

import com.google.common.collect.ImmutableList;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModAPIManager;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerAboutToStartEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.event.FMLServerStoppingEvent;
import vazkii.botania.common.core.handler.IMCHandler;
import vazkii.botania.common.core.handler.ManaNetworkHandler;
import vazkii.botania.common.core.proxy.CommonProxy;
import vazkii.botania.common.integration.coloredlights.ILightHelper;
import vazkii.botania.common.integration.coloredlights.LightHelperColored;
import vazkii.botania.common.integration.coloredlights.LightHelperVanilla;

@Mod(modid="Botania", name="Botania", version="r1.8-249", dependencies="required-after:Baubles;after:Thaumcraft", guiFactory="vazkii.botania.client.core.proxy.GuiFactory")
public class Botania {
    public static boolean gardenOfGlassLoaded = false;
    public static boolean thaumcraftLoaded = false;
    public static boolean bcTriggersLoaded = false;
    public static boolean bloodMagicLoaded = false;
    public static boolean coloredLightsLoaded = false;
    public static boolean etFuturumLoaded = false;
    public static boolean storageDrawersLoaded = false;
    public static ILightHelper lightHelper;
    @Mod.Instance(value="Botania")
    public static Botania instance;
    @SidedProxy(serverSide="vazkii.botania.common.core.proxy.CommonProxy", clientSide="vazkii.botania.client.core.proxy.ClientProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        gardenOfGlassLoaded = Loader.isModLoaded((String)"GardenOfGlass");
        thaumcraftLoaded = Loader.isModLoaded((String)"Thaumcraft");
        bcTriggersLoaded = ModAPIManager.INSTANCE.hasAPI("BuildCraftAPI|statements");
        bloodMagicLoaded = Loader.isModLoaded((String)"AWWayofTime");
        coloredLightsLoaded = Loader.isModLoaded((String)"easycoloredlights");
        etFuturumLoaded = Loader.isModLoaded((String)"etfuturum");
        storageDrawersLoaded = Loader.isModLoaded((String)"StorageDrawers");
        lightHelper = coloredLightsLoaded ? new LightHelperColored() : new LightHelperVanilla();
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerAboutToStartEvent event) {
        proxy.serverAboutToStart(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

    @Mod.EventHandler
    public void serverStopping(FMLServerStoppingEvent event) {
        ManaNetworkHandler.instance.clear();
    }

    @Mod.EventHandler
    public void handleIMC(FMLInterModComms.IMCEvent event) {
        IMCHandler.processMessages((ImmutableList<FMLInterModComms.IMCMessage>)event.getMessages());
    }
}

