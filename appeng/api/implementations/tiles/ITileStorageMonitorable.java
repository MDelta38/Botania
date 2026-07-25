/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.common.util.ForgeDirection
 */
package appeng.api.implementations.tiles;

import appeng.api.networking.security.BaseActionSource;
import appeng.api.storage.IStorageMonitorable;
import net.minecraftforge.common.util.ForgeDirection;

public interface ITileStorageMonitorable {
    public IStorageMonitorable getMonitorable(ForgeDirection var1, BaseActionSource var2);
}

