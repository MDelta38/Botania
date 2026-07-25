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

public class WGEnchantInvisibleGear
extends Enchantment {
    public WGEnchantInvisibleGear(int id) {
        super(id, 0, EnumEnchantmentType.all);
        this.func_77322_b("wg.invisibleGear");
    }

    public int func_77321_a(int lvl) {
        return 6;
    }

    public int func_77317_b(int lvl) {
        return this.func_77321_a(lvl) + 20;
    }

    public int func_77325_b() {
        return 2;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}

