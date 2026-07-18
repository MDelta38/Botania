/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 */
package vazkii.botania.api.mana;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public interface IManaItem {
    public int getMana(ItemStack var1);

    public int getMaxMana(ItemStack var1);

    public void addMana(ItemStack var1, int var2);

    public boolean canReceiveManaFromPool(ItemStack var1, TileEntity var2);

    public boolean canReceiveManaFromItem(ItemStack var1, ItemStack var2);

    public boolean canExportManaToPool(ItemStack var1, TileEntity var2);

    public boolean canExportManaToItem(ItemStack var1, ItemStack var2);

    public boolean isNoExport(ItemStack var1);
}

