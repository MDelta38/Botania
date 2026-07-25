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
package com.kentington.thaumichorizons.client.fx;

import java.awt.Color;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXEssentiaBubble
extends EntityFX {
    private int count = 0;
    private int delay = 0;
    public int particle = 24;

    public FXEssentiaBubble(World par1World, double par2, double par4, double par6, int count, int color, float scale, int delay) {
        super(par1World, par2, par4, par6, 0.0, 0.0, 0.0);
        this.field_70551_j = 0.6f;
        this.field_70553_i = 0.6f;
        this.field_70552_h = 0.6f;
        this.field_70544_f = (MathHelper.func_76126_a((float)((float)count / 2.0f)) * 0.1f + 1.0f) * scale;
        this.delay = delay;
        this.count = count;
        this.field_70547_e = 20 + this.field_70146_Z.nextInt(20);
        this.field_70181_x = 0.025f + MathHelper.func_76126_a((float)((float)count / 3.0f)) * 0.002f;
        this.field_70179_y = 0.0;
        this.field_70159_w = 0.0;
        Color c = new Color(color);
        float mr = (float)c.getRed() / 255.0f * 0.2f;
        float mg = (float)c.getGreen() / 255.0f * 0.2f;
        float mb = (float)c.getBlue() / 255.0f * 0.2f;
        this.field_70552_h = (float)c.getRed() / 255.0f - mr + this.field_70146_Z.nextFloat() * mr;
        this.field_70553_i = (float)c.getGreen() / 255.0f - mg + this.field_70146_Z.nextFloat() * mg;
        this.field_70551_j = (float)c.getBlue() / 255.0f - mb + this.field_70146_Z.nextFloat() * mb;
        this.field_70545_g = 0.2f;
        this.field_70145_X = false;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        if (this.delay > 0) {
            return;
        }
        float t2 = 0.5625f;
        float t3 = 0.625f;
        float t4 = 0.0625f;
        float t5 = 0.125f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        int part = this.particle + this.field_70546_d % 16;
        float s = MathHelper.func_76126_a((float)((float)(this.field_70546_d - this.count) / 5.0f)) * 0.25f + 1.0f;
        float var12 = 0.1f * this.field_70544_f * s;
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        float var16 = 1.0f;
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, 0.5f);
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)t2, (double)t5);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)t3, (double)t5);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)t3, (double)t4);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)t2, (double)t4);
    }

    public int func_70537_b() {
        return 1;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.delay > 0) {
            --this.delay;
            return;
        }
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
            return;
        }
        this.field_70181_x += 0.00125;
        this.field_70544_f *= 1.05f;
        this.func_70091_d(this.field_70159_w, this.field_70181_x, this.field_70179_y);
        this.field_70181_x *= 0.985;
    }
}

