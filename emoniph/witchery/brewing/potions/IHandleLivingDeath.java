/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingDeathEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public interface IHandleLivingDeath {
    public PotionBase getPotion();

    public void onLivingDeath(World var1, EntityLivingBase var2, LivingDeathEvent var3, int var4);
}

