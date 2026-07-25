/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Loader
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentOxygen
 *  net.minecraft.enchantment.EnchantmentWaterWorker
 *  net.minecraft.enchantment.EnumEnchantmentType
 *  net.minecraft.item.ItemStack
 */
package flaxbeard.thaumicexploration.enchantment;

import cpw.mods.fml.common.Loader;
import flaxbeard.thaumicexploration.ThaumicExploration;
import flaxbeard.thaumicexploration.integration.TTIntegration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentOxygen;
import net.minecraft.enchantment.EnchantmentWaterWorker;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.ItemStack;

public class EnchantmentNightVision
extends Enchantment {
    public EnchantmentNightVision(int par1, int par2) {
        super(par1, par2, EnumEnchantmentType.armor_head);
        this.func_77322_b("nightVision");
    }

    public int func_77321_a(int par1) {
        return 40;
    }

    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return false;
    }

    public boolean func_77326_a(Enchantment par1Enchantment) {
        if (Loader.isModLoaded((String)"ThaumicTinkerer") && !TTIntegration.canApplyTogether(par1Enchantment, ThaumicExploration.enchantmentNightVision)) {
            return false;
        }
        return !(par1Enchantment instanceof EnchantmentOxygen) && !(par1Enchantment instanceof EnchantmentWaterWorker);
    }

    public int func_77325_b() {
        return 1;
    }
}

