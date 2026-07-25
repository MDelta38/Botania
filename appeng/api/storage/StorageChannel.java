/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.AEApi;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IAEStack;
import appeng.api.storage.data.IItemList;

public enum StorageChannel {
    ITEMS(IAEItemStack.class),
    FLUIDS(IAEFluidStack.class);

    public final Class<? extends IAEStack> type;

    private StorageChannel(Class<? extends IAEStack> t) {
        this.type = t;
    }

    public IItemList createList() {
        if (this == ITEMS) {
            return AEApi.instance().storage().createItemList();
        }
        return AEApi.instance().storage().createFluidList();
    }
}

