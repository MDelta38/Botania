/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAITarget
 */
package thaumcraft.common.entities.ai.combat;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import thaumcraft.common.entities.ai.combat.AIOldestAttackableTargetSorter;
import thaumcraft.common.entities.golems.EntityGolemBase;

public class AINearestButcherTarget
extends EntityAITarget {
    EntityGolemBase theGolem;
    EntityLivingBase target;
    int targetChance;
    private final IEntitySelector entitySelector;
    private float targetDistance = 0.0f;
    private AIOldestAttackableTargetSorter theOldestAttackableTargetSorter;

    public AINearestButcherTarget(EntityGolemBase par1EntityLiving, int par4, boolean par5) {
        this(par1EntityLiving, 0.0f, par4, par5, false, null);
    }

    public AINearestButcherTarget(EntityGolemBase par1, float par3, int par4, boolean par5, boolean par6, IEntitySelector par7IEntitySelector) {
        super((EntityCreature)par1, par5, par6);
        this.theGolem = par1;
        this.targetDistance = 0.0f;
        this.targetChance = par4;
        this.theOldestAttackableTargetSorter = new AIOldestAttackableTargetSorter(this, (Entity)par1);
        this.entitySelector = par7IEntitySelector;
        this.func_75248_a(3);
    }

    public boolean func_75250_a() {
        this.targetDistance = this.theGolem.getRange();
        if (this.targetChance > 0 && this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0) {
            return false;
        }
        List var5 = this.field_75299_d.field_70170_p.func_82733_a(EntityLivingBase.class, this.field_75299_d.field_70121_D.func_72314_b((double)this.targetDistance, 4.0, (double)this.targetDistance), this.entitySelector);
        Collections.sort(var5, this.theOldestAttackableTargetSorter);
        for (Entity var3 : var5) {
            EntityLivingBase var4 = (EntityLivingBase)var3;
            if (!this.theGolem.isValidTarget(var3)) continue;
            this.target = var4;
            List var55 = this.field_75299_d.field_70170_p.func_82733_a(this.target.getClass(), this.field_75299_d.field_70121_D.func_72314_b((double)this.targetDistance, 4.0, (double)this.targetDistance), this.entitySelector);
            Iterator var22 = var55.iterator();
            int count = 0;
            while (var22.hasNext()) {
                Entity var33 = (Entity)var22.next();
                if (!this.theGolem.isValidTarget(var33)) continue;
                ++count;
            }
            if (count <= 2) continue;
            return true;
        }
        return false;
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.target);
        super.func_75249_e();
    }
}

