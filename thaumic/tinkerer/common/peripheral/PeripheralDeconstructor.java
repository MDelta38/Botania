/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  thaumcraft.common.tiles.TileDeconstructionTable
 */
package thaumic.tinkerer.common.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import thaumcraft.common.tiles.TileDeconstructionTable;

public class PeripheralDeconstructor
implements IPeripheral {
    TileDeconstructionTable deconstructor;

    public PeripheralDeconstructor(TileDeconstructionTable deconstructor) {
        this.deconstructor = deconstructor;
    }

    public String getType() {
        return "tt_deconstructor";
    }

    public String[] getMethodNames() {
        return new String[]{"hasAspect", "hasItem", "getAspect"};
    }

    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
        switch (method) {
            case 0: {
                return new Object[]{this.deconstructor.aspect != null};
            }
            case 1: {
                return new Object[]{this.deconstructor.func_70301_a(0) != null};
            }
            case 2: {
                Object[] objectArray;
                if (this.deconstructor.aspect == null) {
                    objectArray = null;
                } else {
                    Object[] objectArray2 = new Object[1];
                    objectArray = objectArray2;
                    objectArray2[0] = this.deconstructor.aspect.getTag();
                }
                return objectArray;
            }
        }
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

