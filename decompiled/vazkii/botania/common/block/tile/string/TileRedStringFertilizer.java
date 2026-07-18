/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.IGrowable
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.tile.string;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.common.block.tile.string.TileRedString;

public class TileRedStringFertilizer
extends TileRedString {
    public boolean func_149851_a(World p_149851_1_, boolean p_149851_5_) {
        ChunkCoordinates binding = this.getBinding();
        Block block = this.getBlockAtBinding();
        return block instanceof IGrowable ? ((IGrowable)block).func_149851_a(p_149851_1_, binding.field_71574_a, binding.field_71572_b, binding.field_71573_c, p_149851_5_) : false;
    }

    public boolean func_149852_a(World p_149852_1_, Random p_149852_2_) {
        ChunkCoordinates binding = this.getBinding();
        Block block = this.getBlockAtBinding();
        return block instanceof IGrowable ? ((IGrowable)block).func_149852_a(p_149852_1_, p_149852_2_, binding.field_71574_a, binding.field_71572_b, binding.field_71573_c) : false;
    }

    public void func_149853_b(World p_149853_1_, Random p_149853_2_) {
        ChunkCoordinates binding = this.getBinding();
        Block block = this.getBlockAtBinding();
        if (block instanceof IGrowable) {
            ((IGrowable)block).func_149853_b(p_149853_1_, p_149853_2_, binding.field_71574_a, binding.field_71572_b, binding.field_71573_c);
        }
    }

    @Override
    public boolean acceptBlock(int x, int y, int z) {
        return this.field_145850_b.func_147439_a(x, y, z) instanceof IGrowable;
    }
}

