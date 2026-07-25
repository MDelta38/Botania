/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package thaumic.tinkerer.common.network.packet.kami;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import thaumic.tinkerer.client.core.handler.kami.SoulHeartClientHandler;

public class PacketSoulHearts
implements IMessage,
IMessageHandler<PacketSoulHearts, IMessage> {
    private static final long serialVersionUID = 8044672277674872323L;
    int hearts;

    public PacketSoulHearts(int hearts) {
        this.hearts = hearts;
    }

    public PacketSoulHearts() {
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.hearts = byteBuf.readInt();
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(this.hearts);
    }

    public IMessage onMessage(PacketSoulHearts message, MessageContext ctx) {
        if (!ctx.side.isClient()) {
            throw new IllegalStateException("received PacketSoulHearts " + message + "on server side!");
        }
        SoulHeartClientHandler.clientPlayerHP = message.hearts;
        return null;
    }
}

