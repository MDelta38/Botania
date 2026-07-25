/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.pathing;

import appeng.api.networking.IGridCache;
import appeng.api.networking.pathing.ControllerState;

public interface IPathingGrid
extends IGridCache {
    public boolean isNetworkBooting();

    public ControllerState getControllerState();

    public void repath();
}

