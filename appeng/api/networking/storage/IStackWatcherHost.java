/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.storage;

import appeng.api.networking.security.BaseActionSource;
import appeng.api.networking.storage.IStackWatcher;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;

public interface IStackWatcherHost {
    public void updateWatcher(IStackWatcher var1);

    public void onStackChange(IItemList var1, IAEStack var2, IAEStack var3, BaseActionSource var4, StorageChannel var5);
}

