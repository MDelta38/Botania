/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  li.cil.oc.api.Network
 *  li.cil.oc.api.network.Environment
 *  li.cil.oc.api.network.Visibility
 *  li.cil.oc.api.prefab.ManagedEnvironment
 */
package thaumic.tinkerer.common.peripheral.OpenComputers;

import li.cil.oc.api.Network;
import li.cil.oc.api.network.Environment;
import li.cil.oc.api.network.Visibility;
import li.cil.oc.api.prefab.ManagedEnvironment;

public class ManagedTileEntityEnvironment<T>
extends ManagedEnvironment {
    protected final T tileEntity;

    public ManagedTileEntityEnvironment(T tileEntity, String name) {
        this.tileEntity = tileEntity;
        this.setNode(Network.newNode((Environment)this, (Visibility)Visibility.Network).withComponent(name).create());
    }
}

