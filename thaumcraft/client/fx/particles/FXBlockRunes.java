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

public class FXBlockRunes
extends EntityFX {
    double ofx = 0.0;
    double ofy = 0.0;
    float rotation = 0.0f;
    int runeIndex = 0;

    public FXBlockRunes(World world, double d, double d1, double d2, float f1, float f2, float f3, int m) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        if (f1 == 0.0f) {
            f1 = 1.0f;
        }
        this.rotation = this.field_70146_Z.nextInt(4) * 90;
        this.field_70552_h = f1;
        this.field_70553_i = f2;
        this.field_70551_j = f3;
        this.field_70545_g = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70547_e = 3 * m;
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70145_X = true;
        this.runeIndex = (int)(Math.random() * 16.0 + 224.0);
        this.ofx = (double)this.field_70146_Z.nextFloat() * 0.2;
        this.ofy = -0.3 + (double)this.field_70146_Z.nextFloat() * 0.6;
        this.field_70544_f = (float)(1.0 + this.field_70146_Z.nextGaussian() * (double)0.1f);
        this.field_82339_as = 0.0f;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        tessellator.func_78381_a();
        GL11.glPushMatrix();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(this.field_82339_as / 2.0f));
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        GL11.glTranslated((double)var13, (double)var14, (double)var15);
        GL11.glRotatef((float)this.rotation, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslated((double)this.ofx, (double)this.ofy, (double)-0.51);
        float var8 = (float)(this.runeIndex % 16) / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.375f;
        float var11 = var10 + 0.0624375f;
        float var12 = 0.3f * this.field_70544_f;
        float var16 = 1.0f;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0f);
        tessellator.func_78374_a(-0.5 * (double)var12, 0.5 * (double)var12, 0.0, (double)var9, (double)var11);
        tessellator.func_78374_a(0.5 * (double)var12, 0.5 * (double)var12, 0.0, (double)var9, (double)var10);
        tessellator.func_78374_a(0.5 * (double)var12, -0.5 * (double)var12, 0.0, (double)var8, (double)var10);
        tessellator.func_78374_a(-0.5 * (double)var12, -0.5 * (double)var12, 0.0, (double)var8, (double)var11);
        tessellator.func_78381_a();
        GL11.glPopMatrix();
        tessellator.func_78382_b();
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        float threshold = (float)this.field_70547_e / 5.0f;
        this.field_82339_as = (float)this.field_70546_d <= threshold ? (float)this.field_70546_d / threshold : (float)(this.field_70547_e - this.field_70546_d) / (float)this.field_70547_e;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
        this.field_70181_x -= 0.04 * (double)this.field_70545_g;
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
    }

    public void setGravity(float value) {
        this.field_70545_g = value;
    }
}

