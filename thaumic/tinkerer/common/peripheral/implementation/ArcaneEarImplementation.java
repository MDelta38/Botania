/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  thaumcraft.common.tiles.TileSensor
 */
package thaumic.tinkerer.common.peripheral.implementation;

import thaumcraft.common.tiles.TileSensor;

public class ArcaneEarImplementation {
    public static Object[] getNote(TileSensor ear) {
        return new Double[]{ear.note};
    }

    public static Object[] setNote(TileSensor ear, byte note) {
        ear.note = note;
        return new Object[0];
    }
}

