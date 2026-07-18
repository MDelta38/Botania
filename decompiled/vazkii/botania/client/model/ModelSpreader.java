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

public class ModelSpreader
extends ModelBase {
    ModelRenderer cubeSide1;
    ModelRenderer cubeSide2;
    ModelRenderer cubeSide3;
    ModelRenderer cubeSide4;
    ModelRenderer cubeSide5;
    ModelRenderer cubeHole1;
    ModelRenderer cubeHole2;
    ModelRenderer cubeHole3;
    ModelRenderer cubeHole4;
    ModelRenderer cubeInside;

    public ModelSpreader() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.cubeSide1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeSide1.func_78789_a(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.cubeSide1.func_78793_a(-7.0f, 22.0f, -7.0f);
        this.cubeSide1.func_78787_b(64, 32);
        this.cubeSide2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeSide2.func_78789_a(0.0f, 0.0f, 0.0f, 14, 13, 1);
        this.cubeSide2.func_78793_a(-7.0f, 9.0f, -7.0f);
        this.cubeSide2.func_78787_b(64, 32);
        this.cubeSide3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeSide3.func_78789_a(0.0f, 0.0f, 0.0f, 1, 13, 13);
        this.cubeSide3.func_78793_a(-7.0f, 9.0f, -6.0f);
        this.cubeSide3.func_78787_b(64, 32);
        this.cubeSide4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeSide4.func_78789_a(0.0f, 0.0f, 0.0f, 1, 13, 13);
        this.cubeSide4.func_78793_a(6.0f, 9.0f, -6.0f);
        this.cubeSide4.func_78787_b(64, 32);
        this.cubeSide5 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeSide5.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 13);
        this.cubeSide5.func_78793_a(-6.0f, 9.0f, -6.0f);
        this.cubeSide5.func_78787_b(64, 32);
        this.cubeHole1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeHole1.func_78789_a(0.0f, 0.0f, 0.0f, 4, 12, 1);
        this.cubeHole1.func_78793_a(2.0f, 10.0f, 6.0f);
        this.cubeHole1.func_78787_b(64, 32);
        this.cubeHole2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeHole2.func_78789_a(0.0f, 0.0f, 0.0f, 4, 12, 1);
        this.cubeHole2.func_78793_a(-6.0f, 10.0f, 6.0f);
        this.cubeHole2.func_78787_b(64, 32);
        this.cubeHole3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeHole3.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 1);
        this.cubeHole3.func_78793_a(-2.0f, 18.0f, 6.0f);
        this.cubeHole3.func_78787_b(64, 32);
        this.cubeHole4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.cubeHole4.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 1);
        this.cubeHole4.func_78793_a(-2.0f, 10.0f, 6.0f);
        this.cubeHole4.func_78787_b(64, 32);
        this.cubeInside = new ModelRenderer((ModelBase)this, 30, 17);
        this.cubeInside.func_78789_a(0.0f, 0.0f, 0.0f, 6, 6, 6);
        this.cubeInside.func_78793_a(-3.0f, 13.0f, -3.0f);
        this.cubeInside.func_78787_b(64, 32);
    }

    public void render() {
        float f = 0.0625f;
        this.cubeSide1.func_78785_a(f);
        this.cubeSide2.func_78785_a(f);
        this.cubeSide3.func_78785_a(f);
        this.cubeSide4.func_78785_a(f);
        this.cubeSide5.func_78785_a(f);
        this.cubeHole1.func_78785_a(f);
        this.cubeHole2.func_78785_a(f);
        this.cubeHole3.func_78785_a(f);
        this.cubeHole4.func_78785_a(f);
    }

    public void renderCube() {
        float f = 0.0625f;
        this.cubeInside.func_78785_a(f);
    }
}

