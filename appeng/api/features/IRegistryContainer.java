/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.features;

import appeng.api.features.IGrinderRegistry;
import appeng.api.features.ILocatableRegistry;
import appeng.api.features.IMatterCannonAmmoRegistry;
import appeng.api.features.IP2PTunnelRegistry;
import appeng.api.features.IPlayerRegistry;
import appeng.api.features.IRecipeHandlerRegistry;
import appeng.api.features.ISpecialComparisonRegistry;
import appeng.api.features.IWirelessTermRegistry;
import appeng.api.features.IWorldGen;
import appeng.api.movable.IMovableRegistry;
import appeng.api.networking.IGridCacheRegistry;
import appeng.api.storage.ICellRegistry;
import appeng.api.storage.IExternalStorageRegistry;

public interface IRegistryContainer {
    public IMovableRegistry movable();

    public IGridCacheRegistry gridCache();

    public IExternalStorageRegistry externalStorage();

    public ISpecialComparisonRegistry specialComparison();

    public IWirelessTermRegistry wireless();

    public ICellRegistry cell();

    public IGrinderRegistry grinder();

    public ILocatableRegistry locatable();

    public IP2PTunnelRegistry p2pTunnel();

    public IMatterCannonAmmoRegistry matterCannon();

    public IPlayerRegistry players();

    public IRecipeHandlerRegistry recipes();

    public IWorldGen worldgen();
}

