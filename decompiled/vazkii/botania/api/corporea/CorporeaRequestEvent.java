/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.eventhandler.Cancelable
 *  cpw.mods.fml.common.eventhandler.Event
 */
package vazkii.botania.api.corporea;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import vazkii.botania.api.corporea.ICorporeaSpark;

@Cancelable
public class CorporeaRequestEvent
extends Event {
    public final Object request;
    public final int count;
    public final ICorporeaSpark spark;
    public final boolean checkNBT;
    public final boolean realRequest;

    public CorporeaRequestEvent(Object request, int count, ICorporeaSpark spark, boolean nbt, boolean real) {
        this.request = request;
        this.count = count;
        this.spark = spark;
        this.checkNBT = nbt;
        this.realRequest = real;
    }
}

