/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumic.tinkerer.common.enchantment.EnchantmentVampirism
 *  thaumic.tinkerer.common.enchantment.core.EnchantmentManager
 *  thaumic.tinkerer.common.lib.LibEnchantIDs
 */
package flaxbeard.thaumicexploration.integration;

import flaxbeard.thaumicexploration.ThaumicExploration;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumic.tinkerer.common.enchantment.EnchantmentVampirism;
import thaumic.tinkerer.common.enchantment.core.EnchantmentManager;
import thaumic.tinkerer.common.lib.LibEnchantIDs;

public class TTIntegration {
    public static void registerEnchants() {
        EnchantmentManager.registerExponentialCostData((Enchantment)ThaumicExploration.enchantmentBinding, (String)"thaumicexploration:textures/tabs/binding.png", (boolean)false, (AspectList)new AspectList().add(Aspect.ENTROPY, 15).add(Aspect.ORDER, 15), (String)"ENCHBINDING");
        EnchantmentManager.registerExponentialCostData((Enchantment)ThaumicExploration.enchantmentNightVision, (String)"thaumicexploration:textures/tabs/nightvision.png", (boolean)false, (AspectList)new AspectList().add(Aspect.ENTROPY, 20).add(Aspect.FIRE, 10).add(Aspect.ORDER, 20), (String)"ENCHNIGHTVISION");
        EnchantmentManager.registerExponentialCostData((Enchantment)ThaumicExploration.enchantmentDisarm, (String)"thaumicexploration:textures/tabs/disarm.png", (boolean)false, (AspectList)new AspectList().add(Aspect.AIR, 12).add(Aspect.ORDER, 7).add(Aspect.ENTROPY, 7), (String)"ENCHDISARM");
    }

    public static boolean canApplyTogether(Enchantment par1Enchantment, Enchantment par2Enchantment) {
        if (par2Enchantment == ThaumicExploration.enchantmentBinding) {
            return !(par1Enchantment instanceof EnchantmentVampirism);
        }
        return true;
    }

    public static boolean okVersion() {
        String ver = "unspecified".substring("unspecified".lastIndexOf("-") + 1);
        int version = Integer.parseInt(ver);
        System.out.println("!THAUMIC TINKERER VERSION: " + version + "!");
        return version > 71;
    }

    public static String keyRepairer() {
        return "REPAIRER";
    }

    public static int getAscentLevel(EntityPlayer player) {
        int boost = EnchantmentHelper.func_77511_a((int)LibEnchantIDs.idAscentBoost, (ItemStack[])player.field_71071_by.field_70460_b);
        return boost;
    }

    public static String keyEnchanter() {
        return "ENCHANTER";
    }
}

