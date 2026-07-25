/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual;

import com.emoniph.witchery.ritual.RitualStep;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public abstract class Rite {
    protected boolean canRelocate = false;

    public abstract void addSteps(ArrayList<RitualStep> var1, int var2);

    public ArrayList<EntityItem> getItemsInRadius(World world, int x, int y, int z, float radius) {
        float RADIUS_SQ = radius * radius;
        double midX = 0.5 + (double)x;
        double midZ = 0.5 + (double)z;
        ArrayList<EntityItem> resultList = new ArrayList<EntityItem>();
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(midX - (double)radius), (double)y, (double)(midZ - (double)radius), (double)(midX + (double)radius), (double)(1.0 + (double)y), (double)(midZ + (double)radius));
        List items = world.func_72872_a(EntityItem.class, bounds);
        for (Object obj : items) {
            EntityItem entity = (EntityItem)obj;
            if (!(entity.func_70092_e(midX, (double)y, midZ) <= (double)RADIUS_SQ)) continue;
            resultList.add(entity);
        }
        return resultList;
    }

    public boolean relocatable() {
        return this.canRelocate;
    }
}

