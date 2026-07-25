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

public class ModelTubeValve
extends ModelBase {
    ModelRenderer ValveRod;
    ModelRenderer ValveRing;

    public ModelTubeValve() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.ValveRod = new ModelRenderer((ModelBase)this, 0, 10);
        this.ValveRod.func_78789_a(-1.0f, 2.0f, -1.0f, 2, 2, 2);
        this.ValveRod.func_78793_a(0.0f, 0.0f, 0.0f);
        this.ValveRod.func_78787_b(64, 32);
        this.ValveRod.field_78809_i = true;
        this.setRotation(this.ValveRod, 0.0f, 0.0f, 0.0f);
    }

    public void render() {
        this.ValveRod.func_78785_a(0.0625f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

