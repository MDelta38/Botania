/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.events;

import appeng.api.networking.events.MENetworkEvent;
import appeng.api.storage.IMEMonitor;
import appeng.api.storage.StorageChannel;

public class MENetworkStorageEvent
extends MENetworkEvent {
    public final IMEMonitor monitor;
    public final StorageChannel channel;

    public MENetworkStorageEvent(IMEMonitor o, StorageChannel chan) {
        this.monitor = o;
        this.channel = chan;
    }
}

