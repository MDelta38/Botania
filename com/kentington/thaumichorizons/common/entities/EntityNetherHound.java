/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.world.World
 *  thaumcraft.common.entities.projectile.EntityEmber
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.items.ItemFocusContainment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.world.World;
import thaumcraft.common.entities.projectile.EntityEmber;

public class EntityNetherHound
extends EntityWolf {
    long soundDelay = 0L;

    public EntityNetherHound(World p_i1696_1_) {
        super(p_i1696_1_);
        this.field_70178_ae = true;
    }

    public void func_70636_d() {
        super.func_70636_d();
        EntityLivingBase target = null;
        if (this.func_70643_av() != null) {
            target = this.func_70643_av();
        }
        if (this.func_70638_az() != null) {
            target = this.func_70638_az();
        }
        if (target != null && ItemFocusContainment.getPointedEntity(this.field_70170_p, (EntityLivingBase)this, 7.0) == target) {
            if (!this.field_70170_p.field_72995_K && this.soundDelay < System.currentTimeMillis()) {
                this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:fireloop", 0.33f, 2.0f);
                this.soundDelay = System.currentTimeMillis() + 500L;
            }
            float scatter = 8.0f;
            EntityEmber orb = new EntityEmber(this.field_70170_p, (EntityLivingBase)this, scatter);
            orb.damage = 1.0f;
            orb.firey = 1;
            orb.field_70165_t += orb.field_70159_w;
            orb.field_70163_u += orb.field_70181_x;
            orb.field_70161_v += orb.field_70179_y;
            this.field_70170_p.func_72838_d((Entity)orb);
        }
    }
}

