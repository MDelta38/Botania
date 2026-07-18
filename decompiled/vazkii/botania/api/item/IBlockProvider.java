/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IBlockProvider {
    public boolean provideBlock(EntityPlayer var1, ItemStack var2, ItemStack var3, Block var4, int var5, boolean var6);

    public int getBlockCount(EntityPlayer var1, ItemStack var2, ItemStack var3, Block var4, int var5);
}

