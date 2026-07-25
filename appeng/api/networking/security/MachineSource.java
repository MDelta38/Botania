/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.networking.security;

import appeng.api.networking.security.BaseActionSource;
import appeng.api.networking.security.IActionHost;

public class MachineSource
extends BaseActionSource {
    public final IActionHost via;

    @Override
    public boolean isMachine() {
        return true;
    }

    public MachineSource(IActionHost v) {
        this.via = v;
    }
}

