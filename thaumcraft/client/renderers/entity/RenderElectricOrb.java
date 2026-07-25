/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import java.util.Random;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.entities.projectile.EntityGolemOrb;

public class RenderElectricOrb
extends Render {
    private Random random = new Random();

    public RenderElectricOrb() {
        this.field_76989_e = 0.0f;
    }

    public void renderEntityAt(Entity entity, double x, double y, double z, float fq, float pticks) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glPushMatrix();
        GL11.glTranslated((double)x, (double)y, (double)z);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        UtilsFX.bindTexture(ParticleEngine.particleTexture);
        float f2 = (float)(1 + entity.field_70173_aa % 6) / 8.0f;
        float f3 = f2 + 0.125f;
        float f4 = 0.875f;
        if (entity instanceof EntityGolemOrb && ((EntityGolemOrb)entity).red) {
            f4 = 0.75f;
        }
        float f5 = f4 + 0.125f;
        float f6 = 1.0f;
        float f7 = 0.5f;
        float f8 = 0.5f;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)0.8f);
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        float bob = MathHelper.func_76126_a((float)((float)entity.field_70173_aa / 5.0f)) * 0.2f + 0.2f;
        GL11.glScalef((float)(1.0f + bob), (float)(1.0f + bob), (float)(1.0f + bob));
        tessellator.func_78382_b();
        tessellator.func_78380_c(220);
        tessellator.func_78375_b(0.0f, 1.0f, 0.0f);
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
        this.renderEntityAt(entity, d, d1, d2, f, f1);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

