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
import java.awt.Color;
import thaumcraft.common.Thaumcraft;

public class PacketFXBlockBubble
implements IMessage,
IMessageHandler<PacketFXBlockBubble, IMessage> {
    private int x;
    private int y;
    private int z;
    private int color;

    public PacketFXBlockBubble() {
    }

    public PacketFXBlockBubble(int x, int y, int z, int color) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.color = color;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.color);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.color = buffer.readInt();
    }

    public IMessage onMessage(PacketFXBlockBubble message, MessageContext ctx) {
        Color c = new Color(message.color);
        float r = (float)c.getRed() / 255.0f;
        float g = (float)c.getGreen() / 255.0f;
        float b = (float)c.getBlue() / 255.0f;
        for (int a = 0; a < Thaumcraft.proxy.particleCount(1); ++a) {
            Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), message.x, (float)message.y + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), (float)message.z + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), r, g, b);
            Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), message.x + 1, (float)message.y + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), (float)message.z + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), r, g, b);
            Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), (float)message.x + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), (float)message.y + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.z, r, g, b);
            Thaumcraft.proxy.crucibleBubble(Thaumcraft.proxy.getClientWorld(), (float)message.x + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), (float)message.y + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat(), message.z + 1, r, g, b);
        }
        return null;
    }
}

