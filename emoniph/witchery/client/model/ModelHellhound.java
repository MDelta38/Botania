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

import com.emoniph.witchery.entity.EntityHellhound;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelHellhound
extends ModelBase {
    public ModelRenderer wolfHeadMain;
    public ModelRenderer wolfBody;
    public ModelRenderer wolfLeg1;
    public ModelRenderer wolfLeg2;
    public ModelRenderer wolfLeg3;
    public ModelRenderer wolfLeg4;
    ModelRenderer wolfTail;
    ModelRenderer wolfMane;

    public ModelHellhound() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.wolfBody = new ModelRenderer((ModelBase)this, 18, 17);
        this.wolfBody.func_78793_a(0.0f, 12.5f, 2.0f);
        this.wolfBody.func_78789_a(-4.0f, -2.0f, -3.0f, 6, 9, 6);
        this.setRotateAngle(this.wolfBody, 1.1838568f, 0.0f, 0.0f);
        this.wolfTail = new ModelRenderer((ModelBase)this, 9, 19);
        this.wolfTail.func_78793_a(-1.0f, 12.0f, 8.0f);
        this.wolfTail.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 6, 2);
        this.wolfLeg2 = new ModelRenderer((ModelBase)this, 0, 19);
        this.wolfLeg2.func_78793_a(0.5f, 16.0f, 7.0f);
        this.wolfLeg2.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.wolfLeg4 = new ModelRenderer((ModelBase)this, 0, 19);
        this.wolfLeg4.func_78793_a(0.5f, 16.0f, -4.0f);
        this.wolfLeg4.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.wolfMane = new ModelRenderer((ModelBase)this, 22, 0);
        this.wolfMane.func_78793_a(-1.0f, 14.0f, -3.0f);
        this.wolfMane.func_78789_a(-4.0f, -3.0f, -3.0f, 8, 8, 9);
        this.setRotateAngle(this.wolfMane, 1.5707964f, 0.0f, 0.0f);
        this.wolfLeg3 = new ModelRenderer((ModelBase)this, 0, 19);
        this.wolfLeg3.func_78793_a(-2.5f, 16.0f, -4.0f);
        this.wolfLeg3.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.wolfHeadMain = new ModelRenderer((ModelBase)this, 0, 0);
        this.wolfHeadMain.func_78793_a(-1.0f, 10.6f, -7.5f);
        this.wolfHeadMain.func_78789_a(-3.0f, -3.0f, -2.0f, 6, 6, 4);
        this.setRotateAngle(this.wolfHeadMain, 0.22759093f, 0.0f, 0.0f);
        this.wolfLeg1 = new ModelRenderer((ModelBase)this, 0, 19);
        this.wolfLeg1.func_78793_a(-2.5f, 16.0f, 7.0f);
        this.wolfLeg1.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        float f = 0.0f;
        this.wolfHeadMain.func_78784_a(16, 14).func_78790_a(-3.0f, -5.0f, 1.0f, 2, 2, 1, f);
        this.wolfHeadMain.func_78784_a(16, 14).func_78790_a(1.0f, -5.0f, 1.0f, 2, 2, 1, f);
        this.wolfHeadMain.func_78784_a(0, 10).func_78790_a(-1.5f, 0.0f, -5.0f, 3, 3, 4, f);
    }

    public void func_78088_a(Entity p_78088_1_, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        super.func_78088_a(p_78088_1_, p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_);
        this.func_78087_a(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, p_78088_1_);
        this.wolfHeadMain.func_78791_b(p_78088_7_);
        this.wolfBody.func_78785_a(p_78088_7_);
        this.wolfLeg1.func_78785_a(p_78088_7_);
        this.wolfLeg2.func_78785_a(p_78088_7_);
        this.wolfLeg3.func_78785_a(p_78088_7_);
        this.wolfLeg4.func_78785_a(p_78088_7_);
        this.wolfTail.func_78791_b(p_78088_7_);
        this.wolfMane.func_78785_a(p_78088_7_);
    }

    public void func_78086_a(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
        EntityHellhound entitywolf = (EntityHellhound)p_78086_1_;
        this.setRotateAngle(this.wolfTail, entitywolf.isConverting() ? 2.0f : 0.59184116f, 0.0f, 0.0f);
        if ((double)p_78086_3_ > 0.1) {
            this.wolfTail.field_78795_f = (float)((double)this.wolfTail.field_78795_f + ((double)(1.5f * p_78086_3_) - 0.1));
        }
        this.wolfBody.func_78793_a(0.0f, 14.0f, 2.0f);
        this.wolfBody.func_78793_a(0.0f, 12.5f, 2.0f);
        this.wolfBody.field_78795_f = 1.5707964f;
        this.setRotateAngle(this.wolfBody, 1.1838568f, 0.0f, 0.0f);
        this.wolfMane.func_78793_a(-1.0f, 14.0f, -3.0f);
        this.wolfMane.field_78795_f = 1.5707964f;
        this.wolfTail.func_78793_a(-1.0f, 12.0f, 8.0f);
        this.wolfLeg1.func_78793_a(-2.5f, 16.0f, 7.0f);
        this.wolfLeg2.func_78793_a(0.5f, 16.0f, 7.0f);
        this.wolfLeg3.func_78793_a(-2.5f, 16.0f, -4.0f);
        this.wolfLeg4.func_78793_a(0.5f, 16.0f, -4.0f);
        this.wolfLeg1.field_78795_f = MathHelper.func_76134_b((float)(p_78086_2_ * 0.6662f)) * 1.4f * p_78086_3_;
        this.wolfLeg2.field_78795_f = MathHelper.func_76134_b((float)(p_78086_2_ * 0.6662f + (float)Math.PI)) * 1.4f * p_78086_3_;
        this.wolfLeg3.field_78795_f = MathHelper.func_76134_b((float)(p_78086_2_ * 0.6662f + (float)Math.PI)) * 1.4f * p_78086_3_;
        this.wolfLeg4.field_78795_f = MathHelper.func_76134_b((float)(p_78086_2_ * 0.6662f)) * 1.4f * p_78086_3_;
        this.wolfTail.func_78793_a(-1.0f, 13.0f, 8.0f);
        this.wolfHeadMain.field_78808_h = entitywolf.getInterestedAngle(p_78086_4_) + entitywolf.getShakeAngle(p_78086_4_, 0.0f);
        this.wolfMane.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.08f);
        this.wolfBody.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.16f);
        this.wolfTail.field_78808_h = entitywolf.getShakeAngle(p_78086_4_, -0.2f);
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        EntityHellhound entitywolf = (EntityHellhound)p_78087_7_;
        this.wolfHeadMain.field_78795_f = p_78087_5_ / 57.295776f + (entitywolf.isConverting() ? 0.5f : 0.15f);
        this.wolfHeadMain.field_78796_g = p_78087_4_ / 57.295776f;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

