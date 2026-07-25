/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.boss.EntityDragon
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.ritual.rites;

import com.emoniph.witchery.ritual.rites.RiteProtectionCircle;
import com.emoniph.witchery.util.Coord;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class RiteProtectionCircleRepulsive
extends RiteProtectionCircle {
    public RiteProtectionCircleRepulsive(int radius, float upkeepPowerCost, int ticksTolive) {
        super(radius, upkeepPowerCost, ticksTolive);
    }

    @Override
    protected void update(World world, int posX, int posY, int posZ, int radius, long ticks) {
        this.repulse(world, posX, posY, posZ, radius);
    }

    private void repulse(World world, int posX, int posY, int posZ, float radius) {
        AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)((float)posX - radius), (double)((float)posY - radius), (double)((float)posZ - radius), (double)((float)posX + radius), (double)((float)posY + radius), (double)((float)posZ + radius));
        List list = world.func_72872_a(EntityCreature.class, bounds);
        for (Entity entity : list) {
            if (!(Coord.distance(entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, posX, posY, posZ) < (double)radius)) continue;
            RiteProtectionCircleRepulsive.push(world, entity, posX, posY, posZ);
        }
    }

    public static void push(World world, Entity entity, double posX, double posY, double posZ) {
        RiteProtectionCircleRepulsive.push(world, entity, posX, posY, posZ, true);
    }

    public static void push(World world, Entity entity, double posX, double posY, double posZ, boolean restricted) {
        if (!restricted || !(entity instanceof EntityPlayer) && !(entity instanceof EntityDragon)) {
            Entity entity2 = entity;
            double d = posX - entity2.field_70165_t;
            double d1 = posY - entity2.field_70163_u;
            double d2 = posZ - entity2.field_70161_v;
            double d4 = d * d + d1 * d1 + d2 * d2;
            if ((d4 *= d4) <= Math.pow(6.0, 4.0)) {
                double d5 = -(d * 0.01999999955296516 / d4) * Math.pow(6.0, 3.0);
                double d6 = -(d1 * 0.01999999955296516 / d4) * Math.pow(6.0, 3.0);
                double d7 = -(d2 * 0.01999999955296516 / d4) * Math.pow(6.0, 3.0);
                if (d5 > 0.0) {
                    d5 = 0.22;
                } else if (d5 < 0.0) {
                    d5 = -0.22;
                }
                if (d6 > 0.2) {
                    d6 = 0.12;
                } else if (d6 < -0.1) {
                    d6 = 0.12;
                }
                if (d7 > 0.0) {
                    d7 = 0.22;
                } else if (d7 < 0.0) {
                    d7 = -0.22;
                }
                entity2.field_70159_w += d5;
                entity2.field_70181_x += d6;
                entity2.field_70179_y += d7;
            }
        }
    }
}

