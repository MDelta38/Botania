/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemBook
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.lib.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import thaumcraft.common.items.armor.ItemHoverHarness;

public class EnchantmentHaste
extends Enchantment {
    public EnchantmentHaste(int par1, int par2) {
        super(par1, par2, EnumEnchantmentType.armor);
        this.func_77322_b("haste");
    }

    public int func_77321_a(int par1) {
        return 15 + (par1 - 1) * 9;
    }

    public int func_77317_b(int par1) {
        return super.func_77321_a(par1) + 50;
    }

    public int func_77325_b() {
        return 3;
    }

    public boolean func_92089_a(ItemStack is) {
        return is != null && (is.func_77973_b() instanceof ItemArmor && (((ItemArmor)is.func_77973_b()).field_77881_a == 3 || is.func_77973_b() instanceof ItemHoverHarness) || is.func_77973_b() instanceof ItemBook);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.func_92089_a(stack);
    }
}

