/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMysticBranch
extends ModelBase {
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;

    public ModelMysticBranch() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.Shape1 = new ModelRenderer((ModelBase)this, 12, 0);
        this.Shape1.func_78789_a(0.0f, -4.0f, 0.0f, 1, 4, 1);
        this.Shape1.func_78793_a(0.0f, -8.0f, 0.0f);
        this.Shape1.func_78787_b(32, 32);
        this.Shape1.field_78809_i = true;
        this.setRotation(this.Shape1, 0.1858931f, 0.0f, 0.2230717f);
        this.Shape2 = new ModelRenderer((ModelBase)this, 20, 0);
        this.Shape2.func_78789_a(-0.5f, -5.2f, -1.2f, 1, 5, 1);
        this.Shape2.func_78793_a(1.0f, -9.0f, 1.0f);
        this.Shape2.func_78787_b(32, 32);
        this.Shape2.field_78809_i = true;
        this.setRotation(this.Shape2, -0.2230717f, 0.0f, -0.4089647f);
        this.Shape3 = new ModelRenderer((ModelBase)this, 4, 0);
        this.Shape3.func_78789_a(-1.0f, -6.0f, 0.0f, 1, 6, 1);
        this.Shape3.func_78793_a(1.0f, -4.0f, 0.0f);
        this.Shape3.func_78787_b(32, 32);
        this.Shape3.field_78809_i = true;
        this.setRotation(this.Shape3, 0.2230717f, -0.0371786f, 0.4089647f);
        this.Shape4 = new ModelRenderer((ModelBase)this, 8, 0);
        this.Shape4.func_78789_a(-1.0f, -3.5f, -0.5f, 1, 4, 1);
        this.Shape4.func_78793_a(1.0f, -4.9f, 1.0f);
        this.Shape4.func_78787_b(32, 32);
        this.Shape4.field_78809_i = true;
        this.setRotation(this.Shape4, -0.5948578f, 0.0f, -0.4089647f);
        this.Shape5 = new ModelRenderer((ModelBase)this, 16, 0);
        this.Shape5.func_78789_a(-0.2f, -4.8f, 0.4f, 1, 5, 1);
        this.Shape5.func_78793_a(1.0f, -12.0f, -1.0f);
        this.Shape5.func_78787_b(32, 32);
        this.Shape5.field_78809_i = true;
        this.setRotation(this.Shape5, -0.3717861f, 0.0f, 0.0f);
        this.Shape6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Shape6.func_78789_a(0.0f, -8.0f, 0.0f, 1, 8, 1);
        this.Shape6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Shape6.func_78787_b(32, 32);
        this.Shape6.field_78809_i = true;
        this.setRotation(this.Shape6, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Shape1.func_78785_a(f5);
        this.Shape2.func_78785_a(f5);
        this.Shape3.func_78785_a(f5);
        this.Shape4.func_78785_a(f5);
        this.Shape5.func_78785_a(f5);
        this.Shape6.func_78785_a(f5);
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

