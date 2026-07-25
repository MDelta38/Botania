/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderChicken
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityChicken
 *  net.minecraft.util.ResourceLocation
 *  thaumcraft.common.Thaumcraft
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.common.entities.EntityGoldChicken;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import thaumcraft.common.Thaumcraft;

public class RenderGoldChicken
extends RenderChicken {
    ResourceLocation rl = new ResourceLocation("thaumichorizons", "textures/entity/goldchicken.png");

    public RenderGoldChicken(ModelBase p_i1252_1_, float p_i1252_2_) {
        super(p_i1252_1_, p_i1252_2_);
    }

    protected ResourceLocation func_110775_a(EntityChicken p_110775_1_) {
        return this.rl;
    }

    public void doRender(EntityGoldChicken p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        super.func_76986_a((EntityChicken)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        if (p_76986_1_.field_70170_p.field_73012_v.nextFloat() > 0.95f) {
            float angle = (float)((double)(p_76986_1_.field_70170_p.field_73012_v.nextFloat() * 2.0f) * Math.PI);
            Thaumcraft.proxy.sparkle((float)p_76986_1_.field_70165_t + p_76986_1_.field_70130_N * (float)Math.cos(angle), (float)p_76986_1_.field_70163_u + p_76986_1_.field_70131_O * (p_76986_1_.field_70170_p.field_73012_v.nextFloat() - 0.1f) * 1.2f, (float)p_76986_1_.field_70161_v + p_76986_1_.field_70130_N * (float)Math.sin(angle), 2.0f, 1, 0.0f);
        }
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityGoldChicken)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityGoldChicken)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityGoldChicken)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}

