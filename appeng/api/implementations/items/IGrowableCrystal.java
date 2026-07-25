/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.item.ItemStack
 */
package appeng.api.implementations.items;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;

public interface IGrowableCrystal {
    public ItemStack triggerGrowth(ItemStack var1);

    public float getMultiplier(Block var1, Material var2);
}

