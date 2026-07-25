/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 */
package thaumcraft.client.renderers.models.entities;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import thaumcraft.common.entities.monster.EntityPech;

public class ModelPech
extends ModelBase {
    ModelRenderer Body;
    ModelRenderer RightLeg;
    ModelRenderer LeftLeg;
    ModelRenderer Head;
    ModelRenderer Jowls;
    ModelRenderer LowerPack;
    ModelRenderer UpperPack;
    public ModelRenderer RightArm;
    ModelRenderer LeftArm;

    public ModelPech() {
        this.field_78090_t = 128;
        this.field_78089_u = 64;
        this.Body = new ModelRenderer((ModelBase)this, 34, 12);
        this.Body.func_78789_a(-3.0f, 0.0f, 0.0f, 6, 10, 6);
        this.Body.func_78793_a(0.0f, 9.0f, -3.0f);
        this.Body.func_78787_b(128, 64);
        this.Body.field_78809_i = true;
        this.setRotation(this.Body, 0.3129957f, 0.0f, 0.0f);
        this.RightLeg = new ModelRenderer((ModelBase)this, 35, 1);
        this.RightLeg.field_78809_i = true;
        this.RightLeg.func_78789_a(-2.9f, 0.0f, 0.0f, 3, 6, 3);
        this.RightLeg.func_78793_a(0.0f, 18.0f, 0.0f);
        this.RightLeg.func_78787_b(128, 64);
        this.RightLeg.field_78809_i = true;
        this.setRotation(this.RightLeg, 0.0f, 0.0f, 0.0f);
        this.RightLeg.field_78809_i = false;
        this.LeftLeg = new ModelRenderer((ModelBase)this, 35, 1);
        this.LeftLeg.func_78789_a(-0.1f, 0.0f, 0.0f, 3, 6, 3);
        this.LeftLeg.func_78793_a(0.0f, 18.0f, 0.0f);
        this.LeftLeg.func_78787_b(128, 64);
        this.LeftLeg.field_78809_i = true;
        this.setRotation(this.LeftLeg, 0.0f, 0.0f, 0.0f);
        this.Head = new ModelRenderer((ModelBase)this, 2, 11);
        this.Head.func_78789_a(-3.5f, -5.0f, -5.0f, 7, 5, 5);
        this.Head.func_78793_a(0.0f, 8.0f, 0.0f);
        this.Head.func_78787_b(128, 64);
        this.Head.field_78809_i = true;
        this.setRotation(this.Head, 0.0f, 0.0f, 0.0f);
        this.Jowls = new ModelRenderer((ModelBase)this, 1, 21);
        this.Jowls.func_78789_a(-4.0f, -1.0f, -6.0f, 8, 3, 5);
        this.Jowls.func_78793_a(0.0f, 8.0f, 0.0f);
        this.Jowls.func_78787_b(128, 64);
        this.Jowls.field_78809_i = true;
        this.setRotation(this.Jowls, 0.0f, 0.0f, 0.0f);
        this.LowerPack = new ModelRenderer((ModelBase)this, 0, 0);
        this.LowerPack.func_78789_a(-5.0f, 0.0f, 0.0f, 10, 5, 5);
        this.LowerPack.func_78793_a(0.0f, 10.0f, 3.5f);
        this.LowerPack.func_78787_b(128, 64);
        this.LowerPack.field_78809_i = true;
        this.setRotation(this.LowerPack, 0.3013602f, 0.0f, 0.0f);
        this.UpperPack = new ModelRenderer((ModelBase)this, 64, 1);
        this.UpperPack.func_78789_a(-7.5f, -14.0f, 0.0f, 15, 14, 11);
        this.UpperPack.func_78793_a(0.0f, 10.0f, 3.0f);
        this.UpperPack.func_78787_b(128, 64);
        this.UpperPack.field_78809_i = true;
        this.setRotation(this.UpperPack, 0.4537856f, 0.0f, 0.0f);
        this.RightArm = new ModelRenderer((ModelBase)this, 52, 2);
        this.RightArm.field_78809_i = true;
        this.RightArm.func_78789_a(-2.0f, 0.0f, -1.0f, 2, 6, 2);
        this.RightArm.func_78793_a(-3.0f, 10.0f, -1.0f);
        this.RightArm.func_78787_b(128, 64);
        this.RightArm.field_78809_i = true;
        this.setRotation(this.RightArm, 0.0f, 0.0f, 0.0f);
        this.RightArm.field_78809_i = false;
        this.LeftArm = new ModelRenderer((ModelBase)this, 52, 2);
        this.LeftArm.func_78789_a(0.0f, 0.0f, -1.0f, 2, 6, 2);
        this.LeftArm.func_78793_a(3.0f, 10.0f, -1.0f);
        this.LeftArm.func_78787_b(128, 64);
        this.LeftArm.field_78809_i = true;
        this.setRotation(this.LeftArm, 0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        this.func_78087_a(par2, par3, par4, par5, par6, par7, par1Entity);
        this.Body.func_78785_a(par7);
        this.RightLeg.func_78785_a(par7);
        this.LeftLeg.func_78785_a(par7);
        this.Head.func_78785_a(par7);
        this.Jowls.func_78785_a(par7);
        this.LowerPack.func_78785_a(par7);
        this.UpperPack.func_78785_a(par7);
        this.RightArm.func_78785_a(par7);
        this.LeftArm.func_78785_a(par7);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.Head.field_78796_g = par4 / 57.295776f;
        this.Head.field_78795_f = par5 / 57.295776f;
        float mumble = 0.0f;
        if (entity instanceof EntityPech) {
            mumble = ((EntityPech)entity).mumble;
        }
        this.Jowls.field_78796_g = this.Head.field_78796_g;
        this.Jowls.field_78795_f = this.Head.field_78795_f + (0.2617994f + MathHelper.func_76134_b((float)(par1 * 0.6662f)) * par2 * 0.25f) + 0.34906587f * Math.abs(MathHelper.func_76126_a((float)mumble));
        this.RightArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.LeftArm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.RightArm.field_78808_h = 0.0f;
        this.LeftArm.field_78808_h = 0.0f;
        this.RightLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.LeftLeg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.RightLeg.field_78796_g = 0.0f;
        this.LeftLeg.field_78796_g = 0.0f;
        this.LowerPack.field_78796_g = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.125f;
        this.LowerPack.field_78808_h = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.125f;
        if (this.field_78093_q) {
            this.RightArm.field_78795_f += -0.62831855f;
            this.LeftArm.field_78795_f += -0.62831855f;
            this.RightLeg.field_78795_f = -1.2566371f;
            this.LeftLeg.field_78795_f = -1.2566371f;
            this.RightLeg.field_78796_g = 0.31415927f;
            this.LeftLeg.field_78796_g = -0.31415927f;
        }
        this.RightArm.field_78796_g = 0.0f;
        this.LeftArm.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            float f6 = this.field_78095_p;
            this.RightArm.field_78796_g += this.Body.field_78796_g;
            this.LeftArm.field_78796_g += this.Body.field_78796_g;
            this.LeftArm.field_78795_f += this.Body.field_78796_g;
            f6 = 1.0f - this.field_78095_p;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            float f7 = MathHelper.func_76126_a((float)(f6 * (float)Math.PI));
            float f8 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.Head.field_78795_f - 0.7f) * 0.75f;
            this.RightArm.field_78795_f = (float)((double)this.RightArm.field_78795_f - ((double)f7 * 1.2 + (double)f8));
            this.RightArm.field_78796_g += this.Body.field_78796_g * 2.0f;
            this.RightArm.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        if (entity.func_70093_af()) {
            this.RightArm.field_78795_f += 0.4f;
            this.LeftArm.field_78795_f += 0.4f;
        }
        this.RightArm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.LeftArm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.RightArm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.LeftArm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
    }
}

