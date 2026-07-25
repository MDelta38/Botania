/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 */
package thaumcraft.common.entities.monster.mods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;

public class ChampionModSickly
implements IChampionModifierEffect {
    @Override
    public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
        if (boss.field_70170_p.field_73012_v.nextFloat() < 0.4f) {
            target.func_70690_d(new PotionEffect(Potion.field_76438_s.field_76415_H, 500));
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
        Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + (double)w, boss.field_70121_D.field_72338_b + (double)h, boss.field_70121_D.field_72339_c + (double)d, 0.0, -0.02, 0.0, 0.2f, 0.6f + boss.field_70170_p.field_73012_v.nextFloat() * 0.1f, 0.2f + boss.field_70170_p.field_73012_v.nextFloat() * 0.1f, 0.5f, false, 1, 4, 2, 5 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.9f + boss.field_70170_p.field_73012_v.nextFloat() * 0.3f);
    }
}

