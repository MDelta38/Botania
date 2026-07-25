/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFamiliarHat
extends ModelBase {
    ModelRenderer HatBase;
    ModelRenderer HatA;
    ModelRenderer HatB;
    ModelRenderer HatC;
    ModelRenderer GoldBuckle;

    public ModelFamiliarHat() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.HatBase = new ModelRenderer((ModelBase)this, 0, 0);
        this.HatBase.func_78789_a(0.0f, 0.0f, 0.0f, 7, 1, 7);
        this.HatBase.func_78793_a(0.0f, 0.0f, 0.0f);
        this.HatBase.func_78787_b(32, 32);
        this.HatBase.field_78809_i = true;
        this.setRotation(this.HatBase, 0.0f, 0.0f, 0.0f);
        this.HatA = new ModelRenderer((ModelBase)this, 0, 8);
        this.HatA.func_78789_a(0.0f, -1.0f, 1.0f, 5, 2, 5);
        this.HatA.func_78793_a(1.0f, -1.0f, 0.0f);
        this.HatA.func_78787_b(32, 32);
        this.HatA.field_78809_i = true;
        this.setRotation(this.HatA, 0.0f, 0.0f, 0.0f);
        this.HatB = new ModelRenderer((ModelBase)this, 0, 15);
        this.HatB.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 3);
        this.HatB.func_78793_a(2.0f, -4.0f, 2.0f);
        this.HatB.func_78787_b(32, 32);
        this.HatB.field_78809_i = true;
        this.setRotation(this.HatB, 0.0f, 0.0f, 0.0f);
        this.HatC = new ModelRenderer((ModelBase)this, 0, 20);
        this.HatC.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.HatC.func_78793_a(3.0f, -5.0f, 3.0f);
        this.HatC.func_78787_b(32, 32);
        this.HatC.field_78809_i = true;
        this.setRotation(this.HatC, 0.0f, 0.0f, 0.0f);
        this.GoldBuckle = new ModelRenderer((ModelBase)this, 0, 22);
        this.GoldBuckle.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 1);
        this.GoldBuckle.func_78793_a(0.0f, -1.0f, 3.0f);
        this.GoldBuckle.func_78787_b(32, 32);
        this.GoldBuckle.field_78809_i = true;
        this.setRotation(this.GoldBuckle, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.HatBase.func_78785_a(f5);
        this.HatA.func_78785_a(f5);
        this.HatB.func_78785_a(f5);
        this.HatC.func_78785_a(f5);
        this.GoldBuckle.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

