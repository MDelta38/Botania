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

public class ModelPool
extends ModelBase {
    ModelRenderer base;
    ModelRenderer side1;
    ModelRenderer side2;
    ModelRenderer side3;
    ModelRenderer side4;

    public ModelPool() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.base = new ModelRenderer((ModelBase)this, 0, 0);
        this.base.func_78789_a(0.0f, 0.0f, 0.0f, 16, 1, 16);
        this.base.func_78793_a(-8.0f, 23.0f, -8.0f);
        this.base.func_78787_b(64, 32);
        this.side1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.side1.func_78789_a(0.0f, 0.0f, 0.0f, 16, 7, 1);
        this.side1.func_78793_a(-8.0f, 16.0f, 7.0f);
        this.side1.func_78787_b(64, 32);
        this.side2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.side2.func_78789_a(0.0f, 0.0f, 0.0f, 16, 7, 1);
        this.side2.func_78793_a(-8.0f, 16.0f, -8.0f);
        this.side2.func_78787_b(64, 32);
        this.side3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.side3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 7, 14);
        this.side3.func_78793_a(-8.0f, 16.0f, -7.0f);
        this.side3.func_78787_b(64, 32);
        this.side4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.side4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 7, 14);
        this.side4.func_78793_a(7.0f, 16.0f, -7.0f);
        this.side4.func_78787_b(64, 32);
    }

    public void render() {
        float f = 0.0625f;
        this.base.func_78785_a(f);
        this.side1.func_78785_a(f);
        this.side2.func_78785_a(f);
        this.side3.func_78785_a(f);
        this.side4.func_78785_a(f);
    }
}

