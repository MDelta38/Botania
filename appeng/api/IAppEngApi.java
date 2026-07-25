/*
 * Decompiled with CFR 0.152.
 */
package appeng.api;

import appeng.api.definitions.Blocks;
import appeng.api.definitions.Items;
import appeng.api.definitions.Materials;
import appeng.api.definitions.Parts;
import appeng.api.exceptions.FailedConnection;
import appeng.api.features.IRegistryContainer;
import appeng.api.networking.IGridBlock;
import appeng.api.networking.IGridConnection;
import appeng.api.networking.IGridNode;
import appeng.api.parts.IPartHelper;
import appeng.api.storage.IStorageHelper;

public interface IAppEngApi {
    public IRegistryContainer registries();

    public IStorageHelper storage();

    public IPartHelper partHelper();

    public Items items();

    public Materials materials();

    public Blocks blocks();

    public Parts parts();

    public IGridNode createGridNode(IGridBlock var1);

    public IGridConnection createGridConnection(IGridNode var1, IGridNode var2) throws FailedConnection;
}

