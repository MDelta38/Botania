/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBlackHoleCube
extends ModelBase {
    public ModelRenderer shape1;
    public ModelRenderer shape1_1;
    public ModelRenderer shape1_2;
    public ModelRenderer shape1_3;
    public ModelRenderer shape1_4;
    public ModelRenderer shape1_5;
    public ModelRenderer shape1_6;
    public ModelRenderer shape1_7;
    public ModelRenderer shape1_8;
    public ModelRenderer shape1_9;
    public ModelRenderer shape1_10;
    public ModelRenderer shape1_11;

    public ModelBlackHoleCube() {
        this.field_78090_t = 36;
        this.field_78089_u = 2;
        this.shape1_3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_3.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_3.func_78790_a(-7.0f, 7.0f, -8.0f, 14, 1, 1, 0.0f);
        this.setRotateAngle(this.shape1_3, 0.0f, 1.5707964f, 0.0f);
        this.shape1_7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_7.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_7.func_78790_a(-7.0f, -8.0f, -8.0f, 14, 1, 1, 0.0f);
        this.setRotateAngle(this.shape1_7, 0.0f, 1.5707964f, 0.0f);
        this.shape1_9 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_9.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_9.func_78790_a(-7.0f, 7.0f, 7.0f, 14, 1, 1, 0.0f);
        this.setRotateAngle(this.shape1_9, 0.0f, (float)Math.PI, 1.5707964f);
        this.shape1_11 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_11.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_11.func_78790_a(-7.0f, 7.0f, -8.0f, 14, 1, 1, 0.0f);
        this.setRotateAngle(this.shape1_11, 0.0f, (float)Math.PI, 1.5707964f);
        this.shape1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1.func_78790_a(-8.0f, 7.0f, 7.0f, 16, 1, 1, 0.0f);
        this.shape1_4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_4.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_4.func_78790_a(-8.0f, 7.0f, -8.0f, 16, 1, 1, 0.0f);
        this.shape1_8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_8.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_8.func_78790_a(-7.0f, -8.0f, -8.0f, 14, 1, 1, 0.0f);
        this.setRotateAngle(this.shape1_8, 0.0f, (float)Math.PI, 1.5707964f);
        this.shape1_2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_2.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_2.func_78790_a(-8.0f, -8.0f, -8.0f, 16, 1, 1, 0.0f);
        this.shape1_1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_1.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_1.func_78790_a(-8.0f, -8.0f, 7.0f, 16, 1, 1, 0.0f);
        this.shape1_6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_6.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_6.func_78790_a(-7.0f, -8.0f, -8.0f, 14, 1, 1, 0.0f);
        this.setRotateAngle(this.shape1_6, 0.0f, -1.5707964f, 0.0f);
        this.shape1_10 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_10.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_10.func_78790_a(-7.0f, -8.0f, 7.0f, 14, 1, 1, 0.0f);
        this.setRotateAngle(this.shape1_10, 0.0f, (float)Math.PI, 1.5707964f);
        this.shape1_5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape1_5.func_78793_a(0.0f, 16.0f, 0.0f);
        this.shape1_5.func_78790_a(-7.0f, 7.0f, -8.0f, 14, 1, 1, 0.0f);
        this.setRotateAngle(this.shape1_5, 0.0f, -1.5707964f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.shape1_3.func_78785_a(f5);
        this.shape1_7.func_78785_a(f5);
        this.shape1_9.func_78785_a(f5);
        this.shape1_11.func_78785_a(f5);
        this.shape1.func_78785_a(f5);
        this.shape1_4.func_78785_a(f5);
        this.shape1_8.func_78785_a(f5);
        this.shape1_2.func_78785_a(f5);
        this.shape1_1.func_78785_a(f5);
        this.shape1_6.func_78785_a(f5);
        this.shape1_10.func_78785_a(f5);
        this.shape1_5.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

