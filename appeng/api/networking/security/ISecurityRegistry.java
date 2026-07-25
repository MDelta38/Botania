/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.security;

import appeng.api.config.SecurityPermissions;
import java.util.EnumSet;

public interface ISecurityRegistry {
    public void addPlayer(int var1, EnumSet<SecurityPermissions> var2);
}

