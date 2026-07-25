/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  dan200.computercraft.api.ComputerCraftAPI
 *  dan200.computercraft.api.peripheral.IPeripheral
 *  dan200.computercraft.api.peripheral.IPeripheralProvider
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.World
 *  thaumcraft.api.aspects.IAspectContainer
 *  thaumcraft.api.aspects.IEssentiaTransport
 *  thaumcraft.common.tiles.TileArcaneBore
 *  thaumcraft.common.tiles.TileDeconstructionTable
 *  thaumcraft.common.tiles.TileJarBrain
 *  thaumcraft.common.tiles.TileSensor
 */
package thaumic.tinkerer.common.peripheral;

import dan200.computercraft.api.ComputerCraftAPI;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.TileArcaneBore;
import thaumcraft.common.tiles.TileDeconstructionTable;
import thaumcraft.common.tiles.TileJarBrain;
import thaumcraft.common.tiles.TileSensor;
import thaumic.tinkerer.common.peripheral.PeripheralArcaneBore;
import thaumic.tinkerer.common.peripheral.PeripheralArcaneEar;
import thaumic.tinkerer.common.peripheral.PeripheralAspectContainer;
import thaumic.tinkerer.common.peripheral.PeripheralBrainInAJar;
import thaumic.tinkerer.common.peripheral.PeripheralDeconstructor;
import thaumic.tinkerer.common.peripheral.PeripheralEssentiaTransport;

public final class PeripheralHandler
implements IPeripheralProvider {
    public IPeripheral getPeripheral(TileEntity tile) {
        if (tile instanceof IAspectContainer) {
            return new PeripheralAspectContainer((IAspectContainer)tile);
        }
        if (tile instanceof TileDeconstructionTable) {
            return new PeripheralDeconstructor((TileDeconstructionTable)tile);
        }
        if (tile instanceof TileJarBrain) {
            return new PeripheralBrainInAJar((TileJarBrain)tile);
        }
        if (tile instanceof TileSensor) {
            return new PeripheralArcaneEar((TileSensor)tile);
        }
        if (tile instanceof TileArcaneBore) {
            return new PeripheralArcaneBore((TileArcaneBore)tile);
        }
        if (tile instanceof IEssentiaTransport) {
            return new PeripheralEssentiaTransport((IEssentiaTransport)tile);
        }
        if (tile instanceof IPeripheral && tile.getClass().getName().contains("thaumic.tinkerer")) {
            return (IPeripheral)tile;
        }
        return null;
    }

    public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
        TileEntity te = world.func_147438_o(x, y, z);
        if (te != null) {
            return this.getPeripheral(te);
        }
        return null;
    }

    public void registerPeripheralProvider() {
        ComputerCraftAPI.registerPeripheralProvider((IPeripheralProvider)this);
    }
}

