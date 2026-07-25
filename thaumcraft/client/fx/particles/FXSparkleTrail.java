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

public class FXSparkleTrail
extends EntityFX {
    private Entity target;
    public int particle = 24;

    public FXSparkleTrail(World par1World, double x, double y, double z, Entity target, float r, float g, float b) {
        super(par1World, x, y, z, 0.0, 0.0, 0.0);
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
        this.field_70544_f = this.field_70146_Z.nextFloat() * 0.5f + 0.5f;
        this.target = target;
        double dx = target.field_70165_t - this.field_70165_t;
        double dy = target.field_70163_u - this.field_70163_u;
        double dz = target.field_70161_v - this.field_70161_v;
        int base = (int)(MathHelper.func_76133_a((double)(dx * dx + dy * dy + dz * dz)) * 3.0f);
        if (base < 1) {
            base = 1;
        }
        this.field_70547_e = base / 2 + this.field_70146_Z.nextInt(base);
        float f3 = 0.1f;
        this.field_70159_w = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3;
        this.field_70181_x = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3;
        this.field_70179_y = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3;
        this.field_70545_g = 0.2f;
        this.field_70145_X = false;
        EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
        int visibleDistance = 64;
        if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) {
            visibleDistance = 32;
        }
        if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > (double)visibleDistance) {
            this.field_70547_e = 0;
        }
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float bob = MathHelper.func_76126_a((float)((float)this.field_70546_d / 3.0f)) * 0.5f + 1.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        int part = this.particle + this.field_70546_d % 16;
        float var8 = (float)(part % 8) / 8.0f;
        float var9 = var8 + 0.124875f;
        float var10 = (float)(part / 8) / 8.0f;
        float var11 = var10 + 0.124875f;
        float var12 = 0.1f * this.field_70544_f * bob;
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

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e || this.func_70068_e(this.target) < 1.0) {
            this.func_70106_y();
            return;
        }
        if (!this.field_70145_X) {
            this.pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        }
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.985;
        this.field_70181_x *= 0.985;
        this.field_70179_y *= 0.985;
        double dx = this.target.field_70165_t - this.field_70165_t;
        double dy = this.target.field_70163_u - this.field_70163_u;
        double dz = this.target.field_70161_v - this.field_70161_v;
        double d13 = 0.3;
        double d11 = MathHelper.func_76133_a((double)(dx * dx + dy * dy + dz * dz));
        if (d11 < 4.0) {
            this.field_70544_f *= 0.9f;
            d13 = 0.6;
        }
        this.field_70159_w += (dx /= d11) * d13;
        this.field_70181_x += (dy /= d11) * d13;
        this.field_70179_y += (dz /= d11) * d13;
        this.field_70159_w = MathHelper.func_76131_a((float)((float)this.field_70159_w), (float)-0.35f, (float)0.35f);
        this.field_70181_x = MathHelper.func_76131_a((float)((float)this.field_70181_x), (float)-0.35f, (float)0.35f);
        this.field_70179_y = MathHelper.func_76131_a((float)((float)this.field_70179_y), (float)-0.35f, (float)0.35f);
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
        if (!this.field_70170_p.func_147437_c(var7, var8, var9) && !this.field_70170_p.func_72953_d(this.field_70121_D)) {
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

