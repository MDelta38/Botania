/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.AxisAlignedBB
 */
package vazkii.botania.api.recipe;

import cpw.mods.fml.common.eventhandler.Event;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class ElvenPortalUpdateEvent
extends Event {
    public final TileEntity portalTile;
    public final AxisAlignedBB aabb;
    public boolean open;
    public final List<ItemStack> stacksInside;

    public ElvenPortalUpdateEvent(TileEntity te, AxisAlignedBB aabb, boolean open, List<ItemStack> stacks) {
        this.portalTile = te;
        this.aabb = aabb;
        this.open = open;
        this.stacksInside = stacks;
    }
}

