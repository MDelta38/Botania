/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package vazkii.botania.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelTeruTeruBozu
extends ModelBase {
    public ModelRenderer thread;
    public ModelRenderer cloth;
    public ModelRenderer happyFace;
    public ModelRenderer sadFace;

    public ModelTeruTeruBozu() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.sadFace = new ModelRenderer((ModelBase)this, 32, 0);
        this.sadFace.func_78793_a(0.0f, 14.5f, 0.0f);
        this.sadFace.func_78790_a(-4.0f, -6.0f, -4.0f, 8, 8, 8, 0.0f);
        this.setRotateAngle(this.sadFace, 0.17453292f, 0.0f, 0.0f);
        this.happyFace = new ModelRenderer((ModelBase)this, 0, 0);
        this.happyFace.func_78793_a(0.0f, 14.5f, 0.0f);
        this.happyFace.func_78790_a(-4.0f, -6.0f, -4.0f, 8, 8, 8, 0.0f);
        this.setRotateAngle(this.happyFace, -0.17453292f, 0.0f, 0.0f);
        this.thread = new ModelRenderer((ModelBase)this, 32, 16);
        this.thread.func_78793_a(0.0f, 14.0f, 0.0f);
        this.thread.func_78790_a(-3.0f, 2.0f, -3.0f, 6, 1, 6, 0.0f);
        this.cloth = new ModelRenderer((ModelBase)this, 0, 16);
        this.cloth.func_78793_a(0.0f, 21.5f, -1.0f);
        this.cloth.func_78790_a(-4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f);
        this.setRotateAngle(this.cloth, 0.7853982f, 2.268928f, 1.5707964f);
    }

    public void render() {
        float f5 = 0.0625f;
        if (Minecraft.func_71410_x().field_71441_e.func_72896_J()) {
            this.sadFace.func_78785_a(f5);
        } else {
            this.happyFace.func_78785_a(f5);
        }
        this.thread.func_78785_a(f5);
        this.cloth.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

