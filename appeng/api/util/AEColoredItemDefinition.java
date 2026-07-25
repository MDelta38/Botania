/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 */
package appeng.api.util;

import appeng.api.util.AEColor;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface AEColoredItemDefinition {
    public Block block(AEColor var1);

    public Item item(AEColor var1);

    public Class<? extends TileEntity> entity(AEColor var1);

    public ItemStack stack(AEColor var1, int var2);

    public ItemStack[] allStacks(int var1);

    public boolean sameAs(AEColor var1, ItemStack var2);
}

