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

public class ModelVat
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Water;
    ModelRenderer Glass;
    ModelRenderer Top;
    ModelRenderer Hatch;

    public ModelVat() {
        this.field_78090_t = 512;
        this.field_78089_u = 512;
        this.Base = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 48, 16, 48);
        this.Base.func_78793_a(-24.0f, 8.0f, -24.0f);
        this.Base.func_78787_b(512, 512);
        this.Base.field_78809_i = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Water = new ModelRenderer((ModelBase)this, 256, 0);
        this.Water.func_78789_a(0.0f, 0.0f, 0.0f, 40, 32, 40);
        this.Water.func_78793_a(-20.0f, -24.0f, -20.0f);
        this.Water.func_78787_b(512, 512);
        this.Water.field_78809_i = true;
        this.setRotation(this.Water, 0.0f, 0.0f, 0.0f);
        this.Glass = new ModelRenderer((ModelBase)this, 0, 128);
        this.Glass.func_78789_a(0.0f, 0.0f, 0.0f, 48, 32, 48);
        this.Glass.func_78793_a(-24.0f, -24.0f, -24.0f);
        this.Glass.func_78787_b(512, 512);
        this.Glass.field_78809_i = true;
        this.setRotation(this.Glass, 0.0f, 0.0f, 0.0f);
        this.Top = new ModelRenderer((ModelBase)this, 256, 128);
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 48, 14, 48);
        this.Top.func_78793_a(-24.0f, -38.0f, -24.0f);
        this.Top.func_78787_b(512, 512);
        this.Top.field_78809_i = true;
        this.setRotation(this.Top, 0.0f, 0.0f, 0.0f);
        this.Hatch = new ModelRenderer((ModelBase)this, 0, 80);
        this.Hatch.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 16);
        this.Hatch.func_78793_a(-8.0f, -40.0f, -8.0f);
        this.Hatch.func_78787_b(512, 512);
        this.Hatch.field_78809_i = true;
        this.setRotation(this.Hatch, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Water.func_78785_a(f5);
        this.Glass.func_78785_a(f5);
        this.Top.func_78785_a(f5);
        this.Hatch.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

