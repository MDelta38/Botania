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
import java.awt.Color;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXVent
extends EntityFX {
    float psm = 1.0f;

    public FXVent(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int color) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.func_70105_a(0.02f, 0.02f);
        this.field_70544_f = this.field_70146_Z.nextFloat() * 0.1f + 0.05f;
        this.field_70159_w = par8;
        this.field_70181_x = par10;
        this.field_70179_y = par12;
        this.field_70145_X = true;
        Color c = new Color(color);
        this.field_70552_h = (float)c.getRed() / 255.0f;
        this.field_70551_j = (float)c.getBlue() / 255.0f;
        this.field_70553_i = (float)c.getGreen() / 255.0f;
        this.setHeading(this.field_70159_w, this.field_70181_x, this.field_70179_y, 0.125f, 5.0f);
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

    public void setScale(float f) {
        this.field_70544_f *= f;
        this.psm *= f;
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

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        ++this.field_70546_d;
        if (this.field_70544_f > this.psm) {
            this.func_70106_y();
        }
        this.field_70181_x += 0.0025;
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70159_w *= 0.8500000190734863;
        this.field_70181_x *= 0.8500000190734863;
        this.field_70179_y *= 0.8500000190734863;
        if (this.field_70544_f < this.psm) {
            this.field_70544_f = (float)((double)this.field_70544_f * 1.15);
        }
        if (this.field_70122_E) {
            this.field_70159_w *= (double)0.7f;
            this.field_70179_y *= (double)0.7f;
        }
    }

    public void setRGB(float r, float g, float b) {
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.33f);
        int part = (int)(1.0f + this.field_70544_f / this.psm * 4.0f);
        float var8 = (float)(part % 16) / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = (float)(part / 16) / 16.0f;
        float var11 = var10 + 0.0624375f;
        float var12 = 0.3f * this.field_70544_f;
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var16 = 1.0f;
        tessellator.func_78380_c(this.func_70070_b(f));
        float alpha = this.field_82339_as * ((this.psm - this.field_70544_f) / this.psm);
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, alpha);
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
    }

    public int func_70537_b() {
        return 1;
    }
}

