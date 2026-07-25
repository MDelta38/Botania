/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package appeng.api.features;

import appeng.api.features.IWirelessTermHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IWirelessTermRegistry {
    public void registerWirelessHandler(IWirelessTermHandler var1);

    public boolean isWirelessTerminal(ItemStack var1);

    public IWirelessTermHandler getWirelessTerminalHandler(ItemStack var1);

    public void openWirelessTerminalGui(ItemStack var1, World var2, EntityPlayer var3);
}

