/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.config;

import java.util.HashMap;
import net.minecraft.item.ItemStack;

public enum Upgrades {
    CAPACITY(0),
    REDSTONE(0),
    CRAFTING(0),
    FUZZY(1),
    SPEED(1),
    INVERTER(1);

    public final int myTier;
    public final HashMap<ItemStack, Integer> supportedMax = new HashMap();

    private Upgrades(int tier) {
        this.myTier = tier;
    }

    public HashMap<ItemStack, Integer> getSupported() {
        return this.supportedMax;
    }

    public void registerItem(ItemStack myItem, int maxSupported) {
        if (myItem != null) {
            this.supportedMax.put(myItem, maxSupported);
        }
    }
}

