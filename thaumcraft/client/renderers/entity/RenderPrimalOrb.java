/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import java.util.Random;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.blocks.BlockCustomOreItem;

public class RenderPrimalOrb
extends Render {
    public RenderPrimalOrb() {
        this.field_76989_e = 0.0f;
    }

    public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        RenderHelper.func_74518_a();
        float f1 = (float)entity.field_70173_aa / 80.0f;
        float f3 = 0.9f;
        float f2 = 0.0f;
        Random random = new Random(entity.func_145782_y());
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glDisable((int)3553);
        GL11.glShadeModel((int)7425);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)false);
        GL11.glPushMatrix();
        for (int i = 0; i < 12; ++i) {
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glRotatef((float)(random.nextFloat() * 360.0f + f1 * 360.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            tessellator.func_78371_b(6);
            float fa = random.nextFloat() * 20.0f + 5.0f + f2 * 10.0f;
            float f4 = random.nextFloat() * 2.0f + 1.0f + f2 * 2.0f;
            tessellator.func_78384_a(0xFFFFFF, (int)(255.0f * (1.0f - f2)));
            tessellator.func_78377_a(0.0, 0.0, 0.0);
            tessellator.func_78384_a(BlockCustomOreItem.colors[i / 2 + 1], 0);
            tessellator.func_78377_a(-0.866 * (double)(f4 /= 30.0f / ((float)Math.min(entity.field_70173_aa, 10) / 10.0f)), (double)(fa /= 30.0f / ((float)Math.min(entity.field_70173_aa, 10) / 10.0f)), (double)(-0.5f * f4));
            tessellator.func_78377_a(0.866 * (double)f4, (double)fa, (double)(-0.5f * f4));
            tessellator.func_78377_a(0.0, (double)fa, (double)(1.0f * f4));
            tessellator.func_78377_a(-0.866 * (double)f4, (double)fa, (double)(-0.5f * f4));
            tessellator.func_78381_a();
        }
        GL11.glPopMatrix();
        GL11.glDepthMask((boolean)true);
        GL11.glDisable((int)2884);
        GL11.glDisable((int)3042);
        GL11.glShadeModel((int)7424);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)3008);
        RenderHelper.func_74519_b();
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        UtilsFX.bindTexture(ParticleEngine.particleTexture);
        f2 = (float)(entity.field_70173_aa % 13) / 16.0f;
        f3 = f2 + 0.0624375f;
        float f4 = 0.125f;
        float f5 = f4 + 0.0624375f;
        float f6 = 1.0f;
        float f7 = 0.5f;
        float f8 = 0.5f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.8f);
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
        tessellator.func_78382_b();
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
        tessellator.func_78374_a((double)(0.0f - f7), (double)(0.0f - f8), 0.0, (double)f2, (double)f5);
        tessellator.func_78374_a((double)(f6 - f7), (double)(0.0f - f8), 0.0, (double)f3, (double)f5);
        tessellator.func_78374_a((double)(f6 - f7), (double)(1.0f - f8), 0.0, (double)f3, (double)f4);
        tessellator.func_78374_a((double)(0.0f - f7), (double)(1.0f - f8), 0.0, (double)f2, (double)f4);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public void func_76986_a(Entity entity, double d, double d1, double d2, float f, float f1) {
        this.renderEntityAt(entity, d, d1, d2, f, f1);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

