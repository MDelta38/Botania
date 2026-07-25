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

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ModelDeath
extends ModelBase {
    ModelRenderer bipedHead;
    ModelRenderer bipedBody;
    ModelRenderer bipedRightArm;
    ModelRenderer bipedLeftArm;
    ModelRenderer bipedRightLeg;
    ModelRenderer bipedLeftLeg;
    ModelRenderer robe;
    ModelRenderer scythe;

    public ModelDeath() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.func_78085_a("scythe.shaft", 58, 5);
        this.func_78085_a("scythe.blade", 36, 0);
        this.bipedHead = new ModelRenderer((ModelBase)this, 27, 43);
        this.bipedHead.func_78789_a(-4.0f, -8.0f, -4.0f, 8, 10, 8);
        this.bipedHead.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedHead.func_78787_b(64, 64);
        this.bipedHead.field_78809_i = true;
        this.setRotation(this.bipedHead, 0.0f, 0.0f, 0.0f);
        this.bipedBody = new ModelRenderer((ModelBase)this, 16, 16);
        this.bipedBody.func_78789_a(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.bipedBody.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bipedBody.func_78787_b(64, 64);
        this.bipedBody.field_78809_i = true;
        this.setRotation(this.bipedBody, 0.0f, 0.0f, 0.0f);
        this.bipedRightArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedRightArm.func_78789_a(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.bipedRightArm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.bipedRightArm.func_78787_b(64, 64);
        this.bipedRightArm.field_78809_i = true;
        this.setRotation(this.bipedRightArm, 0.0f, 0.0f, 0.0f);
        this.bipedLeftArm = new ModelRenderer((ModelBase)this, 40, 16);
        this.bipedLeftArm.func_78789_a(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.bipedLeftArm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.bipedLeftArm.func_78787_b(64, 64);
        this.bipedLeftArm.field_78809_i = true;
        this.setRotation(this.bipedLeftArm, 0.0f, 0.0f, 0.0f);
        this.bipedRightLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedRightLeg.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 12, 2);
        this.bipedRightLeg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.bipedRightLeg.func_78787_b(64, 64);
        this.bipedRightLeg.field_78809_i = true;
        this.setRotation(this.bipedRightLeg, 0.0f, 0.0f, 0.0f);
        this.bipedLeftLeg = new ModelRenderer((ModelBase)this, 0, 16);
        this.bipedLeftLeg.func_78789_a(-1.0f, 0.0f, -1.0f, 2, 12, 2);
        this.bipedLeftLeg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.bipedLeftLeg.func_78787_b(64, 64);
        this.bipedLeftLeg.field_78809_i = true;
        this.setRotation(this.bipedLeftLeg, 0.0f, 0.0f, 0.0f);
        this.robe = new ModelRenderer((ModelBase)this, 0, 33);
        this.robe.func_78789_a(-4.0f, 0.0f, -2.5f, 8, 23, 5);
        this.robe.func_78793_a(0.0f, 0.0f, 0.0f);
        this.robe.func_78787_b(64, 64);
        this.robe.field_78809_i = true;
        this.setRotation(this.robe, 0.0f, 0.0f, 0.0f);
        this.scythe = new ModelRenderer((ModelBase)this, "scythe");
        this.scythe.func_78793_a(-6.0f, 10.0f, 0.0f);
        this.setRotation(this.scythe, 0.0f, 0.0f, 0.0f);
        this.scythe.field_78809_i = true;
        this.scythe.func_78786_a("shaft", -0.5f, -16.0f, -0.5f, 1, 35, 1);
        this.scythe.func_78786_a("blade", 0.0f, -16.0f, 0.0f, 13, 4, 0);
        this.bipedRightArm.func_78792_a(this.scythe);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.bipedHead.func_78785_a(f5);
        this.bipedBody.func_78785_a(f5);
        this.bipedRightArm.func_78785_a(f5);
        this.bipedLeftArm.func_78785_a(f5);
        this.bipedRightLeg.func_78785_a(f5);
        this.bipedLeftLeg.func_78785_a(f5);
        GL11.glScalef((float)1.05f, (float)1.0f, (float)1.05f);
        this.robe.func_78785_a(f5);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {
        this.scythe.field_78800_c = -0.8f;
        this.scythe.field_78798_e = 0.0f;
        this.scythe.field_78797_d = 8.1f;
        this.scythe.field_78795_f = 1.5707964f;
        this.bipedHead.field_78796_g = par4 / 57.295776f;
        this.bipedHead.field_78795_f = par5 / 57.295776f;
        this.bipedRightArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f - 1.5707964f;
        this.bipedLeftArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.bipedRightArm.field_78808_h = 0.0f;
        this.bipedLeftArm.field_78808_h = 0.0f;
        this.bipedRightLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.bipedLeftLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.bipedRightLeg.field_78796_g = 0.0f;
        this.bipedLeftLeg.field_78796_g = 0.0f;
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
            float f7 = MathHelper.func_76126_a((float)(f6 * (float)Math.PI));
            float f8 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.bipedHead.field_78795_f - 0.7f) * 0.75f;
            this.bipedRightArm.field_78795_f = (float)((double)this.bipedRightArm.field_78795_f - ((double)f7 * 1.2 + (double)f8));
            this.bipedRightArm.field_78796_g += this.bipedBody.field_78796_g * 2.0f;
            this.bipedRightArm.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        this.bipedBody.field_78795_f = 0.0f;
        this.bipedRightLeg.field_78798_e = 0.1f;
        this.bipedLeftLeg.field_78798_e = 0.1f;
        this.bipedRightLeg.field_78797_d = 12.0f;
        this.bipedLeftLeg.field_78797_d = 12.0f;
        this.bipedHead.field_78797_d = 0.0f;
        this.bipedRightArm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedLeftArm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.bipedRightArm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.bipedLeftArm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
    }
}

