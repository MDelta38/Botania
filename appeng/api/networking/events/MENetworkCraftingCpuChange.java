/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.events;

import appeng.api.networking.IGridNode;
import appeng.api.networking.events.MENetworkEvent;

public class MENetworkCraftingCpuChange
extends MENetworkEvent {
    public final IGridNode node;

    public MENetworkCraftingCpuChange(IGridNode n) {
        this.node = n;
    }
}

