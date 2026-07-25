/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.storage;

import appeng.api.networking.IGridCache;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.ICellProvider;
import appeng.api.storage.IStorageMonitorable;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEStack;

public interface IStorageGrid
extends IGridCache,
IStorageMonitorable {
    public void postAlterationOfStoredItems(StorageChannel var1, Iterable<? extends IAEStack> var2, BaseActionSource var3);

    public void registerCellProvider(ICellProvider var1);

    public void unregisterCellProvider(ICellProvider var1);
}

