/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 */
package vazkii.botania.api.mana;

import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;

public interface ILensEffect {
    public void apply(ItemStack var1, BurstProperties var2);

    public boolean collideBurst(IManaBurst var1, MovingObjectPosition var2, boolean var3, boolean var4, ItemStack var5);

    public void updateBurst(IManaBurst var1, ItemStack var2);

    public boolean doParticles(IManaBurst var1, ItemStack var2);
}

