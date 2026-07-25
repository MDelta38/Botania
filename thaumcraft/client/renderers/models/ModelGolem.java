/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class ModelGolem
extends ModelBase {
    public ModelRenderer golemHead;
    public ModelRenderer golemBody;
    public ModelRenderer golemRightArm;
    public ModelRenderer golemLeftArm;
    public ModelRenderer golemRightLeg;
    public ModelRenderer golemLeftLeg;
    public int pass = 0;

    public ModelGolem(boolean p) {
        float f1 = 0.0f;
        float f2 = p ? -5.0f : 30.0f;
        int var3 = 128;
        int var4 = 128;
        this.golemHead = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemHead.func_78793_a(0.0f, 0.0f + f2, -2.0f);
        this.golemHead.func_78784_a(0, 0).func_78790_a(-4.0f, -11.0f, -5.5f, 8, 9, 8, f1);
        this.golemBody = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemBody.func_78793_a(0.0f, 0.0f + f2, 0.0f);
        this.golemBody.func_78784_a(0, 40).func_78790_a(-8.0f, -2.0f, -6.0f, 16, 12, 11, f1);
        this.golemBody.func_78784_a(0, 70).func_78790_a(-4.5f, 10.0f, -3.0f, 9, 5, 6, f1 + 0.5f);
        this.golemRightArm = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemRightArm.func_78793_a(0.0f, 0.0f + f2, 0.0f);
        this.golemRightArm.func_78784_a(60, 21).func_78790_a(-12.0f, -2.5f, -3.0f, 4, 25, 6, f1);
        this.golemLeftArm = new ModelRenderer((ModelBase)this).func_78787_b(var3, var4);
        this.golemLeftArm.field_78809_i = true;
        this.golemLeftArm.func_78793_a(0.0f, 0.0f + f2, 0.0f);
        this.golemLeftArm.func_78784_a(60, 21).func_78790_a(8.0f, -2.5f, -3.0f, 4, 25, 6, f1);
        this.golemRightLeg = new ModelRenderer((ModelBase)this, 0, 22).func_78787_b(var3, var4);
        this.golemRightLeg.func_78793_a(-4.0f, 18.0f + f2, 0.0f);
        this.golemRightLeg.func_78784_a(37, 0).func_78790_a(-3.5f, -3.0f, -3.0f, 6, 16, 5, f1);
        this.golemLeftLeg = new ModelRenderer((ModelBase)this, 0, 22).func_78787_b(var3, var4);
        this.golemLeftLeg.field_78809_i = true;
        this.golemLeftLeg.func_78784_a(37, 0).func_78793_a(5.0f, 18.0f + f2, 0.0f);
        this.golemLeftLeg.func_78790_a(-3.5f, -3.0f, -3.0f, 6, 16, 5, f1);
    }

    public void func_78088_a(Entity e, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.setRotationAngles(e, par2, par3, par4, par5, par6, par7);
        GL11.glPushMatrix();
        if (this.pass == 2) {
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)771);
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
        }
        GL11.glScaled((double)0.4, (double)0.4, (double)0.4);
        this.golemHead.func_78785_a(par7);
        this.golemBody.func_78785_a(par7);
        this.golemRightLeg.func_78785_a(par7);
        this.golemLeftLeg.func_78785_a(par7);
        this.golemRightArm.func_78785_a(par7);
        this.golemLeftArm.func_78785_a(par7);
        GL11.glScaled((double)1.0, (double)1.0, (double)1.0);
        if (this.pass == 2) {
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glDisable((int)3042);
        }
        GL11.glPopMatrix();
    }

    public void setRotationAngles(Entity en, float par1, float par2, float par3, float par4, float par5, float par6) {
        float bu = 0.0f;
        int core = 0;
        boolean inactive = false;
        if (en instanceof EntityGolemBase) {
            core = ((EntityGolemBase)en).getCore();
            bu = ((EntityGolemBase)en).bootup;
            inactive = ((EntityGolemBase)en).inactive;
            if (this.pass == 0 && ((EntityGolemBase)en).healing > 0) {
                float h1 = (float)((EntityGolemBase)en).healing / 10.0f;
                float h2 = (float)((EntityGolemBase)en).healing / 5.0f;
                GL11.glColor3f((float)(0.5f + h1), (float)(0.9f + h2), (float)(0.5f + h1));
            }
        }
        if (core == -1 || bu < 0.0f) {
            this.golemHead.field_78796_g = 0.0f;
            this.golemHead.field_78795_f = 0.57595867f;
            this.golemRightLeg.field_78795_f = 0.0f;
            this.golemLeftLeg.field_78795_f = 0.0f;
            this.golemRightArm.field_78795_f = 0.0f;
            this.golemLeftArm.field_78795_f = 0.0f;
            this.golemRightLeg.field_78796_g = 0.0f;
            this.golemLeftLeg.field_78796_g = 0.0f;
            this.golemLeftArm.field_78808_h = 0.0f;
            this.golemRightArm.field_78808_h = 0.0f;
        } else {
            if (inactive) {
                this.golemHead.field_78796_g = 0.0f;
                this.golemHead.field_78795_f = 0.57595867f;
            } else if (bu > 0.0f) {
                this.golemHead.field_78796_g = 0.0f;
                this.golemHead.field_78795_f = bu / 57.295776f;
            } else {
                this.golemHead.field_78796_g = par4 / 57.295776f;
                this.golemHead.field_78795_f = par5 / 57.295776f;
            }
            this.golemRightLeg.field_78795_f = -1.5f * this.func_78172_a(par1, 13.0f) * par2;
            this.golemLeftLeg.field_78795_f = 1.5f * this.func_78172_a(par1, 13.0f) * par2;
            this.golemRightLeg.field_78796_g = 0.0f;
            this.golemLeftLeg.field_78796_g = 0.0f;
            this.golemLeftArm.field_78808_h = 0.0f;
            this.golemRightArm.field_78808_h = 0.0f;
            if (core == 6) {
                float s = (1.0f - (0.5f + (float)Math.min(64, ((EntityGolemBase)en).getCarryLimit()) / 128.0f)) * 25.0f;
                this.golemLeftArm.field_78808_h = s / 57.295776f;
                this.golemRightArm.field_78808_h = -s / 57.295776f;
            }
        }
    }

    public void func_78086_a(EntityLivingBase par1EntityLiving, float par2, float par3, float par4) {
        EntityGolemBase var5 = null;
        int var6 = 0;
        ItemStack carried = null;
        boolean bucket = false;
        int leftarm = 0;
        int rightarm = 0;
        if (par1EntityLiving instanceof EntityGolemBase) {
            var5 = (EntityGolemBase)par1EntityLiving;
            var6 = var5.getActionTimer();
            carried = var5.getCarriedForDisplay();
            bucket = var5.getCore() == 5;
            leftarm = var5.leftArm;
            rightarm = var5.rightArm;
        }
        if (var6 > 0) {
            this.golemRightArm.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)var6 - par4, 5.0f);
            this.golemLeftArm.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)var6 - par4, 5.0f);
        } else if (leftarm > 0 || rightarm > 0) {
            if (leftarm > 0) {
                this.golemLeftArm.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)leftarm - par4, 20.0f);
            }
            if (rightarm > 0) {
                this.golemRightArm.field_78795_f = -2.0f + 1.5f * this.func_78172_a((float)rightarm - par4, 20.0f);
            }
        } else if (carried != null || bucket) {
            this.golemRightArm.field_78795_f = -1.0f;
            this.golemLeftArm.field_78795_f = -1.0f;
        } else {
            this.golemRightArm.field_78795_f = (-0.2f + 1.5f * this.func_78172_a(par2, 13.0f)) * par3;
            this.golemLeftArm.field_78795_f = (-0.2f - 1.5f * this.func_78172_a(par2, 13.0f)) * par3;
        }
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

