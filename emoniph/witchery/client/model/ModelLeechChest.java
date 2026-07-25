/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 */
package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

@SideOnly(value=Side.CLIENT)
public class ModelLeechChest
extends ModelBase {
    public ModelRenderer chestBelow;
    public ModelRenderer chestLidBL;
    public ModelRenderer chestLidFR;
    public ModelRenderer chestLidBR;
    public ModelRenderer chestLidFL;
    public ModelRenderer sac1;
    public ModelRenderer sac2;
    public ModelRenderer sac3;

    public ModelLeechChest() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.chestBelow = new ModelRenderer((ModelBase)this, 0, 0);
        this.chestBelow.func_78789_a(0.0f, 0.0f, 0.0f, 14, 9, 14);
        this.chestBelow.func_78793_a(1.0f, 7.0f, 1.0f);
        this.chestBelow.func_78787_b(64, 64);
        this.chestBelow.field_78809_i = true;
        this.setRotation(this.chestBelow, 0.0f, 0.0f, 0.0f);
        this.chestLidBL = new ModelRenderer((ModelBase)this, 28, 24);
        this.chestLidBL.func_78789_a(-6.0f, -5.0f, -6.0f, 6, 5, 6);
        this.chestLidBL.func_78793_a(14.0f, 7.0f, 14.0f);
        this.chestLidBL.func_78787_b(64, 64);
        this.chestLidBL.field_78809_i = true;
        this.setRotation(this.chestLidBL, 0.0f, 0.0f, 0.0f);
        this.chestLidFR = new ModelRenderer((ModelBase)this, 0, 36);
        this.chestLidFR.func_78789_a(0.0f, -5.0f, 0.0f, 6, 5, 6);
        this.chestLidFR.func_78793_a(2.0f, 7.0f, 2.0f);
        this.chestLidFR.func_78787_b(64, 64);
        this.chestLidFR.field_78809_i = true;
        this.setRotation(this.chestLidFR, 0.0f, 0.0f, 0.0f);
        this.chestLidBR = new ModelRenderer((ModelBase)this, 0, 24);
        this.chestLidBR.func_78789_a(0.0f, -5.0f, -6.0f, 6, 5, 6);
        this.chestLidBR.func_78793_a(2.0f, 7.0f, 14.0f);
        this.chestLidBR.func_78787_b(64, 64);
        this.chestLidBR.field_78809_i = true;
        this.setRotation(this.chestLidBR, 0.0f, 0.0f, 0.0f);
        this.chestLidFL = new ModelRenderer((ModelBase)this, 28, 36);
        this.chestLidFL.func_78789_a(-6.0f, -5.0f, 0.0f, 6, 5, 6);
        this.chestLidFL.func_78793_a(14.0f, 7.0f, 2.0f);
        this.chestLidFL.func_78787_b(64, 64);
        this.chestLidFL.field_78809_i = true;
        this.setRotation(this.chestLidFL, 0.0f, 0.0f, 0.0f);
        this.sac1 = new ModelRenderer((ModelBase)this, 0, 8);
        this.sac1.func_78789_a(0.0f, 0.0f, 0.0f, 2, 3, 1);
        this.sac1.func_78793_a(3.0f, 8.0f, 0.0f);
        this.sac1.func_78787_b(64, 64);
        this.sac1.field_78809_i = true;
        this.setRotation(this.sac1, 0.0f, 0.0f, 0.0f);
        this.sac2 = new ModelRenderer((ModelBase)this, 0, 3);
        this.sac2.func_78789_a(0.0f, 0.0f, 0.0f, 3, 2, 1);
        this.sac2.func_78793_a(9.0f, 13.0f, 0.0f);
        this.sac2.func_78787_b(64, 64);
        this.sac2.field_78809_i = true;
        this.setRotation(this.sac2, 0.0f, 0.0f, 0.0f);
        this.sac3 = new ModelRenderer((ModelBase)this, 0, 0);
        this.sac3.func_78789_a(0.0f, 0.0f, 0.0f, 2, 1, 1);
        this.sac3.func_78793_a(9.0f, 9.0f, 0.0f);
        this.sac3.func_78787_b(64, 64);
        this.sac3.field_78809_i = true;
        this.setRotation(this.sac3, 0.0f, 0.0f, 0.0f);
    }

    public void renderAll(int count) {
        this.chestLidBL.func_78785_a(0.0625f);
        this.chestLidFL.func_78785_a(0.0625f);
        this.chestLidBR.func_78785_a(0.0625f);
        this.chestLidFR.func_78785_a(0.0625f);
        this.chestBelow.func_78785_a(0.0625f);
        if (count >= 1) {
            this.sac1.func_78785_a(0.0625f);
        }
        if (count >= 2) {
            this.sac2.func_78785_a(0.0625f);
        }
        if (count >= 3) {
            this.sac3.func_78785_a(0.0625f);
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

