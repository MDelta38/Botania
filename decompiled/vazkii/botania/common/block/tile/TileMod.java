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
package vazkii.botania.common.block.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileMod
extends TileEntity {
    public void func_145841_b(NBTTagCompound par1nbtTagCompound) {
        super.func_145841_b(par1nbtTagCompound);
        this.writeCustomNBT(par1nbtTagCompound);
    }

    public void func_145839_a(NBTTagCompound par1nbtTagCompound) {
        super.func_145839_a(par1nbtTagCompound);
        this.readCustomNBT(par1nbtTagCompound);
    }

    public void writeCustomNBT(NBTTagCompound cmp) {
    }

    public void readCustomNBT(NBTTagCompound cmp) {
    }

    public Packet func_145844_m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(net, packet);
        this.readCustomNBT(packet.func_148857_g());
    }
}

