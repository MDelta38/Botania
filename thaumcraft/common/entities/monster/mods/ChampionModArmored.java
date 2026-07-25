/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 */
package thaumcraft.common.entities.monster.mods;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;

public class ChampionModArmored
implements IChampionModifierEffect {
    @Override
    public float performEffect(EntityLivingBase mob, EntityLivingBase target, DamageSource source, float amount) {
        if (!source.func_76363_c()) {
            float f1 = amount * 19.0f;
            amount = f1 / 25.0f;
        }
        return amount;
    }

    @Override
    public void showFX(EntityLivingBase boss) {
        if (boss.field_70170_p.field_73012_v.nextInt(4) != 0) {
            return;
        }
        float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
        float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
        float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
        Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + (double)w, boss.field_70121_D.field_72338_b + (double)h, boss.field_70121_D.field_72339_c + (double)d, 0.0, 0.0, 0.0, 0.9f, 0.9f, 0.9f + boss.field_70170_p.field_73012_v.nextFloat() * 0.1f, 0.7f, false, 112, 9, 1, 5 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.6f + boss.field_70170_p.field_73012_v.nextFloat() * 0.2f);
    }
}

