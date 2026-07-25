/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.eventbus.EventBus
 *  com.google.common.eventbus.Subscribe
 *  cpw.mods.fml.common.DummyModContainer
 *  cpw.mods.fml.common.LoadController
 *  cpw.mods.fml.common.Mod$EventHandler
 *  cpw.mods.fml.common.ModMetadata
 *  cpw.mods.fml.common.event.FMLConstructionEvent
 *  cpw.mods.fml.common.event.FMLPostInitializationEvent
 *  cpw.mods.fml.common.event.FMLPreInitializationEvent
 */
package witchinggadgets.asm;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLConstructionEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class WitchingGadgetsCore
extends DummyModContainer {
    public WitchingGadgetsCore() {
        super(new ModMetadata());
        ModMetadata metadata = this.getMetadata();
        metadata.modId = "WitchingGadgetsCore";
        metadata.name = "Witching Gadgets Core";
        metadata.version = "1.1.10";
        metadata.authorList.add("BluSunrize");
    }

    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register((Object)this);
        return true;
    }

    @Subscribe
    public void modConstruction(FMLConstructionEvent event) {
    }

    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
}

