/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.emoniph.witchery.dimension;

import com.emoniph.witchery.dimension.WorldChunkManagerMirror;
import com.emoniph.witchery.util.BlockUtil;
import com.emoniph.witchery.util.Config;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderMirror
extends WorldProvider {
    public WorldProviderMirror() {
        this.field_76576_e = true;
    }

    public int getActualHeight() {
        return Config.instance().shrinkMirrorWorld ? this.getHeight() : super.getActualHeight();
    }

    public String func_80007_l() {
        return "Mirror";
    }

    public IChunkProvider func_76555_c() {
        return new WorldChunkManagerMirror(this.field_76579_a);
    }

    public boolean func_76567_e() {
        return false;
    }

    public boolean func_76569_d() {
        return false;
    }

    public boolean canDoLightning(Chunk chunk) {
        return false;
    }

    public boolean isBlockHighHumidity(int x, int y, int z) {
        return false;
    }

    public boolean isDaytime() {
        return false;
    }

    public ChunkCoordinates getSpawnPoint() {
        return new ChunkCoordinates(4, 9, 4);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76561_g() {
        return false;
    }

    protected void func_76556_a() {
        float f = 0.1f;
        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0f - (float)i / 15.0f;
            this.field_76573_f[i] = (1.0f - f1) / (f1 * 3.0f + 1.0f) * (1.0f - f) + f;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_) {
        return Vec3.func_72443_a((double)0.0, (double)0.03, (double)0.1);
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76568_b(int p_76568_1_, int p_76568_2_) {
        return true;
    }

    public float func_76563_a(long par1, float par3) {
        return 0.5f;
    }

    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks) {
        return super.getSkyColor(cameraEntity, partialTicks);
    }

    private static boolean isSafeBlock(World world, int posX, int posY, int posZ) {
        boolean base = BlockUtil.isSolid(world, posX, posY, posZ);
        boolean air1 = !BlockUtil.isSolid(world, posX, posY + 1, posZ);
        boolean air2 = !BlockUtil.isSolid(world, posX, posY + 2, posZ);
        boolean isSafe = base && air1 && air2;
        return isSafe;
    }

    public float getSunBrightnessFactor(float par1) {
        return 0.0f;
    }

    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }
}

