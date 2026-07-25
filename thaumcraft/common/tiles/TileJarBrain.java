/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.tiles;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.tiles.TileJar;

public class TileJarBrain
extends TileJar {
    public float field_40063_b;
    public float field_40061_d;
    public float field_40059_f;
    public float field_40066_q;
    public float rota;
    public float rotb;
    public int xp = 0;
    public int xpMax = 2000;
    public int eatDelay = 0;
    long lastsigh = System.currentTimeMillis() + 1500L;

    @Override
    public void readCustomNBT(NBTTagCompound nbttagcompound) {
        this.xp = nbttagcompound.func_74762_e("XP");
    }

    @Override
    public void writeCustomNBT(NBTTagCompound nbttagcompound) {
        nbttagcompound.func_74768_a("XP", this.xp);
    }

    @Override
    public boolean canUpdate() {
        return true;
    }

    @Override
    public void func_145845_h() {
        List ents;
        double var7;
        double var5;
        double var3;
        double var9;
        double var11;
        super.func_145845_h();
        Entity entity = null;
        if (this.xp > this.xpMax) {
            this.xp = this.xpMax;
        }
        if (this.xp < this.xpMax && (entity = this.getClosestXPOrb()) != null && this.eatDelay == 0 && (var11 = 1.0 - (var9 = Math.sqrt((var3 = ((double)this.field_145851_c + 0.5 - entity.field_70165_t) / 7.0) * var3 + (var5 = ((double)this.field_145848_d + 0.5 - entity.field_70163_u) / 7.0) * var5 + (var7 = ((double)this.field_145849_e + 0.5 - entity.field_70161_v) / 7.0) * var7))) > 0.0) {
            var11 *= var11;
            entity.field_70159_w += var3 / var9 * var11 * 0.15;
            entity.field_70181_x += var5 / var9 * var11 * 0.33;
            entity.field_70179_y += var7 / var9 * var11 * 0.15;
        }
        if (this.field_145850_b.field_72995_K) {
            float f;
            this.rotb = this.rota;
            if (entity == null && (entity = this.field_145850_b.func_72977_a((double)((float)this.field_145851_c + 0.5f), (double)((float)this.field_145848_d + 0.5f), (double)((float)this.field_145849_e + 0.5f), 6.0)) != null && this.lastsigh < System.currentTimeMillis()) {
                this.field_145850_b.func_72980_b((double)this.field_145851_c + 0.5, (double)this.field_145848_d + 0.5, (double)this.field_145849_e + 0.5, "thaumcraft:brain", 0.15f, 0.8f + this.field_145850_b.field_73012_v.nextFloat() * 0.4f, false);
                this.lastsigh = System.currentTimeMillis() + 5000L + (long)this.field_145850_b.field_73012_v.nextInt(25000);
            }
            if (entity != null) {
                double d = entity.field_70165_t - (double)((float)this.field_145851_c + 0.5f);
                double d1 = entity.field_70161_v - (double)((float)this.field_145849_e + 0.5f);
                this.field_40066_q = (float)Math.atan2(d1, d);
                this.field_40059_f += 0.1f;
                if (this.field_40059_f < 0.5f || rand.nextInt(40) == 0) {
                    float f3 = this.field_40061_d;
                    do {
                        this.field_40061_d += (float)(rand.nextInt(4) - rand.nextInt(4));
                    } while (f3 == this.field_40061_d);
                }
            } else {
                this.field_40066_q += 0.01f;
            }
            while (this.rota >= 3.141593f) {
                this.rota -= 6.283185f;
            }
            while (this.rota < -3.141593f) {
                this.rota += 6.283185f;
            }
            while (this.field_40066_q >= 3.141593f) {
                this.field_40066_q -= 6.283185f;
            }
            while (this.field_40066_q < -3.141593f) {
                this.field_40066_q += 6.283185f;
            }
            for (f = this.field_40066_q - this.rota; f >= 3.141593f; f -= 6.283185f) {
            }
            while (f < -3.141593f) {
                f += 6.283185f;
            }
            this.rota += f * 0.04f;
        }
        if (this.eatDelay > 0) {
            --this.eatDelay;
        } else if (this.xp < this.xpMax && (ents = this.field_145850_b.func_72872_a(EntityXPOrb.class, AxisAlignedBB.func_72330_a((double)((double)this.field_145851_c - 0.1), (double)((double)this.field_145848_d - 0.1), (double)((double)this.field_145849_e - 0.1), (double)((double)this.field_145851_c + 1.1), (double)((double)this.field_145848_d + 1.1), (double)((double)this.field_145849_e + 1.1)))).size() > 0) {
            for (Object ent : ents) {
                EntityXPOrb eo = (EntityXPOrb)ent;
                this.xp += eo.func_70526_d();
                this.field_145850_b.func_72956_a((Entity)eo, "random.eat", 0.1f, (this.field_145850_b.field_73012_v.nextFloat() - this.field_145850_b.field_73012_v.nextFloat()) * 0.2f + 1.0f);
                eo.func_70106_y();
            }
            this.field_145850_b.func_147471_g(this.field_145851_c, this.field_145848_d, this.field_145849_e);
            this.func_70296_d();
        }
    }

    public Entity getClosestXPOrb() {
        double cdist = Double.MAX_VALUE;
        EntityXPOrb orb = null;
        List ents = this.field_145850_b.func_72872_a(EntityXPOrb.class, AxisAlignedBB.func_72330_a((double)this.field_145851_c, (double)this.field_145848_d, (double)this.field_145849_e, (double)(this.field_145851_c + 1), (double)(this.field_145848_d + 1), (double)(this.field_145849_e + 1)).func_72314_b(6.0, 6.0, 6.0));
        if (ents.size() > 0) {
            for (Object ent : ents) {
                EntityXPOrb eo = (EntityXPOrb)ent;
                double d = this.getDistanceTo(eo.field_70165_t, eo.field_70163_u, eo.field_70161_v);
                if (!(d < cdist)) continue;
                orb = eo;
                cdist = d;
            }
        }
        return orb;
    }

    public double getDistanceTo(double par1, double par3, double par5) {
        double var7 = (double)this.field_145851_c + 0.5 - par1;
        double var9 = (double)this.field_145848_d + 0.5 - par3;
        double var11 = (double)this.field_145849_e + 0.5 - par5;
        return var7 * var7 + var9 * var9 + var11 * var11;
    }
}

