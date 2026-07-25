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
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AIDartAttack
extends EntityAIBase {
    private final EntityGolemBase theGolem;
    private EntityLivingBase attackTarget;
    private int rangedAttackTime = 0;
    private int maxRangedAttackTime;

    public AIDartAttack(EntityGolemBase par1IRangedAttackMob) {
        this.theGolem = par1IRangedAttackMob;
        this.maxRangedAttackTime = 30 - this.theGolem.getUpgradeAmount(0) * 8;
        this.rangedAttackTime = this.maxRangedAttackTime / 2;
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
        double ra = this.theGolem.func_70092_e(var1.field_70165_t, var1.field_70121_D.field_72338_b, var1.field_70161_v);
        if (ra < 9.0) {
            return false;
        }
        this.attackTarget = var1;
        return true;
    }

    public boolean func_75253_b() {
        return this.func_75250_a() && !this.theGolem.func_70661_as().func_75500_f();
    }

    public void func_75251_c() {
        this.attackTarget = null;
        this.rangedAttackTime = this.maxRangedAttackTime / 2;
    }

    public void func_75246_d() {
        double var1 = this.theGolem.func_70092_e(this.attackTarget.field_70165_t, this.attackTarget.field_70121_D.field_72338_b, this.attackTarget.field_70161_v);
        boolean var3 = this.theGolem.func_70635_at().func_75522_a((Entity)this.attackTarget);
        this.theGolem.func_70661_as().func_75497_a((Entity)this.attackTarget, (double)this.theGolem.func_70689_ay());
        if (var3) {
            this.theGolem.func_70671_ap().func_75651_a((Entity)this.attackTarget, 30.0f, 30.0f);
            this.rangedAttackTime = Math.max(this.rangedAttackTime - 1, 0);
            if (this.rangedAttackTime <= 0) {
                float r = this.theGolem.getRange() * 0.8f;
                if (var1 <= (double)(r *= r) && var3) {
                    this.theGolem.attackEntityWithRangedAttack(this.attackTarget);
                    this.rangedAttackTime = this.maxRangedAttackTime;
                }
            }
        }
    }
}

