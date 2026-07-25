/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package appeng.api.networking.security;

import appeng.api.config.SecurityPermissions;
import appeng.api.networking.IGridCache;
import net.minecraft.entity.player.EntityPlayer;

public interface ISecurityGrid
extends IGridCache {
    public boolean isAvailable();

    public boolean hasPermission(EntityPlayer var1, SecurityPermissions var2);

    public boolean hasPermission(int var1, SecurityPermissions var2);

    public int getOwner();
}

