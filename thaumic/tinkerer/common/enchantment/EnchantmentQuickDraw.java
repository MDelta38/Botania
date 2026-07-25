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

public class EnchantmentQuickDraw
extends EnchantmentMod {
    protected EnchantmentQuickDraw(int par1) {
        super(par1, 2, EnumEnchantmentType.bow);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return par1Enchantment.field_77352_x != Enchantment.field_77344_u.field_77352_x;
    }
}

