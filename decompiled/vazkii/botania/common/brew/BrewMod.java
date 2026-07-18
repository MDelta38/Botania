/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.potion.PotionEffect
 */
package vazkii.botania.common.brew;

import net.minecraft.potion.PotionEffect;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.brew.Brew;

public class BrewMod
extends Brew {
    public BrewMod(String key, int color, int cost, PotionEffect ... effects) {
        super(key, key, color, cost, effects);
        BotaniaAPI.registerBrew(this);
    }

    @Override
    public String getUnlocalizedName() {
        return "botania.brew." + super.getUnlocalizedName();
    }
}

