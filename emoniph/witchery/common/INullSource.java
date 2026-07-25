/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.common;

import net.minecraft.world.World;

public interface INullSource {
    public World getWorld();

    public int getPosX();

    public int getPosY();

    public int getPosZ();

    public float getRange();

    public boolean isPowerInvalid();
}

