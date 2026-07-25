/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Event
 */
package appeng.api.events;

import appeng.api.features.ILocatable;
import cpw.mods.fml.common.eventhandler.Event;

public class LocatableEventAnnounce
extends Event {
    public final ILocatable target;
    public final LocatableEvent change;

    public LocatableEventAnnounce(ILocatable o, LocatableEvent ev) {
        this.target = o;
        this.change = ev;
    }

    public static enum LocatableEvent {
        Register,
        Unregister;

    }
}

