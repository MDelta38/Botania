/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemBook
 *  net.minecraft.item.ItemStack
 */
package thaumcraft.common.lib.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import thaumcraft.api.wands.IWandFocus;

public class EnchantmentWandFortune
extends Enchantment {
    public EnchantmentWandFortune(int par1, int par2) {
        super(par1, par2, EnumEnchantmentType.all);
        this.func_77322_b("wandfortune");
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

    public boolean func_92089_a(ItemStack stack) {
        return stack.func_77973_b() instanceof IWandFocus && ((IWandFocus)stack.func_77973_b()).acceptsEnchant(this.field_77352_x) || stack.func_77973_b() instanceof ItemBook;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.func_92089_a(stack);
    }
}

