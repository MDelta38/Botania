/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.Packet
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package witchinggadgets.common.blocks.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityWGBase
extends TileEntity {
    public void func_145839_a(NBTTagCompound tags) {
        super.func_145839_a(tags);
        this.readCustomNBT(tags);
    }

    public abstract void readCustomNBT(NBTTagCompound var1);

    public void func_145841_b(NBTTagCompound tags) {
        super.func_145841_b(tags);
        this.writeCustomNBT(tags);
    }

    public abstract void writeCustomNBT(NBTTagCompound var1);

    public Packet func_145844_m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.func_145841_b(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, 3, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        this.func_145839_a(pkt.func_148857_g());
    }
}

