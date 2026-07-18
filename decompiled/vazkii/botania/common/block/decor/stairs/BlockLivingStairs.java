/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 */
package vazkii.botania.common.block.decor.stairs;

import net.minecraft.block.Block;
import vazkii.botania.common.block.decor.stairs.BlockModStairs;

public class BlockLivingStairs
extends BlockModStairs {
    public BlockLivingStairs(Block source, int meta) {
        super(source, meta, source.func_149739_a().replaceAll("tile.", "") + meta + "Stairs");
    }
}

