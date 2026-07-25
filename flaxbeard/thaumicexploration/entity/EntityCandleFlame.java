/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityBlaze
 *  net.minecraft.entity.projectile.EntityThrowable
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.MovingObjectPosition
 *  net.minecraft.world.World
 */
package flaxbeard.thaumicexploration.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCandleFlame
extends EntityThrowable {
    private EntityLivingBase thrower;

    public EntityCandleFlame(World par1World, EntityLivingBase par2EntityLivingBase) {
        super(par1World, par2EntityLivingBase);
        float f = 0.4f;
        this.field_70159_w = 0.1 * (double)(-MathHelper.func_76126_a((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI)) * f);
        this.field_70179_y = 0.1 * (double)(MathHelper.func_76134_b((float)(this.field_70177_z / 180.0f * (float)Math.PI)) * MathHelper.func_76134_b((float)(this.field_70125_A / 180.0f * (float)Math.PI)) * f);
        this.field_70181_x = 0.1 * (double)(-MathHelper.func_76126_a((float)((this.field_70125_A + this.func_70183_g()) / 180.0f * (float)Math.PI)) * f);
        this.func_70186_c(this.field_70159_w, this.field_70181_x, this.field_70179_y, this.func_70182_d(), 1.0f);
    }

    protected void func_70184_a(MovingObjectPosition par1MovingObjectPosition) {
        if (par1MovingObjectPosition.field_72308_g != null) {
            int b0 = 0;
            if (par1MovingObjectPosition.field_72308_g instanceof EntityBlaze) {
                b0 = 3;
            }
            par1MovingObjectPosition.field_72308_g.func_70097_a(DamageSource.func_76356_a((Entity)this, (Entity)this.func_85052_h()), (float)b0);
        }
        for (int i = 0; i < 8; ++i) {
            this.field_70170_p.func_72869_a("snowballpoof", this.field_70165_t, this.field_70163_u, this.field_70161_v, 0.0, 0.0, 0.0);
        }
        if (!this.field_70170_p.field_72995_K) {
            this.func_70106_y();
        }
    }
}

