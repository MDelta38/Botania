/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  net.minecraft.world.ChunkCoordIntPair
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenFlowers
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.Config;
import com.emoniph.witchery.worldgen.BiomeManager;
import com.emoniph.witchery.worldgen.ComponentGoblinHut;
import com.emoniph.witchery.worldgen.IWorldGenHandler;
import com.emoniph.witchery.worldgen.WorldHandlerClonedStructure;
import com.emoniph.witchery.worldgen.WorldHandlerCoven;
import com.emoniph.witchery.worldgen.WorldHandlerShack;
import com.emoniph.witchery.worldgen.WorldHandlerWickerMan;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraftforge.common.BiomeDictionary;

public class WitcheryWorldGenerator
implements IWorldGenerator {
    private LinkedList<ChunkCoordIntPair> structuresList = new LinkedList();
    private final WorldHandlerCoven covenGen = new WorldHandlerCoven(1.0, 400);
    private final WorldHandlerWickerMan wickerManGen = new WorldHandlerWickerMan(1.0, 400);
    private final WorldHandlerShack shackGen = new WorldHandlerShack(1.0, 400);
    private final List<IWorldGenHandler> generators;
    private int midX;
    private int midZ;
    int field_82665_g;
    int field_82666_h = 8;

    public WitcheryWorldGenerator() {
        WorldHandlerClonedStructure goblinHut = new WorldHandlerClonedStructure(ComponentGoblinHut.class, 1.0, 400, 7, 7, 7);
        this.generators = Arrays.asList(this.wickerManGen, this.covenGen, this.shackGen, goblinHut);
        this.field_82665_g = 8 + Math.max(Math.min(Config.instance().worldGenFrequency, 64), 1);
        this.midX = 0;
        this.midZ = 0;
        for (IWorldGenHandler gen : this.generators) {
            this.midX = Math.max(this.midX, gen.getExtentX() / 2);
            this.midZ = Math.max(this.midZ, gen.getExtentZ() / 2);
        }
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        if (world.field_73011_w.field_76574_g == 0) {
            this.generateOverworld(world, world.field_73012_v, chunkX * 16, chunkZ * 16);
        } else if (Config.instance().worldGenTwilightForest && world.field_73011_w.func_80007_l().equals("Twilight Forest")) {
            this.generateOverworld(world, world.field_73012_v, chunkX * 16, chunkZ * 16);
        } else if (world.field_73011_w.field_76574_g == Config.instance().dimensionDreamID) {
            this.generateDreamworld(world, world.field_73012_v, chunkX * 16, chunkZ * 16);
        }
    }

    private void generateDreamworld(World world, Random random, int chunkX, int chunkZ) {
        boolean flowerPerChunk = true;
        BiomeGenBase biome = world.func_72807_a(chunkX, chunkZ);
        if ((!BiomeManager.DISALLOWED_BIOMES.contains(biome.field_76756_M) || BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)BiomeDictionary.Type.JUNGLE)) && random.nextInt(4) != 0) {
            for (int j = 0; j < 1; ++j) {
                int k = chunkX + random.nextInt(16) + 8;
                int i1 = chunkZ + random.nextInt(16) + 8;
                int l = random.nextInt(world.func_72976_f(k, i1) + 32);
                new WorldGenFlowers(Witchery.Blocks.WISPY_COTTON).func_76484_a(world, random, k, l, i1);
            }
        }
    }

    private void generateOverworld(World world, Random random, int x, int z) {
        boolean gen = false;
        if (!BiomeManager.DISALLOWED_BIOMES.contains(world.func_72807_a((int)(x + this.midX), (int)(z + this.midZ)).field_76756_M)) {
            Collections.shuffle(this.generators, random);
            for (IWorldGenHandler generator : this.generators) {
                if (!this.nonInRange(world, x, z, generator.getRange()) || !generator.generate(world, random, x, z)) continue;
                this.structuresList.add(new ChunkCoordIntPair(x, z));
                gen = true;
                break;
            }
        }
    }

    protected boolean nonInRange(World worldObj, int x, int z, int range) {
        int par1 = x / 16;
        int par2 = z / 16;
        int k = par1;
        int l = par2;
        if (par1 < 0) {
            par1 -= this.field_82665_g - 1;
        }
        if (par2 < 0) {
            par2 -= this.field_82665_g - 1;
        }
        int i1 = par1 / this.field_82665_g;
        int j1 = par2 / this.field_82665_g;
        Random random = worldObj.func_72843_D(i1, j1, 10387312);
        i1 *= this.field_82665_g;
        j1 *= this.field_82665_g;
        return k == (i1 += random.nextInt(this.field_82665_g - this.field_82666_h)) && l == (j1 += random.nextInt(this.field_82665_g - this.field_82666_h));
    }

    public void initiate() {
        this.structuresList.clear();
    }
}

