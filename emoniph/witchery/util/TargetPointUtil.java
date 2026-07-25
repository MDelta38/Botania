/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.util;

import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public final class TargetPointUtil {
    public static NetworkRegistry.TargetPoint from(Entity entity, double range) {
        if (entity != null) {
            return new NetworkRegistry.TargetPoint(entity.field_71093_bK, entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, range);
        }
        return new NetworkRegistry.TargetPoint(0, 0.0, 0.0, 0.0, range);
    }

    public static NetworkRegistry.TargetPoint from(World world, double x, double y, double z, double range) {
        return new NetworkRegistry.TargetPoint(world.field_73011_w.field_76574_g, x, y, z, range);
    }
}

