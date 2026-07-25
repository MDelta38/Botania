/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.IWorldGenerator
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.init.Blocks
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.ChunkPosition
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.feature.WorldGenBlockBlob
 *  net.minecraft.world.gen.feature.WorldGenMinable
 *  net.minecraft.world.gen.structure.MapGenScatteredFeature
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 *  net.minecraftforge.common.BiomeManager
 *  net.minecraftforge.common.BiomeManager$BiomeEntry
 *  net.minecraftforge.common.BiomeManager$BiomeType
 */
package thaumcraft.common.lib.world;

import cpw.mods.fml.common.IWorldGenerator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.structure.MapGenScatteredFeature;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.WorldGenCustomFlowers;
import thaumcraft.common.lib.world.WorldGenEldritchRing;
import thaumcraft.common.lib.world.WorldGenGreatwoodTrees;
import thaumcraft.common.lib.world.WorldGenHilltopStones;
import thaumcraft.common.lib.world.WorldGenMound;
import thaumcraft.common.lib.world.WorldGenSilverwoodTrees;
import thaumcraft.common.lib.world.biomes.BiomeGenTaint;
import thaumcraft.common.lib.world.biomes.BiomeHandler;
import thaumcraft.common.lib.world.dim.MazeHandler;
import thaumcraft.common.lib.world.dim.MazeThread;
import thaumcraft.common.tiles.TileNode;

