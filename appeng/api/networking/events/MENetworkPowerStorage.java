/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.events;

import appeng.api.networking.energy.IAEPowerStorage;
import appeng.api.networking.events.MENetworkEvent;

public class MENetworkPowerStorage
extends MENetworkEvent {
    public final IAEPowerStorage storage;
    public final PowerEventType type;

    public MENetworkPowerStorage(IAEPowerStorage t, PowerEventType y) {
        this.storage = t;
        this.type = y;
    }

    public static enum PowerEventType {
        REQUEST_POWER,
        PROVIDE_POWER;

    }
}

