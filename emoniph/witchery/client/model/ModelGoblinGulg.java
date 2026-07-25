/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityGoblinGulg;
import com.emoniph.witchery.util.Config;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ModelGoblinGulg
extends ModelBase {
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedHead;
    public ModelRenderer bipedChest;
    public int heldItemLeft;
    public int heldItemRight;
    public boolean isSneak;
    public boolean aimedBow;
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public ModelGoblinGulg() {
        this(0.0f);
    }

    public ModelGoblinGulg(float f) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.func_78085_a("bipedHead.face", 0, 0);
        this.func_78085_a("bipedHead.tuskright", 0, 4);
        this.func_78085_a("bipedHead.tuskleft", 0, 4);
        this.func_78085_a("bipedHead.nose", 25, 0);
        this.func_78085_a("bipedHead.lip", 34, 0);
        this.bipedBody = new ModelRenderer((ModelBase)this, 16, 16);
        this.bipedBody.func_78789_a(-4.0f, 0.0f, -2.0f, 8, 4, 4);
        this.bipedBody.func_78793_a(0.0f, 8.0f, 0.0f);
        this.bipedBody.func_78787_b(64, 64);
        this.bipedBody.field_78809_i = true;
        this.setRotation(this.bipedBody, 0.0f, 0.0f, 0.0f);
        this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 14);
        this.bipedRightArm.func_78789_a(-3.0f, -2.0f, -2.0f, 4, 16, 4);
        this.bipedRightArm.func_78793_a(-6.0f, 2.0f, 0.0f);
        this.bipedRightArm.func_78787_b(64, 64);
        this.bipedRightArm.field_78809_i = true;
        this.setRotation(this.bipedRightArm, 0.0f, 0.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 14);
        this.bipedLeftArm.func_78789_a(0.0f, -2.0f, -2.0f, 4, 16, 4);
        this.bipedLeftArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.bipedLeftArm.func_78787_b(64, 64);
        this.bipedLeftArm.field_78809_i = true;
        this.setRotation(this.bipedLeftArm, 0.0f, 0.0f, 0.0f);
        this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedRightLeg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.bipedRightLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.bipedRightLeg.func_78787_b(64, 64);
        this.bipedRightLeg.field_78809_i = true;
        this.setRotation(this.bipedRightLeg, 0.0f, 0.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedLeftLeg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.bipedLeftLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.bipedLeftLeg.func_78787_b(64, 64);
        this.bipedLeftLeg.field_78809_i = true;
        this.setRotation(this.bipedLeftLeg, 0.0f, 0.0f, 0.0f);
        this.bipedHead = new ModelRenderer((ModelBase)this, "bipedHead");
        this.bipedHead.func_78793_a(0.0f, 0.0f, 0.0f);
        this.setRotation(this.bipedHead, 0.0f, 0.0f, 0.0f);
        this.bipedHead.field_78809_i = true;
        this.bipedHead.func_78786_a("face", -4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.bipedHead.func_78786_a("tuskright", -2.0f, -4.0f, -5.0f, 1, 2, 1);
        this.bipedHead.func_78786_a("tuskleft", 1.0f, -4.0f, -5.0f, 1, 2, 1);
        this.bipedHead.func_78786_a("nose", -1.0f, -6.0f, -6.0f, 2, 3, 2);
        this.bipedHead.func_78786_a("lip", -2.0f, -2.0f, -6.0f, 4, 1, 2);
        this.bipedChest = new ModelRenderer((ModelBase)this, 12, 35);
        this.bipedChest.func_78789_a(-5.0f, 0.0f, -3.0f, 10, 8, 6);
        this.bipedChest.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedChest.func_78787_b(64, 64);
        this.bipedChest.field_78809_i = true;
        this.setRotation(this.bipedChest, 0.0f, 0.0f, 0.0f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, entity);
        this.doRender(par7);
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71474_y.field_74347_j && Config.instance().renderHuntsmanGlintEffect) {
            float f9 = entity.field_70173_aa;
            mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
            GL11.glEnable((int)3042);
            float f10 = 0.5f;
            GL11.glColor4f((float)f10, (float)f10, (float)f10, (float)1.0f);
            GL11.glDepthFunc((int)514);
            GL11.glDepthMask((boolean)false);
            for (int k = 0; k < 2; ++k) {
                GL11.glDisable((int)2896);
                float f11 = 0.76f;
                GL11.glColor4f((float)(0.2f * f11), (float)(0.7f * f11), (float)(0.7f * f11), (float)1.0f);
                GL11.glBlendFunc((int)768, (int)1);
                GL11.glMatrixMode((int)5890);
                GL11.glLoadIdentity();
                float f12 = f9 * (0.001f + (float)k * 0.003f) * 20.0f;
                float f13 = 0.33333334f;
                GL11.glScalef((float)f13, (float)f13, (float)f13);
                GL11.glRotatef((float)(30.0f - (float)k * 60.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)0.0f, (float)f12, (float)0.0f);
                GL11.glMatrixMode((int)5888);
                this.doRender(par7);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glDepthMask((boolean)true);
            GL11.glLoadIdentity();
            GL11.glMatrixMode((int)5888);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glDepthFunc((int)515);
        }
    }

    private void doRender(float par7) {
        if (this.field_78091_s) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * par7), (float)0.0f);
            this.bipedHead.func_78785_a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * par7), (float)0.0f);
            this.bipedBody.func_78785_a(par7);
            this.bipedChest.func_78785_a(par7);
            this.bipedRightArm.func_78785_a(par7);
            this.bipedLeftArm.func_78785_a(par7);
            this.bipedRightLeg.func_78785_a(par7);
            this.bipedLeftLeg.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.bipedHead.func_78785_a(par7);
            this.bipedChest.func_78785_a(par7);
            this.bipedBody.func_78785_a(par7);
            this.bipedRightArm.func_78785_a(par7);
            this.bipedLeftArm.func_78785_a(par7);
            this.bipedRightLeg.func_78785_a(par7);
            this.bipedLeftLeg.func_78785_a(par7);
        }
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        EntityGoblinGulg entityDemon;
        int i;
        float f7;
        float f6;
        this.bipedHead.field_78796_g = par4 / 57.295776f;
        this.bipedHead.field_78795_f = par5 / 57.295776f;
        this.bipedRightArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.bipedLeftArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.bipedRightArm.field_78808_h = 0.0f;
        this.bipedLeftArm.field_78808_h = 0.0f;
        this.bipedRightLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.bipedLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.bipedRightLeg.field_78796_g = 0.0f;
        this.bipedLeftLeg.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.bipedRightArm.field_78795_f += -0.62831855f;
            this.bipedLeftArm.field_78795_f += -0.62831855f;
            this.bipedRightLeg.field_78795_f = -1.2566371f;
            this.bipedLeftLeg.field_78795_f = -1.2566371f;
            this.bipedRightLeg.field_78796_g = 0.31415927f;
            this.bipedLeftLeg.field_78796_g = -0.31415927f;
        }
        if (this.heldItemLeft != 0) {
            this.bipedLeftArm.field_78795_f = this.bipedLeftArm.field_78795_f * 0.5f - 0.31415927f * (float)this.heldItemLeft;
        }
        if (this.heldItemRight != 0) {
            this.bipedRightArm.field_78795_f = this.bipedRightArm.field_78795_f * 0.5f - 0.31415927f * (float)this.heldItemRight;
        }
        this.bipedRightArm.field_78796_g = 0.0f;
        this.bipedLeftArm.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            f6 = this.field_78095_p;
            this.bipedBody.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f6) * (float)Math.PI * 2.0f)) * 0.2f;
            this.bipedRightArm.field_78798_e = MathHelper.func_76126_a((float)this.bipedBody.field_78796_g) * 5.0f;
            this.bipedRightArm.field_78800_c = -MathHelper.func_76134_b((float)this.bipedBody.field_78796_g) * 5.0f;
            this.bipedLeftArm.field_78798_e = -MathHelper.func_76126_a((float)this.bipedBody.field_78796_g) * 5.0f;
            this.bipedLeftArm.field_78800_c = MathHelper.func_76134_b((float)this.bipedBody.field_78796_g) * 5.0f;
            this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g;
            this.bipedLeftArm.field_78796_g += this.bipedBody.field_78796_g;
            this.bipedLeftArm.field_78795_f += this.bipedBody.field_78796_g;
            f6 = 1.0f - this.field_78095_p;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            f7 = MathHelper.func_76126_a((float)(f6 * (float)Math.PI));
            float f8 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.bipedHead.field_78795_f - 0.7f) * 0.75f;
            this.bipedRightArm.field_78795_f = (float)((double)this.bipedRightArm.field_78795_f - ((double)f7 * 1.2 + (double)f8));
            this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g * 2.0f;
            this.bipedRightArm.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        if (this.isSneak) {
            this.bipedBody.field_78795_f = 0.5f;
            this.bipedRightArm.field_78795_f += 0.4f;
            this.bipedLeftArm.field_78795_f += 0.4f;
            this.bipedRightLeg.field_78798_e = 4.0f;
            this.bipedLeftLeg.field_78798_e = 4.0f;
            this.bipedRightLeg.field_78797_d = 9.0f;
            this.bipedLeftLeg.field_78797_d = 9.0f;
            this.bipedHead.field_78797_d = 1.0f;
        } else {
            this.bipedBody.field_78795_f = 0.0f;
            this.bipedRightLeg.field_78798_e = 0.1f;
            this.bipedLeftLeg.field_78798_e = 0.1f;
            this.bipedRightLeg.field_78797_d = 12.0f;
            this.bipedLeftLeg.field_78797_d = 12.0f;
            this.bipedHead.field_78797_d = 0.0f;
        }
        this.bipedRightArm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedRightArm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        if (this.aimedBow) {
            f6 = 0.0f;
            f7 = 0.0f;
            this.bipedRightArm.field_78808_h = 0.0f;
            this.bipedLeftArm.field_78808_h = 0.0f;
            this.bipedRightArm.field_78796_g = -(0.1f - f6 * 0.6f) + this.bipedHead.field_78796_g;
            this.bipedLeftArm.field_78796_g = 0.1f - f6 * 0.6f + this.bipedHead.field_78796_g + 0.4f;
            this.bipedRightArm.field_78795_f = -1.5707964f + this.bipedHead.field_78795_f;
            this.bipedLeftArm.field_78795_f = -1.5707964f + this.bipedHead.field_78795_f;
            this.bipedRightArm.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.bipedLeftArm.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.bipedRightArm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.bipedRightArm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
        if ((i = (entityDemon = (EntityGoblinGulg)entity).getAttackTimer()) > 0) {
            this.bipedRightArm.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)i - par4, 10.0f);
        }
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

