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
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import thaumcraft.common.Thaumcraft;

public class PacketFXWispZap
implements IMessage,
IMessageHandler<PacketFXWispZap, IMessage> {
    private int source;
    private int target;

    public PacketFXWispZap() {
    }

    public PacketFXWispZap(int source, int target) {
        this.source = source;
        this.target = target;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.source);
        buffer.writeInt(this.target);
    }

    public void fromBytes(ByteBuf buffer) {
        this.source = buffer.readInt();
        this.target = buffer.readInt();
    }

    public IMessage onMessage(PacketFXWispZap message, MessageContext ctx) {
        Minecraft mc = FMLClientHandler.instance().getClient();
        WorldClient world = mc.field_71441_e;
        Entity var2 = this.getEntityByID(message.source, mc, world);
        Entity var3 = this.getEntityByID(message.target, mc, world);
        if (var2 != null && var3 != null) {
            Thaumcraft.proxy.bolt(var2.field_70170_p, var2, var3);
        }
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    private Entity getEntityByID(int par1, Minecraft mc, WorldClient world) {
        return par1 == mc.field_71439_g.func_145782_y() ? mc.field_71439_g : world.func_73045_a(par1);
    }
}

