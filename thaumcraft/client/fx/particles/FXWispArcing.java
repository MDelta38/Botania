/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.particles;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXWispArcing
extends EntityFX {
    private double field_70568_aq;
    private double field_70567_ar;
    private double field_70566_as;
    float moteParticleScale;
    int moteHalfLife;
    public boolean tinkle = false;
    public int blendmode = 1;

    public FXWispArcing(World world, double d, double d1, double d2, float f, float red, float green, float blue) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        if (red == 0.0f) {
            red = 1.0f;
        }
        this.field_70568_aq = this.field_70165_t = d;
        this.field_70567_ar = this.field_70163_u = d1;
        this.field_70566_as = this.field_70161_v = d2;
        this.field_70552_h = red;
        this.field_70553_i = green;
        this.field_70551_j = blue;
        this.field_70545_g = 0.0f;
        this.field_70544_f *= f;
        this.moteParticleScale = this.field_70544_f;
        this.field_70547_e = (int)(36.0 / (Math.random() * 0.3 + 0.7));
        this.moteHalfLife = this.field_70547_e / 2;
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
        int visibleDistance = 50;
        if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) {
            visibleDistance = 25;
        }
        if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > (double)visibleDistance) {
            this.field_70547_e = 0;
        }
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
    }

    public FXWispArcing(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue) {
        this(world, d, d1, d2, f, red, green, blue);
        this.field_70159_w = x - d;
        this.field_70181_x = y - d1;
        this.field_70179_y = z - d2;
        this.func_70012_b(x, y, z, 0.0f, 0.0f);
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float agescale = 0.0f;
        agescale = (float)this.field_70546_d / (float)this.moteHalfLife;
        if (agescale > 1.0f) {
            agescale = 2.0f - agescale;
        }
        this.field_70544_f = this.moteParticleScale * agescale;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        float f10 = 0.5f * this.field_70544_f;
        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var8 = 0.0f;
        float var9 = 0.0f;
        float var10 = 0.875f;
        float var11 = 1.0f;
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5f);
        tessellator.func_78374_a((double)(f11 - f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 - f3 * f10 - f5 * f10), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(f11 - f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 - f3 * f10 + f5 * f10), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(f11 + f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 + f3 * f10 + f5 * f10), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(f11 + f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 + f3 * f10 - f5 * f10), (double)var8, (double)var11);
    }

    public int func_70537_b() {
        return this.blendmode == 1 ? 0 : 1;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        float var1 = (float)this.field_70546_d / (float)this.field_70547_e;
        float var2 = (float)this.field_70546_d / ((float)this.field_70547_e / 2.0f);
        var1 = 1.0f - var1;
        var2 = 1.0f - var2;
        var2 *= var2;
        this.field_70165_t = this.field_70568_aq + this.field_70159_w * (double)var1;
        this.field_70163_u = this.field_70567_ar + this.field_70181_x * (double)var1 - (double)var2 + 1.0;
        this.field_70161_v = this.field_70566_as + this.field_70179_y * (double)var1;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
    }

    public void setGravity(float value) {
        this.field_70545_g = value;
    }
}

