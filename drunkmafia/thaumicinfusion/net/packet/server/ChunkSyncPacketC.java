/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.net.packet.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import drunkmafia.thaumicinfusion.common.world.ChunkData;
import drunkmafia.thaumicinfusion.common.world.SavableHelper;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockSavable;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

public class ChunkSyncPacketC
implements IMessage {
    private ChunkData data;

    public ChunkSyncPacketC() {
    }

    public ChunkSyncPacketC(ChunkData data) {
        this.data = data;
    }

    public void fromBytes(ByteBuf buf) {
        try {
            NBTTagCompound tag = new PacketBuffer(buf).func_150793_b();
            if (tag != null) {
                this.data = (ChunkData)SavableHelper.loadDataFromNBT(tag);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void toBytes(ByteBuf buf) {
        try {
            if (this.data != null) {
                new PacketBuffer(buf).func_150786_a(SavableHelper.saveDataToNBT(this.data));
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static class Handler
    implements IMessageHandler<ChunkSyncPacketC, IMessage> {
        public IMessage onMessage(ChunkSyncPacketC message, MessageContext ctx) {
            ChunkData data = message.data;
            if (data == null || ctx.side.isServer()) {
                return null;
            }
            World world = ChannelHandler.getClientWorld();
            TIWorldData worldData = TIWorldData.getWorldData(world);
            ChunkCoordIntPair chunkPos = data.getChunkPos();
            for (BlockSavable block : data.getAllBlocks()) {
                block.dataLoad(world);
            }
            worldData.chunkDatas.set(chunkPos.func_77273_a(), chunkPos.func_77274_b(), data);
            for (BlockSavable savable : data.getAllBlocks()) {
                WorldCoordinates blockPos = savable.getCoords();
                world.func_147471_g(blockPos.x, blockPos.y, blockPos.z);
            }
            return null;
        }
    }
}

