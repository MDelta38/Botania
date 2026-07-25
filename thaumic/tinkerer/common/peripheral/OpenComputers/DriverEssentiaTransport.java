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
 *  net.minecraftforge.common.util.ForgeDirection
 *  thaumcraft.api.aspects.IEssentiaTransport
 */
package thaumic.tinkerer.common.peripheral.OpenComputers;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverTileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumic.tinkerer.common.peripheral.OpenComputers.ManagedTileEntityEnvironment;

public class DriverEssentiaTransport
extends DriverTileEntity {
    public Class<?> getTileEntityClass() {
        return IEssentiaTransport.class;
    }

    public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
        return new Enviroment((IEssentiaTransport)world.func_147438_o(x, y, z));
    }

    public static final class Enviroment
    extends ManagedTileEntityEnvironment<IEssentiaTransport> {
        public Enviroment(IEssentiaTransport tileEntity) {
            super(tileEntity, "essentiaTransport");
        }

        @Callback(doc="function(direction:number):boolean -- returns is the pipe connectable from this direction")
        public Object[] isConnectable(Context context, Arguments arguments) {
            return new Object[]{((IEssentiaTransport)this.tileEntity).isConnectable(ForgeDirection.getOrientation((int)arguments.checkInteger(0)))};
        }

        @Callback(doc="function(direction:number):boolean -- can pipe input from direction")
        public Object[] canInputFrom(Context context, Arguments arguments) {
            return new Object[]{((IEssentiaTransport)this.tileEntity).canInputFrom(ForgeDirection.getOrientation((int)arguments.checkInteger(0)))};
        }

        @Callback(doc="function(direction:number):boolean -- can pipe output to direction")
        public Object[] canOutputTo(Context context, Arguments arguments) {
            return new Object[]{((IEssentiaTransport)this.tileEntity).canOutputTo(ForgeDirection.getOrientation((int)arguments.checkInteger(0)))};
        }

        @Callback(doc="function(direction:number):string -- returns which aspect suction")
        public Object[] getSuctionType(Context context, Arguments arguments) {
            return new Object[]{((IEssentiaTransport)this.tileEntity).getSuctionType(ForgeDirection.getOrientation((int)arguments.checkInteger(0))).getTag()};
        }

        @Callback(doc="function(direction:number):number -- returnd amount of suction")
        public Object[] getSuctionAmount(Context context, Arguments arguments) {
            return new Object[]{((IEssentiaTransport)this.tileEntity).getSuctionAmount(ForgeDirection.getOrientation((int)arguments.checkInteger(0)))};
        }

        @Callback(doc="function(direction:number):string -- returns which essentia in pipe")
        public Object[] getEssentiaType(Context context, Arguments arguments) {
            return new Object[]{((IEssentiaTransport)this.tileEntity).getEssentiaType(ForgeDirection.getOrientation((int)arguments.checkInteger(0))).getTag()};
        }

        @Callback(doc="function(direction:number):number -- returnd amount of essentia")
        public Object[] getEssentiaAmount(Context context, Arguments arguments) {
            return new Object[]{((IEssentiaTransport)this.tileEntity).getEssentiaAmount(ForgeDirection.getOrientation((int)arguments.checkInteger(0)))};
        }

        @Callback(doc="function():number -- returns minimum suction")
        public Object[] getMinimumSuction(Context context, Arguments arguments) {
            return new Object[]{((IEssentiaTransport)this.tileEntity).getMinimumSuction()};
        }
    }
}

