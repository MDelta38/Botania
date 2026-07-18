/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package vazkii.botania.common.block.subtile.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileFunctional;
import vazkii.botania.common.block.ModFluffBlocks;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.lexicon.LexiconData;

public class SubTileMarimorphosis
extends SubTileFunctional {
    private static final int COST = 12;
    private static final int RANGE = 8;
    private static final int RANGE_Y = 5;
    private static final int RANGE_MINI = 2;
    private static final int RANGE_Y_MINI = 1;
    private static final BiomeDictionary.Type[] TYPES = new BiomeDictionary.Type[]{BiomeDictionary.Type.FOREST, BiomeDictionary.Type.PLAINS, BiomeDictionary.Type.MOUNTAIN, BiomeDictionary.Type.MUSHROOM, BiomeDictionary.Type.SWAMP, BiomeDictionary.Type.SANDY, BiomeDictionary.Type.COLD, BiomeDictionary.Type.MESA};

    @Override
    public void onUpdate() {
        ItemStack stack;
        ChunkCoordinates coords;
        super.onUpdate();
        if (this.redstoneSignal > 0) {
            return;
        }
        if (!this.supertile.func_145831_w().field_72995_K && this.mana >= 12 && this.ticksExisted % 2 == 0 && (coords = this.getCoordsToPut()) != null && (stack = this.getStoneToPut(coords)) != null) {
            Block block = Block.func_149634_a((Item)stack.func_77973_b());
            int meta = stack.func_77960_j();
            this.supertile.func_145831_w().func_147465_d(coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, block, meta, 3);
            if (ConfigHandler.blockBreakParticles) {
                this.supertile.func_145831_w().func_72926_e(2001, coords.field_71574_a, coords.field_71572_b, coords.field_71573_c, Block.func_149682_b((Block)block) + (meta << 12));
            }
            this.mana -= 12;
            this.sync();
        }
    }

    public ItemStack getStoneToPut(ChunkCoordinates coords) {
        List<BiomeDictionary.Type> types = Arrays.asList(BiomeDictionary.getTypesForBiome((BiomeGenBase)this.supertile.func_145831_w().func_72807_a(coords.field_71574_a, coords.field_71573_c)));
        ArrayList<Integer> values = new ArrayList<Integer>();
        for (int i = 0; i < 8; ++i) {
            int times = 1;
            if (types.contains(TYPES[i])) {
                times = 12;
            }
            for (int j = 0; j < times; ++j) {
                values.add(i);
            }
        }
        return new ItemStack(ModFluffBlocks.biomeStoneA, 1, ((Integer)values.get(this.supertile.func_145831_w().field_73012_v.nextInt(values.size()))).intValue());
    }

    public ChunkCoordinates getCoordsToPut() {
        ArrayList<ChunkCoordinates> possibleCoords = new ArrayList<ChunkCoordinates>();
        int range = this.getRange();
        int rangeY = this.getRangeY();
        for (int i = -range; i < range + 1; ++i) {
            for (int j = -rangeY; j < rangeY; ++j) {
                for (int k = -range; k < range + 1; ++k) {
                    int x = this.supertile.field_145851_c + i;
                    int y = this.supertile.field_145848_d + j;
                    int z = this.supertile.field_145849_e + k;
                    Block block = this.supertile.func_145831_w().func_147439_a(x, y, z);
                    if (block == null || !block.isReplaceableOreGen(this.supertile.func_145831_w(), x, y, z, Blocks.field_150348_b)) continue;
                    possibleCoords.add(new ChunkCoordinates(x, y, z));
                }
            }
        }
        if (possibleCoords.isEmpty()) {
            return null;
        }
        return (ChunkCoordinates)possibleCoords.get(this.supertile.func_145831_w().field_73012_v.nextInt(possibleCoords.size()));
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(this.toChunkCoordinates(), this.getRange());
    }

    public int getRange() {
        return 8;
    }

    public int getRangeY() {
        return 5;
    }

    @Override
    public int getColor() {
        return 7772311;
    }

    @Override
    public boolean acceptsRedstone() {
        return true;
    }

    @Override
    public int getMaxMana() {
        return 1000;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.marimorphosis;
    }

    public static class Mini
    extends SubTileMarimorphosis {
        @Override
        public int getRange() {
            return 2;
        }

        @Override
        public int getRangeY() {
            return 1;
        }
    }
}

