/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.event.world.WorldEvent$Load
 *  net.minecraftforge.event.world.WorldEvent$Save
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.world.WorldEvent;

public class EventHandlerWorld {
    @SubscribeEvent
    public void worldLoad(WorldEvent.Load event) {
        if (!event.world.field_72995_K && event.world.field_73011_w.field_76574_g == 0) {
            PocketPlaneData.loadPocketPlanes(event.world);
        }
    }

    @SubscribeEvent
    public void worldSave(WorldEvent.Save event) {
        if (!event.world.field_72995_K && event.world.field_73011_w.field_76574_g == 0) {
            PocketPlaneData.savePocketPlanes(event.world);
        }
    }
}

