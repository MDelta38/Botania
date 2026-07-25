/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.passive.EntityVillager
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.emoniph.witchery.network;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.common.ExtendedVillager;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;

public class PacketExtendedVillagerSync
implements IMessage {
    private int entityId;
    private int blood;
    private boolean sleeping;

    public PacketExtendedVillagerSync() {
    }

    public PacketExtendedVillagerSync(ExtendedVillager extendedVillager) {
        this.entityId = extendedVillager.getVillager().func_145782_y();
        this.blood = extendedVillager.getBlood();
        this.sleeping = extendedVillager.isSleeping();
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.entityId);
        buffer.writeInt(this.blood);
        buffer.writeBoolean(this.sleeping);
    }

    public void fromBytes(ByteBuf buffer) {
        this.entityId = buffer.readInt();
        this.blood = buffer.readInt();
        this.sleeping = buffer.readBoolean();
    }

    public static class Handler
    implements IMessageHandler<PacketExtendedVillagerSync, IMessage> {
        public IMessage onMessage(PacketExtendedVillagerSync message, MessageContext ctx) {
            ExtendedVillager ext;
            Entity entity;
            EntityPlayer player = Witchery.proxy.getPlayer(ctx);
            if (player != null && (entity = player.field_70170_p.func_73045_a(message.entityId)) instanceof EntityVillager && (ext = ExtendedVillager.get((EntityVillager)entity)) != null) {
                ext.synced = true;
                ext.setBlood(message.blood);
                ext.setSleeping(message.sleeping);
            }
            return null;
        }
    }
}

