/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  thaumcraft.common.tiles.TileSensor
 */
package thaumic.tinkerer.common.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import thaumcraft.common.tiles.TileSensor;

public class PeripheralArcaneEar
implements IPeripheral {
    TileSensor ear;

    public PeripheralArcaneEar(TileSensor ear) {
        this.ear = ear;
    }

    public String getType() {
        return "tt_arcaneear";
    }

    public String[] getMethodNames() {
        return new String[]{"getNote", "setNote"};
    }

    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
        if (method == 0) {
            return new Object[]{this.ear.note};
        }
        this.ear.note = (byte)((Double)arguments[0]).doubleValue();
        return null;
    }

    public void attach(IComputerAccess computer) {
    }

    public void detach(IComputerAccess computer) {
    }

    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }
}

