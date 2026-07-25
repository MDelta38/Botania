/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import org.lwjgl.opengl.GL11;

public class ModelInjector
extends ModelBase {
    ModelRenderer Drum;
    ModelRenderer Front;
    ModelRenderer BowL1;
    ModelRenderer BowR1;
    ModelRenderer BowL2;
    ModelRenderer BowR2;
    ModelRenderer Stock;
    ModelRenderer Grip;
    ModelRenderer Thingy;

    public ModelInjector() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Drum = new ModelRenderer((ModelBase)this, 0, 0);
        this.Drum.func_78789_a(-2.5f, -2.5f, 0.0f, 5, 5, 1);
        this.Drum.func_78793_a(2.5f, 2.25f, 0.0f);
        this.Drum.func_78787_b(64, 32);
        this.Drum.field_78809_i = true;
        this.setRotation(this.Drum, 0.0f, 0.0f, 0.0f);
        this.Front = new ModelRenderer((ModelBase)this, 12, 0);
        this.Front.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 8);
        this.Front.func_78793_a(1.0f, 1.0f, -8.0f);
        this.Front.func_78787_b(64, 32);
        this.Front.field_78809_i = true;
        this.setRotation(this.Front, 0.0f, 0.0f, 0.0f);
        this.BowL1 = new ModelRenderer((ModelBase)this, 0, 6);
        this.BowL1.func_78789_a(0.0f, -1.0f, 0.0f, 4, 1, 1);
        this.BowL1.func_78793_a(2.5f, 1.0f, -8.0f);
        this.BowL1.func_78787_b(64, 32);
        this.BowL1.field_78809_i = true;
        this.setRotation(this.BowL1, 0.0f, 0.0f, 0.0f);
        this.BowR1 = new ModelRenderer((ModelBase)this, 0, 6);
        this.BowR1.func_78789_a(0.0f, -1.0f, -1.0f, 4, 1, 1);
        this.BowR1.func_78793_a(2.5f, 1.0f, -8.0f);
        this.BowR1.func_78787_b(64, 32);
        this.BowR1.field_78809_i = true;
        this.setRotation(this.BowR1, 0.0f, 3.141593f, 0.0f);
        this.BowL2 = new ModelRenderer((ModelBase)this, 0, 8);
        this.BowL2.func_78789_a(0.0f, -1.0f, 0.0f, 3, 1, 1);
        this.BowL2.func_78793_a(6.0f, 1.0f, -7.5f);
        this.BowL2.func_78787_b(64, 32);
        this.BowL2.field_78809_i = true;
        this.setRotation(this.BowL2, 0.0f, -0.3490659f, 0.0f);
        this.BowR2 = new ModelRenderer((ModelBase)this, 0, 8);
        this.BowR2.func_78789_a(0.0f, -1.0f, -1.0f, 3, 1, 1);
        this.BowR2.func_78793_a(-1.0f, 1.0f, -7.5f);
        this.BowR2.func_78787_b(64, 32);
        this.BowR2.field_78809_i = true;
        this.setRotation(this.BowR2, 0.0f, 3.490659f, 0.0f);
        this.Thingy = new ModelRenderer((ModelBase)this, 13, 0);
        this.Thingy.func_78789_a(-0.5f, 0.0f, 0.5f, 1, 1, 1);
        this.Thingy.func_78793_a((this.BowR2.field_78800_c + this.BowL2.field_78800_c) / 2.0f, 0.0f, -6.5f);
        this.Thingy.func_78787_b(64, 32);
        this.Thingy.field_78809_i = true;
        this.Stock = new ModelRenderer((ModelBase)this, 0, 10);
        this.Stock.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 3);
        this.Stock.func_78793_a(1.0f, 1.0f, 1.0f);
        this.Stock.func_78787_b(64, 32);
        this.Stock.field_78809_i = true;
        this.setRotation(this.Stock, 0.0f, 0.0f, 0.0f);
        this.Grip = new ModelRenderer((ModelBase)this, 0, 14);
        this.Grip.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 1);
        this.Grip.func_78793_a(1.0f, 2.0f, 3.0f);
        this.Grip.func_78787_b(64, 32);
        this.Grip.field_78809_i = true;
        this.setRotation(this.Grip, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Drum.func_78785_a(f5);
        this.Front.func_78785_a(f5);
        this.BowL1.func_78785_a(f5);
        this.BowR1.func_78785_a(f5);
        this.BowL2.func_78785_a(f5);
        this.BowR2.func_78785_a(f5);
        this.Stock.func_78785_a(f5);
        this.Grip.func_78785_a(f5);
        GL11.glDisable((int)3553);
        GL11.glDisable((int)2896);
        Tessellator tessellator = Tessellator.field_78398_a;
        tessellator.func_78371_b(3);
        tessellator.func_78378_d(0);
        tessellator.func_78377_a((double)this.BowR2.field_78800_c + 0.6 + (double)f3 * 0.25, (double)this.BowR2.field_78797_d - 0.9375, (double)this.BowR2.field_78798_e + 6.8125 + (double)f3 * 0.125);
        tessellator.func_78377_a((double)((this.BowR2.field_78800_c + this.BowL2.field_78800_c) / 2.0f) - 2.175, (double)this.BowR2.field_78797_d - 0.9375, (double)this.BowR2.field_78798_e + 6.8125 + (double)f3 * 0.65);
        tessellator.func_78377_a((double)this.BowL2.field_78800_c - 4.95 - (double)f3 * 0.25, (double)this.BowL2.field_78797_d - 0.9375, (double)this.BowL2.field_78798_e + 6.8125 + (double)f3 * 0.125);
        tessellator.func_78381_a();
        GL11.glEnable((int)2896);
        GL11.glEnable((int)3553);
        this.Thingy.func_78793_a((this.BowR2.field_78800_c + this.BowL2.field_78800_c) / 2.0f, 0.0f, -6.5f + 5.0f * f3);
        this.Thingy.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, ent);
        this.BowL1.field_78796_g = -f;
        this.BowR1.field_78796_g = f + (float)Math.PI;
        this.BowL2.field_78796_g = -0.34906238f - f1;
        this.BowR2.field_78796_g = 3.490655f + f1;
        this.Drum.field_78808_h = f2 * 2.0f * (float)Math.PI / 180.0f;
    }
}

