/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package thaumic.tinkerer.common.block.tile.transvector;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import thaumic.tinkerer.common.block.tile.TileCamo;

public abstract class TileTransvector
extends TileCamo {
    private static final String TAG_X_TARGET = "xt";
    private static final String TAG_Y_TARGET = "yt";
    private static final String TAG_Z_TARGET = "zt";
    private static final String TAG_CHEATY_MODE = "cheatyMode";
    public int x = 0;
    public int y = -1;
    public int z = 0;
    private boolean cheaty;

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        cmp.func_74768_a(TAG_X_TARGET, this.x);
        cmp.func_74768_a(TAG_Y_TARGET, this.y);
        cmp.func_74768_a(TAG_Z_TARGET, this.z);
        cmp.func_74757_a(TAG_CHEATY_MODE, this.cheaty);
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        this.x = cmp.func_74762_e(TAG_X_TARGET);
        this.y = cmp.func_74762_e(TAG_Y_TARGET);
        this.z = cmp.func_74762_e(TAG_Z_TARGET);
        this.cheaty = cmp.func_74767_n(TAG_CHEATY_MODE);
    }

    public final TileEntity getTile() {
        if (!this.field_145850_b.func_72899_e(this.x, this.y, this.z)) {
            return null;
        }
        TileEntity tile = this.field_145850_b.func_147438_o(this.x, this.y, this.z);
        if (tile == null && this.tileRequiredAtLink() || (Math.abs(this.x - this.field_145851_c) > this.getMaxDistance() || Math.abs(this.y - this.field_145848_d) > this.getMaxDistance() || Math.abs(this.z - this.field_145849_e) > this.getMaxDistance()) && !this.cheaty) {
            this.y = -1;
            return null;
        }
        return tile;
    }

    public abstract int getMaxDistance();

    boolean tileRequiredAtLink() {
        return !this.cheaty;
    }
}

