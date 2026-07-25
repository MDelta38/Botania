/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelEarmuffs
extends ModelBiped {
    private ModelRenderer earRight;
    private ModelRenderer earLeft;
    private ModelRenderer bandLeft;
    private ModelRenderer bandTop;
    private ModelRenderer bandRight;

    public ModelEarmuffs() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.bandTop = new ModelRenderer((ModelBase)this, 46, 38);
        this.bandTop.func_78789_a(-4.0f, -10.0f, -0.5f, 8, 1, 1);
        this.bandTop.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bandTop.func_78787_b(64, 64);
        this.bandTop.field_78809_i = true;
        this.setRotation(this.bandTop, 0.0f, 0.0f, 0.0f);
        this.earRight = new ModelRenderer((ModelBase)this, 33, 32);
        this.earRight.func_78789_a(-6.0f, -6.0f, -2.0f, 2, 4, 4);
        this.earRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earRight.func_78787_b(64, 64);
        this.earRight.field_78809_i = true;
        this.setRotation(this.earRight, 0.0f, 0.0f, 0.0f);
        this.earLeft = new ModelRenderer((ModelBase)this, 33, 32);
        this.earLeft.func_78789_a(4.0f, -6.0f, -2.0f, 2, 4, 4);
        this.earLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earLeft.func_78787_b(64, 64);
        this.earLeft.field_78809_i = true;
        this.setRotation(this.earLeft, 0.0f, 0.0f, 0.0f);
        this.bandLeft = new ModelRenderer((ModelBase)this, 46, 32);
        this.bandLeft.func_78789_a(4.0f, -10.0f, -0.5f, 1, 4, 1);
        this.bandLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bandLeft.func_78787_b(64, 64);
        this.bandLeft.field_78809_i = true;
        this.setRotation(this.bandLeft, 0.0f, 0.0f, 0.0f);
        this.bandRight = new ModelRenderer((ModelBase)this, 46, 32);
        this.bandRight.func_78789_a(-5.0f, -10.0f, -0.5f, 1, 4, 1);
        this.bandRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bandRight.func_78787_b(64, 64);
        this.bandRight.field_78809_i = true;
        this.setRotation(this.bandRight, 0.0f, 0.0f, 0.0f);
        this.field_78116_c.func_78792_a(this.earRight);
        this.field_78116_c.func_78792_a(this.earLeft);
        this.field_78116_c.func_78792_a(this.bandLeft);
        this.field_78116_c.func_78792_a(this.bandRight);
        this.field_78116_c.func_78792_a(this.bandTop);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }
}

