/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.entity.player.EntityPlayer
 */
package appeng.api.features;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.EntityPlayer;

public interface IPlayerRegistry {
    public int getID(GameProfile var1);

    public int getID(EntityPlayer var1);

    public EntityPlayer findPlayer(int var1);
}

