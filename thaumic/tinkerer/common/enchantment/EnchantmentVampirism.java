/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.ThaumcraftApi
 */
package thaumic.tinkerer.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ThaumcraftApi;
import thaumic.tinkerer.common.enchantment.EnchantmentMod;
import thaumic.tinkerer.common.item.ItemBloodSword;

public class EnchantmentVampirism
extends EnchantmentMod {
    protected EnchantmentVampirism(int par1) {
        super(par1, 2, EnumEnchantmentType.weapon);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return par1Enchantment.field_77352_x != Enchantment.field_77334_n.field_77352_x && par1Enchantment.field_77352_x != Enchantment.field_77337_m.field_77352_x || par1Enchantment.field_77352_x == Enchantment.field_77347_r.field_77352_x || par1Enchantment.field_77352_x == ThaumcraftApi.enchantRepair;
    }

    public boolean func_92089_a(ItemStack par1ItemStack) {
        return super.func_92089_a(par1ItemStack) && par1ItemStack.func_77973_b().getClass() != ItemBloodSword.class;
    }
}

