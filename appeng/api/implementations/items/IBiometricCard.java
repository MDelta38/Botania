/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.item.ItemStack
 */
package appeng.api.implementations.items;

import appeng.api.config.SecurityPermissions;
import appeng.api.features.IPlayerRegistry;
import appeng.api.networking.security.ISecurityRegistry;
import com.mojang.authlib.GameProfile;
import java.util.EnumSet;
import net.minecraft.item.ItemStack;

public interface IBiometricCard {
    public void setProfile(ItemStack var1, GameProfile var2);

    public GameProfile getProfile(ItemStack var1);

    public EnumSet<SecurityPermissions> getPermissions(ItemStack var1);

    public boolean hasPermission(ItemStack var1, SecurityPermissions var2);

    public void removePermission(ItemStack var1, SecurityPermissions var2);

    public void addPermission(ItemStack var1, SecurityPermissions var2);

    public void registerPermissions(ISecurityRegistry var1, IPlayerRegistry var2, ItemStack var3);
}

