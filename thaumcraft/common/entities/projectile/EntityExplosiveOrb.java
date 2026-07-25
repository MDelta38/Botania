/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.EntityDamageSourceIndirect
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class EntityExplosiveOrb
extends EntityThrowable {
    public float strength = 1.0f;
    public boolean onFire = false;

    public EntityExplosiveOrb(World par1World) {
        super(par1World);
    }

    public EntityExplosiveOrb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    protected float func_70185_h() {
        return 0.01f;
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (!this.field_70170_p.field_72995_K) {
            if (mop.field_72308_g != null) {
                mop.field_72308_g.func_70097_a(EntityExplosiveOrb.causeFireballDamage(this, (Entity)this.func_85052_h()), this.strength * 1.5f);
            }
            this.field_70170_p.func_72885_a((Entity)null, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.strength, this.onFire, false);
            this.func_70106_y();
        }
        this.func_70106_y();
    }

    public static DamageSource causeFireballDamage(EntityExplosiveOrb p_76362_0_, Entity p_76362_1_) {
        return p_76362_1_ == null ? new EntityDamageSourceIndirect("onFire", (Entity)p_76362_0_, (Entity)p_76362_0_).func_76361_j().func_76349_b() : new EntityDamageSourceIndirect("fireball", (Entity)p_76362_0_, p_76362_1_).func_76361_j().func_76349_b();
    }

    public float func_70053_R() {
        return 0.1f;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70170_p.field_72995_K) {
            Thaumcraft.proxy.drawGenericParticles(this.field_70170_p, this.field_70169_q + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3f), this.field_70167_r + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3f), this.field_70166_s + (double)((this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.3f), 0.0, 0.0, 0.0, 1.0f, 1.0f, 1.0f, 0.8f, false, 151, 9, 1, 7 + this.field_70146_Z.nextInt(5), 0, 2.0f + this.field_70146_Z.nextFloat());
        }
        if (this.field_70173_aa > 500) {
            this.func_70106_y();
        }
    }

    public boolean func_70097_a(DamageSource p_70097_1_, float p_70097_2_) {
        if (this.func_85032_ar()) {
            return false;
        }
        this.func_70018_K();
        if (p_70097_1_.func_76346_g() != null) {
            Vec3 vec3 = p_70097_1_.func_76346_g().func_70040_Z();
            if (vec3 != null) {
                this.field_70159_w = vec3.field_72450_a;
                this.field_70181_x = vec3.field_72448_b;
                this.field_70179_y = vec3.field_72449_c;
                this.field_70159_w *= 0.9;
                this.field_70181_x *= 0.9;
                this.field_70179_y *= 0.9;
            }
            return true;
        }
        return false;
    }
}

