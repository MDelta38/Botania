/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking;

import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.util.IReadOnlyCollection;

public interface IMachineSet
extends IReadOnlyCollection<IGridNode> {
    public Class<? extends IGridHost> getMachineClass();
}

