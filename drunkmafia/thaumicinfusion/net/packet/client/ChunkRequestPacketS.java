/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 */
package drunkmafia.thaumicinfusion.net.packet.client;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import drunkmafia.thaumicinfusion.common.world.ChunkData;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import drunkmafia.thaumicinfusion.net.packet.server.ChunkSyncPacketC;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

public class ChunkRequestPacketS
implements IMessage {
    private ChunkCoordIntPair pos;
    private int dim;

    public ChunkRequestPacketS() {
    }

    public ChunkRequestPacketS(ChunkCoordIntPair pos, int dim) {
        this.pos = pos;
        this.dim = dim;
    }

    public void fromBytes(ByteBuf buf) {
        this.pos = new ChunkCoordIntPair(buf.readInt(), buf.readInt());
        this.dim = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.pos.func_77273_a() >> 4);
        buf.writeInt(this.pos.func_77274_b() >> 4);
        buf.writeInt(this.dim);
    }

    public static class Handler
    implements IMessageHandler<ChunkRequestPacketS, IMessage> {
        public IMessage onMessage(ChunkRequestPacketS message, MessageContext ctx) {
            ChunkCoordIntPair pos = message.pos;
            if (pos == null || ctx.side.isClient()) {
                return null;
            }
            TIWorldData worldData = TIWorldData.getWorldData((World)ChannelHandler.getServerWorld(message.dim));
            if (worldData == null) {
                return null;
            }
            ChunkData data = worldData.chunkDatas.get(pos.func_77273_a(), pos.func_77274_b(), null);
            if (data != null) {
                return new ChunkSyncPacketC(data);
            }
            return null;
        }
    }
}

