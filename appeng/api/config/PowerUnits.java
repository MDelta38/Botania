/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.config;

public enum PowerUnits {
    AE("gui.appliedenergistics2.units.appliedenergstics"),
    MJ("gui.appliedenergistics2.units.buildcraft"),
    EU("gui.appliedenergistics2.units.ic2"),
    WA("gui.appliedenergistics2.units.rotarycraft"),
    RF("gui.appliedenergistics2.units.thermalexpansion"),
    MK("gui.appliedenergistics2.units.mekanism");

    public double conversionRatio = 1.0;
    public final String unlocalizedName;

    private PowerUnits(String un) {
        this.unlocalizedName = un;
    }

    public double convertTo(PowerUnits target, double value) {
        return value * this.conversionRatio / target.conversionRatio;
    }
}

