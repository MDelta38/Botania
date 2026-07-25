/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  thaumcraft.api.ThaumcraftApiHelper
 *  thaumcraft.api.research.ResearchCategories
 *  thaumcraft.api.research.ResearchCategoryList
 */
package witchinggadgets.common.util.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategoryList;
import witchinggadgets.common.WGResearch;
import witchinggadgets.common.util.network.AbstractPacket;

public class PacketClientNotifier
extends AbstractPacket {
    int packetid;

    public PacketClientNotifier() {
    }

    public PacketClientNotifier(int id) {
        this.packetid = id;
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        this.packetid = buffer.readInt();
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(this.packetid);
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
        switch (this.packetid) {
            case 0: {
                ((ResearchCategoryList)ResearchCategories.researchCategories.get((Object)"WITCHGADG")).background = ThaumcraftApiHelper.isResearchComplete((String)player.func_70005_c_(), (String)"WGFAKEELDRITCHMINOR") ? WGResearch.wgbackgrounds[1] : WGResearch.wgbackgrounds[0];
            }
        }
    }

    @Override
    public void handleServerSide(EntityPlayer player2) {
    }
}

