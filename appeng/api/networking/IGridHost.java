/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.networking;

import appeng.api.networking.IGridNode;
import appeng.api.util.AECableType;
import net.minecraftforge.common.util.ForgeDirection;

public interface IGridHost {
    public IGridNode getGridNode(ForgeDirection var1);

    public AECableType getCableConnectionType(ForgeDirection var1);

    public void securityBreak();
}

