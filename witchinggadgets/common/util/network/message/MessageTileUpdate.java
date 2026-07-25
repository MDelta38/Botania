/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package witchinggadgets.common.util.network.message;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;

public class MessageTileUpdate
implements IMessage {
    int worldId;
    int x;
    int y;
    int z;
    NBTTagCompound tag;

    public MessageTileUpdate() {
    }

    public MessageTileUpdate(TileEntityWGBase te) {
        this.worldId = te.func_145831_w().field_73011_w.field_76574_g;
        this.x = te.field_145851_c;
        this.y = te.field_145848_d;
        this.z = te.field_145849_e;
        this.tag = new NBTTagCompound();
        te.writeCustomNBT(this.tag);
    }

    public void fromBytes(ByteBuf buffer) {
        this.worldId = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.tag = ByteBufUtils.readTag((ByteBuf)buffer);
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.worldId);
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        ByteBufUtils.writeTag((ByteBuf)buffer, (NBTTagCompound)this.tag);
    }

    public static class HandlerServer
    implements IMessageHandler<MessageTileUpdate, IMessage> {
        public IMessage onMessage(MessageTileUpdate message, MessageContext ctx) {
            WorldServer world = DimensionManager.getWorld((int)message.worldId);
            if (world != null && world.func_147438_o(message.x, message.y, message.z) != null && world.func_147438_o(message.x, message.y, message.z) instanceof TileEntityWGBase) {
                ((TileEntityWGBase)world.func_147438_o(message.x, message.y, message.z)).readCustomNBT(message.tag);
            }
            return null;
        }
    }

    public static class HandlerClient
    implements IMessageHandler<MessageTileUpdate, IMessage> {
        public IMessage onMessage(MessageTileUpdate message, MessageContext ctx) {
            WorldServer world = DimensionManager.getWorld((int)message.worldId);
            if (world != null && world.func_147438_o(message.x, message.y, message.z) != null && world.func_147438_o(message.x, message.y, message.z) instanceof TileEntityWGBase) {
                ((TileEntityWGBase)world.func_147438_o(message.x, message.y, message.z)).readCustomNBT(message.tag);
            }
            return null;
        }
    }
}

