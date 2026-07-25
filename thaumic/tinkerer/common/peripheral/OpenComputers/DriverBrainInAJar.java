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
 *  thaumcraft.common.tiles.TileJarBrain
 */
package thaumic.tinkerer.common.peripheral.OpenComputers;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverTileEntity;
import net.minecraft.world.World;
import thaumcraft.common.tiles.TileJarBrain;
import thaumic.tinkerer.common.peripheral.OpenComputers.ManagedTileEntityEnvironment;

public class DriverBrainInAJar
extends DriverTileEntity {
    public Class<?> getTileEntityClass() {
        return TileJarBrain.class;
    }

    public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
        return new Enviroment((TileJarBrain)world.func_147438_o(x, y, z));
    }

    public static final class Enviroment
    extends ManagedTileEntityEnvironment<TileJarBrain> {
        public Enviroment(TileJarBrain tileEntity) {
            super(tileEntity, "brainjar");
        }

        @Callback(doc="function():number -- returns the amount of XP in this jar")
        public Object[] getXP(Context context, Arguments arguments) {
            return new Object[]{((TileJarBrain)this.tileEntity).xp};
        }
    }
}

