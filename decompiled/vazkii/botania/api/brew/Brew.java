/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.potion.PotionEffect
 */
package vazkii.botania.api.brew;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;

public class Brew {
    String key;
    String name;
    int color;
    int cost;
    List<PotionEffect> effects;
    boolean canInfuseBloodPendant = true;
    boolean canInfuseIncense = true;

    public Brew(String key, String name, int color, int cost, PotionEffect ... effects) {
        this.key = key;
        this.name = name;
        this.color = color;
        this.cost = cost;
        this.effects = new ArrayList<PotionEffect>(Arrays.asList(effects));
    }

    public Brew setNotBloodPendantInfusable() {
        this.canInfuseBloodPendant = false;
        return this;
    }

    public Brew setNotIncenseInfusable() {
        this.canInfuseIncense = false;
        return this;
    }

    public boolean canInfuseBloodPendant() {
        return this.canInfuseBloodPendant;
    }

    public boolean canInfuseIncense() {
        return this.canInfuseIncense;
    }

    public String getKey() {
        return this.key;
    }

    public String getUnlocalizedName() {
        return this.name;
    }

    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName();
    }

    public int getColor(ItemStack stack) {
        return this.color;
    }

    public int getManaCost() {
        return this.cost;
    }

    public int getManaCost(ItemStack stack) {
        return this.getManaCost();
    }

    public List<PotionEffect> getPotionEffects(ItemStack stack) {
        return this.effects;
    }
}

