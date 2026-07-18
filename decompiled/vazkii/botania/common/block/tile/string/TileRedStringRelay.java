/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockDoublePlant
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.block.BlockMushroom
 *  net.minecraft.tileentity.TileEntity
 */
package vazkii.botania.common.block.tile.string;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockMushroom;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.common.block.tile.string.TileRedString;

public class TileRedStringRelay
extends TileRedString {
    @Override
    public boolean acceptBlock(int x, int y, int z) {
        if (x == this.field_145851_c && y == this.field_145848_d + 1 && z == this.field_145849_e) {
            return false;
        }
        Block block = this.field_145850_b.func_147439_a(x, y, z);
        TileEntity tile = this.field_145850_b.func_147438_o(x, y, z);
        return !(!(block instanceof BlockFlower) && !(block instanceof BlockMushroom) && !(block instanceof BlockDoublePlant) || tile != null && tile instanceof ISubTileContainer);
    }
}

