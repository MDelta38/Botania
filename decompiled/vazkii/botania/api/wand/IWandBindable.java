/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.wand;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.wand.ITileBound;

public interface IWandBindable
extends ITileBound {
    public boolean canSelect(EntityPlayer var1, ItemStack var2, int var3, int var4, int var5, int var6);

    public boolean bindTo(EntityPlayer var1, ItemStack var2, int var3, int var4, int var5, int var6);
}

