/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.kentington.thaumichorizons.common.entities.ai;

import com.kentington.thaumichorizons.common.lib.EntityInfusionProperties;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAISitTH
extends EntityAIBase {
    private EntityLiving theEntity;
    private boolean isSitting;
    private static final String __OBFID = "CL_00001613";

    public EntityAISitTH(EntityLiving p_i1654_1_) {
        this.theEntity = p_i1654_1_;
        this.func_75248_a(5);
    }

    public boolean func_75250_a() {
        if (this.theEntity.func_70090_H()) {
            return false;
        }
        if (!this.theEntity.field_70122_E) {
            return false;
        }
        EntityInfusionProperties prop = (EntityInfusionProperties)this.theEntity.getExtendedProperties("CreatureInfusion");
        EntityPlayer entitylivingbase = this.theEntity.field_70170_p.func_72924_a(prop.getOwner());
        return entitylivingbase == null ? true : (this.theEntity.func_70068_e((Entity)entitylivingbase) < 144.0 && entitylivingbase.func_70643_av() != null ? false : this.isSitting);
    }

    public void func_75249_e() {
        EntityInfusionProperties prop = (EntityInfusionProperties)this.theEntity.getExtendedProperties("CreatureInfusion");
        this.theEntity.func_70661_as().func_75499_g();
        prop.setSitting(true);
    }

    public void func_75251_c() {
        EntityInfusionProperties prop = (EntityInfusionProperties)this.theEntity.getExtendedProperties("CreatureInfusion");
        prop.setSitting(false);
    }

    public void setSitting(boolean p_75270_1_) {
        this.isSitting = p_75270_1_;
    }
}

