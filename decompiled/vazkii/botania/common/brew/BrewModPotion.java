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
import vazkii.botania.common.brew.BrewMod;

public class BrewModPotion
extends BrewMod {
    public BrewModPotion(String key, int cost, PotionEffect ... effects) {
        super(key, Potion.field_76425_a[effects[0].func_76456_a()].func_76401_j(), cost, effects);
    }
}

