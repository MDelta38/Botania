/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.projectile;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.EntityPech;

public class EntityPechBlast
extends EntityThrowable {
    int strength = 0;
    int duration = 0;
    boolean nightshade = false;

    public EntityPechBlast(World par1World) {
        super(par1World);
    }

    public EntityPechBlast(World par1World, EntityLivingBase par2EntityLiving, int strength, int duration, boolean nightshade) {
        super(par1World, par2EntityLiving);
        this.strength = strength;
        this.nightshade = nightshade;
        this.duration = duration;
    }

    public EntityPechBlast(World par1World, double par2, double par4, double par6, int strength, int duration, boolean nightshade) {
        super(par1World, par2, par4, par6);
        this.strength = strength;
        this.nightshade = nightshade;
        this.duration = duration;
    }

    protected float func_70185_h() {
        return 0.025f;
    }

    protected float func_70182_d() {
        return 1.5f;
    }

    public void func_70071_h_() {
        if (this.field_70170_p.field_72995_K) {
            for (int a = 0; a < 3; ++a) {
                Thaumcraft.proxy.wispFX2(this.field_70170_p, this.field_70165_t + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), this.field_70163_u + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), this.field_70161_v + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f), 0.3f, 3, true, true, 0.02f);
                double x2 = (this.field_70165_t + this.field_70169_q) / 2.0 + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f);
                double y2 = (this.field_70163_u + this.field_70167_r) / 2.0 + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f);
                double z2 = (this.field_70161_v + this.field_70166_s) / 2.0 + (double)((this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.2f);
                Thaumcraft.proxy.wispFX2(this.field_70170_p, x2, y2, z2, 0.3f, 2, true, true, 0.02f);
                Thaumcraft.proxy.sparkle((float)this.field_70165_t + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1f, (float)this.field_70163_u + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1f, (float)this.field_70161_v + (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.1f, 5);
            }
        }
        super.func_70071_h_();
        if (this.field_70173_aa > 500) {
            this.func_70106_y();
        }
    }

    protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition) {
        if (this.field_70170_p.field_72995_K) {
            for (int a = 0; a < 9; ++a) {
                float fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                float fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                float fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + (double)fx, this.field_70163_u + (double)fy, this.field_70161_v + (double)fz, this.field_70165_t + (double)(fx * 8.0f), this.field_70163_u + (double)(fy * 8.0f), this.field_70161_v + (double)(fz * 8.0f), 0.3f, 3, true, 0.02f);
                fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + (double)fx, this.field_70163_u + (double)fy, this.field_70161_v + (double)fz, this.field_70165_t + (double)(fx * 8.0f), this.field_70163_u + (double)(fy * 8.0f), this.field_70161_v + (double)(fz * 8.0f), 0.3f, 2, true, 0.02f);
                fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + (double)fx, this.field_70163_u + (double)fy, this.field_70161_v + (double)fz, this.field_70165_t + (double)(fx * 8.0f), this.field_70163_u + (double)(fy * 8.0f), this.field_70161_v + (double)(fz * 8.0f), 0.3f, 0, true, 0.02f);
            }
        }
        if (!this.field_70170_p.field_72995_K) {
            List list = this.field_70170_p.func_72839_b((Entity)this.func_85052_h(), this.field_70121_D.func_72314_b(2.0, 2.0, 2.0));
            for (int i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity)list.get(i);
                if (entity1 instanceof EntityPech || !(entity1 instanceof EntityLivingBase)) continue;
                ((EntityLivingBase)entity1).func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), (float)(this.strength + 2));
                try {
                    if (this.nightshade) {
                        ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100 + this.duration * 40, this.strength));
                        ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100 + this.duration * 40, this.strength + 1));
                        ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100 + this.duration * 40, this.strength));
                        continue;
                    }
                    switch (this.field_70146_Z.nextInt(3)) {
                        case 0: {
                            ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76436_u.field_76415_H, 100 + this.duration * 40, this.strength));
                            break;
                        }
                        case 1: {
                            ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76421_d.field_76415_H, 100 + this.duration * 40, this.strength + 1));
                            break;
                        }
                        case 2: {
                            ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 100 + this.duration * 40, this.strength));
                        }
                    }
                    continue;
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
            this.func_70106_y();
        }
    }

    public float func_70053_R() {
        return 0.1f;
    }
}

