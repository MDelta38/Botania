/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.events;

import appeng.api.networking.IGridHost;
import appeng.api.networking.events.MENetworkEvent;

public class MENetworkSpatialEvent
extends MENetworkEvent {
    public final IGridHost host;
    public final double spatialEnergyUsage;

    public MENetworkSpatialEvent(IGridHost SpatialIO, double EnergyUsage) {
        this.host = SpatialIO;
        this.spatialEnergyUsage = EnergyUsage;
    }
}

