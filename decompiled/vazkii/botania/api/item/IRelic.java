/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.stats.Achievement
 */
package vazkii.botania.api.item;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;

public interface IRelic {
    public void bindToUsername(String var1, ItemStack var2);

    public String getSoulbindUsername(ItemStack var1);

    public void setBindAchievement(Achievement var1);

    public Achievement getBindAchievement();
}

