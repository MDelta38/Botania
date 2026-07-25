/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.StatCollector
 */
package thaumcraft.common.lib.network.playerdata;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.StatCollector;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.common.Thaumcraft;

public class PacketAspectPool
implements IMessage,
IMessageHandler<PacketAspectPool, IMessage> {
    private String key;
    private Short amount;
    private Short total;
    private static long lastSound = 0L;

    public PacketAspectPool() {
    }

    public PacketAspectPool(String key, Short amount, Short total) {
        this.key = key;
        this.amount = amount;
        this.total = total;
    }

    public void toBytes(ByteBuf buffer) {
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.key);
        buffer.writeShort((int)this.amount.shortValue());
        buffer.writeShort((int)this.total.shortValue());
    }

    public void fromBytes(ByteBuf buffer) {
        this.key = ByteBufUtils.readUTF8String((ByteBuf)buffer);
        this.amount = buffer.readShort();
        this.total = buffer.readShort();
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketAspectPool message, MessageContext ctx) {
        boolean success;
        if (Aspect.getAspect(message.key) != null && (success = Thaumcraft.proxy.getPlayerKnowledge().setAspectPool(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), Aspect.getAspect(message.key), message.total)) && message.amount > 0) {
            String text = StatCollector.func_74838_a((String)"tc.addaspectpool");
            text = text.replaceAll("%s", message.amount + "");
            text = text.replaceAll("%n", Aspect.getAspect(message.key).getName());
            PlayerNotifications.addNotification(text, Aspect.getAspect(message.key));
            for (int a = 0; a < message.amount; ++a) {
                PlayerNotifications.addAspectNotification(Aspect.getAspect(message.key));
            }
            if (System.currentTimeMillis() > lastSound) {
                Minecraft.func_71410_x().field_71439_g.func_85030_a("random.orb", 0.1f, 0.9f + Minecraft.func_71410_x().field_71439_g.field_70170_p.field_73012_v.nextFloat() * 0.2f);
                lastSound = System.currentTimeMillis() + 100L;
            }
        }
        return null;
    }
}

