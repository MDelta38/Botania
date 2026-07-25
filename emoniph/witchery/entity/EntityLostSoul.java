/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.enchantment.EnchantmentHelper
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityCreature
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.EntityAIBase
 *  net.minecraft.entity.ai.EntityAINearestAttackableTarget
 *  net.minecraft.entity.ai.EntityAIWatchClosest
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.DamageSource
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 */
package com.emoniph.witchery.entity;

import com.emoniph.witchery.entity.EntitySpirit;
import com.emoniph.witchery.entity.ai.EntityAIFlyerAttackOnCollide;
import com.emoniph.witchery.entity.ai.EntityAIFlyerLand;
import com.emoniph.witchery.entity.ai.EntityAISitAndStay;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class EntityLostSoul
extends EntitySpirit {
    private int timeToLive = -1;

    public void setTimeToLive(int i) {
        this.timeToLive = i;
    }

    public boolean isTemp() {
        return this.timeToLive != -1;
    }

    public EntityLostSoul(World world) {
        super(world);
        this.field_70714_bg.field_75782_a.clear();
        this.field_70715_bh.field_75782_a.clear();
        this.field_70714_bg.func_75776_a(1, (EntityAIBase)new EntityAISitAndStay(this));
        this.field_70714_bg.func_75776_a(2, (EntityAIBase)new EntityAIFlyerAttackOnCollide((EntityCreature)this, 1.0, true));
        this.field_70714_bg.func_75776_a(9, (EntityAIBase)new EntityAIFlyerLand((EntityLiving)this, 0.8, true));
        this.field_70714_bg.func_75776_a(11, (EntityAIBase)new EntityAIWatchClosest((EntityLiving)this, EntityPlayer.class, 10.0f, 0.2f));
        this.field_70715_bh.func_75776_a(2, (EntityAIBase)new EntityAINearestAttackableTarget((EntityCreature)this, EntityPlayer.class, 0, true));
    }

    @Override
    public void func_70014_b(NBTTagCompound par1NBTTagCompound) {
        super.func_70014_b(par1NBTTagCompound);
        par1NBTTagCompound.func_74768_a("FeatherColor", this.getFeatherColor());
        par1NBTTagCompound.func_74774_a("SoulType", (byte)this.getSoulType());
        par1NBTTagCompound.func_74768_a("SuicideIn", this.timeToLive);
    }

    @Override
    public void func_70037_a(NBTTagCompound par1NBTTagCompound) {
        super.func_70037_a(par1NBTTagCompound);
        if (par1NBTTagCompound.func_74764_b("FeatherColor")) {
            this.setFeatherColor(par1NBTTagCompound.func_74762_e("FeatherColor"));
        }
        if (par1NBTTagCompound.func_74764_b("SoulType")) {
            this.setSoulType(par1NBTTagCompound.func_74771_c("SoulType"));
        }
        this.timeToLive = par1NBTTagCompound.func_74764_b("SuicideIn") ? par1NBTTagCompound.func_74762_e("SuicideIn") : -1;
    }

    public void func_70110_aj() {
    }

    @Override
    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(22, (Object)this.field_70170_p.field_73012_v.nextInt(3));
        switch (this.getSoulType()) {
            case 0: {
                this.setFeatherColor(0xFF0000);
                break;
            }
            case 1: {
                this.setFeatherColor(65280);
                break;
            }
            case 2: {
                this.setFeatherColor(255);
            }
        }
    }

    public int getSoulType() {
        return this.field_70180_af.func_75679_c(22);
    }

    public void setSoulType(int par1) {
        this.field_70180_af.func_75692_b(22, (Object)par1);
    }

    @Override
    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0);
        this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111128_a(0.4);
        this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111128_a(2.0);
    }

    public boolean func_70652_k(Entity targetEntity) {
        boolean flag;
        float f = (float)this.func_110148_a(SharedMonsterAttributes.field_111264_e).func_111126_e();
        int i = 0;
        if (targetEntity instanceof EntityLivingBase) {
            f += EnchantmentHelper.func_77512_a((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)targetEntity));
            i += EnchantmentHelper.func_77507_b((EntityLivingBase)this, (EntityLivingBase)((EntityLivingBase)targetEntity));
        }
        DamageSource source = null;
        if (this.field_70170_p.field_73012_v.nextInt(4) == 0) {
            switch (this.getSoulType()) {
                case 0: {
                    source = DamageSource.field_76372_a;
                    break;
                }
                case 1: {
                    source = DamageSource.func_76358_a((EntityLivingBase)this);
                    break;
                }
                case 2: {
                    source = DamageSource.field_76376_m;
                }
            }
        }
        if (source == null) {
            source = DamageSource.func_76358_a((EntityLivingBase)this);
        }
        if (flag = targetEntity.func_70097_a(source, f)) {
            int j;
            if (i > 0) {
                targetEntity.func_70024_g((double)(-MathHelper.func_76126_a((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f), 0.1, (double)(MathHelper.func_76134_b((float)(this.field_70177_z * (float)Math.PI / 180.0f)) * (float)i * 0.5f));
                this.field_70159_w *= 0.6;
                this.field_70179_y *= 0.6;
            }
            if ((j = EnchantmentHelper.func_90036_a((EntityLivingBase)this)) > 0) {
                targetEntity.func_70015_d(j * 4);
            }
            if (targetEntity instanceof EntityLivingBase) {
                EnchantmentHelper.func_151384_a((EntityLivingBase)((EntityLivingBase)targetEntity), (Entity)this);
            }
            EnchantmentHelper.func_151385_b((EntityLivingBase)this, (Entity)targetEntity);
        }
        return flag;
    }

    public boolean func_70097_a(DamageSource source, float damage) {
        float MAX_DAMAGE = 15.0f;
        switch (this.getSoulType()) {
            case 0: {
                if (!source.func_76347_k() && !source.func_94541_c()) break;
                return super.func_70097_a(source, Math.min(damage, 15.0f));
            }
            case 1: {
                if (source.func_76352_a() || source.func_82725_o() || source.func_76347_k() || source.func_94541_c() || source == DamageSource.field_76368_d || source == DamageSource.field_76367_g || source == DamageSource.field_76369_e || source == DamageSource.field_82727_n) break;
                return super.func_70097_a(source, Math.min(damage, 15.0f));
            }
            case 2: {
                if (!source.func_82725_o()) break;
                return super.func_70097_a(source, Math.min(damage, 15.0f));
            }
        }
        return false;
    }

    @Override
    protected void func_70629_bd() {
        super.func_70629_bd();
        if (!this.field_70170_p.field_72995_K && this.timeToLive != -1 && --this.timeToLive <= 0) {
            this.func_70106_y();
        }
    }

    @Override
    public void func_70071_h_() {
        super.func_70071_h_();
    }

    @Override
    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.witchery.lostsoul.name");
    }
}

