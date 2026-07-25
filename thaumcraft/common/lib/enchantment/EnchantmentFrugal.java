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

import java.util.Random;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemStack;
import thaumcraft.api.wands.IWandFocus;
import thaumcraft.api.wands.ItemFocusBasic;

public class EnchantmentFrugal
extends Enchantment {
    public EnchantmentFrugal(int par1, int par2) {
        super(par1, par2, EnumEnchantmentType.all);
        this.func_77322_b("frugal");
    }

    public int func_77321_a(int par1) {
        return 5 + 11 * (par1 - 1);
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

    public static boolean doDamage(ItemStack par0ItemStack, int par1, Random par2Random) {
        float chance = 1.0f - (float)par1 / 5.0f;
        return par0ItemStack.func_77973_b() instanceof ItemFocusBasic && par2Random.nextFloat() < chance;
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return super.func_77326_a(par1Enchantment);
    }
}

