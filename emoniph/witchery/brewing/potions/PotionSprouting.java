/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.action.effect.BrewActionSprouting;
import com.emoniph.witchery.brewing.potions.IHandleLivingUpdate;
import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PotionSprouting
extends PotionBase
implements IHandleLivingUpdate {
    public PotionSprouting(int id, int color) {
        super(id, color);
    }

    @Override
    public void onLivingUpdate(World world, EntityLivingBase entity, LivingEvent.LivingUpdateEvent event, int amplifier, int duration) {
        if (!world.field_72995_K && world.func_82737_E() % 20L == 9L && world.field_73012_v.nextInt(4) == 0) {
            BrewActionSprouting.growBranch(entity, 1 + amplifier);
        }
    }
}

