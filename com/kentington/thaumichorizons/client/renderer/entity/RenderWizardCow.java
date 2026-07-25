/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.entity.RenderCow
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.tile.TileNodeRenderer
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.common.entities.EntityWizardCow;
import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderCow;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.NodeModifier;
import thaumcraft.api.nodes.NodeType;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.tile.TileNodeRenderer;

public class RenderWizardCow
extends RenderCow {
    public static HashMap<String, AspectList> cowAspects = new HashMap();
    public static HashMap<String, NodeType> cowTypes = new HashMap();
    public static HashMap<String, NodeModifier> cowMods = new HashMap();

    public RenderWizardCow(ModelBase p_i1253_1_, float p_i1253_2_) {
        super(p_i1253_1_, p_i1253_2_);
    }

    public void doRender(EntityWizardCow p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.5f);
        super.func_76986_a((EntityLiving)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (cowAspects.containsKey(p_76986_1_.func_110124_au().toString())) {
            this.renderMyNode(p_76986_1_.field_70165_t, p_76986_1_.field_70163_u + (double)(p_76986_1_.field_70131_O / 2.0f), p_76986_1_.field_70161_v, Minecraft.func_71410_x().field_71451_h, 32.0, true, true, 1.0f, p_76986_9_, cowAspects.get(p_76986_1_.func_110124_au().toString()), cowTypes.get(p_76986_1_.func_110124_au().toString()), cowMods.get(p_76986_1_.func_110124_au().toString()));
        }
        GL11.glDisable((int)3042);
    }

    public void renderMyNode(double x, double y, double z, EntityLivingBase viewer, double viewDistance, boolean visible, boolean depthIgnore, float size, float partialTicks, AspectList aspects, NodeType type, NodeModifier mod) {
        long nt = System.nanoTime();
        UtilsFX.bindTexture((ResourceLocation)TileNodeRenderer.nodetex);
        int frames = 32;
        if (aspects.size() > 0 && visible) {
            double distance = viewer.func_70011_f(x, y, z);
            if (distance > viewDistance) {
                return;
            }
            float alpha = (float)((viewDistance - distance) / viewDistance);
            if (mod != null) {
                switch (mod) {
                    case BRIGHT: {
                        alpha *= 1.5f;
                        break;
                    }
                    case PALE: {
                        alpha *= 0.66f;
                        break;
                    }
                    case FADING: {
                        alpha *= MathHelper.func_76126_a((float)((float)viewer.field_70173_aa / 3.0f)) * 0.25f + 0.33f;
                    }
                }
            }
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glDepthMask((boolean)false);
            if (depthIgnore) {
                GL11.glDisable((int)2929);
            }
            GL11.glDisable((int)2884);
            long time = nt / 5000000L;
            float bscale = 0.25f;
            GL11.glPushMatrix();
            float rad = 6.283186f;
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            int i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
            int count = 0;
            float scale = 0.0f;
            float angle = 0.0f;
            float average = 0.0f;
            for (Aspect aspect : aspects.getAspects()) {
                if (aspect.getBlend() == 771) {
                    alpha = (float)((double)alpha * 1.5);
                }
                average += (float)aspects.getAmount(aspect);
                GL11.glPushMatrix();
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)aspect.getBlend());
                scale = MathHelper.func_76126_a((float)((float)viewer.field_70173_aa / (14.0f - (float)count))) * bscale + bscale * 2.0f;
                scale = 0.2f + scale * ((float)aspects.getAmount(aspect) / 50.0f);
                angle = (float)(time % (long)(5000 + 500 * count)) / (5000.0f + (float)(500 * count)) * rad;
                UtilsFX.renderFacingStrip((double)x, (double)y, (double)z, (float)angle, (float)(scale *= size), (float)(alpha / Math.max(1.0f, (float)aspects.size() / 2.0f)), (int)frames, (int)0, (int)i, (float)partialTicks, (int)aspect.getColor());
                GL11.glDisable((int)3042);
                GL11.glPopMatrix();
                ++count;
                if (aspect.getBlend() != 771) continue;
                alpha = (float)((double)alpha / 1.5);
            }
            GL11.glPushMatrix();
            GL11.glEnable((int)3042);
            i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
            scale = 0.1f + (average /= (float)aspects.size()) / 150.0f;
            scale *= size;
            int strip = 1;
            switch (type) {
                case NORMAL: {
                    GL11.glBlendFunc((int)770, (int)1);
                    break;
                }
                case UNSTABLE: {
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 6;
                    angle = 0.0f;
                    break;
                }
                case DARK: {
                    GL11.glBlendFunc((int)770, (int)771);
                    strip = 2;
                    break;
                }
                case TAINTED: {
                    GL11.glBlendFunc((int)770, (int)771);
                    strip = 5;
                    break;
                }
                case HUNGRY: {
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 4;
                    break;
                }
                case PURE: {
                    scale *= 0.75f;
                    GL11.glBlendFunc((int)770, (int)1);
                    strip = 3;
                }
            }
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)1.0f, (float)alpha);
            UtilsFX.renderFacingStrip((double)x, (double)y, (double)z, (float)angle, (float)scale, (float)alpha, (int)frames, (int)strip, (int)i, (float)partialTicks, (int)0xFFFFFF);
            GL11.glDisable((int)3042);
            GL11.glPopMatrix();
            GL11.glPopMatrix();
            GL11.glEnable((int)2884);
            if (depthIgnore) {
                GL11.glEnable((int)2929);
            }
            GL11.glDepthMask((boolean)true);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glAlphaFunc((int)516, (float)0.003921569f);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)770, (int)1);
            GL11.glDepthMask((boolean)false);
            int i = (int)(((double)(nt / 40000000L) + x) % (double)frames);
            GL11.glColor4f((float)1.0f, (float)0.0f, (float)1.0f, (float)0.1f);
            UtilsFX.renderFacingStrip((double)x, (double)y, (double)z, (float)0.0f, (float)0.5f, (float)0.1f, (int)frames, (int)1, (int)i, (float)partialTicks, (int)0xFFFFFF);
            GL11.glDepthMask((boolean)true);
            GL11.glDisable((int)3042);
            GL11.glAlphaFunc((int)516, (float)0.1f);
            GL11.glPopMatrix();
        }
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityWizardCow)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(EntityLiving p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityWizardCow)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }

    public void func_76986_a(EntityLivingBase p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        this.doRender((EntityWizardCow)p_76986_1_, p_76986_2_, p_76986_4_, p_76986_6_, p_76986_8_, p_76986_9_);
    }
}

