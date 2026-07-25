/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.emoniph.witchery.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBloodCrucible
extends ModelBase {
    private ModelRenderer right1;
    private ModelRenderer right2;
    private ModelRenderer left1;
    private ModelRenderer left2;
    private ModelRenderer back1;
    private ModelRenderer back2;
    private ModelRenderer front1;
    private ModelRenderer front2;
    private ModelRenderer bottom;

    public ModelBloodCrucible() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.left2 = new ModelRenderer((ModelBase)this, 17, 11);
        this.left2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.left2.func_78790_a(2.0f, -2.0f, -2.0f, 1, 1, 4, 0.0f);
        this.right2 = new ModelRenderer((ModelBase)this, 17, 11);
        this.right2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.right2.func_78790_a(-3.0f, -2.0f, -2.0f, 1, 1, 4, 0.0f);
        this.front2 = new ModelRenderer((ModelBase)this, 17, 19);
        this.front2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.front2.func_78790_a(-3.0f, -2.0f, -3.0f, 6, 1, 1, 0.0f);
        this.front1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.front1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.front1.func_78790_a(-3.5f, -5.0f, -4.0f, 7, 3, 1, 0.0f);
        this.right1 = new ModelRenderer((ModelBase)this, 0, 6);
        this.right1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.right1.func_78790_a(-4.0f, -5.0f, -3.7f, 1, 3, 7, 0.0f);
        this.bottom = new ModelRenderer((ModelBase)this, 0, 0);
        this.bottom.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bottom.func_78790_a(-2.0f, -1.0f, -2.0f, 4, 1, 4, 0.0f);
        this.back2 = new ModelRenderer((ModelBase)this, 17, 19);
        this.back2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.back2.func_78790_a(-3.0f, -2.0f, 2.0f, 6, 1, 1, 0.0f);
        this.back1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.back1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.back1.func_78790_a(-3.5f, -5.0f, 3.0f, 7, 3, 1, 0.0f);
        this.left1 = new ModelRenderer((ModelBase)this, 0, 6);
        this.left1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.left1.func_78790_a(3.0f, -5.0f, -3.5f, 1, 3, 7, 0.0f);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.left2.func_78785_a(f5);
        this.right2.func_78785_a(f5);
        this.front2.func_78785_a(f5);
        this.front1.func_78785_a(f5);
        this.right1.func_78785_a(f5);
        this.bottom.func_78785_a(f5);
        this.back2.func_78785_a(f5);
        this.back1.func_78785_a(f5);
        this.left1.func_78785_a(f5);
    }
}

