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

public class ModelAltar
extends ModelBase {
    ModelRenderer gobletBase;
    ModelRenderer gobletBase2;
    ModelRenderer gobletBase3;
    ModelRenderer gobletStand;
    ModelRenderer kelkBase;
    ModelRenderer kelkBase2;
    ModelRenderer kelkWall1;
    ModelRenderer kelkWall2;
    ModelRenderer kelkWall3;
    ModelRenderer kelkWall4;

    public ModelAltar() {
        this.field_78090_t = 256;
        this.field_78089_u = 128;
        this.gobletBase = new ModelRenderer((ModelBase)this, 0, 0);
        this.gobletBase.func_78789_a(0.0f, 0.0f, 0.0f, 16, 2, 16);
        this.gobletBase.func_78793_a(-8.0f, 22.0f, -8.0f);
        this.gobletBase.func_78787_b(256, 128);
        this.gobletBase2 = new ModelRenderer((ModelBase)this, 0, 18);
        this.gobletBase2.func_78789_a(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.gobletBase2.func_78793_a(-7.0f, 21.0f, -7.0f);
        this.gobletBase2.func_78787_b(256, 128);
        this.gobletStand = new ModelRenderer((ModelBase)this, 68, 0);
        this.gobletStand.func_78789_a(0.0f, 0.0f, 0.0f, 6, 8, 6);
        this.gobletStand.func_78793_a(-3.0f, 12.0f, -3.0f);
        this.gobletStand.func_78787_b(256, 128);
        this.gobletBase3 = new ModelRenderer((ModelBase)this, 0, 33);
        this.gobletBase3.func_78789_a(0.0f, 0.0f, 0.0f, 12, 1, 12);
        this.gobletBase3.func_78793_a(-6.0f, 20.0f, -6.0f);
        this.gobletBase3.func_78787_b(256, 128);
        this.kelkBase = new ModelRenderer((ModelBase)this, 72, 45);
        this.kelkBase.func_78789_a(0.0f, 0.0f, 0.0f, 8, 1, 8);
        this.kelkBase.func_78793_a(-4.0f, 11.0f, -4.0f);
        this.kelkBase.func_78787_b(256, 128);
        this.kelkBase2 = new ModelRenderer((ModelBase)this, 72, 34);
        this.kelkBase2.func_78789_a(0.0f, 0.0f, 0.0f, 10, 1, 10);
        this.kelkBase2.func_78793_a(-5.0f, 10.0f, -5.0f);
        this.kelkBase2.func_78787_b(256, 128);
        this.kelkWall1 = new ModelRenderer((ModelBase)this, 72, 18);
        this.kelkWall1.func_78789_a(0.0f, 0.0f, 0.0f, 1, 6, 10);
        this.kelkWall1.func_78793_a(5.0f, 4.0f, -5.0f);
        this.kelkWall1.func_78787_b(256, 128);
        this.kelkWall2 = new ModelRenderer((ModelBase)this, 72, 18);
        this.kelkWall2.func_78789_a(0.0f, 0.0f, 0.0f, 1, 6, 10);
        this.kelkWall2.func_78793_a(-6.0f, 4.0f, -5.0f);
        this.kelkWall2.func_78787_b(256, 128);
        this.kelkWall3 = new ModelRenderer((ModelBase)this, 94, 18);
        this.kelkWall3.func_78789_a(0.0f, 0.0f, 0.0f, 10, 6, 1);
        this.kelkWall3.func_78793_a(-5.0f, 4.0f, 5.0f);
        this.kelkWall3.func_78787_b(256, 128);
        this.kelkWall4 = new ModelRenderer((ModelBase)this, 94, 18);
        this.kelkWall4.func_78789_a(0.0f, 0.0f, 0.0f, 10, 6, 1);
        this.kelkWall4.func_78793_a(-5.0f, 4.0f, -6.0f);
        this.kelkWall4.func_78787_b(256, 128);
    }

    public void render() {
        float f = 0.0625f;
        this.gobletBase.func_78785_a(f);
        this.gobletBase2.func_78785_a(f);
        this.gobletBase3.func_78785_a(f);
        this.gobletStand.func_78785_a(f);
        this.kelkBase.func_78785_a(f);
        this.kelkBase2.func_78785_a(f);
        this.kelkWall1.func_78785_a(f);
        this.kelkWall2.func_78785_a(f);
        this.kelkWall3.func_78785_a(f);
        this.kelkWall4.func_78785_a(f);
    }
}

