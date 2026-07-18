/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.World
 */
package vazkii.botania.api.lexicon.multiblock.component;

import net.minecraft.block.Block;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.World;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

public class ColorSwitchingComponent
extends MultiblockComponent {
    public ColorSwitchingComponent(ChunkCoordinates relPos, Block block) {
        super(relPos, block, -1);
    }

    @Override
    public int getMeta() {
        return (int)(BotaniaAPI.internalHandler.getWorldElapsedTicks() / 20L) % 16;
    }

    @Override
    public boolean matches(World world, int x, int y, int z) {
        return world.func_147439_a(x, y, z) == this.getBlock();
    }

    @Override
    public MultiblockComponent copy() {
        return new ColorSwitchingComponent(this.relPos, this.block);
    }
}

