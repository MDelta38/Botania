/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.pathfinding.PathEntity
 *  net.minecraft.world.World
 */
package thaumcraft.common.entities.ai.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIGolemAttackOnCollide
extends EntityAIBase {
    World worldObj;
    EntityGolemBase theGolem;
    EntityLivingBase entityTarget;
    int attackTick = 0;
    PathEntity entityPathEntity;
    private int counter;

    public AIGolemAttackOnCollide(EntityGolemBase par1EntityLiving) {
        this.theGolem = par1EntityLiving;
        this.worldObj = par1EntityLiving.field_70170_p;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        EntityLivingBase var1 = this.theGolem.func_70638_az();
        if (var1 == null) {
            return false;
        }
        if (!this.theGolem.isValidTarget((Entity)var1)) {
            this.theGolem.func_70624_b(null);
            return false;
        }
        this.entityTarget = var1;
        this.entityPathEntity = this.theGolem.func_70661_as().func_75494_a((Entity)this.entityTarget);
        return this.entityPathEntity != null;
    }

    public boolean func_75253_b() {
        return this.func_75250_a() && !this.theGolem.func_70661_as().func_75500_f();
    }

    public void func_75249_e() {
        this.theGolem.func_70661_as().func_75484_a(this.entityPathEntity, (double)this.theGolem.func_70689_ay());
        this.counter = 0;
    }

    public void func_75251_c() {
        this.entityTarget = null;
        this.theGolem.func_70661_as().func_75499_g();
    }

    public void func_75246_d() {
        this.theGolem.func_70671_ap().func_75651_a((Entity)this.entityTarget, 30.0f, 30.0f);
        if (this.theGolem.func_70635_at().func_75522_a((Entity)this.entityTarget) && --this.counter <= 0) {
            this.counter = 4 + this.theGolem.func_70681_au().nextInt(7);
            this.theGolem.func_70661_as().func_75497_a((Entity)this.entityTarget, (double)this.theGolem.func_70689_ay());
        }
        this.attackTick = Math.max(this.attackTick - 1, 0);
        double attackRange = (double)(this.entityTarget.field_70130_N * 2.0f * this.entityTarget.field_70130_N * 2.0f) + 1.0;
        if (this.theGolem.func_70092_e(this.entityTarget.field_70165_t, this.entityTarget.field_70121_D.field_72338_b, this.entityTarget.field_70161_v) <= attackRange && this.attackTick <= 0) {
            this.attackTick = this.theGolem.getAttackSpeed();
            if (this.theGolem.func_70694_bm() != null) {
                this.theGolem.func_71038_i();
            } else {
                this.theGolem.startActionTimer();
            }
            this.theGolem.func_70652_k((Entity)this.entityTarget);
        }
    }
}

