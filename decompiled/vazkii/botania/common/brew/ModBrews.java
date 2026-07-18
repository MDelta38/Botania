/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 */
package vazkii.botania.common.brew;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.common.brew.BrewMod;
import vazkii.botania.common.brew.BrewModPotion;
import vazkii.botania.common.brew.ModPotions;

public class ModBrews {
    public static Brew speed;
    public static Brew strength;
    public static Brew haste;
    public static Brew healing;
    public static Brew jumpBoost;
    public static Brew regen;
    public static Brew regenWeak;
    public static Brew resistance;
    public static Brew fireResistance;
    public static Brew waterBreathing;
    public static Brew invisibility;
    public static Brew nightVision;
    public static Brew absorption;
    public static Brew allure;
    public static Brew soulCross;
    public static Brew featherfeet;
    public static Brew emptiness;
    public static Brew bloodthirst;
    public static Brew overload;
    public static Brew clear;
    public static Brew warpWard;

    public static void init() {
        speed = new BrewMod("speed", 5879807, 4000, new PotionEffect(Potion.field_76424_c.field_76415_H, 1800, 1));
        strength = new BrewMod("strength", 0xEE3F3F, 4000, new PotionEffect(Potion.field_76420_g.field_76415_H, 1800, 1));
        haste = new BrewMod("haste", 16032818, 4000, new PotionEffect(Potion.field_76422_e.field_76415_H, 1800, 1));
        healing = new BrewMod("healing", 16735948, 6000, new PotionEffect(Potion.field_76432_h.field_76415_H, 1, 1));
        jumpBoost = new BrewMod("jumpBoost", 3339373, 4000, new PotionEffect(Potion.field_76430_j.field_76415_H, 1800, 1));
        regen = new BrewMod("regen", 16606344, 7000, new PotionEffect(Potion.field_76428_l.field_76415_H, 500, 1));
        regenWeak = new BrewMod("regenWeak", 16606344, 9000, new PotionEffect(Potion.field_76428_l.field_76415_H, 2400, 0));
        resistance = new BrewMod("resistance", 11816471, 4000, new PotionEffect(Potion.field_76429_m.field_76415_H, 1800, 1));
        fireResistance = new BrewMod("fireResistance", 16279808, 4000, new PotionEffect(Potion.field_76426_n.field_76415_H, 9600, 0));
        waterBreathing = new BrewMod("waterBreathing", 8693711, 4000, new PotionEffect(Potion.field_76427_o.field_76415_H, 9600, 0));
        invisibility = new BrewMod("invisibility", 0xAEAEAE, 8000, new PotionEffect(Potion.field_76441_p.field_76415_H, 9600, 0));
        nightVision = new BrewMod("nightVision", 8145899, 4000, new PotionEffect(Potion.field_76439_r.field_76415_H, 9600, 0));
        absorption = new BrewMod("absorption", 15919907, 7000, new PotionEffect(Potion.field_76444_x.field_76415_H, 1800, 3)).setNotBloodPendantInfusable().setNotIncenseInfusable();
        overload = new BrewMod("overload", 0x232323, 12000, new PotionEffect(Potion.field_76420_g.field_76415_H, 1800, 3), new PotionEffect(Potion.field_76424_c.field_76415_H, 1800, 2), new PotionEffect(Potion.field_76437_t.field_76415_H, 3600, 2), new PotionEffect(Potion.field_76438_s.field_76415_H, 200, 2));
        soulCross = new BrewModPotion("soulCross", 10000, new PotionEffect(ModPotions.soulCross.field_76415_H, 1800, 0));
        featherfeet = new BrewModPotion("featherFeet", 7000, new PotionEffect(ModPotions.featherfeet.field_76415_H, 1800, 0));
        emptiness = new BrewModPotion("emptiness", 30000, new PotionEffect(ModPotions.emptiness.field_76415_H, 7200, 0));
        bloodthirst = new BrewModPotion("bloodthirst", 20000, new PotionEffect(ModPotions.bloodthrst.field_76415_H, 7200, 0));
        allure = new BrewModPotion("allure", 2000, new PotionEffect(ModPotions.allure.field_76415_H, 4800, 0));
        clear = new BrewModPotion("clear", 4000, new PotionEffect(ModPotions.clear.field_76415_H, 0, 0));
    }

    public static void initTC() {
        Potion warpWardPotion = null;
        for (Potion potion : Potion.field_76425_a) {
            if (potion == null || !potion.func_76393_a().equals("potion.warpward")) continue;
            warpWardPotion = potion;
            break;
        }
        if (warpWardPotion != null) {
            warpWard = new BrewMod("warpWard", 0xFBBDFF, 25000, new PotionEffect(warpWardPotion.field_76415_H, 12000, 0)).setNotBloodPendantInfusable();
        }
    }
}

