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
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;

public class ChampionModMighty
implements IChampionModifierEffect {
    @Override
    public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float ammount) {
        return 0.0f;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void showFX(EntityLivingBase boss) {
        if (boss.field_70170_p.field_73012_v.nextFloat() > 0.3f) {
            return;
        }
        float w = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
        float d = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70130_N;
        float h = boss.field_70170_p.field_73012_v.nextFloat() * boss.field_70131_O;
        int p = 176 + boss.field_70170_p.field_73012_v.nextInt(4) * 3;
        Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + (double)w, boss.field_70121_D.field_72338_b + (double)h, boss.field_70121_D.field_72339_c + (double)d, 0.0, 0.0, 0.0, 0.8f + boss.field_70170_p.field_73012_v.nextFloat() * 0.2f, 0.8f + boss.field_70170_p.field_73012_v.nextFloat() * 0.2f, 0.8f + boss.field_70170_p.field_73012_v.nextFloat() * 0.2f, 0.7f, false, p, 3, 1, 4 + boss.field_70170_p.field_73012_v.nextInt(3), 0, 1.0f + boss.field_70170_p.field_73012_v.nextFloat() * 0.3f);
    }
}

