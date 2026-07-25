/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.StatCollector
 */
package thaumcraft.common.lib.network.playerdata;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import thaumcraft.client.lib.PlayerNotifications;

public class PacketWarpMessage
implements IMessage,
IMessageHandler<PacketWarpMessage, IMessage> {
    protected int data = 0;
    protected byte type = 0;

    public PacketWarpMessage() {
    }

    public PacketWarpMessage(EntityPlayer player, byte type, int change) {
        this.data = change;
        this.type = type;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.data);
        buffer.writeByte((int)this.type);
    }

    public void fromBytes(ByteBuf buffer) {
        this.data = buffer.readInt();
        this.type = buffer.readByte();
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketWarpMessage message, MessageContext ctx) {
        if (message.data != 0) {
            if (message.type == 0 && message.data > 0) {
                String text = StatCollector.func_74838_a((String)"tc.addwarp");
                if (message.data < 0) {
                    text = StatCollector.func_74838_a((String)"tc.removewarp");
                } else {
                    Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:whispers", 0.5f, 1.0f);
                }
                PlayerNotifications.addNotification(text);
            } else if (message.type == 1) {
                String text = StatCollector.func_74838_a((String)"tc.addwarpsticky");
                if (message.data < 0) {
                    text = StatCollector.func_74838_a((String)"tc.removewarpsticky");
                } else {
                    Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:whispers", 0.5f, 1.0f);
                }
                PlayerNotifications.addNotification(text);
            } else if (message.data > 0) {
                String text = StatCollector.func_74838_a((String)"tc.addwarptemp");
                if (message.data < 0) {
                    text = StatCollector.func_74838_a((String)"tc.removewarptemp");
                }
                PlayerNotifications.addNotification(text);
            }
        }
        return null;
    }
}

