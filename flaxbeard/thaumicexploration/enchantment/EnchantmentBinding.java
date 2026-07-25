/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentFireAspect
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemStack
 */
package flaxbeard.thaumicexploration.enchantment;

import cpw.mods.fml.common.Loader;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.integration.TTIntegration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentFireAspect;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentBinding
extends Enchantment {
    public EnchantmentBinding(int par1, int par2) {
        super(par1, par2, EnumEnchantmentType.weapon);
        this.func_77322_b("binding");
    }

    public int func_77321_a(int par1) {
        return 10 + 20 * (par1 - 1);
    }

    public int func_77317_b(int par1) {
        return super.func_77321_a(par1) + 50;
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        if (Loader.isModLoaded((String)"ThaumicTinkerer") && !TTIntegration.canApplyTogether(par1Enchantment, ThaumicExploration.enchantmentBinding)) {
            return false;
        }
        return !(par1Enchantment instanceof EnchantmentFireAspect);
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    public int func_77325_b() {
        return 2;
    }
}

