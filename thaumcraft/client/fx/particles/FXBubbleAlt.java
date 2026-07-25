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

public class FXBubbleAlt
extends EntityFX {
    public int particle = 25;
    public double bubblespeed = 1.0E-4;

    public FXBubbleAlt(World par1World, double par2, double par4, double par6, double par8, double par10, double par12, int age) {
        super(par1World, par2, par4, par6, par8, par10, par12);
        this.field_70552_h = 1.0f;
        this.field_70553_i = 0.0f;
        this.field_70551_j = 0.5f;
        this.func_70105_a(0.02f, 0.02f);
        this.field_70145_X = true;
        this.field_70544_f *= this.field_70146_Z.nextFloat() * 0.3f + 0.2f;
        this.field_70159_w = par8 * (double)0.2f + (double)((float)(Math.random() * 2.0 - 1.0) * 0.02f);
        this.field_70181_x = par10 * (double)0.2f + (double)((float)Math.random() * 0.02f);
        this.field_70179_y = par12 * (double)0.2f + (double)((float)(Math.random() * 2.0 - 1.0) * 0.02f);
        this.field_70547_e = (int)((double)(age + 2) + 8.0 / (Math.random() * 0.8 + 0.2));
        this.field_70546_d = 0;
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

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70159_w += (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.001f);
        this.field_70179_y += (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.001f);
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        this.field_70159_w *= (double)0.85f;
        this.field_70181_x *= (double)0.85f;
        this.field_70179_y *= (double)0.85f;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
        if (this.field_70546_d == this.field_70547_e - 2) {
            this.particle = 17;
        } else if (this.field_70546_d == this.field_70547_e - 1) {
            this.particle = 18;
        }
    }

    public void setRGB(float r, float g, float b) {
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)this.field_82339_as);
        float var8 = (float)(this.particle % 16) / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = (float)(this.particle / 16) / 16.0f;
        float var11 = var10 + 0.0624375f;
        float var12 = 0.2f * (this.field_70544_f * ((float)this.field_70546_d / (float)this.field_70547_e));
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var16 = 1.0f;
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as);
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
    }
}

