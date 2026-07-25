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
import thaumic.tinkerer.common.block.tile.tablet.TileAnimationTablet;
import thaumic.tinkerer.common.network.packet.PacketTile;

public class PacketTabletButton
extends PacketTile<TileAnimationTablet>
implements IMessageHandler<PacketTabletButton, IMessage> {
    private static final long serialVersionUID = -6507755382924554527L;
    boolean leftClick;
    boolean redstone;

    public PacketTabletButton() {
    }

    public PacketTabletButton(TileAnimationTablet tile) {
        super(tile);
        this.leftClick = tile.leftClick;
        this.redstone = tile.redstone;
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        super.toBytes(byteBuf);
        byteBuf.writeBoolean(this.leftClick);
        byteBuf.writeBoolean(this.redstone);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        super.fromBytes(byteBuf);
        this.leftClick = byteBuf.readBoolean();
        this.redstone = byteBuf.readBoolean();
    }

    public IMessage onMessage(PacketTabletButton message, MessageContext ctx) {
        super.onMessage(message, ctx);
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("received PacketTabletbutton " + message + "on client side!");
        }
        ((TileAnimationTablet)message.tile).leftClick = message.leftClick;
        ((TileAnimationTablet)message.tile).redstone = message.redstone;
        return null;
    }
}

