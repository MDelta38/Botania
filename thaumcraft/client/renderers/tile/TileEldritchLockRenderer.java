/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderItem
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.item.ItemStack
 *  net.minecraft.tileentity.TileEntity
 *  net.minecraft.util.MathHelper
 *  net.minecraftforge.common.util.ForgeDirection
 *  org.lwjgl.opengl.GL11
 */
package thaumcraft.client.renderers.tile;

import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraftforge.common.util.ForgeDirection;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.tiles.TileEldritchLock;

public class TileEldritchLockRenderer
extends TileEntitySpecialRenderer {
    FloatBuffer fBuffer;
    private boolean inrange;
    private ModelCube model = new ModelCube(0);
    private String t1 = "textures/misc/tunnel.png";
    private String t2 = "textures/misc/particlefield.png";
    private String t3 = "textures/misc/particlefield32.png";
    ItemStack is = null;
    EntityItem entityitem = null;

    public TileEldritchLockRenderer() {
        this.fBuffer = GLAllocation.func_74529_h((int)16);
    }

    public void func_147500_a(TileEntity te, double x, double y, double z, float f) {
        boolean bl = this.inrange = Minecraft.func_71410_x().field_71451_h.func_70092_e((double)te.field_145851_c + 0.5, (double)te.field_145848_d + 0.5, (double)te.field_145849_e + 0.5) < 512.0;
        if (this.is == null) {
            this.is = new ItemStack(ConfigItems.itemEldritchObject, 1, 2);
        }
        float bob = 0.0f;
        float count = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + f;
        GL11.glPushMatrix();
        UtilsFX.bindTexture("textures/models/eldritch_cube.png");
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        ForgeDirection dir = ForgeDirection.getOrientation((int)((TileEldritchLock)te).getFacing());
        for (int u = 0; u < 4; ++u) {
            GL11.glPushMatrix();
            GL11.glRotated((double)(90 * u), (double)dir.offsetX, (double)dir.offsetY, (double)dir.offsetZ);
            for (int a = 1; a < 5 - (((TileEldritchLock)te).count + u * 5) / 20; ++a) {
                GL11.glPushMatrix();
                GL11.glTranslated((double)0.0, (double)(0.25f + 0.5f * (float)a), (double)0.0);
                float w = MathHelper.func_76126_a((float)((count + (float)(a * 10) + (float)(u * 20)) / 20.0f)) * 0.1f;
                if (a == 1 || a == 4) {
                    w = w / 2.0f + 0.2f;
                }
                GL11.glScaled((double)(0.5 + (double)w), (double)0.5, (double)(0.5 + (double)w));
                this.model.render();
                GL11.glPopMatrix();
            }
            GL11.glPopMatrix();
        }
        GL11.glPopMatrix();
        if (((TileEldritchLock)te).count >= 0) {
            GL11.glPushMatrix();
            GL11.glTranslatef((float)((float)x + 0.5f + (float)dir.offsetX * 0.525f), (float)((float)y + 0.285f), (float)((float)z + 0.5f + (float)dir.offsetZ * 0.525f));
            switch (dir.ordinal()) {
                case 5: {
                    GL11.glRotatef((float)90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 4: {
                    GL11.glRotatef((float)270.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                    break;
                }
                case 2: {
                    GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                }
            }
            GL11.glScaled((double)1.0, (double)1.0, (double)1.0);
            if (this.entityitem == null) {
                this.entityitem = new EntityItem(te.func_145831_w(), 0.0, 0.0, 0.0, this.is);
            }
            this.entityitem.field_70290_d = 0.0f;
            RenderItem.field_82407_g = true;
            RenderManager.field_78727_a.func_147940_a((Entity)this.entityitem, 0.0, 0.0, 0.0, 0.0f, 0.0f);
            RenderItem.field_82407_g = false;
            GL11.glPopMatrix();
        }
        GL11.glPushMatrix();
        GL11.glDisable((int)2912);
        switch (((TileEldritchLock)te).getFacing()) {
            case 2: {
                this.drawPlaneZNeg(x, y, z, f, 3);
                break;
            }
            case 3: {
                this.drawPlaneZPos(x, y, z, f, 3);
                break;
            }
            case 4: {
                this.drawPlaneXNeg(x, y, z, f, 3);
                break;
            }
            case 5: {
                this.drawPlaneXPos(x, y, z, f, 3);
            }
        }
        GL11.glEnable((int)2912);
        GL11.glPopMatrix();
    }

    public void drawPlaneZPos(double x, double y, double z, float f, int height) {
        float px = (float)TileEntityRendererDispatcher.field_147554_b;
        float py = (float)TileEntityRendererDispatcher.field_147555_c;
        float pz = (float)TileEntityRendererDispatcher.field_147552_d;
        GL11.glDisable((int)2896);
        Random random = new Random(31100L);
        float offset = 0.5f;
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
                tessellator.func_78377_a(x - 2.0, y + 3.0, z + (double)offset);
                tessellator.func_78377_a(x - 2.0, y - 2.0, z + (double)offset);
                tessellator.func_78377_a(x + 3.0, y - 2.0, z + (double)offset);
                tessellator.func_78377_a(x + 3.0, y + 3.0, z + (double)offset);
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
            tessellator.func_78374_a(x - 2.0, y + 3.0, z + (double)offset, 1.0, 1.0);
            tessellator.func_78374_a(x - 2.0, y - 2.0, z + (double)offset, 1.0, 0.0);
            tessellator.func_78374_a(x + 3.0, y - 2.0, z + (double)offset, 0.0, 0.0);
            tessellator.func_78374_a(x + 3.0, y + 3.0, z + (double)offset, 0.0, 1.0);
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
        float offset = 0.5f;
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
                tessellator.func_78377_a(x - 2.0, y - 2.0, z + (double)offset);
                tessellator.func_78377_a(x - 2.0, y + 3.0, z + (double)offset);
                tessellator.func_78377_a(x + 3.0, y + 3.0, z + (double)offset);
                tessellator.func_78377_a(x + 3.0, y - 2.0, z + (double)offset);
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
            tessellator.func_78374_a(x - 2.0, y - 2.0, z + (double)offset, 1.0, 1.0);
            tessellator.func_78374_a(x - 2.0, y + 3.0, z + (double)offset, 1.0, 0.0);
            tessellator.func_78374_a(x + 3.0, y + 3.0, z + (double)offset, 0.0, 0.0);
            tessellator.func_78374_a(x + 3.0, y - 2.0, z + (double)offset, 0.0, 1.0);
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
        float offset = 0.5f;
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
                tessellator.func_78377_a(x + (double)offset, y + 3.0, z - 2.0);
                tessellator.func_78377_a(x + (double)offset, y + 3.0, z + 3.0);
                tessellator.func_78377_a(x + (double)offset, y - 2.0, z + 3.0);
                tessellator.func_78377_a(x + (double)offset, y - 2.0, z - 2.0);
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
            tessellator.func_78374_a(x + (double)offset, y + 3.0, z - 2.0, 1.0, 1.0);
            tessellator.func_78374_a(x + (double)offset, y + 3.0, z + 3.0, 1.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y - 2.0, z + 3.0, 0.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y - 2.0, z - 2.0, 0.0, 1.0);
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
        float offset = 0.5f;
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
                tessellator.func_78377_a(x + (double)offset, y - 2.0, z - 2.0);
                tessellator.func_78377_a(x + (double)offset, y - 2.0, z + 3.0);
                tessellator.func_78377_a(x + (double)offset, y + 3.0, z + 3.0);
                tessellator.func_78377_a(x + (double)offset, y + 3.0, z - 2.0);
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
            tessellator.func_78374_a(x + (double)offset, y - 2.0, z - 2.0, 1.0, 1.0);
            tessellator.func_78374_a(x + (double)offset, y - 2.0, z + 3.0, 1.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y + 3.0, z + 3.0, 0.0, 0.0);
            tessellator.func_78374_a(x + (double)offset, y + 3.0, z - 2.0, 0.0, 1.0);
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
}

