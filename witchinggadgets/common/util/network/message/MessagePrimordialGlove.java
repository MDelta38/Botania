/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package witchinggadgets.common.util.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import witchinggadgets.WitchingGadgets;

public class MessagePrimordialGlove
implements IMessage {
    int dim;
    int playerid;
    byte type;
    int selected;

    public MessagePrimordialGlove() {
    }

    public MessagePrimordialGlove(EntityPlayer player, byte type, int i) {
        this.dim = player.field_70170_p.field_73011_w.field_76574_g;
        this.playerid = player.func_145782_y();
        this.type = type;
        this.selected = i;
    }

    public void fromBytes(ByteBuf buffer) {
        this.dim = buffer.readInt();
        this.playerid = buffer.readInt();
        this.type = buffer.readByte();
        this.selected = buffer.readInt();
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.dim);
        buffer.writeInt(this.playerid);
        buffer.writeByte((int)this.type);
        buffer.writeInt(this.selected);
    }

    public static class HandlerServer
    implements IMessageHandler<MessagePrimordialGlove, IMessage> {
        public IMessage onMessage(MessagePrimordialGlove message, MessageContext ctx) {
            Entity ent;
            WorldServer world = DimensionManager.getWorld((int)message.dim);
            if (world != null && (ent = world.func_73045_a(message.playerid)) instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer)ent;
                if (message.type == 0 && player.func_71045_bC() != null) {
                    if (!player.func_71045_bC().func_77942_o()) {
                        player.func_71045_bC().func_77982_d(new NBTTagCompound());
                    }
                    player.func_71045_bC().func_77978_p().func_74768_a("selected", message.selected);
                } else if (message.type == 1) {
                    player.openGui((Object)WitchingGadgets.instance, 7, (World)world, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
                }
            }
            return null;
        }
    }
}

