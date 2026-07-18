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
import vazkii.botania.client.model.IPylonModel;

public class ModelPylonOld
extends ModelBase
implements IPylonModel {
    ModelRenderer crystal1;
    ModelRenderer crystal2;
    ModelRenderer crystal3;
    ModelRenderer crystal4;
    ModelRenderer crystal5;
    ModelRenderer crystal6;
    ModelRenderer crystal7;
    ModelRenderer crystal8;
    ModelRenderer outside1;
    ModelRenderer outside2;
    ModelRenderer outside3;
    ModelRenderer outside4;
    ModelRenderer outside5;
    ModelRenderer outside6;
    ModelRenderer outside7;
    ModelRenderer outside8;

    public ModelPylonOld() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.crystal1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.crystal1.func_78789_a(-1.5f, -7.0f, -1.0f, 3, 7, 2);
        this.crystal1.func_78793_a(0.0f, 23.0f, 0.0f);
        this.crystal1.func_78787_b(256, 128);
        this.setRotation(this.crystal1, 0.1396263f, -0.418879f, 0.0f);
        this.crystal2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.crystal2.func_78789_a(-1.5f, -7.0f, -1.0f, 3, 7, 2);
        this.crystal2.func_78793_a(0.0f, 23.0f, 0.0f);
        this.crystal2.func_78787_b(256, 128);
        this.setRotation(this.crystal2, -0.1396263f, 0.418879f, 0.0f);
        this.crystal3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.crystal3.func_78789_a(-1.5f, -7.0f, -1.0f, 3, 7, 2);
        this.crystal3.func_78793_a(0.0f, 23.0f, 0.0f);
        this.crystal3.func_78787_b(256, 128);
        this.setRotation(this.crystal3, 0.1396263f, 0.418879f, 0.0f);
        this.crystal4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.crystal4.func_78789_a(-1.5f, -7.0f, -1.0f, 3, 7, 2);
        this.crystal4.func_78793_a(0.0f, 23.0f, 0.0f);
        this.crystal4.func_78787_b(256, 128);
        this.setRotation(this.crystal4, -0.1396263f, -0.418879f, 0.0f);
        this.crystal5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.crystal5.func_78789_a(-1.5f, 0.0f, -1.0f, 3, 7, 2);
        this.crystal5.func_78793_a(0.0f, 10.0f, 0.0f);
        this.crystal5.func_78787_b(256, 128);
        this.setRotation(this.crystal5, 0.1396263f, 0.418879f, 0.0f);
        this.crystal6 = new ModelRenderer((ModelBase)this, 0, 0);
        this.crystal6.func_78789_a(-1.5f, 0.0f, -1.0f, 3, 7, 2);
        this.crystal6.func_78793_a(0.0f, 10.0f, 0.0f);
        this.crystal6.func_78787_b(256, 128);
        this.setRotation(this.crystal6, 0.1396263f, -0.418879f, 0.0f);
        this.crystal7 = new ModelRenderer((ModelBase)this, 0, 0);
        this.crystal7.func_78789_a(-1.5f, 0.0f, -1.0f, 3, 7, 2);
        this.crystal7.func_78793_a(0.0f, 10.0f, 0.0f);
        this.crystal7.func_78787_b(256, 128);
        this.setRotation(this.crystal7, -0.1396263f, -0.418879f, 0.0f);
        this.crystal8 = new ModelRenderer((ModelBase)this, 0, 0);
        this.crystal8.func_78789_a(-1.5f, 0.0f, -1.0f, 3, 7, 2);
        this.crystal8.func_78793_a(0.0f, 10.0f, 0.0f);
        this.crystal8.func_78787_b(256, 128);
        this.setRotation(this.crystal8, -0.1396263f, 0.418879f, 0.0f);
        this.outside1 = new ModelRenderer((ModelBase)this, 17, 0);
        this.outside1.func_78789_a(0.0f, -4.0f, -1.5f, 1, 8, 3);
        this.outside1.func_78793_a(4.0f, 18.0f, 0.0f);
        this.outside1.func_78787_b(256, 128);
        this.setRotation(this.outside1, 0.0f, 0.0f, 0.1396263f);
        this.outside2 = new ModelRenderer((ModelBase)this, 17, 0);
        this.outside2.func_78789_a(-1.0f, -4.0f, -1.5f, 1, 8, 3);
        this.outside2.func_78793_a(-4.0f, 18.0f, 0.0f);
        this.outside2.func_78787_b(256, 128);
        this.setRotation(this.outside2, 0.0f, 0.0f, -0.1396263f);
        this.outside3 = new ModelRenderer((ModelBase)this, 26, 0);
        this.outside3.func_78789_a(-1.5f, -3.0f, -1.0f, 3, 6, 1);
        this.outside3.func_78793_a(0.0f, 18.0f, -4.0f);
        this.outside3.func_78787_b(256, 128);
        this.setRotation(this.outside3, 0.0698132f, 0.0f, 0.0f);
        this.outside4 = new ModelRenderer((ModelBase)this, 26, 0);
        this.outside4.func_78789_a(-1.5f, -3.0f, 0.0f, 3, 6, 1);
        this.outside4.func_78793_a(0.0f, 18.0f, 4.0f);
        this.outside4.func_78787_b(256, 128);
        this.setRotation(this.outside4, -0.0698132f, 0.0f, 0.0f);
        this.outside5 = new ModelRenderer((ModelBase)this, 27, 0);
        this.outside5.func_78789_a(0.0f, 0.0f, -4.0f, 1, 2, 8);
        this.outside5.func_78793_a(3.0f, 18.0f, 0.0f);
        this.outside5.func_78787_b(256, 128);
        this.setRotation(this.outside5, 0.0f, 0.0f, 0.0f);
        this.outside6 = new ModelRenderer((ModelBase)this, 27, 0);
        this.outside6.func_78789_a(-1.0f, -1.0f, -4.0f, 1, 2, 8);
        this.outside6.func_78793_a(-3.0f, 19.0f, 0.0f);
        this.outside6.func_78787_b(256, 128);
        this.setRotation(this.outside6, 0.0f, 0.0f, 0.0f);
        this.outside7 = new ModelRenderer((ModelBase)this, 17, 12);
        this.outside7.func_78789_a(-3.0f, -1.0f, 0.0f, 6, 2, 1);
        this.outside7.func_78793_a(0.0f, 19.0f, 3.0f);
        this.outside7.func_78787_b(256, 128);
        this.setRotation(this.outside7, 0.0f, 0.0f, 0.0f);
        this.outside8 = new ModelRenderer((ModelBase)this, 17, 12);
        this.outside8.func_78789_a(-3.0f, -1.0f, -1.0f, 6, 2, 1);
        this.outside8.func_78793_a(0.0f, 19.0f, -3.0f);
        this.outside8.func_78787_b(256, 128);
        this.setRotation(this.outside8, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void renderCrystal() {
        float f = 0.0625f;
        this.crystal1.func_78785_a(f);
        this.crystal2.func_78785_a(f);
        this.crystal3.func_78785_a(f);
        this.crystal4.func_78785_a(f);
        this.crystal5.func_78785_a(f);
        this.crystal6.func_78785_a(f);
        this.crystal7.func_78785_a(f);
        this.crystal8.func_78785_a(f);
    }

    @Override
    public void renderRing() {
        float f = 0.0625f;
        this.outside1.func_78785_a(f);
        this.outside2.func_78785_a(f);
        this.outside3.func_78785_a(f);
        this.outside4.func_78785_a(f);
        this.outside5.func_78785_a(f);
        this.outside6.func_78785_a(f);
        this.outside7.func_78785_a(f);
        this.outside8.func_78785_a(f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    @Override
    public void renderGems() {
    }
}

