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

public class ModelBloodInfuser
extends ModelBase {
    ModelRenderer Body;
    ModelRenderer SyringeSlot;
    ModelRenderer Pipe1A;
    ModelRenderer Pipe1B;
    ModelRenderer Pipe2A;
    ModelRenderer Pipe2B;
    ModelRenderer Pipe3A;
    ModelRenderer Pipe3B;
    ModelRenderer Pipe4A;
    ModelRenderer Pipe4B;

    public ModelBloodInfuser() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Body = new ModelRenderer((ModelBase)this, 0, 0);
        this.Body.func_78789_a(0.0f, 0.0f, 0.0f, 16, 10, 16);
        this.Body.func_78793_a(-8.0f, 14.0f, -8.0f);
        this.Body.func_78787_b(64, 64);
        this.Body.field_78809_i = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.SyringeSlot = new ModelRenderer((ModelBase)this, 0, 32);
        this.SyringeSlot.func_78789_a(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.SyringeSlot.func_78793_a(-3.0f, 8.0f, -3.0f);
        this.SyringeSlot.func_78787_b(64, 64);
        this.SyringeSlot.field_78809_i = true;
        this.setRotation(this.SyringeSlot, 0.0f, 0.0f, 0.0f);
        this.Pipe1A = new ModelRenderer((ModelBase)this, 0, 48);
        this.Pipe1A.func_78789_a(0.0f, 0.0f, 0.0f, 2, 2, 3);
        this.Pipe1A.func_78793_a(-1.0f, 9.0f, 3.0f);
        this.Pipe1A.func_78787_b(64, 64);
        this.Pipe1A.field_78809_i = true;
        this.setRotation(this.Pipe1A, 0.0f, 0.0f, 0.0f);
        this.Pipe1B = new ModelRenderer((ModelBase)this, 0, 54);
        this.Pipe1B.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Pipe1B.func_78793_a(-1.0f, 11.0f, 4.0f);
        this.Pipe1B.func_78787_b(64, 64);
        this.Pipe1B.field_78809_i = true;
        this.setRotation(this.Pipe1B, 0.0f, 0.0f, 0.0f);
        this.Pipe2A = new ModelRenderer((ModelBase)this, 0, 48);
        this.Pipe2A.func_78789_a(0.0f, 0.0f, 0.0f, 2, 2, 3);
        this.Pipe2A.func_78793_a(-1.0f, 9.0f, -6.0f);
        this.Pipe2A.func_78787_b(64, 64);
        this.Pipe2A.field_78809_i = true;
        this.setRotation(this.Pipe2A, 0.0f, 0.0f, 0.0f);
        this.Pipe2B = new ModelRenderer((ModelBase)this, 0, 54);
        this.Pipe2B.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Pipe2B.func_78793_a(-1.0f, 11.0f, -6.0f);
        this.Pipe2B.func_78787_b(64, 64);
        this.Pipe2B.field_78809_i = true;
        this.setRotation(this.Pipe2B, 0.0f, 0.0f, 0.0f);
        this.Pipe3A = new ModelRenderer((ModelBase)this, 0, 48);
        this.Pipe3A.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 2);
        this.Pipe3A.func_78793_a(3.0f, 9.0f, -1.0f);
        this.Pipe3A.func_78787_b(64, 64);
        this.Pipe3A.field_78809_i = true;
        this.setRotation(this.Pipe3A, 0.0f, 0.0f, 0.0f);
        this.Pipe3B = new ModelRenderer((ModelBase)this, 0, 54);
        this.Pipe3B.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Pipe3B.func_78793_a(4.0f, 11.0f, -1.0f);
        this.Pipe3B.func_78787_b(64, 64);
        this.Pipe3B.field_78809_i = true;
        this.setRotation(this.Pipe3B, 0.0f, 0.0f, 0.0f);
        this.Pipe4A = new ModelRenderer((ModelBase)this, 0, 48);
        this.Pipe4A.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 2);
        this.Pipe4A.func_78793_a(-6.0f, 9.0f, -1.0f);
        this.Pipe4A.func_78787_b(64, 64);
        this.Pipe4A.field_78809_i = true;
        this.setRotation(this.Pipe4A, 0.0f, 0.0f, 0.0f);
        this.Pipe4B = new ModelRenderer((ModelBase)this, 0, 54);
        this.Pipe4B.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 2);
        this.Pipe4B.func_78793_a(-6.0f, 11.0f, -1.0f);
        this.Pipe4B.func_78787_b(64, 64);
        this.Pipe4B.field_78809_i = true;
        this.setRotation(this.Pipe4B, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.Body.func_78785_a(f5);
        this.SyringeSlot.func_78785_a(f5);
        this.Pipe1A.func_78785_a(f5);
        this.Pipe1B.func_78785_a(f5);
        this.Pipe2A.func_78785_a(f5);
        this.Pipe2B.func_78785_a(f5);
        this.Pipe3A.func_78785_a(f5);
        this.Pipe3B.func_78785_a(f5);
        this.Pipe4A.func_78785_a(f5);
        this.Pipe4B.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

