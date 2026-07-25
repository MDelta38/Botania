/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.kentington.thaumichorizons.client.renderer.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class ModelQuarterBlock
extends ModelBase {
    public ModelRenderer block = new ModelRenderer((ModelBase)this, 0, 0);

    public ModelQuarterBlock() {
        this.block.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.block.func_78793_a(0.0f, 0.0f, 0.0f);
        this.block.func_78787_b(64, 32);
        this.block.field_78809_i = true;
        this.field_78090_t = 64;
        this.field_78089_u = 32;
    }

    public void render() {
        this.block.func_78785_a(0.0625f);
    }
}

