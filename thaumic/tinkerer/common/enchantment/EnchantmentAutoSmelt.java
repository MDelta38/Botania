/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemStack
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigItems
 */
package thaumic.tinkerer.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigItems;
import thaumic.tinkerer.common.enchantment.EnchantmentMod;

public class EnchantmentAutoSmelt
extends EnchantmentMod {
    protected EnchantmentAutoSmelt(int par1) {
        super(par1, 1, EnumEnchantmentType.digger);
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        return par1Enchantment.field_77352_x == Enchantment.field_77347_r.field_77352_x || par1Enchantment.field_77352_x == Config.enchRepair.field_77352_x;
    }

    public boolean func_92089_a(ItemStack par1ItemStack) {
        return super.func_92089_a(par1ItemStack) && par1ItemStack.func_77973_b() != ConfigItems.itemAxeElemental;
    }
}

