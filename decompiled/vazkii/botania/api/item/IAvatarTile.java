/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.inventory.IInventory
 */
package vazkii.botania.api.item;

import net.minecraft.inventory.IInventory;
import vazkii.botania.api.mana.IManaReceiver;

public interface IAvatarTile
extends IInventory,
IManaReceiver {
    public int getElapsedFunctionalTicks();

    public boolean isEnabled();
}

