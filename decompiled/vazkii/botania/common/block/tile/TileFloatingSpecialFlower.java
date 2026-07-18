/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package vazkii.botania.common.block.tile;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.block.decor.IFloatingFlower;
import vazkii.botania.common.block.tile.TileSpecialFlower;
import vazkii.botania.common.item.block.ItemBlockSpecialFlower;

public class TileFloatingSpecialFlower
extends TileSpecialFlower
implements IFloatingFlower {
    public static final String TAG_ISLAND_TYPE = "islandType";
    IFloatingFlower.IslandType type = IFloatingFlower.IslandType.GRASS;

    @Override
    public boolean isOnSpecialSoil() {
        return false;
    }

    @Override
    public ItemStack getDisplayStack() {
        return ItemBlockSpecialFlower.ofType(this.subTileName);
    }

    @Override
    public IFloatingFlower.IslandType getIslandType() {
        return this.type;
    }

    @Override
    public void setIslandType(IFloatingFlower.IslandType type) {
        this.type = type;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        super.writeCustomNBT(cmp);
        cmp.func_74778_a(TAG_ISLAND_TYPE, this.type.toString());
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        super.readCustomNBT(cmp);
        this.type = IFloatingFlower.IslandType.ofType(cmp.func_74779_i(TAG_ISLAND_TYPE));
    }

    @Override
    public int getSlowdownFactor() {
        IFloatingFlower.IslandType type = this.getIslandType();
        if (type == IFloatingFlower.IslandType.MYCEL) {
            return 10;
        }
        if (type == IFloatingFlower.IslandType.PODZOL) {
            return 5;
        }
        return 0;
    }
}

