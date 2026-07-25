/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package thaumic.tinkerer.common.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import thaumic.tinkerer.common.block.tile.TileMobMagnet;
import thaumic.tinkerer.common.network.packet.PacketTile;

public class PacketMobMagnetButton
extends PacketTile<TileMobMagnet>
implements IMessageHandler<PacketMobMagnetButton, IMessage> {
    private static final long serialVersionUID = 7613980953987386713L;
    public boolean adult;

    public PacketMobMagnetButton() {
    }

    public PacketMobMagnetButton(TileMobMagnet tile) {
        super(tile);
        this.adult = tile.adult;
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        super.toBytes(byteBuf);
        byteBuf.writeBoolean(this.adult);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        super.fromBytes(byteBuf);
        this.adult = byteBuf.readBoolean();
    }

    public IMessage onMessage(PacketMobMagnetButton message, MessageContext ctx) {
        super.onMessage(message, ctx);
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("received PacketTabletbutton " + message + "on client side!");
        }
        ((TileMobMagnet)message.tile).adult = message.adult;
        return null;
    }
}

