/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.util.Vec3
 */
package com.kentington.thaumichorizons.common.entities.ai;

import com.kentington.thaumichorizons.common.entities.ai.RandomPositionGeneratorTH;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;

public class EntityAIWanderTH
extends EntityAIBase {
    private EntityLiving entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;
    private static final String __OBFID = "CL_00001608";

    public EntityAIWanderTH(EntityLiving p_i1648_1_, double p_i1648_2_) {
        this.entity = p_i1648_1_;
        this.speed = p_i1648_2_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        if (this.entity.func_70654_ax() >= 100) {
            return false;
        }
        if (this.entity.func_70681_au().nextInt(120) != 0) {
            return false;
        }
        Vec3 vec3 = RandomPositionGeneratorTH.findRandomTarget(this.entity, 10, 7);
        if (vec3 == null) {
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
}

