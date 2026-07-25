/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraftforge.common.BiomeDictionary
 *  net.minecraftforge.common.BiomeDictionary$Type
 */
package thaumcraft.common.lib.world.biomes;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import thaumcraft.api.aspects.Aspect;

public class BiomeHandler {
    public static HashMap<BiomeDictionary.Type, List> biomeInfo = new HashMap();

    public static void registerBiomeInfo(BiomeDictionary.Type type, int auraLevel, Aspect tag, boolean greatwood, float greatwoodchance) {
        biomeInfo.put(type, Arrays.asList(auraLevel, tag, greatwood, Float.valueOf(greatwoodchance)));
    }

    public static int getBiomeAura(BiomeGenBase biome) {
        try {
            BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome((BiomeGenBase)biome);
            int average = 0;
            int count = 0;
            for (BiomeDictionary.Type type : types) {
                average += ((Integer)biomeInfo.get(type).get(0)).intValue();
                ++count;
            }
            return average / count;
        }
        catch (Exception exception) {
            return 100;
        }
    }

    public static Aspect getRandomBiomeTag(int biomeId, Random random) {
        try {
            BiomeDictionary.Type[] types = BiomeDictionary.getTypesForBiome((BiomeGenBase)BiomeGenBase.func_150568_d((int)biomeId));
            BiomeDictionary.Type type = types[random.nextInt(types.length)];
            return (Aspect)biomeInfo.get(type).get(1);
        }
        catch (Exception exception) {
            return null;
        }
    }

    public static float getBiomeSupportsGreatwood(int biomeId) {
        try {
            BiomeDictionary.Type[] types;
            for (BiomeDictionary.Type type : types = BiomeDictionary.getTypesForBiome((BiomeGenBase)BiomeGenBase.func_150568_d((int)biomeId))) {
                if (!((Boolean)biomeInfo.get(type).get(2)).booleanValue()) continue;
                return ((Float)biomeInfo.get(type).get(3)).floatValue();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        return 0.0f;
    }
}

