/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 */
package appeng.api.implementations;

import appeng.api.config.Upgrades;
import appeng.api.implementations.tiles.ISegmentedInventory;
import appeng.api.util.IConfigurableObject;
import net.minecraft.tileentity.TileEntity;

public interface IUpgradeableHost
extends IConfigurableObject,
ISegmentedInventory {
    public int getInstalledUpgrades(Upgrades var1);

    public TileEntity getTile();
}

