/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.monster.EntitySlime
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.entity.passive.EntityCow
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.entity.passive.EntitySheep
 *  net.minecraft.entity.passive.EntitySquid
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.init.Blocks
 *  net.minecraft.nbt.CompressedStreamTools
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.nbt.NBTTagList
 *  net.minecraft.potion.Potion
 *  net.minecraft.server.MinecraftServer
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldServer
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.BiomeGenBase$TempCategory
 *  net.minecraft.world.gen.NoiseGeneratorOctaves
 *  thaumcraft.common.config.Config
 *  thaumcraft.common.config.ConfigBlocks
 *  thaumcraft.common.entities.monster.EntityTaintSporeSwarmer
 *  thaumcraft.common.entities.monster.EntityTaintacle
 *  thaumcraft.common.lib.utils.Utils
 *  thaumcraft.common.lib.world.ThaumcraftWorldGenerator
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityMeatSlime;
import com.kentington.thaumichorizons.common.entities.EntityMercurialSlime;
import com.kentington.thaumichorizons.common.tiles.TileVortex;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.Config;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.entities.monster.EntityTaintSporeSwarmer;
import thaumcraft.common.entities.monster.EntityTaintacle;
import thaumcraft.common.lib.utils.Utils;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;

public class PocketPlaneData {
    public int radius = 32;
    public int color = 0;
    public int[] potionEffects;
    public int[] portalA;
    public int[] portalB;
    public int[] portalC;
    public int[] portalD;
    public String name = "Generic Pocket Plane";
    public static ArrayList<PocketPlaneData> planes = new ArrayList();
    public static ArrayList<Vec3> positions = new ArrayList();

    public static void generatePocketPlane(AspectList aspects, PocketPlaneData data, World world, int vortexX, int vortexY, int vortexZ) {
        System.out.println("Starting pocket plane generation");
        int xCenter = 0;
        int yCenter = 128;
        int zCenter = 256 * planes.size();
        data.radius = (float)aspects.visSize() * 0.75f < 128.0f ? (int)Math.max(32.0f, (float)aspects.visSize() * 0.75f) : 127;
        data.color = PocketPlaneData.getColor(aspects);
        BiomeGenBase bio = PocketPlaneData.setBiome(xCenter, yCenter, zCenter, data, world, aspects);
        int noise = PocketPlaneData.calcNoise(aspects);
        int life = PocketPlaneData.calcLife(aspects);
        PocketPlaneData.drawLayers(xCenter, yCenter, zCenter, data, world, aspects, noise, bio, life);
        PocketPlaneData.drawCaves(xCenter, yCenter, zCenter, data, world, aspects, noise);
        PocketPlaneData.drawPockets(xCenter, yCenter, zCenter, data, world, aspects, noise);
        PocketPlaneData.drawRavines(xCenter, yCenter, zCenter, data, world, aspects, noise);
        PocketPlaneData.drawClouds(xCenter, yCenter, zCenter, data, world, aspects, noise);
        PocketPlaneData.drawSurfaceFeatures(xCenter, yCenter, zCenter, data, world, aspects, noise, life);
        PocketPlaneData.drawUndergroundFeatures(xCenter, yCenter, zCenter, data, world, aspects, noise, life);
        PocketPlaneData.drawLeviathanBones(xCenter, yCenter, zCenter, data, world, aspects, noise);
        PocketPlaneData.addEffects(data, aspects);
        PocketPlaneData.drawRings(xCenter, yCenter, zCenter, data, world, aspects);
        PocketPlaneData.drawSphere(xCenter, yCenter, zCenter, data.radius, ThaumicHorizons.blockVoid, 0, world);
        for (int x = -2; x <= 2; ++x) {
            for (int z = -2; z <= 2; ++z) {
                world.func_147465_d(xCenter + x, yCenter, zCenter + z, ConfigBlocks.blockCosmeticSolid, 6, 0);
                world.func_147468_f(xCenter + x, yCenter + 1, zCenter + z);
                world.func_147468_f(xCenter + x, yCenter + 2, zCenter + z);
            }
        }
        world.func_147449_b(xCenter, yCenter + 1, zCenter, ThaumicHorizons.blockVortex);
        TileVortex vortex = (TileVortex)world.func_147438_o(xCenter, yCenter + 1, zCenter);
        vortex.cheat = true;
        vortex.dimensionID = planes.size();
        vortex.createdDimension = true;
        data.portalA = new int[3];
        data.portalB = new int[3];
        data.portalC = new int[3];
        data.portalD = new int[3];
        planes.add(data);
        positions.add(Vec3.func_72443_a((double)vortexX, (double)vortexY, (double)vortexZ));
        System.out.println("Finished with pocket plane generation!");
    }

    static int getColor(AspectList aspects) {
        int r = 0;
        int g = 0;
        int b = 0;
        for (Aspect asp : aspects.getAspects()) {
            Color aspColor = new Color(asp.getColor());
            r = (int)((float)r + (float)aspColor.getRed() * PocketPlaneData.aspectFraction(asp, aspects));
            g = (int)((float)g + (float)aspColor.getGreen() * PocketPlaneData.aspectFraction(asp, aspects));
            b = (int)((float)b + (float)aspColor.getBlue() * PocketPlaneData.aspectFraction(asp, aspects));
        }
        int color = r * 256 * 256 + g * 256 + b;
        System.out.println("Plane color is " + color);
        return color;
    }

    public static void addEffects(PocketPlaneData data, AspectList aspects) {
        int pointer = 0;
        data.potionEffects = new int[8];
        if (aspects.getAmount(Aspect.MOTION) > 0) {
            data.potionEffects[pointer] = Potion.field_76424_c.field_76415_H;
            ++pointer;
        }
        if (aspects.getAmount(Aspect.FLIGHT) > 0) {
            data.potionEffects[pointer] = Potion.field_76430_j.field_76415_H;
            ++pointer;
        }
        if (aspects.getAmount(Aspect.HEAL) > 0) {
            data.potionEffects[pointer] = Potion.field_76428_l.field_76415_H;
            ++pointer;
        }
        if (aspects.getAmount(Aspect.TRAVEL) > 0) {
            data.potionEffects[pointer] = Potion.field_76424_c.field_76415_H;
            ++pointer;
        }
        if (aspects.getAmount(Aspect.TOOL) > 0) {
            data.potionEffects[pointer] = Potion.field_76422_e.field_76415_H;
            ++pointer;
        }
        if (aspects.getAmount(Aspect.WEAPON) > 0) {
            data.potionEffects[pointer] = Potion.field_76420_g.field_76415_H;
            ++pointer;
        }
        if (aspects.getAmount(Aspect.ARMOR) > 0) {
            data.potionEffects[pointer] = Potion.field_76429_m.field_76415_H;
            ++pointer;
        }
    }

