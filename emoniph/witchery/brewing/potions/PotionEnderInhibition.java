/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.IHandleEnderTeleport;
import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class PotionEnderInhibition
extends PotionBase
implements IHandleEnderTeleport {
    public PotionEnderInhibition(int id, int color) {
        super(id, true, color);
    }

    @Override
    public void onEnderTeleport(World world, EntityLivingBase entity, EnderTeleportEvent event, int amplifier) {
        event.setCanceled(true);
    }

    public static boolean isActive(Entity entity, int amplifier) {
        if (entity != null && entity instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase)entity;
            return living.func_70644_a(Witchery.Potions.ENDER_INHIBITION) && living.func_70660_b(Witchery.Potions.ENDER_INHIBITION).func_76458_c() >= amplifier;
        }
        return false;
    }
}

