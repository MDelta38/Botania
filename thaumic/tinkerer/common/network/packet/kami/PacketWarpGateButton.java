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
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.network.packet.PacketTile;

public class PacketWarpGateButton
extends PacketTile<TileWarpGate>
implements IMessageHandler<PacketWarpGateButton, IMessage> {
    private static final long serialVersionUID = 1497188581985763661L;
    boolean locked;

    public PacketWarpGateButton() {
    }

    public PacketWarpGateButton(TileWarpGate tile) {
        super(tile);
        this.locked = tile.locked;
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        super.toBytes(byteBuf);
        byteBuf.writeBoolean(this.locked);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        super.fromBytes(byteBuf);
        this.locked = byteBuf.readBoolean();
    }

    public IMessage onMessage(PacketWarpGateButton message, MessageContext ctx) {
        super.onMessage(message, ctx);
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("received PacketWarpgateButton " + message + "on client side!");
        }
        ((TileWarpGate)message.tile).locked = message.locked;
        return null;
    }
}

