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

public class ModelIncensePlate
extends ModelBase {
    ModelRenderer Plate;
    ModelRenderer High;

    public ModelIncensePlate() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Plate = new ModelRenderer((ModelBase)this, 0, 0);
        this.Plate.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 4);
        this.Plate.func_78793_a(-6.0f, 23.0f, -2.0f);
        this.Plate.func_78787_b(64, 32);
        this.Plate.field_78809_i = true;
        this.High = new ModelRenderer((ModelBase)this, 0, 6);
        this.High.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 4);
        this.High.func_78793_a(-6.0f, 22.0f, -2.0f);
        this.High.func_78787_b(64, 32);
        this.High.field_78809_i = true;
    }

    public void render() {
        float f5 = 0.0625f;
        this.Plate.func_78785_a(f5);
        this.High.func_78785_a(f5);
    }
}

