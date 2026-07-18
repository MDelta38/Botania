/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 */
package vazkii.botania.common.item.lens;

import java.util.List;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.common.item.lens.Lens;

public class LensDamage
extends Lens {
    @Override
    public void updateBurst(IManaBurst burst, EntityThrowable entity, ItemStack stack) {
        AxisAlignedBB axis = AxisAlignedBB.func_72330_a((double)entity.field_70165_t, (double)entity.field_70163_u, (double)entity.field_70161_v, (double)entity.field_70142_S, (double)entity.field_70137_T, (double)entity.field_70136_U).func_72314_b(1.0, 1.0, 1.0);
        List entities = entity.field_70170_p.func_72872_a(EntityLivingBase.class, axis);
        for (EntityLivingBase living : entities) {
            int mana;
            if (living instanceof EntityPlayer || living.field_70737_aN != 0 || (mana = burst.getMana()) < 16) continue;
            burst.setMana(mana - 16);
            if (burst.isFake() || entity.field_70170_p.field_72995_K) break;
            living.func_70097_a(DamageSource.field_76376_m, 8.0f);
            break;
        }
    }
}

