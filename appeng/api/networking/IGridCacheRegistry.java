/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking;

import appeng.api.networking.IGrid;
import appeng.api.networking.IGridCache;
import java.util.HashMap;

public interface IGridCacheRegistry {
    public void registerGridCache(Class<? extends IGridCache> var1, Class<? extends IGridCache> var2);

    public HashMap<Class<? extends IGridCache>, IGridCache> createCacheInstance(IGrid var1);
}

