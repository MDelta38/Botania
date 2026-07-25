/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual;

import com.emoniph.witchery.ritual.RitualStep;
import com.emoniph.witchery.ritual.Sacrifice;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class SacrificeMultiple
extends Sacrifice {
    private final Sacrifice[] sacrifices;

    public SacrificeMultiple(Sacrifice ... sacrifices) {
        this.sacrifices = sacrifices;
    }

    @Override
    public void addDescription(StringBuffer sb) {
        for (Sacrifice sacrifice : this.sacrifices) {
            sacrifice.addDescription(sb);
        }
    }

    @Override
    public boolean isMatch(World world, int posX, int posY, int posZ, int maxDistance, ArrayList<Entity> entities, ArrayList<ItemStack> grassperStacks) {
        for (Sacrifice sacrifice : this.sacrifices) {
            if (sacrifice.isMatch(world, posX, posY, posZ, maxDistance, entities, grassperStacks)) continue;
            return false;
        }
        return true;
    }

    @Override
    public void addSteps(ArrayList<RitualStep> steps, AxisAlignedBB bounds, int maxDistance) {
        for (Sacrifice sacrifice : this.sacrifices) {
            sacrifice.addSteps(steps, bounds, maxDistance);
        }
    }
}

