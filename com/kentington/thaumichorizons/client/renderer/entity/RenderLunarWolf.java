/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderWolf
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityWolf
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.tile.TileNodeRenderer
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.common.entities.EntityLunarWolf;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileNodeRenderer;

public class RenderLunarWolf
extends RenderWolf {
    ResourceLocation wolfTex = new ResourceLocation("thaumichorizons", "textures/entity/lunarwolf.png");

    public RenderLunarWolf(ModelBase p_i1269_1_, ModelBase p_i1269_2_, float p_i1269_3_) {
        super(p_i1269_1_, p_i1269_2_, p_i1269_3_);
    }

    public void doRender(EntityLunarWolf p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        if (p_76986_1_.field_70170_p.func_72820_D() % 24000L < 12000L) {
            return;
        }
        float scale = p_76986_1_.field_70170_p.func_130001_d() * 3.0f;
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2884);
        int i = p_76986_1_.field_70173_aa % 32;
        UtilsFX.bindTexture((ResourceLocation)TileNodeRenderer.nodetex);
        UtilsFX.renderFacingStrip((double)p_76986_1_.field_70165_t, (double)(p_76986_1_.field_70163_u + (double)p_76986_1_.field_70131_O / 1.75), (double)p_76986_1_.field_70161_v, (float)0.0f, (float)scale, (float)0.75f, (int)32, (int)1, (int)i, (float)p_76986_9_, (int)0xAADDFF);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return this.getEntityTexture((EntityLunarWolf)p_110775_1_);
    }

    protected ResourceLocation func_110775_a(EntityWolf p_110775_1_) {
        return this.getEntityTexture((EntityLunarWolf)p_110775_1_);
    }

    protected ResourceLocation getEntityTexture(EntityLunarWolf p_110775_1_) {
        return this.wolfTex;
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityLunarWolf)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityLunarWolf)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityLunarWolf)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}

