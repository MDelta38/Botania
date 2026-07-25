/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;

public class TileBrainbox
extends TileThaumcraft {
    public ForgeDirection facing = ForgeDirection.UNKNOWN;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.facing = ForgeDirection.getOrientation((int)nbttagcompound.func_74771_c("facing"));
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("facing", (byte)this.facing.ordinal());
    }

    public boolean canUpdate() {
        return false;
    }
}

