/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.tileentity.TileEntity
 */
package vazkii.botania.api.item;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.tileentity.TileEntity;

@SideOnly(value=Side.CLIENT)
public class TinyPotatoRenderEvent
extends Event {
    public final TileEntity tile;
    public final String name;
    public final double x;
    public final double y;
    public final double z;
    public final float partTicks;

    public TinyPotatoRenderEvent(TileEntity tile, String name, double x, double y, double z, float partTicks) {
        this.tile = tile;
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.partTicks = partTicks;
    }
}

