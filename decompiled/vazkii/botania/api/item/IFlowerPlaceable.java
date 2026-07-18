/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.subtile.SubTileEntity;

public interface IFlowerPlaceable {
    public Block getBlockToPlaceByFlower(ItemStack var1, SubTileEntity var2, int var3, int var4, int var5);

    public void onBlockPlacedByFlower(ItemStack var1, SubTileEntity var2, int var3, int var4, int var5);
}

