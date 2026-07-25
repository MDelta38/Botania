/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 */
package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.common.ExtendedPlayer;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIMoveTowardsVampire
extends EntityAIBase {
    private EntityCreature theEntity;
    private EntityLivingBase targetEntity;
    private double movePosX;
    private double movePosY;
    private double movePosZ;
    private double speed;
    private float maxTargetDistance;
    private float minTargetDistance;

    public EntityAIMoveTowardsVampire(EntityCreature par1EntityCreature, double par2, float min, float max) {
        this.theEntity = par1EntityCreature;
        this.speed = par2;
        this.minTargetDistance = min;
        this.maxTargetDistance = max;
        this.func_75248_a(1);
    }

    private EntityLivingBase getDistanceSqToPartner() {
        double R = this.maxTargetDistance;
        AxisAlignedBB bb = AxisAlignedBB.func_72330_a((double)(this.theEntity.field_70165_t - R), (double)(this.theEntity.field_70163_u - R), (double)(this.theEntity.field_70161_v - R), (double)(this.theEntity.field_70165_t + R), (double)(this.theEntity.field_70163_u + R), (double)(this.theEntity.field_70161_v + R));
        List mogs = this.theEntity.field_70170_p.func_72872_a(EntityPlayer.class, bb);
        double minDistance = Double.MAX_VALUE;
        EntityPlayer target = null;
        for (EntityPlayer player : mogs) {
            double distance;
            if (ExtendedPlayer.get(player).getVampireLevel() < 8 || !((distance = this.theEntity.func_70068_e((Entity)player)) < minDistance)) continue;
            minDistance = distance;
            target = player;
        }
        return target;
    }

    public boolean func_75250_a() {
        this.targetEntity = this.getDistanceSqToPartner();
        if (this.targetEntity == null) {
            return false;
        }
        double dist = this.targetEntity.func_70068_e((Entity)this.theEntity);
        if (dist > (double)(this.maxTargetDistance * this.maxTargetDistance)) {
            return false;
        }
        return !(dist < (double)(this.minTargetDistance * this.minTargetDistance));
    }

    public boolean func_75253_b() {
        if (this.theEntity.field_70173_aa % 20 == 0) {
            this.theEntity.func_70661_as().func_75492_a(this.targetEntity.field_70165_t, this.targetEntity.field_70163_u, this.targetEntity.field_70161_v, this.speed);
        }
        return true;
    }

    public void func_75251_c() {
        this.targetEntity = null;
    }

    public void func_75249_e() {
        this.theEntity.func_70661_as().func_75492_a(this.targetEntity.field_70165_t, this.targetEntity.field_70163_u, this.targetEntity.field_70161_v, this.speed);
    }
}

