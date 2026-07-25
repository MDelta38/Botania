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
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;

public class RenderSoul
extends Render {
    int size1 = 0;
    int size2 = 0;

    public RenderSoul() {
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
        float f10 = 0.25f;
        float f11 = (float)x;
        float f12 = (float)y;
        float f13 = (float)z;
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        UtilsFX.bindTexture((String)"thaumichorizons", (String)"textures/misc/soul.png");
        int i = entity.field_70173_aa % 16;
        float x0 = (float)i / (float)this.size1;
        float x1 = (float)(i + 1) / (float)this.size1;
        tessellator.func_78382_b();
        tessellator.func_78380_c(240);
        tessellator.func_78374_a((double)(f11 - f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 - f3 * f10 - f5 * f10), (double)x1, 0.0);
        tessellator.func_78374_a((double)(f11 - f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 - f3 * f10 + f5 * f10), (double)x1, 1.0);
        tessellator.func_78374_a((double)(f11 + f1 * f10 + f4 * f10), (double)(f12 + f2 * f10), (double)(f13 + f3 * f10 + f5 * f10), (double)x0, 1.0);
        tessellator.func_78374_a((double)(f11 + f1 * f10 - f4 * f10), (double)(f12 - f2 * f10), (double)(f13 + f3 * f10 - f5 * f10), (double)x0, 0.0);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        if (this.size1 == 0) {
            this.size1 = 16;
        }
        this.renderEntityAt(entity, d, d1, d2, f, f1);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

