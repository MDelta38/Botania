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
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.lib.research.ScanManager;

public class PacketScannedToServer
implements IMessage,
IMessageHandler<PacketScannedToServer, IMessage> {
    private int playerid;
    private int dim;
    private byte type;
    private int id;
    private int md;
    private int entityid;
    private String phenomena;
    private String prefix;

    public PacketScannedToServer() {
    }

    public PacketScannedToServer(ScanResult scan, EntityPlayer player, String prefix) {
        this.playerid = player.func_145782_y();
        this.dim = player.field_70170_p.field_73011_w.field_76574_g;
        this.type = scan.type;
        this.id = scan.id;
        this.md = scan.meta;
        this.entityid = scan.entity == null ? 0 : scan.entity.func_145782_y();
        this.phenomena = scan.phenomena;
        this.prefix = prefix;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.playerid);
        buffer.writeInt(this.dim);
        buffer.writeByte((int)this.type);
        buffer.writeInt(this.id);
        buffer.writeInt(this.md);
        buffer.writeInt(this.entityid);
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.phenomena);
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.prefix);
    }

    public void fromBytes(ByteBuf buffer) {
        this.playerid = buffer.readInt();
        this.dim = buffer.readInt();
        this.type = buffer.readByte();
        this.id = buffer.readInt();
        this.md = buffer.readInt();
        this.entityid = buffer.readInt();
        this.phenomena = ByteBufUtils.readUTF8String((ByteBuf)buffer);
        this.prefix = ByteBufUtils.readUTF8String((ByteBuf)buffer);
    }

    public IMessage onMessage(PacketScannedToServer message, MessageContext ctx) {
        WorldServer world = DimensionManager.getWorld((int)message.dim);
        if (world == null) {
            return null;
        }
        Entity player = world.func_73045_a(message.playerid);
        Entity e = null;
        if (message.entityid != 0) {
            e = world.func_73045_a(message.entityid);
        }
        if (player != null && player instanceof EntityPlayer) {
            ScanManager.completeScan((EntityPlayer)player, new ScanResult(message.type, message.id, message.md, e, message.phenomena), message.prefix);
        }
        return null;
    }
}

