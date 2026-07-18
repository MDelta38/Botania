/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.string;

import net.minecraft.world.World;
import vazkii.botania.common.block.string.BlockRedString;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.block.tile.string.TileRedStringComparator;

public class BlockRedStringComparator
extends BlockRedString {
    public BlockRedStringComparator() {
        super("redStringComparator");
    }

    public boolean func_149740_M() {
        return true;
    }

    public int func_149736_g(World world, int x, int y, int z, int side) {
        return ((TileRedStringComparator)world.func_147438_o(x, y, z)).getComparatorValue();
    }

    public TileRedString createNewTileEntity(World world, int meta) {
        return new TileRedStringComparator();
    }
}

