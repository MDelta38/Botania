/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.crafting;

import appeng.api.networking.crafting.ICraftingGrid;
import appeng.api.networking.crafting.ICraftingWatcher;
import appeng.api.storage.data.IAEItemStack;

public interface ICraftingWatcherHost {
    public void updateWatcher(ICraftingWatcher var1);

    public void onRequestChange(ICraftingGrid var1, IAEItemStack var2);
}

