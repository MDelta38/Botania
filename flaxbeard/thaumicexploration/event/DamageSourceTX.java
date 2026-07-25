/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSource
 */
package flaxbeard.thaumicexploration.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;

public class DamageSourceTX
extends DamageSource {
    public static DamageSource soulCrucible = new DamageSourceTX("soulCrucible").func_76348_h();
    public static DamageSource noTaint = new DamageSourceTX("noTaint").func_76348_h().func_82726_p();

    public DamageSourceTX(String par1Str) {
        super(par1Str);
    }

    public static DamageSource witherPlayerDamage(EntityLivingBase par0EntityLiving) {
        return new EntityDamageSource("witherMask", (Entity)par0EntityLiving);
    }
}

