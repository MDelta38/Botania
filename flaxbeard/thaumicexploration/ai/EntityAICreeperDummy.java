/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.monster.EntityCreeper
 */
package flaxbeard.thaumicexploration.ai;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.monster.EntityCreeper;

public class EntityAICreeperDummy
extends EntityAIBase {
    EntityCreeper swellingCreeper;
    EntityLivingBase creeperAttackTarget;

    public EntityAICreeperDummy(EntityCreeper par1EntityCreeper) {
        this.swellingCreeper = par1EntityCreeper;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase = this.swellingCreeper.func_70638_az();
        return this.swellingCreeper.func_70832_p() > 0 || entitylivingbase != null && this.swellingCreeper.func_70068_e((Entity)entitylivingbase) < 9.0;
    }

    public void func_75249_e() {
        this.swellingCreeper.func_70661_as().func_75499_g();
        this.creeperAttackTarget = this.swellingCreeper.func_70638_az();
    }

    public void func_75251_c() {
        this.creeperAttackTarget = null;
    }

    public void func_75246_d() {
        this.swellingCreeper.func_70829_a(-1);
    }
}

