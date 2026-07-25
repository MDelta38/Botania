/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 */
package appeng.api.storage.data;

import appeng.api.storage.data.IAEStack;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IAEItemStack
extends IAEStack<IAEItemStack> {
    public ItemStack getItemStack();

    @Override
    public IAEItemStack copy();

    public boolean hasTagCompound();

    @Override
    public void add(IAEItemStack var1);

    public Item getItem();

    public int getItemDamage();

    public boolean sameOre(IAEItemStack var1);

    public boolean isSameType(IAEItemStack var1);

    public boolean isSameType(ItemStack var1);
}

