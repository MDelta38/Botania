/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package thaumcraft.common.lib.network.playerdata;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.tiles.TileResearchTable;

public class PacketAspectPlaceToServer
implements IMessage,
IMessageHandler<PacketAspectPlaceToServer, IMessage> {
    private int dim;
    private int playerid;
    private int x;
    private int y;
    private int z;
    Aspect aspect;
    byte q;
    byte r;

    public PacketAspectPlaceToServer() {
    }

    public PacketAspectPlaceToServer(EntityPlayer player, byte q, byte r, int x, int y, int z, Aspect aspect) {
        this.dim = player.field_70170_p.field_73011_w.field_76574_g;
        this.playerid = player.func_145782_y();
        this.x = x;
        this.y = y;
        this.z = z;
        this.aspect = aspect;
        this.q = q;
        this.r = r;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.dim);
        buffer.writeInt(this.playerid);
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)(this.aspect == null ? "null" : this.aspect.getTag()));
        buffer.writeByte((int)this.q);
        buffer.writeByte((int)this.r);
    }

    public void fromBytes(ByteBuf buffer) {
        this.dim = buffer.readInt();
        this.playerid = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.aspect = Aspect.getAspect(ByteBufUtils.readUTF8String((ByteBuf)buffer));
        this.q = buffer.readByte();
        this.r = buffer.readByte();
    }

    public IMessage onMessage(PacketAspectPlaceToServer message, MessageContext ctx) {
        WorldServer world = DimensionManager.getWorld((int)message.dim);
        if (world == null || ctx.getServerHandler().field_147369_b != null && ctx.getServerHandler().field_147369_b.func_145782_y() != message.playerid) {
            return null;
        }
        Entity player = world.func_73045_a(message.playerid);
        if (player == null) {
            return null;
        }
        TileEntity rt = world.func_147438_o(message.x, message.y, message.z);
        if (rt != null && rt instanceof TileResearchTable) {
            ((TileResearchTable)rt).placeAspect(message.q, message.r, message.aspect, (EntityPlayer)player);
        }
        return null;
    }
}

