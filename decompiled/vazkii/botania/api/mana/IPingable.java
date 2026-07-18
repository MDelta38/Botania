/*
 * Decompiled with CFR 0.152.
 */
package vazkii.botania.api.mana;

import java.util.UUID;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.IIdentifiable;

public interface IPingable
extends IIdentifiable {
    public void pingback(IManaBurst var1, UUID var2);
}

