/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.api.TileThaumcraft;

public class TileInfusionPillar
extends TileThaumcraft {
    public byte orientation = 0;

    public boolean canUpdate() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getRenderBoundingBox() {
        return AxisAlignedBB.func_72330_a((double)(this.field_145851_c - 1), (double)(this.field_145848_d - 1), (double)(this.field_145849_e - 1), (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 2), (double)(this.field_145849_e + 1));
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.orientation = nbttagcompound.func_74771_c("orientation");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74774_a("orientation", this.orientation);
    }
}

