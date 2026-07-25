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
 */
package thaumcraft.common.lib.network.playerdata;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import thaumcraft.common.Thaumcraft;

public class PacketSyncWipe
implements IMessage,
IMessageHandler<PacketSyncWipe, IMessage> {
    public void toBytes(ByteBuf buffer) {
    }

    public void fromBytes(ByteBuf buffer) {
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketSyncWipe message, MessageContext ctx) {
        Thaumcraft.proxy.getPlayerKnowledge().wipePlayerKnowledge(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
        return null;
    }
}

