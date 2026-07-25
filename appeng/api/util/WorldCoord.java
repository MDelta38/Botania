/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldCoord {
    public int x;
    public int y;
    public int z;

    public WorldCoord add(ForgeDirection direction, int length) {
        this.x += direction.offsetX * length;
        this.y += direction.offsetY * length;
        this.z += direction.offsetZ * length;
        return this;
    }

    public WorldCoord subtract(ForgeDirection direction, int length) {
        this.x -= direction.offsetX * length;
        this.y -= direction.offsetY * length;
        this.z -= direction.offsetZ * length;
        return this;
    }

    public WorldCoord add(int _x, int _y, int _z) {
        this.x += _x;
        this.y += _y;
        this.z += _z;
        return this;
    }

    public WorldCoord subtract(int _x, int _y, int _z) {
        this.x -= _x;
        this.y -= _y;
        this.z -= _z;
        return this;
    }

    public WorldCoord multiple(int _x, int _y, int _z) {
        this.x *= _x;
        this.y *= _y;
        this.z *= _z;
        return this;
    }

    public WorldCoord divide(int _x, int _y, int _z) {
        this.x /= _x;
        this.y /= _y;
        this.z /= _z;
        return this;
    }

    public WorldCoord(int _x, int _y, int _z) {
        this.x = _x;
        this.y = _y;
        this.z = _z;
    }

    public WorldCoord(TileEntity s) {
        this(s.field_145851_c, s.field_145848_d, s.field_145849_e);
    }

    public ForgeDirection directionTo(WorldCoord loc) {
        int ox = this.x - loc.x;
        int oy = this.y - loc.y;
        int oz = this.z - loc.z;
        int xlen = Math.abs(ox);
        int ylen = Math.abs(oy);
        int zlen = Math.abs(oz);
        if (loc.isEqual(this.copy().add(ForgeDirection.EAST, xlen))) {
            return ForgeDirection.EAST;
        }
        if (loc.isEqual(this.copy().add(ForgeDirection.WEST, xlen))) {
            return ForgeDirection.WEST;
        }
        if (loc.isEqual(this.copy().add(ForgeDirection.NORTH, zlen))) {
            return ForgeDirection.NORTH;
        }
        if (loc.isEqual(this.copy().add(ForgeDirection.SOUTH, zlen))) {
            return ForgeDirection.SOUTH;
        }
        if (loc.isEqual(this.copy().add(ForgeDirection.UP, ylen))) {
            return ForgeDirection.UP;
        }
        if (loc.isEqual(this.copy().add(ForgeDirection.DOWN, ylen))) {
            return ForgeDirection.DOWN;
        }
        return null;
    }

    public boolean isEqual(WorldCoord c) {
        return this.x == c.x && this.y == c.y && this.z == c.z;
    }

    public WorldCoord copy() {
        return new WorldCoord(this.x, this.y, this.z);
    }

    public boolean equals(Object obj) {
        return obj instanceof WorldCoord && this.isEqual((WorldCoord)obj);
    }

    public String toString() {
        return "" + this.x + "," + this.y + "," + this.z;
    }

    public int hashCode() {
        return this.y << 24 ^ this.x ^ this.z;
    }
}

