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

import com.emoniph.witchery.entity.EntityReflection;
import com.emoniph.witchery.entity.EntityWolfman;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelWolfman
extends ModelBase {
    public ModelRenderer headMain;
    public ModelRenderer bodyUpper;
    public ModelRenderer legRightUpper;
    public ModelRenderer legLeftUpper;
    public ModelRenderer armLeft;
    public ModelRenderer armRight;
    public ModelRenderer tail;
    public ModelRenderer bodyLower;
    public ModelRenderer legRightLower;
    public ModelRenderer legLeftLower;
    public int heldItemLeft;
    public int heldItemRight;
    public boolean isSneak;
    public boolean aimedBow;

    public ModelWolfman() {
        this(0.0f);
    }

    public ModelWolfman(float scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        float headScale = 0.05f;
        this.headMain = new ModelRenderer((ModelBase)this, 0, 0);
        this.headMain.func_78790_a(-3.0f, -6.0f, -2.0f, 6, 6, 4, 0.05f);
        this.headMain.func_78793_a(0.0f, 0.0f, -2.0f);
        float f = 0.0f;
        this.headMain.func_78784_a(16, 14).func_78790_a(-3.0f, -8.0f, 1.0f, 2, 2, 1, 0.0f);
        this.headMain.func_78784_a(16, 14).func_78790_a(1.0f, -8.0f, 1.0f, 2, 2, 1, 0.0f);
        this.headMain.func_78784_a(0, 10).func_78790_a(-1.5f, -3.1f, -5.0f, 3, 3, 4, 0.0f);
        this.bodyUpper = new ModelRenderer((ModelBase)this, 0, 35);
        this.bodyUpper.func_78793_a(0.0f, -0.1f, -2.0f);
        this.bodyUpper.func_78790_a(-5.0f, 0.0f, -3.9f, 10, 7, 8, scale);
        this.setRotateAngle(this.bodyUpper, 0.4098033f, 0.0f, 0.0f);
        this.bodyLower = new ModelRenderer((ModelBase)this, 3, 50);
        this.bodyLower.func_78793_a(0.0f, 5.0f, -1.5f);
        this.bodyLower.func_78790_a(-4.0f, 2.0f, -2.3f, 8, 7, 5, scale);
        this.bodyUpper.func_78792_a(this.bodyLower);
        this.tail = new ModelRenderer((ModelBase)this, 55, 52);
        this.tail.func_78793_a(0.0f, 11.9f, 3.6f);
        this.tail.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 10, 2, scale);
        this.setRotateAngle(this.tail, 0.59184116f, 0.0f, 0.0f);
        this.legLeftUpper = new ModelRenderer((ModelBase)this, 38, 0);
        this.legLeftUpper.field_78809_i = true;
        this.legLeftUpper.func_78793_a(2.0f, 12.0f, 0.0f);
        this.legLeftUpper.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 7, 4, scale);
        this.setRotateAngle(this.legLeftUpper, -0.4098033f, 0.0f, 0.0f);
        this.legLeftLower = new ModelRenderer((ModelBase)this, 38, 13);
        this.legLeftLower.func_78793_a(0.0f, 0.0f, 0.0f);
        this.legLeftLower.func_78790_a(-2.0f, 3.5f, 2.0f, 4, 8, 4, scale);
        this.legLeftUpper.func_78792_a(this.legLeftLower);
        this.legRightUpper = new ModelRenderer((ModelBase)this, 38, 0);
        this.legRightUpper.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.legRightUpper.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 7, 4, scale);
        this.setRotateAngle(this.legRightUpper, -0.4098033f, 0.0f, 0.0f);
        this.legRightLower = new ModelRenderer((ModelBase)this, 38, 13);
        this.legRightLower.func_78793_a(0.0f, 0.0f, 0.0f);
        this.legRightLower.func_78790_a(-2.0f, 3.5f, 2.0f, 4, 8, 4, scale);
        this.legRightUpper.func_78792_a(this.legRightLower);
        this.armLeft = new ModelRenderer((ModelBase)this, 38, 46);
        this.armLeft.field_78809_i = true;
        this.armLeft.func_78793_a(6.0f, 2.0f, 0.0f);
        this.armLeft.func_78790_a(-1.0f, -2.0f, -2.0f, 4, 14, 4, scale);
        this.armRight = new ModelRenderer((ModelBase)this, 38, 46);
        this.armRight.func_78793_a(-5.8f, 2.0f, 0.0f);
        this.armRight.func_78790_a(-3.0f, -2.0f, -2.0f, 4, 14, 4, scale);
    }

    public void func_78088_a(Entity entity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, entity);
        this.headMain.func_78785_a(p_78088_7_);
        this.bodyUpper.func_78785_a(p_78088_7_);
        this.armRight.func_78785_a(p_78088_7_);
        this.legLeftUpper.func_78785_a(p_78088_7_);
        this.tail.func_78785_a(p_78088_7_);
        this.armLeft.func_78785_a(p_78088_7_);
        this.legRightUpper.func_78785_a(p_78088_7_);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78086_a(EntityLivingBase entity, float par2, float par3, float par4) {
        float i = 0.0f;
        if (entity instanceof EntityWolfman) {
            EntityWolfman wolfman = (EntityWolfman)entity;
            i = wolfman.getAttackTimer();
            this.field_78093_q = wolfman.isSitting();
        } else if (entity instanceof EntityReflection) {
            EntityReflection wolfman = (EntityReflection)entity;
            i = wolfman.getAttackTimer();
        }
        if (i > 0.0f) {
            this.armRight.field_78795_f = -2.0f + 1.5f * this.interpolateRotation(i - par4, 10.0f);
            this.armLeft.field_78795_f = -1.0f + 0.9f * this.interpolateRotation(i - par4, 10.0f);
        } else {
            this.armRight.field_78795_f = MathHelper.func_76134_b((float)(par2 * 0.6662f + (float)Math.PI)) * 2.0f * par3 * 0.5f;
            this.armLeft.field_78795_f = MathHelper.func_76134_b((float)(par2 * 0.6662f)) * 2.0f * par3 * 0.5f;
        }
    }

    private float interpolateRotation(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        float f7;
        float f6;
        this.headMain.field_78796_g = p_78087_4_ / 57.295776f;
        this.headMain.field_78795_f = p_78087_5_ / 57.295776f;
        this.armRight.field_78808_h = 0.0f;
        this.armLeft.field_78808_h = 0.0f;
        this.legRightUpper.field_78795_f = Math.max(-0.4098033f + MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f)) * 1.4f * p_78087_2_, -0.8f);
        this.legLeftUpper.field_78795_f = Math.max(-0.4098033f + MathHelper.func_76134_b((float)(p_78087_1_ * 0.6662f + (float)Math.PI)) * 1.4f * p_78087_2_, -0.8f);
        this.legRightUpper.field_78796_g = 0.0f;
        this.legLeftUpper.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.armRight.field_78795_f += -0.62831855f;
            this.armLeft.field_78795_f += -0.62831855f;
            this.legRightUpper.field_78795_f = -1.2566371f;
            this.legLeftUpper.field_78795_f = -1.2566371f;
            this.legRightUpper.field_78796_g = 0.31415927f;
            this.legLeftUpper.field_78796_g = -0.31415927f;
        }
        if (this.heldItemLeft != 0) {
            this.armLeft.field_78795_f = this.armLeft.field_78795_f * 0.5f - 0.31415927f * (float)this.heldItemLeft;
        }
        if (this.heldItemRight != 0) {
            this.armRight.field_78795_f = this.armRight.field_78795_f * 0.5f - 0.31415927f * (float)this.heldItemRight;
        }
        this.armRight.field_78796_g = 0.0f;
        this.armLeft.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            f6 = this.field_78095_p;
            this.bodyUpper.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f6) * (float)Math.PI * 2.0f)) * 0.2f;
            this.armRight.field_78798_e = MathHelper.func_76126_a((float)this.bodyUpper.field_78796_g) * 5.0f;
            this.armRight.field_78800_c = -MathHelper.func_76134_b((float)this.bodyUpper.field_78796_g) * 5.0f;
            this.armLeft.field_78798_e = -MathHelper.func_76126_a((float)this.bodyUpper.field_78796_g) * 5.0f;
            this.armLeft.field_78800_c = MathHelper.func_76134_b((float)this.bodyUpper.field_78796_g) * 5.0f;
            this.armRight.field_78796_g += this.bodyUpper.field_78796_g;
            this.armLeft.field_78796_g += this.bodyUpper.field_78796_g;
            this.armLeft.field_78795_f += this.bodyUpper.field_78796_g;
            f6 = 1.0f - this.field_78095_p;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            f7 = MathHelper.func_76126_a((float)(f6 * (float)Math.PI));
            float f8 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.headMain.field_78795_f - 0.7f) * 0.75f;
            this.armRight.field_78795_f = (float)((double)this.armRight.field_78795_f - ((double)f7 * 1.2 + (double)f8));
            this.armRight.field_78796_g += this.bodyUpper.field_78796_g * 2.0f;
            this.armRight.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        if (this.isSneak) {
            this.bodyUpper.field_78795_f = 0.5f;
            this.armRight.field_78795_f += 0.4f;
            this.armLeft.field_78795_f += 0.4f;
            this.legRightUpper.field_78798_e = 4.0f;
            this.legLeftUpper.field_78798_e = 4.0f;
            this.legRightUpper.field_78797_d = 9.0f;
            this.legLeftUpper.field_78797_d = 9.0f;
            this.headMain.field_78797_d = 0.0f;
        } else {
            this.setRotateAngle(this.bodyUpper, 0.4098033f, 0.0f, 0.0f);
            this.legRightUpper.field_78798_e = 0.1f;
            this.legLeftUpper.field_78798_e = 0.1f;
            this.legRightUpper.field_78797_d = 12.0f;
            this.legLeftUpper.field_78797_d = 12.0f;
            this.headMain.field_78797_d = 0.0f;
        }
        this.setRotateAngle(this.tail, 0.59184116f, 0.0f, 0.0f);
        if ((double)p_78087_2_ > 0.1) {
            this.tail.field_78795_f = (float)((double)this.tail.field_78795_f + ((double)p_78087_2_ - 0.1));
            this.tail.field_78808_h += 5.0f * MathHelper.func_76134_b((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
        } else {
            this.tail.field_78808_h += 3.0f * MathHelper.func_76134_b((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
        }
        this.armRight.field_78808_h += MathHelper.func_76134_b((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
        this.armLeft.field_78808_h -= MathHelper.func_76134_b((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
        this.armRight.field_78795_f += MathHelper.func_76126_a((float)(p_78087_3_ * 0.067f)) * 0.05f;
        this.armLeft.field_78795_f -= MathHelper.func_76126_a((float)(p_78087_3_ * 0.067f)) * 0.05f;
        if (this.aimedBow) {
            f6 = 0.0f;
            f7 = 0.0f;
            this.armRight.field_78808_h = 0.0f;
            this.armLeft.field_78808_h = 0.0f;
            this.armRight.field_78796_g = -(0.1f - f6 * 0.6f) + this.headMain.field_78796_g;
            this.armLeft.field_78796_g = 0.1f - f6 * 0.6f + this.headMain.field_78796_g + 0.4f;
            this.armRight.field_78795_f = -1.5707964f + this.headMain.field_78795_f;
            this.armLeft.field_78795_f = -1.5707964f + this.headMain.field_78795_f;
            this.armRight.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.armLeft.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.armRight.field_78808_h += MathHelper.func_76134_b((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
            this.armLeft.field_78808_h -= MathHelper.func_76134_b((float)(p_78087_3_ * 0.09f)) * 0.05f + 0.05f;
            this.armRight.field_78795_f += MathHelper.func_76126_a((float)(p_78087_3_ * 0.067f)) * 0.05f;
            this.armLeft.field_78795_f -= MathHelper.func_76126_a((float)(p_78087_3_ * 0.067f)) * 0.05f;
        }
    }
}

