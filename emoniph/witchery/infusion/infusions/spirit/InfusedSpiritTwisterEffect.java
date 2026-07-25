/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S08PacketPlayerPosLook
 *  net.minecraft.tileentity.TileEntity
 */
package com.emoniph.witchery.infusion.infusions.spirit;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.util.EntityUtil;
import java.util.ArrayList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S08PacketPlayerPosLook;
import net.minecraft.tileentity.TileEntity;

public class InfusedSpiritTwisterEffect
extends InfusedSpiritEffect {
    private static final double RANDOM_SPIN_RADIUS = 3.0;
    private static final double RANDOM_SPIN_RADIUS_SQ = 9.0;

    public InfusedSpiritTwisterEffect(int id, int spirits, int spectres, int banshees, int poltergeists) {
        super(id, "twister", spirits, spectres, banshees, poltergeists);
    }

    @Override
    public int getCooldownTicks() {
        return 10;
    }

    @Override
    public double getRadius() {
        return 8.0;
    }

    @Override
    public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities) {
        if (triggered) {
            for (EntityLivingBase entity : foundEntities) {
                EntityLiving creature;
                if (entity instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer)entity;
                    if (player.field_71071_by.func_70440_f(0) == null && player.field_71071_by.func_70440_f(1) == null && player.field_71071_by.func_70440_f(2) == null && player.field_71071_by.func_70440_f(3) == null && player.func_70694_bm() == null) continue;
                    double yawRadians = Math.atan2(player.field_70161_v - (0.5 + (double)tile.field_145849_e), player.field_70165_t - (0.5 + (double)tile.field_145851_c));
                    double yaw = Math.toDegrees(yawRadians) + 180.0;
                    double playerYaw = (player.field_70177_z + 90.0f) % 360.0f;
                    if (playerYaw < 0.0) {
                        playerYaw += 360.0;
                    }
                    float rev = ((float)yaw + 90.0f) % 360.0f;
                    double ARC = 45.0;
                    double diff = Math.abs(yaw - playerYaw);
                    if (!(360.0 - diff % 360.0 < 45.0) && !(diff % 360.0 < 45.0) || !(player instanceof EntityPlayerMP)) continue;
                    S08PacketPlayerPosLook packet = new S08PacketPlayerPosLook(player.field_70165_t, player.field_70163_u, player.field_70161_v, rev, player.field_70125_A, false);
                    Witchery.packetPipeline.sendTo((Packet)packet, player);
                    continue;
                }
                if (!(entity instanceof EntityLiving) || !((creature = (EntityLiving)entity).func_110138_aP() < 50.0f)) continue;
                EntityUtil.dropAttackTarget(creature);
                if (foundEntities.size() <= 1) continue;
                EntityUtil.setTarget(creature, foundEntities.get(tile.func_145831_w().field_73012_v.nextInt(foundEntities.size())));
            }
        }
        return triggered;
    }
}

