/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.DamageSource
 */
package thaumcraft.common.entities.monster.mods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;

public class ChampionModWarp
implements IChampionModifierEffect {
    @Override
    public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
        if (boss.field_70170_p.field_73012_v.nextFloat() < 0.33f && target instanceof EntityPlayer) {
            Thaumcraft.addWarpToPlayer((EntityPlayer)target, 1 + boss.field_70170_p.field_73012_v.nextInt(3), true);
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
        Thaumcraft.proxy.drawGenericParticles(boss.field_70170_p, boss.field_70121_D.field_72340_a + (double)w, boss.field_70121_D.field_72338_b + (double)h, boss.field_70121_D.field_72339_c + (double)d, 0.0, 0.0, 0.0, 0.8f + boss.field_70170_p.field_73012_v.nextFloat() * 0.2f, 0.0f, 0.9f + boss.field_70170_p.field_73012_v.nextFloat() * 0.1f, 0.7f, true, 72, 8, 1, 10 + boss.field_70170_p.field_73012_v.nextInt(4), 0, 0.6f + boss.field_70170_p.field_73012_v.nextFloat() * 0.4f);
    }
}

