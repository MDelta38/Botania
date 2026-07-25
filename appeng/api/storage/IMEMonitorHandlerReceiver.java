/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.networking.security.BaseActionSource;
import appeng.api.networking.storage.IBaseMonitor;
import appeng.api.storage.data.IAEStack;

public interface IMEMonitorHandlerReceiver<StackType extends IAEStack> {
    public boolean isValid(Object var1);

    public void postChange(IBaseMonitor<StackType> var1, Iterable<StackType> var2, BaseActionSource var3);

    public void onListUpdate();
}

