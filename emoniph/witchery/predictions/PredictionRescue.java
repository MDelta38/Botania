/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.predictions;

import com.emoniph.witchery.entity.EntityOwl;
import com.emoniph.witchery.predictions.PredictionAlwaysForced;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PredictionRescue
extends PredictionAlwaysForced {
    private final Class<? extends EntityLiving> entityClass;

    public PredictionRescue(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, int regularFulfillmentDurationInTicks, double regularFulfillmentProbability, Class<? extends EntityLiving> entityClass) {
        super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey, regularFulfillmentDurationInTicks, regularFulfillmentProbability);
        this.entityClass = entityClass;
    }

    @Override
    public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld) {
        Entity attackingEntity;
        if (!event.isCanceled() && (attackingEntity = event.source.func_76346_g()) != null && attackingEntity instanceof EntityLivingBase) {
            try {
                int x = MathHelper.func_76128_c((double)player.field_70165_t);
                int y = MathHelper.func_76128_c((double)player.field_70163_u);
                int z = MathHelper.func_76128_c((double)player.field_70161_v);
                if (!world.field_72995_K) {
                    int hy;
                    int ny;
                    int MAX_DISTANCE = 4;
                    int MIN_DISTANCE = 2;
                    int activeRadius = 2;
                    int ax = world.field_73012_v.nextInt(activeRadius * 2 + 1);
                    if (ax > activeRadius) {
                        ax += 4;
                    }
                    int nx = x - 4 + ax;
                    int az = world.field_73012_v.nextInt(activeRadius * 2 + 1);
                    if (az > activeRadius) {
                        az += 4;
                    }
                    int nz = z - 4 + az;
                    for (ny = y; !world.func_147437_c(nx, ny, nz) && ny < y + 8; ++ny) {
                    }
                    while (world.func_147437_c(nx, ny, nz) && ny > 0) {
                        --ny;
                    }
                    for (hy = 0; world.func_147437_c(nx, ny + hy + 1, nz) && hy < 6; ++hy) {
                    }
                    Constructor<? extends EntityLiving> ctor = this.entityClass.getConstructor(World.class);
                    EntityLiving entity = ctor.newInstance(world);
                    if ((float)hy >= entity.field_70131_O) {
                        entity.func_70012_b(0.5 + (double)nx, 0.05 + (double)ny + 1.0, 0.5 + (double)nz, 0.0f, 0.0f);
                        world.func_72838_d((Entity)entity);
                        IEntityLivingData entitylivingData = null;
                        entitylivingData = entity.func_110161_a(entitylivingData);
                        if (entity instanceof EntityOwl) {
                            ((EntityOwl)entity).setTimeToLive(300);
                        }
                        entity.func_70624_b((EntityLivingBase)attackingEntity);
                        if (entity instanceof EntityCreature) {
                            ((EntityCreature)entity).func_70784_b((Entity)((EntityLivingBase)attackingEntity));
                            ((EntityCreature)entity).func_70604_c((EntityLivingBase)attackingEntity);
                        }
                        ParticleEffect.SMOKE.send(SoundEffect.NONE, (Entity)entity, 0.5, 2.0, 16);
                        return true;
                    }
                    return false;
                }
            }
            catch (NoSuchMethodException ex) {
            }
            catch (InvocationTargetException ex) {
            }
            catch (InstantiationException ex) {
            }
            catch (IllegalAccessException ex) {
                // empty catch block
            }
            Log.instance().debug(String.format("Prediction for rescue by fulfilled as predicted", attackingEntity.toString()));
            return false;
        }
        return false;
    }
}

