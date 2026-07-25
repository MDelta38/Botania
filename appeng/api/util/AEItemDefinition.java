/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 */
package appeng.api.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public interface AEItemDefinition {
    public Block block();

    public Item item();

    public Class<? extends TileEntity> entity();

    public ItemStack stack(int var1);

    public boolean sameAsStack(ItemStack var1);

    public boolean sameAsBlock(IBlockAccess var1, int var2, int var3, int var4);
}

