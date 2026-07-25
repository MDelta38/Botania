/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.blocks.BlockSilverVat;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelSilverVat
extends ModelBase {
    public ModelRenderer base;
    public ModelRenderer sideBack;
    public ModelRenderer sideFront;
    public ModelRenderer sideLeft;
    public ModelRenderer sideRight;
    public ModelRenderer spoutLowerLeft;
    public ModelRenderer spoutUpperLeft;
    public ModelRenderer spoutUpperFront;
    public ModelRenderer spoutLowerFront;
    public ModelRenderer spoutUpperRight;
    public ModelRenderer spoutUpperBack;
    public ModelRenderer spoutLowerRight;
    public ModelRenderer silver1;
    public ModelRenderer spoutLowerBack;
    public ModelRenderer silver2;
    public ModelRenderer silver3;
    public ModelRenderer silver4;
    public ModelRenderer silver5;
    public ModelRenderer silver6;
    public ModelRenderer silver7;
    public ModelRenderer silver8;
    private final ModelRenderer[] silver;
    int capacity;

    public ModelSilverVat() {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.base = new ModelRenderer((ModelBase)this, 0, 19);
        this.base.func_78793_a(-6.0f, 23.0f, -6.0f);
        this.base.func_78790_a(0.0f, 0.0f, 0.0f, 12, 1, 12, 0.0f);
        this.spoutLowerRight = new ModelRenderer((ModelBase)this, 15, 0);
        this.spoutLowerRight.func_78793_a(-5.2f, 16.0f, -0.5f);
        this.spoutLowerRight.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.spoutLowerFront = new ModelRenderer((ModelBase)this, 15, 0);
        this.spoutLowerFront.func_78793_a(-0.5f, 16.0f, -5.2f);
        this.spoutLowerFront.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.silver2 = new ModelRenderer((ModelBase)this, 0, 6);
        this.silver2.func_78793_a(1.6f, 19.0f, -2.1f);
        this.silver2.func_78790_a(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f);
        this.sideRight = new ModelRenderer((ModelBase)this, 38, 10);
        this.sideRight.func_78793_a(-7.0f, 16.0f, -6.0f);
        this.sideRight.func_78790_a(0.0f, 0.0f, 0.0f, 1, 8, 12, 0.0f);
        this.spoutUpperLeft = new ModelRenderer((ModelBase)this, 15, 3);
        this.spoutUpperLeft.func_78793_a(4.0f, 14.0f, -1.5f);
        this.spoutUpperLeft.func_78790_a(0.0f, 0.0f, 0.0f, 4, 2, 3, 0.0f);
        this.sideFront = new ModelRenderer((ModelBase)this, 34, 0);
        this.sideFront.func_78793_a(-7.0f, 16.0f, -7.0f);
        this.sideFront.func_78790_a(0.0f, 0.0f, 0.0f, 14, 8, 1, 0.0f);
        this.silver7 = new ModelRenderer((ModelBase)this, 0, 22);
        this.silver7.func_78793_a(-0.5f, 19.0f, 2.0f);
        this.silver7.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.spoutUpperRight = new ModelRenderer((ModelBase)this, 15, 3);
        this.spoutUpperRight.func_78793_a(-8.0f, 14.0f, -1.5f);
        this.spoutUpperRight.func_78790_a(0.0f, 0.0f, 0.0f, 4, 2, 3, 0.0f);
        this.sideBack = new ModelRenderer((ModelBase)this, 34, 0);
        this.sideBack.func_78793_a(-7.0f, 16.0f, 6.0f);
        this.sideBack.func_78790_a(0.0f, 0.0f, 0.0f, 14, 8, 1, 0.0f);
        this.spoutUpperFront = new ModelRenderer((ModelBase)this, 15, 9);
        this.spoutUpperFront.func_78793_a(-1.5f, 14.0f, -8.0f);
        this.spoutUpperFront.func_78790_a(0.0f, 0.0f, 0.0f, 3, 2, 4, 0.0f);
        this.spoutLowerLeft = new ModelRenderer((ModelBase)this, 15, 0);
        this.spoutLowerLeft.func_78793_a(4.2f, 16.0f, -0.5f);
        this.spoutLowerLeft.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.silver8 = new ModelRenderer((ModelBase)this, 0, 25);
        this.silver8.func_78793_a(-1.2f, 19.0f, -0.3f);
        this.silver8.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.silver3 = new ModelRenderer((ModelBase)this, 0, 9);
        this.silver3.func_78793_a(-3.8f, 19.1f, 3.1f);
        this.silver3.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 2, 0.0f);
        this.silver6 = new ModelRenderer((ModelBase)this, 0, 19);
        this.silver6.func_78793_a(-4.6f, 19.1f, -1.6f);
        this.silver6.func_78790_a(0.0f, 0.0f, 0.0f, 2, 1, 1, 0.0f);
        this.spoutLowerBack = new ModelRenderer((ModelBase)this, 15, 0);
        this.spoutLowerBack.func_78793_a(-0.5f, 16.0f, 4.2f);
        this.spoutLowerBack.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.silver1 = new ModelRenderer((ModelBase)this, 0, 3);
        this.silver1.func_78793_a(-2.2f, 19.3f, -3.9f);
        this.silver1.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.silver4 = new ModelRenderer((ModelBase)this, 0, 13);
        this.silver4.func_78793_a(-3.4f, 18.8f, 0.9f);
        this.silver4.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.spoutUpperBack = new ModelRenderer((ModelBase)this, 15, 9);
        this.spoutUpperBack.func_78793_a(-1.5f, 14.0f, 4.0f);
        this.spoutUpperBack.func_78790_a(0.0f, 0.0f, 0.0f, 3, 2, 4, 0.0f);
        this.sideLeft = new ModelRenderer((ModelBase)this, 38, 10);
        this.sideLeft.func_78793_a(6.0f, 16.0f, -6.0f);
        this.sideLeft.func_78790_a(0.0f, 0.0f, 0.0f, 1, 8, 12, 0.0f);
        this.silver5 = new ModelRenderer((ModelBase)this, 0, 16);
        this.silver5.func_78793_a(1.6f, 19.0f, -0.1f);
        this.silver5.func_78790_a(0.0f, 0.0f, 0.0f, 1, 1, 1, 0.0f);
        this.silver = new ModelRenderer[]{this.silver1, this.silver2, this.silver3, this.silver4, this.silver5, this.silver6, this.silver7, this.silver8};
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockSilverVat.TileEntitySilverVat te) {
        boolean isWorld;
        this.base.func_78785_a(f5);
        this.sideRight.func_78785_a(f5);
        this.sideFront.func_78785_a(f5);
        this.sideBack.func_78785_a(f5);
        this.sideLeft.func_78785_a(f5);
        boolean bl = isWorld = te != null && te.func_145831_w() != null;
        if (!isWorld || te.func_145831_w().func_147439_a(te.field_145851_c - 1, te.field_145848_d, te.field_145849_e).func_149716_u()) {
            this.spoutUpperLeft.func_78785_a(f5);
            this.spoutLowerLeft.func_78785_a(f5);
        }
        if (!isWorld || te.func_145831_w().func_147439_a(te.field_145851_c + 1, te.field_145848_d, te.field_145849_e).func_149716_u()) {
            this.spoutUpperRight.func_78785_a(f5);
            this.spoutLowerRight.func_78785_a(f5);
        }
        if (!isWorld || te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e - 1).func_149716_u()) {
            this.spoutUpperFront.func_78785_a(f5);
            this.spoutLowerFront.func_78785_a(f5);
        }
        if (!isWorld || te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e + 1).func_149716_u()) {
            this.spoutUpperBack.func_78785_a(f5);
            this.spoutLowerBack.func_78785_a(f5);
        }
        int capacity = isWorld ? (te.func_70301_a(0) != null && te.func_70301_a((int)0).field_77994_a > 0 ? Math.max(te.func_70301_a((int)0).field_77994_a / 8, 1) : 0) : 0;
        for (int i = 0; i < capacity; ++i) {
            this.silver[i].func_78785_a(f5);
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void setCapactiy(int i) {
        this.capacity = i;
    }
}

