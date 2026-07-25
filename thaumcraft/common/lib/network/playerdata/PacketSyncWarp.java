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
import thaumcraft.common.Thaumcraft;

public class PacketSyncWarp
implements IMessage,
IMessageHandler<PacketSyncWarp, IMessage> {
    protected int data = 0;
    protected byte type = 0;

    public PacketSyncWarp() {
    }

    public PacketSyncWarp(EntityPlayer player, byte type) {
        if (type == 0) {
            this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpPerm(player.func_70005_c_());
        }
        if (type == 1) {
            this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpSticky(player.func_70005_c_());
        }
        if (type == 2) {
            this.data = Thaumcraft.proxy.getPlayerKnowledge().getWarpTemp(player.func_70005_c_());
        }
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
    public IMessage onMessage(PacketSyncWarp message, MessageContext ctx) {
        if (message.type == 0) {
            Thaumcraft.proxy.getPlayerKnowledge().setWarpPerm(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), message.data);
        } else if (message.type == 1) {
            Thaumcraft.proxy.getPlayerKnowledge().setWarpSticky(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), message.data);
        } else {
            Thaumcraft.proxy.getPlayerKnowledge().setWarpTemp(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), message.data);
        }
        return null;
    }
}

