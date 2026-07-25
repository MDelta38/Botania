/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package thaumic.tinkerer.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelRepairer
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Support1;
    ModelRenderer Support2;
    ModelRenderer Support3;
    ModelRenderer Support4;
    ModelRenderer Top1;
    ModelRenderer Top2;
    ModelRenderer Top3;
    ModelRenderer Top4;
    ModelRenderer Opening1;
    ModelRenderer Opening2;
    ModelRenderer Opening3;
    ModelRenderer Opening4;
    ModelRenderer Glass1;
    ModelRenderer Glass2;
    ModelRenderer Glass3;
    ModelRenderer Glass4;
    ModelRenderer Glass5;

    public ModelRepairer() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Base = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.Base.func_78793_a(-8.0f, 23.0f, -8.0f);
        this.Base.func_78787_b(64, 64);
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Support1 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Support1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 14, 2);
        this.Support1.func_78793_a(-8.0f, 9.0f, 6.0f);
        this.Support1.func_78787_b(64, 64);
        this.setRotation(this.Support1, 0.0f, 0.0f, 0.0f);
        this.Support2 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Support2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 14, 2);
        this.Support2.func_78793_a(6.0f, 9.0f, 6.0f);
        this.Support2.func_78787_b(64, 64);
        this.setRotation(this.Support2, 0.0f, 0.0f, 0.0f);
        this.Support3 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Support3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 14, 2);
        this.Support3.func_78793_a(-8.0f, 9.0f, -8.0f);
        this.Support3.func_78787_b(64, 64);
        this.setRotation(this.Support3, 0.0f, 0.0f, 0.0f);
        this.Support4 = new ModelRenderer((ModelBase)this, 0, 17);
        this.Support4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 14, 2);
        this.Support4.func_78793_a(6.0f, 9.0f, -8.0f);
        this.Support4.func_78787_b(64, 64);
        this.setRotation(this.Support4, 0.0f, 0.0f, 0.0f);
        this.Top1 = new ModelRenderer((ModelBase)this, 11, 19);
        this.Top1.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 2);
        this.Top1.func_78793_a(-8.0f, 8.0f, -8.0f);
        this.Top1.func_78787_b(64, 64);
        this.setRotation(this.Top1, 0.0f, 0.0f, 0.0f);
        this.Top2 = new ModelRenderer((ModelBase)this, 11, 19);
        this.Top2.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 2);
        this.Top2.func_78793_a(-8.0f, 8.0f, 6.0f);
        this.Top2.func_78787_b(64, 64);
        this.setRotation(this.Top2, 0.0f, 0.0f, 0.0f);
        this.Top3 = new ModelRenderer((ModelBase)this, 11, 23);
        this.Top3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 12);
        this.Top3.func_78793_a(6.0f, 8.0f, -6.0f);
        this.Top3.func_78787_b(64, 64);
        this.setRotation(this.Top3, 0.0f, 0.0f, 0.0f);
        this.Top4 = new ModelRenderer((ModelBase)this, 11, 23);
        this.Top4.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 12);
        this.Top4.func_78793_a(-8.0f, 8.0f, -6.0f);
        this.Top4.func_78787_b(64, 64);
        this.setRotation(this.Top4, 0.0f, 0.0f, 0.0f);
        this.Opening1 = new ModelRenderer((ModelBase)this, 48, 30);
        this.Opening1.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.Opening1.func_78793_a(-2.5f, 14.0f, 7.0f);
        this.Opening1.func_78787_b(64, 64);
        this.setRotation(this.Opening1, 0.0f, 0.0f, 0.0f);
        this.Opening2 = new ModelRenderer((ModelBase)this, 48, 30);
        this.Opening2.func_78789_a(0.0f, 0.0f, 0.0f, 5, 1, 1);
        this.Opening2.func_78793_a(-2.5f, 17.0f, 7.0f);
        this.Opening2.func_78787_b(64, 64);
        this.setRotation(this.Opening2, 0.0f, 0.0f, 0.0f);
        this.Opening3 = new ModelRenderer((ModelBase)this, 48, 24);
        this.Opening3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Opening3.func_78793_a(-2.0f, 14.5f, 7.0f);
        this.Opening3.func_78787_b(64, 64);
        this.setRotation(this.Opening3, 0.0f, 0.0f, 0.0f);
        this.Opening4 = new ModelRenderer((ModelBase)this, 48, 24);
        this.Opening4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 3, 1);
        this.Opening4.func_78793_a(1.0f, 14.5f, 7.0f);
        this.Opening4.func_78787_b(64, 64);
        this.setRotation(this.Opening4, 0.0f, 0.0f, 0.0f);
        this.Glass1 = new ModelRenderer((ModelBase)this, -11, 37);
        this.Glass1.func_78789_a(0.0f, 0.0f, 0.0f, 12, 0, 12);
        this.Glass1.func_78793_a(-6.0f, 8.5f, -6.0f);
        this.Glass1.func_78787_b(64, 64);
        this.setRotation(this.Glass1, 0.0f, 0.0f, 0.0f);
        this.Glass2 = new ModelRenderer((ModelBase)this, 1, 38);
        this.Glass2.func_78789_a(0.0f, 0.0f, 0.0f, 0, 14, 12);
        this.Glass2.func_78793_a(7.5f, 9.0f, -6.0f);
        this.Glass2.func_78787_b(64, 64);
        this.setRotation(this.Glass2, 0.0f, 0.0f, 0.0f);
        this.Glass3 = new ModelRenderer((ModelBase)this, 1, 38);
        this.Glass3.func_78789_a(0.0f, 0.0f, 0.0f, 0, 14, 12);
        this.Glass3.func_78793_a(-7.5f, 9.0f, 6.0f);
        this.Glass3.func_78787_b(64, 64);
        this.setRotation(this.Glass3, 0.0f, 3.141593f, 0.0f);
        this.Glass4 = new ModelRenderer((ModelBase)this, 40, 34);
        this.Glass4.func_78789_a(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Glass4.func_78793_a(-6.0f, 9.0f, -7.5f);
        this.Glass4.func_78787_b(64, 64);
        this.setRotation(this.Glass4, 0.0f, 0.0f, 0.0f);
        this.Glass5 = new ModelRenderer((ModelBase)this, 33, 50);
        this.Glass5.func_78789_a(0.0f, 0.0f, 0.0f, 12, 14, 0);
        this.Glass5.func_78793_a(6.0f, 9.0f, 7.5f);
        this.Glass5.func_78787_b(64, 64);
        this.setRotation(this.Glass5, 0.0f, 3.141593f, 0.0f);
    }

    public void render() {
        float scale = 0.0625f;
        this.Base.func_78785_a(0.0625f);
        this.Support1.func_78785_a(0.0625f);
        this.Support2.func_78785_a(0.0625f);
        this.Support3.func_78785_a(0.0625f);
        this.Support4.func_78785_a(0.0625f);
        this.Top1.func_78785_a(0.0625f);
        this.Top2.func_78785_a(0.0625f);
        this.Top3.func_78785_a(0.0625f);
        this.Top4.func_78785_a(0.0625f);
        this.Opening1.func_78785_a(0.0625f);
        this.Opening2.func_78785_a(0.0625f);
        this.Opening3.func_78785_a(0.0625f);
        this.Opening4.func_78785_a(0.0625f);
    }

    public void renderGlass() {
        float scale = 0.0625f;
        this.Glass1.func_78785_a(0.0625f);
        this.Glass2.func_78785_a(0.0625f);
        this.Glass3.func_78785_a(0.0625f);
        this.Glass4.func_78785_a(0.0625f);
        this.Glass5.func_78785_a(0.0625f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

