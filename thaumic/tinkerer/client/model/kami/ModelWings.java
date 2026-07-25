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
package thaumic.tinkerer.client.model.kami;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public class ModelWings
extends ModelBiped {
    ModelRenderer Wing1;
    ModelRenderer Wing2;

    public ModelWings() {
        super(1.0f);
        this.field_78090_t = 64;
        this.field_78089_u = 32;
        this.Wing1 = new ModelRenderer((ModelBase)this, 16, -12);
        this.Wing1.func_78789_a(0.0f, 0.0f, 0.0f, 0, 7, 12);
        this.Wing1.func_78793_a(-2.0f, 1.0f, 2.0f);
        this.setRotation(this.Wing1, 0.0f, -0.6108652f, 0.0f);
        this.field_78115_e.func_78792_a(this.Wing1);
        this.Wing2 = new ModelRenderer((ModelBase)this, 16, -12);
        this.Wing2.func_78789_a(0.1f, 0.0f, 0.0f, 0, 7, 12);
        this.Wing2.func_78793_a(2.0f, 1.0f, 2.0f);
        this.setRotation(this.Wing2, 0.0f, 0.4468043f, 0.0f);
        this.field_78115_e.func_78792_a(this.Wing2);
    }

    public void func_78088_a(Entity entity, float v1, float v2, float v3, float v4, float v5, float v6) {
        this.func_78087_a(v1, v2, v3, v4, v5, v6, entity);
        this.field_78116_c.field_78806_j = false;
        this.field_78114_d.field_78806_j = false;
        this.field_78124_i.field_78806_j = false;
        this.field_78123_h.field_78806_j = false;
        super.func_78088_a(entity, v1, v2, v3, v4, v5, v6);
    }

    public void func_78087_a(float v1, float v2, float v3, float v4, float v5, float v6, Entity entity) {
        EntityLivingBase living = (EntityLivingBase)entity;
        boolean bl = this.field_78117_n = living != null && living.func_70093_af();
        if (living != null && living instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)living;
            ItemStack itemstack = player.field_71071_by.func_70448_g();
            int n = this.field_78120_m = itemstack != null ? 1 : 0;
            if (itemstack != null && player.func_71052_bv() > 0) {
                EnumAction enumaction = itemstack.func_77975_n();
                if (enumaction == EnumAction.block) {
                    this.field_78120_m = 3;
                } else if (enumaction == EnumAction.bow) {
                    this.field_78118_o = true;
                }
            }
            if (player.field_71075_bZ.field_75100_b) {
                this.Wing1.field_78796_g = (float)((Math.sin(entity.field_70173_aa) + 1.0) * (Math.PI / 180) * 15.0 - 0.6108651757240295);
                this.Wing2.field_78796_g = -this.Wing1.field_78796_g;
            }
        }
        super.func_78087_a(v1, v2, v3, v4, v5, v6, entity);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.field_78795_f = x;
        model.field_78796_g = y;
        model.field_78808_h = z;
    }
}

