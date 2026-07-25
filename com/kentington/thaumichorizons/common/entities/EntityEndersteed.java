/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashMultimap
 *  com.google.common.collect.Multimap
 *  cpw.mods.fml.common.eventhandler.Event
 *  net.minecraft.block.Block
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.ai.attributes.AttributeModifier
 *  net.minecraft.entity.passive.EntityHorse
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.StatCollector
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.living.EnderTeleportEvent
 */
package com.kentington.thaumichorizons.common.entities;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityEndersteed
extends EntityHorse {
    boolean initialized = false;

    public EntityEndersteed(World p_i1685_1_) {
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
    }

    public void func_70014_b(NBTTagCompound p_70014_1_) {
        super.func_70014_b(p_70014_1_);
        p_70014_1_.func_74757_a("initialized", this.initialized);
    }

    public void func_110206_u(int p_110206_1_) {
        double blocks = (double)p_110206_1_ / 7.0;
        this.teleportTo(this.field_70165_t - blocks * Math.sin(Math.toRadians(this.field_70177_z)), this.field_70163_u, this.field_70161_v + blocks * Math.cos(Math.toRadians(this.field_70177_z)));
    }

    public String func_70005_c_() {
        if (this.func_94056_bM()) {
            return this.func_94057_bL();
        }
        return StatCollector.func_74838_a((String)"entity.ThaumicHorizons.Endersteed.name");
    }

    protected boolean teleportTo(double p_70825_1_, double p_70825_3_, double p_70825_5_) {
        EnderTeleportEvent event = new EnderTeleportEvent((EntityLivingBase)this, p_70825_1_, p_70825_3_, p_70825_5_, 0.0f);
        if (MinecraftForge.EVENT_BUS.post((Event)event)) {
            return false;
        }
        double d3 = this.field_70165_t;
        double d4 = this.field_70163_u;
        double d5 = this.field_70161_v;
        this.field_70165_t = event.targetX;
        this.field_70163_u = MathHelper.func_76128_c((double)event.targetY) - 3;
        this.field_70161_v = event.targetZ;
        boolean flag = false;
        int i = MathHelper.func_76128_c((double)this.field_70165_t);
        int j = MathHelper.func_76128_c((double)this.field_70163_u);
        int k = MathHelper.func_76128_c((double)this.field_70161_v);
        boolean foundGround = false;
        boolean foundAir = false;
        while (!(foundGround && foundAir || !((double)j < d4 + 4.0))) {
            Block block = this.field_70170_p.func_147439_a(i, j - 1, k);
            if (block.func_149688_o().func_76230_c()) {
                foundGround = true;
            } else if (!foundGround) {
                ++j;
                this.field_70163_u += 1.0;
            }
            if (!foundGround) continue;
            this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
            if (this.field_70170_p.func_72945_a((Entity)this, this.field_70121_D).isEmpty() && !this.field_70170_p.func_72953_d(this.field_70121_D)) {
                flag = true;
                foundAir = true;
            }
            ++j;
            this.field_70163_u += 1.0;
        }
        this.func_70107_b(this.field_70165_t, this.field_70163_u, this.field_70161_v);
        if (!flag) {
            this.func_70107_b(d3, d4, d5);
            return false;
        }
        int short1 = 128;
        for (int l = 0; l < short1; ++l) {
            double d6 = (double)l / ((double)short1 - 1.0);
            float f = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            float f1 = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            float f2 = (this.field_70146_Z.nextFloat() - 0.5f) * 0.2f;
            double d7 = d3 + (this.field_70165_t - d3) * d6 + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N * 2.0;
            double d8 = d4 + (this.field_70163_u - d4) * d6 + this.field_70146_Z.nextDouble() * (double)this.field_70131_O;
            double d9 = d5 + (this.field_70161_v - d5) * d6 + (this.field_70146_Z.nextDouble() - 0.5) * (double)this.field_70130_N * 2.0;
            this.field_70170_p.func_72869_a("portal", d7, d8, d9, (double)f, (double)f1, (double)f2);
        }
        this.field_70170_p.func_72908_a(d3, d4, d5, "mob.endermen.portal", 1.0f, 1.0f);
        this.func_85030_a("mob.endermen.portal", 1.0f, 1.0f);
        return true;
    }
}

