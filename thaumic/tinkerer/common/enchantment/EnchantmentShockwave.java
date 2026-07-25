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
import thaumic.tinkerer.common.ThaumicTinkerer;
import thaumic.tinkerer.common.enchantment.EnchantmentMod;
import thaumic.tinkerer.common.enchantment.ModEnchantments;
import thaumic.tinkerer.common.item.kami.armor.ItemGemBoots;

public class EnchantmentShockwave
extends EnchantmentMod {
    public EnchantmentShockwave(int par1) {
        super(par1, 5, EnumEnchantmentType.armor_feet);
    }

    public boolean func_92089_a(ItemStack p_92089_1_) {
        return p_92089_1_.func_77973_b() == ThaumicTinkerer.registry.getFirstItemFromClass(ItemGemBoots.class) ? false : super.func_92089_a(p_92089_1_);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return par1Enchantment.field_77352_x != Enchantment.field_77330_e.field_77352_x && par1Enchantment.field_77352_x != ModEnchantments.slowFall.field_77352_x && super.func_77326_a(par1Enchantment);
    }
}

