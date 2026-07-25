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
import thaumcraft.client.gui.GuiResearchBrowser;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.common.Thaumcraft;

public class PacketAspectDiscovery
implements IMessage,
IMessageHandler<PacketAspectDiscovery, IMessage> {
    private String key;

    public PacketAspectDiscovery() {
    }

    public PacketAspectDiscovery(String key) {
        this.key = key;
    }

    public void toBytes(ByteBuf buffer) {
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.key);
    }

    public void fromBytes(ByteBuf buffer) {
        this.key = ByteBufUtils.readUTF8String((ByteBuf)buffer);
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketAspectDiscovery message, MessageContext ctx) {
        if (Aspect.getAspect(message.key) != null) {
            Thaumcraft.proxy.getPlayerKnowledge().addDiscoveredAspect(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), Aspect.getAspect(message.key));
            String text = StatCollector.func_74838_a((String)"tc.addaspectdiscovery");
            text = text.replaceAll("%n", Aspect.getAspect(message.key).getName());
            PlayerNotifications.addNotification("\u00a76" + text, Aspect.getAspect(message.key));
            Minecraft.func_71410_x().field_71439_g.func_85030_a("random.orb", 0.2f, 0.5f + Thaumcraft.proxy.getClientWorld().field_73012_v.nextFloat() * 0.2f);
            GuiResearchBrowser.highlightedItem.add("ASPECTS");
            GuiResearchBrowser.highlightedItem.add("BASICS");
        }
        return null;
    }
}

