/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
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

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import thaumcraft.common.entities.golems.ItemGolemBell;
import thaumcraft.common.items.equipment.ItemElementalShovel;
import thaumcraft.common.items.wands.ItemWandCasting;
import thaumcraft.common.items.wands.WandManager;

public class PacketItemKeyToServer
implements IMessage,
IMessageHandler<PacketItemKeyToServer, IMessage> {
    private int dim;
    private int playerid;
    private byte key;

    public PacketItemKeyToServer() {
    }

    public PacketItemKeyToServer(EntityPlayer player, int key) {
        this.dim = player.field_70170_p.field_73011_w.field_76574_g;
        this.playerid = player.func_145782_y();
        this.key = (byte)key;
    }

    public void toBytes(ByteBuf buffer) {
        buffer.writeInt(this.dim);
        buffer.writeInt(this.playerid);
        buffer.writeByte((int)this.key);
    }

    public void fromBytes(ByteBuf buffer) {
        this.dim = buffer.readInt();
        this.playerid = buffer.readInt();
        this.key = buffer.readByte();
    }

    public IMessage onMessage(PacketItemKeyToServer message, MessageContext ctx) {
        WorldServer world = DimensionManager.getWorld((int)message.dim);
        if (world == null) {
            return null;
        }
        Entity player = world.func_73045_a(message.playerid);
        if (player != null && player instanceof EntityPlayer && ((EntityPlayer)player).func_70694_bm() != null) {
            if (message.key == 0 && ((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemGolemBell) {
                ItemGolemBell.resetMarkers(((EntityPlayer)player).func_70694_bm(), (World)world, (EntityPlayer)player);
            }
            if (message.key == 1 && ((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemWandCasting) {
                WandManager.toggleMisc(((EntityPlayer)player).func_70694_bm(), (World)world, (EntityPlayer)player);
            }
            if (message.key == 1 && ((EntityPlayer)player).func_70694_bm().func_77973_b() instanceof ItemElementalShovel) {
                ItemElementalShovel cfr_ignored_0 = (ItemElementalShovel)((EntityPlayer)player).func_70694_bm().func_77973_b();
                byte b = ItemElementalShovel.getOrientation(((EntityPlayer)player).func_70694_bm());
                ItemElementalShovel cfr_ignored_1 = (ItemElementalShovel)((EntityPlayer)player).func_70694_bm().func_77973_b();
                ItemElementalShovel.setOrientation(((EntityPlayer)player).func_70694_bm(), (byte)(b + 1));
            }
        }
        return null;
    }
}

