/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import thaumcraft.api.TileThaumcraft;

public class TileCrystal
extends TileThaumcraft {
    public short orientation = 1;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.orientation = nbttagcompound.func_74765_d("orientation");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74777_a("orientation", this.orientation);
    }
}

