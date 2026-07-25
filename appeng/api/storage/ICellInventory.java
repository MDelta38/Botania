/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package appeng.api.storage;

import appeng.api.config.FuzzyMode;
import appeng.api.storage.IMEInventory;
import appeng.api.storage.data.IAEItemStack;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface ICellInventory
extends IMEInventory<IAEItemStack> {
    public ItemStack getItemStack();

    public double getIdleDrain();

    public FuzzyMode getFuzzyMode();

    public IInventory getConfigInventory();

    public IInventory getUpgradesInventory();

    public int getBytesPerType();

    public boolean canHoldNewItem();

    public long getTotalBytes();

    public long getFreeBytes();

    public long getUsedBytes();

    public long getTotalItemTypes();

    public long getStoredItemCount();

    public long getStoredItemTypes();

    public long getRemainingItemTypes();

    public long getRemainingItemCount();

    public int getUnusedItemCount();

    public int getStatusForCell();
}

