/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelCandelabra
extends ModelBase {
    ModelRenderer candleLeft;
    ModelRenderer candleRight;
    ModelRenderer candleFront;
    ModelRenderer candleBack;
    ModelRenderer candleMiddle;
    ModelRenderer supportLR;
    ModelRenderer supportFB;
    ModelRenderer sconceLeft;
    ModelRenderer sconceRight;
    ModelRenderer sconceFront;
    ModelRenderer sconceBack;
    ModelRenderer sconceMiddle;
    ModelRenderer baseTop;
    ModelRenderer baseBottom;

    public ModelCandelabra() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.candleLeft = new ModelRenderer((ModelBase)this, 0, 0);
        this.candleLeft.func_78789_a(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.candleLeft.func_78793_a(-6.0f, 11.0f, -1.0f);
        this.candleLeft.func_78787_b(32, 32);
        this.candleLeft.field_78809_i = true;
        this.setRotation(this.candleLeft, 0.0f, 0.0f, 0.0f);
        this.candleRight = new ModelRenderer((ModelBase)this, 0, 0);
        this.candleRight.func_78789_a(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.candleRight.func_78793_a(4.0f, 11.0f, -1.0f);
        this.candleRight.func_78787_b(32, 32);
        this.candleRight.field_78809_i = true;
        this.setRotation(this.candleRight, 0.0f, 0.0f, 0.0f);
        this.candleFront = new ModelRenderer((ModelBase)this, 0, 0);
        this.candleFront.func_78789_a(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.candleFront.func_78793_a(-1.0f, 11.0f, -6.0f);
        this.candleFront.func_78787_b(32, 32);
        this.candleFront.field_78809_i = true;
        this.setRotation(this.candleFront, 0.0f, 0.0f, 0.0f);
        this.candleBack = new ModelRenderer((ModelBase)this, 0, 0);
        this.candleBack.func_78789_a(0.0f, 0.0f, 0.0f, 2, 8, 2);
        this.candleBack.func_78793_a(-1.0f, 11.0f, 4.0f);
        this.candleBack.func_78787_b(32, 32);
        this.candleBack.field_78809_i = true;
        this.setRotation(this.candleBack, 0.0f, 0.0f, 0.0f);
        this.candleMiddle = new ModelRenderer((ModelBase)this, 0, 0);
        this.candleMiddle.func_78789_a(0.0f, 0.0f, 0.0f, 2, 13, 2);
        this.candleMiddle.func_78793_a(-1.0f, 9.0f, -1.0f);
        this.candleMiddle.func_78787_b(32, 32);
        this.candleMiddle.field_78809_i = true;
        this.setRotation(this.candleMiddle, 0.0f, 0.0f, 0.0f);
        this.supportLR = new ModelRenderer((ModelBase)this, 0, 17);
        this.supportLR.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 2);
        this.supportLR.func_78793_a(-6.0f, 19.0f, -1.0f);
        this.supportLR.func_78787_b(32, 32);
        this.supportLR.field_78809_i = true;
        this.setRotation(this.supportLR, 0.0f, 0.0f, 0.0f);
        this.supportFB = new ModelRenderer((ModelBase)this, 0, 4);
        this.supportFB.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 12);
        this.supportFB.func_78793_a(-1.0f, 19.0f, -6.0f);
        this.supportFB.func_78787_b(32, 32);
        this.supportFB.field_78809_i = true;
        this.setRotation(this.supportFB, 0.0f, 0.0f, 0.0f);
        this.sconceLeft = new ModelRenderer((ModelBase)this, 0, 20);
        this.sconceLeft.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.sconceLeft.func_78793_a(-6.5f, 17.0f, -1.5f);
        this.sconceLeft.func_78787_b(32, 32);
        this.sconceLeft.field_78809_i = true;
        this.setRotation(this.sconceLeft, 0.0f, 0.0f, 0.0f);
        this.sconceRight = new ModelRenderer((ModelBase)this, 0, 20);
        this.sconceRight.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.sconceRight.func_78793_a(3.5f, 17.0f, -1.5f);
        this.sconceRight.func_78787_b(32, 32);
        this.sconceRight.field_78809_i = true;
        this.setRotation(this.sconceRight, 0.0f, 0.0f, 0.0f);
        this.sconceFront = new ModelRenderer((ModelBase)this, 0, 20);
        this.sconceFront.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.sconceFront.func_78793_a(-1.5f, 17.0f, -6.5f);
        this.sconceFront.func_78787_b(32, 32);
        this.sconceFront.field_78809_i = true;
        this.setRotation(this.sconceFront, 0.0f, 0.0f, 0.0f);
        this.sconceBack = new ModelRenderer((ModelBase)this, 0, 20);
        this.sconceBack.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.sconceBack.func_78793_a(-1.5f, 17.0f, 3.5f);
        this.sconceBack.func_78787_b(32, 32);
        this.sconceBack.field_78809_i = true;
        this.setRotation(this.sconceBack, 0.0f, 0.0f, 0.0f);
        this.sconceMiddle = new ModelRenderer((ModelBase)this, 0, 20);
        this.sconceMiddle.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.sconceMiddle.func_78793_a(-1.5f, 15.0f, -1.5f);
        this.sconceMiddle.func_78787_b(32, 32);
        this.sconceMiddle.field_78809_i = true;
        this.setRotation(this.sconceMiddle, 0.0f, 0.0f, 0.0f);
        this.baseTop = new ModelRenderer((ModelBase)this, 12, 20);
        this.baseTop.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.baseTop.func_78793_a(-1.5f, 22.0f, -1.5f);
        this.baseTop.func_78787_b(32, 32);
        this.baseTop.field_78809_i = true;
        this.setRotation(this.baseTop, 0.0f, 0.0f, 0.0f);
        this.baseBottom = new ModelRenderer((ModelBase)this, 8, 24);
        this.baseBottom.func_78789_a(-2.5f, 0.0f, -2.5f, 5, 1, 5);
        this.baseBottom.func_78793_a(0.0f, 23.0f, 0.0f);
        this.baseBottom.func_78787_b(32, 32);
        this.baseBottom.field_78809_i = true;
        this.setRotation(this.baseBottom, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.candleLeft.func_78785_a(f5);
        this.candleRight.func_78785_a(f5);
        this.candleFront.func_78785_a(f5);
        this.candleBack.func_78785_a(f5);
        this.candleMiddle.func_78785_a(f5);
        this.supportLR.func_78785_a(f5);
        this.supportFB.func_78785_a(f5);
        this.sconceLeft.func_78785_a(f5);
        this.sconceRight.func_78785_a(f5);
        this.sconceFront.func_78785_a(f5);
        this.sconceBack.func_78785_a(f5);
        this.sconceMiddle.func_78785_a(f5);
        this.baseTop.func_78785_a(f5);
        this.baseBottom.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }
}

