/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.networking;

import appeng.api.networking.IGridNode;
import net.minecraftforge.common.util.ForgeDirection;

public interface IGridConnection {
    public IGridNode getOtherSide(IGridNode var1);

    public ForgeDirection getDirection(IGridNode var1);

    public void destroy();

    public IGridNode a();

    public IGridNode b();

    public boolean hasDirection();

    public int getUsedChannels();
}

