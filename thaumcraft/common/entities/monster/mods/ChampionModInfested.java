/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 */
package thaumcraft.common.entities.monster.mods;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.EntityTaintSpider;
import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;

public class ChampionModInfested
implements IChampionModifierEffect {
    @Override
    public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
        if (boss.field_70170_p.field_73012_v.nextFloat() < 0.4f && !boss.field_70170_p.field_72995_K) {
            EntityTaintSpider spiderling = new EntityTaintSpider(boss.field_70170_p);
            spiderling.func_70012_b(boss.field_70165_t, boss.field_70163_u + (double)(boss.field_70131_O / 2.0f), boss.field_70161_v, boss.field_70170_p.field_73012_v.nextFloat() * 360.0f, 0.0f);
            boss.field_70170_p.func_72838_d((Entity)spiderling);
            boss.func_85030_a("thaumcraft:gore", 0.5f, 1.0f);
        }
        return amount;
    }

    @Override
    @SideOnly(value=Side.CLIENT)
    public void showFX(EntityLivingBase boss) {
        if (boss.field_70170_p.field_73012_v.nextBoolean()) {
            Thaumcraft.proxy.slimeJumpFX((Entity)boss, 0);
        }
    }
}

