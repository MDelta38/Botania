/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 */
package thaumcraft.common.entities.monster.mods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.fx.particles.FXSpark;
import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;

public class ChampionModBold
implements IChampionModifierEffect {
    @Override
    public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float ammount) {
        return 0.0f;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void showFX(EntityLivingBase boss) {
        if (boss.field_70170_p.field_73012_v.nextBoolean()) {
            return;
        }
        float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
        float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
        float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O / 3.0f;
        FXSpark ef = new FXSpark(boss.field_70170_p, boss.field_70121_D.field_72340_a + (double)w, boss.field_70121_D.field_72338_b + (double)h, boss.field_70121_D.field_72339_c + (double)d, 0.2f);
        ef.func_70538_b(0.3f - boss.field_70170_p.field_73012_v.nextFloat() * 0.1f, 0.0f, 0.8f + boss.field_70170_p.field_73012_v.nextFloat() * 0.2f);
        ParticleEngine.instance.addEffect(boss.field_70170_p, ef);
    }
}

