/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.Optional$Method
 *  dan200.computercraft.api.lua.ILuaContext
 *  dan200.computercraft.api.peripheral.IComputerAccess
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  thaumcraft.api.aspects.IAspectContainer
 */
package thaumic.tinkerer.common.peripheral;

import cpw.mods.fml.common.Optional;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import thaumcraft.api.aspects.IAspectContainer;
import thaumic.tinkerer.common.peripheral.implementation.IAspectContainerImplementation;

public class PeripheralAspectContainer
implements IPeripheral {
    IAspectContainer container;

    public PeripheralAspectContainer(IAspectContainer container) {
        this.container = container;
    }

    public String getType() {
        return "tt_aspectContainer";
    }

    public String[] getMethodNames() {
        return new String[]{"getAspects", "getAspectCount"};
    }

    @Optional.Method(modid="ComputerCraft")
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
        switch (method) {
            case 0: {
                return IAspectContainerImplementation.getAspects(this.container);
            }
            case 1: {
                String aspectName = (String)arguments[0];
                return IAspectContainerImplementation.getAspectCount(this.container, aspectName);
            }
        }
        return null;
    }

    @Optional.Method(modid="ComputerCraft")
    public void attach(IComputerAccess computer) {
    }

    @Optional.Method(modid="ComputerCraft")
    public void detach(IComputerAccess computer) {
    }

    @Optional.Method(modid="ComputerCraft")
    public boolean equals(IPeripheral other) {
        return this.equals((Object)other);
    }
}

