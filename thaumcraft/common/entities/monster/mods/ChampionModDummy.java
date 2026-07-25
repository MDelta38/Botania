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
import thaumcraft.common.entities.monster.mods.IChampionModifierEffect;

public class ChampionModDummy
implements IChampionModifierEffect {
    @Override
    public float performEffect(EntityLivingBase boss, EntityLivingBase target, DamageSource source, float amount) {
        return amount;
    }

    @Override
    public void showFX(EntityLivingBase boss) {
    }
}

