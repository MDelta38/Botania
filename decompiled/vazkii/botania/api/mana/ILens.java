/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.mana;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.ILensEffect;

public interface ILens
extends ILensEffect {
    @SideOnly(value=Side.CLIENT)
    public int getLensColor(ItemStack var1);

    public boolean canCombineLenses(ItemStack var1, ItemStack var2);

    public ItemStack getCompositeLens(ItemStack var1);

    public ItemStack setCompositeLens(ItemStack var1, ItemStack var2);
}

