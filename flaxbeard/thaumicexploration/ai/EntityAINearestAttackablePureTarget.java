/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget$Sorter
 *  net.minecraft.entity.ai.EntityAITarget
 */
package flaxbeard.thaumicexploration.ai;

import flaxbeard.thaumicexploration.ai.EntityAINearestAttackableTargetSelectorReplacement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITarget;

public class EntityAINearestAttackablePureTarget
extends EntityAITarget {
    private final Class targetClass;
    private final int targetChance;
    private final EntityAINearestAttackableTarget.Sorter theNearestAttackableTargetSorter;
    private final IEntitySelector targetEntitySelector;
    private EntityLivingBase targetEntity;

    public EntityAINearestAttackablePureTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4) {
        this(par1EntityCreature, par2Class, par3, par4, false);
    }

    public EntityAINearestAttackablePureTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5) {
        this(par1EntityCreature, par2Class, par3, par4, par5, null);
    }

    public EntityAINearestAttackablePureTarget(EntityCreature par1EntityCreature, Class par2Class, int par3, boolean par4, boolean par5, IEntitySelector par6IEntitySelector) {
        super(par1EntityCreature, par4, par5);
        this.targetClass = par2Class;
        this.targetChance = par3;
        this.theNearestAttackableTargetSorter = new EntityAINearestAttackableTarget.Sorter((Entity)par1EntityCreature);
        this.func_75248_a(1);
        this.targetEntitySelector = new EntityAINearestAttackableTargetSelectorReplacement(this, par6IEntitySelector);
    }

    public boolean func_75296_a(EntityLivingBase par1EntityLivingBase, boolean par2) {
        if (par1EntityLivingBase.getEntityData().func_74764_b("tainted") && par1EntityLivingBase.getEntityData().func_74767_n("tainted")) {
            return false;
        }
        return super.func_75296_a(par1EntityLivingBase, par2);
    }

    public boolean func_75250_a() {
        if (this.targetChance > 0 && this.field_75299_d.func_70681_au().nextInt(this.targetChance) != 0) {
            return false;
        }
        double d0 = this.func_111175_f();
        List list = this.field_75299_d.field_70170_p.func_82733_a(this.targetClass, this.field_75299_d.field_70121_D.func_72314_b(d0, 4.0, d0), this.targetEntitySelector);
        Collections.sort(list, this.theNearestAttackableTargetSorter);
        ArrayList mobsToRemove = new ArrayList();
        for (Object mob : list) {
            if (this.func_75296_a((EntityLivingBase)mob, false)) continue;
            mobsToRemove.add(mob);
        }
        for (Object mob : mobsToRemove) {
            list.remove(mob);
        }
        if (list.isEmpty()) {
            return false;
        }
        this.targetEntity = (EntityLivingBase)list.get(0);
        return true;
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.targetEntity);
        super.func_75249_e();
    }
}

