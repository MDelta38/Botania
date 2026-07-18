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

public class LensPower
extends Lens {
    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        props.maxMana *= 2;
        props.motionModifier *= 0.85f;
        props.manaLossPerTick *= 2.0f;
    }
}

