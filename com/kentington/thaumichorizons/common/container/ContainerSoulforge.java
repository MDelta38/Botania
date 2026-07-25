/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.Container
 */
package com.kentington.thaumichorizons.common.container;

import com.kentington.thaumichorizons.common.tiles.TileSoulforge;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerSoulforge
extends Container {
    private EntityPlayer player;
    private TileSoulforge tile;

    public ContainerSoulforge(EntityPlayer p, TileSoulforge t) {
        this.player = p;
        this.tile = t;
    }

    public boolean func_75145_c(EntityPlayer p_75145_1_) {
        return this.tile.isUseableByPlayer(p_75145_1_);
    }
}

