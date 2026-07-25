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

public class ModelSoulforge
extends ModelBase {
    ModelRenderer BrainTank;
    ModelRenderer Neck;
    ModelRenderer SoulTank;
    ModelRenderer Rod1;
    ModelRenderer Rod2;
    ModelRenderer Rod3;
    ModelRenderer Rod4;
    public ModelRenderer Brine;

    public ModelSoulforge() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.BrainTank = new ModelRenderer((ModelBase)this, 0, 0);
        this.BrainTank.func_78789_a(0.0f, 0.0f, 0.0f, 16, 8, 16);
        this.BrainTank.func_78793_a(-8.0f, 16.0f, -8.0f);
        this.BrainTank.func_78787_b(64, 64);
        this.BrainTank.field_78809_i = true;
        this.setRotation(this.BrainTank, 0.0f, 0.0f, 0.0f);
        this.Neck = new ModelRenderer((ModelBase)this, 0, 24);
        this.Neck.func_78789_a(0.0f, 0.0f, 0.0f, 5, 2, 5);
        this.Neck.func_78793_a(-2.5f, 14.0f, -2.5f);
        this.Neck.func_78787_b(64, 64);
        this.Neck.field_78809_i = true;
        this.setRotation(this.Neck, 0.0f, 0.0f, 0.0f);
        this.SoulTank = new ModelRenderer((ModelBase)this, 0, 48);
        this.SoulTank.func_78789_a(0.0f, 0.0f, 0.0f, 10, 6, 10);
        this.SoulTank.func_78793_a(-5.0f, 8.0f, -5.0f);
        this.SoulTank.func_78787_b(64, 64);
        this.SoulTank.field_78809_i = true;
        this.setRotation(this.SoulTank, 0.0f, 0.0f, 0.0f);
        this.Rod1 = new ModelRenderer((ModelBase)this, 0, 31);
        this.Rod1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Rod1.func_78793_a(-8.0f, 12.0f, -8.0f);
        this.Rod1.func_78787_b(64, 64);
        this.Rod1.field_78809_i = true;
        this.setRotation(this.Rod1, 0.0f, 0.0f, 0.0f);
        this.Rod2 = new ModelRenderer((ModelBase)this, 0, 31);
        this.Rod2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Rod2.func_78793_a(-8.0f, 12.0f, 7.0f);
        this.Rod2.func_78787_b(64, 64);
        this.Rod2.field_78809_i = true;
        this.setRotation(this.Rod2, 0.0f, 0.0f, 0.0f);
        this.Rod3 = new ModelRenderer((ModelBase)this, 0, 31);
        this.Rod3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Rod3.func_78793_a(7.0f, 12.0f, 7.0f);
        this.Rod3.func_78787_b(64, 64);
        this.Rod3.field_78809_i = true;
        this.setRotation(this.Rod3, 0.0f, 0.0f, 0.0f);
        this.Rod4 = new ModelRenderer((ModelBase)this, 0, 31);
        this.Rod4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 4, 1);
        this.Rod4.func_78793_a(7.0f, 12.0f, -8.0f);
        this.Rod4.func_78787_b(64, 64);
        this.Rod4.field_78809_i = true;
        this.setRotation(this.Rod4, 0.0f, 0.0f, 0.0f);
        this.Brine = new ModelRenderer((ModelBase)this, 0, 0);
        this.Brine.func_78789_a(-7.0f, 17.0f, -7.0f, 14, 6, 14);
        this.Brine.func_78793_a(0.0f, 0.0f, 0.0f);
        this.Brine.func_78787_b(64, 32);
        this.Brine.field_78809_i = true;
        this.setRotation(this.Brine, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.BrainTank.func_78785_a(f5);
        this.Neck.func_78785_a(f5);
        this.SoulTank.func_78785_a(f5);
        this.Rod1.func_78785_a(f5);
        this.Rod2.func_78785_a(f5);
        this.Rod3.func_78785_a(f5);
        this.Rod4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }

    public void renderBrine() {
        this.Brine.func_78785_a(0.0625f);
    }
}

