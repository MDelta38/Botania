/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package thaumcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelManaPod
extends ModelBase {
    public ModelRenderer pod0;
    public ModelRenderer pod1;
    public ModelRenderer pod2;

    public ModelManaPod() {
        this.field_78090_t = 32;
        this.field_78089_u = 32;
        this.pod0 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pod0.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 5, 4);
        this.pod0.func_78793_a(0.0f, 0.0f, 0.0f);
        this.pod0.func_78787_b(32, 32);
        this.pod0.field_78809_i = true;
        this.setRotation(this.pod0, 0.0f, 0.0f, 0.0f);
        this.pod1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pod1.func_78789_a(-3.0f, 0.0f, -3.0f, 6, 7, 6);
        this.pod1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.pod1.func_78787_b(32, 32);
        this.pod1.field_78809_i = true;
        this.setRotation(this.pod1, 0.0f, 0.0f, 0.0f);
        this.pod2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.pod2.func_78789_a(-3.5f, 0.0f, -3.5f, 7, 9, 7);
        this.pod2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.pod2.func_78787_b(32, 32);
        this.pod2.field_78809_i = true;
        this.setRotation(this.pod2, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.setRotationAngles(f, f1, f2, f3, f4, f5);
        this.pod0.func_78785_a(f5);
        this.pod1.func_78785_a(f5);
        this.pod2.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5) {
    }
}

