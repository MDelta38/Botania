/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.client.model.armor;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ModelArmorManasteel
extends ModelBiped {
    public ModelRenderer helm;
    public ModelRenderer body;
    public ModelRenderer armR;
    public ModelRenderer armL;
    public ModelRenderer belt;
    public ModelRenderer bootR;
    public ModelRenderer bootL;
    public ModelRenderer helm1;
    public ModelRenderer helm2;
    public ModelRenderer helm3;
    public ModelRenderer helm4;
    public ModelRenderer helm5;
    public ModelRenderer helm6;
    public ModelRenderer helm7;
    public ModelRenderer body2;
    public ModelRenderer armRpauldron;
    public ModelRenderer armLpauldron;
    public ModelRenderer legR;
    public ModelRenderer legL;
    int slot;

    public ModelArmorManasteel(int slot) {
        this.slot = slot;
        this.field_78090_t = 64;
        this.field_78089_u = 128;
        float s = 0.2f;
        this.armRpauldron = new ModelRenderer((ModelBase)this, 30, 47);
        this.armRpauldron.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRpauldron.func_78790_a(-4.0f, -2.0f, -2.5f, 4, 4, 5, 0.2f);
        this.armL = new ModelRenderer((ModelBase)this, 0, 68);
        this.armL.field_78809_i = true;
        this.armL.func_78793_a(5.0f, 2.0f, -0.0f);
        this.armL.func_78790_a(1.0f, 3.0f, -2.0f, 2, 6, 4, s);
        this.setRotateAngle(this.armL, 0.0f, 0.0f, -0.17453292f);
        this.legR = new ModelRenderer((ModelBase)this, 12, 68);
        this.legR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.legR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, s);
        this.setRotateAngle(this.legR, 0.0f, 0.0f, 0.0f);
        this.helm3 = new ModelRenderer((ModelBase)this, 24, 32);
        this.helm3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm3.func_78790_a(-1.0f, -8.5f, -6.5f, 2, 5, 1, s);
        this.setRotateAngle(this.helm3, -0.17453292f, 0.0f, 0.0f);
        this.helm7 = new ModelRenderer((ModelBase)this, 24, 32);
        this.helm7.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm7.func_78790_a(-1.0f, -8.5f, -6.0f, 2, 3, 1, s);
        this.setRotateAngle(this.helm7, -0.34906584f, 0.0f, 0.0f);
        this.bootL = new ModelRenderer((ModelBase)this, 28, 68);
        this.bootL.field_78809_i = true;
        this.bootL.func_78793_a(2.0f, 12.0f, 0.0f);
        this.bootL.func_78790_a(-2.0f, 8.0f, -3.0f, 4, 4, 5, s);
        this.setRotateAngle(this.bootL, 0.0f, 0.0f, 0.0f);
        this.helm4 = new ModelRenderer((ModelBase)this, 0, 39);
        this.helm4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm4.func_78790_a(-4.0f, -8.0f, -0.5f, 1, 3, 5, s);
        this.bootR = new ModelRenderer((ModelBase)this, 28, 68);
        this.bootR.func_78793_a(-2.0f, 12.0f, 0.0f);
        this.bootR.func_78790_a(-2.0f, 8.0f, -3.0f, 4, 4, 5, s);
        this.setRotateAngle(this.bootR, 0.0f, 0.0f, 0.0f);
        this.legL = new ModelRenderer((ModelBase)this, 12, 68);
        this.legL.field_78809_i = true;
        this.legL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.legL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 8, 4, s);
        this.setRotateAngle(this.legL, 0.0f, 0.0f, 0.0f);
        this.armR = new ModelRenderer((ModelBase)this, 0, 68);
        this.armR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.armR.func_78790_a(-3.0f, 3.0f, -2.0f, 2, 6, 4, s);
        this.setRotateAngle(this.armR, 0.0f, 0.0f, 0.17453292f);
        this.helm1 = new ModelRenderer((ModelBase)this, 12, 39);
        this.helm1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm1.func_78790_a(-4.0f, -5.0f, -4.5f, 1, 3, 4, s);
        this.helm2 = new ModelRenderer((ModelBase)this, 12, 39);
        this.helm2.field_78809_i = true;
        this.helm2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm2.func_78790_a(3.0f, -5.0f, -4.5f, 1, 3, 4, s);
        this.body2 = new ModelRenderer((ModelBase)this, 0, 59);
        this.body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body2.func_78790_a(-4.0f, 6.0f, -2.5f, 8, 4, 5, s);
        this.setRotateAngle(this.body2, -0.08726646f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 47);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78790_a(-4.5f, 0.0f, -3.5f, 9, 6, 6, s);
        this.setRotateAngle(this.body, 0.08726646f, 0.0f, 0.0f);
        this.helm6 = new ModelRenderer((ModelBase)this, 24, 32);
        this.helm6.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm6.func_78790_a(-1.0f, -8.5f, -5.5f, 2, 3, 1, s);
        this.setRotateAngle(this.helm6, -0.5235988f, 0.0f, 0.0f);
        this.belt = new ModelRenderer((ModelBase)this, 26, 59);
        this.belt.func_78793_a(0.0f, 0.0f, 0.0f);
        this.belt.func_78790_a(-4.5f, 9.5f, -3.0f, 9, 3, 6, s);
        this.helm = new ModelRenderer((ModelBase)this, 0, 32);
        this.helm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm.func_78790_a(-4.0f, -8.0f, -4.5f, 8, 3, 4, s);
        this.setRotateAngle(this.helm, 0.08726646f, 0.0f, 0.0f);
        this.armLpauldron = new ModelRenderer((ModelBase)this, 30, 47);
        this.armLpauldron.field_78809_i = true;
        this.armLpauldron.func_78793_a(0.0f, 0.0f, -0.0f);
        this.armLpauldron.func_78790_a(0.0f, -2.0f, -2.5f, 4, 4, 5, s);
        this.helm5 = new ModelRenderer((ModelBase)this, 0, 39);
        this.helm5.field_78809_i = true;
        this.helm5.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm5.func_78790_a(3.0f, -8.0f, -0.5f, 1, 3, 5, s);
        this.helm.func_78792_a(this.helm3);
        this.helm.func_78792_a(this.helm7);
        this.helm.func_78792_a(this.helm4);
        this.helm.func_78792_a(this.helm6);
        this.helm.func_78792_a(this.helm1);
        this.helm.func_78792_a(this.helm2);
        this.helm.func_78792_a(this.helm5);
        this.body.func_78792_a(this.body2);
        this.armL.func_78792_a(this.armLpauldron);
        this.armR.func_78792_a(this.armRpauldron);
        this.belt.func_78792_a(this.legR);
        this.belt.func_78792_a(this.legL);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.helm.field_78806_j = this.slot == 0;
        this.body.field_78806_j = this.slot == 1;
        this.armR.field_78806_j = this.slot == 1;
        this.armL.field_78806_j = this.slot == 1;
        this.legR.field_78806_j = this.slot == 2;
        this.legL.field_78806_j = this.slot == 2;
        this.bootL.field_78806_j = this.slot == 3;
        this.bootR.field_78806_j = this.slot == 3;
        this.field_78114_d.field_78806_j = false;
        this.field_78116_c = this.helm;
        this.field_78115_e = this.body;
        this.field_78112_f = this.armR;
        this.field_78113_g = this.armL;
        if (this.slot == 2) {
            this.field_78123_h = this.legR;
            this.field_78124_i = this.legL;
        } else {
            this.field_78123_h = this.bootR;
            this.field_78124_i = this.bootL;
        }
        this.prepareForRender(entity);
        super.func_78088_a(entity, f, f1, f2, f3, f4, f5);
    }

    public void prepareForRender(Entity entity) {
        EntityLivingBase living = (EntityLivingBase)entity;
        boolean bl = this.field_78117_n = living != null ? living.func_70093_af() : false;
        if (living != null && living instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)living;
            ItemStack itemstack = player.field_71071_by.func_70448_g();
            this.field_78120_m = itemstack != null ? 1 : 0;
            this.field_78118_o = false;
            if (itemstack != null && player.func_71052_bv() > 0) {
                EnumAction enumaction = itemstack.func_77975_n();
                if (enumaction == EnumAction.block) {
                    this.field_78120_m = 3;
                } else if (enumaction == EnumAction.bow) {
                    this.field_78118_o = true;
                }
            }
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.field_78795_f = x;
        modelRenderer.field_78796_g = y;
        modelRenderer.field_78808_h = z;
    }
}

