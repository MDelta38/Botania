/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.block.Block
 *  net.minecraft.block.BlockFlower
 *  net.minecraft.entity.monster.EntityEnderman
 *  net.minecraft.entity.monster.EntityWitch
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.init.Blocks
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$Height
 *  net.minecraft.world.biome.BiomeGenBase$SpawnListEntry
 *  net.minecraft.world.gen.feature.WorldGenAbstractTree
 *  net.minecraft.world.gen.feature.WorldGenBigMushroom
 *  net.minecraft.world.gen.feature.WorldGenBlockBlob
 *  net.minecraft.world.gen.feature.WorldGenTallGrass
 *  net.minecraft.world.gen.feature.WorldGenerator
 */
package thaumcraft.common.lib.world.biomes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.monster.EntityPech;
import thaumcraft.common.entities.monster.EntityWisp;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.WorldGenBigMagicTree;
import thaumcraft.common.lib.world.WorldGenGreatwoodTrees;
import thaumcraft.common.lib.world.WorldGenManaPods;
import thaumcraft.common.lib.world.WorldGenSilverwoodTrees;

public class BiomeGenMagicalForest
extends BiomeGenBase {
    protected WorldGenBigMagicTree bigTree = new WorldGenBigMagicTree(false);
    private static final WorldGenBlockBlob blobs = new WorldGenBlockBlob(Blocks.field_150341_Y, 0);

    public BiomeGenMagicalForest(int par1) {
        super(par1);
        this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 2, 1, 3));
        this.field_76762_K.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 2, 1, 3));
        this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWitch.class, 3, 1, 1));
        this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityEnderman.class, 3, 1, 1));
        if (Config.spawnPech) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityPech.class, 10, 1, 2));
        }
        if (Config.spawnWisp) {
            this.field_76761_J.add(new BiomeGenBase.SpawnListEntry(EntityWisp.class, 10, 1, 2));
        }
        this.field_76760_I.field_76832_z = 2;
        this.field_76760_I.field_76802_A = 10;
        this.field_76760_I.field_76803_B = 12;
        this.field_76760_I.field_76833_y = 6;
        this.field_76760_I.field_76798_D = 6;
        this.func_76732_a(0.7f, 0.6f);
        this.func_150570_a(new BiomeGenBase.Height(0.2f, 0.2f));
        this.func_76735_a("Magical Forest");
        this.func_76739_b(Config.blueBiome ? 0x66AACC : 6747307);
        this.flowers.clear();
        for (int x = 0; x < BlockFlower.field_149859_a.length; ++x) {
            this.addFlower((Block)Blocks.field_150328_O, x, 10);
        }
    }

    public WorldGenAbstractTree func_150567_a(Random par1Random) {
        return par1Random.nextInt(14) == 0 ? new WorldGenSilverwoodTrees(false, 8, 5) : (par1Random.nextInt(10) == 0 ? new WorldGenGreatwoodTrees(false) : this.bigTree);
    }

    public WorldGenerator func_76730_b(Random par1Random) {
        return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass((Block)Blocks.field_150329_H, 2) : new WorldGenTallGrass((Block)Blocks.field_150329_H, 1);
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150558_b(int x, int y, int z) {
        return Config.blueBiome ? 0x66AACC : 5635969;
    }

    @SideOnly(value=Side.CLIENT)
    public int func_150571_c(int x, int y, int z) {
        return Config.blueBiome ? 0x77CCEE : 6750149;
    }

    public int getWaterColorMultiplier() {
        return 30702;
    }

    public void func_76728_a(World world, Random random, int x, int z) {
        int k1;
        int j1;
        int i1;
        int l;
        int k = random.nextInt(3);
        for (l = 0; l < k; ++l) {
            i1 = x + random.nextInt(16) + 8;
            j1 = z + random.nextInt(16) + 8;
            k1 = world.func_72976_f(i1, j1);
            blobs.func_76484_a(world, random, i1, k1, j1);
        }
        for (k = 0; k < 4; ++k) {
            for (l = 0; l < 4; ++l) {
                i1 = x + k * 4 + 1 + 8 + random.nextInt(3);
                j1 = z + l * 4 + 1 + 8 + random.nextInt(3);
                k1 = world.func_72976_f(i1, j1);
                if (random.nextInt(40) != 0) continue;
                WorldGenBigMushroom worldgenbigmushroom = new WorldGenBigMushroom();
                worldgenbigmushroom.func_76484_a(world, random, i1, k1, j1);
            }
        }
        super.func_76728_a(world, random, x, z);
        WorldGenManaPods worldgenpods = new WorldGenManaPods();
        for (k = 0; k < 10; ++k) {
            l = x + random.nextInt(16) + 8;
            int b0 = 64;
            i1 = z + random.nextInt(16) + 8;
            worldgenpods.func_76484_a(world, random, l, b0, i1);
        }
        for (int a = 0; a < 8; ++a) {
            int yy;
            int xx = x + random.nextInt(16);
            int zz = z + random.nextInt(16);
            for (yy = world.func_72976_f(xx, zz); yy > 50 && world.func_147439_a(xx, yy, zz) != Blocks.field_150349_c; --yy) {
            }
            Block l1 = world.func_147439_a(xx, yy, zz);
            if (l1 != Blocks.field_150349_c || !world.func_147439_a(xx, yy + 1, zz).isReplaceable((IBlockAccess)world, xx, yy + 1, zz) || !this.isBlockAdjacentToWood((IBlockAccess)world, xx, yy + 1, zz)) continue;
            world.func_147465_d(xx, yy + 1, zz, ConfigBlocks.blockCustomPlant, 5, 2);
        }
    }

    private boolean isBlockAdjacentToWood(IBlockAccess world, int x, int y, int z) {
        boolean count = false;
        for (int xx = -1; xx <= 1; ++xx) {
            for (int yy = -1; yy <= 1; ++yy) {
                for (int zz = -1; zz <= 1; ++zz) {
                    if (xx == 0 && yy == 0 && zz == 0 || !Utils.isWoodLog(world, xx + x, yy + y, zz + z)) continue;
                    return true;
                }
            }
        }
        return false;
    }

    public BiomeGenBase func_150566_k() {
        return null;
    }
}

