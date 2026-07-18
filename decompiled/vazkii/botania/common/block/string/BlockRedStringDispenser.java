/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.string;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import vazkii.botania.common.block.string.BlockRedString;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.block.tile.string.TileRedStringDispenser;

public class BlockRedStringDispenser
extends BlockRedString {
    public BlockRedStringDispenser() {
        super("redStringDispenser");
    }

    public void func_149695_a(World world, int x, int y, int z, Block block) {
        boolean powered;
        boolean power = world.func_72864_z(x, y, z) || world.func_72864_z(x, y + 1, z);
        int meta = world.func_72805_g(x, y, z);
        boolean bl = powered = (meta & 8) != 0;
        if (power && !powered) {
            ((TileRedStringDispenser)world.func_147438_o(x, y, z)).tickDispenser();
            world.func_72921_c(x, y, z, meta | 8, 4);
        } else if (!power && powered) {
            world.func_72921_c(x, y, z, meta & 0xFFFFFFF7, 4);
        }
    }

    public TileRedString createNewTileEntity(World world, int meta) {
        return new TileRedStringDispenser();
    }
}

