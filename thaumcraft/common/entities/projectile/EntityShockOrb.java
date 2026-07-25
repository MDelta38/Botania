/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.util.Vec3
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.codechicken.lib.math.MathHelper;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.lib.utils.EntityUtils;

public class EntityShockOrb
extends EntityThrowable {
    public int area = 4;
    public int damage = 5;

    public EntityShockOrb(World par1World) {
        super(par1World);
    }

    public EntityShockOrb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    protected float func_70185_h() {
        return 0.05f;
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (!this.field_70170_p.field_72995_K) {
            ArrayList<Entity> list = EntityUtils.getEntitiesInRange(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, (Entity)this, Entity.class, this.area);
            for (Entity e : list) {
                if (!EntityUtils.canEntityBeSeen((Entity)this, e)) continue;
                e.func_70097_a(DamageSource.func_76354_b((Entity)this, (Entity)this.func_85052_h()), (float)this.damage);
            }
            for (int a = 0; a < 20; ++a) {
                int yy;
                int xx = MathHelper.floor_double(this.field_70165_t) + this.field_70146_Z.nextInt(this.area) - this.field_70146_Z.nextInt(this.area);
                int zz = MathHelper.floor_double(this.field_70161_v) + this.field_70146_Z.nextInt(this.area) - this.field_70146_Z.nextInt(this.area);
                for (yy = MathHelper.floor_double(this.field_70163_u) + this.area; this.field_70170_p.func_147437_c(xx, yy, zz) && yy > MathHelper.floor_double(this.field_70163_u) - this.area; --yy) {
                }
                if (!this.field_70170_p.func_147437_c(xx, yy + 1, zz) || this.field_70170_p.func_147437_c(xx, yy, zz) || this.field_70170_p.func_147439_a(xx, yy + 1, zz) == ConfigBlocks.blockAiry || !EntityUtils.canEntityBeSeen((Entity)this, (double)xx + 0.5, (double)yy + 1.5, (double)zz + 0.5)) continue;
                this.field_70170_p.func_147465_d(xx, yy + 1, zz, ConfigBlocks.blockAiry, 10, 3);
            }
        }
        Thaumcraft.proxy.burst(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, 3.0f);
        this.field_70170_p.func_72908_a(this.field_70165_t, this.field_70163_u, this.field_70161_v, "thaumcraft:shock", 1.0f, 1.0f + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f);
        this.func_70106_y();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa > 500) {
            this.func_70106_y();
        }
    }

    public float func_70053_R() {
        return 0.1f;
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
                this.field_70170_p.func_72956_a((Entity)this, "thaumcraft:zap", 1.0f, 1.0f + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.2f);
            }
            return true;
        }
        return false;
    }
}

