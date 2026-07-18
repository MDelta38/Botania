/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.api.mana;

import vazkii.botania.api.mana.IDirectioned;
import vazkii.botania.api.mana.IManaBlock;
import vazkii.botania.api.mana.IPingable;

public interface IManaSpreader
extends IManaBlock,
IPingable,
IDirectioned {
    public void setCanShoot(boolean var1);

    public int getBurstParticleTick();

    public void setBurstParticleTick(int var1);

    public int getLastBurstDeathTick();

    public void setLastBurstDeathTick(int var1);
}

