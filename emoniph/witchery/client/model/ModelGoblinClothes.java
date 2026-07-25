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
public class ModelGoblinClothes
extends ModelBiped {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer quiver;
    ModelRenderer arrow1;
    ModelRenderer feathers;
    ModelRenderer arrow2;

    public ModelGoblinClothes(float scale) {
        super(scale, 0.0f, 64, 32);
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.quiver = new ModelRenderer((ModelBase)this, 33, 0);
        this.quiver.func_78789_a(-2.0f, -3.0f, 0.0f, 4, 7, 1);
        this.quiver.func_78793_a(0.0f, 7.0f, 2.0f);
        this.quiver.func_78787_b(64, 32);
        this.quiver.field_78809_i = true;
        this.setRotation(this.quiver, 0.0f, 0.0f, -0.3490659f);
        this.arrow1 = new ModelRenderer((ModelBase)this, 44, 4);
        this.arrow1.func_78789_a(0.5f, -5.0f, 0.0f, 1, 2, 1);
        this.arrow1.func_78793_a(0.0f, 7.0f, 2.0f);
        this.arrow1.func_78787_b(64, 32);
        this.arrow1.field_78809_i = true;
        this.setRotation(this.arrow1, 0.0f, 0.0f, -0.3490659f);
        this.feathers = new ModelRenderer((ModelBase)this, 44, 0);
        this.feathers.func_78789_a(-2.0f, -7.0f, 0.0f, 4, 2, 1);
        this.feathers.func_78793_a(0.0f, 7.0f, 2.0f);
        this.feathers.func_78787_b(64, 32);
        this.feathers.field_78809_i = true;
        this.setRotation(this.feathers, 0.0f, 0.0f, -0.3490659f);
        this.arrow2 = new ModelRenderer((ModelBase)this, 44, 4);
        this.arrow2.func_78789_a(-1.5f, -5.0f, 0.0f, 1, 2, 1);
        this.arrow2.func_78793_a(0.0f, 7.0f, 2.0f);
        this.arrow2.func_78787_b(64, 32);
        this.arrow2.field_78809_i = true;
        this.setRotation(this.arrow2, 0.0f, 0.0f, -0.3490659f);
        this.field_78115_e.func_78792_a(this.quiver);
        this.field_78115_e.func_78792_a(this.arrow1);
        this.field_78115_e.func_78792_a(this.arrow2);
        this.field_78115_e.func_78792_a(this.feathers);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }
}

