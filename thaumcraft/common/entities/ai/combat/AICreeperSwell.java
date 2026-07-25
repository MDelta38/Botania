/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 */
package thaumcraft.common.entities.ai.combat;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import thaumcraft.common.entities.monster.EntityTaintCreeper;

public class AICreeperSwell
extends EntityAIBase {
    EntityTaintCreeper swellingCreeper;
    EntityLivingBase creeperAttackTarget;

    public AICreeperSwell(EntityTaintCreeper par1EntityCreeper) {
        this.swellingCreeper = par1EntityCreeper;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        EntityLivingBase entitylivingbase = this.swellingCreeper.func_70638_az();
        return this.swellingCreeper.getCreeperState() > 0 || entitylivingbase != null && this.swellingCreeper.func_70068_e((Entity)entitylivingbase) < 9.0;
    }

    public void func_75249_e() {
        this.swellingCreeper.func_70661_as().func_75499_g();
        this.creeperAttackTarget = this.swellingCreeper.func_70638_az();
    }

    public void func_75251_c() {
        this.creeperAttackTarget = null;
    }

    public void func_75246_d() {
        if (this.creeperAttackTarget == null) {
            this.swellingCreeper.setCreeperState(-1);
        } else if (this.swellingCreeper.func_70068_e((Entity)this.creeperAttackTarget) > 49.0) {
            this.swellingCreeper.setCreeperState(-1);
        } else if (!this.swellingCreeper.func_70635_at().func_75522_a((Entity)this.creeperAttackTarget)) {
            this.swellingCreeper.setCreeperState(-1);
        } else {
            this.swellingCreeper.setCreeperState(1);
        }
    }
}

