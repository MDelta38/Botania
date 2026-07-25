/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.lua.LuaException
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.IEssentiaTransport
 */
package thaumic.tinkerer.common.peripheral;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IEssentiaTransport;

public class PeripheralEssentiaTransport
implements IPeripheral {
    IEssentiaTransport pipe;

    public PeripheralEssentiaTransport(IEssentiaTransport input) {
        this.pipe = input;
    }

    public static int GetDirection(Object obj) {
        Double num = (Double)obj;
        return num.intValue();
    }

    public String getType() {
        return "tt_aspectTransport";
    }

    public String[] getMethodNames() {
        return new String[]{"isConnectable", "canInputFrom", "canOutputTo", "getSuctionType", "getSuctionAmount", "getEssentiaType", "getEssentiaAmount", "getMinimumSuction"};
    }

    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException {
        switch (method) {
            case 0: {
                return new Object[]{this.pipe.isConnectable(ForgeDirection.getOrientation((int)PeripheralEssentiaTransport.GetDirection(arguments[0])))};
            }
            case 1: {
                return new Object[]{this.pipe.canInputFrom(ForgeDirection.getOrientation((int)PeripheralEssentiaTransport.GetDirection(arguments[0])))};
            }
            case 2: {
                return new Object[]{this.pipe.canOutputTo(ForgeDirection.getOrientation((int)PeripheralEssentiaTransport.GetDirection(arguments[0])))};
            }
            case 3: {
                return new Object[]{this.pipe.getSuctionType(ForgeDirection.getOrientation((int)PeripheralEssentiaTransport.GetDirection(arguments[0]))).getTag()};
            }
            case 4: {
                return new Object[]{this.pipe.getSuctionAmount(ForgeDirection.getOrientation((int)PeripheralEssentiaTransport.GetDirection(arguments[0])))};
            }
            case 5: {
                return new Object[]{this.pipe.getEssentiaType(ForgeDirection.getOrientation((int)PeripheralEssentiaTransport.GetDirection(arguments[0]))).getTag()};
            }
            case 6: {
                return new Object[]{this.pipe.getEssentiaAmount(ForgeDirection.getOrientation((int)PeripheralEssentiaTransport.GetDirection(arguments[0])))};
            }
            case 7: {
                return new Object[]{this.pipe.getMinimumSuction()};
            }
        }
        return new Object[0];
    }

    public void attach(IComputerAccess computer) {
    }

    public void detach(IComputerAccess computer) {
    }

    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }
}

