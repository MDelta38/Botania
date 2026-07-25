/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.energy;

import appeng.api.config.Actionable;
import appeng.api.networking.energy.IEnergyGrid;
import java.util.Set;

public interface IEnergyGridProvider {
    public double extractAEPower(double var1, Actionable var3, Set<IEnergyGrid> var4);

    public double injectAEPower(double var1, Actionable var3, Set<IEnergyGrid> var4);

    public double getEnergyDemand(double var1, Set<IEnergyGrid> var3);
}

