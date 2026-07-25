/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 */
package thaumic.tinkerer.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import thaumic.tinkerer.common.enchantment.EnchantmentMod;
import thaumic.tinkerer.common.enchantment.ModEnchantments;

public class EnchantmentShatter
extends EnchantmentMod {
    public EnchantmentShatter(int par1) {
        super(par1, 5, EnumEnchantmentType.digger);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return par1Enchantment != Enchantment.field_77349_p && par1Enchantment != ModEnchantments.desintegrate;
    }
}

