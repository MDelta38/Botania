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
import vazkii.botania.api.lexicon.multiblock.component.ColorSwitchingComponent;
import vazkii.botania.api.lexicon.multiblock.component.MultiblockComponent;

public class FlowerComponent
extends ColorSwitchingComponent {
    public FlowerComponent(ChunkCoordinates relPos, Block block) {
        super(relPos, block);
    }

    @Override
    public boolean matches(World world, int x, int y, int z) {
        return BotaniaAPI.internalHandler.isBotaniaFlower(world, x, y, z);
    }

    @Override
    public MultiblockComponent copy() {
        return new FlowerComponent(this.relPos, this.block);
    }
}

