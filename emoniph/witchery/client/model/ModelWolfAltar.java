/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelWolfAltar
extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer bipedBody;
    public ModelRenderer wolfBody;
    public ModelRenderer wolfBody_1;
    public ModelRenderer shape37;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedRightArm;
    public ModelRenderer wolfHeadMain0;
    public ModelRenderer bipedRightLegLower;
    public ModelRenderer shaft;
    public ModelRenderer wolfHeadMain3;
    public ModelRenderer wolfHeadMain1;
    public ModelRenderer wolfHeadMain2;
    public ModelRenderer wolfMane;
    public ModelRenderer wolfHeadMain0_1;
    public ModelRenderer wolfTail;
    public ModelRenderer wolfLeg2;
    public ModelRenderer wolfLeg1;
    public ModelRenderer wolfLeg4;
    public ModelRenderer wolfLeg3;
    public ModelRenderer wolfHeadMain3_1;
    public ModelRenderer wolfHeadMain2_1;
    public ModelRenderer wolfHeadMain1_1;
    public ModelRenderer wolfMane_1;
    public ModelRenderer wolfHeadMain0_2;
    public ModelRenderer wolfTail_1;
    public ModelRenderer wolfLeg2_1;
    public ModelRenderer wolfLeg1_1;
    public ModelRenderer wolfLeg4_1;
    public ModelRenderer wolfLeg3_1;
    public ModelRenderer wolfHeadMain3_2;
    public ModelRenderer wolfHeadMain2_2;
    public ModelRenderer wolfHeadMain1_2;

    public ModelWolfAltar() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.wolfHeadMain1_2 = new ModelRenderer((ModelBase)this, 16, 14);
        this.wolfHeadMain1_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain1_2.func_78789_a(-3.0f, -4.5f, -10.0f, 2, 2, 1);
        this.bipedBody = new ModelRenderer((ModelBase)this, 16, 16);
        this.bipedBody.func_78793_a(0.0f, 3.1f, 0.0f);
        this.bipedBody.func_78789_a(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.wolfHeadMain2_2 = new ModelRenderer((ModelBase)this, 16, 14);
        this.wolfHeadMain2_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain2_2.func_78789_a(1.0f, -4.5f, -10.0f, 2, 2, 1);
        this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedRightLeg.field_78809_i = true;
        this.bipedRightLeg.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedRightLeg.func_78789_a(-4.0f, 7.4f, 8.0f, 4, 6, 4);
        this.setRotateAngle(this.bipedRightLeg, -1.0471976f, 0.0f, 0.0f);
        this.wolfMane = new ModelRenderer((ModelBase)this, 21, 0);
        this.wolfMane.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfMane.func_78789_a(-4.0f, -3.0f, -9.0f, 8, 7, 6);
        this.setRotateAngle(this.wolfMane, 0.0f, -0.13665928f, 0.0f);
        this.wolfMane_1 = new ModelRenderer((ModelBase)this, 21, 0);
        this.wolfMane_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfMane_1.func_78789_a(-4.0f, -3.0f, -9.0f, 8, 7, 6);
        this.bipedRightLegLower = new ModelRenderer((ModelBase)this, 18, 0);
        this.bipedRightLegLower.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedRightLegLower.func_78789_a(-4.0f, 15.7f, -6.2f, 4, 6, 4);
        this.setRotateAngle(this.bipedRightLegLower, 0.95609134f, 0.0f, 0.0f);
        this.wolfHeadMain3 = new ModelRenderer((ModelBase)this, 0, 10);
        this.wolfHeadMain3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain3.func_78789_a(-1.5f, -3.1f, -5.0f, 3, 3, 4);
        this.shape37 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape37.func_78793_a(-3.9f, 18.0f, -6.5f);
        this.shape37.func_78789_a(0.0f, 0.0f, 0.0f, 4, 2, 4);
        this.wolfLeg3 = new ModelRenderer((ModelBase)this, 0, 18);
        this.wolfLeg3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfLeg3.func_78789_a(-2.5f, 4.0f, -8.0f, 2, 8, 2);
        this.setRotateAngle(this.wolfLeg3, 0.0f, -0.091106184f, 0.0f);
        this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedRightArm.field_78809_i = true;
        this.bipedRightArm.func_78793_a(0.0f, 2.0f, 0.0f);
        this.bipedRightArm.func_78789_a(-8.0f, -2.0f, -2.0f, 4, 12, 4);
        this.setRotateAngle(this.bipedRightArm, -2.4586453f, 0.4098033f, 0.0f);
        this.shape1_2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_2.func_78793_a(-8.0f, 20.0f, -8.0f);
        this.shape1_2.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.wolfLeg2_1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.wolfLeg2_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfLeg2_1.func_78789_a(0.5f, 4.0f, 3.0f, 2, 8, 2);
        this.wolfHeadMain1_1 = new ModelRenderer((ModelBase)this, 16, 14);
        this.wolfHeadMain1_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain1_1.func_78789_a(-3.0f, -4.5f, -10.0f, 2, 2, 1);
        this.wolfLeg4 = new ModelRenderer((ModelBase)this, 0, 18);
        this.wolfLeg4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfLeg4.func_78789_a(0.5f, 4.0f, -8.0f, 2, 8, 2);
        this.setRotateAngle(this.wolfLeg4, 0.0f, -0.091106184f, 0.0f);
        this.shaft = new ModelRenderer((ModelBase)this, 0, 33);
        this.shaft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.shaft.func_78789_a(-6.5f, 8.0f, -17.9f, 1, 1, 40);
        this.wolfHeadMain0_2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.wolfHeadMain0_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain0_2.func_78789_a(-3.0f, -2.5f, -13.0f, 6, 6, 4);
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedLeftLeg.field_78809_i = true;
        this.bipedLeftLeg.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedLeftLeg.func_78789_a(0.0f, 12.0f, -2.0f, 4, 12, 4);
        this.shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1.func_78793_a(-8.0f, 22.0f, -8.0f);
        this.shape1.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 16);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedLeftArm.field_78809_i = true;
        this.bipedLeftArm.func_78793_a(0.0f, 2.0f, 0.0f);
        this.bipedLeftArm.func_78789_a(4.0f, -2.0f, -2.0f, 4, 12, 4);
        this.setRotateAngle(this.bipedLeftArm, -0.4553564f, -0.13665928f, 0.0f);
        this.wolfLeg4_1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.wolfLeg4_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfLeg4_1.func_78789_a(0.5f, 4.0f, -8.0f, 2, 8, 2);
        this.wolfHeadMain2_1 = new ModelRenderer((ModelBase)this, 16, 14);
        this.wolfHeadMain2_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain2_1.func_78789_a(1.0f, -4.5f, -10.0f, 2, 2, 1);
        this.wolfHeadMain3_2 = new ModelRenderer((ModelBase)this, 0, 10);
        this.wolfHeadMain3_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain3_2.func_78789_a(-1.5f, 0.5f, -17.1f, 3, 3, 4);
        this.wolfLeg1_1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.wolfLeg1_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfLeg1_1.func_78789_a(-2.5f, 4.0f, 3.0f, 2, 8, 2);
        this.wolfLeg3_1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.wolfLeg3_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfLeg3_1.func_78789_a(-2.5f, 4.0f, -8.0f, 2, 8, 2);
        this.wolfBody = new ModelRenderer((ModelBase)this, 18, 14);
        this.wolfBody.func_78793_a(-5.0f, 14.0f, 3.5f);
        this.wolfBody.func_78789_a(-3.0f, -2.0f, -3.0f, 6, 6, 9);
        this.setRotateAngle(this.wolfBody, 0.0f, 0.27314404f, 0.0f);
        this.wolfLeg2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.wolfLeg2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfLeg2.func_78789_a(0.5f, 4.0f, 3.0f, 2, 8, 2);
        this.shape1_1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_1.func_78793_a(-7.0f, 21.0f, -7.0f);
        this.shape1_1.func_78789_a(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.wolfTail = new ModelRenderer((ModelBase)this, 9, 18);
        this.wolfTail.func_78793_a(0.0f, 0.6f, 0.0f);
        this.wolfTail.func_78789_a(-1.0f, 0.0f, 4.0f, 2, 8, 2);
        this.setRotateAngle(this.wolfTail, 0.31869712f, 0.0f, 0.0f);
        this.wolfHeadMain3_1 = new ModelRenderer((ModelBase)this, 0, 10);
        this.wolfHeadMain3_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain3_1.func_78789_a(-1.5f, 0.5f, -17.1f, 3, 3, 4);
        this.wolfTail_1 = new ModelRenderer((ModelBase)this, 9, 18);
        this.wolfTail_1.func_78793_a(0.0f, 0.6f, 0.0f);
        this.wolfTail_1.func_78789_a(-1.0f, 0.0f, 4.0f, 2, 8, 2);
        this.setRotateAngle(this.wolfTail_1, 0.31869712f, 0.0f, 0.0f);
        this.wolfLeg1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.wolfLeg1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfLeg1.func_78789_a(-2.5f, 4.0f, 3.0f, 2, 8, 2);
        this.wolfHeadMain1 = new ModelRenderer((ModelBase)this, 16, 14);
        this.wolfHeadMain1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain1.func_78789_a(-3.0f, -8.0f, 2.0f, 2, 2, 1);
        this.wolfBody_1 = new ModelRenderer((ModelBase)this, 18, 14);
        this.wolfBody_1.func_78793_a(5.5f, 14.0f, -2.0f);
        this.wolfBody_1.func_78789_a(-3.0f, -2.0f, -3.0f, 6, 6, 9);
        this.setRotateAngle(this.wolfBody_1, 0.0f, 0.31869712f, 0.0f);
        this.wolfHeadMain2 = new ModelRenderer((ModelBase)this, 16, 14);
        this.wolfHeadMain2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain2.func_78789_a(1.0f, -8.0f, 2.0f, 2, 2, 1);
        this.wolfHeadMain0 = new ModelRenderer((ModelBase)this, 0, 42);
        this.wolfHeadMain0.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain0.func_78789_a(-3.0f, -6.0f, -2.0f, 6, 6, 5);
        this.setRotateAngle(this.wolfHeadMain0, 0.13665928f, 0.13665928f, 0.0f);
        this.wolfHeadMain0_1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.wolfHeadMain0_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.wolfHeadMain0_1.func_78789_a(-3.0f, -2.5f, -13.0f, 6, 6, 4);
        this.setRotateAngle(this.wolfHeadMain0_1, -0.22759093f, -0.22759093f, 0.0f);
        this.bipedBody.func_78792_a(this.bipedLeftLeg);
        this.wolfHeadMain0_1.func_78792_a(this.wolfHeadMain1_1);
        this.bipedRightLeg.func_78792_a(this.bipedRightLegLower);
        this.wolfHeadMain0.func_78792_a(this.wolfHeadMain3);
        this.wolfBody.func_78792_a(this.wolfHeadMain0_1);
        this.bipedBody.func_78792_a(this.wolfHeadMain0);
        this.wolfHeadMain0.func_78792_a(this.wolfHeadMain2);
        this.wolfHeadMain0.func_78792_a(this.wolfHeadMain1);
        this.wolfBody.func_78792_a(this.wolfLeg1);
        this.wolfBody_1.func_78792_a(this.wolfTail_1);
        this.wolfHeadMain0_1.func_78792_a(this.wolfHeadMain3_1);
        this.wolfBody.func_78792_a(this.wolfTail);
        this.wolfBody.func_78792_a(this.wolfLeg2);
        this.wolfBody_1.func_78792_a(this.wolfLeg3_1);
        this.wolfBody_1.func_78792_a(this.wolfLeg1_1);
        this.wolfHeadMain0_2.func_78792_a(this.wolfHeadMain3_2);
        this.wolfHeadMain0_1.func_78792_a(this.wolfHeadMain2_1);
        this.wolfBody_1.func_78792_a(this.wolfLeg4_1);
        this.bipedBody.func_78792_a(this.bipedLeftArm);
        this.wolfBody_1.func_78792_a(this.wolfHeadMain0_2);
        this.bipedRightArm.func_78792_a(this.shaft);
        this.wolfBody.func_78792_a(this.wolfLeg4);
        this.wolfBody_1.func_78792_a(this.wolfLeg2_1);
        this.bipedBody.func_78792_a(this.bipedRightArm);
        this.wolfBody.func_78792_a(this.wolfLeg3);
        this.wolfBody_1.func_78792_a(this.wolfMane_1);
        this.wolfBody.func_78792_a(this.wolfMane);
        this.bipedBody.func_78792_a(this.bipedRightLeg);
        this.wolfHeadMain0_2.func_78792_a(this.wolfHeadMain1_2);
        this.wolfHeadMain0_2.func_78792_a(this.wolfHeadMain2_2);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.bipedBody.field_82906_o, (float)this.bipedBody.field_82908_p, (float)this.bipedBody.field_82907_q);
        GL11.glTranslatef((float)(this.bipedBody.field_78800_c * f5), (float)(this.bipedBody.field_78797_d * f5), (float)(this.bipedBody.field_78798_e * f5));
        GL11.glScaled((double)0.7, (double)0.7, (double)0.7);
        GL11.glTranslatef((float)(-this.bipedBody.field_82906_o), (float)(-this.bipedBody.field_82908_p), (float)(-this.bipedBody.field_82907_q));
        GL11.glTranslatef((float)(-this.bipedBody.field_78800_c * f5), (float)(-this.bipedBody.field_78797_d * f5), (float)(-this.bipedBody.field_78798_e * f5));
        this.bipedBody.func_78785_a(f5);
        GL11.glPopMatrix();
        this.shape37.func_78785_a(f5);
        this.shape1_2.func_78785_a(f5);
        this.shape1.func_78785_a(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.wolfBody.field_82906_o, (float)this.wolfBody.field_82908_p, (float)this.wolfBody.field_82907_q);
        GL11.glTranslatef((float)(this.wolfBody.field_78800_c * f5), (float)(this.wolfBody.field_78797_d * f5), (float)(this.wolfBody.field_78798_e * f5));
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        GL11.glTranslatef((float)(-this.wolfBody.field_82906_o), (float)(-this.wolfBody.field_82908_p), (float)(-this.wolfBody.field_82907_q));
        GL11.glTranslatef((float)(-this.wolfBody.field_78800_c * f5), (float)(-this.wolfBody.field_78797_d * f5), (float)(-this.wolfBody.field_78798_e * f5));
        this.wolfBody.func_78785_a(f5);
        GL11.glPopMatrix();
        this.shape1_1.func_78785_a(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.wolfBody_1.field_82906_o, (float)this.wolfBody_1.field_82908_p, (float)this.wolfBody_1.field_82907_q);
        GL11.glTranslatef((float)(this.wolfBody_1.field_78800_c * f5), (float)(this.wolfBody_1.field_78797_d * f5), (float)(this.wolfBody_1.field_78798_e * f5));
        GL11.glScaled((double)0.5, (double)0.5, (double)0.5);
        GL11.glTranslatef((float)(-this.wolfBody_1.field_82906_o), (float)(-this.wolfBody_1.field_82908_p), (float)(-this.wolfBody_1.field_82907_q));
        GL11.glTranslatef((float)(-this.wolfBody_1.field_78800_c * f5), (float)(-this.wolfBody_1.field_78797_d * f5), (float)(-this.wolfBody_1.field_78798_e * f5));
        this.wolfBody_1.func_78785_a(f5);
        GL11.glPopMatrix();
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

