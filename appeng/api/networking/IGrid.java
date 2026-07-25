/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking;

import appeng.api.networking.IGridCache;
import appeng.api.networking.IGridHost;
import appeng.api.networking.IGridNode;
import appeng.api.networking.IMachineSet;
import appeng.api.networking.events.MENetworkEvent;
import appeng.api.util.IReadOnlyCollection;

public interface IGrid {
    public <C extends IGridCache> C getCache(Class<? extends IGridCache> var1);

    public MENetworkEvent postEvent(MENetworkEvent var1);

    public MENetworkEvent postEventTo(IGridNode var1, MENetworkEvent var2);

    public IReadOnlyCollection<Class<? extends IGridHost>> getMachinesClasses();

    public IMachineSet getMachines(Class<? extends IGridHost> var1);

    public IReadOnlyCollection<IGridNode> getNodes();

    public boolean isEmpty();

    public IGridNode getPivot();
}

