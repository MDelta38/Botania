/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityHornedHuntsman;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelHornedAvatar
extends ModelBase {
    ModelRenderer horns;
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer rightleg;
    ModelRenderer chest;
    ModelRenderer rightshin;
    ModelRenderer rightfoot;
    ModelRenderer rightforearm;
    ModelRenderer hips;
    ModelRenderer leftarm;
    ModelRenderer leftforearm;
    ModelRenderer leftleg;
    ModelRenderer leftshin;
    ModelRenderer leftfoot;
    ModelRenderer spear;
    private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");

    public ModelHornedAvatar() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.func_78085_a("spear.shaft", 61, 14);
        this.func_78085_a("spear.tip1", 60, 8);
        this.func_78085_a("spear.tip2", 60, 5);
        this.horns = new ModelRenderer((ModelBase)this, 0, 88);
        this.horns.func_78789_a(-10.0f, -24.0f, 0.0f, 20, 17, 0);
        this.horns.func_78793_a(0.0f, -16.0f, 0.0f);
        this.horns.func_78787_b(128, 128);
        this.horns.field_78809_i = true;
        this.setRotation(this.horns, 0.0f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 4, 112);
        this.head.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.func_78793_a(0.0f, -16.0f, 0.0f);
        this.head.func_78787_b(128, 128);
        this.head.field_78809_i = true;
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 12, 61);
        this.body.func_78789_a(-6.0f, 0.0f, -3.0f, 12, 8, 6);
        this.body.func_78793_a(0.0f, -8.0f, 0.0f);
        this.body.func_78787_b(128, 128);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 72, 50);
        this.rightarm.func_78789_a(-4.0f, -2.0f, -2.0f, 4, 13, 4);
        this.rightarm.func_78793_a(-10.0f, -13.0f, 0.0f);
        this.rightarm.func_78787_b(128, 128);
        this.rightarm.field_78809_i = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 72, 0);
        this.rightleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 10, 4);
        this.rightleg.func_78793_a(-4.0f, 3.0f, -1.0f);
        this.rightleg.func_78787_b(128, 128);
        this.rightleg.field_78809_i = true;
        this.setRotation(this.rightleg, 0.5235988f, 0.0f, 0.0f);
        this.chest = new ModelRenderer((ModelBase)this, 0, 43);
        this.chest.func_78789_a(0.0f, 0.0f, 0.0f, 20, 8, 10);
        this.chest.func_78793_a(-10.0f, -16.0f, -5.0f);
        this.chest.func_78787_b(128, 128);
        this.chest.field_78809_i = true;
        this.setRotation(this.chest, 0.0f, 0.0f, 0.0f);
        this.rightshin = new ModelRenderer((ModelBase)this, 68, 14);
        this.rightshin.func_78789_a(-3.0f, -2.0f, -3.0f, 6, 14, 6);
        this.rightshin.func_78793_a(-4.0f, 12.0f, 5.0f);
        this.rightshin.func_78787_b(128, 128);
        this.rightshin.field_78809_i = true;
        this.setRotation(this.rightshin, -0.5235988f, 0.0f, 0.0f);
        this.rightfoot = new ModelRenderer((ModelBase)this, 69, 34);
        this.rightfoot.func_78789_a(-2.0f, 0.0f, -5.0f, 4, 3, 7);
        this.rightfoot.func_78793_a(-4.0f, 21.0f, 0.0f);
        this.rightfoot.func_78787_b(128, 128);
        this.rightfoot.field_78809_i = true;
        this.setRotation(this.rightfoot, 0.0f, 0.0f, 0.0f);
        this.rightforearm = new ModelRenderer((ModelBase)this, 68, 67);
        this.rightforearm.func_78789_a(-3.0f, 0.0f, -3.0f, 6, 14, 6);
        this.rightforearm.func_78793_a(-12.0f, -3.0f, 0.0f);
        this.rightforearm.func_78787_b(128, 128);
        this.rightforearm.field_78809_i = true;
        this.setRotation(this.rightforearm, -0.5235988f, 0.0f, 0.0f);
        this.hips = new ModelRenderer((ModelBase)this, 8, 75);
        this.hips.func_78789_a(-7.0f, 0.0f, -4.0f, 14, 4, 8);
        this.hips.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hips.func_78787_b(128, 128);
        this.hips.field_78809_i = true;
        this.setRotation(this.hips, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 72, 50);
        this.leftarm.func_78789_a(0.0f, -2.0f, -2.0f, 4, 13, 4);
        this.leftarm.func_78793_a(10.0f, -13.0f, 0.0f);
        this.leftarm.func_78787_b(128, 128);
        this.leftarm.field_78809_i = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.leftforearm = new ModelRenderer((ModelBase)this, 68, 67);
        this.leftforearm.func_78789_a(-3.0f, 0.0f, -3.0f, 6, 14, 6);
        this.leftforearm.func_78793_a(12.0f, -3.0f, 0.0f);
        this.leftforearm.func_78787_b(128, 128);
        this.leftforearm.field_78809_i = true;
        this.setRotation(this.leftforearm, -0.5235988f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 72, 0);
        this.leftleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 10, 4);
        this.leftleg.func_78793_a(4.0f, 3.0f, -1.0f);
        this.leftleg.func_78787_b(128, 128);
        this.leftleg.field_78809_i = true;
        this.setRotation(this.leftleg, 0.5235988f, 0.0f, 0.0f);
        this.leftshin = new ModelRenderer((ModelBase)this, 68, 14);
        this.leftshin.func_78789_a(-3.0f, -2.0f, -3.0f, 6, 14, 6);
        this.leftshin.func_78793_a(4.0f, 12.0f, 5.0f);
        this.leftshin.func_78787_b(128, 128);
        this.leftshin.field_78809_i = true;
        this.setRotation(this.leftshin, -0.5235988f, 0.0f, 0.0f);
        this.leftfoot = new ModelRenderer((ModelBase)this, 69, 34);
        this.leftfoot.func_78789_a(-2.0f, 0.0f, -5.0f, 4, 3, 7);
        this.leftfoot.func_78793_a(4.0f, 21.0f, 0.0f);
        this.leftfoot.func_78787_b(128, 128);
        this.leftfoot.field_78809_i = true;
        this.setRotation(this.leftfoot, 0.0f, 0.0f, 0.0f);
        this.head.func_78792_a(this.horns);
        this.leftleg.func_78792_a(this.leftshin);
        this.leftshin.func_78792_a(this.leftfoot);
        this.rightleg.func_78792_a(this.rightshin);
        this.rightshin.func_78792_a(this.rightfoot);
        this.rightarm.func_78792_a(this.rightforearm);
        this.leftarm.func_78792_a(this.leftforearm);
        this.spear = new ModelRenderer((ModelBase)this, "spear");
        this.spear.func_78793_a(-12.0f, 4.0f, -10.0f);
        this.setRotation(this.spear, 0.0f, 0.0f, 0.0f);
        this.spear.field_78809_i = true;
        this.spear.func_78786_a("shaft", -0.5f, -30.0f, -0.5f, 1, 50, 1);
        this.spear.func_78786_a("tip1", -1.5f, -35.0f, 0.0f, 3, 6, 0);
        this.spear.func_78786_a("tip2", 0.0f, -35.0f, -1.5f, 0, 6, 3);
        this.rightforearm.func_78792_a(this.spear);
        this.horns.func_78793_a(0.0f, 0.0f, 0.0f);
        this.leftforearm.func_78793_a(2.0f, 10.0f, 0.0f);
        this.rightforearm.func_78793_a(-2.0f, 10.0f, 0.0f);
        this.leftshin.func_78793_a(0.0f, 10.0f, 0.0f);
        this.leftshin.field_78795_f = -0.9f;
        this.leftfoot.func_78793_a(0.0f, 10.0f, 0.0f);
        this.leftfoot.field_78795_f = 0.5f;
        this.rightshin.func_78793_a(0.0f, 10.0f, 0.0f);
        this.rightshin.field_78795_f = -0.9f;
        this.rightfoot.func_78793_a(0.0f, 10.0f, 0.0f);
        this.rightfoot.field_78795_f = 0.5f;
        this.spear.func_78793_a(0.0f, 12.0f, 0.0f);
        this.spear.field_78795_f = 1.5f;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.chest.func_78785_a(f5);
        this.hips.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
        Minecraft mc = Minecraft.func_71410_x();
        if (mc.field_71474_y.field_74347_j && Config.instance().renderHuntsmanGlintEffect) {
            float f9 = entity.field_70173_aa;
            mc.field_71446_o.func_110577_a(RES_ITEM_GLINT);
            GL11.glEnable((int)3042);
            float f10 = 0.5f;
            GL11.glColor4f((float)f10, (float)f10, (float)f10, (float)1.0f);
            GL11.glDepthFunc((int)514);
            GL11.glDepthMask((boolean)false);
            for (int k = 0; k < 2; ++k) {
                GL11.glDisable((int)2896);
                float f11 = 0.76f;
                GL11.glColor4f((float)(0.0f * f11), (float)(0.5f * f11), (float)(0.0f * f11), (float)1.0f);
                GL11.glBlendFunc((int)768, (int)1);
                GL11.glMatrixMode((int)5890);
                GL11.glLoadIdentity();
                float f12 = f9 * (0.001f + (float)k * 0.003f) * 20.0f;
                float f13 = 0.33333334f;
                GL11.glScalef((float)f13, (float)f13, (float)f13);
                GL11.glRotatef((float)(30.0f - (float)k * 60.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)0.0f, (float)f12, (float)0.0f);
                GL11.glMatrixMode((int)5888);
                this.head.func_78785_a(f5);
                this.body.func_78785_a(f5);
                this.rightarm.func_78785_a(f5);
                this.rightleg.func_78785_a(f5);
                this.chest.func_78785_a(f5);
                this.hips.func_78785_a(f5);
                this.leftarm.func_78785_a(f5);
                this.leftleg.func_78785_a(f5);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glMatrixMode((int)5890);
            GL11.glDepthMask((boolean)true);
            GL11.glLoadIdentity();
            GL11.glMatrixMode((int)5888);
            GL11.glEnable((int)2896);
            GL11.glDisable((int)3042);
            GL11.glDepthFunc((int)515);
        }
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.head.field_78796_g = par4 / 57.295776f;
        this.head.field_78795_f = par5 / 57.295776f;
        this.leftleg.field_78795_f = -1.3f * this.func_78172_a(par1, 13.0f) * par2 + 0.5f;
        this.rightleg.field_78795_f = 1.3f * this.func_78172_a(par1, 13.0f) * par2 + 0.5f;
        float angle = -1.5f * this.func_78172_a(par1, 13.0f) * par2 - 1.0f;
        this.leftshin.field_78795_f = 0.8f * (this.rightleg.field_78795_f - 0.5f) - 1.0f;
        this.rightshin.field_78795_f = 0.8f * (this.leftleg.field_78795_f - 0.5f) - 1.0f;
        this.leftfoot.field_78795_f = Math.max(1.4f * (this.leftleg.field_78795_f - 0.5f) + 0.5f, 0.2f);
        this.rightfoot.field_78795_f = Math.max(1.4f * (this.rightleg.field_78795_f - 0.5f) + 0.5f, 0.2f);
        this.leftleg.field_78796_g = 0.0f;
        this.rightleg.field_78796_g = 0.0f;
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        EntityHornedHuntsman entityDemon = (EntityHornedHuntsman)par1EntityLiving;
        int i = entityDemon.getAttackTimer();
        if (i > 0) {
            this.rightarm.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)i - par4, 10.0f);
        } else {
            this.rightarm.field_78795_f = (-0.2f + 1.5f * this.func_78172_a(par2, 13.0f)) * par3 * 0.2f;
            this.leftarm.field_78795_f = (-0.2f - 1.5f * this.func_78172_a(par2, 13.0f)) * par3 * 0.2f;
        }
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

