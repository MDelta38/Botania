/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.crafting;

import appeng.api.networking.crafting.ICraftingMedium;
import appeng.api.networking.crafting.ICraftingPatternDetails;
import appeng.api.storage.data.IAEItemStack;

public interface ICraftingProviderHelper {
    public void addCraftingOption(ICraftingMedium var1, ICraftingPatternDetails var2);

    public void setEmitable(IAEItemStack var1);
}

