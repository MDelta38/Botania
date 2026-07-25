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
public class ModelGarlicGarland
extends ModelBase {
    public ModelRenderer garlicC;
    public ModelRenderer garlicA;
    public ModelRenderer garlicE;
    public ModelRenderer garlicD;
    public ModelRenderer garlicB;
    public ModelRenderer string3;
    public ModelRenderer string4;
    public ModelRenderer string1;
    public ModelRenderer string2;
    public ModelRenderer garlic1;
    public ModelRenderer garlic2;
    public ModelRenderer garlic3;
    public ModelRenderer garlic4;
    public ModelRenderer garlic1_1;
    public ModelRenderer garlic2_1;
    public ModelRenderer garlic3_1;
    public ModelRenderer garlic4_1;
    public ModelRenderer garlic1_2;
    public ModelRenderer garlic2_2;
    public ModelRenderer garlic3_2;
    public ModelRenderer garlic4_2;
    public ModelRenderer garlic1_3;
    public ModelRenderer garlic2_3;
    public ModelRenderer garlic3_3;
    public ModelRenderer garlic4_3;
    public ModelRenderer garlic1_4;
    public ModelRenderer garlic2_4;
    public ModelRenderer garlic3_4;
    public ModelRenderer garlic4_4;

    public ModelGarlicGarland() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.garlic4 = new ModelRenderer((ModelBase)this, 0, 23);
        this.garlic4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic4.func_78790_a(-2.0f, 7.0f, -2.0f, 4, 1, 4, 0.0f);
        this.garlic3 = new ModelRenderer((ModelBase)this, 0, 13);
        this.garlic3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic3.func_78790_a(-3.5f, 4.0f, -3.5f, 7, 3, 7, 0.0f);
        this.garlicC = new ModelRenderer((ModelBase)this, 0, 0);
        this.garlicC.func_78793_a(0.0f, 0.0f, 7.0f);
        this.garlicC.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 2, 1, 0.0f);
        this.garlic1_2 = new ModelRenderer((ModelBase)this, 0, 3);
        this.garlic1_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic1_2.func_78790_a(-1.5f, 2.0f, -1.5f, 3, 1, 3, 0.0f);
        this.garlicD = new ModelRenderer((ModelBase)this, 0, 0);
        this.garlicD.func_78793_a(2.5f, 1.5f, 7.0f);
        this.garlicD.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 2, 1, 0.0f);
        this.garlic3_4 = new ModelRenderer((ModelBase)this, 0, 13);
        this.garlic3_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic3_4.func_78790_a(-3.5f, 4.0f, -3.5f, 7, 3, 7, 0.0f);
        this.garlic1 = new ModelRenderer((ModelBase)this, 0, 3);
        this.garlic1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic1.func_78790_a(-1.5f, 2.0f, -1.5f, 3, 1, 3, 0.0f);
        this.garlic1_3 = new ModelRenderer((ModelBase)this, 0, 3);
        this.garlic1_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic1_3.func_78790_a(-1.5f, 2.0f, -1.5f, 3, 1, 3, 0.0f);
        this.garlic4_1 = new ModelRenderer((ModelBase)this, 0, 23);
        this.garlic4_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic4_1.func_78790_a(-2.0f, 7.0f, -2.0f, 4, 1, 4, 0.0f);
        this.garlic1_1 = new ModelRenderer((ModelBase)this, 0, 3);
        this.garlic1_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic1_1.func_78790_a(-1.5f, 2.0f, -1.5f, 3, 1, 3, 0.0f);
        this.string2 = new ModelRenderer((ModelBase)this, 6, 0);
        this.string2.func_78793_a(-3.0f, 1.8f, 7.0f);
        this.string2.func_78790_a(0.0f, -0.5f, -0.5f, 4, 1, 1, -0.4f);
        this.setRotateAngle(this.string2, 0.0f, 0.0f, -0.5462881f);
        this.garlic3_3 = new ModelRenderer((ModelBase)this, 0, 13);
        this.garlic3_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic3_3.func_78790_a(-3.5f, 4.0f, -3.5f, 7, 3, 7, 0.0f);
        this.garlicE = new ModelRenderer((ModelBase)this, 0, 0);
        this.garlicE.func_78793_a(5.0f, 0.0f, 7.0f);
        this.garlicE.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 2, 1, 0.0f);
        this.garlic1_4 = new ModelRenderer((ModelBase)this, 0, 3);
        this.garlic1_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic1_4.func_78790_a(-1.5f, 2.0f, -1.5f, 3, 1, 3, 0.0f);
        this.garlic2_4 = new ModelRenderer((ModelBase)this, 0, 7);
        this.garlic2_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic2_4.func_78790_a(-2.5f, 3.0f, -2.5f, 5, 1, 5, 0.0f);
        this.garlic2 = new ModelRenderer((ModelBase)this, 0, 7);
        this.garlic2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic2.func_78790_a(-2.5f, 3.0f, -2.5f, 5, 1, 5, 0.0f);
        this.string3 = new ModelRenderer((ModelBase)this, 6, 0);
        this.string3.func_78793_a(-0.4f, -0.3f, 7.0f);
        this.string3.func_78790_a(0.0f, -0.5f, -0.5f, 4, 1, 1, -0.4f);
        this.setRotateAngle(this.string3, 0.0f, 0.0f, 0.5462881f);
        this.garlic4_3 = new ModelRenderer((ModelBase)this, 0, 23);
        this.garlic4_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic4_3.func_78790_a(-2.0f, 7.0f, -2.0f, 4, 1, 4, 0.0f);
        this.string1 = new ModelRenderer((ModelBase)this, 6, 0);
        this.string1.func_78793_a(-5.4f, -0.3f, 7.0f);
        this.string1.func_78790_a(0.0f, -0.5f, -0.5f, 4, 1, 1, -0.4f);
        this.setRotateAngle(this.string1, 0.0f, 0.0f, 0.5462881f);
        this.garlic2_1 = new ModelRenderer((ModelBase)this, 0, 7);
        this.garlic2_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic2_1.func_78790_a(-2.5f, 3.0f, -2.5f, 5, 1, 5, 0.0f);
        this.string4 = new ModelRenderer((ModelBase)this, 6, 0);
        this.string4.func_78793_a(2.0f, 1.8f, 7.0f);
        this.string4.func_78790_a(0.0f, -0.5f, -0.5f, 4, 1, 1, -0.4f);
        this.setRotateAngle(this.string4, 0.0f, 0.0f, -0.5462881f);
        this.garlic3_2 = new ModelRenderer((ModelBase)this, 0, 13);
        this.garlic3_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic3_2.func_78790_a(-3.5f, 4.0f, -3.5f, 7, 3, 7, 0.0f);
        this.garlicA = new ModelRenderer((ModelBase)this, 0, 0);
        this.garlicA.func_78793_a(-5.0f, 0.0f, 7.0f);
        this.garlicA.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 2, 1, 0.0f);
        this.garlic4_4 = new ModelRenderer((ModelBase)this, 0, 23);
        this.garlic4_4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic4_4.func_78790_a(-2.0f, 7.0f, -2.0f, 4, 1, 4, 0.0f);
        this.garlicB = new ModelRenderer((ModelBase)this, 0, 0);
        this.garlicB.func_78793_a(-2.5f, 1.5f, 7.0f);
        this.garlicB.func_78790_a(-0.5f, 0.0f, -0.5f, 1, 2, 1, 0.0f);
        this.garlic4_2 = new ModelRenderer((ModelBase)this, 0, 23);
        this.garlic4_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic4_2.func_78790_a(-2.0f, 7.0f, -2.0f, 4, 1, 4, 0.0f);
        this.garlic2_3 = new ModelRenderer((ModelBase)this, 0, 7);
        this.garlic2_3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic2_3.func_78790_a(-2.5f, 3.0f, -2.5f, 5, 1, 5, 0.0f);
        this.garlic2_2 = new ModelRenderer((ModelBase)this, 0, 7);
        this.garlic2_2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic2_2.func_78790_a(-2.5f, 3.0f, -2.5f, 5, 1, 5, 0.0f);
        this.garlic3_1 = new ModelRenderer((ModelBase)this, 0, 13);
        this.garlic3_1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.garlic3_1.func_78790_a(-3.5f, 4.0f, -3.5f, 7, 3, 7, 0.0f);
        this.garlicC.func_78792_a(this.garlic4);
        this.garlicC.func_78792_a(this.garlic3);
        this.garlicE.func_78792_a(this.garlic1_2);
        this.garlicB.func_78792_a(this.garlic3_4);
        this.garlicC.func_78792_a(this.garlic1);
        this.garlicD.func_78792_a(this.garlic1_3);
        this.garlicA.func_78792_a(this.garlic4_1);
        this.garlicA.func_78792_a(this.garlic1_1);
        this.garlicD.func_78792_a(this.garlic3_3);
        this.garlicB.func_78792_a(this.garlic1_4);
        this.garlicB.func_78792_a(this.garlic2_4);
        this.garlicC.func_78792_a(this.garlic2);
        this.garlicD.func_78792_a(this.garlic4_3);
        this.garlicA.func_78792_a(this.garlic2_1);
        this.garlicE.func_78792_a(this.garlic3_2);
        this.garlicB.func_78792_a(this.garlic4_4);
        this.garlicE.func_78792_a(this.garlic4_2);
        this.garlicD.func_78792_a(this.garlic2_3);
        this.garlicE.func_78792_a(this.garlic2_2);
        this.garlicA.func_78792_a(this.garlic3_1);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        double SCALE = 0.21;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.garlicC.field_82906_o, (float)this.garlicC.field_82908_p, (float)this.garlicC.field_82907_q);
        GL11.glTranslatef((float)(this.garlicC.field_78800_c * f5), (float)(this.garlicC.field_78797_d * f5), (float)(this.garlicC.field_78798_e * f5));
        GL11.glScaled((double)0.21, (double)0.21, (double)0.21);
        GL11.glTranslatef((float)(-this.garlicC.field_82906_o), (float)(-this.garlicC.field_82908_p), (float)(-this.garlicC.field_82907_q));
        GL11.glTranslatef((float)(-this.garlicC.field_78800_c * f5), (float)(-this.garlicC.field_78797_d * f5), (float)(-this.garlicC.field_78798_e * f5));
        this.garlicC.func_78785_a(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.garlicD.field_82906_o, (float)this.garlicD.field_82908_p, (float)this.garlicD.field_82907_q);
        GL11.glTranslatef((float)(this.garlicD.field_78800_c * f5), (float)(this.garlicD.field_78797_d * f5), (float)(this.garlicD.field_78798_e * f5));
        GL11.glScaled((double)0.21, (double)0.21, (double)0.21);
        GL11.glTranslatef((float)(-this.garlicD.field_82906_o), (float)(-this.garlicD.field_82908_p), (float)(-this.garlicD.field_82907_q));
        GL11.glTranslatef((float)(-this.garlicD.field_78800_c * f5), (float)(-this.garlicD.field_78797_d * f5), (float)(-this.garlicD.field_78798_e * f5));
        this.garlicD.func_78785_a(f5);
        GL11.glPopMatrix();
        this.string2.func_78785_a(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.garlicE.field_82906_o, (float)this.garlicE.field_82908_p, (float)this.garlicE.field_82907_q);
        GL11.glTranslatef((float)(this.garlicE.field_78800_c * f5), (float)(this.garlicE.field_78797_d * f5), (float)(this.garlicE.field_78798_e * f5));
        GL11.glScaled((double)0.21, (double)0.21, (double)0.21);
        GL11.glTranslatef((float)(-this.garlicE.field_82906_o), (float)(-this.garlicE.field_82908_p), (float)(-this.garlicE.field_82907_q));
        GL11.glTranslatef((float)(-this.garlicE.field_78800_c * f5), (float)(-this.garlicE.field_78797_d * f5), (float)(-this.garlicE.field_78798_e * f5));
        this.garlicE.func_78785_a(f5);
        GL11.glPopMatrix();
        this.string3.func_78785_a(f5);
        this.string1.func_78785_a(f5);
        this.string4.func_78785_a(f5);
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.garlicA.field_82906_o, (float)this.garlicA.field_82908_p, (float)this.garlicA.field_82907_q);
        GL11.glTranslatef((float)(this.garlicA.field_78800_c * f5), (float)(this.garlicA.field_78797_d * f5), (float)(this.garlicA.field_78798_e * f5));
        GL11.glScaled((double)0.21, (double)0.21, (double)0.21);
        GL11.glTranslatef((float)(-this.garlicA.field_82906_o), (float)(-this.garlicA.field_82908_p), (float)(-this.garlicA.field_82907_q));
        GL11.glTranslatef((float)(-this.garlicA.field_78800_c * f5), (float)(-this.garlicA.field_78797_d * f5), (float)(-this.garlicA.field_78798_e * f5));
        this.garlicA.func_78785_a(f5);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.garlicB.field_82906_o, (float)this.garlicB.field_82908_p, (float)this.garlicB.field_82907_q);
        GL11.glTranslatef((float)(this.garlicB.field_78800_c * f5), (float)(this.garlicB.field_78797_d * f5), (float)(this.garlicB.field_78798_e * f5));
        GL11.glScaled((double)0.21, (double)0.21, (double)0.21);
        GL11.glTranslatef((float)(-this.garlicB.field_82906_o), (float)(-this.garlicB.field_82908_p), (float)(-this.garlicB.field_82907_q));
        GL11.glTranslatef((float)(-this.garlicB.field_78800_c * f5), (float)(-this.garlicB.field_78797_d * f5), (float)(-this.garlicB.field_78798_e * f5));
        this.garlicB.func_78785_a(f5);
        GL11.glPopMatrix();
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

