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
import java.util.ArrayList;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.common.config.ConfigBlocks;

public class FXSwarm
extends EntityFX {
    private Entity target;
    private float turnSpeed = 10.0f;
    private float speed = 0.2f;
    int deathtimer = 0;
    private static ArrayList<Long> buzzcount = new ArrayList();
    public int particle = 40;

    public FXSwarm(World par1World, double x, double y, double z, Entity target, float r, float g, float b) {
        super(par1World, x, y, z, 0.0, 0.0, 0.0);
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
        this.field_70544_f = this.field_70146_Z.nextFloat() * 0.5f + 1.0f;
        this.target = target;
        float f3 = 0.2f;
        this.field_70159_w = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3;
        this.field_70181_x = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3;
        this.field_70179_y = (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * f3;
        this.field_70545_g = 0.1f;
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

    public FXSwarm(World par1World, double x, double y, double z, Entity target, float r, float g, float b, float sp, float ts, float pg) {
        this(par1World, x, y, z, target, r, g, b);
        this.speed = sp;
        this.turnSpeed = ts;
        this.field_70545_g = pg;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float bob = MathHelper.func_76126_a((float)((float)this.field_70546_d / 3.0f)) * 0.25f + 1.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        int part = 7 + this.field_70546_d % 8;
        float var8 = (float)part / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.25f;
        float var11 = var10 + 0.0624375f;
        float var12 = 0.1f * this.field_70544_f * bob;
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var16 = 1.0f;
        float trans = (50.0f - (float)this.deathtimer) / 50.0f;
        tessellator.func_78380_c(240);
        if (this.target instanceof EntityLivingBase && ((EntityLivingBase)this.target).field_70737_aN <= 0) {
            tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, trans);
        } else {
            tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16 / 2.0f, this.field_70551_j * var16 / 2.0f, trans);
        }
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
    }

    public int func_70537_b() {
        return 1;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        ++this.field_70546_d;
        if (this.target == null || this.target.field_70128_L || this.target instanceof EntityLivingBase && ((EntityLivingBase)this.target).field_70725_aQ > 0) {
            ++this.deathtimer;
            this.field_70181_x -= (double)(this.field_70545_g / 2.0f);
            if (this.deathtimer > 50) {
                this.func_70106_y();
            }
        } else {
            this.field_70181_x += (double)this.field_70545_g;
        }
        this.pushOutOfBlocks(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.985;
        this.field_70181_x *= 0.985;
        this.field_70179_y *= 0.985;
        if (!(this.target == null || this.target.field_70128_L || this.target instanceof EntityLivingBase && ((EntityLivingBase)this.target).field_70725_aQ > 0)) {
            boolean hurt = false;
            if (this.target instanceof EntityLivingBase) {
                boolean bl = hurt = ((EntityLivingBase)this.target).field_70737_aN > 0;
            }
            if (this.func_70068_e(this.target) > (double)this.target.field_70130_N && !hurt) {
                this.faceEntity(this.target, this.turnSpeed / 2.0f + (float)this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0f)), this.turnSpeed / 2.0f + (float)this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0f)));
            } else {
                this.faceEntity(this.target, -(this.turnSpeed / 2.0f + (float)this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0f))), -(this.turnSpeed / 2.0f + (float)this.field_70146_Z.nextInt((int)(this.turnSpeed / 2.0f))));
            }
            this.field_70159_w = -MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.field_70179_y = MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.field_70181_x = -MathHelper.func_76126_a((float)(this.field_70125_A / 180.0f * (float)Math.PI));
            this.setHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.speed, 15.0f);
        }
        if (buzzcount.size() < 3 && this.field_70146_Z.nextInt(50) == 0 && this.field_70170_p.func_72890_a((Entity)this, 8.0) != null) {
            this.field_70170_p.func_72980_b(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:fly", 0.03f, 0.5f + this.field_70146_Z.nextFloat() * 0.4f, false);
            buzzcount.add(System.nanoTime() + 1500000L);
        }
        if (buzzcount.size() >= 3 && buzzcount.get(0) < System.nanoTime()) {
            buzzcount.remove(0);
        }
    }

    public void faceEntity(Entity par1Entity, float par2, float par3) {
        double d0 = par1Entity.field_70165_t - this.field_70165_t;
        double d1 = par1Entity.field_70161_v - this.field_70161_v;
        double d2 = (par1Entity.field_70121_D.field_72338_b + par1Entity.field_70121_D.field_72337_e) / 2.0 - (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0;
        double d3 = MathHelper.func_76133_a((double)(d0 * d0 + d1 * d1));
        float f2 = (float)(Math.atan2(d1, d0) * 180.0 / Math.PI) - 90.0f;
        float f3 = (float)(-(Math.atan2(d2, d3) * 180.0 / Math.PI));
        this.field_70125_A = this.updateRotation(this.field_70125_A, f3, par3);
        this.field_70177_z = this.updateRotation(this.field_70177_z, f2, par2);
    }

    private float updateRotation(float par1, float par2, float par3) {
        float f3 = MathHelper.func_76142_g((float)(par2 - par1));
        if (f3 > par3) {
            f3 = par3;
        }
        if (f3 < -par3) {
            f3 = -par3;
        }
        return par1 + f3;
    }

    public void setHeading(double par1, double par3, double par5, float par7, float par8) {
        float f2 = MathHelper.func_76133_a((double)(par1 * par1 + par3 * par3 + par5 * par5));
        par1 /= (double)f2;
        par3 /= (double)f2;
        par5 /= (double)f2;
        par1 += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)par8;
        par3 += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)par8;
        par5 += this.field_70146_Z.nextGaussian() * (double)(this.field_70146_Z.nextBoolean() ? -1 : 1) * (double)0.0075f * (double)par8;
        this.field_70159_w = par1 *= (double)par7;
        this.field_70181_x = par3 *= (double)par7;
        this.field_70179_y = par5 *= (double)par7;
    }

    protected boolean pushOutOfBlocks(double par1, double par3, double par5) {
        int var7 = MathHelper.func_76128_c((double)par1);
        int var8 = MathHelper.func_76128_c((double)par3);
        int var9 = MathHelper.func_76128_c((double)par5);
        double var10 = par1 - (double)var7;
        double var12 = par3 - (double)var8;
        double var14 = par5 - (double)var9;
        if (this.field_70170_p.func_147439_a(var7, var8, var9) != ConfigBlocks.blockTaintFibres && !this.field_70170_p.func_147437_c(var7, var8, var9) && !this.field_70170_p.func_72953_d(this.field_70121_D)) {
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

