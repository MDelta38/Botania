/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.boss.EntityWither
 *  net.minecraft.entity.monster.EntityGiantZombie
 *  net.minecraft.entity.monster.EntityPigZombie
 *  net.minecraft.entity.monster.EntitySkeleton
 *  net.minecraft.entity.monster.EntityZombie
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  thaumcraft.common.Thaumcraft
 *  thaumcraft.common.entities.monster.EntityBrainyZombie
 *  thaumcraft.common.entities.monster.EntityEldritchGuardian
 *  thaumcraft.common.entities.monster.EntityGiantBrainyZombie
 *  thaumcraft.common.entities.monster.EntityInhabitedZombie
 */
package com.kentington.thaumichorizons.common.entities;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.entities.monster.EntityBrainyZombie;
import thaumcraft.common.entities.monster.EntityEldritchGuardian;
import thaumcraft.common.entities.monster.EntityGiantBrainyZombie;
import thaumcraft.common.entities.monster.EntityInhabitedZombie;

public class EntityGravekeeper
extends EntityOcelot {
    public EntityGravekeeper(World p_i1688_1_) {
        super(p_i1688_1_);
        this.field_70714_bg.func_85156_a(((EntityAITasks.EntityAITaskEntry)this.field_70714_bg.field_75782_a.get((int)6)).field_75733_a);
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntitySkeleton.class, 0, false));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityZombie.class, 0, false));
        this.field_70715_bh.func_75776_a(4, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityWither.class, 0, false));
        this.field_70715_bh.func_75776_a(5, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPigZombie.class, 0, false));
        this.field_70715_bh.func_75776_a(6, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityGiantZombie.class, 0, false));
        this.field_70715_bh.func_75776_a(7, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityBrainyZombie.class, 0, false));
        this.field_70715_bh.func_75776_a(8, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityEldritchGuardian.class, 0, false));
        this.field_70715_bh.func_75776_a(9, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityGiantBrainyZombie.class, 0, false));
        this.field_70715_bh.func_75776_a(10, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityInhabitedZombie.class, 0, false));
    }

    public boolean func_70652_k(Entity p_70652_1_) {
        if (p_70652_1_ instanceof EntityLivingBase && ((EntityLivingBase)p_70652_1_).func_70662_br()) {
            return true;
        }
        return p_70652_1_.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 2.0f);
    }

    public String func_70005_c_() {
        return this.func_94056_bM() ? this.func_94057_bL() : (this.func_70909_n() ? StatCollector.func_74838_a((String)"entity.ThaumicHorizons.Gravekeeper.name") : super.func_70005_c_());
    }

    public void func_70629_bd() {
        super.func_70629_bd();
        List critters = this.field_70170_p.func_72872_a(EntityLivingBase.class, AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 5.0), (double)(this.field_70163_u - 5.0), (double)(this.field_70161_v - 5.0), (double)(this.field_70165_t + 5.0), (double)(this.field_70163_u + 5.0), (double)(this.field_70161_v + 5.0)));
        for (EntityLivingBase ent : critters) {
            if (!ent.func_70662_br()) continue;
            ent.func_70015_d(1);
            Thaumcraft.proxy.beam(this.field_70170_p, this.field_70165_t, this.field_70163_u + (double)(this.field_70131_O / 2.0f), this.field_70161_v, ent.field_70165_t, ent.field_70163_u + (double)(ent.field_70131_O / 2.0f), ent.field_70161_v, 0, 0xFFF144, false, 2.5f, 1);
        }
    }

    protected void func_70088_a() {
        super.func_70088_a();
        byte b0 = this.field_70180_af.func_75683_a(16);
        this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 | 4)));
    }

    public boolean func_70909_n() {
        return true;
    }
}

