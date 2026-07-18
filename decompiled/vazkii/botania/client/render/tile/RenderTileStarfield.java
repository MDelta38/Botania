/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package vazkii.botania.client.render.tile;

import java.awt.Color;
import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderTileStarfield
extends TileEntitySpecialRenderer {
    private static final ResourceLocation field_147529_c = new ResourceLocation("textures/environment/end_sky.png");
    private static final ResourceLocation field_147526_d = new ResourceLocation("textures/entity/end_portal.png");
    private static final Random field_147527_e = new Random(31100L);
    FloatBuffer field_147528_b = GLAllocation.func_74529_h((int)16);

    public void func_147500_a(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) {
        float f1 = (float)this.field_147501_a.field_147560_j;
        float f2 = (float)this.field_147501_a.field_147561_k;
        float f3 = (float)this.field_147501_a.field_147558_l;
        GL11.glDisable((int)2896);
        field_147527_e.setSeed(31100L);
        float f4 = 0.24f;
        for (int i = 0; i < 16; ++i) {
            GL11.glPushMatrix();
            float f5 = 16 - i;
            float f6 = 0.0625f;
            float f7 = 1.0f / (f5 + 1.0f);
            if (i == 0) {
                this.func_147499_a(field_147529_c);
                f7 = 0.1f;
                f5 = 65.0f;
                f6 = 0.125f;
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)770, (int)771);
            }
            if (i == 1) {
                this.func_147499_a(field_147526_d);
                GL11.glEnable((int)3042);
                GL11.glBlendFunc((int)1, (int)1);
                f6 = 0.5f;
            }
            float f8 = (float)(-(p_147500_4_ + (double)f4));
            float f9 = f8 + ActiveRenderInfo.field_74590_b;
            float f10 = f8 + f5 + ActiveRenderInfo.field_74590_b;
            float f11 = f9 / f10;
            GL11.glTranslatef((float)f1, (float)(f11 += (float)(p_147500_4_ + (double)f4)), (float)f3);
            GL11.glTexGeni((int)8192, (int)9472, (int)9217);
            GL11.glTexGeni((int)8193, (int)9472, (int)9217);
            GL11.glTexGeni((int)8194, (int)9472, (int)9217);
            GL11.glTexGeni((int)8195, (int)9472, (int)9216);
            GL11.glTexGen((int)8192, (int)9473, (FloatBuffer)this.func_147525_a(1.0f, 0.0f, 0.0f, 0.0f));
            GL11.glTexGen((int)8193, (int)9473, (FloatBuffer)this.func_147525_a(0.0f, 0.0f, 1.0f, 0.0f));
            GL11.glTexGen((int)8194, (int)9473, (FloatBuffer)this.func_147525_a(0.0f, 0.0f, 0.0f, 1.0f));
            GL11.glTexGen((int)8195, (int)9474, (FloatBuffer)this.func_147525_a(0.0f, 1.0f, 0.0f, 0.0f));
            GL11.glEnable((int)3168);
            GL11.glEnable((int)3169);
            GL11.glEnable((int)3170);
            GL11.glEnable((int)3171);
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5890);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GL11.glTranslatef((float)0.0f, (float)((float)(Minecraft.func_71386_F() % 20000L) / 20000.0f), (float)0.0f);
            GL11.glScalef((float)f6, (float)f6, (float)f6);
            GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
            GL11.glRotatef((float)((float)(i * i * 4321 + i * 9) * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
            GL11.glTranslatef((float)(-f1), (float)(-f3), (float)(-f2));
            f9 = f8 + ActiveRenderInfo.field_74590_b;
            GL11.glTranslatef((float)(ActiveRenderInfo.field_74592_a * f5 / f9), (float)(ActiveRenderInfo.field_74591_c * f5 / f9), (float)(-f2));
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            Color color = Color.getHSBColor((float)Minecraft.func_71386_F() / 20.0f % 360.0f / 360.0f, 1.0f, 1.0f);
            f11 = (float)color.getRed() / 255.0f;
            float f12 = (float)color.getGreen() / 255.0f;
            float f13 = (float)color.getBlue() / 255.0f;
            tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0f);
            tessellator.func_78377_a(p_147500_2_, p_147500_4_ + (double)f4, p_147500_6_);
            tessellator.func_78377_a(p_147500_2_, p_147500_4_ + (double)f4, p_147500_6_ + 1.0);
            tessellator.func_78377_a(p_147500_2_ + 1.0, p_147500_4_ + (double)f4, p_147500_6_ + 1.0);
            tessellator.func_78377_a(p_147500_2_ + 1.0, p_147500_4_ + (double)f4, p_147500_6_);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
            GL11.glMatrixMode((int)5888);
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)3171);
        GL11.glEnable((int)2896);
    }

    private FloatBuffer func_147525_a(float p_147525_1_, float p_147525_2_, float p_147525_3_, float p_147525_4_) {
        this.field_147528_b.clear();
        this.field_147528_b.put(p_147525_1_).put(p_147525_2_).put(p_147525_3_).put(p_147525_4_);
        this.field_147528_b.flip();
        return this.field_147528_b;
    }
}

