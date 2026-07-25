/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 */
package thaumcraft.common.lib.network.fx;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import thaumcraft.common.Thaumcraft;

public class PacketFXBlockZap
implements IMessage,
IMessageHandler<PacketFXBlockZap, IMessage> {
    private float x;
    private float y;
    private float z;
    private float dx;
    private float dy;
    private float dz;

    public PacketFXBlockZap() {
    }

    public PacketFXBlockZap(float x, float y, float z, float dx, float dy, float dz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeFloat(this.x);
        buffer.writeFloat(this.y);
        buffer.writeFloat(this.z);
        buffer.writeFloat(this.dx);
        buffer.writeFloat(this.dy);
        buffer.writeFloat(this.dz);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readFloat();
        this.y = buffer.readFloat();
        this.z = buffer.readFloat();
        this.dx = buffer.readFloat();
        this.dy = buffer.readFloat();
        this.dz = buffer.readFloat();
    }

    public IMessage onMessage(PacketFXBlockZap message, MessageContext ctx) {
        Thaumcraft.proxy.nodeBolt(Thaumcraft.proxy.getClientWorld(), message.x, message.y, message.z, message.dx, message.dy, message.dz);
        Thaumcraft.proxy.getClientWorld().func_72980_b((double)message.x, (double)message.y, (double)message.z, "thaumcraft:zap", 0.1f, 1.0f + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.2f, false);
        return null;
    }
}

