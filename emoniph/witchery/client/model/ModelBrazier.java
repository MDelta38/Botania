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

import com.emoniph.witchery.blocks.BlockBrazier;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelBrazier
extends ModelBase {
    ModelRenderer leg1;
    ModelRenderer leg2;
    ModelRenderer leg3;
    ModelRenderer leg4;
    ModelRenderer foot3;
    ModelRenderer foot2;
    ModelRenderer foot1;
    ModelRenderer foot4;
    ModelRenderer ash;
    ModelRenderer panSide1;
    ModelRenderer panSide2;
    ModelRenderer panSide3;
    ModelRenderer panSide4;
    ModelRenderer footBase;
    ModelRenderer panBase;

    public ModelBrazier() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.leg1 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg1.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 11, 1);
        this.leg1.func_78793_a(0.7f, 10.0f, -0.74f);
        this.leg1.func_78787_b(64, 64);
        this.leg1.field_78809_i = true;
        this.setRotation(this.leg1, 0.0f, 0.0f, 0.0f);
        this.leg2 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg2.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 11, 1);
        this.leg2.func_78793_a(-0.7f, 10.0f, -0.7f);
        this.leg2.func_78787_b(64, 64);
        this.leg2.field_78809_i = true;
        this.setRotation(this.leg2, 0.0f, 0.0f, 0.0f);
        this.leg3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg3.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 11, 1);
        this.leg3.func_78793_a(-0.7f, 10.0f, 0.7f);
        this.leg3.func_78787_b(64, 64);
        this.leg3.field_78809_i = true;
        this.setRotation(this.leg3, 0.0f, 0.0f, 0.0f);
        this.leg4 = new ModelRenderer((ModelBase)this, 0, 0);
        this.leg4.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 11, 1);
        this.leg4.func_78793_a(0.7f, 10.0f, 0.7f);
        this.leg4.func_78787_b(64, 64);
        this.leg4.field_78809_i = true;
        this.setRotation(this.leg4, 0.0f, 0.0f, 0.0f);
        this.foot3 = new ModelRenderer((ModelBase)this, 0, 13);
        this.foot3.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 5, 1);
        this.foot3.func_78793_a(-0.7f, 21.0f, 0.7f);
        this.foot3.func_78787_b(64, 64);
        this.foot3.field_78809_i = true;
        this.setRotation(this.foot3, 0.7853982f, 0.0f, 0.7853982f);
        this.foot2 = new ModelRenderer((ModelBase)this, 0, 13);
        this.foot2.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 5, 1);
        this.foot2.func_78793_a(-0.7f, 21.0f, -0.7f);
        this.foot2.func_78787_b(64, 64);
        this.foot2.field_78809_i = true;
        this.setRotation(this.foot2, -0.7853982f, 0.0f, 0.7853982f);
        this.foot1 = new ModelRenderer((ModelBase)this, 0, 13);
        this.foot1.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 5, 1);
        this.foot1.func_78793_a(0.7f, 21.0f, -0.7f);
        this.foot1.func_78787_b(64, 64);
        this.foot1.field_78809_i = true;
        this.setRotation(this.foot1, -0.7853982f, 0.0f, -0.7853982f);
        this.foot4 = new ModelRenderer((ModelBase)this, 0, 13);
        this.foot4.func_78789_a(-0.5f, 0.0f, -0.5f, 1, 5, 1);
        this.foot4.func_78793_a(0.7f, 21.0f, 0.7f);
        this.foot4.func_78787_b(64, 64);
        this.foot4.field_78809_i = true;
        this.setRotation(this.foot4, 0.7853982f, 0.0f, -0.7853982f);
        this.ash = new ModelRenderer((ModelBase)this, 0, 20);
        this.ash.func_78789_a(-2.5f, 0.0f, -2.5f, 5, 0, 5);
        this.ash.func_78793_a(0.0f, 9.7f, 0.0f);
        this.ash.func_78787_b(64, 64);
        this.ash.field_78809_i = true;
        this.setRotation(this.ash, 0.0f, 0.0f, 0.0f);
        this.panSide1 = new ModelRenderer((ModelBase)this, 5, 12);
        this.panSide1.func_78789_a(-0.5f, -0.5f, -3.0f, 1, 1, 6);
        this.panSide1.func_78793_a(3.0f, 9.5f, 0.0f);
        this.panSide1.func_78787_b(64, 64);
        this.panSide1.field_78809_i = true;
        this.setRotation(this.panSide1, 0.0f, 0.0f, 0.0f);
        this.panSide2 = new ModelRenderer((ModelBase)this, 4, 26);
        this.panSide2.func_78789_a(-3.0f, -0.5f, -0.5f, 6, 1, 1);
        this.panSide2.func_78793_a(0.0f, 9.5f, 3.0f);
        this.panSide2.func_78787_b(64, 64);
        this.panSide2.field_78809_i = true;
        this.setRotation(this.panSide2, 0.0f, 0.0f, 0.0f);
        this.panSide4 = new ModelRenderer((ModelBase)this, 4, 26);
        this.panSide4.func_78789_a(-3.0f, -0.5f, -0.5f, 6, 1, 1);
        this.panSide4.func_78793_a(0.0f, 9.5f, -3.0f);
        this.panSide4.func_78787_b(64, 64);
        this.panSide4.field_78809_i = true;
        this.setRotation(this.panSide4, 0.0f, 0.0f, 0.0f);
        this.panSide3 = new ModelRenderer((ModelBase)this, 5, 12);
        this.panSide3.func_78789_a(-0.5f, -0.5f, -3.0f, 1, 1, 6);
        this.panSide3.func_78793_a(-3.0f, 9.5f, 0.0f);
        this.panSide3.func_78787_b(64, 64);
        this.panSide3.field_78809_i = true;
        this.setRotation(this.panSide3, 0.0f, 0.0f, 0.0f);
        this.footBase = new ModelRenderer((ModelBase)this, 6, 0);
        this.footBase.func_78789_a(-1.5f, -0.5f, -1.5f, 3, 1, 3);
        this.footBase.func_78793_a(0.0f, 21.0f, 0.0f);
        this.footBase.func_78787_b(64, 64);
        this.footBase.field_78809_i = true;
        this.setRotation(this.footBase, 0.0f, 0.0f, 0.0f);
        this.panBase = new ModelRenderer((ModelBase)this, 6, 5);
        this.panBase.func_78789_a(-3.0f, 0.0f, -3.0f, 6, 1, 6);
        this.panBase.func_78793_a(0.0f, 9.95f, 0.0f);
        this.panBase.func_78787_b(64, 64);
        this.panBase.field_78809_i = true;
        this.setRotation(this.panBase, 0.0f, 0.0f, 0.0f);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockBrazier.TileEntityBrazier tile) {
        int ingredientCount;
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.leg1.func_78785_a(f5);
        this.leg2.func_78785_a(f5);
        this.leg3.func_78785_a(f5);
        this.leg4.func_78785_a(f5);
        this.foot3.func_78785_a(f5);
        this.foot2.func_78785_a(f5);
        this.foot1.func_78785_a(f5);
        this.foot4.func_78785_a(f5);
        this.panSide1.func_78785_a(f5);
        this.panSide2.func_78785_a(f5);
        this.panSide3.func_78785_a(f5);
        this.panSide4.func_78785_a(f5);
        this.footBase.func_78785_a(f5);
        this.panBase.func_78785_a(f5);
        this.panSide4.field_78795_f = 0.0f;
        this.panSide2.field_78795_f = 0.0f;
        this.panSide1.field_78808_h = 0.0f;
        this.panSide3.field_78808_h = 0.0f;
        if (tile != null && (ingredientCount = tile.getIngredientCount()) > 0) {
            this.ash.field_78797_d = 9.7f - (float)(ingredientCount - 1) * 0.1f;
            this.ash.func_78785_a(f5);
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }
}

