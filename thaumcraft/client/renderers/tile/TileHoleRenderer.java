/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.tiles.TileHole;

public class TileHoleRenderer
extends TileEntitySpecialRenderer {
    FloatBuffer fBuffer = GLAllocation.func_74529_h((int)16);
    private String t1 = "textures/misc/tunnel.png";
    private String t2 = "textures/misc/particlefield.png";
    private String t3 = "textures/misc/particlefield32.png";
    private boolean inrange;

    public void drawPlaneYPos(TileHole tileentityendportal, double x, double y, double z, float f) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.999f;
        if (this.inrange) {
            for (int i = 0; i < 16; ++i) {
                GL11.glPushMatrix();
                float f5 = 16 - i;
                float f6 = 0.0625f;
                float f7 = 1.0f / (f5 + 1.0f);
                if (i == 0) {
                    UtilsFX.bindTexture(this.t1);
                    f7 = 0.1f;
                    f5 = 65.0f;
                    f6 = 0.125f;
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                }
                if (i == 1) {
                    UtilsFX.bindTexture(this.t2);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)1, (int)1);
                    f6 = 0.5f;
                }
                float f8 = (float)(y + (double)offset);
                float f9 = f8 - ActiveRenderInfo.field_74590_b;
                float f10 = f8 + f5 - ActiveRenderInfo.field_74590_b;
                float f11 = f9 / f10;
                f11 = (float)(y + (double)offset) + f11;
                GL11.glTranslatef((float)px, (float)f11, (float)pz);
                GL11.glTexGeni((int)8192, (int)9472, (int)9217);
                GL11.glTexGeni((int)8193, (int)9472, (int)9217);
                GL11.glTexGeni((int)8194, (int)9472, (int)9217);
                GL11.glTexGeni((int)8195, (int)9472, (int)9216);
                GL11.glTexGen((int)8192, (int)9473, (FloatBuffer)this.calcFloatBuffer(1.0f, 0.0f, 0.0f, 0.0f));
                GL11.glTexGen((int)8193, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 1.0f, 0.0f));
                GL11.glTexGen((int)8194, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 0.0f, 1.0f));
                GL11.glTexGen((int)8195, (int)9474, (FloatBuffer)this.calcFloatBuffer(0.0f, 1.0f, 0.0f, 0.0f));
                GL11.glEnable((int)3168);
                GL11.glEnable((int)3169);
                GL11.glEnable((int)3170);
                GL11.glEnable((int)3171);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                GL11.glLoadIdentity();
                GL11.glTranslatef((float)0.0f, (float)((float)(System.currentTimeMillis() % 700000L) / 250000.0f), (float)0.0f);
                GL11.glScalef((float)f6, (float)f6, (float)f6);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                GL11.glRotatef((float)((float)(i * i * 4321 + i * 9) * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                GL11.glTranslatef((float)(-px), (float)(-pz), (float)(-py));
                GL11.glTranslatef((float)(ActiveRenderInfo.field_74592_a * f5 / f9), (float)(ActiveRenderInfo.field_74591_c * f5 / f9), (float)(-py));
                Tessellator tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();
                f11 = random.nextFloat() * 0.5f + 0.1f;
                float f12 = random.nextFloat() * 0.5f + 0.4f;
                float f13 = random.nextFloat() * 0.5f + 0.5f;
                if (i == 0) {
                    f13 = 1.0f;
                    f12 = 1.0f;
                    f11 = 1.0f;
                }
                tessellator.func_78380_c(180);
                tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0f);
                tessellator.func_78377_a(x, y + (double)offset, z + 1.0);
                tessellator.func_78377_a(x, y + (double)offset, z);
                tessellator.func_78377_a(x + 1.0, y + (double)offset, z);
                tessellator.func_78377_a(x + 1.0, y + (double)offset, z + 1.0);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
            }
        } else {
            GL11.glPushMatrix();
            UtilsFX.bindTexture(this.t3);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(180);
            tessellator.func_78369_a(0.5f, 0.5f, 0.5f, 1.0f);
            tessellator.func_78374_a(x, y + (double)offset, z + 1.0, 1.0, 1.0);
            tessellator.func_78374_a(x, y + (double)offset, z, 1.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y + (double)offset, z, 0.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y + (double)offset, z + 1.0, 0.0, 1.0);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)3171);
        GL11.glEnable((int)2896);
    }

    public void drawPlaneYNeg(TileHole tileentityendportal, double x, double y, double z, float f) {
        float f1 = (float)TileEntityRendererDispatcher.field_147554_b;
        float f2 = (float)TileEntityRendererDispatcher.field_147555_c;
        float f3 = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.001f;
        if (this.inrange) {
            for (int i = 0; i < 16; ++i) {
                GL11.glPushMatrix();
                float f5 = 16 - i;
                float f6 = 0.0625f;
                float f7 = 1.0f / (f5 + 1.0f);
                if (i == 0) {
                    UtilsFX.bindTexture(this.t1);
                    f7 = 0.1f;
                    f5 = 65.0f;
                    f6 = 0.125f;
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                }
                if (i == 1) {
                    UtilsFX.bindTexture(this.t2);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)1, (int)1);
                    f6 = 0.5f;
                }
                float f8 = (float)(-(y + (double)offset));
                float f9 = f8 + ActiveRenderInfo.field_74590_b;
                float f10 = f8 + f5 + ActiveRenderInfo.field_74590_b;
                float f11 = f9 / f10;
                f11 = (float)(y + (double)offset) + f11;
                GL11.glTranslatef((float)f1, (float)f11, (float)f3);
                GL11.glTexGeni((int)8192, (int)9472, (int)9217);
                GL11.glTexGeni((int)8193, (int)9472, (int)9217);
                GL11.glTexGeni((int)8194, (int)9472, (int)9217);
                GL11.glTexGeni((int)8195, (int)9472, (int)9216);
                GL11.glTexGen((int)8192, (int)9473, (FloatBuffer)this.calcFloatBuffer(1.0f, 0.0f, 0.0f, 0.0f));
                GL11.glTexGen((int)8193, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 1.0f, 0.0f));
                GL11.glTexGen((int)8194, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 0.0f, 1.0f));
                GL11.glTexGen((int)8195, (int)9474, (FloatBuffer)this.calcFloatBuffer(0.0f, 1.0f, 0.0f, 0.0f));
                GL11.glEnable((int)3168);
                GL11.glEnable((int)3169);
                GL11.glEnable((int)3170);
                GL11.glEnable((int)3171);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                GL11.glLoadIdentity();
                GL11.glTranslatef((float)0.0f, (float)((float)(System.currentTimeMillis() % 700000L) / 250000.0f), (float)0.0f);
                GL11.glScalef((float)f6, (float)f6, (float)f6);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                GL11.glRotatef((float)((float)(i * i * 4321 + i * 9) * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                GL11.glTranslatef((float)(-f1), (float)(-f3), (float)(-f2));
                GL11.glTranslatef((float)(ActiveRenderInfo.field_74592_a * f5 / f9), (float)(ActiveRenderInfo.field_74591_c * f5 / f9), (float)(-f2));
                Tessellator tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();
                f11 = random.nextFloat() * 0.5f + 0.1f;
                float f12 = random.nextFloat() * 0.5f + 0.4f;
                float f13 = random.nextFloat() * 0.5f + 0.5f;
                if (i == 0) {
                    f13 = 1.0f;
                    f12 = 1.0f;
                    f11 = 1.0f;
                }
                tessellator.func_78380_c(180);
                tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0f);
                tessellator.func_78377_a(x, y + (double)offset, z);
                tessellator.func_78377_a(x, y + (double)offset, z + 1.0);
                tessellator.func_78377_a(x + 1.0, y + (double)offset, z + 1.0);
                tessellator.func_78377_a(x + 1.0, y + (double)offset, z);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
            }
        } else {
            GL11.glPushMatrix();
            UtilsFX.bindTexture(this.t3);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(180);
            tessellator.func_78369_a(0.5f, 0.5f, 0.5f, 1.0f);
            tessellator.func_78374_a(x, y + (double)offset, z, 1.0, 1.0);
            tessellator.func_78374_a(x, y + (double)offset, z + 1.0, 1.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y + (double)offset, z + 1.0, 0.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y + (double)offset, z, 0.0, 1.0);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)3171);
        GL11.glEnable((int)2896);
    }

    public void drawPlaneZNeg(TileHole tileentityendportal, double x, double y, double z, float f) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.001f;
        if (this.inrange) {
            for (int i = 0; i < 16; ++i) {
                GL11.glPushMatrix();
                float f5 = 16 - i;
                float f6 = 0.0625f;
                float f7 = 1.0f / (f5 + 1.0f);
                if (i == 0) {
                    UtilsFX.bindTexture(this.t1);
                    f7 = 0.1f;
                    f5 = 65.0f;
                    f6 = 0.125f;
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                }
                if (i == 1) {
                    UtilsFX.bindTexture(this.t2);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)1, (int)1);
                    f6 = 0.5f;
                }
                float f8 = (float)(-(z + (double)offset));
                float f9 = f8 + ActiveRenderInfo.field_74591_c;
                float f10 = f8 + f5 + ActiveRenderInfo.field_74591_c;
                float f11 = f9 / f10;
                f11 = (float)(z + (double)offset) + f11;
                GL11.glTranslatef((float)px, (float)py, (float)f11);
                GL11.glTexGeni((int)8192, (int)9472, (int)9217);
                GL11.glTexGeni((int)8193, (int)9472, (int)9217);
                GL11.glTexGeni((int)8194, (int)9472, (int)9217);
                GL11.glTexGeni((int)8195, (int)9472, (int)9216);
                GL11.glTexGen((int)8192, (int)9473, (FloatBuffer)this.calcFloatBuffer(1.0f, 0.0f, 0.0f, 0.0f));
                GL11.glTexGen((int)8193, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 1.0f, 0.0f, 0.0f));
                GL11.glTexGen((int)8194, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 0.0f, 1.0f));
                GL11.glTexGen((int)8195, (int)9474, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 1.0f, 0.0f));
                GL11.glEnable((int)3168);
                GL11.glEnable((int)3169);
                GL11.glEnable((int)3170);
                GL11.glEnable((int)3171);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                GL11.glLoadIdentity();
                GL11.glTranslatef((float)0.0f, (float)((float)(System.currentTimeMillis() % 700000L) / 250000.0f), (float)0.0f);
                GL11.glScalef((float)f6, (float)f6, (float)f6);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                GL11.glRotatef((float)((float)(i * i * 4321 + i * 9) * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                GL11.glTranslatef((float)(-px), (float)(-py), (float)(-pz));
                GL11.glTranslatef((float)(ActiveRenderInfo.field_74592_a * f5 / f9), (float)(ActiveRenderInfo.field_74590_b * f5 / f9), (float)(-pz));
                Tessellator tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();
                f11 = random.nextFloat() * 0.5f + 0.1f;
                float f12 = random.nextFloat() * 0.5f + 0.4f;
                float f13 = random.nextFloat() * 0.5f + 0.5f;
                if (i == 0) {
                    f13 = 1.0f;
                    f12 = 1.0f;
                    f11 = 1.0f;
                }
                tessellator.func_78380_c(180);
                tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0f);
                tessellator.func_78377_a(x, y + 1.0, z + (double)offset);
                tessellator.func_78377_a(x, y, z + (double)offset);
                tessellator.func_78377_a(x + 1.0, y, z + (double)offset);
                tessellator.func_78377_a(x + 1.0, y + 1.0, z + (double)offset);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
            }
        } else {
            GL11.glPushMatrix();
            UtilsFX.bindTexture(this.t3);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(180);
            tessellator.func_78369_a(0.5f, 0.5f, 0.5f, 1.0f);
            tessellator.func_78374_a(x, y + 1.0, z + (double)offset, 1.0, 1.0);
            tessellator.func_78374_a(x, y, z + (double)offset, 1.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y, z + (double)offset, 0.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y + 1.0, z + (double)offset, 0.0, 1.0);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)3171);
        GL11.glEnable((int)2896);
    }

    public void drawPlaneZPos(TileHole tileentityendportal, double x, double y, double z, float f) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.999f;
        if (this.inrange) {
            for (int i = 0; i < 16; ++i) {
                GL11.glPushMatrix();
                float f5 = 16 - i;
                float f6 = 0.0625f;
                float f7 = 1.0f / (f5 + 1.0f);
                if (i == 0) {
                    UtilsFX.bindTexture(this.t1);
                    f7 = 0.1f;
                    f5 = 65.0f;
                    f6 = 0.125f;
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                }
                if (i == 1) {
                    UtilsFX.bindTexture(this.t2);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)1, (int)1);
                    f6 = 0.5f;
                }
                float f8 = (float)(z + (double)offset);
                float f9 = f8 - ActiveRenderInfo.field_74591_c;
                float f10 = f8 + f5 - ActiveRenderInfo.field_74591_c;
                float f11 = f9 / f10;
                f11 = (float)(z + (double)offset) + f11;
                GL11.glTranslatef((float)px, (float)py, (float)f11);
                GL11.glTexGeni((int)8192, (int)9472, (int)9217);
                GL11.glTexGeni((int)8193, (int)9472, (int)9217);
                GL11.glTexGeni((int)8194, (int)9472, (int)9217);
                GL11.glTexGeni((int)8195, (int)9472, (int)9216);
                GL11.glTexGen((int)8192, (int)9473, (FloatBuffer)this.calcFloatBuffer(1.0f, 0.0f, 0.0f, 0.0f));
                GL11.glTexGen((int)8193, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 1.0f, 0.0f, 0.0f));
                GL11.glTexGen((int)8194, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 0.0f, 1.0f));
                GL11.glTexGen((int)8195, (int)9474, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 1.0f, 0.0f));
                GL11.glEnable((int)3168);
                GL11.glEnable((int)3169);
                GL11.glEnable((int)3170);
                GL11.glEnable((int)3171);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                GL11.glLoadIdentity();
                GL11.glTranslatef((float)0.0f, (float)((float)(System.currentTimeMillis() % 700000L) / 250000.0f), (float)0.0f);
                GL11.glScalef((float)f6, (float)f6, (float)f6);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                GL11.glRotatef((float)((float)(i * i * 4321 + i * 9) * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                GL11.glTranslatef((float)(-px), (float)(-py), (float)(-pz));
                GL11.glTranslatef((float)(ActiveRenderInfo.field_74592_a * f5 / f9), (float)(ActiveRenderInfo.field_74590_b * f5 / f9), (float)(-pz));
                Tessellator tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();
                f11 = random.nextFloat() * 0.5f + 0.1f;
                float f12 = random.nextFloat() * 0.5f + 0.4f;
                float f13 = random.nextFloat() * 0.5f + 0.5f;
                if (i == 0) {
                    f13 = 1.0f;
                    f12 = 1.0f;
                    f11 = 1.0f;
                }
                tessellator.func_78380_c(180);
                tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0f);
                tessellator.func_78377_a(x, y, z + (double)offset);
                tessellator.func_78377_a(x, y + 1.0, z + (double)offset);
                tessellator.func_78377_a(x + 1.0, y + 1.0, z + (double)offset);
                tessellator.func_78377_a(x + 1.0, y, z + (double)offset);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
            }
        } else {
            GL11.glPushMatrix();
            UtilsFX.bindTexture(this.t3);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(180);
            tessellator.func_78369_a(0.5f, 0.5f, 0.5f, 1.0f);
            tessellator.func_78374_a(x, y, z + (double)offset, 1.0, 1.0);
            tessellator.func_78374_a(x, y + 1.0, z + (double)offset, 1.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y + 1.0, z + (double)offset, 0.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y, z + (double)offset, 0.0, 1.0);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)3171);
        GL11.glEnable((int)2896);
    }

    public void drawPlaneXNeg(TileHole tileentityendportal, double x, double y, double z, float f) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.001f;
        if (this.inrange) {
            for (int i = 0; i < 16; ++i) {
                GL11.glPushMatrix();
                float f5 = 16 - i;
                float f6 = 0.0625f;
                float f7 = 1.0f / (f5 + 1.0f);
                if (i == 0) {
                    UtilsFX.bindTexture(this.t1);
                    f7 = 0.1f;
                    f5 = 65.0f;
                    f6 = 0.125f;
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                }
                if (i == 1) {
                    UtilsFX.bindTexture(this.t2);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)1, (int)1);
                    f6 = 0.5f;
                }
                float f8 = (float)(-(x + (double)offset));
                float f9 = f8 + ActiveRenderInfo.field_74592_a;
                float f10 = f8 + f5 + ActiveRenderInfo.field_74592_a;
                float f11 = f9 / f10;
                f11 = (float)(x + (double)offset) + f11;
                GL11.glTranslatef((float)f11, (float)py, (float)pz);
                GL11.glTexGeni((int)8192, (int)9472, (int)9217);
                GL11.glTexGeni((int)8193, (int)9472, (int)9217);
                GL11.glTexGeni((int)8194, (int)9472, (int)9217);
                GL11.glTexGeni((int)8195, (int)9472, (int)9216);
                GL11.glTexGen((int)8193, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 1.0f, 0.0f, 0.0f));
                GL11.glTexGen((int)8192, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 1.0f, 0.0f));
                GL11.glTexGen((int)8194, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 0.0f, 1.0f));
                GL11.glTexGen((int)8195, (int)9474, (FloatBuffer)this.calcFloatBuffer(1.0f, 0.0f, 0.0f, 0.0f));
                GL11.glEnable((int)3168);
                GL11.glEnable((int)3169);
                GL11.glEnable((int)3170);
                GL11.glEnable((int)3171);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                GL11.glLoadIdentity();
                GL11.glTranslatef((float)0.0f, (float)((float)(System.currentTimeMillis() % 700000L) / 250000.0f), (float)0.0f);
                GL11.glScalef((float)f6, (float)f6, (float)f6);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                GL11.glRotatef((float)((float)(i * i * 4321 + i * 9) * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                GL11.glTranslatef((float)(-pz), (float)(-py), (float)(-px));
                GL11.glTranslatef((float)(ActiveRenderInfo.field_74591_c * f5 / f9), (float)(ActiveRenderInfo.field_74590_b * f5 / f9), (float)(-px));
                Tessellator tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();
                f11 = random.nextFloat() * 0.5f + 0.1f;
                float f12 = random.nextFloat() * 0.5f + 0.4f;
                float f13 = random.nextFloat() * 0.5f + 0.5f;
                if (i == 0) {
                    f13 = 1.0f;
                    f12 = 1.0f;
                    f11 = 1.0f;
                }
                tessellator.func_78380_c(180);
                tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0f);
                tessellator.func_78377_a(x + (double)offset, y + 1.0, z);
                tessellator.func_78377_a(x + (double)offset, y + 1.0, z + 1.0);
                tessellator.func_78377_a(x + (double)offset, y, z + 1.0);
                tessellator.func_78377_a(x + (double)offset, y, z);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
            }
        } else {
            GL11.glPushMatrix();
            UtilsFX.bindTexture(this.t3);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(180);
            tessellator.func_78369_a(0.5f, 0.5f, 0.5f, 1.0f);
            tessellator.func_78374_a(x + (double)offset, y + 1.0, z, 1.0, 1.0);
            tessellator.func_78374_a(x + (double)offset, y + 1.0, z + 1.0, 1.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y, z + 1.0, 0.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y, z, 0.0, 1.0);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)3171);
        GL11.glEnable((int)2896);
    }

    public void drawPlaneXPos(TileHole tileentityendportal, double x, double y, double z, float f) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.999f;
        if (this.inrange) {
            for (int i = 0; i < 16; ++i) {
                GL11.glPushMatrix();
                float f5 = 16 - i;
                float f6 = 0.0625f;
                float f7 = 1.0f / (f5 + 1.0f);
                if (i == 0) {
                    UtilsFX.bindTexture(this.t1);
                    f7 = 0.1f;
                    f5 = 65.0f;
                    f6 = 0.125f;
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)770, (int)771);
                }
                if (i == 1) {
                    UtilsFX.bindTexture(this.t2);
                    GL11.glEnable((int)3042);
                    GL11.glBlendFunc((int)1, (int)1);
                    f6 = 0.5f;
                }
                float f8 = (float)(x + (double)offset);
                float f9 = f8 - ActiveRenderInfo.field_74592_a;
                float f10 = f8 + f5 - ActiveRenderInfo.field_74592_a;
                float f11 = f9 / f10;
                f11 = (float)(x + (double)offset) + f11;
                GL11.glTranslatef((float)f11, (float)py, (float)pz);
                GL11.glTexGeni((int)8192, (int)9472, (int)9217);
                GL11.glTexGeni((int)8193, (int)9472, (int)9217);
                GL11.glTexGeni((int)8194, (int)9472, (int)9217);
                GL11.glTexGeni((int)8195, (int)9472, (int)9216);
                GL11.glTexGen((int)8193, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 1.0f, 0.0f, 0.0f));
                GL11.glTexGen((int)8192, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 1.0f, 0.0f));
                GL11.glTexGen((int)8194, (int)9473, (FloatBuffer)this.calcFloatBuffer(0.0f, 0.0f, 0.0f, 1.0f));
                GL11.glTexGen((int)8195, (int)9474, (FloatBuffer)this.calcFloatBuffer(1.0f, 0.0f, 0.0f, 0.0f));
                GL11.glEnable((int)3168);
                GL11.glEnable((int)3169);
                GL11.glEnable((int)3170);
                GL11.glEnable((int)3171);
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5890);
                GL11.glPushMatrix();
                GL11.glLoadIdentity();
                GL11.glTranslatef((float)0.0f, (float)((float)(System.currentTimeMillis() % 700000L) / 250000.0f), (float)0.0f);
                GL11.glScalef((float)f6, (float)f6, (float)f6);
                GL11.glTranslatef((float)0.5f, (float)0.5f, (float)0.0f);
                GL11.glRotatef((float)((float)(i * i * 4321 + i * 9) * 2.0f), (float)0.0f, (float)0.0f, (float)1.0f);
                GL11.glTranslatef((float)-0.5f, (float)-0.5f, (float)0.0f);
                GL11.glTranslatef((float)(-pz), (float)(-py), (float)(-px));
                GL11.glTranslatef((float)(ActiveRenderInfo.field_74591_c * f5 / f9), (float)(ActiveRenderInfo.field_74590_b * f5 / f9), (float)(-px));
                Tessellator tessellator = Tessellator.field_78398_a;
                tessellator.func_78382_b();
                f11 = random.nextFloat() * 0.5f + 0.1f;
                float f12 = random.nextFloat() * 0.5f + 0.4f;
                float f13 = random.nextFloat() * 0.5f + 0.5f;
                if (i == 0) {
                    f13 = 1.0f;
                    f12 = 1.0f;
                    f11 = 1.0f;
                }
                tessellator.func_78380_c(180);
                tessellator.func_78369_a(f11 * f7, f12 * f7, f13 * f7, 1.0f);
                tessellator.func_78377_a(x + (double)offset, y, z);
                tessellator.func_78377_a(x + (double)offset, y, z + 1.0);
                tessellator.func_78377_a(x + (double)offset, y + 1.0, z + 1.0);
                tessellator.func_78377_a(x + (double)offset, y + 1.0, z);
                tessellator.func_78381_a();
                GL11.glPopMatrix();
                GL11.glMatrixMode((int)5888);
            }
        } else {
            GL11.glPushMatrix();
            UtilsFX.bindTexture(this.t3);
            Tessellator tessellator = Tessellator.field_78398_a;
            tessellator.func_78382_b();
            tessellator.func_78380_c(180);
            tessellator.func_78369_a(0.5f, 0.5f, 0.5f, 1.0f);
            tessellator.func_78374_a(x + (double)offset, y, z, 1.0, 1.0);
            tessellator.func_78374_a(x + (double)offset, y, z + 1.0, 1.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y + 1.0, z + 1.0, 0.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y + 1.0, z, 0.0, 1.0);
            tessellator.func_78381_a();
            GL11.glPopMatrix();
        }
        GL11.glDisable((int)3042);
        GL11.glDisable((int)3168);
        GL11.glDisable((int)3169);
        GL11.glDisable((int)3170);
        GL11.glDisable((int)3171);
        GL11.glEnable((int)2896);
    }

    private FloatBuffer calcFloatBuffer(float f, float f1, float f2, float f3) {
        this.fBuffer.clear();
        this.fBuffer.put(f).put(f1).put(f2).put(f3);
        this.fBuffer.flip();
        return this.fBuffer;
    }

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        this.inrange = Minecraft.func_71410_x().field_71451_h.func_70092_e((double)te.field_145851_c + 0.5, (double)te.field_145848_d + 0.5, (double)te.field_145849_e + 0.5) < 512.0;
        GL11.glDisable((int)2912);
        if (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d + 1, te.field_145849_e).func_149662_c() && te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d + 1, te.field_145849_e) != ConfigBlocks.blockHole) {
            this.drawPlaneYPos((TileHole)te, x, y, z, f);
        }
        if (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e).func_149662_c() && te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d - 1, te.field_145849_e) != ConfigBlocks.blockHole) {
            this.drawPlaneYNeg((TileHole)te, x, y, z, f);
        }
        if (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e - 1).func_149662_c() && te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e - 1) != ConfigBlocks.blockHole) {
            this.drawPlaneZNeg((TileHole)te, x, y, z, f);
        }
        if (te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e + 1).func_149662_c() && te.func_145831_w().func_147439_a(te.field_145851_c, te.field_145848_d, te.field_145849_e + 1) != ConfigBlocks.blockHole) {
            this.drawPlaneZPos((TileHole)te, x, y, z, f);
        }
        if (te.func_145831_w().func_147439_a(te.field_145851_c - 1, te.field_145848_d, te.field_145849_e).func_149662_c() && te.func_145831_w().func_147439_a(te.field_145851_c - 1, te.field_145848_d, te.field_145849_e) != ConfigBlocks.blockHole) {
            this.drawPlaneXNeg((TileHole)te, x, y, z, f);
        }
        if (te.func_145831_w().func_147439_a(te.field_145851_c + 1, te.field_145848_d, te.field_145849_e).func_149662_c() && te.func_145831_w().func_147439_a(te.field_145851_c + 1, te.field_145848_d, te.field_145849_e) != ConfigBlocks.blockHole) {
            this.drawPlaneXPos((TileHole)te, x, y, z, f);
        }
        GL11.glEnable((int)2912);
    }
}

