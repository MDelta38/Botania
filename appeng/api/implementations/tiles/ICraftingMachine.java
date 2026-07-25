/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.InventoryCrafting
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.implementations.tiles;

import appeng.api.networking.crafting.ICraftingPatternDetails;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraftforge.common.util.ForgeDirection;

public interface ICraftingMachine {
    public boolean pushPattern(ICraftingPatternDetails var1, InventoryCrafting var2, ForgeDirection var3);

    public boolean acceptsPlans();
}

