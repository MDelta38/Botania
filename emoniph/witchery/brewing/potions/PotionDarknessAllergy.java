/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;

public class PotionDarknessAllergy
extends PotionBase {
    public PotionDarknessAllergy(int id, int color) {
        super(id, true, color);
        this.setIncurable();
    }

    public boolean func_76397_a(int duration, int amplifier) {
        return duration % 20 == 4;
    }

    public void func_76394_a(EntityLivingBase entity, int amplifier) {
        int z;
        int y;
        int x = MathHelper.func_76128_c((double)entity.field_70165_t);
        int lightLevel = entity.field_70170_p.func_72957_l(x, y = MathHelper.func_76128_c((double)entity.field_70163_u), z = MathHelper.func_76128_c((double)entity.field_70161_v));
        if (lightLevel < 2 + amplifier * 2) {
            entity.func_70097_a(DamageSource.field_76380_i, 1.0f);
        }
    }
}

