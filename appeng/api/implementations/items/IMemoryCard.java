/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 */
package appeng.api.implementations.items;

import appeng.api.implementations.items.MemoryCardMessages;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public interface IMemoryCard {
    public void setMemoryCardContents(ItemStack var1, String var2, NBTTagCompound var3);

    public String getSettingsName(ItemStack var1);

    public NBTTagCompound getData(ItemStack var1);

    public void notifyUser(EntityPlayer var1, MemoryCardMessages var2);
}

