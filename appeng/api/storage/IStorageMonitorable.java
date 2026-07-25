/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.storage.IMEMonitor;
import appeng.api.storage.data.IAEFluidStack;
import appeng.api.storage.data.IAEItemStack;

public interface IStorageMonitorable {
    public IMEMonitor<IAEItemStack> getItemInventory();

    public IMEMonitor<IAEFluidStack> getFluidInventory();
}

