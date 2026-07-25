/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.ByteBufUtils
 *  io.netty.buffer.ByteBuf
 *  io.netty.channel.ChannelHandlerContext
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.WorldServer
 *  net.minecraftforge.common.DimensionManager
 */
package witchinggadgets.common.util.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.DimensionManager;
import witchinggadgets.common.blocks.tiles.TileEntityWGBase;
import witchinggadgets.common.util.network.AbstractPacket;

public class PacketTileUpdate
extends AbstractPacket {
    int worldId;
    int x;
    int y;
    int z;
    NBTTagCompound tag;

    public PacketTileUpdate() {
    }

    public PacketTileUpdate(TileEntityWGBase te) {
        this.worldId = te.func_145831_w().field_73011_w.field_76574_g;
        this.x = te.field_145851_c;
        this.y = te.field_145848_d;
        this.z = te.field_145849_e;
        this.tag = new NBTTagCompound();
        te.writeCustomNBT(this.tag);
    }

    @Override
    public void encodeInto(ChannelHandlerContext context, ByteBuf buffer) {
        buffer.writeInt(this.worldId);
        buffer.writeInt(this.x);
        buffer.writeInt(this.y);
        buffer.writeInt(this.z);
        ByteBufUtils.writeTag((ByteBuf)buffer, (NBTTagCompound)this.tag);
    }

    @Override
    public void decodeInto(ChannelHandlerContext context, ByteBuf buffer) {
        this.worldId = buffer.readInt();
        this.x = buffer.readInt();
        this.y = buffer.readInt();
        this.z = buffer.readInt();
        this.tag = ByteBufUtils.readTag((ByteBuf)buffer);
    }

    @Override
    public void handleClientSide(EntityPlayer clientPlayer) {
        WorldServer world = DimensionManager.getWorld((int)this.worldId);
        if (world == null) {
            return;
        }
        if (world.func_147438_o(this.x, this.y, this.z) != null && world.func_147438_o(this.x, this.y, this.z) instanceof TileEntityWGBase) {
            ((TileEntityWGBase)world.func_147438_o(this.x, this.y, this.z)).readCustomNBT(this.tag);
        }
    }

    @Override
    public void handleServerSide(EntityPlayer p2) {
        WorldServer world = DimensionManager.getWorld((int)this.worldId);
        if (world == null) {
            return;
        }
        if (world.func_147438_o(this.x, this.y, this.z) != null && world.func_147438_o(this.x, this.y, this.z) instanceof TileEntityWGBase) {
            ((TileEntityWGBase)world.func_147438_o(this.x, this.y, this.z)).readCustomNBT(this.tag);
        }
    }
}

