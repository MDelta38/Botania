/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.tileentity.TileEntityDispenser
 *  net.minecraft.util.ChunkCoordinates
 */
package vazkii.botania.common.block.tile.string;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.ChunkCoordinates;
import vazkii.botania.common.block.tile.string.TileRedStringContainer;

public class TileRedStringDispenser
extends TileRedStringContainer {
    @Override
    public boolean acceptBlock(int x, int y, int z) {
        TileEntity tile = this.field_145850_b.func_147438_o(x, y, z);
        return tile != null && tile instanceof TileEntityDispenser;
    }

    public void tickDispenser() {
        TileEntity tile;
        ChunkCoordinates bind = this.getBinding();
        if (bind != null && (tile = this.field_145850_b.func_147438_o(bind.field_71574_a, bind.field_71572_b, bind.field_71573_c)) instanceof TileEntityDispenser) {
            this.field_145850_b.func_147464_a(bind.field_71574_a, bind.field_71572_b, bind.field_71573_c, tile.func_145838_q(), tile.func_145838_q().func_149738_a(this.field_145850_b));
        }
    }
}

