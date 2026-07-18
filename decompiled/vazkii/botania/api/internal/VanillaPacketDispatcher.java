/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package vazkii.botania.api.internal;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public final class VanillaPacketDispatcher {
    public static void dispatchTEToNearbyPlayers(TileEntity tile) {
        World world = tile.func_145831_w();
        List players = world.field_73010_i;
        for (Object player : players) {
            if (!(player instanceof EntityPlayerMP)) continue;
            EntityPlayerMP mp = (EntityPlayerMP)player;
            if (!(VanillaPacketDispatcher.pointDistancePlane(mp.field_70165_t, mp.field_70161_v, (double)tile.field_145851_c + 0.5, (double)tile.field_145849_e + 0.5) < 64.0f)) continue;
            ((EntityPlayerMP)player).field_71135_a.func_147359_a(tile.func_145844_m());
        }
    }

    public static void dispatchTEToNearbyPlayers(World world, int x, int y, int z) {
        TileEntity tile = world.func_147438_o(x, y, z);
        if (tile != null) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(tile);
        }
    }

    public static float pointDistancePlane(double x1, double y1, double x2, double y2) {
        return (float)Math.hypot(x1 - x2, y1 - y2);
    }
}

