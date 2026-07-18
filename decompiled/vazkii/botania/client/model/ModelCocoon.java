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

public class ModelCocoon
extends ModelBase {
    public ModelRenderer shape;

    public ModelCocoon() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.shape = new ModelRenderer((ModelBase)this, 0, 0);
        this.shape.func_78793_a(0.0f, 22.0f, 0.0f);
        this.shape.func_78790_a(-5.0f, -8.0f, -7.0f, 10, 10, 14, 0.0f);
    }

    public void render() {
        this.shape.func_78785_a(0.0625f);
    }
}

