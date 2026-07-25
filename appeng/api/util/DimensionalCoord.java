/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 */
package appeng.api.util;

import appeng.api.util.WorldCoord;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class DimensionalCoord
extends WorldCoord {
    private final World w;
    private final int dimId;

    public DimensionalCoord(DimensionalCoord s) {
        super(s.x, s.y, s.z);
        this.w = s.w;
        this.dimId = s.dimId;
    }

    public DimensionalCoord(TileEntity s) {
        super(s);
        this.w = s.func_145831_w();
        this.dimId = this.w.field_73011_w.field_76574_g;
    }

    public DimensionalCoord(World _w, int _x, int _y, int _z) {
        super(_x, _y, _z);
        this.w = _w;
        this.dimId = _w.field_73011_w.field_76574_g;
    }

    @Override
    public DimensionalCoord copy() {
        return new DimensionalCoord(this);
    }

    public boolean isEqual(DimensionalCoord c) {
        return this.x == c.x && this.y == c.y && this.z == c.z && c.w == this.w;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DimensionalCoord && this.isEqual((DimensionalCoord)obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode() ^ this.dimId;
    }

    public boolean isInWorld(World world) {
        return this.w == world;
    }

    @Override
    public String toString() {
        return this.dimId + "," + super.toString();
    }

    public World getWorld() {
        return this.w;
    }
}

