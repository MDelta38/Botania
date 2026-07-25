/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import java.util.Random;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.entities.projectile.EntityEmber;

public class RenderEmber
extends Render {
    private Random random = new Random();

    public RenderEmber() {
        this.field_76989_e = 0.0f;
    }

    public void renderEntityAt(EntityEmber entity, double x, double y, double z, float fq, float pticks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        UtilsFX.bindTexture(ParticleEngine.particleTexture);
        int p = (int)(8.0f * ((float)entity.field_70173_aa / (float)entity.duration));
        float f2 = (float)(7 + p) / 16.0f;
        float f3 = f2 + 0.0625f;
        float f4 = 0.5625f;
        float f5 = f4 + 0.0625f;
        float f6 = 1.0f;
        float f7 = 0.5f;
        float f8 = 0.5f;
        float fc = (float)entity.field_70173_aa / (float)entity.duration;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.9f);
        float particleScale = 0.25f + fc;
        GL11.glScalef((float)particleScale, (float)particleScale, (float)particleScale);
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        tessellator.func_78382_b();
        tessellator.func_78380_c(220);
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        tessellator.func_78369_a(1.0f, 1.0f, 1.0f, 0.9f);
        tessellator.func_78374_a((double)(-f7), (double)(-f8), 0.0, (double)f2, (double)f5);
        tessellator.func_78374_a((double)(f6 - f7), (double)(-f8), 0.0, (double)f3, (double)f5);
        tessellator.func_78374_a((double)(f6 - f7), (double)(1.0f - f8), 0.0, (double)f3, (double)f4);
        tessellator.func_78374_a((double)(-f7), (double)(1.0f - f8), 0.0, (double)f2, (double)f4);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.renderEntityAt((EntityEmber)entity, d, d1, d2, f, f1);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

