/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockSapling
 *  net.minecraft.init.Blocks
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraftforge.common.IPlantable
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.lib.world.WorldGenCustomFlowers;

public class WorldGenSilverwoodTrees
extends WorldGenAbstractTree {
    private final int minTreeHeight;
    private final int randomTreeHeight;
    boolean worldgen = false;

    public WorldGenSilverwoodTrees(boolean doBlockNotify, int minTreeHeight, int randomTreeHeight) {
        super(doBlockNotify);
        this.worldgen = !doBlockNotify;
        this.minTreeHeight = minTreeHeight;
        this.randomTreeHeight = randomTreeHeight;
    }

    public boolean func_76484_a(World world, Random random, int x, int y, int z) {
        int height = random.nextInt(this.randomTreeHeight) + this.minTreeHeight;
        boolean flag = true;
        if (y >= 1 && y + height + 1 <= 256) {
            for (int i1 = y; i1 <= y + 1 + height; ++i1) {
                int spread = 1;
                if (i1 == y) {
                    spread = 0;
                }
                if (i1 >= y + 1 + height - 2) {
                    spread = 3;
                }
                for (int j1 = x - spread; j1 <= x + spread && flag; ++j1) {
                    for (int k1 = z - spread; k1 <= z + spread && flag; ++k1) {
                        if (i1 >= 0 && i1 < 256) {
                            Block block = world.func_147439_a(j1, i1, k1);
                            if (block.isAir((IBlockAccess)world, j1, i1, k1) || block.isLeaves((IBlockAccess)world, j1, i1, k1) || block.isReplaceable((IBlockAccess)world, j1, i1, k1) || i1 <= y) continue;
                            flag = false;
                            continue;
                        }
                        flag = false;
                    }
                }
            }
            if (!flag) {
                return false;
            }
            Block block1 = world.func_147439_a(x, y - 1, z);
            boolean isSoil = block1.canSustainPlant((IBlockAccess)world, x, y - 1, z, ForgeDirection.UP, (IPlantable)((BlockSapling)Blocks.field_150345_g));
            if (isSoil && y < 256 - height - 1) {
                int k2;
                block1.onPlantGrow(world, x, y - 1, z, x, y, z);
                int start = y + height - 5;
                int end = y + height + 3 + random.nextInt(3);
                for (k2 = start; k2 <= end; ++k2) {
                    int cty = MathHelper.func_76125_a((int)k2, (int)(y + height - 3), (int)(y + height));
                    for (int xx = x - 5; xx <= x + 5; ++xx) {
                        for (int zz = z - 5; zz <= z + 5; ++zz) {
                            double d3 = xx - x;
                            double d4 = k2 - cty;
                            double d5 = zz - z;
                            double dist = d3 * d3 + d4 * d4 + d5 * d5;
                            if (!(dist < (double)(10 + random.nextInt(8))) || !world.func_147439_a(xx, k2, zz).canBeReplacedByLeaves((IBlockAccess)world, xx, k2, zz)) continue;
                            this.func_150516_a(world, xx, k2, zz, ConfigBlocks.blockMagicalLeaves, 1);
                        }
                    }
                }
                int chance = (int)((double)height * 1.5);
                boolean lastblock = false;
                for (k2 = 0; k2 < height; ++k2) {
                    Block block2 = world.func_147439_a(x, y + k2, z);
                    if (!block2.isAir((IBlockAccess)world, x, y + k2, z) && !block2.isLeaves((IBlockAccess)world, x, y + k2, z) && !block2.isReplaceable((IBlockAccess)world, x, y + k2, z)) continue;
                    if (k2 > 0 && !lastblock && random.nextInt(chance) == 0) {
                        this.func_150516_a(world, x, y + k2, z, ConfigBlocks.blockMagicalLog, 2);
                        ThaumcraftWorldGenerator.createRandomNodeAt(world, x, y + k2, z, random, true, false, false);
                        chance += height;
                        lastblock = true;
                    } else {
                        this.func_150516_a(world, x, y + k2, z, ConfigBlocks.blockMagicalLog, 1);
                        lastblock = false;
                    }
                    this.func_150516_a(world, x - 1, y + k2, z, ConfigBlocks.blockMagicalLog, 1);
                    this.func_150516_a(world, x + 1, y + k2, z, ConfigBlocks.blockMagicalLog, 1);
                    this.func_150516_a(world, x, y + k2, z - 1, ConfigBlocks.blockMagicalLog, 1);
                    this.func_150516_a(world, x, y + k2, z + 1, ConfigBlocks.blockMagicalLog, 1);
                }
                this.func_150516_a(world, x, y + k2, z, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x - 1, y, z - 1, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x + 1, y, z + 1, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x - 1, y, z + 1, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x + 1, y, z - 1, ConfigBlocks.blockMagicalLog, 1);
                if (random.nextInt(3) != 0) {
                    this.func_150516_a(world, x - 1, y + 1, z - 1, ConfigBlocks.blockMagicalLog, 1);
                }
                if (random.nextInt(3) != 0) {
                    this.func_150516_a(world, x + 1, y + 1, z + 1, ConfigBlocks.blockMagicalLog, 1);
                }
                if (random.nextInt(3) != 0) {
                    this.func_150516_a(world, x - 1, y + 1, z + 1, ConfigBlocks.blockMagicalLog, 1);
                }
                if (random.nextInt(3) != 0) {
                    this.func_150516_a(world, x + 1, y + 1, z - 1, ConfigBlocks.blockMagicalLog, 1);
                }
                this.func_150516_a(world, x - 2, y, z, ConfigBlocks.blockMagicalLog, 5);
                this.func_150516_a(world, x + 2, y, z, ConfigBlocks.blockMagicalLog, 5);
                this.func_150516_a(world, x, y, z - 2, ConfigBlocks.blockMagicalLog, 9);
                this.func_150516_a(world, x, y, z + 2, ConfigBlocks.blockMagicalLog, 9);
                this.func_150516_a(world, x - 2, y - 1, z, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x + 2, y - 1, z, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x, y - 1, z - 2, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x, y - 1, z + 2, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x - 1, y + (height - 4), z - 1, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x + 1, y + (height - 4), z + 1, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x - 1, y + (height - 4), z + 1, ConfigBlocks.blockMagicalLog, 1);
                this.func_150516_a(world, x + 1, y + (height - 4), z - 1, ConfigBlocks.blockMagicalLog, 1);
                if (random.nextInt(3) == 0) {
                    this.func_150516_a(world, x - 1, y + (height - 5), z - 1, ConfigBlocks.blockMagicalLog, 1);
                }
                if (random.nextInt(3) == 0) {
                    this.func_150516_a(world, x + 1, y + (height - 5), z + 1, ConfigBlocks.blockMagicalLog, 1);
                }
                if (random.nextInt(3) == 0) {
                    this.func_150516_a(world, x - 1, y + (height - 5), z + 1, ConfigBlocks.blockMagicalLog, 1);
                }
                if (random.nextInt(3) == 0) {
                    this.func_150516_a(world, x + 1, y + (height - 5), z - 1, ConfigBlocks.blockMagicalLog, 1);
                }
                this.func_150516_a(world, x - 2, y + (height - 4), z, ConfigBlocks.blockMagicalLog, 5);
                this.func_150516_a(world, x + 2, y + (height - 4), z, ConfigBlocks.blockMagicalLog, 5);
                this.func_150516_a(world, x, y + (height - 4), z - 2, ConfigBlocks.blockMagicalLog, 9);
                this.func_150516_a(world, x, y + (height - 4), z + 2, ConfigBlocks.blockMagicalLog, 9);
                if (this.worldgen) {
                    WorldGenCustomFlowers flowers = new WorldGenCustomFlowers(ConfigBlocks.blockCustomPlant, 2);
                    flowers.func_76484_a(world, random, x, y, z);
                }
                return true;
            }
            return false;
        }
        return false;
    }
}

