/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.ticking;

import appeng.api.networking.IGridCache;
import appeng.api.networking.IGridNode;

public interface ITickManager
extends IGridCache {
    public boolean alertDevice(IGridNode var1);

    public boolean sleepDevice(IGridNode var1);

    public boolean wakeDevice(IGridNode var1);
}

