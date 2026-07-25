/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.spatial;

import appeng.api.networking.IGridCache;
import appeng.api.util.DimensionalCoord;

public interface ISpatialCache
extends IGridCache {
    public boolean hasRegion();

    public boolean isValidRegion();

    public DimensionalCoord getMin();

    public DimensionalCoord getMax();

    public long requiredPower();

    public float currentEfficiency();
}

