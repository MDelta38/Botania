/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking;

import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.networking.IGridStorage;

public interface IGridCache {
    public void onUpdateTick();

    public void removeNode(IGridNode var1, IGridHost var2);

    public void addNode(IGridNode var1, IGridHost var2);

    public void onSplit(IGridStorage var1);

    public void onJoin(IGridStorage var1);

    public void populateGridStorage(IGridStorage var1);
}

