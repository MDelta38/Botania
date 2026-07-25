/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingSetAttackTargetEvent;

public interface IHandleLivingSetAttackTarget {
    public PotionBase getPotion();

    public void onLivingSetAttackTarget(World var1, EntityLiving var2, LivingSetAttackTargetEvent var3, int var4);
}

