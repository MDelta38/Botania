/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package vazkii.botania.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelPixie
extends ModelBase {
    ModelRenderer Body;
    ModelRenderer LeftWing;
    ModelRenderer RightWing;

    public ModelPixie() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Body = new ModelRenderer((ModelBase)this, 0, 0);
        this.Body.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        this.Body.func_78793_a(-2.0f, 16.0f, -2.0f);
        this.Body.func_78787_b(64, 32);
        this.Body.field_78809_i = true;
        this.setRotation(this.Body, 0.0f, 0.0f, 0.0f);
        this.LeftWing = new ModelRenderer((ModelBase)this, 32, 0);
        this.LeftWing.func_78789_a(0.0f, 0.0f, -1.0f, 0, 4, 7);
        this.LeftWing.func_78793_a(2.0f, 15.0f, 2.0f);
        this.LeftWing.func_78787_b(64, 32);
        this.LeftWing.field_78809_i = true;
        this.setRotation(this.LeftWing, 0.0f, 0.0f, 0.0f);
        this.RightWing = new ModelRenderer((ModelBase)this, 50, 0);
        this.RightWing.func_78789_a(0.0f, 0.0f, -1.0f, 0, 4, 7);
        this.RightWing.func_78793_a(-2.0f, 15.0f, 2.0f);
        this.RightWing.func_78787_b(64, 32);
        this.RightWing.field_78809_i = true;
        this.setRotation(this.RightWing, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.Body.func_78785_a(f5);
        this.LeftWing.func_78785_a(f5);
        this.RightWing.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.RightWing.field_78796_g = -(MathHelper.func_76134_b((float)(f2 * 1.7f)) * (float)Math.PI * 0.5f);
        this.LeftWing.field_78796_g = MathHelper.func_76134_b((float)(f2 * 1.7f)) * (float)Math.PI * 0.5f;
    }
}

