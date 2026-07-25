/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.world.World
 *  thaumcraft.common.entities.monster.EntityTaintacleSmall
 */
package flaxbeard.thaumicexploration.entity;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import thaumcraft.common.entities.monster.EntityTaintacleSmall;

public class EntityTaintacleMinion
extends EntityTaintacleSmall {
    public EntityTaintacleMinion(World par1World) {
        super(par1World);
    }

    public void playSpawnSound() {
        this.func_85030_a("thaumcraft:tentacle", this.func_70599_aP(), this.func_70647_i());
    }

    protected Entity func_70782_k() {
        double d0 = 16.0;
        EntityLivingBase entity = null;
        List ents = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v, (double)this.field_70165_t, (double)this.field_70163_u, (double)this.field_70161_v).func_72314_b(16.0, 8.0, 16.0));
        if (ents.size() > 0) {
            double distance = Double.MAX_VALUE;
            for (Object ent : ents) {
                if (ent == null) continue;
                EntityLivingBase el = (EntityLivingBase)ent;
                double d = el.func_70068_e((Entity)this);
                if (el instanceof EntityTaintacleMinion || !(el instanceof EntityMob) || !(d < distance)) continue;
                distance = d;
                entity = el;
            }
        }
        return entity;
    }
}

