/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.IGoggles
 *  thaumcraft.api.nodes.IRevealer
 */
package witchinggadgets.common.magic;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import thaumcraft.api.IGoggles;
import thaumcraft.api.nodes.IRevealer;

public class WGEnchantUnveiling
extends Enchantment {
    public WGEnchantUnveiling(int id) {
        super(id, 0, EnumEnchantmentType.all);
        this.func_77322_b("wg.unveiling");
    }

    public int func_77321_a(int lvl) {
        return 6;
    }

    public int func_77317_b(int lvl) {
        return this.func_77321_a(lvl) + 20;
    }

    public int func_77325_b() {
        return 1;
    }

    public boolean func_92089_a(ItemStack stack) {
        return stack != null && stack.func_77973_b() instanceof ItemArmor && ((ItemArmor)stack.func_77973_b()).field_77881_a == 0 && (stack.func_77973_b() instanceof IRevealer || stack.func_77973_b() instanceof IGoggles);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}

