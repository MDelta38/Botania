/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  ic2.api.energy.event.EnergyTileLoadEvent
 *  ic2.api.energy.event.EnergyTileUnloadEvent
 *  ic2.api.energy.tile.IEnergyTile
 *  net.minecraftforge.common.MinecraftForge
 */
package thaumic.tinkerer.common.compat;

import cpw.mods.fml.common.eventhandler.Event;
import ic2.api.energy.event.EnergyTileLoadEvent;
import ic2.api.energy.event.EnergyTileUnloadEvent;
import ic2.api.energy.tile.IEnergyTile;
import net.minecraftforge.common.MinecraftForge;
import thaumic.tinkerer.common.block.tile.transvector.TileTransvectorInterface;

public class IndustrialcraftUnloadHelper {
    public static void removeFromIC2EnergyNet(TileTransvectorInterface tileEntity) {
        if (tileEntity.addedToICEnergyNet && !tileEntity.func_145831_w().field_72995_K) {
            MinecraftForge.EVENT_BUS.post((Event)new EnergyTileUnloadEvent((IEnergyTile)tileEntity));
            tileEntity.addedToICEnergyNet = false;
        }
    }

    public static void addToIC2EnergyNet(TileTransvectorInterface tileTransvectorInterface) {
        MinecraftForge.EVENT_BUS.post((Event)new EnergyTileLoadEvent((IEnergyTile)tileTransvectorInterface));
    }
}

