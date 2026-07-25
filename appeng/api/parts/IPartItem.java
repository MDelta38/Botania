/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.parts;

import appeng.api.parts.IPart;
import net.minecraft.item.ItemStack;

public interface IPartItem {
    public IPart createPartFromItemStack(ItemStack var1);
}

