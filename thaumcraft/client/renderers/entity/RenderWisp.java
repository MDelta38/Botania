/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLiving
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import java.awt.Color;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.entities.monster.EntityWisp;

public class RenderWisp
extends Render {
    int size1 = 0;
    int size2 = 0;

    public RenderWisp() {
        this.field_76989_e = 0.0f;
    }

    public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {
        if (((EntityLiving)entity).func_110143_aJ() <= 0.0f) {
            return;
        }
        float f1 = ActiveRenderInfo.field_74588_d;
        float f2 = ActiveRenderInfo.field_74589_e;
        float f3 = ActiveRenderInfo.field_74586_f;
        float f4 = ActiveRenderInfo.field_74587_g;
        float f5 = ActiveRenderInfo.field_74596_h;
        float f10 = 1.0f;
        float f11 = (float)x;
        float f12 = (float)y + 0.45f;
        float f13 = (float)z;
        Tessellator tessellator = Tessellator.field_78398_a;
        Color color = new Color(0);
        if (Aspect.getAspect(((EntityWisp)entity).getType()) != null) {
            color = new Color(Aspect.getAspect(((EntityWisp)entity).getType()).getColor());
        }
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        UtilsFX.bindTexture("textures/misc/wisp.png");
        int i = entity.field_70173_aa % 16;
        float size4 = this.size1 * 4;
        float float_sizeMinus0_01 = (float)this.size1 - 0.01f;
        float float_texNudge = 1.0f / ((float)this.size1 * (float)this.size1 * 2.0f);
        float float_reciprocal = 1.0f / (float)this.size1;
        float x0 = ((float)(i % 4 * this.size1) + 0.0f) / size4;
        float x1 = ((float)(i % 4 * this.size1) + float_sizeMinus0_01) / size4;
        float x2 = ((float)(i / 4 * this.size1) + 0.0f) / size4;
        float x3 = ((float)(i / 4 * this.size1) + float_sizeMinus0_01) / size4;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        if (((EntityLiving)entity).field_70737_aN > 0) {
            tessellator.func_78369_a(1.0f, (float)color.getGreen() / 300.0f, (float)color.getBlue() / 300.0f, 1.0f);
        } else {
            tessellator.func_78369_a((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, 1.0f);
        }
        tessellator.func_78374_a((double)(f11 - f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 - f3 * f10 - f5 * f10), (double)x1, (double)x3);
        tessellator.func_78374_a((double)(f11 - f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 - f3 * f10 + f5 * f10), (double)x1, (double)x2);
        tessellator.func_78374_a((double)(f11 + f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 + f3 * f10 + f5 * f10), (double)x0, (double)x2);
        tessellator.func_78374_a((double)(f11 + f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 + f3 * f10 - f5 * f10), (double)x0, (double)x3);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        UtilsFX.bindTexture(ParticleEngine.particleTexture);
        int qq = entity.field_70173_aa % 16;
        float size8 = 16.0f;
        x0 = (float)qq / size8;
        x1 = (float)(qq + 1) / size8;
        x2 = 5.0f / size8;
        x3 = 6.0f / size8;
        float var11 = MathHelper.func_76126_a((float)(((float)entity.field_70173_aa + pticks) / 10.0f)) * 0.1f;
        f10 = 0.4f + var11;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        tessellator.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
        tessellator.func_78374_a((double)(f11 - f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 - f3 * f10 - f5 * f10), (double)x1, (double)x3);
        tessellator.func_78374_a((double)(f11 - f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 - f3 * f10 + f5 * f10), (double)x1, (double)x2);
        tessellator.func_78374_a((double)(f11 + f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 + f3 * f10 + f5 * f10), (double)x0, (double)x2);
        tessellator.func_78374_a((double)(f11 + f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 + f3 * f10 - f5 * f10), (double)x0, (double)x3);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        if (this.size1 == 0) {
            this.size1 = UtilsFX.getTextureSize("textures/misc/wisp.png", 64);
        }
        this.renderEntityAt(entity, d, d1, d2, f, f1);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

