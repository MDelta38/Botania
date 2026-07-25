/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ChunkCoordinates
 */
package thaumcraft.common.lib.network.fx;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.tiles.TileInfusionMatrix;
import thaumcraft.common.tiles.TilePedestal;

public class PacketFXInfusionSource
implements IMessage,
IMessageHandler<PacketFXInfusionSource, IMessage> {
    private int x;
    private int y;
    private int z;
    private byte dx;
    private byte dy;
    private byte dz;
    private int color;

    public PacketFXInfusionSource() {
    }

    public PacketFXInfusionSource(int x, int y, int z, byte dx, byte dy, byte dz, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
        this.color = color;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.color);
        buffer.writeByte((int)this.dx);
        buffer.writeByte((int)this.dy);
        buffer.writeByte((int)this.dz);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.color = buffer.readInt();
        this.dx = buffer.readByte();
        this.dy = buffer.readByte();
        this.dz = buffer.readByte();
    }

    public IMessage onMessage(PacketFXInfusionSource message, MessageContext ctx) {
        int tx = message.x - message.dx;
        int ty = message.y - message.dy;
        int tz = message.z - message.dz;
        String key = tx + ":" + ty + ":" + tz + ":" + message.color;
        TileEntity tile = Thaumcraft.proxy.getClientWorld().func_147438_o(message.x, message.y, message.z);
        if (tile != null && tile instanceof TileInfusionMatrix) {
            int count = 15;
            if (Thaumcraft.proxy.getClientWorld().func_147438_o(tx, ty, tz) != null && Thaumcraft.proxy.getClientWorld().func_147438_o(tx, ty, tz) instanceof TilePedestal) {
                count = 60;
            }
            TileInfusionMatrix is = (TileInfusionMatrix)tile;
            if (is.sourceFX.containsKey(key)) {
                TileInfusionMatrix.SourceFX sf = is.sourceFX.get(key);
                sf.ticks = count;
                is.sourceFX.put(key, sf);
            } else {
                HashMap<String, TileInfusionMatrix.SourceFX> hashMap = is.sourceFX;
                TileInfusionMatrix tileInfusionMatrix = is;
                tileInfusionMatrix.getClass();
                hashMap.put(key, new TileInfusionMatrix.SourceFX(tileInfusionMatrix, new ChunkCoordinates(tx, ty, tz), count, message.color));
            }
        }
        return null;
    }
}

