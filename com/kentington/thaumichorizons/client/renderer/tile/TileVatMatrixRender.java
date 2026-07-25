/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cpw.mods.fml.client.FMLClientHandler
 *  cpw.mods.fml.relauncher.Side
 *  cpw.mods.fml.relauncher.SideOnly
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer
 *  net.minecraft.tileentity.TileEntity
 *  org.lwjgl.opengl.GL11
 *  thaumcraft.client.lib.UtilsFX
 *  thaumcraft.client.renderers.models.ModelCube
 *  thaumcraft.codechicken.lib.math.MathHelper
 */
package com.kentington.thaumichorizons.client.renderer.tile;

import com.kentington.thaumichorizons.common.tiles.TileVat;
import com.kentington.thaumichorizons.common.tiles.TileVatMatrix;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import org.lwjgl.opengl.GL11;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.client.renderers.models.ModelCube;
import thaumcraft.codechicken.lib.math.MathHelper;

@SideOnly(value=Side.CLIENT)
public class TileVatMatrixRender
extends TileEntitySpecialRenderer {
    private ModelCube model = new ModelCube(0);
    private ModelCube model_over = new ModelCube(32);
    int type = 0;

    public TileVatMatrixRender(int type) {
        this.type = type;
    }

    private void drawHalo(TileEntity is, double x, double y, double z, float par8, int count) {
        GL11.glPushMatrix();
        GL11.glTranslated((double)(x + 0.5), (double)(y + 0.5), (double)(z + 0.5));
        int q = !FMLClientHandler.instance().getClient().field_71474_y.field_74347_j ? 10 : 20;
        Tessellator tessellator = Tessellator.field_78398_a;
        RenderHelper.func_74518_a();
        float f1 = (float)count / 500.0f;
        float f3 = 0.9f;
        float f2 = 0.0f;
        Random random = new Random(245L);
        GL11.glDisable((int)3553);
        GL11.glShadeModel((int)7425);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        GL11.glDisable((int)3008);
        GL11.glEnable((int)2884);
        GL11.glDepthMask((boolean)false);
        GL11.glPushMatrix();
        for (int i = 0; i < q; ++i) {
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
            tessellator.func_78384_a(0xCC00FF, 0);
            tessellator.func_78377_a(-0.866 * (double)(f4 /= 20.0f / ((float)Math.min(count, 50) / 50.0f)), (double)(fa /= 20.0f / ((float)Math.min(count, 50) / 50.0f)), (double)(-0.5f * f4));
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
        GL11.glBlendFunc((int)770, (int)771);
        GL11.glPopMatrix();
    }

    public void renderInfusionMatrix(TileVatMatrix tile, double par2, double par4, double par6, float par8) {
        int c;
        int b;
        int a;
        TileVat vat = tile.getVat();
        GL11.glPushMatrix();
        UtilsFX.bindTexture((String)"textures/models/infuser.png");
        GL11.glTranslatef((float)((float)par2 + 0.5f), (float)((float)par4 + 0.5f), (float)((float)par6 + 0.5f));
        float ticks = (float)Minecraft.func_71410_x().field_71451_h.field_70173_aa + par8;
        float instability = 0.0f;
        float startUp = 0.0f;
        float craftCount = 0.0f;
        if (vat != null) {
            startUp = vat.startUp;
            instability = vat.instability;
            craftCount = vat.craftCount;
        }
        if (tile.func_145831_w() != null) {
            GL11.glRotatef((float)(ticks % 360.0f * startUp), (float)0.0f, (float)1.0f, (float)0.0f);
        }
        instability = Math.min(6.0f, 1.0f + instability * 0.66f * (Math.min(craftCount, 50.0f) / 50.0f));
        float b1 = 0.0f;
        float b2 = 0.0f;
        float b3 = 0.0f;
        int aa = 0;
        int bb = 0;
        int cc = 0;
        for (a = 0; a < 2; ++a) {
            for (b = 0; b < 2; ++b) {
                for (c = 0; c < 2; ++c) {
                    b1 = (float)(MathHelper.sin((double)((ticks + (float)(a * 10)) / (15.0f - instability / 2.0f))) * (double)0.01f * (double)startUp * (double)instability);
                    b2 = (float)(MathHelper.sin((double)((ticks + (float)(b * 10)) / (14.0f - instability / 2.0f))) * (double)0.01f * (double)startUp * (double)instability);
                    b3 = (float)(MathHelper.sin((double)((ticks + (float)(c * 10)) / (13.0f - instability / 2.0f))) * (double)0.01f * (double)startUp * (double)instability);
                    aa = a == 0 ? -1 : 1;
                    bb = b == 0 ? -1 : 1;
                    cc = c == 0 ? -1 : 1;
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)(b1 + (float)aa * 0.25f), (float)(b2 + (float)bb * 0.25f), (float)(b3 + (float)cc * 0.25f));
                    if (a > 0) {
                        GL11.glRotatef((float)90.0f, (float)a, (float)0.0f, (float)0.0f);
                    }
                    if (b > 0) {
                        GL11.glRotatef((float)90.0f, (float)0.0f, (float)b, (float)0.0f);
                    }
                    if (c > 0) {
                        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)c);
                    }
                    GL11.glScaled((double)0.45, (double)0.45, (double)0.45);
                    this.model.render();
                    GL11.glPopMatrix();
                }
            }
        }
        GL11.glPushMatrix();
        GL11.glAlphaFunc((int)516, (float)0.003921569f);
        GL11.glEnable((int)3042);
        GL11.glBlendFunc((int)770, (int)1);
        for (a = 0; a < 2; ++a) {
            for (b = 0; b < 2; ++b) {
                for (c = 0; c < 2; ++c) {
                    b1 = (float)(MathHelper.sin((double)((ticks + (float)(a * 10)) / (15.0f - instability / 2.0f))) * (double)0.01f * (double)startUp * (double)instability);
                    b2 = (float)(MathHelper.sin((double)((ticks + (float)(b * 10)) / (14.0f - instability / 2.0f))) * (double)0.01f * (double)startUp * (double)instability);
                    b3 = (float)(MathHelper.sin((double)((ticks + (float)(c * 10)) / (13.0f - instability / 2.0f))) * (double)0.01f * (double)startUp * (double)instability);
                    aa = a == 0 ? -1 : 1;
                    bb = b == 0 ? -1 : 1;
                    cc = c == 0 ? -1 : 1;
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)(b1 + (float)aa * 0.25f), (float)(b2 + (float)bb * 0.25f), (float)(b3 + (float)cc * 0.25f));
                    if (a > 0) {
                        GL11.glRotatef((float)90.0f, (float)a, (float)0.0f, (float)0.0f);
                    }
                    if (b > 0) {
                        GL11.glRotatef((float)90.0f, (float)0.0f, (float)b, (float)0.0f);
                    }
                    if (c > 0) {
                        GL11.glRotatef((float)90.0f, (float)0.0f, (float)0.0f, (float)c);
                    }
                    GL11.glScaled((double)0.45, (double)0.45, (double)0.45);
                    int j = 0xF000F0;
                    int k = j % 65536;
                    int l = j / 65536;
                    OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)((float)k / 1.0f), (float)((float)l / 1.0f));
                    GL11.glColor4f((float)0.8f, (float)0.1f, (float)1.0f, (float)((float)((MathHelper.sin((double)((ticks + (float)(a * 2) + (float)(b * 3) + (float)(c * 4)) / 4.0f)) * (double)0.1f + (double)0.2f) * (double)startUp)));
                    this.model_over.render();
                    GL11.glPopMatrix();
                }
            }
        }
        GL11.glDisable((int)3042);
        GL11.glAlphaFunc((int)516, (float)0.1f);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
        if (vat != null && vat.mode == 2) {
            this.drawHalo(vat, par2, par4, par6, par8, vat.craftCount);
        }
    }

    public void func_147500_a(TileEntity par1TileEntity, double par2, double par4, double par6, float par8) {
        this.renderInfusionMatrix((TileVatMatrix)par1TileEntity, par2, par4, par6, par8);
    }
}

