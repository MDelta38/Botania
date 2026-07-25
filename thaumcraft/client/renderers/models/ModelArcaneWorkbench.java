/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package thaumcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelArcaneWorkbench
extends ModelBase {
    ModelRenderer Top;
    ModelRenderer Base;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Leg3;
    ModelRenderer Leg4;

    public ModelArcaneWorkbench() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Top = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 16, 8, 16);
        this.Top.func_78793_a(-8.0f, 0.0f, -8.0f);
        this.Top.func_78787_b(128, 64);
        this.Top.field_78809_i = true;
        this.setRotation(this.Top, 0.0f, 0.0f, 0.0f);
        this.Base = new ModelRenderer((ModelBase)this, 0, 32);
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 16, 4, 16);
        this.Base.func_78793_a(-8.0f, 12.0f, -8.0f);
        this.Base.func_78787_b(128, 64);
        this.Base.field_78809_i = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 72, 0);
        this.Leg1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Leg1.func_78793_a(3.0f, 8.0f, -7.0f);
        this.Leg1.func_78787_b(128, 64);
        this.Leg1.field_78809_i = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 72, 0);
        this.Leg2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Leg2.func_78793_a(-7.0f, 8.0f, 3.0f);
        this.Leg2.func_78787_b(128, 64);
        this.Leg2.field_78809_i = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Leg3 = new ModelRenderer((ModelBase)this, 72, 0);
        this.Leg3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Leg3.func_78793_a(3.0f, 8.0f, 3.0f);
        this.Leg3.func_78787_b(128, 64);
        this.Leg3.field_78809_i = true;
        this.setRotation(this.Leg3, 0.0f, 0.0f, 0.0f);
        this.Leg4 = new ModelRenderer((ModelBase)this, 72, 0);
        this.Leg4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Leg4.func_78793_a(-7.0f, 8.0f, -7.0f);
        this.Leg4.func_78787_b(128, 64);
        this.Leg4.field_78809_i = true;
        this.setRotation(this.Leg4, 0.0f, 0.0f, 0.0f);
    }

    public void renderAll() {
        this.Top.func_78785_a(0.0625f);
        this.Base.func_78785_a(0.0625f);
        this.Leg1.func_78785_a(0.0625f);
        this.Leg2.func_78785_a(0.0625f);
        this.Leg3.func_78785_a(0.0625f);
        this.Leg4.func_78785_a(0.0625f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

