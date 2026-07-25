/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.server.MinecraftServer
 */
package com.emoniph.witchery.util;

import java.util.Iterator;
import java.util.UUID;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class TameableUtil {
    public static void setOwner(EntityTameable tameable, EntityPlayer owner) {
        if (tameable != null && owner != null) {
            tameable.func_152115_b(owner.func_110124_au().toString());
        }
    }

    public static void setOwnerByUsername(EntityTameable tameable, String ownerUsername) {
        EntityPlayerMP player = MinecraftServer.func_71276_C().func_71203_ab().func_152612_a(ownerUsername);
        TameableUtil.setOwner(tameable, (EntityPlayer)player);
    }

    public static boolean isOwner(EntityTameable tameable, EntityPlayer player) {
        return tameable.func_152114_e((EntityLivingBase)player);
    }

    public static boolean hasOwner(EntityTameable tameable) {
        String id = tameable.func_152113_b();
        return id != null && !id.isEmpty();
    }

    public static EntityLivingBase getOwnerAccrossDimensions(EntityTameable tameable) {
        String id = tameable.func_152113_b();
        UUID uuid = UUID.fromString(id);
        return TameableUtil.getPlayerByID(uuid);
    }

    public static EntityPlayerMP getPlayerByID(UUID uuid) {
        EntityPlayerMP entityplayermp;
        Iterator iterator = MinecraftServer.func_71276_C().func_71203_ab().field_72404_b.iterator();
        do {
            if (iterator.hasNext()) continue;
            return null;
        } while (!(entityplayermp = (EntityPlayerMP)iterator.next()).func_146103_bH().getId().equals(uuid));
        return entityplayermp;
    }

    public static void cloneOwner(EntityTameable tameable, EntityTameable tameableToCopyFrom) {
        tameable.func_152115_b(tameableToCopyFrom.func_152113_b());
    }
}

