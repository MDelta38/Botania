/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAIAttackOnCollideTH
extends EntityAIBase {
    World worldObj;
    EntityLiving attacker;
    int attackTick;
    double speedTowardsTarget;
    boolean longMemory;
    PathEntity entityPathEntity;
    Class classTarget;
    private int field_75445_i;
    private double field_151497_i;
    private double field_151495_j;
    private double field_151496_k;
    private static final String __OBFID = "CL_00001595";
    private int failedPathFindingPenalty;

    public EntityAIAttackOnCollideTH(EntityLiving p_i1635_1_, Class p_i1635_2_, double p_i1635_3_, boolean p_i1635_5_) {
        this(p_i1635_1_, p_i1635_3_, p_i1635_5_);
        this.classTarget = p_i1635_2_;
    }

    public EntityAIAttackOnCollideTH(EntityLiving p_i1636_1_, double p_i1636_2_, boolean p_i1636_4_) {
        this.attacker = p_i1636_1_;
        this.worldObj = p_i1636_1_.field_70170_p;
        this.speedTowardsTarget = p_i1636_2_;
        this.longMemory = p_i1636_4_;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        if (entitylivingbase == null) {
            return false;
        }
        if (!entitylivingbase.func_70089_S()) {
            return false;
        }
        if (this.classTarget != null && !this.classTarget.isAssignableFrom(entitylivingbase.getClass())) {
            return false;
        }
        if (--this.field_75445_i <= 0) {
            this.entityPathEntity = this.attacker.func_70661_as().func_75494_a((Entity)entitylivingbase);
            this.field_75445_i = 4 + this.attacker.func_70681_au().nextInt(7);
            return this.entityPathEntity != null;
        }
        return true;
    }

    public boolean func_75253_b() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        return entitylivingbase == null ? false : (!entitylivingbase.func_70089_S() ? false : (!this.longMemory ? !this.attacker.func_70661_as().func_75500_f() : true));
    }

    public void func_75249_e() {
        this.attacker.func_70661_as().func_75484_a(this.entityPathEntity, this.speedTowardsTarget);
        this.field_75445_i = 0;
    }

    public void func_75251_c() {
        this.attacker.func_70661_as().func_75499_g();
    }

    public void func_75246_d() {
        EntityLivingBase entitylivingbase = this.attacker.func_70638_az();
        this.attacker.func_70671_ap().func_75651_a((Entity)entitylivingbase, 30.0f, 30.0f);
        double d0 = this.attacker.func_70092_e(entitylivingbase.field_70165_t, entitylivingbase.field_70121_D.field_72338_b, entitylivingbase.field_70161_v);
        double d1 = this.attacker.field_70130_N * 2.0f * this.attacker.field_70130_N * 2.0f + entitylivingbase.field_70130_N;
        --this.field_75445_i;
        if ((this.longMemory || this.attacker.func_70635_at().func_75522_a((Entity)entitylivingbase)) && this.field_75445_i <= 0 && (this.field_151497_i == 0.0 && this.field_151495_j == 0.0 && this.field_151496_k == 0.0 || entitylivingbase.func_70092_e(this.field_151497_i, this.field_151495_j, this.field_151496_k) >= 1.0 || this.attacker.func_70681_au().nextFloat() < 0.05f)) {
            PathPoint finalPathPoint;
            this.field_151497_i = entitylivingbase.field_70165_t;
            this.field_151495_j = entitylivingbase.field_70121_D.field_72338_b;
            this.field_151496_k = entitylivingbase.field_70161_v;
            this.field_75445_i = this.failedPathFindingPenalty + 4 + this.attacker.func_70681_au().nextInt(7);
            this.failedPathFindingPenalty = this.attacker.func_70661_as().func_75505_d() != null ? ((finalPathPoint = this.attacker.func_70661_as().func_75505_d().func_75870_c()) != null && entitylivingbase.func_70092_e((double)finalPathPoint.field_75839_a, (double)finalPathPoint.field_75837_b, (double)finalPathPoint.field_75838_c) < 1.0 ? 0 : (this.failedPathFindingPenalty += 10)) : (this.failedPathFindingPenalty += 10);
            if (d0 > 1024.0) {
                this.field_75445_i += 10;
            } else if (d0 > 256.0) {
                this.field_75445_i += 5;
            }
            if (!this.attacker.func_70661_as().func_75497_a((Entity)entitylivingbase, this.speedTowardsTarget)) {
                this.field_75445_i += 15;
            }
        }
        this.attackTick = Math.max(this.attackTick - 1, 0);
        if (d0 <= d1 && this.attackTick <= 20) {
            this.attackTick = 20;
            if (this.attacker.func_70694_bm() != null) {
                this.attacker.func_71038_i();
            }
            float damage = 3.0f;
            if (this.attacker.func_110148_a(SharedMonsterAttributes.field_111264_e) != null) {
                damage = (float)((double)damage + this.attacker.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e());
            }
            entitylivingbase.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this.attacker), damage);
        }
    }
}

