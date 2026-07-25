/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 *  net.minecraft.tileentity.TileEntity
 */
package thaumic.tinkerer.common.block.tile;

import appeng.api.movable.IMovableTile;
import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileCamo
extends TileEntity
implements IMovableTile {
    private static final String TAG_CAMO = "camo";
    private static final String TAG_CAMO_META = "camoMeta";
    public Block camo;
    public int camoMeta;

    public boolean canUpdate() {
        return false;
    }

    public void func_145841_b(NBTTagCompound par1nbtTagCompound) {
        super.func_145841_b(par1nbtTagCompound);
        this.writeCustomNBT(par1nbtTagCompound);
    }

    public void func_145839_a(NBTTagCompound par1nbtTagCompound) {
        super.func_145839_a(par1nbtTagCompound);
        this.readCustomNBT(par1nbtTagCompound);
    }

    public void writeCustomNBT(NBTTagCompound cmp) {
        if (this.camo != null) {
            cmp.func_74778_a(TAG_CAMO, Block.field_149771_c.func_148750_c((Object)this.camo));
            cmp.func_74768_a(TAG_CAMO_META, this.camoMeta);
        }
    }

    public void readCustomNBT(NBTTagCompound cmp) {
        this.camo = Block.func_149684_b((String)cmp.func_74779_i(TAG_CAMO));
        this.camoMeta = cmp.func_74762_e(TAG_CAMO_META);
    }

    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeCustomNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.field_145851_c, this.field_145848_d, this.field_145849_e, -999, nbttagcompound);
    }

    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(manager, packet);
        this.readCustomNBT(packet.func_148857_g());
        this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }

    @Override
    public boolean prepareToMove() {
        return true;
    }

    @Override
    public void doneMoving() {
    }
}

