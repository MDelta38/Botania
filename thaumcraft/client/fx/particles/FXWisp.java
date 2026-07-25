/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.particles;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXWisp
extends EntityFX {
    Entity target = null;
    public boolean shrink = false;
    float moteParticleScale;
    int moteHalfLife;
    public boolean tinkle = false;
    public int blendmode = 1;

    public FXWisp(World world, double d, double d1, double d2, float f, float f1, float f2) {
        this(world, d, d1, d2, 1.0f, f, f1, f2);
    }

    public FXWisp(World world, double d, double d1, double d2, float f, float red, float green, float blue) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        if (red == 0.0f) {
            red = 1.0f;
        }
        this.field_70552_h = red;
        this.field_70553_i = green;
        this.field_70551_j = blue;
        this.field_70545_g = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70544_f *= f;
        this.moteParticleScale = this.field_70544_f;
        this.field_70547_e = (int)(36.0 / (Math.random() * 0.3 + 0.7));
        this.moteHalfLife = this.field_70547_e / 2;
        this.field_70145_X = false;
        this.func_70105_a(0.1f, 0.1f);
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

    public FXWisp(World world, double d, double d1, double d2, float f, int type) {
        this(world, d, d1, d2, f, 0.0f, 0.0f, 0.0f);
        switch (type) {
            case 0: {
                this.field_70552_h = 0.75f + world.field_73012_v.nextFloat() * 0.25f;
                this.field_70553_i = 0.25f + world.field_73012_v.nextFloat() * 0.25f;
                this.field_70551_j = 0.75f + world.field_73012_v.nextFloat() * 0.25f;
                break;
            }
            case 1: {
                this.field_70552_h = 0.5f + world.field_73012_v.nextFloat() * 0.3f;
                this.field_70553_i = 0.5f + world.field_73012_v.nextFloat() * 0.3f;
                this.field_70551_j = 0.2f;
                break;
            }
            case 2: {
                this.field_70552_h = 0.2f;
                this.field_70553_i = 0.2f;
                this.field_70551_j = 0.7f + world.field_73012_v.nextFloat() * 0.3f;
                break;
            }
            case 3: {
                this.field_70552_h = 0.2f;
                this.field_70553_i = 0.7f + world.field_73012_v.nextFloat() * 0.3f;
                this.field_70551_j = 0.2f;
                break;
            }
            case 4: {
                this.field_70552_h = 0.7f + world.field_73012_v.nextFloat() * 0.3f;
                this.field_70553_i = 0.2f;
                this.field_70551_j = 0.2f;
                break;
            }
            case 5: {
                this.blendmode = 771;
                this.field_70552_h = world.field_73012_v.nextFloat() * 0.1f;
                this.field_70553_i = world.field_73012_v.nextFloat() * 0.1f;
                this.field_70551_j = world.field_73012_v.nextFloat() * 0.1f;
                break;
            }
            case 6: {
                this.field_70552_h = 0.8f + world.field_73012_v.nextFloat() * 0.2f;
                this.field_70553_i = 0.8f + world.field_73012_v.nextFloat() * 0.2f;
                this.field_70551_j = 0.8f + world.field_73012_v.nextFloat() * 0.2f;
                break;
            }
            case 7: {
                this.field_70552_h = 0.7f + world.field_73012_v.nextFloat() * 0.3f;
                this.field_70553_i = 0.5f + world.field_73012_v.nextFloat() * 0.2f;
                this.field_70551_j = 0.3f + world.field_73012_v.nextFloat() * 0.1f;
            }
        }
    }

    public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, int type) {
        this(world, d, d1, d2, f, type);
        if (this.field_70547_e > 0) {
            double dx = x - this.field_70165_t;
            double dy = y - this.field_70163_u;
            double dz = z - this.field_70161_v;
            this.field_70159_w = dx / (double)this.field_70547_e;
            this.field_70181_x = dy / (double)this.field_70547_e;
            this.field_70179_y = dz / (double)this.field_70547_e;
        }
    }

    public FXWisp(World world, double d, double d1, double d2, Entity tar, int type) {
        this(world, d, d1, d2, 0.4f, type);
        this.target = tar;
    }

    public FXWisp(World world, double d, double d1, double d2, double x, double y, double z, float f, float red, float green, float blue) {
        this(world, d, d1, d2, f, red, green, blue);
        if (this.field_70547_e > 0) {
            double dx = x - this.field_70165_t;
            double dy = y - this.field_70163_u;
            double dz = z - this.field_70161_v;
            this.field_70159_w = dx / (double)this.field_70547_e;
            this.field_70181_x = dy / (double)this.field_70547_e;
            this.field_70179_y = dz / (double)this.field_70547_e;
        }
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float agescale = 0.0f;
        if (this.shrink) {
            agescale = ((float)this.field_70547_e - (float)this.field_70546_d) / (float)this.field_70547_e;
        } else {
            agescale = (float)this.field_70546_d / (float)this.moteHalfLife;
            if (agescale > 1.0f) {
                agescale = 2.0f - agescale;
            }
        }
        this.field_70544_f = this.moteParticleScale * agescale;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        float f10 = 0.5f * this.field_70544_f;
        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var8 = 0.0f;
        float var9 = 0.125f;
        float var10 = 0.875f;
        float var11 = 1.0f;
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.5f);
        tessellator.func_78374_a((double)(f11 - f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 - f3 * f10 - f5 * f10), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(f11 - f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 - f3 * f10 + f5 * f10), (double)var9, (double)var10);
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
        if (this.field_70546_d == 0 && this.tinkle && this.field_70170_p.field_73012_v.nextInt(3) == 0) {
            this.field_70170_p.func_72956_a((Entity)this, "random.orb", 0.02f, 0.5f * ((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.6f + 2.0f));
        }
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
        this.field_70181_x -= 0.04 * (double)this.field_70545_g;
        if (!this.field_70145_X) {
            this.pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        if (this.target != null) {
            this.field_70159_w *= 0.985;
            this.field_70181_x *= 0.985;
            this.field_70179_y *= 0.985;
            double dx = this.target.field_70165_t - this.field_70165_t;
            double dy = this.target.field_70163_u + (double)(this.target.field_70131_O / 2.0f) - this.field_70163_u;
            double dz = this.target.field_70161_v - this.field_70161_v;
            double d13 = 0.2;
            double d11 = MathHelper.func_76133_a((double)(dx * dx + dy * dy + dz * dz));
            this.field_70159_w += (dx /= d11) * d13;
            this.field_70181_x += (dy /= d11) * d13;
            this.field_70179_y += (dz /= d11) * d13;
            this.field_70159_w = MathHelper.func_76131_a((float)((float)this.field_70159_w), (float)-0.2f, (float)0.2f);
            this.field_70181_x = MathHelper.func_76131_a((float)((float)this.field_70181_x), (float)-0.2f, (float)0.2f);
            this.field_70179_y = MathHelper.func_76131_a((float)((float)this.field_70179_y), (float)-0.2f, (float)0.2f);
        } else {
            this.field_70159_w *= (double)0.98f;
            this.field_70181_x *= (double)0.98f;
            this.field_70179_y *= (double)0.98f;
            if (this.field_70122_E) {
                this.field_70159_w *= (double)0.7f;
                this.field_70179_y *= (double)0.7f;
            }
        }
    }

    protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
        int var7 = MathHelper.func_76128_c((double)par1);
        int var8 = MathHelper.func_76128_c((double)par3);
        int var9 = MathHelper.func_76128_c((double)par5);
        double var10 = par1 - (double)var7;
        double var12 = par3 - (double)var8;
        double var14 = par5 - (double)var9;
        if (!this.field_70170_p.func_147437_c(var7, var8, var9) && this.field_70170_p.func_147445_c(var7, var8, var9, true) && !this.field_70170_p.func_72953_d(this.field_70121_D)) {
            boolean var16 = !this.field_70170_p.func_147445_c(var7 - 1, var8, var9, true);
            boolean var17 = !this.field_70170_p.func_147445_c(var7 + 1, var8, var9, true);
            boolean var18 = !this.field_70170_p.func_147445_c(var7, var8 - 1, var9, true);
            boolean var19 = !this.field_70170_p.func_147445_c(var7, var8 + 1, var9, true);
            boolean var20 = !this.field_70170_p.func_147445_c(var7, var8, var9 - 1, true);
            boolean var21 = !this.field_70170_p.func_147445_c(var7, var8, var9 + 1, true);
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

    public void setGravity(float value) {
        this.field_70545_g = value;
    }
}

