/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.brew;

import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.ModItems;
import vazkii.botania.common.item.brew.ItemBrewBase;

public class ItemBrewFlask
extends ItemBrewBase {
    public ItemBrewFlask() {
        super("brewFlask", "flask", 6, 24, new ItemStack(ModItems.vial, 1, 1));
    }
}

