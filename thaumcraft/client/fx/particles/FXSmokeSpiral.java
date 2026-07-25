/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXSmokeSpiral
extends EntityFX {
    private float radius = 1.0f;
    private int start = 0;
    private int miny = 0;

    public FXSmokeSpiral(World world, double d, double d1, double d2, float radius, int start, int miny) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        this.field_70545_g = -0.01f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70544_f *= 1.0f;
        this.field_70547_e = 20 + world.field_73012_v.nextInt(10);
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.radius = radius;
        this.start = start;
        this.miny = miny;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.66f * this.field_82339_as));
        int particle = (int)(1.0f + (float)this.field_70546_d / (float)this.field_70547_e * 4.0f);
        float r1 = (float)this.start + 720.0f * (((float)this.field_70546_d + f) / (float)this.field_70547_e);
        float r2 = 90.0f - 180.0f * (((float)this.field_70546_d + f) / (float)this.field_70547_e);
        float mX = -MathHelper.func_76126_a((float)(r1 / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(r2 / 180.0f * (float)Math.PI));
        float mZ = MathHelper.func_76134_b((float)(r1 / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(r2 / 180.0f * (float)Math.PI));
        float mY = -MathHelper.func_76126_a((float)(r2 / 180.0f * (float)Math.PI));
        mX *= this.radius;
        mY *= this.radius;
        mZ *= this.radius;
        float var8 = (float)(particle % 16) / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = (float)(particle / 16) / 16.0f;
        float var11 = var10 + 0.0624375f;
        float var12 = 0.15f * this.field_70544_f;
        float var13 = (float)(this.field_70165_t + (double)mX - field_70556_an);
        float var14 = (float)(Math.max(this.field_70163_u + (double)mY, (double)((float)this.miny + 0.1f)) - field_70554_ao);
        float var15 = (float)(this.field_70161_v + (double)mZ - field_70555_ap);
        float var16 = 1.0f;
        tessellator.func_78380_c(this.func_70070_b(f));
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.66f * this.field_82339_as);
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
    }

    public int func_70537_b() {
        return 1;
    }

    public void func_70071_h_() {
        this.func_82338_g((float)(this.field_70547_e - this.field_70546_d) / (float)this.field_70547_e);
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
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

