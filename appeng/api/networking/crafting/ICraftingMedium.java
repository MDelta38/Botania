/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 */
package appeng.api.networking.crafting;

import appeng.api.networking.crafting.ICraftingPatternDetails;
import net.minecraft.inventory.InventoryCrafting;

public interface ICraftingMedium {
    public boolean pushPattern(ICraftingPatternDetails var1, InventoryCrafting var2);

    public boolean isBusy();
}

