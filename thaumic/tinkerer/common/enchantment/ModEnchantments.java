/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.Enchantment
 *  net.minecraftforge.common.MinecraftForge
 */
package thaumic.tinkerer.common.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraftforge.common.MinecraftForge;
import thaumic.tinkerer.common.enchantment.EnchantmentAscentBoost;
import thaumic.tinkerer.common.enchantment.EnchantmentAutoSmelt;
import thaumic.tinkerer.common.enchantment.EnchantmentDesintegrate;
import thaumic.tinkerer.common.enchantment.EnchantmentDispersedStrikes;
import thaumic.tinkerer.common.enchantment.EnchantmentFinalStrike;
import thaumic.tinkerer.common.enchantment.EnchantmentFocusedStrikes;
import thaumic.tinkerer.common.enchantment.EnchantmentPounce;
import thaumic.tinkerer.common.enchantment.EnchantmentQuickDraw;
import thaumic.tinkerer.common.enchantment.EnchantmentShatter;
import thaumic.tinkerer.common.enchantment.EnchantmentShockwave;
import thaumic.tinkerer.common.enchantment.EnchantmentSlowFall;
import thaumic.tinkerer.common.enchantment.EnchantmentTunnel;
import thaumic.tinkerer.common.enchantment.EnchantmentValiance;
import thaumic.tinkerer.common.enchantment.EnchantmentVampirism;
import thaumic.tinkerer.common.enchantment.ModEnchantmentHandler;
import thaumic.tinkerer.common.lib.LibEnchantIDs;

public final class ModEnchantments {
    public static Enchantment ascentBoost;
    public static Enchantment slowFall;
    public static Enchantment autoSmelt;
    public static Enchantment desintegrate;
    public static Enchantment quickDraw;
    public static Enchantment vampirism;
    public static Enchantment dispersedStrikes;
    public static Enchantment filtration;
    public static Enchantment finalStrike;
    public static Enchantment focusedStrike;
    public static Enchantment imbued;
    public static Enchantment pounce;
    public static Enchantment resolute;
    public static Enchantment shatter;
    public static Enchantment shockwave;
    public static Enchantment tunnel;
    public static Enchantment valiance;

    public static void initEnchantments() {
        ascentBoost = new EnchantmentAscentBoost(LibEnchantIDs.idAscentBoost).func_77322_b("ttinkerer:ascentBoost");
        slowFall = new EnchantmentSlowFall(LibEnchantIDs.idSlowFall).func_77322_b("ttinkerer:slowFall");
        autoSmelt = new EnchantmentAutoSmelt(LibEnchantIDs.idAutoSmelt).func_77322_b("ttinkerer:autoSmelt");
        desintegrate = new EnchantmentDesintegrate(LibEnchantIDs.idDesintegrate).func_77322_b("ttinkerer:desintegrate");
        quickDraw = new EnchantmentQuickDraw(LibEnchantIDs.idQuickDraw).func_77322_b("ttinkerer:quickDraw");
        vampirism = new EnchantmentVampirism(LibEnchantIDs.idVampirism).func_77322_b("ttinkerer:vampirism");
        dispersedStrikes = new EnchantmentDispersedStrikes(LibEnchantIDs.dispersedStrikes).func_77322_b("ttinkerer:dispersedStrike");
        finalStrike = new EnchantmentFinalStrike(LibEnchantIDs.finalStrike).func_77322_b("ttinkerer:finalStrike");
        focusedStrike = new EnchantmentFocusedStrikes(LibEnchantIDs.focusedStrike).func_77322_b("ttinkerer:focusedStrike");
        pounce = new EnchantmentPounce(LibEnchantIDs.pounce).func_77322_b("ttinkerer:pounce");
        shatter = new EnchantmentShatter(LibEnchantIDs.shatter).func_77322_b("ttinkerer:shatter");
        shockwave = new EnchantmentShockwave(LibEnchantIDs.shockwave).func_77322_b("ttinkerer:shockwave");
        tunnel = new EnchantmentTunnel(LibEnchantIDs.tunnel).func_77322_b("ttinkerer:tunnel");
        valiance = new EnchantmentValiance(LibEnchantIDs.valiance).func_77322_b("ttinkerer:valiance");
        MinecraftForge.EVENT_BUS.register((Object)new ModEnchantmentHandler());
    }
}

