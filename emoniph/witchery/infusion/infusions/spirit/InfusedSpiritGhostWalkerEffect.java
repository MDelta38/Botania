/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 */
package com.emoniph.witchery.infusion.infusions.spirit;

import com.emoniph.witchery.dimension.WorldProviderDreamWorld;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;

public class InfusedSpiritGhostWalkerEffect
extends InfusedSpiritEffect {
    public InfusedSpiritGhostWalkerEffect(int id, int spirits, int spectres, int banshees, int poltergeists) {
        super(id, "ghostwalker", spirits, spectres, banshees, poltergeists);
    }

    @Override
    public double getRadius() {
        return 8.0;
    }

    @Override
    public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities) {
        if (triggered) {
            for (EntityLivingBase entity : foundEntities) {
                EntityPlayer player;
                if (!(entity instanceof EntityPlayer) || !WorldProviderDreamWorld.getPlayerIsGhost(player = (EntityPlayer)entity)) continue;
                WorldProviderDreamWorld.setPlayerSkipNextManifestationReduction(player);
            }
        }
        return triggered;
    }
}

