/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package com.emoniph.witchery.predictions;

import com.emoniph.witchery.entity.ai.EntityAIMateWithPlayer;
import com.emoniph.witchery.predictions.PredictionAlwaysForced;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public class PredictionFallInLove
extends PredictionAlwaysForced {
    public PredictionFallInLove(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability) {
        super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
    }

    @Override
    public boolean checkIfFulfilled(World world, EntityPlayer player, LivingEvent.LivingUpdateEvent event, boolean isPastDue, boolean veryOld) {
        if (this.shouldWeActivate(world, player, isPastDue)) {
            int hy;
            int ny;
            int x = MathHelper.func_76128_c((double)player.field_70165_t);
            int y = MathHelper.func_76128_c((double)player.field_70163_u);
            int z = MathHelper.func_76128_c((double)player.field_70161_v);
            int MAX_DISTANCE = 6;
            int MIN_DISTANCE = 4;
            int activeRadius = 2;
            int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
            if (ax > activeRadius) {
                ax += 8;
            }
            int nx = x - 6 + ax;
            int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
            if (az > activeRadius) {
                az += 8;
            }
            int nz = z - 6 + az;
            for (ny = y; !world.func_147437_c(nx, ny, nz) && ny < y + 8; ++ny) {
            }
            while (world.func_147437_c(nx, ny, nz) && ny > 0) {
                --ny;
            }
            for (hy = 0; world.func_147437_c(nx, ny + hy + 1, nz) && hy < 6; ++hy) {
            }
            EntityVillager entity = new EntityVillager(world, 0);
            if ((float)hy >= entity.field_70131_O && world.func_147439_a(nx, ny - 1, nz).func_149721_r()) {
                entity.func_70012_b(0.5 + (double)nx, 0.05 + (double)ny + 1.0, 0.5 + (double)nz, 0.0f, 0.0f);
                world.func_72838_d((Entity)entity);
                Log.instance().debug(String.format("Forcing prediction for lover by %s", entity.toString()));
                EntityAIMateWithPlayer task = new EntityAIMateWithPlayer(entity);
                task.forceTask(player);
                entity.field_70714_bg.func_75776_a(1, (EntityAIBase)task);
                ParticleEffect.SMOKE.send(SoundEffect.NONE, (Entity)entity, 0.5, 2.0, 16);
                SoundEffect.WITCHERY_RANDOM_LOVED.playAtPlayer(world, player);
                return true;
            }
            return false;
        }
        return false;
    }
}

