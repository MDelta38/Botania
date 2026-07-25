/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.config.Actionable;
import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.StorageChannel;
import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;

public interface IMEInventory<StackType extends IAEStack> {
    public StackType injectItems(StackType var1, Actionable var2, BaseActionSource var3);

    public StackType extractItems(StackType var1, Actionable var2, BaseActionSource var3);

    public IItemList<StackType> getAvailableItems(IItemList<StackType> var1);

    public StorageChannel getChannel();
}

