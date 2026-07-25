/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.config.IncludeExclude;
import appeng.api.storage.ICellInventory;
import appeng.api.storage.IMEInventoryHandler;
import appeng.api.storage.data.IAEItemStack;

public interface ICellInventoryHandler
extends IMEInventoryHandler<IAEItemStack> {
    public ICellInventory getCellInv();

    public boolean isPreformatted();

    public boolean isFuzzy();

    public IncludeExclude getIncludeExcludeMode();
}

