/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingUpdateEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;

public interface IHandleLivingUpdate {
    public PotionBase getPotion();

    public void onLivingUpdate(World var1, EntityLivingBase var2, LivingEvent.LivingUpdateEvent var3, int var4, int var5);
}

