/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class CreaturePowerHeal
extends CreaturePower {
    public static final int DEFAULT_CHARGES_PER_SACRIFICE = 1;
    private final int charges;

    public CreaturePowerHeal(int powerID, Class<? extends EntityLiving> creatureType, int charges) {
        super(powerID, creatureType);
        this.charges = charges;
    }

    @Override
    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        player.func_70690_d(new PotionEffect(Potion.field_76432_h.field_76415_H, 10, 0));
        SoundEffect.RANDOM_FIZZ.playAtPlayer(world, player);
    }

    @Override
    public int getChargesPerSacrifice() {
        return this.charges;
    }
}

