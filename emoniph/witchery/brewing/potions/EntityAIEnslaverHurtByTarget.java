/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAITarget
 *  net.minecraft.entity.player.EntityPlayer
 */
package com.emoniph.witchery.brewing.potions;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.brewing.potions.PotionEnslaved;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAIEnslaverHurtByTarget
extends EntityAITarget {
    EntityCreature enslavedEntity;
    EntityLivingBase enslaversAttacker;
    private int enslaversRevengeTimer;

    public EntityAIEnslaverHurtByTarget(EntityCreature enslavedCreature) {
        super(enslavedCreature, false);
        this.enslavedEntity = enslavedCreature;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        if (!this.enslavedEntity.func_70644_a(Witchery.Potions.ENSLAVED)) {
            return false;
        }
        String ownerName = PotionEnslaved.getMobEnslaverName((EntityLiving)this.enslavedEntity);
        if (ownerName == null || ownerName.isEmpty()) {
            return false;
        }
        EntityPlayer enslaver = this.enslavedEntity.field_70170_p.func_72924_a(ownerName);
        if (enslaver == null) {
            return false;
        }
        this.enslaversAttacker = enslaver.func_70643_av();
        int revengeTimer = enslaver.func_142015_aE();
        if (revengeTimer == this.enslaversRevengeTimer) {
            return false;
        }
        if (this.enslaversAttacker == null) {
            return false;
        }
        return this.func_75296_a(this.enslaversAttacker, false);
    }

    public void func_75249_e() {
        EntityUtil.setTarget((EntityLiving)this.field_75299_d, this.enslaversAttacker);
        String enslaverName = PotionEnslaved.getMobEnslaverName((EntityLiving)this.enslavedEntity);
        EntityPlayer enslaver = this.enslavedEntity.field_70170_p.func_72924_a(enslaverName);
        if (enslaver != null) {
            this.enslaversRevengeTimer = enslaver.func_142015_aE();
        }
        super.func_75249_e();
    }
}

