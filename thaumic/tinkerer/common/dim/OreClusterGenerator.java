/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenMinable
 */
package thaumic.tinkerer.common.dim;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import thaumic.tinkerer.common.dim.EnumOreFrequency;
import thaumic.tinkerer.common.dim.WorldProviderBedrock;

public class OreClusterGenerator
implements IWorldGenerator {
    public static int density;
    public static String[] blacklist;

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.field_73011_w instanceof WorldProviderBedrock) {
            for (int k = 0; k < density; ++k) {
                int firstBlockXCoord = 16 * chunkX + random.nextInt(16);
                int firstBlockZCoord = 16 * chunkZ + random.nextInt(16);
                ItemStack itemStack = EnumOreFrequency.getRandomOre(random);
                for (int l = 0; l < 200; ++l) {
                    int firstBlockYCoord = random.nextInt(245) + 6;
                    WorldGenMinable mineable = new WorldGenMinable(Block.func_149634_a((Item)itemStack.func_77973_b()), itemStack.func_77960_j(), random.nextInt(20), Blocks.field_150357_h);
                    mineable.func_76484_a(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
                }
            }
        }
    }

    static {
        blacklist = new String[]{"oreFirestone"};
    }
}

