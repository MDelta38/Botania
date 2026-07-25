/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.implementations;

public class TransitionResult {
    public final boolean success;
    public final double energyUsage;

    public TransitionResult(boolean _success, double power) {
        this.success = _success;
        this.energyUsage = power;
    }
}

