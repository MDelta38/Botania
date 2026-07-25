/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package appeng.api.storage;

import appeng.api.config.FuzzyMode;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface ICellWorkbenchItem {
    public boolean isEditable(ItemStack var1);

    public IInventory getUpgradesInventory(ItemStack var1);

    public IInventory getConfigInventory(ItemStack var1);

    public FuzzyMode getFuzzyMode(ItemStack var1);

    public void setFuzzyMode(ItemStack var1, FuzzyMode var2);
}

