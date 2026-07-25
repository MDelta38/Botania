/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.other;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class FXBlockWard
extends EntityFX {
    ForgeDirection side;
    int rotation = 0;
    float sx = 0.0f;
    float sy = 0.0f;
    float sz = 0.0f;

    public FXBlockWard(World world, double d, double d1, double d2, ForgeDirection side, float f, float f1, float f2) {
        super(world, d, d1, d2, 0.0, 0.0, 0.0);
        this.side = side;
        this.field_70545_g = 0.0f;
        this.field_70179_y = 0.0;
        this.field_70181_x = 0.0;
        this.field_70159_w = 0.0;
        this.field_70547_e = 12 + this.field_70146_Z.nextInt(5);
        this.field_70145_X = false;
        this.func_70105_a(0.01f, 0.01f);
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70145_X = true;
        this.field_70544_f = (float)(1.4 + this.field_70146_Z.nextGaussian() * (double)0.3f);
        this.rotation = this.field_70146_Z.nextInt(360);
        this.sx = MathHelper.func_76131_a((float)(f - 0.6f + this.field_70146_Z.nextFloat() * 0.2f), (float)-0.4f, (float)0.4f);
        this.sy = MathHelper.func_76131_a((float)(f1 - 0.6f + this.field_70146_Z.nextFloat() * 0.2f), (float)-0.4f, (float)0.4f);
        this.sz = MathHelper.func_76131_a((float)(f2 - 0.6f + this.field_70146_Z.nextFloat() * 0.2f), (float)-0.4f, (float)0.4f);
        if (side.offsetX != 0) {
            this.sx = 0.0f;
        }
        if (side.offsetY != 0) {
            this.sy = 0.0f;
        }
        if (side.offsetZ != 0) {
            this.sz = 0.0f;
        }
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        tessellator.func_78381_a();
        GL11.glPushMatrix();
        float fade = ((float)this.field_70546_d + f) / (float)this.field_70547_e;
        int frame = Math.min(15, (int)(15.0f * fade));
        UtilsFX.bindTexture("textures/models/hemis" + frame + ".png");
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(this.field_82339_as / 2.0f));
        float var13 = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float var14 = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float var15 = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        GL11.glTranslated((double)(var13 + this.sx), (double)(var14 + this.sy), (double)(var15 + this.sz));
        GL11.glRotatef((float)90.0f, (float)this.side.offsetY, (float)(-this.side.offsetX), (float)this.side.offsetZ);
        GL11.glRotatef((float)this.rotation, (float)0.0f, (float)0.0f, (float)1.0f);
        if (this.side.offsetZ > 0) {
            GL11.glTranslated((double)0.0, (double)0.0, (double)0.505f);
            GL11.glRotatef((float)180.0f, (float)0.0f, (float)-1.0f, (float)0.0f);
        } else {
            GL11.glTranslated((double)0.0, (double)0.0, (double)-0.505f);
        }
        float var12 = this.field_70544_f;
        float var16 = 1.0f;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(this.field_70552_h * var16, this.field_70553_i * var16, this.field_70551_j * var16, this.field_82339_as / 2.0f);
        tessellator.func_78374_a(-0.5 * (double)var12, 0.5 * (double)var12, 0.0, 0.0, 1.0);
        tessellator.func_78374_a(0.5 * (double)var12, 0.5 * (double)var12, 0.0, 1.0, 1.0);
        tessellator.func_78374_a(0.5 * (double)var12, -0.5 * (double)var12, 0.0, 1.0, 0.0);
        tessellator.func_78374_a(-0.5 * (double)var12, -0.5 * (double)var12, 0.0, 0.0, 0.0);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
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

