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
public class ModelVampire
extends ModelBiped {
    public ModelRenderer bipedHeadNose;
    public ModelRenderer bipedBodyRobe;

    public ModelVampire() {
        super(0.0f, 0.0f, 64, 64);
        this.field_78116_c = new ModelRenderer((ModelBase)this, 28, 46);
        this.field_78116_c.func_78793_a(0.0f, 0.0f, 0.0f);
        this.field_78116_c.func_78790_a(-4.0f, -10.0f, -4.0f, 8, 10, 8, 0.0f);
        this.bipedHeadNose = new ModelRenderer((ModelBase)this, 52, 46);
        this.bipedHeadNose.func_78793_a(0.0f, -2.0f, 0.0f);
        this.bipedHeadNose.func_78790_a(-1.0f, -1.0f, -6.0f, 2, 4, 2, 0.0f);
        this.bipedBodyRobe = new ModelRenderer((ModelBase)this, 0, 38);
        this.bipedBodyRobe.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedBodyRobe.func_78790_a(-4.0f, 0.0f, -3.0f, 8, 18, 6, 0.5f);
        this.field_78116_c.func_78792_a(this.bipedHeadNose);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        this.field_78114_d.field_78797_d += 0.3f;
    }
}

