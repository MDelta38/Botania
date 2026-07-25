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
package thaumcraft.client.fx.beams;

import cpw.mods.fml.client.FMLClientHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class FXBeam
extends EntityFX {
    public int particle = 16;
    boolean updated = false;
    double movX = 0.0;
    double movY = 0.0;
    double movZ = 0.0;
    private float length = 0.0f;
    private float rotYaw = 0.0f;
    private float rotPitch = 0.0f;
    private float prevYaw = 0.0f;
    private float prevPitch = 0.0f;
    private Entity targetEntity = null;
    private double tX = 0.0;
    private double tY = 0.0;
    private double tZ = 0.0;
    private int type = 0;
    private float endMod = 1.0f;
    private boolean reverse = false;
    private boolean pulse = true;
    private int rotationspeed = 5;
    private float prevSize = 0.0f;
    public int blendmode = 1;
    public float field_70130_N = 1.0f;

    public FXBeam(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, int age) {
        super(par1World, x, y, z, 0.0, 0.0, 0.0);
        this.field_70552_h = red;
        this.field_70553_i = green;
        this.field_70551_j = blue;
        this.func_70105_a(0.02f, 0.02f);
        this.field_70145_X = true;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.tX = tx;
        this.tY = ty;
        this.tZ = tz;
        float xd = (float)(this.field_70165_t - this.tX);
        float yd = (float)(this.field_70163_u - this.tY);
        float zd = (float)(this.field_70161_v - this.tZ);
        this.length = MathHelper.func_76129_c((float)(xd * xd + yd * yd + zd * zd));
        double var7 = MathHelper.func_76133_a((double)(xd * xd + zd * zd));
        this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0 / Math.PI);
        this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0 / Math.PI);
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

    public FXBeam(World par1World, double x, double y, double z, Entity entity, float red, float green, float blue, int age) {
        super(par1World, x, y, z, 0.0, 0.0, 0.0);
        this.field_70552_h = red;
        this.field_70553_i = green;
        this.field_70551_j = blue;
        this.func_70105_a(0.02f, 0.02f);
        this.field_70145_X = true;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.targetEntity = entity;
        this.tX = this.targetEntity.field_70165_t;
        this.tY = this.targetEntity.field_70163_u + (double)this.targetEntity.func_70047_e() - (double)(this.targetEntity.field_70131_O / 2.0f);
        this.tZ = this.targetEntity.field_70161_v;
        float xd = (float)(this.field_70165_t - this.tX);
        float yd = (float)(this.field_70163_u - this.tY);
        float zd = (float)(this.field_70161_v - this.tZ);
        this.length = MathHelper.func_76129_c((float)(xd * xd + yd * yd + zd * zd));
        double var7 = MathHelper.func_76133_a((double)(xd * xd + zd * zd));
        this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0 / Math.PI);
        this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0 / Math.PI);
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

    public void updateBeam(double xs, double ys, double zs, double x, double y, double z) {
        this.movX = xs;
        this.movY = ys;
        this.movZ = zs;
        this.tX = x;
        this.tY = y;
        this.tZ = z;
        while (this.field_70547_e - this.field_70546_d < 4) {
            ++this.field_70547_e;
        }
        this.updated = true;
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.updated) {
            this.field_70165_t = this.movX;
            this.field_70163_u = this.movY;
            this.field_70161_v = this.movZ;
            this.updated = false;
        }
        this.prevYaw = this.rotYaw;
        this.prevPitch = this.rotPitch;
        if (this.targetEntity != null) {
            this.tX = this.targetEntity.field_70165_t;
            this.tY = this.targetEntity.field_70163_u + (double)this.targetEntity.func_70047_e() - (double)(this.targetEntity.field_70131_O / 2.0f);
            this.tZ = this.targetEntity.field_70161_v;
        }
        float xd = (float)(this.field_70165_t - this.tX);
        float yd = (float)(this.field_70163_u - this.tY);
        float zd = (float)(this.field_70161_v - this.tZ);
        this.length = MathHelper.func_76129_c((float)(xd * xd + yd * yd + zd * zd));
        double var7 = MathHelper.func_76133_a((double)(xd * xd + zd * zd));
        this.rotYaw = (float)(Math.atan2(xd, zd) * 180.0 / Math.PI);
        this.rotPitch = (float)(Math.atan2(yd, var7) * 180.0 / Math.PI);
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
    }

    public void setRGB(float r, float g, float b) {
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setEndMod(float endMod) {
        this.endMod = endMod;
    }

    public void setReverse(boolean reverse) {
        this.reverse = reverse;
    }

    public void setPulse(boolean pulse) {
        this.pulse = pulse;
    }

    public void setRotationspeed(int rotationspeed) {
        this.rotationspeed = rotationspeed;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        tessellator.func_78381_a();
        GL11.glPushMatrix();
        float var9 = 1.0f;
        float slide = Minecraft.func_71410_x().field_71439_g.field_70173_aa;
        float rot = (float)(this.field_70170_p.field_73011_w.getWorldTime() % (long)(360 / this.rotationspeed) * (long)this.rotationspeed) + (float)this.rotationspeed * f;
        float size = this.field_70130_N;
        if (this.pulse) {
            size = Math.min((float)this.field_70546_d / 4.0f, this.field_70130_N);
            size = (float)((double)this.prevSize + (double)(size - this.prevSize) * (double)f);
        }
        float op = 0.4f;
        if (this.pulse && this.field_70547_e - this.field_70546_d <= 4) {
            op = 0.4f - (float)(4 - (this.field_70547_e - this.field_70546_d)) * 0.1f;
        }
        switch (this.type) {
            default: {
                UtilsFX.bindTexture("textures/misc/beam.png");
                break;
            }
            case 1: {
                UtilsFX.bindTexture("textures/misc/beam1.png");
                break;
            }
            case 2: {
                UtilsFX.bindTexture("textures/misc/beam2.png");
                break;
            }
            case 3: {
                UtilsFX.bindTexture("textures/misc/beam3.png");
            }
        }
        GL11.glTexParameterf((int)3553, (int)10242, (float)10497.0f);
        GL11.glTexParameterf((int)3553, (int)10243, (float)10497.0f);
        GL11.glDisable((int)2884);
        float var11 = slide + f;
        if (this.reverse) {
            var11 *= -1.0f;
        }
        float var12 = -var11 * 0.2f - (float)MathHelper.func_76141_d((float)(-var11 * 0.1f));
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)this.blendmode);
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
        double var44b = -0.15 * (double)size * (double)this.endMod;
        double var17b = 0.15 * (double)size * (double)this.endMod;
        GL11.glRotatef((float)rot, (float)0.0f, (float)1.0f, (float)0.0f);
        for (int t = 0; t < 3; ++t) {
            double var29 = this.length * size / this.field_70130_N * var9;
            double var31 = 0.0;
            double var33 = 1.0;
            double var35 = -1.0f + var12 + (float)t / 3.0f;
            double var37 = (double)(this.length * size / this.field_70130_N * var9) + var35;
            GL11.glRotatef((float)60.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            tessellator.func_78382_b();
            tessellator.func_78380_c(200);
            tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, op);
            tessellator.func_78374_a(var44b, var29, 0.0, var33, var37);
            tessellator.func_78374_a(var44, 0.0, 0.0, var33, var35);
            tessellator.func_78374_a(var17, 0.0, 0.0, var31, var35);
            tessellator.func_78374_a(var17b, var29, 0.0, var31, var37);
            tessellator.func_78381_a();
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)3042);
        GL11.glEnable((int)2884);
        GL11.glPopMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
        tessellator.func_78382_b();
        this.prevSize = size;
    }
}

