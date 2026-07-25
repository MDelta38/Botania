/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.features;

import appeng.api.features.IItemComparison;
import appeng.api.features.IItemComparisonProvider;
import net.minecraft.item.ItemStack;

public interface ISpecialComparisonRegistry {
    public IItemComparison getSpecialComparison(ItemStack var1);

    public void addComparisonProvider(IItemComparisonProvider var1);
}

