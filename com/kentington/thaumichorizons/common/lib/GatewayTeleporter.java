/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.WorldServer
 */
package com.kentington.thaumichorizons.common.lib;

import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class GatewayTeleporter
extends Teleporter {
    private WorldServer worldServerInstance;
    private int x;
    private int y;
    private int z;
    private float yaw;

    public GatewayTeleporter(WorldServer p_i1963_1_, int x, int y, int z, float yaw) {
        super(p_i1963_1_);
        this.worldServerInstance = p_i1963_1_;
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
    }

    public void func_77185_a(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_) {
        p_77185_1_.func_70080_a((double)this.x + 0.5, (double)this.y, (double)this.z + 0.5, this.yaw, 0.0f);
    }
}

