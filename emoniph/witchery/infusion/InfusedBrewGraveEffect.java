/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.MovingObjectPosition$MovingObjectType
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.infusion;

import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.infusion.InfusedBrewEffect;
import com.emoniph.witchery.infusion.Infusion;
import com.emoniph.witchery.util.CreatureUtil;
import com.emoniph.witchery.util.TimeUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class InfusedBrewGraveEffect
extends InfusedBrewEffect {
    private static final String LAST_USE_TIME_KEY = "WITCBrewGraveTime";
    private static final long COOLDOWN_TICKS = 200L;

    public InfusedBrewGraveEffect(int id, long durationMS) {
        super(id, durationMS, 16, 16);
    }

    @Override
    public void immediateEffect(World world, EntityPlayer player, ItemStack stack) {
    }

    @Override
    public void regularEffect(World world, EntityPlayer player) {
    }

    @Override
    public boolean tryUseEffect(EntityPlayer player, MovingObjectPosition mop) {
        if (this.isActive(player)) {
            EntityLiving living;
            NBTTagCompound nbtPlayer = Infusion.getNBT((Entity)player);
            long lastUseTicks = nbtPlayer.func_74763_f(LAST_USE_TIME_KEY);
            long currentServerTime = TimeUtil.getServerTimeInTicks();
            if (currentServerTime - lastUseTicks > 200L && mop != null && mop.field_72313_a == MovingObjectPosition.MovingObjectType.ENTITY && CreatureUtil.isUndead(mop.field_72308_g) && !PotionEnslaved.isMobEnslavedBy(living = (EntityLiving)mop.field_72308_g, player)) {
                PotionEnslaved.setEnslaverForMob(living, player);
                nbtPlayer.func_74772_a(LAST_USE_TIME_KEY, currentServerTime);
                return true;
            }
        }
        return false;
    }
}

