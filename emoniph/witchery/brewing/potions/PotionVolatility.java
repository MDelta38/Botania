/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingHurt;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.util.BlockProtect;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.util.Coord;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionVolatility
extends PotionBase
implements IHandleLivingHurt {
    public PotionVolatility(int id, int color) {
        super(id, true, color);
        this.setIncurable();
    }

    @Override
    public boolean handleAllHurtEvents() {
        return false;
    }

    @Override
    public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier) {
        if (!world.field_72995_K && this.isExplodableDamage(event.source)) {
            boolean breakable = Config.instance().allowVolatilityPotionBlockDamage;
            if (breakable) {
                Coord c = new Coord((Entity)entity);
                breakable = BlockProtect.checkModsForBreakOK(world, c.x, c.y, c.z, entity);
            }
            if (event.source.func_94541_c() || world.field_73012_v.nextInt(5 - Math.min(amplifier, 3)) == 0) {
                if (world.field_73012_v.nextInt(amplifier + 3) == 0) {
                    entity.func_82170_o(this.field_76415_H);
                }
                world.func_72876_a((Entity)(event.source.func_94541_c() ? entity : null), entity.field_70165_t, entity.field_70163_u, entity.field_70161_v, Math.min(2.0f + 0.5f * (float)amplifier, 3.0f), breakable);
            }
        }
    }

    private boolean isExplodableDamage(DamageSource source) {
        return source != DamageSource.field_76369_e && source != DamageSource.field_76368_d && source != DamageSource.field_76379_h && source != DamageSource.field_76380_i && source != DamageSource.field_76366_f;
    }
}

