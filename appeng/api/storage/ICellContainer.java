/*
 * Decompiled with CFR 0.152.
 */
package appeng.api.storage;

import appeng.api.networking.security.IActionHost;
import appeng.api.storage.ICellProvider;
import appeng.api.storage.ISaveProvider;

public interface ICellContainer
extends IActionHost,
ICellProvider,
ISaveProvider {
    public void blinkCell(int var1);
}

