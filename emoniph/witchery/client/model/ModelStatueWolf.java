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
public class ModelStatueWolf
extends ModelBase {
    ModelRenderer WolfHead;
    ModelRenderer Body;
    ModelRenderer Mane;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;
    ModelRenderer Tail;
    ModelRenderer Ear1;
    ModelRenderer Ear2;
    ModelRenderer Nose;
    ModelRenderer Shape1;

    public ModelStatueWolf() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.WolfHead = new ModelRenderer((ModelBase)this, 0, 0);
        this.WolfHead.func_78789_a(-3.0f, -3.0f, -2.0f, 6, 6, 4);
        this.WolfHead.func_78793_a(-0.5f, 13.5f, -7.0f);
        this.WolfHead.func_78787_b(64, 32);
        this.WolfHead.field_78809_i = true;
        this.setRotation(this.WolfHead, 0.0f, 0.0f, 0.0f);
        this.Body = new ModelRenderer((ModelBase)this, 18, 14);
        this.Body.func_78789_a(-4.0f, -2.0f, -3.0f, 6, 9, 6);
        this.Body.func_78793_a(0.5f, 14.0f, 2.0f);
        this.Body.func_78787_b(64, 32);
        this.Body.field_78809_i = true;
        this.setRotation(this.Body, 1.570796f, 0.0f, 0.0f);
        this.Mane = new ModelRenderer((ModelBase)this, 21, 0);
        this.Mane.func_78789_a(-4.0f, -3.0f, -3.0f, 8, 6, 7);
        this.Mane.func_78793_a(-0.5f, 14.0f, -3.0f);
        this.Mane.func_78787_b(64, 32);
        this.Mane.field_78809_i = true;
        this.setRotation(this.Mane, 1.570796f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg1.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 7, 2);
        this.Leg1.func_78793_a(-2.0f, 16.0f, 7.0f);
        this.Leg1.func_78787_b(64, 32);
        this.Leg1.field_78809_i = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg2.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 7, 2);
        this.Leg2.func_78793_a(1.0f, 16.0f, 7.0f);
        this.Leg2.func_78787_b(64, 32);
        this.Leg2.field_78809_i = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg3.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 7, 2);
        this.Leg3.func_78793_a(-2.0f, 16.0f, -4.0f);
        this.Leg3.func_78787_b(64, 32);
        this.Leg3.field_78809_i = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 0, 18);
        this.Leg4.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 7, 2);
        this.Leg4.func_78793_a(1.0f, 16.0f, -4.0f);
        this.Leg4.func_78787_b(64, 32);
        this.Leg4.field_78809_i = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
        this.Tail = new ModelRenderer((ModelBase)this, 9, 18);
        this.Tail.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 8, 2);
        this.Tail.func_78793_a(-0.5f, 12.0f, 8.0f);
        this.Tail.func_78787_b(64, 32);
        this.Tail.field_78809_i = true;
        this.setRotation(this.Tail, 1.130069f, 0.0f, 0.0f);
        this.Ear1 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear1.func_78789_a(-3.0f, -5.0f, 0.0f, 2, 2, 1);
        this.Ear1.func_78793_a(-0.5f, 13.5f, -7.0f);
        this.Ear1.func_78787_b(64, 32);
        this.Ear1.field_78809_i = true;
        this.setRotation(this.Ear1, 0.0f, 0.0f, 0.0f);
        this.Ear2 = new ModelRenderer((ModelBase)this, 16, 14);
        this.Ear2.func_78789_a(1.0f, -5.0f, 0.0f, 2, 2, 1);
        this.Ear2.func_78793_a(-0.5f, 13.5f, -7.0f);
        this.Ear2.func_78787_b(64, 32);
        this.Ear2.field_78809_i = true;
        this.setRotation(this.Ear2, 0.0f, 0.0f, 0.0f);
        this.Nose = new ModelRenderer((ModelBase)this, 0, 10);
        this.Nose.func_78789_a(-2.0f, 0.0f, -5.0f, 3, 3, 4);
        this.Nose.func_78793_a(0.0f, 13.5f, -7.0f);
        this.Nose.func_78787_b(64, 32);
        this.Nose.field_78809_i = true;
        this.setRotation(this.Nose, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 22, 18);
        this.Shape1.func_78789_a(0.0f, 0.0f, 0.0f, 8, 1, 13);
        this.Shape1.func_78793_a(-4.5f, 23.0f, -5.0f);
        this.Shape1.func_78787_b(64, 32);
        this.Shape1.field_78809_i = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.WolfHead.func_78785_a(f5);
        this.Body.func_78785_a(f5);
        this.Mane.func_78785_a(f5);
        this.Leg1.func_78785_a(f5);
        this.Leg2.func_78785_a(f5);
        this.Leg3.func_78785_a(f5);
        this.Leg4.func_78785_a(f5);
        this.Tail.func_78785_a(f5);
        this.Ear1.func_78785_a(f5);
        this.Ear2.func_78785_a(f5);
        this.Nose.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
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

