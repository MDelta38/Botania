/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.EntityLivingBase;

public class PotionFeatherFall
extends PotionBase {
    public PotionFeatherFall(int id, int color) {
        super(id, color);
    }

    public boolean func_76397_a(int duration, int amplifier) {
        return true;
    }

    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        int maxFallDistance;
        int activationDistance;
        int n = amplifier >= 2 ? 3 : (activationDistance = amplifier >= 1 ? 4 : 5);
        int n2 = amplifier >= 3 ? 3 : (amplifier >= 2 ? 4 : (maxFallDistance = amplifier >= 1 ? 5 : 6));
        if (entity.field_70143_R >= (float)activationDistance && entity.field_70181_x < -0.2) {
            entity.field_70181_x = -0.2;
            if (entity.field_70143_R > (float)maxFallDistance) {
                entity.field_70143_R = maxFallDistance;
            }
        }
    }
}

