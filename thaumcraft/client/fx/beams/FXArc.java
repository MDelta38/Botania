/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.fx.beams;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.lib.utils.Utils;

public class FXArc
extends EntityFX {
    public int particle = 16;
    ArrayList<Vec3> points = new ArrayList();
    private Entity targetEntity = null;
    private double tX = 0.0;
    private double tY = 0.0;
    private double tZ = 0.0;
    public int blendmode = 1;
    public float length = 1.0f;

    public FXArc(World par1World, double x, double y, double z, double tx, double ty, double tz, float red, float green, float blue, double hg) {
        super(par1World, x, y, z, 0.0, 0.0, 0.0);
        this.field_70552_h = red;
        this.field_70553_i = green;
        this.field_70551_j = blue;
        this.func_70105_a(0.02f, 0.02f);
        this.field_70145_X = true;
        this.field_70159_w = 0.0;
        this.field_70181_x = 0.0;
        this.field_70179_y = 0.0;
        this.tX = tx - x;
        this.tY = ty - y;
        this.tZ = tz - z;
        this.field_70547_e = 1;
        double xx = 0.0;
        double yy = 0.0;
        double zz = 0.0;
        double gravity = 0.115;
        double noise = 0.25;
        Vec3 vs = Vec3.func_72443_a((double)xx, (double)yy, (double)zz);
        Vec3 ve = Vec3.func_72443_a((double)this.tX, (double)this.tY, (double)this.tZ);
        Vec3 vc = Vec3.func_72443_a((double)xx, (double)yy, (double)zz);
        this.length = (float)ve.func_72433_c();
        Vec3 vv = Utils.calculateVelocity(vs, ve, hg, gravity);
        double l = Utils.distanceSquared3d(Vec3.func_72443_a((double)0.0, (double)0.0, (double)0.0), vv);
        this.points.add(vs);
        for (int c = 0; Utils.distanceSquared3d(ve, vc) > l && c < 50; ++c) {
            Vec3 vt = vc.func_72441_c(vv.field_72450_a, vv.field_72448_b, vv.field_72449_c);
            vc = Vec3.func_72443_a((double)vt.field_72450_a, (double)vt.field_72448_b, (double)vt.field_72449_c);
            vt.field_72450_a += (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise;
            vt.field_72448_b += (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise;
            vt.field_72449_c += (this.field_70146_Z.nextDouble() - this.field_70146_Z.nextDouble()) * noise;
            this.points.add(vt);
            vv.field_72448_b -= gravity / 1.9;
        }
        this.points.add(ve);
    }

    public void func_70071_h_() {
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        if (this.field_70546_d++ >= this.field_70547_e) {
            this.func_70106_y();
        }
    }

    public void setRGB(float r, float g, float b) {
        this.field_70552_h = r;
        this.field_70553_i = g;
        this.field_70551_j = b;
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        double dz;
        double dy;
        double dx;
        float f13;
        Vec3 v;
        int c;
        tessellator.func_78381_a();
        GL11.glPushMatrix();
        double ePX = this.field_70169_q + (this.field_70165_t - this.field_70169_q) * (double)f - field_70556_an;
        double ePY = this.field_70167_r + (this.field_70163_u - this.field_70167_r) * (double)f - field_70554_ao;
        double ePZ = this.field_70166_s + (this.field_70161_v - this.field_70166_s) * (double)f - field_70555_ap;
        GL11.glTranslated((double)ePX, (double)ePY, (double)ePZ);
        float size = 0.25f;
        UtilsFX.bindTexture("textures/misc/beamh.png");
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glDisable((int)2884);
        tessellator.func_78371_b(5);
        tessellator.func_78380_c(200);
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8f);
        float f9 = 0.0f;
        float f10 = 1.0f;
        for (c = 0; c < this.points.size(); ++c) {
            v = this.points.get(c);
            f13 = (float)c / this.length;
            dx = v.field_72450_a;
            dy = v.field_72448_b;
            dz = v.field_72449_c;
            tessellator.func_78374_a(dx, dy - (double)size, dz, (double)f13, (double)f10);
            tessellator.func_78374_a(dx, dy + (double)size, dz, (double)f13, (double)f9);
        }
        tessellator.func_78381_a();
        tessellator.func_78371_b(5);
        tessellator.func_78380_c(200);
        tessellator.func_78369_a(this.field_70552_h, this.field_70553_i, this.field_70551_j, 0.8f);
        for (c = 0; c < this.points.size(); ++c) {
            v = this.points.get(c);
            f13 = (float)c / this.length;
            dx = v.field_72450_a;
            dy = v.field_72448_b;
            dz = v.field_72449_c;
            tessellator.func_78374_a(dx - (double)size, dy, dz - (double)size, (double)f13, (double)f10);
            tessellator.func_78374_a(dx + (double)size, dy, dz + (double)size, (double)f13, (double)f9);
        }
        tessellator.func_78381_a();
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        Minecraft.func_71410_x().field_71446_o.func_110577_a(UtilsFX.getParticleTexture());
        tessellator.func_78382_b();
    }
}

