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
import thaumcraft.api.IRepairable;

public class EnchantmentRepair
extends Enchantment {
    public EnchantmentRepair(int par1, int par2) {
        super(par1, par2, EnumEnchantmentType.all);
        this.func_77322_b("repair");
    }

    public int func_77321_a(int par1) {
        return 20 + (par1 - 1) * 10;
    }

    public int func_77317_b(int par1) {
        return super.func_77321_a(par1) + 50;
    }

    public int func_77325_b() {
        return 2;
    }

    public boolean func_92089_a(ItemStack stack) {
        return stack.func_77984_f() && (stack.func_77973_b() instanceof IRepairable || stack.func_77973_b() instanceof ItemBook);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return this.func_92089_a(stack);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return super.func_77326_a(par1Enchantment) && par1Enchantment.field_77352_x != Enchantment.field_77347_r.field_77352_x;
    }
}

