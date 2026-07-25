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
package com.emoniph.witchery.brewing;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SideOnly(value=Side.CLIENT)
public class ModelCauldron
extends ModelBase {
    ModelRenderer base;
    ModelRenderer bottomF;
    ModelRenderer bottomB;
    ModelRenderer bottomL;
    ModelRenderer bottomR;
    ModelRenderer sideF;
    ModelRenderer sideB;
    ModelRenderer sideL;
    ModelRenderer sideR;
    ModelRenderer neckF;
    ModelRenderer neckB;
    ModelRenderer neckL;
    ModelRenderer neckR;
    ModelRenderer lipF;
    ModelRenderer lipB;
    ModelRenderer lipL;
    ModelRenderer lipR;
    ModelRenderer legFL;
    ModelRenderer legFR;
    ModelRenderer legBL;
    ModelRenderer legBR;

    public ModelCauldron() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.base = new ModelRenderer((ModelBase)this, 0, 53);
        this.base.func_78789_a(-5.0f, 5.0f, -5.0f, 10, 1, 10);
        this.base.func_78793_a(0.0f, 16.0f, 0.0f);
        this.base.func_78787_b(64, 64);
        this.base.field_78809_i = true;
        this.setRotation(this.base, 0.0f, 0.0f, 0.0f);
        this.bottomF = new ModelRenderer((ModelBase)this, 0, 50);
        this.bottomF.func_78789_a(-5.0f, 4.0f, -6.0f, 10, 1, 1);
        this.bottomF.func_78793_a(0.0f, 16.0f, 0.0f);
        this.bottomF.func_78787_b(64, 64);
        this.bottomF.field_78809_i = true;
        this.setRotation(this.bottomF, 0.0f, 0.0f, 0.0f);
        this.bottomB = new ModelRenderer((ModelBase)this, 0, 50);
        this.bottomB.func_78789_a(-5.0f, 4.0f, 5.0f, 10, 1, 1);
        this.bottomB.func_78793_a(0.0f, 16.0f, 0.0f);
        this.bottomB.func_78787_b(64, 64);
        this.bottomB.field_78809_i = true;
        this.setRotation(this.bottomB, 0.0f, 0.0f, 0.0f);
        this.bottomL = new ModelRenderer((ModelBase)this, 0, 36);
        this.bottomL.func_78789_a(5.0f, 4.0f, -6.0f, 1, 1, 12);
        this.bottomL.func_78793_a(0.0f, 16.0f, 0.0f);
        this.bottomL.func_78787_b(64, 64);
        this.bottomL.field_78809_i = true;
        this.setRotation(this.bottomL, 0.0f, 0.0f, 0.0f);
        this.bottomR = new ModelRenderer((ModelBase)this, 0, 36);
        this.bottomR.func_78789_a(-6.0f, 4.0f, -6.0f, 1, 1, 12);
        this.bottomR.func_78793_a(0.0f, 16.0f, 0.0f);
        this.bottomR.func_78787_b(64, 64);
        this.bottomR.field_78809_i = true;
        this.setRotation(this.bottomR, 0.0f, 0.0f, 0.0f);
        this.sideF = new ModelRenderer((ModelBase)this, 27, 45);
        this.sideF.func_78789_a(-6.0f, -2.0f, -7.0f, 12, 6, 1);
        this.sideF.func_78793_a(0.0f, 16.0f, 0.0f);
        this.sideF.func_78787_b(64, 64);
        this.sideF.field_78809_i = true;
        this.setRotation(this.sideF, 0.0f, 0.0f, 0.0f);
        this.sideB = new ModelRenderer((ModelBase)this, 27, 45);
        this.sideB.func_78789_a(-6.0f, -2.0f, 6.0f, 12, 6, 1);
        this.sideB.func_78793_a(0.0f, 16.0f, 0.0f);
        this.sideB.func_78787_b(64, 64);
        this.sideB.field_78809_i = true;
        this.setRotation(this.sideB, 0.0f, 0.0f, 0.0f);
        this.sideL = new ModelRenderer((ModelBase)this, 27, 24);
        this.sideL.func_78789_a(6.0f, -2.0f, -7.0f, 1, 6, 14);
        this.sideL.func_78793_a(0.0f, 16.0f, 0.0f);
        this.sideL.func_78787_b(64, 64);
        this.sideL.field_78809_i = true;
        this.setRotation(this.sideL, 0.0f, 0.0f, 0.0f);
        this.sideR = new ModelRenderer((ModelBase)this, 27, 24);
        this.sideR.func_78789_a(-7.0f, -2.0f, -7.0f, 1, 6, 14);
        this.sideR.func_78793_a(0.0f, 16.0f, 0.0f);
        this.sideR.func_78787_b(64, 64);
        this.sideR.field_78809_i = true;
        this.setRotation(this.sideR, 0.0f, 0.0f, 0.0f);
        this.neckF = new ModelRenderer((ModelBase)this, 0, 32);
        this.neckF.func_78789_a(-5.0f, -4.0f, -6.0f, 10, 2, 1);
        this.neckF.func_78793_a(0.0f, 16.0f, 0.0f);
        this.neckF.func_78787_b(64, 64);
        this.neckF.field_78809_i = true;
        this.setRotation(this.neckF, 0.0f, 0.0f, 0.0f);
        this.neckB = new ModelRenderer((ModelBase)this, 0, 32);
        this.neckB.func_78789_a(-5.0f, -4.0f, 5.0f, 10, 2, 1);
        this.neckB.func_78793_a(0.0f, 16.0f, 0.0f);
        this.neckB.func_78787_b(64, 64);
        this.neckB.field_78809_i = true;
        this.setRotation(this.neckB, 0.0f, 0.0f, 0.0f);
        this.neckL = new ModelRenderer((ModelBase)this, 0, 17);
        this.neckL.func_78789_a(5.0f, -4.0f, -6.0f, 1, 2, 12);
        this.neckL.func_78793_a(0.0f, 16.0f, 0.0f);
        this.neckL.func_78787_b(64, 64);
        this.neckL.field_78809_i = true;
        this.setRotation(this.neckL, 0.0f, 0.0f, 0.0f);
        this.neckR = new ModelRenderer((ModelBase)this, 0, 17);
        this.neckR.func_78789_a(-6.0f, -4.0f, -6.0f, 1, 2, 12);
        this.neckR.func_78793_a(0.0f, 16.0f, 0.0f);
        this.neckR.func_78787_b(64, 64);
        this.neckR.field_78809_i = true;
        this.setRotation(this.neckR, 0.0f, 0.0f, 0.0f);
        this.lipF = new ModelRenderer((ModelBase)this, 27, 21);
        this.lipF.func_78789_a(-6.0f, -5.0f, -7.0f, 12, 1, 1);
        this.lipF.func_78793_a(0.0f, 16.0f, 0.0f);
        this.lipF.func_78787_b(64, 64);
        this.lipF.field_78809_i = true;
        this.setRotation(this.lipF, 0.0f, 0.0f, 0.0f);
        this.lipB = new ModelRenderer((ModelBase)this, 27, 21);
        this.lipB.func_78789_a(-6.0f, -5.0f, 6.0f, 12, 1, 1);
        this.lipB.func_78793_a(0.0f, 16.0f, 0.0f);
        this.lipB.func_78787_b(64, 64);
        this.lipB.field_78809_i = true;
        this.setRotation(this.lipB, 0.0f, 0.0f, 0.0f);
        this.lipL = new ModelRenderer((ModelBase)this, 27, 5);
        this.lipL.func_78789_a(6.0f, -5.0f, -7.0f, 1, 1, 14);
        this.lipL.func_78793_a(0.0f, 16.0f, 0.0f);
        this.lipL.func_78787_b(64, 64);
        this.lipL.field_78809_i = true;
        this.setRotation(this.lipL, 0.0f, 0.0f, 0.0f);
        this.lipR = new ModelRenderer((ModelBase)this, 27, 5);
        this.lipR.func_78789_a(-7.0f, -5.0f, -7.0f, 1, 1, 14);
        this.lipR.func_78793_a(0.0f, 16.0f, 0.0f);
        this.lipR.func_78787_b(64, 64);
        this.lipR.field_78809_i = true;
        this.setRotation(this.lipR, 0.0f, 0.0f, 0.0f);
        this.legFL = new ModelRenderer((ModelBase)this, 0, 0);
        this.legFL.func_78789_a(1.5f, 7.5f, -1.5f, 1, 3, 1);
        this.legFL.func_78793_a(0.0f, 16.0f, 0.0f);
        this.legFL.func_78787_b(64, 64);
        this.legFL.field_78809_i = true;
        this.setRotation(this.legFL, -0.3490659f, 0.0f, -0.3490659f);
        this.legFR = new ModelRenderer((ModelBase)this, 0, 0);
        this.legFR.func_78789_a(-2.5f, 7.5f, -1.5f, 1, 3, 1);
        this.legFR.func_78793_a(0.0f, 16.0f, 0.0f);
        this.legFR.func_78787_b(64, 64);
        this.legFR.field_78809_i = true;
        this.setRotation(this.legFR, -0.3490659f, 0.0f, 0.3490659f);
        this.legBL = new ModelRenderer((ModelBase)this, 0, 0);
        this.legBL.func_78789_a(1.5f, 7.5f, 0.5f, 1, 3, 1);
        this.legBL.func_78793_a(0.0f, 16.0f, 0.0f);
        this.legBL.func_78787_b(64, 64);
        this.legBL.field_78809_i = true;
        this.setRotation(this.legBL, 0.3490659f, 0.0f, -0.3490659f);
        this.legBR = new ModelRenderer((ModelBase)this, 0, 0);
        this.legBR.func_78789_a(-2.5f, 7.5f, 0.5f, 1, 3, 1);
        this.legBR.func_78793_a(0.0f, 16.0f, 0.0f);
        this.legBR.func_78787_b(64, 64);
        this.legBR.field_78809_i = true;
        this.setRotation(this.legBR, 0.3490659f, 0.0f, 0.3490659f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.base.func_78785_a(f5);
        this.bottomF.func_78785_a(f5);
        this.bottomB.func_78785_a(f5);
        this.bottomL.func_78785_a(f5);
        this.bottomR.func_78785_a(f5);
        this.sideF.func_78785_a(f5);
        this.sideB.func_78785_a(f5);
        this.sideL.func_78785_a(f5);
        this.sideR.func_78785_a(f5);
        this.neckF.func_78785_a(f5);
        this.neckB.func_78785_a(f5);
        this.neckL.func_78785_a(f5);
        this.neckR.func_78785_a(f5);
        this.lipF.func_78785_a(f5);
        this.lipB.func_78785_a(f5);
        this.lipL.func_78785_a(f5);
        this.lipR.func_78785_a(f5);
        this.legFL.func_78785_a(f5);
        this.legFR.func_78785_a(f5);
        this.legBL.func_78785_a(f5);
        this.legBR.func_78785_a(f5);
    }

    public void func_78087_a(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
        super.func_78087_a(f, f1, f2, f3, f4, f5, entity);
    }
}

