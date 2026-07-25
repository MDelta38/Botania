/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.entity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.awt.Color;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.blocks.BlockCustomOreItem;
import thaumcraft.common.entities.projectile.EntityPrimalArrow;

@SideOnly(value=Side.CLIENT)
public class RenderPrimalArrow
extends Render {
    private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");
    int size1 = 0;
    int size2 = 0;

    public void renderArrow(EntityPrimalArrow arrow, double x, double y, double z, float ns, float prt) {
        Color color = new Color(BlockCustomOreItem.colors[arrow.type + 1]);
        this.func_110777_b((Entity)arrow);
        GL11.glPushMatrix();
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glRotatef((float)(arrow.field_70126_B + (arrow.field_70177_z - arrow.field_70126_B) * prt - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glRotatef((float)(arrow.field_70127_C + (arrow.field_70125_A - arrow.field_70127_C) * prt), (float)0.0f, (float)0.0f, (float)1.0f);
        Tessellator tessellator = Tessellator.field_78398_a;
        int b0 = 0;
        float f2 = 0.0f;
        float f3 = 0.5f;
        float f4 = (float)(0 + b0 * 10) / 32.0f;
        float f5 = (float)(5 + b0 * 10) / 32.0f;
        float f6 = 0.0f;
        float f7 = 0.15625f;
        float f8 = (float)(5 + b0 * 10) / 32.0f;
        float f9 = (float)(10 + b0 * 10) / 32.0f;
        float f10 = 0.05625f;
        GL11.glEnable((int)32826);
        float f11 = (float)arrow.field_70249_b - prt;
        if (f11 > 0.0f) {
            float f12 = -MathHelper.func_76126_a((float)(f11 * 3.0f)) * f11;
            GL11.glRotatef((float)f12, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)((100.0f - (float)arrow.field_70252_j) / 100.0f));
        GL11.glRotatef((float)45.0f, (float)1.0f, (float)0.0f, (float)0.0f);
        GL11.glScalef((float)f10, (float)f10, (float)f10);
        GL11.glTranslatef((float)-4.0f, (float)0.0f, (float)0.0f);
        GL11.glNormal3f((float)f10, (float)0.0f, (float)0.0f);
        tessellator.func_78382_b();
        tessellator.func_78374_a(-7.0, -2.0, -2.0, (double)f6, (double)f8);
        tessellator.func_78374_a(-7.0, -2.0, 2.0, (double)f7, (double)f8);
        tessellator.func_78374_a(-7.0, 2.0, 2.0, (double)f7, (double)f9);
        tessellator.func_78374_a(-7.0, 2.0, -2.0, (double)f6, (double)f9);
        tessellator.func_78381_a();
        GL11.glNormal3f((float)(-f10), (float)0.0f, (float)0.0f);
        tessellator.func_78382_b();
        tessellator.func_78374_a(-7.0, 2.0, -2.0, (double)f6, (double)f8);
        tessellator.func_78374_a(-7.0, 2.0, 2.0, (double)f7, (double)f8);
        tessellator.func_78374_a(-7.0, -2.0, 2.0, (double)f7, (double)f9);
        tessellator.func_78374_a(-7.0, -2.0, -2.0, (double)f6, (double)f9);
        tessellator.func_78381_a();
        for (int i = 0; i < 4; ++i) {
            GL11.glRotatef((float)90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glNormal3f((float)0.0f, (float)0.0f, (float)f10);
            tessellator.func_78382_b();
            tessellator.func_78374_a(-8.0, -2.0, 0.0, (double)f2, (double)f4);
            tessellator.func_78374_a(8.0, -2.0, 0.0, (double)f3, (double)f4);
            tessellator.func_78374_a(8.0, 2.0, 0.0, (double)f3, (double)f5);
            tessellator.func_78374_a(-8.0, 2.0, 0.0, (double)f2, (double)f5);
            tessellator.func_78381_a();
        }
        GL11.glDisable((int)32826);
        GL11.glDisable((int)3042);
        GL11.glPopMatrix();
        float f1 = ActiveRenderInfo.field_74588_d;
        f2 = ActiveRenderInfo.field_74589_e;
        f3 = ActiveRenderInfo.field_74586_f;
        f4 = ActiveRenderInfo.field_74587_g;
        f5 = ActiveRenderInfo.field_74596_h;
        f10 = 0.5f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)x), (float)((float)y), (float)((float)z));
        GL11.glDepthMask((boolean)false);
        GL11.glEnable((int)3042);
        if (arrow.type < 5) {
            GL11.glBlendFunc((int)770, (int)1);
        } else {
            GL11.glBlendFunc((int)770, (int)771);
        }
        UtilsFX.bindTexture("textures/misc/wisp.png");
        int i = arrow.field_70173_aa % 16;
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
        tessellator.func_78369_a((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (100.0f - (float)arrow.field_70252_j) / 100.0f);
        tessellator.func_78374_a((double)(-f1 * f10 - f4 * f10), (double)(-f2 * f10), (double)(-f3 * f10 - f5 * f10), (double)x1, (double)x3);
        tessellator.func_78374_a((double)(-f1 * f10 + f4 * f10), (double)(f2 * f10), (double)(-f3 * f10 + f5 * f10), (double)x1, (double)x2);
        tessellator.func_78374_a((double)(f1 * f10 + f4 * f10), (double)(f2 * f10), (double)(f3 * f10 + f5 * f10), (double)x0, (double)x2);
        tessellator.func_78374_a((double)(f1 * f10 - f4 * f10), (double)(-f2 * f10), (double)(f3 * f10 - f5 * f10), (double)x0, (double)x3);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDepthMask((boolean)true);
        GL11.glPopMatrix();
    }

    protected ResourceLocation getArrowTextures(EntityPrimalArrow par1EntityArrow) {
        return arrowTextures;
    }

    protected ResourceLocation func_110775_a(Entity par1Entity) {
        return this.getArrowTextures((EntityPrimalArrow)par1Entity);
    }

    public void func_76986_a(Entity par1Entity, double par2, double par4, double par6, float par8, float par9) {
        if (this.size1 == 0) {
            this.size1 = UtilsFX.getTextureSize("textures/misc/wisp.png", 64);
        }
        this.renderArrow((EntityPrimalArrow)par1Entity, par2, par4, par6, par8, par9);
    }
}

