/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class ModelGolemAccessories
extends ModelBase {
    public ModelRenderer golemHeadFez;
    public ModelRenderer golemHeadGlasses;
    public ModelRenderer golemHeadHat;
    public ModelRenderer golemHeadHatRim;
    public ModelRenderer golemBowtie;
    public ModelRenderer golemDartgun;
    public ModelRenderer golemMace;
    public ModelRenderer golemVisor;
    public ModelRenderer golemPlate;
    public ModelRenderer golemPlateLeft;
    public ModelRenderer golemPlateRight;
    public ModelRenderer golemHeadJar;
    public ModelRenderer golemHeadBrain;
    public ModelRenderer golemEvilHead;

    public ModelGolemAccessories() {
        this(0.0f);
    }

    public ModelGolemAccessories(float par1) {
        this(par1, -7.0f);
    }

    public ModelGolemAccessories(float par1, float par2) {
        int var3 = 128;
        int var4 = 128;
        this.golemHeadFez = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemHeadFez.func_78793_a(0.0f, 0.0f + par2, -2.0f);
        this.golemHeadFez.func_78784_a(0, 94).func_78790_a(-4.5f, -15.0f, -6.0f, 9, 7, 9, par1);
        this.golemPlate = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemPlate.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.golemPlate.func_78784_a(32, 40).func_78790_a(-6.5f, -1.0f, -7.0f, 13, 12, 13, par1);
        this.golemPlateLeft = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemPlateLeft.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.golemPlateLeft.func_78784_a(0, 44).func_78790_a(-8.5f, -4.0f, -6.5f, 3, 6, 12, par1);
        this.golemPlateRight = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemPlateRight.field_78809_i = true;
        this.golemPlateRight.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.golemPlateRight.func_78784_a(0, 44).func_78790_a(5.5f, -4.0f, -6.5f, 3, 6, 12, par1);
        this.golemHeadHat = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemHeadHat.func_78793_a(0.0f, 0.0f + par2, -2.0f);
        this.golemHeadHat.func_78784_a(0, 110).func_78790_a(-4.5f, -17.0f, -6.0f, 9, 9, 9, par1);
        this.golemHeadGlasses = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemHeadGlasses.func_78793_a(0.0f, 0.0f + par2, -2.0f);
        this.golemHeadGlasses.func_78784_a(0, 80).func_78790_a(-4.5f, -8.0f, -6.0f, 9, 4, 9, par1);
        this.golemVisor = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemVisor.func_78793_a(0.0f, 0.0f + par2, -2.0f);
        this.golemVisor.func_78784_a(0, 70).func_78790_a(-5.0f, -8.0f, -6.0f, 10, 5, 5, par1);
        this.golemHeadHatRim = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemHeadHatRim.func_78793_a(0.0f, 0.0f + par2, -2.0f);
        this.golemHeadHatRim.func_78784_a(36, 114).func_78790_a(-6.5f, -9.0f, -8.0f, 13, 1, 13, 0.0f);
        this.golemDartgun = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemDartgun.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.golemDartgun.func_78784_a(80, 80).func_78790_a(7.9f, 7.5f, -3.5f, 6, 16, 7, par1);
        this.golemMace = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemMace.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.golemMace.func_78784_a(80, 26).func_78790_a(-13.0f, 15.0f, -5.0f, 6, 8, 10, par1);
        this.golemBowtie = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemBowtie.func_78793_a(0.0f, 0.0f + par2, 0.0f);
        this.golemBowtie.func_78784_a(0, 0).func_78790_a(-8.5f, -2.0f, -6.5f, 17, 4, 12, par1);
        this.golemHeadJar = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemHeadJar.func_78793_a(0.0f, 0.0f + par2, -2.0f);
        this.golemHeadJar.func_78784_a(96, 56).func_78790_a(-4.0f, -15.0f, -5.5f, 8, 4, 8, par1);
        this.golemHeadBrain = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemHeadBrain.func_78793_a(0.0f, 0.0f + par2, -2.0f);
        this.golemHeadBrain.func_78784_a(96, 70).func_78790_a(-3.5f, -14.0f, -5.0f, 7, 3, 7, par1);
        this.golemEvilHead = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemEvilHead.func_78793_a(0.0f, 0.0f + par2, -2.0f);
        this.golemEvilHead.func_78784_a(64, 65).func_78790_a(-4.0f, -9.0f, -5.5f, 8, 7, 8, par1);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        EntityGolemBase en = (EntityGolemBase)par1Entity;
        this.setRotationAngles(par2, par3, par4, par5, par6, par7, en);
        GL11.glPushMatrix();
        GL11.glScaled((double)0.4, (double)0.4, (double)0.4);
        String deco = en.getGolemDecoration();
        if (deco != null && deco.contains("R")) {
            this.golemDartgun.func_78785_a(par7);
        }
        GL11.glPushMatrix();
        if (deco != null && deco.contains("F")) {
            if (en.advanced) {
                GL11.glTranslatef((float)0.0f, (float)-0.01f, (float)0.0f);
            }
            this.golemHeadFez.func_78785_a(par7);
        }
        if (deco != null && deco.contains("H")) {
            if (en.advanced) {
                GL11.glTranslatef((float)0.0f, (float)-0.01f, (float)0.0f);
            }
            this.golemHeadHat.func_78785_a(par7);
            this.golemHeadHatRim.func_78785_a(par7);
        }
        GL11.glPopMatrix();
        if (deco != null && deco.contains("B")) {
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            this.golemBowtie.func_78785_a(par7);
            GL11.glDisable((int)3042);
        }
        if (deco != null && deco.contains("P")) {
            this.golemPlate.func_78785_a(par7);
            this.golemPlateLeft.func_78785_a(par7);
            this.golemPlateRight.func_78785_a(par7);
        }
        if (deco != null && deco.contains("G")) {
            this.golemHeadGlasses.func_78785_a(par7);
        }
        if (deco != null && deco.contains("V")) {
            this.golemVisor.func_78785_a(par7);
        }
        if (deco != null && deco.contains("M")) {
            this.golemMace.func_78785_a(par7);
        }
        if (en.advanced) {
            this.golemHeadBrain.func_78785_a(par7);
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            this.golemHeadJar.func_78785_a(par7);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            if (en.getCore() >= 0) {
                GL11.glPushMatrix();
                GL11.glScaled((double)1.01, (double)1.0, (double)1.01);
                this.golemEvilHead.func_78785_a(par7);
                GL11.glPopMatrix();
            }
        }
        GL11.glPopMatrix();
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, EntityGolemBase en) {
        if (en.getCore() == -1 || en.bootup < 0.0f) {
            this.golemHeadFez.field_78796_g = 0.0f;
            this.golemHeadFez.field_78795_f = 0.57595867f;
        } else if (en.inactive) {
            this.golemHeadFez.field_78796_g = 0.0f;
            this.golemHeadFez.field_78795_f = 0.57595867f;
        } else if (en.bootup > 0.0f) {
            this.golemHeadFez.field_78796_g = 0.0f;
            this.golemHeadFez.field_78795_f = en.bootup / 57.295776f;
        } else {
            this.golemHeadFez.field_78796_g = par4 / 57.295776f;
            this.golemHeadFez.field_78795_f = par5 / 57.295776f;
        }
        this.golemHeadGlasses.field_78796_g = this.golemHeadFez.field_78796_g;
        this.golemHeadGlasses.field_78795_f = this.golemHeadFez.field_78795_f;
        this.golemHeadJar.field_78796_g = this.golemHeadFez.field_78796_g;
        this.golemHeadJar.field_78795_f = this.golemHeadFez.field_78795_f;
        this.golemHeadBrain.field_78796_g = this.golemHeadFez.field_78796_g;
        this.golemHeadBrain.field_78795_f = this.golemHeadFez.field_78795_f;
        this.golemEvilHead.field_78796_g = this.golemHeadFez.field_78796_g;
        this.golemEvilHead.field_78795_f = this.golemHeadFez.field_78795_f;
        this.golemVisor.field_78796_g = this.golemHeadFez.field_78796_g;
        this.golemVisor.field_78795_f = this.golemHeadFez.field_78795_f;
        this.golemHeadHat.field_78796_g = this.golemHeadFez.field_78796_g;
        this.golemHeadHat.field_78795_f = this.golemHeadFez.field_78795_f;
        this.golemHeadHatRim.field_78796_g = this.golemHeadFez.field_78796_g;
        this.golemHeadHatRim.field_78795_f = this.golemHeadFez.field_78795_f;
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        EntityGolemBase var5 = (EntityGolemBase)par1EntityLiving;
        int var6 = var5.getActionTimer();
        if (var6 > 0) {
            this.golemDartgun.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)var6 - par4, 10.0f);
            this.golemMace.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)var6 - par4, 10.0f);
        } else if (var5.leftArm > 0 || var5.rightArm > 0) {
            if (var5.leftArm > 0) {
                this.golemDartgun.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)var5.leftArm - par4, 10.0f);
            }
            if (var5.rightArm > 0) {
                this.golemMace.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)var5.rightArm - par4, 10.0f);
            }
        } else if (var5.getCarriedForDisplay() != null) {
            this.golemDartgun.field_78795_f = -1.0f;
            this.golemMace.field_78795_f = -1.0f;
        } else {
            this.golemDartgun.field_78795_f = (-0.2f - 1.5f * this.func_78172_a(par2, 13.0f)) * par3;
            this.golemMace.field_78795_f = (-0.2f + 1.5f * this.func_78172_a(par2, 13.0f)) * par3;
        }
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

