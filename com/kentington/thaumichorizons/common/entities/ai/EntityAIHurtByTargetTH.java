/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.AxisAlignedBB
 */
package com.kentington.thaumichorizons.common.entities.ai;

import com.kentington.thaumichorizons.common.entities.ai.EntityAITargetTH;
import java.util.List;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIHurtByTargetTH
extends EntityAITargetTH {
    boolean entityCallsForHelp;
    private int field_142052_b;
    private static final String __OBFID = "CL_00001619";

    public EntityAIHurtByTargetTH(EntityLiving p_i1660_1_, boolean p_i1660_2_) {
        super(p_i1660_1_, false);
        this.entityCallsForHelp = p_i1660_2_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        int i = this.taskOwner.func_142015_aE();
        return i != this.field_142052_b && this.isSuitableTarget(this.taskOwner.func_70643_av(), false);
    }

    @Override
    public void func_75249_e() {
        this.taskOwner.func_70624_b(this.taskOwner.func_70643_av());
        this.field_142052_b = this.taskOwner.func_142015_aE();
        if (this.entityCallsForHelp) {
            double d0 = this.getTargetDistance();
            List list = this.taskOwner.field_70170_p.func_72872_a(this.taskOwner.getClass(), AxisAlignedBB.func_72330_a((double)this.taskOwner.field_70165_t, (double)this.taskOwner.field_70163_u, (double)this.taskOwner.field_70161_v, (double)(this.taskOwner.field_70165_t + 1.0), (double)(this.taskOwner.field_70163_u + 1.0), (double)(this.taskOwner.field_70161_v + 1.0)).func_72314_b(d0, 10.0, d0));
            for (EntityLiving EntityLiving2 : list) {
                if (this.taskOwner == EntityLiving2 || EntityLiving2.func_70638_az() != null || EntityLiving2.func_142014_c(this.taskOwner.func_70643_av())) continue;
                EntityLiving2.func_70624_b(this.taskOwner.func_70643_av());
            }
        }
        super.func_75249_e();
    }
}

