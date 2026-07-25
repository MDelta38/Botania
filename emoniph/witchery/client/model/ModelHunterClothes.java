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
public class ModelHunterClothes
extends ModelBiped {
    ModelRenderer coat;
    ModelRenderer hatBrim;
    ModelRenderer hatTop;
    ModelRenderer hatMid;

    public ModelHunterClothes(float scale, boolean shoulders) {
        super(scale, 0.0f, 128, 64);
        float hatScale = 0.52f;
        this.hatBrim = new ModelRenderer((ModelBase)this, 0, 50);
        this.hatBrim.func_78790_a(-6.5f, 0.0f, -6.5f, 13, 1, 13, hatScale - 0.2f);
        this.hatBrim.func_78793_a(0.0f, -6.0f, 0.0f);
        this.hatBrim.func_78787_b(128, 64);
        this.hatBrim.field_78809_i = true;
        this.setRotation(this.hatBrim, 0.0f, 0.0f, 0.0f);
        this.field_78116_c.func_78792_a(this.hatBrim);
        this.hatMid = new ModelRenderer((ModelBase)this, 40, 52);
        this.hatMid.func_78790_a(-4.0f, 0.0f, -4.0f, 8, 2, 8, hatScale);
        this.hatMid.func_78793_a(0.0f, -2.0f, 0.0f);
        this.hatMid.func_78787_b(128, 64);
        this.hatMid.field_78809_i = true;
        this.setRotation(this.hatMid, 0.0f, 0.0f, 0.0f);
        this.hatBrim.func_78792_a(this.hatMid);
        this.hatTop = new ModelRenderer((ModelBase)this, 12, 41);
        this.hatTop.func_78790_a(-3.5f, 0.0f, -3.5f, 7, 2, 7, hatScale);
        this.hatTop.func_78793_a(0.0f, -2.0f, 0.0f);
        this.hatTop.func_78787_b(128, 64);
        this.hatTop.field_78809_i = true;
        this.setRotation(this.hatTop, 0.0f, 0.0f, 0.0f);
        this.hatMid.func_78792_a(this.hatTop);
        this.coat = new ModelRenderer((ModelBase)this, 41, 33);
        this.coat.func_78790_a(-5.5f, 0.0f, -3.0f, 11, 10, 6, -0.3f);
        this.coat.func_78793_a(0.0f, 12.0f, 0.0f);
        this.coat.func_78787_b(128, 64);
        this.coat.field_78809_i = true;
        this.setRotation(this.coat, 0.0f, 0.0f, 0.0f);
        this.field_78115_e.func_78792_a(this.coat);
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
}

