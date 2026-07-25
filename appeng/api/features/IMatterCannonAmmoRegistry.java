/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package appeng.api.features;

import net.minecraft.item.ItemStack;

public interface IMatterCannonAmmoRegistry {
    public void registerAmmo(ItemStack var1, double var2);

    public float getPenetration(ItemStack var1);
}

