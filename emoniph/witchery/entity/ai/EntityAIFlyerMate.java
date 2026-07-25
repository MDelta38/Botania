/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.item.EntityXPOrb
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity.ai;

import java.util.List;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIFlyerMate
extends EntityAIBase {
    private EntityAnimal theAnimal;
    World theWorld;
    private EntityAnimal targetMate;
    int spawnBabyDelay;
    int updateDelay = 0;
    double moveSpeed;

    public EntityAIFlyerMate(EntityAnimal par1EntityAnimal, double par2) {
        this.theAnimal = par1EntityAnimal;
        this.theWorld = par1EntityAnimal.field_70170_p;
        this.moveSpeed = par2;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        if (!this.theAnimal.func_70880_s()) {
            return false;
        }
        this.targetMate = this.getNearbyMate();
        return this.targetMate != null;
    }

    public boolean func_75253_b() {
        return this.targetMate.func_70089_S() && this.targetMate.func_70880_s() && this.spawnBabyDelay < 60;
    }

    public void func_75251_c() {
        this.targetMate = null;
        this.spawnBabyDelay = 0;
        this.updateDelay = 0;
    }

    public void func_75246_d() {
        if (--this.updateDelay <= 0) {
            double d0 = this.targetMate.field_70165_t - this.theAnimal.field_70165_t;
            double d1 = this.targetMate.field_70163_u - this.theAnimal.field_70163_u;
            double d2 = this.targetMate.field_70161_v - this.theAnimal.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (this.isCourseTraversable(this.targetMate.field_70165_t, this.targetMate.field_70163_u, this.targetMate.field_70161_v, d3 = (double)MathHelper.func_76133_a((double)d3))) {
                this.theAnimal.field_70159_w += d0 / d3 * 0.25;
                this.theAnimal.field_70181_x += d1 / d3 * 0.25;
                this.theAnimal.field_70179_y += d2 / d3 * 0.25;
            }
            this.updateDelay = 10;
        }
        this.theAnimal.field_70761_aq = this.theAnimal.field_70177_z = -((float)Math.atan2(this.theAnimal.field_70159_w, this.theAnimal.field_70179_y)) * 180.0f / (float)Math.PI;
        ++this.spawnBabyDelay;
        if (this.spawnBabyDelay >= 60 && this.theAnimal.func_70068_e((Entity)this.targetMate) < 9.0) {
            this.spawnBaby();
        }
    }

    private boolean isCourseTraversable(double par1, double par3, double par5, double par7) {
        double d4 = (par1 - this.theAnimal.field_70165_t) / par7;
        double d5 = (par3 - this.theAnimal.field_70163_u) / par7;
        double d6 = (par5 - this.theAnimal.field_70161_v) / par7;
        AxisAlignedBB axisalignedbb = this.theAnimal.field_70121_D.func_72329_c();
        int i = 1;
        while ((double)i < par7) {
            axisalignedbb.func_72317_d(d4, d5, d6);
            if (!this.theAnimal.field_70170_p.func_72945_a((Entity)this.theAnimal, axisalignedbb).isEmpty()) {
                return false;
            }
            ++i;
        }
        return true;
    }

    private EntityAnimal getNearbyMate() {
        float f = 8.0f;
        List list = this.theWorld.func_72872_a(this.theAnimal.getClass(), this.theAnimal.field_70121_D.func_72314_b((double)f, (double)f, (double)f));
        double d0 = Double.MAX_VALUE;
        EntityAnimal entityanimal = null;
        for (EntityAnimal entityanimal1 : list) {
            if (!this.theAnimal.func_70878_b(entityanimal1) || !(this.theAnimal.func_70068_e((Entity)entityanimal1) < d0)) continue;
            entityanimal = entityanimal1;
            d0 = this.theAnimal.func_70068_e((Entity)entityanimal1);
        }
        return entityanimal;
    }

    private void spawnBaby() {
        EntityAgeable entityageable = this.theAnimal.func_90011_a((EntityAgeable)this.targetMate);
        if (entityageable != null) {
            this.theAnimal.func_70873_a(6000);
            this.targetMate.func_70873_a(6000);
            this.theAnimal.func_70875_t();
            this.targetMate.func_70875_t();
            entityageable.func_70873_a(-24000);
            entityageable.func_70012_b(this.theAnimal.field_70165_t, this.theAnimal.field_70163_u, this.theAnimal.field_70161_v, 0.0f, 0.0f);
            this.theWorld.func_72838_d((Entity)entityageable);
            Random random = this.theAnimal.func_70681_au();
            for (int i = 0; i < 7; ++i) {
                double d0 = random.nextGaussian() * 0.02;
                double d1 = random.nextGaussian() * 0.02;
                double d2 = random.nextGaussian() * 0.02;
                this.theWorld.func_72869_a("heart", this.theAnimal.field_70165_t + (double)(random.nextFloat() * this.theAnimal.field_70130_N * 2.0f) - (double)this.theAnimal.field_70130_N, this.theAnimal.field_70163_u + 0.5 + (double)(random.nextFloat() * this.theAnimal.field_70131_O), this.theAnimal.field_70161_v + (double)(random.nextFloat() * this.theAnimal.field_70130_N * 2.0f) - (double)this.theAnimal.field_70130_N, d0, d1, d2);
            }
            this.theWorld.func_72838_d((Entity)new EntityXPOrb(this.theWorld, this.theAnimal.field_70165_t, this.theAnimal.field_70163_u, this.theAnimal.field_70161_v, random.nextInt(7) + 1));
        }
    }
}

