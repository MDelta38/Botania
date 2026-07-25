/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIFollowOwner
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILeapAtTarget
 *  net.minecraft.entity.ai.EntityAIMate
 *  net.minecraft.entity.ai.EntityAIOwnerHurtByTarget
 *  net.minecraft.entity.ai.EntityAIOwnerHurtTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.passive.EntityAnimal
 *  net.minecraft.entity.passive.EntityOcelot
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.entities.IEntityInfusedStats;
import java.util.ArrayList;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAIMate;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityGuardianPanther
extends EntityOcelot
implements IEntityInfusedStats {
    public EntityGuardianPanther(World p_i1688_1_) {
        super(p_i1688_1_);
        this.func_70105_a(1.2f, 1.6f);
        ArrayList<EntityAITasks.EntityAITaskEntry> toRemove = new ArrayList<EntityAITasks.EntityAITaskEntry>();
        for (EntityAITasks.EntityAITaskEntry task : this.field_70714_bg.field_75782_a) {
            toRemove.add(task);
        }
        for (EntityAITasks.EntityAITaskEntry task : toRemove) {
            this.field_70714_bg.func_85156_a(task.field_75733_a);
        }
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)this.field_70911_d);
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAILeapAtTarget((EntityLiving)this, 0.5f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, true));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIFollowOwner((EntityTameable)this, 1.0, 10.0f, 5.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAIMate((EntityAnimal)this, 0.8));
        this.field_70714_bg.func_75776_a(7, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(8, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 10.0f));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIOwnerHurtByTarget((EntityTameable)this));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIOwnerHurtTarget((EntityTameable)this));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(24.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5000000119209289);
    }

    public boolean func_70085_c(EntityPlayer p_70085_1_) {
        ItemStack itemstack = p_70085_1_.field_71071_by.func_70448_g();
        if (itemstack != null && itemstack.func_77973_b() instanceof ItemFood) {
            ItemFood itemfood = (ItemFood)itemstack.func_77973_b();
            if (this.func_110143_aJ() < this.func_110138_aP()) {
                --itemstack.field_77994_a;
                this.func_70691_i(itemfood.func_150905_g(itemstack));
                if (itemstack.field_77994_a <= 0) {
                    p_70085_1_.field_71071_by.func_70299_a(p_70085_1_.field_71071_by.field_70461_c, (ItemStack)null);
                }
                return true;
            }
        }
        return super.func_70085_c(p_70085_1_);
    }

    public boolean func_70652_k(Entity p_70652_1_) {
        return p_70652_1_.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), 6.0f);
    }

    public String func_70005_c_() {
        return this.func_94056_bM() ? this.func_94057_bL() : (this.func_70909_n() ? StatCollector.func_74838_a((String)"entity.ThaumicHorizons.GuardianPanther.name") : super.func_70005_c_());
    }

    public void func_70629_bd() {
        super.func_70629_bd();
    }

    protected void func_70088_a() {
        super.func_70088_a();
        byte b0 = this.field_70180_af.func_75683_a(16);
        this.field_70180_af.func_75692_b(16, (Object)((byte)(b0 | 4)));
    }

    public boolean func_70909_n() {
        return true;
    }

    @Override
    public void resetStats() {
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(24.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.5000000119209289);
    }
}

