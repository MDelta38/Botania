/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.common;

import com.emoniph.witchery.util.Coord;
import net.minecraft.world.World;

public interface IPowerSource {
    public World getWorld();

    public Coord getLocation();

    public boolean isLocationEqual(Coord var1);

    public boolean consumePower(float var1);

    public float getCurrentPower();

    public float getRange();

    public int getEnhancementLevel();

    public boolean isPowerInvalid();
}

