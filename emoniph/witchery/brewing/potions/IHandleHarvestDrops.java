/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.event.world.BlockEvent$HarvestDropsEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;

public interface IHandleHarvestDrops {
    public PotionBase getPotion();

    public void onHarvestDrops(World var1, EntityPlayer var2, BlockEvent.HarvestDropsEvent var3, int var4);
}

