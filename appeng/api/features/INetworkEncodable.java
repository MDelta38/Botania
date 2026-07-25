/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.features;

import net.minecraft.item.ItemStack;

public interface INetworkEncodable {
    public String getEncryptionKey(ItemStack var1);

    public void setEncryptionKey(ItemStack var1, String var2, String var3);
}

