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
 *  net.minecraft.client.entity.EntityClientPlayerMP
 */
package thaumcraft.common.lib.network.misc;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import thaumcraft.client.lib.ClientTickEventsFML;
import thaumcraft.client.lib.RenderEventHandler;

public class PacketMiscEvent
implements IMessage,
IMessageHandler<PacketMiscEvent, IMessage> {
    private short type;
    public static final short WARP_EVENT = 0;
    public static final short MIST_EVENT = 1;
    public static final short MIST_EVENT_SHORT = 2;

    public PacketMiscEvent() {
    }

    public PacketMiscEvent(short type) {
        this.type = type;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeShort((int)this.type);
    }

    public void fromBytes(ByteBuf buffer) {
        this.type = buffer.readShort();
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketMiscEvent message, MessageContext ctx) {
        EntityClientPlayerMP p = Minecraft.func_71410_x().field_71439_g;
        switch (message.type) {
            case 0: {
                ClientTickEventsFML.warpVignette = 100;
                p.field_70170_p.func_72980_b(p.field_70165_t, p.field_70163_u, p.field_70161_v, "thaumcraft:heartbeat", 1.0f, 1.0f, false);
                break;
            }
            case 1: {
                RenderEventHandler.fogFiddled = true;
                RenderEventHandler.fogDuration = 2400;
                break;
            }
            case 2: {
                RenderEventHandler.fogFiddled = true;
                if (RenderEventHandler.fogDuration >= 200) break;
                RenderEventHandler.fogDuration = 200;
                break;
            }
        }
        return null;
    }
}

