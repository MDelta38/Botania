/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.init.Blocks
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.tileentity.TileEntityChest
 *  net.minecraft.tileentity.TileEntityMobSpawner
 *  net.minecraft.util.WeightedRandomChestContent
 *  net.minecraft.world.World
 *  net.minecraft.world.gen.feature.WorldGenerator
 *  net.minecraftforge.common.ChestGenHooks
 */
package thaumcraft.common.lib.world;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.ChestGenHooks;
import thaumcraft.common.config.ConfigBlocks;

public class WorldGenHilltopStones
extends WorldGenerator {
    protected Block[] GetValidSpawnBlocks() {
        return new Block[]{Blocks.field_150348_b, Blocks.field_150349_c, Blocks.field_150346_d};
    }

    public boolean LocationIsValidSpawn(World world, int i, int j, int k) {
        if (j < 85) {
            return false;
        }
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
        if (!(this.LocationIsValidSpawn(world, i - 2, j, k - 2) && this.LocationIsValidSpawn(world, i, j, k) && this.LocationIsValidSpawn(world, i + 2, j, k) && this.LocationIsValidSpawn(world, i + 2, j, k + 2) && this.LocationIsValidSpawn(world, i, j, k + 2))) {
            return false;
        }
        Block replaceBlock = world.func_72807_a((int)i, (int)k).field_76752_A;
        boolean genVines = !world.func_72807_a(i, k).func_76746_c();
        for (int x = i - 3; x <= i + 3; ++x) {
            for (int z = k - 3; z <= k + 3; ++z) {
                if ((x == i - 3 || x == i + 3) && (z == k - 3 || z == k + 3)) continue;
                if (rand.nextBoolean()) {
                    world.func_147465_d(x, j, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
                } else {
                    world.func_147465_d(x, j, z, Blocks.field_150343_Z, 0, 3);
                }
                boolean stop = false;
                for (int y = 1; y < 5; ++y) {
                    if (j - y < 0) continue;
                    Block blockID = world.func_147439_a(x, j - y, z);
                    if (replaceBlock != null && blockID == Blocks.field_150431_aC || blockID == Blocks.field_150328_O || blockID == Blocks.field_150327_N || blockID == Blocks.field_150329_H || blockID == Blocks.field_150350_a) {
                        world.func_147465_d(x, j - y, z, replaceBlock, 0, 3);
                    }
                    if (x == i && z == k && y == 1) {
                        world.func_147465_d(x, j + y, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
                        ChestGenHooks info1 = ChestGenHooks.getInfo((String)"dungeonChest");
                        ChestGenHooks info2 = ChestGenHooks.getInfo((String)"dungeonChest");
                        world.func_147465_d(x, j + y + 1, z, (Block)Blocks.field_150486_ae, 0, 3);
                        TileEntityChest chest = (TileEntityChest)world.func_147438_o(x, j + y + 1, z);
                        if (chest != null) {
                            WeightedRandomChestContent.func_76293_a((Random)rand, (WeightedRandomChestContent[])info1.getItems(rand), (IInventory)chest, (int)info1.getCount(rand));
                            WeightedRandomChestContent.func_76293_a((Random)rand, (WeightedRandomChestContent[])info2.getItems(rand), (IInventory)chest, (int)info2.getCount(rand));
                        }
                        world.func_147465_d(x, j + y - 1, z, Blocks.field_150474_ac, 0, 3);
                        TileEntityMobSpawner var12 = (TileEntityMobSpawner)world.func_147438_o(x, j + y - 1, z);
                        if (var12 != null) {
                            var12.func_145881_a().func_98272_a("Thaumcraft.Wisp");
                        }
                    }
                    if (stop || (x != i - 3 && x != i + 3 || Math.abs((z - k) % 2) != 1) && (z != k - 3 && z != k + 3 || Math.abs((x - i) % 2) != 1)) continue;
                    world.func_147465_d(x, j + y, z, ConfigBlocks.blockCosmeticSolid, 0, 3);
                    if (y < 2 || !rand.nextBoolean()) continue;
                    stop = true;
                    if (!genVines) continue;
                    if (rand.nextInt(3) == 0 && world.func_147437_c(x - 1, j + y, z)) {
                        this.growVines(world, x - 1, j + y, z, 8);
                    }
                    if (rand.nextInt(3) == 0 && world.func_147437_c(x + 1, j + y, z)) {
                        this.growVines(world, x + 1, j + y, z, 2);
                    }
                    if (rand.nextInt(3) == 0 && world.func_147437_c(x, j + y, z - 1)) {
                        this.growVines(world, x, j + y, z - 1, 1);
                    }
                    if (rand.nextInt(3) != 0 || !world.func_147437_c(x, j + y, z + 1)) continue;
                    this.growVines(world, x, j + y, z + 1, 4);
                }
            }
        }
        return true;
    }

    private void growVines(World par1World, int par2, int par3, int par4, int par5) {
        this.func_150516_a(par1World, par2, par3, par4, Blocks.field_150395_bd, par5);
        int var6 = 4;
        while (par1World.func_147437_c(par2, --par3, par4) && var6 > 0) {
            this.func_150516_a(par1World, par2, par3, par4, Blocks.field_150395_bd, par5);
            --var6;
        }
        return;
    }
}

