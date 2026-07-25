/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.util.MathHelper
 */
package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.MathHelper;

public class ModelFamiliar
extends ModelBase {
    ModelRenderer Body;
    ModelRenderer Tail1;
    ModelRenderer Tail2;
    ModelRenderer LegBackL;
    ModelRenderer Ear1;
    ModelRenderer Nose;
    ModelRenderer Main;
    ModelRenderer Ear2;
    ModelRenderer HatBuckle;
    ModelRenderer HatC;
    ModelRenderer HatB;
    ModelRenderer HatA;
    ModelRenderer HatBase;
    ModelRenderer LegBackR;
    ModelRenderer LegFrontL;
    ModelRenderer LegFrontR;
    int field_78163_i = 1;

    public ModelFamiliar() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Body = new ModelRenderer((ModelBase)this, 20, 0);
        this.Body.func_78789_a(-2.0f, 3.0f, -8.0f, 4, 16, 6);
        this.Body.func_78793_a(0.0f, 12.0f, -10.0f);
        this.Body.func_78787_b(64, 32);
        this.Body.field_78809_i = true;
        this.setRotation(this.Body, 1.570796f, 0.0f, 0.0f);
        this.Tail1 = new ModelRenderer((ModelBase)this, 0, 15);
        this.Tail1.func_78789_a(-0.5f, 0.0f, 0.0f, 1, 8, 1);
        this.Tail1.func_78793_a(0.0f, 15.0f, 8.0f);
        this.Tail1.func_78787_b(64, 32);
        this.Tail1.field_78809_i = true;
        this.setRotation(this.Tail1, 1.570796f, 0.0f, 0.0f);
        this.Tail2 = new ModelRenderer((ModelBase)this, 4, 15);
        this.Tail2.func_78789_a(-0.5f, 0.0f, 0.0f, 1, 8, 1);
        this.Tail2.func_78793_a(0.0f, 15.0f, 14.0f);
        this.Tail2.func_78787_b(64, 32);
        this.Tail2.field_78809_i = true;
        this.setRotation(this.Tail2, 1.570796f, 0.0f, 0.0f);
        this.LegBackL = new ModelRenderer((ModelBase)this, 8, 13);
        this.LegBackL.func_78789_a(-1.0f, 0.0f, 1.0f, 2, 6, 2);
        this.LegBackL.func_78793_a(1.1f, 18.0f, 5.0f);
        this.LegBackL.func_78787_b(64, 32);
        this.LegBackL.field_78809_i = true;
        this.setRotation(this.LegBackL, 0.0f, 0.0f, 0.0f);
        this.Ear1 = new ModelRenderer((ModelBase)this, 0, 10);
        this.Ear1.func_78789_a(-2.0f, -3.0f, 0.0f, 1, 1, 2);
        this.Ear1.func_78793_a(0.0f, 15.0f, -9.0f);
        this.Ear1.func_78787_b(64, 32);
        this.Ear1.field_78809_i = true;
        this.setRotation(this.Ear1, 0.0f, 0.0f, 0.0f);
        this.Nose = new ModelRenderer((ModelBase)this, 0, 24);
        this.Nose.func_78789_a(-1.5f, 0.0f, -4.0f, 3, 2, 2);
        this.Nose.func_78793_a(0.0f, 15.0f, -9.0f);
        this.Nose.func_78787_b(64, 32);
        this.Nose.field_78809_i = true;
        this.setRotation(this.Nose, 0.0f, 0.0f, 0.0f);
        this.Main = new ModelRenderer((ModelBase)this, 0, 0);
        this.Main.func_78789_a(-2.5f, -2.0f, -3.0f, 5, 4, 5);
        this.Main.func_78793_a(0.0f, 15.0f, -9.0f);
        this.Main.func_78787_b(64, 32);
        this.Main.field_78809_i = true;
        this.setRotation(this.Main, 0.0f, 0.0f, 0.0f);
        this.Ear2 = new ModelRenderer((ModelBase)this, 6, 10);
        this.Ear2.func_78789_a(1.0f, -3.0f, 0.0f, 1, 1, 2);
        this.Ear2.func_78793_a(0.0f, 15.0f, -9.0f);
        this.Ear2.func_78787_b(64, 32);
        this.Ear2.field_78809_i = true;
        this.setRotation(this.Ear2, 0.0f, 0.0f, 0.0f);
        this.HatBuckle = new ModelRenderer((ModelBase)this, 48, 13);
        this.HatBuckle.func_78789_a(-1.0f, -4.0f, -3.0f, 2, 1, 1);
        this.HatBuckle.func_78793_a(0.0f, 15.0f, -9.0f);
        this.HatBuckle.func_78787_b(64, 32);
        this.HatBuckle.field_78809_i = true;
        this.setRotation(this.HatBuckle, 0.0f, 0.0f, 0.0f);
        this.HatC = new ModelRenderer((ModelBase)this, 48, 11);
        this.HatC.func_78789_a(-0.5f, -7.0f, -1.0f, 1, 1, 1);
        this.HatC.func_78793_a(0.0f, 15.0f, -9.0f);
        this.HatC.func_78787_b(64, 32);
        this.HatC.field_78809_i = true;
        this.setRotation(this.HatC, 0.0f, 0.0f, 0.0f);
        this.HatB = new ModelRenderer((ModelBase)this, 48, 8);
        this.HatB.func_78789_a(-1.0f, -6.0f, -1.5f, 2, 1, 2);
        this.HatB.func_78793_a(0.0f, 15.0f, -9.0f);
        this.HatB.func_78787_b(64, 32);
        this.HatB.field_78809_i = true;
        this.setRotation(this.HatB, 0.0f, 0.0f, 0.0f);
        this.HatA = new ModelRenderer((ModelBase)this, 48, 0);
        this.HatA.func_78789_a(-1.5f, -5.0f, -2.0f, 3, 2, 3);
        this.HatA.func_78793_a(0.0f, 15.0f, -9.0f);
        this.HatA.func_78787_b(64, 32);
        this.HatA.field_78809_i = true;
        this.setRotation(this.HatA, 0.0f, 0.0f, 0.0f);
        this.HatBase = new ModelRenderer((ModelBase)this, 16, 24);
        this.HatBase.func_78789_a(-3.0f, -3.0f, -3.5f, 6, 1, 6);
        this.HatBase.func_78793_a(0.0f, 15.0f, -9.0f);
        this.HatBase.func_78787_b(64, 32);
        this.HatBase.field_78809_i = true;
        this.setRotation(this.HatBase, 0.0f, 0.0f, 0.0f);
        this.LegBackR = new ModelRenderer((ModelBase)this, 8, 13);
        this.LegBackR.func_78789_a(-1.0f, 0.0f, 1.0f, 2, 6, 2);
        this.LegBackR.func_78793_a(-1.1f, 18.0f, 5.0f);
        this.LegBackR.func_78787_b(64, 32);
        this.LegBackR.field_78809_i = true;
        this.setRotation(this.LegBackR, 0.0f, 0.0f, 0.0f);
        this.LegFrontL = new ModelRenderer((ModelBase)this, 40, 0);
        this.LegFrontL.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 10, 2);
        this.LegFrontL.func_78793_a(1.2f, 13.8f, -5.0f);
        this.LegFrontL.func_78787_b(64, 32);
        this.LegFrontL.field_78809_i = true;
        this.setRotation(this.LegFrontL, 0.0f, 0.0f, 0.0f);
        this.LegFrontR = new ModelRenderer((ModelBase)this, 40, 0);
        this.LegFrontR.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 10, 2);
        this.LegFrontR.func_78793_a(-1.2f, 13.8f, -5.0f);
        this.LegFrontR.func_78787_b(64, 32);
        this.LegFrontR.field_78809_i = true;
        this.setRotation(this.LegFrontR, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body.func_78785_a(f5);
        this.Tail1.func_78785_a(f5);
        this.Tail2.func_78785_a(f5);
        this.LegBackL.func_78785_a(f5);
        this.Ear1.func_78785_a(f5);
        this.Nose.func_78785_a(f5);
        this.Main.func_78785_a(f5);
        this.Ear2.func_78785_a(f5);
        this.HatBuckle.func_78785_a(f5);
        this.HatC.func_78785_a(f5);
        this.HatB.func_78785_a(f5);
        this.HatA.func_78785_a(f5);
        this.HatBase.func_78785_a(f5);
        this.LegBackR.func_78785_a(f5);
        this.LegFrontL.func_78785_a(f5);
        this.LegFrontR.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        this.Main.field_78795_f = p_78087_5_ / 57.295776f;
        this.Main.field_78796_g = p_78087_4_ / 57.295776f;
        this.Nose.field_78795_f = p_78087_5_ / 57.295776f;
        this.Nose.field_78796_g = p_78087_4_ / 57.295776f;
        this.Ear1.field_78795_f = p_78087_5_ / 57.295776f;
        this.Ear1.field_78796_g = p_78087_4_ / 57.295776f;
        this.Ear2.field_78795_f = p_78087_5_ / 57.295776f;
        this.Ear2.field_78796_g = p_78087_4_ / 57.295776f;
        this.HatA.field_78795_f = p_78087_5_ / 57.295776f;
        this.HatA.field_78796_g = p_78087_4_ / 57.295776f;
        this.HatB.field_78795_f = p_78087_5_ / 57.295776f;
        this.HatB.field_78796_g = p_78087_4_ / 57.295776f;
        this.HatC.field_78795_f = p_78087_5_ / 57.295776f;
        this.HatC.field_78796_g = p_78087_4_ / 57.295776f;
        this.HatBuckle.field_78795_f = p_78087_5_ / 57.295776f;
        this.HatBuckle.field_78796_g = p_78087_4_ / 57.295776f;
        this.HatBase.field_78795_f = p_78087_5_ / 57.295776f;
        this.HatBase.field_78796_g = p_78087_4_ / 57.295776f;
        if (this.field_78163_i != 3) {
            this.Body.field_78795_f = 1.5707964f;
            if (this.field_78163_i == 2) {
                this.LegBackL.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 1.0f * p_78087_2_;
                this.LegBackR.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + 0.3f)) * 1.0f * p_78087_2_;
                this.LegFrontL.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI + 0.3f)) * 1.0f * p_78087_2_;
                this.LegFrontR.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 1.0f * p_78087_2_;
                this.Tail2.field_78795_f = 1.7278761f + 0.31415927f * MathHelper.func_76134_b((float)p_78087_1_) * p_78087_2_;
            } else {
                this.LegBackL.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 1.0f * p_78087_2_;
                this.LegBackR.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 1.0f * p_78087_2_;
                this.LegFrontL.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 1.0f * p_78087_2_;
                this.LegFrontR.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 1.0f * p_78087_2_;
                this.Tail2.field_78795_f = this.field_78163_i == 1 ? 1.7278761f + 0.7853982f * MathHelper.func_76134_b((float)p_78087_1_) * p_78087_2_ : 1.7278761f + 0.47123894f * MathHelper.func_76134_b((float)p_78087_1_) * p_78087_2_;
            }
        }
    }

    public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        EntityOcelot entityocelot = (EntityOcelot)p_78086_1_;
        this.Body.field_78797_d = 12.0f;
        this.Body.field_78798_e = -10.0f;
        this.Main.field_78797_d = 15.0f;
        this.Main.field_78798_e = -9.0f;
        this.Nose.field_78797_d = 15.0f;
        this.Nose.field_78798_e = -9.0f;
        this.Ear1.field_78797_d = 15.0f;
        this.Ear1.field_78798_e = -9.0f;
        this.Ear2.field_78797_d = 15.0f;
        this.Ear2.field_78798_e = -9.0f;
        this.HatBase.field_78797_d = 15.0f;
        this.HatBase.field_78798_e = -9.0f;
        this.HatB.field_78797_d = 15.0f;
        this.HatB.field_78798_e = -9.0f;
        this.HatA.field_78797_d = 15.0f;
        this.HatA.field_78798_e = -9.0f;
        this.HatC.field_78797_d = 15.0f;
        this.HatC.field_78798_e = -9.0f;
        this.HatBuckle.field_78797_d = 15.0f;
        this.HatBuckle.field_78798_e = -9.0f;
        this.Tail1.field_78797_d = 15.0f;
        this.Tail1.field_78798_e = 8.0f;
        this.Tail2.field_78797_d = 20.0f;
        this.Tail2.field_78798_e = 14.0f;
        this.LegFrontR.field_78797_d = 13.8f;
        this.LegFrontL.field_78797_d = 13.8f;
        this.LegFrontR.field_78798_e = -5.0f;
        this.LegFrontL.field_78798_e = -5.0f;
        this.LegBackR.field_78797_d = 18.0f;
        this.LegBackL.field_78797_d = 18.0f;
        this.LegBackR.field_78798_e = 5.0f;
        this.LegBackL.field_78798_e = 5.0f;
        this.Tail1.field_78795_f = 0.9f;
        if (entityocelot.func_70093_af()) {
            this.Body.field_78797_d += 1.0f;
            this.Main.field_78797_d += 2.0f;
            this.Nose.field_78797_d += 2.0f;
            this.Ear1.field_78797_d += 2.0f;
            this.Ear2.field_78797_d += 2.0f;
            this.HatBase.field_78797_d += 2.0f;
            this.HatA.field_78797_d += 2.0f;
            this.HatB.field_78797_d += 2.0f;
            this.HatC.field_78797_d += 2.0f;
            this.HatBuckle.field_78797_d += 2.0f;
            this.Tail1.field_78797_d += 1.0f;
            this.Tail2.field_78797_d += -4.0f;
            this.Tail2.field_78798_e += 2.0f;
            this.Tail1.field_78795_f = 1.5707964f;
            this.Tail2.field_78795_f = 1.5707964f;
            this.field_78163_i = 0;
        } else if (entityocelot.func_70051_ag()) {
            this.Tail2.field_78797_d = this.Tail1.field_78797_d;
            this.Tail2.field_78798_e += 2.0f;
            this.Tail1.field_78795_f = 1.5707964f;
            this.Tail2.field_78795_f = 1.5707964f;
            this.field_78163_i = 2;
        } else if (entityocelot.func_70906_o()) {
            this.Body.field_78795_f = 0.7853982f;
            this.Body.field_78797_d += -4.0f;
            this.Body.field_78798_e += 5.0f;
            this.Main.field_78797_d += -3.3f;
            this.Main.field_78798_e += 1.0f;
            this.Nose.field_78797_d += -3.3f;
            this.Nose.field_78798_e += 1.0f;
            this.Ear1.field_78797_d += -3.3f;
            this.Ear1.field_78798_e += 1.0f;
            this.Ear2.field_78797_d += -3.3f;
            this.Ear2.field_78798_e += 1.0f;
            this.HatBase.field_78797_d += -3.3f;
            this.HatBase.field_78798_e += 1.0f;
            this.HatA.field_78797_d += -3.3f;
            this.HatA.field_78798_e += 1.0f;
            this.HatB.field_78797_d += -3.3f;
            this.HatB.field_78798_e += 1.0f;
            this.HatC.field_78797_d += -3.3f;
            this.HatC.field_78798_e += 1.0f;
            this.HatBuckle.field_78797_d += -3.3f;
            this.HatBuckle.field_78798_e += 1.0f;
            this.Tail1.field_78797_d += 8.0f;
            this.Tail1.field_78798_e += -2.0f;
            this.Tail2.field_78797_d += 2.0f;
            this.Tail2.field_78798_e += -0.8f;
            this.Tail1.field_78795_f = 1.7278761f;
            this.Tail2.field_78795_f = 2.670354f;
            this.LegFrontR.field_78795_f = -0.15707964f;
            this.LegFrontL.field_78795_f = -0.15707964f;
            this.LegFrontR.field_78797_d = 15.8f;
            this.LegFrontL.field_78797_d = 15.8f;
            this.LegFrontR.field_78798_e = -7.0f;
            this.LegFrontL.field_78798_e = -7.0f;
            this.LegBackR.field_78795_f = -1.5707964f;
            this.LegBackL.field_78795_f = -1.5707964f;
            this.LegBackR.field_78797_d = 21.0f;
            this.LegBackL.field_78797_d = 21.0f;
            this.LegBackR.field_78798_e = 1.0f;
            this.LegBackL.field_78798_e = 1.0f;
            this.field_78163_i = 3;
        } else {
            this.field_78163_i = 1;
        }
    }
}

