/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.api.mana;

import vazkii.botania.api.mana.IDirectioned;

public interface IRedirectable
extends IDirectioned {
    public void setRotationX(float var1);

    public void setRotationY(float var1);

    public void commitRedirection();
}

