/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.config;

public enum FuzzyMode {
    IGNORE_ALL(-1.0f),
    PERCENT_99(0.0f),
    PERCENT_75(25.0f),
    PERCENT_50(50.0f),
    PERCENT_25(75.0f);

    public final float breakPoint;
    public final float percentage;

    private FuzzyMode(float p) {
        this.percentage = p;
        this.breakPoint = p / 100.0f;
    }

    public int calculateBreakPoint(int maxDamage) {
        return (int)(this.percentage * (float)maxDamage / 100.0f);
    }
}

