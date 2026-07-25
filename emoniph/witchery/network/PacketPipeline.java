/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.NetworkRegistry
 *  cpw.mods.fml.common.network.NetworkRegistry$TargetPoint
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper
 *  cpw.mods.fml.relauncher.Side
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.network.Packet
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 */
package com.emoniph.witchery.network;

import com.emoniph.witchery.network.PacketBrewPrepared;
import com.emoniph.witchery.network.PacketCamPos;
import com.emoniph.witchery.network.PacketClearFallDamage;
import com.emoniph.witchery.network.PacketExtendedEntityRequestSyncToClient;
import com.emoniph.witchery.network.PacketExtendedPlayerSync;
import com.emoniph.witchery.network.PacketExtendedVillagerSync;
import com.emoniph.witchery.network.PacketHowl;
import com.emoniph.witchery.network.PacketItemUpdate;
import com.emoniph.witchery.network.PacketPartialExtendedPlayerSync;
import com.emoniph.witchery.network.PacketParticles;
import com.emoniph.witchery.network.PacketPlayerStyle;
import com.emoniph.witchery.network.PacketPlayerSync;
import com.emoniph.witchery.network.PacketPushTarget;
import com.emoniph.witchery.network.PacketSelectPlayerAbility;
import com.emoniph.witchery.network.PacketSetClientPlayerFacing;
import com.emoniph.witchery.network.PacketSound;
import com.emoniph.witchery.network.PacketSpellPrepared;
import com.emoniph.witchery.network.PacketSyncEntitySize;
import com.emoniph.witchery.network.PacketSyncMarkupBook;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class PacketPipeline {
    private SimpleNetworkWrapper CHANNEL;

    public void preInit() {
        this.CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("witchery".toLowerCase());
    }

    public void init() {
        this.CHANNEL.registerMessage(PacketBrewPrepared.Handler.class, PacketBrewPrepared.class, 1, Side.SERVER);
        this.CHANNEL.registerMessage(PacketParticles.Handler.class, PacketParticles.class, 2, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketCamPos.Handler.class, PacketCamPos.class, 3, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketItemUpdate.Handler.class, PacketItemUpdate.class, 4, Side.SERVER);
        this.CHANNEL.registerMessage(PacketPlayerStyle.Handler.class, PacketPlayerStyle.class, 5, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketPlayerSync.Handler.class, PacketPlayerSync.class, 6, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketPushTarget.Handler.class, PacketPushTarget.class, 7, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketSound.Handler.class, PacketSound.class, 8, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketSpellPrepared.Handler.class, PacketSpellPrepared.class, 9, Side.SERVER);
        this.CHANNEL.registerMessage(PacketClearFallDamage.Handler.class, PacketClearFallDamage.class, 10, Side.SERVER);
        this.CHANNEL.registerMessage(PacketSyncEntitySize.Handler.class, PacketSyncEntitySize.class, 11, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketSyncMarkupBook.Handler.class, PacketSyncMarkupBook.class, 12, Side.SERVER);
        this.CHANNEL.registerMessage(PacketExtendedPlayerSync.Handler.class, PacketExtendedPlayerSync.class, 13, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketHowl.Handler.class, PacketHowl.class, 14, Side.SERVER);
        this.CHANNEL.registerMessage(PacketExtendedVillagerSync.Handler.class, PacketExtendedVillagerSync.class, 15, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketSelectPlayerAbility.Handler.class, PacketSelectPlayerAbility.class, 16, Side.SERVER);
        this.CHANNEL.registerMessage(PacketExtendedEntityRequestSyncToClient.Handler.class, PacketExtendedEntityRequestSyncToClient.class, 17, Side.SERVER);
        this.CHANNEL.registerMessage(PacketPartialExtendedPlayerSync.Handler.class, PacketPartialExtendedPlayerSync.class, 18, Side.CLIENT);
        this.CHANNEL.registerMessage(PacketSetClientPlayerFacing.Handler.class, PacketSetClientPlayerFacing.class, 19, Side.CLIENT);
    }

    public void sendTo(IMessage message, EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            this.CHANNEL.sendTo(message, (EntityPlayerMP)player);
        }
    }

    public void sendTo(IMessage message, EntityPlayerMP player) {
        this.CHANNEL.sendTo(message, player);
    }

    public void sendToServer(IMessage message) {
        this.CHANNEL.sendToServer(message);
    }

    public void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint targetPoint) {
        this.CHANNEL.sendToAllAround(message, targetPoint);
    }

    public void sendToAll(IMessage message) {
        this.CHANNEL.sendToAll(message);
    }

    public void sendToDimension(IMessage message, int dimensionId) {
        this.CHANNEL.sendToDimension(message, dimensionId);
    }

    public void sendTo(Packet packet, EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            EntityPlayerMP mp = (EntityPlayerMP)player;
            mp.field_71135_a.func_147359_a(packet);
        }
    }

    public void sendToDimension(Packet packet, World world) {
        for (Object obj : world.field_73010_i) {
            if (!(obj instanceof EntityPlayerMP)) continue;
            EntityPlayerMP mp = (EntityPlayerMP)obj;
            mp.field_71135_a.func_147359_a(packet);
        }
    }

    public void sendToAll(Packet packet) {
        for (WorldServer world : MinecraftServer.func_71276_C().field_71305_c) {
            this.sendToDimension(packet, (World)world);
        }
    }

    public void sendToAllAround(Packet packet, World world, NetworkRegistry.TargetPoint point) {
        double RANGE_SQ = point.range * point.range;
        for (Object obj : world.field_73010_i) {
            EntityPlayerMP mp;
            if (!(obj instanceof EntityPlayerMP) || !((mp = (EntityPlayerMP)obj).func_70092_e(point.x, point.y, point.z) <= RANGE_SQ)) continue;
            mp.field_71135_a.func_147359_a(packet);
        }
    }
}

