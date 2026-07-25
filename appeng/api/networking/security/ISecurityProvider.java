/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.security;

import appeng.api.config.SecurityPermissions;
import java.util.EnumSet;
import java.util.HashMap;

public interface ISecurityProvider {
    public long getSecurityKey();

    public void readPermissions(HashMap<Integer, EnumSet<SecurityPermissions>> var1);

    public boolean isSecurityEnabled();

    public int getOwner();
}

