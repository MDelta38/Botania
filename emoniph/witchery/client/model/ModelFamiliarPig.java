/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelPig
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityFamiliar;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelPig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelFamiliarPig
extends ModelPig {
    int field_78163_i = 1;

    public ModelFamiliarPig() {
        this(0.0f);
    }

    public ModelFamiliarPig(float par1) {
        super(par1);
    }

    public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        EntityFamiliar entityocelot = (EntityFamiliar)par1EntityLivingBase;
        this.field_78150_a.func_78793_a(0.0f, 12.0f, -6.0f);
        this.field_78148_b.func_78793_a(0.0f, 11.0f, 2.0f);
        this.field_78149_c.func_78793_a(-3.0f, 18.0f, 7.0f);
        this.field_78146_d.func_78793_a(3.0f, 18.0f, 7.0f);
        this.field_78147_e.func_78793_a(-3.0f, 18.0f, -5.0f);
        this.field_78144_f.func_78793_a(3.0f, 18.0f, -5.0f);
        if (entityocelot.func_70906_o()) {
            this.field_78148_b.field_78795_f = 0.7853982f;
            this.field_78148_b.field_78797_d += 3.5f;
            this.field_78148_b.field_78798_e += 0.0f;
            this.field_78146_d.field_78795_f = -0.15707964f;
            this.field_78149_c.field_78795_f = -0.15707964f;
            this.field_78146_d.field_78797_d = 15.8f;
            this.field_78149_c.field_78797_d = 15.8f;
            this.field_78146_d.field_78798_e = -7.0f;
            this.field_78149_c.field_78798_e = -7.0f;
            this.field_78144_f.field_78795_f = -1.5707964f;
            this.field_78147_e.field_78795_f = -1.5707964f;
            this.field_78144_f.field_78797_d = 21.0f;
            this.field_78147_e.field_78797_d = 21.0f;
            this.field_78144_f.field_78798_e = 1.0f;
            this.field_78147_e.field_78798_e = 1.0f;
            this.field_78163_i = 3;
        } else {
            this.field_78163_i = 1;
        }
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.field_78150_a.field_78795_f = par5 / 57.295776f;
        this.field_78150_a.field_78796_g = par4 / 57.295776f;
        if (this.field_78163_i != 3) {
            this.field_78148_b.field_78795_f = 1.5707964f;
            this.field_78147_e.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
            this.field_78144_f.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
            this.field_78149_c.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.0f * par2;
            this.field_78146_d.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.0f * par2;
        }
    }
}

