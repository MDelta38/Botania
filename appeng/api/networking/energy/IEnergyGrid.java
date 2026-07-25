/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.energy;

import appeng.api.config.Actionable;
import appeng.api.networking.IGridCache;
import appeng.api.networking.energy.IEnergyGridProvider;
import appeng.api.networking.energy.IEnergySource;

public interface IEnergyGrid
extends IGridCache,
IEnergySource,
IEnergyGridProvider {
    public double getIdlePowerUsage();

    public double getAvgPowerUsage();

    public double getAvgPowerInjection();

    public boolean isNetworkPowered();

    public double injectPower(double var1, Actionable var3);

    public double getStoredPower();

    public double getMaxStoredPower();

    public double getEnergyDemand(double var1);
}

