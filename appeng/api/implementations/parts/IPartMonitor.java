/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.implementations.parts;

import appeng.api.networking.IGridHost;
import appeng.api.parts.IPart;

public interface IPartMonitor
extends IPart,
IGridHost {
    public boolean isPowered();
}

