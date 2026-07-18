/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface IFlowerComponent {
    public boolean canFit(ItemStack var1, IInventory var2);

    public int getParticleColor(ItemStack var1);
}