    public static boolean drawRings(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects) {
        boolean drewAnything = false;
        int numRings = 0;
        if (aspects.getAmount(Aspect.ELDRITCH) > 0) {
            PocketPlaneData.drawSphere(xCenter, yCenter, zCenter, data.radius - ++numRings, ConfigBlocks.blockCosmeticSolid, 11, world);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.DARKNESS) > 0) {
            PocketPlaneData.drawSphere(xCenter, yCenter, zCenter, data.radius - ++numRings, Blocks.field_150343_Z, 0, world);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.LIGHT) > 0) {
            PocketPlaneData.drawSphere(xCenter, yCenter, zCenter, data.radius - ++numRings, ThaumicHorizons.blockLight, 11, world);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.ELDRITCH) > 0) {
            PocketPlaneData.drawSphere(xCenter, yCenter, zCenter, data.radius - ++numRings, ConfigBlocks.blockCosmeticSolid, 11, world);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.CRYSTAL) > 0) {
            PocketPlaneData.drawSphere(xCenter, yCenter, zCenter, data.radius - ++numRings, Blocks.field_150359_w, 0, world);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.COLD) > 0) {
            PocketPlaneData.drawSphere(xCenter, yCenter, zCenter, data.radius - ++numRings, Blocks.field_150403_cj, 0, world);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.WEATHER) > 0 || aspects.getAmount(Aspect.AIR) > 0 && aspects.getAmount(Aspect.WATER) > 0) {
            PocketPlaneData.drawSphere(xCenter, yCenter, zCenter, data.radius - ++numRings, ThaumicHorizons.blockCloud, 0, world);
            drewAnything = true;
        }
        return drewAnything;
    }

    public static int calcNoise(AspectList aspects) {
        int noise = 50;
        noise = (int)((float)noise - PocketPlaneData.aspectFraction(Aspect.ORDER, aspects) * 100.0f);
        noise = (int)((float)noise + PocketPlaneData.aspectFraction(Aspect.ENTROPY, aspects) * 100.0f);
        noise = (int)((float)noise + PocketPlaneData.aspectFraction(Aspect.MOTION, aspects) * 50.0f);
        noise = (int)((float)noise + PocketPlaneData.aspectFraction(Aspect.EXCHANGE, aspects) * 100.0f);
        noise = (int)((float)noise + PocketPlaneData.aspectFraction(Aspect.FLIGHT, aspects) * 50.0f);
        if ((noise = (int)((float)noise - PocketPlaneData.aspectFraction(Aspect.TRAVEL, aspects) * 100.0f)) > 100) {
            return 100;
        }
        if (noise < 0) {
            return 0;
        }
        return noise;
    }

    public static int calcLife(AspectList aspects) {
        int life = 0;
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.LIGHT, aspects) * 25.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.WEATHER, aspects) * 25.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.LIFE, aspects) * 150.0f);
        life = (int)((float)life - PocketPlaneData.aspectFraction(Aspect.POISON, aspects) * 75.0f);
        life = (int)((float)life - PocketPlaneData.aspectFraction(Aspect.DEATH, aspects) * 100.0f);
        life = (int)((float)life - PocketPlaneData.aspectFraction(Aspect.DARKNESS, aspects) * 25.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.SOUL, aspects) * 50.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.HEAL, aspects) * 150.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.AURA, aspects) * 50.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.SLIME, aspects) * 50.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.PLANT, aspects) * 100.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.TREE, aspects) * 100.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.BEAST, aspects) * 100.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.FLESH, aspects) * 50.0f);
        life = (int)((float)life - PocketPlaneData.aspectFraction(Aspect.UNDEAD, aspects) * 50.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.MIND, aspects) * 25.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.SENSES, aspects) * 25.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.MAN, aspects) * 25.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.CROP, aspects) * 50.0f);
        life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.HARVEST, aspects) * 25.0f);
        life = (int)((float)life - PocketPlaneData.aspectFraction(Aspect.WEAPON, aspects) * 25.0f);
        life = (int)((float)life - PocketPlaneData.aspectFraction(Aspect.HUNGER, aspects) * 25.0f);
        if ((life = (int)((float)life + PocketPlaneData.aspectFraction(Aspect.CLOTH, aspects) * 25.0f)) < 0) {
            life = 0;
        } else if (life > 100) {
            life = 100;
        }
        return life;
    }

    public static float aspectFraction(Aspect asp, AspectList aspects) {
        return (float)aspects.getAmount(asp) / (float)aspects.visSize();
    }

    public static void drawLayers(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise, BiomeGenBase bio, int life) {
        int total = aspects.visSize();
        int level = yCenter;
        boolean drewAnything = false;
        if (aspects.getAmount(Aspect.COLD) > 0) {
            PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, Blocks.field_150403_cj, 0, level, 0, null, 0, aspects);
            level -= (int)(PocketPlaneData.aspectFraction(Aspect.COLD, aspects) * (float)data.radius);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.WATER) > 0) {
            PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, Blocks.field_150355_j, 0, level, 0, bio, life, aspects);
            level -= (int)(PocketPlaneData.aspectFraction(Aspect.WATER, aspects) * (float)data.radius / 1.5f);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.TAINT) > 0) {
            PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, ConfigBlocks.blockTaint, 1, level, noise, bio, life, aspects);
            level -= (int)(PocketPlaneData.aspectFraction(Aspect.TAINT, aspects) * (float)data.radius);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.CLOTH) > 0) {
            PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, Blocks.field_150325_L, 0, level, noise, null, 0, aspects);
            level -= (int)(PocketPlaneData.aspectFraction(Aspect.CLOTH, aspects) * (float)data.radius);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.FLESH) > 0) {
            PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, ConfigBlocks.blockTaint, 2, level, noise, null, 0, aspects);
            level -= (int)(PocketPlaneData.aspectFraction(Aspect.CLOTH, aspects) * (float)data.radius);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.EARTH) > 0) {
            if (bio == BiomeGenBase.field_76769_d) {
                PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, (Block)Blocks.field_150354_m, 0, level, noise, bio, life, aspects);
            } else {
                PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, Blocks.field_150346_d, 0, level, noise, bio, life, aspects);
            }
            if (aspects.getAmount(Aspect.ORDER) >= aspects.getAmount(Aspect.ENTROPY)) {
                PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, Blocks.field_150348_b, 0, level - 5, noise, null, 0, aspects);
            } else {
                PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, Blocks.field_150347_e, 0, level - 5, noise, null, 0, aspects);
            }
            level -= (int)(PocketPlaneData.aspectFraction(Aspect.EARTH, aspects) * (float)data.radius);
            drewAnything = true;
        }
        if (aspects.getAmount(Aspect.FIRE) > 0) {
            PocketPlaneData.drawLayer(xCenter, yCenter, zCenter, data, world, Blocks.field_150424_aL, 0, level, noise, null, 0, aspects);
            level -= (int)(PocketPlaneData.aspectFraction(Aspect.FIRE, aspects) * (float)data.radius);
            drewAnything = true;
        }
        if (!drewAnything) {
            // empty if block
        }
    }

    public static void drawLayer(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, Block block, int md, int level, int noise, BiomeGenBase bio, int life, AspectList aspects) {
        NoiseGeneratorOctaves noiseGen = new NoiseGeneratorOctaves(world.field_73012_v, 10);
        double[] noiseData = null;
        if (noise != 0) {
            noiseData = noiseGen.func_76304_a(noiseData, xCenter - data.radius, yCenter, zCenter - data.radius, 2 * data.radius, 1, 2 * data.radius, (double)((float)noise / 25.0f), (double)((float)noise / 50.0f), (double)((float)noise / 25.0f));
        }
        for (int x = -data.radius; x <= data.radius; ++x) {
            for (int z = -data.radius; z <= data.radius; ++z) {
                int bottom;
                int top = 0;
                if (noise != 0 && x + data.radius + (z * 2 * data.radius + data.radius * data.radius * 2) < noiseData.length) {
                    top = (int)(noiseData[x + data.radius + (z * 2 * data.radius + data.radius * data.radius * 2)] / 8.0);
                }
                int offsettop = -level + yCenter;
                int ctop = x * x + z * z + offsettop * offsettop - data.radius * data.radius;
                int tmax = offsettop + (int)Math.sqrt(offsettop * offsettop - ctop);
                int offsetbottom = level - yCenter;
                int cbottom = x * x + z * z + offsetbottom * offsetbottom - data.radius * data.radius;
                int bmax = offsetbottom + (int)Math.sqrt(offsetbottom * offsetbottom - cbottom);
                if (top > tmax) {
                    top = tmax;
                }
                for (int y = bottom = -bmax; y <= top; ++y) {
                    if (top == bottom || level + y <= 0) continue;
                    if (y == top) {
                        EntitySlime slime;
                        if (block == Blocks.field_150355_j) {
                            if (bio != null && bio.func_150561_m() == BiomeGenBase.TempCategory.COLD) {
                                world.func_147465_d(x + xCenter, y + level, z + zCenter, Blocks.field_150432_aD, 0, 0);
                            } else {
                                world.func_147465_d(x + xCenter, y + level, z + zCenter, block, 0, 0);
                                if ((life > 40 || aspects.getAmount(Aspect.BEAST) > 0) && world.field_73012_v.nextInt(100) > 98) {
                                    EntitySquid squiddie = new EntitySquid(world);
                                    squiddie.func_70107_b((double)(x + xCenter), (double)(y + level), (double)(z + zCenter));
                                    squiddie.func_110163_bv();
                                    world.func_72838_d((Entity)squiddie);
                                }
                                if (life > 0 && world.field_73012_v.nextInt(100) > 98) {
                                    world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150392_bi, 0, 0);
                                }
                            }
                        } else if (block == Blocks.field_150346_d) {
                            if (life > 0 && world.func_147437_c(x + xCenter, y + level + 1, z + zCenter)) {
                                world.func_147465_d(x + xCenter, y + level, z + zCenter, (Block)Blocks.field_150349_c, 0, 0);
                                if (life >= 10) {
                                    if ((life >= 20 || aspects.getAmount(Aspect.CROP) > 0 || aspects.getAmount(Aspect.HARVEST) > 0) && world.field_73012_v.nextInt(100) > 97) {
                                        switch (world.field_73012_v.nextInt(10)) {
                                            case 0: {
                                                world.func_147465_d(x + xCenter, y + level, z + zCenter, Blocks.field_150458_ak, 0, 0);
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150459_bM, 0, 0);
                                                break;
                                            }
                                            case 1: {
                                                world.func_147465_d(x + xCenter, y + level, z + zCenter, Blocks.field_150458_ak, 0, 0);
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150469_bN, 0, 0);
                                                break;
                                            }
                                            case 2: {
                                                world.func_147465_d(x + xCenter, y + level, z + zCenter, Blocks.field_150458_ak, 0, 0);
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150464_aj, 0, 0);
                                                break;
                                            }
                                            case 3: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150440_ba, 0, 0);
                                                break;
                                            }
                                            case 4: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150423_aK, 0, 0);
                                                break;
                                            }
                                            case 5: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150436_aH, 0, 0);
                                                break;
                                            }
                                            case 6: {
                                                world.func_147465_d(x + xCenter, y + level, z + zCenter, (Block)Blocks.field_150391_bh, 0, 0);
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, (Block)Blocks.field_150337_Q, 0, 0);
                                                break;
                                            }
                                            case 7: {
                                                world.func_147465_d(x + xCenter, y + level, z + zCenter, (Block)Blocks.field_150391_bh, 0, 0);
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, (Block)Blocks.field_150338_P, 0, 0);
                                                break;
                                            }
                                            case 8: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, ConfigBlocks.blockCustomPlant, 2, 0);
                                                break;
                                            }
                                            case 9: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, ConfigBlocks.blockCustomPlant, 5, 0);
                                            }
                                        }
                                    } else if ((life >= 15 || aspects.getAmount(Aspect.TREE) > 0) && world.field_73012_v.nextInt(100) > 97) {
                                        switch (world.field_73012_v.nextInt(10)) {
                                            case 0: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150345_g, 0, 0);
                                                break;
                                            }
                                            case 1: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150345_g, 1, 0);
                                                break;
                                            }
                                            case 2: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150345_g, 2, 0);
                                                break;
                                            }
                                            case 3: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150345_g, 3, 0);
                                                break;
                                            }
                                            case 4: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150345_g, 4, 0);
                                                break;
                                            }
                                            case 5: {
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150345_g, 5, 0);
                                                break;
                                            }
                                            case 6: {
                                                if ((bio == null || bio.field_76756_M != Config.biomeMagicalForestID) && aspects.getAmount(Aspect.MAGIC) <= 10 && aspects.getAmount(Aspect.AURA) <= 5 || world.field_73012_v.nextInt(4) != 0) break;
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, ConfigBlocks.blockCustomPlant, 1, 0);
                                                break;
                                            }
                                            case 7: {
                                                if (world.field_73012_v.nextInt(3) != 0) break;
                                                world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, ConfigBlocks.blockCustomPlant, 0, 0);
                                            }
                                        }
                                    } else if ((life >= 10 || aspects.getAmount(Aspect.PLANT) > 0) && world.field_73012_v.nextInt(100) > 94) {
                                        int random = world.field_73012_v.nextInt(18);
                                        if (random <= 15) {
                                            world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, (Block)Blocks.field_150328_O, random, 0);
                                        } else {
                                            world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, (Block)Blocks.field_150329_H, random - 16, 0);
                                        }
                                    }
                                }
                            } else {
                                world.func_147465_d(x + xCenter, y + level, z + zCenter, block, 0, 0);
                            }
                        } else if (block == Blocks.field_150354_m) {
                            world.func_147465_d(x + xCenter, y + level, z + zCenter, block, 0, 0);
                            if (life > 10) {
                                if (life >= 20 && world.field_73012_v.nextInt(100) > 98) {
                                    world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, ConfigBlocks.blockCustomPlant, 3, 0);
                                } else if (world.field_73012_v.nextInt(100) > 97) {
                                    world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150434_aF, 0, 0);
                                }
                            }
                        } else if (block == ConfigBlocks.blockTaint && md == 2 && world.field_73012_v.nextInt(300) > 298) {
                            int why;
                            int zee;
                            if (world.field_73012_v.nextBoolean()) {
                                for (int ecks = 0; ecks < 3; ++ecks) {
                                    for (zee = 0; zee < 2; ++zee) {
                                        for (why = 0; why < 2; ++why) {
                                            world.func_147465_d(x + xCenter + ecks, y + level + why, z + zCenter + zee, ThaumicHorizons.blockBrain, 0, 0);
                                        }
                                    }
                                }
                            } else {
                                for (int ecks = 0; ecks < 2; ++ecks) {
                                    for (zee = 0; zee < 3; ++zee) {
                                        for (why = 0; why < 2; ++why) {
                                            world.func_147465_d(x + xCenter + ecks, y + level + why, z + zCenter + zee, ThaumicHorizons.blockBrain, 0, 0);
                                        }
                                    }
                                }
                            }
                        }
                        if (bio != null && block.func_149721_r() && bio.func_150561_m() == BiomeGenBase.TempCategory.COLD) {
                            world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, Blocks.field_150431_aC, 1, 0);
                        }
                        if ((life >= 50 || aspects.getAmount(Aspect.BEAST) > 0) && world.field_73012_v.nextInt(100) > 98) {
                            EntityCow critter = null;
                            switch (world.field_73012_v.nextInt(7)) {
                                case 0: {
                                    critter = new EntityCow(world);
                                    break;
                                }
                                case 1: {
                                    critter = new EntityPig(world);
                                    break;
                                }
                                case 2: {
                                    critter = new EntityChicken(world);
                                    break;
                                }
                                case 3: {
                                    critter = new EntitySheep(world);
                                    break;
                                }
                                case 4: {
                                    critter = new EntityHorse(world);
                                    break;
                                }
                                case 5: {
                                    critter = new EntityOcelot(world);
                                    break;
                                }
                                case 6: {
                                    critter = new EntityWolf(world);
                                }
                            }
                            if (critter != null) {
                                critter.func_70107_b((double)(x + xCenter) + 0.5, (double)(y + level + 1), (double)(z + zCenter) + 0.5);
                                world.func_72838_d((Entity)critter);
                            }
                        }
                        if ((aspects.getAmount(Aspect.POISON) > 0 || aspects.getAmount(Aspect.EXCHANGE) > 0 || aspects.getAmount(Aspect.METAL) > 0 || aspects.getAmount(Aspect.MECHANISM) > 0) && world.field_73012_v.nextInt(100) > 98) {
                            slime = new EntityMercurialSlime(world);
                            slime.func_70107_b((double)(x + xCenter) + 0.5, (double)(y + level + 1), (double)(z + zCenter) + 0.5);
                            world.func_72838_d((Entity)slime);
                        }
                        if ((aspects.getAmount(Aspect.SLIME) > 0 || aspects.getAmount(Aspect.FLESH) > 0 || aspects.getAmount(Aspect.HUNGER) > 0) && world.field_73012_v.nextInt(100) > 98) {
                            slime = new EntityMeatSlime(world);
                            slime.func_70107_b((double)(x + xCenter) + 0.5, (double)(y + level + 1), (double)(z + zCenter) + 0.5);
                            world.func_72838_d((Entity)slime);
                        }
                        if (aspects.getAmount(Aspect.SLIME) > 0 && world.field_73012_v.nextInt(100) > 98) {
                            slime = new EntitySlime(world);
                            slime.func_70107_b((double)(x + xCenter) + 0.5, (double)(y + level + 1), (double)(z + zCenter) + 0.5);
                            world.func_72838_d((Entity)slime);
                        }
                        if (aspects.getAmount(Aspect.ELDRITCH) > 0 && world.field_73012_v.nextInt(200) > 198) {
                            world.func_147465_d(x + xCenter, y + level, z + zCenter, ConfigBlocks.blockCosmeticSolid, 1, 0);
                            world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, ConfigBlocks.blockCosmeticSolid, 0, 0);
                            world.func_147465_d(x + xCenter, y + level + 2, z + zCenter, ConfigBlocks.blockCosmeticSolid, 0, 0);
                            world.func_147465_d(x + xCenter, y + level + 3, z + zCenter, ConfigBlocks.blockCosmeticSolid, 0, 0);
                        }
                        if (aspects.getAmount(Aspect.CRAFT) > 0 && world.field_73012_v.nextInt(200) > 198) {
                            world.func_147465_d(x + xCenter, y + level + 1, z + zCenter, ConfigBlocks.blockStoneDevice, 1, 0);
                        }
                        if (aspects.getAmount(Aspect.TAINT) > 0 && world.field_73012_v.nextInt(100) > 98) {
                            world.func_147465_d(x + xCenter, y + level, z + zCenter, ConfigBlocks.blockTaintFibres, 0, 0);
                        }
                        if (aspects.getAmount(Aspect.TAINT) > 0 && world.field_73012_v.nextInt(200) > 198) {
                            slime = new EntityTaintacle(world);
                            slime.func_70107_b((double)(x + xCenter) + 0.5, (double)(y + level + 1), (double)(z + zCenter) + 0.5);
                            world.func_72838_d((Entity)slime);
                        }
                        if (aspects.getAmount(Aspect.TAINT) <= 0 || world.field_73012_v.nextInt(200) <= 198) continue;
                        slime = new EntityTaintSporeSwarmer(world);
                        slime.func_70107_b((double)(x + xCenter) + 0.5, (double)(y + level + 1), (double)(z + zCenter) + 0.5);
                        world.func_72838_d((Entity)slime);
                        continue;
                    }
                    if (block == null) continue;
                    world.func_147465_d(x + xCenter, y + level, z + zCenter, block, md, 0);
                }
            }
        }
    }

    public static void drawCaves(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise) {
        float solidity = 0.5f;
        solidity -= PocketPlaneData.aspectFraction(Aspect.AIR, aspects) * 0.5f;
        solidity += PocketPlaneData.aspectFraction(Aspect.ORDER, aspects) * 0.25f;
        solidity -= PocketPlaneData.aspectFraction(Aspect.VOID, aspects);
        solidity -= PocketPlaneData.aspectFraction(Aspect.FLIGHT, aspects) * 0.5f;
        solidity += PocketPlaneData.aspectFraction(Aspect.CRAFT, aspects) * 0.5f;
        if ((solidity -= PocketPlaneData.aspectFraction(Aspect.TRAP, aspects) * 0.5f) <= 0.0f) {
            solidity = 0.0f;
        } else if (solidity > 1.0f) {
            solidity = 1.0f;
        }
        int numCaves = (int)((float)(data.radius * data.radius * data.radius / 10000) * (solidity + 0.1f));
        HashMap<Aspect, Float> dressing = new HashMap<Aspect, Float>();
        if (aspects.getAmount(Aspect.EARTH) > 0) {
            dressing.put(Aspect.EARTH, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.EARTH, aspects)));
        }
        if (aspects.getAmount(Aspect.FIRE) > 0) {
            dressing.put(Aspect.FIRE, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.FIRE, aspects)));
        }
        if (aspects.getAmount(Aspect.ORDER) > 0) {
            dressing.put(Aspect.ORDER, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.ORDER, aspects)));
        }
        if (aspects.getAmount(Aspect.AIR) > 0) {
            dressing.put(Aspect.AIR, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.AIR, aspects)));
        }
        if (aspects.getAmount(Aspect.WATER) > 0) {
            dressing.put(Aspect.WATER, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.WATER, aspects)));
        }
        if (aspects.getAmount(Aspect.ENTROPY) > 0) {
            dressing.put(Aspect.ENTROPY, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.ENTROPY, aspects)));
        }
        if (aspects.getAmount(Aspect.METAL) > 0) {
            dressing.put(Aspect.METAL, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.METAL, aspects)));
        }
        if (aspects.getAmount(Aspect.DEATH) > 0) {
            dressing.put(Aspect.DEATH, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.DEATH, aspects)));
        }
        if (aspects.getAmount(Aspect.UNDEAD) > 0) {
            dressing.put(Aspect.UNDEAD, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.UNDEAD, aspects)));
        }
        if (aspects.getAmount(Aspect.SENSES) > 0) {
            dressing.put(Aspect.SENSES, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.SENSES, aspects)));
        }
        if (aspects.getAmount(Aspect.GREED) > 0) {
            dressing.put(Aspect.GREED, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.GREED, aspects)));
        }
        for (int i = 0; i < numCaves; ++i) {
            PocketPlaneData.drawACave(xCenter, yCenter, zCenter, data, world, aspects, noise, Blocks.field_150350_a, world.field_73012_v.nextInt(10) + 10, world.field_73012_v.nextInt(10) + 10, world.field_73012_v.nextInt((int)((float)data.radius * 1.5f)) - (int)((float)data.radius * 0.75f), -world.field_73012_v.nextInt((int)((float)data.radius * 0.5f)), world.field_73012_v.nextInt((int)((float)data.radius * 1.5f)) - (int)((float)data.radius * 0.75f), dressing);
        }
    }

    public static void drawACave(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise, Block block, int xSize, int zSize, int xOffset, int yOffset, int zOffset, HashMap<Aspect, Float> dressing) {
        NoiseGeneratorOctaves noiseGen = new NoiseGeneratorOctaves(world.field_73012_v, 10);
        double[] noiseData = null;
        noiseData = noiseGen.func_76304_a(noiseData, xCenter - xSize + xOffset, yCenter + yOffset, zCenter - zSize + zOffset, xSize, 16, zSize, (double)((float)noise / 10.0f), (double)((float)noise / 25.0f), (double)((float)noise / 10.0f));
        int avg = PocketPlaneData.getAvg(noiseData);
        for (int x = 0; x < xSize; ++x) {
            for (int z = 0; z < zSize; ++z) {
                for (int y = 0; y < 16; ++y) {
                    if (!(noiseData[x + xSize * z + y * xSize * zSize] > (double)avg) || world.func_147439_a(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z) == Blocks.field_150355_j) continue;
                    if (!world.func_147437_c(xCenter - xSize + xOffset + x, yCenter + yOffset + y - 1, zCenter - zSize + zOffset + z) && world.field_73012_v.nextInt(50) == 49) {
                        float which = world.field_73012_v.nextFloat();
                        Aspect whichAspect = null;
                        Iterator<Aspect> it = dressing.keySet().iterator();
                        boolean doDressing = false;
                        while (it.hasNext()) {
                            whichAspect = it.next();
                            if (which <= dressing.get(whichAspect).floatValue()) {
                                doDressing = true;
                                break;
                            }
                            which -= dressing.get(whichAspect).floatValue();
                        }
                        if (!doDressing || whichAspect == null || world.func_147437_c(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z) || world.func_147439_a(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z) == Blocks.field_150355_j) continue;
                        if (whichAspect == Aspect.EARTH) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, ConfigBlocks.blockCrystal, 3, 0);
                            continue;
                        }
                        if (whichAspect == Aspect.AIR) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, ConfigBlocks.blockCrystal, 0, 0);
                            continue;
                        }
                        if (whichAspect == Aspect.WATER) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, ConfigBlocks.blockCrystal, 2, 0);
                            continue;
                        }
                        if (whichAspect == Aspect.FIRE) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, ConfigBlocks.blockCrystal, 1, 0);
                            continue;
                        }
                        if (whichAspect == Aspect.ORDER) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, ConfigBlocks.blockCrystal, 4, 0);
                            continue;
                        }
                        if (whichAspect == Aspect.ENTROPY) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, ConfigBlocks.blockCrystal, 5, 0);
                            continue;
                        }
                        if (whichAspect == Aspect.DEATH || whichAspect == Aspect.UNDEAD) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, Blocks.field_150465_bP, 0, 0);
                            continue;
                        }
                        if (whichAspect == Aspect.SENSES) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, Blocks.field_150368_y, 0, 0);
                            continue;
                        }
                        if (whichAspect == Aspect.GREED) {
                            if (world.field_73012_v.nextBoolean()) {
                                world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, Blocks.field_150340_R, 0, 0);
                                continue;
                            }
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, Blocks.field_150475_bE, 0, 0);
                            continue;
                        }
                        if (whichAspect != Aspect.METAL) continue;
                        if (world.field_73012_v.nextBoolean()) {
                            world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, Blocks.field_150340_R, 0, 0);
                            continue;
                        }
                        world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, Blocks.field_150339_S, 0, 0);
                        continue;
                    }
                    world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, Blocks.field_150350_a, 0, 0);
                }
            }
        }
    }

    public static void drawRavines(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise) {
    }

    public static void drawClouds(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise) {
    }

    public static void drawSurfaceFeatures(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise, float life) {
    }

    public static void drawUndergroundFeatures(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise, float life) {
    }

    public static void drawLeviathanBones(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise) {
    }

    public static int getAvg(double[] array) {
        long x = 0L;
        for (int i = 0; i < array.length; ++i) {
            x = (long)((double)x + array[i]);
        }
        return (int)(x /= (long)array.length);
    }

    public static void drawPockets(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise) {
        int numPockets = data.radius * data.radius * data.radius / 200;
        HashMap<Aspect, Float> probabilities = new HashMap<Aspect, Float>();
        if (aspects.getAmount(Aspect.EARTH) > 0) {
            probabilities.put(Aspect.EARTH, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.EARTH, aspects)));
        }
        if (aspects.getAmount(Aspect.FIRE) > 0) {
            probabilities.put(Aspect.FIRE, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.FIRE, aspects)));
        }
        if (aspects.getAmount(Aspect.ORDER) > 0) {
            probabilities.put(Aspect.ORDER, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.ORDER, aspects)));
        }
        if (aspects.getAmount(Aspect.AIR) > 0) {
            probabilities.put(Aspect.AIR, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.AIR, aspects)));
        }
        if (aspects.getAmount(Aspect.WATER) > 0) {
            probabilities.put(Aspect.WATER, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.WATER, aspects)));
        }
        if (aspects.getAmount(Aspect.ENTROPY) > 0) {
            probabilities.put(Aspect.ENTROPY, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.ENTROPY, aspects)));
        }
        if (aspects.getAmount(Aspect.METAL) > 0) {
            probabilities.put(Aspect.METAL, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.METAL, aspects)));
        }
        if (aspects.getAmount(Aspect.CRYSTAL) > 0) {
            probabilities.put(Aspect.CRYSTAL, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.CRYSTAL, aspects)));
        }
        if (aspects.getAmount(Aspect.SOUL) > 0) {
            probabilities.put(Aspect.SOUL, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.SOUL, aspects)));
        }
        if (aspects.getAmount(Aspect.MAGIC) > 0) {
            probabilities.put(Aspect.MAGIC, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.MAGIC, aspects)));
        }
        if (aspects.getAmount(Aspect.AURA) > 0) {
            probabilities.put(Aspect.AURA, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.AURA, aspects)));
        }
        if (aspects.getAmount(Aspect.ENERGY) > 0) {
            probabilities.put(Aspect.ENERGY, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.ENERGY, aspects)));
        }
        if (aspects.getAmount(Aspect.TREE) > 0) {
            probabilities.put(Aspect.TREE, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.TREE, aspects)));
        }
        if (aspects.getAmount(Aspect.FLESH) > 0) {
            probabilities.put(Aspect.FLESH, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.FLESH, aspects)));
        }
        if (aspects.getAmount(Aspect.SENSES) > 0) {
            probabilities.put(Aspect.SENSES, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.SENSES, aspects)));
        }
        if (aspects.getAmount(Aspect.MINE) > 0) {
            probabilities.put(Aspect.MINE, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.MINE, aspects)));
        }
        if (aspects.getAmount(Aspect.TOOL) > 0) {
            probabilities.put(Aspect.TOOL, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.TOOL, aspects)));
        }
        if (aspects.getAmount(Aspect.GREED) > 0) {
            probabilities.put(Aspect.GREED, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.GREED, aspects)));
        }
        if (aspects.getAmount(Aspect.TAINT) > 0) {
            probabilities.put(Aspect.TAINT, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.TAINT, aspects)));
        }
        if (aspects.getAmount(Aspect.POISON) > 0) {
            probabilities.put(Aspect.POISON, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.POISON, aspects)));
        }
        if (aspects.getAmount(Aspect.EXCHANGE) > 0) {
            probabilities.put(Aspect.EXCHANGE, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.EXCHANGE, aspects)));
        }
        if (aspects.getAmount(Aspect.MIND) > 0) {
            probabilities.put(Aspect.MIND, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.MIND, aspects)));
        }
        if (aspects.getAmount(Aspect.MAN) > 0) {
            probabilities.put(Aspect.MAN, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.MAN, aspects)));
        }
        if (aspects.getAmount(Aspect.HUNGER) > 0) {
            probabilities.put(Aspect.HUNGER, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.HUNGER, aspects)));
        }
        if (aspects.getAmount(Aspect.CRAFT) > 0) {
            probabilities.put(Aspect.CRAFT, Float.valueOf(PocketPlaneData.aspectFraction(Aspect.CRAFT, aspects)));
        }
        Block block = Blocks.field_150350_a;
        int md = 0;
        for (int i = 0; i < numPockets; ++i) {
            float which = world.field_73012_v.nextFloat();
            Aspect whichAspect = null;
            Iterator it = probabilities.keySet().iterator();
            boolean doDressing = false;
            while (it.hasNext()) {
                whichAspect = (Aspect)it.next();
                if (which <= ((Float)probabilities.get(whichAspect)).floatValue()) {
                    doDressing = true;
                    break;
                }
                which -= ((Float)probabilities.get(whichAspect)).floatValue();
            }
            if (doDressing && whichAspect != null) {
                if (whichAspect == Aspect.EARTH) {
                    block = ConfigBlocks.blockCustomOre;
                    md = 4;
                } else if (whichAspect == Aspect.AIR) {
                    block = ConfigBlocks.blockCustomOre;
                    md = 1;
                } else if (whichAspect == Aspect.WATER) {
                    block = ConfigBlocks.blockCustomOre;
                    md = 3;
                } else if (whichAspect == Aspect.FIRE) {
                    if (world.field_73012_v.nextBoolean()) {
                        block = ConfigBlocks.blockCustomOre;
                        md = 2;
                    } else {
                        block = Blocks.field_150353_l;
                    }
                } else if (whichAspect == Aspect.ORDER) {
                    block = ConfigBlocks.blockCustomOre;
                    md = 5;
                } else if (whichAspect == Aspect.ENTROPY) {
                    block = ConfigBlocks.blockCustomOre;
                    md = 6;
                } else if (whichAspect == Aspect.METAL) {
                    if ((double)world.field_73012_v.nextFloat() <= 0.5) {
                        block = Blocks.field_150366_p;
                    } else if ((double)world.field_73012_v.nextFloat() <= 0.8) {
                        block = Blocks.field_150352_o;
                    } else {
                        block = ConfigBlocks.blockCustomOre;
                        md = 0;
                    }
                } else if (whichAspect == Aspect.MAGIC || whichAspect == Aspect.AURA) {
                    block = ConfigBlocks.blockCustomOre;
                    md = world.field_73012_v.nextInt(6) + 1;
                } else if (whichAspect == Aspect.CRYSTAL) {
                    block = Blocks.field_150371_ca;
                } else if (whichAspect == Aspect.SOUL) {
                    block = Blocks.field_150425_aM;
                } else if (whichAspect == Aspect.ENERGY) {
                    block = Blocks.field_150451_bX;
                } else if (whichAspect == Aspect.TREE) {
                    if (world.field_73012_v.nextBoolean()) {
                        block = Blocks.field_150364_r;
                    } else {
                        block = ConfigBlocks.blockCustomOre;
                        md = 7;
                    }
                } else if (whichAspect == Aspect.FLESH) {
                    block = ConfigBlocks.blockTaint;
                    md = 2;
                } else if (whichAspect == Aspect.SENSES) {
                    block = Blocks.field_150369_x;
                } else if (whichAspect == Aspect.MINE || whichAspect == Aspect.TOOL) {
                    float f = world.field_73012_v.nextFloat();
                    if (f < 0.4f) {
                        block = Blocks.field_150366_p;
                    } else if (f < 0.5f) {
                        block = Blocks.field_150352_o;
                    } else if (f < 0.6f) {
                        block = Blocks.field_150482_ag;
                    } else if (f < 0.7f) {
                        block = Blocks.field_150412_bA;
                    } else if (f < 0.8f) {
                        block = Blocks.field_150369_x;
                    } else {
                        block = ConfigBlocks.blockCustomOre;
                        md = world.field_73012_v.nextInt(8);
                    }
                } else if (whichAspect == Aspect.GREED) {
                    float f = world.field_73012_v.nextFloat();
                    block = f < 0.4f ? Blocks.field_150352_o : (f < 0.6f ? Blocks.field_150482_ag : (f < 0.8f ? Blocks.field_150412_bA : Blocks.field_150369_x));
                } else if (whichAspect == Aspect.TAINT) {
                    block = ConfigBlocks.blockTaint;
                    md = 0;
                } else if (whichAspect == Aspect.POISON) {
                    block = ConfigBlocks.blockFluidDeath;
                    md = 16;
                } else if (whichAspect == Aspect.EXCHANGE) {
                    block = ConfigBlocks.blockCustomOre;
                    md = 0;
                } else if (whichAspect == Aspect.MIND) {
                    block = world.field_73012_v.nextBoolean() ? Blocks.field_150342_X : ThaumicHorizons.blockBrain;
                } else if (whichAspect == Aspect.MAN) {
                    int man = world.field_73012_v.nextInt(3);
                    if (man == 0) {
                        block = Blocks.field_150342_X;
                    } else if (man == 1) {
                        block = ThaumicHorizons.blockBrain;
                    } else {
                        block = ConfigBlocks.blockTaint;
                        md = 2;
                    }
                } else if (whichAspect == Aspect.HUNGER) {
                    block = ConfigBlocks.blockTaint;
                    md = 2;
                } else if (whichAspect == Aspect.CRAFT) {
                    block = Blocks.field_150462_ai;
                }
            }
            PocketPlaneData.drawAPocket(xCenter, yCenter, zCenter, data, world, aspects, noise, block, md, world.field_73012_v.nextInt(3) + 1, world.field_73012_v.nextInt(3) + 1, world.field_73012_v.nextInt((int)((float)data.radius * 2.0f)) - (int)((float)data.radius * 1.0f), -world.field_73012_v.nextInt((int)((float)data.radius * 1.0f)), world.field_73012_v.nextInt((int)((float)data.radius * 2.0f)) - (int)((float)data.radius * 1.0f));
        }
    }

    public static void drawAPocket(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects, int noise, Block block, int md, int xSize, int zSize, int xOffset, int yOffset, int zOffset) {
        System.out.println("Drawing a pocket of " + block + " with width " + xSize + " and length " + zSize);
        NoiseGeneratorOctaves noiseGen = new NoiseGeneratorOctaves(world.field_73012_v, 10);
        double[] noiseDataTop = null;
        noiseDataTop = noiseGen.func_76304_a(noiseDataTop, xCenter - xSize + xOffset, yCenter + yOffset, zCenter - zSize + zOffset, xSize, 1, zSize, (double)((float)noise / 50.0f), (double)((float)noise / 25.0f), (double)((float)noise / 50.0f));
        double[] noiseDataBottom = null;
        noiseDataBottom = noiseGen.func_76304_a(noiseDataBottom, xCenter - xSize + xOffset, yCenter + yOffset, zCenter - zSize + zOffset, xSize, 1, zSize, (double)((float)noise / 50.0f), (double)((float)noise / 25.0f), (double)((float)noise / 50.0f));
        for (int x = 0; x < xSize; ++x) {
            for (int z = 0; z < zSize; ++z) {
                int bottom;
                int top = (int)(noiseDataTop[x + z * xSize] / 16.0) + 1;
                for (int y = bottom = (int)(noiseDataBottom[x + z * xSize] / 16.0) - 1; y < top; ++y) {
                    if (world.func_147437_c(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z) || world.func_147439_a(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z) == Blocks.field_150355_j) continue;
                    world.func_147465_d(xCenter - xSize + xOffset + x, yCenter + yOffset + y, zCenter - zSize + zOffset + z, block, md, 0);
                }
            }
        }
    }

    public static BiomeGenBase setBiome(int xCenter, int yCenter, int zCenter, PocketPlaneData data, World world, AspectList aspects) {
        BiomeGenBase bio = null;
        if (aspects.getAmount(Aspect.TAINT) > 0) {
            bio = ThaumcraftWorldGenerator.biomeTaint;
        } else if (aspects.getAmount(Aspect.ELDRITCH) > 0 || aspects.getAmount(Aspect.UNDEAD) > 0) {
            bio = ThaumcraftWorldGenerator.biomeEerie;
        } else if (aspects.getAmount(Aspect.MAGIC) > 0 || aspects.getAmount(Aspect.AURA) > 0) {
            bio = ThaumcraftWorldGenerator.biomeMagicalForest;
        } else {
            float temp = 0.5f;
            float moist = 0.5f;
            temp += PocketPlaneData.aspectFraction(Aspect.FIRE, aspects);
            temp -= PocketPlaneData.aspectFraction(Aspect.WATER, aspects) * 0.5f;
            temp -= PocketPlaneData.aspectFraction(Aspect.ORDER, aspects) * 0.5f;
            temp -= PocketPlaneData.aspectFraction(Aspect.VOID, aspects) * 0.5f;
            temp += PocketPlaneData.aspectFraction(Aspect.LIGHT, aspects) * 0.5f;
            temp -= PocketPlaneData.aspectFraction(Aspect.WEATHER, aspects) * 0.25f;
            temp += PocketPlaneData.aspectFraction(Aspect.MOTION, aspects) * 0.5f;
            temp -= PocketPlaneData.aspectFraction(Aspect.COLD, aspects);
            temp += PocketPlaneData.aspectFraction(Aspect.ENERGY, aspects) * 0.5f;
            if ((temp -= PocketPlaneData.aspectFraction(Aspect.DARKNESS, aspects) * 0.25f) < 0.0f) {
                temp = 0.0f;
            } else if (temp > 1.0f) {
                temp = 1.0f;
            }
            moist += PocketPlaneData.aspectFraction(Aspect.WATER, aspects);
            moist -= PocketPlaneData.aspectFraction(Aspect.FIRE, aspects) * 0.5f;
            moist -= PocketPlaneData.aspectFraction(Aspect.ENTROPY, aspects) * 0.25f;
            moist -= PocketPlaneData.aspectFraction(Aspect.VOID, aspects) * 0.25f;
            moist += PocketPlaneData.aspectFraction(Aspect.WEATHER, aspects) * 0.5f;
            moist += PocketPlaneData.aspectFraction(Aspect.SLIME, aspects) * 0.5f;
            if (moist < 0.0f) {
                moist = 0.0f;
            } else if (moist > 1.0f) {
                moist = 1.0f;
            }
            bio = (double)temp > 0.8 ? BiomeGenBase.field_76769_d : ((double)temp > 0.5 ? ((double)moist < 0.4 ? BiomeGenBase.field_150588_X : BiomeGenBase.field_76782_w) : ((double)temp > 0.2 ? ((double)moist < 0.4 ? BiomeGenBase.field_76772_c : BiomeGenBase.field_76767_f) : BiomeGenBase.field_76774_n));
        }
        for (int x = -data.radius; x <= data.radius; ++x) {
            for (int z = -data.radius; z <= data.radius; ++z) {
                Utils.setBiomeAt((World)world, (int)(x + xCenter), (int)(z + zCenter), (BiomeGenBase)bio);
            }
        }
        return bio;
    }

    public static void drawCircle(int x0, int y0, int z0, int y1, int radius, int error0, Block block, int md, World world) {
        int x = radius;
        int radiusError = error0;
        for (int z = 0; x >= z; ++z) {
            world.func_147465_d(x0 + x, y0 + y1, z0 + z, block, md, 0);
            world.func_147465_d(x0 + z, y0 + y1, z0 + x, block, md, 0);
            world.func_147465_d(x0 - x, y0 + y1, z0 + z, block, md, 0);
            world.func_147465_d(x0 - z, y0 + y1, z0 + x, block, md, 0);
            world.func_147465_d(x0 - x, y0 + y1, z0 - z, block, md, 0);
            world.func_147465_d(x0 - z, y0 + y1, z0 - x, block, md, 0);
            world.func_147465_d(x0 + x, y0 + y1, z0 - z, block, md, 0);
            world.func_147465_d(x0 + z, y0 + y1, z0 - x, block, md, 0);
            world.func_147465_d(x0 + x, y0 - y1, z0 + z, block, md, 0);
            world.func_147465_d(x0 + z, y0 - y1, z0 + x, block, md, 0);
            world.func_147465_d(x0 - x, y0 - y1, z0 + z, block, md, 0);
            world.func_147465_d(x0 - z, y0 - y1, z0 + x, block, md, 0);
            world.func_147465_d(x0 - x, y0 - y1, z0 - z, block, md, 0);
            world.func_147465_d(x0 - z, y0 - y1, z0 - x, block, md, 0);
            world.func_147465_d(x0 + x, y0 - y1, z0 - z, block, md, 0);
            world.func_147465_d(x0 + z, y0 - y1, z0 - x, block, md, 0);
            world.func_147465_d(x0 + y1, y0 + x, z0 + z, block, md, 0);
            world.func_147465_d(x0 + z, y0 + x, z0 + y1, block, md, 0);
            world.func_147465_d(x0 - y1, y0 + x, z0 + z, block, md, 0);
            world.func_147465_d(x0 - z, y0 + x, z0 + y1, block, md, 0);
            world.func_147465_d(x0 + y1, y0 + x, z0 - z, block, md, 0);
            world.func_147465_d(x0 + z, y0 + x, z0 - y1, block, md, 0);
            world.func_147465_d(x0 - y1, y0 + x, z0 - z, block, md, 0);
            world.func_147465_d(x0 - z, y0 + x, z0 - y1, block, md, 0);
            world.func_147465_d(x0 + y1, y0 - x, z0 + z, block, md, 0);
            world.func_147465_d(x0 + z, y0 - x, z0 + y1, block, md, 0);
            world.func_147465_d(x0 - y1, y0 - x, z0 + z, block, md, 0);
            world.func_147465_d(x0 - z, y0 - x, z0 + y1, block, md, 0);
            world.func_147465_d(x0 + y1, y0 - x, z0 - z, block, md, 0);
            world.func_147465_d(x0 + z, y0 - x, z0 - y1, block, md, 0);
            world.func_147465_d(x0 - y1, y0 - x, z0 - z, block, md, 0);
            world.func_147465_d(x0 - z, y0 - x, z0 - y1, block, md, 0);
            if (radiusError < 0) {
                radiusError += 2 * z + 1;
                continue;
            }
            radiusError += 2 * (z - --x + 1);
        }
    }

    public static void drawSphere(int x0, int y0, int z0, int radius, Block block, int md, World world) {
        int x = radius;
        int radiusError = 1 - x;
        for (int y = 0; x >= y; ++y) {
            PocketPlaneData.drawCircle(x0, y0, z0, y, x, radiusError, block, md, world);
            if (radiusError < 0) {
                radiusError += 2 * y + 1;
                continue;
            }
            radiusError += 2 * (y - --x + 1);
        }
    }

    public static void loadPocketPlanes(World world) {
        File planeFile = new File(world.func_72860_G().func_75765_b(), "pocketplane.dat");
        NBTTagCompound root = null;
        if (planeFile.exists()) {
            try {
                root = CompressedStreamTools.func_74796_a((InputStream)new FileInputStream(planeFile));
            }
            catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            if (root != null) {
                planes.clear();
                NBTTagList planeNBT = root.func_150295_c("Data", 10);
                for (int i = 0; i < planeNBT.func_74745_c(); ++i) {
                    NBTTagCompound thePlane = planeNBT.func_150305_b(i);
                    PocketPlaneData data = new PocketPlaneData();
                    data.radius = thePlane.func_74762_e("radius");
                    data.potionEffects = thePlane.func_74759_k("effects");
                    data.name = thePlane.func_74779_i("name");
                    data.color = thePlane.func_74762_e("color");
                    data.portalA = thePlane.func_74759_k("portalA");
                    data.portalB = thePlane.func_74759_k("portalB");
                    data.portalC = thePlane.func_74759_k("portalC");
                    data.portalD = thePlane.func_74759_k("portalD");
                    planes.add(data);
                }
                NBTTagCompound positionz = root.func_74775_l("Positions");
                positions.clear();
                for (String id : positionz.func_150296_c()) {
                    positions.add(Vec3.func_72443_a((double)((double)positionz.func_74759_k(id)[0] + 0.5), (double)(positionz.func_74759_k(id)[1] + 1), (double)((double)positionz.func_74759_k(id)[2] + 0.5)));
                }
            }
        }
    }

    public static void savePocketPlanes(World world) {
        File planeFile = new File(world.func_72860_G().func_75765_b(), "pocketplane.dat");
        NBTTagCompound root = new NBTTagCompound();
        NBTTagCompound positionz = new NBTTagCompound();
        int which = 0;
        for (Vec3 pos : positions) {
            positionz.func_74783_a(which + "", new int[]{(int)pos.field_72450_a, (int)pos.field_72448_b, (int)pos.field_72449_c});
            ++which;
        }
        root.func_74782_a("Positions", (NBTBase)positionz);
        NBTTagList planeNBT = new NBTTagList();
        root.func_74782_a("Data", (NBTBase)planeNBT);
        for (PocketPlaneData data : planes) {
            if (data == null) continue;
            NBTTagCompound thePlane = new NBTTagCompound();
            thePlane.func_74768_a("radius", data.radius);
            thePlane.func_74783_a("effects", data.potionEffects);
            thePlane.func_74778_a("name", data.name);
            thePlane.func_74768_a("color", data.color);
            thePlane.func_74783_a("portalA", data.portalA);
            thePlane.func_74783_a("portalB", data.portalB);
            thePlane.func_74783_a("portalC", data.portalC);
            thePlane.func_74783_a("portalD", data.portalD);
            planeNBT.func_74742_a((NBTBase)thePlane);
        }
        try {
            CompressedStreamTools.func_74799_a((NBTTagCompound)root, (OutputStream)new FileOutputStream(planeFile));
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int firstAvailablePortal(int num) {
        PocketPlaneData data = planes.get(num);
        if (data.portalA[0] == 0 && data.portalA[1] == 0 && data.portalA[2] == 0) {
            return 1;
        }
        if (data.portalB[0] == 0 && data.portalB[1] == 0 && data.portalB[2] == 0) {
            return 2;
        }
        if (data.portalC[0] == 0 && data.portalC[1] == 0 && data.portalC[2] == 0) {
            return 3;
        }
        if (data.portalD[0] == 0 && data.portalD[1] == 0 && data.portalD[2] == 0) {
            return 4;
        }
        return 0;
    }

    public static void destroyPortal(int id, int which) {
        System.out.println("Destroying portal " + which + " in plane " + id);
        PocketPlaneData data = planes.get(id);
        WorldServer world = MinecraftServer.func_71276_C().func_71218_a(ThaumicHorizons.dimensionPocketId);
        if (which == 1) {
            for (int x = -1; x <= 1; ++x) {
                for (int y = 126; y <= 128; ++y) {
                    world.func_147449_b(x, y, 256 * id + data.radius, ThaumicHorizons.blockVoid);
                }
            }
            data.portalA[0] = 0;
            data.portalA[1] = 0;
            data.portalA[2] = 0;
        } else if (which == 2) {
            for (int x = -1; x <= 1; ++x) {
                for (int y = 126; y <= 128; ++y) {
                    world.func_147449_b(x, y, 256 * id - data.radius, ThaumicHorizons.blockVoid);
                }
            }
            data.portalB[0] = 0;
            data.portalB[1] = 0;
            data.portalB[2] = 0;
        } else if (which == 3) {
            for (int z = -1; z <= 1; ++z) {
                for (int y = 126; y <= 128; ++y) {
                    world.func_147449_b(data.radius, y, 256 * id + z, ThaumicHorizons.blockVoid);
                }
            }
            data.portalC[0] = 0;
            data.portalC[1] = 0;
            data.portalC[2] = 0;
        } else if (which == 4) {
            for (int z = -1; z <= 1; ++z) {
                for (int y = 126; y <= 128; ++y) {
                    world.func_147449_b(-data.radius, y, 256 * id + z, ThaumicHorizons.blockVoid);
                }
            }
            data.portalD[0] = 0;
            data.portalD[1] = 0;
            data.portalD[2] = 0;
        }
    }

    public static void makePortal(int id, int which, int xCoord, int yCoord, int zCoord) {
        System.out.println("Creating portal " + which + " in plane " + id);
        PocketPlaneData data = planes.get(id);
        WorldServer world = MinecraftServer.func_71276_C().func_71218_a(ThaumicHorizons.dimensionPocketId);
        if (which == 1) {
            for (int x = -1; x <= 1; ++x) {
                for (int y = 126; y <= 128; ++y) {
                    world.func_147465_d(x, y, 256 * id + data.radius, ThaumicHorizons.blockPortal, 0, 3);
                    world.func_147449_b(x, y, 256 * id + data.radius + 1, ThaumicHorizons.blockVoid);
                }
            }
            data.portalA[0] = xCoord;
            data.portalA[1] = yCoord;
            data.portalA[2] = zCoord;
        } else if (which == 2) {
            for (int x = -1; x <= 1; ++x) {
                for (int y = 126; y <= 128; ++y) {
                    world.func_147465_d(x, y, 256 * id - data.radius, ThaumicHorizons.blockPortal, 2, 3);
                    world.func_147449_b(x, y, 256 * id - data.radius - 1, ThaumicHorizons.blockVoid);
                }
            }
            data.portalB[0] = xCoord;
            data.portalB[1] = yCoord;
            data.portalB[2] = zCoord;
        } else if (which == 3) {
            for (int z = -1; z <= 1; ++z) {
                for (int y = 126; y <= 128; ++y) {
                    world.func_147465_d(data.radius, y, 256 * id + z, ThaumicHorizons.blockPortal, 1, 3);
                    world.func_147449_b(data.radius + 1, y, 256 * id + z, ThaumicHorizons.blockVoid);
                }
            }
            data.portalC[0] = xCoord;
            data.portalC[1] = yCoord;
            data.portalC[2] = zCoord;
        } else if (which == 4) {
            for (int z = -1; z <= 1; ++z) {
                for (int y = 126; y <= 128; ++y) {
                    world.func_147465_d(-data.radius, y, 256 * id + z, ThaumicHorizons.blockPortal, 3, 3);
                    world.func_147449_b(-data.radius - 1, y, 256 * id + z, ThaumicHorizons.blockVoid);
                }
            }
            data.portalD[0] = xCoord;
            data.portalD[1] = yCoord;
            data.portalD[2] = zCoord;
        }
    }

    void buildStructure(int x, int y, int z, Structure struct) {
    }

    private class Structure {
        public int x;
        public int y;
        public int z;
        public Block[] blocks;
        public int[] meta;

        public Structure(int x, int y, int z, Block[] blocks, int[] meta) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.blocks = blocks;
            this.meta = meta;
        }
    }
}

