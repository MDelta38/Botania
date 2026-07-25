/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.tileentity.TileEntity
 */
package com.emoniph.witchery.infusion.infusions.spirit;

import com.emoniph.witchery.infusion.infusions.spirit.InfusedSpiritEffect;
import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;

public class InfusedSpiritEnhancedPoppetEffect
extends InfusedSpiritEffect {
    public InfusedSpiritEnhancedPoppetEffect(int id, int spirits, int spectres, int banshees, int poltergeists) {
        super(id, "enhancedpoppets", spirits, spectres, banshees, poltergeists);
    }

    @Override
    public boolean doUpdateEffect(TileEntity tile, boolean triggered, ArrayList<EntityLivingBase> foundEntities) {
        return false;
    }
}

