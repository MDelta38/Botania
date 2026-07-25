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

public class ModelMagnet
extends ModelBase {
    ModelRenderer panel;
    ModelRenderer magnet;
    ModelRenderer box1;
    ModelRenderer box2;
    ModelRenderer box3;
    ModelRenderer box4;
    ModelRenderer box5;

    public ModelMagnet() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.panel = new ModelRenderer((ModelBase)this, 0, 0);
        this.panel.func_78789_a(0.0f, 0.0f, 0.0f, 14, 2, 14);
        this.panel.func_78793_a(-7.0f, 22.0f, -7.0f);
        this.panel.func_78787_b(64, 64);
        this.setRotation(this.panel, 0.0f, 0.0f, 0.0f);
        this.magnet = new ModelRenderer((ModelBase)this, 0, 16);
        this.magnet.func_78789_a(0.0f, 0.0f, 0.0f, 4, 13, 4);
        this.magnet.func_78793_a(-2.0f, 9.0f, -2.0f);
        this.magnet.func_78787_b(64, 64);
        this.setRotation(this.magnet, 0.0f, 0.0f, 0.0f);
        this.box1 = new ModelRenderer((ModelBase)this, 28, 19);
        this.box1.func_78789_a(0.0f, -2.0f, 0.0f, 6, 14, 0);
        this.box1.func_78793_a(3.0f, 10.0f, -3.0f);
        this.box1.func_78787_b(64, 64);
        this.setRotation(this.box1, 0.0f, -1.570796f, 0.0f);
        this.box2 = new ModelRenderer((ModelBase)this, 28, 33);
        this.box2.func_78789_a(0.0f, 0.0f, 0.0f, 6, 14, 0);
        this.box2.func_78793_a(-3.0f, 8.0f, 3.0f);
        this.box2.func_78787_b(64, 64);
        this.setRotation(this.box2, 0.0f, 1.570796f, 0.0f);
        this.box3 = new ModelRenderer((ModelBase)this, 40, 19);
        this.box3.func_78789_a(0.0f, 0.0f, 0.0f, 6, 14, 0);
        this.box3.func_78793_a(3.0f, 8.0f, 3.0f);
        this.box3.func_78787_b(64, 64);
        this.setRotation(this.box3, 0.0f, 3.141593f, 0.0f);
        this.box4 = new ModelRenderer((ModelBase)this, 40, 33);
        this.box4.func_78789_a(0.0f, 0.0f, 0.0f, 6, 14, 0);
        this.box4.func_78793_a(-3.0f, 8.0f, -3.0f);
        this.box4.func_78787_b(64, 64);
        this.setRotation(this.box4, 0.0f, 0.0f, 0.0f);
        this.box5 = new ModelRenderer((ModelBase)this, 28, 49);
        this.box5.func_78789_a(0.0f, 0.0f, 0.0f, 6, 0, 6);
        this.box5.func_78793_a(-3.0f, 8.0f, -3.0f);
        this.box5.func_78787_b(64, 64);
        this.setRotation(this.box5, 0.0f, 0.0f, 0.0f);
    }

    public void render() {
        float scale = 0.0625f;
        this.panel.func_78785_a(0.0625f);
        this.magnet.func_78785_a(0.0625f);
        this.box1.func_78785_a(0.0625f);
        this.box2.func_78785_a(0.0625f);
        this.box3.func_78785_a(0.0625f);
        this.box4.func_78785_a(0.0625f);
        this.box5.func_78785_a(0.0625f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

