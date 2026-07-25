/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package com.kentington.thaumichorizons.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;

public class TileSpike
extends TileThaumcraft {
    public byte direction = 1;
    public byte spikeType = 0;

    public TileSpike(byte metadata, byte type) {
        this.direction = metadata;
        this.spikeType = type;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74774_a("dir", this.direction);
        nbttagcompound.func_74774_a("type", this.spikeType);
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.direction = nbttagcompound.func_74771_c("dir");
        this.spikeType = nbttagcompound.func_74771_c("type");
    }
}

