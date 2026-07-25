/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.energy;

import appeng.api.networking.energy.IEnergyGrid;
import appeng.api.networking.energy.IEnergyWatcher;

public interface IEnergyWatcherHost {
    public void updateWatcher(IEnergyWatcher var1);

    public void onThresholdPass(IEnergyGrid var1);
}

