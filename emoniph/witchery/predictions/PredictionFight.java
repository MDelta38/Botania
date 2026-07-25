/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.predictions;

import com.emoniph.witchery.entity.EntityDemon;
import com.emoniph.witchery.entity.IOwnable;
import com.emoniph.witchery.predictions.Prediction;
import com.emoniph.witchery.util.Log;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TameableUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class PredictionFight
extends Prediction {
    private final Class<? extends EntityLiving> entityClass;
    private final boolean bindTameable;

    public PredictionFight(int id, int itemWeight, double selfFulfillmentProbabilityPerSec, String translationKey, Class<? extends EntityLiving> entityClass, boolean bindTameable) {
        super(id, itemWeight, selfFulfillmentProbabilityPerSec, translationKey);
        this.entityClass = entityClass;
        this.bindTameable = bindTameable;
    }

    @Override
    public boolean doSelfFulfillment(World world, EntityPlayer player) {
        block17: {
            try {
                int hy;
                int ny;
                int x = MathHelper.func_76128_c((double)player.field_70165_t);
                int y = MathHelper.func_76128_c((double)player.field_70163_u);
                int z = MathHelper.func_76128_c((double)player.field_70161_v);
                if (world.field_72995_K) break block17;
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
                    boolean bound = false;
                    if (entity instanceof EntityDemon) {
                        ((EntityDemon)entity).setPlayerCreated(true);
                    } else if (this.bindTameable && entity instanceof EntityTameable) {
                        ((EntityTameable)entity).func_70903_f(true);
                        TameableUtil.setOwner((EntityTameable)entity, player);
                        bound = true;
                    } else if (this.bindTameable && entity instanceof IOwnable) {
                        ((IOwnable)entity).setOwner(player.func_70005_c_());
                        bound = true;
                    }
                    entity.func_70012_b(0.5 + (double)nx, 0.05 + (double)ny + 1.0, 0.5 + (double)nz, 0.0f, 0.0f);
                    world.func_72838_d((Entity)entity);
                    Log.instance().debug(String.format("Forcing prediction for attack by %s", entity.toString()));
                    IEntityLivingData entitylivingData = null;
                    entitylivingData = entity.func_110161_a(entitylivingData);
                    if (!bound) {
                        entity.func_70624_b((EntityLivingBase)player);
                    }
                    ParticleEffect.SMOKE.send(SoundEffect.NONE, (Entity)entity, 0.5, 2.0, 16);
                    break block17;
                }
                return false;
            }
            catch (NoSuchMethodException ex) {
            }
            catch (InvocationTargetException ex) {
            }
            catch (InstantiationException ex) {
            }
            catch (IllegalAccessException illegalAccessException) {
                // empty catch block
            }
        }
        return true;
    }

    @Override
    public boolean checkIfFulfilled(World world, EntityPlayer player, LivingHurtEvent event, boolean isPastDue, boolean veryOld) {
        Entity attackingEntity;
        if (!event.isCanceled() && (attackingEntity = event.source.func_76346_g()) != null) {
            boolean attackedByCreature = this.entityClass.isAssignableFrom(attackingEntity.getClass());
            if (attackedByCreature) {
                Log.instance().debug(String.format("Prediction for attack by %s fulfilled as predicted", attackingEntity.toString()));
            }
            return attackedByCreature;
        }
        return false;
    }
}

