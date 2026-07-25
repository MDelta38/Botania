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

public class ModelRecombinator
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Pearl;
    ModelRenderer Claw1A;
    ModelRenderer Claw2A;
    ModelRenderer Claw3A;
    ModelRenderer Claw4A;
    ModelRenderer EndA;
    ModelRenderer Shape1;
    ModelRenderer Claw1B;
    ModelRenderer Claw2B;
    ModelRenderer Claw3B;
    ModelRenderer Claw4B;

    public ModelRecombinator() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Base = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 16, 4, 16);
        this.Base.func_78793_a(-8.0f, 20.0f, -8.0f);
        this.Base.func_78787_b(64, 64);
        this.Base.field_78809_i = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Pearl = new ModelRenderer((ModelBase)this, 0, 20);
        this.Pearl.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Pearl.func_78793_a(-2.0f, 14.0f, -2.0f);
        this.Pearl.func_78787_b(64, 64);
        this.Pearl.field_78809_i = true;
        this.setRotation(this.Pearl, 0.0f, 0.0f, 0.0f);
        this.Claw1A = new ModelRenderer((ModelBase)this, 0, 28);
        this.Claw1A.func_78789_a(0.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Claw1A.func_78793_a(-1.0f, 12.0f, 7.0f);
        this.Claw1A.func_78787_b(64, 64);
        this.Claw1A.field_78809_i = true;
        this.setRotation(this.Claw1A, 0.0f, 0.0f, 0.0f);
        this.Claw2A = new ModelRenderer((ModelBase)this, 0, 28);
        this.Claw2A.func_78789_a(0.0f, 0.0f, 0.0f, 2, 8, 1);
        this.Claw2A.func_78793_a(-1.0f, 12.0f, -8.0f);
        this.Claw2A.func_78787_b(64, 64);
        this.Claw2A.field_78809_i = true;
        this.setRotation(this.Claw2A, 0.0f, 0.0f, 0.0f);
        this.Claw3A = new ModelRenderer((ModelBase)this, 6, 28);
        this.Claw3A.func_78789_a(0.0f, 0.0f, 0.0f, 1, 8, 2);
        this.Claw3A.func_78793_a(-8.0f, 12.0f, -1.0f);
        this.Claw3A.func_78787_b(64, 64);
        this.Claw3A.field_78809_i = true;
        this.setRotation(this.Claw3A, 0.0f, 0.0f, 0.0f);
        this.Claw4A = new ModelRenderer((ModelBase)this, 6, 28);
        this.Claw4A.func_78789_a(0.0f, 0.0f, 0.0f, 1, 8, 2);
        this.Claw4A.func_78793_a(7.0f, 12.0f, -1.0f);
        this.Claw4A.func_78787_b(64, 64);
        this.Claw4A.field_78809_i = true;
        this.setRotation(this.Claw4A, 0.0f, 0.0f, 0.0f);
        this.EndA = new ModelRenderer((ModelBase)this, 0, 48);
        this.EndA.func_78789_a(0.0f, 0.0f, 0.0f, 4, 2, 4);
        this.EndA.func_78793_a(-2.0f, 8.0f, -2.0f);
        this.EndA.func_78787_b(64, 64);
        this.EndA.field_78809_i = true;
        this.setRotation(this.EndA, 0.0f, 0.0f, 0.0f);
        this.Shape1 = new ModelRenderer((ModelBase)this, 0, 38);
        this.Shape1.func_78789_a(0.0f, 0.0f, 0.0f, 8, 2, 8);
        this.Shape1.func_78793_a(-4.0f, 10.0f, -4.0f);
        this.Shape1.func_78787_b(64, 64);
        this.Shape1.field_78809_i = true;
        this.setRotation(this.Shape1, 0.0f, 0.0f, 0.0f);
        this.Claw1B = new ModelRenderer((ModelBase)this, 0, 54);
        this.Claw1B.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 4);
        this.Claw1B.func_78793_a(-1.0f, 11.0f, 4.0f);
        this.Claw1B.func_78787_b(64, 64);
        this.Claw1B.field_78809_i = true;
        this.setRotation(this.Claw1B, 0.0f, 0.0f, 0.0f);
        this.Claw2B = new ModelRenderer((ModelBase)this, 0, 54);
        this.Claw2B.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 4);
        this.Claw2B.func_78793_a(-1.0f, 11.0f, -8.0f);
        this.Claw2B.func_78787_b(64, 64);
        this.Claw2B.field_78809_i = true;
        this.setRotation(this.Claw2B, 0.0f, 0.0f, 0.0f);
        this.Claw3B = new ModelRenderer((ModelBase)this, 0, 59);
        this.Claw3B.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 2);
        this.Claw3B.func_78793_a(4.0f, 11.0f, -1.0f);
        this.Claw3B.func_78787_b(64, 64);
        this.Claw3B.field_78809_i = true;
        this.setRotation(this.Claw3B, 0.0f, 0.0f, 0.0f);
        this.Claw4B = new ModelRenderer((ModelBase)this, 0, 59);
        this.Claw4B.func_78789_a(0.0f, 0.0f, 0.0f, 4, 1, 2);
        this.Claw4B.func_78793_a(-8.0f, 11.0f, -1.0f);
        this.Claw4B.func_78787_b(64, 64);
        this.Claw4B.field_78809_i = true;
        this.setRotation(this.Claw4B, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, float motion) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.Base.func_78785_a(f5);
        this.Pearl.func_78793_a(-2.0f, 14.0f + motion, -2.0f);
        this.Pearl.func_78785_a(f5);
        this.Claw1A.func_78785_a(f5);
        this.Claw2A.func_78785_a(f5);
        this.Claw3A.func_78785_a(f5);
        this.Claw4A.func_78785_a(f5);
        this.EndA.func_78785_a(f5);
        this.Shape1.func_78785_a(f5);
        this.Claw1B.func_78785_a(f5);
        this.Claw2B.func_78785_a(f5);
        this.Claw3B.func_78785_a(f5);
        this.Claw4B.func_78785_a(f5);
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

