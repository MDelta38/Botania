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
import thaumic.tinkerer.client.core.proxy.TTClientProxy;
import thaumic.tinkerer.common.ThaumicTinkerer;

public class PacketToggleArmor
implements IMessage,
IMessageHandler<PacketToggleArmor, IMessage> {
    private static final long serialVersionUID = -1247633508013055777L;
    public boolean armorStatus;

    public PacketToggleArmor(boolean status) {
        this.armorStatus = status;
    }

    public PacketToggleArmor() {
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.armorStatus = byteBuf.getBoolean(0);
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeBoolean(this.armorStatus);
    }

    public IMessage onMessage(PacketToggleArmor message, MessageContext ctx) {
        Object player = ctx.side.isClient() ? TTClientProxy.getPlayer() : ctx.getServerHandler().field_147369_b;
        if (player instanceof EntityPlayer) {
            ThaumicTinkerer.proxy.setArmor((EntityPlayer)player, message.armorStatus);
        }
        return null;
    }
}

