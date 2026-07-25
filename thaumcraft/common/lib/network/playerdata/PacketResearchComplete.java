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
 *  net.minecraft.entity.player.EntityPlayer
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
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.StatCollector;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.client.gui.GuiResearchBrowser;
import thaumcraft.client.lib.ClientTickEventsFML;
import thaumcraft.client.lib.PlayerNotifications;
import thaumcraft.common.Thaumcraft;

public class PacketResearchComplete
implements IMessage,
IMessageHandler<PacketResearchComplete, IMessage> {
    private String key;

    public PacketResearchComplete() {
    }

    public PacketResearchComplete(String key) {
        this.key = key;
    }

    public void toBytes(ByteBuf buffer) {
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.key);
    }

    public void fromBytes(ByteBuf buffer) {
        this.key = ByteBufUtils.readUTF8String((ByteBuf)buffer);
    }

    @SideOnly(value=Side.CLIENT)
    public IMessage onMessage(PacketResearchComplete message, MessageContext ctx) {
        if (message.key != null && message.key.length() > 0) {
            Thaumcraft.proxy.getResearchManager().completeResearch((EntityPlayer)Minecraft.func_71410_x().field_71439_g, message.key);
            if (message.key.startsWith("@")) {
                String text = StatCollector.func_74838_a((String)"tc.addclue");
                PlayerNotifications.addNotification("\u00a7a" + text);
                Minecraft.func_71410_x().field_71439_g.func_85030_a("thaumcraft:learn", 0.2f, 1.0f + Minecraft.func_71410_x().field_71439_g.field_70170_p.field_73012_v.nextFloat() * 0.1f);
            } else if (!ResearchCategories.getResearch(message.key).isVirtual()) {
                ClientTickEventsFML.researchPopup.queueResearchInformation(ResearchCategories.getResearch(message.key));
                GuiResearchBrowser.highlightedItem.add(message.key);
                GuiResearchBrowser.highlightedItem.add(ResearchCategories.getResearch((String)message.key).category);
            }
            if (Minecraft.func_71410_x().field_71462_r instanceof GuiResearchBrowser) {
                ArrayList<String> al = GuiResearchBrowser.completedResearch.get(Minecraft.func_71410_x().field_71439_g.func_70005_c_());
                if (al == null) {
                    al = new ArrayList();
                }
                al.add(message.key);
                GuiResearchBrowser.completedResearch.put(Minecraft.func_71410_x().field_71439_g.func_70005_c_(), al);
                ((GuiResearchBrowser)Minecraft.func_71410_x().field_71462_r).updateResearch();
            }
        }
        return null;
    }
}

