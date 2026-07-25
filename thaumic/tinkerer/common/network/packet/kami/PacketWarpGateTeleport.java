/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 */
package thaumic.tinkerer.common.network.packet.kami;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import thaumic.tinkerer.common.block.tile.kami.TileWarpGate;
import thaumic.tinkerer.common.network.packet.PacketTile;

public class PacketWarpGateTeleport
extends PacketTile<TileWarpGate>
implements IMessageHandler<PacketWarpGateTeleport, IMessage> {
    private static final long serialVersionUID = 2247241734524685744L;
    int index;

    public PacketWarpGateTeleport() {
    }

    public PacketWarpGateTeleport(TileWarpGate tile, int index) {
        super(tile);
        this.index = index;
    }

    @Override
    public void toBytes(ByteBuf byteBuf) {
        super.toBytes(byteBuf);
        byteBuf.writeInt(this.index);
    }

    @Override
    public void fromBytes(ByteBuf byteBuf) {
        super.fromBytes(byteBuf);
        this.index = byteBuf.readInt();
    }

    public IMessage onMessage(PacketWarpGateTeleport message, MessageContext ctx) {
        super.onMessage(message, ctx);
        if (!ctx.side.isServer()) {
            throw new IllegalStateException("received PacketTabletbutton " + message + "on client side!");
        }
        if (message.player instanceof EntityPlayer) {
            ((TileWarpGate)message.tile).teleportPlayer(message.player, message.index);
        }
        return null;
    }
}

