/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package vazkii.botania.api.mana;

import net.minecraft.world.World;
import vazkii.botania.api.internal.IManaBurst;

public interface IManaTrigger {
    public void onBurstCollision(IManaBurst var1, World var2, int var3, int var4, int var5);
}

