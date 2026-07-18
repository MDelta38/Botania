/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldType
 *  net.minecraft.world.chunk.IChunkProvider
 *  net.minecraft.world.gen.ChunkProviderFlat
 */
package vazkii.botania.common.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderFlat;

public class WorldTypeSkyblock
extends WorldType {
    public WorldTypeSkyblock() {
        super("botania-skyblock");
    }

    public static boolean isWorldSkyblock(World world) {
        return world.func_72912_H().func_76067_t() instanceof WorldTypeSkyblock;
    }

    public boolean func_151357_h() {
        return true;
    }

    public boolean hasVoidParticles(boolean flag) {
        return false;
    }

    public int getMinimumSpawnHeight(World world) {
        return 86;
    }

    public int getSpawnFuzz() {
        return 1;
    }

    public float getCloudHeight() {
        return 260.0f;
    }

    public IChunkProvider getChunkGenerator(World world, String generatorOptions) {
        return new ChunkProviderFlat(world, world.func_72905_C(), false, "2;1x0;");
    }
}

