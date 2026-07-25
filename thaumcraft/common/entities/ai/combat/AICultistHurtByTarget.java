/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.ai.EntityAITarget
 *  net.minecraft.util.AxisAlignedBB
 */
package thaumcraft.common.entities.ai.combat;

import java.util.List;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.util.AxisAlignedBB;
import thaumcraft.common.entities.monster.EntityCultist;
import thaumcraft.common.entities.monster.EntityCultistCleric;

public class AICultistHurtByTarget
extends EntityAITarget {
    boolean entityCallsForHelp;
    private int field_142052_b;
    private static final String __OBFID = "CL_00001619";

    public AICultistHurtByTarget(EntityCreature p_i1660_1_, boolean p_i1660_2_) {
        super(p_i1660_1_, false);
        this.entityCallsForHelp = p_i1660_2_;
        this.func_75248_a(1);
    }

    public boolean func_75250_a() {
        int i = this.field_75299_d.func_142015_aE();
        return i != this.field_142052_b && this.func_75296_a(this.field_75299_d.func_70643_av(), false);
    }

    public void func_75249_e() {
        this.field_75299_d.func_70624_b(this.field_75299_d.func_70643_av());
        this.field_142052_b = this.field_75299_d.func_142015_aE();
        if (this.entityCallsForHelp) {
            double d0 = this.func_111175_f();
            List list = this.field_75299_d.field_70170_p.func_72872_a(EntityCultist.class, AxisAlignedBB.func_72330_a((double)this.field_75299_d.field_70165_t, (double)this.field_75299_d.field_70163_u, (double)this.field_75299_d.field_70161_v, (double)(this.field_75299_d.field_70165_t + 1.0), (double)(this.field_75299_d.field_70163_u + 1.0), (double)(this.field_75299_d.field_70161_v + 1.0)).func_72314_b(d0, 10.0, d0));
            for (EntityCreature entitycreature : list) {
                if (this.field_75299_d == entitycreature || entitycreature.func_70638_az() != null || entitycreature.func_142014_c(this.field_75299_d.func_70643_av())) continue;
                if (entitycreature instanceof EntityCultistCleric && ((EntityCultistCleric)entitycreature).getIsRitualist()) {
                    if (this.field_75299_d.field_70170_p.field_73012_v.nextInt(3) != 0) continue;
                    ((EntityCultistCleric)entitycreature).setIsRitualist(false);
                    entitycreature.func_70624_b(this.field_75299_d.func_70643_av());
                    continue;
                }
                entitycreature.func_70624_b(this.field_75299_d.func_70643_av());
            }
        }
        super.func_75249_e();
    }
}

