/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.storage;

import appeng.api.storage.IMEMonitorHandlerReceiver;
import appeng.api.storage.data.IAEStack;

public interface IBaseMonitor<T extends IAEStack> {
    public void addListener(IMEMonitorHandlerReceiver<T> var1, Object var2);

    public void removeListener(IMEMonitorHandlerReceiver<T> var1);
}

