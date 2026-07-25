/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FXSpark
extends EntityFX {
    int particle = 0;
    boolean flip = false;

    public FXSpark(World world, double d, double d1, double d2, float f) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        this.field_70552_h = 1.0f;
        this.field_70553_i = 1.0f;
        this.field_70551_j = 1.0f;
        this.field_70545_g = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70544_f = f;
        this.field_70547_e = 5 + world.field_73012_v.nextInt(5);
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        this.particle = world.field_73012_v.nextInt(3) * 8;
        this.flip = world.field_73012_v.nextBoolean();
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)this.field_82339_as);
        int part = this.particle + (int)((float)this.field_70546_d / (float)this.field_70547_e * 7.0f);
        float var8 = (float)(part % 8) / 8.0f;
        float var9 = var8 + 0.125f;
        float var10 = (float)(part / 8) / 8.0f;
        float var11 = var10 + 0.125f;
        float var12 = this.field_70544_f;
        if (this.flip) {
            float t = var8;
            var8 = var9;
            var9 = t;
        }
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        tessellator.func_78380_c(this.func_70070_b(f));
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.field_82339_as);
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
    }

    public int func_70537_b() {
        return 2;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
    }
}

