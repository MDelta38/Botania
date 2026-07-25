/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.ai.EntityAITarget
 */
package thaumcraft.common.entities.ai.combat;

import java.util.Comparator;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAITarget;

public class AIOldestAttackableTargetSorter
implements Comparator {
    private Entity theEntity;
    final EntityAITarget parent;

    public AIOldestAttackableTargetSorter(EntityAITarget par1EntityAIOldestAttackableTarget, Entity par2Entity) {
        this.parent = par1EntityAIOldestAttackableTarget;
        this.theEntity = par2Entity;
    }

    public int compareAge(Entity par1Entity, Entity par2Entity) {
        int var3 = par1Entity.field_70173_aa;
        int var5 = par2Entity.field_70173_aa;
        return var3 > var5 ? -1 : (var3 < var5 ? 1 : 0);
    }

    public int compare(Object par1Obj, Object par2Obj) {
        return this.compareAge((Entity)par1Obj, (Entity)par2Obj);
    }
}

