/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.world.Teleporter
 *  net.minecraft.world.WorldServer
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.lib.PocketPlaneData;
import net.minecraft.entity.Entity;
import net.minecraft.world.Teleporter;
import net.minecraft.world.WorldServer;

public class VortexTeleporter
extends Teleporter {
    private WorldServer worldServerInstance;
    private int planeNum;

    public VortexTeleporter(WorldServer p_i1963_1_, int num) {
        super(p_i1963_1_);
        this.worldServerInstance = p_i1963_1_;
        this.planeNum = num;
    }

    public void func_77185_a(Entity p_77185_1_, double p_77185_2_, double p_77185_4_, double p_77185_6_, float p_77185_8_) {
        if (this.worldServerInstance.field_73011_w.field_76574_g != 0) {
            p_77185_1_.func_70107_b(0.5, 129.0, (double)((float)(256 * this.planeNum) + 0.5f));
        } else {
            p_77185_1_.func_70107_b(PocketPlaneData.positions.get((int)this.planeNum).field_72450_a, PocketPlaneData.positions.get((int)this.planeNum).field_72448_b, PocketPlaneData.positions.get((int)this.planeNum).field_72449_c);
        }
    }
}

