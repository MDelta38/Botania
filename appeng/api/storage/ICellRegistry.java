/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.storage;

import appeng.api.storage.ICellHandler;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.ISaveProvider;
import appeng.api.storage.StorageChannel;
import net.minecraft.item.ItemStack;

public interface ICellRegistry {
    public void addCellHandler(ICellHandler var1);

    public boolean isCellHandled(ItemStack var1);

    public ICellHandler getHandler(ItemStack var1);

    public IMEInventoryHandler getCellInventory(ItemStack var1, ISaveProvider var2, StorageChannel var3);
}

