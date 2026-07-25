/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package appeng.api.implementations.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IAEWrench {
    public boolean canWrench(ItemStack var1, EntityPlayer var2, int var3, int var4, int var5);
}

