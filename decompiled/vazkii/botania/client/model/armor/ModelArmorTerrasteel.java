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

public class ModelArmorTerrasteel
extends ModelBiped {
    public ModelRenderer helm;
    public ModelRenderer body;
    public ModelRenderer armr;
    public ModelRenderer armL;
    public ModelRenderer belt;
    public ModelRenderer bootR;
    public ModelRenderer bootL;
    public ModelRenderer helm2;
    public ModelRenderer helm3;
    public ModelRenderer helm4;
    public ModelRenderer helmLeaf1;
    public ModelRenderer helmLeaf2;
    public ModelRenderer helmLeaf3;
    public ModelRenderer helmLeaf4;
    public ModelRenderer helmLeaf5;
    public ModelRenderer helmLeaf6;
    public ModelRenderer helmbranch1;
    public ModelRenderer helmbranch2;
    public ModelRenderer helmbranch3;
    public ModelRenderer helmbranch4;
    public ModelRenderer body2;
    public ModelRenderer armRpauldron;
    public ModelRenderer armRbranch1;
    public ModelRenderer armRbranch2;
    public ModelRenderer armLpauldron;
    public ModelRenderer armLbranch1;
    public ModelRenderer armLbranch2;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer bootR1;
    public ModelRenderer bootRbranch;
    public ModelRenderer bootL2;
    public ModelRenderer bootLbranch;
    int slot;

    public ModelArmorTerrasteel(int slot) {
        this.slot = slot;
        this.field_78090_t = 64;
        this.field_78089_u = 128;
        float s = 0.2f;
        this.armr = new ModelRenderer((ModelBase)this, 0, 77);
        this.armr.func_78793_a(-5.0f, 2.0f, -0.0f);
        this.armr.func_78790_a(-3.0f, 3.0f, -2.0f, 4, 7, 4, s);
        this.setRotateAngle(this.armr, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 44);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78790_a(-4.5f, 0.0f, -3.5f, 9, 6, 6, s);
        this.setRotateAngle(this.body, 0.0f, 0.0f, 0.0f);
        this.helm4 = new ModelRenderer((ModelBase)this, 56, 32);
        this.helm4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm4.func_78790_a(-1.0f, -7.5f, -6.5f, 2, 6, 2, s);
        this.setRotateAngle(this.helm4, 0.0f, 0.0f, 0.0f);
        this.bootR1 = new ModelRenderer((ModelBase)this, 32, 77);
        this.bootR1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bootR1.func_78790_a(-2.0f, 6.0f, -2.0f, 4, 2, 4, s);
        this.helmbranch4 = new ModelRenderer((ModelBase)this, 34, 43);
        this.helmbranch4.field_78809_i = true;
        this.helmbranch4.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helmbranch4.func_78790_a(-2.0f, -8.0f, -4.0f, 1, 2, 7, s);
        this.setRotateAngle(this.helmbranch4, 0.2617994f, 0.0f, 1.0471976f);
        this.bootL = new ModelRenderer((ModelBase)this, 32, 83);
        this.bootL.field_78809_i = true;
        this.bootL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.bootL.func_78790_a(-2.0f, 8.0f, -3.0f, 4, 4, 5, s);
        this.setRotateAngle(this.bootL, 0.0f, 0.0f, 0.0f);
        this.bootR = new ModelRenderer((ModelBase)this, 32, 83);
        this.bootR.func_78793_a(-1.9f, 12.0f, 0.1f);
        this.bootR.func_78790_a(-2.0f, 8.0f, -3.0f, 4, 4, 5, s);
        this.setRotateAngle(this.bootR, 0.0f, 0.0f, 0.0f);
        this.helmLeaf5 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf5.field_78809_i = true;
        this.helmLeaf5.func_78793_a(0.0f, 0.2f, 0.0f);
        this.helmLeaf5.func_78790_a(-1.0f, -11.0f, -4.5f, 2, 5, 1, s);
        this.setRotateAngle(this.helmLeaf5, -0.5235988f, -0.5235988f, 0.5235988f);
        this.bootLbranch = new ModelRenderer((ModelBase)this, 48, 77);
        this.bootLbranch.field_78809_i = true;
        this.bootLbranch.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bootLbranch.func_78790_a(8.0f, 1.0f, -2.0f, 1, 2, 5, s);
        this.setRotateAngle(this.bootLbranch, 0.2617994f, 0.0f, 1.0471976f);
        this.helmLeaf4 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf4.field_78809_i = true;
        this.helmLeaf4.func_78793_a(0.0f, 0.2f, 0.0f);
        this.helmLeaf4.func_78790_a(-1.5f, -9.0f, -6.0f, 2, 3, 1, s);
        this.setRotateAngle(this.helmLeaf4, -0.2617994f, -0.2617994f, 0.5235988f);
        this.helmLeaf2 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf2.func_78793_a(0.0f, 0.2f, 0.0f);
        this.helmLeaf2.func_78790_a(-1.0f, -11.0f, -4.5f, 2, 5, 1, s);
        this.setRotateAngle(this.helmLeaf2, -0.5235988f, 0.5235988f, -0.5235988f);
        this.helm = new ModelRenderer((ModelBase)this, 0, 32);
        this.helm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm.func_78790_a(-4.0f, -8.0f, -4.5f, 8, 3, 9, s);
        this.setRotateAngle(this.helm, 0.08726646f, 0.0f, 0.0f);
        this.helm2 = new ModelRenderer((ModelBase)this, 34, 32);
        this.helm2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm2.func_78790_a(-4.0f, -5.0f, -4.5f, 2, 5, 6, s);
        this.helm3 = new ModelRenderer((ModelBase)this, 34, 32);
        this.helm3.field_78809_i = true;
        this.helm3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm3.func_78790_a(2.0f, -5.0f, -4.5f, 2, 5, 6, s);
        this.helmbranch1 = new ModelRenderer((ModelBase)this, 34, 43);
        this.helmbranch1.field_78809_i = true;
        this.helmbranch1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helmbranch1.func_78790_a(-2.0f, -10.0f, -1.0f, 1, 2, 7, s);
        this.setRotateAngle(this.helmbranch1, 0.5235988f, 0.0f, -0.08726646f);
        this.bootL2 = new ModelRenderer((ModelBase)this, 32, 77);
        this.bootL2.field_78809_i = true;
        this.bootL2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bootL2.func_78790_a(-2.0f, 6.0f, -2.0f, 4, 2, 4, s);
        this.helmbranch2 = new ModelRenderer((ModelBase)this, 34, 43);
        this.helmbranch2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helmbranch2.func_78790_a(1.0f, -10.0f, -1.0f, 1, 2, 7, s);
        this.setRotateAngle(this.helmbranch2, 0.5235988f, 0.0f, 0.08726646f);
        this.legR = new ModelRenderer((ModelBase)this, 16, 77);
        this.legR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.legR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, s);
        this.setRotateAngle(this.legR, 0.0f, 0.0f, 0.0f);
        this.helmLeaf6 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf6.field_78809_i = true;
        this.helmLeaf6.func_78793_a(0.0f, 0.2f, 0.0f);
        this.helmLeaf6.func_78790_a(-0.5f, -13.0f, -3.0f, 2, 7, 1, s);
        this.setRotateAngle(this.helmLeaf6, -0.7853982f, -0.7853982f, 0.7853982f);
        this.armLbranch1 = new ModelRenderer((ModelBase)this, 51, 44);
        this.armLbranch1.field_78809_i = true;
        this.armLbranch1.func_78793_a(0.0f, 0.0f, -0.0f);
        this.armLbranch1.func_78790_a(2.5f, -5.0f, -1.0f, 1, 5, 2, s);
        this.setRotateAngle(this.armLbranch1, 0.0f, 0.0f, 0.7853982f);
        this.bootRbranch = new ModelRenderer((ModelBase)this, 48, 77);
        this.bootRbranch.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bootRbranch.func_78790_a(-9.0f, 1.0f, -2.0f, 1, 2, 5, s);
        this.setRotateAngle(this.bootRbranch, 0.2617994f, 0.0f, -1.0471976f);
        this.armRbranch2 = new ModelRenderer((ModelBase)this, 50, 43);
        this.armRbranch2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRbranch2.func_78790_a(-1.5f, -5.0f, -1.5f, 1, 5, 3, s);
        this.setRotateAngle(this.armRbranch2, 0.0f, 0.0f, -0.5235988f);
        this.helmLeaf3 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf3.func_78793_a(0.0f, 0.2f, 0.0f);
        this.helmLeaf3.func_78790_a(-1.5f, -13.0f, -3.0f, 2, 7, 1, s);
        this.setRotateAngle(this.helmLeaf3, -0.7853982f, 0.7853982f, -0.7853982f);
        this.armRpauldron = new ModelRenderer((ModelBase)this, 0, 66);
        this.armRpauldron.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRpauldron.func_78790_a(-4.0f, -2.0f, -3.0f, 5, 5, 6, s);
        this.armLbranch2 = new ModelRenderer((ModelBase)this, 50, 43);
        this.armLbranch2.field_78809_i = true;
        this.armLbranch2.func_78793_a(0.0f, 0.0f, -0.0f);
        this.armLbranch2.func_78790_a(0.5f, -5.0f, -1.5f, 1, 5, 3, s);
        this.setRotateAngle(this.armLbranch2, 0.0f, 0.0f, 0.5235988f);
        this.armL = new ModelRenderer((ModelBase)this, 0, 77);
        this.armL.field_78809_i = true;
        this.armL.func_78793_a(5.0f, 2.0f, -0.0f);
        this.armL.func_78790_a(-1.0f, 3.0f, -2.0f, 4, 7, 4, s);
        this.setRotateAngle(this.armL, 0.0f, 0.0f, 0.0f);
        this.body2 = new ModelRenderer((ModelBase)this, 0, 57);
        this.body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body2.func_78790_a(-4.0f, 6.0f, -2.5f, 8, 4, 5, s);
        this.setRotateAngle(this.body2, -0.08726646f, 0.0f, 0.0f);
        this.helmbranch3 = new ModelRenderer((ModelBase)this, 34, 43);
        this.helmbranch3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helmbranch3.func_78790_a(1.0f, -8.0f, -4.0f, 1, 2, 7, s);
        this.setRotateAngle(this.helmbranch3, 0.2617994f, 0.0f, -1.0471976f);
        this.armRbranch1 = new ModelRenderer((ModelBase)this, 51, 44);
        this.armRbranch1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRbranch1.func_78790_a(-3.5f, -5.0f, -1.0f, 1, 5, 2, s);
        this.setRotateAngle(this.armRbranch1, 0.0f, 0.0f, -0.7853982f);
        this.belt = new ModelRenderer((ModelBase)this, 22, 66);
        this.belt.func_78793_a(0.0f, 0.0f, 0.0f);
        this.belt.func_78790_a(-4.5f, 9.5f, -3.0f, 9, 3, 6, s);
        this.helmLeaf1 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helmLeaf1.func_78793_a(0.0f, 0.2f, 0.0f);
        this.helmLeaf1.func_78790_a(-0.5f, -9.0f, -6.0f, 2, 3, 1, s);
        this.setRotateAngle(this.helmLeaf1, -0.2617994f, 0.2617994f, -0.5235988f);
        this.armLpauldron = new ModelRenderer((ModelBase)this, 0, 66);
        this.armLpauldron.field_78809_i = true;
        this.armLpauldron.func_78793_a(0.0f, 0.0f, -0.0f);
        this.armLpauldron.func_78790_a(-1.0f, -2.0f, -3.0f, 5, 5, 6, s);
        this.legL = new ModelRenderer((ModelBase)this, 16, 77);
        this.legL.field_78809_i = true;
        this.legL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.legL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, s);
        this.setRotateAngle(this.legL, 0.0f, 0.0f, 0.0f);
        this.helm.func_78792_a(this.helm4);
        this.bootR.func_78792_a(this.bootR1);
        this.helm.func_78792_a(this.helmbranch4);
        this.helm.func_78792_a(this.helmLeaf5);
        this.bootL.func_78792_a(this.bootLbranch);
        this.helm.func_78792_a(this.helmLeaf4);
        this.helm.func_78792_a(this.helmLeaf2);
        this.helm.func_78792_a(this.helm2);
        this.helm.func_78792_a(this.helm3);
        this.helm.func_78792_a(this.helmbranch1);
        this.bootL.func_78792_a(this.bootL2);
        this.helm.func_78792_a(this.helmbranch2);
        this.belt.func_78792_a(this.legR);
        this.helm.func_78792_a(this.helmLeaf6);
        this.armLpauldron.func_78792_a(this.armLbranch1);
        this.bootR.func_78792_a(this.bootRbranch);
        this.armRpauldron.func_78792_a(this.armRbranch2);
        this.helm.func_78792_a(this.helmLeaf3);
        this.armr.func_78792_a(this.armRpauldron);
        this.armLpauldron.func_78792_a(this.armLbranch2);
        this.body.func_78792_a(this.body2);
        this.helm.func_78792_a(this.helmbranch3);
        this.armRpauldron.func_78792_a(this.armRbranch1);
        this.helm.func_78792_a(this.helmLeaf1);
        this.armL.func_78792_a(this.armLpauldron);
        this.belt.func_78792_a(this.legL);
    }

    public void func_78088_a(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.helm.field_78806_j = this.slot == 0;
        this.body.field_78806_j = this.slot == 1;
        this.armr.field_78806_j = this.slot == 1;
        this.armL.field_78806_j = this.slot == 1;
        this.legR.field_78806_j = this.slot == 2;
        this.legL.field_78806_j = this.slot == 2;
        this.bootL.field_78806_j = this.slot == 3;
        this.bootR.field_78806_j = this.slot == 3;
        this.field_78114_d.field_78806_j = false;
        this.field_78116_c = this.helm;
        this.field_78115_e = this.body;
        this.field_78112_f = this.armr;
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

