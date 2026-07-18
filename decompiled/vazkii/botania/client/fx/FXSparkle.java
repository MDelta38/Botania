/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.fx;

import java.util.ArrayDeque;
import java.util.Queue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.core.helper.ShaderHelper;
import vazkii.botania.client.fx.ParticleRenderDispatcher;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ObfuscationHelper;

public class FXSparkle
extends EntityFX {
    public static final ResourceLocation particles = new ResourceLocation("botania:textures/misc/particles.png");
    public static Queue<FXSparkle> queuedRenders = new ArrayDeque<FXSparkle>();
    public static Queue<FXSparkle> queuedCorruptRenders = new ArrayDeque<FXSparkle>();
    float f;
    float f1;
    float f2;
    float f3;
    float f4;
    float f5;
    public boolean corrupt = false;
    public boolean fake = false;
    public int multiplier = 2;
    public boolean shrink = true;
    public int particle = 16;
    public boolean tinkle = false;
    public boolean slowdown = true;
    public int currentColor = 0;

    public FXSparkle(World world, double x, double y, double z, float size, float red, float green, float blue, int m) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.field_70552_h = red;
        this.field_70553_i = green;
        this.field_70551_j = blue;
        this.field_70545_g = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70544_f *= size;
        this.field_70547_e = 3 * m;
        this.multiplier = m;
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
    }

    public static void dispatchQueuedRenders(Tessellator tessellator) {
        ParticleRenderDispatcher.sparkleFxCount = 0;
        ParticleRenderDispatcher.fakeSparkleFxCount = 0;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(ConfigHandler.matrixMode ? ObfuscationHelper.getParticleTexture() : particles);
        tessellator.func_78382_b();
        for (FXSparkle sparkle : queuedRenders) {
            sparkle.renderQueued(tessellator);
        }
        tessellator.func_78381_a();
        ShaderHelper.useShader(ShaderHelper.filmGrain);
        tessellator.func_78382_b();
        for (FXSparkle sparkle : queuedCorruptRenders) {
            sparkle.renderQueued(tessellator);
        }
        tessellator.func_78381_a();
        ShaderHelper.releaseShader();
        queuedRenders.clear();
        queuedCorruptRenders.clear();
    }

    private void renderQueued(Tessellator tessellator) {
        if (this.fake) {
            ++ParticleRenderDispatcher.fakeSparkleFxCount;
        } else {
            ++ParticleRenderDispatcher.sparkleFxCount;
        }
        int part = this.particle + this.field_70546_d / this.multiplier;
        float var8 = (float)(part % 8) / 8.0f;
        float var9 = var8 + 0.124875f;
        float var10 = (float)(part / 8) / 8.0f;
        float var11 = var10 + 0.124875f;
        float var12 = 0.1f * this.field_70544_f;
        if (this.shrink) {
            var12 *= (float)(this.field_70547_e - this.field_70546_d + 1) / (float)this.field_70547_e;
        }
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)this.f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)this.f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)this.f - field_70555_ap);
        float var16 = 1.0f;
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0f);
        tessellator.func_78374_a((double)(var13 - this.f1 * var12 - this.f4 * var12), (double)(var14 - this.f2 * var12), (double)(var15 - this.f3 * var12 - this.f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - this.f1 * var12 + this.f4 * var12), (double)(var14 + this.f2 * var12), (double)(var15 - this.f3 * var12 + this.f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + this.f1 * var12 + this.f4 * var12), (double)(var14 + this.f2 * var12), (double)(var15 + this.f3 * var12 + this.f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + this.f1 * var12 - this.f4 * var12), (double)(var14 - this.f2 * var12), (double)(var15 + this.f3 * var12 - this.f5 * var12), (double)var8, (double)var11);
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        this.f = f;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
        this.f5 = f5;
        if (this.corrupt) {
            queuedCorruptRenders.add(this);
        } else {
            queuedRenders.add(this);
        }
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
        this.field_70181_x -= 0.04 * (double)this.field_70545_g;
        if (!this.field_70145_X && !this.fake) {
            this.pushOutOfBlocks(this.field_70165_t, (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0, this.field_70161_v);
        }
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        if (this.slowdown) {
            this.field_70159_w *= 0.9080000019073486;
            this.field_70181_x *= 0.9080000019073486;
            this.field_70179_y *= 0.9080000019073486;
            if (this.field_70122_E) {
                this.field_70159_w *= (double)0.7f;
                this.field_70179_y *= (double)0.7f;
            }
        }
        if (this.fake && this.field_70546_d > 1) {
            this.func_70106_y();
        }
    }

    public void setGravity(float value) {
        this.field_70545_g = value;
    }

    protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
        int var7 = MathHelper.func_76128_c((double)par1);
        int var8 = MathHelper.func_76128_c((double)par3);
        int var9 = MathHelper.func_76128_c((double)par5);
        double var10 = par1 - (double)var7;
        double var12 = par3 - (double)var8;
        double var14 = par5 - (double)var9;
        if (!this.field_70170_p.func_147437_c(var7, var8, var9)) {
            boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, false);
            boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, false);
            boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, false);
            boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, false);
            boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, false);
            boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, false);
            int var22 = -1;
            double var23 = 9999.0;
            if (var16 && var10 < var23) {
                var23 = var10;
                var22 = 0;
            }
            if (var17 && 1.0 - var10 < var23) {
                var23 = 1.0 - var10;
                var22 = 1;
            }
            if (var18 && var12 < var23) {
                var23 = var12;
                var22 = 2;
            }
            if (var19 && 1.0 - var12 < var23) {
                var23 = 1.0 - var12;
                var22 = 3;
            }
            if (var20 && var14 < var23) {
                var23 = var14;
                var22 = 4;
            }
            if (var21 && 1.0 - var14 < var23) {
                var23 = 1.0 - var14;
                var22 = 5;
            }
            float var25 = this.field_70146_Z.nextFloat() * 0.05f + 0.025f;
            float var26 = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.1f;
            if (var22 == 0) {
                this.field_70159_w = -var25;
                this.field_70181_x = this.field_70179_y = (double)var26;
            }
            if (var22 == 1) {
                this.field_70159_w = var25;
                this.field_70181_x = this.field_70179_y = (double)var26;
            }
            if (var22 == 2) {
                this.field_70181_x = -var25;
                this.field_70159_w = this.field_70179_y = (double)var26;
            }
            if (var22 == 3) {
                this.field_70181_x = var25;
                this.field_70159_w = this.field_70179_y = (double)var26;
            }
            if (var22 == 4) {
                this.field_70179_y = -var25;
                this.field_70181_x = this.field_70159_w = (double)var26;
            }
            if (var22 == 5) {
                this.field_70179_y = var25;
                this.field_70181_x = this.field_70159_w = (double)var26;
            }
            return true;
        }
        return false;
    }
}

