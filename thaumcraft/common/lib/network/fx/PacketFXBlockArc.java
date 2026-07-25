/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 */
package thaumcraft.common.lib.network.fx;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.boss.EntityCultistPortal;

public class PacketFXBlockArc
implements IMessage,
IMessageHandler<PacketFXBlockArc, IMessage> {
    private int x;
    private int y;
    private int z;
    private int source;

    public PacketFXBlockArc() {
    }

    public PacketFXBlockArc(int x, int y, int z, int source) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.source = source;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        buffer.writeInt(this.source);
    }

    public void fromBytes(ByteBuf buffer) {
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.source = buffer.readInt();
    }

    public IMessage onMessage(PacketFXBlockArc message, MessageContext ctx) {
        Entity p = Thaumcraft.proxy.getClientWorld().func_73045_a(message.source);
        if (p != null) {
            float r = 0.3f - Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.1f;
            float g = 0.0f;
            float b = 0.5f + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.2f;
            if (p instanceof EntityCultistPortal) {
                r = 0.5f + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.2f;
                g = 0.0f;
                b = 0.0f;
            }
            Thaumcraft.proxy.arcLightning(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70121_D.field_72338_b + (double)(p.field_70131_O / 2.0f), p.field_70161_v, (double)message.x + 0.5, message.y + 1, (double)message.z + 0.5, r, g, b, 0.5f);
        }
        return null;
    }
}

