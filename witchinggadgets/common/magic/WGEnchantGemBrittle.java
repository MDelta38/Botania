/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemStack
 */
package witchinggadgets.common.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class WGEnchantGemBrittle
extends Enchantment {
    public WGEnchantGemBrittle(int id, int weight) {
        super(id, weight, EnumEnchantmentType.all);
        this.func_77322_b("wg.gemstoneBrittle");
    }

    public int func_77321_a(int par1) {
        return 10 + 11 * (par1 - 1);
    }

    public int func_77317_b(int par1) {
        return super.func_77321_a(par1) + 50;
    }

    public int func_77325_b() {
        return 3;
    }

    public boolean func_92089_a(ItemStack stack) {
        return false;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    public boolean isAllowedOnBooks() {
        return false;
    }
}

