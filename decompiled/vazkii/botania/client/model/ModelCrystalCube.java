/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelCrystalCube
extends ModelBase {
    public ModelRenderer cube;
    public ModelRenderer base1;
    public ModelRenderer base2;

    public ModelCrystalCube() {
        this.field_78090_t = 48;
        this.field_78089_u = 32;
        this.cube = new ModelRenderer((ModelBase)this, 0, 0);
        this.cube.func_78793_a(0.0f, 12.0f, 0.0f);
        this.cube.func_78790_a(-4.0f, -4.0f, -4.0f, 8, 8, 8, 0.0f);
        this.base1 = new ModelRenderer((ModelBase)this, 22, 0);
        this.base1.func_78793_a(0.0f, 16.0f, 0.0f);
        this.base1.func_78790_a(-3.0f, 7.0f, -3.0f, 6, 1, 6, 0.0f);
        this.base2 = new ModelRenderer((ModelBase)this, 0, 16);
        this.base2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.base2.func_78790_a(-5.0f, 3.0f, -5.0f, 10, 4, 10, 0.0f);
        this.base1.func_78792_a(this.base2);
    }

    public void renderBase() {
        float f5 = 0.0625f;
        this.base1.func_78785_a(f5);
    }

    public void renderCube() {
        float f5 = 0.0625f;
        this.cube.func_78785_a(f5);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

