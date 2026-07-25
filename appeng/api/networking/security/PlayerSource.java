/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 */
package appeng.api.networking.security;

import appeng.api.networking.security.BaseActionSource;
import appeng.api.networking.security.IActionHost;
import net.minecraft.entity.player.EntityPlayer;

public class PlayerSource
extends BaseActionSource {
    public final EntityPlayer player;
    public final IActionHost via;

    @Override
    public boolean isPlayer() {
        return true;
    }

    public PlayerSource(EntityPlayer p, IActionHost v) {
        this.player = p;
        this.via = v;
    }
}

