/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package vazkii.botania.api.lexicon.multiblock.component;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

public class AnyComponent
extends MultiblockComponent {
    public AnyComponent(ChunkCoordinates relPos, Block block, int meta) {
        super(relPos, block, meta);
    }

    @Override
    public boolean matches(World world, int x, int y, int z) {
        Block block = world.func_147439_a(x, y, z);
        return !block.isAir((IBlockAccess)world, x, y, z) && block.func_149668_a(world, x, y, z) != null;
    }
}

