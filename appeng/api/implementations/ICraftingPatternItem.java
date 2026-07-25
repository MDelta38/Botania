/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package appeng.api.implementations;

import appeng.api.networking.crafting.ICraftingPatternDetails;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface ICraftingPatternItem {
    public ICraftingPatternDetails getPatternForItem(ItemStack var1, World var2);
}

