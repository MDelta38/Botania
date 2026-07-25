/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package com.kentington.thaumichorizons.common.lib;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.lib.ChunkProviderPocketPlane;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;

public class WorldProviderPocketPlane
extends WorldProvider {
    public void func_76572_b() {
        this.field_76578_c = new WorldChunkManagerHell(BiomeGenBase.field_76778_j, 0.0f);
        this.field_76574_g = ThaumicHorizons.dimensionPocketId;
        this.field_76576_e = true;
    }

    public IChunkProvider func_76555_c() {
        return new ChunkProviderPocketPlane(this.field_76579_a, this.field_76579_a.func_72905_C());
    }

    public int func_76557_i() {
        return 128;
    }

    public ChunkCoordinates func_76554_h() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_) {
        return Vec3.func_72443_a((double)0.02734375, (double)0.01171875, (double)0.16015625);
    }

    protected void func_76556_a() {
        float f = 0.3f;
        for (int i = 0; i <= 15; ++i) {
            float f1 = 1.0f - (float)i / 15.0f;
            this.field_76573_f[i] = (1.0f - f1) / (f1 * 3.0f + 1.0f) * (1.0f - f) + f;
        }
    }

    public boolean func_76569_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76561_g() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public float[] func_76560_a(float p_76560_1_, float p_76560_2_) {
        return null;
    }

    public String getWelcomeMessage() {
        return "Entering pocket plane...";
    }

    public String getDepartMessage() {
        return "Leaving pocket plane...";
    }

    public boolean shouldMapSpin(String entity, double x, double y, double z) {
        return true;
    }

    public boolean canBlockFreeze(int x, int y, int z, boolean byWater) {
        return false;
    }

    public boolean canSnowAt(int x, int y, int z, boolean checkLight) {
        return false;
    }

    public boolean canDoLightning(Chunk chunk) {
        return false;
    }

    public boolean canDoRainSnowIce(Chunk chunk) {
        return false;
    }

    public boolean func_76566_a(int p_76566_1_, int p_76566_2_) {
        return this.field_76579_a.func_147474_b(p_76566_1_, p_76566_2_).func_149688_o().func_76230_c();
    }

    public float func_76563_a(long p_76563_1_, float p_76563_3_) {
        return 0.5f;
    }

    public boolean func_76567_e() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76568_b(int p_76568_1_, int p_76568_2_) {
        return false;
    }

    public String func_80007_l() {
        return "Pocket Plane";
    }
}

