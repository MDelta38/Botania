/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.StorageChannel;
import java.util.List;

public interface ICellProvider {
    public List<IMEInventoryHandler> getCellArray(StorageChannel var1);

    public int getPriority();
}

