/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.World
 *  net.minecraftforge.event.entity.player.PlayerDropsEvent
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.brewing.potions.PotionBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

public interface IHandlePlayerDrops {
    public PotionBase getPotion();

    public void onPlayerDrops(World var1, EntityPlayer var2, PlayerDropsEvent var3, int var4);
}

