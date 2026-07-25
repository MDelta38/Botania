/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  li.cil.oc.api.machine.Arguments
 *  li.cil.oc.api.machine.Callback
 *  li.cil.oc.api.machine.Context
 *  li.cil.oc.api.network.ManagedEnvironment
 *  li.cil.oc.api.prefab.DriverTileEntity
 *  net.minecraft.world.World
 *  thaumcraft.common.tiles.TileSensor
 */
package thaumic.tinkerer.common.peripheral.OpenComputers;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverTileEntity;
import net.minecraft.world.World;
import thaumcraft.common.tiles.TileSensor;
import thaumic.tinkerer.common.peripheral.OpenComputers.ManagedTileEntityEnvironment;
import thaumic.tinkerer.common.peripheral.implementation.ArcaneEarImplementation;

public class DriverArcaneEar
extends DriverTileEntity {
    public Class<?> getTileEntityClass() {
        return TileSensor.class;
    }

    public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
        return new Enviroment((TileSensor)world.func_147438_o(x, y, z));
    }

    public static final class Enviroment
    extends ManagedTileEntityEnvironment<TileSensor> {
        public Enviroment(TileSensor tileEntity) {
            super(tileEntity, "ArcaneEar");
        }

        @Callback(doc="function():number -- returns the note the ear is set to")
        public Object[] getNote(Context context, Arguments arguments) {
            return ArcaneEarImplementation.getNote((TileSensor)this.tileEntity);
        }

        @Callback(doc="function(note:number):nil -- sets the note the ear listens for")
        public Object[] setNote(Context context, Arguments arguments) {
            return ArcaneEarImplementation.setNote((TileSensor)this.tileEntity, (byte)arguments.checkInteger(0));
        }
    }
}

