/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.network.play.server.S35PacketUpdateTileEntity
 */
package vazkii.botania.common.block.tile;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import vazkii.botania.common.block.tile.TileMod;

public class TileCamo
extends TileMod {
    private static final String TAG_CAMO = "camo";
    private static final String TAG_CAMO_META = "camoMeta";
    public Block camo;
    public int camoMeta;

    public boolean canUpdate() {
        return false;
    }

    @Override
    public void writeCustomNBT(NBTTagCompound cmp) {
        if (this.camo != null) {
            cmp.func_74778_a(TAG_CAMO, Block.field_149771_c.func_148750_c((Object)this.camo));
            cmp.func_74768_a(TAG_CAMO_META, this.camoMeta);
        }
    }

    @Override
    public void readCustomNBT(NBTTagCompound cmp) {
        this.camo = Block.func_149684_b((String)cmp.func_74779_i(TAG_CAMO));
        this.camoMeta = cmp.func_74762_e(TAG_CAMO_META);
    }

    @Override
    public void onDataPacket(NetworkManager manager, S35PacketUpdateTileEntity packet) {
        super.onDataPacket(manager, packet);
        this.field_145850_b.func_147458_c(this.field_145851_c, this.field_145848_d, this.field_145849_e, this.field_145851_c, this.field_145848_d, this.field_145849_e);
    }
}

