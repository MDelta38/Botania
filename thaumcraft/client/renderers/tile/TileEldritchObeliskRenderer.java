/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.world.IBlockAccess
 *  net.minecraftforge.client.model.AdvancedModelLoader
 *  net.minecraftforge.client.model.IModelCustom
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.Config;

public class TileEldritchObeliskRenderer
extends TileEntitySpecialRenderer {
    FloatBuffer fBuffer = GLAllocation.func_74529_h((int)16);
    private boolean inrange;
    private IModelCustom model = AdvancedModelLoader.loadModel((ResourceLocation)CAP);
    private static final ResourceLocation CAP = new ResourceLocation("thaumcraft", "textures/models/obelisk_cap.obj");
    private String t1 = "textures/misc/tunnel.png";
    private String t2 = "textures/misc/particlefield.png";
    private String t3 = "textures/misc/particlefield32.png";
    private String t4 = "textures/models/obelisk_side.png";
    private String t5 = "textures/models/obelisk_cap.png";
    private String t6 = "textures/models/obelisk_side_2.png";
    private String t7 = "textures/models/obelisk_cap_2.png";

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        this.inrange = Minecraft.func_71410_x().field_71451_h.func_70092_e((double)te.field_145851_c + 0.5, (double)te.field_145848_d + 0.5, (double)te.field_145849_e + 0.5) < 512.0;
        float bob = 0.0f;
        float count = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + f;
        bob = MathHelper.func_76126_a((float)(count / 10.0f)) * 0.1f + 0.1f;
        GL11.glPushMatrix();
        GL11.glDisable((int)2912);
        this.drawPlaneZNeg(x, y + 1.0 + (double)bob, z, f, 3);
        this.drawPlaneZPos(x, y + 1.0 + (double)bob, z, f, 3);
        this.drawPlaneXNeg(x, y + 1.0 + (double)bob, z, f, 3);
        this.drawPlaneXPos(x, y + 1.0 + (double)bob, z, f, 3);
        GL11.glEnable((int)2912);
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glEnable((int)2896);
        GL11.glEnable((int)32826);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        String tempTex1 = this.t4;
        String tempTex2 = this.t5;
        if (te.func_145831_w() != null) {
            int j = te.func_145838_q().func_149677_c((IBlockAccess)te.func_145831_w(), te.field_145851_c, te.field_145848_d + 5, te.field_145849_e);
            int k = j % 65536;
            int l = j / 65536;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
            if (te.func_145831_w().field_73011_w.field_76574_g == Config.dimensionOuterId) {
                tempTex1 = this.t6;
                tempTex2 = this.t7;
            }
        }
        GL11.glPushMatrix();
        UtilsFX.bindTexture(tempTex1);
        GL11.glTranslated((double)(x + 0.5), (double)(y + 1.0 + (double)bob), (double)(z + 0.5));
        for (int a = 0; a < 4; ++a) {
            GL11.glPushMatrix();
            GL11.glRotatef((float)(a * 90), (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glTranslated((double)0.0, (double)0.0, (double)-0.5);
            this.renderSide(3);
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 1.0 + (double)bob), (double)(z + 0.5));
        GL11.glRotated((double)90.0, (double)1.0, (double)0.0, (double)0.0);
        UtilsFX.bindTexture(tempTex2);
        this.model.renderPart("Cap");
        GL11.glPopMatrix();
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 4.0 + (double)bob), (double)(z + 0.5));
        GL11.glRotated((double)90.0, (double)-1.0, (double)0.0, (double)0.0);
        this.model.renderPart("Cap");
        GL11.glPopMatrix();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
        GL11.glPopMatrix();
    }

    public void drawPlaneZPos(double x, double y, double z, float f, int height) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.99f;
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
                tessellator.func_78377_a(x, y + (double)height, z + (double)offset);
                tessellator.func_78377_a(x, y, z + (double)offset);
                tessellator.func_78377_a(x + 1.0, y, z + (double)offset);
                tessellator.func_78377_a(x + 1.0, y + (double)height, z + (double)offset);
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
            tessellator.func_78374_a(x, y + (double)height, z + (double)offset, 1.0, 1.0);
            tessellator.func_78374_a(x, y, z + (double)offset, 1.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y, z + (double)offset, 0.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y + (double)height, z + (double)offset, 0.0, 1.0);
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

    public void drawPlaneZNeg(double x, double y, double z, float f, int height) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.01f;
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
                tessellator.func_78377_a(x, y + (double)height, z + (double)offset);
                tessellator.func_78377_a(x + 1.0, y + (double)height, z + (double)offset);
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
            tessellator.func_78374_a(x, y + (double)height, z + (double)offset, 1.0, 0.0);
            tessellator.func_78374_a(x + 1.0, y + (double)height, z + (double)offset, 0.0, 0.0);
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

    public void drawPlaneXPos(double x, double y, double z, float f, int height) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.99f;
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
                tessellator.func_78377_a(x + (double)offset, y + (double)height, z);
                tessellator.func_78377_a(x + (double)offset, y + (double)height, z + 1.0);
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
            tessellator.func_78374_a(x + (double)offset, y + (double)height, z, 1.0, 1.0);
            tessellator.func_78374_a(x + (double)offset, y + (double)height, z + 1.0, 1.0, 0.0);
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

    public void drawPlaneXNeg(double x, double y, double z, float f, int height) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.01f;
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
                tessellator.func_78377_a(x + (double)offset, y + (double)height, z + 1.0);
                tessellator.func_78377_a(x + (double)offset, y + (double)height, z);
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
            tessellator.func_78374_a(x + (double)offset, y + (double)height, z + 1.0, 0.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y + (double)height, z, 0.0, 1.0);
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

    public void renderSide(int h) {
        Tessellator tessellator = Tessellator.field_78398_a;
        GL11.glEnable((int)32826);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        tessellator.func_78382_b();
        tessellator.func_78369_a(1.0f, 1.0f, 1.0f, 1.0f);
        tessellator.func_78374_a(-0.5, (double)h, 0.0, 0.0, 1.0);
        tessellator.func_78374_a(0.5, (double)h, 0.0, 1.0, 1.0);
        tessellator.func_78374_a(0.5, 0.0, 0.0, 1.0, 0.0);
        tessellator.func_78374_a(-0.5, 0.0, 0.0, 0.0, 0.0);
        tessellator.func_78381_a();
        GL11.glDisable((int)3042);
        GL11.glDisable((int)32826);
    }
}

