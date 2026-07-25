/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package flaxbeard.thaumicexploration.ai;

import flaxbeard.thaumicexploration.ai.EntityAINearestAttackablePureTarget;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class EntityAINearestAttackableTargetSelectorReplacement
implements IEntitySelector {
    final IEntitySelector field_111103_c;
    final EntityAINearestAttackablePureTarget field_111102_d;

    EntityAINearestAttackableTargetSelectorReplacement(EntityAINearestAttackablePureTarget par1EntityAINearestAttackableTargetNecromancy, IEntitySelector par2IEntitySelector) {
        this.field_111102_d = par1EntityAINearestAttackableTargetNecromancy;
        this.field_111103_c = par2IEntitySelector;
    }

    public boolean func_82704_a(Entity par1Entity) {
        return !(par1Entity instanceof EntityLivingBase) ? false : (this.field_111103_c != null && !this.field_111103_c.func_82704_a(par1Entity) ? false : this.field_111102_d.func_75296_a((EntityLivingBase)par1Entity, false));
    }
}

