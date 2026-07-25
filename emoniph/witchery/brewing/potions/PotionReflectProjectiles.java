/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IProjectile
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class PotionReflectProjectiles
extends PotionBase {
    public PotionReflectProjectiles(int id, int color) {
        super(id, color);
    }

    public boolean func_76397_a(int duration, int amplifier) {
        return true;
    }

    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        World world = entity.field_70170_p;
        double RADIUS = 2.0;
        double RADIUS_SQ = 4.0;
        AxisAlignedBB bounds = entity.field_70121_D.func_72314_b(2.0, 2.0, 2.0);
        List projectileList = world.func_72872_a(IProjectile.class, bounds);
        for (IProjectile projectile : projectileList) {
            EntityArrow arrow;
            boolean isArrow = false;
            if (projectile instanceof EntityArrow) {
                arrow = (EntityArrow)projectile;
                isArrow = true;
                if (arrow.field_70250_c == entity) {
                    continue;
                }
            } else if (projectile instanceof EntityThrowable && (arrow = (EntityThrowable)projectile).func_85052_h() == entity) continue;
            if (!(projectile instanceof Entity)) continue;
            Entity projectileEntity = (Entity)projectile;
            projectileEntity.field_70159_w *= -0.25 * (1.0 + (double)amplifier);
            if (projectileEntity.field_70159_w > 0.0 || projectileEntity.field_70179_y > 0.0) {
                projectileEntity.field_70181_x *= -0.25 * (1.0 + (double)amplifier);
            }
            projectileEntity.field_70179_y *= -0.25 * (1.0 + (double)amplifier);
            if (!isArrow) continue;
        }
    }
}

