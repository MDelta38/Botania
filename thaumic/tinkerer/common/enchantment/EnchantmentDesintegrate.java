/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  thaumcraft.common.config.Config
 */
package thaumic.tinkerer.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import thaumcraft.common.config.Config;
import thaumic.tinkerer.common.enchantment.EnchantmentMod;

public class EnchantmentDesintegrate
extends EnchantmentMod {
    protected EnchantmentDesintegrate(int par1) {
        super(par1, 1, EnumEnchantmentType.digger);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return par1Enchantment.field_77352_x == Enchantment.field_77347_r.field_77352_x || par1Enchantment.field_77352_x == Config.enchRepair.field_77352_x;
    }
}

