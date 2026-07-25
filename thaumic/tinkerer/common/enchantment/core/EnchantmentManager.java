/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ArrayListMultimap
 *  com.google.common.collect.Multimap
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraft.item.ItemStack
 *  thaumcraft.api.aspects.Aspect
 *  thaumcraft.api.aspects.AspectList
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.lib.research.ResearchManager
 */
package thaumic.tinkerer.common.enchantment.core;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.research.ResearchManager;
import thaumic.tinkerer.common.core.helper.MiscHelper;
import thaumic.tinkerer.common.enchantment.ModEnchantments;
import thaumic.tinkerer.common.enchantment.core.EnchantmentData;
import thaumic.tinkerer.common.enchantment.core.IEnchantmentRule;
import thaumic.tinkerer.common.enchantment.core.rule.BasicCompatibilityRule;

public final class EnchantmentManager {
    public static final Map<Integer, Map<Integer, EnchantmentData>> enchantmentData = new HashMap<Integer, Map<Integer, EnchantmentData>>();
    public static final Multimap<Integer, IEnchantmentRule> rules = ArrayListMultimap.create();

    public static void initEnchantmentData() {
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77332_c, "ttinkerer:textures/enchants/protection.png", true, new AspectList().add(Aspect.EARTH, 10).add(Aspect.ENTROPY, 7));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77329_d, "ttinkerer:textures/enchants/fireProtection.png", true, new AspectList().add(Aspect.FIRE, 10).add(Aspect.ENTROPY, 3).add(Aspect.WATER, 4));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77330_e, "ttinkerer:textures/enchants/featherFalling.png", true, new AspectList().add(Aspect.AIR, 16).add(Aspect.ORDER, 5));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77327_f, "ttinkerer:textures/enchants/blastProtection.png", true, new AspectList().add(Aspect.EARTH, 5).add(Aspect.FIRE, 5).add(Aspect.ENTROPY, 8));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77328_g, "ttinkerer:textures/enchants/projectileProtection.png", true, new AspectList().add(Aspect.AIR, 10).add(Aspect.ENTROPY, 7));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77340_h, "ttinkerer:textures/enchants/respiration.png", true, new AspectList().add(Aspect.WATER, 10).add(Aspect.AIR, 8).add(Aspect.ORDER, 5));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77341_i, "ttinkerer:textures/enchants/aquaAffinity.png", true, new AspectList().add(Aspect.WATER, 25).add(Aspect.ORDER, 20).add(Aspect.EARTH, 5));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_92091_k, "ttinkerer:textures/enchants/thorns.png", true, new AspectList().add(Aspect.EARTH, 10).add(Aspect.ENTROPY, 12));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77338_j, "ttinkerer:textures/enchants/sharpness.png", true, new AspectList().add(Aspect.ORDER, 10));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77339_k, "ttinkerer:textures/enchants/smite.png", true, new AspectList().add(Aspect.ORDER, 5).add(Aspect.AIR, 5));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77336_l, "ttinkerer:textures/enchants/baneOfAwfulTurds.png", true, new AspectList().add(Aspect.ORDER, 5).add(Aspect.FIRE, 5));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77337_m, "ttinkerer:textures/enchants/knockback.png", true, new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.AIR, 10));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77334_n, "ttinkerer:textures/enchants/fireAspect.png", true, new AspectList().add(Aspect.FIRE, 15).add(Aspect.EARTH, 4));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77335_o, "ttinkerer:textures/enchants/looting.png", true, new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77349_p, "ttinkerer:textures/enchants/efficiency.png", true, new AspectList().add(Aspect.ENTROPY, 12).add(Aspect.EARTH, 4));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77348_q, "ttinkerer:textures/enchants/silkTouch.png", true, new AspectList().add(Aspect.ORDER, 50).add(Aspect.EARTH, 10).add(Aspect.ENTROPY, 10));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77347_r, "ttinkerer:textures/enchants/unbreaking.png", true, new AspectList().add(Aspect.ORDER, 15).add(Aspect.WATER, 8).add(Aspect.EARTH, 8));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77346_s, "ttinkerer:textures/enchants/fortune.png", true, new AspectList().add(Aspect.AIR, 10).add(Aspect.FIRE, 10).add(Aspect.WATER, 10).add(Aspect.EARTH, 10).add(Aspect.ORDER, 15).add(Aspect.ENTROPY, 15));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77345_t, "ttinkerer:textures/enchants/power.png", true, new AspectList().add(Aspect.EARTH, 5).add(Aspect.ORDER, 10));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77344_u, "ttinkerer:textures/enchants/punch.png", true, new AspectList().add(Aspect.AIR, 4).add(Aspect.EARTH, 10).add(Aspect.ENTROPY, 5));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77343_v, "ttinkerer:textures/enchants/flame.png", true, new AspectList().add(Aspect.ENTROPY, 5).add(Aspect.FIRE, 20).add(Aspect.EARTH, 5));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_77342_w, "ttinkerer:textures/enchants/infinity.png", true, new AspectList().add(Aspect.ENTROPY, 40).add(Aspect.ORDER, 40).add(Aspect.EARTH, 10));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_151369_A, "ttinkerer:textures/enchants/lure.png", true, new AspectList().add(Aspect.WATER, 20).add(Aspect.BEAST, 20));
        EnchantmentManager.registerExponentialCostData(Enchantment.field_151370_z, "ttinkerer:textures/enchants/luckOfTheSea.png", true, new AspectList().add(Aspect.ENTROPY, 20).add(Aspect.WATER, 20));
        EnchantmentManager.registerExponentialCostData(Config.enchHaste, "ttinkerer:textures/enchants/haste.png", true, new AspectList().add(Aspect.AIR, 10).add(Aspect.ENTROPY, 5).add(Aspect.EARTH, 5));
        EnchantmentManager.registerExponentialCostData(Config.enchRepair, "ttinkerer:textures/enchants/repair.png", true, new AspectList().add(Aspect.WATER, 20).add(Aspect.FIRE, 20).add(Aspect.EARTH, 20).add(Aspect.AIR, 20).add(Aspect.ORDER, 20).add(Aspect.ENTROPY, 5));
        EnchantmentManager.registerExponentialCostData(ModEnchantments.ascentBoost, "ttinkerer:textures/enchants/ascentBoost.png", false, new AspectList().add(Aspect.ENTROPY, 8).add(Aspect.AIR, 10), "TTENCH_ASCENT_BOOST");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.slowFall, "ttinkerer:textures/enchants/slowFall.png", false, new AspectList().add(Aspect.ORDER, 8).add(Aspect.AIR, 10), "TTENCH_SLOW_FALL");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.autoSmelt, "ttinkerer:textures/enchants/autoSmelt.png", false, new AspectList().add(Aspect.ENTROPY, 20).add(Aspect.FIRE, 30), "TTENCH_AUTO_SMELT");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.desintegrate, "ttinkerer:textures/enchants/desintegrate.png", false, new AspectList().add(Aspect.ENTROPY, 25).add(Aspect.AIR, 10).add(Aspect.EARTH, 10), "TTENCH_DESINTEGRATE");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.quickDraw, "ttinkerer:textures/enchants/quickDraw.png", false, new AspectList().add(Aspect.ORDER, 10).add(Aspect.AIR, 10).add(Aspect.WATER, 5), "TTENCH_QUICK_DRAW");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.vampirism, "ttinkerer:textures/enchants/vamprisim.png", false, new AspectList().add(Aspect.ENTROPY, 8).add(Aspect.FIRE, 10).add(Aspect.WATER, 10), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.focusedStrike, "ttinkerer:textures/enchants/focusedStrikes.png", false, new AspectList().add(Aspect.ORDER, 12).add(Aspect.AIR, 10).add(Aspect.WATER, 10), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.dispersedStrikes, "ttinkerer:textures/enchants/dispersedStrikes.png", false, new AspectList().add(Aspect.ENTROPY, 12).add(Aspect.FIRE, 10).add(Aspect.EARTH, 10), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.dispersedStrikes, "ttinkerer:textures/enchants/dispersedStrikes.png", false, new AspectList().add(Aspect.ENTROPY, 12).add(Aspect.FIRE, 10).add(Aspect.EARTH, 10), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.valiance, "ttinkerer:textures/enchants/valiance.png", false, new AspectList().add(Aspect.ORDER, 12).add(Aspect.FIRE, 10).add(Aspect.EARTH, 10), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.finalStrike, "ttinkerer:textures/enchants/finalStrike.png", false, new AspectList().add(Aspect.ENTROPY, 16).add(Aspect.FIRE, 16), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.tunnel, "ttinkerer:textures/enchants/tunnel.png", false, new AspectList().add(Aspect.EARTH, 16).add(Aspect.ORDER, 16), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.shatter, "ttinkerer:textures/enchants/shatter.png", false, new AspectList().add(Aspect.EARTH, 16).add(Aspect.ENTROPY, 16), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.shockwave, "ttinkerer:textures/enchants/shockwave.png", false, new AspectList().add(Aspect.EARTH, 16).add(Aspect.AIR, 16), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerExponentialCostData(ModEnchantments.pounce, "ttinkerer:textures/enchants/pounce.png", false, new AspectList().add(Aspect.EARTH, 16).add(Aspect.AIR, 16), "TTENCH_VAMPIRISM");
        EnchantmentManager.registerCompatibilityRules();
        EnchantmentManager.registerExtraRules();
    }

    public static boolean canApply(ItemStack stack, Enchantment enchant, List<Integer> currentEnchants) {
        if (!enchant.func_92089_a(stack) || !enchant.field_77351_y.func_77557_a(stack.func_77973_b()) || currentEnchants.contains(enchant.field_77352_x)) {
            return false;
        }
        for (IEnchantmentRule rule : rules.get((Object)enchant.field_77352_x)) {
            if (!rule.cantApplyAlongside(currentEnchants)) continue;
            return false;
        }
        return true;
    }

    public static boolean canEnchantmentBeUsed(String player, Enchantment enchant) {
        if (!enchantmentData.containsKey(enchant.field_77352_x)) {
            return false;
        }
        EnchantmentData data = enchantmentData.get(enchant.field_77352_x).get(1);
        return data.research.isEmpty() || ResearchManager.isResearchComplete((String)player, (String)data.research);
    }

    public static void registerExponentialCostData(Enchantment enchantment, String texture, boolean vanilla, AspectList level1Aspects) {
        EnchantmentManager.registerExponentialCostData(enchantment, texture, vanilla, level1Aspects, "");
    }

    public static void registerExponentialCostData(Enchantment enchantment, String texture, boolean vanilla, AspectList level1Aspects, String research) {
        for (double i = (double)enchantment.func_77319_d(); i <= (double)enchantment.func_77325_b(); i += 1.0) {
            EnchantmentData data = new EnchantmentData(texture, vanilla, MiscHelper.multiplyAspectList(level1Aspects, i * (1.0 + i * 0.2)), research);
            EnchantmentManager.registerData(enchantment.field_77352_x, (int)i, data);
        }
    }

    private static void registerData(int enchantment, int level, EnchantmentData data) {
        if (!enchantmentData.containsKey(enchantment)) {
            enchantmentData.put(enchantment, new HashMap());
        }
        enchantmentData.get(enchantment).put(level, data);
    }

    private static void registerCompatibilityRules() {
        for (Enchantment ench : Enchantment.field_77331_b) {
            if (ench == null) continue;
            for (Enchantment ench1 : Enchantment.field_77331_b) {
                if (ench1 == null || ench == ench1 || ench.func_77326_a(ench1) && ench1.func_77326_a(ench)) continue;
                rules.put((Object)ench.field_77352_x, (Object)new BasicCompatibilityRule(ench1));
            }
        }
    }

    private static void registerExtraRules() {
    }
}

