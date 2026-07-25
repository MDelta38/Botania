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

import com.emoniph.witchery.entity.EntityLilith;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

@SideOnly(value=Side.CLIENT)
public class ModelLilith
extends ModelBase {
    public ModelRenderer legRight;
    public ModelRenderer legLeft;
    public ModelRenderer bodyChest;
    public ModelRenderer bodyWaist;
    public ModelRenderer skirt1;
    public ModelRenderer skirt2;
    public ModelRenderer bodyShoulders;
    public ModelRenderer armRight;
    public ModelRenderer armLeft;
    public ModelRenderer neck;
    public ModelRenderer head;
    public ModelRenderer legRightLower;
    public ModelRenderer legLeftLower;
    public ModelRenderer armRightLower;
    public ModelRenderer armRightWing;
    public ModelRenderer armLeftLower;
    public ModelRenderer armLeftWing;
    public ModelRenderer head2;
    public ModelRenderer hornRight;
    public ModelRenderer hornLeft;
    public ModelRenderer nose;
    public ModelRenderer toothRight;
    public ModelRenderer toothLeft;
    public ModelRenderer head3;

    public ModelLilith() {
        this.field_78090_t = 64;
        this.field_78089_u = 64;
        this.neck = new ModelRenderer((ModelBase)this, 24, 0);
        this.neck.func_78793_a(0.0f, -13.0f, 0.0f);
        this.neck.func_78790_a(-1.5f, -1.5f, -1.5f, 3, 2, 3, 0.0f);
        this.legLeftLower = new ModelRenderer((ModelBase)this, 48, 47);
        this.legLeftLower.field_78809_i = true;
        this.legLeftLower.func_78793_a(0.0f, 0.0f, 0.0f);
        this.legLeftLower.func_78790_a(-2.0f, 8.0f, 2.0f, 4, 13, 4, 0.0f);
        this.hornLeft = new ModelRenderer((ModelBase)this, 52, 30);
        this.hornLeft.field_78809_i = true;
        this.hornLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hornLeft.func_78790_a(1.0f, -12.3f, 0.0f, 6, 10, 0, 0.0f);
        this.setRotateAngle(this.hornLeft, -0.18203785f, 0.0f, 0.0f);
        this.bodyChest = new ModelRenderer((ModelBase)this, 17, 17);
        this.bodyChest.func_78793_a(0.0f, -9.8f, -1.9f);
        this.bodyChest.func_78790_a(-4.0f, -1.5f, -1.5f, 8, 3, 3, 0.0f);
        this.setRotateAngle(this.bodyChest, 0.7853982f, 0.0f, 0.0f);
        this.nose = new ModelRenderer((ModelBase)this, 41, 0);
        this.nose.func_78793_a(0.0f, 0.0f, 0.0f);
        this.nose.func_78790_a(-0.5f, -3.6f, -4.0f, 1, 2, 1, 0.0f);
        this.armLeftLower = new ModelRenderer((ModelBase)this, 8, 25);
        this.armLeftLower.field_78809_i = true;
        this.armLeftLower.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armLeftLower.func_78790_a(-0.5f, 9.8f, 0.8f, 3, 13, 3, 0.0f);
        this.setRotateAngle(this.armLeftLower, -0.22759093f, 0.0f, 0.0f);
        this.skirt1 = new ModelRenderer((ModelBase)this, 0, 49);
        this.skirt1.func_78793_a(0.0f, -0.9f, 0.0f);
        this.skirt1.func_78790_a(-4.5f, 0.0f, -2.5f, 9, 10, 5, 0.0f);
        this.skirt2 = new ModelRenderer((ModelBase)this, 0, 49);
        this.skirt2.func_78793_a(0.0f, -0.9f, 0.0f);
        this.skirt2.func_78790_a(-4.5f, 0.0f, -2.5f, 9, 10, 5, 0.0f);
        this.armLeftWing = new ModelRenderer((ModelBase)this, 0, 13);
        this.armLeftWing.field_78809_i = true;
        this.armLeftWing.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armLeftWing.func_78790_a(1.0f, -19.6f, -12.7f, 0, 30, 4, 0.0f);
        this.setRotateAngle(this.armLeftWing, 2.5497515f, 0.17453292f, 0.0f);
        this.legRightLower = new ModelRenderer((ModelBase)this, 48, 47);
        this.legRightLower.func_78793_a(0.0f, 0.0f, 0.0f);
        this.legRightLower.func_78790_a(-2.0f, 8.0f, 2.0f, 4, 13, 4, 0.0f);
        this.armLeft = new ModelRenderer((ModelBase)this, 0, 0);
        this.armLeft.field_78809_i = true;
        this.armLeft.func_78793_a(4.4f, -11.5f, 0.0f);
        this.armLeft.func_78790_a(-0.5f, -1.5f, -1.5f, 3, 13, 3, 0.0f);
        this.hornRight = new ModelRenderer((ModelBase)this, 52, 30);
        this.hornRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.hornRight.func_78790_a(-7.0f, -12.3f, 0.0f, 6, 10, 0, 0.0f);
        this.setRotateAngle(this.hornRight, -0.18203785f, 0.0f, 0.0f);
        this.legLeft = new ModelRenderer((ModelBase)this, 36, 30);
        this.legLeft.field_78809_i = true;
        this.legLeft.func_78793_a(2.1f, 2.5f, 0.0f);
        this.legLeft.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 13, 4, 0.0f);
        this.setRotateAngle(this.legLeft, -0.27314404f, 0.0f, 0.0f);
        this.bodyShoulders = new ModelRenderer((ModelBase)this, 15, 6);
        this.bodyShoulders.func_78793_a(0.0f, -12.7f, 0.0f);
        this.bodyShoulders.func_78790_a(-4.0f, 0.0f, -2.0f, 8, 6, 4, 0.0f);
        this.armRightLower = new ModelRenderer((ModelBase)this, 8, 25);
        this.armRightLower.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRightLower.func_78790_a(-2.5f, 9.8f, 0.8f, 3, 13, 3, 0.0f);
        this.setRotateAngle(this.armRightLower, -0.22759093f, 0.0f, 0.0f);
        this.head3 = new ModelRenderer((ModelBase)this, 44, 22);
        this.head3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head3.func_78790_a(-2.0f, -4.7f, 5.6f, 4, 4, 4, 0.0f);
        this.head2 = new ModelRenderer((ModelBase)this, 42, 12);
        this.head2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.head2.func_78790_a(-2.5f, -5.5f, 1.0f, 5, 5, 5, 0.0f);
        this.setRotateAngle(this.head2, -0.18203785f, 0.0f, 0.0f);
        this.armRightWing = new ModelRenderer((ModelBase)this, 0, 13);
        this.armRightWing.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRightWing.func_78790_a(-1.0f, -19.6f, -12.7f, 0, 30, 4, 0.0f);
        this.setRotateAngle(this.armRightWing, 2.5497515f, -0.17453292f, 0.0f);
        this.legRight = new ModelRenderer((ModelBase)this, 36, 30);
        this.legRight.func_78793_a(-2.1f, 2.5f, 0.0f);
        this.legRight.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 13, 4, 0.0f);
        this.setRotateAngle(this.legRight, -0.27314404f, 0.0f, 0.0f);
        this.armRight = new ModelRenderer((ModelBase)this, 0, 0);
        this.armRight.func_78793_a(-4.5f, -11.5f, 0.0f);
        this.armRight.func_78790_a(-2.5f, -1.5f, -1.5f, 3, 13, 3, 0.0f);
        this.toothLeft = new ModelRenderer((ModelBase)this, 20, 0);
        this.toothLeft.field_78809_i = true;
        this.toothLeft.func_78793_a(0.0f, 0.0f, 0.0f);
        this.toothLeft.func_78790_a(0.5f, -1.6f, -3.6f, 1, 3, 1, -0.35f);
        this.toothRight = new ModelRenderer((ModelBase)this, 20, 0);
        this.toothRight.func_78793_a(0.0f, 0.0f, 0.0f);
        this.toothRight.func_78790_a(-1.5f, -1.6f, -3.6f, 1, 3, 1, -0.35f);
        this.bodyWaist = new ModelRenderer((ModelBase)this, 20, 24);
        this.bodyWaist.func_78793_a(0.0f, -7.5f, 0.0f);
        this.bodyWaist.func_78790_a(-3.0f, 0.0f, -1.0f, 6, 10, 2, 0.0f);
        this.head = new ModelRenderer((ModelBase)this, 40, 0);
        this.head.func_78793_a(0.0f, -13.5f, 0.0f);
        this.head.func_78790_a(-3.0f, -6.0f, -3.0f, 6, 6, 6, 0.0f);
        this.legLeft.func_78792_a(this.legLeftLower);
        this.head.func_78792_a(this.hornLeft);
        this.head.func_78792_a(this.nose);
        this.armLeft.func_78792_a(this.armLeftLower);
        this.armLeft.func_78792_a(this.armLeftWing);
        this.legRight.func_78792_a(this.legRightLower);
        this.head.func_78792_a(this.hornRight);
        this.armRight.func_78792_a(this.armRightLower);
        this.head2.func_78792_a(this.head3);
        this.head.func_78792_a(this.head2);
        this.armRight.func_78792_a(this.armRightWing);
        this.head.func_78792_a(this.toothLeft);
        this.head.func_78792_a(this.toothRight);
    }

    private void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.func_78087_a(f, f1, f2, f3, f4, f5, entity);
        this.neck.func_78785_a(f5);
        this.bodyChest.func_78785_a(f5);
        this.skirt1.func_78785_a(f5);
        this.skirt2.func_78785_a(f5);
        this.armLeft.func_78785_a(f5);
        this.legLeft.func_78785_a(f5);
        this.bodyShoulders.func_78785_a(f5);
        this.legRight.func_78785_a(f5);
        this.armRight.func_78785_a(f5);
        this.bodyWaist.func_78785_a(f5);
        this.head.func_78785_a(f5);
    }

    public void func_78087_a(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.head.field_78796_g = par4 / 57.295776f;
        this.head.field_78795_f = par5 / 57.295776f;
        this.armRight.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 2.0f * par2 * 0.5f;
        this.armLeft.field_78795_f = MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 2.0f * par2 * 0.5f;
        this.armRight.field_78808_h = 0.0f;
        this.armLeft.field_78808_h = 0.0f;
        this.legRight.field_78795_f = Math.max(MathHelper.func_76134_b((float)(par1 * 0.6662f)) * 1.4f * par2 - 0.27314404f, -0.8f);
        this.legLeft.field_78795_f = Math.max(MathHelper.func_76134_b((float)(par1 * 0.6662f + (float)Math.PI)) * 1.4f * par2 - 0.27314404f, -0.8f);
        this.legRight.field_78796_g = 0.0f;
        this.legLeft.field_78796_g = 0.0f;
        this.skirt1.field_78795_f = Math.min(this.legRight.field_78795_f, this.legLeft.field_78795_f);
        this.skirt2.field_78795_f = Math.max(Math.max(this.legRight.field_78795_f, this.legLeft.field_78795_f), 0.2f);
        if (this.field_78093_q) {
            this.armRight.field_78795_f += -0.62831855f;
            this.armLeft.field_78795_f += -0.62831855f;
            this.legRight.field_78795_f = -1.2566371f;
            this.legLeft.field_78795_f = -1.2566371f;
            this.legRight.field_78796_g = 0.31415927f;
            this.legLeft.field_78796_g = -0.31415927f;
        }
        this.armRight.field_78796_g = 0.0f;
        this.armLeft.field_78796_g = 0.0f;
        this.armRight.field_78808_h += MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.armLeft.field_78808_h -= MathHelper.func_76134_b((float)(par3 * 0.09f)) * 0.05f + 0.05f;
        this.armRight.field_78795_f += MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        this.armLeft.field_78795_f -= MathHelper.func_76126_a((float)(par3 * 0.067f)) * 0.05f;
        EntityLilith entityDemon = (EntityLilith)entity;
        int i = entityDemon.getAttackTimer();
        if (i > 0) {
            float di = 10.0f;
            this.armRight.field_78795_f = -2.0f + 1.5f * (Math.abs(((float)i - par4) % 10.0f - di * 0.5f) - di * 0.25f) / (di * 0.25f);
        }
    }
}

