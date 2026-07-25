/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAIHurtByTarget
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.potion.Potion
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.item.ItemEarmuffs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityMandrake
extends EntityMob {
    public EntityMandrake(World world) {
        super(world);
        this.func_70661_as().func_75491_a(true);
        this.func_70661_as().func_75495_e(true);
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAIHurtByTarget((EntityCreature)this, false));
        this.field_70728_aV = 0;
        this.func_70105_a(0.6f, 0.9f);
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.mandrake.name");
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.65);
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

    protected String func_70639_aQ() {
        return "mob.ghast.scream";
    }

    protected String func_70621_aR() {
        return "mob.ghast.scream";
    }

    protected String func_70673_aS() {
        return "mob.ghast.death";
    }

    public boolean func_70652_k(Entity entity) {
        EntityPlayer player;
        if (!this.field_70170_p.field_72995_K && entity instanceof EntityPlayer && !(player = (EntityPlayer)entity).func_70644_a(Potion.field_76431_k) && !ItemEarmuffs.isHelmWorn(player)) {
            player.func_70690_d(new PotionEffect(Potion.field_76431_k.field_76415_H, 300, 1));
        }
        return true;
    }

    protected void func_70628_a(boolean par1, int par2) {
        this.func_70099_a(Witchery.Items.GENERIC.itemMandrakeRoot.createStack(), 0.0f);
        this.func_70099_a(new ItemStack(Witchery.Items.SEEDS_MANDRAKE, this.field_70170_p.field_73012_v.nextDouble() <= 0.25 ? 2 : 1), 0.0f);
    }
}

