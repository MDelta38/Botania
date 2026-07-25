/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAITarget
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.kentington.thaumichorizons.common.entities.ai;

import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIOwnerHurtTargetTH
extends EntityAITarget {
    EntityLiving theEntityTameable;
    EntityLivingBase theTarget;
    private int field_142050_e;
    private static final String __OBFID = "CL_00001625";

    public EntityAIOwnerHurtTargetTH(EntityLiving p_i1668_1_) {
        super((EntityCreature)p_i1668_1_, false);
        this.theEntityTameable = p_i1668_1_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        EntityInfusionProperties prop = (EntityInfusionProperties)this.theEntityTameable.getExtendedProperties("CreatureInfusion");
        EntityPlayer entitylivingbase = this.theEntityTameable.field_70170_p.func_72924_a(prop.getOwner());
        if (entitylivingbase == null) {
            return false;
        }
        this.theTarget = entitylivingbase.func_110144_aD();
        int i = entitylivingbase.func_142013_aG();
        return i != this.field_142050_e && this.func_75296_a(this.theTarget, false);
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.theTarget);
        EntityInfusionProperties prop = (EntityInfusionProperties)this.theEntityTameable.getExtendedProperties("CreatureInfusion");
        EntityPlayer entitylivingbase = this.theEntityTameable.field_70170_p.func_72924_a(prop.getOwner());
        if (entitylivingbase != null) {
            this.field_142050_e = entitylivingbase.func_142013_aG();
        }
        super.func_75249_e();
    }
}