public class ThaumcraftWorldGenerator
implements IWorldGenerator {
    public static BiomeGenBase biomeTaint;
    public static BiomeGenBase biomeEerie;
    public static BiomeGenBase biomeMagicalForest;
    public static BiomeGenBase biomeEldritchLands;
    static Collection<Aspect> c;
    static ArrayList<Aspect> basicAspects;
    static ArrayList<Aspect> complexAspects;
    public static HashMap<Integer, Integer> dimensionBlacklist;
    public static HashMap<Integer, Integer> biomeBlacklist;
    HashMap<Integer, Boolean> structureNode = new HashMap();

    public static int getFirstFreeBiomeSlot(int old) {
        for (int a = 0; a < BiomeGenBase.func_150565_n().length; ++a) {
            if (BiomeGenBase.func_150565_n()[a] != null) continue;
            Thaumcraft.log.warn("Biome slot " + old + " already occupied. Using first free biome slot at " + a);
            return a;
        }
        return -1;
    }

    public void initialize() {
        BiomeGenTaint.blobs = new WorldGenBlockBlob(ConfigBlocks.blockTaint, 0);
        BiomeManager.addBiome((BiomeManager.BiomeType)BiomeManager.BiomeType.WARM, (BiomeManager.BiomeEntry)new BiomeManager.BiomeEntry(biomeMagicalForest, Config.biomeMagicalForestWeight));
        BiomeManager.addBiome((BiomeManager.BiomeType)BiomeManager.BiomeType.COOL, (BiomeManager.BiomeEntry)new BiomeManager.BiomeEntry(biomeMagicalForest, Config.biomeMagicalForestWeight));
        BiomeManager.addBiome((BiomeManager.BiomeType)BiomeManager.BiomeType.WARM, (BiomeManager.BiomeEntry)new BiomeManager.BiomeEntry(biomeTaint, Config.biomeTaintWeight));
        BiomeManager.addBiome((BiomeManager.BiomeType)BiomeManager.BiomeType.COOL, (BiomeManager.BiomeEntry)new BiomeManager.BiomeEntry(biomeTaint, Config.biomeTaintWeight));
    }

    public static void addDimBlacklist(int dim, int level) {
        dimensionBlacklist.put(dim, level);
    }

    public static int getDimBlacklist(int dim) {
        if (!dimensionBlacklist.containsKey(dim)) {
            return -1;
        }
        return dimensionBlacklist.get(dim);
    }

    public static void addBiomeBlacklist(int biome, int level) {
        biomeBlacklist.put(biome, level);
    }

    public static int getBiomeBlacklist(int biome) {
        if (!biomeBlacklist.containsKey(biome)) {
            return -1;
        }
        return biomeBlacklist.get(biome);
    }

    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
        this.worldGeneration(random, chunkX, chunkZ, world, true);
    }

    public void worldGeneration(Random random, int chunkX, int chunkZ, World world, boolean newGen) {
        if (world.field_73011_w.field_76574_g == Config.dimensionOuterId) {
            MazeHandler.generateEldritch(world, random, chunkX, chunkZ);
            world.func_72964_e(chunkX, chunkZ).func_76630_e();
        } else {
            switch (world.field_73011_w.field_76574_g) {
                case -1: {
                    this.generateNether(world, random, chunkX, chunkZ, newGen);
                    break;
                }
                case 1: {
                    break;
                }
                default: {
                    this.generateSurface(world, random, chunkX, chunkZ, newGen);
                }
            }
            if (!newGen) {
                world.func_72964_e(chunkX, chunkZ).func_76630_e();
            }
        }
    }

    private boolean generateTotem(World world, Random random, int chunkX, int chunkZ, boolean auraGen, boolean newGen) {
        if (Config.genStructure && (world.field_73011_w.field_76574_g == 0 || world.field_73011_w.field_76574_g == 1) && newGen && !auraGen && random.nextInt(Config.nodeRarity * 10) == 0) {
            int topy;
            int x = chunkX * 16 + random.nextInt(16);
            int z = chunkZ * 16 + random.nextInt(16);
            int n = topy = world.field_73011_w.field_76574_g == -1 ? Utils.getFirstUncoveredY(world, x, z) - 1 : world.func_72976_f(x, z) - 1;
            if (topy > world.func_72940_L()) {
                return false;
            }
            if (world.func_147439_a(x, topy, z) != null && world.func_147439_a(x, topy, z).isLeaves((IBlockAccess)world, x, topy, z)) {
                while (world.func_147439_a(x, --topy, z) != Blocks.field_150349_c && topy > 40) {
                }
            }
            if (world.func_147439_a(x, topy, z) == Blocks.field_150431_aC || world.func_147439_a(x, topy, z) == Blocks.field_150329_H) {
                --topy;
            }
            if (world.func_147439_a(x, topy, z) == Blocks.field_150349_c || world.func_147439_a(x, topy, z) == Blocks.field_150354_m || world.func_147439_a(x, topy, z) == Blocks.field_150346_d || world.func_147439_a(x, topy, z) == Blocks.field_150348_b || world.func_147439_a(x, topy, z) == Blocks.field_150424_aL) {
                int count;
                for (count = 1; (world.func_147437_c(x, topy + count, z) || world.func_147439_a(x, topy + count, z) == Blocks.field_150431_aC || world.func_147439_a(x, topy + count, z) == Blocks.field_150329_H) && count < 3; ++count) {
                }
                if (count >= 2) {
                    world.func_147465_d(x, topy, z, ConfigBlocks.blockCosmeticSolid, 1, 3);
                    count = 1;
                    while ((world.func_147437_c(x, topy + count, z) || world.func_147439_a(x, topy + count, z) == Blocks.field_150431_aC || world.func_147439_a(x, topy + count, z) == Blocks.field_150329_H) && count < 5) {
                        world.func_147465_d(x, topy + count, z, ConfigBlocks.blockCosmeticSolid, 0, 3);
                        if (count > 1 && random.nextInt(4) == 0) {
                            world.func_147465_d(x, topy + count, z, ConfigBlocks.blockCosmeticSolid, 8, 3);
                            ThaumcraftWorldGenerator.createRandomNodeAt(world, x, topy + count, z, random, false, true, false);
                            count = 5;
                            auraGen = true;
                        }
                        if (++count < 5 || auraGen) continue;
                        world.func_147465_d(x, topy + 5, z, ConfigBlocks.blockCosmeticSolid, 8, 3);
                        ThaumcraftWorldGenerator.createRandomNodeAt(world, x, topy + 5, z, random, false, true, false);
                    }
                }
            }
        }
        return false;
    }

    private boolean generateWildNodes(World world, Random random, int chunkX, int chunkZ, boolean auraGen, boolean newGen) {
        if (Config.genAura && random.nextInt(Config.nodeRarity) == 0 && !auraGen) {
            int y;
            int z;
            int x = chunkX * 16 + random.nextInt(16);
            int q = Utils.getFirstUncoveredY(world, x, z = chunkZ * 16 + random.nextInt(16));
            if (q < 2) {
                q = world.field_73011_w.func_76557_i() + random.nextInt(64) - 32 + Utils.getFirstUncoveredY(world, x, z);
            }
            if (q < 2) {
                q = 32 + random.nextInt(64);
            }
            if (world.func_147437_c(x, q + 1, z)) {
                ++q;
            }
            int p = random.nextInt(4);
            Block b = world.func_147439_a(x, q + p, z);
            if (world.func_147437_c(x, q + p, z) || b.isReplaceable((IBlockAccess)world, x, q + p, z)) {
                q += p;
            }
            if ((y = q) > world.func_72940_L()) {
                return false;
            }
            ThaumcraftWorldGenerator.createRandomNodeAt(world, x, y, z, random, false, false, false);
            return true;
        }
        return false;
    }

    public static void createRandomNodeAt(World world, int x, int y, int z, Random random, boolean silverwood, boolean eerie, boolean small) {
        int a;
        if (basicAspects.size() == 0) {
            for (Aspect as : c) {
                if (as.getComponents() != null) {
                    complexAspects.add(as);
                    continue;
                }
                basicAspects.add(as);
            }
        }
        NodeType type = NodeType.NORMAL;
        if (silverwood) {
            type = NodeType.PURE;
        } else if (eerie) {
            type = NodeType.DARK;
        } else if (random.nextInt(Config.specialNodeRarity) == 0) {
            switch (random.nextInt(10)) {
                case 0: 
                case 1: 
                case 2: {
                    type = NodeType.DARK;
                    break;
                }
                case 3: 
                case 4: 
                case 5: {
                    type = NodeType.UNSTABLE;
                    break;
                }
                case 6: 
                case 7: 
                case 8: {
                    type = NodeType.PURE;
                    break;
                }
                case 9: {
                    type = NodeType.HUNGRY;
                }
            }
        }
        NodeModifier modifier = null;
        if (random.nextInt(Config.specialNodeRarity / 2) == 0) {
            switch (random.nextInt(3)) {
                case 0: {
                    modifier = NodeModifier.BRIGHT;
                    break;
                }
                case 1: {
                    modifier = NodeModifier.PALE;
                    break;
                }
                case 2: {
                    modifier = NodeModifier.FADING;
                }
            }
        }
        BiomeGenBase bg = world.func_72807_a(x, z);
        int baura = BiomeHandler.getBiomeAura(bg);
        if (type != NodeType.PURE && bg.field_76756_M == ThaumcraftWorldGenerator.biomeTaint.field_76756_M) {
            baura = (int)((float)baura * 1.5f);
            if (random.nextBoolean()) {
                type = NodeType.TAINTED;
                baura = (int)((float)baura * 1.5f);
            }
        }
        if (silverwood || small) {
            baura /= 4;
        }
        int value = random.nextInt(baura / 2) + baura / 2;
        Aspect ra = BiomeHandler.getRandomBiomeTag(bg.field_76756_M, random);
        AspectList al = new AspectList();
        if (ra != null) {
            al.add(ra, 2);
        } else {
            Aspect aa = complexAspects.get(random.nextInt(complexAspects.size()));
            al.add(aa, 1);
            aa = basicAspects.get(random.nextInt(basicAspects.size()));
            al.add(aa, 1);
        }
        for (int a2 = 0; a2 < 3; ++a2) {
            Aspect aa;
            if (!random.nextBoolean()) continue;
            if (random.nextInt(Config.specialNodeRarity) == 0) {
                aa = complexAspects.get(random.nextInt(complexAspects.size()));
                al.merge(aa, 1);
                continue;
            }
            aa = basicAspects.get(random.nextInt(basicAspects.size()));
            al.merge(aa, 1);
        }
        if (type == NodeType.HUNGRY) {
            al.merge(Aspect.HUNGER, 2);
            if (random.nextBoolean()) {
                al.merge(Aspect.GREED, 1);
            }
        } else if (type == NodeType.PURE) {
            if (random.nextBoolean()) {
                al.merge(Aspect.LIFE, 2);
            } else {
                al.merge(Aspect.ORDER, 2);
            }
        } else if (type == NodeType.DARK) {
            if (random.nextBoolean()) {
                al.merge(Aspect.DEATH, 1);
            }
            if (random.nextBoolean()) {
                al.merge(Aspect.UNDEAD, 1);
            }
            if (random.nextBoolean()) {
                al.merge(Aspect.ENTROPY, 1);
            }
            if (random.nextBoolean()) {
                al.merge(Aspect.DARKNESS, 1);
            }
        }
        int water = 0;
        int lava = 0;
        int stone = 0;
        int foliage = 0;
        try {
            for (int xx = -5; xx <= 5; ++xx) {
                for (int yy = -5; yy <= 5; ++yy) {
                    for (int zz = -5; zz <= 5; ++zz) {
                        try {
                            Block bi = world.func_147439_a(x + xx, y + yy, z + zz);
                            if (bi.func_149688_o() == Material.field_151586_h) {
                                ++water;
                            } else if (bi.func_149688_o() == Material.field_151587_i) {
                                ++lava;
                            } else if (bi == Blocks.field_150348_b) {
                                ++stone;
                            }
                            if (!bi.isFoliage((IBlockAccess)world, x + xx, y + yy, z + zz)) continue;
                            ++foliage;
                            continue;
                        }
                        catch (Exception e) {
                            // empty catch block
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            // empty catch block
        }
        if (water > 100) {
            al.merge(Aspect.WATER, 1);
        }
        if (lava > 100) {
            al.merge(Aspect.FIRE, 1);
            al.merge(Aspect.EARTH, 1);
        }
        if (stone > 500) {
            al.merge(Aspect.EARTH, 1);
        }
        if (foliage > 100) {
            al.merge(Aspect.PLANT, 1);
        }
        int[] spread = new int[al.size()];
        float total = 0.0f;
        for (a = 0; a < spread.length; ++a) {
            spread[a] = al.getAmount(al.getAspectsSorted()[a]) == 2 ? 50 + random.nextInt(25) : 25 + random.nextInt(50);
            total += (float)spread[a];
        }
        for (a = 0; a < spread.length; ++a) {
            al.merge(al.getAspectsSorted()[a], (int)((float)spread[a] / total * (float)value));
        }
        ThaumcraftWorldGenerator.createNodeAt(world, x, y, z, type, modifier, al);
    }

    public static void createNodeAt(World world, int x, int y, int z, NodeType nt, NodeModifier nm, AspectList al) {
        TileEntity te;
        if (world.func_147437_c(x, y, z)) {
            world.func_147465_d(x, y, z, ConfigBlocks.blockAiry, 0, 0);
        }
        if ((te = world.func_147438_o(x, y, z)) != null && te instanceof TileNode) {
            ((TileNode)te).setNodeType(nt);
            ((TileNode)te).setNodeModifier(nm);
            ((TileNode)te).setAspects(al);
        }
        world.func_147471_g(x, y, z);
    }

    private void generateSurface(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
        boolean auraGen = false;
        int blacklist = ThaumcraftWorldGenerator.getDimBlacklist(world.field_73011_w.field_76574_g);
        if (blacklist == -1 && Config.genTrees && !world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat") && (newGen || Config.regenTrees)) {
            this.generateVegetation(world, random, chunkX, chunkZ, newGen);
        }
        if (blacklist != 0 && blacklist != 2) {
            this.generateOres(world, random, chunkX, chunkZ, newGen);
        }
        if (blacklist != 0 && blacklist != 2 && Config.genAura && (newGen || Config.regenAura)) {
            ChunkPosition var7 = new MapGenScatteredFeature().func_151545_a(world, chunkX * 16 + 8, world.func_72976_f(chunkX * 16 + 8, chunkZ * 16 + 8), chunkZ * 16 + 8);
            if (var7 != null && !this.structureNode.containsKey(var7.hashCode())) {
                auraGen = true;
                this.structureNode.put(var7.hashCode(), true);
                ThaumcraftWorldGenerator.createRandomNodeAt(world, var7.field_151329_a, world.func_72976_f(var7.field_151329_a, var7.field_151328_c) + 3, var7.field_151328_c, random, false, false, false);
            }
            auraGen = this.generateWildNodes(world, random, chunkX, chunkZ, auraGen, newGen);
        }
        if (blacklist == -1 && Config.genStructure && world.field_73011_w.field_76574_g == 0 && !world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat") && (newGen || Config.regenStructure)) {
            int randPosZ;
            int randPosX = chunkX * 16 + random.nextInt(16);
            int randPosY = world.func_72976_f(randPosX, randPosZ = chunkZ * 16 + random.nextInt(16)) - 9;
            if (randPosY < world.func_72940_L()) {
                WorldGenHilltopStones hilltopStones;
                Chunk var1 = world.func_72938_d(MathHelper.func_76128_c((double)randPosX), MathHelper.func_76128_c((double)randPosZ));
                WorldGenMound mound = new WorldGenMound();
                if (random.nextInt(150) == 0) {
                    if (mound.func_76484_a(world, random, randPosX, randPosY, randPosZ)) {
                        auraGen = true;
                        int value = random.nextInt(200) + 400;
                        ThaumcraftWorldGenerator.createRandomNodeAt(world, randPosX + 9, randPosY + 8, randPosZ + 9, random, false, true, false);
                    }
                } else if (random.nextInt(66) == 0) {
                    WorldGenEldritchRing stonering = new WorldGenEldritchRing();
                    randPosY += 8;
                    int w = 11 + random.nextInt(6) * 2;
                    int h = 11 + random.nextInt(6) * 2;
                    stonering.chunkX = chunkX;
                    stonering.chunkZ = chunkZ;
                    stonering.width = w;
                    stonering.height = h;
                    if (stonering.func_76484_a(world, random, randPosX, randPosY, randPosZ)) {
                        auraGen = true;
                        ThaumcraftWorldGenerator.createRandomNodeAt(world, randPosX, randPosY + 2, randPosZ, random, false, true, false);
                        Thread t = new Thread(new MazeThread(chunkX, chunkZ, w, h, random.nextLong()));
                        t.start();
                    }
                } else if (random.nextInt(40) == 0 && (hilltopStones = new WorldGenHilltopStones()).func_76484_a(world, random, randPosX, randPosY += 9, randPosZ)) {
                    auraGen = true;
                    ThaumcraftWorldGenerator.createRandomNodeAt(world, randPosX, randPosY + 5, randPosZ, random, false, true, false);
                }
            }
            this.generateTotem(world, random, chunkX, chunkZ, auraGen, newGen);
        }
    }

    private void generateVegetation(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
        int randPosZ;
        int randPosX;
        int randPosY;
        BiomeGenBase bgb = world.func_72807_a(chunkX * 16 + 8, chunkZ * 16 + 8);
        if (ThaumcraftWorldGenerator.getBiomeBlacklist(bgb.field_76756_M) != -1) {
            return;
        }
        if (random.nextInt(60) == 3) {
            ThaumcraftWorldGenerator.generateSilverwood(world, random, chunkX, chunkZ);
        }
        if (random.nextInt(25) == 7) {
            ThaumcraftWorldGenerator.generateGreatwood(world, random, chunkX, chunkZ);
        }
        if ((randPosY = world.func_72976_f(randPosX = chunkX * 16 + random.nextInt(16), randPosZ = chunkZ * 16 + random.nextInt(16))) > world.func_72940_L()) {
            return;
        }
        if (world.func_72807_a((int)randPosX, (int)randPosZ).field_76752_A == Blocks.field_150354_m && world.func_72807_a((int)randPosX, (int)randPosZ).field_76750_F > 1.0f && random.nextInt(30) == 0) {
            ThaumcraftWorldGenerator.generateFlowers(world, random, randPosX, randPosY, randPosZ, 3);
        }
    }

    private void generateOres(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
        int randPosZ;
        int randPosY;
        Block block;
        int randPosX;
        int i;
        block26: {
            block25: {
                BiomeGenBase bgb = world.func_72807_a(chunkX * 16 + 8, chunkZ * 16 + 8);
                if (ThaumcraftWorldGenerator.getBiomeBlacklist(bgb.field_76756_M) == 0) break block25;
                if (ThaumcraftWorldGenerator.getBiomeBlacklist(bgb.field_76756_M) != 2) break block26;
            }
            return;
        }
        if (Config.genCinnibar && (newGen || Config.regenCinnibar)) {
            for (i = 0; i < 18; ++i) {
                int randPosZ2;
                int randPosY2;
                randPosX = chunkX * 16 + random.nextInt(16);
                block = world.func_147439_a(randPosX, randPosY2 = random.nextInt(world.func_72800_K() / 5), randPosZ2 = chunkZ * 16 + random.nextInt(16));
                if (block == null || !block.isReplaceableOreGen(world, randPosX, randPosY2, randPosZ2, Blocks.field_150348_b)) continue;
                world.func_147465_d(randPosX, randPosY2, randPosZ2, ConfigBlocks.blockCustomOre, 0, 0);
            }
        }
        if (Config.genAmber && (newGen || Config.regenAmber)) {
            for (i = 0; i < 20; ++i) {
                randPosX = chunkX * 16 + random.nextInt(16);
                block = world.func_147439_a(randPosX, randPosY = world.func_72976_f(randPosX, randPosZ = chunkZ * 16 + random.nextInt(16)) - random.nextInt(25), randPosZ);
                if (block == null || !block.isReplaceableOreGen(world, randPosX, randPosY, randPosZ, Blocks.field_150348_b)) continue;
                world.func_147465_d(randPosX, randPosY, randPosZ, ConfigBlocks.blockCustomOre, 7, 2);
            }
        }
        if (Config.genInfusedStone && (newGen || Config.regenInfusedStone)) {
            for (i = 0; i < 8; ++i) {
                randPosX = chunkX * 16 + random.nextInt(16);
                randPosZ = chunkZ * 16 + random.nextInt(16);
                randPosY = random.nextInt(Math.max(5, world.func_72976_f(randPosX, randPosZ) - 5));
                int md = random.nextInt(6) + 1;
                if (random.nextInt(3) == 0) {
                    Aspect tag = BiomeHandler.getRandomBiomeTag(world.func_72807_a((int)randPosX, (int)randPosZ).field_76756_M, random);
                    if (tag == null) {
                        md = 1 + random.nextInt(6);
                    } else if (tag == Aspect.AIR) {
                        md = 1;
                    } else if (tag == Aspect.FIRE) {
                        md = 2;
                    } else if (tag == Aspect.WATER) {
                        md = 3;
                    } else if (tag == Aspect.EARTH) {
                        md = 4;
                    } else if (tag == Aspect.ORDER) {
                        md = 5;
                    } else if (tag == Aspect.ENTROPY) {
                        md = 6;
                    }
                }
                try {
                    new WorldGenMinable(ConfigBlocks.blockCustomOre, md, 6, Blocks.field_150348_b).func_76484_a(world, random, randPosX, randPosY, randPosZ);
                    continue;
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void generateNether(World world, Random random, int chunkX, int chunkZ, boolean newGen) {
        boolean auraGen = false;
        if (!world.func_72912_H().func_76067_t().func_77127_a().startsWith("flat") && (newGen || Config.regenStructure)) {
            this.generateTotem(world, random, chunkX, chunkZ, auraGen, newGen);
        }
        if (newGen || Config.regenAura) {
            this.generateWildNodes(world, random, chunkX, chunkZ, auraGen, newGen);
        }
    }

    public static boolean generateFlowers(World world, Random random, int x, int y, int z, int flower) {
        WorldGenCustomFlowers flowers = new WorldGenCustomFlowers(ConfigBlocks.blockCustomPlant, flower);
        return flowers.func_76484_a(world, random, x, y, z);
    }

    public static boolean generateGreatwood(World world, Random random, int chunkX, int chunkZ) {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);
        int y = world.func_72976_f(x, z);
        int bio = world.func_72807_a((int)x, (int)z).field_76756_M;
        if (BiomeHandler.getBiomeSupportsGreatwood(bio) > random.nextFloat()) {
            boolean t = new WorldGenGreatwoodTrees(false).generate(world, random, x, y, z, random.nextInt(8) == 0);
            return t;
        }
        return false;
    }

    public static boolean generateSilverwood(World world, Random random, int chunkX, int chunkZ) {
        int x = chunkX * 16 + random.nextInt(16);
        int z = chunkZ * 16 + random.nextInt(16);
        int y = world.func_72976_f(x, z);
        BiomeGenBase bio = world.func_72807_a(x, z);
        if (!(bio.equals(biomeMagicalForest) || bio.equals(biomeTaint) || !BiomeDictionary.isBiomeOfType((BiomeGenBase)bio, (BiomeDictionary.Type)BiomeDictionary.Type.MAGICAL) && bio.field_76756_M != BiomeGenBase.field_76785_t.field_76756_M && bio.field_76756_M != BiomeGenBase.field_150582_Q.field_76756_M)) {
            boolean t = new WorldGenSilverwoodTrees(false, 7, 4).func_76484_a(world, random, x, y, z);
            return t;
        }
        return false;
    }

    static {
        c = Aspect.aspects.values();
        basicAspects = new ArrayList();
        complexAspects = new ArrayList();
        dimensionBlacklist = new HashMap();
        biomeBlacklist = new HashMap();
    }
}

