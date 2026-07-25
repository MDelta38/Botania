/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package flaxbeard.thaumicexploration.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSoulBrazier
extends ModelBase {
    ModelRenderer Base;
    ModelRenderer Prong1;
    ModelRenderer Prong2;
    ModelRenderer Prong3;
    ModelRenderer Prong4;

    public ModelSoulBrazier() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.Base = new ModelRenderer((ModelBase)this, 0, 0);
        this.Base.func_78789_a(0.0f, 0.0f, 0.0f, 10, 12, 10);
        this.Base.func_78793_a(-5.0f, 12.0f, -5.0f);
        this.Base.func_78787_b(64, 64);
        this.Base.field_78809_i = true;
        this.setRotation(this.Base, 0.0f, 0.0f, 0.0f);
        this.Prong1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Prong1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 2);
        this.Prong1.func_78793_a(-2.0f, 8.0f, -7.0f);
        this.Prong1.func_78787_b(64, 64);
        this.Prong1.field_78809_i = true;
        this.setRotation(this.Prong1, 0.0f, 0.0f, 0.0f);
        this.Prong2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Prong2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 2);
        this.Prong2.func_78793_a(-2.0f, 8.0f, 5.0f);
        this.Prong2.func_78787_b(64, 64);
        this.Prong2.field_78809_i = true;
        this.setRotation(this.Prong2, 0.0f, 0.0f, 0.0f);
        this.Prong3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Prong3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 2);
        this.Prong3.func_78793_a(-7.0f, 8.0f, 2.0f);
        this.Prong3.func_78787_b(64, 64);
        this.Prong3.field_78809_i = true;
        this.setRotation(this.Prong3, 0.0f, 1.570796f, 0.0f);
        this.Prong4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.Prong4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 8, 2);
        this.Prong4.func_78793_a(5.0f, 8.0f, 2.0f);
        this.Prong4.func_78787_b(64, 64);
        this.Prong4.field_78809_i = true;
        this.setRotation(this.Prong4, 0.0f, 1.570796f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Base.func_78785_a(f5);
        this.Prong1.func_78785_a(f5);
        this.Prong2.func_78785_a(f5);
        this.Prong3.func_78785_a(f5);
        this.Prong4.func_78785_a(f5);
    }

    public void renderAll(float f5) {
        this.Base.func_78785_a(f5);
        this.Prong1.func_78785_a(f5);
        this.Prong2.func_78785_a(f5);
        this.Prong3.func_78785_a(f5);
        this.Prong4.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }
}

