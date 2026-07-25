/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.entity.EntityGoblin;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelGoblin
extends ModelBase {
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public int heldItemLeft;
    public int heldItemRight;
    public boolean isSneak;
    public boolean aimedBow;

    public ModelGoblin() {
        this(0.0f);
    }

    public ModelGoblin(float scale) {
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.func_78085_a("head.face", 0, 0);
        this.func_78085_a("head.nose1", 34, 3);
        this.func_78085_a("head.nose2", 34, 0);
        this.func_78085_a("head.nose3", 33, 9);
        this.func_78085_a("head.earTipLeft", 46, 0);
        this.func_78085_a("head.earInnerLeft", 39, 0);
        this.func_78085_a("head.earInnerRight", 39, 0);
        this.func_78085_a("head.earTipRight", 46, 0);
        this.bipedHead = new ModelRenderer((ModelBase)this, "head");
        this.bipedHead.func_78793_a(0.0f, 11.0f, 0.0f);
        this.setRotation(this.bipedHead, 0.0f, 0.0f, 0.0f);
        this.bipedHead.field_78809_i = true;
        this.bipedHead.func_78786_a("face", -4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.bipedHead.func_78786_a("nose1", -0.5f, -6.0f, -5.0f, 1, 3, 1);
        this.bipedHead.func_78786_a("nose2", -0.5f, -5.0f, -6.0f, 1, 1, 1);
        this.bipedHead.func_78786_a("nose3", -0.5f, -4.0f, -7.0f, 1, 2, 2);
        this.bipedHead.func_78786_a("earTipLeft", 6.0f, -7.0f, 0.0f, 2, 2, 1);
        this.bipedHead.func_78786_a("earInnerLeft", 4.0f, -7.0f, 0.0f, 2, 3, 1);
        this.bipedHead.func_78786_a("earInnerRight", -6.0f, -7.0f, 0.0f, 2, 3, 1);
        this.bipedHead.func_78786_a("earTipRight", -8.0f, -7.0f, 0.0f, 2, 2, 1);
        this.bipedBody = new ModelRenderer((ModelBase)this, 16, 16);
        this.bipedBody.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 7, 4, scale);
        this.bipedBody.func_78793_a(0.0f, 11.0f, 0.0f);
        this.bipedBody.func_78787_b(64, 32);
        this.bipedBody.field_78809_i = true;
        this.setRotation(this.bipedBody, 0.0f, 0.0f, 0.0f);
        this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedRightArm.func_78790_a(-3.0f, -3.0f, -2.0f, 4, 12, 4, scale);
        this.bipedRightArm.func_78793_a(-5.0f, 12.0f, 0.0f);
        this.bipedRightArm.func_78787_b(64, 32);
        this.bipedRightArm.field_78809_i = true;
        this.setRotation(this.bipedRightArm, 0.0f, 0.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedLeftArm.func_78790_a(-1.0f, -3.0f, -2.0f, 4, 12, 4, scale);
        this.bipedLeftArm.func_78793_a(5.0f, 12.0f, 0.0f);
        this.bipedLeftArm.func_78787_b(64, 32);
        this.bipedLeftArm.field_78809_i = true;
        this.setRotation(this.bipedLeftArm, 0.0f, 0.0f, 0.0f);
        this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedRightLeg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, scale);
        this.bipedRightLeg.func_78793_a(-2.0f, 18.0f, 0.0f);
        this.bipedRightLeg.func_78787_b(64, 32);
        this.bipedRightLeg.field_78809_i = true;
        this.setRotation(this.bipedRightLeg, 0.0f, 0.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedLeftLeg.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, scale);
        this.bipedLeftLeg.func_78793_a(2.0f, 18.0f, 0.0f);
        this.bipedLeftLeg.func_78787_b(64, 32);
        this.bipedLeftLeg.field_78809_i = true;
        this.setRotation(this.bipedLeftLeg, 0.0f, 0.0f, 0.0f);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        if (this.field_78091_s) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.5f / f6), (float)(1.5f / f6), (float)(1.5f / f6));
            GL11.glTranslatef((float)0.0f, (float)(16.0f * par7), (float)0.0f);
            this.bipedHead.func_78785_a(par7);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef((float)(1.0f / f6), (float)(1.0f / f6), (float)(1.0f / f6));
            GL11.glTranslatef((float)0.0f, (float)(24.0f * par7), (float)0.0f);
            this.bipedBody.func_78785_a(par7);
            this.bipedRightArm.func_78785_a(par7);
            this.bipedLeftArm.func_78785_a(par7);
            this.bipedRightLeg.func_78785_a(par7);
            this.bipedLeftLeg.func_78785_a(par7);
            GL11.glPopMatrix();
        } else {
            this.bipedHead.func_78785_a(par7);
            this.bipedBody.func_78785_a(par7);
            this.bipedRightArm.func_78785_a(par7);
            this.bipedLeftArm.func_78785_a(par7);
            this.bipedRightLeg.func_78785_a(par7);
            this.bipedLeftLeg.func_78785_a(par7);
        }
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        boolean isWorshipping;
        float f7;
        this.bipedHead.field_78796_g = par4 / 57.295776f;
        this.bipedHead.field_78795_f = par5 / 57.295776f;
        this.bipedRightArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.bipedLeftArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.bipedRightArm.field_78808_h = 0.0f;
        this.bipedLeftArm.field_78808_h = 0.0f;
        this.bipedRightLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.bipedLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.bipedRightLeg.field_78796_g = 0.0f;
        this.bipedLeftLeg.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.bipedRightArm.field_78795_f += -0.62831855f;
            this.bipedLeftArm.field_78795_f += -0.62831855f;
            this.bipedRightLeg.field_78795_f = -1.2566371f;
            this.bipedLeftLeg.field_78795_f = -1.2566371f;
            this.bipedRightLeg.field_78796_g = 0.31415927f;
            this.bipedLeftLeg.field_78796_g = -0.31415927f;
        }
        if (this.heldItemLeft != 0) {
            this.bipedLeftArm.field_78795_f = this.bipedLeftArm.field_78795_f * 0.5f - 0.31415927f * (float)this.heldItemLeft;
        }
        if (this.heldItemRight != 0) {
            EntityGoblin goblin;
            this.bipedRightArm.field_78795_f = this.bipedRightArm.field_78795_f * 0.5f - 0.31415927f * (float)this.heldItemRight;
            if (par7Entity != null && par7Entity instanceof EntityGoblin && (goblin = (EntityGoblin)par7Entity).isWorking()) {
                this.bipedRightArm.field_78795_f = goblin.func_70694_bm() != null && goblin.func_70694_bm().func_77973_b() == Witchery.Items.KOBOLDITE_PICKAXE ? (float)((double)this.bipedRightArm.field_78795_f - (double)(par7Entity.field_70173_aa % 6) * 0.3) : (float)((double)this.bipedRightArm.field_78795_f - (double)(par7Entity.field_70173_aa % 20) * 0.1);
            }
        }
        this.bipedRightArm.field_78796_g = 0.0f;
        this.bipedLeftArm.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            float f6 = this.field_78095_p;
            this.bipedBody.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f6) * (float)Math.PI * 2.0f)) * 0.2f;
            this.bipedRightArm.field_78798_e = MathHelper.func_76126_a((float)this.bipedBody.field_78796_g) * 5.0f;
            this.bipedRightArm.field_78800_c = -MathHelper.func_76134_b((float)this.bipedBody.field_78796_g) * 5.0f;
            this.bipedLeftArm.field_78798_e = -MathHelper.func_76126_a((float)this.bipedBody.field_78796_g) * 5.0f;
            this.bipedLeftArm.field_78800_c = MathHelper.func_76134_b((float)this.bipedBody.field_78796_g) * 5.0f;
            this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g;
            this.bipedLeftArm.field_78796_g += this.bipedBody.field_78796_g;
            this.bipedLeftArm.field_78795_f += this.bipedBody.field_78796_g;
            f6 = 1.0f - this.field_78095_p;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            f7 = MathHelper.func_76126_a((float)(f6 * (float)Math.PI));
            float f8 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.bipedHead.field_78795_f - 0.7f) * 0.75f;
            this.bipedRightArm.field_78795_f = (float)((double)this.bipedRightArm.field_78795_f - ((double)f7 * 1.2 + (double)f8));
            this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g * 2.0f;
            this.bipedRightArm.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        boolean bl = isWorshipping = par7Entity != null && par7Entity instanceof EntityGoblin && ((EntityGoblin)par7Entity).isWorshipping();
        if (this.isSneak || isWorshipping) {
            this.bipedBody.field_78795_f = 0.5f;
            this.bipedRightArm.field_78795_f -= 2.2f;
            this.bipedLeftArm.field_78795_f -= 2.2f;
            this.bipedRightLeg.field_78798_e = 3.0f;
            this.bipedLeftLeg.field_78798_e = 3.0f;
            this.bipedHead.field_78795_f = 0.5f;
            this.bipedRightLeg.field_78797_d = 18.0f;
            this.bipedLeftLeg.field_78797_d = 18.0f;
            this.bipedHead.field_78797_d = 13.0f;
            this.bipedBody.field_78797_d = 13.0f;
        } else {
            this.bipedBody.field_78795_f = 0.0f;
            this.bipedRightLeg.field_78798_e = 0.1f;
            this.bipedLeftLeg.field_78798_e = 0.1f;
            this.bipedRightLeg.field_78797_d = 18.0f;
            this.bipedLeftLeg.field_78797_d = 18.0f;
            this.bipedHead.field_78797_d = 11.0f;
            this.bipedBody.field_78797_d = 11.0f;
        }
        this.bipedRightArm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedRightArm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        if (this.aimedBow) {
            float f6 = 0.0f;
            f7 = 0.0f;
            this.bipedRightArm.field_78808_h = 0.0f;
            this.bipedLeftArm.field_78808_h = 0.0f;
            this.bipedRightArm.field_78796_g = -(0.1f - f6 * 0.6f) + this.bipedHead.field_78796_g;
            this.bipedLeftArm.field_78796_g = 0.1f - f6 * 0.6f + this.bipedHead.field_78796_g + 0.4f;
            this.bipedRightArm.field_78795_f = -1.5707964f + this.bipedHead.field_78795_f;
            this.bipedLeftArm.field_78795_f = -1.5707964f + this.bipedHead.field_78795_f;
            this.bipedRightArm.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.bipedLeftArm.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.bipedRightArm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.bipedRightArm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
    }
}

