/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.util.ForgeDirection
 */
package thaumcraft.common.lib.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.world.dim.MazeHandler;
import thaumcraft.common.tiles.TileBanner;
import thaumcraft.common.tiles.TileEldritchAltar;

public class WorldGenEldritchRing
extends WorldGenerator {
    public int chunkX;
    public int chunkZ;
    public int width;
    public int height = 0;

    protected Block[] GetValidSpawnBlocks() {
        return new Block[]{Blocks.field_150348_b, Blocks.field_150354_m, Blocks.field_150403_cj, Blocks.field_150349_c, Blocks.field_150351_n, Blocks.field_150346_d};
    }

    public boolean LocationIsValidSpawn(World world, int i, int j, int k) {
        int distanceToAir = 0;
        Block checkID = world.func_147439_a(i, j, k);
        while (checkID != Blocks.field_150350_a) {
            checkID = world.func_147439_a(i, j + ++distanceToAir, k);
        }
        if (distanceToAir > 2) {
            return false;
        }
        Block blockID = world.func_147439_a(i, j += distanceToAir - 1, k);
        Block blockIDAbove = world.func_147439_a(i, j + 1, k);
        Block blockIDBelow = world.func_147439_a(i, j - 1, k);
        for (Block x : this.GetValidSpawnBlocks()) {
            if (blockIDAbove != Blocks.field_150350_a) {
                return false;
            }
            if (blockID == x) {
                return true;
            }
            if (blockID != Blocks.field_150431_aC && blockID != Blocks.field_150329_H || blockIDBelow != x) continue;
            return true;
        }
        return false;
    }

    public boolean func_76484_a(World world, Random rand, int i, int j, int k) {
        if (!(this.LocationIsValidSpawn(world, i - 3, j, k - 3) && this.LocationIsValidSpawn(world, i, j, k) && this.LocationIsValidSpawn(world, i + 3, j, k) && this.LocationIsValidSpawn(world, i + 3, j, k + 3) && this.LocationIsValidSpawn(world, i, j, k + 3) && !MazeHandler.mazesInRange(this.chunkX, this.chunkZ, this.width, this.height))) {
            return false;
        }
        Block replaceBlock = world.func_72807_a((int)i, (int)k).field_76752_A;
        for (int x = i - 3; x <= i + 3; ++x) {
            for (int z = k - 3; z <= k + 3; ++z) {
                if (!(x != i - 3 && x != i + 3 || z != k - 3 && z != k + 3)) continue;
                for (int q = -4; q < 5; ++q) {
                    Block bb = world.func_147439_a(x, j + q, z);
                    if (q <= 0 || bb.isReplaceable((IBlockAccess)world, x, j + q, z) || !bb.func_149688_o().func_76230_c() || bb.isFoliage((IBlockAccess)world, x, j + q, z)) {
                        if (rand.nextInt(4) == 0) {
                            world.func_147449_b(x, j + q, z, Blocks.field_150343_Z);
                        } else {
                            world.func_147465_d(x, j + q, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
                        }
                    }
                    if (q <= 0) continue;
                    world.func_147468_f(x, j + q, z);
                }
                if (x == i && z == k) {
                    world.func_147465_d(x, j + 1, z, ConfigBlocks.blockEldritch, 0, 3);
                    world.func_147465_d(x, j, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
                    int r = rand.nextInt(10);
                    TileEntity te = world.func_147438_o(x, j + 1, z);
                    if (te != null && te instanceof TileEldritchAltar) {
                        switch (r) {
                            case 1: 
                            case 2: 
                            case 3: 
                            case 4: {
                                ((TileEldritchAltar)te).setSpawner(true);
                                ((TileEldritchAltar)te).setSpawnType((byte)0);
                                for (int a = 2; a < 6; ++a) {
                                    ForgeDirection dir = ForgeDirection.getOrientation((int)a);
                                    world.func_147465_d(x - dir.offsetX * 3, j + 1, z + dir.offsetZ * 3, ConfigBlocks.blockWoodenDevice, 8, 3);
                                    te = world.func_147438_o(x - dir.offsetX * 3, j + 1, z + dir.offsetZ * 3);
                                    if (te == null || !(te instanceof TileBanner)) continue;
                                    int face = 0;
                                    switch (a) {
                                        case 2: {
                                            face = 8;
                                            break;
                                        }
                                        case 3: {
                                            face = 0;
                                            break;
                                        }
                                        case 4: {
                                            face = 12;
                                            break;
                                        }
                                        case 5: {
                                            face = 4;
                                            break;
                                        }
                                    }
                                    ((TileBanner)te).setFacing((byte)face);
                                }
                                break;
                            }
                            case 6: 
                            case 7: {
                                ((TileEldritchAltar)te).setSpawner(true);
                                ((TileEldritchAltar)te).setSpawnType((byte)1);
                            }
                        }
                    }
                    world.func_147465_d(x, j + 3, z, ConfigBlocks.blockEldritch, 1, 3);
                    world.func_147465_d(x, j + 4, z, ConfigBlocks.blockEldritch, 2, 3);
                    world.func_147465_d(x, j + 5, z, ConfigBlocks.blockEldritch, 2, 3);
                    world.func_147465_d(x, j + 6, z, ConfigBlocks.blockEldritch, 2, 3);
                    world.func_147465_d(x, j + 7, z, ConfigBlocks.blockEldritch, 2, 3);
                    continue;
                }
                if ((x != i - 3 && x != i + 3 || Math.abs((z - k) % 2) != 1) && (z != k - 3 && z != k + 3 || Math.abs((x - i) % 2) != 1) || Math.abs(x - i) == Math.abs(z - k)) continue;
                world.func_147465_d(x, j, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
                world.func_147465_d(x, j + 1, z, ConfigBlocks.blockEldritch, 3, 3);
            }
        }
        return true;
    }
}

