/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.implementations.parts;

import appeng.api.implementations.parts.IPartMonitor;
import appeng.api.networking.IGridHost;
import appeng.api.parts.IPart;
import appeng.api.storage.data.IAEStack;
import appeng.api.util.INetworkToolAgent;

public interface IPartStorageMonitor
extends IPartMonitor,
IPart,
IGridHost,
INetworkToolAgent {
    public IAEStack getDisplayed();

    public boolean isLocked();
}

