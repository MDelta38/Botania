/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.MovingObjectPosition
 */
package vazkii.botania.common.item.lens;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.IManaSpreader;

public class Lens {
    public void apply(ItemStack stack, BurstProperties props) {
    }

    public boolean collideBurst(IManaBurst burst, EntityThrowable entity, MovingObjectPosition pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        return dead;
    }

    public void updateBurst(IManaBurst burst, EntityThrowable entity, ItemStack stack) {
    }

    public boolean allowBurstShooting(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        return true;
    }

    public void onControlledSpreaderTick(ItemStack stack, IManaSpreader spreader, boolean redstone) {
    }

    public void onControlledSpreaderPulse(ItemStack stack, IManaSpreader spreader, boolean redstone) {
    }
}

