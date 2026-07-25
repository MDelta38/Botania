/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.config;

public enum SecurityPermissions {
    INJECT,
    EXTRACT,
    CRAFT,
    BUILD,
    SECURITY;

    private final String unlocalizedName = "gui.appliedenergistics2.security." + this.name().toLowerCase();

    public String getUnlocalizedName() {
        return this.unlocalizedName + ".name";
    }

    public String getUnlocalizedTip() {
        return this.unlocalizedName + ".tip";
    }
}

