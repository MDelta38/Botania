/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class CreaturePowerZombie
extends CreaturePower {
    public CreaturePowerZombie(int powerID) {
        super(powerID, EntityZombie.class);
    }

    @Override
    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        if (!world.field_72995_K) {
            player.func_70690_d(new PotionEffect(Potion.field_76429_m.field_76415_H, 600, 1));
            player.func_70690_d(new PotionEffect(Potion.field_76420_g.field_76415_H, 600, 0));
        }
    }
}

