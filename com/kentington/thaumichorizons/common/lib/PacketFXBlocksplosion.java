/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 */
package com.kentington.thaumichorizons.common.lib;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;

public class PacketFXBlocksplosion
implements IMessage,
IMessageHandler<PacketFXBlocksplosion, IMessage> {
    int x;
    int y;
    int z;
    int id;
    int md;

    public PacketFXBlocksplosion() {
    }

    public PacketFXBlocksplosion(int id, int md, int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
        this.md = md;
    }

    public IMessage onMessage(PacketFXBlocksplosion message, MessageContext ctx) {
        Minecraft.func_71410_x().field_71452_i.func_147215_a(message.x, message.y, message.z, Block.func_149729_e((int)message.id), message.md);
        return null;
    }

    public void fromBytes(ByteBuf buf) {
        this.x = buf.readInt();
        this.y = buf.readInt();
        this.z = buf.readInt();
        this.id = buf.readInt();
        this.md = buf.readInt();
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.x);
        buf.writeInt(this.y);
        buf.writeInt(this.z);
        buf.writeInt(this.id);
        buf.writeInt(this.md);
    }
}

