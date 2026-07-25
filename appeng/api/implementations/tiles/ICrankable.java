/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.implementations.tiles;

import net.minecraftforge.common.util.ForgeDirection;

public interface ICrankable {
    public boolean canTurn();

    public void applyTurn();

    public boolean canCrankAttach(ForgeDirection var1);
}

