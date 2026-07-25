/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.ModelVillager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelVillager;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelBabaYaga
extends ModelVillager {
    public boolean field_82900_g;
    private ModelRenderer field_82901_h = new ModelRenderer((ModelBase)this).func_78787_b(64, 128);
    private ModelRenderer witchHat;
    private ModelRenderer mortar;
    private ModelRenderer pestle;
    public ModelRenderer bipedCloak = new ModelRenderer((ModelBase)this, 94, 0);

    public ModelBabaYaga(float par1) {
        super(par1, 0.0f, 128, 128);
        this.bipedCloak.func_78787_b(128, 128);
        this.bipedCloak.func_78789_a(0.0f, 0.0f, 0.0f, 8, 10, 0);
        this.bipedCloak.field_78795_f = 0.1f;
        this.field_82901_h.func_78793_a(0.0f, -2.0f, 0.0f);
        this.field_82901_h.func_78784_a(0, 0).func_78790_a(0.0f, 3.0f, -6.75f, 1, 1, 1, -0.15f);
        this.field_82898_f.func_78792_a(this.field_82901_h);
        this.witchHat = new ModelRenderer((ModelBase)this).func_78787_b(128, 128);
        this.witchHat.func_78793_a(-7.0f, -10.03125f, -7.0f);
        this.witchHat.func_78784_a(0, 98).func_78789_a(0.0f, 0.0f, 0.0f, 14, 2, 14);
        this.field_78191_a.func_78792_a(this.witchHat);
        ModelRenderer modelrenderer = new ModelRenderer((ModelBase)this).func_78787_b(128, 128);
        modelrenderer.func_78793_a(3.75f, -4.0f, 4.0f);
        modelrenderer.func_78784_a(0, 76).func_78789_a(0.0f, 0.0f, 0.0f, 7, 4, 7);
        modelrenderer.field_78795_f = -0.05235988f;
        modelrenderer.field_78808_h = 0.02617994f;
        this.witchHat.func_78792_a(modelrenderer);
        ModelRenderer modelrenderer1 = new ModelRenderer((ModelBase)this).func_78787_b(128, 128);
        modelrenderer1.func_78793_a(1.75f, -4.0f, 2.0f);
        modelrenderer1.func_78784_a(0, 87).func_78789_a(0.0f, 0.0f, 0.0f, 4, 4, 4);
        modelrenderer1.field_78795_f = -0.10471976f;
        modelrenderer1.field_78808_h = 0.05235988f;
        modelrenderer.func_78792_a(modelrenderer1);
        ModelRenderer modelrenderer2 = new ModelRenderer((ModelBase)this).func_78787_b(128, 128);
        modelrenderer2.func_78793_a(1.75f, -2.0f, 2.0f);
        modelrenderer2.func_78784_a(0, 95).func_78790_a(0.0f, 0.0f, 0.0f, 1, 2, 1, 0.25f);
        modelrenderer2.field_78795_f = -0.20943952f;
        modelrenderer2.field_78808_h = 0.10471976f;
        modelrenderer1.func_78792_a(modelrenderer2);
        this.func_78085_a("mortar.bottom", 80, 88);
        this.func_78085_a("mortar.top", 72, 107);
        this.func_78085_a("pestle.upper", 124, 0);
        this.func_78085_a("pestle.lower", 116, 13);
        this.mortar = new ModelRenderer((ModelBase)this, "mortar");
        this.mortar.func_78787_b(128, 128);
        this.mortar.func_78793_a(-7.0f, 10.0f, -8.0f);
        this.setRotation(this.mortar, 0.0f, 0.0f, 0.0f);
        this.mortar.field_78809_i = true;
        this.mortar.func_78786_a("bottom", 1.0f, 7.0f, 2.0f, 12, 7, 12);
        this.mortar.func_78786_a("top", 0.0f, 0.0f, 1.0f, 14, 7, 14);
        this.pestle = new ModelRenderer((ModelBase)this, "pestle");
        this.pestle.func_78787_b(128, 128);
        this.pestle.func_78793_a(-3.0f, 6.0f, -4.0f);
        this.setRotation(this.pestle, -1.152537f, -2.305074f, 1.839205f);
        this.pestle.field_78809_i = true;
        this.pestle.func_78786_a("upper", -1.0f, -7.0f, 0.0f, 1, 12, 1);
        this.pestle.func_78786_a("lower", -2.0f, 5.0f, -1.0f, 3, 12, 3);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        GL11.glTranslatef((float)0.0f, (float)-0.2f, (float)0.0f);
        super.func_78088_a(par1Entity, par2, par3, par4, par5, par6, par7);
        this.mortar.func_78785_a(par7);
        this.pestle.func_78785_a(par7);
        this.bipedCloak.func_78785_a(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.field_78191_a.field_78796_g = par4 / 57.295776f;
        this.field_78191_a.field_78795_f = par5 / 57.295776f;
        this.field_78190_c.field_78797_d = 3.0f;
        this.field_78190_c.field_78798_e = -1.0f;
        this.field_78190_c.field_78795_f = -0.75f;
        this.bipedCloak.func_78793_a(-3.5f, -0.5f, 3.5f);
        this.field_82898_f.field_82907_q = 0.0f;
        this.field_82898_f.field_82908_p = 0.0f;
        this.field_82898_f.field_82906_o = 0.0f;
        float f6 = 0.01f * (float)(par7Entity.func_145782_y() % 10);
        this.field_82898_f.field_78795_f = MathHelper.func_76126_a((float)((float)par7Entity.field_70173_aa * f6)) * 4.5f * (float)Math.PI / 180.0f;
        this.field_82898_f.field_78796_g = 0.0f;
        this.field_82898_f.field_78808_h = MathHelper.func_76134_b((float)((float)par7Entity.field_70173_aa * f6)) * 2.5f * (float)Math.PI / 180.0f;
        if (this.field_82900_g) {
            this.field_82898_f.field_78795_f = -0.9f;
            this.field_82898_f.field_82907_q = -0.09375f;
            this.field_82898_f.field_82908_p = 0.1875f;
        }
        this.pestle.field_78795_f = -1.152537f + MathHelper.func_76126_a((float)((float)par7Entity.field_70173_aa * f6)) * 4.5f * (float)Math.PI / 180.0f;
        this.pestle.field_78796_g = -2.305074f;
        this.pestle.field_78808_h = 1.839205f + MathHelper.func_76134_b((float)((float)par7Entity.field_70173_aa * f6)) * 2.5f * (float)Math.PI / 180.0f;
    }
}

