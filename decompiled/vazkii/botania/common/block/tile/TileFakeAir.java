/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 */
package vazkii.botania.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.common.block.subtile.functional.SubTileBubbell;
import vazkii.botania.common.block.tile.TileMod;

public class TileFakeAir
extends TileMod {
    private static final String TAG_FLOWER_X = "flowerX";
    private static final String TAG_FLOWER_Y = "flowerY";
    private static final String TAG_FLOWER_Z = "flowerZ";
    int flowerX;
    int flowerY;
    int flowerZ;

    public boolean canUpdate() {
        return false;
    }

    public void setFlower(TileEntity tile) {
        this.flowerX = tile.field_145851_c;
        this.flowerY = tile.field_145848_d;
        this.flowerZ = tile.field_145849_e;
    }

    public boolean canStay() {
        return SubTileBubbell.isValidBubbell(this.field_145850_b, this.flowerX, this.flowerY, this.flowerZ);
    }

    @Override
    public void func_145841_b(NBTTagCompound par1nbtTagCompound) {
        super.func_145841_b(par1nbtTagCompound);
        par1nbtTagCompound.func_74768_a(TAG_FLOWER_X, this.flowerX);
        par1nbtTagCompound.func_74768_a(TAG_FLOWER_Y, this.flowerY);
        par1nbtTagCompound.func_74768_a(TAG_FLOWER_Z, this.flowerZ);
    }

    @Override
    public void func_145839_a(NBTTagCompound par1nbtTagCompound) {
        super.func_145839_a(par1nbtTagCompound);
        this.flowerX = par1nbtTagCompound.func_74762_e(TAG_FLOWER_X);
        this.flowerY = par1nbtTagCompound.func_74762_e(TAG_FLOWER_Y);
        this.flowerZ = par1nbtTagCompound.func_74762_e(TAG_FLOWER_Z);
    }
}

