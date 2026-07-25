/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityOwnable
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.attributes.IAttributeInstance
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.pathfinding.PathPoint
 *  net.minecraft.util.MathHelper
 *  org.apache.commons.lang3.StringUtils
 */
package com.kentington.thaumichorizons.common.entities.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityOwnable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.StringUtils;

public abstract class EntityAITargetTH
extends EntityAIBase {
    protected EntityLiving taskOwner;
    protected boolean shouldCheckSight;
    private boolean nearbyOnly;
    private int targetSearchStatus;
    private int targetSearchDelay;
    private int field_75298_g;
    private static final String __OBFID = "CL_00001626";

    public EntityAITargetTH(EntityLiving p_i1669_1_, boolean p_i1669_2_) {
        this(p_i1669_1_, p_i1669_2_, false);
    }

    public EntityAITargetTH(EntityLiving p_i1670_1_, boolean p_i1670_2_, boolean p_i1670_3_) {
        this.taskOwner = p_i1670_1_;
        this.shouldCheckSight = p_i1670_2_;
        this.nearbyOnly = p_i1670_3_;
    }

    public boolean func_75253_b() {
        EntityLivingBase entitylivingbase = this.taskOwner.func_70638_az();
        if (entitylivingbase == null) {
            return false;
        }
        if (!entitylivingbase.func_70089_S()) {
            return false;
        }
        double d0 = this.getTargetDistance();
        if (this.taskOwner.func_70068_e((Entity)entitylivingbase) > d0 * d0) {
            return false;
        }
        if (this.shouldCheckSight) {
            if (this.taskOwner.func_70635_at().func_75522_a((Entity)entitylivingbase)) {
                this.field_75298_g = 0;
            } else if (++this.field_75298_g > 60) {
                return false;
            }
        }
        return !(entitylivingbase instanceof EntityPlayerMP) || !((EntityPlayerMP)entitylivingbase).field_71134_c.func_73083_d();
    }

    protected double getTargetDistance() {
        IAttributeInstance iattributeinstance = this.taskOwner.func_110148_a(SharedMonsterAttributes.field_111265_b);
        return iattributeinstance == null ? 16.0 : iattributeinstance.func_111126_e();
    }

    public void func_75249_e() {
        this.targetSearchStatus = 0;
        this.targetSearchDelay = 0;
        this.field_75298_g = 0;
    }

    public void func_75251_c() {
        this.taskOwner.func_70624_b((EntityLivingBase)null);
    }

    protected boolean isSuitableTarget(EntityLivingBase p_75296_1_, boolean p_75296_2_) {
        if (p_75296_1_ == null) {
            return false;
        }
        if (p_75296_1_ == this.taskOwner) {
            return false;
        }
        if (!p_75296_1_.func_70089_S()) {
            return false;
        }
        if (!this.taskOwner.func_70686_a(p_75296_1_.getClass())) {
            return false;
        }
        if (this.taskOwner instanceof IEntityOwnable && StringUtils.isNotEmpty((CharSequence)((IEntityOwnable)this.taskOwner).func_152113_b())) {
            if (p_75296_1_ instanceof IEntityOwnable && ((IEntityOwnable)this.taskOwner).func_152113_b().equals(((IEntityOwnable)p_75296_1_).func_152113_b())) {
                return false;
            }
            if (p_75296_1_ == ((IEntityOwnable)this.taskOwner).func_70902_q()) {
                return false;
            }
        } else if (p_75296_1_ instanceof EntityPlayer && !p_75296_2_ && ((EntityPlayer)p_75296_1_).field_71075_bZ.field_75102_a) {
            return false;
        }
        if (this.shouldCheckSight && !this.taskOwner.func_70635_at().func_75522_a((Entity)p_75296_1_)) {
            return false;
        }
        if (this.nearbyOnly) {
            if (--this.targetSearchDelay <= 0) {
                this.targetSearchStatus = 0;
            }
            if (this.targetSearchStatus == 0) {
                int n = this.targetSearchStatus = this.canEasilyReach(p_75296_1_) ? 1 : 2;
            }
            if (this.targetSearchStatus == 2) {
                return false;
            }
        }
        return true;
    }

    private boolean canEasilyReach(EntityLivingBase p_75295_1_) {
        int j;
        this.targetSearchDelay = 10 + this.taskOwner.func_70681_au().nextInt(5);
        PathEntity pathentity = this.taskOwner.func_70661_as().func_75494_a((Entity)p_75295_1_);
        if (pathentity == null) {
            return false;
        }
        PathPoint pathpoint = pathentity.func_75870_c();
        if (pathpoint == null) {
            return false;
        }
        int i = pathpoint.field_75839_a - MathHelper.func_76128_c((double)p_75295_1_.field_70165_t);
        return (double)(i * i + (j = pathpoint.field_75838_c - MathHelper.func_76128_c((double)p_75295_1_.field_70161_v)) * j) <= 2.25;
    }
}

