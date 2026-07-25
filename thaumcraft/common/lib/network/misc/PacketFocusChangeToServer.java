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
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package thaumcraft.common.lib.network.misc;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;

public class PacketFocusChangeToServer
implements IMessage,
IMessageHandler<PacketFocusChangeToServer, IMessage> {
    private int dim;
    private int playerid;
    private String focus;

    public PacketFocusChangeToServer() {
    }

    public PacketFocusChangeToServer(EntityPlayer player, String focus) {
        this.dim = player.field_70170_p.field_73011_w.field_76574_g;
        this.playerid = player.func_145782_y();
        this.focus = focus;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.dim);
        buffer.writeInt(this.playerid);
        ByteBufUtils.writeUTF8String((ByteBuf)buffer, (String)this.focus);
    }

    public void fromBytes(ByteBuf buffer) {
        this.dim = buffer.readInt();
        this.playerid = buffer.readInt();
        this.focus = ByteBufUtils.readUTF8String((ByteBuf)buffer);
    }

    public IMessage onMessage(PacketFocusChangeToServer message, MessageContext ctx) {
        WorldServer world = DimensionManager.getWorld((int)message.dim);
        if (world == null || ctx.getServerHandler().field_147369_b != null && ctx.getServerHandler().field_147369_b.func_145782_y() != message.playerid) {
            return null;
        }
        Entity player = world.func_73045_a(message.playerid);
        if (player != null && player instanceof EntityPlayer && ((EntityPlayer)player).func_70694_bm() != null && ((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemWandCasting && !((ItemWandCasting)((EntityPlayer)player).func_70694_bm().func_77973_b()).isSceptre(((EntityPlayer)player).func_70694_bm())) {
            WandManager.changeFocus(((EntityPlayer)player).func_70694_bm(), (World)world, (EntityPlayer)player, message.focus);
        }
        return null;
    }
}

