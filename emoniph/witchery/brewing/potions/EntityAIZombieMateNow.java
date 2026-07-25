/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.ParticleEffect;
import com.emoniph.witchery.util.SoundEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.world.World;

public class EntityAIZombieMateNow
extends EntityAIBase {
    private EntityZombie zombieObj;
    private EntityZombie mate;
    private World worldObj;
    private int matingTimeout;
    private boolean mating;
    private boolean begin;

    public EntityAIZombieMateNow(EntityZombie zombie) {
        this.zombieObj = zombie;
        this.worldObj = zombie.field_70170_p;
        this.func_75248_a(3);
    }

    public void beginMating() {
        this.begin = true;
    }

    public boolean func_75250_a() {
        if (!this.begin) {
            return false;
        }
        EntityZombie zombie = EntityUtil.findNearestEntityWithinAABB(this.worldObj, EntityZombie.class, this.zombieObj.field_70121_D.func_72314_b(8.0, 3.0, 8.0), (Entity)this.zombieObj);
        if (zombie == null || zombie.func_70631_g_()) {
            return false;
        }
        this.mate = zombie;
        return true;
    }

    public void func_75249_e() {
        this.matingTimeout = 600;
        this.mating = true;
        this.begin = false;
    }

    public void func_75251_c() {
        this.mate = null;
        this.mating = false;
        this.begin = false;
    }

    public boolean func_75253_b() {
        boolean keepGoing = this.matingTimeout >= 0;
        return keepGoing;
    }

    public void func_75246_d() {
        --this.matingTimeout;
        this.zombieObj.func_70671_ap().func_75651_a((Entity)this.mate, 10.0f, 30.0f);
        if (this.zombieObj.func_70068_e((Entity)this.mate) > 2.25) {
            this.zombieObj.func_70661_as().func_75497_a((Entity)this.mate, 1.4);
        } else if (this.matingTimeout == 0 && this.mating) {
            this.giveBirth();
        }
    }

    private void giveBirth() {
        ParticleEffect.HEART.send(SoundEffect.NONE, (Entity)this.mate, 1.0, 2.0, 8);
        this.zombieObj.func_82229_g(true);
        this.mate.func_82229_g(true);
        EntityZombie baby = new EntityZombie(this.worldObj);
        baby.func_70012_b(this.mate.field_70165_t, this.mate.field_70163_u, this.mate.field_70161_v, 0.0f, 0.0f);
        baby.func_82227_f(true);
        this.worldObj.func_72838_d((Entity)baby);
    }
}

