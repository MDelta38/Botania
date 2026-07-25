/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.implementations.items;

import appeng.api.config.AccessRestriction;
import net.minecraft.item.ItemStack;

public interface IAEItemPowerStorage {
    public double injectAEPower(ItemStack var1, double var2);

    public double extractAEPower(ItemStack var1, double var2);

    public double getAEMaxPower(ItemStack var1);

    public double getAECurrentPower(ItemStack var1);

    public AccessRestriction getPowerFlow(ItemStack var1);
}

