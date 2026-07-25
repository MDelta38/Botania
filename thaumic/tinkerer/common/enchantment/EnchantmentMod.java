/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemStack
 */
package thaumic.tinkerer.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentMod
extends Enchantment {
    int maxLevel;

    protected EnchantmentMod(int par1, int max, EnumEnchantmentType par3EnumEnchantmentType) {
        super(par1, 0, par3EnumEnchantmentType);
        this.maxLevel = max;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    public int func_77325_b() {
        return this.maxLevel;
    }
}

