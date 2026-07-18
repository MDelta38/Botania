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
import vazkii.botania.common.block.tile.string.TileRedStringRelay;

public class BlockRedStringRelay
extends BlockRedString {
    public BlockRedStringRelay() {
        super("redStringRelay");
    }

    public TileRedString createNewTileEntity(World world, int meta) {
        return new TileRedStringRelay();
    }
}

