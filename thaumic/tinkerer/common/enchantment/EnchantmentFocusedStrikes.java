/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  thaumcraft.api.ThaumcraftApi
 */
package thaumic.tinkerer.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import thaumcraft.api.ThaumcraftApi;
import thaumic.tinkerer.common.enchantment.EnchantmentMod;

public class EnchantmentFocusedStrikes
extends EnchantmentMod {
    public EnchantmentFocusedStrikes(int par1) {
        super(par1, 5, EnumEnchantmentType.weapon);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return par1Enchantment.field_77352_x == Enchantment.field_77347_r.field_77352_x || par1Enchantment.field_77352_x == ThaumcraftApi.enchantRepair || par1Enchantment.field_77352_x == Enchantment.field_77337_m.field_77352_x || par1Enchantment.field_77352_x == Enchantment.field_77335_o.field_77352_x;
    }
}

