/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.tileentity.TileEntity
 */
package com.emoniph.witchery.infusion.infusions.spirit;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;

public class InfusedSpiritScreamerEffect
extends InfusedSpiritEffect {
    public InfusedSpiritScreamerEffect(int id, int spirits, int spectres, int banshees, int poltergeists) {
        super(id, "screamer", spirits, spectres, banshees, poltergeists);
    }

    @Override
    public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities) {
        if (triggered) {
            ParticleEffect.REDDUST.send(tile.func_145838_q() != Witchery.Blocks.FETISH_WITCHS_LADDER ? SoundEffect.WITCHERY_MOB_SPECTRE_SPECTRE_HIT : SoundEffect.NONE, tile.func_145831_w(), 0.5 + (double)tile.field_145851_c, 0.3 + (double)tile.field_145848_d, 0.5 + (double)tile.field_145849_e, 0.2, 0.5, 16);
        }
        return triggered;
    }

    @Override
    public boolean isRedstoneSignaller() {
        return true;
    }

    @Override
    public double getRadius() {
        return 16.0;
    }
}

