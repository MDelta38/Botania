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

public class ModelInspiratron
extends ModelBase {
    ModelRenderer Jar;
    ModelRenderer BottomA;
    ModelRenderer Bottom1B;
    ModelRenderer Top;

    public ModelInspiratron() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Jar = new ModelRenderer((ModelBase)this, 0, 0);
        this.Jar.func_78789_a(0.0f, 0.0f, 0.0f, 10, 14, 10);
        this.Jar.func_78793_a(-5.0f, 11.0f, -5.0f);
        this.Jar.func_78787_b(64, 64);
        this.Jar.field_78809_i = true;
        this.setRotation(this.Jar, 0.0f, 0.0f, 0.0f);
        this.BottomA = new ModelRenderer((ModelBase)this, 0, 39);
        this.BottomA.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.BottomA.func_78793_a(-8.0f, 23.0f, -8.0f);
        this.BottomA.func_78787_b(64, 64);
        this.BottomA.field_78809_i = true;
        this.setRotation(this.BottomA, 0.0f, 0.0f, 0.0f);
        this.Bottom1B = new ModelRenderer((ModelBase)this, 0, 24);
        this.Bottom1B.func_78789_a(0.0f, 0.0f, 0.0f, 12, 3, 12);
        this.Bottom1B.func_78793_a(-6.0f, 20.0f, -6.0f);
        this.Bottom1B.func_78787_b(64, 64);
        this.Bottom1B.field_78809_i = true;
        this.setRotation(this.Bottom1B, 0.0f, 0.0f, 0.0f);
        this.Top = new ModelRenderer((ModelBase)this, 0, 24);
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.Top.func_78793_a(-6.0f, 10.0f, -6.0f);
        this.Top.func_78787_b(64, 64);
        this.Top.field_78809_i = true;
        this.setRotation(this.Top, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Jar.func_78785_a(f5);
        this.BottomA.func_78785_a(f5);
        this.Bottom1B.func_78785_a(f5);
        this.Top.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

