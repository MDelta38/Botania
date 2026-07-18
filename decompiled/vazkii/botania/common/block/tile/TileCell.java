/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.common.block.tile.TileMod;

public class TileCell
extends TileMod {
    private static final String TAG_GENERATION = "generation";
    private static final String TAG_TICKED = "ticked";
    private static final String TAG_FLOWER_X = "flowerX";
    private static final String TAG_FLOWER_Y = "flowerY";
    private static final String TAG_FLOWER_Z = "flowerZ";
    private static final String TAG_VALID_X = "validX";
    private static final String TAG_VALID_Y = "validY";
    private static final String TAG_VALID_Z = "validZ";
    private int generation;
    private boolean ticked;
    private ChunkCoordinates flowerCoords = new ChunkCoordinates();
    private ChunkCoordinates validCoords = new ChunkCoordinates();

    public boolean canUpdate() {
        return false;
    }

    public void setGeneration(TileEntity flower, int gen) {
        this.generation = gen;
        if (!this.ticked) {
            this.flowerCoords.field_71574_a = flower.field_145851_c;
            this.flowerCoords.field_71572_b = flower.field_145848_d;
            this.flowerCoords.field_71573_c = flower.field_145849_e;
            this.validCoords.field_71574_a = this.field_145851_c;
            this.validCoords.field_71572_b = this.field_145848_d;
            this.validCoords.field_71573_c = this.field_145849_e;
            this.ticked = true;
        } else if (!this.matchCoords(this.validCoords, this) || !this.matchCoords(this.flowerCoords, flower)) {
            this.field_145850_b.func_147468_f(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }

    public boolean isSameFlower(TileEntity flower) {
        return this.matchCoords(this.validCoords, this) && this.matchCoords(this.flowerCoords, flower);
    }

    private boolean matchCoords(ChunkCoordinates coords, TileEntity tile) {
        return coords.field_71574_a == tile.field_145851_c && coords.field_71572_b == tile.field_145848_d && coords.field_71573_c == tile.field_145849_e;
    }

    public int getGeneration() {
        return this.generation;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        cmp.func_74768_a(TAG_GENERATION, this.generation);
        cmp.func_74757_a(TAG_TICKED, this.ticked);
        if (this.ticked) {
            cmp.func_74768_a(TAG_FLOWER_X, this.flowerCoords.field_71574_a);
            cmp.func_74768_a(TAG_FLOWER_Y, this.flowerCoords.field_71572_b);
            cmp.func_74768_a(TAG_FLOWER_Z, this.flowerCoords.field_71573_c);
            cmp.func_74768_a(TAG_VALID_X, this.validCoords.field_71574_a);
            cmp.func_74768_a(TAG_VALID_Y, this.validCoords.field_71572_b);
            cmp.func_74768_a(TAG_VALID_Z, this.validCoords.field_71573_c);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.generation = cmp.func_74762_e(TAG_GENERATION);
        this.ticked = cmp.func_74767_n(TAG_TICKED);
        if (this.ticked) {
            this.flowerCoords.field_71574_a = cmp.func_74762_e(TAG_FLOWER_X);
            this.flowerCoords.field_71572_b = cmp.func_74762_e(TAG_FLOWER_Y);
            this.flowerCoords.field_71573_c = cmp.func_74762_e(TAG_FLOWER_Z);
            this.validCoords.field_71574_a = cmp.func_74762_e(TAG_VALID_X);
            this.validCoords.field_71572_b = cmp.func_74762_e(TAG_VALID_Y);
            this.validCoords.field_71573_c = cmp.func_74762_e(TAG_VALID_Z);
        }
    }
}

