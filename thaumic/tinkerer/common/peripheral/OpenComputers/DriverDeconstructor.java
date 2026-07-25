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
 *  thaumcraft.common.tiles.TileDeconstructionTable
 */
package thaumic.tinkerer.common.peripheral.OpenComputers;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverTileEntity;
import net.minecraft.world.World;
import thaumcraft.common.tiles.TileDeconstructionTable;
import thaumic.tinkerer.common.peripheral.OpenComputers.ManagedTileEntityEnvironment;

public class DriverDeconstructor
extends DriverTileEntity {
    public Class<?> getTileEntityClass() {
        return TileDeconstructionTable.class;
    }

    public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
        return new Enviroment((TileDeconstructionTable)world.func_147438_o(x, y, z));
    }

    public static final class Enviroment
    extends ManagedTileEntityEnvironment<TileDeconstructionTable> {
        public Enviroment(TileDeconstructionTable tileEntity) {
            super(tileEntity, "deconstructiontable");
        }

        @Callback(doc="function():boolean -- returns if the table has an aspect waiting")
        public Object[] hasAspect(Context context, Arguments arguments) {
            return new Object[]{((TileDeconstructionTable)this.tileEntity).aspect != null};
        }

        @Callback(doc="function():boolean -- returns if the table has an item in the slot")
        public Object[] hasItem(Context context, Arguments arguments) {
            return new Object[]{((TileDeconstructionTable)this.tileEntity).func_70301_a(0) != null};
        }

        @Callback(doc="function():string -- returns aspect in the deconstructor")
        public Object[] getAspect(Context context, Arguments arguments) {
            Object[] objectArray;
            if (((TileDeconstructionTable)this.tileEntity).aspect != null) {
                Object[] objectArray2 = new Object[1];
                objectArray = objectArray2;
                objectArray2[0] = ((TileDeconstructionTable)this.tileEntity).aspect.getTag();
            } else {
                objectArray = null;
            }
            return objectArray;
        }
    }
}

