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
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityToad;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelToad
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer armRight;
    ModelRenderer armLeft;
    ModelRenderer legRight;
    ModelRenderer legLeft;

    public ModelToad() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.func_78085_a("head.nose", 0, 5);
        this.func_78085_a("head.eyeRight", 0, 0);
        this.func_78085_a("head.eyeLeft", 8, 0);
        this.func_78085_a("legRight.thighRight", 0, 20);
        this.func_78085_a("legRight.footRight", 0, 26);
        this.func_78085_a("legLeft.thighLeft", 11, 20);
        this.func_78085_a("legLeft.footLeft", 0, 26);
        this.head = new ModelRenderer((ModelBase)this, "head");
        this.head.func_78793_a(0.0f, 20.0f, -1.0f);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.head.field_78809_i = true;
        this.head.func_78786_a("nose", -2.0f, -1.0f, -4.0f, 4, 2, 4);
        this.head.func_78786_a("eyeRight", -2.5f, -3.0f, -3.0f, 2, 2, 2);
        this.head.func_78786_a("eyeLeft", 0.5f, -3.0f, -3.0f, 2, 2, 2);
        this.body = new ModelRenderer((ModelBase)this, 0, 12);
        this.body.func_78789_a(-2.0f, -1.0f, 0.0f, 4, 2, 5);
        this.body.func_78793_a(0.0f, 20.0f, -1.0f);
        this.body.func_78787_b(32, 32);
        this.body.field_78809_i = true;
        this.setRotation(this.body, -0.4833219f, 0.0f, 0.0f);
        this.armRight = new ModelRenderer((ModelBase)this, 13, 26);
        this.armRight.func_78789_a(-1.0f, 0.0f, 0.0f, 1, 4, 1);
        this.armRight.func_78793_a(-2.0f, 20.0f, -1.0f);
        this.armRight.func_78787_b(32, 32);
        this.armRight.field_78809_i = true;
        this.setRotation(this.armRight, -0.3346075f, 0.0f, 0.0f);
        this.armLeft = new ModelRenderer((ModelBase)this, 18, 26);
        this.armLeft.func_78789_a(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.armLeft.func_78793_a(2.0f, 20.0f, -1.0f);
        this.armLeft.func_78787_b(32, 32);
        this.armLeft.field_78809_i = true;
        this.setRotation(this.armLeft, -0.3346075f, 0.0f, 0.0f);
        this.legRight = new ModelRenderer((ModelBase)this, "legRight");
        this.legRight.func_78793_a(-2.0f, 23.0f, 3.0f);
        this.setRotation(this.legRight, 0.0f, 0.0f, 0.0f);
        this.legRight.field_78809_i = true;
        this.legRight.func_78786_a("thighRight", -2.0f, -1.0f, -2.0f, 2, 2, 3);
        this.legRight.func_78786_a("footRight", -3.0f, 1.0f, -4.0f, 3, 0, 3);
        this.legLeft = new ModelRenderer((ModelBase)this, "legLeft");
        this.legLeft.func_78793_a(2.0f, 23.0f, 3.0f);
        this.setRotation(this.legLeft, 0.0f, 0.0f, 0.0f);
        this.legLeft.field_78809_i = true;
        this.legLeft.func_78786_a("thighLeft", 0.0f, -1.0f, -2.0f, 2, 2, 3);
        this.legLeft.func_78786_a("footLeft", 0.0f, 1.0f, -4.0f, 3, 0, 3);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.field_78796_g = f3 / 57.295776f;
        this.head.field_78795_f = f4 / 57.295776f;
        if (this.field_78091_s) {
            float p6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / p6), (float)(1.5f / p6), (float)(1.5f / p6));
            GL11.glTranslatef((float)0.0f, (float)(10.0f * f5), (float)0.0f);
            this.head.func_78785_a(f5);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / p6), (float)(1.0f / p6), (float)(1.0f / p6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * f5), (float)0.0f);
            this.body.func_78785_a(f5);
            this.armRight.func_78785_a(f5);
            this.armLeft.func_78785_a(f5);
            this.legRight.func_78785_a(f5);
            this.legLeft.func_78785_a(f5);
            GL11.glPopMatrix();
        } else {
            this.head.func_78785_a(f5);
            this.body.func_78785_a(f5);
            this.armRight.func_78785_a(f5);
            this.armLeft.func_78785_a(f5);
            this.legRight.func_78785_a(f5);
            this.legLeft.func_78785_a(f5);
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }

    public void func_78086_a(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        EntityToad toad = (EntityToad)par1EntityLivingBase;
        if (toad.func_70906_o()) {
            this.legRight.field_78795_f = this.legLeft.field_78795_f = -0.3926991f;
        } else {
            this.legRight.field_78795_f = this.legLeft.field_78795_f = MathHelper.func_76134_b((float)(par2 * 0.6662f)) * 1.4f * par3 + ((double)par3 > 0.1 || (double)par3 < -0.1 ? 0.5f : 0.0f);
            this.armLeft.field_78795_f = MathHelper.func_76134_b((float)(par2 * 0.6662f + (float)Math.PI)) * 1.4f * par3;
            this.armRight.field_78795_f = MathHelper.func_76134_b((float)(par2 * 0.6662f)) * 1.4f * par3;
        }
    }
}

