/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.Minecraft
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
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXWispEG
extends EntityFX {
    Entity target = null;
    double rx = 0.0;
    double ry = 0.0;
    double rz = 0.0;
    public int blendmode = 1;

    public FXWispEG(World worldObj, double posX, double posY, double posZ, Entity target2) {
        super(worldObj, posX, posY, posZ, 0.0, 0.0, 0.0);
        this.target = target2;
        this.field_70159_w = this.field_70146_Z.nextGaussian() * 0.03;
        this.field_70181_x = -0.05;
        this.field_70179_y = this.field_70146_Z.nextGaussian() * 0.03;
        this.field_70544_f *= 0.4f;
        this.field_70547_e = (int)(40.0 / (Math.random() * 0.3 + 0.7));
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
        int visibleDistance = 50;
        if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) {
            visibleDistance = 25;
        }
        if (renderentity.func_70011_f(posX, posY, posZ) > (double)visibleDistance) {
            this.field_70547_e = 0;
        }
        this.field_70169_q = posX;
        this.field_70167_r = posY;
        this.field_70166_s = posZ;
        this.blendmode = 771;
        this.field_70552_h = this.field_70146_Z.nextFloat() * 0.05f;
        this.field_70553_i = this.field_70146_Z.nextFloat() * 0.05f;
        this.field_70551_j = this.field_70146_Z.nextFloat() * 0.05f;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        EntityLivingBase e = Minecraft.func_71410_x().field_71451_h;
        float agescale = 1.0f - (float)this.field_70546_d / (float)this.field_70547_e;
        float d6 = 1024.0f;
        float base = (float)(1.0 - Math.min((double)d6, this.func_70092_e(e.field_70165_t, e.field_70163_u, e.field_70161_v)) / (double)d6);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.75f * base));
        float f10 = 0.5f * this.field_70544_f;
        float f11 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float f12 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float f13 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var8 = (float)(this.field_70546_d % 13) / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.1875f;
        float var11 = var10 + 0.0624375f;
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.2f * agescale * base);
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
        if (this.target != null && !this.field_70122_E) {
            this.field_70165_t += this.target.field_70159_w;
            this.field_70161_v += this.target.field_70179_y;
        }
        this.pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= (double)0.98f;
        this.field_70181_x *= (double)0.98f;
        this.field_70179_y *= (double)0.98f;
        if (this.field_70122_E) {
            this.field_70159_w *= 0.8500000190734863;
            this.field_70179_y *= 0.8500000190734863;
        }
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

