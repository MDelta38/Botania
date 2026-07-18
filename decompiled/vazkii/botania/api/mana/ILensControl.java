/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.item.ItemStack
 */
package vazkii.botania.api.mana;

import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.ILens;
import vazkii.botania.api.mana.IManaSpreader;

public interface ILensControl
extends ILens {
    public boolean isControlLens(ItemStack var1);

    public boolean allowBurstShooting(ItemStack var1, IManaSpreader var2, boolean var3);

    public void onControlledSpreaderTick(ItemStack var1, IManaSpreader var2, boolean var3);

    public void onControlledSpreaderPulse(ItemStack var1, IManaSpreader var2, boolean var3);
}

