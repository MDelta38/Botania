/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.projectile.EntityEgg
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityEggIncubated
extends EntityEgg {
    public EntityEggIncubated(World p_i1779_1_) {
        super(p_i1779_1_);
    }

    public EntityEggIncubated(World p_i1780_1_, EntityLivingBase p_i1780_2_) {
        super(p_i1780_1_, p_i1780_2_);
    }

    public EntityEggIncubated(World p_i1781_1_, double p_i1781_2_, double p_i1781_4_, double p_i1781_6_) {
        super(p_i1781_1_, p_i1781_2_, p_i1781_4_, p_i1781_6_);
    }

    protected void func_70184_a(MovingObjectPosition p_70184_1_) {
        if (p_70184_1_.field_72308_g != null) {
            p_70184_1_.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), 0.0f);
        }
        if (!this.field_70170_p.field_72995_K) {
            EntityChicken entitychicken = new EntityChicken(this.field_70170_p);
            entitychicken.func_70873_a(-24000);
            entitychicken.func_70012_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, this.field_70177_z, 0.0f);
            this.field_70170_p.func_72838_d((Entity)entitychicken);
        }
        for (int j = 0; j < 8; ++j) {
            this.field_70170_p.func_72869_a("snowballpoof", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0, 0.0, 0.0);
        }
        if (!this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }
    }
}

