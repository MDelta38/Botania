/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingHurtEvent
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;

public class CreaturePowerPigMan
extends CreaturePower {
    public CreaturePowerPigMan(int powerID) {
        super(powerID, EntityPigZombie.class);
    }

    @Override
    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        if (!world.field_72995_K) {
            player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 600, 2));
            player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 600, 2));
            SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
        }
    }

    @Override
    public void onDamage(World world, EntityPlayer player, LivingHurtEvent event) {
        int currentEnergy;
        if (event.source.func_76347_k() && event.isCancelable() && (currentEnergy = Infusion.getCurrentEnergy(player)) >= 3) {
            Infusion.setCurrentEnergy(player, currentEnergy - 3);
            player.func_70690_d(new PotionEffect(Potion.field_76426_n.field_76415_H, 1200, 0));
            SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
            event.setCanceled(true);
        }
    }
}

