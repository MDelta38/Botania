/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.features;

import appeng.api.config.TunnelType;
import net.minecraft.item.ItemStack;

public interface IP2PTunnelRegistry {
    public void addNewAttunement(ItemStack var1, TunnelType var2);

    public TunnelType getTunnelTypeByItem(ItemStack var1);
}

