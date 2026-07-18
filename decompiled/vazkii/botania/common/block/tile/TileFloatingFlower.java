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
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.decor.IFloatingFlower;
import vazkii.botania.common.block.tile.TileMod;

public class TileFloatingFlower
extends TileMod
implements IFloatingFlower {
    public static final String TAG_ISLAND_TYPE = "islandType";
    public static ItemStack forcedStack = null;
    IFloatingFlower.IslandType type = IFloatingFlower.IslandType.GRASS;

    @Override
    public ItemStack getDisplayStack() {
        if (forcedStack != null) {
            ItemStack retStack = forcedStack;
            forcedStack = null;
            return retStack;
        }
        return new ItemStack(ModBlocks.shinyFlower, 1, this.func_145832_p());
    }

    public boolean canUpdate() {
        return false;
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
        cmp.func_74778_a(TAG_ISLAND_TYPE, this.type.toString());
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.type = IFloatingFlower.IslandType.ofType(cmp.func_74779_i(TAG_ISLAND_TYPE));
    }
}

