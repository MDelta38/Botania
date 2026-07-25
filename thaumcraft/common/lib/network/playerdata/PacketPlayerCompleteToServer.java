/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  cpw.mods.fml.common.network.simpleimpl.IMessageHandler
 *  cpw.mods.fml.common.network.simpleimpl.MessageContext
 *  io.netty.buffer.ByteBuf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.entity.player.EntityPlayerMP
 *  net.minecraft.util.ChatComponentTranslation
 *  net.minecraft.util.IChatComponent
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package thaumcraft.common.lib.network.playerdata;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.network.playerdata.PacketResearchComplete;
import thaumcraft.common.lib.research.ResearchManager;

public class PacketPlayerCompleteToServer
implements IMessage,
IMessageHandler<PacketPlayerCompleteToServer, IMessage> {
    private String key;
    private int dim;
    private String username;
    private byte type;

    public PacketPlayerCompleteToServer() {
    }

    public PacketPlayerCompleteToServer(String key, String username, int dim, byte type) {
        this.key = key;
        this.dim = dim;
        this.username = username;
        this.type = type;
    }

    public void toBytes(ByteBuf buffer) {
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.key);
        buffer.writeInt(this.dim);
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.username);
        buffer.writeByte((int)this.type);
    }

    public void fromBytes(ByteBuf buffer) {
        this.key = ByteBufUtils.readUTF8String((ByteBuf)buffer);
        this.dim = buffer.readInt();
        this.username = ByteBufUtils.readUTF8String((ByteBuf)buffer);
        this.type = buffer.readByte();
    }

    public IMessage onMessage(PacketPlayerCompleteToServer message, MessageContext ctx) {
        WorldServer world = DimensionManager.getWorld((int)message.dim);
        if (world == null || ctx.getServerHandler().field_147369_b != null && !ctx.getServerHandler().field_147369_b.func_70005_c_().equals(message.username)) {
            return null;
        }
        EntityPlayer player = world.func_72924_a(message.username);
        if (player != null && !ResearchManager.isResearchComplete(message.username, message.key)) {
            if (ResearchManager.doesPlayerHaveRequisites(message.username, message.key)) {
                if (message.type == 0) {
                    for (Aspect a : ResearchCategories.getResearch((String)message.key).tags.getAspects()) {
                        Thaumcraft.proxy.playerKnowledge.addAspectPool(message.username, a, (short)(-ResearchCategories.getResearch((String)message.key).tags.getAmount(a)));
                        ResearchManager.scheduleSave(player);
                        PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(a.getTag(), (short)(-ResearchCategories.getResearch((String)message.key).tags.getAmount(a)), Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(message.username, a)), (EntityPlayerMP)player);
                    }
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete(message.key), (EntityPlayerMP)player);
                    Thaumcraft.proxy.getResearchManager().completeResearch(player, message.key);
                    if (ResearchCategories.getResearch((String)message.key).siblings != null) {
                        for (String sibling : ResearchCategories.getResearch((String)message.key).siblings) {
                            if (ResearchManager.isResearchComplete(message.username, sibling) || !ResearchManager.doesPlayerHaveRequisites(message.username, sibling)) continue;
                            PacketHandler.INSTANCE.sendTo((IMessage)new PacketResearchComplete(sibling), (EntityPlayerMP)player);
                            Thaumcraft.proxy.getResearchManager().completeResearch(player, sibling);
                        }
                    }
                } else if (message.type == 1) {
                    ResearchManager.createResearchNoteForPlayer((World)world, player, message.key);
                }
                world.func_72956_a((Entity)player, "thaumcraft:learn", 0.75f, 1.0f);
            } else {
                player.func_145747_a((IChatComponent)new ChatComponentTranslation(StatCollector.func_74838_a((String)"tc.researcherror"), new Object[0]));
            }
        }
        return null;
    }
}

