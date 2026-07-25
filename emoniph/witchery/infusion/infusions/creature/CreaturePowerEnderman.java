/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.infusion.infusions.creature;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.infusion.infusions.InfusionOtherwhere;
import com.emoniph.witchery.infusion.infusions.creature.CreaturePower;
import com.emoniph.witchery.network.PacketParticles;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import com.emoniph.witchery.util.TargetPointUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class CreaturePowerEnderman
extends CreaturePower {
    public CreaturePowerEnderman(int powerID) {
        super(powerID, EntityEnderman.class);
    }

    @Override
    public void onActivate(World world, EntityPlayer player, int elapsedTicks, MovingObjectPosition mop) {
        if (!world.field_72995_K) {
            if (mop != null) {
                Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.PORTAL, SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)player, 0.5, 2.0), TargetPointUtil.from((Entity)player, 16.0));
                InfusionOtherwhere.teleportEntity(player, mop);
                Witchery.packetPipeline.sendToAllAround(new PacketParticles(ParticleEffect.PORTAL, SoundEffect.MOB_ENDERMEN_PORTAL, (Entity)player, 0.5, 2.0), TargetPointUtil.from((Entity)player, 16.0));
            } else {
                world.func_72956_a((Entity)player, "note.snare", 0.5f, 0.4f / ((float)Math.random() * 0.4f + 0.8f));
            }
        }
    }
}

