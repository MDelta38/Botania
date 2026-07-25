/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 */
package appeng.api.features;

import appeng.api.features.INetworkEncodable;
import appeng.api.util.IConfigManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IWirelessTermHandler
extends INetworkEncodable {
    public boolean canHandle(ItemStack var1);

    public boolean usePower(EntityPlayer var1, double var2, ItemStack var4);

    public boolean hasPower(EntityPlayer var1, double var2, ItemStack var4);

    public IConfigManager getConfigManager(ItemStack var1);
}

