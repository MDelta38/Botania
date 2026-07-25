/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.ticking;

public class TickingRequest {
    public final int minTickRate;
    public final int maxTickRate;
    public final boolean isSleeping;
    public final boolean canBeAlerted;

    public TickingRequest(int min, int max, boolean sleep, boolean alertable) {
        this.minTickRate = min;
        this.maxTickRate = max;
        this.isSleeping = sleep;
        this.canBeAlerted = alertable;
    }
}

