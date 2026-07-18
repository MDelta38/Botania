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

public class ModelSpawnerClaw
extends ModelBase {
    ModelRenderer Plate;
    ModelRenderer Claw1;
    ModelRenderer Claw2;
    ModelRenderer Claw3;
    ModelRenderer Claw4;
    ModelRenderer Claw5;
    ModelRenderer Claw6;
    ModelRenderer Claw7;
    ModelRenderer Claw8;

    public ModelSpawnerClaw() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Plate = new ModelRenderer((ModelBase)this, 0, 0);
        this.Plate.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.Plate.func_78793_a(-6.0f, 23.0f, -6.0f);
        this.Plate.func_78787_b(64, 32);
        this.Claw1 = new ModelRenderer((ModelBase)this, 0, 14);
        this.Claw1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 3);
        this.Claw1.func_78793_a(-1.0f, 23.0f, 6.0f);
        this.Claw1.func_78787_b(64, 32);
        this.Claw2 = new ModelRenderer((ModelBase)this, 11, 14);
        this.Claw2.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 3);
        this.Claw2.func_78793_a(-1.0f, 23.0f, -9.0f);
        this.Claw2.func_78787_b(64, 32);
        this.Claw3 = new ModelRenderer((ModelBase)this, 0, 19);
        this.Claw3.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 2);
        this.Claw3.func_78793_a(-9.0f, 23.0f, -1.0f);
        this.Claw3.func_78787_b(64, 32);
        this.Claw4 = new ModelRenderer((ModelBase)this, 11, 19);
        this.Claw4.func_78789_a(0.0f, 0.0f, 0.0f, 3, 1, 2);
        this.Claw4.func_78793_a(6.0f, 23.0f, -1.0f);
        this.Claw4.func_78787_b(64, 32);
        this.Claw5 = new ModelRenderer((ModelBase)this, 23, 16);
        this.Claw5.func_78789_a(0.0f, 0.0f, 0.0f, 2, 5, 1);
        this.Claw5.func_78793_a(-1.0f, 24.0f, 8.0f);
        this.Claw5.func_78787_b(64, 32);
        this.Claw6 = new ModelRenderer((ModelBase)this, 30, 16);
        this.Claw6.func_78789_a(0.0f, 0.0f, 0.0f, 1, 5, 2);
        this.Claw6.func_78793_a(8.0f, 24.0f, -1.0f);
        this.Claw6.func_78787_b(64, 32);
        this.Claw7 = new ModelRenderer((ModelBase)this, 37, 16);
        this.Claw7.func_78789_a(0.0f, 0.0f, 0.0f, 2, 5, 1);
        this.Claw7.func_78793_a(-1.0f, 24.0f, -9.0f);
        this.Claw7.func_78787_b(64, 32);
        this.Claw8 = new ModelRenderer((ModelBase)this, 44, 16);
        this.Claw8.func_78789_a(0.0f, 0.0f, 0.0f, 1, 5, 2);
        this.Claw8.func_78793_a(-9.0f, 24.0f, -1.0f);
        this.Claw8.func_78787_b(64, 32);
    }

    public void render() {
        float f5 = 0.0625f;
        this.Plate.func_78785_a(f5);
        this.Claw1.func_78785_a(f5);
        this.Claw2.func_78785_a(f5);
        this.Claw3.func_78785_a(f5);
        this.Claw4.func_78785_a(f5);
        this.Claw5.func_78785_a(f5);
        this.Claw6.func_78785_a(f5);
        this.Claw7.func_78785_a(f5);
        this.Claw8.func_78785_a(f5);
    }
}

