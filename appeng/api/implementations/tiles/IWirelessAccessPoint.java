/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.implementations.tiles;

import appeng.api.networking.IGrid;
import appeng.api.networking.security.IActionHost;
import appeng.api.util.DimensionalCoord;

public interface IWirelessAccessPoint
extends IActionHost {
    public DimensionalCoord getLocation();

    public double getRange();

    public boolean isActive();

    public IGrid getGrid();
}

