/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.mana;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.ILens;

public interface ICompositableLens
extends ILens {
    public int getProps(ItemStack var1);

    public boolean isCombinable(ItemStack var1);
}

