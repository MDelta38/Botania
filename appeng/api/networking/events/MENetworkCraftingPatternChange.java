/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.events;

import appeng.api.networking.IGridNode;
import appeng.api.networking.crafting.ICraftingProvider;
import appeng.api.networking.events.MENetworkEvent;

public class MENetworkCraftingPatternChange
extends MENetworkEvent {
    public final ICraftingProvider provider;
    public final IGridNode node;

    public MENetworkCraftingPatternChange(ICraftingProvider p, IGridNode n) {
        this.provider = p;
        this.node = n;
    }
}

