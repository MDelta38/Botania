/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package com.emoniph.witchery.worldgen;

import com.emoniph.witchery.util.Log;
import java.util.ArrayList;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeManager {
    public static final ArrayList DISALLOWED_BIOMES = new ArrayList();

    private BiomeManager() {
    }

    public static void addModBiomes() {
        DISALLOWED_BIOMES.clear();
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (BiomeGenBase biome : BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.SWAMP)) {
            if (list.contains(biome.field_76756_M)) continue;
            list.add(biome.field_76756_M);
        }
        for (BiomeGenBase biome : BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.RIVER)) {
            if (list.contains(biome.field_76756_M)) continue;
            list.add(biome.field_76756_M);
        }
        for (BiomeGenBase biome : BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.OCEAN)) {
            if (list.contains(biome.field_76756_M)) continue;
            list.add(biome.field_76756_M);
        }
        for (BiomeGenBase biome : BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.MOUNTAIN)) {
            if (list.contains(biome.field_76756_M)) continue;
            list.add(biome.field_76756_M);
        }
        for (BiomeGenBase biome : BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.JUNGLE)) {
            if (list.contains(biome.field_76756_M)) continue;
            list.add(biome.field_76756_M);
        }
        for (BiomeGenBase biome : BiomeDictionary.getBiomesForType((BiomeDictionary.Type)BiomeDictionary.Type.BEACH)) {
            if (list.contains(biome.field_76756_M)) continue;
            list.add(biome.field_76756_M);
        }
        Log.instance().debug("Found " + list.size() + " biomes to ignore for world gen.");
        if (list.size() > 0) {
            DISALLOWED_BIOMES.addAll(list);
        }
    }

    public static BiomeGenBase[] biomesWithout(BiomeDictionary.Type ... biomesWithout) {
        ArrayList<BiomeGenBase> biomes = new ArrayList<BiomeGenBase>();
        for (BiomeGenBase biome : BiomeGenBase.func_150565_n()) {
            if (biome == null) continue;
            boolean skip = false;
            for (int i = 0; i < biomesWithout.length; ++i) {
                if (!BiomeDictionary.isBiomeOfType((BiomeGenBase)biome, (BiomeDictionary.Type)biomesWithout[i])) continue;
                skip = true;
                break;
            }
            if (skip) continue;
            biomes.add(biome);
        }
        return biomes.toArray(new BiomeGenBase[biomes.size()]);
    }
}

