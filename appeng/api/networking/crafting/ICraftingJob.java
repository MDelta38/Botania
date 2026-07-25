/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.crafting;

import appeng.api.storage.data.IAEItemStack;
import appeng.api.storage.data.IItemList;

public interface ICraftingJob {
    public boolean isSimulation();

    public long getByteTotal();

    public void populatePlan(IItemList<IAEItemStack> var1);

    public IAEItemStack getOutput();
}

