/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.entity.AbstractClientPlayer
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.fx.ParticleEngine;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.entities.EntityAspectOrb;

@SideOnly(value=Side.CLIENT)
public class RenderAspectOrb
extends Render {
    public RenderAspectOrb() {
        this.field_76989_e = 0.1f;
        this.field_76987_f = 0.5f;
    }

    public void renderAspectOrb(EntityAspectOrb orb, double par2, double par4, double par6, float par8, float par9) {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)par2), (float)((float)par4), (float)((float)par6));
        GL11.glEnable((int)3042);
        if (orb.getAspect() != null) {
            GL11.glBlendFunc((int)770, (int)orb.getAspect().getBlend());
        } else {
            GL11.glBlendFunc((int)770, (int)1);
        }
        UtilsFX.bindTexture(ParticleEngine.particleTexture);
        int i = (int)(System.nanoTime() / 25000000L % 16L);
        Tessellator tessellator = Tessellator.field_78398_a;
        float f2 = (float)i / 16.0f;
        float f3 = (float)(i + 1) / 16.0f;
        float f4 = 0.5f;
        float f5 = 0.5625f;
        float f6 = 1.0f;
        float f7 = 0.5f;
        float f8 = 0.25f;
        int j = orb.func_70070_b(par9);
        int k = j % 65536;
        int l = j / 65536;
        OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        float f11 = 0.1f + 0.3f * ((float)(orb.orbMaxAge - orb.orbAge) / (float)orb.orbMaxAge);
        GL11.glScalef((float)f11, (float)f11, (float)f11);
        tessellator.func_78382_b();
        if (orb.getAspect() != null) {
            tessellator.func_78384_a(orb.getAspect().getColor(), 128);
        }
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

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        this.renderAspectOrb((EntityAspectOrb)par1Entity, par2, par4, par6, par8, par9);
    }

    protected ResourceLocation func_110775_a(Entity entity) {
        return AbstractClientPlayer.field_110314_b;
    }
}

