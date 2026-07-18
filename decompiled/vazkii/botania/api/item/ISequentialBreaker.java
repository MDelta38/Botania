/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ISequentialBreaker {
    public void breakOtherBlock(EntityPlayer var1, ItemStack var2, int var3, int var4, int var5, int var6, int var7, int var8, int var9);

    public boolean disposeOfTrashBlocks(ItemStack var1);
}

