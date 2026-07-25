/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
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
 *  net.minecraft.entity.ai.EntityAIMoveTowardsTarget
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.EnumDifficulty
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.entity.EntitySummonedUndead;
import com.emoniph.witchery.util.EntityUtil;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySpectre
extends EntitySummonedUndead {
    public EntitySpectre(World par1World) {
        super(par1World);
        this.func_70661_as().func_75491_a(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, EntityLivingBase.class, 1.0, true));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIMoveTowardsTarget((EntityCreature)this, 1.0, 32.0f));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWander((EntityCreature)this, 1.0));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(6, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111265_b).func_111128_a(40.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(4.0);
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
    }

    public int func_70658_aO() {
        int i = super.func_70658_aO() + 2;
        if (i > 20) {
            i = 20;
        }
        return i;
    }

    protected boolean func_70650_aV() {
        return true;
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    public void func_70071_h_() {
        super.func_70071_h_();
    }

    public boolean func_70652_k(Entity par1Entity) {
        boolean flag;
        float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        int i = 0;
        if (par1Entity instanceof EntityLivingBase) {
            EntityLivingBase living = (EntityLivingBase)par1Entity;
            float maxHealth = living.func_110138_aP();
            f = Math.max(maxHealth * 0.15f, 1.0f);
        }
        if (par1Entity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)par1Entity));
            i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)par1Entity));
        }
        if (flag = EntityUtil.touchOfDeath(par1Entity, (EntityLivingBase)this, f)) {
            int j;
            if (i > 0) {
                par1Entity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                this.field_70159_w *= 0.6;
                this.field_70179_y *= 0.6;
            }
            if ((j = EnchantmentHelper.func_90036_a((EntityLivingBase)this)) > 0) {
                par1Entity.func_70015_d(j * 4);
            }
        }
        return flag;
    }

    protected String func_70639_aQ() {
        return "witchery:mob.spectre.spectre_say";
    }

    protected String func_70621_aR() {
        return "witchery:mob.spectre.spectre_die";
    }

    protected String func_70673_aS() {
        return "witchery:mob.spectre.spectre_die";
    }

    protected void func_82164_bB() {
        if (this.field_70146_Z.nextFloat() < 0.15f * this.field_70170_p.func_147462_b(this.field_70165_t, this.field_70163_u, this.field_70161_v)) {
            float f;
            int i = this.field_70146_Z.nextInt(2);
            float f2 = f = this.field_70170_p.field_73013_u == EnumDifficulty.HARD ? 0.1f : 0.25f;
            if (this.field_70146_Z.nextFloat() < 0.095f) {
                ++i;
            }
            if (this.field_70146_Z.nextFloat() < 0.095f) {
                ++i;
            }
            if (this.field_70146_Z.nextFloat() < 0.095f) {
                ++i;
            }
            for (int j = 3; j >= 2; --j) {
                Item item;
                ItemStack itemstack = this.func_130225_q(j);
                if (j < 3 && this.field_70146_Z.nextFloat() < f) break;
                if (itemstack != null || (item = EntitySpectre.func_82161_a((int)(j + 1), (int)i)) == null) continue;
                this.func_70062_b(j + 1, new ItemStack(item));
            }
        }
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.spectre.name");
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
        this.func_82164_bB();
        this.func_82162_bC();
        this.setObscured(true);
        return par1EntityLivingData1;
    }
}

