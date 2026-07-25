/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.material.Material
 *  net.minecraft.command.IEntitySelector
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityAgeable
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIOwnerHurtTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.passive.EntityTameable
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.init.Blocks
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.util.BlockUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.command.IEntitySelector;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityMindrake
extends EntityTameable
implements IEntitySelector {
    public EntityMindrake(World world) {
        super(world);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAIOwnerHurtTarget((EntityTameable)this));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70715_bh.func_75776_a(3, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, false, true, (IEntitySelector)this));
        this.field_70728_aV = 0;
        this.func_70105_a(0.6f, 0.8f);
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.mindrake.name");
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(4.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4);
    }

    public boolean func_70650_aV() {
        return true;
    }

    public int func_82143_as() {
        return this.func_70638_az() == null ? 3 : 3 + (int)(this.func_110143_aJ() - 1.0f);
    }

    protected void func_70088_a() {
        super.func_70088_a();
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected String func_70621_aR() {
        return "mob.ghast.scream";
    }

    protected String func_70673_aS() {
        return "mob.ghast.death";
    }

    public boolean func_82704_a(Entity target) {
        if (!this.func_70909_n()) {
            return target instanceof EntityPlayer;
        }
        return false;
    }

    public boolean func_70652_k(Entity entity) {
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.5f, false);
            this.func_70106_y();
            Block block = BlockUtil.getBlock(this.field_70170_p, this.field_70165_t, this.field_70121_D.field_72338_b - 1.0, this.field_70161_v);
            if (block != null && (block.func_149688_o() == Material.field_151577_b || block.func_149688_o() == Material.field_151578_c)) {
                BlockUtil.setBlock(this.field_70170_p, this.field_70165_t, this.field_70121_D.field_72338_b, this.field_70161_v, (Block)(this.field_70146_Z.nextInt(2) == 0 ? Blocks.field_150328_O : Blocks.field_150327_N));
            }
        }
        return true;
    }

    public void func_70645_a(DamageSource par1DamageSource) {
        super.func_70645_a(par1DamageSource);
        if (!this.field_70170_p.field_72995_K) {
            this.field_70170_p.func_72876_a((Entity)this, this.field_70165_t, this.field_70163_u, this.field_70161_v, 1.0f, false);
        }
    }

    protected void func_70628_a(boolean par1, int par2) {
        this.func_70099_a(new ItemStack(Witchery.Items.SEEDS_MANDRAKE, this.field_70170_p.field_73012_v.nextDouble() <= 0.25 ? 2 : 1), 0.0f);
    }

    public EntityAgeable func_90011_a(EntityAgeable var1) {
        return null;
    }
}

