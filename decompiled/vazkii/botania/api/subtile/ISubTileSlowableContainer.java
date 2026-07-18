/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.api.subtile;

import vazkii.botania.api.subtile.ISubTileContainer;

public interface ISubTileSlowableContainer
extends ISubTileContainer {
    public static final int SLOWDOWN_FACTOR_PODZOL = 5;
    public static final int SLOWDOWN_FACTOR_MYCEL = 10;

    public int getSlowdownFactor();
}

