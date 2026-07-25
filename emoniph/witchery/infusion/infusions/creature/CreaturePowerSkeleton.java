/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CreaturePowerSkeleton
extends CreaturePower {
    public CreaturePowerSkeleton(int powerID) {
        super(powerID, EntitySkeleton.class);
    }

    @Override
    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        if (!world.field_72995_K) {
            int j = elapsedTicks;
            float f = (float)j / 20.0f;
            if ((f = (f * f + f * 2.0f) / 3.0f) > 1.0f) {
                f = 1.0f;
            }
            world.func_72956_a((Entity)player, "random.bow", 1.0f, 1.0f / (world.field_73012_v.nextFloat() * 0.4f + 1.2f) + f * 0.5f);
            EntityArrow entityarrow = new EntityArrow(world, (EntityLivingBase)player, f * 2.0f);
            if (f == 1.0f) {
                entityarrow.func_70243_d(true);
            }
            boolean EXTRA_PUNCH = true;
            entityarrow.func_70240_a(1);
            boolean EXTRA_DAMAGE = true;
            entityarrow.func_70239_b(entityarrow.func_70242_d() + 0.5 + 0.5);
            world.func_72838_d((Entity)entityarrow);
        }
    }

    @Override
    public void onDamage(World world, EntityPlayer player, LivingHurtEvent event) {
        int currentEnergy;
        if (!world.field_72995_K && event.source == DamageSource.field_76369_e && (currentEnergy = Infusion.getCurrentEnergy(player)) >= 5) {
            Infusion.setCurrentEnergy(player, currentEnergy - 5);
            SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
            player.func_70690_d(new PotionEffect(Potion.field_76427_o.field_76415_H, 600, 0));
            player.func_70050_g(30);
            event.setCanceled(true);
        }
        super.onDamage(world, player, event);
    }
}

