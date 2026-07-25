/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityVampire;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelVampireArmor
extends ModelBiped {
    private ModelRenderer skirtFront;
    private ModelRenderer skirtMiddle;
    private ModelRenderer skirtMiddle2;
    private ModelRenderer skirtMiddle3;
    private ModelRenderer skirtBack;
    private ModelRenderer cloakMain;
    private ModelRenderer cloakLeft;
    private ModelRenderer cloakRight;
    public ModelRenderer hat;
    public ModelRenderer hatBrim;
    public ModelRenderer chest;
    private boolean legs;
    private boolean female;
    private boolean metal;
    ResourceLocation chain = new ResourceLocation("witchery", "textures/entities/vampirearmor_chain.png");

    public ModelVampireArmor(float scale, boolean legs, boolean female, boolean metal) {
        super(scale, 0.0f, 64, 96);
        this.legs = legs;
        this.female = female;
        this.metal = metal;
        this.skirtBack = new ModelRenderer((ModelBase)this, 26, 32);
        this.skirtBack.func_78793_a(0.0f, 11.0f, 0.0f);
        this.skirtBack.func_78790_a(-4.5f, 0.0f, -2.5f, 9, 12, 5, 0.0f);
        this.skirtFront = new ModelRenderer((ModelBase)this, 26, 50);
        this.skirtFront.func_78793_a(0.0f, 11.0f, 0.0f);
        this.skirtFront.func_78790_a(-4.5f, 0.0f, -2.5f, 9, 12, 5, 0.0f);
        this.skirtMiddle = new ModelRenderer((ModelBase)this, 26, 68);
        this.skirtMiddle.func_78793_a(0.0f, 11.0f, 0.0f);
        this.skirtMiddle.func_78790_a(-4.5f, 0.0f, -2.5f, 9, 12, 5, 0.0f);
        this.skirtMiddle2 = new ModelRenderer((ModelBase)this, 26, 68);
        this.skirtMiddle2.func_78793_a(0.0f, 11.0f, 0.0f);
        this.skirtMiddle2.func_78790_a(-4.5f, 0.0f, -2.5f, 9, 12, 5, 0.0f);
        this.skirtMiddle3 = new ModelRenderer((ModelBase)this, 26, 68);
        this.skirtMiddle3.func_78793_a(0.0f, 11.0f, 0.0f);
        this.skirtMiddle3.func_78790_a(-4.5f, 0.0f, -2.5f, 9, 12, 5, 0.0f);
        this.cloakLeft = new ModelRenderer((ModelBase)this, 0, 56);
        this.cloakLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.cloakLeft.func_78790_a(-3.5f, -8.0f, 4.0f, 7, 7, 1, 0.0f);
        this.setRotateAngle(this.cloakLeft, -0.34906584f, 0.5108652f, 0.41086525f);
        this.cloakRight = new ModelRenderer((ModelBase)this, 0, 56);
        this.cloakRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.cloakRight.func_78790_a(-3.5f, -8.0f, 4.0f, 7, 7, 1, 0.0f);
        this.setRotateAngle(this.cloakRight, -0.34906584f, -0.5108652f, -0.41086525f);
        this.cloakMain = new ModelRenderer((ModelBase)this, 0, 33);
        this.cloakMain.func_78793_a(0.0f, 1.0f, 0.0f);
        this.cloakMain.func_78790_a(-6.0f, 0.0f, 2.5f, 12, 22, 1, 0.0f);
        this.setRotateAngle(this.cloakMain, 0.045553092f, 0.0f, 0.0f);
        float hatScale = 0.6f;
        this.hatBrim = new ModelRenderer((ModelBase)this, 0, 85);
        this.hatBrim.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hatBrim.func_78790_a(-5.0f, -7.0f, -5.0f, 10, 1, 10, hatScale + 0.1f);
        this.hat = new ModelRenderer((ModelBase)this, 0, 67);
        this.hat.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hat.func_78790_a(-4.0f, -15.0f, -4.0f, 8, 8, 8, hatScale);
        if (!metal) {
            this.field_78116_c.func_78792_a(this.hat);
            this.field_78116_c.func_78792_a(this.hatBrim);
        }
        this.chest = new ModelRenderer((ModelBase)this, 16, 67);
        this.chest.func_78790_a(-4.0f, -2.0f, -5.0f, 8, 4, 4, 0.0f);
        this.chest.func_78793_a(0.0f, 2.0f, 0.0f);
        this.setRotateAngle(this.chest, 0.7853982f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        if (!this.metal) {
            this.field_78114_d.field_78806_j = false;
        }
        if (this.legs) {
            if (!this.field_78093_q && this.field_78123_h.field_78806_j && this.female) {
                this.skirtBack.func_78785_a(f5);
                this.skirtFront.func_78785_a(f5);
                this.skirtMiddle.func_78785_a(f5);
                this.skirtMiddle2.func_78785_a(f5);
                this.skirtMiddle3.func_78785_a(f5);
            }
        } else if (this.field_78115_e.field_78806_j) {
            if (!(entity instanceof EntityVampire)) {
                this.cloakRight.func_78785_a(f5);
                this.cloakLeft.func_78785_a(f5);
                this.cloakMain.func_78785_a(f5);
            }
            if (this.female) {
                this.chest.func_78785_a(f5);
            }
            if (this.metal) {
                GL11.glPushMatrix();
                float scale = 1.06f;
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                Minecraft.func_71410_x().func_110434_K().func_110577_a(this.chain);
                if (this.female) {
                    this.chest.func_78785_a(f5);
                }
                this.field_78115_e.func_78785_a(f5);
                GL11.glScalef((float)scale, (float)scale, (float)scale);
                this.field_78112_f.field_78797_d = (float)((double)this.field_78112_f.field_78797_d - 0.05);
                this.field_78113_g.field_78797_d = (float)((double)this.field_78113_g.field_78797_d - 0.05);
                this.field_78112_f.func_78785_a(f5);
                this.field_78113_g.func_78785_a(f5);
                GL11.glPopMatrix();
            }
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78087_a(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
        super.func_78087_a(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
        this.field_78112_f.field_78797_d = 2.0f;
        this.field_78113_g.field_78797_d = 2.0f;
        this.hat.field_78797_d = -0.5f;
        this.skirtBack.field_78795_f = Math.max(this.field_78123_h.field_78795_f, this.field_78124_i.field_78795_f);
        this.skirtMiddle.field_78795_f = Math.max(this.field_78123_h.field_78795_f, this.field_78124_i.field_78795_f) * 0.5f;
        this.skirtFront.field_78795_f = Math.min(this.field_78123_h.field_78795_f, this.field_78124_i.field_78795_f);
        this.skirtMiddle2.field_78795_f = Math.min(this.field_78123_h.field_78795_f, this.field_78124_i.field_78795_f) * 0.5f;
        if (this.field_78117_n) {
            this.skirtMiddle2.field_78798_e = 4.0f;
            this.skirtMiddle.field_78798_e = 4.0f;
            this.skirtMiddle3.field_78798_e = 4.0f;
            this.skirtFront.field_78798_e = 4.0f;
            this.skirtBack.field_78798_e = 4.0f;
            this.skirtMiddle2.field_78797_d = 8.0f;
            this.skirtMiddle.field_78797_d = 8.0f;
            this.skirtMiddle3.field_78797_d = 8.0f;
            this.skirtFront.field_78797_d = 8.0f;
            this.skirtBack.field_78797_d = 8.0f;
            this.cloakMain.field_78795_f = 0.6f;
        } else {
            this.skirtMiddle2.field_78798_e = 0.0f;
            this.skirtMiddle.field_78798_e = 0.0f;
            this.skirtMiddle3.field_78798_e = 0.0f;
            this.skirtFront.field_78798_e = 0.0f;
            this.skirtBack.field_78798_e = 0.0f;
            this.skirtMiddle2.field_78797_d = 11.0f;
            this.skirtMiddle.field_78797_d = 11.0f;
            this.skirtMiddle3.field_78797_d = 11.0f;
            this.skirtFront.field_78797_d = 11.0f;
            this.skirtBack.field_78797_d = 11.0f;
            this.cloakMain.field_78795_f = 0.045553092f;
            if ((double)p_78087_2_ > 0.1) {
                this.cloakMain.field_78795_f = (float)((double)this.cloakMain.field_78795_f + ((double)p_78087_2_ * 0.8 - 0.1));
            }
        }
        if ((double)this.field_78116_c.field_78795_f < -0.15) {
            this.cloakLeft.field_78795_f = this.field_78116_c.field_78795_f - 0.15f;
            this.cloakRight.field_78795_f = this.field_78116_c.field_78795_f - 0.15f;
        } else {
            this.cloakRight.field_78795_f = -0.3f;
            this.cloakLeft.field_78795_f = -0.3f;
        }
    }
}

