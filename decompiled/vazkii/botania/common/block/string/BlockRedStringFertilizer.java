/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.IGrowable
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.string;

import java.util.Random;
import net.minecraft.block.IGrowable;
import net.minecraft.world.World;
import vazkii.botania.common.block.string.BlockRedString;
import vazkii.botania.common.block.tile.string.TileRedString;
import vazkii.botania.common.block.tile.string.TileRedStringFertilizer;

public class BlockRedStringFertilizer
extends BlockRedString
implements IGrowable {
    public BlockRedStringFertilizer() {
        super("redStringFertilizer");
    }

    public boolean func_149851_a(World world, int x, int y, int z, boolean something) {
        return ((TileRedStringFertilizer)world.func_147438_o(x, y, z)).func_149851_a(world, something);
    }

    public boolean func_149852_a(World world, Random rand, int x, int y, int z) {
        return ((TileRedStringFertilizer)world.func_147438_o(x, y, z)).func_149852_a(world, rand);
    }

    public void func_149853_b(World world, Random rand, int x, int y, int z) {
        ((TileRedStringFertilizer)world.func_147438_o(x, y, z)).func_149853_b(world, rand);
    }

    public TileRedString createNewTileEntity(World world, int meta) {
        return new TileRedStringFertilizer();
    }
}

