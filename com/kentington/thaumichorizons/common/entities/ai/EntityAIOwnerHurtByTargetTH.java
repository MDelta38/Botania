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

public class EntityAIOwnerHurtByTargetTH
extends EntityAITarget {
    EntityLiving theDefendingTameable;
    EntityLivingBase theOwnerAttacker;
    private int field_142051_e;
    private static final String __OBFID = "CL_00001624";

    public EntityAIOwnerHurtByTargetTH(EntityLiving p_i1667_1_) {
        super((EntityCreature)p_i1667_1_, false);
        this.theDefendingTameable = p_i1667_1_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        EntityInfusionProperties prop = (EntityInfusionProperties)this.theDefendingTameable.getExtendedProperties("CreatureInfusion");
        EntityPlayer entitylivingbase = this.theDefendingTameable.field_70170_p.func_72924_a(prop.getOwner());
        if (entitylivingbase == null) {
            return false;
        }
        this.theOwnerAttacker = entitylivingbase.func_70643_av();
        int i = entitylivingbase.func_142015_aE();
        return i != this.field_142051_e && this.func_75296_a(this.theOwnerAttacker, false);
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.theOwnerAttacker);
        EntityInfusionProperties prop = (EntityInfusionProperties)this.theDefendingTameable.getExtendedProperties("CreatureInfusion");
        EntityPlayer entitylivingbase = this.theDefendingTameable.field_70170_p.func_72924_a(prop.getOwner());
        if (entitylivingbase != null) {
            this.field_142051_e = entitylivingbase.func_142015_aE();
        }
        super.func_75249_e();
    }
}

