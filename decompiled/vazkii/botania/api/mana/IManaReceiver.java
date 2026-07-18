/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.api.mana;

import vazkii.botania.api.mana.IManaBlock;

public interface IManaReceiver
extends IManaBlock {
    public boolean isFull();

    public void recieveMana(int var1);

    public boolean canRecieveManaFromBursts();
}

