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
public class ModelCaneSword
extends ModelBase {
    private ModelRenderer sheath;
    private ModelRenderer ball;
    private ModelRenderer blade1;
    private ModelRenderer blade2;
    private ModelRenderer blade3;

    public ModelCaneSword() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.blade3 = new ModelRenderer((ModelBase)this, 24, 0);
        this.blade3.func_78793_a(-5.8f, 11.0f, -1.6f);
        this.blade3.func_78790_a(-1.0f, -17.0f, -1.0f, 2, 2, 2, 0.0f);
        this.blade2 = new ModelRenderer((ModelBase)this, 24, 5);
        this.blade2.func_78793_a(-5.8f, 11.0f, -1.6f);
        this.blade2.func_78790_a(-1.0f, -15.0f, -1.0f, 2, 4, 2, 0.0f);
        this.ball = new ModelRenderer((ModelBase)this, 0, 0);
        this.ball.func_78793_a(-5.8f, 10.0f, -1.6f);
        this.ball.func_78790_a(-1.5f, -1.0f, -1.5f, 3, 2, 3, 0.0f);
        this.blade1 = new ModelRenderer((ModelBase)this, 24, 12);
        this.blade1.func_78793_a(-5.8f, 8.0f, -1.6f);
        this.blade1.func_78790_a(-1.0f, -8.0f, -1.0f, 2, 9, 2, 0.0f);
        this.sheath = new ModelRenderer((ModelBase)this, 0, 6);
        this.sheath.func_78793_a(-5.8f, 11.0f, -1.6f);
        this.sheath.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 13, 2, 0.0f);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean firstPerson, boolean deployed) {
        if (deployed) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.blade3.field_82906_o, (float)this.blade3.field_82908_p, (float)this.blade3.field_82907_q);
            GL11.glTranslatef((float)(this.blade3.field_78800_c * f5), (float)(this.blade3.field_78797_d * f5), (float)(this.blade3.field_78798_e * f5));
            GL11.glTranslatef((float)0.0f, (float)3.15f, (float)0.0f);
            GL11.glScaled((double)0.2, (double)4.3, (double)0.2);
            GL11.glTranslatef((float)(-this.blade3.field_82906_o), (float)(-this.blade3.field_82908_p), (float)(-this.blade3.field_82907_q));
            GL11.glTranslatef((float)(-this.blade3.field_78800_c * f5), (float)(-this.blade3.field_78797_d * f5), (float)(-this.blade3.field_78798_e * f5));
            this.blade3.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.blade2.field_82906_o, (float)this.blade2.field_82908_p, (float)this.blade2.field_82907_q);
            GL11.glTranslatef((float)(this.blade2.field_78800_c * f5), (float)(this.blade2.field_78797_d * f5), (float)(this.blade2.field_78798_e * f5));
            GL11.glTranslatef((float)0.0f, (float)2.1f, (float)0.0f);
            GL11.glScaled((double)0.15, (double)3.7, (double)0.7);
            GL11.glTranslatef((float)(-this.blade2.field_82906_o), (float)(-this.blade2.field_82908_p), (float)(-this.blade2.field_82907_q));
            GL11.glTranslatef((float)(-this.blade2.field_78800_c * f5), (float)(-this.blade2.field_78797_d * f5), (float)(-this.blade2.field_78798_e * f5));
            this.blade2.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.blade1.field_82906_o, (float)this.blade1.field_82908_p, (float)this.blade1.field_82907_q);
            GL11.glTranslatef((float)(this.blade1.field_78800_c * f5), (float)(this.blade1.field_78797_d * f5), (float)(this.blade1.field_78798_e * f5));
            GL11.glScaled((double)0.21, (double)1.4, (double)0.5);
            GL11.glTranslatef((float)(-this.blade1.field_82906_o), (float)(-this.blade1.field_82908_p), (float)(-this.blade1.field_82907_q));
            GL11.glTranslatef((float)(-this.blade1.field_78800_c * f5), (float)(-this.blade1.field_78797_d * f5), (float)(-this.blade1.field_78798_e * f5));
            this.blade1.func_78785_a(f5);
            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
        GL11.glTranslatef((float)this.ball.field_82906_o, (float)this.ball.field_82908_p, (float)this.ball.field_82907_q);
        GL11.glTranslatef((float)(this.ball.field_78800_c * f5), (float)(this.ball.field_78797_d * f5), (float)(this.ball.field_78798_e * f5));
        GL11.glScaled((double)0.8, (double)1.1, (double)0.8);
        GL11.glTranslatef((float)(-this.ball.field_82906_o), (float)(-this.ball.field_82908_p), (float)(-this.ball.field_82907_q));
        GL11.glTranslatef((float)(-this.ball.field_78800_c * f5), (float)(-this.ball.field_78797_d * f5), (float)(-this.ball.field_78798_e * f5));
        this.ball.func_78785_a(f5);
        GL11.glPopMatrix();
        if (!deployed) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)this.sheath.field_82906_o, (float)this.sheath.field_82908_p, (float)this.sheath.field_82907_q);
            GL11.glTranslatef((float)(this.sheath.field_78800_c * f5), (float)(this.sheath.field_78797_d * f5), (float)(this.sheath.field_78798_e * f5));
            GL11.glScaled((double)0.8, (double)1.0, (double)0.8);
            GL11.glTranslatef((float)(-this.sheath.field_82906_o), (float)(-this.sheath.field_82908_p), (float)(-this.sheath.field_82907_q));
            GL11.glTranslatef((float)(-this.sheath.field_78800_c * f5), (float)(-this.sheath.field_78797_d * f5), (float)(-this.sheath.field_78798_e * f5));
            this.sheath.func_78785_a(f5);
            GL11.glPopMatrix();
        }
    }
}

