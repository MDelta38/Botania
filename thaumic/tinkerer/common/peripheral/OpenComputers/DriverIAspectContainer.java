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
 *  thaumcraft.api.aspects.IAspectContainer
 */
package thaumic.tinkerer.common.peripheral.OpenComputers;

import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.ManagedEnvironment;
import li.cil.oc.api.prefab.DriverTileEntity;
import net.minecraft.world.World;
import thaumcraft.api.aspects.IAspectContainer;
import thaumic.tinkerer.common.peripheral.OpenComputers.ManagedTileEntityEnvironment;
import thaumic.tinkerer.common.peripheral.implementation.IAspectContainerImplementation;

public class DriverIAspectContainer
extends DriverTileEntity {
    public Class<?> getTileEntityClass() {
        return IAspectContainer.class;
    }

    public ManagedEnvironment createEnvironment(World world, int x, int y, int z) {
        return new Enviroment((IAspectContainer)world.func_147438_o(x, y, z));
    }

    public static final class Enviroment
    extends ManagedTileEntityEnvironment<IAspectContainer> {
        public Enviroment(IAspectContainer tileEntity) {
            super(tileEntity, "IAspectContainer");
        }

        @Callback(doc="function():table -- returns a list of tables containing aspect and quantity")
        public Object[] getAspects(Context context, Arguments arguments) {
            return IAspectContainerImplementation.getAspects((IAspectContainer)this.tileEntity);
        }

        @Callback(doc="function(aspectName:string):number -- returns the amount of aspect in the block")
        public Object[] getAspectCount(Context context, Arguments arguments) {
            String aspectName = arguments.checkString(0);
            return IAspectContainerImplementation.getAspectCount((IAspectContainer)this.tileEntity, aspectName);
        }
    }
}

