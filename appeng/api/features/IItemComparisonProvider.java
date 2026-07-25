/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.features;

import appeng.api.features.IItemComparison;
import net.minecraft.item.ItemStack;

public interface IItemComparisonProvider {
    public IItemComparison getComparison(ItemStack var1);

    public boolean canHandle(ItemStack var1);
}

