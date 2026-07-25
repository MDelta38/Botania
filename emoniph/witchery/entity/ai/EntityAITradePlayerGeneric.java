/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.IMerchant
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 */
package com.emoniph.witchery.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class EntityAITradePlayerGeneric
extends EntityAIBase {
    private IMerchant merchant;
    private EntityLiving entity;

    public EntityAITradePlayerGeneric(IMerchant merchant, EntityLiving entity) {
        this.merchant = merchant;
        this.entity = entity;
        this.func_75248_a(5);
    }

    public boolean func_75250_a() {
        if (!this.entity.func_70089_S()) {
            return false;
        }
        if (this.entity.func_70090_H()) {
            return false;
        }
        if (!this.entity.field_70122_E) {
            return false;
        }
        if (this.entity.field_70133_I) {
            return false;
        }
        EntityPlayer entityplayer = this.merchant.func_70931_l_();
        return entityplayer == null ? false : (this.entity.func_70068_e((Entity)entityplayer) > 16.0 ? false : entityplayer.field_71070_bA instanceof Container);
    }

    public void func_75249_e() {
        this.entity.func_70661_as().func_75499_g();
    }

    public void func_75251_c() {
        this.merchant.func_70932_a_((EntityPlayer)null);
    }
}

