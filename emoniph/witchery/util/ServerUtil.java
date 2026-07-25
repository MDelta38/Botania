/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.WorldServer
 */
package com.emoniph.witchery.util;

import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

public class ServerUtil {
    public static WorldServer getWorld(int dimension) {
        for (WorldServer world : MinecraftServer.func_71276_C().field_71305_c) {
            if (world.field_73011_w.field_76574_g != dimension) continue;
            return world;
        }
        return null;
    }
}

