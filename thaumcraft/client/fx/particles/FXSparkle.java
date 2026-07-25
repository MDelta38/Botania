/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.particles;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXSparkle
extends EntityFX {
    public boolean leyLineEffect = false;
    public int multiplier = 2;
    public boolean shrink = true;
    public int particle = 16;
    public boolean tinkle = false;
    public int blendmode = 1;
    public boolean slowdown = true;
    public int currentColor = 0;

    public FXSparkle(World world, double d, double d1, double d2, float f, float f1, float f2, float f3, int m) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        if (f1 == 0.0f) {
            f1 = 1.0f;
        }
        this.field_70552_h = f1;
        this.field_70553_i = f2;
        this.field_70551_j = f3;
        this.field_70545_g = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70544_f *= f;
        this.field_70547_e = 3 * m;
        this.multiplier = m;
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
    }

    public FXSparkle(World world, double d, double d1, double d2, float f, int type, int m) {
        this(world, d, d1, d2, f, 0.0f, 0.0f, 0.0f, m);
        this.currentColor = type;
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
                this.field_70552_h = 0.2f;
                this.field_70553_i = 0.5f + world.field_73012_v.nextFloat() * 0.3f;
                this.field_70551_j = 0.6f + world.field_73012_v.nextFloat() * 0.3f;
            }
        }
    }

    public FXSparkle(World world, double d, double d1, double d2, double x, double y, double z, float f, int type, int m) {
        this(world, d, d1, d2, f, type, m);
        double dx = x - this.field_70165_t;
        double dy = y - this.field_70163_u;
        double dz = z - this.field_70161_v;
        this.field_70159_w = dx / (double)this.field_70547_e;
        this.field_70181_x = dy / (double)this.field_70547_e;
        this.field_70179_y = dz / (double)this.field_70547_e;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        int part = this.particle + this.field_70546_d / this.multiplier;
        float var8 = (float)(part % 4) / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.25f;
        float var11 = var10 + 0.0624375f;
        float var12 = 0.1f * this.field_70544_f;
        if (this.shrink) {
            var12 *= (float)(this.field_70547_e - this.field_70546_d + 1) / (float)this.field_70547_e;
        }
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var16 = 1.0f;
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 1.0f);
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
    }

    public int func_70537_b() {
        return this.blendmode == 1 ? 0 : 1;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d == 0 && this.tinkle && this.field_70170_p.field_73012_v.nextInt(10) == 0) {
            this.field_70170_p.func_72956_a((Entity)this, "random.orb", 0.02f, 0.7f * ((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.6f + 2.0f));
        }
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
        this.field_70181_x -= 0.04 * (double)this.field_70545_g;
        if (!this.field_70145_X) {
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
        if (this.leyLineEffect) {
            FXSparkle fx = new FXSparkle(this.field_70170_p, this.field_70169_q + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1f), this.field_70167_r + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1f), this.field_70166_s + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1f), 1.0f, this.currentColor, 3 + this.field_70170_p.field_73012_v.nextInt(3));
            fx.field_70145_X = true;
            FMLClientHandler.instance().getClient().field_71452_i.func_78873_a((EntityFX)fx);
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
}

