/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.IEntityLivingData
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.AxisAlignedBB
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.item.ItemEarmuffs;
import com.emoniph.witchery.util.EntityUtil;
import com.emoniph.witchery.util.TimeUtil;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityBanshee
extends EntitySummonedUndead {
    public EntityBanshee(World par1World) {
        super(par1World);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75504_d(true);
        this.func_70661_as().func_75498_b(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 0.3, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.2);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public void func_70636_d() {
        super.func_70636_d();
        boolean startedScreaming = false;
        if (!this.field_70170_p.field_72995_K && (TimeUtil.secondsElapsed(5, this.field_70173_aa) || this.isScreaming() && TimeUtil.ticksElapsed(20, this.field_70173_aa))) {
            double RADIUS = 6.0;
            double RADIUS_SQ = 36.0;
            AxisAlignedBB bounds = AxisAlignedBB.func_72330_a((double)(this.field_70165_t - 6.0), (double)(this.field_70163_u - 6.0), (double)(this.field_70161_v - 6.0), (double)(this.field_70165_t + 6.0), (double)(this.field_70163_u + 6.0), (double)(this.field_70161_v + 6.0));
            List players = this.field_70170_p.func_72872_a(EntityLivingBase.class, bounds);
            boolean playersFound = false;
            for (Object obj : players) {
                EntityLivingBase player = (EntityLivingBase)obj;
                if (!(this.func_70068_e((Entity)player) <= 36.0) || player != this.func_70638_az() && player != this.field_70789_a && !(player instanceof EntityPlayer)) continue;
                playersFound = true;
                if (!this.isScreaming()) {
                    this.setScreaming(true);
                    startedScreaming = true;
                }
                if (player instanceof EntityPlayer && ItemEarmuffs.isHelmWorn((EntityPlayer)player)) continue;
                float maxHealth = player.func_110138_aP();
                boolean flag = EntityUtil.touchOfDeath((Entity)player, (EntityLivingBase)this, Math.max(0.1f * maxHealth, 1.0f));
            }
            if (!playersFound && this.isScreaming()) {
                this.setScreaming(false);
            }
        }
        if ((startedScreaming || TimeUtil.secondsElapsed(3, this.field_70173_aa)) && this.isScreaming()) {
            this.func_85030_a("witchery:mob.banshee.banshee_scream", 1.0f, this.field_70170_p.field_73012_v.nextFloat() * 0.3f + 0.7f);
        }
    }

    public void func_70071_h_() {
        super.func_70071_h_();
    }

    public boolean func_70652_k(Entity par1Entity) {
        boolean flag = super.func_70652_k(par1Entity);
        return flag;
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected String func_70621_aR() {
        return "witchery:mob.spectre.spectre_hit";
    }

    protected String func_70673_aS() {
        return "witchery:mob.spectre.spectre_hit";
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.banshee.name");
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
    }

    public IEntityLivingData func_110161_a(IEntityLivingData par1EntityLivingData) {
        IEntityLivingData par1EntityLivingData1 = super.func_110161_a(par1EntityLivingData);
        return par1EntityLivingData1;
    }
}

