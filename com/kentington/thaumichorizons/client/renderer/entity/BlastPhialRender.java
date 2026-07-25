/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderSnowball
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityPotion
 *  net.minecraft.item.Item
 *  net.minecraft.potion.PotionHelper
 *  net.minecraft.util.IIcon
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package com.kentington.thaumichorizons.client.renderer.entity;

import com.kentington.thaumichorizons.common.ThaumicHorizons;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionHelper;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class BlastPhialRender
extends RenderSnowball {
    Item item = ThaumicHorizons.itemSyringeInjection;

    public BlastPhialRender() {
        super(ThaumicHorizons.itemSyringeInjection);
    }

    public void func_76986_a(Entity p_76986_1_, double p_76986_2_, double p_76986_4_, double p_76986_6_, float p_76986_8_, float p_76986_9_) {
        IIcon iicon = this.item.func_77617_a(1);
        if (iicon != null) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)p_76986_2_), (float)((float)p_76986_4_), (float)((float)p_76986_6_));
            GL11.glEnable((int)32826);
            GL11.glScalef((float)0.5f, (float)0.5f, (float)0.5f);
            this.func_110777_b(p_76986_1_);
            Tessellator tessellator = Tessellator.field_78398_a;
            int i = PotionHelper.func_77915_a((int)((EntityPotion)p_76986_1_).func_70196_i(), (boolean)false);
            float f2 = (float)(i >> 16 & 0xFF) / 255.0f;
            float f3 = (float)(i >> 8 & 0xFF) / 255.0f;
            float f4 = (float)(i & 0xFF) / 255.0f;
            GL11.glColor3f((float)f2, (float)f3, (float)f4);
            GL11.glPushMatrix();
            this.func_77026_a(tessellator, iicon);
            GL11.glPopMatrix();
            GL11.glColor3f((float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glDisable((int)32826);
            GL11.glPopMatrix();
        }
    }

    private void func_77026_a(Tessellator p_77026_1_, IIcon p_77026_2_) {
        float f = p_77026_2_.func_94209_e();
        float f1 = p_77026_2_.func_94212_f();
        float f2 = p_77026_2_.func_94206_g();
        float f3 = p_77026_2_.func_94210_h();
        float f4 = 1.0f;
        float f5 = 0.5f;
        float f6 = 0.25f;
        GL11.glRotatef((float)(180.0f - this.field_76990_c.field_78735_i), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(-this.field_76990_c.field_78732_j), (float)1.0f, (float)0.0f, (float)0.0f);
        p_77026_1_.func_78382_b();
        p_77026_1_.func_78375_b(0.0f, 1.0f, 0.0f);
        p_77026_1_.func_78374_a((double)(0.0f - f5), (double)(0.0f - f6), 0.0, (double)f, (double)f3);
        p_77026_1_.func_78374_a((double)(f4 - f5), (double)(0.0f - f6), 0.0, (double)f1, (double)f3);
        p_77026_1_.func_78374_a((double)(f4 - f5), (double)(f4 - f6), 0.0, (double)f1, (double)f2);
        p_77026_1_.func_78374_a((double)(0.0f - f5), (double)(f4 - f6), 0.0, (double)f, (double)f2);
        p_77026_1_.func_78381_a();
    }

    protected ResourceLocation func_110775_a(Entity p_110775_1_) {
        return TextureMap.field_110576_c;
    }
}

