/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.lib.ExplosionAlchemite;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityAlchemitePrimed
extends Entity {
    public EntityLivingBase alchemitePlacedBy;
    public int fuse = 80;

    public EntityAlchemitePrimed(World p_i1729_1_) {
        super(p_i1729_1_);
        this.field_70156_m = true;
        this.func_70105_a(0.98f, 0.98f);
        this.field_70129_M = this.field_70131_O / 2.0f;
    }

    public EntityAlchemitePrimed(World p_i1730_1_, double p_i1730_2_, double p_i1730_4_, double p_i1730_6_, EntityLivingBase p_i1730_8_) {
        this(p_i1730_1_);
        this.func_70107_b(p_i1730_2_, p_i1730_4_, p_i1730_6_);
        float f = (float)(Math.random() * Math.PI * 2.0);
        this.func_70105_a(0.98f, 0.98f);
        this.field_70159_w = -((float)Math.sin(f)) * 0.02f;
        this.field_70181_x = 0.2f;
        this.field_70179_y = -((float)Math.cos(f)) * 0.02f;
        this.fuse = 80;
        this.field_70169_q = p_i1730_2_;
        this.field_70167_r = p_i1730_4_;
        this.field_70166_s = p_i1730_6_;
        this.alchemitePlacedBy = p_i1730_8_;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70181_x -= (double)0.04f;
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= (double)0.98f;
        this.field_70181_x *= (double)0.98f;
        this.field_70179_y *= (double)0.98f;
        if (this.field_70122_E) {
            this.field_70159_w *= (double)0.7f;
            this.field_70179_y *= (double)0.7f;
            this.field_70181_x *= -0.5;
        }
        if (this.fuse-- <= 0) {
            this.func_70106_y();
            if (!this.field_70170_p.field_72995_K) {
                this.explode();
            }
        } else {
            this.field_70170_p.func_72869_a("smoke", this.field_70165_t, this.field_70163_u + 0.5, this.field_70161_v, 0.0, 0.0, 0.0);
        }
    }

    private void explode() {
        ExplosionAlchemite explosion = new ExplosionAlchemite(this.field_70170_p, (Entity)this.alchemitePlacedBy, this.field_70165_t, this.field_70163_u, this.field_70161_v, 5.0f);
        explosion.field_77286_a = false;
        explosion.field_82755_b = true;
        explosion.func_77278_a();
        explosion.func_77279_a(true);
    }

    protected void func_70014_b(NBTTagCompound p_70014_1_) {
        p_70014_1_.func_74774_a("Fuse", (byte)this.fuse);
    }

    protected void func_70037_a(NBTTagCompound p_70037_1_) {
        this.fuse = p_70037_1_.func_74771_c("Fuse");
    }

    protected void func_70088_a() {
    }

    public float func_70053_R() {
        return 0.0f;
    }

    protected boolean func_70041_e_() {
        return false;
    }

    public boolean func_70067_L() {
        return !this.field_70128_L;
    }
}

