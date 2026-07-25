/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  thaumcraft.common.tiles.TileJarBrain
 */
package thaumic.tinkerer.common.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import thaumcraft.common.tiles.TileJarBrain;

public class PeripheralBrainInAJar
implements IPeripheral {
    TileJarBrain jar;

    public PeripheralBrainInAJar(TileJarBrain jar) {
        this.jar = jar;
    }

    public String getType() {
        return "tt_braininajar";
    }

    public String[] getMethodNames() {
        return new String[]{"getXP"};
    }

    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
        return new Object[]{this.jar.xp};
    }

    public void attach(IComputerAccess computer) {
    }

    public void detach(IComputerAccess computer) {
    }

    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }
}

