/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.networking.storage.IBaseMonitor;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;

public interface IMEMonitor<T extends IAEStack>
extends IMEInventoryHandler<T>,
IBaseMonitor<T> {
    @Override
    @Deprecated
    public IItemList<T> getAvailableItems(IItemList var1);

    public IItemList<T> getStorageList();
}

