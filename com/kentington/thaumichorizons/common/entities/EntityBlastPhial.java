/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityPotion
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityBlastPhial
extends EntityPotion {
    public EntityBlastPhial(World p_i1790_1_) {
        super(p_i1790_1_);
    }

    public EntityBlastPhial(World p_i1790_1_, EntityLivingBase p_i1790_2_, float power, ItemStack p_i1790_3_) {
        super(p_i1790_1_, p_i1790_2_, p_i1790_3_);
        this.func_70105_a(0.25f, 0.25f);
        this.func_70105_a(0.5f, 0.5f);
        this.func_70012_b(p_i1790_2_.field_70165_t, p_i1790_2_.field_70163_u + (double)p_i1790_2_.func_70047_e(), p_i1790_2_.field_70161_v, p_i1790_2_.field_70177_z, p_i1790_2_.field_70125_A);
        this.field_70165_t -= (double)(MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * 0.16f);
        this.field_70163_u -= (double)0.1f;
        this.field_70161_v -= (double)(MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * 0.16f);
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.field_70129_M = 0.0f;
        this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, power * 1.5f, 1.0f);
    }

    public int func_70196_i() {
        return 8229;
    }
}

