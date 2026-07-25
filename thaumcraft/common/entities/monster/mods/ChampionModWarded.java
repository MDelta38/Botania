/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.util.DamageSource
 */
package thaumcraft.common.entities.monster.mods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;

public class ChampionModWarded
implements IChampionModifierEffect {
    @Override
    public float performEffect(EntityLivingBase mob, EntityLivingBase target, DamageSource source, float amount) {
        if (mob.field_70172_ad <= 0 && mob.field_70173_aa % 25 == 0) {
            int bh = (int)mob.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111125_b() / 2;
            if (mob.func_110139_bj() < (float)bh) {
                mob.func_110149_m(mob.func_110139_bj() + 1.0f);
            }
        }
        return amount;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void showFX(EntityLivingBase boss) {
        if (boss.field_70170_p.field_73012_v.nextBoolean()) {
            return;
        }
        float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
        float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
        float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
        Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + (double)w, boss.field_70121_D.field_72338_b + (double)h, boss.field_70121_D.field_72339_c + (double)d, 0.0, 0.0, 0.0, 0.5f + boss.field_70170_p.field_73012_v.nextFloat() * 0.1f, 0.5f + boss.field_70170_p.field_73012_v.nextFloat() * 0.1f, 0.5f + boss.field_70170_p.field_73012_v.nextFloat() * 0.1f, 0.6f, true, 21, 4, 1, 4 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.8f + boss.field_70170_p.field_73012_v.nextFloat() * 0.3f);
    }
}

