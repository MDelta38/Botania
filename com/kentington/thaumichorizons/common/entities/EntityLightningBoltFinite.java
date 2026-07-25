/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.effect.EntityLightningBolt
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 */
package com.kentington.thaumichorizons.common.entities;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import com.kentington.thaumichorizons.common.entities.EntityGolemTH;
import com.kentington.thaumichorizons.common.items.ItemFocusAnimation;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class EntityLightningBoltFinite
extends EntityLightningBolt {
    public int boltLength;
    public boolean animate;

    public EntityLightningBoltFinite(World p_i1703_1_, double p_i1703_2_, double p_i1703_4_, double p_i1703_6_, int boltLength, boolean animate) {
        super(p_i1703_1_, p_i1703_2_, p_i1703_4_, p_i1703_6_);
        this.boltLength = boltLength;
        this.animate = animate;
    }

    public EntityLightningBoltFinite(World w) {
        super(w, 0.0, 0.0, 0.0);
    }

    protected void func_70037_a(NBTTagCompound tag) {
        super.func_70037_a(tag);
        this.boltLength = tag.func_74762_e("length");
        this.animate = tag.func_74767_n("animate");
    }

    protected void func_70014_b(NBTTagCompound tag) {
        super.func_70014_b(tag);
        tag.func_74757_a("animate", this.animate);
        tag.func_74768_a("length", this.boltLength);
    }

    public void func_70071_h_() {
        super.func_70071_h_();
        ThaumicHorizons.proxy.lightningBolt(this.field_70170_p, this.field_70165_t, this.field_70163_u, this.field_70161_v, this.boltLength);
        if (this.animate) {
            int p_77648_4_ = (int)Math.floor(this.field_70165_t);
            int p_77648_5_ = (int)this.field_70163_u;
            int p_77648_6_ = (int)Math.floor(this.field_70161_v);
            Block blocky = this.field_70170_p.func_147439_a(p_77648_4_, p_77648_5_, p_77648_6_);
            int md = this.field_70170_p.func_72805_g(p_77648_4_, p_77648_5_, p_77648_6_);
            if (this.field_70170_p.field_72995_K) {
                return;
            }
            if (!blocky.hasTileEntity(md) && !blocky.isAir((IBlockAccess)this.field_70170_p, p_77648_4_, p_77648_5_, p_77648_6_) && (blocky.func_149662_c() || ItemFocusAnimation.isWhitelisted(blocky, md)) && blocky.func_149712_f(this.field_70170_p, p_77648_4_, p_77648_5_, p_77648_6_) != -1.0f) {
                if (!this.field_70170_p.field_72995_K && ThaumicHorizons.blockCloud.func_149712_f(this.field_70170_p, p_77648_4_, p_77648_5_, p_77648_6_) > 0.0f) {
                    EntityGolemTH golem = new EntityGolemTH(this.field_70170_p);
                    golem.loadGolem((double)p_77648_4_ + 0.5, p_77648_5_, (double)p_77648_6_ + 0.5, blocky, md, 1200, false, false, false);
                    this.field_70170_p.func_147468_f(p_77648_4_, p_77648_5_, p_77648_6_);
                    this.field_70170_p.func_72908_a((double)p_77648_4_ + 0.5, (double)p_77648_5_ + 0.5, (double)p_77648_6_ + 0.5, "thaumcraft:wand", 1.0f, 1.0f);
                    golem.func_110171_b((int)golem.field_70165_t, (int)golem.field_70163_u, (int)golem.field_70161_v, 32);
                    golem.setOwner("");
                    golem.berserk = true;
                    golem.func_70066_B();
                    golem.func_70691_i(100.0f);
                    this.field_70170_p.func_72838_d((Entity)golem);
                    this.field_70170_p.func_72960_a((Entity)golem, (byte)7);
                } else {
                    Minecraft.func_71410_x().field_71452_i.func_147215_a(p_77648_4_, p_77648_5_, p_77648_6_, blocky, md);
                }
                return;
            }
            return;
        }
    }
}

