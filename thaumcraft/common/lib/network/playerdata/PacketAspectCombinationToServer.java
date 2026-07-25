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
 *  net.minecraft.tileentity.TileEntity
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
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketAspectPool;
import thaumcraft.common.lib.research.ResearchManager;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.tiles.TileResearchTable;

public class PacketAspectCombinationToServer
implements IMessage,
IMessageHandler<PacketAspectCombinationToServer, IMessage> {
    private int dim;
    private int playerid;
    private int x;
    private int y;
    private int z;
    Aspect aspect1;
    Aspect aspect2;
    boolean ab1;
    boolean ab2;

    public PacketAspectCombinationToServer() {
    }

    public PacketAspectCombinationToServer(EntityPlayer player, int x, int y, int z, Aspect aspect1, Aspect aspect2, boolean ab1, boolean ab2, boolean ret) {
        this.dim = player.field_70170_p.field_73011_w.field_76574_g;
        this.playerid = player.func_145782_y();
        this.x = x;
        this.y = y;
        this.z = z;
        this.aspect1 = aspect1;
        this.aspect2 = aspect2;
        this.ab1 = ab1;
        this.ab2 = ab2;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.dim);
        buffer.writeInt(this.playerid);
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.aspect1.getTag());
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.aspect2.getTag());
        buffer.writeBoolean(this.ab1);
        buffer.writeBoolean(this.ab2);
    }

    public void fromBytes(ByteBuf buffer) {
        this.dim = buffer.readInt();
        this.playerid = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.aspect1 = Aspect.getAspect(ByteBufUtils.readUTF8String((ByteBuf)buffer));
        this.aspect2 = Aspect.getAspect(ByteBufUtils.readUTF8String((ByteBuf)buffer));
        this.ab1 = buffer.readBoolean();
        this.ab2 = buffer.readBoolean();
    }

    public IMessage onMessage(PacketAspectCombinationToServer message, MessageContext ctx) {
        WorldServer world = DimensionManager.getWorld((int)message.dim);
        if (world == null || ctx.getServerHandler().field_147369_b != null && ctx.getServerHandler().field_147369_b.func_145782_y() != message.playerid) {
            return null;
        }
        Entity player = world.func_73045_a(message.playerid);
        if (player != null && player instanceof EntityPlayer && message.aspect1 != null && message.aspect1 != null) {
            Aspect combo = ResearchManager.getCombinationResult(message.aspect1, message.aspect2);
            if ((Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect1) > 0 || message.ab1) && (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect2) > 0 || message.ab2)) {
                TileEntity rt = player.field_70170_p.func_147438_o(message.x, message.y, message.z);
                if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect1) <= 0 && message.ab1) {
                    if (rt != null && rt instanceof TileResearchTable) {
                        ((TileResearchTable)rt).bonusAspects.remove(message.aspect1, 1);
                        player.field_70170_p.func_147471_g(message.x, message.y, message.z);
                        rt.func_70296_d();
                    }
                } else {
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(((EntityPlayer)player).func_70005_c_(), message.aspect1, (short)-1);
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(message.aspect1.getTag(), (short)0, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect1)), (EntityPlayerMP)player);
                }
                if (Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect2) <= 0 && message.ab2) {
                    if (rt != null && rt instanceof TileResearchTable) {
                        ((TileResearchTable)rt).bonusAspects.remove(message.aspect2, 1);
                        player.field_70170_p.func_147471_g(message.x, message.y, message.z);
                        rt.func_70296_d();
                    }
                } else {
                    Thaumcraft.proxy.playerKnowledge.addAspectPool(((EntityPlayer)player).func_70005_c_(), message.aspect2, (short)-1);
                    PacketHandler.INSTANCE.sendTo((IMessage)new PacketAspectPool(message.aspect2.getTag(), (short)0, Thaumcraft.proxy.playerKnowledge.getAspectPoolFor(((EntityPlayer)player).func_70005_c_(), message.aspect2)), (EntityPlayerMP)player);
                }
                if (combo != null) {
                    ScanManager.checkAndSyncAspectKnowledge((EntityPlayer)player, combo, 1);
                }
                ResearchManager.scheduleSave((EntityPlayer)player);
            }
        }
        return null;
    }
}

