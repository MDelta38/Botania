/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.net.packet.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import drunkmafia.thaumicinfusion.common.world.SavableHelper;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.common.world.data.BlockSavable;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

public class BlockSyncPacketC
implements IMessage {
    private BlockSavable data;

    public BlockSyncPacketC() {
    }

    public BlockSyncPacketC(BlockSavable data) {
        this.data = data;
    }

    public void fromBytes(ByteBuf buf) {
        try {
            NBTTagCompound tag = new PacketBuffer(buf).func_150793_b();
            if (tag != null) {
                this.data = (BlockSavable)SavableHelper.loadDataFromNBT(tag);
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
    implements IMessageHandler<BlockSyncPacketC, IMessage> {
        public IMessage onMessage(BlockSyncPacketC message, MessageContext ctx) {
            BlockSavable data = message.data;
            if (data == null || ctx.side.isServer()) {
                return null;
            }
            World world = ChannelHandler.getClientWorld();
            WorldCoordinates pos = data.getCoords();
            TIWorldData worldData = TIWorldData.getWorldData(world);
            worldData.removeData(message.data.getClass(), pos, false);
            worldData.addBlock(message.data, true, false);
            Minecraft.func_71410_x().field_71438_f.func_147586_a(pos.x, pos.y, pos.z);
            return null;
        }
    }
}

