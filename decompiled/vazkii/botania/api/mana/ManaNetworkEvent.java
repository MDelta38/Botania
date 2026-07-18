/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.MinecraftForge
 */
package vazkii.botania.api.mana;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;

public class ManaNetworkEvent
extends Event {
    public final TileEntity tile;
    public final ManaBlockType type;
    public final Action action;

    public ManaNetworkEvent(TileEntity tile, ManaBlockType type, Action action) {
        this.tile = tile;
        this.type = type;
        this.action = action;
    }

    public static void addCollector(TileEntity tile) {
        ManaNetworkEvent event = new ManaNetworkEvent(tile, ManaBlockType.COLLECTOR, Action.ADD);
        MinecraftForge.EVENT_BUS.post((Event)event);
    }

    public static void removeCollector(TileEntity tile) {
        ManaNetworkEvent event = new ManaNetworkEvent(tile, ManaBlockType.COLLECTOR, Action.REMOVE);
        MinecraftForge.EVENT_BUS.post((Event)event);
    }

    public static void addPool(TileEntity tile) {
        ManaNetworkEvent event = new ManaNetworkEvent(tile, ManaBlockType.POOL, Action.ADD);
        MinecraftForge.EVENT_BUS.post((Event)event);
    }

    public static void removePool(TileEntity tile) {
        ManaNetworkEvent event = new ManaNetworkEvent(tile, ManaBlockType.POOL, Action.REMOVE);
        MinecraftForge.EVENT_BUS.post((Event)event);
    }

    public static enum Action {
        REMOVE,
        ADD;

    }

    public static enum ManaBlockType {
        POOL,
        COLLECTOR;

    }
}

