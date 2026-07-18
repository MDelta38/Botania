/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.common.item.lens;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.common.item.lens.Lens;

public class LensSpeed
extends Lens {
    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        props.motionModifier *= 2.0f;
        props.maxMana = (int)((float)props.maxMana * 0.75f);
        props.ticksBeforeManaLoss = (int)((float)props.ticksBeforeManaLoss / 3.0f);
        props.manaLossPerTick *= 2.0f;
    }
}

