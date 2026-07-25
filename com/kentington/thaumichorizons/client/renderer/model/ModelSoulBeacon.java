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

public class ModelSoulBeacon
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Core;
    ModelRenderer Claw1A;
    ModelRenderer Claw2A;
    ModelRenderer Claw3A;
    ModelRenderer Claw4A;
    ModelRenderer Claw3B;
    ModelRenderer Claw4B;
    ModelRenderer Claw1B;
    ModelRenderer Claw2B;
    ModelRenderer Claw1C;
    ModelRenderer Claw2C;
    ModelRenderer Claw3C;
    ModelRenderer Claw4C;

    public ModelSoulBeacon() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Base = new ModelRenderer((ModelBase)this, 0, 23);
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.Base.func_78793_a(-8.0f, 23.0f, -8.0f);
        this.Base.func_78787_b(64, 64);
        this.Base.field_78809_i = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Core = new ModelRenderer((ModelBase)this, 0, 0);
        this.Core.func_78789_a(0.0f, 0.0f, 0.0f, 12, 11, 12);
        this.Core.func_78793_a(-6.0f, 12.0f, -6.0f);
        this.Core.func_78787_b(64, 64);
        this.Core.field_78809_i = true;
        this.setRotation(this.Core, 0.0f, 0.0f, 0.0f);
        this.Claw1A = new ModelRenderer((ModelBase)this, 48, 0);
        this.Claw1A.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
        this.Claw1A.func_78793_a(6.0f, 12.0f, -1.0f);
        this.Claw1A.func_78787_b(64, 64);
        this.Claw1A.field_78809_i = true;
        this.setRotation(this.Claw1A, 0.0f, 0.0f, 0.0f);
        this.Claw2A = new ModelRenderer((ModelBase)this, 48, 0);
        this.Claw2A.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
        this.Claw2A.func_78793_a(-8.0f, 12.0f, -1.0f);
        this.Claw2A.func_78787_b(64, 64);
        this.Claw2A.field_78809_i = true;
        this.setRotation(this.Claw2A, 0.0f, 0.0f, 0.0f);
        this.Claw3A = new ModelRenderer((ModelBase)this, 48, 0);
        this.Claw3A.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
        this.Claw3A.func_78793_a(-1.0f, 12.0f, -8.0f);
        this.Claw3A.func_78787_b(64, 64);
        this.Claw3A.field_78809_i = true;
        this.setRotation(this.Claw3A, 0.0f, 0.0f, 0.0f);
        this.Claw4A = new ModelRenderer((ModelBase)this, 48, 0);
        this.Claw4A.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 2);
        this.Claw4A.func_78793_a(-1.0f, 12.0f, 6.0f);
        this.Claw4A.func_78787_b(64, 64);
        this.Claw4A.field_78809_i = true;
        this.setRotation(this.Claw4A, 0.0f, 0.0f, 0.0f);
        this.Claw3B = new ModelRenderer((ModelBase)this, 48, 8);
        this.Claw3B.func_78789_a(0.0f, 0.0f, 0.0f, 2, 4, 1);
        this.Claw3B.func_78793_a(-1.0f, 8.0f, -8.0f);
        this.Claw3B.func_78787_b(64, 64);
        this.Claw3B.field_78809_i = true;
        this.setRotation(this.Claw3B, 0.0f, 0.0f, 0.0f);
        this.Claw4B = new ModelRenderer((ModelBase)this, 48, 8);
        this.Claw4B.func_78789_a(0.0f, 0.0f, 0.0f, 2, 4, 1);
        this.Claw4B.func_78793_a(-1.0f, 8.0f, 7.0f);
        this.Claw4B.func_78787_b(64, 64);
        this.Claw4B.field_78809_i = true;
        this.setRotation(this.Claw4B, 0.0f, 0.0f, 0.0f);
        this.Claw1B = new ModelRenderer((ModelBase)this, 48, 16);
        this.Claw1B.func_78789_a(0.0f, 0.0f, 0.0f, 1, 4, 2);
        this.Claw1B.func_78793_a(7.0f, 8.0f, -1.0f);
        this.Claw1B.func_78787_b(64, 64);
        this.Claw1B.field_78809_i = true;
        this.setRotation(this.Claw1B, 0.0f, 0.0f, 0.0f);
        this.Claw2B = new ModelRenderer((ModelBase)this, 48, 16);
        this.Claw2B.func_78789_a(0.0f, 0.0f, 0.0f, 1, 4, 2);
        this.Claw2B.func_78793_a(-8.0f, 8.0f, -1.0f);
        this.Claw2B.func_78787_b(64, 64);
        this.Claw2B.field_78809_i = true;
        this.setRotation(this.Claw2B, 0.0f, 0.0f, 0.0f);
        this.Claw1C = new ModelRenderer((ModelBase)this, 48, 48);
        this.Claw1C.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 2);
        this.Claw1C.func_78793_a(3.0f, 8.0f, -1.0f);
        this.Claw1C.func_78787_b(64, 64);
        this.Claw1C.field_78809_i = true;
        this.setRotation(this.Claw1C, 0.0f, 0.0f, 0.0f);
        this.Claw2C = new ModelRenderer((ModelBase)this, 48, 48);
        this.Claw2C.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 2);
        this.Claw2C.func_78793_a(-7.0f, 8.0f, -1.0f);
        this.Claw2C.func_78787_b(64, 64);
        this.Claw2C.field_78809_i = true;
        this.setRotation(this.Claw2C, 0.0f, 0.0f, 0.0f);
        this.Claw3C = new ModelRenderer((ModelBase)this, 0, 48);
        this.Claw3C.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 4);
        this.Claw3C.func_78793_a(-1.0f, 8.0f, -7.0f);
        this.Claw3C.func_78787_b(64, 64);
        this.Claw3C.field_78809_i = true;
        this.setRotation(this.Claw3C, 0.0f, 0.0f, 0.0f);
        this.Claw4C = new ModelRenderer((ModelBase)this, 0, 48);
        this.Claw4C.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 4);
        this.Claw4C.func_78793_a(-1.0f, 8.0f, 3.0f);
        this.Claw4C.func_78787_b(64, 64);
        this.Claw4C.field_78809_i = true;
        this.setRotation(this.Claw4C, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Base.func_78785_a(f5);
        this.Core.func_78785_a(f5);
        this.Claw1A.func_78785_a(f5);
        this.Claw2A.func_78785_a(f5);
        this.Claw3A.func_78785_a(f5);
        this.Claw4A.func_78785_a(f5);
        this.Claw3B.func_78785_a(f5);
        this.Claw4B.func_78785_a(f5);
        this.Claw1B.func_78785_a(f5);
        this.Claw2B.func_78785_a(f5);
        this.Claw1C.func_78785_a(f5);
        this.Claw2C.func_78785_a(f5);
        this.Claw3C.func_78785_a(f5);
        this.Claw4C.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, ent);
    }
}

