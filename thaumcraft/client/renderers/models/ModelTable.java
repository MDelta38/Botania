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

public class ModelTable
extends ModelBase {
    ModelRenderer Top;
    ModelRenderer Leg1;
    ModelRenderer Leg2;
    ModelRenderer Crossbar;

    public ModelTable() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Top = new ModelRenderer((ModelBase)this, 0, 0);
        this.Top.func_78789_a(0.0f, 0.0f, 0.0f, 16, 4, 16);
        this.Top.func_78793_a(-8.0f, 0.0f, -8.0f);
        this.Top.func_78787_b(64, 32);
        this.Top.field_78809_i = true;
        this.setRotation(this.Top, 0.0f, 0.0f, 0.0f);
        this.Leg1 = new ModelRenderer((ModelBase)this, 0, 20);
        this.Leg1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Leg1.func_78793_a(2.0f, 4.0f, -2.0f);
        this.Leg1.func_78787_b(64, 32);
        this.Leg1.field_78809_i = true;
        this.setRotation(this.Leg1, 0.0f, 0.0f, 0.0f);
        this.Leg2 = new ModelRenderer((ModelBase)this, 0, 20);
        this.Leg2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 4);
        this.Leg2.func_78793_a(-6.0f, 4.0f, -2.0f);
        this.Leg2.func_78787_b(64, 32);
        this.Leg2.field_78809_i = true;
        this.setRotation(this.Leg2, 0.0f, 0.0f, 0.0f);
        this.Crossbar = new ModelRenderer((ModelBase)this, 16, 20);
        this.Crossbar.func_78789_a(0.0f, 0.0f, 0.0f, 16, 4, 8);
        this.Crossbar.func_78793_a(-8.0f, 12.0f, -4.0f);
        this.Crossbar.func_78787_b(64, 32);
        this.Crossbar.field_78809_i = true;
        this.setRotation(this.Crossbar, 0.0f, 0.0f, 0.0f);
    }

    public void renderAll() {
        this.Top.func_78785_a(0.0625f);
        this.Leg1.func_78785_a(0.0625f);
        this.Leg2.func_78785_a(0.0625f);
        this.Crossbar.func_78785_a(0.0625f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

