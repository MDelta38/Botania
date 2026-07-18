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

public class ModelTinyPotato
extends ModelBase {
    ModelRenderer potato;

    public ModelTinyPotato() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.potato = new ModelRenderer((ModelBase)this, 0, 0);
        this.potato.func_78789_a(0.0f, 0.0f, 0.0f, 4, 6, 4);
        this.potato.func_78793_a(-2.0f, 18.0f, -2.0f);
        this.potato.func_78787_b(64, 32);
    }

    public void render() {
        this.potato.func_78785_a(0.0625f);
    }
}

