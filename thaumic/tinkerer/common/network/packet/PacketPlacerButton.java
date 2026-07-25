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
import thaumic.tinkerer.common.block.tile.TileRPlacer;
import thaumic.tinkerer.common.network.packet.PacketTile;

public class PacketPlacerButton
extends PacketTile<TileRPlacer>
implements IMessageHandler<PacketPlacerButton, IMessage> {
    public int blocks;

    public PacketPlacerButton() {
    }

    public PacketPlacerButton(TileRPlacer tile) {
        super(tile);
        this.blocks = tile.blocks;
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        super.toBytes(byteBuf);
        byteBuf.writeInt(this.blocks);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        super.fromBytes(byteBuf);
        this.blocks = byteBuf.readInt();
    }

    public IMessage onMessage(PacketPlacerButton message, MessageContext ctx) {
        super.onMessage(message, ctx);
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("received PacketPlacerbutton " + message + "on client side!");
        }
        ((TileRPlacer)message.tile).blocks = message.blocks;
        return null;
    }
}

