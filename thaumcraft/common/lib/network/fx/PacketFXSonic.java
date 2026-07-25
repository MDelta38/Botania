/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.entity.Entity
 */
package thaumcraft.common.lib.network.fx;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import thaumcraft.client.fx.other.FXSonic;
import thaumcraft.common.Thaumcraft;

public class PacketFXSonic
implements IMessage,
IMessageHandler<PacketFXSonic, IMessage> {
    private int source;

    public PacketFXSonic() {
    }

    public PacketFXSonic(int source) {
        this.source = source;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.source);
    }

    public void fromBytes(ByteBuf buffer) {
        this.source = buffer.readInt();
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketFXSonic message, MessageContext ctx) {
        Entity p = Thaumcraft.proxy.getClientWorld().func_73045_a(message.source);
        if (p != null) {
            FXSonic fb = new FXSonic(Thaumcraft.proxy.getClientWorld(), p.field_70165_t, p.field_70163_u, p.field_70161_v, p, 10);
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fb);
        }
        return null;
    }
}

