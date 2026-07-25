/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.client.fx.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class FXScorch
extends EntityFX {
    public boolean pvp = true;
    public boolean mobs = true;
    public boolean animals = true;
    private double px;
    private double py;
    private double pz;
    private float transferParticleScale;
    Entity partDestEnt;
    public boolean lance = false;

    public FXScorch(World world, double x, double y, double z, Vec3 v, float spread, boolean lance) {
        super(world, x, y, z, 0.0, 0.0, 0.0);
        this.field_70165_t = x;
        this.field_70163_u = y;
        this.field_70161_v = z;
        this.lance = lance;
        this.px = x + v.field_72450_a * 100.0;
        this.py = y + v.field_72448_b * 100.0;
        this.pz = z + v.field_72449_c * 100.0;
        if (!lance) {
            this.px += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * spread);
            this.py += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * spread);
            this.pz += (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * spread);
        } else {
            this.px += (double)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5;
            this.py += (double)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5;
            this.pz += (double)(this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.5;
        }
        this.transferParticleScale = this.field_70544_f = this.field_70146_Z.nextFloat() * 0.5f + 2.0f;
        if (!lance) {
            this.transferParticleScale = this.field_70544_f = this.field_70146_Z.nextFloat() + 3.0f;
        }
        this.field_70547_e = 50;
        this.func_70105_a(0.1f, 0.1f);
        this.func_70536_a(151);
        this.field_70145_X = false;
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_82339_as = 0.66f;
    }

    public int func_70070_b(float par1) {
        return 210;
    }

    public float func_70013_c(float par1) {
        return 1.0f;
    }

    public void func_70071_h_() {
        float fs;
        double dx = this.px - this.field_70165_t;
        double dy = this.py - this.field_70163_u;
        double dz = this.pz - this.field_70161_v;
        double distance = MathHelper.func_76133_a((double)(dx * dx + dy * dy + dz * dz));
        this.field_70159_w = dx /= distance * 1.25;
        this.field_70181_x = dy /= distance * 1.25;
        this.field_70179_y = dz /= distance * 1.25;
        this.field_70159_w *= (double)((float)(this.field_70547_e - this.field_70546_d) / (float)this.field_70547_e);
        this.field_70181_x *= (double)((float)(this.field_70547_e - this.field_70546_d) / (float)this.field_70547_e);
        this.field_70179_y *= (double)((float)(this.field_70547_e - this.field_70546_d) / (float)this.field_70547_e);
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70159_w += (double)(this.field_70146_Z.nextFloat() * 0.07f - 0.035f);
        this.field_70181_x += (double)(this.field_70146_Z.nextFloat() * 0.07f - 0.035f);
        this.field_70179_y += (double)(this.field_70146_Z.nextFloat() * 0.07f - 0.035f);
        int var7 = MathHelper.func_76128_c((double)this.field_70165_t);
        int var8 = MathHelper.func_76128_c((double)this.field_70163_u);
        int var9 = MathHelper.func_76128_c((double)this.field_70161_v);
        if (this.field_70546_d > 1 && this.field_70170_p.func_147439_a(var7, var8, var9).func_149662_c()) {
            this.field_70159_w = 0.0;
            this.field_70181_x = 0.0;
            this.field_70179_y = 0.0;
            this.field_70546_d += 10;
        }
        this.func_145771_j(this.field_70165_t, (this.field_70121_D.field_72338_b + this.field_70121_D.field_72337_e) / 2.0, this.field_70161_v);
        this.field_70165_t += this.field_70159_w;
        this.field_70163_u += this.field_70181_x;
        this.field_70161_v += this.field_70179_y;
        ++this.field_70546_d;
        if (this.field_70546_d >= this.field_70547_e) {
            this.func_70106_y();
        }
        if ((fs = (float)this.field_70546_d / (float)(this.field_70547_e - 9)) <= 1.0f) {
            this.func_70536_a((int)(151.0f + fs * 6.0f));
        } else {
            this.func_70536_a(159 - (this.field_70547_e - this.field_70546_d) / 3);
        }
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        float fs = (float)this.field_70546_d / (float)this.field_70547_e;
        this.field_70544_f = this.transferParticleScale * (fs + 0.25f) * 2.0f;
        float fc = (float)this.field_70546_d * 9.0f / (float)this.field_70547_e;
        if (fc > 1.0f) {
            fc = 1.0f;
        }
        this.field_70552_h = this.field_70553_i = fc;
        this.field_70551_j = 1.0f;
        super.func_70539_a(tessellator, f, f1, f2, f3, f4, f5);
    }
}

