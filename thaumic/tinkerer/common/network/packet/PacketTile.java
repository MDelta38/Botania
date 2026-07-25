/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.WorldServer
 */
package thaumic.tinkerer.common.network.packet;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import thaumic.tinkerer.client.core.proxy.TTClientProxy;
import thaumic.tinkerer.common.core.helper.MiscHelper;

public abstract class PacketTile<T extends TileEntity>
implements IMessage {
    private static final long serialVersionUID = -1447633008013055477L;
    protected int dim;
    protected int x;
    protected int y;
    protected int z;
    protected transient T tile;
    protected transient EntityPlayer player;

    public PacketTile() {
    }

    public PacketTile(T tile) {
        this.tile = tile;
        this.x = ((TileEntity)tile).field_145851_c;
        this.y = ((TileEntity)tile).field_145848_d;
        this.z = ((TileEntity)tile).field_145849_e;
        this.dim = tile.func_145831_w().field_73011_w.field_76574_g;
    }

    public void toBytes(ByteBuf byteBuf) {
        byteBuf.writeInt(this.x);
        byteBuf.writeInt(this.y);
        byteBuf.writeInt(this.z);
        byteBuf.writeInt(this.dim);
    }

    public void fromBytes(ByteBuf byteBuf) {
        this.x = byteBuf.readInt();
        this.y = byteBuf.readInt();
        this.z = byteBuf.readInt();
        this.dim = byteBuf.readInt();
    }

    public IMessage onMessage(PacketTile message, MessageContext ctx) {
        MinecraftServer server = MiscHelper.server();
        message.player = ctx.side.isClient() ? TTClientProxy.getPlayer() : ctx.getServerHandler().field_147369_b;
        if (server != null) {
            WorldServer world = server.func_71218_a(message.dim);
            if (world == null) {
                MiscHelper.printCurrentStackTrace("No world found for dimension " + message.dim + "!");
                return null;
            }
            TileEntity tile = world.func_147438_o(message.x, message.y, message.z);
            if (tile != null) {
                message.tile = tile;
            }
        }
        return null;
    }
}

