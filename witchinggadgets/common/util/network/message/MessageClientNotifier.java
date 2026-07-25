/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.client.Minecraft
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchCategoryList
 */
package witchinggadgets.common.util.network.message;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import witchinggadgets.common.WGResearch;

public class MessageClientNotifier
implements IMessage {
    int packetid;

    public MessageClientNotifier() {
    }

    public MessageClientNotifier(int id) {
        this.packetid = id;
    }

    public void fromBytes(ByteBuf buffer) {
        this.packetid = buffer.readInt();
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.packetid);
    }

    public static class HandlerClient
    implements IMessageHandler<MessageClientNotifier, IMessage> {
        public IMessage onMessage(MessageClientNotifier message, MessageContext ctx) {
            switch (message.packetid) {
                case 0: {
                    if (Minecraft.func_71410_x().field_71439_g == null) break;
                    ((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)"WITCHGADG")).background = ThaumcraftApiHelper.isResearchComplete((String)Minecraft.func_71410_x().field_71439_g.func_70005_c_(), (String)"WGFAKEELDRITCHMINOR") ? WGResearch.wgbackgrounds[1] : WGResearch.wgbackgrounds[0];
                }
            }
            return null;
        }
    }
}

