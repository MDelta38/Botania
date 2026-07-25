/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.chunk.IChunkProvider
 */
package thaumcraft.common.lib.world.dim;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.IChunkProvider;
import thaumcraft.common.config.Config;
import thaumcraft.common.lib.world.ThaumcraftWorldGenerator;
import thaumcraft.common.lib.world.dim.ChunkProviderOuter;

public class WorldProviderOuter
extends WorldProvider {
    public String func_80007_l() {
        return "The Outer Lands";
    }

    public String getWelcomeMessage() {
        return "Entering The Outer Lands";
    }

    public String getDepartMessage() {
        return "Leaving The Outer Lands";
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

    public void func_76572_b() {
        this.field_76578_c = new WorldChunkManagerHell(ThaumcraftWorldGenerator.biomeEldritchLands, 0.0f);
        this.field_76574_g = Config.dimensionOuterId;
        this.field_76576_e = true;
    }

    public IChunkProvider func_76555_c() {
        return new ChunkProviderOuter(this.field_76579_a, this.field_76579_a.func_72905_C(), true);
    }

    public float func_76563_a(long p_76563_1_, float p_76563_3_) {
        return 0.0f;
    }

    @SideOnly(value=Side.CLIENT)
    public float[] func_76560_a(float p_76560_1_, float p_76560_2_) {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 func_76562_b(float p_76562_1_, float p_76562_2_) {
        int i = 0xA080A0;
        float f2 = MathHelper.func_76134_b((float)(p_76562_1_ * (float)Math.PI * 2.0f)) * 2.0f + 0.5f;
        if (f2 < 0.0f) {
            f2 = 0.0f;
        }
        if (f2 > 1.0f) {
            f2 = 1.0f;
        }
        float f3 = (float)(i >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(i >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(i & 0xFF) / 255.0f;
        return Vec3.func_72443_a((double)(f3 *= f2 * 0.0f + 0.15f), (double)(f4 *= f2 * 0.0f + 0.15f), (double)(f5 *= f2 * 0.0f + 0.15f));
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76561_g() {
        return false;
    }

    public boolean func_76567_e() {
        return false;
    }

    public boolean func_76569_d() {
        return false;
    }

    @SideOnly(value=Side.CLIENT)
    public float func_76571_f() {
        return 1.0f;
    }

    public boolean func_76566_a(int p_76566_1_, int p_76566_2_) {
        return this.field_76579_a.func_147474_b(p_76566_1_, p_76566_2_).func_149688_o().func_76230_c();
    }

    public ChunkCoordinates func_76554_h() {
        return null;
    }

    public int func_76557_i() {
        return 50;
    }

    @SideOnly(value=Side.CLIENT)
    public boolean func_76568_b(int p_76568_1_, int p_76568_2_) {
        return true;
    }
}

