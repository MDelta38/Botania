/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.init.Items
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityScholarChicken
extends EntityChicken {
    public int timeUntilNextFeather;

    public EntityScholarChicken(World p_i1682_1_) {
        super(p_i1682_1_);
        this.timeUntilNextFeather = this.field_70146_Z.nextInt(4000) + 4000;
    }

    public void func_70636_d() {
        int notTime = this.field_70887_j;
        this.field_70887_j = Integer.MAX_VALUE;
        super.func_70636_d();
        this.field_70887_j = notTime;
        this.field_70888_h = this.field_70886_e;
        this.field_70884_g = this.field_70883_f;
        this.field_70883_f = (float)((double)this.field_70883_f + (double)(this.field_70122_E ? -1 : 4) * 0.3);
        if (this.field_70883_f < 0.0f) {
            this.field_70883_f = 0.0f;
        }
        if (this.field_70883_f > 1.0f) {
            this.field_70883_f = 1.0f;
        }
        if (!this.field_70122_E && this.field_70889_i < 1.0f) {
            this.field_70889_i = 1.0f;
        }
        this.field_70889_i = (float)((double)this.field_70889_i * 0.9);
        if (!this.field_70122_E && this.field_70181_x < 0.0) {
            this.field_70181_x *= 0.6;
        }
        this.field_70886_e += this.field_70889_i * 2.0f;
        if (!(this.field_70170_p.field_72995_K || this.func_70631_g_() || this.func_152116_bZ() || --this.field_70887_j > 0)) {
            this.func_85030_a("mob.chicken.plop", 1.0f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
            this.func_145779_a(ThaumicHorizons.itemInkEgg, 1);
            this.field_70887_j = this.field_70146_Z.nextInt(6000) + 6000;
        }
        if (!(this.field_70170_p.field_72995_K || this.func_70631_g_() || this.func_152116_bZ() || --this.timeUntilNextFeather > 0)) {
            this.func_85030_a("mob.chicken.plop", 1.0f, (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f + 1.0f);
            this.func_145779_a(Items.field_151008_G, 1);
            this.timeUntilNextFeather = this.field_70146_Z.nextInt(4000) + 4000;
        }
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74768_a("egg", this.field_70887_j);
        p_70014_1_.func_74768_a("feather", this.timeUntilNextFeather);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_) {
        super.func_70037_a(p_70037_1_);
        this.field_70887_j = p_70037_1_.func_74762_e("egg");
        this.timeUntilNextFeather = p_70037_1_.func_74762_e("feather");
    }
}

