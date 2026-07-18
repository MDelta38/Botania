/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.potion.PotionEffect
 *  net.minecraft.util.DamageSource
 *  net.minecraft.world.World
 */
package vazkii.botania.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import vazkii.botania.common.Botania;
import vazkii.botania.common.entity.EntityFlyingCreature;

public class EntityPixie
extends EntityFlyingCreature {
    EntityLivingBase summoner = null;
    float damage = 0.0f;
    PotionEffect effect = null;

    public EntityPixie(World world) {
        super(world);
        this.func_70105_a(1.0f, 1.0f);
    }

    protected void func_70088_a() {
        super.func_70088_a();
        this.field_70180_af.func_75682_a(20, (Object)0);
    }

    protected void func_110147_ax() {
        super.func_110147_ax();
        this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(2.0);
    }

    public void setType(int type) {
        this.field_70180_af.func_75692_b(20, (Object)type);
    }

    public int getType() {
        return this.field_70180_af.func_75679_c(20);
    }

    public void setProps(EntityLivingBase target, EntityLivingBase summoner, int type, float damage) {
        this.func_70624_b(target);
        this.summoner = summoner;
        this.damage = damage;
        this.setType(type);
    }

    public void setApplyPotionEffect(PotionEffect effect) {
        this.effect = effect;
    }

    protected void func_70626_be() {
        EntityLivingBase target = this.func_70638_az();
        if (target != null) {
            double d0 = target.field_70165_t + (double)(target.field_70130_N / 2.0f) - this.field_70165_t;
            double d1 = target.field_70163_u + (double)(target.field_70131_O / 2.0f) - this.field_70163_u;
            double d2 = target.field_70161_v + (double)(target.field_70130_N / 2.0f) - this.field_70161_v;
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            float mod = 0.45f;
            if (this.getType() == 1) {
                mod = 0.1f;
            }
            this.field_70159_w += d0 / d3 * (double)mod;
            this.field_70181_x += d1 / d3 * (double)mod;
            this.field_70179_y += d2 / d3 * (double)mod;
            if (Math.sqrt(d3) < 1.0) {
                if (this.summoner != null) {
                    if (this.summoner instanceof EntityPlayer) {
                        target.func_70097_a(DamageSource.func_76365_a((EntityPlayer)((EntityPlayer)this.summoner)), this.damage);
                    } else {
                        target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this.summoner), this.damage);
                    }
                } else {
                    target.func_70097_a(DamageSource.func_76358_a((EntityLivingBase)this), this.damage);
                }
                if (this.effect != null && !(target instanceof EntityPlayer)) {
                    target.func_70690_d(this.effect);
                }
                this.die();
            }
        }
        this.field_70761_aq = this.field_70177_z = -((float)Math.atan2(this.field_70159_w, this.field_70179_y)) * 180.0f / (float)Math.PI;
    }

    public boolean func_70097_a(DamageSource par1DamageSource, float par2) {
        if (this.getType() == 0 && par1DamageSource.func_76346_g() != this.summoner || this.getType() == 1 && par1DamageSource.func_76346_g() instanceof EntityPlayer) {
            return super.func_70097_a(par1DamageSource, par2);
        }
        return false;
    }

    public void func_70030_z() {
        boolean dark;
        super.func_70030_z();
        if (this.func_70638_az() == null || this.field_70173_aa > 200) {
            this.die();
        }
        boolean bl = dark = this.getType() == 1;
        if (this.field_70170_p.field_72995_K) {
            for (int i = 0; i < 4; ++i) {
                Botania.proxy.sparkleFX(this.field_70170_p, this.field_70165_t + (Math.random() - 0.5) * 0.25, this.field_70163_u + 0.5 + (Math.random() - 0.5) * 0.25, this.field_70161_v + (Math.random() - 0.5) * 0.25, dark ? 0.1f : 1.0f, dark ? 0.025f : 0.25f, dark ? 0.09f : 0.9f, 0.1f + (float)Math.random() * 0.25f, 12);
            }
        }
    }

    public void die() {
        this.func_70106_y();
        if (this.field_70170_p.field_72995_K && this.getType() == 0) {
            for (int i = 0; i < 12; ++i) {
                Botania.proxy.sparkleFX(this.field_70170_p, this.field_70165_t + (Math.random() - 0.5) * 0.25, this.field_70163_u + 0.5 + (Math.random() - 0.5) * 0.25, this.field_70161_v + (Math.random() - 0.5) * 0.25, 1.0f, 0.25f, 0.9f, 1.0f + (float)Math.random() * 0.25f, 5);
            }
        }
    }

    protected boolean func_70692_ba() {
        return false;
    }
}

