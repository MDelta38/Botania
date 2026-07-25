/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.common.network.simpleimpl.IMessage
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIAttackOnCollide
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAILookIdle
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAISwimming
 *  net.minecraft.entity.ai.EntityAIWander
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.monster.EntityMob
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.Witchery;
import com.emoniph.witchery.network.PacketSound;
import com.emoniph.witchery.util.SoundEffect;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public abstract class EntityIllusion
extends EntityMob {
    private EntityPlayer victimPlayer = null;

    public EntityIllusion(World world) {
        super(world);
        this.field_70178_ae = true;
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISwimming((EntityLiving)this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIAttackOnCollide((EntityCreature)this, 1.0, false));
        this.field_70714_bg.func_75776_a(3, (EntityAIBase)new EntityAIWander((EntityCreature)this, 0.8));
        this.field_70714_bg.func_75776_a(4, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 8.0f));
        this.field_70714_bg.func_75776_a(5, (EntityAIBase)new EntityAILookIdle((EntityLiving)this));
        this.field_70715_bh.func_75776_a(1, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.illusion.name");
    }

    protected SoundEffect getFakeLivingSound() {
        return SoundEffect.NONE;
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.25);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(0.0);
    }

    public boolean func_70650_aV() {
        return true;
    }

    public int func_70627_aG() {
        return super.func_70627_aG() * 2;
    }

    public EntityLivingBase func_70638_az() {
        return this.field_70170_p.func_72924_a(this.getVictimName());
    }

    public int func_82143_as() {
        return this.func_70638_az() == null ? 3 : 3 + (int)(this.func_110143_aJ() - 1.0f);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(17, (Object)"");
        this.field_70180_af.func_75682_a(18, (Object)0);
    }

    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        if (this.getVictimName() == null) {
            par1NBTTagCompound.func_74778_a("Victim", "");
        } else {
            par1NBTTagCompound.func_74778_a("Victim", this.getVictimName());
        }
        par1NBTTagCompound.func_74768_a("IllusionType", this.getIllusionType());
    }

    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        String s = par1NBTTagCompound.func_74779_i("Victim");
        if (s.length() > 0) {
            this.setVictim(s);
        }
        this.setIllusionType(par1NBTTagCompound.func_74762_e("IllusionType"));
    }

    public String getVictimName() {
        return this.field_70180_af.func_75681_e(17);
    }

    public void setVictim(String par1Str) {
        this.field_70180_af.func_75692_b(17, (Object)par1Str);
    }

    public int getIllusionType() {
        return this.field_70180_af.func_75683_a(18);
    }

    public void setIllusionType(int par1) {
        this.field_70180_af.func_75692_b(18, (Object)((byte)par1));
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        if (!this.field_70170_p.field_72995_K) {
            if (this.field_70170_p.field_73012_v.nextInt(15) == 0) {
                float newHealth = this.func_110143_aJ() - 1.0f;
                if ((double)newHealth <= 0.5) {
                    this.func_70106_y();
                } else {
                    this.func_70606_j(newHealth);
                }
            }
            if (this.field_70170_p.field_73012_v.nextInt(40) == 0) {
                SoundEffect sound = this.getFakeLivingSound();
                if (this.victimPlayer == null) {
                    this.victimPlayer = this.field_70170_p.func_72924_a(this.getVictimName());
                }
                if (this.victimPlayer != null && sound != null && sound != SoundEffect.NONE && this.victimPlayer.func_70068_e((Entity)this) < 64.0) {
                    Witchery.packetPipeline.sendTo((IMessage)new PacketSound(sound, (Entity)this, 1.0f, 1.0f), this.victimPlayer);
                }
            }
        }
    }

    protected String func_70639_aQ() {
        return null;
    }

    protected String func_70621_aR() {
        return null;
    }

    protected String func_70673_aS() {
        return null;
    }

    public boolean func_70652_k(Entity entity) {
        return true;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        return false;
    }

    protected void func_70628_a(boolean par1, int par2) {
    }
}

