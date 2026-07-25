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
package thaumcraft.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileThaumcraft
extends TileEntity {
    public void func_145839_a(NBTTagCompound nbttagcompound) {
        super.func_145839_a(nbttagcompound);
        this.readCustomNBT(nbttagcompound);
    }

    public void readCustomNBT(NBTTagCompound nbttagcompound) {
    }

    public void func_145841_b(NBTTagCompound nbttagcompound) {
        super.func_145841_b(nbttagcompound);
        this.writeCustomNBT(nbttagcompound);
    }

    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
    }

    public Packet func_145844_m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readCustomNBT(pkt.func_148857_g());
    }
}

