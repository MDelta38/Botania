/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.worldgen.ComponentCoven;
import com.emoniph.witchery.worldgen.IWorldGenHandler;
import java.util.Random;
import net.minecraft.world.World;

public class WorldHandlerCoven
implements IWorldGenHandler {
    private final double chance;
    private final int range;

    public WorldHandlerCoven(double chance, int range) {
        this.chance = chance;
        this.range = range;
    }

    @Override
    public int getExtentX() {
        return 11;
    }

    @Override
    public int getExtentZ() {
        return 11;
    }

    @Override
    public int getRange() {
        return this.range;
    }

    @Override
    public boolean generate(World world, Random random, int x, int z) {
        if (Config.instance().generateCovens && random.nextDouble() < this.chance) {
            int direction = random.nextInt(4);
            if (!new ComponentCoven(direction, random, x, z).addComponentParts(world, random)) {
                return false;
            }
            Log.instance().debug("coven " + x + " " + z + " dir=" + direction);
            return true;
        }
        return false;
    }

    @Override
    public void initiate() {
    }
}

