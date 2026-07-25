/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 */
package witchinggadgets.common.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class WGEnchantStealth
extends Enchantment {
    public WGEnchantStealth(int id) {
        super(id, 0, EnumEnchantmentType.armor);
        this.func_77322_b("wg.stealth");
    }

    public int func_77321_a(int lvl) {
        return 5 + (lvl - 1) * 11;
    }

    public int func_77317_b(int lvl) {
        return this.func_77321_a(lvl) + 20;
    }

    public int func_77325_b() {
        return 5;
    }

    public boolean func_92089_a(ItemStack stack) {
        boolean b = stack != null && stack.func_77973_b() instanceof ItemArmor && (((ItemArmor)stack.func_77973_b()).field_77881_a == 2 || ((ItemArmor)stack.func_77973_b()).field_77881_a == 3);
        return b;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}

