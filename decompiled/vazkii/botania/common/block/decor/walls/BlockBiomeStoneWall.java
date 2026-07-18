/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 */
package vazkii.botania.common.block.decor.walls;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.block.decor.walls.BlockVariantWall;
import vazkii.botania.common.lexicon.LexiconData;

public class BlockBiomeStoneWall
extends BlockVariantWall {
    public BlockBiomeStoneWall() {
        super(ModFluffBlocks.biomeStoneA, 8, 8);
        this.func_149711_c(1.5f);
        this.func_149752_b(10.0f);
    }

    @Override
    public LexiconEntry getEntry(World world, int x, int y, int z, EntityPlayer player, ItemStack lexicon) {
        return LexiconData.marimorphosis;
    }
}

