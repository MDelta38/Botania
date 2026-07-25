/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.RandomPositionGenerator
 *  net.minecraft.util.Vec3
 */
package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.util.Coord;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityAIWanderWithRestriction
extends EntityAIBase {
    private EntityCreature entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private IHomeLocationProvider home;

    public EntityAIWanderWithRestriction(EntityCreature creature, double speed, IHomeLocationProvider home) {
        this.entity = creature;
        this.speed = speed;
        this.func_75248_a(1);
        this.home = home;
    }

    public boolean func_75250_a() {
        if (this.entity.func_70654_ax() >= 100) {
            return false;
        }
        if (this.entity.func_70681_au().nextInt(120) != 0) {
            return false;
        }
        Vec3 vec3 = RandomPositionGenerator.func_75463_a((EntityCreature)this.entity, (int)10, (int)7);
        if (vec3 == null) {
            return false;
        }
        if (Coord.distanceSq(vec3.field_72450_a, vec3.field_72448_b, vec3.field_72449_c, this.home.getHomeX(), this.home.getHomeY(), this.home.getHomeZ()) > this.home.getHomeRange() * this.home.getHomeRange()) {
            return false;
        }
        this.xPosition = vec3.field_72450_a;
        this.yPosition = vec3.field_72448_b;
        this.zPosition = vec3.field_72449_c;
        return true;
    }

    public boolean func_75253_b() {
        return !this.entity.func_70661_as().func_75500_f();
    }

    public void func_75249_e() {
        this.entity.func_70661_as().func_75492_a(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }

    public static interface IHomeLocationProvider {
        public double getHomeX();

        public double getHomeY();

        public double getHomeZ();

        public double getHomeRange();
    }
}

