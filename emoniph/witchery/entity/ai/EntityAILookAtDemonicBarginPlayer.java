/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.emoniph.witchery.entity.ai;

import com.emoniph.witchery.entity.EntityDemon;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAILookAtDemonicBarginPlayer
extends EntityAIWatchClosest {
    private final EntityDemon theMerchant;

    public EntityAILookAtDemonicBarginPlayer(EntityDemon trader) {
        super((EntityLiving)trader, EntityPlayer.class, 8.0f);
        this.theMerchant = trader;
    }

    public boolean func_75250_a() {
        if (this.theMerchant.isTrading()) {
            this.field_75334_a = this.theMerchant.func_70931_l_();
            return true;
        }
        return false;
    }
}

