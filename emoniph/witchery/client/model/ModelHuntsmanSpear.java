/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelHuntsmanSpear
extends ModelBase {
    ModelRenderer shaft;
    ModelRenderer headFront;
    ModelRenderer headSide;

    public ModelHuntsmanSpear() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.shaft = new ModelRenderer((ModelBase)this, 0, 0);
        this.shaft.func_78789_a(-0.5f, -18.0f, -0.5f, 1, 32, 1);
        this.shaft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.shaft.func_78787_b(64, 64);
        this.shaft.field_78809_i = true;
        this.setRotation(this.shaft, 0.0f, 0.0f, 0.0f);
        this.headFront = new ModelRenderer((ModelBase)this, 6, 3);
        this.headFront.func_78789_a(-1.5f, -6.0f, 0.0f, 3, 6, 0);
        this.headFront.func_78793_a(0.0f, -17.0f, 0.0f);
        this.headFront.func_78787_b(64, 64);
        this.headFront.field_78809_i = true;
        this.setRotation(this.headFront, 0.0f, 0.0f, 0.0f);
        this.headSide = new ModelRenderer((ModelBase)this, 6, 0);
        this.headSide.func_78789_a(0.0f, -6.0f, -1.5f, 0, 6, 3);
        this.headSide.func_78793_a(0.0f, -17.0f, 0.0f);
        this.headSide.func_78787_b(64, 64);
        this.headSide.field_78809_i = true;
        this.setRotation(this.headSide, 0.0f, 0.0f, 0.0f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.shaft.func_78785_a(f5);
        this.headFront.func_78785_a(f5);
        this.headSide.func_78785_a(f5);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }
}

