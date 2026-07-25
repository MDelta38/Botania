/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.client.model.ModelOwl;
import com.emoniph.witchery.entity.EntityWingedMonkey;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelMonkey
extends ModelBase {
    private ModelRenderer tail;
    private ModelRenderer armLeft;
    private ModelRenderer legRight;
    private ModelRenderer bodyShoulder;
    private ModelRenderer body;
    private ModelRenderer legLeft;
    private ModelRenderer head;
    private ModelRenderer armRight;
    private ModelRenderer wingRight;
    private ModelRenderer wingLeft;
    private ModelRenderer headFace;
    private ModelRenderer headNose;
    private ModelRenderer headEarLeft;
    private ModelRenderer headEarRight;

    public ModelMonkey() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.headEarRight = new ModelRenderer((ModelBase)this, 18, 14);
        this.headEarRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headEarRight.func_78790_a(-4.5f, -2.5f, -1.5f, 2, 3, 1, 0.0f);
        this.tail = new ModelRenderer((ModelBase)this, 18, 23);
        this.tail.func_78790_a(-0.5f, -7.8f, -0.5f, 1, 8, 1, 0.0f);
        this.tail.func_78793_a(0.0f, 18.5f, 5.3f);
        this.setRotateAngle(this.tail, -0.63739425f, 0.0f, 0.0f);
        this.armRight = new ModelRenderer((ModelBase)this, 0, 19);
        this.armRight.func_78793_a(-3.5f, 14.0f, 0.0f);
        this.armRight.func_78790_a(-2.0f, -1.1f, -1.0f, 2, 11, 2, 0.0f);
        this.setRotateAngle(this.armRight, -0.18203785f, 0.0f, 0.0f);
        this.legRight = new ModelRenderer((ModelBase)this, 9, 25);
        this.legRight.func_78793_a(-1.4f, 19.0f, 4.7f);
        this.legRight.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f);
        this.bodyShoulder = new ModelRenderer((ModelBase)this, 32, 0);
        this.bodyShoulder.func_78793_a(0.0f, 16.0f, 1.0f);
        this.bodyShoulder.func_78790_a(-3.5f, -3.0f, -3.0f, 7, 5, 7, 0.0f);
        this.setRotateAngle(this.bodyShoulder, 1.5707964f, 0.0f, 0.0f);
        this.wingRight = new ModelRenderer((ModelBase)this, 28, 25);
        this.wingRight.func_78793_a(-1.0f, 14.0f, 2.5f);
        this.wingRight.func_78790_a(-12.0f, -0.5f, -3.0f, 12, 1, 6, 0.0f);
        this.setRotateAngle(this.wingRight, -0.68294734f, 0.3642502f, 0.5462881f);
        this.headEarLeft = new ModelRenderer((ModelBase)this, 18, 14);
        this.headEarLeft.field_78809_i = true;
        this.headEarLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headEarLeft.func_78790_a(2.5f, -2.5f, -1.5f, 2, 3, 1, 0.0f);
        this.headFace = new ModelRenderer((ModelBase)this, 5, 12);
        this.headFace.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headFace.func_78790_a(-2.5f, -3.5f, -3.5f, 5, 5, 1, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 36, 12);
        this.body.func_78793_a(0.0f, 15.8f, 2.0f);
        this.body.func_78790_a(-2.5f, -2.0f, -3.0f, 5, 7, 5, 0.0f);
        this.setRotateAngle(this.body, 0.59184116f, 0.0f, 0.0f);
        this.legLeft = new ModelRenderer((ModelBase)this, 9, 25);
        this.legLeft.field_78809_i = true;
        this.legLeft.func_78793_a(1.4f, 19.0f, 4.7f);
        this.legLeft.func_78790_a(-1.0f, 0.0f, -1.0f, 2, 5, 2, 0.0f);
        this.armLeft = new ModelRenderer((ModelBase)this, 0, 19);
        this.armLeft.field_78809_i = true;
        this.armLeft.func_78793_a(4.0f, 14.0f, 0.0f);
        this.armLeft.func_78790_a(-0.5f, -1.0f, -1.0f, 2, 11, 2, 0.0f);
        this.setRotateAngle(this.armLeft, -0.18203785f, 0.0f, 0.0f);
        this.wingLeft = new ModelRenderer((ModelBase)this, 28, 25);
        this.wingLeft.field_78809_i = true;
        this.wingLeft.func_78793_a(1.0f, 14.0f, 2.5f);
        this.wingLeft.func_78790_a(0.0f, -0.5f, -3.0f, 12, 1, 6, 0.0f);
        this.setRotateAngle(this.wingLeft, -0.68294734f, -0.3642502f, -0.5462881f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.func_78793_a(0.0f, 12.0f, -1.5f);
        this.head.func_78790_a(-3.0f, -4.0f, -3.0f, 6, 6, 5, 0.0f);
        this.headNose = new ModelRenderer((ModelBase)this, 9, 19);
        this.headNose.func_78793_a(0.0f, 0.0f, 0.0f);
        this.headNose.func_78790_a(-2.0f, -1.5f, -4.5f, 4, 3, 1, 0.0f);
        this.head.func_78792_a(this.headEarRight);
        this.head.func_78792_a(this.headEarLeft);
        this.head.func_78792_a(this.headFace);
        this.head.func_78792_a(this.headNose);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        boolean landed;
        EntityWingedMonkey entitybat = (EntityWingedMonkey)entity;
        this.armRight.field_78808_h = 0.0f;
        this.armLeft.field_78808_h = 0.0f;
        this.legRight.field_78808_h = 0.0f;
        this.legLeft.field_78808_h = 0.0f;
        boolean bl = landed = entity.field_70181_x == 0.0 && entity.field_70159_w == 0.0 && entity.field_70179_y == 0.0 && ModelOwl.isLanded(entity);
        if (landed) {
            this.setRotateAngle(this.wingLeft, -0.68294734f, -0.3642502f, -0.5462881f);
            this.setRotateAngle(this.wingRight, -0.68294734f, 0.3642502f, 0.5462881f);
            this.armLeft.field_78795_f = -0.18203785f;
            this.armRight.field_78795_f = -0.18203785f;
            this.legLeft.field_78795_f = 0.0f;
            this.legRight.field_78795_f = 0.0f;
            this.wingLeft.field_78797_d = 14.0f;
            this.wingRight.field_78797_d = 14.0f;
        } else {
            this.wingRight.field_78808_h = MathHelper.func_76134_b((float)(f2 * 0.5f)) * (float)Math.PI * 0.2f * 2.0f + 0.2f;
            this.wingRight.field_78795_f = 0.0f;
            this.wingRight.field_78797_d = 12.0f;
            this.wingLeft.field_78808_h = -this.wingRight.field_78808_h;
            this.wingLeft.field_78795_f = 0.0f;
            this.wingLeft.field_78797_d = 12.0f;
            this.armLeft.field_78795_f = 0.2f;
            this.armRight.field_78795_f = 0.2f;
            this.legLeft.field_78795_f = 0.1f;
            this.legRight.field_78795_f = 0.1f;
            this.armRight.field_78808_h += MathHelper.func_76134_b((float)(f2 * 0.09f)) * 0.05f + 0.05f;
            this.armLeft.field_78808_h -= MathHelper.func_76134_b((float)(f2 * 0.09f)) * 0.05f + 0.05f;
            this.armRight.field_78795_f += MathHelper.func_76126_a((float)(f2 * 0.067f)) * 0.05f;
            this.armLeft.field_78795_f -= MathHelper.func_76126_a((float)(f2 * 0.067f)) * 0.05f;
            this.legRight.field_78795_f += MathHelper.func_76126_a((float)(f2 * 0.067f)) * 0.05f;
            this.legLeft.field_78795_f -= MathHelper.func_76126_a((float)(f2 * 0.067f)) * 0.05f;
        }
        if (entitybat.func_70906_o()) {
            this.legRight.field_78795_f = -1.3f;
            this.legRight.field_78798_e = 2.0f;
            this.legRight.field_78797_d = 21.0f;
            this.legLeft.field_78795_f = -1.3f;
            this.legLeft.field_78798_e = 2.0f;
            this.legLeft.field_78797_d = 21.0f;
            this.body.field_78795_f = 0.1f;
            this.body.field_78797_d = 17.0f;
            this.tail.func_78793_a(0.0f, 18.5f, 5.3f);
            this.tail.field_78798_e = 4.0f;
            this.tail.field_78797_d = 20.0f;
            this.setRotateAngle(this.tail, -0.9f, 0.0f, 0.0f);
            this.armRight.field_78808_h += MathHelper.func_76134_b((float)(f2 * 0.09f)) * 0.05f + 0.05f;
            this.armLeft.field_78808_h -= MathHelper.func_76134_b((float)(f2 * 0.09f)) * 0.05f + 0.05f;
            this.armRight.field_78795_f += MathHelper.func_76126_a((float)(f2 * 0.067f)) * 0.05f;
            this.armLeft.field_78795_f -= MathHelper.func_76126_a((float)(f2 * 0.067f)) * 0.05f;
            if (!landed) {
                this.legRight.field_78795_f = 0.0f;
                this.legLeft.field_78795_f = 0.0f;
            }
        } else {
            this.body.field_78795_f = 0.59184116f;
            this.body.func_78793_a(0.0f, 15.8f, 2.0f);
            this.legRight.func_78793_a(-1.4f, 19.0f, 4.7f);
            this.legLeft.func_78793_a(1.4f, 19.0f, 4.7f);
            this.legRight.field_78795_f = 0.0f;
            this.legLeft.field_78795_f = 0.0f;
            this.tail.func_78793_a(0.0f, 18.5f, 5.3f);
            this.setRotateAngle(this.tail, -0.63739425f, 0.0f, 0.0f);
        }
        if ((double)f1 > 0.1) {
            this.tail.field_78795_f = (float)((double)this.tail.field_78795_f + ((double)(-f1) - 0.1));
            this.tail.field_78808_h += 5.0f * MathHelper.func_76134_b((float)(f2 * 0.09f)) * 0.05f + 0.05f;
        } else {
            this.tail.field_78808_h += 3.0f * MathHelper.func_76134_b((float)(f2 * 0.09f)) * 0.05f + 0.05f;
        }
        this.head.field_78796_g = f3 / 57.295776f;
        this.head.field_78795_f = f4 / 57.295776f;
        int i = entitybat.getAttackTimer();
        if (i > 0) {
            float di = 10.0f;
            this.armRight.field_78795_f = -2.0f + 1.5f * (Math.abs(((float)i - f3) % 10.0f - di * 0.5f) - di * 0.25f) / (di * 0.25f);
        }
        this.tail.func_78785_a(f5);
        this.armRight.func_78785_a(f5);
        this.legRight.func_78785_a(f5);
        this.bodyShoulder.func_78785_a(f5);
        this.wingRight.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.legLeft.func_78785_a(f5);
        this.armLeft.func_78785_a(f5);
        this.wingLeft.func_78785_a(f5);
        this.head.func_78785_a(f5);
    }
}

