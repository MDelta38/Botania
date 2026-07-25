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
 */
package com.emoniph.witchery.client.model;

import com.emoniph.witchery.entity.EntityLordOfTorment;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelLordOfTorment
extends ModelBase {
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm1;
    ModelRenderer rightarm2;
    ModelRenderer leftarm1;
    ModelRenderer leftarm2;
    ModelRenderer rightleg;
    ModelRenderer leftleg;
    ModelRenderer wingsLeft;
    ModelRenderer wingsRight;
    ModelRenderer hornLeft;
    ModelRenderer hornRight;

    public ModelLordOfTorment() {
        this.field_78090_t = 128;
        this.field_78089_u = 128;
        this.func_78085_a("head.skull", 0, 0);
        this.func_78085_a("head.beard1", 34, 0);
        this.func_78085_a("head.beard2", 34, 0);
        this.func_78085_a("head.beard3", 34, 0);
        this.func_78085_a("head.beard4", 34, 0);
        this.func_78085_a("head.beard5", 34, 0);
        this.func_78085_a("head.beard6", 34, 0);
        this.func_78085_a("head.nose", 40, 0);
        this.func_78085_a("head.nose2", 40, 6);
        this.head = new ModelRenderer((ModelBase)this, "head");
        this.head.func_78793_a(0.0f, -6.0f, 0.0f);
        this.setRotation(this.head, 0.0f, 0.0f, 0.0f);
        this.head.field_78809_i = true;
        this.head.func_78786_a("skull", -4.0f, -8.0f, -4.0f, 8, 8, 8);
        this.head.func_78786_a("beard1", -3.0f, 0.0f, -4.0f, 1, 7, 1);
        this.head.func_78786_a("beard2", -2.0f, 0.0f, -5.0f, 1, 5, 1);
        this.head.func_78786_a("beard3", -1.0f, 0.0f, -4.0f, 1, 9, 1);
        this.head.func_78786_a("beard4", 0.0f, 0.0f, -5.0f, 1, 6, 1);
        this.head.func_78786_a("beard5", 1.0f, 0.0f, -4.0f, 1, 4, 1);
        this.head.func_78786_a("beard6", 2.0f, 0.0f, -5.0f, 1, 8, 1);
        this.head.func_78786_a("nose", -3.0f, -4.0f, -5.0f, 6, 4, 1);
        this.head.func_78786_a("nose2", -2.0f, -6.0f, -5.0f, 4, 2, 1);
        this.hornRight = new ModelRenderer((ModelBase)this, 55, 0);
        this.hornRight.func_78789_a(-2.0f, -15.0f, 0.0f, 1, 9, 1);
        this.hornRight.func_78793_a(0.0f, -6.0f, 0.0f);
        this.hornRight.func_78787_b(128, 128);
        this.hornRight.field_78809_i = true;
        this.setRotation(this.hornRight, 0.5948578f, 0.0f, -0.1858931f);
        this.head.func_78792_a(this.hornRight);
        this.hornLeft = new ModelRenderer((ModelBase)this, 55, 0);
        this.hornLeft.func_78789_a(1.0f, -15.0f, 0.0f, 1, 9, 1);
        this.hornLeft.func_78793_a(0.0f, -6.0f, 0.0f);
        this.hornLeft.func_78787_b(128, 128);
        this.hornLeft.field_78809_i = true;
        this.setRotation(this.hornLeft, 0.5948578f, 0.0f, 0.1858931f);
        this.head.func_78792_a(this.hornLeft);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78789_a(-4.0f, -6.0f, -2.0f, 8, 14, 4);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78787_b(128, 128);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.rightarm1 = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm1.func_78789_a(-3.0f, -2.0f, -2.0f, 4, 20, 4);
        this.rightarm1.func_78793_a(-5.0f, -4.0f, 0.0f);
        this.rightarm1.func_78787_b(128, 128);
        this.rightarm1.field_78809_i = true;
        this.setRotation(this.rightarm1, 0.0f, 0.0f, 0.0f);
        this.rightarm2 = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm2.func_78789_a(-3.0f, -2.0f, -2.0f, 4, 20, 4);
        this.rightarm2.func_78793_a(-5.0f, -4.0f, 0.0f);
        this.rightarm2.func_78787_b(128, 128);
        this.rightarm2.field_78809_i = true;
        this.setRotation(this.rightarm2, 0.0f, 0.0f, 0.0f);
        this.leftarm1 = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm1.func_78789_a(-1.0f, -2.0f, -2.0f, 4, 20, 4);
        this.leftarm1.func_78793_a(5.0f, -4.0f, 0.0f);
        this.leftarm1.func_78787_b(128, 128);
        this.leftarm1.field_78809_i = true;
        this.setRotation(this.leftarm1, 0.0f, 0.0f, 0.0f);
        this.leftarm2 = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm2.func_78789_a(-1.0f, -2.0f, -2.0f, 4, 20, 4);
        this.leftarm2.func_78793_a(5.0f, -4.0f, 0.0f);
        this.leftarm2.func_78787_b(128, 128);
        this.leftarm2.field_78809_i = true;
        this.setRotation(this.leftarm2, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 15, 4);
        this.rightleg.func_78793_a(-2.0f, 8.0f, 0.0f);
        this.rightleg.func_78787_b(128, 128);
        this.rightleg.field_78809_i = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 15, 4);
        this.leftleg.func_78793_a(2.0f, 8.0f, 0.0f);
        this.leftleg.func_78787_b(128, 128);
        this.leftleg.field_78809_i = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.wingsLeft = new ModelRenderer((ModelBase)this, 0, 42);
        this.wingsLeft.func_78789_a(-20.0f, -20.0f, 0.0f, 20, 40, 0);
        this.wingsLeft.func_78793_a(0.0f, 1.0f, 5.0f);
        this.wingsLeft.func_78787_b(128, 128);
        this.wingsLeft.field_78809_i = true;
        this.setRotation(this.wingsLeft, 0.0f, 0.0f, 0.0f);
        this.wingsRight = new ModelRenderer((ModelBase)this, 0, 82);
        this.wingsRight.func_78789_a(0.0f, -20.0f, 0.0f, 20, 40, 0);
        this.wingsRight.func_78793_a(0.0f, 1.0f, 5.0f);
        this.wingsRight.func_78787_b(128, 128);
        this.wingsRight.field_78809_i = true;
        this.setRotation(this.wingsRight, 0.0f, 0.0f, 0.0f);
        this.hornRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hornLeft.func_78793_a(0.0f, 0.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.head.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm1.func_78785_a(f5);
        this.rightarm2.func_78785_a(f5);
        this.leftarm1.func_78785_a(f5);
        this.leftarm2.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
        this.wingsLeft.func_78785_a(f5);
        this.wingsRight.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        boolean inMotion;
        this.head.field_78796_g = par4 / 57.295776f;
        this.head.field_78795_f = par5 / 57.295776f;
        this.rightarm1.field_78795_f = MathHelper.func_76134_b((float)3.8077927f) * 2.0f * par2 * 0.5f;
        this.rightarm2.field_78795_f = MathHelper.func_76134_b((float)3.8077927f) * 2.0f * par2 * 0.25f;
        this.leftarm1.field_78795_f = MathHelper.func_76134_b((float)0.6662f) * 2.0f * par2 * 0.5f;
        this.leftarm2.field_78795_f = MathHelper.func_76134_b((float)0.6662f) * 2.0f * par2 * 0.25f;
        boolean bl = inMotion = entity.field_70159_w > 0.0 || entity.field_70179_y > 0.0;
        if (inMotion) {
            this.wingsLeft.field_78796_g = 0.4f;
            this.wingsRight.field_78796_g = -0.4f;
        } else {
            this.wingsLeft.field_78796_g = MathHelper.func_76134_b((float)3.8077927f) * 2.0f * par2 * 0.5f + MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.3f;
            this.wingsRight.field_78796_g = MathHelper.func_76134_b((float)3.8077927f) * 2.0f * par2 * 0.5f - MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.3f;
        }
        this.rightarm1.field_78796_g = 0.0f;
        this.rightarm2.field_78796_g = 0.0f;
        this.leftarm1.field_78796_g = 0.0f;
        this.leftarm2.field_78796_g = 0.0f;
        this.rightarm1.field_78808_h = 0.0f;
        this.rightarm2.field_78808_h = 0.0f;
        this.leftarm1.field_78808_h = 0.0f;
        this.leftarm2.field_78808_h = 0.0f;
        this.rightarm1.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarm2.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm1.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm2.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarm1.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.rightarm2.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.leftarm1.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.leftarm2.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        EntityLordOfTorment entityDemon = (EntityLordOfTorment)entity;
        int i = entityDemon.getAttackTimer();
        if (i > 0) {
            this.rightarm1.field_78795_f = -1.5f + 0.8f * this.func_78172_a((float)i - par4, 15.0f);
            this.leftarm1.field_78795_f = -1.5f + 0.8f * this.func_78172_a((float)i - par4, 15.0f);
            this.rightarm1.field_78808_h = -(-1.5f + 1.5f * this.func_78172_a((float)i - par4, 15.0f));
            this.leftarm1.field_78808_h = -1.5f + 1.5f * this.func_78172_a((float)i - par4, 15.0f);
            this.rightarm2.field_78808_h = -(-1.0f + 1.5f * this.func_78172_a((float)i - par4, 15.0f));
            this.leftarm2.field_78808_h = -1.0f + 1.5f * this.func_78172_a((float)i - par4, 15.0f);
        }
    }

    private float func_78172_a(float par1, float par2) {
        return (Math.abs(par1 % par2 - par2 * 0.5f) - par2 * 0.25f) / (par2 * 0.25f);
    }
}

