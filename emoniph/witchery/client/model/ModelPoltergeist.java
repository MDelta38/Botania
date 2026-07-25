/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityPoltergeist;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelPoltergeist
extends ModelBase {
    ModelRenderer bipedHead;
    ModelRenderer bipedBody;
    ModelRenderer bipedRightArm;
    ModelRenderer bipedRightArm2;
    ModelRenderer bipedLeftArm;
    ModelRenderer bipedLeftArm2;
    ModelRenderer bipedRightLeg;
    ModelRenderer bipedLeftLeg;

    public ModelPoltergeist() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.bipedHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.bipedHead.func_78789_a(-4.0f, -8.0f, -3.0f, 8, 8, 6);
        this.bipedHead.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedHead.func_78787_b(64, 32);
        this.bipedHead.field_78809_i = true;
        this.setRotation(this.bipedHead, 0.0f, 0.0f, 0.0f);
        this.bipedBody = new ModelRenderer((ModelBase)this, 16, 16);
        this.bipedBody.func_78789_a(-4.0f, 0.0f, -1.0f, 8, 11, 2);
        this.bipedBody.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedBody.func_78787_b(64, 32);
        this.bipedBody.field_78809_i = true;
        this.setRotation(this.bipedBody, 0.0f, 0.0f, 0.0f);
        this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 0);
        this.bipedRightArm.func_78789_a(-1.0f, -2.0f, -1.0f, 2, 18, 2);
        this.bipedRightArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.bipedRightArm.func_78787_b(64, 32);
        this.bipedRightArm.field_78809_i = true;
        this.setRotation(this.bipedRightArm, 0.0f, 0.0f, 0.0f);
        this.bipedRightArm2 = new ModelRenderer((ModelBase)this, 40, 0);
        this.bipedRightArm2.func_78789_a(-1.0f, -2.0f, -1.0f, 2, 18, 2);
        this.bipedRightArm2.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.bipedRightArm2.func_78787_b(64, 32);
        this.bipedRightArm2.field_78809_i = true;
        this.setRotation(this.bipedRightArm2, 0.0f, 0.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 0);
        this.bipedLeftArm.func_78789_a(-1.0f, -2.0f, -1.0f, 2, 18, 2);
        this.bipedLeftArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.bipedLeftArm.func_78787_b(64, 32);
        this.bipedLeftArm.field_78809_i = true;
        this.setRotation(this.bipedLeftArm, 0.0f, 0.0f, 0.0f);
        this.bipedLeftArm2 = new ModelRenderer((ModelBase)this, 40, 0);
        this.bipedLeftArm2.func_78789_a(-1.0f, -2.0f, -1.0f, 2, 18, 2);
        this.bipedLeftArm2.func_78793_a(5.0f, 2.0f, 0.0f);
        this.bipedLeftArm2.func_78787_b(64, 32);
        this.bipedLeftArm2.field_78809_i = true;
        this.setRotation(this.bipedLeftArm2, 0.0f, 0.0f, 0.0f);
        this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedRightLeg.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 13, 2);
        this.bipedRightLeg.func_78793_a(-2.0f, 11.0f, 0.0f);
        this.bipedRightLeg.func_78787_b(64, 32);
        this.bipedRightLeg.field_78809_i = true;
        this.setRotation(this.bipedRightLeg, 0.0f, 0.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedLeftLeg.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 13, 2);
        this.bipedLeftLeg.func_78793_a(2.0f, 11.0f, 0.0f);
        this.bipedLeftLeg.func_78787_b(64, 32);
        this.bipedLeftLeg.field_78809_i = true;
        this.setRotation(this.bipedLeftLeg, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.bipedHead.func_78785_a(f5);
        this.bipedBody.func_78785_a(f5);
        this.bipedRightArm.func_78785_a(f5);
        this.bipedRightArm2.func_78785_a(f5);
        this.bipedLeftArm.func_78785_a(f5);
        this.bipedLeftArm2.func_78785_a(f5);
        this.bipedRightLeg.func_78785_a(f5);
        this.bipedLeftLeg.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        super.func_78087_a(par1, par2, par3, par4, par5, par6, entity);
        this.bipedHead.field_78796_g = par4 / 57.295776f;
        this.bipedHead.field_78795_f = par5 / 57.295776f;
        this.bipedRightArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.bipedRightArm2.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.25f;
        this.bipedLeftArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.bipedLeftArm2.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.25f;
        this.bipedRightArm.field_78796_g = 0.0f;
        this.bipedRightArm2.field_78796_g = 0.0f;
        this.bipedLeftArm.field_78796_g = 0.0f;
        this.bipedLeftArm2.field_78796_g = 0.0f;
        this.bipedRightArm.field_78808_h = 0.0f;
        this.bipedRightArm2.field_78808_h = 0.0f;
        this.bipedLeftArm.field_78808_h = 0.0f;
        this.bipedLeftArm2.field_78808_h = 0.0f;
        this.bipedRightLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.bipedLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.bipedRightLeg.field_78796_g = 0.0f;
        this.bipedLeftLeg.field_78796_g = 0.0f;
        this.bipedBody.field_78795_f = 0.0f;
        this.bipedRightLeg.field_78798_e = 0.1f;
        this.bipedLeftLeg.field_78798_e = 0.1f;
        this.bipedRightLeg.field_78797_d = 12.0f;
        this.bipedLeftLeg.field_78797_d = 12.0f;
        this.bipedHead.field_78797_d = 0.0f;
        this.bipedRightArm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedRightArm2.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedLeftArm2.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedRightArm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.bipedRightArm2.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.bipedLeftArm2.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        EntityPoltergeist entityDemon = (EntityPoltergeist)entity;
        int i = entityDemon.getAttackTimer();
        if (i > 0) {
            this.bipedRightArm.field_78795_f = -1.5f + 0.8f * this.func_78172_a((float)i - par4, 15.0f);
            this.bipedLeftArm.field_78795_f = -1.5f + 0.8f * this.func_78172_a((float)i - par4, 15.0f);
            this.bipedRightArm2.field_78795_f = -1.5f + 0.8f * this.func_78172_a((float)i - par4, 15.0f);
            this.bipedLeftArm2.field_78795_f = -1.5f + 0.8f * this.func_78172_a((float)i - par4, 15.0f);
            this.bipedRightArm.field_78808_h = -(-1.5f + 1.5f * this.func_78172_a((float)i - par4, 15.0f));
            this.bipedLeftArm.field_78808_h = -1.5f + 1.5f * this.func_78172_a((float)i - par4, 15.0f);
        }
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

