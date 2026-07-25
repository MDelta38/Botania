/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.util;

import net.minecraft.world.World;

public interface ISpiralBlockAction {
    public void onSpiralActionStart(World var1, int var2, int var3, int var4);

    public boolean onSpiralBlockAction(World var1, int var2, int var3, int var4);

    public void onSpiralActionStop(World var1, int var2, int var3, int var4);
}

