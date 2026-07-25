/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.particles;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXVisSparkle
extends EntityFX {
    private double targetX;
    private double targetY;
    private double targetZ;
    float sizeMod = 0.0f;

    public FXVisSparkle(World par1World, double par2, double par4, double par6, double tx, double ty, double tz) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.field_70551_j = 0.6f;
        this.field_70553_i = 0.6f;
        this.field_70552_h = 0.6f;
        this.field_70544_f = 0.0f;
        this.targetX = tx;
        this.targetY = ty;
        this.targetZ = tz;
        this.field_70547_e = 1000;
        float f3 = 0.01f;
        this.field_70159_w = (float)this.field_70146_Z.nextGaussian() * f3;
        this.field_70181_x = (float)this.field_70146_Z.nextGaussian() * f3;
        this.field_70179_y = (float)this.field_70146_Z.nextGaussian() * f3;
        this.sizeMod = 45 + this.field_70146_Z.nextInt(15);
        this.field_70552_h = 0.2f;
        this.field_70553_i = 0.6f + this.field_70146_Z.nextFloat() * 0.3f;
        this.field_70551_j = 0.2f;
        this.field_70545_g = 0.2f;
        this.field_70145_X = true;
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
        float bob = MathHelper.func_76126_a((float)((float)this.field_70546_d / 3.0f)) * 0.3f + 6.0f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.75f);
        int part = this.field_70546_d % 16;
        float var8 = (float)part / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.5f;
        float var11 = var10 + 0.0624375f;
        float var12 = 0.1f * this.field_70544_f * bob;
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var16 = 1.0f;
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5f);
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
            return;
        }
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.985;
        this.field_70181_x *= 0.985;
        this.field_70179_y *= 0.985;
        double dx = this.targetX - this.field_70165_t;
        double dy = this.targetY - this.field_70163_u;
        double dz = this.targetZ - this.field_70161_v;
        double d13 = 0.1f;
        double d11 = MathHelper.func_76133_a((double)(dx * dx + dy * dy + dz * dz));
        if (d11 < 2.0) {
            this.field_70544_f *= 0.95f;
        }
        if (d11 < 0.2) {
            this.field_70547_e = this.field_70546_d;
        }
        if (this.field_70546_d < 10) {
            this.field_70544_f = (float)this.field_70546_d / this.sizeMod;
        }
        this.field_70159_w += (dx /= d11) * d13;
        this.field_70181_x += (dy /= d11) * d13;
        this.field_70179_y += (dz /= d11) * d13;
        this.field_70159_w = MathHelper.func_76131_a((float)((float)this.field_70159_w), (float)-0.1f, (float)0.1f);
        this.field_70181_x = MathHelper.func_76131_a((float)((float)this.field_70181_x), (float)-0.1f, (float)0.1f);
        this.field_70179_y = MathHelper.func_76131_a((float)((float)this.field_70179_y), (float)-0.1f, (float)0.1f);
    }

    public void setGravity(float value) {
        this.field_70545_g = value;
    }
}

