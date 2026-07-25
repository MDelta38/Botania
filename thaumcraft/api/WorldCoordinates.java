/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package thaumcraft.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class WorldCoordinates
implements Comparable {
    public int x;
    public int y;
    public int z;
    public int dim;

    public WorldCoordinates() {
    }

    public WorldCoordinates(int par1, int par2, int par3, int d) {
        this.x = par1;
        this.y = par2;
        this.z = par3;
        this.dim = d;
    }

    public WorldCoordinates(TileEntity tile) {
        this.x = tile.field_145851_c;
        this.y = tile.field_145848_d;
        this.z = tile.field_145849_e;
        this.dim = tile.func_145831_w().field_73011_w.field_76574_g;
    }

    public WorldCoordinates(WorldCoordinates par1ChunkCoordinates) {
        this.x = par1ChunkCoordinates.x;
        this.y = par1ChunkCoordinates.y;
        this.z = par1ChunkCoordinates.z;
        this.dim = par1ChunkCoordinates.dim;
    }

    public boolean equals(Object par1Obj) {
        if (!(par1Obj instanceof WorldCoordinates)) {
            return false;
        }
        WorldCoordinates coordinates = (WorldCoordinates)par1Obj;
        return this.x == coordinates.x && this.y == coordinates.y && this.z == coordinates.z && this.dim == coordinates.dim;
    }

    public int hashCode() {
        return this.x + this.y << 8 + this.z << 16 + this.dim << 24;
    }

    public int compareWorldCoordinate(WorldCoordinates par1) {
        return this.dim == par1.dim ? (this.y == par1.y ? (this.z == par1.z ? this.x - par1.x : this.z - par1.z) : this.y - par1.y) : -1;
    }

    public void set(int par1, int par2, int par3, int d) {
        this.x = par1;
        this.y = par2;
        this.z = par3;
        this.dim = d;
    }

    public float getDistanceSquared(int par1, int par2, int par3) {
        float f = this.x - par1;
        float f1 = this.y - par2;
        float f2 = this.z - par3;
        return f * f + f1 * f1 + f2 * f2;
    }

    public float getDistanceSquaredToWorldCoordinates(WorldCoordinates par1ChunkCoordinates) {
        return this.getDistanceSquared(par1ChunkCoordinates.x, par1ChunkCoordinates.y, par1ChunkCoordinates.z);
    }

    public int compareTo(Object par1Obj) {
        return this.compareWorldCoordinate((WorldCoordinates)par1Obj);
    }

    public void readNBT(NBTTagCompound nbt) {
        this.x = nbt.func_74762_e("w_x");
        this.y = nbt.func_74762_e("w_y");
        this.z = nbt.func_74762_e("w_z");
        this.dim = nbt.func_74762_e("w_d");
    }

    public void writeNBT(NBTTagCompound nbt) {
        nbt.func_74768_a("w_x", this.x);
        nbt.func_74768_a("w_y", this.y);
        nbt.func_74768_a("w_z", this.z);
        nbt.func_74768_a("w_d", this.dim);
    }
}

