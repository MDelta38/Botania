/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  net.minecraft.block.Block
 *  net.minecraft.block.Block$SoundType
 *  net.minecraft.block.material.Material
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityThunderhorse
extends EntityHorse {
    boolean initialized = false;
    boolean flying = false;

    public EntityThunderhorse(World p_i1685_1_) {
        super(p_i1685_1_);
    }

    public void func_70037_a(NBTTagCompound p_70037_1_) {
        super.func_70037_a(p_70037_1_);
        this.initialized = p_70037_1_.func_74767_n("initialized");
        if (!this.initialized) {
            HashMultimap map = HashMultimap.create();
            map.put((Object)"generic.movementSpeed", (Object)new AttributeModifier("generic.movementSpeed", 0.1, 1));
            map.put((Object)"horse.jumpStrength", (Object)new AttributeModifier("horse.jumpStrength", 0.25, 1));
            map.put((Object)"generic.maxHealth", (Object)new AttributeModifier("generic.maxHealth", 4.0, 1));
            this.func_110140_aT().func_111147_b((Multimap)map);
            this.initialized = true;
        }
        this.flying = p_70037_1_.func_74767_n("flying");
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74757_a("initialized", this.initialized);
        p_70014_1_.func_74757_a("flying", this.flying);
    }

    protected void func_70069_a(float p_70069_1_) {
        Block block;
        int i;
        if (p_70069_1_ > 1.0f) {
            this.func_85030_a("mob.horse.land", 0.4f, 1.0f);
        }
        if ((i = MathHelper.func_76123_f((float)(p_70069_1_ * 0.5f - 3.0f))) > 0 && (block = this.field_70170_p.func_147439_a(MathHelper.func_76128_c((double)this.field_70165_t), MathHelper.func_76128_c((double)(this.field_70163_u - 0.2 - (double)this.field_70126_B)), MathHelper.func_76128_c((double)this.field_70161_v))).func_149688_o() != Material.field_151579_a) {
            Block.SoundType soundtype = block.field_149762_H;
            this.field_70170_p.func_72956_a((Entity)this, soundtype.func_150498_e(), soundtype.func_150497_c() * 0.5f, soundtype.func_150494_d() * 0.75f);
        }
    }

    public void func_70636_d() {
        super.func_70636_d();
    }

    public void toggleFlying() {
        if (this.field_70153_n == null || !(this.field_70153_n instanceof EntityPlayer)) {
            return;
        }
        if (!this.flying) {
            this.flying = true;
            ((EntityPlayer)this.field_70153_n).field_71075_bZ.field_75100_b = true;
        } else {
            this.flying = false;
            ((EntityPlayer)this.field_70153_n).field_71075_bZ.field_75100_b = false;
        }
    }

    public void func_70612_e(float p_70612_1_, float p_70612_2_) {
        if (this.field_70153_n != null && this.field_70153_n instanceof EntityLivingBase && this.func_110257_ck()) {
            this.field_70126_B = this.field_70177_z = this.field_70153_n.field_70177_z;
            this.field_70125_A = this.field_70153_n.field_70125_A * 0.5f;
            this.func_70101_b(this.field_70177_z, this.field_70125_A);
            this.field_70759_as = this.field_70761_aq = this.field_70177_z;
            p_70612_1_ = ((EntityLivingBase)this.field_70153_n).field_70702_br * 0.5f;
            p_70612_2_ = ((EntityLivingBase)this.field_70153_n).field_70701_bs;
            if (p_70612_2_ <= 0.0f) {
                p_70612_2_ *= 0.25f;
            }
            if (this.field_70181_x > 0.0 || this.field_70181_x < 0.0) {
                this.field_70181_x *= (double)0.9f;
            }
            this.field_70138_W = 1.0f;
            this.field_70747_aH = this.func_70689_ay() * 0.1f;
            if (!this.field_70170_p.field_72995_K) {
                this.func_70659_e((float)this.func_110148_a(SharedMonsterAttributes.field_111263_d).func_111126_e());
                super.func_70612_e(p_70612_1_, p_70612_2_);
            }
            if (this.field_70122_E) {
                this.field_110277_bt = 0.0f;
                this.func_110255_k(false);
            }
            this.field_70722_aY = this.field_70721_aZ;
            double d1 = this.field_70165_t - this.field_70169_q;
            double d0 = this.field_70161_v - this.field_70166_s;
            float f4 = MathHelper.func_76133_a((double)(d1 * d1 + d0 * d0)) * 4.0f;
            if (f4 > 1.0f) {
                f4 = 1.0f;
            }
            this.field_70721_aZ += (f4 - this.field_70721_aZ) * 0.4f;
            this.field_70754_ba += this.field_70721_aZ;
        } else {
            this.field_70138_W = 0.5f;
            this.field_70747_aH = 0.02f;
            super.func_70612_e(p_70612_1_, p_70612_2_);
        }
    }
}

