/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketPartialExtendedPlayerSync
implements IMessage {
    private int entityId;
    private int blood;

    public PacketPartialExtendedPlayerSync() {
    }

    public PacketPartialExtendedPlayerSync(ExtendedPlayer playerEx, EntityPlayer player) {
        this.entityId = player.func_145782_y();
        this.blood = playerEx.getHumanBlood();
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeInt(this.blood);
    }

    public void fromBytes(ByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.blood = buffer.readInt();
    }

    public static class Handler
    implements IMessageHandler<PacketPartialExtendedPlayerSync, IMessage> {
        public IMessage onMessage(PacketPartialExtendedPlayerSync message, MessageContext ctx) {
            ExtendedPlayer ext;
            Entity entity;
            EntityPlayer player = Witchery.proxy.getPlayer(ctx);
            if (player != null && (entity = player.field_70170_p.func_73045_a(message.entityId)) instanceof EntityPlayer && (ext = ExtendedPlayer.get((EntityPlayer)entity)) != null) {
                ext.setHumanBlood(message.blood);
            }
            return null;
        }
    }
}

