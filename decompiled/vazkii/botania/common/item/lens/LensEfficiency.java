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

public class LensEfficiency
extends Lens {
    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        props.manaLossPerTick /= 5.0f;
        props.ticksBeforeManaLoss = (int)((float)props.ticksBeforeManaLoss * 1.1f);
    }
}

