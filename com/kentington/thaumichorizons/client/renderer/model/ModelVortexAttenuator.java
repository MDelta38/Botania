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

public class ModelVortexAttenuator
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Rod;
    ModelRenderer Ring_A1;
    ModelRenderer Ring_A2;
    ModelRenderer Ring_A3;
    ModelRenderer Ring_A4;
    ModelRenderer Ring_B1;
    ModelRenderer Ring_B2;
    ModelRenderer Ring_B3;
    ModelRenderer Ring_B4;
    ModelRenderer Ring_C1;
    ModelRenderer Ring_C2;
    ModelRenderer Ring_C3;
    ModelRenderer Ring_C4;

    public ModelVortexAttenuator() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Base = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 16, 4, 16);
        this.Base.func_78793_a(-8.0f, 20.0f, -8.0f);
        this.Base.func_78787_b(64, 64);
        this.Base.field_78809_i = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Rod = new ModelRenderer((ModelBase)this, 0, 20);
        this.Rod.func_78789_a(0.0f, 0.0f, 0.0f, 2, 12, 2);
        this.Rod.func_78793_a(-1.0f, 8.0f, -1.0f);
        this.Rod.func_78787_b(64, 64);
        this.Rod.field_78809_i = true;
        this.setRotation(this.Rod, 0.0f, 0.0f, 0.0f);
        this.Ring_A1 = new ModelRenderer((ModelBase)this, 0, 34);
        this.Ring_A1.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 1);
        this.Ring_A1.func_78793_a(-6.0f, 18.0f, -6.0f);
        this.Ring_A1.func_78787_b(64, 64);
        this.Ring_A1.field_78809_i = true;
        this.setRotation(this.Ring_A1, 0.0f, 0.0f, 0.0f);
        this.Ring_A2 = new ModelRenderer((ModelBase)this, 0, 34);
        this.Ring_A2.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 1);
        this.Ring_A2.func_78793_a(-6.0f, 18.0f, 5.0f);
        this.Ring_A2.func_78787_b(64, 64);
        this.Ring_A2.field_78809_i = true;
        this.setRotation(this.Ring_A2, 0.0f, 0.0f, 0.0f);
        this.Ring_A3 = new ModelRenderer((ModelBase)this, 0, 36);
        this.Ring_A3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 10);
        this.Ring_A3.func_78793_a(5.0f, 18.0f, -5.0f);
        this.Ring_A3.func_78787_b(64, 64);
        this.Ring_A3.field_78809_i = true;
        this.setRotation(this.Ring_A3, 0.0f, 0.0f, 0.0f);
        this.Ring_A4 = new ModelRenderer((ModelBase)this, 0, 36);
        this.Ring_A4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 10);
        this.Ring_A4.func_78793_a(-6.0f, 18.0f, -5.0f);
        this.Ring_A4.func_78787_b(64, 64);
        this.Ring_A4.field_78809_i = true;
        this.setRotation(this.Ring_A4, 0.0f, 0.0f, 0.0f);
        this.Ring_B1 = new ModelRenderer((ModelBase)this, 0, 47);
        this.Ring_B1.func_78789_a(0.0f, 0.0f, 0.0f, 8, 1, 1);
        this.Ring_B1.func_78793_a(-4.0f, 15.0f, 3.0f);
        this.Ring_B1.func_78787_b(64, 64);
        this.Ring_B1.field_78809_i = true;
        this.setRotation(this.Ring_B1, 0.0f, 0.0f, 0.0f);
        this.Ring_B2 = new ModelRenderer((ModelBase)this, 0, 47);
        this.Ring_B2.func_78789_a(0.0f, 0.0f, 0.0f, 8, 1, 1);
        this.Ring_B2.func_78793_a(-4.0f, 15.0f, -4.0f);
        this.Ring_B2.func_78787_b(64, 64);
        this.Ring_B2.field_78809_i = true;
        this.setRotation(this.Ring_B2, 0.0f, 0.0f, 0.0f);
        this.Ring_B3 = new ModelRenderer((ModelBase)this, 0, 49);
        this.Ring_B3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Ring_B3.func_78793_a(3.0f, 15.0f, -3.0f);
        this.Ring_B3.func_78787_b(64, 64);
        this.Ring_B3.field_78809_i = true;
        this.setRotation(this.Ring_B3, 0.0f, 0.0f, 0.0f);
        this.Ring_B4 = new ModelRenderer((ModelBase)this, 0, 49);
        this.Ring_B4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 6);
        this.Ring_B4.func_78793_a(-4.0f, 15.0f, -3.0f);
        this.Ring_B4.func_78787_b(64, 64);
        this.Ring_B4.field_78809_i = true;
        this.setRotation(this.Ring_B4, 0.0f, 0.0f, 0.0f);
        this.Ring_C1 = new ModelRenderer((ModelBase)this, 0, 56);
        this.Ring_C1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Ring_C1.func_78793_a(-2.0f, 12.0f, -2.0f);
        this.Ring_C1.func_78787_b(64, 64);
        this.Ring_C1.field_78809_i = true;
        this.setRotation(this.Ring_C1, 0.0f, 0.0f, 0.0f);
        this.Ring_C2 = new ModelRenderer((ModelBase)this, 0, 56);
        this.Ring_C2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 1);
        this.Ring_C2.func_78793_a(-2.0f, 12.0f, 1.0f);
        this.Ring_C2.func_78787_b(64, 64);
        this.Ring_C2.field_78809_i = true;
        this.setRotation(this.Ring_C2, 0.0f, 0.0f, 0.0f);
        this.Ring_C3 = new ModelRenderer((ModelBase)this, 0, 58);
        this.Ring_C3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.Ring_C3.func_78793_a(1.0f, 12.0f, -1.0f);
        this.Ring_C3.func_78787_b(64, 64);
        this.Ring_C3.field_78809_i = true;
        this.setRotation(this.Ring_C3, 0.0f, 0.0f, 0.0f);
        this.Ring_C4 = new ModelRenderer((ModelBase)this, 0, 58);
        this.Ring_C4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 1, 2);
        this.Ring_C4.func_78793_a(-2.0f, 12.0f, -1.0f);
        this.Ring_C4.func_78787_b(64, 64);
        this.Ring_C4.field_78809_i = true;
        this.setRotation(this.Ring_C4, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Rod.func_78785_a(f5);
        this.Ring_A1.func_78785_a(f5);
        this.Ring_A2.func_78785_a(f5);
        this.Ring_A3.func_78785_a(f5);
        this.Ring_A4.func_78785_a(f5);
        this.Ring_B1.func_78785_a(f5);
        this.Ring_B2.func_78785_a(f5);
        this.Ring_B3.func_78785_a(f5);
        this.Ring_B4.func_78785_a(f5);
        this.Ring_C1.func_78785_a(f5);
        this.Ring_C2.func_78785_a(f5);
        this.Ring_C3.func_78785_a(f5);
        this.Ring_C4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, null);
    }
}

