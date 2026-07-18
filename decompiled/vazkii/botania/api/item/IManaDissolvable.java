/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.item;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaPool;

public interface IManaDissolvable {
    public void onDissolveTick(IManaPool var1, ItemStack var2, EntityItem var3);
}

