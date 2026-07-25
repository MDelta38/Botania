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
import thaumic.tinkerer.common.enchantment.EnchantmentMod;
import thaumic.tinkerer.common.item.kami.armor.ItemGemBoots;

public class EnchantmentSlowFall
extends EnchantmentMod {
    protected EnchantmentSlowFall(int par1) {
        super(par1, 3, EnumEnchantmentType.armor_feet);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return par1Enchantment.field_77352_x != Enchantment.field_77330_e.field_77352_x;
    }

    public boolean func_92089_a(ItemStack par1ItemStack) {
        return !(par1ItemStack.func_77973_b() instanceof ItemGemBoots);
    }
}

