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
import com.emoniph.witchery.brewing.potions.PotionResizing;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class PacketSyncEntitySize
implements IMessage {
    private int entityID;
    private float width;
    private float height;
    private float stepSize;
    private float eyeHeight;

    public PacketSyncEntitySize() {
    }

    public PacketSyncEntitySize(Entity entity) {
        this.entityID = entity != null ? entity.func_145782_y() : 0;
        this.width = entity.field_70130_N;
        this.height = entity.field_70131_O;
        this.stepSize = entity.field_70138_W;
        this.eyeHeight = entity instanceof EntityPlayer ? ((EntityPlayer)entity).eyeHeight : -1.0f;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.entityID);
        buffer.writeFloat(this.width);
        buffer.writeFloat(this.height);
        buffer.writeFloat(this.stepSize);
        buffer.writeFloat(this.eyeHeight);
    }

    public void fromBytes(ByteBuf buffer) {
        this.entityID = buffer.readInt();
        this.width = buffer.readFloat();
        this.height = buffer.readFloat();
        this.stepSize = buffer.readFloat();
        this.eyeHeight = buffer.readFloat();
    }

    public static class Handler
    implements IMessageHandler<PacketSyncEntitySize, IMessage> {
        public IMessage onMessage(PacketSyncEntitySize message, MessageContext ctx) {
            EntityPlayer player = Witchery.proxy.getPlayer(ctx);
            for (Object obj : player.field_70170_p.field_72996_f) {
                Entity entity = (Entity)obj;
                if (entity.func_145782_y() != message.entityID) continue;
                PotionResizing.setEntitySize(entity, message.width, message.height);
                entity.field_70138_W = message.stepSize;
                if (!(entity instanceof EntityPlayer) || message.eyeHeight != -1.0f) {
                    // empty if block
                }
                return null;
            }
            return null;
        }
    }
}

