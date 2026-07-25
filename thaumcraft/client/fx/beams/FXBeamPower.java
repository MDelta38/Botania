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
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.beams;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.nodes.IRevealer;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;

public class FXBeamPower
extends EntityFX {
    public int particle = 16;
    private double offset = 0.0;
    private double tX = 0.0;
    private double tY = 0.0;
    private double tZ = 0.0;
    private double ptX = 0.0;
    private double ptY = 0.0;
    private double ptZ = 0.0;
    private float length = 0.0f;
    private float rotYaw = 0.0f;
    private float rotPitch = 0.0f;
    private float prevYaw = 0.0f;
    private float prevPitch = 0.0f;
    private Entity targetEntity = null;
    private float opacity = 0.3f;
    private float prevSize = 0.0f;

    public FXBeamPower(World par1World, double px, double py, double pz, double tx, double ty, double tz, float red, float green, float blue, int age) {
        super(par1World, px, py, pz, 0.0, 0.0, 0.0);
        this.field_70552_h = 0.5f;
        this.field_70553_i = 0.5f;
        this.field_70551_j = 0.5f;
        this.func_70105_a(0.02f, 0.02f);
        this.field_70145_X = true;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.tX = tx;
        this.tY = ty;
        this.tZ = tz;
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        this.field_70547_e = age;
        EntityLivingBase renderentity = FMLClientHandler.instance().getClient().field_71451_h;
        int visibleDistance = 50;
        if (!FMLClientHandler.instance().getClient().field_71474_y.field_74347_j) {
            visibleDistance = 25;
        }
        if (renderentity.func_70011_f(this.field_70165_t, this.field_70163_u, this.field_70161_v) > (double)visibleDistance) {
            this.field_70547_e = 0;
        }
    }

    public void updateBeam(double xx, double yy, double zz, double x, double y, double z) {
        this.func_70107_b(xx, yy, zz);
        this.tX = x;
        this.tY = y;
        this.tZ = z;
        while (this.field_70547_e - this.field_70546_d < 4) {
            ++this.field_70547_e;
        }
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u + this.offset;
        this.field_70166_s = this.field_70161_v;
        this.ptX = this.tX;
        this.ptY = this.tY;
        this.ptZ = this.tZ;
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        float xd = (float)(this.field_70165_t - this.tX);
        float yd = (float)(this.field_70163_u - this.tY);
        float zd = (float)(this.field_70161_v - this.tZ);
        this.length = MathHelper.func_76129_c((float)(xd * xd + yd * yd + zd * zd));
        double var7 = MathHelper.func_76133_a((double)(xd * xd + zd * zd));
        this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0 / Math.PI);
        this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0 / Math.PI);
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        if (this.opacity > 0.3f) {
            this.opacity -= 0.025f;
        }
        if (this.opacity < 0.3f) {
            this.opacity = 0.3f;
        }
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
    }

    public void setRGB(float r, float g, float b) {
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
    }

    public void setPulse(boolean pulse, float r, float g, float b) {
        this.setRGB(r, g, b);
        if (pulse) {
            this.opacity = 0.8f;
        }
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        tessellator.func_78381_a();
        GL11.glPushMatrix();
        float var9 = 1.0f;
        float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
        float size = 0.7f;
        UtilsFX.bindTexture("textures/misc/beam1.png");
        GL11.glTexParameterf((int)3553, (int)10242, (float)10497.0f);
        GL11.glTexParameterf((int)3553, (int)10243, (float)10497.0f);
        GL11.glDisable((int)2884);
        float var11 = slide + f;
        float var12 = -var11 * 0.2f - (float)MathHelper.func_76141_d((float)(-var11 * 0.1f));
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glDepthMask((boolean)false);
        float xx = (float)(this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an);
        float yy = (float)(this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao);
        float zz = (float)(this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap);
        GL11.glTranslated((double)xx, (double)yy, (double)zz);
        float ry = (float)((double)this.prevYaw + (double)(this.rotYaw - this.prevYaw) * (double)f);
        float rp = (float)((double)this.prevPitch + (double)(this.rotPitch - this.prevPitch) * (double)f);
        GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glRotatef((float)(180.0f + ry), (float)0.0f, (float)0.0f, (float)-1.0f);
        GL11.glRotatef((float)rp, (float)1.0f, (float)0.0f, (float)0.0f);
        double var44 = -0.15 * (double)size;
        double var17 = 0.15 * (double)size;
        float opmod = 0.1f;
        EntityLivingBase v = FMLClientHandler.instance().getClient().field_71451_h;
        if (v instanceof EntityPlayer && ((EntityPlayer)v).field_71071_by.func_70440_f(3) != null && ((EntityPlayer)v).field_71071_by.func_70440_f(3).func_77973_b() instanceof IRevealer) {
            opmod = 1.0f;
        }
        for (int t = 0; t < 2; ++t) {
            double var29 = this.length * var9;
            double var31 = 0.0;
            double var33 = 1.0;
            double var35 = -1.0f + var12 + (float)t / 3.0f;
            double var37 = (double)(this.length * var9) + var35;
            GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            tessellator.func_78382_b();
            tessellator.func_78380_c(200);
            tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.opacity * opmod);
            tessellator.func_78374_a(var44, var29, 0.0, var33, var37);
            tessellator.func_78374_a(var44, 0.0, 0.0, var33, var35);
            tessellator.func_78374_a(var17, 0.0, 0.0, var31, var35);
            tessellator.func_78374_a(var17, var29, 0.0, var31, var37);
            tessellator.func_78381_a();
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDepthMask((boolean)true);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        this.renderFlare(tessellator, f, f1, f2, f3, f4, f5);
        Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
        tessellator.func_78382_b();
        this.prevSize = size;
    }

    public void renderFlare(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float opmod = 0.2f;
        EntityLivingBase v = FMLClientHandler.instance().getClient().field_71451_h;
        if (v instanceof EntityPlayer && ((EntityPlayer)v).field_71071_by.func_70440_f(3) != null && ((EntityPlayer)v).field_71071_by.func_70440_f(3).func_77973_b() instanceof IRevealer) {
            opmod = 1.0f;
        }
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glBlendFunc((int)770, (int)1);
        UtilsFX.bindTexture(ParticleEngine.particleTexture);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.66f);
        int part = this.field_70546_d % 16;
        float var8 = (float)part / 16.0f;
        float var9 = var8 + 0.0624375f;
        float var10 = 0.3125f;
        float var11 = var10 + 0.0624375f;
        float var12 = 0.66f * this.opacity;
        float var13 = (float)(this.ptX + (this.tX - this.ptX) * (double)f - field_70556_an);
        float var14 = (float)(this.ptY + (this.tY - this.ptY) * (double)f - field_70554_ao);
        float var15 = (float)(this.ptZ + (this.tZ - this.ptZ) * (double)f - field_70555_ap);
        float var16 = 1.0f;
        tessellator.func_78382_b();
        tessellator.func_78380_c(200);
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, this.opacity * opmod);
        tessellator.func_78374_a((double)(var13 - f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 - f3 * var12 - f5 * var12), (double)var9, (double)var11);
        tessellator.func_78374_a((double)(var13 - f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 - f3 * var12 + f5 * var12), (double)var9, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 + f4 * var12), (double)(var14 + f2 * var12), (double)(var15 + f3 * var12 + f5 * var12), (double)var8, (double)var10);
        tessellator.func_78374_a((double)(var13 + f1 * var12 - f4 * var12), (double)(var14 - f2 * var12), (double)(var15 + f3 * var12 - f5 * var12), (double)var8, (double)var11);
        tessellator.func_78381_a();
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }
}

