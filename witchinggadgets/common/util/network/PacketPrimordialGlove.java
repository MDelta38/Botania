/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package witchinggadgets.common.util.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import witchinggadgets.WitchingGadgets;
import witchinggadgets.common.util.network.AbstractPacket;

public class PacketPrimordialGlove
extends AbstractPacket {
    int dim;
    int playerid;
    byte type;
    int selected;

    public PacketPrimordialGlove() {
    }

    public PacketPrimordialGlove(EntityPlayer player, byte type, int i) {
        this.dim = player.field_70170_p.field_73011_w.field_76574_g;
        this.playerid = player.func_145782_y();
        this.type = type;
        this.selected = i;
    }

    @Override
    public void decodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        this.dim = buffer.readInt();
        this.playerid = buffer.readInt();
        this.type = buffer.readByte();
        this.selected = buffer.readInt();
    }

    @Override
    public void encodeInto(ChannelHandlerContext ctx, ByteBuf buffer) {
        buffer.writeInt(this.dim);
        buffer.writeInt(this.playerid);
        buffer.writeByte((int)this.type);
        buffer.writeInt(this.selected);
    }

    @Override
    public void handleClientSide(EntityPlayer player) {
    }

    @Override
    public void handleServerSide(EntityPlayer player2) {
        WorldServer world = DimensionManager.getWorld((int)this.dim);
        if (world == null) {
            return;
        }
        Entity ent = world.func_73045_a(this.playerid);
        if (!(ent instanceof EntityPlayer)) {
            return;
        }
        EntityPlayer player = (EntityPlayer)ent;
        if (this.type == 0 && player.func_71045_bC() != null) {
            if (!player.func_71045_bC().func_77942_o()) {
                player.func_71045_bC().func_77982_d(new NBTTagCompound());
            }
            player.func_71045_bC().func_77978_p().func_74768_a("selected", this.selected);
        } else if (this.type == 1) {
            player.openGui((Object)WitchingGadgets.instance, 7, (World)world, (int)player.field_70165_t, (int)player.field_70163_u, (int)player.field_70161_v);
        }
    }
}

