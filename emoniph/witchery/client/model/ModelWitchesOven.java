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

import com.emoniph.witchery.blocks.BlockWitchesOven;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelWitchesOven
extends ModelBase {
    ModelRenderer body;
    ModelRenderer lidBottom;
    ModelRenderer lidTop;
    ModelRenderer chimney;
    ModelRenderer chimneyTop;
    ModelRenderer legBackRight;
    ModelRenderer legFrontRight;
    ModelRenderer legBackLeft;
    ModelRenderer legFrontLeft;

    public ModelWitchesOven() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.func_78085_a("legBackRight.legBackRightH", 0, 0);
        this.func_78085_a("legBackRight.legBackRightV", 0, 2);
        this.func_78085_a("legFrontRight.legFrontRightH", 0, 0);
        this.func_78085_a("legFrontRight.legFrontRightV", 0, 2);
        this.func_78085_a("legBackLeft.legBackLeftH", 0, 0);
        this.func_78085_a("legBackLeft.legBackLeftV", 0, 2);
        this.func_78085_a("legFrontLeft.legFrontLeftH", 0, 0);
        this.func_78085_a("legFrontLeft.legFrontLeftV", 0, 2);
        this.body = new ModelRenderer((ModelBase)this, 0, 0);
        this.body.func_78789_a(0.0f, 1.0f, 0.0f, 12, 8, 12);
        this.body.func_78793_a(-6.0f, 14.0f, -6.0f);
        this.body.func_78787_b(64, 64);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.lidBottom = new ModelRenderer((ModelBase)this, 0, 20);
        this.lidBottom.func_78789_a(0.0f, 0.0f, 0.0f, 14, 1, 14);
        this.lidBottom.func_78793_a(-7.0f, 14.0f, -7.0f);
        this.lidBottom.func_78787_b(64, 64);
        this.lidBottom.field_78809_i = true;
        this.setRotation(this.lidBottom, 0.0f, 0.0f, 0.0f);
        this.lidTop = new ModelRenderer((ModelBase)this, 8, 35);
        this.lidTop.func_78789_a(0.0f, 0.0f, 0.0f, 10, 1, 10);
        this.lidTop.func_78793_a(-5.0f, 13.0f, -5.0f);
        this.lidTop.func_78787_b(64, 64);
        this.lidTop.field_78809_i = true;
        this.setRotation(this.lidTop, 0.0f, 0.0f, 0.0f);
        this.chimney = new ModelRenderer((ModelBase)this, 48, 0);
        this.chimney.func_78789_a(0.0f, 0.0f, 0.0f, 4, 13, 4);
        this.chimney.func_78793_a(-2.0f, 8.0f, 3.0f);
        this.chimney.func_78787_b(64, 64);
        this.chimney.field_78809_i = true;
        this.setRotation(this.chimney, 0.0f, 0.0f, 0.0f);
        this.chimneyTop = new ModelRenderer((ModelBase)this, 38, 0);
        this.chimneyTop.func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 1);
        this.chimneyTop.func_78793_a(-2.0f, 8.0f, 7.0f);
        this.chimneyTop.func_78787_b(64, 64);
        this.chimneyTop.field_78809_i = true;
        this.setRotation(this.chimneyTop, 0.0f, 0.0f, 0.0f);
        this.legBackRight = new ModelRenderer((ModelBase)this, "legBackRight");
        this.legBackRight.func_78793_a(-5.0f, 21.0f, -7.0f);
        this.setRotation(this.legBackRight, 0.0f, 0.0f, 0.0f);
        this.legBackRight.field_78809_i = true;
        this.legBackRight.func_78786_a("legBackRightH", -2.0f, 0.0f, 0.0f, 2, 1, 1);
        this.legBackRight.func_78786_a("legBackRightV", -3.0f, 0.0f, 0.0f, 1, 3, 1);
        this.legFrontRight = new ModelRenderer((ModelBase)this, "legFrontRight");
        this.legFrontRight.func_78793_a(-5.0f, 21.0f, 6.0f);
        this.setRotation(this.legFrontRight, 0.0f, 0.0f, 0.0f);
        this.legFrontRight.field_78809_i = true;
        this.legFrontRight.func_78786_a("legFrontRightH", -2.0f, 0.0f, 0.0f, 2, 1, 1);
        this.legFrontRight.func_78786_a("legFrontRightV", -3.0f, 0.0f, 0.0f, 1, 3, 1);
        this.legBackLeft = new ModelRenderer((ModelBase)this, "legBackLeft");
        this.legBackLeft.func_78793_a(5.0f, 21.0f, -7.0f);
        this.setRotation(this.legBackLeft, 0.0f, 0.0f, 0.0f);
        this.legBackLeft.field_78809_i = true;
        this.legBackLeft.func_78786_a("legBackLeftH", 0.0f, 0.0f, 0.0f, 2, 1, 1);
        this.legBackLeft.func_78786_a("legBackLeftV", 2.0f, 0.0f, 0.0f, 1, 3, 1);
        this.legFrontLeft = new ModelRenderer((ModelBase)this, "legFrontLeft");
        this.legFrontLeft.func_78793_a(5.0f, 21.0f, 6.0f);
        this.setRotation(this.legFrontLeft, 0.0f, 0.0f, 0.0f);
        this.legFrontLeft.field_78809_i = true;
        this.legFrontLeft.func_78786_a("legFrontLeftH", 0.0f, 0.0f, 0.0f, 2, 1, 1);
        this.legFrontLeft.func_78786_a("legFrontLeftV", 2.0f, 0.0f, 0.0f, 1, 3, 1);
    }

    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, BlockWitchesOven.TileEntityWitchesOven te) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.body.func_78785_a(f5);
        this.lidBottom.func_78785_a(f5);
        this.lidTop.func_78785_a(f5);
        this.chimney.func_78785_a(f5);
        this.chimneyTop.func_78785_a(f5);
        this.legBackRight.func_78785_a(f5);
        this.legFrontRight.func_78785_a(f5);
        this.legBackLeft.func_78785_a(f5);
        this.legFrontLeft.func_78785_a(f5);
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

