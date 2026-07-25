/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.EntityFX
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.world.World
 */
package thaumcraft.client.fx.particles;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.world.World;

public class FXGeneric
extends EntityFX {
    boolean loop = false;
    int delay = 0;
    int startParticle = 0;
    int numParticles = 1;
    int particleInc = 1;

    public FXGeneric(World world, double x, double y, double z, double xx, double yy, double zz) {
        super(world, x, y, z, xx, yy, zz);
        this.func_70105_a(0.1f, 0.1f);
        this.field_70145_X = false;
        this.field_70169_q = this.field_70165_t;
        this.field_70167_r = this.field_70163_u;
        this.field_70166_s = this.field_70161_v;
        this.field_70159_w = xx;
        this.field_70181_x = yy;
        this.field_70179_y = zz;
        this.field_70145_X = true;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public void setScale(float scale) {
        this.field_70544_f = scale;
    }

    public void setMaxAge(int max, int delay) {
        this.field_70547_e = max;
        this.field_70547_e += delay;
        this.delay = delay;
    }

    public void setParticles(int startParticle, int numParticles, int particleInc) {
        this.startParticle = startParticle;
        this.numParticles = numParticles;
        this.particleInc = particleInc;
        this.func_70536_a(startParticle);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.loop) {
            this.func_70536_a(this.startParticle + this.field_70546_d / this.particleInc % this.numParticles);
        } else {
            float fs = (float)this.field_70546_d / (float)this.field_70547_e;
            this.func_70536_a((int)((float)this.startParticle + Math.min((float)this.numParticles * fs, (float)(this.numParticles - 1))));
        }
    }

    public void func_70539_a(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5) {
        if (this.field_70546_d < this.delay) {
            return;
        }
        float t = this.field_82339_as;
        if (this.field_70546_d <= 1 || this.field_70546_d >= this.field_70547_e - 1) {
            this.field_82339_as = t / 2.0f;
        }
        super.func_70539_a(tessellator, f, f1, f2, f3, f4, f5);
        this.field_82339_as = t;
    }
}

