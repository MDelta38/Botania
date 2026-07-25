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
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelGuardianPanther
extends ModelBase {
    ModelRenderer ocelotBackLeftLeg;
    ModelRenderer ocelotBackRightLeg;
    ModelRenderer ocelotFrontLeftLeg;
    ModelRenderer ocelotFrontRightLeg;
    ModelRenderer ocelotTail;
    ModelRenderer ocelotTail2;
    ModelRenderer ocelotHead;
    ModelRenderer ocelotBody;
    int field_78163_i = 1;
    private static final String __OBFID = "CL_00000848";

    public ModelGuardianPanther() {
        this.func_78085_a("head.main", 0, 0);
        this.func_78085_a("head.nose", 0, 24);
        this.func_78085_a("head.ear1", 0, 10);
        this.func_78085_a("head.ear2", 6, 10);
        this.ocelotHead = new ModelRenderer((ModelBase)this, "head");
        this.ocelotHead.func_78786_a("main", -5.0f, -4.0f, -6.0f, 10, 8, 10);
        this.ocelotHead.func_78786_a("nose", -3.0f, 0.0f, -8.0f, 6, 4, 4);
        this.ocelotHead.func_78786_a("ear1", -4.0f, -6.0f, 0.0f, 2, 2, 4);
        this.ocelotHead.func_78786_a("ear2", 2.0f, -6.0f, 0.0f, 2, 2, 4);
        this.ocelotHead.func_78793_a(0.0f, 30.0f, -18.0f);
        this.ocelotBody = new ModelRenderer((ModelBase)this, 20, 0);
        this.ocelotBody.func_78790_a(-4.0f, 6.0f, -16.0f, 8, 32, 12, 0.0f);
        this.ocelotBody.func_78793_a(0.0f, 24.0f, -20.0f);
        this.ocelotTail = new ModelRenderer((ModelBase)this, 0, 15);
        this.ocelotTail.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 16, 2);
        this.ocelotTail.field_78795_f = 0.9f;
        this.ocelotTail.func_78793_a(0.0f, 30.0f, 16.0f);
        this.ocelotTail2 = new ModelRenderer((ModelBase)this, 4, 15);
        this.ocelotTail2.func_78789_a(-1.0f, 0.0f, 0.0f, 2, 16, 2);
        this.ocelotTail2.func_78793_a(0.0f, 40.0f, 28.0f);
        this.ocelotBackLeftLeg = new ModelRenderer((ModelBase)this, 8, 13);
        this.ocelotBackLeftLeg.func_78789_a(-2.0f, 0.0f, 2.0f, 4, 12, 4);
        this.ocelotBackLeftLeg.func_78793_a(2.2f, 36.0f, 10.0f);
        this.ocelotBackRightLeg = new ModelRenderer((ModelBase)this, 8, 13);
        this.ocelotBackRightLeg.func_78789_a(-2.0f, 0.0f, 2.0f, 4, 12, 4);
        this.ocelotBackRightLeg.func_78793_a(-2.2f, 36.0f, 10.0f);
        this.ocelotFrontLeftLeg = new ModelRenderer((ModelBase)this, 40, 0);
        this.ocelotFrontLeftLeg.func_78789_a(-2.0f, 0.0f, 0.0f, 4, 20, 4);
        this.ocelotFrontLeftLeg.func_78793_a(2.4f, 27.6f, -10.0f);
        this.ocelotFrontRightLeg = new ModelRenderer((ModelBase)this, 40, 0);
        this.ocelotFrontRightLeg.func_78789_a(-1.0f, 0.0f, 0.0f, 4, 10, 4);
        this.ocelotFrontRightLeg.func_78793_a(-2.4f, 27.6f, -10.0f);
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        if (this.field_78091_s) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)(20.0f * p_78088_7_), (float)(8.0f * p_78088_7_));
            this.ocelotHead.func_78785_a(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(48.0f * p_78088_7_), (float)0.0f);
            this.ocelotBody.func_78785_a(p_78088_7_);
            this.ocelotBackLeftLeg.func_78785_a(p_78088_7_);
            this.ocelotBackRightLeg.func_78785_a(p_78088_7_);
            this.ocelotFrontLeftLeg.func_78785_a(p_78088_7_);
            this.ocelotFrontRightLeg.func_78785_a(p_78088_7_);
            this.ocelotTail.func_78785_a(p_78088_7_);
            this.ocelotTail2.func_78785_a(p_78088_7_);
            GL11.glPopMatrix();
        } else {
            this.ocelotHead.func_78785_a(p_78088_7_);
            this.ocelotBody.func_78785_a(p_78088_7_);
            this.ocelotTail.func_78785_a(p_78088_7_);
            this.ocelotTail2.func_78785_a(p_78088_7_);
            this.ocelotBackLeftLeg.func_78785_a(p_78088_7_);
            this.ocelotBackRightLeg.func_78785_a(p_78088_7_);
            this.ocelotFrontLeftLeg.func_78785_a(p_78088_7_);
            this.ocelotFrontRightLeg.func_78785_a(p_78088_7_);
        }
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        this.ocelotHead.field_78795_f = p_78087_5_ / 57.295776f;
        this.ocelotHead.field_78796_g = p_78087_4_ / 57.295776f;
        if (this.field_78163_i != 3) {
            this.ocelotBody.field_78795_f = 1.5707964f;
            if (this.field_78163_i == 2) {
                this.ocelotBackLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 1.0f * p_78087_2_;
                this.ocelotBackRightLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + 0.3f)) * 1.0f * p_78087_2_;
                this.ocelotFrontLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI + 0.3f)) * 1.0f * p_78087_2_;
                this.ocelotFrontRightLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 1.0f * p_78087_2_;
                this.ocelotTail2.field_78795_f = 1.7278761f + 0.31415927f * MathHelper.func_76134_b((float)p_78087_1_) * p_78087_2_;
            } else {
                this.ocelotBackLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 1.0f * p_78087_2_;
                this.ocelotBackRightLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 1.0f * p_78087_2_;
                this.ocelotFrontLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 1.0f * p_78087_2_;
                this.ocelotFrontRightLeg.field_78795_f = MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 1.0f * p_78087_2_;
                this.ocelotTail2.field_78795_f = this.field_78163_i == 1 ? 1.7278761f + 0.7853982f * MathHelper.func_76134_b((float)p_78087_1_) * p_78087_2_ : 1.7278761f + 0.47123894f * MathHelper.func_76134_b((float)p_78087_1_) * p_78087_2_;
            }
        }
    }

    public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        EntityOcelot entityocelot = (EntityOcelot)p_78086_1_;
        this.ocelotBody.field_78797_d = 12.0f;
        this.ocelotBody.field_78798_e = -10.0f;
        this.ocelotHead.field_78797_d = 15.0f;
        this.ocelotHead.field_78798_e = -9.0f;
        this.ocelotTail.field_78797_d = 15.0f;
        this.ocelotTail.field_78798_e = 8.0f;
        this.ocelotTail2.field_78797_d = 20.0f;
        this.ocelotTail2.field_78798_e = 14.0f;
        this.ocelotFrontRightLeg.field_78797_d = 13.8f;
        this.ocelotFrontLeftLeg.field_78797_d = 13.8f;
        this.ocelotFrontRightLeg.field_78798_e = -5.0f;
        this.ocelotFrontLeftLeg.field_78798_e = -5.0f;
        this.ocelotBackRightLeg.field_78797_d = 18.0f;
        this.ocelotBackLeftLeg.field_78797_d = 18.0f;
        this.ocelotBackRightLeg.field_78798_e = 5.0f;
        this.ocelotBackLeftLeg.field_78798_e = 5.0f;
        this.ocelotTail.field_78795_f = 0.9f;
        if (entityocelot.func_70093_af()) {
            this.ocelotBody.field_78797_d += 1.0f;
            this.ocelotHead.field_78797_d += 2.0f;
            this.ocelotTail.field_78797_d += 1.0f;
            this.ocelotTail2.field_78797_d += -4.0f;
            this.ocelotTail2.field_78798_e += 2.0f;
            this.ocelotTail.field_78795_f = 1.5707964f;
            this.ocelotTail2.field_78795_f = 1.5707964f;
            this.field_78163_i = 0;
        } else if (entityocelot.func_70051_ag()) {
            this.ocelotTail2.field_78797_d = this.ocelotTail.field_78797_d;
            this.ocelotTail2.field_78798_e += 2.0f;
            this.ocelotTail.field_78795_f = 1.5707964f;
            this.ocelotTail2.field_78795_f = 1.5707964f;
            this.field_78163_i = 2;
        } else if (entityocelot.func_70906_o()) {
            this.ocelotBody.field_78795_f = 0.7853982f;
            this.ocelotBody.field_78797_d += -4.0f;
            this.ocelotBody.field_78798_e += 5.0f;
            this.ocelotHead.field_78797_d += -3.3f;
            this.ocelotHead.field_78798_e += 1.0f;
            this.ocelotTail.field_78797_d += 8.0f;
            this.ocelotTail.field_78798_e += -2.0f;
            this.ocelotTail2.field_78797_d += 2.0f;
            this.ocelotTail2.field_78798_e += -0.8f;
            this.ocelotTail.field_78795_f = 1.7278761f;
            this.ocelotTail2.field_78795_f = 2.670354f;
            this.ocelotFrontRightLeg.field_78795_f = -0.15707964f;
            this.ocelotFrontLeftLeg.field_78795_f = -0.15707964f;
            this.ocelotFrontRightLeg.field_78797_d = 15.8f;
            this.ocelotFrontLeftLeg.field_78797_d = 15.8f;
            this.ocelotFrontRightLeg.field_78798_e = -7.0f;
            this.ocelotFrontLeftLeg.field_78798_e = -7.0f;
            this.ocelotBackRightLeg.field_78795_f = -1.5707964f;
            this.ocelotBackLeftLeg.field_78795_f = -1.5707964f;
            this.ocelotBackRightLeg.field_78797_d = 21.0f;
            this.ocelotBackLeftLeg.field_78797_d = 21.0f;
            this.ocelotBackRightLeg.field_78798_e = 1.0f;
            this.ocelotBackLeftLeg.field_78798_e = 1.0f;
            this.field_78163_i = 3;
        } else {
            this.field_78163_i = 1;
        }
    }
}

