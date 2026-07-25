/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.World
 *  thaumcraft.api.WorldCoordinates
 */
package drunkmafia.thaumicinfusion.net.packet.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import drunkmafia.thaumicinfusion.common.world.TIWorldData;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import thaumcraft.api.WorldCoordinates;

public class DataRemovePacketC
implements IMessage {
    private Class clazz;
    private WorldCoordinates coordinates;

    public DataRemovePacketC() {
    }

    public DataRemovePacketC(Class clazz, WorldCoordinates coordinates) {
        this.clazz = clazz;
        this.coordinates = coordinates;
    }

    public void fromBytes(ByteBuf buf) {
        if (buf.readByte() == 1) {
            byte[] bytes = new byte[buf.readInt()];
            for (int i = 0; i < bytes.length; ++i) {
                bytes[i] = buf.readByte();
            }
            try {
                this.clazz = Class.forName(new String(bytes));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.coordinates = new WorldCoordinates(buf.readInt(), buf.readInt(), buf.readInt(), buf.readInt());
        }
    }

    public void toBytes(ByteBuf buf) {
        if (this.coordinates != null) {
            buf.writeByte(1);
            byte[] bytes = this.clazz.getName().getBytes();
            buf.writeInt(bytes.length);
            for (byte aByte : bytes) {
                buf.writeByte((int)aByte);
            }
            buf.writeInt(this.coordinates.x);
            buf.writeInt(this.coordinates.y);
            buf.writeInt(this.coordinates.z);
            buf.writeInt(this.coordinates.dim);
        } else {
            buf.writeByte(0);
        }
    }

    public static class Handler
    implements IMessageHandler<DataRemovePacketC, IMessage> {
        public IMessage onMessage(DataRemovePacketC message, MessageContext ctx) {
            WorldCoordinates pos = message.coordinates;
            if (pos == null || ctx.side.isServer()) {
                return null;
            }
            World world = ChannelHandler.getClientWorld();
            if (world != null && world.field_73011_w.field_76574_g == pos.dim) {
                TIWorldData.getWorldData(world).removeData(message.clazz, pos, false);
            }
            Minecraft.func_71410_x().field_71438_f.func_147586_a(pos.x, pos.y, pos.z);
            return null;
        }
    }
}

