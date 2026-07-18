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

public class ModelArmorElementium
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
    public ModelRenderer fairy;
    public ModelRenderer helmWing1;
    public ModelRenderer helmWing2;
    public ModelRenderer helmWing3;
    public ModelRenderer helmWing4;
    public ModelRenderer body2;
    public ModelRenderer armRpauldron;
    public ModelRenderer wing1;
    public ModelRenderer wing2;
    public ModelRenderer armLpauldron;
    public ModelRenderer wing1_1;
    public ModelRenderer wing2_1;
    public ModelRenderer legR;
    public ModelRenderer legL;
    public ModelRenderer bootR1;
    public ModelRenderer wing1_2;
    public ModelRenderer wing2_2;
    public ModelRenderer bootL1;
    public ModelRenderer wing1_3;
    public ModelRenderer wing2_3;
    int slot;

    public ModelArmorElementium(int slot) {
        this.slot = slot;
        this.field_78090_t = 64;
        this.field_78089_u = 128;
        float s = 0.2f;
        this.fairy = new ModelRenderer((ModelBase)this, 34, 32);
        this.fairy.func_78793_a(0.0f, 0.0f, 0.0f);
        this.fairy.func_78790_a(-2.0f, -8.5f, -7.0f, 4, 4, 4, s);
        this.setRotateAngle(this.fairy, -0.17453292f, 0.0f, 0.0f);
        this.helm3 = new ModelRenderer((ModelBase)this, 0, 32);
        this.helm3.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm3.func_78790_a(-1.0f, -5.5f, -5.5f, 2, 3, 1, s);
        this.setRotateAngle(this.helm3, -0.17453292f, 0.0f, 0.0f);
        this.wing1_2 = new ModelRenderer((ModelBase)this, 56, 43);
        this.wing1_2.field_78809_i = true;
        this.wing1_2.func_78793_a(-2.5f, 9.0f, 0.0f);
        this.wing1_2.func_78790_a(0.5f, -2.0f, 0.0f, 0, 2, 3, s);
        this.setRotateAngle(this.wing1_2, 0.2617994f, -0.7853982f, -0.2617994f);
        this.helm1 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helm1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm1.func_78790_a(-4.0f, -5.0f, -4.5f, 1, 5, 4, s);
        this.legL = new ModelRenderer((ModelBase)this, 12, 79);
        this.legL.field_78809_i = true;
        this.legL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.legL.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, s);
        this.setRotateAngle(this.legL, 0.0f, 0.0f, 0.0f);
        this.armL = new ModelRenderer((ModelBase)this, 0, 79);
        this.armL.field_78809_i = true;
        this.armL.func_78793_a(5.0f, 2.0f, -0.0f);
        this.armL.func_78790_a(1.5f, 6.0f, -2.0f, 2, 4, 4, s);
        this.setRotateAngle(this.armL, 0.0f, 0.0f, 0.0f);
        this.armRpauldron = new ModelRenderer((ModelBase)this, 0, 67);
        this.armRpauldron.func_78793_a(0.0f, 0.0f, 0.0f);
        this.armRpauldron.func_78790_a(-4.0f, -2.5f, -3.0f, 5, 6, 6, s);
        this.legR = new ModelRenderer((ModelBase)this, 12, 79);
        this.legR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.legR.func_78790_a(-2.0f, 0.0f, -2.0f, 4, 6, 4, s);
        this.setRotateAngle(this.legR, 0.0f, 0.0f, 0.0f);
        this.helmWing2 = new ModelRenderer((ModelBase)this, 46, 45);
        this.helmWing2.field_78809_i = true;
        this.helmWing2.func_78793_a(-4.0f, -4.0f, -1.0f);
        this.helmWing2.func_78790_a(-0.5f, 0.0f, 0.0f, 1, 3, 4, s);
        this.setRotateAngle(this.helmWing2, -0.2617994f, -0.2617994f, 0.2617994f);
        this.bootL1 = new ModelRenderer((ModelBase)this, 12, 79);
        this.bootL1.field_78809_i = true;
        this.bootL1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bootL1.func_78790_a(-2.0f, 7.0f, -2.0f, 4, 1, 4, s);
        this.armR = new ModelRenderer((ModelBase)this, 0, 79);
        this.armR.func_78793_a(-5.0f, 2.0f, 0.0f);
        this.armR.func_78790_a(-3.5f, 6.0f, -2.0f, 2, 4, 4, s);
        this.setRotateAngle(this.armR, 0.0f, 0.0f, 0.0f);
        this.bootR1 = new ModelRenderer((ModelBase)this, 12, 79);
        this.bootR1.func_78793_a(0.0f, 0.0f, 0.0f);
        this.bootR1.func_78790_a(-2.0f, 7.0f, -2.0f, 4, 1, 4, s);
        this.bootR = new ModelRenderer((ModelBase)this, 12, 79);
        this.bootR.func_78793_a(-1.9f, 12.0f, 0.0f);
        this.bootR.func_78790_a(-2.0f, 8.0f, -3.0f, 4, 4, 5, s);
        this.setRotateAngle(this.bootR, 0.0f, 0.0f, 0.0f);
        this.wing2_1 = new ModelRenderer((ModelBase)this, 56, 42);
        this.wing2_1.func_78793_a(4.5f, 0.0f, 0.0f);
        this.wing2_1.func_78790_a(0.0f, 0.0f, -0.5f, 0, 2, 3, s);
        this.setRotateAngle(this.wing2_1, 0.08726646f, 0.7853982f, 0.2617994f);
        this.wing2_2 = new ModelRenderer((ModelBase)this, 56, 44);
        this.wing2_2.field_78809_i = true;
        this.wing2_2.func_78793_a(-2.5f, 9.0f, 0.0f);
        this.wing2_2.func_78790_a(0.5f, 0.0f, 0.0f, 0, 1, 2, s);
        this.setRotateAngle(this.wing2_2, 0.08726646f, -0.7853982f, -0.2617994f);
        this.bootL = new ModelRenderer((ModelBase)this, 12, 79);
        this.bootL.field_78809_i = true;
        this.bootL.func_78793_a(1.9f, 12.0f, 0.0f);
        this.bootL.func_78790_a(-2.0f, 8.0f, -3.0f, 4, 4, 5, s);
        this.setRotateAngle(this.bootL, 0.0f, 0.0f, 0.0f);
        this.body = new ModelRenderer((ModelBase)this, 0, 44);
        this.body.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body.func_78790_a(-4.5f, 0.0f, -4.0f, 9, 5, 7, s);
        this.setRotateAngle(this.body, 0.08726646f, 0.0f, 0.0f);
        this.belt = new ModelRenderer((ModelBase)this, 22, 56);
        this.belt.func_78793_a(0.0f, 0.0f, 0.0f);
        this.belt.func_78790_a(-4.5f, 9.5f, -3.0f, 9, 3, 5, s);
        this.helm = new ModelRenderer((ModelBase)this, 0, 32);
        this.helm.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm.func_78790_a(-4.0f, -8.0f, -4.5f, 8, 3, 9, s);
        this.setRotateAngle(this.helm, 0.08726646f, 0.0f, 0.0f);
        this.helmWing4 = new ModelRenderer((ModelBase)this, 46, 45);
        this.helmWing4.func_78793_a(4.0f, -4.0f, -1.0f);
        this.helmWing4.func_78790_a(-0.5f, 0.0f, 0.0f, 1, 3, 4, s);
        this.setRotateAngle(this.helmWing4, -0.2617994f, 0.2617994f, -0.2617994f);
        this.armLpauldron = new ModelRenderer((ModelBase)this, 0, 67);
        this.armLpauldron.field_78809_i = true;
        this.armLpauldron.func_78793_a(0.0f, 0.0f, -0.0f);
        this.armLpauldron.func_78790_a(-1.0f, -2.5f, -3.0f, 5, 6, 6, s);
        this.wing1_1 = new ModelRenderer((ModelBase)this, 56, 41);
        this.wing1_1.func_78793_a(4.5f, 0.0f, 0.0f);
        this.wing1_1.func_78790_a(0.0f, -3.0f, -0.5f, 0, 3, 4, s);
        this.setRotateAngle(this.wing1_1, 0.2617994f, 0.7853982f, 0.2617994f);
        this.helm2 = new ModelRenderer((ModelBase)this, 50, 32);
        this.helm2.field_78809_i = true;
        this.helm2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.helm2.func_78790_a(3.0f, -5.0f, -4.5f, 1, 5, 4, s);
        this.wing2_3 = new ModelRenderer((ModelBase)this, 56, 44);
        this.wing2_3.func_78793_a(2.5f, 9.0f, 0.0f);
        this.wing2_3.func_78790_a(0.0f, 0.0f, -0.5f, 0, 1, 2, s);
        this.setRotateAngle(this.wing2_3, 0.08726646f, 0.7853982f, 0.2617994f);
        this.wing1 = new ModelRenderer((ModelBase)this, 56, 41);
        this.wing1.field_78809_i = true;
        this.wing1.func_78793_a(-4.5f, 0.0f, 0.0f);
        this.wing1.func_78790_a(0.5f, -3.0f, 0.0f, 0, 3, 4, s);
        this.setRotateAngle(this.wing1, 0.2617994f, -0.7853982f, -0.2617994f);
        this.body2 = new ModelRenderer((ModelBase)this, 0, 56);
        this.body2.func_78793_a(0.0f, 0.0f, 0.0f);
        this.body2.func_78790_a(-3.0f, 4.0f, -3.0f, 6, 6, 5, s);
        this.setRotateAngle(this.body2, -0.08726646f, 0.0f, 0.0f);
        this.helmWing3 = new ModelRenderer((ModelBase)this, 32, 45);
        this.helmWing3.func_78793_a(4.0f, -4.0f, -1.0f);
        this.helmWing3.func_78790_a(-0.5f, -5.0f, 0.0f, 1, 5, 6, s);
        this.setRotateAngle(this.helmWing3, 0.2617994f, 0.5235988f, 0.08726646f);
        this.helmWing1 = new ModelRenderer((ModelBase)this, 32, 45);
        this.helmWing1.field_78809_i = true;
        this.helmWing1.func_78793_a(-4.0f, -4.0f, -1.0f);
        this.helmWing1.func_78790_a(-0.5f, -5.0f, 0.0f, 1, 5, 6, s);
        this.setRotateAngle(this.helmWing1, 0.2617994f, -0.5235988f, -0.08726646f);
        this.wing2 = new ModelRenderer((ModelBase)this, 56, 42);
        this.wing2.field_78809_i = true;
        this.wing2.func_78793_a(-4.5f, 0.0f, 0.0f);
        this.wing2.func_78790_a(0.5f, 0.0f, 0.0f, 0, 2, 3, s);
        this.setRotateAngle(this.wing2, 0.08726646f, -0.7853982f, -0.2617994f);
        this.wing1_3 = new ModelRenderer((ModelBase)this, 56, 43);
        this.wing1_3.func_78793_a(2.5f, 9.0f, 0.0f);
        this.wing1_3.func_78790_a(0.0f, -2.0f, -0.5f, 0, 2, 3, s);
        this.setRotateAngle(this.wing1_3, 0.2617994f, 0.7853982f, 0.2617994f);
        this.helm.func_78792_a(this.fairy);
        this.helm.func_78792_a(this.helm3);
        this.bootR.func_78792_a(this.wing1_2);
        this.helm.func_78792_a(this.helm1);
        this.belt.func_78792_a(this.legL);
        this.armR.func_78792_a(this.armRpauldron);
        this.belt.func_78792_a(this.legR);
        this.helm.func_78792_a(this.helmWing2);
        this.bootL.func_78792_a(this.bootL1);
        this.bootR.func_78792_a(this.bootR1);
        this.armLpauldron.func_78792_a(this.wing2_1);
        this.bootR.func_78792_a(this.wing2_2);
        this.helm.func_78792_a(this.helmWing4);
        this.armL.func_78792_a(this.armLpauldron);
        this.armLpauldron.func_78792_a(this.wing1_1);
        this.helm.func_78792_a(this.helm2);
        this.bootL.func_78792_a(this.wing2_3);
        this.armRpauldron.func_78792_a(this.wing1);
        this.body.func_78792_a(this.body2);
        this.helm.func_78792_a(this.helmWing3);
        this.helm.func_78792_a(this.helmWing1);
        this.armRpauldron.func_78792_a(this.wing2);
        this.bootL.func_78792_a(this.wing1_3);
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

