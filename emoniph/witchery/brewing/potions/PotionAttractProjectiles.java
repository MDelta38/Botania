/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PotionAttractProjectiles
extends PotionBase {
    public PotionAttractProjectiles(int id, int color) {
        super(id, true, color);
    }

    public boolean func_76397_a(int duration, int amplifier) {
        return true;
    }

    public void func_76394_a(EntityLivingBase target, int amplifier) {
        World world = target.field_70170_p;
        double RADIUS = (1.0 + (double)amplifier) * 3.0;
        double RADIUS_SQ = RADIUS * RADIUS;
        AxisAlignedBB bounds = target.field_70121_D.func_72314_b(RADIUS, RADIUS, RADIUS);
        List projectileList = world.func_72872_a(IProjectile.class, bounds);
        for (IProjectile projectile : projectileList) {
            if (!(projectile instanceof Entity)) continue;
            Entity arrow = (Entity)projectile;
            double velocitySq = arrow.field_70159_w * arrow.field_70159_w + arrow.field_70181_x * arrow.field_70181_x + arrow.field_70179_y * arrow.field_70179_y;
            double FAST_SQ = 0.25;
            if (arrow.field_70173_aa < (velocitySq > 0.25 ? 1 : 10)) continue;
            double d0 = target.field_70165_t - arrow.field_70165_t;
            double d1 = target.field_70121_D.field_72338_b + (double)target.field_70131_O * 0.75 - arrow.field_70163_u;
            double d2 = target.field_70161_v - arrow.field_70161_v;
            double d3 = MathHelper.func_76133_a((double)(d0 * d0 + d2 * d2));
            if (!(d3 >= 1.0E-7)) continue;
            projectile.func_70186_c(d0, d1, d2, 1.0f, 1.0f);
        }
    }
}

