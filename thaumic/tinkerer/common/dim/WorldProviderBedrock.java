/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.util.ChunkCoordinates
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.biome.BiomeGenBase
 *  net.minecraft.world.biome.WorldChunkManagerHell
 *  net.minecraft.world.chunk.IChunkProvider
 */
package thaumic.tinkerer.common.dim;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import thaumic.tinkerer.common.core.handler.ConfigHandler;
import thaumic.tinkerer.common.dim.ChunkProviderBedrock;

public class WorldProviderBedrock
extends WorldProvider {
    private float[] colorsSunriseSunset = new float[4];

    public void func_76572_b() {
        this.field_76578_c = new WorldChunkManagerHell(BiomeGenBase.field_76787_r, (float)this.field_76574_g);
        this.field_76574_g = ConfigHandler.bedrockDimensionID;
        this.field_76576_e = false;
    }

    public IChunkProvider func_76555_c() {
        return new ChunkProviderBedrock(this.field_76579_a, this.field_76579_a.func_72905_C(), false);
    }

    public int func_76557_i() {
        return 1;
    }

    public boolean func_76568_b(int par1, int par2) {
        return false;
    }

    public String func_80007_l() {
        return "Bedrock";
    }

    public boolean renderStars() {
        return true;
    }

    public float getStarBrightness(World world, float f) {
        return 10.0f;
    }

    public boolean renderClouds() {
        return true;
    }

    public boolean renderVoidFog() {
        return false;
    }

    public boolean renderEndSky() {
        return false;
    }

    public float setSunSize() {
        return 10.0f;
    }

    public float setMoonSize() {
        return 8.0f;
    }

    public boolean func_76567_e() {
        return false;
    }

    public boolean func_76569_d() {
        return false;
    }

    public float func_76571_f() {
        return 0.0f;
    }

    public boolean func_76566_a(int par1, int par2) {
        return false;
    }

    public ChunkCoordinates func_76554_h() {
        return new ChunkCoordinates(50, 5, 0);
    }

    protected void func_76556_a() {
        float f = 12.0f;
        for (int i = 0; i <= 15; ++i) {
            float f1 = 12.0f - (float)i / 15.0f;
            this.field_76573_f[i] = (1.0f - f1) / (f1 * 3.0f + 1.0f) * (1.0f - f) + f;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public String getWelcomeMessage() {
        return null;
    }

    @SideOnly(value=Side.CLIENT)
    public float[] func_76560_a(float par1, float par2) {
        float f4;
        float f2 = 0.4f;
        float f3 = MathHelper.func_76134_b((float)(par1 * 3.141593f * 2.0f)) - 0.0f;
        if (f3 >= (f4 = -0.0f) - f2 && f3 <= f4 + f2) {
            float f5 = (f3 - f4) / f2 * 0.5f + 0.5f;
            float f6 = 1.0f - (1.0f - MathHelper.func_76126_a((float)(f5 * 3.141593f))) * 0.99f;
            f6 *= f6;
            this.colorsSunriseSunset[0] = f5 * 0.3f + 0.7f;
            this.colorsSunriseSunset[1] = f5 * f5 * 0.7f + 0.2f;
            this.colorsSunriseSunset[2] = f5 * f5 * 0.0f + 0.2f;
            this.colorsSunriseSunset[3] = f6;
            return this.colorsSunriseSunset;
        }
        return null;
    }

    public float func_76563_a(long par1, float par3) {
        int j = (int)(par1 % 24000L);
        float f1 = ((float)j + par3) / 24000.0f - 0.25f;
        if (f1 < 0.0f) {
            f1 += 1.0f;
        }
        if (f1 > 1.0f) {
            f1 -= 1.0f;
        }
        float f2 = f1;
        f1 = 1.0f - (float)((Math.cos((double)f1 * Math.PI) + 1.0) / 2.0);
        f1 = f2 + (f1 - f2) / 3.0f;
        return f1;
    }

    @SideOnly(value=Side.CLIENT)
    public Vec3 func_76562_b(float par1, float par2) {
        int i = 0xA080A0;
        float f2 = MathHelper.func_76134_b((float)(par1 * 3.141593f * 2.0f)) * 2.0f + 0.5f;
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
}

