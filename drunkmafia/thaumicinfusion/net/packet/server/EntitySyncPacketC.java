/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.PacketBuffer
 */
package drunkmafia.thaumicinfusion.net.packet.server;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import drunkmafia.thaumicinfusion.net.ChannelHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;

public class EntitySyncPacketC
implements IMessage {
    private int id;
    private NBTTagCompound tagCompound;
    private Entity entity;

    public EntitySyncPacketC() {
    }

    public EntitySyncPacketC(Entity entity) {
        this.entity = entity;
    }

    public void fromBytes(ByteBuf buf) {
        try {
            this.id = buf.readInt();
            this.tagCompound = new PacketBuffer(buf).func_150793_b();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toBytes(ByteBuf buf) {
        try {
            if (this.entity != null) {
                buf.writeInt(this.entity.func_145782_y());
                NBTTagCompound tag = new NBTTagCompound();
                this.entity.func_70109_d(tag);
                new PacketBuffer(buf).func_150786_a(tag);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Handler
    implements IMessageHandler<EntitySyncPacketC, IMessage> {
        public IMessage onMessage(EntitySyncPacketC message, MessageContext ctx) {
            NBTTagCompound tag = message.tagCompound;
            if (tag == null || ctx.side.isServer()) {
                return null;
            }
            Entity entity = ChannelHandler.getClientWorld().func_73045_a(message.id);
            if (entity != null) {
                entity.func_70020_e(tag);
            }
            return null;
        }
    }
}

