/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.fx;

import cpw.mods.fml.client.FMLClientHandler;
import java.util.ArrayDeque;
import java.util.Queue;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import vazkii.botania.client.fx.ParticleRenderDispatcher;
import vazkii.botania.common.core.handler.ConfigHandler;
import vazkii.botania.common.core.helper.ObfuscationHelper;

public class FXWisp
extends EntityFX {
    public static final ResourceLocation particles = new ResourceLocation("botania:textures/misc/wispLarge.png");
    public static Queue<FXWisp> queuedRenders = new ArrayDeque<FXWisp>();
    public static Queue<FXWisp> queuedDepthIgnoringRenders = new ArrayDeque<FXWisp>();
    float f;
    float f1;
    float f2;
    float f3;
    float f4;
    float f5;
    boolean depthTest = true;
    public boolean distanceLimit = true;
    float moteParticleScale;
    int moteHalfLife;
    public boolean tinkle = false;
    public int blendmode = 1;

    public FXWisp(World world, double d, double d1, double d2, float size, float red, float green, float blue, boolean distanceLimit, boolean depthTest, float maxAgeMul) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        this.field_70552_h = red;
        this.field_70553_i = green;
        this.field_70551_j = blue;
        this.field_70545_g = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70544_f *= size;
        this.moteParticleScale = this.field_70544_f;
        this.field_70547_e = (int)(28.0 / (Math.random() * 0.3 + 0.7) * (double)maxAgeMul);
        this.depthTest = depthTest;
        this.moteHalfLife = this.field_70547_e / 2;
        this.field_70145_X = true;
        this.func_70105_a(0.01f, 0.01f);
        EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
        if (distanceLimit) {
            int visibleDistance = 50;
            if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) {
                visibleDistance = 25;
            }
            if (renderentity == null || renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > (double)visibleDistance) {
                this.field_70547_e = 0;
            }
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
    }

    public static void dispatchQueuedRenders(Tessellator tessellator) {
        ParticleRenderDispatcher.wispFxCount = 0;
        ParticleRenderDispatcher.depthIgnoringWispFxCount = 0;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(ConfigHandler.matrixMode ? ObfuscationHelper.getParticleTexture() : particles);
        if (!queuedRenders.isEmpty()) {
            tessellator.func_78382_b();
            for (FXWisp wisp : queuedRenders) {
                wisp.renderQueued(tessellator, true);
            }
            tessellator.func_78381_a();
        }
        if (!queuedDepthIgnoringRenders.isEmpty()) {
            GL11.glDisable((int)2929);
            tessellator.func_78382_b();
            for (FXWisp wisp : queuedDepthIgnoringRenders) {
                wisp.renderQueued(tessellator, false);
            }
            tessellator.func_78381_a();
            GL11.glEnable((int)2929);
        }
        queuedRenders.clear();
        queuedDepthIgnoringRenders.clear();
    }

    private void renderQueued(Tessellator tessellator, boolean depthEnabled) {
        if (depthEnabled) {
            ++ParticleRenderDispatcher.wispFxCount;
        } else {
            ++ParticleRenderDispatcher.depthIgnoringWispFxCount;
        }
        float agescale = 0.0f;
        agescale = (float)this.field_70546_d / (float)this.moteHalfLife;
        if (agescale > 1.0f) {
            agescale = 2.0f - agescale;
        }
        this.field_70544_f = this.moteParticleScale * agescale;
        float f10 = 0.5f * this.field_70544_f;
        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)this.f - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)this.f - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)this.f - field_70555_ap);
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5f);
        tessellator.func_78374_a((double)(f11 - this.f1 * f10 - this.f4 * f10), (double)(f12 - this.f2 * f10), (double)(f13 - this.f3 * f10 - this.f5 * f10), 0.0, 1.0);
        tessellator.func_78374_a((double)(f11 - this.f1 * f10 + this.f4 * f10), (double)(f12 + this.f2 * f10), (double)(f13 - this.f3 * f10 + this.f5 * f10), 1.0, 1.0);
        tessellator.func_78374_a((double)(f11 + this.f1 * f10 + this.f4 * f10), (double)(f12 + this.f2 * f10), (double)(f13 + this.f3 * f10 + this.f5 * f10), 1.0, 0.0);
        tessellator.func_78374_a((double)(f11 + this.f1 * f10 - this.f4 * f10), (double)(f12 - this.f2 * f10), (double)(f13 + this.f3 * f10 - this.f5 * f10), 0.0, 0.0);
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        this.f = f;
        this.f1 = f1;
        this.f2 = f2;
        this.f3 = f3;
        this.f4 = f4;
        this.f5 = f5;
        if (this.depthTest) {
            queuedRenders.add(this);
        } else {
            queuedDepthIgnoringRenders.add(this);
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
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        this.field_70159_w *= (double)0.98f;
        this.field_70181_x *= (double)0.98f;
        this.field_70179_y *= (double)0.98f;
    }

    public void setGravity(float value) {
        this.field_70545_g = value;
    }
}

