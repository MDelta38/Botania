/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderPig
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.passive.EntityPig
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.tile.TileNodeRenderer
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.common.entities.EntityTaintPig;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderPig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileNodeRenderer;

public class RenderTaintfeeder
extends RenderPig {
    private static final ResourceLocation pigTextures = new ResourceLocation("thaumichorizons", "textures/entity/taintfeeder.png");
    private static final ResourceLocation pigEyesTextures = new ResourceLocation("thaumichorizons", "textures/entity/taintfeeder_eyes.png");

    public RenderTaintfeeder(ModelBase p_i1265_1_, ModelBase p_i1265_2_, float p_i1265_3_) {
        super(p_i1265_1_, p_i1265_2_, p_i1265_3_);
    }

    protected ResourceLocation func_110775_a(EntityPig p_110775_1_) {
        return pigTextures;
    }

    public void doRender(EntityTaintPig p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glDisable((int)2884);
        int i = p_76986_1_.field_70173_aa % 32;
        UtilsFX.bindTexture((ResourceLocation)TileNodeRenderer.nodetex);
        UtilsFX.renderFacingStrip((double)p_76986_1_.field_70165_t, (double)(p_76986_1_.field_70163_u + (double)p_76986_1_.field_70131_O / 1.75), (double)p_76986_1_.field_70161_v, (float)0.0f, (float)2.0f, (float)1.0f, (int)32, (int)6, (int)i, (float)p_76986_9_, (int)0xAADDFF);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityTaintPig)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityTaintPig)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityTaintPig)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    protected int func_77032_a(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
        return this.shouldRenderPass((EntityTaintPig)p_77032_1_, p_77032_2_, p_77032_3_);
    }

    protected int shouldRenderPass(EntityTaintPig p_77032_1_, int p_77032_2_, float p_77032_3_) {
        if (p_77032_2_ != 0) {
            return -1;
        }
        float f = (float)Math.sin(Math.toRadians(p_77032_1_.field_70173_aa % 45) * 8.0);
        this.func_110776_a(pigEyesTextures);
        GL11.glEnable((int)3042);
        GL11.glDisable((int)3008);
        GL11.glBlendFunc((int)770, (int)771);
        if (p_77032_1_.func_82150_aj()) {
            GL11.glDepthMask((boolean)false);
        } else {
            GL11.glDepthMask((boolean)true);
        }
        int c0 = 61680;
        int j = c0 % 65536;
        int k = c0 / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)j / 1.0f), (float)((float)k / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)(0.32f + 0.32f * f));
        return 1;
    }
}

