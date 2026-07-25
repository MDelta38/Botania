/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 */
package com.emoniph.witchery.util;

import com.emoniph.witchery.util.BlockPosition;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;

public class EntityPosition {
    public final double x;
    public final double y;
    public final double z;

    public EntityPosition(int x, int y, int z) {
        this(0.5 + (double)x, 0.5 + (double)y, 0.5 + (double)z);
    }

    public EntityPosition(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public EntityPosition(BlockPosition position) {
        this.x = position.x;
        this.y = position.y;
        this.z = position.z;
    }

    public EntityPosition(Entity entity) {
        this(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v);
    }

    public EntityPosition(MovingObjectPosition mop) {
        if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY) {
            this.x = mop.field_72308_g.field_70165_t;
            this.y = mop.field_72308_g.field_70163_u;
            this.z = mop.field_72308_g.field_70161_v;
        } else if (mop.field_72313_a == MovingObjectPosition.MovingObjectType.BLOCK) {
            this.x = (double)mop.field_72311_b + 0.5;
            this.y = (double)mop.field_72312_c + 0.5;
            this.z = (double)mop.field_72309_d + 0.5;
        } else {
            this.x = 0.0;
            this.y = 0.0;
            this.z = 0.0;
        }
    }

    public double getDistanceSqToEntity(Entity entity) {
        double d0 = this.x - entity.field_70165_t;
        double d1 = this.y - entity.field_70163_u;
        double d2 = this.z - entity.field_70161_v;
        return d0 * d0 + d1 * d1 + d2 * d2;
    }

    public AxisAlignedBB getBounds(double radius) {
        AxisAlignedBB aabb = AxisAlignedBB.func_72330_a((double)(this.x - radius), (double)(this.y - radius), (double)(this.z - radius), (double)(this.x + radius), (double)(this.y + radius), (double)(this.z + radius));
        return aabb;
    }

    public boolean occupiedBy(Entity entity) {
        return entity != null && entity.field_70165_t == this.x && entity.field_70163_u == this.y && entity.field_70161_v == this.z;
    }
}

