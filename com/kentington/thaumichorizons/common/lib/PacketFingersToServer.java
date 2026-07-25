/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;

public class PacketFingersToServer
implements IMessage,
IMessageHandler<PacketFingersToServer, IMessage> {
    private int playerid;
    private int dim;

    public PacketFingersToServer() {
    }

    public PacketFingersToServer(EntityPlayer player, int dim) {
        this.playerid = player.func_145782_y();
        this.dim = dim;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.playerid);
        buffer.writeInt(this.dim);
    }

    public void fromBytes(ByteBuf buffer) {
        this.playerid = buffer.readInt();
        this.dim = buffer.readInt();
    }

    public IMessage onMessage(PacketFingersToServer message, MessageContext ctx) {
        WorldServer world = DimensionManager.getWorld((int)message.dim);
        EntityPlayer player = (EntityPlayer)world.func_73045_a(message.playerid);
        player.openGui((Object)ThaumicHorizons.instance, 9, player.field_70170_p, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        return null;
    }
}

