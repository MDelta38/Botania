/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.entities.ai.combat;

import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.entities.ai.combat.AITarget;

public class AIHurtByTarget
extends AITarget {
    boolean field_75312_a;
    EntityCreature entityPathNavigate;

    public AIHurtByTarget(EntityCreature par1EntityLiving, boolean par2) {
        super(par1EntityLiving, 16.0f, false);
        this.field_75312_a = par2;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        return this.isSuitableTarget(this.taskOwner.func_70643_av(), false);
    }

    @Override
    public boolean func_75253_b() {
        return this.taskOwner.func_70643_av() != null && this.taskOwner.func_70643_av() != this.entityPathNavigate;
    }

    @Override
    public void func_75249_e() {
        this.taskOwner.func_70624_b(this.taskOwner.func_70643_av());
        if (this.field_75312_a) {
            List var1 = this.taskOwner.field_70170_p.func_72872_a(this.taskOwner.getClass(), AxisAlignedBB.func_72330_a((double)this.taskOwner.field_70165_t, (double)this.taskOwner.field_70163_u, (double)this.taskOwner.field_70161_v, (double)(this.taskOwner.field_70165_t + 1.0), (double)(this.taskOwner.field_70163_u + 1.0), (double)(this.taskOwner.field_70161_v + 1.0)).func_72314_b((double)this.targetDistance, 4.0, (double)this.targetDistance));
            for (EntityLiving var3 : var1) {
                if (this.taskOwner == var3 || var3.func_70638_az() != null) continue;
                var3.func_70624_b(this.taskOwner.func_70643_av());
            }
        }
        super.func_75249_e();
    }

    @Override
    public void func_75251_c() {
        if (this.taskOwner.func_70638_az() != null && this.taskOwner.func_70638_az() instanceof EntityPlayer && ((EntityPlayer)this.taskOwner.func_70638_az()).field_71075_bZ.field_75102_a) {
            this.taskOwner.func_70624_b(null);
            super.func_75251_c();
        }
    }
}

