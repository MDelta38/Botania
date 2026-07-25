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
public class ModelWitchHunter
extends ModelBiped {
    ModelRenderer coatBottom = new ModelRenderer((ModelBase)this, 1, 47);
    ModelRenderer hatBrim;
    ModelRenderer hatTop;

    public ModelWitchHunter() {
        super(0.0f, 0.0f, 64, 64);
        this.coatBottom.func_78789_a(-5.0f, 0.0f, -2.5f, 10, 11, 5);
        this.coatBottom.func_78793_a(0.0f, 11.0f, 0.0f);
        this.coatBottom.func_78787_b(64, 64);
        this.coatBottom.field_78809_i = true;
        this.setRotation(this.coatBottom, 0.0f, 0.0f, 0.0f);
        this.field_78115_e.func_78792_a(this.coatBottom);
        this.hatBrim = new ModelRenderer((ModelBase)this, 0, 32);
        this.hatBrim.func_78789_a(-7.0f, 0.0f, -7.0f, 14, 1, 14);
        this.hatBrim.func_78793_a(0.0f, -6.0f, 0.0f);
        this.hatBrim.func_78787_b(64, 64);
        this.hatBrim.field_78809_i = true;
        this.setRotation(this.hatBrim, 0.0f, 0.0f, 0.0f);
        this.field_78116_c.func_78792_a(this.hatBrim);
        this.hatTop = new ModelRenderer((ModelBase)this, 33, 48);
        this.hatTop.func_78789_a(-3.0f, 0.0f, -3.0f, 6, 2, 6);
        this.hatTop.func_78793_a(0.0f, -10.0f, 0.0f);
        this.hatTop.func_78787_b(64, 64);
        this.hatTop.field_78809_i = true;
        this.setRotation(this.hatTop, 0.0f, 0.0f, 0.0f);
        this.field_78116_c.func_78792_a(this.hatTop);
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

