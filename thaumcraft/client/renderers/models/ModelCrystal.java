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

public class ModelCrystal
extends ModelBase {
    ModelRenderer Crystal;

    public ModelCrystal() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Crystal = new ModelRenderer((ModelBase)this, 0, 0);
        this.Crystal.func_78789_a(-16.0f, -16.0f, 0.0f, 16, 16, 16);
        this.Crystal.func_78793_a(0.0f, 32.0f, 0.0f);
        this.Crystal.func_78787_b(64, 32);
        this.Crystal.field_78809_i = true;
        this.setRotation(this.Crystal, 0.7071f, 0.0f, 0.7071f);
    }

    public void render() {
        this.Crystal.func_78785_a(0.0625f);
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

