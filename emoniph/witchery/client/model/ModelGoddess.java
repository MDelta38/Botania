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
public class ModelGoddess
extends ModelBase {
    ModelRenderer head;
    ModelRenderer cleave;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer dressLower;
    ModelRenderer dressMiddle;

    public ModelGoddess() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head.func_78787_b(64, 64);
        this.head.field_78809_i = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.cleave = new ModelRenderer((ModelBase)this, 35, 0);
        this.cleave.func_78789_a(0.0f, -2.0f, -2.0f, 8, 4, 4);
        this.cleave.func_78793_a(-4.0f, 3.0f, -2.0f);
        this.cleave.func_78787_b(64, 64);
        this.cleave.field_78809_i = true;
        this.setRotation(this.cleave, -0.7853982f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78789_a(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78787_b(64, 64);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.func_78789_a(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.rightarm.func_78787_b(64, 64);
        this.rightarm.field_78809_i = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.func_78789_a(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.leftarm.func_78787_b(64, 64);
        this.leftarm.field_78809_i = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.rightleg.func_78787_b(64, 64);
        this.rightleg.field_78809_i = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.leftleg.func_78787_b(64, 64);
        this.leftleg.field_78809_i = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.dressLower = new ModelRenderer((ModelBase)this, 0, 45);
        this.dressLower.func_78789_a(-6.0f, 0.0f, -4.0f, 12, 7, 8);
        this.dressLower.func_78793_a(0.0f, 17.0f, 0.0f);
        this.dressLower.func_78787_b(64, 64);
        this.dressLower.field_78809_i = true;
        this.setRotation(this.dressLower, 0.0f, 0.0f, 0.0f);
        this.dressMiddle = new ModelRenderer((ModelBase)this, 0, 33);
        this.dressMiddle.func_78789_a(-5.0f, 0.0f, -3.0f, 10, 5, 6);
        this.dressMiddle.func_78793_a(0.0f, 12.0f, 0.0f);
        this.dressMiddle.func_78787_b(64, 64);
        this.dressMiddle.field_78809_i = true;
        this.setRotation(this.dressMiddle, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.func_78785_a(f5);
        this.cleave.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
        this.dressLower.func_78785_a(f5);
        this.dressMiddle.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.leftarm.field_78795_f = -0.6f;
        this.leftarm.field_78808_h = -0.1f;
        this.leftarm.field_78796_g = -0.6f;
        this.rightarm.field_78795_f = -0.6f;
        this.rightarm.field_78808_h = 0.1f;
        this.rightarm.field_78796_g = 0.6f;
    }
}

