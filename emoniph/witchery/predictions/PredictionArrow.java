/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.predictions;

import com.emoniph.witchery.predictions.PredictionFight;
import com.emoniph.witchery.util.Log;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PredictionArrow
extends PredictionFight {
    public PredictionArrow(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey) {
        super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, EntitySkeleton.class, false);
    }

    @Override
    public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld) {
        if (!event.isCanceled()) {
            boolean hitByArrow;
            boolean bl = hitByArrow = event.source.field_76373_n == "arrow";
            if (hitByArrow) {
                Log.instance().debug(String.format("Prediction for hit by arrow came true", new Object[0]));
            }
            return hitByArrow;
        }
        return false;
    }
}

