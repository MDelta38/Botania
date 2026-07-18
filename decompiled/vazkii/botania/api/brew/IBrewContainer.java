/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.brew;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.brew.Brew;

public interface IBrewContainer {
    public ItemStack getItemForBrew(Brew var1, ItemStack var2);

    public int getManaCost(Brew var1, ItemStack var2);
}

