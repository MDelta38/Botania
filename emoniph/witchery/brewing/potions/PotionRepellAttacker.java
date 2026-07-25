/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.IHandleLivingHurt;
import com.emoniph.witchery.brewing.potions.PotionBase;
import com.emoniph.witchery.util.EntityPosition;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PotionRepellAttacker
extends PotionBase
implements IHandleLivingHurt {
    public PotionRepellAttacker(int id, int color) {
        super(id, color);
    }

    @Override
    public boolean handleAllHurtEvents() {
        return false;
    }

    @Override
    public void onLivingHurt(World world, EntityLivingBase entity, LivingHurtEvent event, int amplifier) {
        if (!world.field_72995_K) {
            EntityLivingBase attacker = event.source.func_76346_g() != null && event.source.func_76346_g() instanceof EntityLivingBase ? (EntityLivingBase)event.source.func_76346_g() : null;
            double MAX_RANGE = 3.0;
            double MAX_RANGE_SQ = 9.0;
            if (attacker != null && attacker != entity && !event.source.func_76352_a() && attacker.func_70068_e((Entity)entity) < 9.0) {
                EntityUtil.pushback(world, (Entity)attacker, new EntityPosition((Entity)entity), 1.0 + (double)amplifier * 0.75, 0.5 + (double)amplifier * 0.2);
            }
        }
    }
}

