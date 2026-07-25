/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  baubles.api.IBauble
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  travellersgear.api.TravellersGearAPI
 */
package witchinggadgets.common.magic;

import baubles.api.IBauble;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import travellersgear.api.TravellersGearAPI;

public class WGEnchantRideProtect
extends Enchantment {
    public WGEnchantRideProtect(int id) {
        super(id, 0, EnumEnchantmentType.armor_head);
        this.func_77322_b("wg.rideProtect");
    }

    public int func_77321_a(int lvl) {
        return 9;
    }

    public int func_77317_b(int lvl) {
        return this.func_77321_a(lvl) + 20;
    }

    public int func_77325_b() {
        return 1;
    }

    public boolean func_92089_a(ItemStack stack) {
        return stack != null && (stack.func_77973_b() instanceof ItemArmor || stack.func_77973_b() instanceof IBauble || TravellersGearAPI.isTravellersGear((ItemStack)stack));
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }
}

