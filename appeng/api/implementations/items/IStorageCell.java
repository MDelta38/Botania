/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.implementations.items;

import appeng.api.storage.ICellWorkbenchItem;
import appeng.api.storage.data.IAEItemStack;
import net.minecraft.item.ItemStack;

public interface IStorageCell
extends ICellWorkbenchItem {
    public int getBytes(ItemStack var1);

    public int BytePerType(ItemStack var1);

    public int getTotalTypes(ItemStack var1);

    public boolean isBlackListed(ItemStack var1, IAEItemStack var2);

    public boolean storableInStorageCell();

    public boolean isStorageCell(ItemStack var1);

    public double getIdleDrain();
}

