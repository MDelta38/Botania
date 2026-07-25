/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 */
package thaumcraft.common.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import thaumcraft.common.tiles.TileOwned;

public class TileArcanePressurePlate
extends TileOwned {
    public byte setting = 0;

    @Override
    public boolean canUpdate() {
        return false;
    }

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        super.readCustomNBT(nbttagcompound);
        this.setting = nbttagcompound.func_74771_c("setting");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        super.writeCustomNBT(nbttagcompound);
        nbttagcompound.func_74774_a("setting", this.setting);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        if (this.field_145850_b != null && this.field_145850_b.field_72995_K) {
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
        }
    }
}

