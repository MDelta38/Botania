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

import com.emoniph.witchery.entity.EntityLeonard;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelLeonard
extends ModelBase {
    private final ModelRenderer head;
    private final ModelRenderer snout;
    private final ModelRenderer beard;
    private final ModelRenderer earLeft;
    private final ModelRenderer earRight;
    private final ModelRenderer hornLeft;
    private final ModelRenderer hornMiddle;
    private final ModelRenderer hornRight;
    private final ModelRenderer neck;
    private final ModelRenderer body;
    private final ModelRenderer gownLowerRight;
    private final ModelRenderer rightarm;
    private final ModelRenderer leftarm;
    private final ModelRenderer rightleg;
    private final ModelRenderer leftleg;
    private final ModelRenderer gownLowerLeft;

    public ModelLeonard() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.neck = new ModelRenderer((ModelBase)this, 48, 0);
        this.neck.func_78789_a(-2.0f, -1.0f, -2.0f, 4, 2, 4);
        this.neck.func_78793_a(0.0f, 0.0f, 0.0f);
        this.neck.func_78787_b(64, 64);
        this.neck.field_78809_i = true;
        this.setRotation(this.neck, 0.1745329f, 0.0f, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 0, 0);
        this.head.func_78789_a(-3.0f, -5.0f, -1.0f, 6, 4, 4);
        this.head.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head.func_78787_b(64, 64);
        this.head.field_78809_i = true;
        this.neck.func_78792_a(this.head);
        this.setRotation(this.head, 0.1745329f, 0.0f, 0.0f);
        this.snout = new ModelRenderer((ModelBase)this, 16, 2);
        this.snout.func_78789_a(-2.0f, -5.0f, -7.0f, 4, 4, 7);
        this.snout.func_78793_a(0.0f, 0.0f, 0.0f);
        this.snout.func_78787_b(64, 64);
        this.snout.field_78809_i = true;
        this.setRotation(this.snout, 0.1745329f, 0.0f, 0.0f);
        this.head.func_78792_a(this.snout);
        this.beard = new ModelRenderer((ModelBase)this, 0, 10);
        this.beard.func_78789_a(-2.0f, -0.2f, -7.0f, 4, 2, 2);
        this.beard.func_78793_a(0.0f, 0.0f, 0.0f);
        this.beard.func_78787_b(64, 64);
        this.beard.field_78809_i = true;
        this.setRotation(this.beard, -0.0113601f, 0.0f, 0.0f);
        this.head.func_78792_a(this.beard);
        this.earLeft = new ModelRenderer((ModelBase)this, 38, 0);
        this.earLeft.func_78789_a(3.5f, 1.0f, -0.5f, 1, 3, 1);
        this.earLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earLeft.func_78787_b(64, 64);
        this.earLeft.field_78809_i = true;
        this.setRotation(this.earLeft, -0.5129616f, -0.2617994f, -1.180008f);
        this.head.func_78792_a(this.earLeft);
        this.earRight = new ModelRenderer((ModelBase)this, 38, 0);
        this.earRight.func_78789_a(-4.5f, 1.0f, 0.5f, 1, 3, 1);
        this.earRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.earRight.func_78787_b(64, 64);
        this.earRight.field_78809_i = true;
        this.setRotation(this.earRight, -0.3346075f, 0.0371786f, 1.226894f);
        this.head.func_78792_a(this.earRight);
        this.hornLeft = new ModelRenderer((ModelBase)this, 43, 0);
        this.hornLeft.func_78789_a(-0.5f, -12.0f, -0.5f, 1, 8, 1);
        this.hornLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hornLeft.func_78787_b(64, 64);
        this.hornLeft.field_78809_i = true;
        this.setRotation(this.hornLeft, -0.2268928f, 0.0f, 0.3665191f);
        this.head.func_78792_a(this.hornLeft);
        this.hornMiddle = new ModelRenderer((ModelBase)this, 43, 0);
        this.hornMiddle.func_78789_a(-0.5f, -10.0f, -0.5f, 1, 6, 1);
        this.hornMiddle.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hornMiddle.func_78787_b(64, 64);
        this.hornMiddle.field_78809_i = true;
        this.setRotation(this.hornMiddle, -0.2974289f, 0.0f, 0.0f);
        this.head.func_78792_a(this.hornMiddle);
        this.hornRight = new ModelRenderer((ModelBase)this, 43, 0);
        this.hornRight.func_78789_a(-0.5f, -12.0f, -0.5f, 1, 8, 1);
        this.hornRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hornRight.func_78787_b(64, 64);
        this.hornRight.field_78809_i = true;
        this.setRotation(this.hornRight, -0.2268928f, 0.0f, -0.3665191f);
        this.head.func_78792_a(this.hornRight);
        this.body = new ModelRenderer((ModelBase)this, 16, 16);
        this.body.func_78789_a(-4.0f, 0.0f, -2.0f, 8, 12, 4);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78787_b(64, 64);
        this.body.field_78809_i = true;
        this.setRotation(this.body, 0.0f, 0.0f, 0.0f);
        this.gownLowerRight = new ModelRenderer((ModelBase)this, 0, 33);
        this.gownLowerRight.func_78789_a(-5.0f, 12.0f, -2.5f, 5, 11, 5);
        this.gownLowerRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.gownLowerRight.func_78787_b(64, 64);
        this.gownLowerRight.field_78809_i = true;
        this.setRotation(this.gownLowerRight, 0.0f, 0.0f, 0.0f);
        this.rightarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.rightarm.func_78789_a(-3.0f, -2.0f, -2.0f, 4, 12, 4);
        this.rightarm.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.rightarm.func_78787_b(64, 64);
        this.rightarm.field_78809_i = true;
        this.setRotation(this.rightarm, 0.0f, 0.0f, 0.0f);
        this.leftarm = new ModelRenderer((ModelBase)this, 40, 16);
        this.leftarm.func_78789_a(-1.0f, -2.0f, -2.0f, 4, 12, 4);
        this.leftarm.func_78793_a(5.0f, 2.0f, 0.0f);
        this.leftarm.func_78787_b(64, 64);
        this.leftarm.field_78809_i = true;
        this.setRotation(this.leftarm, 0.0f, 0.0f, 0.0f);
        this.rightleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.rightleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.rightleg.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.rightleg.func_78787_b(64, 64);
        this.rightleg.field_78809_i = true;
        this.setRotation(this.rightleg, 0.0f, 0.0f, 0.0f);
        this.rightleg.func_78792_a(this.gownLowerRight);
        this.leftleg = new ModelRenderer((ModelBase)this, 0, 16);
        this.leftleg.func_78789_a(-2.0f, 0.0f, -2.0f, 4, 12, 4);
        this.leftleg.func_78793_a(2.0f, 12.0f, 0.0f);
        this.leftleg.func_78787_b(64, 64);
        this.leftleg.field_78809_i = true;
        this.setRotation(this.leftleg, 0.0f, 0.0f, 0.0f);
        this.gownLowerLeft = new ModelRenderer((ModelBase)this, 21, 33);
        this.gownLowerLeft.func_78789_a(0.0f, 12.0f, -2.5f, 5, 11, 5);
        this.gownLowerLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.gownLowerLeft.func_78787_b(64, 64);
        this.gownLowerLeft.field_78809_i = true;
        this.setRotation(this.gownLowerLeft, 0.0f, 0.0f, 0.0f);
        this.leftleg.func_78792_a(this.gownLowerLeft);
        this.neck.field_78795_f = 0.1745329f;
        this.head.field_78795_f = 0.1745329f;
        this.setRotation(this.earRight, -0.3346075f, 0.0371786f, 1.226894f);
        this.gownLowerLeft.func_78793_a(-2.0f, -12.0f, 0.0f);
        this.gownLowerRight.func_78793_a(2.0f, -12.0f, 0.0f);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.neck.func_78785_a(f5);
        this.body.func_78785_a(f5);
        this.rightarm.func_78785_a(f5);
        this.leftarm.func_78785_a(f5);
        this.rightleg.func_78785_a(f5);
        this.leftleg.func_78785_a(f5);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        EntityLeonard entityDemon;
        int i;
        boolean sneaking;
        float f7;
        float f6;
        this.neck.field_78796_g = par4 / 57.295776f;
        this.neck.field_78795_f = par5 / 57.295776f;
        this.rightarm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.leftarm.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.rightarm.field_78808_h = 0.0f;
        this.leftarm.field_78808_h = 0.0f;
        this.rightleg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2;
        this.leftleg.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2;
        this.rightleg.field_78796_g = 0.0f;
        this.leftleg.field_78796_g = 0.0f;
        if (this.field_78093_q) {
            this.rightarm.field_78795_f += -0.62831855f;
            this.leftarm.field_78795_f += -0.62831855f;
            this.rightleg.field_78795_f = -1.2566371f;
            this.leftleg.field_78795_f = -1.2566371f;
            this.rightleg.field_78796_g = 0.31415927f;
            this.leftleg.field_78796_g = -0.31415927f;
        }
        this.rightarm.field_78796_g = 0.0f;
        this.leftarm.field_78796_g = 0.0f;
        if (this.field_78095_p > -9990.0f) {
            f6 = this.field_78095_p;
            this.body.field_78796_g = MathHelper.func_76126_a((float)(MathHelper.func_76129_c((float)f6) * (float)Math.PI * 2.0f)) * 0.2f;
            this.rightarm.field_78798_e = MathHelper.func_76126_a((float)this.body.field_78796_g) * 5.0f;
            this.rightarm.field_78800_c = -MathHelper.func_76134_b((float)this.body.field_78796_g) * 5.0f;
            this.leftarm.field_78798_e = -MathHelper.func_76126_a((float)this.body.field_78796_g) * 5.0f;
            this.leftarm.field_78800_c = MathHelper.func_76134_b((float)this.body.field_78796_g) * 5.0f;
            this.rightarm.field_78796_g += this.body.field_78796_g;
            this.leftarm.field_78796_g += this.body.field_78796_g;
            this.leftarm.field_78795_f += this.body.field_78796_g;
            f6 = 1.0f - this.field_78095_p;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            f7 = MathHelper.func_76126_a((float)(f6 * (float)Math.PI));
            float f8 = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -(this.neck.field_78795_f - 0.7f) * 0.75f;
            this.rightarm.field_78795_f = (float)((double)this.rightarm.field_78795_f - ((double)f7 * 1.2 + (double)f8));
            this.rightarm.field_78796_g += this.body.field_78796_g * 2.0f;
            this.rightarm.field_78808_h = MathHelper.func_76126_a((float)(this.field_78095_p * (float)Math.PI)) * -0.4f;
        }
        if (sneaking = false) {
            this.body.field_78795_f = 0.5f;
            this.rightarm.field_78795_f += 0.4f;
            this.leftarm.field_78795_f += 0.4f;
            this.rightleg.field_78798_e = 4.0f;
            this.leftleg.field_78798_e = 4.0f;
            this.rightleg.field_78797_d = 9.0f;
            this.leftleg.field_78797_d = 9.0f;
            this.neck.field_78797_d = 1.0f;
        } else {
            this.body.field_78795_f = 0.0f;
            this.rightleg.field_78798_e = 0.1f;
            this.leftleg.field_78798_e = 0.1f;
            this.rightleg.field_78797_d = 12.0f;
            this.leftleg.field_78797_d = 12.0f;
            this.neck.field_78797_d = 0.0f;
        }
        this.rightarm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.leftarm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.rightarm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.leftarm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        boolean shootingBow = false;
        if (shootingBow) {
            f6 = 0.0f;
            f7 = 0.0f;
            this.rightarm.field_78808_h = 0.0f;
            this.leftarm.field_78808_h = 0.0f;
            this.rightarm.field_78796_g = -(0.1f - f6 * 0.6f) + this.neck.field_78796_g;
            this.leftarm.field_78796_g = 0.1f - f6 * 0.6f + this.neck.field_78796_g + 0.4f;
            this.rightarm.field_78795_f = -1.5707964f + this.neck.field_78795_f;
            this.leftarm.field_78795_f = -1.5707964f + this.neck.field_78795_f;
            this.rightarm.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.leftarm.field_78795_f -= f6 * 1.2f - f7 * 0.4f;
            this.rightarm.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.leftarm.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
            this.rightarm.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
            this.leftarm.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        }
        if ((i = (entityDemon = (EntityLeonard)entity).getAttackTimer()) > 0) {
            float di = 10.0f;
            this.rightarm.field_78795_f = -2.0f + 1.5f * (Math.abs(((float)i - par4) % 10.0f - di * 0.5f) - di * 0.25f) / (di * 0.25f);
        }
    }
}

