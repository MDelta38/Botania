/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.worldgen;

import java.util.Random;
import net.minecraft.world.World;

public interface IWorldGenHandler {
    public boolean generate(World var1, Random var2, int var3, int var4);

    public void initiate();

    public int getExtentX();

    public int getExtentZ();

    public int getRange();
}

