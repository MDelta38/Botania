/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
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
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;

public class EntityEldritchOrb
extends EntityThrowable {
    public EntityEldritchOrb(World par1World) {
        super(par1World);
    }

    public EntityEldritchOrb(World par1World, EntityLivingBase par2EntityLiving) {
        super(par1World, par2EntityLiving);
    }

    protected float func_70185_h() {
        return 0.0f;
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (this.field_70173_aa > 100) {
            this.func_70106_y();
        }
    }

    public void func_70103_a(byte b) {
        if (b == 16) {
            if (this.field_70170_p.field_72995_K) {
                for (int a = 0; a < 30; ++a) {
                    float fx = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                    float fy = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                    float fz = (this.field_70170_p.field_73012_v.nextFloat() - this.field_70170_p.field_73012_v.nextFloat()) * 0.3f;
                    Thaumcraft.proxy.wispFX3(this.field_70170_p, this.field_70165_t + (double)fx, this.field_70163_u + (double)fy, this.field_70161_v + (double)fz, this.field_70165_t + (double)(fx * 8.0f), this.field_70163_u + (double)(fy * 8.0f), this.field_70161_v + (double)(fz * 8.0f), 0.3f, 5, true, 0.02f);
                }
            }
        } else {
            super.func_70103_a(b);
        }
    }

    protected void func_70184_a(MovingObjectPosition mop) {
        if (!this.field_70170_p.field_72995_K && this.func_85052_h() != null) {
            List list = this.field_70170_p.func_72839_b((Entity)this.func_85052_h(), this.field_70121_D.func_72314_b(2.0, 2.0, 2.0));
            for (int i = 0; i < list.size(); ++i) {
                Entity entity1 = (Entity)list.get(i);
                if (!(entity1 instanceof EntityLivingBase) || ((EntityLivingBase)entity1).func_70662_br()) continue;
                ((EntityLivingBase)entity1).func_70097_a(DamageSource.func_76354_b((Entity)this, (Entity)this.func_85052_h()), (float)this.func_85052_h().func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e() * 0.666f);
                try {
                    ((EntityLivingBase)entity1).func_70690_d(new PotionEffect(Potion.field_76437_t.field_76415_H, 160, 0));
                    continue;
                }
                catch (Exception e) {
                    // empty catch block
                }
            }
            this.field_70170_p.func_72956_a((Entity)this, "random.fizz", 0.5f, 2.6f + (this.field_70146_Z.nextFloat() - this.field_70146_Z.nextFloat()) * 0.8f);
            this.field_70173_aa = 100;
            this.field_70170_p.func_72960_a((Entity)this, (byte)16);
        }
    }

    public float func_70053_R() {
        return 0.1f;
    }
}

