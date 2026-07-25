/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemAxe
 *  net.minecraft.item.ItemStack
 */
package witchinggadgets.common.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class WGEnchantBackstab
extends Enchantment {
    public WGEnchantBackstab(int id) {
        super(id, 0, EnumEnchantmentType.weapon);
        this.func_77322_b("wg.backstab");
    }

    public int func_77321_a(int lvl) {
        return 5 + (lvl - 1) * 11;
    }

    public int func_77317_b(int lvl) {
        return this.func_77321_a(lvl) + 20;
    }

    public int func_77325_b() {
        return 3;
    }

    public boolean func_77326_a(Enchantment ench) {
        return ench != Enchantment.field_77339_k && ench != Enchantment.field_77336_l;
    }

    public boolean func_92089_a(ItemStack stack) {
        return stack != null && stack.func_77973_b() instanceof ItemAxe || super.func_92089_a(stack);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}

