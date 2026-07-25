/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.lua.LuaException
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 */
package thaumic.tinkerer.common.compat;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;

public class FumePeripheral
implements IPeripheral {
    public String getType() {
        return "gasRemover";
    }

    public String[] getMethodNames() {
        return new String[0];
    }

    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
        return new Object[0];
    }

    public void attach(IComputerAccess computer) {
    }

    public void detach(IComputerAccess computer) {
    }

    public boolean equals(IPeripheral other) {
        return other instanceof FumePeripheral;
    }
}

