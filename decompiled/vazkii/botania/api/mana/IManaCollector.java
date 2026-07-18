/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.api.mana;

import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IManaReceiver;

public interface IManaCollector
extends IManaReceiver {
    public void onClientDisplayTick();

    public float getManaYieldMultiplier(IManaBurst var1);

    public int getMaxMana();
}

