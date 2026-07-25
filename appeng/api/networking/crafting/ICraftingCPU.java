/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.crafting;

import appeng.api.networking.security.BaseActionSource;
import appeng.api.networking.storage.IBaseMonitor;
import appeng.api.storage.data.IAEItemStack;

public interface ICraftingCPU
extends IBaseMonitor<IAEItemStack> {
    public boolean isBusy();

    public BaseActionSource getActionSource();

    public long getAvailableStorage();

    public int getCoProcessors();

    public String getName();
}

