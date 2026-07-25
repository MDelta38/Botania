/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 */
package thaumcraft.codechicken.lib.raytracer;

import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public class ExtendedMOP
extends MovingObjectPosition
implements Comparable<ExtendedMOP> {
    public Object data;
    public double dist;

    public ExtendedMOP(Entity entity, Object data) {
        super(entity);
        this.setData(data);
    }

    public ExtendedMOP(int x, int y, int z, int side, Vec3 hit, Object data) {
        super(x, y, z, side, hit);
        this.setData(data);
    }

    public ExtendedMOP(MovingObjectPosition mop, Object data, double dist) {
        super(0, 0, 0, 0, mop.field_72307_f);
        this.field_72313_a = mop.field_72313_a;
        this.field_72311_b = mop.field_72311_b;
        this.field_72312_c = mop.field_72312_c;
        this.field_72309_d = mop.field_72309_d;
        this.field_72310_e = mop.field_72310_e;
        this.subHit = mop.subHit;
        this.setData(data);
        this.dist = dist;
    }

    public void setData(Object data) {
        if (data instanceof Integer) {
            this.subHit = (Integer)data;
        }
        this.data = data;
    }

    public static <T> T getData(MovingObjectPosition mop) {
        if (mop instanceof ExtendedMOP) {
            return (T)((ExtendedMOP)mop).data;
        }
        return (T)Integer.valueOf(mop.subHit);
    }

    @Override
    public int compareTo(ExtendedMOP o) {
        return this.dist == o.dist ? 0 : (this.dist < o.dist ? -1 : 1);
    }
}

