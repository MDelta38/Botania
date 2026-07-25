/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.util.ISpiralBlockAction;
import net.minecraft.world.World;

public class EffectSpiral {
    private final ISpiralBlockAction action;

    public EffectSpiral(ISpiralBlockAction action) {
        this.action = action;
    }

    public void apply(World world, int midX, int midY, int midZ, int dimX, int dimZ) {
        this.action.onSpiralActionStart(world, midX, midY, midZ);
        int x = 0;
        int z = 0;
        int dx = 0;
        int dz = -1;
        int t = Math.max(dimX, dimZ);
        int maxI = t * t;
        for (int i = 0; i < maxI && (-dimX / 2 > x || x > dimX / 2 || -dimZ / 2 > z || z > dimZ / 2 || this.action.onSpiralBlockAction(world, midX + x, midY, midZ + z)); x += dx, z += dz, ++i) {
            if (x != z && (x >= 0 || x != -z) && (x <= 0 || x != 1 - z)) continue;
            t = dx;
            dx = -dz;
            dz = t;
        }
        this.action.onSpiralActionStop(world, midX, midY, midZ);
    }
}

